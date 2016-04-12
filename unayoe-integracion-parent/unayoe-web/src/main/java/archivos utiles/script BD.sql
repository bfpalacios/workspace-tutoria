-- 1. CREAR LA CARPETA unayoe en la siguiente ruta "C:\oraclexe\app\oracle\oradata"

-- 2. Ingresar como system

CREATE SMALLFILE TABLESPACE "TS_UNAYOE" DATAFILE 'C:\oraclexe\app\oracle\oradata\UNAYOE\TS_UNAYOE' SIZE 300M AUTOEXTEND ON MAXSIZE
UNLIMITED LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO ;

CREATE TEMPORARY TABLESPACE "TS_TEMP_UNAYOE" TEMPFILE 'C:\oraclexe\app\oracle\oradata\UNAYOE\TS_TEMP_UNAYOE' SIZE 32m AUTOEXTEND on
NEXT 1m EXTENT MANAGEMENT LOCAL;

CREATE USER "DBUNAYOE" PROFILE "DEFAULT" IDENTIFIED BY "DBUNAYOE" DEFAULT TABLESPACE "TS_UNAYOE" TEMPORARY TABLESPACE "TS_TEMP_UNAYOE"
QUOTA UNLIMITED ON "TS_UNAYOE" ACCOUNT UNLOCK;

GRANT CONNECT TO "DBUNAYOE";
GRANT RESOURCE TO "DBUNAYOE";

--3 Ingresar como DBUNAYOE y crear tablas



CREATE TABLE ALUMNO
(
	a_codigo             CHAR(8) NOT NULL ,
	a_nombre             CHAR(60) NULL ,
	a_apellidos          CHAR(60) NULL ,
	a_fnacimiento        DATE NULL ,
	a_direccion          CHAR(80) NULL ,
	a_email              CHAR(40) NULL ,
	a_telefono           NUMBER(9) NULL ,
	a_dni                NUMBER(8) NULL 
);



CREATE UNIQUE INDEX XPKALUMNO ON ALUMNO
(a_codigo   ASC);



ALTER TABLE ALUMNO
	ADD CONSTRAINT  XPKALUMNO PRIMARY KEY (a_codigo);



CREATE TABLE ASISTENCIA_C_ALUM
(
	fecha                DATE NOT NULL ,
	a_codigo             CHAR(8) NOT NULL ,
	grupo                NUMBER(2) NOT NULL ,
	c_codigo             CHAR(8) NOT NULL ,
	asistencia           CHAR(1) NULL ,
	observacion          CHAR(250) NULL 
);



CREATE UNIQUE INDEX XPKASISTENCIA_C_ALUM ON ASISTENCIA_C_ALUM
(fecha   ASC,a_codigo   ASC,grupo   ASC,c_codigo   ASC);



ALTER TABLE ASISTENCIA_C_ALUM
	ADD CONSTRAINT  XPKASISTENCIA_C_ALUM PRIMARY KEY (fecha,a_codigo,grupo,c_codigo);



CREATE TABLE ASISTENCIA_C_PROF
(
	fecha                DATE NOT NULL ,
	p_codigo             CHAR(8) NOT NULL ,
	grupo                NUMBER(2) NOT NULL ,
	c_codigo             CHAR(8) NOT NULL ,
	asistencia           CHAR(1) NULL ,
	observacion          CHAR(250) NULL 
);



CREATE UNIQUE INDEX XPKASISTENCIA_C_PROF ON ASISTENCIA_C_PROF
(fecha   ASC,p_codigo   ASC,grupo   ASC,c_codigo   ASC);



ALTER TABLE ASISTENCIA_C_PROF
	ADD CONSTRAINT  XPKASISTENCIA_C_PROF PRIMARY KEY (fecha,p_codigo,grupo,c_codigo);



CREATE TABLE ASISTENCIA_T_ALUM
(
	FECHA_T              DATE NOT NULL ,
	ASISTENCIA_T         CHAR(1) NULL ,
	OBSERVACION_T        CHAR(250) NULL ,
	t_anio               NUMBER(4) NOT NULL ,
	t_codigo             CHAR(8) NOT NULL ,
	t_periodo            NUMBER(2) NOT NULL 
);



