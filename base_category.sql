
create table `base_category` (
	`code` varchar (150),
	`parent_code` varchar (150),
	`title` varchar (60),
	`orderNo` int (4),
	`level` int (4),
	`id` int (11),
	`flag` tinyint (4),
	`old_code` varchar (150),
	`source_version` varchar (60),
	`createDT` timestamp 
); 
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('1','0','针织','1','0','1','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('1/4','1','汗布','1','1','4','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('1/4/10','1/4','印花','1','2','10','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('1/4/11','1/4','色织','2','2','11','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('1/4/11/12','1/4/11','压花','1','3','12','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('1/5','1','罗纹','3','1','5','1','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('1/6','1','粗针','2','1','6','1','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('2','0','梭织','2','0','2','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('2/7','2','毛圈','1','1','7','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('2/8','2','毛衫','2','1','8','0','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('3','0','辅料','3','0','3','1','','','2016-08-24 16:22:16');
insert into `base_category` (`code`, `parent_code`, `title`, `orderNo`, `level`, `id`, `flag`, `old_code`, `source_version`, `createDT`) values('3/9','3','毛巾布','1','1','9','0','','','2016-08-24 16:22:16');
