#### OAUTH-BOOT

spring boot 基于 spring-security ，spring-security-oauth2 快速开发脚手架

---

#### 目前功能
1. 授权码模式，密码模式，简化模式（未测试），客户端模式（未测试）
2. JWT 
3. 自定义登录页面和授权页面
4. 自定义异常处理（完善中）

#### 配置

```yaml
boot:
  oauth:
    # token 存储方式，可选配置
    token-store-type: jwt #默认为 memory， redis
    # token签名秘钥，可选配置，默认：OAUTHBOOT@IUY09&098#UIOKNJJ-YUIT.CLUB
    token-signing-key: 123qwe 
    # 登录处理url 可选配置
    login-process-url: /auth/authorize 
```

#### 依赖

|框架/类库/数据库|   版本号 |
|--|--|
|java|11（Mac）/ 8（Win10）|
| spring-boot | 2.0.5.RELEASE |
|spring-security|5.0.8.RELEASE |
|spring-security-oauth2-autoconfigure|2.0.6.RELEASE|
|mybatis-plus|3.0.4|
|数据库连接池（druid）|1.1.11|
|swagger-ui|2.9.2|
|hibernate-validator|6.0.13.Final|
|MySQL|5.7.22 MySQL Community Server|
|Redis|4.0.10|

#### 项目效果
自定义登录和授权页面效果图
![自定义登录和授权页面效果图](https://img-blog.csdnimg.cn/20181102173054952.gif)