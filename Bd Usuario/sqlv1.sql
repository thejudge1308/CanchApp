DROP TABLE IF EXISTS cuenta;
CREATE TABLE cuenta (
  email varchar(20) UNIQUE NOT NULL,
  nickname varchar(10) DEFAULT NULL,
  nombre varchar(10) DEFAULT NULL,
  apellido varchar(10) DEFAULT NULL,
  password varchar(20) DEFAULT NULL,
  fecha_nacimiento date DEFAULT NULL,
  puntuacion DECIMAL(3,2) NOT NULL DEFAULT '5.00',
  estado TINYINT(1) NOT NULL DEFAULT '1',
  creado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`email`)
);

DROP TABLE IF EXISTS servicio;
CREATE TABLE servicio (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  descripcion varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS cuentaservicio;
CREATE TABLE cuentaservicio (
  ref_servicio INT UNSIGNED,
  ref_cuenta varchar(20),
  valor INT NOT NULL DEFAULT '0', 
  estado TINYINT(1) NOT NULL DEFAULT '1',
  foreign key (ref_cuenta) references cuenta (email),
  foreign key (ref_servicio) references servicio (id)
);