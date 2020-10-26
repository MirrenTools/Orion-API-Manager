# 起步
Orion-API-Manager(以下简称: OAM)是一个API文档管理器，为后端开发人员提供API管理，也为前端人员提供友好容易查看与测试的UI; 系统集成了权限适合在一个机构中不同的团队使用，支持导入或显示OpenAPI (Swagger)等接口文档...

QQ交流群:796665306 <a target="_blank" href="//qm.qq.com/cgi-bin/qm/qr?k=d6kTExBscrndpdI5nhGDSbNedO0IJeHd&jump_from=webapi"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="QQ交流群" title="QQ交流群"></a>

如果不能观看你可以在 [哔哩哔哩中查看视频](https://www.bilibili.com/video/av76507691/)

<iframe src="//player.bilibili.com/player.html?aid=76507691&cid=130869495&page=1" width='100%' height='600px' scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"></iframe>

## 启动服务
- 项目你可以通过Releases(发行版)中下载最新已经打包的项目或者clone代码后执行**mvn clean package**
- 项目的开发环境为java 1.8.0_121,理论上java1.8以上都可以运行
- 在Orion目录中执行java -jar Orion-API-Manager.jar 或运行start.bat(windows)start.sh(unix)启动Orion服务,端口号默认为8686
- 启动Orion服务后在浏览器访问http://服务地址:端口号

## 修改配置信息
- 配置信息所在目录为./config/application.yml 是一个YAML语言的SpringBoot配置文件
- **clientAllowUnauthorized**: 是否允许未登录的用户访问客户端,**取值true=允许,false=不允许**
- **proxyAllowUnauthorized**: 是否允许未登录的用户使用代理服务,**取值true=允许,false=不允许**
- **server:port**: OAM服务的端口号,**默认为8686**
- **spring:datasource**: url=数据库连接地址,driver-class-name=数据库连接的驱动,username=数据库用户,password=数据库密码
- mybatis相关的为操作数据库的mapper一般不需要操作
- logging相关的为日志操作

## 如何查看API文档
- **方式一:** 在用户管理中创建普通用户,普通用户登录后既可以看到项目列表
- **方式二:** 在项目详情中导出接口数据(得到一个.json文件),用户访问客户端(/client/index.html)并选择右上角的 **本地加载** 打开接口数据
- **方式三:** 在项目详情中导出接口数据(得到一个.json文件),同时复制项目中的Client-UI,一起给打包给用户,用户打开Client-UI中的index.html并选择右上角的 **本地加载** 打开接口数据
## 用户与角色权限
- OAM有3中身份角色:<br>
- root=超级管理员(管理所有项目)<br>
- server=普通管理员(管理自己的项目)<br>
- client=普通用户(查看自己加入的项目)<br>
- root用户需要在./config/user.json中定义,server与client用户在管理端的用户管理中创建;<br>
- 用户管理中的标签相当于用户分组,项目中可以指定允许访问的分组(标签)<br>
- 默认只有一个超级管理员用户账号为X-root,密码为helloOAM<br>正确的做法应该使用超级管理员账号登录后,创建管理员用户(注:超级管理员可以创建管理员用户,管理员用户只能创建普通用户),再禁用超级管理员或修改超级管理员账号密码;

## 修改为其他数据库存储数据
- OAM默认使用**SQLite3**数据库做为存储数据,对应路径为./config/ConfigDB.db
- OAM修改为**MySQL**存储,你只需要在MySQL创建数据库与表后修改application.yml中的数据源指向MySQL(创建表可以下方其他数据库一样使用脚本生成,或自己根据[数据表格式说明](./introduction.md#数据表格式说明)创建)
- OAM默认已经添加了SQLite与MySQL的依赖,如果你需要修改为**其他数据库**你可以使用以下的操作:
- 1. 在pom.xml中添加对应数据库的maven依赖
- 2. 打开src/test/java/org.mirrentools.orion.scripts.CreateTable.java
- 3. 修改driver、url、username、password为数据库对应的信息后执行Main方法
- 4. 如果使用的数据库不兼容MySQL语法(比如Oracle),你需要再执行第5步
- 5. [可选] 打开src/test/java/org.mirrentools.orion.scripts.CreateTable.java<br>修改 MYBATIS_MAAPPER_TEMPLATE=注释中对应数据库的Mapper
- 6. 重新执行**mvn clean package**进行打包就可以了,数据库表与代码的生成基于[Screw-Driver](https://mirren.gitee.io/screw-driver-docs/)
## 数据表格式说明
#### 项目信息表 project 
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|key	|key	|项目的id,主键,255字,必填	|
|string	|name	|name	|项目的名字,255字,必填	|
|string	|versions	|versions	|项目的版本号,60字,选填	|
|text	|description	|description	|项目的简介,选填	|
|text	|servers	|servers	|服务集,选填,格式为(JsonArray(JsonObject)):[{<br>url(String):"服务地址",<br>description(String):描述简介}]	|
|text	|external_docs	|externalDocs	|附加文档,选填,格式为(JsonObject):{<br>description(String):附加文档说明,<br>url(String): 附加文档路径}	|
|string	|contact_name	|contactName	|联系人,60字,选填	|
|text	|contact_info	|contactInfo	|联系人信息,选填	|
|string	|owner	|owner	|项目的负责人,为空时则为超级管理员,255字,选填	|
|string	|owners	|owners	|可以查看该项目的人(标签分组),选填格式为(JsonArray(String))["标签的id"]	|
|text	|extensions	|extensions	|拓展信息,选填	|
|long	|last_time	|lastTime	|最后操作时间,选填	|
|int	|sorts	|sorts	|项目排序,选填,默认=0	|

#### 接口分组表 project_api_group 
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|group_id	|groupId	|分组的id,主键,255字,必填	|
|string	|project_id	|projectId	|项目的id,255字,必填	|
|string	|name	|name	|分组的名称,255字,必填	|
|string	|summary	|summary	|分组的简介,255字,必填	|
|text	|description	|description	|分组的详细描述,选填	|
|text	|external_docs	|externalDocs	|附加文档,选填,格式为(JsonObject):{<br>description(String):附加文档说明,<br>url(String): 附加文档路径}	|
|text	|extensions	|extensions	|拓展信息,选填	|
|int	|sorts	|sorts	|分组的排序,选填,默认=0	|

#### 接口表 project_api 
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|api_id	|apiId	|接口的id,主键,255字,必填	|
|string	|group_id	|groupId	|分组的id,255字,必填	|
|string	|method	|method	|的请求方式,255字,必填	|
|text	|path	|path	|Path,必填	|
|text	|title	|title	|名称,必填	|
|text	|description	|description	|描述,选填	|
|text	|consumes	|consumes	|请求类型,选填,格式为(JsonArray(String)):["类型"]	|
|text	|produces	|produces	|响应类型,选填,格式为(JsonArray(String)):["类型"]	|
|text	|parameters	|parameters	|请求参数,选填,格式为(JsonArray(JsonObject)):[{<br>required(boolean):是否必填,<br>in(string):位置,<br>type(String):类型,<br>name(String):名称,<br>description(String):描述,<br>def(String):默认值,<br>minLength(int):字符串最小长度,<br>maxLength(int):字符串最大长度,<br>minimum(number):数值最小值,<br>maximum(number):数值最大值,<br>maximum(number):数值最大值,<br>enums(JsonArray(String)):枚举值,<br>pattern(String):正则表达式,<br>items(JsonArray(JsonObject)):子级参数,{<br>--type(String):数据类型,<br>--name(String):名称,<br>--description(String):描述}
|text	|body	|body	|请求的Body,选填	|
|text	|responses	|responses	|响应参数,选填,格式为(JsonArray(JsonObject)):[{<br>status(int):状态码,<br>msg(int):状态信息,<br>data(JsonArray(JsonObject)):响应数据[{<br>--type(String):数据类型,<br>--in(String):响应位置,<br>--name(String):参数名称,<br>--description(String):参数描述,}]<br>}]	|
|string	|deprecated	|deprecated	|接口是否已经过期,10字,取值true或false,选填	|
|text	|external_docs	|externalDocs	|附加文档,选填,格式为(JsonObject):{<br>description(String):附加文档说明,<br>url(String): 附加文档路径}	|
|text	|additional	|additional	|附加信息,选填,格式为(JsonArray((JsonObject)):[{<br>title(String):标题,<br>description(String):附加文档说明}]	|}]	|
|text	|extensions	|extensions	|拓展信息,选填	|
|int	|sorts	|sorts	|分组的排序,选填,默认=0	|

#### 接口模板表 project_api_template
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|tid	|tid	|模板的id,主键,32字,必填	|
|string	|uid	|uid	|所属的id,255字,必填	|
|string	|name	|name	|模板的名称,255字,必填	|
|text	|api	|api	|接口的数据,与接口表,但请求参数与响应参数单独抽出来了,选填	|
|text	|parameters	|parameters	|接口中的请求参数,选填	|
|text	|responses	|responses	|接口中的响应参数,选填	|
|long	|ctime	|ctime	|创建时间,选填	|

#### 标签(用户分组)表 tags
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|tid	|tid	|标签的id,主键,255字,必填	|
|string	|tname	|tname	|标签的名称,必填	|
|string	|tid	|tid	|模板的id,主键,32字,必填	|
|int	|sorts	|sorts	|分组的排序,选填,默认=0	|
|long	|ctime	|ctime	|创建时间,选填	|

#### 用户表 users
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|uid	|uid	|用户的id,主键,255字,必填	|
|string	|role	|role	|用户的角色,取值SERVER或CLIENT,10字,必填	|
|text	|tags	|tags	|用户的标签(分组),选填格式为(JsonArray(String))["标签的id"]	|
|text	|pwd	|pwd	|用户的密码,必填	|
|string	|nickname	|nickname	|用户的名称,255,必填	|
|text	|contact	|contact	|用户的练习信息,必填	|
|text	|summary	|summary	|用户的简介,选填	|
|long	|lasttime	|lasttime	|最后登录时间,选填	|
|long	|ctime	|ctime	|创建时间,选填	|

## 关于我们
QQ交流群:796665306 <a target="_blank" href="//qm.qq.com/cgi-bin/qm/qr?k=d6kTExBscrndpdI5nhGDSbNedO0IJeHd&jump_from=webapi"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="QQ交流群" title="QQ交流群"></a><br>
联系邮箱: <a href="mailto:mirrentools@vip.qq.com">mirrentools@vip.qq.com</a><br>
github: [https://github.com/MirrenTools/Orion-API-Manager](https://github.com/MirrenTools/Orion-API-Manager)<br>
码云: [https://gitee.com/MirrenTools/Orion-API-Manager](https://gitee.com/MirrenTools/Orion-API-Manager)<br>
管理端UI: [https://github.com/MirrenTools/Orion-Api-Manager-Server](https://github.com/MirrenTools/Orion-Api-Manager-Server)<br>
客户端UI: [https://github.com/MirrenTools/Orion-API-Manager-Client](https://github.com/MirrenTools/Orion-API-Manager-Client)<br>
文档管理框架使用[vuepress](https://www.vuepress.cn/),文档静态网页由[码云Pages](https://gitee.com/)提供服务
### 维护者
[<img width='60px' src='//avatars3.githubusercontent.com/u/24805209?s=96&v=4' alt='Mirren'/>](https://github.com/shenzhenMirren)

### 赞助与打赏
本项目完全开源免费,如果你有感动你可以赞助或打赏我,你的赞助我们将在首页展示,你可以打赏后发送邮件给我!

**我的paypal** [https://www.paypal.com/paypalme/mirrentools](https://www.paypal.com/paypalme/mirrentools)

**微信收款码**<br>
<img width="400" src="/orion-api-manager-docs/weixin.png"><br>

**支付宝收款码**<br>
<img width="400" src="/orion-api-manager-docs/alipay.jpg"><br>

### License
```
The MIT License (MIT)

Copyright (c) 2019-Present https://mirrentools.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

