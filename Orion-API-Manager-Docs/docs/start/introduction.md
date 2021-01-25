# 起步
Orion-API-Manager(以下简称: OAM)是一个API文档管理器，为后端开发人员提供API管理，也为前端人员提供友好容易查看与测试的UI; 系统集成了权限适合在一个机构中不同的团队使用，支持导入或显示OpenAPI (Swagger)等接口文档...

QQ交流群:796665306 <a target="_blank" href="//qm.qq.com/cgi-bin/qm/qr?k=d6kTExBscrndpdI5nhGDSbNedO0IJeHd&jump_from=webapi"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="QQ交流群" title="QQ交流群"></a>

如果不能观看你可以在 [哔哩哔哩中查看视频](https://www.bilibili.com/video/av76507691/)

<iframe src="//player.bilibili.com/player.html?aid=76507691&cid=130869495&page=1" width='100%' height='600px' scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true"></iframe>

## 启动服务
- 项目你可以通过Releases(发行版)中下载最新已经打包的项目或者clone代码后执行**mvn clean package**进行打包,打包完毕后软件在**target/Orion**文件夹
- 项目的开发环境为java 1.8.0_121,理论上java1.8以上都可以运行
- 在Orion目录中执行java -jar Orion-API-Manager.jar 或运行start.bat(windows)start.sh(unix)启动OAM服务,端口号默认为8686
- 启动OAM服务后在浏览器访问http://服务地址:端口号 便可以进行API的管理,登录账号与密码参考下方 用户与角色权限

## 用户与角色权限
- OAM有3中身份角色:<br>
- root=超级管理员(管理所有项目)<br>
- server=普通管理员(管理自己的项目)<br>
- client=普通用户(查看自己加入的项目)<br>
- root用户需要在./config/user.json中定义,server与client用户在管理端的用户管理中创建;<br>
- 用户管理中的标签相当于用户分组,项目中可以指定允许访问的分组(标签)<br>
- **默认**只有一个超级管理员用户,登录账号为**X-root**,登录密码为**helloOAM**<br>正确的做法应该使用超级管理员账号登录后,创建管理员用户(注:超级管理员可以创建管理员用户,管理员用户只能创建普通用户),再禁用超级管理员或修改超级管理员账号密码;

## 其他人员如何查看API文档
- **方式一:** 在用户管理中创建普通用户,普通用户登录后就可以看到他参与的项目列表
- **方式二:** 在项目详情中创建分享,创建成功后复制分享连接与访问密码给其他人
- **方式三:** 在项目详情中导出接口数据(得到一个.json文件),其他人访问客户端(/client/index.html)并选择右上角的 **本地加载** 打开接口数据
- **方式四:** 在项目详情中导出接口数据(得到一个.json文件),同时复制项目中的Client-UI,一起给打包给其他人,其他人打开Client-UI中的index.html并选择右上角的 **本地加载** 打开接口数据


## 修改配置信息
- 配置信息所在目录为打包后的./config/application.yml 是一个YAML的SpringBoot配置文件
- **clientAllowUnauthorized**(Boolean): 是否允许未登录的用户访问客户端,**取值true=允许,false=不允许**
- **proxyAllowUnauthorized**(Boolean): 是否允许未登录的用户使用代理服务,**取值true=允许,false=不允许**
- **orionConsoleTitle**(String): 管理端显示的标题
- **orionConsoleWelcome**(String): 管理端登录页的欢迎语句
- **server.port**(Integer): OAM服务的端口号,**默认为8686**
- **spring.datasource**: <br>url: 数据库连接地址<br>driver-class-name: 数据库连接的驱动<br>username: 数据库用户<br>password: 数据库密码
- **mybatis**: 相关的mapper XML一般不需要操作
- **logging**: 相关的为日志操作

## 二次开发目录说明
- **config** 软件运行时的配置文件,默认的数据库与用户在这个文件夹中
- **Client-UI** OAM的客户端UI静态资源,WebMvcConfig中创建引用
- **resources** 模板资源文件,当进行软件打包时会将该文件夹复制到打包文件夹(target/Orion)中
- **SdTemplates** [Screw-Driver](https://mirren.gitee.io/screw-driver-docs/)生成代码的模板文件夹
- **Server-UI** OAM的管理端UI静态资源,WebMvcConfig中创建引用
- **Orion-API-Manager-Docs** OAM的使用说明是一个[vuepress](https://www.vuepress.cn/)项目,其中dist文件夹是编译后的使用文档
- **src/test/java/org.mirrentools.orion** <br>Constant.java: 生成代码与创建表的Bean<br>scripts.CreateCode.java: 生成代码的脚本<br>scripts.CreateTable.java: 创建表的脚本
- **src/main/resources/** <br>mappers: mybatis的mapper XML,默认是MySQL版<br>application.yml: IDE启动的配置文件
- **src/main/java/org.mirrentools.orion** <br> Main.java是启动类,其他的包是基本的三层结构

## 修改为其他数据库存储数据
- OAM默认使用**SQLite3**数据库做为数据存储,对应路径为./config/ConfigDB.db
- OAM修改为**MySQL**(或其他数据库)存储,你只需修改application.yml中的数据源指向MySQL(创建表可以使用下方的方法在测试资源文件的generate包中执行创建脚本,或自己根据[数据表格式说明](./introduction.md#数据表格式说明)创建表)
- OAM默认已经添加了SQLite与MySQL的maven jar 依赖,如果你需要修改为**其他数据库**你参考下方操作:
* 1. 在pom.xml中添加对应数据库的maven依赖
  2. 打开src/test/java/org.mirrentools.orion.scripts.CreateTable.java
  3. 修改driver、url、username、password为数据库对应的信息后执行Main方法进行创建数据库表
  4. 如果使用的数据库不兼容MySQL语法(比如Oracle),你需要再执行第5步
  5. [可选] 打开src/test/java/org.mirrentools.orion.scripts.CreateTable.java<br>修改 MYBATIS_MAAPPER_TEMPLATE=注释中对应数据库的Mapper
  6. 重新执行**mvn clean package**进行打包就可以了,数据库表与代码的生成基于[Screw-Driver](https://mirren.gitee.io/screw-driver-docs/)

## 导入或加载项目数据格式说明
项目数据说明:
``` java
{
	orionApi(String): OAM的版本
	key(String): 项目的id
	name(String): 项目的名称
	versions(String): 项目的版本
	description(String): 项目的描述(支持HTML)
	lastTime(String): 项目最后更新时间
	contactName(String): 项目联系人
	contactInfo(String): 项目联系信息(支持HTML)
	extensions(String|Object|Array): 拓展信息(客户端未实现)
	servers(Array[Object]): 项目的服务集
	--[
	----{
	------url(String): 附加文档URL
	------description(String): 附加文档说明
	----}
	--]
	externalDocs(Object): 附加文档
	--{
	----url(String): 附加文档URL
	----description(String): 附加文档说明(支持HTML)
	--}
	content(Array[Object]): API分组[{见下方API分组说明}]
}
```

API分组说明:

``` java
{
	groupId(String): 分组的id
	projectId(String): 项目的id
	name(String): 分组的名称
	summary(String): 分组的简介
	description(String): 分组的详细描述(支持HTML)
	extensions(String|Object|Array): 拓展信息(客户端未实现)
	externalDocs(Object): 附加文档
	--{
	----url(String): 附加文档URL
	----description(String): 附加文档说明(支持HTML)
	--}
	apis(Array[Object]): API列表[{见下方API说明}]
}
```

API说明:
``` java
{
	apiId(String): API的id
	groupId(String): 分组的id
	title(String): API的名称
	method(String): 请求方法
	path(String): 请求的路径
	description(String): API的描述(支持HTML)
	deprecated(Boolean): API是否已经过期
	consumes(Array[String]): 请求类型集
	parameters(Array[Object]): 请求的参数
	--[{
	----required(Boolean): 是否必填
	----in(enum): 参数位置(query|body|path|header)
	----type(enum): 参数类型(string|int|long|object|array|float|double|number|boolean)
	----name(String): 参数的名称
	----description(String): 参数的描述(支持HTML)
	----def(String): 默认值
	----enums(JsonArray(String)): 枚举值
	----minLength(int): 字符串类型最小长度
	----maxLength(int): 字符串类型最大长度
	----minimum(Number): 数值最小值
	----maximum(Number): 数值最大值
	----pattern(String): 正则表达式
	----items(JsonArray(JsonObject)): 与data一致
	------[{
	--------type(String): 响应类型与parameters的类型一致
	--------name(String): 参数的名称
	--------description(String): 参数的描述(支持HTML)
	--------items(JsonArray(JsonObject)): 与items一致
	------}]
	--}]
	body(String): 请求的body
	produces(Array[String]): 响应的类型
	body(String): 请求的body
	responses(Array[Object]): 响应结果
	--[{
	----status(Integer): 状态码
	----description(String): 附加文档说明(支持HTML)
	----data(JsonArray(JsonObject)): 响应参数:
	------[{
	--------in(enum): 响应位置(header|body)
	--------type(String): 响应类型与parameters的类型一致
	--------name(String): 参数的名称
	--------description(String): 参数的描述(支持HTML)
	--------items(JsonArray(JsonObject)): 与data一致
	------}]
	--}]
	additional(Array[Object]): 附加说明(已弃用)
	extensions(String|Object|Array): 拓展信息(客户端未实现)
	externalDocs(Object): 附加文档
	--{
	----url(String): 附加文档URL
	----description(String): 附加文档说明(支持HTML)
	--}
}
```

## 数据表格式说明
### 项目信息表 oam_project 
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|oam_key	|key	|项目的id,主键,255字,必填	|
|string	|oam_name	|name	|项目的名字,255字,必填	|
|string	|oam_versions	|versions	|项目的版本号,60字,选填	|
|text	|oam_description	|description	|项目的简介,选填	|
|text	|oam_servers	|servers	|服务集,选填,格式为(JsonArray(JsonObject)):[{<br>url(String):"服务地址",<br>description(String):描述简介}]	|
|string	|oam_contact_name	|contactName	|联系人,60字,选填	|
|text	|oam_contact_info	|contactInfo	|联系人信息,选填	|
|text	|oam_external_docs	|externalDocs	|附加文档,选填,格式为(JsonObject):{<br>description(String):附加文档说明,<br>url(String): 附加文档路径}	|
|string	|oam_owner	|owner	|项目的负责人,为空时则为超级管理员,255字,选填	|
|string	|oam_owners	|owners	|可以查看该项目的人(标签分组),选填格式为(JsonArray(String))["标签的id"]	|
|long	|oam_last_time	|lastTime	|最后操作时间,选填	|
|int	|oam_sorts	|sorts	|项目排序,选填,默认=0	|
|text	|oam_extensions	|extensions	|拓展信息,选填	|

### 项目分享记录表 oam_project_share 
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|oam_sid	|sid	|分享的id,主键,32字,必填	|
|string	|oam_pid	|pid	|项目的id,255字,必填	|
|string	|oam_pwd	|pwd	|查看密码,32字,必填	|
|long	|oam_share_time	|shareTime	|分享的时间	|

### 接口分组表 oam_project_api_group
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|oam_group_id	|groupId	|分组的id,主键,255字,必填	|
|string	|oam_project_id	|projectId	|项目的id,255字,必填	|
|string	|oam_name	|name	|分组的名称,255字,必填	|
|string	|oam_summary	|summary	|分组的简介,255字,必填	|
|text	|oam_description	|description	|分组的详细描述,选填	|
|text	|oam_external_docs	|externalDocs	|附加文档,选填,格式为(JsonObject):{<br>description(String):附加文档说明,<br>url(String): 附加文档路径}	|
|text	|oam_extensions	|extensions	|拓展信息,选填	|
|int	|oam_sorts	|sorts	|分组的排序,选填,默认=0	|

### 接口表 oam_project_api 
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|oam_api_id	|apiId	|接口的id,主键,255字,必填	|
|string	|oam_group_id	|groupId	|分组的id,255字,必填	|
|string	|oam_method	|method	|的请求方式,255字,必填	|
|text	|oam_path	|path	|Path,必填	|
|text	|oam_title	|title	|名称,必填	|
|text	|oam_description	|description	|描述,选填	|
|text	|oam_consumes	|consumes	|请求类型,选填,格式为(JsonArray(String)):["类型"]	|
|text	|oam_produces	|produces	|响应类型,选填,格式为(JsonArray(String)):["类型"]	|
|text	|oam_parameters	|parameters	|请求参数,选填,格式为(JsonArray(JsonObject)):[{<br>required(boolean):是否必填,<br>in(string):位置,<br>type(String):类型,<br>name(String):名称,<br>description(String):描述,<br>def(String):默认值,<br>minLength(int):字符串最小长度,<br>maxLength(int):字符串最大长度,<br>minimum(number):数值最小值,<br>maximum(number):数值最大值,<br>maximum(number):数值最大值,<br>enums(JsonArray(String)):枚举值,<br>pattern(String):正则表达式,<br>items(JsonArray(JsonObject)):子级参数,[{<br>　type(String):数据类型,<br>　name(String):名称,<br>　description(String):描述<br> }]<br>}]
|text	|oam_body	|body	|请求的Body,选填	|
|text	|oam_responses	|responses	|响应参数,选填,格式为(JsonArray(JsonObject)):[{<br>status(int):状态码,<br>msg(int):状态信息,<br>data(JsonArray(JsonObject)):响应数据[{<br>　type(String):数据类型,<br>　in(String):响应位置,<br>　name(String):参数名称,<br>　description(String):参数描述,}]<br>}]	|
|string	|oam_deprecated	|deprecated	|接口是否已经过期,10字,取值true或false,选填	|
|text	|oam_external_docs	|externalDocs	|附加文档,选填,格式为(JsonObject):{<br>description(String):附加文档说明,<br>url(String): 附加文档路径}	|
|text	|oam_additional	|additional	|附加信息,选填,格式为(JsonArray((JsonObject)):[{<br>　title(String):标题,<br>　description(String):附加文档说明<br>}]	|}]	|
|text	|oam_extensions	|extensions	|拓展信息,选填	|
|int	|oam_sorts	|sorts	|分组的排序,选填,默认=0	|

### 接口模板表 oam_project_api_template
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|oam_tid	|tid	|模板的id,主键,32字,必填	|
|string	|oam_uid	|uid	|所属的id,255字,必填	|
|string	|oam_name	|name	|模板的名称,255字,必填	|
|text	|oam_api	|api	|接口的数据,与接口表,但请求参数与响应参数单独抽出来了,选填	|
|text	|oam_parameters	|parameters	|接口中的请求参数,选填	|
|text	|oam_responses	|responses	|接口中的响应参数,选填	|
|long	|oam_ctime	|ctime	|创建时间,选填	|

### 标签(用户分组)表 oam_tags
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|oam_tid	|tid	|标签的id,主键,255字,必填	|
|string	|tname	|tname	|标签的名称,必填	|
|string	|oam_tname	|tid	|模板的id,主键,32字,必填	|
|int	|oam_sorts	|sorts	|分组的排序,选填,默认=0	|
|long	|oam_ctime	|ctime	|创建时间,选填	|

### 用户表 oam_users
|数据类型	|表列名称	|属性名称	|属性描述	|
|--	|--	|--	|--	|
|string	|oam_uid	|uid	|用户的id,主键,255字,必填	|
|string	|oam_role	|role	|用户的角色,取值SERVER或CLIENT,10字,必填	|
|text	|oam_tags	|tags	|用户的标签(分组),选填格式为(JsonArray(String))["标签的id"]	|
|text	|oam_pwd	|pwd	|用户的密码,必填	|
|string	|oam_nickname	|nickname	|用户的名称,255,必填	|
|text	|oam_contact	|contact	|用户的练习信息,必填	|
|text	|oam_summary	|summary	|用户的简介,选填	|
|long	|oam_ctime	|ctime	|创建时间,选填	|
|long	|oam_lasttime	|lasttime	|最后登录时间,选填	|

## 关于我们
QQ交流群:796665306 <a target="_blank" href="//qm.qq.com/cgi-bin/qm/qr?k=d6kTExBscrndpdI5nhGDSbNedO0IJeHd&jump_from=webapi"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="QQ交流群" title="QQ交流群"></a><br>
联系邮箱: <a href="mailto:mirrentools@gmail.com">mirrentools@gmail.com</a><br>
github: [https://github.com/MirrenTools/Orion-API-Manager](https://github.com/MirrenTools/Orion-API-Manager)<br>
码云: [https://gitee.com/MirrenTools/Orion-API-Manager](https://gitee.com/MirrenTools/Orion-API-Manager)<br>
管理端UI原码: [https://github.com/MirrenTools/Orion-Api-Manager-Server](https://github.com/MirrenTools/Orion-Api-Manager-Server)<br>
客户端UI原码: [https://github.com/MirrenTools/Orion-API-Manager-Client](https://github.com/MirrenTools/Orion-API-Manager-Client)<br>
文档管理框架使用[vuepress](https://www.vuepress.cn/),文档静态网页由[码云Pages](https://gitee.com/)提供服务
### 维护者
[<img width='60px' src='//avatars3.githubusercontent.com/u/24805209?s=96&v=4' alt='Mirren'/>](https://github.com/shenzhenMirren)

### 赞助与打赏
本项目完全开源免费,如果你有感动你可以赞助或打赏我,你的感慨将帮助我开发开源更多优质软件!

**我的paypal**<br>[https://www.paypal.com/paypalme/mirrentools](https://www.paypal.com/paypalme/mirrentools)

**微信收款码**<br>
<img width="400" src="/orion-api-manager-docs/weixin.png"><br>

**支付宝收款码**<br>
<img width="400" src="/orion-api-manager-docs/alipay.jpg"><br>

### License
```
The MIT License (MIT)

Copyright (c) 2019-Present http://mirrentools.org

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