CREATE UNIQUE INDEX XPKASISTENCIA_T_ALUM ON ASISTENCIA_T_ALUM
(FECHA_T   ASC,t_anio   ASC,t_periodo   ASC,t_codigo   ASC);



ALTER TABLE ASISTENCIA_T_ALUM
	ADD CONSTRAINT  XPKASISTENCIA_T_ALUM PRIMARY KEY (FECHA_T,t_anio,t_periodo,t_codigo);



CREATE TABLE ASISTENCIA_T_PROF
(
	FECHA_T              DATE NOT NULL ,
	ASISTENCIA_T         CHAR(1) NULL ,
	OBSERVACION_T        CHAR(250) NULL ,
	t_anio               NUMBER(4) NOT NULL ,
	t_periodo            NUMBER(2) NOT NULL ,
	t_codigo             CHAR(8) NOT NULL 
);



CREATE UNIQUE INDEX XPKASISTENCIA_T_PROF ON ASISTENCIA_T_PROF
(FECHA_T   ASC,t_anio   ASC,t_periodo   ASC,t_codigo   ASC);



ALTER TABLE ASISTENCIA_T_PROF
	ADD CONSTRAINT  XPKASISTENCIA_T_PROF PRIMARY KEY (FECHA_T,t_anio,t_periodo,t_codigo);



CREATE TABLE CURSO
(
	c_codigo             CHAR(8) NOT NULL ,
	nombre               CHAR(80) NULL ,
	creditos             INTEGER NOT NULL 
);



CREATE UNIQUE INDEX XPKCURSO ON CURSO
(c_codigo   ASC);



ALTER TABLE CURSO
	ADD CONSTRAINT  XPKCURSO PRIMARY KEY (c_codigo);



CREATE TABLE DIAS_CLASE
(
	anio                 NUMBER(4) NOT NULL ,
	periodo              NUMBER(2) NOT NULL ,
	grupo                NUMBER(2) NOT NULL ,
	c_codigo             CHAR(8) NOT NULL ,
	dia                  CHAR(40) NULL ,
	hora_ini             CHAR(8) NOT NULL ,
	hora_fin             CHAR(8) NOT NULL 
);



CREATE UNIQUE INDEX XPKDIAS_CLASE ON DIAS_CLASE
(anio   ASC,periodo   ASC,grupo   ASC,c_codigo   ASC);



ALTER TABLE DIAS_CLASE
	ADD CONSTRAINT  XPKDIAS_CLASE PRIMARY KEY (anio,periodo,grupo,c_codigo);



CREATE TABLE GRUPO
(
	anio                 NUMBER(4) NOT NULL ,
	periodo              NUMBER(2) NOT NULL ,
	grupo                NUMBER(2) NOT NULL ,
	c_codigo             CHAR(8) NOT NULL ,
	p_codigo             CHAR(8) NULL 
);



CREATE UNIQUE INDEX XPKGRUPO ON GRUPO
(anio   ASC,periodo   ASC,grupo   ASC,c_codigo   ASC);



ALTER TABLE GRUPO
	ADD CONSTRAINT  XPKGRUPO PRIMARY KEY (anio,periodo,grupo,c_codigo);



CREATE TABLE MATRICULA
(
	a_codigo             CHAR(8) NOT NULL ,
	repitencias          INTEGER NULL ,
	anio                 NUMBER(4) NOT NULL ,
	periodo              NUMBER(2) NOT NULL ,
	grupo                NUMBER(2) NOT NULL ,
	c_codigo             CHAR(8) NOT NULL 
);



CREATE UNIQUE INDEX XPKMATRICULA ON MATRICULA
(a_codigo   ASC,anio   ASC,periodo   ASC,grupo   ASC,c_codigo   ASC);



ALTER TABLE MATRICULA
	ADD CONSTRAINT  XPKMATRICULA PRIMARY KEY (a_codigo,anio,periodo,grupo,c_codigo);



CREATE TABLE NOTAS
(
	a_codigo             CHAR(8) NOT NULL ,
	c_codigo             CHAR(8) NOT NULL ,
	anio                 NUMBER(4) NOT NULL ,
	periodo              CHAR(2) NOT NULL ,
	E1                   REAL NOT NULL ,
	E2                   REAL NOT NULL ,
	E3                   REAL NOT NULL ,
	E4                   REAL NOT NULL ,
	E5                   REAL NOT NULL ,
	EP                   REAL NOT NULL ,
	EF                   REAL NOT NULL 
);



