/*
Navicat MySQL Data Transfer

Source Server         : CanchApp
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : canchapp

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-06-13 20:16:53
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
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `ubicacion` varchar(50) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `fkadministrador` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAdmin` (`fkadministrador`),
  KEY `nombre` (`nombre`),
  CONSTRAINT `FKAdmin` FOREIGN KEY (`fkadministrador`) REFERENCES `administrador` (`rut`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cancha
-- ----------------------------
INSERT INTO `cancha` VALUES ('1', 'cancha 1', '1', '2', 'ocupado', '1');
INSERT INTO `cancha` VALUES ('2', 'cancha 2', 'camilo henriquez, curico', 'algo', 'disponible', '1');
INSERT INTO `cancha` VALUES ('3', 'cancha1', 'los niches', 'algo', 'disponible', '2');
INSERT INTO `cancha` VALUES ('4', 'cancha 3', 'bbb', 'b', 'disponible', '1');
INSERT INTO `cancha` VALUES ('5', 'cancha 4', 'd', 'dd', 'disponible', '1');
INSERT INTO `cancha` VALUES ('7', 'cancha 5', 'f', 'f', 'disponible', '1');
INSERT INTO `cancha` VALUES ('8', 'cancha x', 'f', 'f', 'disponible', '1');
INSERT INTO `cancha` VALUES ('9', 'cancha prueba', 'g', 'g', 'disponible', '1');
INSERT INTO `cancha` VALUES ('17', 'cancha 2', 'f', 'f', 'disponible', '2');
INSERT INTO `cancha` VALUES ('18', 'cancha 3', '3', '3', 'disponible', '2');

-- ----------------------------
-- Table structure for cancha_tiene_horario
-- ----------------------------
DROP TABLE IF EXISTS `cancha_tiene_horario`;
CREATE TABLE `cancha_tiene_horario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_cancha` varchar(11) DEFAULT NULL,
  `fk_hora_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Cancha` (`fk_cancha`),
  KEY `FK_Hora` (`fk_hora_id`),
  CONSTRAINT `FK_Cancha` FOREIGN KEY (`fk_cancha`) REFERENCES `cancha` (`nombre`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_Hora` FOREIGN KEY (`fk_hora_id`) REFERENCES `horario` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cancha_tiene_horario
-- ----------------------------
INSERT INTO `cancha_tiene_horario` VALUES ('1', 'cancha 1', '1');
INSERT INTO `cancha_tiene_horario` VALUES ('2', 'cancha 1', '2');
INSERT INTO `cancha_tiene_horario` VALUES ('3', 'cancha 1', '3');
INSERT INTO `cancha_tiene_horario` VALUES ('4', 'cancha 1', '4');
INSERT INTO `cancha_tiene_horario` VALUES ('5', 'cancha 2', '1');
INSERT INTO `cancha_tiene_horario` VALUES ('6', 'cancha 2', '2');
INSERT INTO `cancha_tiene_horario` VALUES ('7', 'cancha 2', '3');
INSERT INTO `cancha_tiene_horario` VALUES ('8', 'cancha 2', '4');

-- ----------------------------
-- Table structure for cantidadcanchas
-- ----------------------------
DROP TABLE IF EXISTS `cantidadcanchas`;
CREATE TABLE `cantidadcanchas` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `cantidadcancha` int(20) DEFAULT NULL,
  `fkAdministrador` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_admin` (`fkAdministrador`),
  CONSTRAINT `fk_admin` FOREIGN KEY (`fkAdministrador`) REFERENCES `administrador` (`rut`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cantidadcanchas
-- ----------------------------
INSERT INTO `cantidadcanchas` VALUES ('1', '7', '1');
INSERT INTO `cantidadcanchas` VALUES ('2', '3', '2');

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
-- Table structure for horario
-- ----------------------------
DROP TABLE IF EXISTS `horario`;
CREATE TABLE `horario` (
  `id` int(11) NOT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_termino` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hora` (`hora_inicio`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of horario
-- ----------------------------
INSERT INTO `horario` VALUES ('1', '08:00:00', '09:00:00');
INSERT INTO `horario` VALUES ('2', '09:00:00', '10:00:00');
INSERT INTO `horario` VALUES ('3', '10:00:00', '11:00:00');
INSERT INTO `horario` VALUES ('4', '11:00:00', '12:00:00');
INSERT INTO `horario` VALUES ('5', '12:00:00', '13:00:00');
INSERT INTO `horario` VALUES ('6', '13:00:00', '14:00:00');
INSERT INTO `horario` VALUES ('7', '14:00:00', '15:00:00');
INSERT INTO `horario` VALUES ('8', '15:00:00', '16:00:00');
INSERT INTO `horario` VALUES ('9', '16:00:00', '17:00:00');
INSERT INTO `horario` VALUES ('10', '17:00:00', '18:00:00');
INSERT INTO `horario` VALUES ('11', '18:00:00', '19:00:00');
INSERT INTO `horario` VALUES ('12', '19:00:00', '20:00:00');
INSERT INTO `horario` VALUES ('13', '20:00:00', '21:00:00');
INSERT INTO `horario` VALUES ('14', '21:00:00', '22:00:00');
INSERT INTO `horario` VALUES ('15', '22:00:00', '23:00:00');
INSERT INTO `horario` VALUES ('16', '23:00:00', '00:00:00');
INSERT INTO `horario` VALUES ('17', '00:00:00', '01:00:00');

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
-- Table structure for registro_de_reservas
-- ----------------------------
DROP TABLE IF EXISTS `registro_de_reservas`;
CREATE TABLE `registro_de_reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `fk_canchatienehorario` int(11) DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_canchatienehorario` (`fk_canchatienehorario`) USING BTREE,
  CONSTRAINT `registro_de_reservas_ibfk_1` FOREIGN KEY (`fk_canchatienehorario`) REFERENCES `cancha_tiene_horario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of registro_de_reservas
-- ----------------------------
INSERT INTO `registro_de_reservas` VALUES ('5', '2018-06-08', '1', 'ocupado');
INSERT INTO `registro_de_reservas` VALUES ('6', '2018-06-08', '2', 'reservado');
INSERT INTO `registro_de_reservas` VALUES ('7', '2018-06-08', '3', 'disponible');
INSERT INTO `registro_de_reservas` VALUES ('8', '2018-06-08', '4', 'reservado');
INSERT INTO `registro_de_reservas` VALUES ('9', '2018-06-07', '5', 'disponible');
INSERT INTO `registro_de_reservas` VALUES ('10', '2018-06-07', '6', 'ocupado');

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
