
CREATE TABLE `category_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'UID',
  `tableName` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '表名',
  `tableComment` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '表注释',
  `isUse` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否激活使用',
  `createDT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateDT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`tableName`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

insert into `category_table` (`id`, `tableName`, `tableComment`, `isUse`) values('1','base_category','分类1.0版本',0);
