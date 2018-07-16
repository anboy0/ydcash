/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : vhr

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-18 16:05:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_department
-- ----------------------------
DROP TABLE IF EXISTS `base_department`;
CREATE TABLE `base_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `parentId` int(11) DEFAULT NULL,
  `depPath` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `isParent` tinyint(1) DEFAULT '0',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_department
-- ----------------------------
INSERT INTO `base_department` VALUES ('1', '股东会', '-1', '.1', '1', '1', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('4', '董事长', '1', '.1.4', '1', '1', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('5', '总经理', '4', '.1.4.5', '1', '1', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('8', '财务部', '5', '.1.4.5.8', '1', '0', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('78', '市场部', '5', '.1.4.5.78', '1', '1', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('81', '华北市场部', '78', '.1.4.5.78.81', '1', '1', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('82', '华南市场部', '78', '.1.4.5.78.82', '1', '0', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('85', '石家庄市场部', '81', '.1.4.5.78.81.85', '1', '0', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('86', '西北市场部', '78', '.1.4.5.78.86', '1', '1', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('87', '西安市场', '86', '.1.4.5.78.86.87', '1', '1', null, null, null, null, null);
INSERT INTO `base_department` VALUES ('89', '莲湖区市场', '87', '.1.4.5.78.86.87.89', '1', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `base_menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `base_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_menu
-- ----------------------------
INSERT INTO `base_menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('2', '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', null, '1', '1', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('3', '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', null, '1', '1', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('4', '/', '/home', 'Home', '薪资管理', 'fa fa-money', null, '1', '1', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('5', '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', null, '1', '1', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('6', '/', '/home', 'Home', '系统管理', 'fa fa-windows', null, '1', '1', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('7', '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', null, null, '1', '2', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('8', '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', null, null, '1', '2', '0', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('9', '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', null, null, '1', '3', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('10', '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', null, null, '1', '3', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('11', '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', null, null, '1', '3', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('12', '/personnel/salary/**', '/per/salary', 'PerSalary', '员工调薪', null, null, '1', '3', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('13', '/personnel/remove/**', '/per/mv', 'PerMv', '员工调动', null, null, '1', '3', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('14', '/salary/sob/**', '/sal/sob', 'SalSob', '工资账套管理', null, null, '1', '4', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('15', '/salary/sobcfg/**', '/sal/sobcfg', 'SalSobCfg', '员工账套设置', null, null, '1', '4', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('16', '/salary/table/**', '/sal/table', 'SalTable', '工资表管理', null, null, '1', '4', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('17', '/salary/month/**', '/sal/month', 'SalMonth', '月末处理', null, null, '1', '4', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('18', '/salary/search/**', '/sal/search', 'SalSearch', '工资表查询', null, null, '1', '4', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('19', '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', null, null, '1', '5', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('20', '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', null, null, '1', '5', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('21', '/statistics/personnel/**', '/sta/pers', 'StaPers', '人事信息统计', null, null, '1', '5', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('22', '/statistics/recored/**', '/sta/record', 'StaRecord', '人事记录统计', null, null, '1', '5', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('23', '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', null, null, '1', '6', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('24', '/system/cfg/**', '/sys/cfg', 'SysCfg', '系统管理', null, null, '1', '6', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('25', '/system/log/**', '/sys/log', 'SysLog', '操作日志管理', null, null, '1', '6', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('26', '/system/hr/**', '/sys/hr', 'SysHr', '操作员管理', null, null, '1', '6', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('27', '/system/data/**', '/sys/data', 'SysData', '备份恢复数据库', null, null, '1', '6', '1', null, null, null, null, null);
INSERT INTO `base_menu` VALUES ('28', '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', null, null, '1', '6', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for base_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `base_menu_role`;
CREATE TABLE `base_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`),
  CONSTRAINT `base_menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `base_menu` (`id`),
  CONSTRAINT `base_menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `base_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_menu_role
-- ----------------------------
INSERT INTO `base_menu_role` VALUES ('161', '7', '3');
INSERT INTO `base_menu_role` VALUES ('162', '7', '6');
INSERT INTO `base_menu_role` VALUES ('163', '9', '6');
INSERT INTO `base_menu_role` VALUES ('164', '10', '6');
INSERT INTO `base_menu_role` VALUES ('165', '11', '6');
INSERT INTO `base_menu_role` VALUES ('166', '12', '6');
INSERT INTO `base_menu_role` VALUES ('167', '13', '6');
INSERT INTO `base_menu_role` VALUES ('168', '14', '6');
INSERT INTO `base_menu_role` VALUES ('169', '15', '6');
INSERT INTO `base_menu_role` VALUES ('170', '16', '6');
INSERT INTO `base_menu_role` VALUES ('171', '17', '6');
INSERT INTO `base_menu_role` VALUES ('172', '18', '6');
INSERT INTO `base_menu_role` VALUES ('173', '19', '6');
INSERT INTO `base_menu_role` VALUES ('174', '20', '6');
INSERT INTO `base_menu_role` VALUES ('175', '21', '6');
INSERT INTO `base_menu_role` VALUES ('176', '22', '6');
INSERT INTO `base_menu_role` VALUES ('177', '23', '6');
INSERT INTO `base_menu_role` VALUES ('178', '25', '6');
INSERT INTO `base_menu_role` VALUES ('179', '26', '6');
INSERT INTO `base_menu_role` VALUES ('180', '27', '6');
INSERT INTO `base_menu_role` VALUES ('181', '28', '6');
INSERT INTO `base_menu_role` VALUES ('182', '24', '6');
INSERT INTO `base_menu_role` VALUES ('247', '7', '4');
INSERT INTO `base_menu_role` VALUES ('248', '8', '4');
INSERT INTO `base_menu_role` VALUES ('249', '11', '4');
INSERT INTO `base_menu_role` VALUES ('250', '7', '2');
INSERT INTO `base_menu_role` VALUES ('251', '8', '2');
INSERT INTO `base_menu_role` VALUES ('252', '9', '2');
INSERT INTO `base_menu_role` VALUES ('253', '10', '2');
INSERT INTO `base_menu_role` VALUES ('254', '12', '2');
INSERT INTO `base_menu_role` VALUES ('255', '13', '2');
INSERT INTO `base_menu_role` VALUES ('256', '7', '1');
INSERT INTO `base_menu_role` VALUES ('257', '8', '1');
INSERT INTO `base_menu_role` VALUES ('258', '9', '1');
INSERT INTO `base_menu_role` VALUES ('259', '10', '1');
INSERT INTO `base_menu_role` VALUES ('260', '11', '1');
INSERT INTO `base_menu_role` VALUES ('261', '12', '1');
INSERT INTO `base_menu_role` VALUES ('262', '13', '1');
INSERT INTO `base_menu_role` VALUES ('263', '14', '1');
INSERT INTO `base_menu_role` VALUES ('264', '15', '1');
INSERT INTO `base_menu_role` VALUES ('265', '16', '1');
INSERT INTO `base_menu_role` VALUES ('266', '17', '1');
INSERT INTO `base_menu_role` VALUES ('267', '18', '1');
INSERT INTO `base_menu_role` VALUES ('268', '19', '1');
INSERT INTO `base_menu_role` VALUES ('269', '20', '1');
INSERT INTO `base_menu_role` VALUES ('270', '21', '1');
INSERT INTO `base_menu_role` VALUES ('271', '22', '1');
INSERT INTO `base_menu_role` VALUES ('272', '23', '1');
INSERT INTO `base_menu_role` VALUES ('273', '24', '1');
INSERT INTO `base_menu_role` VALUES ('274', '25', '1');
INSERT INTO `base_menu_role` VALUES ('275', '26', '1');
INSERT INTO `base_menu_role` VALUES ('276', '27', '1');
INSERT INTO `base_menu_role` VALUES ('277', '28', '1');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES ('1', 'ROLE_manager', '部门经理', null, null, null, null, null);
INSERT INTO `base_role` VALUES ('2', 'ROLE_personnel', '人事专员', null, null, null, null, null);
INSERT INTO `base_role` VALUES ('3', 'ROLE_recruiter', '招聘主管', null, null, null, null, null);
INSERT INTO `base_role` VALUES ('4', 'ROLE_train', '培训主管', null, null, null, null, null);
INSERT INTO `base_role` VALUES ('5', 'ROLE_performance', '薪酬绩效主管', null, null, null, null, null);
INSERT INTO `base_role` VALUES ('6', 'ROLE_admin', '系统管理员', null, null, null, null, null);
INSERT INTO `base_role` VALUES ('13', 'ROLE_test2', '测试角色2', null, null, null, null, null);
INSERT INTO `base_role` VALUES ('14', 'ROLE_test1', '测试角色1', null, null, null, null, null);

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'userID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否可用（0不可1可）',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) DEFAULT NULL COMMENT '头像',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_by` int(11) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user
-- ----------------------------
INSERT INTO `base_user` VALUES ('3', '系统管理员', '18568887789', '029-82881234', '深圳南山', '1', 'admin', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', null, null, null, null, null);
INSERT INTO `base_user` VALUES ('5', '李白', '18568123489', '029-82123434', '海口美兰', '1', 'libai', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', null, null, null, null, null);
INSERT INTO `base_user` VALUES ('10', '韩愈', '18568123666', '029-82111555', '广州番禺', '1', 'hanyu', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', null, null, null, null, null);
INSERT INTO `base_user` VALUES ('11', '柳宗元', '18568123377', '029-82111333', '广州天河', '1', 'liuzongyuan', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515233756&di=0856d923a0a37a87fd26604a2c871370&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2014-09-27%2F041716704.jpg', null, null, null, null, null);
INSERT INTO `base_user` VALUES ('12', '曾巩', '18568128888', '029-82111222', '广州越秀', '1', 'zenggong', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', null, null, null, null, null);

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `hr_role_ibfk_1` (`uid`),
  CONSTRAINT `base_user_role_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `base_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `base_user_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `base_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_user_role
-- ----------------------------
INSERT INTO `base_user_role` VALUES ('1', '3', '6');
INSERT INTO `base_user_role` VALUES ('9', '5', '1');
INSERT INTO `base_user_role` VALUES ('10', '5', '4');
INSERT INTO `base_user_role` VALUES ('35', '12', '4');
INSERT INTO `base_user_role` VALUES ('36', '12', '3');
INSERT INTO `base_user_role` VALUES ('37', '12', '2');
INSERT INTO `base_user_role` VALUES ('43', '11', '3');
INSERT INTO `base_user_role` VALUES ('44', '11', '2');
INSERT INTO `base_user_role` VALUES ('45', '11', '4');
INSERT INTO `base_user_role` VALUES ('46', '11', '5');
INSERT INTO `base_user_role` VALUES ('47', '10', '3');

-- ----------------------------
-- Table structure for bus_company
-- ----------------------------
DROP TABLE IF EXISTS `bus_company`;
CREATE TABLE `bus_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_code` varchar(10) NOT NULL COMMENT '公司代码',
  `company_name` varchar(100) NOT NULL COMMENT '公司全称',
  `company_py` varchar(100) DEFAULT NULL COMMENT '拼音',
  `company_bl_no` varchar(50) DEFAULT NULL COMMENT '公司营业执照号',
  `company_bl_code` varchar(200) DEFAULT NULL COMMENT '公司营业执照代码',
  `linkman` varchar(50) DEFAULT NULL COMMENT '联系人',
  `linkman_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `mobilephone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `zip_code` varchar(20) DEFAULT NULL COMMENT '邮编',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否可用（0否1是）',
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `site_id` int(11) DEFAULT NULL COMMENT '网点',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_code` (`company_code`,`company_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_company
-- ----------------------------

-- ----------------------------
-- Table structure for bus_cost
-- ----------------------------
DROP TABLE IF EXISTS `bus_charge_item`;
CREATE TABLE `bus_charge_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `charge_item_code` varchar(10) DEFAULT NULL COMMENT '费用项目编码',
  `charge_item_name` varchar(100) DEFAULT NULL COMMENT '费用项目名称',
  `parent_Id` int(11) DEFAULT NULL COMMENT '父节点',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否可用（0否1是）',
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_cost
-- ----------------------------

-- ----------------------------
-- Table structure for bus_site
-- ----------------------------
DROP TABLE IF EXISTS `bus_site`;
CREATE TABLE `bus_site` (
  `id` int(11) NOT NULL,
  `site_code` varchar(10) DEFAULT NULL COMMENT '网点编号',
  `site_name` varchar(100) DEFAULT NULL COMMENT '网点名称',
  `site_type` int(2) DEFAULT NULL COMMENT '网点类型',
  `site_service_type` int(2) DEFAULT NULL COMMENT '网点服务类型',
  `status` int(1) DEFAULT NULL COMMENT '网点状态',
  `parent` int(11) DEFAULT NULL COMMENT '父节点',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_site
-- ----------------------------

-- ----------------------------
-- Procedure structure for addDep
-- ----------------------------
DROP PROCEDURE IF EXISTS `addDep`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addDep`(in depName varchar(32),in parentId int,in enabled boolean,out result int,out result2 int)
begin
  declare did int;
  declare pDepPath varchar(64);
  insert into department set name=depName,parentId=parentId,enabled=enabled;
  select row_count() into result;
  select last_insert_id() into did;
  set result2=did;
  select depPath into pDepPath from department where id=parentId;
  update department set depPath=concat(pDepPath,'.',did) where id=did;
  update department set isParent=true where id=parentId;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteDep
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteDep`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDep`(in did int,out result int)
begin
  declare ecount int;
  declare pid int;
  declare pcount int;
  select count(*) into ecount from employee where departmentId=did;
  if ecount>0 then set result=-1;
  else 
  select parentId into pid from department where id=did;
  delete from department where id=did and isParent=false;
  select row_count() into result;
  select count(*) into pcount from department where parentId=pid;
  if pcount=0 then update department set isParent=false where id=pid;
  end if;
  end if;
end
;;
DELIMITER ;
