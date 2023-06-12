/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19 : Database - restaurantdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`restaurantdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `restaurantdb`;

/*Table structure for table `人事资料` */

DROP TABLE IF EXISTS `人事资料`;

CREATE TABLE `人事资料` (
  `员工号` int(11) NOT NULL AUTO_INCREMENT,
  `姓名` varchar(30) DEFAULT NULL,
  `性别` varchar(6) DEFAULT NULL,
  `年龄` int(11) DEFAULT NULL,
  `身份证号` varchar(18) DEFAULT NULL,
  `联系电话` varchar(11) DEFAULT NULL,
  `职位` varchar(10) DEFAULT NULL,
  `注册时间` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`员工号`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `人事资料` */

insert  into `人事资料`(`员工号`,`姓名`,`性别`,`年龄`,`身份证号`,`联系电话`,`职位`,`注册时间`) values (1,'小小西瓜','男 ',20,'4212220020000000','13215','经理','2022-08-02 10:09:42');

/*Table structure for table `员工` */

DROP TABLE IF EXISTS `员工`;

CREATE TABLE `员工` (
  `员工编号` int(11) NOT NULL AUTO_INCREMENT,
  `身份证号` varchar(18) DEFAULT NULL,
  `姓名` varchar(30) DEFAULT NULL,
  `性别` varchar(6) DEFAULT NULL,
  `联系电话` varchar(11) DEFAULT NULL,
  `年龄` int(11) DEFAULT NULL,
  `工资` varchar(10) DEFAULT NULL,
  `经理编号` int(11) DEFAULT NULL,
  PRIMARY KEY (`员工编号`),
  KEY `经理编号` (`经理编号`),
  CONSTRAINT `员工_ibfk_1` FOREIGN KEY (`经理编号`) REFERENCES `经理` (`经理编号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `员工` */

/*Table structure for table `登录` */

DROP TABLE IF EXISTS `登录`;

CREATE TABLE `登录` (
  `员工号` int(11) NOT NULL AUTO_INCREMENT,
  `用户姓名` varchar(10) DEFAULT NULL,
  `密码` varchar(10) DEFAULT '123456',
  PRIMARY KEY (`员工号`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `登录` */

insert  into `登录`(`员工号`,`用户姓名`,`密码`) values (1,'小小西瓜','123456'),(2,'小沈','123456'),(3,'沈','123456');

/*Table structure for table `经理` */

DROP TABLE IF EXISTS `经理`;

CREATE TABLE `经理` (
  `经理编号` int(11) NOT NULL AUTO_INCREMENT,
  `身份证号` varchar(18) DEFAULT NULL,
  `姓名` varchar(30) DEFAULT NULL,
  `性别` varchar(6) DEFAULT NULL,
  `联系电话` varchar(11) DEFAULT NULL,
  `年龄` int(11) DEFAULT NULL,
  PRIMARY KEY (`经理编号`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `经理` */

insert  into `经理`(`经理编号`,`身份证号`,`姓名`,`性别`,`联系电话`,`年龄`) values (1,'421222','小小西瓜','男','13797220670',20);

/*Table structure for table `菜单` */

DROP TABLE IF EXISTS `菜单`;

CREATE TABLE `菜单` (
  `菜单编号` int(11) NOT NULL AUTO_INCREMENT,
  `菜名` varchar(50) DEFAULT NULL,
  `价格` int(11) DEFAULT NULL,
  PRIMARY KEY (`菜单编号`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `菜单` */

insert  into `菜单`(`菜单编号`,`菜名`,`价格`) values (1,'红烧肉',20),(2,'蛋炒饭',8),(3,'鱼香肉丝',10),(4,'糖醋里脊',12),(5,'地三鲜',8);

/*Table structure for table `订单` */

DROP TABLE IF EXISTS `订单`;

CREATE TABLE `订单` (
  `订单编号` int(11) NOT NULL AUTO_INCREMENT,
  `消费金额` int(11) DEFAULT NULL,
  `结账时间` time DEFAULT NULL,
  `顾客编号` int(11) DEFAULT NULL,
  PRIMARY KEY (`订单编号`),
  KEY `顾客编号` (`顾客编号`),
  CONSTRAINT `订单_ibfk_1` FOREIGN KEY (`顾客编号`) REFERENCES `顾客` (`顾客编号`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `订单` */

insert  into `订单`(`订单编号`,`消费金额`,`结账时间`,`顾客编号`) values (1,44,NULL,3),(2,44,NULL,3),(3,44,NULL,3),(4,44,NULL,3),(5,8,NULL,4);

/*Table structure for table `顾客` */

DROP TABLE IF EXISTS `顾客`;

CREATE TABLE `顾客` (
  `顾客编号` int(11) NOT NULL AUTO_INCREMENT,
  `联系电话` varchar(10) DEFAULT NULL,
  `就餐桌号` int(11) DEFAULT NULL,
  PRIMARY KEY (`顾客编号`),
  KEY `就餐桌号` (`就餐桌号`),
  CONSTRAINT `顾客_ibfk_1` FOREIGN KEY (`就餐桌号`) REFERENCES `餐桌` (`餐桌号`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `顾客` */

insert  into `顾客`(`顾客编号`,`联系电话`,`就餐桌号`) values (1,'1332453',NULL),(2,'3212',NULL),(3,'333',5),(4,'2443435',4);

/*Table structure for table `餐桌` */

DROP TABLE IF EXISTS `餐桌`;

CREATE TABLE `餐桌` (
  `餐桌号` int(11) NOT NULL AUTO_INCREMENT,
  `使用状态` varchar(6) DEFAULT NULL,
  `就餐人数` int(11) DEFAULT NULL,
  PRIMARY KEY (`餐桌号`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `餐桌` */

insert  into `餐桌`(`餐桌号`,`使用状态`,`就餐人数`) values (1,'空闲',NULL),(2,'空闲',NULL),(3,'空闲',NULL),(4,'空闲',0),(5,'空闲',0),(6,'空闲',NULL),(7,'空闲',NULL),(8,'空闲',NULL),(9,'满桌',NULL);

/*Table structure for table `餐车` */

DROP TABLE IF EXISTS `餐车`;

CREATE TABLE `餐车` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `菜名` varchar(50) DEFAULT NULL,
  `价格` int(11) DEFAULT NULL,
  `菜品数量` int(11) DEFAULT '1',
  `顾客编号` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `顾客编号` (`顾客编号`),
  CONSTRAINT `餐车_ibfk_1` FOREIGN KEY (`顾客编号`) REFERENCES `顾客` (`顾客编号`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `餐车` */

insert  into `餐车`(`id`,`菜名`,`价格`,`菜品数量`,`顾客编号`) values (1,'蛋炒饭',8,1,3),(2,'地三鲜',8,1,3),(3,'蛋炒饭',8,1,3),(4,'红烧肉',20,1,3),(5,'地三鲜',8,1,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
