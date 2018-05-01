CREATE TABLE `tbl_user` (
  `id` varchar(50) NOT NULL COMMENT '用户id',
  `email` varchar(50) default NULL COMMENT '邮箱',
  `real_name` varchar(255) default NULL COMMENT '用户名',
  `password` varchar(255) default NULL COMMENT '密码',
  `header_picture` varchar(255) default NULL COMMENT '头像',
  `birthday_date` datetime default NULL COMMENT '出生日期',
  `sex` varchar(255) default NULL COMMENT '性别',
  `mobile` varchar(255) default NULL COMMENT '手机号',
  `address` varchar(255) default NULL COMMENT '地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
