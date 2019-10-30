/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-05-29 22:45:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '这是一次测试', '2019-05-21 01:50:47');

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option` (
  `queid` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL,
  `subjects` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `opa` varchar(255) DEFAULT NULL,
  `opb` varchar(255) DEFAULT NULL,
  `opc` varchar(255) DEFAULT NULL,
  `opd` varchar(255) DEFAULT NULL,
  `rkey` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`queid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES ('13', '3', '4', '下列说法中错误的是是', '振动是声音产生的原因', '汽车运动速度越大它的动能越大', '力可以改变物体的运动状态', '起重机的钢丝绳吊着重为 2000N 的货物匀速上升时，钢丝绳对货物的拉力大于 2000N', 'B', '2019-05-21 02:20:23');
INSERT INTO `option` VALUES ('14', '5', '4', '用力踢足球，球在地面上滚动的过程中，受到的力是', '重力', '地面的磨擦力', '重力和地面的支持力', '地面的磨擦力，重力和地面的支持力', 'B', '2019-04-24 06:35:57');
INSERT INTO `option` VALUES ('15', '5', '4', '下列几种估测中，比较符合实际情况的是', '地球重10KG', '淋浴时让人感到舒适的水温约是 80℃', '中学物理课本的长度大约是 26cm', '家用节能灯的功率大约是 800W ', 'C', '2019-04-24 06:36:07');
INSERT INTO `option` VALUES ('16', '5', '4', '下列说法正确的是', '水是固态', '放在水平面上的物体受到重力和支持力的作用', '物体受到力的作用，它的运动状态一定要改变', '磨擦总阻碍物体的运动', 'D', '2019-04-24 06:36:13');
INSERT INTO `option` VALUES ('17', '5', '4', '自行车的轮胎有凹凸不平的花纹，是为了', '增加美观', '增大接触面的粗糙程度，增大磨擦', '减小接触面积，减小磨擦', '减小接触面的粗糙程度，减小磨擦', 'B', '2019-04-24 06:36:18');
INSERT INTO `option` VALUES ('18', '5', '4', '我国交通法规规定，坐在小型客车前排的驾驶员和乘客都必须在胸前系上安\r\n全带，这主要是为了减轻下列哪种情况下可能对人造成的伤害', '匀速行驶', '突然启动', '车速太快', '车速太慢', 'B', '2019-04-24 06:37:02');
INSERT INTO `option` VALUES ('23', '5', '4', '神舟三号飞船载有模拟宇航员系统，进行拟人载人载荷试验，其中的形体假人有质量形状与真人基本一致的特点，你认为下列数据中最接近形体假人的质量的是', '10KG', '50kg', '120kg', '150kg', 'B', '2019-04-24 06:36:31');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) DEFAULT NULL,
  `rdesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `role` VALUES ('2', 'tec', '教师');
INSERT INTO `role` VALUES ('3', 'stu', '学生');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `sub_id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`sub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('3', '大学化学', '2019-04-11 16:00:00');
INSERT INTO `subject` VALUES ('4', '大学物理', '2019-04-24 16:00:00');
INSERT INTO `subject` VALUES ('5', '大学英语', '2019-04-18 14:57:20');
INSERT INTO `subject` VALUES ('8', '大学信息技术', '2019-05-08 16:00:00');

-- ----------------------------
-- Table structure for sub_op
-- ----------------------------
DROP TABLE IF EXISTS `sub_op`;
CREATE TABLE `sub_op` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subid` int(11) DEFAULT NULL,
  `opid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_op
-- ----------------------------
INSERT INTO `sub_op` VALUES ('22', '4', '13');
INSERT INTO `sub_op` VALUES ('23', '4', '14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `cardid` varchar(255) DEFAULT NULL,
  `edu` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `statu` int(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', '管理员', '10', '男', '510781', '本科', '2019-04-18 12:40:01', '无', '0', null);
INSERT INTO `user` VALUES ('3', 'stu', '0274510146ff8751acb2a4f7d44f5319', '小红', '10', '女', '321231', '本科', '2019-04-16 16:00:00', '电子信息工程', '0', 'images/a756fe1a-8010-4f0b-88d3-9295ccbd2c2f.jpg');
INSERT INTO `user` VALUES ('4', 'tec', '0450cad82be858e5928ac204980be713', '张老师', '30', '男', '311311', '博士', '2019-04-19 16:00:00', '无', '0', null);
INSERT INTO `user` VALUES ('7', 'ppx', 'a07d31585300a1972ede5671bc130b0f', '大王', '19', '男', '5103333', '本科', '2019-05-20 16:00:00', '园林', '0', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `urole_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `r_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`urole_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('7', '1', '1');
INSERT INTO `user_role` VALUES ('8', '1', '2');
INSERT INTO `user_role` VALUES ('9', '1', '3');
INSERT INTO `user_role` VALUES ('11', '3', '3');
INSERT INTO `user_role` VALUES ('12', '4', '2');
INSERT INTO `user_role` VALUES ('13', '5', '3');
INSERT INTO `user_role` VALUES ('15', '6', '2');
INSERT INTO `user_role` VALUES ('16', '7', '3');

-- ----------------------------
-- Table structure for user_sub
-- ----------------------------
DROP TABLE IF EXISTS `user_sub`;
CREATE TABLE `user_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `subid` int(11) DEFAULT NULL,
  `statu` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_sub
-- ----------------------------
INSERT INTO `user_sub` VALUES ('15', '4', '4', '0');
