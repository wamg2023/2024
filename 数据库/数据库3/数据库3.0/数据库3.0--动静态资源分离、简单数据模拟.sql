/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.44-log : Database - se_practice3
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`se_practice3` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `se_practice3`;

/*Table structure for table `enemy_effect` */

DROP TABLE IF EXISTS `enemy_effect`;

CREATE TABLE `enemy_effect` (
  `enemy_effect_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `power` int(10) NOT NULL COMMENT '技能威力',
  `attack` int(10) NOT NULL DEFAULT '0' COMMENT '对敌攻击属性',
  `defence` int(10) NOT NULL DEFAULT '0' COMMENT '对敌防御属性',
  `speed` int(10) NOT NULL DEFAULT '0' COMMENT '对敌速度属性',
  PRIMARY KEY (`enemy_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `enemy_effect` */

insert  into `enemy_effect`(`enemy_effect_id`,`power`,`attack`,`defence`,`speed`) values (1,2,0,0,0),(2,3,0,0,0),(3,1,0,0,0),(4,4,0,0,0);

/*Table structure for table `game_saved` */

DROP TABLE IF EXISTS `game_saved`;

CREATE TABLE `game_saved` (
  `game_saved_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_attribute_id` int(10) NOT NULL DEFAULT '-1',
  `player_status_id` int(10) NOT NULL DEFAULT '-1',
  `inventory_id` int(10) NOT NULL DEFAULT '-1',
  `player_skill_table_id` int(10) NOT NULL DEFAULT '-1',
  `saved_graph_id` int(10) NOT NULL DEFAULT '-1',
  `in_room_id` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`game_saved_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `game_saved` */

insert  into `game_saved`(`game_saved_id`,`player_attribute_id`,`player_status_id`,`inventory_id`,`player_skill_table_id`,`saved_graph_id`,`in_room_id`) values (1,1,1,1,1,1,1);

/*Table structure for table `initial_graph` */

DROP TABLE IF EXISTS `initial_graph`;

CREATE TABLE `initial_graph` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `initial_graph_id` int(10) unsigned NOT NULL,
  `room_id` int(10) NOT NULL,
  `room_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '房间标识。未探索为1，探索了为0',
  `initial_inventory_id` int(10) NOT NULL DEFAULT '-1',
  `east_room_id` int(10) NOT NULL DEFAULT '-1',
  `west_room_id` int(10) NOT NULL DEFAULT '-1',
  `south_room_id` int(10) NOT NULL DEFAULT '-1',
  `north_room_id` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `initial_graph` */

insert  into `initial_graph`(`li_id`,`initial_graph_id`,`room_id`,`room_flag`,`initial_inventory_id`,`east_room_id`,`west_room_id`,`south_room_id`,`north_room_id`) values (1,1,1,1,1,2,-1,-1,-1),(2,1,2,1,2,3,1,-1,-1),(3,1,3,1,3,-1,2,-1,-1);

/*Table structure for table `initial_inventory` */

DROP TABLE IF EXISTS `initial_inventory`;

CREATE TABLE `initial_inventory` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `initial_inventory_id` int(10) NOT NULL DEFAULT '-1',
  `item_id` int(10) NOT NULL DEFAULT '-1',
  `item_num` tinyint(2) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `initial_inventory` */

insert  into `initial_inventory`(`li_id`,`initial_inventory_id`,`item_id`,`item_num`) values (1,1,1,1),(2,2,2,1),(3,3,3,1),(4,3,4,2);

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `inventory_id` int(10) NOT NULL DEFAULT '-1',
  `item_id` int(10) NOT NULL DEFAULT '-1',
  `item_num` tinyint(2) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `inventory` */

insert  into `inventory`(`li_id`,`inventory_id`,`item_id`,`item_num`) values (1,1,5,1),(2,1,6,1),(3,2,1,1),(4,3,2,1),(5,4,3,1),(6,4,4,2);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_attribute_id` int(10) NOT NULL,
  `item_effect_id` int(10) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`item_id`,`item_attribute_id`,`item_effect_id`) values (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6);

/*Table structure for table `item_attribute` */

DROP TABLE IF EXISTS `item_attribute`;

CREATE TABLE `item_attribute` (
  `item_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '物品名字',
  `description` varchar(100) NOT NULL DEFAULT '物品描述',
  `type` varchar(15) NOT NULL DEFAULT '食物',
  `weight` int(10) NOT NULL,
  PRIMARY KEY (`item_attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `item_attribute` */

insert  into `item_attribute`(`item_attribute_id`,`name`,`description`,`type`,`weight`) values (1,'苹果','清脆可口的苹果','食物',1),(2,'梨子','鲜黄多汁的梨子','食物',1),(3,'陈旧的棍子','看起来是哪个拖把柄，还挺趁手','武器',5),(4,'破旧的戒指','脏兮兮的戒指','武器',1),(5,'残破的盾牌','破洞见证着这个木盾牌的历史','武器',4),(6,'遗弃的靴子','不太合脚，但勉强能穿','武器',2);

/*Table structure for table `item_effect` */

DROP TABLE IF EXISTS `item_effect`;

CREATE TABLE `item_effect` (
  `item_effect_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) NOT NULL DEFAULT '0' COMMENT '血量效果',
  `magic_point` int(10) NOT NULL DEFAULT '0' COMMENT '蓝量效果',
  `attack` int(10) NOT NULL DEFAULT '0' COMMENT '攻击效果',
  `defence` int(10) NOT NULL DEFAULT '0' COMMENT '防御效果',
  `speed` int(10) NOT NULL DEFAULT '0' COMMENT '速度效果',
  PRIMARY KEY (`item_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `item_effect` */

insert  into `item_effect`(`item_effect_id`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`) values (1,10,0,0,0,0),(2,5,5,0,0,0),(3,0,0,10,0,0),(4,0,10,0,0,0),(5,20,0,0,10,0),(6,0,0,0,0,5);

/*Table structure for table `player__saved` */

DROP TABLE IF EXISTS `player__saved`;

CREATE TABLE `player__saved` (
  `player_id` int(10) NOT NULL,
  `selection` tinyint(1) unsigned NOT NULL COMMENT '选择的存档，2024.06.19--三个存档：1/2/3',
  `game_saved_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player__saved` */

insert  into `player__saved`(`player_id`,`selection`,`game_saved_id`) values (1,1,1);

/*Table structure for table `player_attribute` */

DROP TABLE IF EXISTS `player_attribute`;

CREATE TABLE `player_attribute` (
  `player_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL DEFAULT '玩家描述',
  `type` varchar(15) NOT NULL DEFAULT '剑客',
  `hit_point` int(10) NOT NULL DEFAULT '100',
  `magic_point` int(10) NOT NULL DEFAULT '100',
  `attack` int(10) NOT NULL DEFAULT '10',
  `defence` int(10) NOT NULL DEFAULT '10',
  `speed` int(10) NOT NULL DEFAULT '10',
  `max_weight` int(10) NOT NULL DEFAULT '100' COMMENT '最大承重',
  PRIMARY KEY (`player_attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `player_attribute` */

insert  into `player_attribute`(`player_attribute_id`,`description`,`type`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`,`max_weight`) values (1,'自诩是罗林的后世弟子，但是拙劣的剑术总是告诉了一切','见习剑客',100,100,15,15,10,100);

/*Table structure for table `player_skill_table` */

DROP TABLE IF EXISTS `player_skill_table`;

CREATE TABLE `player_skill_table` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_skill_table_id` int(10) NOT NULL DEFAULT '-1',
  `skill_id` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `player_skill_table` */

insert  into `player_skill_table`(`li_id`,`player_skill_table_id`,`skill_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4);

/*Table structure for table `player_status` */

DROP TABLE IF EXISTS `player_status`;

CREATE TABLE `player_status` (
  `player_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) NOT NULL DEFAULT '100',
  `magic_point` int(10) NOT NULL DEFAULT '100',
  PRIMARY KEY (`player_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `player_status` */

insert  into `player_status`(`player_status_id`,`hit_point`,`magic_point`) values (1,90,90);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_initial_attribute_id` int(10) NOT NULL,
  `role_initial_skill_table_id` int(10) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_initial_attribute_id`,`role_initial_skill_table_id`) values (1,1,1),(2,2,2),(3,3,3),(4,4,4);

/*Table structure for table `role_initial_attribute` */

DROP TABLE IF EXISTS `role_initial_attribute`;

CREATE TABLE `role_initial_attribute` (
  `role_initial_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL DEFAULT '角色描述',
  `type` varchar(15) NOT NULL,
  `hit_point` int(10) NOT NULL DEFAULT '100',
  `magic_point` int(10) NOT NULL DEFAULT '100',
  `attack` int(10) NOT NULL DEFAULT '10',
  `defence` int(10) NOT NULL DEFAULT '10',
  `speed` int(10) NOT NULL DEFAULT '10',
  `max_weight` int(10) NOT NULL DEFAULT '100' COMMENT '最大承重',
  PRIMARY KEY (`role_initial_attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role_initial_attribute` */

insert  into `role_initial_attribute`(`role_initial_attribute_id`,`description`,`type`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`,`max_weight`) values (1,'自诩是罗林的后世弟子，但是拙劣的剑术总是告诉了一切','见习剑客',100,100,15,15,10,100),(2,'手里拿着《见习魔法师必修一》的懵懂法师','见习法师',100,200,15,10,10,100),(3,'英勇但是莽撞的骑士，拿着不知道哪里捡来的盾牌抵御敌人的攻击','见习骑士',150,100,10,20,5,100),(4,'带着翎羽帽子，手持长弓，胆怯羞涩、不谙世事的少年','见习弓箭手',75,100,20,10,15,100);

/*Table structure for table `role_initial_skill_table` */

DROP TABLE IF EXISTS `role_initial_skill_table`;

CREATE TABLE `role_initial_skill_table` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_initial_skill_table_id` int(10) unsigned NOT NULL,
  `skill_id` int(10) NOT NULL,
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `role_initial_skill_table` */

insert  into `role_initial_skill_table`(`li_id`,`role_initial_skill_table_id`,`skill_id`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,2),(7,2,3),(8,2,4),(9,3,1),(10,3,2),(11,3,3),(12,3,4),(13,4,1),(14,4,2),(15,4,3),(16,4,4);

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '房间名字',
  `description` varchar(100) NOT NULL DEFAULT '房间描述',
  `room_event_id` int(10) NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `room` */

insert  into `room`(`room_id`,`name`,`description`,`room_event_id`) values (1,'A','AAA',1),(2,'B','BBB',2),(3,'C','CCC',3);

/*Table structure for table `room_event` */

DROP TABLE IF EXISTS `room_event`;

CREATE TABLE `room_event` (
  `room_event_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(15) NOT NULL DEFAULT '野怪出没',
  `description` varchar(100) NOT NULL DEFAULT '房间事件描述',
  `probability` float NOT NULL,
  `enemy_id` int(10) NOT NULL DEFAULT '-1' COMMENT '怪物id',
  `teleportation_room_id` int(10) NOT NULL DEFAULT '-1' COMMENT '传送房间id',
  `gotted_item_id` int(10) NOT NULL DEFAULT '-1' COMMENT '拾取物件id',
  PRIMARY KEY (`room_event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `room_event` */

insert  into `room_event`(`room_event_id`,`type`,`description`,`probability`,`enemy_id`,`teleportation_room_id`,`gotted_item_id`) values (1,'野怪出没','AAA',1,5,-1,-1),(2,'野怪出没','BBB',1,6,-1,-1),(3,'野怪出没','CCC',1,7,-1,-1);

/*Table structure for table `saved_graph` */

DROP TABLE IF EXISTS `saved_graph`;

CREATE TABLE `saved_graph` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `saved_graph_id` int(10) NOT NULL DEFAULT '-1',
  `room_id` int(10) NOT NULL DEFAULT '-1',
  `room_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '房间标识。未探索为1，探索了为0',
  `inventory_id` int(10) NOT NULL DEFAULT '-1',
  `east_room_id` int(10) NOT NULL DEFAULT '-1',
  `west_room_id` int(10) NOT NULL DEFAULT '-1',
  `south_room_id` int(10) NOT NULL DEFAULT '-1',
  `north_room_id` int(10) NOT NULL DEFAULT '-1',
  `last_room_id` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `saved_graph` */

insert  into `saved_graph`(`li_id`,`saved_graph_id`,`room_id`,`room_flag`,`inventory_id`,`east_room_id`,`west_room_id`,`south_room_id`,`north_room_id`,`last_room_id`) values (1,1,1,1,2,2,-1,-1,-1,-1),(2,1,2,1,3,1,3,-1,-1,-1),(3,1,3,1,4,-1,2,-1,-1,-1);

/*Table structure for table `self_effect` */

DROP TABLE IF EXISTS `self_effect`;

CREATE TABLE `self_effect` (
  `self_effect_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) NOT NULL DEFAULT '0' COMMENT '血量效果',
  `magic_point` int(10) NOT NULL DEFAULT '0' COMMENT '蓝量效果',
  `attack` int(10) NOT NULL DEFAULT '0' COMMENT '攻击效果',
  `defence` int(10) NOT NULL DEFAULT '0' COMMENT '防御效果',
  `speed` int(10) NOT NULL DEFAULT '0' COMMENT '速度效果',
  PRIMARY KEY (`self_effect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `self_effect` */

insert  into `self_effect`(`self_effect_id`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`) values (1,0,0,0,0,0),(2,0,0,0,0,0),(3,0,0,0,0,0),(4,0,0,0,0,0);

/*Table structure for table `skill` */

DROP TABLE IF EXISTS `skill`;

CREATE TABLE `skill` (
  `skill_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '技能名字',
  `description` varchar(100) NOT NULL DEFAULT '技能描述',
  `self_effect_id` int(10) NOT NULL COMMENT '对己效果id',
  `enemy_effect_id` int(10) NOT NULL COMMENT '对敌效果id',
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `skill` */

insert  into `skill`(`skill_id`,`name`,`description`,`self_effect_id`,`enemy_effect_id`) values (1,'撞击','我创死你',1,1),(2,'拳击','吃我一拳！',2,2),(3,'扫腿','注意重心，小子！',3,3),(4,'背摔','大力出奇迹',4,4);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `account` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `player_id` int(10) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`account`,`password`,`player_id`) values ('111','111111',1);

/* Trigger structure for table `game_saved` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_game_saved_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_game_saved_delete` AFTER DELETE ON `game_saved` FOR EACH ROW BEGIN  
    DELETE FROM player_attribute WHERE player_attribute_id = OLD.player_attribute_id;  
    DELETE FROM player_status WHERE player_status_id = OLD.player_status_id;  
    DELETE FROM inventory WHERE inventory_id = OLD.inventory_id;  
    DELETE FROM player_skill_table WHERE player_skill_table_id = OLD.player_skill_table_id;  
    DELETE FROM saved_graph WHERE saved_graph_id = OLD.saved_graph_id;  
END */$$


DELIMITER ;

/* Trigger structure for table `initial_graph` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_initial_graph_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_initial_graph_delete` AFTER DELETE ON `initial_graph` FOR EACH ROW BEGIN  
    DELETE FROM initial_inventory WHERE initial_inventory_id = OLD.initial_inventory_id; 
END */$$


DELIMITER ;

/* Trigger structure for table `item` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_item_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_item_delete` AFTER DELETE ON `item` FOR EACH ROW BEGIN  
    DELETE FROM item_attribute WHERE item_attribute_id = OLD.item_attribute_id;  
    DELETE FROM item_effect WHERE item_effect_id = OLD.item_effect_id;  
END */$$


DELIMITER ;

/* Trigger structure for table `player__saved` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_player__saved_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_player__saved_delete` AFTER DELETE ON `player__saved` FOR EACH ROW BEGIN  
    DELETE FROM game_saved WHERE game_saved_id = OLD.game_saved_id;  
END */$$


DELIMITER ;

/* Trigger structure for table `role` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_role_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_role_delete` AFTER DELETE ON `role` FOR EACH ROW BEGIN  
    DELETE FROM role_initial_attribute WHERE role_initial_attribute_id = OLD.role_initial_attribute_id;  
    DELETE FROM role_initial_skill_table WHERE role_initial_skill_table_id = OLD.role_initial_skill_table_id;    
END */$$


DELIMITER ;

/* Trigger structure for table `saved_graph` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_saved_graph_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_saved_graph_delete` AFTER DELETE ON `saved_graph` FOR EACH ROW BEGIN  
    DELETE FROM inventory WHERE inventory_id = OLD.inventory_id; 
END */$$


DELIMITER ;

/* Trigger structure for table `skill` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_skill_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_skill_delete` AFTER DELETE ON `skill` FOR EACH ROW BEGIN  
    DELETE FROM self_effect WHERE self_effect_id = OLD.self_effect_id; 
    DELETE FROM enemy_effect WHERE enemy_effect_id = OLD.enemy_effect_id; 
END */$$


DELIMITER ;

/* Trigger structure for table `user` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `after_user_delete` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `after_user_delete` AFTER DELETE ON `user` FOR EACH ROW BEGIN  
    DELETE FROM player__saved WHERE player_id = OLD.player_id;  
END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
