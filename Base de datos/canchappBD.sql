/*
Navicat MySQL Data Transfer

Source Server         : CanchApp
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : canchapp

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-05-16 17:43:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrador
-- ----------------------------
DROP TABLE IF EXISTS `administrador`;
CREATE TABLE `administrador` (
  `rut` varchar(10) NOT NULL,
  `nombre` varchar(10) DEFAULT NULL,
  `apellido` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of administrador
-- ----------------------------
INSERT INTO `administrador` VALUES ('1', 'Rodolfo', 'Henzi', '123');
INSERT INTO `administrador` VALUES ('2', 'Patricio', 'Quezada', '456');

-- ----------------------------
-- Table structure for cancha
-- ----------------------------
DROP TABLE IF EXISTS `cancha`;
CREATE TABLE `cancha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `ubicacion` varchar(10) DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL,
  `fk_administrador` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAdmin` (`fk_administrador`),
  CONSTRAINT `FKAdmin` FOREIGN KEY (`fk_administrador`) REFERENCES `administrador` (`rut`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cancha
-- ----------------------------
INSERT INTO `cancha` VALUES ('1', 'cancha 1', 'los niches, av. sur 15', '', null, '1');
INSERT INTO `cancha` VALUES ('2', 'cancha 2', 'av. camilo henriquez, Curicó', null, null, '2');

-- ----------------------------
-- Table structure for cuenta
-- ----------------------------
DROP TABLE IF EXISTS `cuenta`;
CREATE TABLE `cuenta` (
  `Id` int(20) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) DEFAULT NULL,
  `fk_equipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_Equipo2` (`fk_equipo`),
  CONSTRAINT `FK_Equipo2` FOREIGN KEY (`fk_equipo`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cuenta
-- ----------------------------
INSERT INTO `cuenta` VALUES ('1', 'cta_rut', '1');
INSERT INTO `cuenta` VALUES ('2', 'cta_corriente', '2');

-- ----------------------------
-- Table structure for equipo
-- ----------------------------
DROP TABLE IF EXISTS `equipo`;
CREATE TABLE `equipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `jefe_equipo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `jefe_equipo` (`jefe_equipo`) USING BTREE,
  CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`jefe_equipo`) REFERENCES `usuario` (`nick`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of equipo
-- ----------------------------
INSERT INTO `equipo` VALUES ('1', 'Los Masisis', 'fito');
INSERT INTO `equipo` VALUES ('2', 'Los Matatanes', 'pato');

-- ----------------------------
-- Table structure for evento
-- ----------------------------
DROP TABLE IF EXISTS `evento`;
CREATE TABLE `evento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `fk_equipo` int(11) DEFAULT NULL,
  `fk_cancha` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipo` (`fk_equipo`) USING BTREE,
  KEY `fk_cancha` (`fk_cancha`) USING BTREE,
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`fk_cancha`) REFERENCES `cancha` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`fk_equipo`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of evento
-- ----------------------------
INSERT INTO `evento` VALUES ('1', 'evento 1', '2018-05-16', '17:41:16', '1', '1');
INSERT INTO `evento` VALUES ('2', 'evento 2', '2018-05-16', '17:41:34', '2', '2');

-- ----------------------------
-- Table structure for pago
-- ----------------------------
DROP TABLE IF EXISTS `pago`;
CREATE TABLE `pago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `monto` int(11) DEFAULT NULL,
  `fk_cuenta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Cuenta` (`fk_cuenta`),
  CONSTRAINT `FK_Cuenta` FOREIGN KEY (`fk_cuenta`) REFERENCES `cuenta` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pago
-- ----------------------------
INSERT INTO `pago` VALUES ('1', '2018-05-16', '17:02:30', '10000', '1');
INSERT INTO `pago` VALUES ('2', '2018-05-16', '17:27:16', '10000', '2');

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `nick` varchar(10) NOT NULL,
  `nombre` varchar(10) DEFAULT NULL,
  `apellido` varchar(10) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `fk_equipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`nick`),
  KEY `FK_Equipo` (`fk_equipo`),
  CONSTRAINT `FK_Equipo` FOREIGN KEY (`fk_equipo`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('diego', 'Diego', 'Aldana', '25', 'diego@gmail.com', '2');
INSERT INTO `usuario` VALUES ('fito', 'Rodolfo', 'Henzi', '32', 'rhenzi@gmail.com', '1');
INSERT INTO `usuario` VALUES ('nachitoPop', 'Ignacio', 'Correa', '27', 'nacho@gmail.com', '1');
INSERT INTO `usuario` VALUES ('pato', 'Patricio', 'Quezada', '25', 'pato@gmail.com', '2');
INSERT INTO `usuario` VALUES ('wuatu', 'Cristian', 'Farias', '25', 'wuatu@gmail.com', '1');

-- ----------------------------
-- Table structure for usuario_tiene_equipo
-- ----------------------------
DROP TABLE IF EXISTS `usuario_tiene_equipo`;
CREATE TABLE `usuario_tiene_equipo` (
  `fk_equipo` int(11) NOT NULL,
  `fk_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`fk_equipo`,`fk_usuario`),
  KEY `fk_usuario` (`fk_usuario`) USING BTREE,
  CONSTRAINT `usuario_tiene_equipo_ibfk_1` FOREIGN KEY (`fk_equipo`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuario_tiene_equipo_ibfk_2` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`nick`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuario_tiene_equipo
-- ----------------------------
