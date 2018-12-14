use boot;

-- 创建用户表
create table user
(
  id        varchar(32)  primary key,
  username  varchar(255),
  password  varchar(255),
  gender    varchar(10) ,
  email     varchar(100),
  isEnable  bit,
  isExpired bit,
  isLocked  bit,
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间'
);

-- 客户端表
create table clients (
  id varchar(32) primary key,
  clientId varchar(100) not null,
  resourceIds varchar(255),
  isSecretRequired bit,
  clientSecret varchar(100),
  isScoped bit,
  scope varchar(255),
  authorizedGrantTypes varchar(255) not null,
  registeredRedirectUri varchar(255) not null,
  authorities varchar(255),
  isAutoApprove bit,
  accessTokenValiditySeconds int ,
  refreshTokenValiditySeconds int,
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间'
);

-- user 测试数据 密码123qwe
INSERT INTO boot.user (id, username, password, gender, email, isEnable, isExpired, isLocked) VALUES ('67842834823', 'admin', '$2a$10$06S5v7Mo47e8Qyv65Ltz.uhcQwfhIcgYDKVPVzBlPj6UHWV2ErbzK', '女', '阿斯达@as.com', true, false, true);

-- clients 测试数据 密码123qwe
INSERT INTO boot.clients (id, clientId, resourceIds, isSecretRequired, clientSecret, isScoped, scope, authorizedGrantTypes, registeredRedirectUri, authorities, isAutoApprove, accessTokenValiditySeconds, refreshTokenValiditySeconds, createTime, modifyTime) VALUES ('JKGJHGJHFGH89867', 'client', 'boot-server', true, '$2a$10$06S5v7Mo47e8Qyv65Ltz.uhcQwfhIcgYDKVPVzBlPj6UHWV2ErbzK', true, 'select', 'refresh_token,authorization_code,password', 'http://localhost:9000', 'CLIENT,ADMIN', false, 1800, 36000, '2018-10-16 10:02:14', '2018-12-14 09:05:03');