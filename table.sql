-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: bjyc
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `vidoURL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES (1,'中华烟降价','中华烟价格5元','415fd0b69c754b5bac93f9332d15d836.mp4');
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goodsInfo`
--

DROP TABLE IF EXISTS `goodsInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `goodsInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsType` varchar(100) DEFAULT NULL COMMENT '商品类别',
  `name` varchar(500) DEFAULT NULL COMMENT '商品名称',
  `price` double DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `taste` varchar(100) DEFAULT NULL COMMENT '烟草口味',
  `goodsDegree` varchar(100) DEFAULT NULL,
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `saleDegree` varchar(100) DEFAULT NULL COMMENT '商品促销幅度',
  `goodsNumber` int(11) DEFAULT NULL COMMENT '商品数量',
  `audience` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品受众',
  `haveBox` int(11) DEFAULT NULL COMMENT '商品是否有礼盒',
  `Recommend` varchar(100) DEFAULT NULL COMMENT '商品是否为主力推荐（1.有.0.无）',
  `imgURL` varchar(100) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goodsInfo`
--

LOCK TABLES `goodsInfo` WRITE;
/*!40000 ALTER TABLE `goodsInfo` DISABLE KEYS */;
INSERT INTO `goodsInfo` VALUES (2,'1','中华',45,'北京','普通','312','中华香烟简称“中华”，是1951年创立的香烟品牌，1952年中华烟草公司并入上海烟草公司，“中华”烟以其独特的品味和魅力征服了消费者，赢得了中国第一品牌——国烟的美誉。',NULL,10,NULL,1,NULL,'9759234ae5954386a93cf0f3fd7549a5.jpg'),(3,'0','茅台',108,'北京','普通','312','茅台酒是中国的传统特产酒。与苏格兰威士忌、法国科涅克白兰地齐名的世界三大蒸馏名酒之一，同时是中国三大名酒“茅五剑”之一。也是大曲酱香型白酒的鼻祖，更是中国的国酒，已有800多年的历史。',NULL,10,NULL,1,NULL,'125906d5b4d94ee3be7d23bd0e5f0f28.jpeg'),(4,'0','洋河蓝色',108,'江苏','普通','312','洋河蓝色经典是江苏洋河酒厂于2003年8月推出的高端品牌。“蓝色经典”商标被国家工商行政管理总局认定为中国驰名商标，这是江苏洋河酒厂继“洋河”2002年获中国驰名商标后，又一商标获得国家级认定。',NULL,10,NULL,1,NULL,NULL),(5,'1','红双喜',30,'上海','普通','312','红双喜烟是上海卷烟厂生产的一个著名香烟品牌。 \"红双喜\"牌卷烟属烤烟型传统优质产品，其浓香之气，烟味醇厚，被列为\"国家名优卷烟\"、\"上海市著名商标\"。红双喜卷烟已逐渐形成了15mg、11mg、8mg不同焦油量的系列产品。',NULL,10,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `goodsInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goodsSale`
--

DROP TABLE IF EXISTS `goodsSale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `goodsSale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsID` int(11) DEFAULT NULL,
  `saleID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goodsSale`
--

LOCK TABLES `goodsSale` WRITE;
/*!40000 ALTER TABLE `goodsSale` DISABLE KEYS */;
/*!40000 ALTER TABLE `goodsSale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MapInfo`
--

DROP TABLE IF EXISTS `MapInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MapInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target` varchar(100) DEFAULT NULL,
  `coordinateX` varchar(100) DEFAULT NULL,
  `coordinateY` varchar(100) DEFAULT NULL,
  `angle` varchar(100) DEFAULT NULL,
  `mapIMG` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `isHome` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MapInfo`
--

LOCK TABLES `MapInfo` WRITE;
/*!40000 ALTER TABLE `MapInfo` DISABLE KEYS */;
INSERT INTO `MapInfo` VALUES (1,'充电桩','0.0','0.0','90.0',NULL,1),(2,'北楼梯口','2.25982','1.11156','-90.8059',NULL,0),(3,'洗手间','17.3062','0.327983','87.5387',NULL,0);
/*!40000 ALTER TABLE `MapInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RobotInfo`
--

DROP TABLE IF EXISTS `RobotInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `RobotInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `robotid` varchar(100) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `sleepTime` int(11) DEFAULT NULL,
  `deniedWordOne` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deniedWordTwo` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ltNumber` int(11) DEFAULT NULL,
  `gtNumber` int(11) DEFAULT NULL,
  `overtimeWord` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTIme` date DEFAULT NULL,
  `welcome` varchar(100) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RobotInfo`
--

LOCK TABLES `RobotInfo` WRITE;
/*!40000 ALTER TABLE `RobotInfo` DISABLE KEYS */;
INSERT INTO `RobotInfo` VALUES (8,'00226d3580d6','合肥',10,'环境嘈杂，请靠近我的正前端说话','不好意思，我没听懂你的意思。',2,5,'您好，我没有听到你说的话，我将在十秒后进入待机页面','2018-10-04','欢迎使用烟草机器人','合肥蜀山营业厅');
/*!40000 ALTER TABLE `RobotInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saleInfo`
--

DROP TABLE IF EXISTS `saleInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `saleInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saleDegree` double DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `goodsID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saleInfo`
--

LOCK TABLES `saleInfo` WRITE;
/*!40000 ALTER TABLE `saleInfo` DISABLE KEYS */;
INSERT INTO `saleInfo` VALUES (1,123,'123141312313',NULL),(2,131,'1231',NULL),(3,12,'12313',2);
/*!40000 ALTER TABLE `saleInfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-11  9:38:53
