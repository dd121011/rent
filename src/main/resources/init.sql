-- account initial
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (1, 'admin', '111111', '15013446478', '', unix_timestamp(now()));
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (2, 'scrat', '111111', '18018790114', '', unix_timestamp(now()));
INSERT INTO `account`(account_id, username, pwd, phone, remark, create_ts) VALUES (3, '123123', '111111', '13332941385', '', unix_timestamp(now()));

-- account user
--超级管理员
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (1, '4', 'administrator', '0', '18', 1, unix_timestamp(now()));
--管理员
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (2, '2', 'scrat', '2', '18', 2, unix_timestamp(now()));
--房东
INSERT INTO `user` (user_id, type, name, sex, age, account_id, create_ts) VALUES (3, '1', 'ryan', '1', '18', 3, unix_timestamp(now()));