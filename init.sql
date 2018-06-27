-- account initial
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (1, 'admin', '111111', '15013446478', '', unix_timestamp(now()));
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (2, 'scrat', '111111', '18018790114', '', unix_timestamp(now()));
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (3, '123123', '111111', '13332941385', '', unix_timestamp(now()));

-- user initial
-- 超级管理员
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (1, '4', 'administrator', '0', '18', 1, unix_timestamp(now()));
-- 管理员
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (2, '2', 'scrat', '2', '18', 2, unix_timestamp(now()));
-- 房东
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (3, '1', 'ryan', '1', '18', 3, unix_timestamp(now()));

-- dictionary initial
INSERT INTO `dictionary` (dic_code, name, create_ts) VALUES ('0001', '配套设施', unix_timestamp(now()));
INSERT INTO `dictionary` (dic_code, name, create_ts) VALUES ('0002', '装修情况', unix_timestamp(now()));
INSERT INTO `dictionary` (dic_code, name, create_ts) VALUES ('0003', '房间朝向', unix_timestamp(now()));
INSERT INTO `dictionary` (dic_code, name, create_ts) VALUES ('0004', '额外收费项', unix_timestamp(now()));
INSERT INTO `dictionary` (dic_code, name, create_ts) VALUES ('0005', '押金项', unix_timestamp(now()));

-- dictionary_iterm initial
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1001', '0001', '床', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1002', '0001', 'WIFI', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1003', '0001', '衣柜', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1004', '0001', '热水器', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1005', '0001', '洗衣机', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1006', '0001', '书桌', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1007', '0001', '椅子', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1008', '0001', '空调', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1009', '0001', '冰箱', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1010', '0001', '微波炉', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1011', '0001', '电视机', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1012', '0001', '沙发', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1013', '0001', '燃气灶', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1014', '0001', '餐桌', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1015', '0001', '橱柜', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1016', '0001', '油烟机', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1017', '0001', '暖气', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1018', '0001', '电磁炉', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1019', '0001', '家具', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('1020', '0001', '燃气', unix_timestamp(now()));

INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('2001', '0002', '毛胚', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('2002', '0002', '简装', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('2003', '0002', '精装', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('2004', '0002', '豪装', unix_timestamp(now()));

INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3001', '0003', '东', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3002', '0003', '南', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3003', '0003', '西', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3004', '0003', '北', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3005', '0003', '东南', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3006', '0003', '东北', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3007', '0003', '西南', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, create_ts) VALUES ('3008', '0003', '西北', unix_timestamp(now()));

INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4001', '0004', '水费', '吨', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4002', '0004', '电费', '度', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4003', '0004', '三相电费', '度', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4004', '0004', '燃气费', '方',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4005', '0004', '供暖费', '方',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4006', '0004', '停车费', '月',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4007', '0004', '宽带费', '月',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4008', '0004', '电视费', '月',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4009', '0004', '空调费', '月',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4010', '0004', '卫生费', '月',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('4011', '0004', '物业费', '月',  unix_timestamp(now()));


INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('5001', '0005', '租金', '月',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('5002', '0005', '房间钥匙', '把',  unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_iterm_code, dic_code, value, unit, create_ts) VALUES ('5003', '0005', '门禁钥匙', '个',  unix_timestamp(now()));