CREATE UNIQUE INDEX XPKNOTAS ON NOTAS
(anio   ASC,periodo   ASC,a_codigo   ASC,c_codigo   ASC);



ALTER TABLE NOTAS
	ADD CONSTRAINT  XPKNOTAS PRIMARY KEY (anio,periodo,a_codigo,c_codigo);



CREATE TABLE PROFESOR
(
	p_codigo             CHAR(8) NOT NULL ,
	p_nombre             CHAR(25) NULL ,
	p_apellidos          CHAR(25) NULL ,
	p_fnacimiento        DATE NULL ,
	p_direccion          CHAR(40) NULL ,
	p_email              CHAR(40) NULL ,
	p_telefono           NUMBER(9) NULL ,
	p_dni                NUMBER(8) NULL 
);



CREATE UNIQUE INDEX XPKPROFESOR ON PROFESOR
(p_codigo   ASC);



ALTER TABLE PROFESOR
	ADD CONSTRAINT  XPKPROFESOR PRIMARY KEY (p_codigo);



CREATE TABLE TUTORIA
(
	t_anio               NUMBER(4) NOT NULL ,
	t_periodo            NUMBER(2) NOT NULL ,
	t_codigo             CHAR(8) NOT NULL ,
	a_codigo             CHAR(8) NULL ,
	p_codigo             CHAR(8) NULL ,
	dia                  CHAR(40) NULL ,
	hora_ini             CHAR(8) NULL ,
	hora_fin             CHAR(8) NULL ,
	c_codigo             CHAR(8) NULL 
);



CREATE UNIQUE INDEX XPKTUTORIA ON TUTORIA
(t_anio   ASC,t_periodo   ASC,t_codigo   ASC);



ALTER TABLE TUTORIA
	ADD CONSTRAINT  XPKTUTORIA PRIMARY KEY (t_anio,t_periodo,t_codigo);




CREATE TABLE USUARIO (
       ID_USUARIO           VARCHAR2(20) NOT NULL,
       CLAVE                VARCHAR2(50) NULL,
       NOMBRES              VARCHAR2(64) NULL,
       MATERNO              VARCHAR2(32) NULL,
       PATERNO              VARCHAR2(32) NULL,
       CORREO               VARCHAR2(64) NULL,
       DIRECCION            VARCHAR2(128) NULL,
       TELEFONO             VARCHAR2(32) NULL,
       ESTADO               CHAR(1) NULL
);

CREATE UNIQUE INDEX XPKUSUARIO ON USUARIO
(
       ID_USUARIO                     ASC
);


ALTER TABLE USUARIO
       ADD  ( PRIMARY KEY (ID_USUARIO) ) ;



ALTER TABLE ASISTENCIA_C_ALUM
	ADD (CONSTRAINT R_17 FOREIGN KEY (a_codigo) REFERENCES ALUMNO (a_codigo));



ALTER TABLE ASISTENCIA_C_PROF
	ADD (CONSTRAINT R_25 FOREIGN KEY (p_codigo) REFERENCES PROFESOR (p_codigo));



ALTER TABLE ASISTENCIA_T_ALUM
	ADD (CONSTRAINT R_26 FOREIGN KEY (t_anio, t_periodo, t_codigo) REFERENCES TUTORIA (t_anio, t_periodo, t_codigo));



ALTER TABLE ASISTENCIA_T_PROF
	ADD (CONSTRAINT R_28 FOREIGN KEY (t_anio, t_periodo, t_codigo) REFERENCES TUTORIA (t_anio, t_periodo, t_codigo));



ALTER TABLE DIAS_CLASE
	ADD (CONSTRAINT R_8 FOREIGN KEY (anio, periodo, grupo, c_codigo) REFERENCES GRUPO (anio, periodo, grupo, c_codigo));



ALTER TABLE GRUPO
	ADD (CONSTRAINT R_3 FOREIGN KEY (c_codigo) REFERENCES CURSO (c_codigo));



ALTER TABLE GRUPO
	ADD (CONSTRAINT R_7 FOREIGN KEY (p_codigo) REFERENCES PROFESOR (p_codigo) ON DELETE SET NULL);



