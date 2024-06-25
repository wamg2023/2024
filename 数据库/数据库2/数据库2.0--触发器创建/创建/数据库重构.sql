/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.44-log : Database - se_practice2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`se_practice2` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `se_practice2`;

/*Table structure for table `current_self_effect` */

DROP TABLE IF EXISTS `current_self_effect`;

CREATE TABLE `current_self_effect` (
  `current_self_effect_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) DEFAULT NULL COMMENT '当前血量效果',
  `magic_point` int(10) DEFAULT NULL COMMENT '当前蓝量效果',
  `attack` int(10) DEFAULT NULL COMMENT '当前攻击效果',
  `defence` int(10) DEFAULT NULL COMMENT '当前防御效果',
  `speed` int(10) DEFAULT NULL COMMENT '当前速度效果',
  PRIMARY KEY (`current_self_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `current_self_effect` */

insert  into `current_self_effect`(`current_self_effect_id`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`) values (1,NULL,NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `enemy_effect` */

DROP TABLE IF EXISTS `enemy_effect`;

CREATE TABLE `enemy_effect` (
  `enemy_effect_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `power` int(10) DEFAULT NULL COMMENT '技能威力',
  `attack` int(10) DEFAULT NULL COMMENT '对敌攻击属性',
  `defence` int(10) DEFAULT NULL COMMENT '对敌防御属性',
  `speed` int(10) DEFAULT NULL COMMENT '对敌速度属性',
  PRIMARY KEY (`enemy_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `enemy_effect` */

insert  into `enemy_effect`(`enemy_effect_id`,`power`,`attack`,`defence`,`speed`) values (1,2,NULL,NULL,NULL),(2,2,NULL,NULL,NULL),(3,2,NULL,NULL,NULL),(4,4,NULL,NULL,NULL);

/*Table structure for table `global_self_effect` */

DROP TABLE IF EXISTS `global_self_effect`;

CREATE TABLE `global_self_effect` (
  `global_self_effect_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) DEFAULT NULL COMMENT '全局血量效果',
  `magic_point` int(10) DEFAULT NULL COMMENT '全局蓝量效果',
  `attack` int(10) DEFAULT NULL COMMENT '全局攻击效果',
  `defence` int(10) DEFAULT NULL COMMENT '全局防御效果',
  `speed` int(10) DEFAULT NULL COMMENT '全局速度效果',
  PRIMARY KEY (`global_self_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `global_self_effect` */

insert  into `global_self_effect`(`global_self_effect_id`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`) values (1,5,5,0,0,0),(2,0,0,5,0,0);

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `inventory_seq_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `inventory_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned DEFAULT NULL,
  `item_num` tinyint(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`inventory_seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `inventory` */

insert  into `inventory`(`inventory_seq_id`,`inventory_id`,`item_id`,`item_num`) values (1,1,1,2),(2,2,NULL,NULL);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT '物品名字',
  `item_attribute_id` int(10) unsigned NOT NULL,
  `global_self_effect_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`item_id`,`name`,`item_attribute_id`,`global_self_effect_id`) values (1,'初等戒指',1,1),(2,'陈旧木剑',2,2);

/*Table structure for table `item_attribute` */

DROP TABLE IF EXISTS `item_attribute`;

CREATE TABLE `item_attribute` (
  `item_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) DEFAULT '普通物品',
  `weight` int(10) unsigned DEFAULT '0',
  `description` varchar(100) DEFAULT '物品描述',
  PRIMARY KEY (`item_attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `item_attribute` */

insert  into `item_attribute`(`item_attribute_id`,`type`,`weight`,`description`) values (1,'普通物品',2,'平平无奇的玻璃戒指'),(2,'武器',5,'一把陈旧的除妖木剑');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) unsigned DEFAULT '1' COMMENT '人物类型。玩家为1，怪物为0',
  `inventory_id` int(10) unsigned NOT NULL,
  `role_attribute_id` int(10) unsigned NOT NULL,
  `skill_table_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`role_id`,`type`,`inventory_id`,`role_attribute_id`,`skill_table_id`) values (1,1,1,1,1);

/*Table structure for table `role_attribute` */

DROP TABLE IF EXISTS `role_attribute`;

CREATE TABLE `role_attribute` (
  `role_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) unsigned DEFAULT '100',
  `magic_point` int(10) unsigned DEFAULT '100',
  `attack` int(10) unsigned DEFAULT '10',
  `defence` int(10) unsigned DEFAULT '10',
  `speed` int(10) unsigned DEFAULT '10',
  `role_load` int(10) unsigned DEFAULT '100' COMMENT '最大承重',
  `role_status_id` int(10) unsigned DEFAULT NULL COMMENT '当前状态id',
  PRIMARY KEY (`role_attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role_attribute` */

insert  into `role_attribute`(`role_attribute_id`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`,`role_load`,`role_status_id`) values (1,100,100,10,10,10,100,1);

/*Table structure for table `role_status` */

DROP TABLE IF EXISTS `role_status`;

CREATE TABLE `role_status` (
  `role_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) unsigned DEFAULT NULL,
  `magic_ponit` int(10) unsigned DEFAULT NULL,
  `attack` int(10) unsigned DEFAULT NULL,
  `defence` int(10) unsigned DEFAULT NULL,
  `speed` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`role_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `role_status` */

insert  into `role_status`(`role_status_id`,`hit_point`,`magic_ponit`,`attack`,`defence`,`speed`) values (1,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT '房间名字',
  `status` tinyint(1) unsigned DEFAULT '1' COMMENT '房间状态。未探索为1，探索了为0',
  `room_event_id` int(10) unsigned NOT NULL,
  `inventory_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `room` */

insert  into `room`(`room_id`,`name`,`status`,`room_event_id`,`inventory_id`) values (1,'广场大厅',1,1,2);

/*Table structure for table `room_event` */

DROP TABLE IF EXISTS `room_event`;

CREATE TABLE `room_event` (
  `room_event_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) DEFAULT '野怪出没',
  `description` varchar(100) DEFAULT NULL,
  `probability` float DEFAULT NULL,
  `enemy_id` int(10) unsigned DEFAULT NULL COMMENT '怪物id',
  `teleportation_room_id` int(10) unsigned DEFAULT NULL COMMENT '传送房间id',
  `gotted_item_id` int(10) unsigned DEFAULT NULL COMMENT '拾取物件id',
  PRIMARY KEY (`room_event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `room_event` */

insert  into `room_event`(`room_event_id`,`type`,`description`,`probability`,`enemy_id`,`teleportation_room_id`,`gotted_item_id`) values (1,'拾取物品','捡到一把陈旧的除妖木剑，看起来没什么用。',1,NULL,NULL,2);

/*Table structure for table `skill` */

DROP TABLE IF EXISTS `skill`;

CREATE TABLE `skill` (
  `skill_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT '技能名字',
  `current_self_effect_id` int(10) unsigned NOT NULL COMMENT '对己效果id',
  `enemy_effect_id` int(10) unsigned NOT NULL COMMENT '对敌效果id',
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `skill` */

insert  into `skill`(`skill_id`,`name`,`current_self_effect_id`,`enemy_effect_id`) values (1,'撞击',1,1),(2,'拳击',2,2),(3,'扫腿',3,3),(4,'背摔',4,4);

/*Table structure for table `skill_table` */

DROP TABLE IF EXISTS `skill_table`;

CREATE TABLE `skill_table` (
  `skill_table_seq_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `skill_table_id` int(10) unsigned NOT NULL,
  `skill_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`skill_table_seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `skill_table` */

insert  into `skill_table`(`skill_table_seq_id`,`skill_table_id`,`skill_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `account` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`account`,`password`,`role_id`) values ('1','111',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
