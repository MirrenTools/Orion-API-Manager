# Orion-API-Manager  API 文档管理器
## QQ交流群号: 796665306 
Orion-API-Manager(以下简称: OAM)是一个API文档管理器，为后端开发人员提供API管理，也为前端人员提供友好容易查看与测试的UI;
系统集成了权限适合在一个机构中不同的团队使用，支持导入或显示OpenAPI (Swagger)等接口文档...

## 一些解答
- 问:为什么要做这么一个东西?
- 答:因为大多前后端分离的公司基本都跟本人公司一样,要求先有接口文档然后再进行编码;
- 问:这个东西可以做什么?可以像Swagger之类的生成API吗?
- 答:OAM不能像Swagger一样为代码生成API,但是支持导入Swagger之类的API,OAM是用来可视化创建API或查看API的;
- 问:市面上已经有接口文档管理器了为什么还要出OAM,
- 答:在本人的公司,我写的另外一个接口文档管理器已经用了多年,前后端的同事们都已经习惯了,所以将其升级优化并进行的开源;愿景是让跟我们有类似需求的程序员可以有多一个选择,使大家的编码生涯简单一点方便一点;最主要的目的是要融合本人开发的另外一个API网关形成一套完整的体系

## 项目演示地址
双十一有活动的时候再买一个服务器来做示例,可以先看看使用帮助中的视频

## 项目的结构
- 项目的后台采用了大部分人熟悉的SpringBoot2.3编写(其实本人已经几年没用Spring套装了,这几年都是用Vert.x)
- 存储默认使用Sqlite3数据库,支持更换为其他数据库(数据库操作使用MyBatis)
- 客户端使用Vue加Element-UI

## 项目如何运行
- 运行环境要求:开发环境为java 1.8.0_121,理论上java1.8以上都可以运行,如果没有java运行环境,可以看使用说明里面的免JDK教程
- 项目可以在releases(发行版)里面下载已经打包好的也可以自己打包项目
- 执行 mvn clean package 进行项目打包
- 执行完毕后 进入target/**Orion**目录,该目录包含了Client-UI(展示接口文档的UI),Server-UI(管理接口的UI),config(存放接口文档的Sqlite,配置信息与用户信息),Orion-API-Manager.jar
- 在Orion目录中执行java -jar Orion-API-Manager.jar 或直接运行start.bat(windows)start.sh(unix)启动Orion服务,端口号默认为8686
- 启动Orion服务后在浏览器访问http://服务地址:端口号
- 默认超级管理员账号为X-root,密码为helloOAM,超级管理员账号在config/user.json中管理

## 使用说明
- 第一步 启动Orion-API-Manager.jar(start.bat / start.sh)
- 第二步 访问http://服务地址:端口号
- 第三步 创建用户
- 第四步 创建项目
- 第五步 在API管理中创建分组与接口
- 第六步 在Client中查看