ALTER TABLE MATRICULA
	ADD (CONSTRAINT R_35 FOREIGN KEY (a_codigo) REFERENCES ALUMNO (a_codigo));



ALTER TABLE MATRICULA
	ADD (CONSTRAINT R_54 FOREIGN KEY (anio, periodo, grupo, c_codigo) REFERENCES GRUPO (anio, periodo, grupo, c_codigo));



ALTER TABLE NOTAS
	ADD (CONSTRAINT R_33 FOREIGN KEY (a_codigo) REFERENCES ALUMNO (a_codigo));



ALTER TABLE NOTAS
	ADD (CONSTRAINT R_34 FOREIGN KEY (c_codigo) REFERENCES CURSO (c_codigo));



ALTER TABLE TUTORIA
	ADD (CONSTRAINT R_23 FOREIGN KEY (a_codigo) REFERENCES ALUMNO (a_codigo) ON DELETE SET NULL);



ALTER TABLE TUTORIA
	ADD (CONSTRAINT R_24 FOREIGN KEY (p_codigo) REFERENCES PROFESOR (p_codigo) ON DELETE SET NULL);



ALTER TABLE TUTORIA
	ADD (CONSTRAINT R_39 FOREIGN KEY (c_codigo) REFERENCES CURSO (c_codigo) ON DELETE SET NULL);

 CREATE TABLE "DBUNAYOE"."USUARIO_ROL"
( "ID_ROL" NUMBER NOT NULL ENABLE,
"ROLE" VARCHAR2(50) NOT NULL ENABLE,
"ID_USUARIO" VARCHAR2(50) NOT NULL ENABLE
) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
TABLESPACE "TS_UNAYOE" ;

ALTER TABLE "DBUNAYOE"."USUARIO_ROL" MODIFY ("ID_ROL" NOT NULL ENABLE);

ALTER TABLE "DBUNAYOE"."USUARIO_ROL" MODIFY ("ROLE" NOT NULL ENABLE);

ALTER TABLE "DBUNAYOE"."USUARIO_ROL" MODIFY ("ID_USUARIO" NOT NULL ENABLE);

	
--4. Insert	iniciales 



insert into alumno values ('08200001', 'cristian', 'rios cornejo' , '01/07/1982', 'san luis calle 15', 'carlos@gmail.com' , '998525264', '42836268');
insert into alumno values ('08200003', 'carlos'  , 'duran cueba'  , '01/05/1982', 'los sauces', 'carlos@gmail.com' , '998525266', '42836270');
insert into alumno values ('08200004', 'jose'    , 'matos guebara', '11/07/1982', 'la flores sector 1', 'jose@gmail.com'   , '998525267', '42836271');
insert into alumno values ('08200005', 'carla'   , 'jordan ruiz'  , '21/07/1982', 'LAS MAGNOLIAS', 'carla@gmail.com'  , '998525268', '42836272');
insert into alumno values ('08200006', 'rosio'   , 'cordoba garcia', '11/09/1982', 'rosa toro cuadra 2', 'rosio@gmail.com'  , '998525269', '42836273');
insert into alumno values ('08200007', 'ana'     , 'tejada tejada', '24/07/1982', 'ovalo naranjal', 'ana@gmail.com'    , '998525270', '42836274');
insert into alumno values ('08200008', 'diego'   , 'sarazu cotrina', '30/07/1982', 'san luis calle 11', 'diego@gmail.com'  , '998525271', '42836275');
insert into alumno values ('08200009', 'daniela' , 'cotrina dionicio', '15/07/1982', 'san luis calle 19', 'daniela@gmail.com', '998525272', '42836276');
insert into alumno values ('08200010', 'roberto' , 'huallanca quispe', '16/07/1982', 'san luis calle 20', 'roberto@gmail.com', '998525273', '42836277');




insert into curso values ('C0000001', 'Algoritmica 1', '4');
insert into curso values ('C0000002', 'Algoritmica 2', '4');
insert into curso values ('C0000003', 'Algoritmica 3', '4');
insert into curso values ('C0000004', 'calculo 1', '3');
insert into curso values ('C0000005', 'calculo 2', '3');
insert into curso values ('C0000006', 'diseno grafico', '2');
insert into curso values ('C0000007', 'tesis', '3');
insert into curso values ('C0000008', 'tecnicas de modelamiento', '4');
insert into curso values ('C0000009', 'fisica', '4');

