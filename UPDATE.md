
### 2019/4/9 更新
1. [认证服务与资源服务分离](https://github.com/LookBackInTheRain/oauth-boot-up)
### 2019/3/8 更新
1. 解决无法使用 `refresh_token` 更新`token`问题 
2. `spring-boot` 升级并测试通过

|框架/类库/数据库|   旧版本 | 新版本|
|--|--|--|
|java|11（Mac）/ 8（Win10）| - |
| spring-boot | 2.0.5.RELEASE | 2.1.3.RELEASE |
|spring-security|5.0.8.RELEASE | 5.0.8.RELEASE |
|spring-security-oauth2-autoconfigure|2.0.6.RELEASE| 2.1.3.RELEASE |
|mybatis-plus|3.0.4| 3.1.0 |
|数据库连接池（druid）|1.1.11| 1.1.14 |
|swagger-ui|2.9.2| - |
|hibernate-validator|6.0.13.Final| - |
|MySQL|5.7.22 MySQL Community Server| - |
|Redis|4.0.10| - |

### 2019/2/15 更新 
1. 配置token存储类型增加jdbc，已测试通过
 ```
  使用jdbc时必须创建的表的SQL语句在/src/doc/table.sql中,建表语句参考 :
  https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 ```
2. 修复 [#4](https://github.com/LookBackInTheRain/oauth-boot/issues/4)