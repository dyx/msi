SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
                            `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

SET FOREIGN_KEY_CHECKS = 1;