insert into profesor values ('PR000001', 'luis' , 'galvez sanchez',  '01/07/1972', 'san luis calle 15', 'luis@gmail.com', '998525273', '42836277');
insert into profesor values ('PR000002', 'fancisco' , 'torres obregon' , '02/07/1972', 'las vegas cdra 20', 'francisco@gmail.com'   , '998525275', '42836289');
insert into profesor values ('PR000003', 'alfredo'  , 'vallejos zuniga'  , '01/06/1972', 'av. arequipa cdra 50', 'alfredo@gmail.com' , '998525276', '42836280');
insert into profesor values ('PR000004', 'marisol'    , 'tambra leon', '11/09/1972', 'villla sol segunda etapa', 'marisol@gmail.com'   , '998525277', '42836281');
insert into profesor values ('PR000005', 'marcos'   , 'de la cruz'  , '07/06/1972', 'la floresta', 'marcos@gmail.com'  , '998525278', '42836282');
insert into profesor values ('PR000006', 'eduardo'   , 'cortez vasques', '06/07/1972', 'los portales calle2', 'eduardo@gmail.com'  , '998525279', '42836283');
insert into profesor values ('PR000007', 'maria'     , 'salinas asana', '01/11/1972', 'universitaria cdra. 16', 'maria@gmail.com'    , '998525280', '42836284');
insert into profesor values ('PR000008', 'denise'   , 'del pino', '04/11/1962', 'lurigancho segunda etapa', 'denise@gmail.com'  , '998525281', '42836285');
insert into profesor values ('PR000009', 'mariela' , 'mejia melo', '01/07/1972', 'san luis calle 15', 'mariela@gmail.com', '998525282', '42836286');
insert into profesor values ('PR000010', 'samuel' , 'merino sanchez', '01/07/1952', 'el solar condominio 8', 'samuel@gmail.com', '998525283', '42836287');

insert into grupo values ('2014', '1', '1', 'C0000001','PR000001');
insert into grupo values ('2014', '1', '2', 'C0000001','PR000002');
insert into grupo values ('2014', '1', '3', 'C0000001','PR000003');
insert into grupo values ('2014', '1', '1', 'C0000002','PR000004');
insert into grupo values ('2014', '1', '2', 'C0000002','PR000005');
insert into grupo values ('2014', '1', '1', 'C0000003','PR000006');
insert into grupo values ('2014', '1', '2', 'C0000003','PR000007');
insert into grupo values ('2014', '1', '1', 'C0000004','PR000009');


insert into ASISTENCIA_C_ALUM values ( sysdate , '08200001', '1', 'C0000001', 'A', '');
insert into ASISTENCIA_C_ALUM values (to_date('09/10/2014' , 'dd/mm/yyyy')  , '08200003', '1', 'C0000001', 'A', 'motivos de salud');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200003', '2', 'C0000001', 'T', '');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200004', '2', 'C0000001', 'F', 'motivos de salud');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200005', '1', 'C0000002', 'F', 'motivos de salud');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200006', '1', 'C0000002', 'A', '');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200007', '2', 'C0000003', 'A', '');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200008', '2', 'C0000003', 'A', '');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200009', '1', 'C0000004', 'T', '');
insert into ASISTENCIA_C_ALUM values ( sysdate , '08200010', '1', 'C0000004', 'T', '');


insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000001', '1', 'C0000001', 'A', '');
insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000002', '2', 'C0000001', 'T', '');
insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000003', '3', 'C0000001', 'T', '');
insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000004', '1', 'C0000002', 'T', '');
insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000005', '2', 'C0000002', 'F', 'motivos de salud');
insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000006', '1', 'C0000003', 'F', 'motivos de salud');
insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000007', '2', 'C0000003', 'F', 'motivos de salud');
insert into ASISTENCIA_C_PROF values ( sysdate , 'PR000009', '1', 'C0000004', 'F', 'motivos de salud');


