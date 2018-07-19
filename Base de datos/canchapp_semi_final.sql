/*
Navicat MySQL Data Transfer

Source Server         : database
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : canchapp

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-06-15 14:23:06
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
INSERT INTO `administrador` VALUES ('2', 'Patricio', 'Quezada', '123');

-- ----------------------------
-- Table structure for cancha
-- ----------------------------
DROP TABLE IF EXISTS `cancha`;
CREATE TABLE `cancha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `ubicacion` varchar(10) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `fkadministrador` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAdmin` (`fkadministrador`),
  KEY `nombre` (`nombre`),
  CONSTRAINT `FKAdmin` FOREIGN KEY (`fkadministrador`) REFERENCES `administrador` (`rut`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cancha
-- ----------------------------
INSERT INTO `cancha` VALUES ('1', 'cancha 1', 'a', '121', 'disponible', '20000', '1');
INSERT INTO `cancha` VALUES ('2', 'cancha 2', 'b', 'wq', 'ocupado', '25000', '2');
INSERT INTO `cancha` VALUES ('3', 'cristian', 'farias', 'jasjas', 'disponible', '30000', '1');

-- ----------------------------
-- Table structure for cancha_tiene_horario
-- ----------------------------
DROP TABLE IF EXISTS `cancha_tiene_horario`;
CREATE TABLE `cancha_tiene_horario` (
  `id` int(11) NOT NULL,
  `fk_cancha` varchar(11) DEFAULT NULL,
  `fk_hora_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cancha_tiene_horario` (`fk_cancha`),
  KEY `fk_hora_id_horario` (`fk_hora_id`),
  CONSTRAINT `fk_cancha_tiene_horario` FOREIGN KEY (`fk_cancha`) REFERENCES `cancha` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hora_id_horario` FOREIGN KEY (`fk_hora_id`) REFERENCES `horario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cancha_tiene_horario
-- ----------------------------
INSERT INTO `cancha_tiene_horario` VALUES ('1', 'cancha 1', '1');
INSERT INTO `cancha_tiene_horario` VALUES ('2', 'cancha 1', '2');
INSERT INTO `cancha_tiene_horario` VALUES ('3', 'cancha 2', '3');
INSERT INTO `cancha_tiene_horario` VALUES ('4', 'cancha 2', '4');

-- ----------------------------
-- Table structure for deporte
-- ----------------------------
DROP TABLE IF EXISTS `deporte`;
CREATE TABLE `deporte` (
  `id` int(11) NOT NULL,
  `nombre_deporte` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of deporte
-- ----------------------------
INSERT INTO `deporte` VALUES ('1', 'futbol');

-- ----------------------------
-- Table structure for equipo
-- ----------------------------
DROP TABLE IF EXISTS `equipo`;
CREATE TABLE `equipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `jefe_equipo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `fk_hora_evento` time DEFAULT NULL,
  `fk_equipo1` int(11) DEFAULT NULL,
  `fk_equipo2` int(11) DEFAULT NULL,
  `fk_cancha` int(11) DEFAULT NULL,
  `fk_usuario` varchar(50) DEFAULT NULL,
  `fk_deporte` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipo` (`fk_equipo1`) USING BTREE,
  KEY `fk_cancha` (`fk_cancha`) USING BTREE,
  KEY `fk_horario_evento` (`fk_hora_evento`),
  KEY `evento_ibfk_3` (`fk_equipo2`),
  KEY `fk_usuarioevento` (`fk_usuario`),
  KEY `fk_eventocondeporte` (`fk_deporte`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`fk_cancha`) REFERENCES `cancha` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`fk_equipo1`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `evento_ibfk_3` FOREIGN KEY (`fk_equipo2`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_eventocondeporte` FOREIGN KEY (`fk_deporte`) REFERENCES `deporte` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarioevento` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of evento
-- ----------------------------
INSERT INTO `evento` VALUES ('1', 'evento 1', '2018-06-15', '08:00:00', '1', '2', '1', 'cristian_fariass@hotmail.com', '1');
INSERT INTO `evento` VALUES ('2', 'evento 2', '2018-06-15', '09:00:00', '2', '1', '2', 'a@a.com', '1');
INSERT INTO `evento` VALUES ('3', 'evento 3', '2018-06-15', '10:00:00', '2', '1', '1', 'cristian_fariass@hotmail.com', '1');

-- ----------------------------
-- Table structure for horario
-- ----------------------------
DROP TABLE IF EXISTS `horario`;
CREATE TABLE `horario` (
  `id` int(11) NOT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_termino` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hora` (`hora_inicio`)
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
-- Table structure for registro_de_reservas
-- ----------------------------
DROP TABLE IF EXISTS `registro_de_reservas`;
CREATE TABLE `registro_de_reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL,
  `fk_canchatienehorario` int(11) DEFAULT NULL,
  `fk_evento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_registroconhorario` (`fk_canchatienehorario`),
  KEY `fk_eventoconregistro` (`fk_evento`),
  CONSTRAINT `fk_eventoconregistro` FOREIGN KEY (`fk_evento`) REFERENCES `evento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_registroconhorario` FOREIGN KEY (`fk_canchatienehorario`) REFERENCES `cancha_tiene_horario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of registro_de_reservas
-- ----------------------------
INSERT INTO `registro_de_reservas` VALUES ('1', '2018-06-15', 'Reservado', '1', '1');
INSERT INTO `registro_de_reservas` VALUES ('2', '2018-06-15', 'Disponible', '2', '2');
INSERT INTO `registro_de_reservas` VALUES ('3', '2018-06-15', 'Ocupado', '3', '3');

-- ----------------------------
-- Table structure for servicio
-- ----------------------------
DROP TABLE IF EXISTS `servicio`;
CREATE TABLE `servicio` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of servicio
-- ----------------------------
INSERT INTO `servicio` VALUES ('1', 'arbitro');
INSERT INTO `servicio` VALUES ('2', 'pelotero');

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `email` varchar(50) NOT NULL,
  `nickname` varchar(10) DEFAULT NULL,
  `nombre` varchar(10) DEFAULT NULL,
  `apellido` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `puntuacion` decimal(3,2) NOT NULL DEFAULT '5.00',
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('a@a.com', 'a', 'a', 'a', '123456', '2018-06-15', '5.00', '1', '2018-06-15 14:17:30');
INSERT INTO `usuario` VALUES ('cristian_fariass@hotmail.com', 'wuatu', 'cristian', 'farias', '123456', '1992-12-01', '5.00', '1', '2018-06-15 14:16:51');

-- ----------------------------
-- Table structure for usuarioservicio
-- ----------------------------
DROP TABLE IF EXISTS `usuarioservicio`;
CREATE TABLE `usuarioservicio` (
  `ref_servicio` int(10) unsigned NOT NULL,
  `ref_cuenta` varchar(20) NOT NULL,
  `valor` int(11) NOT NULL DEFAULT '0',
  `estado` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ref_servicio`,`ref_cuenta`),
  KEY `ref_cuenta` (`ref_cuenta`),
  CONSTRAINT `usuarioservicio_ibfk_1` FOREIGN KEY (`ref_cuenta`) REFERENCES `usuario` (`email`),
  CONSTRAINT `usuarioservicio_ibfk_2` FOREIGN KEY (`ref_servicio`) REFERENCES `servicio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuarioservicio
-- ----------------------------
INSERT INTO `usuarioservicio` VALUES ('1', 'a@a.com', '1000', '1');

-- ----------------------------
-- Table structure for usuario_paga_evento
-- ----------------------------
DROP TABLE IF EXISTS `usuario_paga_evento`;
CREATE TABLE `usuario_paga_evento` (
  `id` int(11) NOT NULL,
  `cuota` int(11) DEFAULT NULL,
  `estapagado` smallint(6) DEFAULT NULL,
  `fkusuario` varchar(50) DEFAULT NULL,
  `fkevento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuarioconevento` (`fkusuario`),
  KEY `fk_eventoconpagaevento` (`fkevento`),
  CONSTRAINT `fk_eventoconpagaevento` FOREIGN KEY (`fkevento`) REFERENCES `evento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarioconevento` FOREIGN KEY (`fkusuario`) REFERENCES `usuario` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuario_paga_evento
-- ----------------------------
INSERT INTO `usuario_paga_evento` VALUES ('1', '2000', '1', 'a@a.com', '1');

-- ----------------------------
-- Table structure for usuario_tiene_equipo
-- ----------------------------
DROP TABLE IF EXISTS `usuario_tiene_equipo`;
CREATE TABLE `usuario_tiene_equipo` (
  `fk_equipo` int(11) NOT NULL,
  `fk_usuario` varchar(50) NOT NULL,
  PRIMARY KEY (`fk_equipo`,`fk_usuario`),
  KEY `fk_usarioequipo` (`fk_usuario`),
  CONSTRAINT `fk_equipoequipo` FOREIGN KEY (`fk_equipo`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usarioequipo` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuario_tiene_equipo
-- ----------------------------
INSERT INTO `usuario_tiene_equipo` VALUES ('1', 'a@a.com');
INSERT INTO `usuario_tiene_equipo` VALUES ('1', 'cristian_fariass@hotmail.com');
INSERT INTO `usuario_tiene_equipo` VALUES ('2', 'a@a.com');
INSERT INTO `usuario_tiene_equipo` VALUES ('2', 'cristian_fariass@hotmail.com');
