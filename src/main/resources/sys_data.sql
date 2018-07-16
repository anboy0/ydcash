delete from base_dict;
INSERT INTO `base_dict` VALUES ('1', '登陆日志', 'log_type', '0', '2018-01-18 15:16:14', null, null, null, '1', null, '1');
INSERT INTO `base_dict` VALUES ('2', '操作日志', 'log_type', '0', null, null, null, null, '1', null, '2');
INSERT INTO `base_dict` VALUES ('3', '异常日志', 'log_type', '0', '2018-01-18 15:18:00', null, '2018-01-18 15:18:00', null, '1', null, '3');
INSERT INTO `base_dict` VALUES ('4', '现金', 'pay_type', '0', '2018-01-18 15:19:46', null, '2018-01-18 15:19:46', null, '1', null, '1');
INSERT INTO `base_dict` VALUES ('5', '转账', 'pay_type', '0', null, null, null, null, '1', null, '2');
INSERT INTO `base_dict` VALUES ('6', '支票', 'pay_type', '0', null, null, null, null, '1', null, '3');
INSERT INTO `base_dict` VALUES ('7', '网点', 'company_type', null, '2018-01-18 15:22:49', null, '2018-01-18 15:22:49', null, '1', null, '1');
INSERT INTO `base_dict` VALUES ('8', '公司', 'company_type', null, '2018-01-18 15:22:49', null, '2018-01-18 15:22:49', null, '1', null, '2');
INSERT INTO `base_dict` VALUES ('9', '个人', 'company_type', null, '2018-01-18 15:22:50', null, '2018-01-18 15:22:50', null, '1', null, '3');
INSERT INTO `base_dict` VALUES ('10', '生效', 'receipt_status', null, '2018-01-18 15:23:48', null, '2018-01-18 15:23:48', null, '1', null, '1');
INSERT INTO `base_dict` VALUES ('11', '已冲销', 'receipt_status', null, '2018-01-18 15:23:50', null, '2018-01-18 15:23:50', null, '1', null, '0');

delete from bus_charge_item;
INSERT INTO `bus_charge_item` VALUES ('1', '-1', '根节点', '-1', null, '1', null, null, null, null);

delete from base_sequence;
INSERT INTO `base_sequence`(name,current_value,increment) VALUES ('seq_serial_no', '1', '1');

commit;