INSERT INTO TUTORIA VALUES('2014','1','T0000001','08200001','PR000001','LUNES','15:00:00','20:00:00','C0000001');
INSERT INTO TUTORIA VALUES('2014','1','T0000002','08200001','PR000002','LUNES','15:00:00','20:00:00','C0000002');
INSERT INTO TUTORIA VALUES('2014','1','T0000003','08200003','PR000003','LUNES','15:00:00','20:00:00','C0000002');
INSERT INTO TUTORIA VALUES('2014','1','T0000004','08200004','PR000004','LUNES','15:00:00','20:00:00','C0000005');
INSERT INTO TUTORIA VALUES('2014','1','T0000005','08200005','PR000005','LUNES','15:00:00','20:00:00','C0000003');
INSERT INTO TUTORIA VALUES('2014','1','T0000006','08200006','PR000006','LUNES','15:00:00','20:00:00','C0000004');
INSERT INTO TUTORIA VALUES('2014','1','T0000007','08200007','PR000007','LUNES','15:00:00','20:00:00','C0000007');
INSERT INTO TUTORIA VALUES('2014','1','T0000008','08200008','PR000008','LUNES','15:00:00','20:00:00','C0000008');


INSERT INTO MATRICULA VALUES ('08200001','3','2014','1','1','C0000001');
INSERT INTO MATRICULA VALUES ('08200003','3','2014','1','1','C0000001');
INSERT INTO MATRICULA VALUES ('08200004','4','2014','1','2','C0000001');
INSERT INTO MATRICULA VALUES ('08200005','3','2014','1','1','C0000002');
INSERT INTO MATRICULA VALUES ('08200006','3','2014','1','1','C0000002');
INSERT INTO MATRICULA VALUES ('08200007','3','2014','1','2','C0000003');



insert into ASISTENCIA_T_ALUM values ( sysdate , 'A','', '2014','T0000001','1');
insert into ASISTENCIA_T_ALUM values ( sysdate , 'A','', '2014','T0000002','1');
insert into ASISTENCIA_T_ALUM values ( sysdate , 'A','', '2014','T0000003','1');
insert into ASISTENCIA_T_ALUM values ( sysdate , 'A','', '2014','T0000004','1');
insert into ASISTENCIA_T_ALUM values ( sysdate , 'A','', '2014','T0000005','1');
insert into ASISTENCIA_T_ALUM values ( sysdate , 'A','', '2014','T0000006','1');


insert into ASISTENCIA_T_PROF values ( sysdate , 'A','', '2014','1','T0000001');
insert into ASISTENCIA_T_PROF values ( sysdate , 'A','', '2014','1','T0000002');
insert into ASISTENCIA_T_PROF values ( sysdate , 'A','', '2014','1','T0000003');
insert into ASISTENCIA_T_PROF values ( sysdate , 'A','', '2014','1','T0000004');
insert into ASISTENCIA_T_PROF values ( sysdate , 'A','', '2014','1','T0000005');


INSERT INTO DIAS_CLASE VALUES ('2014','1','1','C0000001', 'MARTES','12:00:00', '15:00:00');
INSERT INTO DIAS_CLASE VALUES ('2014','1','2','C0000001', 'MARTES','12:00:00', '15:00:00');
INSERT INTO DIAS_CLASE VALUES ('2014','1','3','C0000001', 'MARTES','12:00:00', '15:00:00');
INSERT INTO DIAS_CLASE VALUES ('2014','1','1','C0000002', 'MARTES','12:00:00', '15:00:00');
INSERT INTO DIAS_CLASE VALUES ('2014','1','2','C0000002', 'MARTES','12:00:00', '15:00:00');
INSERT INTO DIAS_CLASE VALUES ('2014','1','1','C0000003', 'MARTES','12:00:00', '15:00:00');
INSERT INTO DIAS_CLASE VALUES ('2014','1','2','C0000003', 'MARTES','12:00:00', '15:00:00');
INSERT INTO DIAS_CLASE VALUES ('2014','1','1','C0000004', 'MARTES','12:00:00', '15:00:00');

insert into USUARIO (ID_USUARIO, CLAVE, NOMBRES, MATERNO, PATERNO, CORREO, DIRECCION, TELEFONO, ESTADO)
values ('BFPALACIOS', '601f1889667efaebb33b8c12572835da3f027f78', 'Bruno', 'Estrada', 'Palacios', 'asdsa@asd.as', 'asdasd', '54545', '1');

insert into USUARIO_ROL (ID_ROL, ROLE, ID_USUARIO)
values (1, 'ROLE_ADMIN', 'BFPALACIOS');
