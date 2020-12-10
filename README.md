## 前言

Our-Task项目想要打造一个完整的清单管理系统，用户在上面创建自己待办的清单，每个清单都可以有一个分类和多个标签，每个分类和标签下面，可以包含多个创建的清单。旨在让大家从冗杂的事情中脱离出来，用更少的时间去达成自己的目标。

## 项目文档

### Github地址

[our-task：一个完整的清单管理系统](https://github.com/water76016/our-task)

### 预览地址

项目已经部署在阿里云ECS上，可以在上面尝试使用，没有限制任何功能，大家随意就行（手机上显示不佳，建议大家使用电脑访问）。**第一次进入可能为有点慢，请大家耐心等待。**大家觉的还不错的话，欢迎给个star!

[阿里云预览地址](http://www.daxingyong.cn:3000/)

### 项目部署文档

[Our-Task部署运行需要的开发环境](https://juejin.cn/post/6904081101619200013)

[ Our-Task部署运行详细文档](https://juejin.cn/post/6904085118059544590)

### 项目学习文档

[Our-Task用例图和ER图设计](https://juejin.cn/post/6904082047107596295)

[Our-task盘点开发过程中遇到的问题](https://juejin.cn/post/6904076042315677704)

[mybatis-plus如何自动填充字段创建时间和修改时间](https://juejin.cn/post/6904074648804294663)

[Mybatis-Plus如何自动生成代码](https://juejin.cn/post/6904516234654679054)

## 项目介绍

Our-Task项目是一个清单管理系统，包括前台系统使用及后台管理系统，基于SpringBoot+MyBatis实现。该系统前台包括首页、清单管理、分类管理、标签管理、帮助中心等模块。

## 技术选型

### 后端技术

|      技术      |       说明       |                             版本                             |
| :------------: | :--------------: | :----------------------------------------------------------: |
|   SpringBoot   |     MVC框架      |            https://spring.io/projects/spring-boot            |
|    MyBatis     |     ORM框架      | [ http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
|     Redis      |       缓存       |                      https://redis.io/                       |
| SpringSecurity |  认证和授权框架  |          https://spring.io/projects/spring-security          |
|  Mybatis-Plus  |  数据层代码生成  |                   https://mp.baomidou.com                    |
|     Lombok     | 简化对象封装工具 |            https://github.com/rzwitserloot/lombok            |
|   Swagger-UI   |   文档生产工具   |          https://github.com/swagger-api/swagger-ui           |
|      JWT       |   JWT登录支持    |                 https://github.com/jwtk/jjwt                 |
|     Hutool     |   Java工具类库   |                https://www.hutool.cn/docs/#/                 |

### 前端技术

|    技术    |        说明        |                         官网                          |
| :--------: | :----------------: | :---------------------------------------------------: |
|    Vue     |      前端框架      |                  https://vuejs.org/                   |
| Vue-router |      路由框架      |               https://router.vuejs.org/               |
|  Element   |     前端UI框架     | [https://element.eleme.io](https://element.eleme.io/) |
|   Axios    |    前端HTTP框架    |            https://github.com/axios/axios             |
|  v-charts  |    前端图表生成    |              https://v-charts.js.org/#/               |
|  Iconfont  | 阿里巴巴矢量图标库 |               https://www.iconfont.cn/                |

## 环境搭建

### 开发工具

|          工具           |        说明         |                     官网                      |
| :---------------------: | :-----------------: | :-------------------------------------------: |
|          IDEA           |       开发IDE       |    https://www.jetbrains.com/idea/download    |
|      RedisDesktop       | redis客户端连接工具 |       https://redisdesktop.com/download       |
|        Snipaste         |    屏幕截图工具     |           https://www.snipaste.com/           |
|         Postman         |   API接口调试工具   |           https://www.postman.com/            |
|   Navicat Premium 15    |   数据库设计工具    |      http://www.formysql.com/xiazai.html      |
|   Navicat Premium 15    |   数据库连接工具    |      http://www.formysql.com/xiazai.html      |
|          Axure          |    原型设计工具     |            https://www.axure.com/             |
| Adobe Photoshop CC 2019 |    图片处理工具     | https://www.adobe.com/products/photoshop.html |
|         Typora          |   Markdown编辑器    |              https://typora.io/               |
