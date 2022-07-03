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

/*Table structure for table `sys_admin` */

DROP TABLE IF EXISTS `sys_admin`;

CREATE TABLE `sys_admin` (
  `aid` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_admin` */

/*Table structure for table `sys_car` */

DROP TABLE IF EXISTS `sys_car`;

CREATE TABLE `sys_car` (
  `id` varchar(16) NOT NULL,
  `owner` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_car` */

/*Table structure for table `sys_comment` */

DROP TABLE IF EXISTS `sys_comment`;

CREATE TABLE `sys_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) DEFAULT NULL,
  `to_id` int(8) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`path`,`component`,`visible`,`status`,`perms`) values 
(1,'用户管理','user','system/user','0','0','system:user:list');

/*Table structure for table `sys_parking` */

DROP TABLE IF EXISTS `sys_parking`;

CREATE TABLE `sys_parking` (
  `car_num` varchar(16) NOT NULL,
  `parking_num` int(8) NOT NULL,
  `start` timestamp NULL DEFAULT NULL,
  `end` timestamp NULL DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`car_num`,`parking_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_parking` */

/*Table structure for table `sys_parking_space` */

DROP TABLE IF EXISTS `sys_parking_space`;

CREATE TABLE `sys_parking_space` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carNum` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_parking_space` */

/*Table structure for table `sys_payment` */

DROP TABLE IF EXISTS `sys_payment`;

CREATE TABLE `sys_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) DEFAULT NULL,
  `to_admin` int(8) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `sum` decimal(10,2) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_payment` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1,1);

/*Table structure for table `sys_topic` */

DROP TABLE IF EXISTS `sys_topic`;

CREATE TABLE `sys_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` int(8) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `last_reply_time` timestamp NULL DEFAULT NULL,
  `last_reply_user` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_topic` */

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`password`,`status`,`email`,`phone_number`,`sex`,`avatar`,`name`,`building_number`,`room_number`) values 
(1,'antares','$2a$10$ZL5tR0RiqUyIxj7TEytCkuAbXUM55qKf753DYg5X67SEOxcV/4rk.','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(3,'jack','$2a$10$ZL5tR0RiqUyIxj7TEytCkuAbXUM55qKf753DYg5X67SEOxcV/4rk.','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(4,'tom','$2a$10$ZL5tR0RiqUyIxj7TEytCkuAbXUM55qKf753DYg5X67SEOxcV/4rk.','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(5,'vicki','$2a$10$ZL5tR0RiqUyIxj7TEytCkuAbXUM55qKf753DYg5X67SEOxcV/4rk.','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(6,'lucy','$2a$10$ZL5tR0RiqUyIxj7TEytCkuAbXUM55qKf753DYg5X67SEOxcV/4rk.','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(7,'123456','123456','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(8,'张三','$2a$10$QfqqYrh.3MxAtFDu1uEj8eZwOAyB0u69uWZNa1Iw5w03.FL2m8YcC','0',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(1,1);

/*Table structure for table `sys_volunteer` */

DROP TABLE IF EXISTS `sys_volunteer`;

CREATE TABLE `sys_volunteer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `free_time` varchar(32) DEFAULT NULL,
  `total_time` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_volunteer` */

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
