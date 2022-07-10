/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 5.7.19 : Database - e_life
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`e_life` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `e_life`;

/*Table structure for table `sys_acid` */

DROP TABLE IF EXISTS `sys_acid`;

CREATE TABLE `sys_acid` (
  `id` bigint(20) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `result` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_acid` */

/*Table structure for table `sys_car` */

DROP TABLE IF EXISTS `sys_car`;

CREATE TABLE `sys_car` (
  `id` varchar(16) NOT NULL,
  `owner` bigint(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_car` */

insert  into `sys_car`(`id`,`owner`) values 
('湘A12345',11),
('湘A66666',1),
('豫R66666',1);

/*Table structure for table `sys_comment` */

DROP TABLE IF EXISTS `sys_comment`;

CREATE TABLE `sys_comment` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `from_user` bigint(16) DEFAULT NULL,
  `to_id` bigint(16) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `is_reported` tinyint(1) DEFAULT '0',
  `response` int(11) DEFAULT '0',
  `is_landlord` tinyint(1) DEFAULT '0',
  `to_user` bigint(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_comment` */

insert  into `sys_comment`(`id`,`from_user`,`to_id`,`type`,`content`,`time`,`is_reported`,`response`,`is_landlord`,`to_user`) values 
(1,11,1,0,'11','2022-07-09 08:20:43',0,0,0,NULL),
(2,11,1,0,'[微笑]11','2022-07-09 08:21:13',0,0,0,NULL),
(3,11,1,0,'你们好[偷笑]','2022-07-09 09:40:35',0,0,0,NULL),
(4,11,1,0,'你好！','2022-07-09 09:45:45',0,0,0,NULL),
(5,11,4,0,'注意疫情防控[心]','2022-07-09 14:00:11',0,0,0,NULL),
(6,11,4,0,'我再来测试一下','2022-07-09 14:01:31',0,0,0,NULL),
(7,11,4,0,'hahaha','2022-07-09 14:02:27',0,0,0,NULL),
(8,11,2,0,'朋友们好！','2022-07-09 14:03:27',0,0,0,NULL),
(9,1,1,0,'WOW!','2022-07-09 14:04:45',0,0,0,NULL);

/*Table structure for table `sys_health_check` */

DROP TABLE IF EXISTS `sys_health_check`;

CREATE TABLE `sys_health_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` int(8) DEFAULT NULL,
  `temp` decimal(10,2) DEFAULT NULL,
  `location` varchar(32) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `other_info` varchar(256) DEFAULT NULL,
  `area_level` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_health_check` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
  `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`path`,`component`,`visible`,`status`,`perms`) values 
(1,'用户管理','user','system/user','0','0','system:user:list'),
(2,'停车管理','parking','system/parking','0','0','system:parking:add'),
(3,'停车管理','parking','system/parking','0','0','system:parking:log'),
(4,'停车管理','parking','system/parking','0','0','system:parking:info'),
(5,'论坛管理','forum','system/forum','0','0','system:forum:report'),
(6,'报修管理','repair','system/repair','0','0','system:repair:tasks'),
(7,'缴费管理','payment','system/payment','0','0','system:payment:income'),
(8,'打卡管理','health','system/health','0','0','system:health:info'),
(9,'志愿管理','volunteer','system/volunteer','0','0','system:volunteer:log');

/*Table structure for table `sys_parking` */

DROP TABLE IF EXISTS `sys_parking`;

CREATE TABLE `sys_parking` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `car_num` varchar(16) NOT NULL,
  `is_registered` tinyint(2) NOT NULL,
  `parking_num` varchar(8) NOT NULL,
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end` timestamp NULL DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_parking` */

insert  into `sys_parking`(`id`,`car_num`,`is_registered`,`parking_num`,`start`,`end`,`total_price`) values 
(1,'豫R66666',1,'A01','2022-07-04 06:41:44','2022-07-05 07:00:11',72.90),
(2,'豫R66666',1,'A02','2022-07-06 01:23:02','2022-07-06 01:23:55',0.00),
(3,'豫R66666',1,'A03','2022-07-06 01:28:18','2022-07-06 01:28:21',0.00),
(4,'湘A12345',1,'A05','2022-07-10 02:24:11',NULL,0.00);

/*Table structure for table `sys_parking_space` */

DROP TABLE IF EXISTS `sys_parking_space`;

CREATE TABLE `sys_parking_space` (
  `id` varchar(8) NOT NULL,
  `car_num` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_parking_space` */

insert  into `sys_parking_space`(`id`,`car_num`,`type`) values 
('A01','','0'),
('A02','','01'),
('A03','','01'),
('A04','','01'),
('A05','湘A12345','01'),
('B01','','02'),
('B02','','02'),
('B03','','02'),
('B04','','02'),
('B05','','02'),
('C01','','03');

/*Table structure for table `sys_payment` */

DROP TABLE IF EXISTS `sys_payment`;

CREATE TABLE `sys_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) DEFAULT NULL,
  `to_admin` int(8) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `sum` decimal(10,2) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `if_paid` tinyint(2) NOT NULL,
  `finish_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_payment` */

insert  into `sys_payment`(`id`,`from_user`,`to_admin`,`type`,`sum`,`time`,`if_paid`,`finish_time`) values 
(1,1,NULL,'parking',72.90,'2022-07-05 07:04:27',0,NULL),
(2,1,NULL,'parking',100.00,'2022-07-06 01:28:21',0,NULL),
(3,11,NULL,'repair',50.00,'2022-07-05 08:43:55',1,'2022-07-10 02:23:29'),
(4,11,NULL,'repair',50.00,'2022-07-04 10:54:32',1,'2022-07-09 02:54:59'),
(5,11,NULL,'parking',60.20,'2022-07-03 11:00:35',0,'2022-07-08 11:00:38'),
(6,11,NULL,'repair',50.00,'2022-07-10 00:37:29',0,'2022-07-10 02:02:44'),
(7,11,1,'repair',50.00,'2022-07-10 00:41:11',1,'2022-07-10 02:06:10');

/*Table structure for table `sys_raw` */

DROP TABLE IF EXISTS `sys_raw`;

CREATE TABLE `sys_raw` (
  `user_name` varchar(64) NOT NULL,
  `phone_number` varchar(16) DEFAULT NULL,
  `raw_password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_raw` */

insert  into `sys_raw`(`user_name`,`phone_number`,`raw_password`) values 
('antares','13213761071','123456'),
('root','17518939776','123456');

/*Table structure for table `sys_repair` */

DROP TABLE IF EXISTS `sys_repair`;

CREATE TABLE `sys_repair` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) NOT NULL,
  `type` varchar(16) NOT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
  `img` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `status` varchar(8) NOT NULL,
  `repairer_id` int(8) DEFAULT NULL,
  `address` varchar(64) NOT NULL,
  `phone` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_repair` */

insert  into `sys_repair`(`id`,`from_user`,`type`,`start`,`end`,`img`,`description`,`status`,`repairer_id`,`address`,`phone`) values 
(1,11,'门锁','2022-07-07 12:38:23',NULL,NULL,'ha','已报修',NULL,'123','13213761071'),
(2,11,'家电','2022-07-07 13:54:54',NULL,NULL,'电视机','已报修',NULL,'A5栋1105','13213761071'),
(3,11,'电路','2022-07-07 14:01:44',NULL,NULL,'无','已完成',NULL,'B5栋504号','17518913644'),
(4,11,'电路','2022-07-08 01:26:55',NULL,NULL,'无','已接单',1,'C1栋0605','17879853228'),
(5,11,'门锁','2022-07-08 01:37:20',NULL,NULL,'456456','已取消',NULL,'45645','13213761071'),
(6,11,'家电','2022-07-10 02:19:56',NULL,NULL,'无','已报修',NULL,'A04栋602','13213761072');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `role_key` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int(1) DEFAULT '0' COMMENT 'del_flag',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`role_key`,`status`,`del_flag`) values 
(1,'用户','user','0',0),
(2,'超级管理员','admin','0',0),
(3,'维修员','repairer','0',0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `menu_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values 
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(2,6),
(2,7),
(2,8),
(2,9);

/*Table structure for table `sys_topic` */

DROP TABLE IF EXISTS `sys_topic`;

CREATE TABLE `sys_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` text,
  `create_time` timestamp NULL DEFAULT NULL,
  `last_reply_time` timestamp NULL DEFAULT NULL,
  `last_reply_user` varchar(16) DEFAULT NULL,
  `is_reported` tinyint(1) DEFAULT '0',
  `response` int(11) DEFAULT '0',
  `img` varchar(512) DEFAULT '@/assets/images/home/1.png',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_topic` */

insert  into `sys_topic`(`id`,`from_user`,`title`,`content`,`create_time`,`last_reply_time`,`last_reply_user`,`is_reported`,`response`,`img`) values 
(1,11,'电影推荐','好看！超好看！超级好看！！！\r\n\r\n下面这17部封神级电影，精选自史上最伟大的100部美国电影——BBC评选，豆瓣/IMDB评分的TOP250！每看一次就让我惊叹一次：真是好看到炸裂！没看过的赶紧呀！！\r\n\r\n建议先点赞再收藏，防止走丢！文末片单千万别错过哦~\r\n\r\n【1】\r\n\r\n电影： 《海上钢琴师》（1998）\r\n\r\n豆瓣评分：9.3\r\n\r\nIMDb评分：8.1\r\n\r\n推荐理由：引用网友对此片评价——「每个镜头都回味无穷」。','2022-07-08 07:22:30','2022-07-09 14:04:45','antares',1,5,'https://pic4.zhimg.com/v2-20332711e1def861f23c7e9dcaf5615b_r.jpg'),
(2,11,'你怎么看俄乌冲突？','据法国《世界报》网站7月7日报道，自2月24日冲突以来已超过4个月，俄罗斯军队依然没有控制乌克兰的天空。相反，莫斯科差不多每周都在损失航空器，这既包括战斗机也包括直升机或无人机。而俄军发射的弹道导弹或巡航导弹现在经常性地被乌克兰的防空系统所拦截。根据社交网络上发布的图像，7月5日有6枚向第聂伯发射的俄罗斯导弹在空中被击落。\r\n\r\n这种失败源于俄军进攻准备草率。误认为乌克兰人无力抵抗的俄罗斯人，忽视了在地面攻击之前进行的旨在摧毁敌人的防御系统的空袭。法国国际关系研究所研究员让-克里斯托弗·诺埃尔在一份纪要中指出，“和2003年的伊拉克战争比一比看。在战争开始阶段，美军在数小时内就发射了超过500枚巡航导弹并出动了1700架次飞机。而俄罗斯对乌克兰发射了150枚导弹，没有达到目标饱和攻击”。','2022-07-08 07:22:59','2022-07-09 14:03:33','root',0,1,'https://nimg.ws.126.net/?url=http%3A%2F%2Fcms-bucket.ws.126.net%2F2022%2F0709%2Fdd58972ej00rer84s010oc000xr00mec.jpg&thumbnail=660x2147483647&quality=80&type=jpg'),
(3,11,'安倍遇刺','今日之大新闻','2022-07-08 07:24:18','2022-07-08 07:24:18','root',0,0,'https://n.sinaimg.cn/default/crawl/133/w550h383/20220708/8765-d28ef9d6548ce38648faa771265f9b6a.png'),
(4,11,'小区疫情','省委、省政府对临沂市疫情处置工作高度重视，成立了省市县一体化的疫情处置工作现场指挥部，现场指挥疫情应急处置工作，确保疫情处置科学精准、运转高效。指挥部进一步增配流调队伍，对阳性感染者的活动轨迹和密接、次密接情况进行详细排查。截至今天中午，累计排查密接者1812人，次密接者3627人，全部落实了隔离管控措施。','2022-07-08 13:06:39','2022-07-09 14:02:33','root',0,3,'https://appwk.baidu.com/naapi/doc/view?ih=810&o=jpg_6&iw=1440&ix=0&iy=0&aimw=1440&rn=1&doc_id=02095a72fc4733687e21af45b307e87101f6f805&pn=1&sign=ecbeb02654b5d810e8e433047a70973f&type=1&app_ver=2.9.8.2&ua=bd_800_800_IncredibleS_2.9.8.2_2.3.7&bid=1&app_ua=IncredibleS&uid=&cuid=&fr=3&Bdi_bear=WIFI&from=3_10000&bduss=&pid=1&screen=800_800&sys_ver=2.3.7'),
(5,1,'福建号下水','6月17日上午，003型航空母舰在上海江南造船厂4号船坞正式出坞，这也意味着，中国第3艘航空母舰正式诞生！舰名“福建”号。中国也成为继美国之后，第2个能独立建造8万吨级别重型航空母舰的国家。','2022-07-02 10:16:28','2022-07-02 10:16:46','antares',0,0,'https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2022%2F0617%2F318ba15cj00rdlw5p007rc001hs00zkm.jpg&thumbnail=660x2147483647&quality=80&type=jpg');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(512) DEFAULT NULL COMMENT '头像',
  `name` varchar(16) DEFAULT NULL,
  `building_number` varchar(8) DEFAULT NULL,
  `room_number` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`password`,`status`,`email`,`phone_number`,`sex`,`avatar`,`name`,`building_number`,`room_number`) values 
(1,'antares','$2a$10$ZL5tR0RiqUyIxj7TEytCkuAbXUM55qKf753DYg5X67SEOxcV/4rk.','0',NULL,'13213761071','0','https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png','王五','B2','404'),
(11,'root','$2a$10$duVkOBly1o0zwv.36SsdaeUmDjrzovQZEHd4Fh5Smz97DRcguw9O2','0',NULL,'17518939776','1','https://ts1.cn.mm.bing.net/th/id/R-C.29a84eb867bf75b5327e7df3b1a7e32c?rik=iW9zjAJwqTB%2fdA&riu=http%3a%2f%2ftupian.qqw21.com%2farticle%2fUploadPic%2f2019-7%2f201971622263482217.jpeg&ehk=W4G6YV7SJ1LFEFGJ3r%2bsC66stsnts%2bGu%2b7tsCcMPWGA%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1','das','sad','sad');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(1,1),
(1,2),
(1,3),
(11,2);

/*Table structure for table `sys_volunteer` */

DROP TABLE IF EXISTS `sys_volunteer`;

CREATE TABLE `sys_volunteer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `free_time` varchar(32) DEFAULT NULL,
  `total_time` decimal(10,2) DEFAULT NULL,
  `uid` bigint(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_volunteer` */

insert  into `sys_volunteer`(`id`,`name`,`free_time`,`total_time`,`uid`) values 
(1,'张三','2',5.00,11);

/*Table structure for table `sys_volunteer_log` */

DROP TABLE IF EXISTS `sys_volunteer_log`;

CREATE TABLE `sys_volunteer_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `volunteer_id` int(8) DEFAULT NULL,
  `event` varchar(64) DEFAULT NULL,
  `total_time` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_volunteer_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
