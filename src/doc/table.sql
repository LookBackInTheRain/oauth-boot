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