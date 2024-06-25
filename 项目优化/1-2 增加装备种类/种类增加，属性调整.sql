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

insert  into `enemy_effect`(`enemy_effect_id`,`power`,`attack`,`defence`,`speed`) values (1,1,0,0,0),(2,2,0,0,0),(3,3,0,0,0),(4,4,0,0,0);

/*Table structure for table `game_saved` */

DROP TABLE IF EXISTS `game_saved`;

CREATE TABLE `game_saved` (
  `game_saved_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_attribute_id` int(10) NOT NULL DEFAULT '-1',
  `player_status_id` int(10) NOT NULL DEFAULT '-1',
  `inventory_id` int(10) NOT NULL DEFAULT '-1',
  `player_equipment_id` int(10) DEFAULT '-1',
  `player_skill_table_id` int(10) NOT NULL DEFAULT '1',
  `saved_graph_id` int(10) NOT NULL DEFAULT '-1',
  `in_room_id` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`game_saved_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `game_saved` */

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
  `lower_room_id` int(10) NOT NULL DEFAULT '-1',
  `upper_room_id` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `initial_graph` */

insert  into `initial_graph`(`li_id`,`initial_graph_id`,`room_id`,`room_flag`,`initial_inventory_id`,`east_room_id`,`west_room_id`,`south_room_id`,`north_room_id`,`lower_room_id`,`upper_room_id`) values (1,1,1,1,1,2,-1,5,-1,-1,19),(2,1,2,1,2,3,1,-1,-1,-1,-1),(3,1,3,1,3,4,2,-1,-1,-1,-1),(4,1,4,1,4,-1,3,-1,-1,-1,-1),(5,1,5,1,5,6,-1,-1,1,-1,-1),(6,1,6,1,6,7,5,-1,-1,-1,-1),(7,1,7,1,7,-1,6,11,-1,-1,-1),(8,1,8,1,8,-1,-1,12,-1,-1,-1),(9,1,9,1,9,10,-1,-1,-1,-1,-1),(10,1,10,1,10,-1,9,13,-1,-1,-1),(11,1,11,1,11,-1,-1,14,7,18,-1),(12,1,12,1,12,-1,-1,15,8,-1,20),(13,1,13,1,13,14,-1,-1,10,-1,-1),(14,1,14,1,14,15,13,-1,11,-1,-1),(15,1,15,1,15,-1,14,-1,12,-1,-1),(16,1,16,1,16,17,-1,-1,-1,-1,-1),(17,1,17,1,17,18,16,-1,-1,-1,-1),(18,1,18,1,18,-1,17,-1,-1,-1,11),(19,1,19,1,19,-1,-1,-1,-1,1,-1),(20,1,20,1,20,-1,-1,-1,-1,12,-1);

/*Table structure for table `initial_inventory` */

DROP TABLE IF EXISTS `initial_inventory`;

CREATE TABLE `initial_inventory` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `initial_inventory_id` int(10) NOT NULL DEFAULT '-1',
  `item_id` int(10) NOT NULL DEFAULT '-1',
  `item_num` tinyint(2) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `initial_inventory` */

insert  into `initial_inventory`(`li_id`,`initial_inventory_id`,`item_id`,`item_num`) values (1,1,1,20),(2,2,2,20),(3,3,1,20),(4,4,2,20),(5,5,1,20),(6,6,2,20),(7,7,1,20),(8,8,2,20),(9,9,1,20),(10,10,2,20),(11,11,1,20),(12,12,2,20),(13,13,1,20),(14,14,2,20),(15,15,1,20),(16,16,2,20),(17,17,1,20),(18,18,2,20),(19,19,1,20),(20,20,2,20);

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `inventory_id` int(10) NOT NULL DEFAULT '-1',
  `item_id` int(10) NOT NULL DEFAULT '-1',
  `item_num` tinyint(2) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inventory` */

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_attribute_id` int(10) NOT NULL,
  `item_effect_id` int(10) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`item_id`,`item_attribute_id`,`item_effect_id`) values (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8);

/*Table structure for table `item_attribute` */

DROP TABLE IF EXISTS `item_attribute`;

CREATE TABLE `item_attribute` (
  `item_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '物品名字',
  `description` varchar(100) NOT NULL DEFAULT '物品描述',
  `type` varchar(15) NOT NULL DEFAULT '食物',
  `weight` int(10) NOT NULL,
  PRIMARY KEY (`item_attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `item_attribute` */

insert  into `item_attribute`(`item_attribute_id`,`name`,`description`,`type`,`weight`) values (1,'苹果','清脆可口的苹果','食物',2),(2,'梨子','鲜黄多汁的梨子','食物',2),(3,'破旧的棍子','看起来是哪个拖把柄，还挺趁手','武器',10),(4,'十字架','不知是哪个教徒的十字架，上面刻着\'leaves\'','配件',2),(5,'重甲','阴森森的洞穴里怎么会有如此重甲？上面残存的血迹默默陈述着其遭遇','防具',30),(6,'疾步之靴','不太合脚，但勉强能穿','靴子',5),(7,'藤甲','坚韧的藤甲，有两个洞，但不打紧','防具',15),(8,'长剑','“矮人国制造”','武器',20);

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `item_effect` */

insert  into `item_effect`(`item_effect_id`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`) values (1,100,0,0,0,0),(2,0,100,0,0,0),(3,0,0,10,0,0),(4,50,100,20,10,5),(5,150,0,0,100,-5),(6,0,0,0,0,10),(7,40,0,0,20,0),(8,0,0,30,0,0);

/*Table structure for table `player__saved` */

DROP TABLE IF EXISTS `player__saved`;

CREATE TABLE `player__saved` (
  `player_id` int(10) NOT NULL,
  `selection` tinyint(1) unsigned NOT NULL COMMENT '选择的存档，2024.06.19--三个存档：1/2/3',
  `game_saved_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player__saved` */

/*Table structure for table `player_attribute` */

DROP TABLE IF EXISTS `player_attribute`;

CREATE TABLE `player_attribute` (
  `player_attribute_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL DEFAULT '自诩是罗林的后世弟子，但是拙劣的剑术总是告诉了一切',
  `type` varchar(15) NOT NULL DEFAULT '见习剑客',
  `hit_point` int(10) NOT NULL DEFAULT '100',
  `magic_point` int(10) NOT NULL DEFAULT '100',
  `attack` int(10) NOT NULL DEFAULT '10',
  `defence` int(10) NOT NULL DEFAULT '10',
  `speed` int(10) NOT NULL DEFAULT '10',
  `max_weight` int(10) NOT NULL DEFAULT '100' COMMENT '最大承重',
  PRIMARY KEY (`player_attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player_attribute` */

/*Table structure for table `player_equipment` */

DROP TABLE IF EXISTS `player_equipment`;

CREATE TABLE `player_equipment` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_equipment_id` int(10) NOT NULL,
  `item_id` int(10) DEFAULT '-1',
  `type` varchar(15) DEFAULT '食物',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `player_equipment` */

/*Table structure for table `player_skill_table` */

DROP TABLE IF EXISTS `player_skill_table`;

CREATE TABLE `player_skill_table` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_skill_table_id` int(10) NOT NULL DEFAULT '-1',
  `skill_id` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player_skill_table` */

/*Table structure for table `player_status` */

DROP TABLE IF EXISTS `player_status`;

CREATE TABLE `player_status` (
  `player_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hit_point` int(10) NOT NULL DEFAULT '100',
  `magic_point` int(10) NOT NULL DEFAULT '100',
  PRIMARY KEY (`player_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `player_status` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_initial_attribute_id` int(10) NOT NULL,
  `role_initial_skill_table_id` int(10) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_initial_attribute_id`,`role_initial_skill_table_id`) values (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `role_initial_attribute` */

insert  into `role_initial_attribute`(`role_initial_attribute_id`,`description`,`type`,`hit_point`,`magic_point`,`attack`,`defence`,`speed`,`max_weight`) values (1,'自诩是罗林的后世弟子，但是拙劣的剑术总是告诉了一切','见习剑客',100,100,15,15,10,100),(2,'手里拿着《见习魔法师必修一》的懵懂法师','见习法师',100,200,15,10,10,100),(3,'英勇但是莽撞的骑士，拿着不知道哪里捡来的盾牌抵御敌人的攻击','见习骑士',150,100,10,20,5,100),(4,'戴着翎羽帽子，手持长弓，胆怯羞涩、不谙世事的少年','见习弓箭手',75,100,20,10,15,100),(5,'黏糊糊的史莱姆','怪物',100,100,20,0,0,100),(6,'矮挫挫的小矮人','怪物',100,100,40,10,15,100),(7,'绿不拉几的哥布林','怪物',100,100,30,0,15,100),(8,'手持长柄斧，肩戴鬼面具的披甲兽人','怪物',150,100,50,40,5,100),(9,'诡异的妖风让木杖的七色经幡盘旋在这位苍老的萨满祭司上','怪物',170,100,200,30,25,100),(10,'骁勇善战的骑射兽人，任何敢挑衅的探险者都会被其无情斩首','怪物',200,100,60,15,25,100),(11,'阴森森的骷髅射手','怪物',100,100,20,0,0,100);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `room` */

insert  into `room`(`room_id`,`name`,`description`,`room_event_id`) values (1,'2-1','圣徒长眠于此',1),(2,'2-2','刀甲刮擦之声不绝于耳',2),(3,'2-3','妖风袭来，悲歌徘徊',3),(4,'2-4','游戏胜利',16),(5,'2-5','卸甲之地？',5),(6,'2-6','马蹄嗒嗒，箭矢飒飒，栖骨之地，游人首弃',6),(7,'2-7','小白',7),(8,'2-8','嘻哈哈哈哈哈',8),(9,'2-9','随机传送',4),(10,'2-10','迅捷如我',10),(11,'2-11','初始之地',11),(12,'2-12','试炼之石',12),(13,'2-13','据说是孟获亲自打造？',13),(14,'2-14','~~~粘液~~~',14),(15,'2-15','\'Smite the unholy!\'',15),(16,'1-9','随机传送',4),(17,'1-10','随机传送',4),(18,'1-11','随机传送',4),(19,'3-1','随机传送',4),(20,'3-12','随机传送',4);

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `room_event` */

insert  into `room_event`(`room_event_id`,`type`,`description`,`probability`,`enemy_id`,`teleportation_room_id`,`gotted_item_id`) values (1,'拾取物品','发现了什么东西......',1,-1,-1,4),(2,'野怪出没','持斧披甲的兽人冲着你奔来，狰狞的笑声回荡在整个洞穴',1,8,-1,-1),(3,'野怪出没','狭小的洞穴内挂起阵阵阴风，恶灵的哭吼从经幡中夺路而出',1,9,-1,-1),(4,'房间传送','昏暗的地板上传来一阵光芒。。。',1,-1,-1,-1),(5,'拾取物品','发现了什么东西......',1,-1,-1,5),(6,'野怪出没','嗒嗒的马蹄声迟来于弓箭，勇猛的战士将肃清一切',1,10,-1,-1),(7,'野怪出没','\'Should eat morespinach\'',1,11,-1,-1),(8,'野怪出没','深绿的身躯隐匿着不可告人的邪祟思想',1,7,-1,-1),(10,'拾取物品','发现了什么东西......',1,-1,-1,6),(11,'拾取物品','发现了什么东西......',1,-1,-1,3),(12,'拾取物品','发现了什么东西......',1,-1,-1,8),(13,'拾取物品','发现了什么东西......',1,-1,-1,7),(14,'野怪出没','黏不拉几的东西蠕动着朝着你爬过来',1,5,-1,-1),(15,'野怪出没','\'Here I come!\'',1,6,-1,-1),(16,'游戏胜利','随着鬼魂的散去，萨满的身体逐渐消失，疲惫的你看向洞外，激动地喊道：“おお、ヴィクトリア女神よ、勝利は正義となることを感謝します!”',1,-1,-1,-1);

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
  `lower_room_id` int(10) NOT NULL DEFAULT '-1',
  `upper_room_id` int(10) NOT NULL DEFAULT '-1',
  `last_room_id` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `saved_graph` */

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

/*Table structure for table `temp_enemy` */

DROP TABLE IF EXISTS `temp_enemy`;

CREATE TABLE `temp_enemy` (
  `enemy_li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `enemy_id` int(10) DEFAULT '-1',
  `hit_point` int(10) DEFAULT '-1',
  PRIMARY KEY (`enemy_li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

/*Data for the table `temp_enemy` */

insert  into `temp_enemy`(`enemy_li_id`,`enemy_id`,`hit_point`) values (1,-1,-1);

/*Table structure for table `temp_player__saved` */

DROP TABLE IF EXISTS `temp_player__saved`;

CREATE TABLE `temp_player__saved` (
  `li_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` int(10) DEFAULT '-1',
  `selection` int(10) DEFAULT '-1',
  `game_saved_id` int(10) DEFAULT '-1',
  PRIMARY KEY (`li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

/*Data for the table `temp_player__saved` */

insert  into `temp_player__saved`(`li_id`,`player_id`,`selection`,`game_saved_id`) values (1,-1,-1,-1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `account` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `player_id` int(10) NOT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

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
