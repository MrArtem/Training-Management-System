-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `approveaction`
--

DROP TABLE IF EXISTS `approveaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approveaction` (
  `id` bigint(20) NOT NULL,
  `date` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `approveTraining_id` bigint(20) DEFAULT NULL,
  `training_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK933tx9jcp8aih2afepjrlo57r` (`approveTraining_id`),
  KEY `FKdtx334psbl8rlvb3qf8q1rte3` (`training_id`),
  CONSTRAINT `FK933tx9jcp8aih2afepjrlo57r` FOREIGN KEY (`approveTraining_id`) REFERENCES `approvetraining` (`id`),
  CONSTRAINT `FKdtx334psbl8rlvb3qf8q1rte3` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `approveaction_approvelesson`
--

DROP TABLE IF EXISTS `approveaction_approvelesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approveaction_approvelesson` (
  `ApproveAction_id` bigint(20) NOT NULL,
  `approveLessonList_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_1x2o8fng35wnayotlsljhqxs1` (`approveLessonList_id`),
  KEY `FKqoytg5t9sy7px3et2r41ml9ra` (`ApproveAction_id`),
  CONSTRAINT `FK6a8ficx1cf37q35ctayihntju` FOREIGN KEY (`approveLessonList_id`) REFERENCES `approvelesson` (`id`),
  CONSTRAINT `FKqoytg5t9sy7px3et2r41ml9ra` FOREIGN KEY (`ApproveAction_id`) REFERENCES `approveaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `approvelesson`
--

DROP TABLE IF EXISTS `approvelesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approvelesson` (
  `id` bigint(20) NOT NULL,
  `date` bigint(20) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbqheoswsaeohduofbou7uno3h` (`lesson_id`),
  CONSTRAINT `FKbqheoswsaeohduofbou7uno3h` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `approvetraining`
--

DROP TABLE IF EXISTS `approvetraining`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approvetraining` (
  `id` bigint(20) NOT NULL,
  `additionalInfo` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `excerpt` varchar(255) DEFAULT NULL,
  `isInner` bit(1) DEFAULT NULL,
  `language` int(11) DEFAULT NULL,
  `maxSize` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `approvetraining_tag`
--

DROP TABLE IF EXISTS `approvetraining_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approvetraining_tag` (
  `ApproveTraining_id` bigint(20) NOT NULL,
  `tagList_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ge07te43y8ovjg5qalmt8a50` (`tagList_id`),
  KEY `FK8i3l2wqsh05i59giaa5o5s1o8` (`ApproveTraining_id`),
  CONSTRAINT `FK8i3l2wqsh05i59giaa5o5s1o8` FOREIGN KEY (`ApproveTraining_id`) REFERENCES `approvetraining` (`id`),
  CONSTRAINT `FKoc479xakyrhmsboxpsbe6nk15` FOREIGN KEY (`tagList_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `isSubscribe` bit(1) NOT NULL,
  `presence` bit(1) NOT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKigou20hmor4ftmimfr9543e3u` (`lesson_id`),
  KEY `FK7pfwdy3jpud8kpro12g81mjim` (`user_id`),
  CONSTRAINT `FK7pfwdy3jpud8kpro12g81mjim` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKigou20hmor4ftmimfr9543e3u` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `clear` bit(1) DEFAULT NULL,
  `creativity` bit(1) DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  `effective` int(11) DEFAULT NULL,
  `interesting` bit(1) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT NULL,
  `isPositive` bit(1) DEFAULT NULL,
  `newMaterial` bit(1) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  `recommendation` bit(1) DEFAULT NULL,
  `training_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmgr7btlhim50cmu2m5ko3johl` (`training_id`),
  KEY `FKhlbnnmiua9xpvfq8y1u1a15ie` (`user_id`),
  CONSTRAINT `FKhlbnnmiua9xpvfq8y1u1a15ie` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmgr7btlhim50cmu2m5ko3johl` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL,
  `attendance` bit(1) NOT NULL,
  `attitude` bit(1) NOT NULL,
  `commSkills` bit(1) NOT NULL,
  `date` bigint(20) NOT NULL,
  `focusOnResult` bit(1) NOT NULL,
  `motivation` bit(1) NOT NULL,
  `other` varchar(255) DEFAULT NULL,
  `questions` bit(1) NOT NULL,
  `training_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb9pi72ollanninokivmvq08u6` (`training_id`),
  KEY `FKseduepjdt7eqdfgpvdrdxef37` (`user_id`),
  CONSTRAINT `FKb9pi72ollanninokivmvq08u6` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`),
  CONSTRAINT `FKseduepjdt7eqdfgpvdrdxef37` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `filestorage`
--

DROP TABLE IF EXISTS `filestorage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filestorage` (
  `id` bigint(20) NOT NULL,
  `file` longblob,
  `name` varchar(255) NOT NULL,
  `training_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs8swqbwtomqtao97x5pimjfp8` (`training_id`),
  CONSTRAINT `FKs8swqbwtomqtao97x5pimjfp8` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `id` bigint(20) NOT NULL,
  `date` bigint(20) NOT NULL,
  `place` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `approveLesson_id` bigint(20) DEFAULT NULL,
  `training_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK10cbl2el6rogsy15nj4tg5dup` (`approveLesson_id`),
  KEY `FKpj8kjybx9w0swmydxohex61as` (`training_id`),
  CONSTRAINT `FK10cbl2el6rogsy15nj4tg5dup` FOREIGN KEY (`approveLesson_id`) REFERENCES `approvelesson` (`id`),
  CONSTRAINT `FKpj8kjybx9w0swmydxohex61as` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lesson_attendance`
--

DROP TABLE IF EXISTS `lesson_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_attendance` (
  `Lesson_id` bigint(20) NOT NULL,
  `attendanceList_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_pmuuuonchs0tkrik9ej3f3ic4` (`attendanceList_id`),
  KEY `FKhgno4n39fo1ps4pfhmv069qfm` (`Lesson_id`),
  CONSTRAINT `FK2rsiudtht04c1nejyl57hbngf` FOREIGN KEY (`attendanceList_id`) REFERENCES `attendance` (`id`),
  CONSTRAINT `FKhgno4n39fo1ps4pfhmv069qfm` FOREIGN KEY (`Lesson_id`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listener`
--

DROP TABLE IF EXISTS `listener`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listener` (
  `id` bigint(20) NOT NULL,
  `canRate` bit(1) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `training_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK754gbcdmy6q8s89i7rbgc9g9v` (`training_id`),
  KEY `FK3sjmbs59md47shef5eo5g3xm2` (`user_id`),
  CONSTRAINT `FK3sjmbs59md47shef5eo5g3xm2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK754gbcdmy6q8s89i7rbgc9g9v` FOREIGN KEY (`training_id`) REFERENCES `training` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL,
  `actionType` varchar(255) DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  `idRow` bigint(20) DEFAULT NULL,
  `tableName` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKegdx5cpkcklry5o5vv5p11l69` (`user_id`),
  CONSTRAINT `FKegdx5cpkcklry5o5vv5p11l69` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_elrjlem4sqv6tgsnand05gyl8` (`specialty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training` (
  `id` bigint(20) NOT NULL,
  `countListenerRating` int(11) NOT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `excerpt` varchar(255) DEFAULT NULL,
  `isInner` bit(1) NOT NULL,
  `isRepeat` bit(1) NOT NULL,
  `language` int(11) NOT NULL,
  `maxSize` int(11) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `sumRating` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `approveAction_id` bigint(20) DEFAULT NULL,
  `coach_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9jymhvudf4haocnggdgmteqdl` (`approveAction_id`),
  KEY `FKsvlug2lie70ktd9cd1yo585u4` (`coach_id`),
  CONSTRAINT `FK9jymhvudf4haocnggdgmteqdl` FOREIGN KEY (`approveAction_id`) REFERENCES `approveaction` (`id`),
  CONSTRAINT `FKsvlug2lie70ktd9cd1yo585u4` FOREIGN KEY (`coach_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `training_tag`
--

DROP TABLE IF EXISTS `training_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_tag` (
  `trainingList_id` bigint(20) NOT NULL,
  `tagList_id` bigint(20) NOT NULL,
  KEY `FKe0vr0mxov1pp1vw7gb2xa21h2` (`tagList_id`),
  KEY `FKnsxe4dqxuhqh8i1u7u4v5syue` (`trainingList_id`),
  CONSTRAINT `FKe0vr0mxov1pp1vw7gb2xa21h2` FOREIGN KEY (`tagList_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `FKnsxe4dqxuhqh8i1u7u4v5syue` FOREIGN KEY (`trainingList_id`) REFERENCES `training` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `userPassword_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa6iaq6y0i2pxpbhmc1h2nvldb` (`userPassword_id`),
  CONSTRAINT `FKa6iaq6y0i2pxpbhmc1h2nvldb` FOREIGN KEY (`userPassword_id`) REFERENCES `userpassword` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userpassword`
--

DROP TABLE IF EXISTS `userpassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userpassword` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhkxh2j5x2fr9km62rb8qms1pt` (`user_id`),
  CONSTRAINT `FKhkxh2j5x2fr9km62rb8qms1pt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-31 20:00:12
