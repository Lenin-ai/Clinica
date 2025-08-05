DROP DATABASE IF EXISTS clinica;
CREATE DATABASE clinica;
USE clinica;

CREATE TABLE paciente (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          nombres VARCHAR(50),
                          apellidos VARCHAR(50),
                          fechaNacimiento DATE,
                          dni CHAR(8),
                          direccion VARCHAR(100),
                          telefono VARCHAR(15),
                          email VARCHAR(100),
                          fecha_creacion DATE,
                          usuario_creacion VARCHAR(50),
                          fecha_actualizacion DATE,
                          usuario_actualizacion VARCHAR(50)
);

CREATE TABLE especialidad (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              nombre VARCHAR(50),
                              descripcion VARCHAR(100),
                              fecha_creacion DATE,
                              usuario_creacion VARCHAR(50),
                              fecha_actualizacion DATE,
                              usuario_actualizacion VARCHAR(50)
);

CREATE TABLE rol (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     nombre VARCHAR(50),
                     descripcion VARCHAR(100),
                     fecha_creacion DATE,
                     usuario_creacion VARCHAR(50),
                     fecha_actualizacion DATE,
                     usuario_actualizacion VARCHAR(50)
);

CREATE TABLE medico (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        nombres VARCHAR(50),
                        apellidos VARCHAR(50),
                        cmp VARCHAR(10),
                        idEspecialidad INT,
                        FOREIGN KEY(idEspecialidad) REFERENCES especialidad(id),
                        fecha_creacion DATE,
                        usuario_creacion VARCHAR(50),
                        fecha_actualizacion DATE,
                        usuario_actualizacion VARCHAR(50)
);

CREATE TABLE usuario (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nombreUsuario VARCHAR(50),
                         contrasena VARCHAR(255),
                         idRol INT,
                         FOREIGN KEY(idRol) REFERENCES rol(id),
                         fecha_creacion DATE,
                         usuario_creacion VARCHAR(50),
                         fecha_actualizacion DATE,
                         usuario_actualizacion VARCHAR(50)
);

CREATE TABLE horario (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         dia VARCHAR(15),
                         horaInicio TIME,
                         horaFin TIME,
                         idMedico INT,
                         FOREIGN KEY(idMedico) REFERENCES medico(id),
                         fecha_creacion DATE,
                         usuario_creacion VARCHAR(50),
                         fecha_actualizacion DATE,
                         usuario_actualizacion VARCHAR(50)
);

CREATE TABLE citamedica (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            fecha DATE,
                            hora TIME,
                            idPaciente INT,
                            idMedico INT,
                            motivo VARCHAR(255),
                            estado VARCHAR(20),
                            FOREIGN KEY(idPaciente) REFERENCES paciente(id),
                            FOREIGN KEY(idMedico) REFERENCES medico(id),
                            fecha_creacion DATE,
                            usuario_creacion VARCHAR(50),
                            fecha_actualizacion DATE,
                            usuario_actualizacion VARCHAR(50)
);

CREATE TABLE historiaclinica (
                                 id INT PRIMARY KEY AUTO_INCREMENT,
                                 idPaciente INT,
                                 fecha DATE,
                                 diagnostico VARCHAR(255),
                                 tratamiento VARCHAR(255),
                                 observaciones TEXT,
                                 FOREIGN KEY(idPaciente) REFERENCES paciente(id),
                                 fecha_creacion DATE,
                                 usuario_creacion VARCHAR(50),
                                 fecha_actualizacion DATE,
                                 usuario_actualizacion VARCHAR(50)
);

CREATE TABLE proveedor (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           nombre VARCHAR(100),
                           ruc CHAR(11),
                           direccion VARCHAR(100),
                           telefono VARCHAR(15),
                           email VARCHAR(100),
                           fecha_creacion DATE,
                           usuario_creacion VARCHAR(50),
                           fecha_actualizacion DATE,
                           usuario_actualizacion VARCHAR(50)
);

CREATE TABLE medicamento (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             nombre VARCHAR(100),
                             descripcion VARCHAR(255),
                             stockActual INT,
                             stockMinimo INT,
                             precio DECIMAL(10,2),
                             fechaVencimiento DATE,
                             fecha_creacion DATE,
                             usuario_creacion VARCHAR(50),
                             fecha_actualizacion DATE,
                             usuario_actualizacion VARCHAR(50)
);

CREATE TABLE ingreso_medicamento (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     idMedicamento INT,
                                     idProveedor INT,
                                     fechaIngreso DATE,
                                     cantidad INT,
                                     precioCompra DECIMAL(10,2),
                                     FOREIGN KEY(idMedicamento) REFERENCES medicamento(id),
                                     FOREIGN KEY(idProveedor) REFERENCES proveedor(id),
                                     fecha_creacion DATE,
                                     usuario_creacion VARCHAR(50),
                                     fecha_actualizacion DATE,
                                     usuario_actualizacion VARCHAR(50)
);

