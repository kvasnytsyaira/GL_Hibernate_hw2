create database if not exists company ;

USE company;
CREATE TABLE `department`
(
  `idDepartment` int(11) NOT NULL AUTO_INCREMENT,
  `name`         varchar(255) DEFAULT NULL,
  `status`       bit(1)  NOT NULL,
  PRIMARY KEY (`idDepartment`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `worker`
(
  `id`                        int(11) NOT NULL AUTO_INCREMENT,
  `age`                       int(11) NOT NULL,
  `availability`              enum('PARTTIME', 'FULLTIME') DEFAULT NULL,
  `full_name`                 varchar(255) DEFAULT NULL,
  `departmentId_idDepartment` int(11)      DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrfgtfgaxv90y0vsgulgrf1mdc` (`departmentId_idDepartment`),
  CONSTRAINT `FKrfgtfgaxv90y0vsgulgrf1mdc` FOREIGN KEY (`departmentId_idDepartment`) REFERENCES `department` (`idDepartment`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


insert into department(idDepartment , name , status)
values (1,"Finance", true),
       (2,"Human Resourses" , false),
       (3,"Accounting" , true),
       (4,"Management" , true),
       (5,"Marketing" , false),
       (6,"Sales" , true),
       (7,"Recruitment" , true),
       (8,"Tecnichal" , true);

insert into worker (id, age, availability , full_name ,departmentId_idDepartment )
values
(1,18,'FULLTIME' , 'Mary Kondo' , 1),
(2,18,'FULLTIME' , 'Anna Wintour' , 1),
(3,18,'FULLTIME' , 'Olivia Wild' , 2),
(4,19,'FULLTIME' , 'Kate Moss' , 2),
(5,22,'FULLTIME' , 'Klaus Michelson' , 3),
(6,45,'FULLTIME' , 'Mia Maples' , 3),
(7,32,'FULLTIME' , 'Kathryn Pearse' , 4),
(8,14,'PARTTIME' , 'Damon Tool' , 4),
(9,18,'FULLTIME' , 'Lily Evans', 5),
(10,20,'FULLTIME' , 'Ted Mosby' , 5),
(11,31,'FULLTIME' , 'Robin Shcherbatsky' , 6),
(12,50,'PARTTIME' , 'Barney Stinson' , 6),
(13,43,'FULLTIME' , 'Marshal Eriksen' , 7),
(14,25,'PARTTIME' , 'Lily Aldrin' , 7),
(15,17,'PARTTIME' , 'Rachel Green' , 8),
(16,18,'PARTTIME' , 'Ross Geller' , 8);


