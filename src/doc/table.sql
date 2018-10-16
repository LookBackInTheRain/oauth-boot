use boot;

-- 创建用户表
create table user(
  id varchar(32) primary key,
  fullName varchar(80) not null ,
  username varchar(50) not null unique ,
  password varchar(100) not null ,
  email varchar(50) not null ,
  gender tinyint(2) not null,
  age tinyint(3) not null,
  isEnable bit not null ,
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间',
  description varchar(255)
);

-- 角色表
create table role (
  id varchar(32) primary key ,
  name varchar(50),
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间',
  description varchar(255)
);

-- 菜单表
create table menus (
  id   varchar(32) primary key,
  text varchar(50) not null,
  icon varchar(100) ,
  parentId varchar(32),
  sort int comment '排序',
  link varchar(255) ,
  `group` bit ,
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间',
  description varchar(255)
);

-- 权限表
create table authority (
  id varchar(32) primary key,
  name varchar(50) not null ,
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间',
  description varchar(255)
);

-- 资源表
create table resources(
  id varchar(32) primary key ,
  name varchar(100)  not null ,
  url varchar(255) ,
  method varchar(30) not null ,
  createTime timestamp default current_timestamp comment '创建时间',
  modifyTime timestamp default current_timestamp on update current_timestamp comment '更新时间',
  description varchar(255)
);

-- 用戶角色表
create table user_role (
  id varchar(32) primary key ,
  userId varchar(32) not null ,
  roleId varchar(32) not null ,
  foreign key(userId) references user(id),
  foreign key (roleId) references role(id)
);

-- 角色权限表
create table role_authority(
  id varchar(32) primary key ,
  roleId varchar(32) not null ,
  authorityId varchar(32) not null ,
  foreign key (roleId) references role(id),
  foreign key (authorityId) references authority(id)
);


-- 权限菜单表
create table menus_authority(
  id varchar(32) primary key ,
  menuId varchar(32),
  authorityId varchar(32),
  foreign key (menuId) references menus(id),
  foreign key (authorityId) references authority(id)
);

-- 权限资源表
create table resources_authority(
  id varchar(32) primary key ,
  resourceId varchar(32) not null ,
  authorityId varchar(32) not null ,
  foreign key (resourceId) references resources(id),
  foreign key (authorityId) references authority(id)
);

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