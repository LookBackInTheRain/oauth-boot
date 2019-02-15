#### OAUTH-BOOT

spring-security ，spring-security-oauth2 ，string boot 学习

---

### 更新

#### 目前功能
1. 授权码模式，密码模式，简化模式（未测试），客户端模式（未测试）
2. JWT 
3. 自定义登录页面和授权页面
4. 自定义异常处理

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

#### 授权马模式
    
   1. 请求授权 http://ip:port/oauth/authorize?response_type=code&client_id=client&client_secret=123qwe&redirect_uri=http://localhost:9000&scope=select
   2. 如果没有登录会跳转到登录页面，登录后跳转到授权页面（是否会跳转到授权页面取决于是否将isAutoApprove字段的值 ）
   3. 授权后得到一个授权码，拿着授权码即可申请token

#### 密码模式
   没有配置允许客户端表单登录的，将客户端id和密码base64编码放入请求头中，根据oauth2协议规定的密码模式正确填写参数即可申请token

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
1. 自定义登录和授权页面效果图
![自定义登录和授权页面效果图](https://img-blog.csdnimg.cn/20181102173054952.gif)

#### 建表语句在src/doc/table.sql中
    
   相关的测试数据也在这个sql文件中，加密的密码统一为123qwe
   
#### 注

   请使用上述依赖所规定的版本

> 技术交流群 QQ: 931534231
> 单纯的技术交流


