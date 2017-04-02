/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL
 Source Server Type    : MySQL
 Source Server Version : 50620
 Source Host           : localhost
 Source Database       : NBAD3

 Target Server Type    : MySQL
 Target Server Version : 50620
 File Encoding         : utf-8

*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Answer`
-- ----------------------------
DROP TABLE IF EXISTS `Answer`;
CREATE TABLE `Answer` (
  `StudyID` int(20) NOT NULL,
  `Email` varchar(40) NOT NULL DEFAULT '',
  `Choice` varchar(40) DEFAULT NULL,
  `DateSubmitted` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`StudyID`,`Email`),
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`StudyID`) REFERENCES `Study` (`StudyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Question`
-- ----------------------------
DROP TABLE IF EXISTS `Question`;
CREATE TABLE `Question` (
  `QuestionID` int(20) DEFAULT NULL,
  `StudyID` int(20) NOT NULL ,
  `Question` varchar(50) DEFAULT NULL,
  `Option1` varchar(40) DEFAULT NULL,
  `Option2` varchar(40) DEFAULT NULL,
  `Option3` varchar(40) DEFAULT NULL,
  `Option4` varchar(40) DEFAULT NULL,
  `Option5` varchar(40) DEFAULT NULL,
   PRIMARY KEY (`StudyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Reported`
-- ----------------------------
DROP TABLE IF EXISTS `Reported`;
CREATE TABLE `Reported` (
  `Email` varchar(50) NOT NULL DEFAULT '',
  `DateReported` varchar(20) DEFAULT NULL,
  `Question` varchar(50) NOT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `StudyID` int(20) NOT NULL,
  PRIMARY KEY (`StudyID`,`Email`),
  CONSTRAINT `reported_ibfk_1` FOREIGN KEY (`StudyID`) REFERENCES `Study` (`StudyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reported_ibfk_2` FOREIGN KEY (`Email`) REFERENCES `User` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Study`
-- ----------------------------
DROP TABLE IF EXISTS `Study`;
CREATE TABLE `Study` (
  `StudyID` int(20) NOT NULL ,
  `StudyName` varchar(40) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Email` varchar(50) NOT NULL DEFAULT '',
  `DateCreated` varchar(20) DEFAULT NULL,
  `ImageURL` varchar(50) DEFAULT NULL,
  `ReqParticipants` int(15) DEFAULT 0,
  `ActParticipants` int(15) DEFAULT 0,
  `SStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`StudyID`),
  KEY `Email` (`Email`),
  CONSTRAINT `study_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `User` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `User`
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `Email` varchar(50) NOT NULL DEFAULT '',
  `Name` varchar(50) NOT NULL DEFAULT '',
  `Password` varchar(150) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Studies` int(15) DEFAULT 0,
  `Coins` int(15) DEFAULT 0,
  `Participation` int(15) DEFAULT 0,
  `Participants` int(15) DEFAULT 0,  
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `UserHSPassword`
-- ----------------------------
DROP TABLE IF EXISTS `UserHSPassword`;
CREATE TABLE `UserHSPassword` (
  `Email` varchar(50) NOT NULL DEFAULT '',
  `HashAndSaltPassword` varchar(150) DEFAULT NULL,
  `Salt` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `Recommend`
-- ----------------------------
Drop TABLE IF EXISTS `Recommend`;
CREATE TABLE `Recommend`(
    `RecommenderEmail` varchar(50) NOT NULL DEFAULT '',
    `RecommendedEmail` varchar(50) NOT NULL DEFAULT '',
    `RecID` varchar(50) NOT NULL DEFAULT '',
    `Token` varchar(50) NOT NULL DEFAULT '',
    `Status` int(10) NOT NULL DEFAULT 0,
    PRIMARY KEY (`RecommenderEmail`,`RecommendedEmail`),
  CONSTRAINT `recommend_ibfk_1` FOREIGN KEY (`RecommenderEmail`) REFERENCES `User` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- ----------------------------
--  Table structure for `ForgotPassword`
-- ----------------------------
Drop TABLE IF EXISTS `ForgotPassword`;
CREATE TABLE `ForgotPassword`(
    `Email` varchar(50) NOT NULL DEFAULT '',
    `Token` varchar(50) NOT NULL DEFAULT '',
    `IniTime` datetime NOT NULL,
    PRIMARY KEY (`Email`),
  CONSTRAINT `forgotpassword_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `User` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- ----------------------------
--  Table structure for `TempUser`
-- ----------------------------
DROP TABLE IF EXISTS `TempUser`;
CREATE TABLE `TempUser` (
  `Email` varchar(50) NOT NULL DEFAULT '',
  `UserID` varchar(50) NOT NULL DEFAULT '',
  `Name` varchar(50) NOT NULL DEFAULT '',
  `Password` varchar(150) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Token` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`Token`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- ----------------------------
--  Table structure for `ShareStudy`
-- ----------------------------
DROP TABLE IF EXISTS `ShareStudy`;
CREATE TABLE `ShareStudy` (
  `StudyID` int(20) NOT NULL,
  `Status` int(10) NOT NULL,
  PRIMARY KEY (`StudyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
