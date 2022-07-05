/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.17 : Database - e_life
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`e_life` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `e_life`;

/*Table structure for table `sys_admin` */

DROP TABLE IF EXISTS `sys_admin`;

CREATE TABLE `sys_admin` (
  `aid` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_admin` */

/*Table structure for table `sys_car` */

DROP TABLE IF EXISTS `sys_car`;

CREATE TABLE `sys_car` (
  `id` varchar(16) NOT NULL,
  `owner` bigint(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_car` */

insert  into `sys_car`(`id`,`owner`) values 
('豫R66666',1);

/*Table structure for table `sys_comment` */

DROP TABLE IF EXISTS `sys_comment`;

CREATE TABLE `sys_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` bigint(20) DEFAULT NULL,
  `to_id` bigint(20) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `response` int(1) DEFAULT '0',
  `is_landlord` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_comment` */

/*Table structure for table `sys_health_check` */

DROP TABLE IF EXISTS `sys_health_check`;

CREATE TABLE `sys_health_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` int(8) DEFAULT NULL,
  `temp` decimal(10,2) DEFAULT NULL,
  `location` varchar(32) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `other_info` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`path`,`component`,`visible`,`status`,`perms`) values 
(1,'用户管理','user','system/user','0','0','system:user:list'),
(2,'停车管理','parking','system/parking','0','0','system:parking:add'),
(3,'停车管理','parking','system/parking','0','0','system:parking:log'),
(4,'停车管理','parking','system/parking','0','0','system:parking:info');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_parking` */

insert  into `sys_parking`(`id`,`car_num`,`is_registered`,`parking_num`,`start`,`end`,`total_price`) values 
(1,'豫R66666',1,'A01','2022-07-04 06:41:44',NULL,0.00);

/*Table structure for table `sys_parking_space` */

DROP TABLE IF EXISTS `sys_parking_space`;

CREATE TABLE `sys_parking_space` (
  `id` varchar(8) NOT NULL,
  `car_num` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_parking_space` */

insert  into `sys_parking_space`(`id`,`car_num`,`type`) values 
('A01','豫R66666','01'),
('A02',NULL,'01'),
('A03',NULL,'01'),
('A04',NULL,'01'),
('A05',NULL,'01'),
('B01',NULL,'02'),
('B02',NULL,'02'),
('B03',NULL,'02'),
('B04',NULL,'02'),
('B05',NULL,'02');

/*Table structure for table `sys_payment` */

DROP TABLE IF EXISTS `sys_payment`;

CREATE TABLE `sys_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) DEFAULT NULL,
  `to_admin` int(8) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `sum` decimal(10,2) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `if_paid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_payment` */

/*Table structure for table `sys_raw` */

DROP TABLE IF EXISTS `sys_raw`;

CREATE TABLE `sys_raw` (
  `user_name` varchar(64) NOT NULL,
  `phone_number` varchar(16) DEFAULT NULL,
  `raw_password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_raw` */

insert  into `sys_raw`(`user_name`,`phone_number`,`raw_password`) values 
('antares','13213761071','123456'),
('root',NULL,'123456'),
('张三',NULL,'123456');

/*Table structure for table `sys_repair` */

DROP TABLE IF EXISTS `sys_repair`;

CREATE TABLE `sys_repair` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
  `img` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  `repairer_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_repair` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `role_key` varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
  `status` char(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` int(1) DEFAULT '0' COMMENT 'del_flag',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values 
(2,1),
(2,2),
(2,3),
(2,4);

/*Table structure for table `sys_topic` */

DROP TABLE IF EXISTS `sys_topic`;

CREATE TABLE `sys_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` bigint(20) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `last_reply_time` timestamp NULL DEFAULT NULL,
  `last_reply_user` bigint(20) DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `response` int(8) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_topic` */

insert  into `sys_topic`(`id`,`from_user`,`title`,`content`,`last_reply_time`,`last_reply_user`,`status`,`response`) values 
(1,123,'测试',NULL,NULL,0,1,0),
(2,123,'测试',NULL,NULL,0,NULL,0),
(3,123,'测试',NULL,NULL,0,NULL,0);

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
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `name` varchar(16) DEFAULT NULL,
  `building_number` varchar(8) DEFAULT NULL,
  `room_number` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`password`,`status`,`email`,`phone_number`,`sex`,`avatar`,`name`,`building_number`,`room_number`) values 
(1,'antares','$2a$10$ZL5tR0RiqUyIxj7TEytCkuAbXUM55qKf753DYg5X67SEOxcV/4rk.','0',NULL,'13213761072',NULL,NULL,NULL,NULL,NULL),
(11,'root','$2a$10$K3GYJNEhtExpOEnZRheK1uAprXyx94FwVIJgkQOz5IIi1JyZEfzpu','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(12,'张三','$2a$10$LmD5vMMj9TstI/fRCBDaUuwdF3EQgmzFNTjSVkezw/s7gprwXoe0.','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(1,1),
(1,2),
(1,3),
(11,2),
(12,1);

/*Table structure for table `sys_volunteer` */

DROP TABLE IF EXISTS `sys_volunteer`;

CREATE TABLE `sys_volunteer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `free_time` varchar(32) DEFAULT NULL,
  `total_time` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_volunteer` */

/*Table structure for table `sys_volunteer_log` */

DROP TABLE IF EXISTS `sys_volunteer_log`;

CREATE TABLE `sys_volunteer_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `volunteer_id` int(8) DEFAULT NULL,
  `event` varchar(64) DEFAULT NULL,
  `total_time` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sys_volunteer_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
