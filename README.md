

这是我做的第一个比较大的项目，根据尚硅谷资料完成。

## 介绍

### 系统模块

![](https://ftp.bmp.ovh/imgs/2021/04/98e6145ce917bdc8.png)

### 系统架构

**性能**：主要考虑访问频率，每个用户每天的访问次数。项目初始阶段用户的访问量并不大，如果考 虑做运营推广，可能会迎来服务器访问量骤增，因此要考虑分布式部署，引入缓存 

**可扩展性**：系统功能会随着用户量的增加以及多变的互联网用户需求不断地扩展，因此考虑到系统 的可扩展性的要求需要使用微服务架构，引入消息中间件 

**高可用**：系统一旦宕机，将会带来不可挽回的损失，因此必须做负载均衡，甚至是异地多活这类复 杂的方案。如果数据丢失，修复将会非常麻烦，只能靠人工逐条修复，这个很难接受，因此需要考 虑存储高可靠。我们需要考虑多种异常情况：机器故障、机房故障，针对机器故障，我们需要设计
MySQL 同机房主备方案；针对机房故障，我们需要设计 MySQL 跨机房同步方案。

 **安全性**：系统的信息有一定的隐私性，例如用户的个人身份信息，不包含强隐私（例如玉照、情 感）的信息，因此使用账号密码管理、数据库访问权限控制即可。 

**成本**：视频类网站的主要成本在于服务器成本、流量成本、存储成本、流媒体研发成本，中小型公 司可以考虑使用云服务器和云服务。

### 模块说明

***online-education：在线教学根目录（父工程），管理四个子模块：***    

canal-client：canal数据库表同步模块（统计同步数据）    

common：公共模块父节点        

- common-util：工具类模块，所有模块都可以依赖于它        

- service-base：service服务的base包，包含service服务的公共配置类，所有service模块依赖于它        

- spring-security：认证与授权模块，需要认证授权的service服务依赖于它   


infrastructure：基础服务模块父节点        

- api-gateway：api网关服务    


service：api接口服务父节点 

- service-acl：用户权限管理api接口服务（用户管理、角色管理和权限管理等） 


- service-cms：cms api接口服务 

- service-edu：教学相关api接口服务 

- service-msm：短信api接口服务 

- service-order：订单相关api接口服务 

- service-oss：阿里云oss api接口服务 

- service-statistics：统计报表api接口服务 

- service-ucenter：会员api接口服务 

- service-vod：视频点播api接口服务


### 技术介绍

系统后端接口部分，使用目前流行的SpringBoot+SpringCloud进行微服务架构，使用Feign、Gateway、Hystrix，以及阿里巴巴的Nacos等组件搭建了项目的基础环境。项目中还使用MyBatisPlus进行持久层的操作，使用了OAuth2+JWT实现了分布式的访问，项目中整合了SpringSecurity进行了权限控制。除此之外，项目中使用了阿里巴巴的EasyExcel实现对Excel的读写操作，使用了Redis进行首页数据的缓存，使用Git进行代码的版本控制，还整合了Swagger生成接口文档 。

系统前端部分，使用主流的前端框架Vue，使用Es6的开发规范，采用模块化的开发模式，搭建页面环境使用了Nuxt框架和vue-admin-template模板，使用Element-ui进行页面布局。前端环境中使用Npm进行依赖管理，使用Babel进行代码转换，使用Webpack进行静态资源的打包，采用axios进行Ajax请求调用，使用了ECharts进行数据的图表展示。



