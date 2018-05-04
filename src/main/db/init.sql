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

CREATE TABLE `tbl_shop` (
  `id` varchar(50) NOT NULL,
  `shop_name` varchar(200) DEFAULT NULL COMMENT '商品名',
  `price` varchar(200) DEFAULT NULL COMMENT '原价',
  `price_now` varchar(200) DEFAULT NULL COMMENT '现价',
  `shop_pictrue` varchar(100) DEFAULT NULL COMMENT '商品图片',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发布日期',
  `Num` int(11) DEFAULT NULL,
  `number` int(6) DEFAULT NULL COMMENT '数量',
  `delete_status` varchar(100) DEFAULT NULL COMMENT '删除标记',
  `create_user_id` varchar(50) DEFAULT NULL,
  `create_user_name` varchar(200) DEFAULT NULL COMMENT '上传用户名',
  `news_src` varchar(255) DEFAULT NULL COMMENT '购买连接',
  `conclum1` varchar(255) DEFAULT NULL,
  `conclum2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_talk` (
  `id` varchar(50) DEFAULT NULL,
  `shop_id` varchar(50) DEFAULT NULL COMMENT '关联商品id',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `title` varchar(255) DEFAULT NULL COMMENT '评论标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '评论内容',
  `create_user_name` varchar(100) DEFAULT NULL COMMENT '评论人名',
  `create_time` datetime DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `dislike` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tbl_article` (
  `id` varchar(50) NOT NULL,
  `article_title` varchar(200) default NULL COMMENT '文章名',
  `shop_id` varchar(50) default NULL COMMENT '关联商品id',
  `create_time` datetime default NULL COMMENT '创建日期',
  `create_user_id` varchar(50) default NULL COMMENT '发布人id',
  `create_user_name` varchar(100) default NULL COMMENT '发布人名',
  `src` varchar(200) default NULL COMMENT '文章来源',
  `article_pictrue` varchar(255) default NULL COMMENT '文章图片',
  `type` varchar(100) default NULL COMMENT '文章标签',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

