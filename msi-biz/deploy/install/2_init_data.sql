SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

BEGIN;
INSERT INTO `sys_user` VALUES (1, 'zhangsan', '张三', now(), NULL);
INSERT INTO `sys_user` VALUES (2, 'lisi', '李四', now(), NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
