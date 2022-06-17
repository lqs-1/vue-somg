/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : auth

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 17/06/2022 22:35:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'select');
INSERT INTO `permission` VALUES (3, 'delete');
INSERT INTO `permission` VALUES (4, 'add');
INSERT INTO `permission` VALUES (6, 'alter');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'common');
INSERT INTO `role` VALUES (2, 'supermanager');
INSERT INTO `role` VALUES (3, 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'lqs', '$2a$10$I7IIOyZ.txcGPsgvz3fCJ.RygZC3CiLl9/oD0HBQVzJ2W6k8euNCK', '男');
INSERT INTO `user` VALUES (3, 'admin', '$2a$10$FEHVH3Q5Blr9U1vgiF33peeGUqqK10nrhyjBMwUi0OFnu0dWnBPt.', '女');
INSERT INTO `user` VALUES (4, 'l1', '$2a$10$6ftyvlYdKi/psL7/OVx3PuvSQ7lYg02gdygAZAGCOyVxVq5hAPOUC', '男');
INSERT INTO `user` VALUES (5, 'l2', '$2a$10$XOVMAjhBR7qhYxKlERLUxOv9WvQRGQzR/PNkKhbANHof3G87NdoGO', '女');
INSERT INTO `user` VALUES (6, 'l3', '$2a$10$.4EVNevGJ5tLBqiXi5AMK.ElgbXyaH9II23PFbw82jHLt/WnwMAoO', '男');
INSERT INTO `user` VALUES (7, 'l4', '$2a$10$5eXFP6oClKt4/YO0td6ThOb9N3GwY0ZYj8.LNLr6mPIB/VxAae2Lm', '女');
INSERT INTO `user` VALUES (8, 'l5', '$2a$10$IoVrTfu2lWIEPXVqgcneIezelnAib9/iaVYdkVtPsWhoJjhwMkpL6', '女');
INSERT INTO `user` VALUES (9, 'l6', '$2a$10$HYnZoHv5F9dAlkNfVqB4ROQoGGfeu51cABHIz9atceS2S5NpqA.kK', '男');
INSERT INTO `user` VALUES (10, 'l7', '$2a$10$vg0FnVfhlrhfEN7jsuyKMe3rdcgK3GCEZ4JVJ6E6FdMy3N5QNmA6S', '女');
INSERT INTO `user` VALUES (11, 'l8', '$2a$10$HNzBQm.Xxoc8Fb5TWpqfv.c8wxFV.lUbOFk36eUFupPxLRA/aRqa6', '女');
INSERT INTO `user` VALUES (12, 'l9', '$2a$10$kwmtKTd4YWjNuCqu/TeeEuDjlzsbsaYUuQMOWSiT3ol41wyloZ9ey', '男');

-- ----------------------------
-- Table structure for user_permission
-- ----------------------------
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `permissionId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_permission
-- ----------------------------
INSERT INTO `user_permission` VALUES (2, 12, 1);
INSERT INTO `user_permission` VALUES (3, 12, 2);
INSERT INTO `user_permission` VALUES (4, 1, 1);
INSERT INTO `user_permission` VALUES (5, 1, 3);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `roleId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (4, 3, 1);
INSERT INTO `user_role` VALUES (5, 4, 1);
INSERT INTO `user_role` VALUES (6, 5, 1);
INSERT INTO `user_role` VALUES (7, 6, 1);
INSERT INTO `user_role` VALUES (8, 7, 1);
INSERT INTO `user_role` VALUES (9, 8, 1);
INSERT INTO `user_role` VALUES (10, 9, 1);
INSERT INTO `user_role` VALUES (11, 10, 1);
INSERT INTO `user_role` VALUES (12, 11, 1);
INSERT INTO `user_role` VALUES (14, 12, 1);
INSERT INTO `user_role` VALUES (15, 1, 1);
INSERT INTO `user_role` VALUES (16, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
