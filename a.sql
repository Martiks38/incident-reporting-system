DROP DATABASE IF EXISTS `incident-reporting-system`;

CREATE DATABASE IF NOT EXISTS `incident-reporting-system`;

USE `incident-reporting-system`;

DROP TABLE IF EXISTS `incident-reporting-system`.`notification_medium`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`notification_medium`(
	id BIGINT NOT NULL AUTO_INCREMENT,
    `medium` VARCHAR(15),
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`technical`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`technical`(
	id CHAR(36) NOT NULL,
	`number_incidents_resolved` INT NOT NULL DEFAULT 0,
	`incident_resolution_speed` BIGINT NULL DEFAULT NULL,
	mail VARCHAR(45) NOT NULL,
	`phone_number` VARCHAR(45) NOT NULL,
	`fk_notification_medium` BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(`fk_notification_medium`)
		REFERENCES `incident-reporting-system`.`notification_medium` (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`specialty`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`specialty`(
	id BIGINT NOT NULL AUTO_INCREMENT,
	`specialty_name` VARCHAR(60) NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`technical__specialty`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`technical__specialty`(
	`fk_ts_technical` CHAR(36) NOT NULL,
	`fk_ts_specialty` BIGINT NOT NULL,
    FOREIGN KEY (`fk_ts_technical`)
		REFERENCES `incident-reporting-system`.`technical` (id),
    FOREIGN KEY (`fk_ts_specialty`)
		REFERENCES `incident-reporting-system`.`specialty` (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`service`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`service`(
	id BIGINT NOT NULL AUTO_INCREMENT,
	`service_name` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`client`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`client`(
	id CHAR(36) NOT NULL,
	cuit VARCHAR(11) NOT NULL,
	`business_name` VARCHAR(80) NOT NULL,
	mail VARCHAR(45) NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`type_problem`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`type_problem`(
	id BIGINT NOT NULL AUTO_INCREMENT,
	`type_problem_name` VARCHAR(45) NOT NULL,
	`maximum_resolution_time` BIGINT NOT NULL,
	`estimated_resolution_time` BIGINT NOT NULL,
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`type_problem__specialty`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`type_problem__specialty`(
	`fk_tps_type_problem` BIGINT NOT NULL,
	`fk_tps_specialty` BIGINT NOT NULL,
    FOREIGN KEY (`fk_tps_type_problem`)
		REFERENCES `incident-reporting-system`.`type_problem` (id),
    FOREIGN KEY (`fk_tps_specialty`)
		REFERENCES `incident-reporting-system`.`specialty` (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`incident`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`incident`(
	id CHAR(36) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
	considerations VARCHAR(255) NOT NULL,
	`fk_technical_id` CHAR(36) NOT NULL,
	`fk_client_id` CHAR(36) NOT NULL,
	resolved TINYINT NOT NULL,
	`create_time` TIMESTAMP NOT NULL,
	`time_is_up` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
    FOREIGN KEY (`fk_client_id`)
		REFERENCES `incident-reporting-system`.`client` (id),
    FOREIGN KEY (`fk_technical_id`)
		REFERENCES `incident-reporting-system`.`technical` (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`client__service`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`client__service`(
	`fk_cs_client` CHAR(36) NOT NULL,
	`fk_cs_service` BIGINT NOT NULL,
    FOREIGN KEY (`fk_cs_client`)
		REFERENCES `incident-reporting-system`.`client` (id),
    FOREIGN KEY (`fk_cs_service`)
		REFERENCES `incident-reporting-system`.`service` (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`incident__type_problem`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`incident__type_problem`(
	`fk_ip_incident` CHAR(36) NOT NULL,
	`fk_ip_type_problem` BIGINT NOT NULL,
    FOREIGN KEY (`fk_ip_incident`)
		REFERENCES `incident-reporting-system`.`incident` (id),
    FOREIGN KEY (`fk_ip_type_problem`)
		REFERENCES `incident-reporting-system`.`type_problem` (id)
);

DROP TABLE IF EXISTS `incident-reporting-system`.`service__type_problem`;
CREATE TABLE IF NOT EXISTS `incident-reporting-system`.`service__type_problem`(
	`fk_stp_service` BIGINT NOT NULL,
	`fk_stp_type_problem` BIGINT NOT NULL,
	FOREIGN KEY (`fk_stp_service`)
		REFERENCES `incident-reporting-system`.`service` (id),
    FOREIGN KEY (`fk_stp_type_problem`)
		REFERENCES `incident-reporting-system`.`type_problem` (id)
);

-- -----------------------------------------------------
-- Add values to tables
-- -----------------------------------------------------

INSERT INTO `incident-reporting-system`.`notification_medium`
  VALUES
    (1, "Email"),
    (2, "WhatsApp");

INSERT INTO `incident-reporting-system`.technical
VALUES
    ("f6e57f24-6f9e-4ac5-bb5d-6eb76d048812", "Maider Gomez", 2, 14400043, "maidergomez@gmail.com", "3476123456", 1),
    ("20184a2c-35bf-427e-aa18-92c4e2a42a07", "Gheorghe Galindo", 5, 32400000, "gheorghe.galindo@hotmail.com", "3411234567", 2),
    ("a459b81d-e9f5-41e2-884f-b50e10038a29", "Cesareo Gutierrez", 1, 14400981, "cesareo@gutierrez.com", "1112345678", 1),
    ("66d3085f-411c-45f3-9fff-54ea835adb56", "Alejandra Puerta", 0, null, "alejandra_puerta@outlook.com", "3421234567", 2);

INSERT INTO `incident-reporting-system`.specialty
	VALUES
    (1, "ui/ux"),
    (2, "software"),
    (3, "training"),
    (4, "database"),
    (5, "security"),
    (6, "backup and recuperation"),
    (7, "technology consulting");

INSERT INTO `incident-reporting-system`.technical__specialty
	VALUES
    ("f6e57f24-6f9e-4ac5-bb5d-6eb76d048812", 1),
    ("f6e57f24-6f9e-4ac5-bb5d-6eb76d048812", 2),
    ("f6e57f24-6f9e-4ac5-bb5d-6eb76d048812", 3),
    ("20184a2c-35bf-427e-aa18-92c4e2a42a07", 1),
    ("20184a2c-35bf-427e-aa18-92c4e2a42a07", 2),
    ("a459b81d-e9f5-41e2-884f-b50e10038a29", 3),
    ("66d3085f-411c-45f3-9fff-54ea835adb56", 4),
    ("66d3085f-411c-45f3-9fff-54ea835adb56", 5);

INSERT INTO `incident-reporting-system`.`service`
	VALUES
    (1, 'basic'),
    (2, 'advanced prioritization service'),
    (3, 'escalation service'),
    (4, 'proactive maintenance'),
    (5, 'additional post-incident training');
    
INSERT INTO `incident-reporting-system`.`client`
	VALUES
    ('ee9d15b8-c736-42be-901a-13093494d2fe', '34447117969', 'TecnoDynamics Solutions', "contact@technoDynamics.com"),
    ('f292f337-9a75-4d7e-a4a1-33d888765141', '27729800193', 'Global InnovateTech Services', "contact@innovateTech.com"),
    ('71a3a083-afa5-4b87-86a5-535f627818af', '30956149653', 'Quantum Nexus Enterprises', "contact@quantumNexus.com");

INSERT INTO `incident-reporting-system`.type_problem
	VALUES
	(1, 'access problems', 7200000, 1080000),
	(2, 'software errors', 43200000, 28800000),
	(3, 'connectivity problem', 8640000, 5400000),
	(4, 'security issues', 100800000, 57600000),
	(5, 'updates and patches', 50400000, 21600000),
	(6, 'database errors', 108000000, 86400000),
	(7, 'integration problems', 144000000, 108000000),
	(8, 'performance problems', 61200000, 32400000);

INSERT INTO `incident-reporting-system`.type_problem__specialty
	VALUES
    (1, 1),
	  (2, 2),
	  (3, 2),
	  (4, 5),
	  (5, 2),
    (5, 6),
	  (6, 4),
	  (7, 2),
    (7, 3),
    (7, 4),
    (7, 5),
    (7, 6),
	  (8, 2),
    (8, 4),
    (8, 5),
    (8, 7);
    
INSERT INTO `incident-reporting-system`.incident
	VALUES
    ('5138aede-a64c-471b-a6e2-8ac344fb2cd2', 'Los usuarios experimentan problemas intermitentes de conexión a la red, lo que resulta en la imposibilidad de acceder a servicios en línea o compartir archivos de manera efectiva.', 'Problemas intermitentes de conexión afectan la accesibilidad a servicios en línea y la eficacia en la compartición de archivos. Se requiere diagnóstico exhaustivo, considerando enrutador, posibles interferencias y configuraciones de firewall.', "f6e57f24-6f9e-4ac5-bb5d-6eb76d048812", 'ee9d15b8-c736-42be-901a-13093494d2fe', 0, '2023-11-24 16:13:34', null),
    ('ea1ebab9-55e3-4b68-a0fa-396f64150fb9', 'Se ha detectado un intento de acceso no autorizado a sistemas críticos de la empresa, lo que representa una amenaza potencial para la integridad y la confidencialidad de los datos.', 'Reporte de usuarios con errores al acceder a la base de datos, impactando aplicaciones dependientes. Se requiere revisión y corrección de consultas, asegurando la integridad de datos y optimizando el rendimiento.', '66d3085f-411c-45f3-9fff-54ea835adb56', '71a3a083-afa5-4b87-86a5-535f627818af', 0, '2023-11-18 20:13:34', null),
    ('e6327f74-af7d-453f-9d0a-4edcebc9faca', 'Los usuarios informan errores al intentar acceder o manipular datos en la base de datos, lo que afecta la funcionalidad de las aplicaciones que dependen de la información almacenada.', 'Usuarios reportan errores en acceso y manipulación de datos en la base, afectando aplicaciones dependientes. Se necesita revisar y corregir consultas para garantizar la integridad y optimizar el rendimiento.', '66d3085f-411c-45f3-9fff-54ea835adb56', 'f292f337-9a75-4d7e-a4a1-33d888765141', 0, '2023-11-20 01:13:34', null);

INSERT INTO `incident-reporting-system`.client__service
  VALUES
    ('ee9d15b8-c736-42be-901a-13093494d2fe', 2),
    ('71a3a083-afa5-4b87-86a5-535f627818af', 3),
    ('f292f337-9a75-4d7e-a4a1-33d888765141', 5);

INSERT INTO `incident-reporting-system`.incident__type_problem
  VALUES
    ('5138aede-a64c-471b-a6e2-8ac344fb2cd2', 3),
    ('ea1ebab9-55e3-4b68-a0fa-396f64150fb9', 4),
    ('e6327f74-af7d-453f-9d0a-4edcebc9faca', 6);

INSERT INTO `incident-reporting-system`.service__type_problem
  VALUES
    (1, 1),  -- basic service can resolve access problems
    (2, 2),  -- advanced prioritization service can resolve software errors
    (2, 3),  -- advanced prioritization service can resolve connectivity problems
    (3, 4),  -- escalation service can resolve security issues
    (4, 5),  -- proactive maintenance can resolve updates and patches
    (5, 6),  -- additional post-incident training can resolve database errors
    (5, 7),  -- additional post-incident training can resolve integration problems
    (5, 8);  -- additional post-incident training can resolve performance problems
