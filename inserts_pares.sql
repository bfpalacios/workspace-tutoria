-- conexion TUTORIA user DBTUTORIA pass DBTUTORIA

INSERT INTO DBTUTORIA.USUARIO (ID_USUARIO,CLAVE,NOMBRES,MATERNO,PATERNO,CORREO,DIRECCION,TELEFONO,ESTADO) VALUES ('ALUMNOPAR','601f1889667efaebb33b8c12572835da3f027f78',NULL,NULL,NULL,NULL,NULL,NULL,'A');
INSERT INTO DBTUTORIA.USUARIO (ID_USUARIO,CLAVE,NOMBRES,MATERNO,PATERNO,CORREO,DIRECCION,TELEFONO,ESTADO) VALUES ('DECANOPAR','601f1889667efaebb33b8c12572835da3f027f78',NULL,NULL,NULL,NULL,NULL,NULL,'A');
INSERT INTO DBTUTORIA.USUARIO (ID_USUARIO,CLAVE,NOMBRES,MATERNO,PATERNO,CORREO,DIRECCION,TELEFONO,ESTADO) VALUES ('OCAAPAR','601f1889667efaebb33b8c12572835da3f027f78',NULL,NULL,NULL,NULL,NULL,NULL,'A');
INSERT INTO DBTUTORIA.USUARIO (ID_USUARIO,CLAVE,NOMBRES,MATERNO,PATERNO,CORREO,DIRECCION,TELEFONO,ESTADO) VALUES ('TUTORPAR','601f1889667efaebb33b8c12572835da3f027f78',NULL,NULL,NULL,NULL,NULL,NULL,'A');

INSERT INTO DBTUTORIA.ROL (ID_ROL,ROL_USUARIO,DESC_ROL) VALUES (14,'ROLE_ADMIN_P','Administrador Pares');
INSERT INTO DBTUTORIA.ROL (ID_ROL,ROL_USUARIO,DESC_ROL) VALUES (15,'ROLE_ALUMNO_P','Alumno Par');
INSERT INTO DBTUTORIA.ROL (ID_ROL,ROL_USUARIO,DESC_ROL) VALUES (16,'ROLE_DECANO_P','DECANO PARES');
INSERT INTO DBTUTORIA.ROL (ID_ROL,ROL_USUARIO,DESC_ROL) VALUES (17,'ROLE_OCAA_P','OCAA Pares');
INSERT INTO DBTUTORIA.ROL (ID_ROL,ROL_USUARIO,DESC_ROL) VALUES (18,'ROLE_TUTOR_P','Tutor Pares');

Insert into DBTUTORIA.USUARIO_ROL (ID_ROL,ID_USUARIO,ROL) values (14,'ADMINPAR','ROLE_ADMIN_P');
INSERT INTO DBTUTORIA.USUARIO_ROL (ID_ROL,ID_USUARIO,ROL) VALUES (15,'ALUMNOPAR','ROLE_ALUMNO_P');
INSERT INTO DBTUTORIA.USUARIO_ROL (ID_ROL,ID_USUARIO,ROL) VALUES (16,'DECANOPAR','ROLE_DECANO_P');
INSERT INTO DBTUTORIA.USUARIO_ROL (ID_ROL,ID_USUARIO,ROL) VALUES (17,'OCAAPAR','ROLE_OCAA_P');
INSERT INTO DBTUTORIA.USUARIO_ROL (ID_ROL,ID_USUARIO,ROL) VALUES (18,'TUTORPAR','ROLE_TUTOR_P');

COMMIT;