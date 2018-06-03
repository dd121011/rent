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

-- dictionary initial
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