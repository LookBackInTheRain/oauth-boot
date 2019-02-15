### 2019/2/15 更新 
1. 配置token存储类型增加jdbc，已测试通过
 ```
  使用jdbc时必须创建的表的SQL语句在/src/doc/table.sql中,建表语句参考 :
  https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 ```
2. 修复 [#4](https://github.com/LookBackInTheRain/oauth-boot/issues/4)