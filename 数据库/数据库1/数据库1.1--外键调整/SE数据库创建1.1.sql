/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.44-log : Database - se_practice1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`se_practice1` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `se_practice1`;

/*Table structure for table `current_player_effect` */

DROP TABLE IF EXISTS `current_player_effect`;

CREATE TABLE `current_player_effect` (
  `current_player_effect_id` int(10) unsigned NOT NULL,
  `hit_point` int(10) DEFAULT NULL COMMENT '当前血量效果',
  `magic_ponit` int(10) DEFAULT NULL COMMENT '当前蓝量效果',
  `attack` int(10) DEFAULT NULL COMMENT '当前攻击效果',
  `defence` int(10) DEFAULT NULL COMMENT '当前防御效果',
  `speed` int(10) DEFAULT NULL COMMENT '当前速度效果',
  PRIMARY KEY (`current_player_effect_id`),
  CONSTRAINT `fk_skill_currentPlayerEffectId` FOREIGN KEY (`current_player_effect_id`) REFERENCES `skill` (`current_player_effect_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `current_player_effect` */

/*Table structure for table `enemy_effect` */

DROP TABLE IF EXISTS `enemy_effect`;

CREATE TABLE `enemy_effect` (
  `enemy_effect_id` int(10) unsigned NOT NULL,
  `power` int(10) DEFAULT NULL COMMENT '技能威力',
  `attack` int(10) DEFAULT NULL COMMENT '当前攻击效果',
  `defence` int(10) DEFAULT NULL COMMENT '当前防御效果',
  `speed` int(10) DEFAULT NULL COMMENT '当前速度效果',
  PRIMARY KEY (`enemy_effect_id`),
  CONSTRAINT `fk_skill_enemyEffect` FOREIGN KEY (`enemy_effect_id`) REFERENCES `skill` (`enemy_effect_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `enemy_effect` */

/*Table structure for table `global_player_effect` */

DROP TABLE IF EXISTS `global_player_effect`;

CREATE TABLE `global_player_effect` (
  `global_player_effect_id` int(10) unsigned NOT NULL,
  `hit_point` int(10) DEFAULT NULL COMMENT '全局血量效果',
  `magic_point` int(10) DEFAULT NULL COMMENT '全局蓝量效果',
  `attack` int(10) DEFAULT NULL COMMENT '全局攻击效果',
  `defence` int(10) DEFAULT NULL COMMENT '全局防御效果',
  `speed` int(10) DEFAULT NULL COMMENT '全局速度效果',
  PRIMARY KEY (`global_player_effect_id`),
  CONSTRAINT `fk_item_globalPlayerEffect` FOREIGN KEY (`global_player_effect_id`) REFERENCES `item` (`global_player_effect_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `global_player_effect` */

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `inventory_seq_id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `inventory_id` int(10) NOT NULL,
  `item_name` varchar(20) DEFAULT NULL,
  `item_num` tinyint(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`inventory_seq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inventory` */

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `item_name` varchar(20) NOT NULL,
  `global_player_effect_id` int(10) unsigned NOT NULL,
  `item_attribute_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`item_name`),
  UNIQUE KEY `global_player_effect_id` (`global_player_effect_id`),
  UNIQUE KEY `item_attribute_id` (`item_attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `item` */

/*Table structure for table `item_attribute` */

DROP TABLE IF EXISTS `item_attribute`;

CREATE TABLE `item_attribute` (
  `item_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(16) DEFAULT NULL,
  `weight` int(10) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`item_attribute_id`),
  CONSTRAINT `fk_item_itemAttribute` FOREIGN KEY (`item_attribute_id`) REFERENCES `item` (`item_attribute_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `item_attribute` */

/*Table structure for table `player` */

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `player_id` int(10) unsigned NOT NULL,
  `type` tinyint(2) unsigned NOT NULL,
  `player_attribute_id` int(10) NOT NULL,
  `skill_table_id` int(10) NOT NULL,
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `player_attribute_id` (`player_attribute_id`),
  UNIQUE KEY `skill_table_id` (`skill_table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player` */

/*Table structure for table `player_attribute` */

DROP TABLE IF EXISTS `player_attribute`;

CREATE TABLE `player_attribute` (
  `player_attribute_id` int(10) NOT NULL,
  `hit_point` int(10) DEFAULT NULL,
  `magic_point` int(10) DEFAULT NULL,
  `attack` int(10) DEFAULT NULL,
  `defence` int(10) DEFAULT NULL,
  `speed` int(10) DEFAULT NULL,
  `player_load` int(10) DEFAULT NULL COMMENT '最大承重',
  `player_status_id` int(10) unsigned NOT NULL COMMENT '当前状态id',
  PRIMARY KEY (`player_attribute_id`),
  CONSTRAINT `fk_player_playerAttribute` FOREIGN KEY (`player_attribute_id`) REFERENCES `player` (`player_attribute_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player_attribute` */

/*Table structure for table `player_inventory` */

DROP TABLE IF EXISTS `player_inventory`;

CREATE TABLE `player_inventory` (
  `player_id` int(10) unsigned NOT NULL,
  `inventory_id` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player_inventory` */

/*Table structure for table `player_status` */

DROP TABLE IF EXISTS `player_status`;

CREATE TABLE `player_status` (
  `player_status_id` int(10) unsigned NOT NULL,
  `hit_point` int(10) DEFAULT NULL,
  `magic_ponit` int(10) DEFAULT NULL,
  `attack` int(10) DEFAULT NULL,
  `defence` int(10) DEFAULT NULL,
  `speed` int(10) DEFAULT NULL,
  PRIMARY KEY (`player_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player_status` */

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_id` int(10) unsigned NOT NULL,
  `room_event_id` int(10) DEFAULT NULL COMMENT '房间事件id',
  `room_status` tinyint(1) DEFAULT NULL COMMENT '房间状态。未探索为true，探索了为false',
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `room` */

/*Table structure for table `room_event` */

DROP TABLE IF EXISTS `room_event`;

CREATE TABLE `room_event` (
  `room_event_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `probability` float DEFAULT NULL,
  `enemy_id` int(10) unsigned NOT NULL COMMENT '怪物id',
  `teleportation_room_id` int(10) unsigned NOT NULL COMMENT '传送房间id',
  `gotted_item_name` varchar(20) NOT NULL COMMENT '拾取物件id',
  PRIMARY KEY (`room_event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `room_event` */

/*Table structure for table `room_inventory` */

DROP TABLE IF EXISTS `room_inventory`;

CREATE TABLE `room_inventory` (
  `room_id` int(10) unsigned NOT NULL,
  `inventory_id` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `room_inventory` */

/*Table structure for table `skill` */

DROP TABLE IF EXISTS `skill`;

CREATE TABLE `skill` (
  `skill_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `current_player_effect_id` int(10) unsigned NOT NULL COMMENT '对己效果id',
  `enemy_effect_id` int(10) unsigned NOT NULL COMMENT '对敌效果id',
  PRIMARY KEY (`skill_id`),
  UNIQUE KEY `current_player_effect_id` (`current_player_effect_id`),
  UNIQUE KEY `enemy_effect_id` (`enemy_effect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `skill` */

/*Table structure for table `skill_table` */

DROP TABLE IF EXISTS `skill_table`;

CREATE TABLE `skill_table` (
  `skill_table_seq_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `skill_table_id` int(10) DEFAULT NULL,
  `skill_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`skill_table_seq_id`),
  KEY `fk_player_skillTable` (`skill_table_id`),
  CONSTRAINT `fk_player_skillTable` FOREIGN KEY (`skill_table_id`) REFERENCES `player` (`skill_table_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `skill_table` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `account` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `player_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
