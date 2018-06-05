-- account initial
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (1, 'admin', '111111', '15013446478', '', unix_timestamp(now()));
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (2, 'scrat', '111111', '18018790114', '', unix_timestamp(now()));
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (3, '123123', '111111', '13332941385', '', unix_timestamp(now()));

-- user initial
--超级管理员
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (1, '4', 'administrator', '0', '18', 1, unix_timestamp(now()));
--管理员
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (2, '2', 'scrat', '2', '18', 2, unix_timestamp(now()));
--房东
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (3, '1', 'ryan', '1', '18', 3, unix_timestamp(now()));

-- dictionary initial
INSERT INTO `dictionary` (dic_id, name, code, create_ts) VALUES (1, '房源配套设施', '001', unix_timestamp(now()));
INSERT INTO `dictionary` (dic_id, name, code, create_ts) VALUES (2, '装修情况', '002', unix_timestamp(now()));
INSERT INTO `dictionary` (dic_id, name, code, create_ts) VALUES (3, '房间朝向', '003', unix_timestamp(now()));

-- dictionary_iterm initial
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '床', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, 'WIFI', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '衣柜', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '热水器', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '洗衣机', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '书桌', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '椅子', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '空调', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '冰箱', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '微波炉', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '电视机', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '沙发', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '燃气灶', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '餐桌', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '橱柜', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '油烟机', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '暖气', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '电磁炉', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '家具', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (1, '燃气', unix_timestamp(now()));

INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (2, '毛胚', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (2, '简装', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (2, '精装', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (2, '豪装', unix_timestamp(now()));

INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '东', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '南', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '西', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '北', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '东南', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '东北', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '西南', unix_timestamp(now()));
INSERT INTO `dictionary_iterm` (dic_id, value, create_ts) VALUES (3, '西北', unix_timestamp(now()));

-- extra initial
INSERT INTO `extra` (name, unit, create_ts) VALUES ('水费', '吨', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('电费', '度', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('三相电费', '度', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('物业费', '月', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('卫生费', '月', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('停车费', '月', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('宽带费', '月', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('电视费', '月', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('空调费', '月', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('供暖费', '月', unix_timestamp(now()));
INSERT INTO `extra` (name, unit, create_ts) VALUES ('燃气费', '方', unix_timestamp(now()));