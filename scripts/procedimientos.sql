 CREATE OR REPLACE PROCEDURE "DBTUTORIA"."ACTUALIZAR_HORAFIN" 
/*---------------------------------------------------------------------------*/
/* Nombre    : ACTUALIZAR_HORAFIN                                            */
/* Objetivo  : Actualiza la hora fin en base a la hora inicio                */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_HORAINICIO   TABLA_MAESTRA.ID_CAMPO%TYPE,
    C_HORAFIN       OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    PRIMERA_HORA    TABLA_MAESTRA.ID_CAMPO%TYPE;
    SEGUNDA_HORA    TABLA_MAESTRA.ID_CAMPO%TYPE;

BEGIN        
    IF ID_HORAINICIO <> 21 THEN
        PRIMERA_HORA := ID_HORAINICIO + 1;
        SEGUNDA_HORA := PRIMERA_HORA + 1;
        
        OPEN C_HORAFIN FOR
            SELECT ID_CAMPO, VALOR_CAMPO 
              FROM TABLA_MAESTRA 
             WHERE NOMBRE_TABLA = 'HORA' 
               AND NOMBRE_CAMPO = 'HORA_FIN'
               AND ID_CAMPO IN (PRIMERA_HORA, SEGUNDA_HORA);
    ELSE
        PRIMERA_HORA := ID_HORAINICIO + 1;
            OPEN C_HORAFIN FOR
                SELECT ID_CAMPO, VALOR_CAMPO 
                  FROM TABLA_MAESTRA 
                 WHERE NOMBRE_TABLA = 'HORA' 
                   AND NOMBRE_CAMPO = 'HORA_FIN'
                   AND ID_CAMPO IN (PRIMERA_HORA);
    END IF;
    
END ACTUALIZAR_HORAFIN;

/
--------------------------------------------------------
--  DDL for Procedure ACTUALIZAR_HORAFIN_GENERAL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."ACTUALIZAR_HORAFIN_GENERAL" 
/*---------------------------------------------------------------------------*/
/* Nombre    : ACTUALIZAR_HORAFIN_GENERAL                                    */
/* Objetivo  : Actualiza la hora fin en base a la hora inicio                */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_HORAINICIO   TABLA_MAESTRA.ID_CAMPO%TYPE,
    C_HORAFIN       OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_HORAFIN FOR
        SELECT ID_CAMPO, VALOR_CAMPO 
          FROM TABLA_MAESTRA 
         WHERE NOMBRE_TABLA = 'HORA' 
           AND NOMBRE_CAMPO = 'HORA_FIN'
           AND ID_CAMPO > ID_HORAINICIO;
    
END ACTUALIZAR_HORAFIN_GENERAL;

/
--------------------------------------------------------
--  DDL for Procedure ACTUALIZAR_OBSERVACION_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."ACTUALIZAR_OBSERVACION_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : ACTUALIZAR_OBSERVACION_TUTORIA                                */
/* Objetivo  : Actualizar las observaciones de las tutor?s                  */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA     TABLA_OBSERVACIONES.COD_TUTORIA%TYPE,
    SESION          TABLA_OBSERVACIONES.SESION_REG%TYPE,
    ID_OBSERVACION  TABLA_OBSERVACIONES.ID_OBSERVACION%TYPE,
    ESTADO_CONTROL  VARCHAR,  
    INDICADOR       INTEGER) AUTHID CURRENT_USER AS
    
    ANIO_AUX        TABLA_OBSERVACIONES.ANIO%TYPE;
    PERIODO_AUX     TABLA_OBSERVACIONES.PERIODO%TYPE; 
    ESTADO_OBS      TABLA_OBSERVACIONES.ESTADO_OBSERV%TYPE;
    
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;
    
    SELECT DECODE(ESTADO_CONTROL, 'PENDIENTE', 1, 'PARCIALMENTE LEVANTADO', 2, 'CERRADO', 3) 
      INTO ESTADO_OBS
      FROM DUAL; 
    
    IF INDICADOR = 1 THEN
        UPDATE TABLA_OBSERVACIONES
           SET ESTADO_OBSERV  = ACTUALIZAR_OBSERVACION_TUTORIA.ESTADO_OBS
         WHERE ANIO           = ACTUALIZAR_OBSERVACION_TUTORIA.ANIO_AUX
           AND PERIODO        = ACTUALIZAR_OBSERVACION_TUTORIA.PERIODO_AUX
           AND COD_TUTORIA    = ACTUALIZAR_OBSERVACION_TUTORIA.COD_TUTORIA
           AND SESION_REG     = ACTUALIZAR_OBSERVACION_TUTORIA.SESION
           AND ID_OBSERVACION = ACTUALIZAR_OBSERVACION_TUTORIA.ID_OBSERVACION;
    ELSE
        UPDATE TABLA_OBSERVACIONES
           SET 
              
               FECHA_ENTREGA  = TO_CHAR(SYSDATE, 'DD/MM/YYYY') ,
               ESTADO_OBSERV = ACTUALIZAR_OBSERVACION_TUTORIA.ESTADO_OBS
         WHERE ANIO           = ACTUALIZAR_OBSERVACION_TUTORIA.ANIO_AUX
           AND PERIODO        = ACTUALIZAR_OBSERVACION_TUTORIA.PERIODO_AUX
           AND COD_TUTORIA    = ACTUALIZAR_OBSERVACION_TUTORIA.COD_TUTORIA
           AND SESION_REG     = ACTUALIZAR_OBSERVACION_TUTORIA.SESION
           AND ID_OBSERVACION = ACTUALIZAR_OBSERVACION_TUTORIA.ID_OBSERVACION;
    END IF;
    COMMIT;

END ACTUALIZAR_OBSERVACION_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure B_ASIST_ALUMNOS_TUT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."B_ASIST_ALUMNOS_TUT" 
/*---------------------------------------------------------------------------*/
/* Nombre    : B_ASIST_ALUMNOS_TUT                                           */
/* Objetivo  : Busca las asistencias de los alumnos a las tutor?s           */
/* Parametros: 1 - ANIO      : A? en que se realiza la tutor?              */
/*             2 - PERIODO   : Semestre donde se realiza la tutor?          */
/*             3 - CODIGO    : C?igo del curso                              */
/*             4 - DIA       : D? de la tutor?                             */
/*             5 - FECHA     : Fecha de la tutor?                           */
/*                                                                           */
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO        VARCHAR,
    PERIODO     VARCHAR,
    CODIGO      VARCHAR,
    DIA         VARCHAR,
    FECHA       VARCHAR,
    ASIST_CUR   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN

    OPEN ASIST_CUR FOR    
        SELECT ALU.A_CODIGO, ALU.A_NOMBRE, ALU.A_APELLIDOS, TUT.HORA_INI, TUT.HORA_FIN, 
               CUR.NOMBRE, ASIS.FECHA_T, ASIS.ASISTENCIA_T, ASIS.OBSERVACION_T 
          FROM TUTORIA TUT, PROFESOR PRO, ASISTENCIA_T_ALUM ASIS, ALUMNO ALU, CURSO CUR
         WHERE PRO.P_CODIGO = TUT.P_CODIGO
           AND ASIS.T_ANIO = TUT.T_ANIO
           AND ASIS.T_PERIODO = TUT.T_PERIODO
           AND ASIS.T_CODIGO = TUT.T_CODIGO
           AND ALU.A_CODIGO = TUT.A_CODIGO
           AND CUR.C_CODIGO = TUT.C_CODIGO
           AND TUT.T_ANIO = ANIO
           AND TUT.T_PERIODO = PERIODO
           AND TUT.C_CODIGO = CODIGO
           AND TRIM(TUT.DIA) = DIA
           AND TO_CHAR(ASIS.FECHA_T,'dd/mm/yyyy') = FECHA;
       
END B_ASIST_ALUMNOS_TUT;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_CICLO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_CICLO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_CICLO                                                  */
/* Objetivo  : Busca el a? y el periodo del ciclo acad?ico                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_CICLO  CICLO.ID_CICLO%TYPE,    
    C_CICLO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
BEGIN        
    OPEN C_CICLO FOR
        SELECT ANIO, PERIODO      
          FROM CICLO
         WHERE ID_CICLO = BUSCAR_CICLO.ID_CICLO;
       
END BUSCAR_CICLO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_CICLOACADEMICO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_CICLOACADEMICO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_CICLOACADEMICO                                         */
/* Objetivo  : Busca el ciclo acad?ico en base a el c?igo del ciclo        */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_CICLO        CICLO.ID_CICLO%TYPE,
    CICLO           OUT VARCHAR) AUTHID CURRENT_USER AS

    ANIO_AUX       HISTORIAL_NOTAS.ANIO%TYPE;
    PERIODO_AUX    HISTORIAL_NOTAS.PERIODO%TYPE;

BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ID_CICLO = BUSCAR_CICLOACADEMICO.ID_CICLO;
     
     CICLO := CAST(ANIO_AUX AS VARCHAR) || '-' || CAST(PERIODO_AUX AS VARCHAR); 
       
END BUSCAR_CICLOACADEMICO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_CICLOACTIVO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_CICLOACTIVO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_CICLOACTIVO                                            */
/* Objetivo  : Busca el a? y el periodo del ciclo acad?ico vigente         */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/
   (C_CICLO OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN        
    OPEN C_CICLO FOR
        SELECT ANIO, PERIODO      
          FROM CICLO
         WHERE ACTIVO = 1;   
END BUSCAR_CICLOACTIVO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_CICLOACTUAL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_CICLOACTUAL" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_CICLOACTUAL                                            */
/* Objetivo  : Busca el a? y el periodo del ciclo acad?ico vigente         */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_CICLO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
BEGIN        
    OPEN C_CICLO FOR
        SELECT ANIO, PERIODO      
          FROM CICLO
         WHERE ACTIVO = 1;
       
END BUSCAR_CICLOACTUAL;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DATOSALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DATOSALUMNO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DATOSALUMNO                                            */
/* Objetivo  : Busca los datos del alumno en base a su c?igo                */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_ALUMNO    ALUMNO.A_CODIGO%TYPE,
    C_ALUMNO      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
BEGIN        
    OPEN C_ALUMNO FOR
        SELECT A_CODIGO, UPPER(TRIM(A_NOMBRE) || ' ' || TRIM(A_APELLIDOS)) AS A_NOMBRE, 
               A_FNACIMIENTO, A_DIRECCION, A_EMAIL, A_TELEFONO, A_DNI 
          FROM ALUMNO
         WHERE A_CODIGO = BUSCAR_DATOSALUMNO.COD_ALUMNO;
    
END BUSCAR_DATOSALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DATOSALUMNOTUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DATOSALUMNOTUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DATOSALUMNOTUTORIA                                     */
/* Objetivo  : Buscar si un alumno ya est?registrado en una tutor?         */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_ALUMNO    TUTORIA.A_CODIGO%TYPE,
    COD_DOCENTE   TUTORIA.P_CODIGO%TYPE,
    COD_CURSO     TUTORIA.C_CODIGO%TYPE,
    C_TUTORIA     OUT VARCHAR) AUTHID CURRENT_USER AS
    
BEGIN        
    SELECT T_CODIGO
      INTO C_TUTORIA
      FROM TUTORIA
     WHERE A_CODIGO = BUSCAR_DATOSALUMNOTUTORIA.COD_ALUMNO
       AND P_CODIGO = BUSCAR_DATOSALUMNOTUTORIA.COD_DOCENTE
       AND C_CODIGO = BUSCAR_DATOSALUMNOTUTORIA.COD_CURSO;
    
END BUSCAR_DATOSALUMNOTUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DATOS_AREACONOCIMIENTO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DATOS_AREACONOCIMIENTO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DATOS_AREACONOCIMIENTO                                 */
/* Objetivo  : Busca los datos del ?ea del conocimiento en base a su c?igo */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_AREA_CON   AREA_CONOCIMIENTO.COD_AREA%TYPE,
    C_AREA_CON     OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
BEGIN        
    OPEN C_AREA_CON FOR
        SELECT COD_AREA, NOMBRE_AREA               
          FROM AREA_CONOCIMIENTO
         WHERE COD_AREA = BUSCAR_DATOS_AREACONOCIMIENTO.COD_AREA_CON;
    
END BUSCAR_DATOS_AREACONOCIMIENTO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DATOS_CURSO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DATOS_CURSO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DATOS_CURSO                                            */
/* Objetivo  : Busca los datos del curso en base a su c?igo                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_CURSO    CURSO.C_CODIGO%TYPE,
    C_CURSO      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
BEGIN        
    OPEN C_CURSO FOR
        SELECT C_CODIGO, NOMBRE, CREDITOS, AREA_C              
          FROM CURSO
         WHERE C_CODIGO = BUSCAR_DATOS_CURSO.COD_CURSO;
    
END BUSCAR_DATOS_CURSO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DATOS_ENCUESTA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DATOS_ENCUESTA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DATOS_ENCUESTA                                         */
/* Objetivo  : Buscar los datos de una encuesta                              */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA     TUTORIA_ENCUESTA.COD_TUTORIA%TYPE,
    C_ENCUESTA      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX        TUTORIA_ENCUESTA.ANIO%TYPE;
    PERIODO_AUX     TUTORIA_ENCUESTA.PERIODO%TYPE;    
        
BEGIN
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_ENCUESTA FOR     
        SELECT TUTE.ANIO  , TUTE.PERIODO  , TUTE.COD_TUTORIA, TUTE.COD_PSICOLOGA, 
               TP.PREGUNTA, TUTE.RESPUESTA, TUTE.CALIF_TUTOR, TUTE.CALIF_PSICO,
               TUTE.ID_PREGUNTA
          FROM TUTORIA_ENCUESTA TUTE, TABLA_PREGUNTAS TP
         WHERE TUTE.ANIO = ANIO_AUX 
           AND TUTE.PERIODO = PERIODO_AUX   
           AND TUTE.ID_PREGUNTA = TP.ID_PREGUNTA
           AND COD_TUTORIA = BUSCAR_DATOS_ENCUESTA.COD_TUTORIA;
    
END BUSCAR_DATOS_ENCUESTA;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DATOSFRECUENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DATOSFRECUENCIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DATOSFRECUENCIA                                        */
/* Objetivo  : Busca los datos de la frecuencia en base a su c?igo          */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_FRECUENCIA  TABLA_MAESTRA.ID_CAMPO%TYPE,
    C_FRECUENCIA    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_FRECUENCIA FOR    
        SELECT ID_CAMPO, VALOR_CAMPO 
          FROM TABLA_MAESTRA 
         WHERE NOMBRE_TABLA = 'FRECUENCIA' 
           AND NOMBRE_CAMPO = 'FRECUENCIA_TUTORIA'
           AND ID_CAMPO = BUSCAR_DATOSFRECUENCIA.COD_FRECUENCIA;
       
END BUSCAR_DATOSFRECUENCIA;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DATOSTUTORIA_ALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DATOSTUTORIA_ALUMNO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DATOSTUTORIA_ALUMNO                                    */
/* Objetivo  : Buscar las sesiones de tutor? de alumnos                     */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_DOCENTE   USUARIO_ROL_EQUIVALENCIA.COD_USUARIO%TYPE,
    COD_CURSO     TUTORIA.C_CODIGO%TYPE,
    COD_ALUMNO    TUTORIA.A_CODIGO%TYPE,
    FECHA_TUT     VARCHAR,
    TIPO_ALUMNO   INTEGER,
    MODO          INTEGER,
    C_TUTORIA     OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    IF MODO = 1 THEN --MODO ADMIN
        OPEN C_TUTORIA FOR
            SELECT DISTINCT TUT.T_CODIGO, PTUT.SESION, ALU.A_CODIGO, UPPER(TRIM(ALU.A_NOMBRE)) AS A_NOMBRE, 
                            UPPER(TRIM(ALU.A_APELLIDOS)) AS A_APELLIDOS, TUT.C_CODIGO, TUT.P_CODIGO,
                            TRIM(PTUT.HORA_INI), TRIM(PTUT.HORA_FIN), PTUT.ESTADO
              FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT, ALUMNO ALU
             WHERE TUT.T_CODIGO = PTUT.C_TUTORIA 
               AND PTUT.C_TUTORIA IN (SELECT T.T_CODIGO 
                                        FROM TUTORIA T
                                       WHERE P_CODIGO   = BUSCAR_DATOSTUTORIA_ALUMNO.COD_DOCENTE                                      
                                         AND T.C_CODIGO = BUSCAR_DATOSTUTORIA_ALUMNO.COD_CURSO
                                         AND T.A_CODIGO = BUSCAR_DATOSTUTORIA_ALUMNO.COD_ALUMNO 
                                     )
               AND TUT.T_ANIO = ANIO_AUX 
               AND TUT.T_PERIODO = PERIODO_AUX
               AND TUT.A_CODIGO = ALU.A_CODIGO
               AND TUT.T_TIPO_ALUMNO = TIPO_ALUMNO
               AND PTUT.FECHA_TUT = BUSCAR_DATOSTUTORIA_ALUMNO.FECHA_TUT
             ORDER BY PTUT.SESION;
    ELSE
        OPEN C_TUTORIA FOR
            SELECT DISTINCT TUT.T_CODIGO, PTUT.SESION, ALU.A_CODIGO, UPPER(TRIM(ALU.A_NOMBRE)) AS A_NOMBRE, 
                            UPPER(TRIM(ALU.A_APELLIDOS)) AS A_APELLIDOS, TUT.C_CODIGO, TUT.P_CODIGO,
                            TRIM(PTUT.HORA_INI), TRIM(PTUT.HORA_FIN), PTUT.ESTADO
              FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT, ALUMNO ALU
             WHERE TUT.T_CODIGO = PTUT.C_TUTORIA 
               AND PTUT.C_TUTORIA IN (SELECT T.T_CODIGO 
                                        FROM TUTORIA T
                                       WHERE P_CODIGO IN (SELECT UE.COD_SISTEMA 
                                                            FROM USUARIO_ROL_EQUIVALENCIA UE
                                                           WHERE UE.COD_USUARIO = BUSCAR_DATOSTUTORIA_ALUMNO.COD_DOCENTE)                                         
                                         AND T.C_CODIGO = BUSCAR_DATOSTUTORIA_ALUMNO.COD_CURSO
                                         AND T.A_CODIGO = BUSCAR_DATOSTUTORIA_ALUMNO.COD_ALUMNO 
                                     )
               AND TUT.T_ANIO = ANIO_AUX 
               AND TUT.T_PERIODO = PERIODO_AUX
               AND TUT.A_CODIGO = ALU.A_CODIGO
               AND TUT.T_TIPO_ALUMNO = TIPO_ALUMNO
               AND PTUT.FECHA_TUT = BUSCAR_DATOSTUTORIA_ALUMNO.FECHA_TUT
             ORDER BY PTUT.SESION;
             
    END IF;
END BUSCAR_DATOSTUTORIA_ALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DOCENTECURSO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DOCENTECURSO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DOCENTECURSO                                           */
/* Objetivo  : Busca el docente asociado al curso para un periodo acad?ico  */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_CICLO         CICLO.ID_CICLO%TYPE,
    COD_CURSO        TUTORIA.C_CODIGO%TYPE,
    C_DOCENTECURSO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

    ANIO_AUX       HISTORIAL_NOTAS.ANIO%TYPE;
    PERIODO_AUX    HISTORIAL_NOTAS.PERIODO%TYPE;

BEGIN        
    IF ID_CICLO = 0 THEN
        SELECT ANIO, PERIODO
          INTO ANIO_AUX, PERIODO_AUX
          FROM CICLO
         WHERE ESTADO = 1;
    ELSE
        SELECT ANIO, PERIODO
          INTO ANIO_AUX, PERIODO_AUX
          FROM CICLO
         WHERE ID_CICLO = BUSCAR_DOCENTECURSO.ID_CICLO;
    END IF;
    
    OPEN C_DOCENTECURSO FOR
        SELECT DISTINCT UPPER(TRIM(PF.P_NOMBRE)) || ' ' || UPPER(TRIM(PF.P_APELLIDOS)), 
               PF.P_CODIGO 
          FROM TUTORIA TUT, PROFESOR PF 
         WHERE TUT.P_CODIGO  = PF.P_CODIGO 
           AND TUT.T_ANIO    = ANIO_AUX 
           AND TUT.T_PERIODO = PERIODO_AUX
           AND TUT.C_CODIGO  = COD_CURSO;
       
END BUSCAR_DOCENTECURSO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_DOCENTE_CURSO_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_DOCENTE_CURSO_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_DOCENTE_CURSO_REGULARES                                */
/* Objetivo  : Busca el docente asociado al curso en el proceso de tutor?   */
/*             regular                                                       */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_CURSO        TABLA_DISPONIBILIDAD.COD_CURSO%TYPE,
    C_DOCENTECURSO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

    ANIO_ACTUAL      CICLO.ANIO%TYPE;
    PERIODO_ACTUAL   CICLO.PERIODO%TYPE;

BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_ACTUAL, PERIODO_ACTUAL
      FROM CICLO
     WHERE ACTIVO = 1;
         
    OPEN C_DOCENTECURSO FOR
        SELECT DISTINCT UPPER(TRIM(PRO.P_NOMBRE)) || ' ' ||  UPPER(TRIM(PRO.P_APELLIDOS)),  TD.COD_USUARIO
          FROM TABLA_DISPONIBILIDAD TD, PROFESOR PRO
         WHERE TD.ANIO    = ANIO_ACTUAL
           AND TD.PERIODO = PERIODO_ACTUAL
           AND TD.COD_CURSO = BUSCAR_DOCENTE_CURSO_REGULARES.COD_CURSO
           AND TD.COD_USUARIO = PRO.P_CODIGO
           AND TD.TIPO_USUARIO = 1;
           
END BUSCAR_DOCENTE_CURSO_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_EXISTENCIA_OBSERVACION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_EXISTENCIA_OBSERVACION" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_EXISTENCIA_OBSERVACION                                 */
/* Objetivo  : Verificar que para una sesi? hay al menos una observaci?    */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA        TABLA_OBSERVACIONES.COD_TUTORIA%TYPE,
    SESION             TABLA_OBSERVACIONES.SESION_REG%TYPE,
    CONTADOR           OUT INTEGER) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    SELECT COUNT(ID_OBSERVACION)
      INTO CONTADOR
      FROM TABLA_OBSERVACIONES
     WHERE COD_TUTORIA = BUSCAR_EXISTENCIA_OBSERVACION.COD_TUTORIA
       AND SESION_REG  = BUSCAR_EXISTENCIA_OBSERVACION.SESION;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        CONTADOR := 0;              
END BUSCAR_EXISTENCIA_OBSERVACION;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_NOTASALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_NOTASALUMNO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_NOTASALUMNO                                            */
/* Objetivo  : Busca los registros de las notas                              */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_CICLO        CICLO.ID_CICLO%TYPE,
    COD_AUMNO       HISTORIAL_NOTAS.COD_ALUMNO%TYPE,   
    C_NOTASALUMNO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

    ANIO_AUX       HISTORIAL_NOTAS.ANIO%TYPE;
    PERIODO_AUX    HISTORIAL_NOTAS.PERIODO%TYPE;

BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ID_CICLO = BUSCAR_NOTASALUMNO.ID_CICLO;
     
    OPEN C_NOTASALUMNO FOR
        SELECT ANIO       , PERIODO   , PLAN    , COD_CURSO ,
               COD_ALUMNO , NOTA_FINAL, CREDITOS, NOM_ALUMNO,
               NOM_DOCENTE, NOM_CURSO
          FROM HISTORIAL_NOTAS
         WHERE ANIO = ANIO_AUX
           AND PERIODO = PERIODO_AUX
           AND COD_ALUMNO = BUSCAR_NOTASALUMNO.COD_AUMNO;   
       
END BUSCAR_NOTASALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_NOTASALUMNO_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_NOTASALUMNO_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_NOTASALUMNO_TUTORIA                                    */
/* Objetivo  : Busca los registros de las notas                              */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO            CICLO.ANIO%TYPE,
    PERIODO         CICLO.PERIODO%TYPE,
    COD_AUMNO       HISTORIAL_NOTAS.COD_ALUMNO%TYPE,   
    C_NOTASALUMNO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX       CICLO.ANIO%TYPE;
    PERIODO_AUX    CICLO.PERIODO%TYPE;

BEGIN               
    SELECT DECODE(PERIODO, 0, BUSCAR_NOTASALUMNO_TUTORIA.ANIO - 1, 
                           1, BUSCAR_NOTASALUMNO_TUTORIA.ANIO, 
                           2, BUSCAR_NOTASALUMNO_TUTORIA.ANIO)
             INTO ANIO_AUX
             FROM DUAL;
             
    SELECT DECODE(PERIODO, 0, 2, 1, 0, 2, 1)
      INTO PERIODO_AUX
      FROM DUAL;
    
    OPEN C_NOTASALUMNO FOR
        SELECT ANIO       , PERIODO   , PLAN    , COD_CURSO ,
               COD_ALUMNO , NOTA_FINAL, CREDITOS, NOM_ALUMNO,
               NOM_DOCENTE, NOM_CURSO
          FROM HISTORIAL_NOTAS
         WHERE ANIO = ANIO_AUX
           AND PERIODO = PERIODO_AUX
           AND COD_ALUMNO = BUSCAR_NOTASALUMNO_TUTORIA.COD_AUMNO;   
       
END BUSCAR_NOTASALUMNO_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_ROL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_ROL" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_ROL                                                    */
/* Objetivo  : Busca los roles registrados                                   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (PROCESO_TUTORIA     INTEGER,
    C_ROL               OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN 
     IF PROCESO_TUTORIA = 1 THEN
        OPEN C_ROL FOR
            SELECT ID_ROL, DESC_ROL
              FROM ROL
             WHERE ID_ROL BETWEEN 1 AND 5 OR ID_ROL = 12
          ORDER BY ID_ROL;
     ELSE
        IF PROCESO_TUTORIA = 2 THEN
            OPEN C_ROL FOR
                SELECT ID_ROL, DESC_ROL
                  FROM ROL
                 WHERE ID_ROL BETWEEN 6 AND 11 OR ID_ROL = 13
              ORDER BY ID_ROL;
        END IF;     
     END IF;
       
END BUSCAR_ROL;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_SESION_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_SESION_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_SESION_TUTORIA                                         */
/* Objetivo  : Buscar una sesi? espec?ica dentro de una tutor?            */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO               PROGRAMACION_TUTORIA.ANIO%TYPE,
    PERIODO            PROGRAMACION_TUTORIA.PERIODO%TYPE,    
    COD_CURSO          TUTORIA.C_CODIGO%TYPE,
    COD_TUTOR          TUTORIA.P_CODIGO%TYPE,
    COD_ALUMNO         TUTORIA.A_CODIGO%TYPE,
    SESION             PROGRAMACION_TUTORIA.SESION%TYPE,
    MODO               INTEGER,
    TIPO_ALUMNO        INTEGER,
    C_SESIONTUTORIA    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    IF MODO = 1 THEN --MODO ADMIN
        OPEN C_SESIONTUTORIA FOR
            SELECT PRGT.ANIO     , PRGT.PERIODO       , PRGT.C_TUTORIA     , PRGT.SESION, 
                   PRGT.FECHA_TUT, TRIM(PRGT.HORA_INI), TRIM(PRGT.HORA_FIN), PRGT.ESTADO
              FROM PROGRAMACION_TUTORIA PRGT
             WHERE C_TUTORIA IN (SELECT TRIM(TUT.T_CODIGO) 
                                   FROM TUTORIA TUT
                                  WHERE TUT.T_ANIO    = BUSCAR_SESION_TUTORIA.ANIO 
                                    AND TUT.T_PERIODO = BUSCAR_SESION_TUTORIA.PERIODO 
                                    AND TUT.C_CODIGO  = TRIM(BUSCAR_SESION_TUTORIA.COD_CURSO)
                                    AND TUT.P_CODIGO  = TRIM(BUSCAR_SESION_TUTORIA.COD_TUTOR)
                                    AND TUT.A_CODIGO  = TRIM(BUSCAR_SESION_TUTORIA.COD_ALUMNO)
                                    AND TUT.T_TIPO_ALUMNO = BUSCAR_SESION_TUTORIA.TIPO_ALUMNO)
              AND PRGT.ANIO    = BUSCAR_SESION_TUTORIA.ANIO
              AND PRGT.PERIODO = BUSCAR_SESION_TUTORIA.PERIODO
              AND PRGT.SESION  = BUSCAR_SESION_TUTORIA.SESION ;
    ELSE
        IF MODO = 2 THEN --MODO TUTOR
            OPEN C_SESIONTUTORIA FOR
            SELECT PRGT.ANIO     , PRGT.PERIODO , PRGT.C_TUTORIA, PRGT.SESION, 
                   PRGT.FECHA_TUT, PRGT.HORA_INI, PRGT.HORA_FIN , PRGT.ESTADO
              FROM PROGRAMACION_TUTORIA PRGT
             WHERE C_TUTORIA IN (SELECT TRIM(TUT.T_CODIGO)
                                   FROM TUTORIA TUT
                                  WHERE TUT.T_ANIO    = BUSCAR_SESION_TUTORIA.ANIO 
                                    AND TUT.T_PERIODO = BUSCAR_SESION_TUTORIA.PERIODO 
                                    AND TUT.C_CODIGO  = TRIM(BUSCAR_SESION_TUTORIA.COD_CURSO)
                                    AND TUT.P_CODIGO IN (SELECT COD_SISTEMA  
                                                           FROM USUARIO_ROL_EQUIVALENCIA
                                                          WHERE COD_USUARIO = BUSCAR_SESION_TUTORIA.COD_TUTOR)
                                    AND TUT.A_CODIGO  = TRIM(BUSCAR_SESION_TUTORIA.COD_ALUMNO)
                                    AND TUT.T_TIPO_ALUMNO = BUSCAR_SESION_TUTORIA.TIPO_ALUMNO)
              AND PRGT.ANIO    = BUSCAR_SESION_TUTORIA.ANIO
              AND PRGT.PERIODO = BUSCAR_SESION_TUTORIA.PERIODO
              AND PRGT.SESION  = BUSCAR_SESION_TUTORIA.SESION ;
        END IF;        
    END IF;  
    
END BUSCAR_SESION_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_TUTORIA                                                */
/* Objetivo  : Buscar si una tutor? ya existe                               */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,
    COD_CURSO      TUTORIA.C_CODIGO%TYPE,
    COD_ALUMNO     TUTORIA.A_CODIGO%TYPE,
    COD_DOCENTE    TUTORIA.P_CODIGO%TYPE,
    COD_TUTORIA    OUT VARCHAR) AUTHID CURRENT_USER AS
    
    COD_TUTORIA_AUX  VARCHAR(20) := '';    
    
BEGIN        
    SELECT T_CODIGO
      INTO COD_TUTORIA_AUX
      FROM TUTORIA
     WHERE T_ANIO = BUSCAR_TUTORIA.ANIO
       AND PERIODO = BUSCAR_TUTORIA.PERIODO
       AND C_CODIGO = BUSCAR_TUTORIA.COD_CURSO
       AND A_CODIGO = BUSCAR_TUTORIA.COD_ALUMNO
       AND P_CODIGO = BUSCAR_TUTORIA.COD_DOCENTE;
       
    COD_TUTORIA := TRIM(COD_TUTORIA_AUX);
       
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        COD_TUTORIA := '';
END BUSCAR_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_TUTORIA_ALUMNO_REG
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_TUTORIA_ALUMNO_REG" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_TUTORIA_ALUMNO_REG                                     */
/* Objetivo  : Buscar una tutor? seg? el codigo del alumno                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_ALUMNO     ALUMNO.A_CODIGO%TYPE,    
    TUTORIA_C      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX           TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX        TUTORIA.T_PERIODO%TYPE;
    COD_SISTEMA_AUX    USUARIO_ROL_EQUIVALENCIA.COD_SISTEMA%TYPE;
        
BEGIN
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;
    
    OPEN TUTORIA_C FOR     
        SELECT T_ANIO  , T_PERIODO, T_CODIGO     , A_CODIGO, 
               P_CODIGO, C_CODIGO , C_REPITENCIAS, T_DIA
          FROM TUTORIA
         WHERE T_ANIO        = ANIO_AUX
           AND T_PERIODO     = PERIODO_AUX
           AND A_CODIGO      = BUSCAR_TUTORIA_ALUMNO_REG.COD_ALUMNO
           AND T_TIPO_ALUMNO = 2;
               
END BUSCAR_TUTORIA_ALUMNO_REG;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_TUTORIA_ENCUESTA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_TUTORIA_ENCUESTA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_TUTORIA_ENCUESTA                                       */
/* Objetivo  : Buscar si una encuesta est?asociada a una tutor?            */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA        TUTORIA_ENCUESTA.COD_TUTORIA%TYPE,
    TUT_ENCUESTA       OUT VARCHAR) AUTHID CURRENT_USER AS
    
    ANIO_AUX           TUTORIA_ENCUESTA.ANIO%TYPE;
    PERIODO_AUX        TUTORIA_ENCUESTA.PERIODO%TYPE;    
        
BEGIN
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    SELECT DISTINCT COD_TUTORIA
      INTO TUT_ENCUESTA
      FROM TUTORIA_ENCUESTA
     WHERE ANIO        = ANIO_AUX
       AND PERIODO     = PERIODO_AUX
       AND COD_TUTORIA = BUSCAR_TUTORIA_ENCUESTA.COD_TUTORIA;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        TUT_ENCUESTA := '';
END BUSCAR_TUTORIA_ENCUESTA;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_TUTORIAXCODIGO_ALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_TUTORIAXCODIGO_ALUMNO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_TUTORIAXCODIGO_ALUMNO                                  */
/* Objetivo  : Buscar una tutor? seg? el codigo del alumno                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_ALUMNO     ALUMNO.A_CODIGO%TYPE,
    TIPO_ALUMNO    TUTORIA.T_TIPO_ALUMNO%TYPE,    
    TUTORIA_C      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX           TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX        TUTORIA.T_PERIODO%TYPE;
    COD_SISTEMA_AUX    USUARIO_ROL_EQUIVALENCIA.COD_SISTEMA%TYPE;
        
BEGIN
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;
    
    IF TIPO_ALUMNO = 1 THEN --ALUMNO OBSERVADO 
        OPEN TUTORIA_C FOR     
            SELECT T_ANIO  , T_PERIODO, T_CODIGO     , A_CODIGO, 
                   P_CODIGO, C_CODIGO , C_REPITENCIAS, T_DIA
              FROM TUTORIA
             WHERE T_ANIO        = ANIO_AUX
               AND T_PERIODO     = PERIODO_AUX
               AND A_CODIGO      = BUSCAR_TUTORIAXCODIGO_ALUMNO.COD_ALUMNO
               AND T_TIPO_ALUMNO = BUSCAR_TUTORIAXCODIGO_ALUMNO.TIPO_ALUMNO;
    ELSE --ALUMNO REGULAR
        SELECT COD_SISTEMA
          INTO COD_SISTEMA_AUX
          FROM USUARIO_ROL_EQUIVALENCIA
         WHERE COD_USUARIO = COD_ALUMNO;
         
        OPEN TUTORIA_C FOR     
            SELECT T_ANIO  , T_PERIODO, T_CODIGO     , A_CODIGO, 
                   P_CODIGO, C_CODIGO , C_REPITENCIAS, T_DIA
              FROM TUTORIA
             WHERE T_ANIO        = ANIO_AUX
               AND T_PERIODO     = PERIODO_AUX
               AND A_CODIGO      = BUSCAR_TUTORIAXCODIGO_ALUMNO.COD_SISTEMA_AUX
               AND T_TIPO_ALUMNO = BUSCAR_TUTORIAXCODIGO_ALUMNO.TIPO_ALUMNO;
    END IF;
    
END BUSCAR_TUTORIAXCODIGO_ALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_ULTIMA_SESION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_ULTIMA_SESION" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_ULTIMA_SESION                                          */
/* Objetivo  : Busca la ?tima sesi? de la tutor?                          */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA        PROGRAMACION_TUTORIA.C_TUTORIA%TYPE,    
    ULTIMA_SESION      OUT INTEGER) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    SELECT MAX(SESION)
      INTO ULTIMA_SESION 
      FROM PROGRAMACION_TUTORIA 
     WHERE ANIO      = BUSCAR_ULTIMA_SESION.ANIO_AUX
       AND PERIODO   = BUSCAR_ULTIMA_SESION.PERIODO_AUX 
       AND C_TUTORIA = BUSCAR_ULTIMA_SESION.COD_TUTORIA;
           
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ULTIMA_SESION := 0;
END BUSCAR_ULTIMA_SESION;

/
--------------------------------------------------------
--  DDL for Procedure BUSCAR_USUARIO_EQUIVALENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."BUSCAR_USUARIO_EQUIVALENCIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : BUSCAR_USUARIO_EQUIVALENCIA                                   */
/* Objetivo  : Busca el c?igo del sistema de un usuario                     */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_USUARIO        USUARIO_ROL_EQUIVALENCIA.COD_USUARIO%TYPE,
    CODIGO_SISTEMA     OUT VARCHAR) AUTHID CURRENT_USER AS

BEGIN 
    SELECT COD_SISTEMA
      INTO CODIGO_SISTEMA
      FROM USUARIO_ROL_EQUIVALENCIA
     WHERE COD_USUARIO = BUSCAR_USUARIO_EQUIVALENCIA.COD_USUARIO;
     
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        CODIGO_SISTEMA := '';        
END BUSCAR_USUARIO_EQUIVALENCIA;

/
--------------------------------------------------------
--  DDL for Procedure CREAR_USUARIO_OBSERVADOS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."CREAR_USUARIO_OBSERVADOS" 
/*---------------------------------------------------------------------------*/
/* Nombre    : CREAR_USUARIO_OBSERVADOS                                      */
/* Objetivo  : Registra un usuario en el sistema                             */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (NUEVO_USUARIO     USUARIO.ID_USUARIO%TYPE,
    CONTRASENIA       USUARIO.CLAVE%TYPE,   
    APE_PATERNO       USUARIO.PATERNO%TYPE,
    APE_MATERNO       USUARIO.MATERNO%TYPE,
    NOMBRES           USUARIO.NOMBRES%TYPE,
    CORREO            USUARIO.PATERNO%TYPE,
    DIRECCION         USUARIO.DIRECCION%TYPE,
    TELEFONO          USUARIO.TELEFONO%TYPE,
    ID_ROL            ROL.ID_ROL%TYPE) AUTHID CURRENT_USER AS

    ROL_USUARIO_AUX   ROL.ROL_USUARIO%TYPE;    
    CONTRASENIA_SH1   VARCHAR(100);
    COD_PROFESOR_NEW  PROFESOR.P_CODIGO%TYPE;
    USUARIO_AUX       USUARIO.ID_USUARIO%TYPE;
    COD_DOCENTE_AUX   PROFESOR.P_CODIGO%TYPE;

BEGIN        
    SELECT ROL_USUARIO
      INTO ROL_USUARIO_AUX
      FROM ROL
     WHERE ID_ROL = CREAR_USUARIO_OBSERVADOS.ID_ROL;
     
    ENCRIPTAR_CONTRASENIA(CONTRASENIA     => CONTRASENIA,
                          CONTRASENIA_SH1 => CONTRASENIA_SH1);
        
    VALIDAR_EXISTENCIA_USUARIO(COD_USUARIO     => CREAR_USUARIO_OBSERVADOS.NUEVO_USUARIO,
                               COD_USUARIO_VAL => CREAR_USUARIO_OBSERVADOS.USUARIO_AUX);
     
    IF USUARIO_AUX = '' OR USUARIO_AUX IS NULL THEN
        INSERTAR_USUARIO(ID_USUARIO  => CREAR_USUARIO_OBSERVADOS.NUEVO_USUARIO  ,
                         CLAVE       => CREAR_USUARIO_OBSERVADOS.CONTRASENIA_SH1,
                         NOMBRES     => CREAR_USUARIO_OBSERVADOS.NOMBRES,
                         MATERNO     => CREAR_USUARIO_OBSERVADOS.APE_MATERNO,
                         PATERNO     => CREAR_USUARIO_OBSERVADOS.APE_PATERNO,
                         CORREO      => CREAR_USUARIO_OBSERVADOS.CORREO,
                         DIRECCION   => CREAR_USUARIO_OBSERVADOS.DIRECCION,
                         TELEFONO    => CREAR_USUARIO_OBSERVADOS.TELEFONO,
                         ESTADO      => 'A');
                     
        INSERTAR_USUARIO_ROL(ID_ROL     => CREAR_USUARIO_OBSERVADOS.ID_ROL,
                             ID_USUARIO => CREAR_USUARIO_OBSERVADOS.NUEVO_USUARIO,
                             ROL_DESC   => CREAR_USUARIO_OBSERVADOS.ROL_USUARIO_AUX);
                                    
        IF ID_ROL = 5 THEN
            GENERAR_CODIGO_PROFESOR(COD_PROFESOR => COD_PROFESOR_NEW);
                                         
            VALIDAR_EXISTENCIA_DOCENTE(COD_DOCENTE     => COD_PROFESOR_NEW,
                                       COD_DOCENTE_VAL => COD_DOCENTE_AUX);
                                       
            IF COD_DOCENTE_AUX IS NULL THEN
                INSERTAR_PROFESOR (P_CODIGO      => CREAR_USUARIO_OBSERVADOS.COD_PROFESOR_NEW,
                                   P_NOMBRE      => CREAR_USUARIO_OBSERVADOS.NOMBRES,
                                   P_APELLIDOS   => CREAR_USUARIO_OBSERVADOS.APE_PATERNO || ' ' ||
                                                    CREAR_USUARIO_OBSERVADOS.APE_MATERNO,
                                   P_FNACIMIENTO => NULL,
                                   P_DIRECCION   => CREAR_USUARIO_OBSERVADOS.DIRECCION,
                                   P_EMAIL       => CREAR_USUARIO_OBSERVADOS.CORREO,
                                   P_TELEFONO    => CREAR_USUARIO_OBSERVADOS.TELEFONO,
                                   P_DNI         => 0);
                                   
                INS_USUARIO_ROL_EQUIVALENCIA(COD_USUARIO => CREAR_USUARIO_OBSERVADOS.NUEVO_USUARIO,
                                             ROL_USUARIO => CREAR_USUARIO_OBSERVADOS.ROL_USUARIO_AUX,
                                             COD_SISTEMA => CREAR_USUARIO_OBSERVADOS.COD_PROFESOR_NEW);                
            END IF;            
        END IF;
    END IF;
     
    COMMIT;
                                    
END CREAR_USUARIO_OBSERVADOS;

/
--------------------------------------------------------
--  DDL for Procedure CREAR_USUARIO_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."CREAR_USUARIO_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : CREAR_USUARIO_REGULARES                                       */
/* Objetivo  : Registra un usuario regular en el sistema                     */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (NUEVO_USUARIO     USUARIO.ID_USUARIO%TYPE,
    CONTRASENIA       USUARIO.CLAVE%TYPE,   
    APE_PATERNO       USUARIO.PATERNO%TYPE,
    APE_MATERNO       USUARIO.MATERNO%TYPE,
    NOMBRES           USUARIO.NOMBRES%TYPE,
    CORREO            USUARIO.PATERNO%TYPE,
    DIRECCION         USUARIO.DIRECCION%TYPE,
    TELEFONO          USUARIO.TELEFONO%TYPE,
    ID_ROL            ROL.ID_ROL%TYPE,
    COD_ALUMNO        ALUMNO.A_CODIGO%TYPE,
    PLAN_ALUMNO       ALUMNO.A_PLAN%TYPE) AUTHID CURRENT_USER AS

    ROL_USUARIO_AUX   ROL.ROL_USUARIO%TYPE;    
    CONTRASENIA_SH1   VARCHAR(100);
    COD_PROFESOR_NEW  PROFESOR.P_CODIGO%TYPE;
    USUARIO_AUX       USUARIO.ID_USUARIO%TYPE;
    COD_DOCENTE_AUX   PROFESOR.P_CODIGO%TYPE;
    COD_ALUMNO_AUX    ALUMNO.A_CODIGO%TYPE;
    ANIO_ACTUAL       CICLO.ANIO%TYPE;
    PERIODO_ACTUAL    CICLO.PERIODO%TYPE;
    
BEGIN        
    SELECT ROL_USUARIO
      INTO ROL_USUARIO_AUX
      FROM ROL
     WHERE ID_ROL = CREAR_USUARIO_REGULARES.ID_ROL;
             
    SELECT ANIO, PERIODO
      INTO ANIO_ACTUAL, PERIODO_ACTUAL
      FROM CICLO
     WHERE ACTIVO = 1;
    
    ENCRIPTAR_CONTRASENIA(CONTRASENIA     => CONTRASENIA,
                          CONTRASENIA_SH1 => CONTRASENIA_SH1);
                
    VALIDAR_EXISTENCIA_USUARIO(COD_USUARIO     => CREAR_USUARIO_REGULARES.NUEVO_USUARIO,
                               COD_USUARIO_VAL => CREAR_USUARIO_REGULARES.USUARIO_AUX);
             
    IF USUARIO_AUX = '' OR USUARIO_AUX IS NULL THEN
        INSERTAR_USUARIO(ID_USUARIO  => CREAR_USUARIO_REGULARES.NUEVO_USUARIO,
                         CLAVE       => CREAR_USUARIO_REGULARES.CONTRASENIA_SH1,
                         NOMBRES     => CREAR_USUARIO_REGULARES.NOMBRES,
                         MATERNO     => CREAR_USUARIO_REGULARES.APE_MATERNO,
                         PATERNO     => CREAR_USUARIO_REGULARES.APE_PATERNO,
                         CORREO      => CREAR_USUARIO_REGULARES.CORREO,
                         DIRECCION   => CREAR_USUARIO_REGULARES.DIRECCION,
                         TELEFONO    => CREAR_USUARIO_REGULARES.TELEFONO,
                         ESTADO      => 'A');
                             
        INSERTAR_USUARIO_ROL(ID_ROL     => CREAR_USUARIO_REGULARES.ID_ROL,
                             ID_USUARIO => CREAR_USUARIO_REGULARES.NUEVO_USUARIO,
                             ROL_DESC   => CREAR_USUARIO_REGULARES.ROL_USUARIO_AUX);
                                            
        IF ID_ROL = 10 THEN
            GENERAR_CODIGO_PROFESOR(COD_PROFESOR => COD_PROFESOR_NEW);
                                         
            VALIDAR_EXISTENCIA_DOCENTE(COD_DOCENTE     => COD_PROFESOR_NEW,
                                       COD_DOCENTE_VAL => COD_DOCENTE_AUX);
                                       
            IF COD_DOCENTE_AUX IS NULL THEN
                INSERTAR_PROFESOR (P_CODIGO      => CREAR_USUARIO_REGULARES.COD_PROFESOR_NEW,
                                   P_NOMBRE      => CREAR_USUARIO_REGULARES.NOMBRES,
                                   P_APELLIDOS   => CREAR_USUARIO_REGULARES.APE_PATERNO || ' ' ||
                                                    CREAR_USUARIO_REGULARES.APE_MATERNO,
                                   P_FNACIMIENTO => NULL,
                                   P_DIRECCION   => CREAR_USUARIO_REGULARES.DIRECCION,
                                   P_EMAIL       => CREAR_USUARIO_REGULARES.CORREO,
                                   P_TELEFONO    => CREAR_USUARIO_REGULARES.TELEFONO,
                                   P_DNI         => 0);
                                   
                INS_USUARIO_ROL_EQUIVALENCIA(COD_USUARIO => CREAR_USUARIO_REGULARES.NUEVO_USUARIO,
                                             ROL_USUARIO => CREAR_USUARIO_REGULARES.ROL_USUARIO_AUX,
                                             COD_SISTEMA => CREAR_USUARIO_REGULARES.COD_PROFESOR_NEW);
                                             
                INSERTAR_DOCENTE_REGULAR(ANIO => ANIO_ACTUAL,
                                         PERIODO => PERIODO_ACTUAL,
                                         COD_DOCENTE => CREAR_USUARIO_REGULARES.COD_PROFESOR_NEW,
                                         NOM_DOCENTE => CREAR_USUARIO_REGULARES.NOMBRES || ' ' ||
                                                        CREAR_USUARIO_REGULARES.APE_PATERNO || ' ' ||
                                                        CREAR_USUARIO_REGULARES.APE_MATERNO);
            ELSE
                INSERTAR_DOCENTE_REGULAR(ANIO => ANIO_ACTUAL,
                                         PERIODO => PERIODO_ACTUAL,
                                         COD_DOCENTE => CREAR_USUARIO_REGULARES.COD_PROFESOR_NEW,
                                         NOM_DOCENTE => CREAR_USUARIO_REGULARES.NOMBRES || ' ' ||
                                                        CREAR_USUARIO_REGULARES.APE_PATERNO || ' ' ||
                                                        CREAR_USUARIO_REGULARES.APE_MATERNO);
            END IF;
        END IF;
                
        IF ID_ROL = 11 THEN                  
            VALIDAR_EXISTENCIA_ALUMNO(COD_ALUMNO     => CREAR_USUARIO_REGULARES.COD_ALUMNO,
                                      COD_ALUMNO_VAL => CREAR_USUARIO_REGULARES.COD_ALUMNO_AUX);
            
            IF COD_ALUMNO_AUX IS NULL THEN 
                INSERTAR_ALUMNO (A_CODIGO      => CREAR_USUARIO_REGULARES.COD_ALUMNO,
                                 A_NOMBRE      => CREAR_USUARIO_REGULARES.NOMBRES,
                                 A_APELLIDOS   => TRIM(CREAR_USUARIO_REGULARES.APE_PATERNO) || ' ' ||
                                                  TRIM(CREAR_USUARIO_REGULARES.APE_MATERNO),
                                 A_FNACIMIENTO => NULL,
                                 A_DIRECCION   => CREAR_USUARIO_REGULARES.DIRECCION,
                                 A_EMAIL       => CREAR_USUARIO_REGULARES.CORREO,
                                 A_TELEFONO    => CREAR_USUARIO_REGULARES.TELEFONO,
                                 A_DNI         => 0,
                                 A_PLAN        => CREAR_USUARIO_REGULARES.PLAN_ALUMNO);
            
                INS_USUARIO_ROL_EQUIVALENCIA(COD_USUARIO => CREAR_USUARIO_REGULARES.NUEVO_USUARIO,
                                             ROL_USUARIO => CREAR_USUARIO_REGULARES.ROL_USUARIO_AUX,
                                             COD_SISTEMA => CREAR_USUARIO_REGULARES.COD_ALUMNO);
                                         
                INSERTAR_ALUMNO_REGULAR(ANIO => ANIO_ACTUAL,
                                        PERIODO => PERIODO_ACTUAL,
                                        COD_ALUMNO => CREAR_USUARIO_REGULARES.COD_ALUMNO,
                                        NOM_ALUMNO => CREAR_USUARIO_REGULARES.NOMBRES || ' ' ||
                                                      CREAR_USUARIO_REGULARES.APE_PATERNO || ' ' ||
                                                      CREAR_USUARIO_REGULARES.APE_MATERNO);
            ELSE
                INSERTAR_ALUMNO_REGULAR(ANIO => ANIO_ACTUAL,
                                        PERIODO => PERIODO_ACTUAL,
                                        COD_ALUMNO => CREAR_USUARIO_REGULARES.COD_ALUMNO,
                                        NOM_ALUMNO => CREAR_USUARIO_REGULARES.NOMBRES || ' ' ||
                                                      CREAR_USUARIO_REGULARES.APE_PATERNO || ' ' ||
                                                      CREAR_USUARIO_REGULARES.APE_MATERNO);
            END IF;  
        END IF;
    END IF;
             
    COMMIT;
                                            
END CREAR_USUARIO_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure ENCRIPTAR_CONTRASENIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."ENCRIPTAR_CONTRASENIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : ENCRIPTAR_CONTRASENIA                                         */
/* Objetivo  : Encripta una contrase? y la devuelve como cadena             */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (CONTRASENIA       USUARIO.CLAVE%TYPE,
    CONTRASENIA_SH1   OUT USUARIO.CLAVE%TYPE) AUTHID CURRENT_USER AS

    CONTRASENIA_HASH_SH1 RAW(100);
    CONTRASENIA_VARCHAR  VARCHAR(100);    

BEGIN        
    CONTRASENIA_HASH_SH1 := DBMS_CRYPTO.HASH(SRC => UTL_RAW.CAST_TO_RAW (CONTRASENIA),
                                             TYP => DBMS_CRYPTO.HASH_SH1);
                                             
    SELECT LOWER (TO_CHAR (RAWTOHEX (CONTRASENIA_HASH_SH1)))
      INTO CONTRASENIA_VARCHAR
     FROM DUAL;
     
     CONTRASENIA_SH1 := CONTRASENIA_VARCHAR;
                                    
END ENCRIPTAR_CONTRASENIA;

/
--------------------------------------------------------
--  DDL for Procedure GENERAR_CODIGO_DISPONIBILIDAD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GENERAR_CODIGO_DISPONIBILIDAD" 
/*---------------------------------------------------------------------------*/
/* Nombre    : GENERAR_CODIGO_DISPONIBILIDAD                                 */
/* Objetivo  : Generar un nuevo c?igo de disponibilidad de usuario          */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TABLA_DISPONIBILIDAD.ANIO%TYPE,
    PERIODO        TABLA_DISPONIBILIDAD.PERIODO%TYPE,
    COD_DISP       OUT TABLA_DISPONIBILIDAD.COD_DISP%TYPE) AUTHID CURRENT_USER AS
    
BEGIN        
    SELECT 'D'||TRIM(TO_CHAR(CODIGO,'000000000'))
      INTO COD_DISP
      FROM (SELECT TO_NUMBER(SUBSTR(TD.COD_DISP, 2)) + 1  AS CODIGO 
              FROM TABLA_DISPONIBILIDAD TD 
             WHERE TD.ANIO = ANIO 
               AND TD.PERIODO = PERIODO
             ORDER BY SUBSTR(TD.COD_DISP,2) DESC)
     WHERE ROWNUM <=1;       
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        COD_DISP := 'D000000001';
END GENERAR_CODIGO_DISPONIBILIDAD;

/
--------------------------------------------------------
--  DDL for Procedure GENERAR_CODIGO_PROFESOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GENERAR_CODIGO_PROFESOR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : GENERAR_CODIGO_PROFESOR                                       */
/* Objetivo  : Generar un nuevo c?igo de profesor                           */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_PROFESOR   OUT PROFESOR.P_CODIGO%TYPE) AUTHID CURRENT_USER AS
    
BEGIN        
    SELECT 'PR'||TRIM(TO_CHAR(CODIGO,'000000'))
      INTO COD_PROFESOR
      FROM (SELECT TO_NUMBER(SUBSTR(P.P_CODIGO, 3)) + 1  AS CODIGO 
              FROM PROFESOR P             
             ORDER BY SUBSTR(P.P_CODIGO,3) DESC)
     WHERE ROWNUM <=1;
       
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        COD_PROFESOR := 'PR000001';
END GENERAR_CODIGO_PROFESOR;

/
--------------------------------------------------------
--  DDL for Procedure GENERAR_ID_OBSERVACION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GENERAR_ID_OBSERVACION" 
/*---------------------------------------------------------------------------*/
/* Nombre    : GENERAR_ID_OBSERVACION                                        */
/* Objetivo  : Generar un nuevo identificador de observaci?                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO            TABLA_OBSERVACIONES.ANIO%TYPE,
    PERIODO         TABLA_OBSERVACIONES.PERIODO%TYPE,
    COD_TUTORIA     TABLA_OBSERVACIONES.COD_TUTORIA%TYPE,
    SESION          TABLA_OBSERVACIONES.SESION_REG%TYPE,
    ID_OBSERVACION  OUT TABLA_OBSERVACIONES.ID_OBSERVACION%TYPE) AUTHID CURRENT_USER AS
    
BEGIN        
    SELECT MAX(ID_OBSERVACION) + 1
      INTO ID_OBSERVACION
      FROM TABLA_OBSERVACIONES
     WHERE ANIO        = GENERAR_ID_OBSERVACION.ANIO
       AND PERIODO     = GENERAR_ID_OBSERVACION.PERIODO
       AND COD_TUTORIA = GENERAR_ID_OBSERVACION.COD_TUTORIA
       AND SESION      = GENERAR_ID_OBSERVACION.SESION;
       
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ID_OBSERVACION := NULL;
END GENERAR_ID_OBSERVACION;

/
--------------------------------------------------------
--  DDL for Procedure GENERAR_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GENERAR_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : GENERAR_TUTORIA                                               */
/* Objetivo  : Generar un nuevo c?igo de tutor?                            */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,
    COD_TUTORIA    OUT TUTORIA.T_CODIGO%TYPE) AUTHID CURRENT_USER AS
    
BEGIN        
    SELECT 'T'||TRIM(TO_CHAR(CODIGO,'0000000'))
      INTO COD_TUTORIA
      FROM (SELECT TO_NUMBER(SUBSTR(T.T_CODIGO, 2)) + 1  AS CODIGO 
              FROM TUTORIA T 
             WHERE T.T_ANIO = ANIO 
               AND T.T_PERIODO = PERIODO
             ORDER BY SUBSTR(T.T_CODIGO,2) DESC)
     WHERE ROWNUM <=1;
       
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        COD_TUTORIA := 'T0000001';
END GENERAR_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure GUARDAR_CARGANOTAS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GUARDAR_CARGANOTAS" 
/*---------------------------------------------------------------------------*/
/* Nombre    : GUARDAR_CARGANOTAS                                            */
/* Objetivo  : Guarda los registros de las notas                             */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO            HISTORIAL_NOTAS.ANIO%TYPE,
    PERIODO         HISTORIAL_NOTAS.PERIODO%TYPE,
    PLAN            HISTORIAL_NOTAS.PLAN%TYPE,
    COD_CURSO       HISTORIAL_NOTAS.COD_CURSO%TYPE,
    NOM_CURSO       HISTORIAL_NOTAS.NOM_CURSO%TYPE,
    COD_ALUMNO      HISTORIAL_NOTAS.COD_ALUMNO%TYPE,
    NOM_ALUMNO      HISTORIAL_NOTAS.NOM_ALUMNO%TYPE,
    NOM_DOCENTE     HISTORIAL_NOTAS.NOM_DOCENTE%TYPE,
    CREDITOS        HISTORIAL_NOTAS.CREDITOS%TYPE,
    NOTA_FINAL      HISTORIAL_NOTAS.NOTA_FINAL%TYPE) AUTHID CURRENT_USER AS

BEGIN            
    INSERT INTO HISTORIAL_NOTAS VALUES (GUARDAR_CARGANOTAS.ANIO,
                                        GUARDAR_CARGANOTAS.PERIODO,
                                        GUARDAR_CARGANOTAS.PLAN,
                                        GUARDAR_CARGANOTAS.COD_CURSO,
                                        GUARDAR_CARGANOTAS.COD_ALUMNO,
                                        GUARDAR_CARGANOTAS.NOTA_FINAL,
                                        GUARDAR_CARGANOTAS.CREDITOS,
                                        GUARDAR_CARGANOTAS.NOM_ALUMNO,
                                        GUARDAR_CARGANOTAS.NOM_DOCENTE,
                                        GUARDAR_CARGANOTAS.NOM_CURSO);
     COMMIT;
END GUARDAR_CARGANOTAS;

/
--------------------------------------------------------
--  DDL for Procedure GUARDAR_DISPONIBILIDAD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GUARDAR_DISPONIBILIDAD" 
/*---------------------------------------------------------------------------*/
/* Nombre    : GUARDAR_DISPONIBILIDAD                                        */
/* Objetivo  : Guardar las disponibilidades horarias de los usuarios         */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_USUARIO       TABLA_DISPONIBILIDAD.COD_USUARIO%TYPE,
    COD_CURSO         TABLA_DISPONIBILIDAD.COD_CURSO%TYPE,
    DIA               TABLA_DISPONIBILIDAD.DIA%TYPE,
    HORA_INI          TABLA_DISPONIBILIDAD.H_INICIO%TYPE,
    HORA_FIN          TABLA_DISPONIBILIDAD.H_FIN%TYPE,
    MODO              INTEGER,
    PROCESO           INTEGER) AUTHID CURRENT_USER AS
    
    ANIO_AUX          TABLA_DISPONIBILIDAD.ANIO%TYPE;
    PERIODO_AUX       TABLA_DISPONIBILIDAD.PERIODO%TYPE;  
    ROL_USUARIO_AUX   USUARIO_ROL_EQUIVALENCIA.ROL_USUARIO%TYPE;
    COD_DISP_AUX      TABLA_DISPONIBILIDAD.COD_DISP%TYPE;    
    COD_SISTEMA_AUX   USUARIO_ROL_EQUIVALENCIA.COD_SISTEMA%TYPE;
    TIPO_USUARIO_AUX  TABLA_DISPONIBILIDAD.TIPO_USUARIO%TYPE;
    
    
BEGIN       
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;
    
    GENERAR_CODIGO_DISPONIBILIDAD(ANIO     => GUARDAR_DISPONIBILIDAD.ANIO_AUX,
                                  PERIODO  => GUARDAR_DISPONIBILIDAD.PERIODO_AUX,
                                  COD_DISP => GUARDAR_DISPONIBILIDAD.COD_DISP_AUX);
    
    IF MODO = 1 THEN --MODO ADMIN
        IF PROCESO = 1 THEN --PROCESO TUTOR
            TIPO_USUARIO_AUX := 1;
        ELSE --PROCESO ALUMNO
            TIPO_USUARIO_AUX := 2;
        END IF;
        
        INSERTAR_DISPONIBILIDAD (ANIO         => GUARDAR_DISPONIBILIDAD.ANIO_AUX,
                                 PERIODO      => GUARDAR_DISPONIBILIDAD.PERIODO_AUX,
                                 COD_DISP     => GUARDAR_DISPONIBILIDAD.COD_DISP_AUX,
                                 TIPO_USUARIO => TIPO_USUARIO_AUX,
                                 COD_USUARIO  => GUARDAR_DISPONIBILIDAD.COD_USUARIO,
                                 COD_CURSO    => GUARDAR_DISPONIBILIDAD.COD_CURSO,
                                 DIA          => GUARDAR_DISPONIBILIDAD.DIA,
                                 H_INICIO     => GUARDAR_DISPONIBILIDAD.HORA_INI,
                                 H_FIN        => GUARDAR_DISPONIBILIDAD.HORA_FIN);
    
    ELSE --MODO TUTOR, MODO ALUMNO
        SELECT ROL_USUARIO, COD_SISTEMA
          INTO ROL_USUARIO_AUX, COD_SISTEMA_AUX
          FROM USUARIO_ROL_EQUIVALENCIA
         WHERE COD_USUARIO = GUARDAR_DISPONIBILIDAD.COD_USUARIO;
         
        SELECT DECODE(ROL_USUARIO_AUX, 'ROLE_TUTOR_R', 1, 'ROLE_ALUMNO_R',2) 
          INTO TIPO_USUARIO_AUX
          FROM DUAL;
        
        INSERTAR_DISPONIBILIDAD (ANIO         => GUARDAR_DISPONIBILIDAD.ANIO_AUX,
                                 PERIODO      => GUARDAR_DISPONIBILIDAD.PERIODO_AUX,
                                 COD_DISP     => GUARDAR_DISPONIBILIDAD.COD_DISP_AUX,
                                 TIPO_USUARIO => TIPO_USUARIO_AUX,
                                 COD_USUARIO  => GUARDAR_DISPONIBILIDAD.COD_SISTEMA_AUX,
                                 COD_CURSO    => GUARDAR_DISPONIBILIDAD.COD_CURSO,
                                 DIA          => GUARDAR_DISPONIBILIDAD.DIA,
                                 H_INICIO     => GUARDAR_DISPONIBILIDAD.HORA_INI,
                                 H_FIN        => GUARDAR_DISPONIBILIDAD.HORA_FIN);
        
    END IF;
    
    COMMIT;
                                            
END GUARDAR_DISPONIBILIDAD;

/
--------------------------------------------------------
--  DDL for Procedure GUARDAR_ENCUESTA_INICIAL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GUARDAR_ENCUESTA_INICIAL" 
/*---------------------------------------------------------------------------*/
/* Nombre    : GUARDAR_ENCUESTA_INICIAL                                      */
/* Objetivo  : Guardar los datos de la encuesta inicial                      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA     TUTORIA_ENCUESTA.COD_TUTORIA%TYPE,
    ID_PREGUNTA     TUTORIA_ENCUESTA.ID_PREGUNTA%TYPE,    
    RESPUESTA       TUTORIA_ENCUESTA.RESPUESTA%TYPE,
    CALIF_TUTOR     TUTORIA_ENCUESTA.CALIF_TUTOR%TYPE,
    CALIF_PSICO     TUTORIA_ENCUESTA.CALIF_PSICO%TYPE,
    COD_PSICOLOGA   TUTORIA_ENCUESTA.COD_PSICOLOGA%TYPE,
    MODO            INTEGER) AUTHID CURRENT_USER AS
    
    ANIO_AUX           TUTORIA_ENCUESTA.ANIO%TYPE;
    PERIODO_AUX        TUTORIA_ENCUESTA.PERIODO%TYPE;  
        
BEGIN
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;
    
    IF MODO = 1 THEN --MODO ALUMNO REGULAR
        
        INSERTAR_ENCUESTA(ANIO           => GUARDAR_ENCUESTA_INICIAL.ANIO_AUX,
                          PERIODO        => GUARDAR_ENCUESTA_INICIAL.PERIODO_AUX,
                          COD_TUTORIA    => GUARDAR_ENCUESTA_INICIAL.COD_TUTORIA,
                          COD_PSICOLOGA  => NULL,
                          ID_PREGUNTA    => GUARDAR_ENCUESTA_INICIAL.ID_PREGUNTA,
                          RESPUESTA      => GUARDAR_ENCUESTA_INICIAL.RESPUESTA,
                          CALIF_TUTOR    => NULL,
                          CALIF_PSICO    => NULL,
                          FECHA_REGISTRO => SYSDATE);                          
    ELSE
        IF MODO = 2 THEN --MODO TUTOR
            UPDATE TUTORIA_ENCUESTA
               SET CALIF_TUTOR = GUARDAR_ENCUESTA_INICIAL.CALIF_TUTOR
             WHERE ANIO        = GUARDAR_ENCUESTA_INICIAL.ANIO_AUX
               AND PERIODO     = GUARDAR_ENCUESTA_INICIAL.PERIODO_AUX
               AND COD_TUTORIA = GUARDAR_ENCUESTA_INICIAL.COD_TUTORIA
               AND ID_PREGUNTA = GUARDAR_ENCUESTA_INICIAL.ID_PREGUNTA;
        ELSE
            IF MODO = 3 THEN --MODO PSICOLOGA
                UPDATE TUTORIA_ENCUESTA
                   SET COD_PSICOLOGA = GUARDAR_ENCUESTA_INICIAL.COD_PSICOLOGA,
                       CALIF_PSICO   = GUARDAR_ENCUESTA_INICIAL.CALIF_PSICO
                 WHERE ANIO          = GUARDAR_ENCUESTA_INICIAL.ANIO_AUX
                   AND PERIODO       = GUARDAR_ENCUESTA_INICIAL.PERIODO_AUX
                   AND COD_TUTORIA   = GUARDAR_ENCUESTA_INICIAL.COD_TUTORIA
                   AND ID_PREGUNTA = GUARDAR_ENCUESTA_INICIAL.ID_PREGUNTA;
            END IF;
        END IF;
    END IF;
    
    COMMIT;    
    
END GUARDAR_ENCUESTA_INICIAL;

/
--------------------------------------------------------
--  DDL for Procedure GUARDAR_OBSERV_ASISTENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."GUARDAR_OBSERV_ASISTENCIA" 

/*---------------------------------------------------------------------------*/
/* Nombre    : GUARDAR_OBSERVACIONES_ASISTENCIA                              */
/* Objetivo  : Guardar las observaciones hechas a la asistencia a tutoria    */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA     TABLA_OBSERVACIONES.COD_TUTORIA%TYPE,
    RAZON           TABLA_OBSERVACIONES.RAZON%TYPE,    
    TAREA           TABLA_OBSERVACIONES.TAREA%TYPE,
    CRITICIDAD      TABLA_OBSERVACIONES.CRITICIDAD%TYPE,
    SESION          TABLA_OBSERVACIONES.SESION_REG%TYPE,
    FECHA_CUMPLIMIENTO  TABLA_OBSERVACIONES.FECHA_CUMPLIMIENTO%TYPE)  AUTHID CURRENT_USER AS
    
    ANIO_AUX        TABLA_OBSERVACIONES.ANIO%TYPE;
    PERIODO_AUX     TABLA_OBSERVACIONES.PERIODO%TYPE; 
    ID_OBSERV_AUX   TABLA_OBSERVACIONES.ID_OBSERVACION%TYPE;
        
BEGIN
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;
    
    GENERAR_ID_OBSERVACION(ANIO           => ANIO_AUX,
                           PERIODO        => PERIODO_AUX,
                           COD_TUTORIA    => GUARDAR_OBSERV_ASISTENCIA.COD_TUTORIA, 
                           ID_OBSERVACION => GUARDAR_OBSERV_ASISTENCIA.ID_OBSERV_AUX,
                           SESION         => GUARDAR_OBSERV_ASISTENCIA.SESION);
                           
    IF ID_OBSERV_AUX IS NULL THEN
        ID_OBSERV_AUX := 1;
    END IF;
    
    INSERTAR_OBSERVACION_TUTORIA(ANIO           => GUARDAR_OBSERV_ASISTENCIA.ANIO_AUX,           
                                 PERIODO        => GUARDAR_OBSERV_ASISTENCIA.PERIODO_AUX,         
                                 COD_TUTORIA    => GUARDAR_OBSERV_ASISTENCIA.COD_TUTORIA,      
                                 ID_OBSERVACION => GUARDAR_OBSERV_ASISTENCIA.ID_OBSERV_AUX,
                                 CRITICIDAD     => GUARDAR_OBSERV_ASISTENCIA.CRITICIDAD,
                                 SESION_REG     => GUARDAR_OBSERV_ASISTENCIA.SESION,  
                                 FECHA_REG      => TO_CHAR(SYSDATE, 'DD/MM/YYYY'),   
                                 SESION_CIERRE  => NULL,  
                                 OBSERV_CIERRE  => NULL,   
                                 ESTADO_OBSERV  => 1,
                                 RAZON          => GUARDAR_OBSERV_ASISTENCIA.RAZON,
                                 TAREA          => GUARDAR_OBSERV_ASISTENCIA.TAREA,
                                 FECHA_ENTREGA   => NULL,
                                 FECHA_CUMPLIMIENTO =>GUARDAR_OBSERV_ASISTENCIA.FECHA_CUMPLIMIENTO);    
    COMMIT;    
    
END GUARDAR_OBSERV_ASISTENCIA;

/
--------------------------------------------------------
--  DDL for Procedure INDICADORES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INDICADORES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INDICADORES                                                   */
/* Objetivo  : Indicadores para el sistema                                   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_INDICADOR    INTEGER,
    C_INDICADORES    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX                TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX             TUTORIA.T_PERIODO%TYPE;
    ALUMNOS_OBS             INTEGER;
    ALUMNOS_REG             INTEGER;
    TUTORIAS_TOT_OBS        INTEGER;
    TUTORIAS_TOT_REG        INTEGER;
    SESIONES_TOT_OBS        INTEGER;
    SESIONES_TOT_REG        INTEGER;
    SESIONES_ASIST_OBS      INTEGER;
    SESIONES_ASIST_REG      INTEGER;
    SESIONES_NO_ASIST_OBS   INTEGER;
    SESIONES_NO_ASIST_REG   INTEGER;
    ACTAS_CARGADAS_OBS      INTEGER;
    ACTAS_CARGADAS_REG      INTEGER;
    OBSERV_PENDIENTES_OBS   INTEGER;
    OBSERV_PENDIENTES_REG   INTEGER;
    OBSERV_PARCIALES_OBS    INTEGER;
    OBSERV_PARCIALES_REG    INTEGER;
    OBSERV_CERRADAS_OBS     INTEGER;
    OBSERV_CERRADAS_REG     INTEGER;  
    ENCUESTAS_REALIZADAS    INTEGER;  
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    IF COD_INDICADOR = 1 THEN
        SELECT COUNT(DISTINCT A_CODIGO) 
          INTO ALUMNOS_OBS 
          FROM TUTORIA 
         WHERE T_ANIO = INDICADORES.ANIO_AUX
           AND T_PERIODO = INDICADORES.PERIODO_AUX
           AND T_TIPO_ALUMNO = 1;
           
        SELECT COUNT(DISTINCT A_CODIGO) 
          INTO ALUMNOS_REG 
          FROM TUTORIA 
         WHERE T_ANIO = INDICADORES.ANIO_AUX
           AND T_PERIODO = INDICADORES.PERIODO_AUX
           AND T_TIPO_ALUMNO = 2;
    
        OPEN C_INDICADORES FOR
            SELECT 'Cantidad de alumnos matriculados' AS INDICADOR, 
                   ALUMNOS_OBS AS ALUMNOS_OBS,
                   ALUMNOS_REG AS ALUMNOS_REG
              FROM DUAL;
    END IF;
    
    IF COD_INDICADOR = 2 THEN
        SELECT COUNT(T_CODIGO)
          INTO TUTORIAS_TOT_OBS 
          FROM TUTORIA 
         WHERE T_ANIO = INDICADORES.ANIO_AUX 
           AND T_PERIODO = INDICADORES.PERIODO_AUX
           AND T_TIPO_ALUMNO = 1; 
                
        SELECT COUNT(T_CODIGO)
          INTO TUTORIAS_TOT_REG 
          FROM TUTORIA 
         WHERE T_ANIO = INDICADORES.ANIO_AUX 
           AND T_PERIODO = INDICADORES.PERIODO_AUX
           AND T_TIPO_ALUMNO = 2; 
           
        OPEN C_INDICADORES FOR
            SELECT 'Cantidad de tutor?s registradas' AS INDICADOR, 
                   TUTORIAS_TOT_OBS AS TUTORIAS_TOT_OBS,
                   TUTORIAS_TOT_REG AS TUTORIAS_TOT_REG
              FROM DUAL;
    END IF;
    
    IF COD_INDICADOR = 3 THEN
        SELECT COUNT(SESION) 
          INTO SESIONES_TOT_OBS
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX 
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX 
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX  
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 1;
           
        SELECT COUNT(SESION) 
          INTO SESIONES_TOT_REG
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX 
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX 
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX  
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 2;
           
        OPEN C_INDICADORES FOR
            SELECT 'Sesiones totales' AS INDICADOR, 
                   SESIONES_TOT_OBS AS SESIONES_TOT_OBS,
                   SESIONES_TOT_REG AS SESIONES_TOT_REG
              FROM DUAL;
    END IF;
    
    IF COD_INDICADOR = 4 THEN
        SELECT COUNT(SESION)
          INTO SESIONES_ASIST_OBS
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX 
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 1  
           AND PTUT.ESTADO <> 'F';
           
        SELECT COUNT(SESION) 
          INTO SESIONES_ASIST_REG
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX 
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX 
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX 
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 2  
           AND PTUT.ESTADO <> 'F';
           
        OPEN C_INDICADORES FOR
            SELECT 'Cantidad de sesiones asistidas' AS INDICADOR, 
                   SESIONES_ASIST_OBS AS SESIONES_ASIST_OBS,
                   SESIONES_ASIST_REG AS SESIONES_ASIST_REG
              FROM DUAL;
    END IF;
    
    IF COD_INDICADOR = 5 THEN
        SELECT COUNT(SESION)
          INTO SESIONES_NO_ASIST_OBS
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX 
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 1  
           AND PTUT.ESTADO = 'F';
           
        SELECT COUNT(SESION) 
          INTO SESIONES_NO_ASIST_REG
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX 
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX 
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX 
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 2  
           AND PTUT.ESTADO = 'F';
           
        OPEN C_INDICADORES FOR
            SELECT 'Cantidad de sesiones no asistidas' AS INDICADOR, 
                   SESIONES_NO_ASIST_OBS AS SESIONES_NO_ASIST_OBS,
                   SESIONES_NO_ASIST_REG AS SESIONES_NO_ASIST_REG
              FROM DUAL;
    END IF;
    
    IF COD_INDICADOR = 6 THEN
        SELECT COUNT(SESION) 
          INTO ACTAS_CARGADAS_OBS
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX 
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX 
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 1 
           AND ESTADO_ACTA <> 0;
           
        SELECT COUNT(SESION) 
          INTO ACTAS_CARGADAS_REG
          FROM PROGRAMACION_TUTORIA PTUT, TUTORIA TUT 
         WHERE PTUT.ANIO = INDICADORES.ANIO_AUX 
           AND PTUT.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX 
           AND PTUT.C_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 2 
           AND ESTADO_ACTA <> 0;
           
         OPEN C_INDICADORES FOR
            SELECT 'Cantidad de actas cargadas' AS INDICADOR, 
                   ACTAS_CARGADAS_OBS AS ACTAS_CARGADAS_OBS,
                   ACTAS_CARGADAS_REG AS ACTAS_CARGADAS_REG
              FROM DUAL;           
    END IF;
    
    IF COD_INDICADOR = 7 THEN
        SELECT COUNT(ID_OBSERVACION) 
          INTO OBSERV_PENDIENTES_OBS
          FROM TABLA_OBSERVACIONES TOBS, TUTORIA TUT 
         WHERE TOBS.ANIO = INDICADORES.ANIO_AUX  
           AND TOBS.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX   
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX
           AND TOBS.COD_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 1 
           AND TOBS.ESTADO_OBSERV = 1;
           
        SELECT COUNT(ID_OBSERVACION)
          INTO OBSERV_PENDIENTES_REG 
          FROM TABLA_OBSERVACIONES TOBS, TUTORIA TUT 
         WHERE TOBS.ANIO = INDICADORES.ANIO_AUX  
           AND TOBS.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX   
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX
           AND TOBS.COD_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 2 
           AND TOBS.ESTADO_OBSERV = 1;
           
         OPEN C_INDICADORES FOR
            SELECT 'Observaciones pendientes' AS INDICADOR, 
                   OBSERV_PENDIENTES_OBS AS OBSERV_PENDIENTES_OBS,
                   OBSERV_PENDIENTES_REG AS OBSERV_PENDIENTES_REG
              FROM DUAL;           
    END IF;
    
    IF COD_INDICADOR = 8 THEN
        SELECT COUNT(ID_OBSERVACION) 
          INTO OBSERV_PARCIALES_OBS
          FROM TABLA_OBSERVACIONES TOBS, TUTORIA TUT 
         WHERE TOBS.ANIO = INDICADORES.ANIO_AUX  
           AND TOBS.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX   
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX
           AND TOBS.COD_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 1 
           AND TOBS.ESTADO_OBSERV = 2;
           
        SELECT COUNT(ID_OBSERVACION)
          INTO OBSERV_PARCIALES_REG 
          FROM TABLA_OBSERVACIONES TOBS, TUTORIA TUT 
         WHERE TOBS.ANIO = INDICADORES.ANIO_AUX  
           AND TOBS.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX   
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX
           AND TOBS.COD_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 2 
           AND TOBS.ESTADO_OBSERV = 2;
           
         OPEN C_INDICADORES FOR
            SELECT 'Observaciones parcialmente levantadas' AS INDICADOR, 
                   OBSERV_PARCIALES_OBS AS OBSERV_PARCIALES_OBS,
                   OBSERV_PARCIALES_REG AS OBSERV_PARCIALES_REG
              FROM DUAL;           
    END IF;
    
    IF COD_INDICADOR = 9 THEN
        SELECT COUNT(ID_OBSERVACION) 
          INTO OBSERV_CERRADAS_OBS
          FROM TABLA_OBSERVACIONES TOBS, TUTORIA TUT 
         WHERE TOBS.ANIO = INDICADORES.ANIO_AUX  
           AND TOBS.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX   
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX
           AND TOBS.COD_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 1 
           AND TOBS.ESTADO_OBSERV = 3;
           
        SELECT COUNT(ID_OBSERVACION)
          INTO OBSERV_CERRADAS_REG 
          FROM TABLA_OBSERVACIONES TOBS, TUTORIA TUT 
         WHERE TOBS.ANIO = INDICADORES.ANIO_AUX  
           AND TOBS.PERIODO = INDICADORES.PERIODO_AUX 
           AND TUT.T_ANIO = INDICADORES.ANIO_AUX   
           AND TUT.T_PERIODO = INDICADORES.PERIODO_AUX
           AND TOBS.COD_TUTORIA = TUT.T_CODIGO 
           AND TUT.T_TIPO_ALUMNO = 2 
           AND TOBS.ESTADO_OBSERV = 3;
           
         OPEN C_INDICADORES FOR
            SELECT 'Observaciones cerradas' AS INDICADOR, 
                   OBSERV_CERRADAS_OBS AS OBSERV_CERRADAS_OBS,
                   OBSERV_CERRADAS_REG AS OBSERV_CERRADAS_REG
              FROM DUAL;           
    END IF;
    
    IF COD_INDICADOR = 10 THEN
        SELECT COUNT(DISTINCT(COD_TUTORIA)) 
          INTO ENCUESTAS_REALIZADAS
          FROM TUTORIA_ENCUESTA;
           
         OPEN C_INDICADORES FOR
            SELECT 'Encuestas realizadas' AS INDICADOR, 
                   0 AS ENCUESTAS_OBS,
                   ENCUESTAS_REALIZADAS AS ENCUESTAS_REG
              FROM DUAL;           
    END IF;
    
END INDICADORES;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_ACTA_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_ACTA_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_ACTA_TUTORIA                                         */
/* Objetivo  : Insertar el acta de tutor? en la correspondiente sesi?      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO            PROGRAMACION_TUTORIA.ANIO%TYPE,
    PERIODO         PROGRAMACION_TUTORIA.PERIODO%TYPE,
    COD_TUTORIA     PROGRAMACION_TUTORIA.C_TUTORIA%TYPE,
    SESION          PROGRAMACION_TUTORIA.SESION%TYPE,
    ACTA_TUTORIA    PROGRAMACION_TUTORIA.ACTA%TYPE,
    NOMBRE_ACTA     PROGRAMACION_TUTORIA.NOMBRE_ACTA%TYPE,
    ESTADO_ACTA     PROGRAMACION_TUTORIA.ESTADO_ACTA%TYPE) AUTHID CURRENT_USER AS    
      
BEGIN        
    UPDATE PROGRAMACION_TUTORIA 
       SET ACTA        = INSERTAR_ACTA_TUTORIA.ACTA_TUTORIA,
           NOMBRE_ACTA = INSERTAR_ACTA_TUTORIA.NOMBRE_ACTA,
           ESTADO_ACTA = INSERTAR_ACTA_TUTORIA.ESTADO_ACTA
     WHERE ANIO      = INSERTAR_ACTA_TUTORIA.ANIO
       AND PERIODO   = INSERTAR_ACTA_TUTORIA.PERIODO
       AND C_TUTORIA = INSERTAR_ACTA_TUTORIA.COD_TUTORIA
       AND SESION    = INSERTAR_ACTA_TUTORIA.SESION;
           
    COMMIT;
   
END INSERTAR_ACTA_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_ALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_ALUMNO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_ALUMNO                                               */
/* Objetivo  : Guardar los registros del alumno                              */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (A_CODIGO       ALUMNO.A_CODIGO%TYPE,
    A_NOMBRE       ALUMNO.A_APELLIDOS%TYPE,
    A_APELLIDOS    ALUMNO.A_APELLIDOS%TYPE,
    A_FNACIMIENTO  ALUMNO.A_NOMBRE%TYPE,
    A_DIRECCION    ALUMNO.A_DIRECCION%TYPE,
    A_EMAIL        ALUMNO.A_EMAIL%TYPE,
    A_TELEFONO     ALUMNO.A_TELEFONO%TYPE,
    A_DNI          ALUMNO.A_DNI%TYPE,
    A_PLAN         ALUMNO.A_PLAN%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO ALUMNO (A_CODIGO     , A_NOMBRE   , A_APELLIDOS,
                        A_FNACIMIENTO, A_DIRECCION, A_EMAIL    ,
                        A_TELEFONO   , A_DNI      , A_PLAN)
                VALUES (INSERTAR_ALUMNO.A_CODIGO,
                        INSERTAR_ALUMNO.A_NOMBRE,
                        INSERTAR_ALUMNO.A_APELLIDOS,
                        INSERTAR_ALUMNO.A_FNACIMIENTO,
                        INSERTAR_ALUMNO.A_DIRECCION,
                        INSERTAR_ALUMNO.A_EMAIL,
                        INSERTAR_ALUMNO.A_TELEFONO,
                        INSERTAR_ALUMNO.A_DNI,
                        INSERTAR_ALUMNO.A_PLAN);

END INSERTAR_ALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_ALUMNO_REGULAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_ALUMNO_REGULAR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_ALUMNO_REGULAR                                       */
/* Objetivo  : Guardar los registros del alumno regular                      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO         ALUMNO_REGULAR.ANIO%TYPE,
    PERIODO      ALUMNO_REGULAR.PERIODO%TYPE,
    COD_ALUMNO   ALUMNO_REGULAR.COD_ALUMNO%TYPE,
    NOM_ALUMNO   ALUMNO_REGULAR.NOM_ALUMNO%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO ALUMNO_REGULAR (ANIO      , PERIODO   , 
                                COD_ALUMNO, NOM_ALUMNO)
                        VALUES (INSERTAR_ALUMNO_REGULAR.ANIO,
                                INSERTAR_ALUMNO_REGULAR.PERIODO,
                                INSERTAR_ALUMNO_REGULAR.COD_ALUMNO,
                                INSERTAR_ALUMNO_REGULAR.NOM_ALUMNO);
END INSERTAR_ALUMNO_REGULAR;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_DISPONIBILIDAD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_DISPONIBILIDAD" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_DISPONIBILIDAD                                       */
/* Objetivo  : Insertar un registro en la tabla de disponibilidades          */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TABLA_DISPONIBILIDAD.ANIO%TYPE,
    PERIODO        TABLA_DISPONIBILIDAD.PERIODO%TYPE,
    COD_DISP       TABLA_DISPONIBILIDAD.COD_DISP%TYPE,
    TIPO_USUARIO   TABLA_DISPONIBILIDAD.TIPO_USUARIO%TYPE,
    COD_USUARIO    TABLA_DISPONIBILIDAD.COD_USUARIO%TYPE,
    COD_CURSO      TABLA_DISPONIBILIDAD.COD_CURSO%TYPE,
    DIA            TABLA_DISPONIBILIDAD.DIA%TYPE,
    H_INICIO       TABLA_DISPONIBILIDAD.H_INICIO%TYPE,
    H_FIN          TABLA_DISPONIBILIDAD.H_FIN%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO TABLA_DISPONIBILIDAD (ANIO        , PERIODO    , COD_DISP ,
                                      TIPO_USUARIO, COD_USUARIO, COD_CURSO,
                                      DIA         , H_INICIO   , H_FIN)
                              VALUES (INSERTAR_DISPONIBILIDAD.ANIO,
                                      INSERTAR_DISPONIBILIDAD.PERIODO,
                                      INSERTAR_DISPONIBILIDAD.COD_DISP,
                                      INSERTAR_DISPONIBILIDAD.TIPO_USUARIO,
                                      INSERTAR_DISPONIBILIDAD.COD_USUARIO,
                                      INSERTAR_DISPONIBILIDAD.COD_CURSO,
                                      INSERTAR_DISPONIBILIDAD.DIA,
                                      INSERTAR_DISPONIBILIDAD.H_INICIO,
                                      INSERTAR_DISPONIBILIDAD.H_FIN);
END INSERTAR_DISPONIBILIDAD;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_DOCENTE_REGULAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_DOCENTE_REGULAR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_DOCENTE_REGULAR                                      */
/* Objetivo  : Guardar los registros del docente regular                     */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO          DOCENTE_REGULAR.ANIO%TYPE,
    PERIODO       DOCENTE_REGULAR.PERIODO%TYPE,
    COD_DOCENTE   DOCENTE_REGULAR.COD_DOCENTE%TYPE,
    NOM_DOCENTE   DOCENTE_REGULAR.NOM_DOCENTE%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO DOCENTE_REGULAR (ANIO       , PERIODO   , 
                                 COD_DOCENTE, NOM_DOCENTE)
                         VALUES (INSERTAR_DOCENTE_REGULAR.ANIO,
                                 INSERTAR_DOCENTE_REGULAR.PERIODO,
                                 INSERTAR_DOCENTE_REGULAR.COD_DOCENTE,
                                 INSERTAR_DOCENTE_REGULAR.NOM_DOCENTE);
END INSERTAR_DOCENTE_REGULAR;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_ENCUESTA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_ENCUESTA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_ENCUESTA                                             */
/* Objetivo  : Insertar un registro en la tabla de encuestas                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO            TUTORIA_ENCUESTA.ANIO%TYPE,
    PERIODO         TUTORIA_ENCUESTA.PERIODO%TYPE,
    COD_TUTORIA     TUTORIA_ENCUESTA.COD_TUTORIA%TYPE,
    COD_PSICOLOGA   TUTORIA_ENCUESTA.COD_PSICOLOGA%TYPE,
    ID_PREGUNTA     TUTORIA_ENCUESTA.ID_PREGUNTA%TYPE,
    RESPUESTA       TUTORIA_ENCUESTA.RESPUESTA%TYPE,
    CALIF_TUTOR     TUTORIA_ENCUESTA.CALIF_TUTOR%TYPE,
    CALIF_PSICO     TUTORIA_ENCUESTA.CALIF_PSICO%TYPE,
    FECHA_REGISTRO  TUTORIA_ENCUESTA.FECHA_REGISTRO%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO TUTORIA_ENCUESTA (ANIO         , PERIODO    , COD_TUTORIA   ,
                                  COD_PSICOLOGA, ID_PREGUNTA, RESPUESTA     ,
                                  CALIF_TUTOR  , CALIF_PSICO, FECHA_REGISTRO)
                          VALUES (INSERTAR_ENCUESTA.ANIO,
                                  INSERTAR_ENCUESTA.PERIODO,
                                  INSERTAR_ENCUESTA.COD_TUTORIA,
                                  INSERTAR_ENCUESTA.COD_PSICOLOGA,
                                  INSERTAR_ENCUESTA.ID_PREGUNTA,
                                  INSERTAR_ENCUESTA.RESPUESTA,
                                  INSERTAR_ENCUESTA.CALIF_TUTOR,
                                  INSERTAR_ENCUESTA.CALIF_PSICO,
                                  INSERTAR_ENCUESTA.FECHA_REGISTRO);
END INSERTAR_ENCUESTA;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_OBSERVACION_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_OBSERVACION_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_OBSERVACION_TUTORIA                                  */
/* Objetivo  : Insertar un registro en la tabla de observaciones de tutoria  */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO              TABLA_OBSERVACIONES.ANIO%TYPE,       
    PERIODO           TABLA_OBSERVACIONES.PERIODO%TYPE,      
    COD_TUTORIA       TABLA_OBSERVACIONES.COD_TUTORIA%TYPE,        
    ID_OBSERVACION    TABLA_OBSERVACIONES.ID_OBSERVACION%TYPE,        
    CRITICIDAD        TABLA_OBSERVACIONES.CRITICIDAD%TYPE, 
    SESION_REG        TABLA_OBSERVACIONES.SESION_REG%TYPE, 
    FECHA_REG         TABLA_OBSERVACIONES.FECHA_REG%TYPE, 
    SESION_CIERRE     TABLA_OBSERVACIONES.SESION_CIERRE%TYPE, 
    OBSERV_CIERRE     TABLA_OBSERVACIONES.OBSERV_CIERRE%TYPE, 
    FECHA_ENTREGA      TABLA_OBSERVACIONES.FECHA_ENTREGA%TYPE, 
    ESTADO_OBSERV     TABLA_OBSERVACIONES.ESTADO_OBSERV%TYPE,
    RAZON             TABLA_OBSERVACIONES.RAZON%TYPE, 
    TAREA             TABLA_OBSERVACIONES.TAREA%TYPE,
    FECHA_CUMPLIMIENTO TABLA_OBSERVACIONES.FECHA_CUMPLIMIENTO%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO TABLA_OBSERVACIONES (ANIO         , PERIODO      , COD_TUTORIA , ID_OBSERVACION,    
                                     CRITICIDAD   , SESION_REG  , FECHA_REG     ,       
                                     SESION_CIERRE, OBSERV_CIERRE, FECHA_ENTREGA, ESTADO_OBSERV,RAZON,TAREA,
                                     FECHA_CUMPLIMIENTO)
                             VALUES (INSERTAR_OBSERVACION_TUTORIA.ANIO,
                                     INSERTAR_OBSERVACION_TUTORIA.PERIODO,
                                     INSERTAR_OBSERVACION_TUTORIA.COD_TUTORIA,
                                     INSERTAR_OBSERVACION_TUTORIA.ID_OBSERVACION,
                                     INSERTAR_OBSERVACION_TUTORIA.CRITICIDAD,
                                     INSERTAR_OBSERVACION_TUTORIA.SESION_REG,
                                     INSERTAR_OBSERVACION_TUTORIA.FECHA_REG,
                                     INSERTAR_OBSERVACION_TUTORIA.SESION_CIERRE,
                                     INSERTAR_OBSERVACION_TUTORIA.OBSERV_CIERRE,
                                     INSERTAR_OBSERVACION_TUTORIA.FECHA_ENTREGA,
                                     INSERTAR_OBSERVACION_TUTORIA.ESTADO_OBSERV,
                                     INSERTAR_OBSERVACION_TUTORIA.RAZON,
                                     INSERTAR_OBSERVACION_TUTORIA.TAREA,
                                     INSERTAR_OBSERVACION_TUTORIA.FECHA_CUMPLIMIENTO);
END INSERTAR_OBSERVACION_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_PROFESOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_PROFESOR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_PROFESOR                                             */
/* Objetivo  : Guardar los registros del profesor                            */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (P_CODIGO       PROFESOR.P_CODIGO%TYPE,
    P_NOMBRE       PROFESOR.P_APELLIDOS%TYPE,
    P_APELLIDOS    PROFESOR.P_APELLIDOS%TYPE,
    P_FNACIMIENTO  PROFESOR.P_NOMBRE%TYPE,
    P_DIRECCION    PROFESOR.P_DIRECCION%TYPE,
    P_EMAIL        PROFESOR.P_EMAIL%TYPE,
    P_TELEFONO     PROFESOR.P_TELEFONO%TYPE,
    P_DNI          PROFESOR.P_DNI%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO PROFESOR (P_CODIGO     , P_NOMBRE   , P_APELLIDOS,
                          P_FNACIMIENTO, P_DIRECCION, P_EMAIL    ,
                          P_TELEFONO   , P_DNI)
                  VALUES (INSERTAR_PROFESOR.P_CODIGO,
                          INSERTAR_PROFESOR.P_NOMBRE,
                          INSERTAR_PROFESOR.P_APELLIDOS,
                          INSERTAR_PROFESOR.P_FNACIMIENTO,
                          INSERTAR_PROFESOR.P_DIRECCION,
                          INSERTAR_PROFESOR.P_EMAIL,
                          INSERTAR_PROFESOR.P_TELEFONO,
                          INSERTAR_PROFESOR.P_DNI);

END INSERTAR_PROFESOR;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_TRAZA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_TRAZA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_TRAZA                                                */
/* Objetivo  : Insertar un registro en la tabla TRAZA                        */
/*             de tutor?                                                    */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (OPERACION       TRAZA.OPERACION%TYPE,
    PARAMETRO       TRAZA.PARAMETRO%TYPE) AUTHID CURRENT_USER AS
   
BEGIN        
    
    INSERT INTO TRAZA VALUES (INSERTAR_TRAZA.OPERACION, 
                              INSERTAR_TRAZA.PARAMETRO);
                                          
    COMMIT;                                        
   
END INSERTAR_TRAZA;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_TUTORIA                                              */
/* Objetivo  : Guardar los registros de la tutor?                           */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (T_ANIO          TUTORIA.T_ANIO%TYPE,
    T_PERIODO       TUTORIA.T_PERIODO%TYPE,
    T_CODIGO        TUTORIA.T_CODIGO%TYPE,
    A_CODIGO        TUTORIA.A_CODIGO%TYPE,
    P_CODIGO        TUTORIA.P_CODIGO%TYPE,
    C_CODIGO        TUTORIA.C_CODIGO%TYPE,
    C_REPITENCIAS   TUTORIA.C_REPITENCIAS%TYPE,
    T_FRECUENCIA    TUTORIA.T_FRECUENCIA%TYPE,
    T_DIA           TUTORIA.T_DIA%TYPE,
    T_TIPO_ALUMNO   TUTORIA.T_TIPO_ALUMNO%TYPE) AUTHID CURRENT_USER AS
    
BEGIN            
    INSERT INTO TUTORIA (T_ANIO       , T_PERIODO   , T_CODIGO,
                         A_CODIGO     , P_CODIGO    , C_CODIGO,
                         C_REPITENCIAS, T_FRECUENCIA, T_DIA   ,  
                         T_TIPO_ALUMNO)
                 VALUES (INSERTAR_TUTORIA.T_ANIO,
                         INSERTAR_TUTORIA.T_PERIODO,
                         INSERTAR_TUTORIA.T_CODIGO,
                         INSERTAR_TUTORIA.A_CODIGO,
                         INSERTAR_TUTORIA.P_CODIGO,
                         INSERTAR_TUTORIA.C_CODIGO,
                         INSERTAR_TUTORIA.C_REPITENCIAS,
                         INSERTAR_TUTORIA.T_FRECUENCIA,
                         INSERTAR_TUTORIA.T_DIA,
                         INSERTAR_TUTORIA.T_TIPO_ALUMNO);
    COMMIT;
END INSERTAR_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_USUARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_USUARIO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_USUARIO                                              */
/* Objetivo  : Inserta un registro en la tabla usuarios                      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_USUARIO    USUARIO.ID_USUARIO%TYPE,
    CLAVE         USUARIO.CLAVE%TYPE,   
    NOMBRES       USUARIO.NOMBRES%TYPE,
    MATERNO       USUARIO.MATERNO%TYPE,
    PATERNO       USUARIO.PATERNO%TYPE,
    CORREO        USUARIO.PATERNO%TYPE,
    DIRECCION     USUARIO.DIRECCION%TYPE,
    TELEFONO      USUARIO.TELEFONO%TYPE,
    ESTADO        USUARIO.ESTADO%TYPE) AUTHID CURRENT_USER AS

BEGIN               
    INSERT INTO USUARIO (ID_USUARIO, CLAVE   , NOMBRES,
                         MATERNO   , PATERNO , CORREO ,
                         DIRECCION , TELEFONO, ESTADO)
                         
                 VALUES (INSERTAR_USUARIO.ID_USUARIO,
                         INSERTAR_USUARIO.CLAVE     ,
                         INSERTAR_USUARIO.NOMBRES   ,     
                         INSERTAR_USUARIO.MATERNO   ,      
                         INSERTAR_USUARIO.PATERNO   ,             
                         INSERTAR_USUARIO.CORREO    ,
                         INSERTAR_USUARIO.DIRECCION ,
                         INSERTAR_USUARIO.TELEFONO  ,
                         INSERTAR_USUARIO.ESTADO);
                                    
END INSERTAR_USUARIO;

/
--------------------------------------------------------
--  DDL for Procedure INSERTAR_USUARIO_ROL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INSERTAR_USUARIO_ROL" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INSERTAR_USUARIO_ROL                                          */
/* Objetivo  : Inserta un registro en la tabla USUARIO_ROL                   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ID_ROL        USUARIO_ROL.ID_ROL%TYPE,
    ID_USUARIO    USUARIO_ROL.ID_USUARIO%TYPE,   
    ROL_DESC      USUARIO_ROL.ROL%TYPE) AUTHID CURRENT_USER AS

BEGIN               
    INSERT INTO USUARIO_ROL (ID_ROL, ID_USUARIO, ROL)                        
                         
                     VALUES (INSERTAR_USUARIO_ROL.ID_ROL    ,
                             INSERTAR_USUARIO_ROL.ID_USUARIO,
                             INSERTAR_USUARIO_ROL.ROL_DESC);
                                    
END INSERTAR_USUARIO_ROL;

/
--------------------------------------------------------
--  DDL for Procedure INS_USUARIO_ROL_EQUIVALENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."INS_USUARIO_ROL_EQUIVALENCIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : INS_USUARIO_ROL_EQUIVALENCIA                                  */
/* Objetivo  : Inserta un registro en la tabla USUARIO_ROL_EQUIVALENCIA      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_USUARIO    USUARIO_ROL_EQUIVALENCIA.COD_USUARIO%TYPE,
    ROL_USUARIO    USUARIO_ROL_EQUIVALENCIA.ROL_USUARIO%TYPE,   
    COD_SISTEMA    USUARIO_ROL_EQUIVALENCIA.COD_SISTEMA%TYPE) AUTHID CURRENT_USER AS

BEGIN               
    INSERT INTO USUARIO_ROL_EQUIVALENCIA (COD_USUARIO, ROL_USUARIO, COD_SISTEMA)                        
                         
                                  VALUES (INS_USUARIO_ROL_EQUIVALENCIA.COD_USUARIO,
                                          INS_USUARIO_ROL_EQUIVALENCIA.ROL_USUARIO,
                                          INS_USUARIO_ROL_EQUIVALENCIA.COD_SISTEMA);
                                    
END INS_USUARIO_ROL_EQUIVALENCIA;

/
--------------------------------------------------------
--  DDL for Procedure LEER_CICLO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LEER_CICLO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LEER_PERIODO                                                  */
/* Objetivo  : Busca los periodos de los ciclos para las tutor?s            */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_PERIODO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_PERIODO FOR    
        SELECT ID_CAMPO, VALOR_CAMPO 
          FROM TABLA_MAESTRA 
         WHERE NOMBRE_TABLA = 'CICLO' 
           AND NOMBRE_CAMPO = 'CICLO'
           AND ESTADO = 1;
       
END LEER_CICLO;

/
--------------------------------------------------------
--  DDL for Procedure LEER_HORAFIN
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LEER_HORAFIN" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LEER_HORAFIN                                                  */
/* Objetivo  : Busca todas las horas de fin de las tutor?s                  */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_HORAFIN   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_HORAFIN FOR    
        SELECT ID_CAMPO, VALOR_CAMPO 
          FROM TABLA_MAESTRA 
         WHERE NOMBRE_TABLA = 'HORA' 
           AND NOMBRE_CAMPO = 'HORA_FIN';
       
END LEER_HORAFIN;

/
--------------------------------------------------------
--  DDL for Procedure LEER_HORAINICIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LEER_HORAINICIO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LEER_HORAINICIO                                               */
/* Objetivo  : Busca todas las horas de inicio de tutor?s                   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_HORAINICIO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_HORAINICIO FOR    
        SELECT ID_CAMPO, VALOR_CAMPO 
          FROM TABLA_MAESTRA 
         WHERE NOMBRE_TABLA = 'HORA' 
           AND NOMBRE_CAMPO = 'HORA_INICIO';
       
END LEER_HORAINICIO;

/
--------------------------------------------------------
--  DDL for Procedure LEER_TABLAMAESTRA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LEER_TABLAMAESTRA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LEER_TABLAMAESTRA                                             */
/* Objetivo  : Leer los datos de las tablas internas de la tala maestra      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (TABLA            VARCHAR,
    CAMPO            VARCHAR,    
    C_TABLAMAESTRA   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_TABLAMAESTRA FOR    
        SELECT ID_CAMPO, VALOR_CAMPO 
          FROM TABLA_MAESTRA 
         WHERE NOMBRE_TABLA = TABLA 
           AND NOMBRE_CAMPO = CAMPO
           AND ESTADO = 1;
       
END LEER_TABLAMAESTRA;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_ALUMNOSCURSO_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_ALUMNOSCURSO_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_ALUMNOSCURSO_REGULARES                                 */
/* Objetivo  : Buscar los alumnos que corresponden a las tutor?s seg? el   */
/*             curso                                                         */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_CURSO  TUTORIA.C_CODIGO%TYPE,
    C_ALUMNOS  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_ALUMNOS FOR
        SELECT A_CODIGO, UPPER(TRIM(A_NOMBRE)) || ' ' ||  UPPER(TRIM(A_APELLIDOS)) AS NOMBRE
          FROM ALUMNO 
         WHERE A_CODIGO IN (SELECT A_CODIGO 
                              FROM TUTORIA 
                             WHERE C_CODIGO = LISTAR_ALUMNOSCURSO_REGULARES.COD_CURSO
                               AND T_ANIO = ANIO_AUX 
                               AND T_PERIODO = PERIODO_AUX
                               AND T_TIPO_ALUMNO = 2);       
END LISTAR_ALUMNOSCURSO_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_ALUMNOSDOCENTE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_ALUMNOSDOCENTE" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_ALUMNOSDOCENTE                                         */
/* Objetivo  : Buscar todos los alumnos que llevan la tutor? con el profesor*/
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_DOCENTE   TUTORIA.P_CODIGO%TYPE,
    COD_CURSO     TUTORIA.C_CODIGO%TYPE,
    TIPO_ALUMNO   INTEGER,
    MODO          INTEGER,
    C_ALUMNOS     OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    IF MODO = 1 THEN --MODO ADMIN
        OPEN C_ALUMNOS FOR
        SELECT TUT.A_CODIGO, UPPER(TRIM(ALU.A_NOMBRE)) || ' ' || UPPER(TRIM(ALU.A_APELLIDOS)) AS A_NOMBRE 
          FROM TUTORIA TUT, ALUMNO ALU 
         WHERE TUT.P_CODIGO  = COD_DOCENTE
           AND TUT.C_CODIGO  = COD_CURSO
           AND TUT.T_ANIO    = ANIO_AUX
           AND TUT.T_PERIODO = PERIODO_AUX
           AND TUT.A_CODIGO  = ALU.A_CODIGO
           AND TUT.T_TIPO_ALUMNO = TIPO_ALUMNO;
           
    ELSE --MODO DOCENTE
        OPEN C_ALUMNOS FOR
        SELECT TUT.A_CODIGO, UPPER(TRIM(ALU.A_NOMBRE)) || ' ' || UPPER(TRIM(ALU.A_APELLIDOS)) AS A_NOMBRE 
          FROM TUTORIA TUT, ALUMNO ALU 
         WHERE TUT.P_CODIGO IN (SELECT COD_SISTEMA 
                                  FROM USUARIO_ROL_EQUIVALENCIA 
                                 WHERE COD_USUARIO = COD_DOCENTE)
           AND TUT.C_CODIGO  = COD_CURSO
           AND TUT.T_ANIO    = ANIO_AUX
           AND TUT.T_PERIODO = PERIODO_AUX
           AND TUT.A_CODIGO  = ALU.A_CODIGO
           AND TUT.T_TIPO_ALUMNO = TIPO_ALUMNO;
    END IF;
   
END LISTAR_ALUMNOSDOCENTE;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_ALUMNOS_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_ALUMNOS_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_ALUMNOS_REGULARES                                      */
/* Objetivo  : Buscar todos los alumnos regulares para el ciclo actual       */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_ALUMNOS    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_ACTUAL     DOCENTE_REGULAR.ANIO%TYPE;
    PERIODO_ACTUAL  DOCENTE_REGULAR.PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_ACTUAL, PERIODO_ACTUAL
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_ALUMNOS FOR
        SELECT TRIM(COD_ALUMNO), UPPER(TRIM(NOM_ALUMNO))
          FROM ALUMNO_REGULAR AR
         WHERE AR.ANIO    = ANIO_ACTUAL
           AND AR.PERIODO = PERIODO_ACTUAL;          
    
END LISTAR_ALUMNOS_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_AREA_APROBADO_TUTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_AREA_APROBADO_TUTOR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_AREA_APROBADO_TUTOR                                       */
/* Objetivo  : Listar todas las Ã¡reas del conocimiento registradas por tutor y aprobados          */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: Alex Naupay                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTOR   ALUMNO_PAR.A_CODIGO%TYPE , C_AREA_CONOCIMIENTO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN
    OPEN C_AREA_CONOCIMIENTO FOR
         SELECT AC.COD_AREA,	AC.NOMBRE_AREA  FROM  POSTULACION_PAR PP
					INNER JOIN CONVOCATORIA_PAR CP ON PP.CODCONVOCATOR = CP.CODCONVOCATOR
					INNER JOIN CICLO CI ON CP.ID_CICLO  = CI.ID_CICLO AND CI.ACTIVO=1
					INNER JOIN ASOC_POSTULACION_TEMA_PAR  APTP ON  APTP. COD_POSTULACION = PP.COD_POSTULACION AND APTP.FLAG_APROBACION=1
					INNER JOIN TEMAS_PAR TP ON APTP.CODIGO_TEMA = TP.CODIGO_TEMA 
					INNER JOIN CURSO C ON C.C_CODIGO = TP.C_CODIGO
					INNER JOIN AREA_CONOCIMIENTO AC ON AC.COD_AREA = C.AREA_C 
					WHERE PP.A_CODIGO=LISTAR_AREA_APROBADO_TUTOR.COD_TUTOR;

END LISTAR_AREA_APROBADO_TUTOR;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_AREACONOCIMIENTO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_AREACONOCIMIENTO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_AREACONOCIMIENTO                                       */
/* Objetivo  : Listar todas las ?eas del conocimiento registradas           */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_AREA_CONOCIMIENTO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_AREA_CONOCIMIENTO FOR    
        SELECT COD_AREA, NOMBRE_AREA 
          FROM AREA_CONOCIMIENTO; 
          
END LISTAR_AREACONOCIMIENTO;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_CURSO_APROBADO_TUTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_CURSO_APROBADO_TUTOR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_CURSO_APROBADO_TUTOR                                      */
/* Objetivo  : Listar todas las Ã¡reas del conocimiento registradas por tutor y aprobados          */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: Alex Naupay                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTOR   ALUMNO_PAR.A_CODIGO%TYPE , COD_AREA   AREA_CONOCIMIENTO.COD_AREA%TYPE,  C_CURSO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN
    OPEN C_CURSO FOR
         SELECT C.C_CODIGO,	C.NOMBRE  FROM  POSTULACION_PAR PP
					INNER JOIN CONVOCATORIA_PAR CP ON PP.CODCONVOCATOR = CP.CODCONVOCATOR
					INNER JOIN CICLO CI ON CP.ID_CICLO  = CI.ID_CICLO AND CI.ACTIVO=1
					INNER JOIN ASOC_POSTULACION_TEMA_PAR  APTP ON  APTP. COD_POSTULACION = PP.COD_POSTULACION AND APTP.FLAG_APROBACION=1
					INNER JOIN TEMAS_PAR TP ON APTP.CODIGO_TEMA = TP.CODIGO_TEMA 
					INNER JOIN CURSO C ON C.C_CODIGO = TP.C_CODIGO AND C.AREA_C = LISTAR_CURSO_APROBADO_TUTOR.COD_AREA
					WHERE PP.A_CODIGO=LISTAR_CURSO_APROBADO_TUTOR.COD_TUTOR;

END LISTAR_CURSO_APROBADO_TUTOR;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_CURSOSDOCENTE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_CURSOSDOCENTE" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_CURSOSDOCENTE                                          */
/* Objetivo  : Buscar los cursos en los cuales el docente est?efectuando la */
/*             tutor?                                                       */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_DOCENTE   TUTORIA.P_CODIGO%TYPE,
    C_CURSOS   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_CURSOS FOR
        SELECT C_CODIGO, NOMBRE 
          FROM CURSO 
         WHERE C_CODIGO IN (SELECT C_CODIGO 
                              FROM TUTORIA 
                             WHERE P_CODIGO IN (SELECT COD_SISTEMA 
                                                  FROM USUARIO_ROL_EQUIVALENCIA 
                                                 WHERE COD_USUARIO = COD_DOCENTE)
                               AND T_ANIO = ANIO_AUX 
                               AND T_PERIODO = PERIODO_AUX);       
END LISTAR_CURSOSDOCENTE;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_CURSOSDOCENTE_GENERICO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_CURSOSDOCENTE_GENERICO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_CURSOSDOCENTE_GENERICO                                 */
/* Objetivo  : Buscar los cursos en los cuales el docente est?efectuando la */
/*             tutor? sin importar qu?proceso de tutor? siga              */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_DOCENTE   TUTORIA.P_CODIGO%TYPE,
    PROCESO       INTEGER,
    MODO          INTEGER,
    C_CURSOS      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    IF MODO = 1 THEN --MODO ADMIN
        OPEN C_CURSOS FOR
            SELECT C_CODIGO, NOMBRE 
              FROM CURSO 
             WHERE C_CODIGO IN (SELECT C_CODIGO 
                                  FROM TUTORIA 
                                 WHERE P_CODIGO  = LISTAR_CURSOSDOCENTE_GENERICO.COD_DOCENTE
                                   AND T_ANIO    = ANIO_AUX 
                                   AND T_PERIODO = PERIODO_AUX
                                   AND T_TIPO_ALUMNO = LISTAR_CURSOSDOCENTE_GENERICO.PROCESO);
    ELSE       
        OPEN C_CURSOS FOR
            SELECT C_CODIGO, NOMBRE 
              FROM CURSO 
             WHERE C_CODIGO IN (SELECT C_CODIGO 
                                  FROM TUTORIA 
                                 WHERE P_CODIGO IN (SELECT COD_SISTEMA 
                                                      FROM USUARIO_ROL_EQUIVALENCIA 
                                                     WHERE COD_USUARIO = COD_DOCENTE)
                                   AND T_ANIO = ANIO_AUX 
                                   AND T_PERIODO = PERIODO_AUX
                                   AND T_TIPO_ALUMNO = LISTAR_CURSOSDOCENTE_GENERICO.PROCESO);
    END IF;   
                                   
END LISTAR_CURSOSDOCENTE_GENERICO;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_CURSOSDOCENTE_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_CURSOSDOCENTE_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_CURSOSDOCENTE_REGULARES                                */
/* Objetivo  : Buscar los cursos en los cuales el docente est?efectuando la */
/*             tutor? para alumnos regulares                                */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_DOCENTE   TUTORIA.P_CODIGO%TYPE,
    C_CURSOS   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_CURSOS FOR
        SELECT C_CODIGO, NOMBRE 
          FROM CURSO 
         WHERE C_CODIGO IN (SELECT C_CODIGO 
                              FROM TUTORIA 
                             WHERE P_CODIGO IN (SELECT COD_SISTEMA 
                                                  FROM USUARIO_ROL_EQUIVALENCIA 
                                                 WHERE COD_USUARIO = COD_DOCENTE)
                               AND T_ANIO = ANIO_AUX 
                               AND T_PERIODO = PERIODO_AUX
                               AND T_TIPO_ALUMNO = 2);       
END LISTAR_CURSOSDOCENTE_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_CURSOS_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_CURSOS_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_CURSOS_REGULARES                                       */
    /* Objetivo  : Buscar todos los cursos para determinado alumno regular   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_ALUMNO   TABLA_DISPONIBILIDAD.COD_USUARIO%TYPE,
    C_CURSOS     OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_ACTUAL     CICLO.ANIO%TYPE;
    PERIODO_ACTUAL  CICLO.PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_ACTUAL, PERIODO_ACTUAL
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_CURSOS FOR
        SELECT DISTINCT TD.COD_CURSO, CUR.NOMBRE 
          FROM TABLA_DISPONIBILIDAD TD, CURSO CUR 
         WHERE TD.ANIO        = LISTAR_CURSOS_REGULARES.ANIO_ACTUAL 
           AND TD.PERIODO     = LISTAR_CURSOS_REGULARES.PERIODO_ACTUAL
           AND TD.COD_CURSO   = CUR.C_CODIGO
           AND TD.COD_USUARIO = LISTAR_CURSOS_REGULARES.COD_ALUMNO
           AND TIPO_USUARIO = 2;       
    
END LISTAR_CURSOS_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_CURSOSXAREACONOCIMIENTO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_CURSOSXAREACONOCIMIENTO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_CURSOSXAREACONOCIMIENTO                                */
/* Objetivo  : Listar los cursos en base al ?ea de conocimiento al cual     */
/*             pertenezcan                                                   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_AREA_CON    VARCHAR,
    C_CURSOS        OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    OPEN C_CURSOS FOR    
        SELECT C_CODIGO, NOMBRE 
          FROM CURSO
         WHERE AREA_C = COD_AREA_CON; 
          
END LISTAR_CURSOSXAREACONOCIMIENTO;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_CURSO_TUTORIAS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_CURSO_TUTORIAS" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_CURSOSDOCENTE_GENERICO                                 */
/* Objetivo  : Buscar los cursos en los cuales el docente est?efectuando la */
/*             tutor? sin importar qu?proceso de tutor? siga              */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_CURSOS      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS  
BEGIN        
        OPEN C_CURSOS FOR
            SELECT C_CODIGO, NOMBRE 
              FROM CURSO 
             WHERE C_CODIGO IN (SELECT DISTINCT(TUTORIA.C_CODIGO) 
                                  FROM TUTORIA );
   
                                   
END LISTAR_CURSO_TUTORIAS;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_DATOS_SESIONES_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_DATOS_SESIONES_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_DATOS_SESIONES_TUTORIA                                 */
/* Objetivo  : Listar los datos de las sesiones de tutor?                   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_CURSO          TUTORIA.C_CODIGO%TYPE,
    COD_TUTOR          TUTORIA.P_CODIGO%TYPE,
    COD_ALUMNO         TUTORIA.A_CODIGO%TYPE,        
    TIPO_ALUMNO        INTEGER,
    MODO               INTEGER,
    C_DATOSTUTORIA    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;

BEGIN      
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    IF MODO = 1 THEN --MODO ADMIN
        OPEN C_DATOSTUTORIA FOR
            SELECT TUT.T_ANIO   , TUT.T_PERIODO, TUT.T_CODIGO     , TUT.A_CODIGO    , TUT.P_CODIGO  , 
                   TUT.C_CODIGO , TUT.T_DIA    , TUT.T_TIPO_ALUMNO, PTUT.SESION     , TO_CHAR(PTUT.FECHA_TUT,'DD/MM/YYYY'), 
                   PTUT.HORA_INI, PTUT.HORA_FIN, PTUT.ESTADO      , PTUT.ESTADO_ACTA 
              FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT
             WHERE TUT.T_ANIO    = LISTAR_DATOS_SESIONES_TUTORIA.ANIO_AUX 
               AND TUT.T_PERIODO = LISTAR_DATOS_SESIONES_TUTORIA.PERIODO_AUX
               AND PTUT.ANIO     = LISTAR_DATOS_SESIONES_TUTORIA.ANIO_AUX  
               AND PTUT.PERIODO  = LISTAR_DATOS_SESIONES_TUTORIA.PERIODO_AUX
               AND TUT.T_CODIGO IN (SELECT TUTO.T_CODIGO 
                                      FROM TUTORIA TUTO
                                     WHERE TUTO.T_ANIO    = LISTAR_DATOS_SESIONES_TUTORIA.ANIO_AUX
                                       AND TUTO.T_PERIODO = LISTAR_DATOS_SESIONES_TUTORIA.PERIODO_AUX
                                       AND TUTO.C_CODIGO  = LISTAR_DATOS_SESIONES_TUTORIA.COD_CURSO
                                       AND TUTO.P_CODIGO  = LISTAR_DATOS_SESIONES_TUTORIA.COD_TUTOR
                                       AND TUTO.A_CODIGO  = LISTAR_DATOS_SESIONES_TUTORIA.COD_ALUMNO
                                       AND TUTO.T_TIPO_ALUMNO = LISTAR_DATOS_SESIONES_TUTORIA.TIPO_ALUMNO)
               AND TUT.T_CODIGO = PTUT.C_TUTORIA   
             ORDER BY PTUT.SESION;
    ELSE
        IF MODO = 5 THEN --MODO TUTOR
            OPEN C_DATOSTUTORIA FOR
                SELECT TUT.T_ANIO   , TUT.T_PERIODO, TUT.T_CODIGO     , TUT.A_CODIGO    , TUT.P_CODIGO  , 
                       TUT.C_CODIGO , TUT.T_DIA    , TUT.T_TIPO_ALUMNO, PTUT.SESION     , TO_CHAR(PTUT.FECHA_TUT,'DD/MM/YYYY'), 
                       PTUT.HORA_INI, PTUT.HORA_FIN, PTUT.ESTADO      , PTUT.ESTADO_ACTA 
                  FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT
                 WHERE TUT.T_ANIO    = LISTAR_DATOS_SESIONES_TUTORIA.ANIO_AUX 
                   AND TUT.T_PERIODO = LISTAR_DATOS_SESIONES_TUTORIA.PERIODO_AUX
                   AND PTUT.ANIO     = LISTAR_DATOS_SESIONES_TUTORIA.ANIO_AUX  
                   AND PTUT.PERIODO  = LISTAR_DATOS_SESIONES_TUTORIA.PERIODO_AUX
                   AND TUT.T_CODIGO IN (SELECT TUTO.T_CODIGO 
                                          FROM TUTORIA TUTO
                                         WHERE TUTO.T_ANIO    = LISTAR_DATOS_SESIONES_TUTORIA.ANIO_AUX
                                           AND TUTO.T_PERIODO = LISTAR_DATOS_SESIONES_TUTORIA.PERIODO_AUX
                                           AND TUTO.C_CODIGO  = LISTAR_DATOS_SESIONES_TUTORIA.COD_CURSO
                                           AND TUTO.P_CODIGO IN (SELECT COD_SISTEMA  
                                                                   FROM USUARIO_ROL_EQUIVALENCIA
                                                                  WHERE COD_USUARIO = LISTAR_DATOS_SESIONES_TUTORIA.COD_TUTOR)
                                           AND TUTO.A_CODIGO  = LISTAR_DATOS_SESIONES_TUTORIA.COD_ALUMNO
                                           AND TUTO.T_TIPO_ALUMNO = LISTAR_DATOS_SESIONES_TUTORIA.TIPO_ALUMNO)
                   AND TUT.T_CODIGO = PTUT.C_TUTORIA   
             ORDER BY PTUT.SESION;
        END IF;        
    END IF;  
    
END LISTAR_DATOS_SESIONES_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_DISPONIBILIDADES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_DISPONIBILIDADES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_DISPONIBILIDADES                                       */
/* Objetivo  : Listar las disponibilidades de los docentes o alumnos         */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_CURSO              TABLA_DISPONIBILIDAD.COD_CURSO%TYPE,
    COD_USUARIO            TABLA_DISPONIBILIDAD.COD_USUARIO%TYPE,
    TIPO_USUARIO           TABLA_DISPONIBILIDAD.TIPO_USUARIO%TYPE,
    C_DISPONIBILIDADES     OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_DISPONIBILIDADES FOR    
        SELECT DIA, H_INICIO, H_FIN 
          FROM TABLA_DISPONIBILIDAD 
         WHERE ANIO         = LISTAR_DISPONIBILIDADES.ANIO_AUX
           AND PERIODO      = LISTAR_DISPONIBILIDADES.PERIODO_AUX 
           AND COD_CURSO    = LISTAR_DISPONIBILIDADES.COD_CURSO
           AND COD_USUARIO  = LISTAR_DISPONIBILIDADES.COD_USUARIO
           AND TIPO_USUARIO = LISTAR_DISPONIBILIDADES.TIPO_USUARIO;
   
END LISTAR_DISPONIBILIDADES;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_HORARIOSTUTORIA_ALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_HORARIOSTUTORIA_ALUMNO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_HORARIOSTUTORIA_ALUMNO                                 */
/* Objetivo  : Listar los horarios de tutor?s de los alumnos                */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,    
    COD_ALUMNO     TUTORIA.A_CODIGO%TYPE,    
    COD_USUARIO    USUARIO.ID_USUARIO%TYPE,
    PROCESO_TUT    INTEGER,
    HORARIO_C      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
        
    ID_ROL_AUX     USUARIO_ROL.ID_ROL%TYPE;
    COD_PROFESOR   USUARIO_ROL_EQUIVALENCIA.COD_SISTEMA%TYPE;
    SESION_AUX     PROGRAMACION_TUTORIA.SESION%TYPE := 1;
    NOMBRE_TABLA   TABLA_MAESTRA.NOMBRE_TABLA%TYPE := 'FRECUENCIA';
    NOMBRE_CAMPO   TABLA_MAESTRA.NOMBRE_CAMPO%TYPE := 'FRECUENCIA_TUTORIA';

BEGIN            
    SELECT ID_ROL 
      INTO ID_ROL_AUX
      FROM USUARIO_ROL 
     WHERE ID_USUARIO = LISTAR_HORARIOSTUTORIA_ALUMNO.COD_USUARIO;
     
    IF ID_ROL_AUX IN (1, 2, 3, 4, 6, 7, 8, 9) THEN
        OPEN HORARIO_C FOR     
            SELECT TUT.T_ANIO   , TUT.T_PERIODO, TUT.T_CODIGO, TUT.A_CODIGO  , 
                   TUT.P_CODIGO , TUT.C_CODIGO , TUT.T_DIA   ,  PTUT.HORA_INI, 
                   PTUT.HORA_FIN, CUR.NOMBRE   , 
                   UPPER(TRIM(PRO.P_NOMBRE)) || ' ' || UPPER(TRIM(PRO.P_APELLIDOS))
                   AS P_NOMBRE, TUT.C_REPITENCIAS, TM.VALOR_CAMPO                   
              FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT, CURSO CUR, PROFESOR PRO, 
                   TABLA_MAESTRA TM
             WHERE TUT.T_CODIGO  = PTUT.C_TUTORIA               
               AND TUT.T_ANIO    = PTUT.ANIO
               AND TUT.T_PERIODO = PTUT.PERIODO
               AND TUT.C_CODIGO  = CUR.C_CODIGO               
               AND TUT.P_CODIGO  = PRO.P_CODIGO
               AND PTUT.SESION   = 1
               AND TM.NOMBRE_TABLA = 'FRECUENCIA'
               AND TM.NOMBRE_CAMPO = 'FRECUENCIA_TUTORIA'
               AND TM.ID_CAMPO   = TUT.T_FRECUENCIA               
               AND TUT.T_ANIO    = LISTAR_HORARIOSTUTORIA_ALUMNO.ANIO 
               AND TUT.T_PERIODO = LISTAR_HORARIOSTUTORIA_ALUMNO.PERIODO 
               AND TUT.A_CODIGO  = LISTAR_HORARIOSTUTORIA_ALUMNO.COD_ALUMNO               
               AND TUT.T_TIPO_ALUMNO = LISTAR_HORARIOSTUTORIA_ALUMNO.PROCESO_TUT;    
    ELSE
        IF ID_ROL_AUX IN (5, 10) THEN
            SELECT COD_SISTEMA
              INTO COD_PROFESOR
              FROM USUARIO_ROL_EQUIVALENCIA
             WHERE COD_USUARIO = LISTAR_HORARIOSTUTORIA_ALUMNO.COD_USUARIO;
             
            OPEN HORARIO_C FOR             
                SELECT TUT.T_ANIO   , TUT.T_PERIODO, TUT.T_CODIGO, TUT.A_CODIGO , 
                       TUT.P_CODIGO   , TUT.C_CODIGO,TUT.T_DIA  , PTUT.HORA_INI, 
                       PTUT.HORA_FIN, CUR.NOMBRE   , 
                       UPPER(TRIM(PRO.P_NOMBRE)) || ' ' || UPPER(TRIM(PRO.P_APELLIDOS))
                       AS P_NOMBRE, TUT.C_REPITENCIAS, TM.VALOR_CAMPO
                  FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT, CURSO CUR, PROFESOR PRO, 
                       TABLA_MAESTRA TM
                 WHERE TUT.T_CODIGO    = PTUT.C_TUTORIA               
                   AND TUT.T_ANIO      = PTUT.ANIO
                   AND TUT.T_PERIODO   = PTUT.PERIODO
                   AND TUT.C_CODIGO    = CUR.C_CODIGO               
                   AND TUT.P_CODIGO    = PRO.P_CODIGO
                   AND PTUT.SESION     = 1
                   AND TM.NOMBRE_TABLA = 'FRECUENCIA'
                   AND TM.NOMBRE_CAMPO = 'FRECUENCIA_TUTORIA'
                   AND TM.ID_CAMPO     = TUT.T_FRECUENCIA                   
                   AND TUT.T_ANIO      = LISTAR_HORARIOSTUTORIA_ALUMNO.ANIO 
                   AND TUT.T_PERIODO   = LISTAR_HORARIOSTUTORIA_ALUMNO.PERIODO 
                   AND TUT.A_CODIGO    = LISTAR_HORARIOSTUTORIA_ALUMNO.COD_ALUMNO
                   AND TUT.P_CODIGO    = LISTAR_HORARIOSTUTORIA_ALUMNO.COD_PROFESOR               
                   AND TUT.T_TIPO_ALUMNO = LISTAR_HORARIOSTUTORIA_ALUMNO.PROCESO_TUT;
        END IF;
    END IF;
    
END LISTAR_HORARIOSTUTORIA_ALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_HORARIOSTUTORIA_DOCENTE
--------------------------------------------------------
set define off;

create or replace PROCEDURE             "LISTAR_HORARIOSTUTORIA_DOCENTE" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_HORARIOSTUTORIA_DOCENTE                                */
/* Objetivo  : Listar los horarios de tutor?s de los docentes               */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,    
    COD_DOCENTE    TUTORIA.P_CODIGO%TYPE,    
    HORARIO_C      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
        
BEGIN            
    OPEN HORARIO_C FOR     
        SELECT TUT.T_ANIO  , TUT.T_PERIODO, TUT.T_CODIGO, TUT.A_CODIGO, 
               TUT.P_CODIGO, TUT.T_DIA, PTUT.HORA_INI, PTUT.HORA_FIN,
               TUT.C_CODIGO, CUR.NOMBRE, 
               UPPER(TRIM(ALU.A_NOMBRE)) || ' ' || UPPER(TRIM(ALU.A_APELLIDOS))
               AS A_NOMBRE, 
               
              TUT.C_REPITENCIAS,TM.VALOR_CAMPO  , 
              UPPER(TRIM(PRO.P_NOMBRE)) || ' ' || UPPER(TRIM(PRO.P_APELLIDOS))
                   AS P_NOMBRE ,
              (SELECT count(*)FROM ASISTENCIA_T_ALUM WHERE ASISTENCIA_T =1 AND C_TUTORIA=TUT.T_CODIGO) as asistencia_asistio,
              (SELECT count(*)FROM ASISTENCIA_T_ALUM WHERE ASISTENCIA_T =2 AND C_TUTORIA=TUT.T_CODIGO) as asistencia_falto,
              (SELECT count(*)FROM ASISTENCIA_T_ALUM WHERE ASISTENCIA_T =3 AND C_TUTORIA=TUT.T_CODIGO) as asistencia_tardanza,
              (SELECT count(*) FROM PROGRAMACION_TUTORIA WHERE C_TUTORIA=TUT.T_CODIGO) as num_sesiones,
              (SELECT count(*) FROM TABLA_OBSERVACIONES WHERE COD_TUTORIA=TUT.T_CODIGO AND ESTADO_OBSERV = 1) as num_tareas_pendiente,
              (SELECT count(*) FROM TABLA_OBSERVACIONES WHERE COD_TUTORIA=TUT.T_CODIGO AND ESTADO_OBSERV = 2) as num_tareas_parcialmente,
              (SELECT count(*) FROM TABLA_OBSERVACIONES WHERE COD_TUTORIA=TUT.T_CODIGO AND ESTADO_OBSERV = 3) as num_tareas_cerrado ,
                (SELECT count(*) FROM PROGRAMACION_TUTORIA WHERE C_TUTORIA=TUT.T_CODIGO and DBMS_LOB.GETLENGTH(ACTA)>0) as num_actas
          FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT , TABLA_MAESTRA TM, CURSO CUR, ALUMNO ALU,PROFESOR PRO
         WHERE TUT.T_CODIGO  = PTUT.C_TUTORIA               /*06/05*/
           AND TUT.T_ANIO    = PTUT.ANIO                    /*06/05*/
           AND TUT.T_PERIODO = PTUT.PERIODO                 /*06/05*/
           AND TUT.C_CODIGO = CUR.C_CODIGO    
           AND TUT.A_CODIGO = ALU.A_CODIGO
           AND PTUT.SESION   = 1
            AND TM.NOMBRE_TABLA = 'FRECUENCIA'
               AND TM.NOMBRE_CAMPO = 'FRECUENCIA_TUTORIA'
               AND TM.ID_CAMPO   = TUT.T_FRECUENCIA
        
             
           AND TUT.T_ANIO = LISTAR_HORARIOSTUTORIA_DOCENTE.ANIO 
           AND TUT.T_PERIODO = LISTAR_HORARIOSTUTORIA_DOCENTE.PERIODO 
           AND TUT.P_CODIGO = LISTAR_HORARIOSTUTORIA_DOCENTE.COD_DOCENTE
           AND PRO.P_CODIGO  = LISTAR_HORARIOSTUTORIA_DOCENTE.COD_DOCENTE;
END LISTAR_HORARIOSTUTORIA_DOCENTE;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_HORARIOSTUTORIA_SEMANAL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_HORARIOSTUTORIA_SEMANAL" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_HORARIOSTUTORIA_SEMANAL                                */
/* Objetivo  : Listar los horarios de tutor?s semanales                     */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,
    DIA            TUTORIA.T_DIA%TYPE,    
    COD_CURSO      TUTORIA.C_CODIGO%TYPE,    
    HORARIO_C      OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
        
BEGIN            
    IF DIA = '%T%' THEN
        OPEN HORARIO_C FOR     
            SELECT TUT.T_ANIO  , TUT.T_PERIODO, TUT.T_CODIGO , TUT.A_CODIGO , 
                   TUT.P_CODIGO, TUT.T_DIA    , PTUT.HORA_INI, PTUT.HORA_FIN,
                   TUT.C_CODIGO, CUR.NOMBRE   , 
                   UPPER(TRIM(ALU.A_NOMBRE)) || ' ' || UPPER(TRIM(ALU.A_APELLIDOS)) AS A_NOMBRE, 
                   UPPER(TRIM(PRO.P_NOMBRE)) || ' ' || UPPER(TRIM(PRO.P_APELLIDOS)) AS P_NOMBRE,
                   TUT.C_REPITENCIAS 
              FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT, CURSO CUR, ALUMNO ALU, PROFESOR PRO
             WHERE TUT.T_ANIO    = PTUT.ANIO
               AND TUT.T_PERIODO = PTUT.PERIODO
               AND TUT.T_CODIGO  = PTUT.C_TUTORIA               
               AND TUT.C_CODIGO  = CUR.C_CODIGO               
               AND TUT.A_CODIGO  = ALU.A_CODIGO
               AND TUT.P_CODIGO  = PRO.P_CODIGO
               AND TUT.T_ANIO    = LISTAR_HORARIOSTUTORIA_SEMANAL.ANIO 
               AND TUT.T_PERIODO = LISTAR_HORARIOSTUTORIA_SEMANAL.PERIODO 
               AND TUT.C_CODIGO  = LISTAR_HORARIOSTUTORIA_SEMANAL.COD_CURSO               
             ORDER BY TUT.T_DIA, A_NOMBRE;
    ELSE
        OPEN HORARIO_C FOR     
            SELECT TUT.T_ANIO  , TUT.T_PERIODO, TUT.T_CODIGO , TUT.A_CODIGO , 
                   TUT.P_CODIGO, TUT.T_DIA    , PTUT.HORA_INI, PTUT.HORA_FIN,
                   TUT.C_CODIGO, CUR.NOMBRE   , 
                   UPPER(TRIM(ALU.A_NOMBRE)) || ' ' || UPPER(TRIM(ALU.A_APELLIDOS)) AS A_NOMBRE, 
                   UPPER(TRIM(PRO.P_NOMBRE)) || ' ' || UPPER(TRIM(PRO.P_APELLIDOS)) AS P_NOMBRE,
                   TUT.C_REPITENCIAS 
              FROM TUTORIA TUT, PROGRAMACION_TUTORIA PTUT, CURSO CUR, ALUMNO ALU, PROFESOR PRO
             WHERE TUT.T_ANIO    = PTUT.ANIO
               AND TUT.T_PERIODO = PTUT.PERIODO
               AND TUT.T_CODIGO  = PTUT.C_TUTORIA  
               AND TUT.C_CODIGO  = CUR.C_CODIGO               
               AND TUT.A_CODIGO  = ALU.A_CODIGO
               AND TUT.P_CODIGO  = PRO.P_CODIGO
               AND TUT.T_ANIO    = LISTAR_HORARIOSTUTORIA_SEMANAL.ANIO 
               AND TUT.T_PERIODO = LISTAR_HORARIOSTUTORIA_SEMANAL.PERIODO 
               AND TUT.C_CODIGO  = LISTAR_HORARIOSTUTORIA_SEMANAL.COD_CURSO
               AND TUT.T_DIA LIKE LISTAR_HORARIOSTUTORIA_SEMANAL.DIA
               ORDER BY A_NOMBRE;
    END IF;
    
END LISTAR_HORARIOSTUTORIA_SEMANAL;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_OBSERVACIONES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_OBSERVACIONES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_OBSERVACIONES                                          */
/* Objetivo  : Listar las observaciones de las tutor?s                      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_CURSO          TUTORIA.C_CODIGO%TYPE,
    COD_DOCENTE        TUTORIA.P_CODIGO%TYPE,
    COD_ALUMNO         TUTORIA.A_CODIGO%TYPE,
    TIPO_ALUMNO        TUTORIA.T_TIPO_ALUMNO%TYPE,
    C_OBSERVACIONES    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_OBSERVACIONES FOR    
        SELECT ANIO          , PERIODO     , COD_TUTORIA  , 
               ID_OBSERVACION,RAZON, TAREA , CRITICIDAD   , 
               SESION_REG    , FECHA_REG,FECHA_ENTREGA  , FECHA_CUMPLIMIENTO, 
               ESTADO_OBSERV
          FROM DBTUTORIA.TABLA_OBSERVACIONES
         WHERE ANIO    = ANIO_AUX
           AND PERIODO = PERIODO_AUX
           AND COD_TUTORIA IN (SELECT T_CODIGO 
                                 FROM TUTORIA 
                                WHERE T_ANIO    = ANIO_AUX 
                                  AND T_PERIODO = PERIODO_AUX
                                  AND C_CODIGO  = LISTAR_OBSERVACIONES.COD_CURSO
                                  AND P_CODIGO  = LISTAR_OBSERVACIONES.COD_DOCENTE
                                  AND A_CODIGO  = LISTAR_OBSERVACIONES.COD_ALUMNO
                                  AND T_TIPO_ALUMNO = LISTAR_OBSERVACIONES.TIPO_ALUMNO)
        ORDER BY SESION_REG, ID_OBSERVACION;                                      
END LISTAR_OBSERVACIONES;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_OBSERVACIONES_SESION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_OBSERVACIONES_SESION" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_OBSERVACIONES                                          */
/* Objetivo  : Listar las observaciones de las tutor?s                      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_CURSO          TUTORIA.C_CODIGO%TYPE,
    COD_DOCENTE        TUTORIA.P_CODIGO%TYPE,
    COD_ALUMNO         TUTORIA.A_CODIGO%TYPE,
    TIPO_ALUMNO        TUTORIA.T_TIPO_ALUMNO%TYPE,
    SESION             TABLA_OBSERVACIONES.SESION_REG %TYPE,
    C_OBSERVACIONES    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX     TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX  TUTORIA.T_PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_OBSERVACIONES FOR    
        SELECT ANIO          , PERIODO     , COD_TUTORIA  , 
               ID_OBSERVACION,RAZON, TAREA , CRITICIDAD   , 
               SESION_REG    , FECHA_ENTREGA  , FECHA_CUMPLIMIENTO, 
               ESTADO_OBSERV
          FROM DBTUTORIA.TABLA_OBSERVACIONES
         WHERE ANIO    = ANIO_AUX
           AND PERIODO = PERIODO_AUX
           AND SESION_REG= LISTAR_OBSERVACIONES_SESION.SESION
           AND COD_TUTORIA IN (SELECT T_CODIGO 
                                 FROM TUTORIA 
                                WHERE T_ANIO    = ANIO_AUX 
                                  AND T_PERIODO = PERIODO_AUX
                                  AND C_CODIGO  = LISTAR_OBSERVACIONES_SESION.COD_CURSO
                                  AND P_CODIGO  = LISTAR_OBSERVACIONES_SESION.COD_DOCENTE
                                  AND A_CODIGO  = LISTAR_OBSERVACIONES_SESION.COD_ALUMNO
                                  AND T_TIPO_ALUMNO = LISTAR_OBSERVACIONES_SESION.TIPO_ALUMNO)
          
        ORDER BY SESION_REG, ID_OBSERVACION;                                      
END LISTAR_OBSERVACIONES_SESION;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_PREGUNTAS_ENCUESTA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_PREGUNTAS_ENCUESTA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_PREGUNTAS_ENCUESTA                                     */
/* Objetivo  : Listar las preguntas de la tabla de preguntas                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (TIPO_PREGUNTA  TABLA_PREGUNTAS.TIPO_PREGUNTA%TYPE,    
    PREGUNTAS_C    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
        
BEGIN
    OPEN PREGUNTAS_C FOR     
        SELECT ID_PREGUNTA, PREGUNTA
          FROM TABLA_PREGUNTAS
         WHERE TIPO_PREGUNTA = LISTAR_PREGUNTAS_ENCUESTA.TIPO_PREGUNTA
         ORDER BY ID_PREGUNTA;
    
END LISTAR_PREGUNTAS_ENCUESTA;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_PROFESOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_PROFESOR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_PROFESOR                                               */
/* Objetivo  : Lista todos los profesores tutores                            */
/*---------------------------------------------------------------------------*/
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (LISTA_DOC   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
   
BEGIN
    OPEN LISTA_DOC FOR
        SELECT * FROM Profesor P;
END LISTAR_PROFESOR;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_SESIONES_CIERRE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_SESIONES_CIERRE" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_SESIONES_CIERRE                                        */
/* Objetivo  : Listar las sesiones de cierre en base a la sesi? de registro */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA        PROGRAMACION_TUTORIA.C_TUTORIA%TYPE,
    SESION_REG         PROGRAMACION_TUTORIA.SESION%TYPE,
    C_SESIONES         OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_AUX        TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX     TUTORIA.T_PERIODO%TYPE;
    ULTIMA_SESION   PROGRAMACION_TUTORIA.SESION%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    SELECT MAX(SESION)
      INTO ULTIMA_SESION 
      FROM PROGRAMACION_TUTORIA 
     WHERE ANIO      = LISTAR_SESIONES_CIERRE.ANIO_AUX
       AND PERIODO   = LISTAR_SESIONES_CIERRE.PERIODO_AUX 
       AND C_TUTORIA = LISTAR_SESIONES_CIERRE.COD_TUTORIA; 
    
    IF ULTIMA_SESION = SESION_REG THEN
        OPEN C_SESIONES FOR    
            SELECT SESION 
              FROM PROGRAMACION_TUTORIA 
             WHERE ANIO      = LISTAR_SESIONES_CIERRE.ANIO_AUX
               AND PERIODO   = LISTAR_SESIONES_CIERRE.PERIODO_AUX 
               AND C_TUTORIA = LISTAR_SESIONES_CIERRE.COD_TUTORIA
               AND SESION    = LISTAR_SESIONES_CIERRE.ULTIMA_SESION;    
    ELSE
        OPEN C_SESIONES FOR    
            SELECT SESION 
              FROM PROGRAMACION_TUTORIA 
             WHERE ANIO      = LISTAR_SESIONES_CIERRE.ANIO_AUX
               AND PERIODO   = LISTAR_SESIONES_CIERRE.PERIODO_AUX 
               AND C_TUTORIA = LISTAR_SESIONES_CIERRE.COD_TUTORIA
               AND SESION    > LISTAR_SESIONES_CIERRE.SESION_REG;
    END IF;
    
END LISTAR_SESIONES_CIERRE;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_SESIONES_TUTORIA
--------------------------------------------------------
set define off;

 create or replace PROCEDURE             "LISTAR_SESIONES_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_SESIONES_TUTORIA                                       */
/* Objetivo  : Listar las sesiones de una tutor?                            */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO               PROGRAMACION_TUTORIA.ANIO%TYPE,
    PERIODO            PROGRAMACION_TUTORIA.PERIODO%TYPE,    
    COD_CURSO          TUTORIA.C_CODIGO%TYPE,
    COD_TUTOR          TUTORIA.P_CODIGO%TYPE,
    COD_ALUMNO         TUTORIA.A_CODIGO%TYPE,        
    TIPO_ALUMNO        INTEGER,
    MODO               INTEGER,
    C_SESIONTUTORIA    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    IF MODO = 1 THEN --MODO ADMIN
        OPEN C_SESIONTUTORIA FOR
            SELECT PRGT.ANIO     , PRGT.PERIODO , PRGT.C_TUTORIA, PRGT.SESION, 
                   PRGT.FECHA_TUT, PRGT.HORA_INI, PRGT.HORA_FIN , PRGT.ESTADO
              FROM PROGRAMACION_TUTORIA PRGT
             WHERE C_TUTORIA IN (SELECT TUT.T_CODIGO 
                                   FROM TUTORIA TUT
                                  WHERE TUT.T_ANIO    = LISTAR_SESIONES_TUTORIA.ANIO 
                                    AND TUT.T_PERIODO = LISTAR_SESIONES_TUTORIA.PERIODO 
                                    AND TUT.C_CODIGO  = LISTAR_SESIONES_TUTORIA.COD_CURSO
                                    
                                    AND TUT.A_CODIGO  = LISTAR_SESIONES_TUTORIA.COD_ALUMNO
                                    AND TUT.T_TIPO_ALUMNO = LISTAR_SESIONES_TUTORIA.TIPO_ALUMNO)
              AND PRGT.ANIO    = LISTAR_SESIONES_TUTORIA.ANIO
              AND PRGT.PERIODO = LISTAR_SESIONES_TUTORIA.PERIODO;              
    ELSE
        IF MODO = 2 THEN --MODO TUTOR
            OPEN C_SESIONTUTORIA FOR
            SELECT PRGT.ANIO     , PRGT.PERIODO , PRGT.C_TUTORIA, PRGT.SESION, 
                   PRGT.FECHA_TUT, PRGT.HORA_INI, PRGT.HORA_FIN , PRGT.ESTADO
              FROM PROGRAMACION_TUTORIA PRGT
             WHERE C_TUTORIA IN (SELECT TUT.T_CODIGO 
                                   FROM TUTORIA TUT
                                  WHERE TUT.T_ANIO    = LISTAR_SESIONES_TUTORIA.ANIO 
                                    AND TUT.T_PERIODO = LISTAR_SESIONES_TUTORIA.PERIODO 
                                    AND TUT.C_CODIGO  = LISTAR_SESIONES_TUTORIA.COD_CURSO
                                    AND TUT.P_CODIGO IN (SELECT COD_SISTEMA  
                                                           FROM USUARIO_ROL_EQUIVALENCIA
                                                          WHERE COD_USUARIO = LISTAR_SESIONES_TUTORIA.COD_TUTOR)
                                    AND TUT.A_CODIGO  = LISTAR_SESIONES_TUTORIA.COD_ALUMNO
                                    AND TUT.T_TIPO_ALUMNO = LISTAR_SESIONES_TUTORIA.TIPO_ALUMNO)
              AND PRGT.ANIO    = LISTAR_SESIONES_TUTORIA.ANIO
              AND PRGT.PERIODO = LISTAR_SESIONES_TUTORIA.PERIODO;              
        END IF;        
    END IF;  
    
END LISTAR_SESIONES_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_TEMA_APROBADO_TUTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_TEMA_APROBADO_TUTOR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_TEMA_APROBADO_TUTOR                                       */
/* Objetivo  : Listar todas las Ã¡reas del conocimiento registradas por tutor y aprobados          */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: Alex Naupay                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTOR   ALUMNO_PAR.A_CODIGO%TYPE , COD_CURSO   CURSO.C_CODIGO%TYPE,  C_TEMA OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN
    OPEN C_TEMA FOR
         SELECT TP.CODIGO_TEMA,	TP.C_CODIGO, TP.NOMBRE_TEMA, TP.DESCRIPCION_TEMA   FROM  POSTULACION_PAR PP
					INNER JOIN CONVOCATORIA_PAR CP ON PP.CODCONVOCATOR = CP.CODCONVOCATOR
					INNER JOIN CICLO CI ON CP.ID_CICLO  = CI.ID_CICLO AND CI.ACTIVO=1
					INNER JOIN ASOC_POSTULACION_TEMA_PAR  APTP ON  APTP. COD_POSTULACION = PP.COD_POSTULACION AND APTP.FLAG_APROBACION=1
					INNER JOIN TEMAS_PAR TP ON APTP.CODIGO_TEMA = TP.CODIGO_TEMA AND TP.C_CODIGO =  LISTAR_TEMA_APROBADO_TUTOR.COD_CURSO
					WHERE PP.A_CODIGO=LISTAR_TEMA_APROBADO_TUTOR.COD_TUTOR;

END LISTAR_TEMA_APROBADO_TUTOR;

/
--------------------------------------------------------
--  DDL for Procedure LISTAR_TUTORES_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."LISTAR_TUTORES_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : LISTAR_TUTORES_REGULARES                                      */
/* Objetivo  : Buscar todos los tutores regulares para el ciclo actual       */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (C_TUTORES    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
    
    ANIO_ACTUAL     DOCENTE_REGULAR.ANIO%TYPE;
    PERIODO_ACTUAL  DOCENTE_REGULAR.PERIODO%TYPE;
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_ACTUAL, PERIODO_ACTUAL
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    OPEN C_TUTORES FOR
        SELECT TRIM(COD_DOCENTE), UPPER(TRIM(NOM_DOCENTE))
          FROM DOCENTE_REGULAR DR
         WHERE DR.ANIO    = ANIO_ACTUAL
           AND DR.PERIODO = PERIODO_ACTUAL;          
    
END LISTAR_TUTORES_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure OBTENER_ACTA_TUTORIA
--------------------------------------------------------
set define off;

create or replace PROCEDURE             "OBTENER_ACTA_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : OBTENER_ACTA_TUTORIA                                          */
/* Objetivo  : Obtener los datos del acta de una sesi? de tutor?           */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO               PROGRAMACION_TUTORIA.ANIO%TYPE,
    PERIODO            PROGRAMACION_TUTORIA.PERIODO%TYPE,    
    COD_CURSO          TUTORIA.C_CODIGO%TYPE,
    COD_TUTOR          TUTORIA.P_CODIGO%TYPE,
    COD_ALUMNO         TUTORIA.A_CODIGO%TYPE,
    SESION             PROGRAMACION_TUTORIA.SESION%TYPE,
    MODO               INTEGER,
    TIPO_ALUMNO        INTEGER,
    C_SESIONTUTORIA    OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS

BEGIN        
    IF MODO = 1 THEN --MODO ADMIN
        OPEN C_SESIONTUTORIA FOR
            SELECT PRGT.ANIO  , PRGT.PERIODO, PRGT.C_TUTORIA  , PRGT.SESION, 
                   PRGT.ESTADO, PRGT.ACTA   , PRGT.NOMBRE_ACTA, PRGT.ESTADO_ACTA
              FROM PROGRAMACION_TUTORIA PRGT
             WHERE C_TUTORIA IN (SELECT TUT.T_CODIGO 
                                   FROM TUTORIA TUT
                                  WHERE TUT.T_ANIO    = OBTENER_ACTA_TUTORIA.ANIO 
                                    AND TUT.T_PERIODO = OBTENER_ACTA_TUTORIA.PERIODO 
                                    AND TUT.C_CODIGO  = OBTENER_ACTA_TUTORIA.COD_CURSO
                                    
                                    AND TUT.A_CODIGO  = OBTENER_ACTA_TUTORIA.COD_ALUMNO
                                    AND TUT.T_TIPO_ALUMNO = OBTENER_ACTA_TUTORIA.TIPO_ALUMNO)
              AND PRGT.ANIO    = OBTENER_ACTA_TUTORIA.ANIO
              AND PRGT.PERIODO = OBTENER_ACTA_TUTORIA.PERIODO
              AND PRGT.SESION  = OBTENER_ACTA_TUTORIA.SESION;
    ELSE
        IF MODO = 2 THEN --MODO TUTOR
            OPEN C_SESIONTUTORIA FOR
            SELECT PRGT.ANIO  , PRGT.PERIODO, PRGT.C_TUTORIA  , PRGT.SESION, 
                   PRGT.ESTADO, PRGT.ACTA   , PRGT.NOMBRE_ACTA, PRGT.ESTADO_ACTA
              FROM PROGRAMACION_TUTORIA PRGT
             WHERE C_TUTORIA IN (SELECT TUT.T_CODIGO 
                                   FROM TUTORIA TUT
                                  WHERE TUT.T_ANIO    = OBTENER_ACTA_TUTORIA.ANIO 
                                    AND TUT.T_PERIODO = OBTENER_ACTA_TUTORIA.PERIODO 
                                    AND TUT.C_CODIGO  = OBTENER_ACTA_TUTORIA.COD_CURSO
                                    AND TUT.P_CODIGO IN (SELECT COD_SISTEMA  
                                                           FROM USUARIO_ROL_EQUIVALENCIA
                                                          WHERE COD_USUARIO = OBTENER_ACTA_TUTORIA.COD_TUTOR)
                                    AND TUT.A_CODIGO  = OBTENER_ACTA_TUTORIA.COD_ALUMNO
                                    AND TUT.T_TIPO_ALUMNO = OBTENER_ACTA_TUTORIA.TIPO_ALUMNO)
              AND PRGT.ANIO    = OBTENER_ACTA_TUTORIA.ANIO
              AND PRGT.PERIODO = OBTENER_ACTA_TUTORIA.PERIODO
              AND PRGT.SESION  = OBTENER_ACTA_TUTORIA.SESION;
        END IF;        
    END IF;  
    
END OBTENER_ACTA_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure OBTENER_ACTIVIDAD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."OBTENER_ACTIVIDAD" 
( COD_ACT  PROG_ACADEMICA_PAR.COD_PROGR%TYPE ,C_ACTIVITIES  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
OPEN C_ACTIVITIES FOR
	SELECT PA.* , (SELECT  PA.NUMVACANTES-COUNT(COD_PROGR)  AS RESTANTES FROM MATRICULA_PAR WHERE MATRICULA_PAR.COD_PROGR = PA.COD_PROGR)  AS VACANTES_RESTANTES
FROM PROG_ACADEMICA_PAR PA WHERE PA.COD_PROGR = OBTENER_ACTIVIDAD.COD_ACT ;
END OBTENER_ACTIVIDAD;

/
--------------------------------------------------------
--  DDL for Procedure PROCESAR_TILDES_CADENA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."PROCESAR_TILDES_CADENA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : PROCESAR_TILDES_CADENA                                        */
/* Objetivo  : Procesar la cadena para corregir las tildes defectuosas       */
/*             de tutor?                                                    */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (CAD_ENTRADA     VARCHAR,
    CAD_SALIDA      OUT VARCHAR) AUTHID CURRENT_USER AS
    
    CAD_PROCESO     VARCHAR(250) := '';
   
BEGIN        
    CAD_PROCESO := CAD_ENTRADA;
    
    dbms_output.put_line(CAD_ENTRADA);
    
    
    IF CAD_PROCESO = '' OR CAD_PROCESO IS NULL THEN
        CAD_SALIDA := '';
    ELSE
        CAD_PROCESO := CAD_ENTRADA;
        
        SELECT REPLACE(CAD_PROCESO, 'Ã¡', 'á') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'Ã', 'Á') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'Á©', 'é') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'Á', 'É') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'É­', 'í') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'É', 'Í') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'Ã³', 'ó') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'Í³', 'ó') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'Í', 'Ó') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'Ãº', 'ú') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'ó', 'Ú') INTO CAD_PROCESO FROM DUAL;
        SELECT REPLACE(CAD_PROCESO, 'ó±', 'ñ') INTO CAD_PROCESO FROM DUAL;
        
        
        CAD_SALIDA := CAD_PROCESO;
        
    END IF;
    
END PROCESAR_TILDES_CADENA;

/
--------------------------------------------------------
--  DDL for Procedure PROCESAR_TUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."PROCESAR_TUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : PROCESAR_TUTORIA                                              */
/* Objetivo  : Realizar las operaciones de inserci? y actualizaci? de las  */
/*             tutor?s                                                      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,
    COD_CURSO      TUTORIA.T_CODIGO%TYPE,
    COD_ALUMNO     TUTORIA.A_CODIGO%TYPE,
    COD_DOCENTE    TUTORIA.P_CODIGO%TYPE,
    DIA            TUTORIA.T_DIA%TYPE,
    H_INICIO       PROGRAMACION_TUTORIA.HORA_INI%TYPE,
    H_FIN          PROGRAMACION_TUTORIA.HORA_FIN%TYPE,
    COD_TUTORIA    VARCHAR,
    FRECUENCIA     INTEGER,
    MODO           INT) AUTHID CURRENT_USER AS
    
    COD_TUTORIA_NUEVO   VARCHAR(8);
    
BEGIN            
    IF MODO = 1 THEN
        GENERAR_TUTORIA(ANIO        => PROCESAR_TUTORIA.ANIO,
                        PERIODO     => PROCESAR_TUTORIA.PERIODO,
                        COD_TUTORIA => PROCESAR_TUTORIA.COD_TUTORIA_NUEVO);
                        
        IF COD_TUTORIA_NUEVO IS NULL THEN
            COD_TUTORIA_NUEVO := 'T0000001';
        END IF;
        
        INSERT INTO TUTORIA VALUES (ANIO      , PERIODO    , COD_TUTORIA_NUEVO,
                                    COD_ALUMNO, COD_DOCENTE, COD_CURSO        ,
                                    4         , FRECUENCIA , DIA              ,
                                    1);
                                    
        PROGRAMAR_HORARIOSTUTORIA(ANIO       => PROCESAR_TUTORIA.ANIO,
                                  PERIODO    => PROCESAR_TUTORIA.PERIODO,
                                  T_CODIGO   => COD_TUTORIA_NUEVO,
                                  DIA        => PROCESAR_TUTORIA.DIA,
                                  FRECUENCIA => PROCESAR_TUTORIA.FRECUENCIA,
                                  HORA_INI   => PROCESAR_TUTORIA.H_INICIO,
                                  HORA_FIN   => PROCESAR_TUTORIA.H_FIN);     
    ELSE
         DELETE PROGRAMACION_TUTORIA
          WHERE ANIO      = PROCESAR_TUTORIA.ANIO
            AND PERIODO   = PROCESAR_TUTORIA.PERIODO
            AND C_TUTORIA = PROCESAR_TUTORIA.COD_TUTORIA;            
            
         PROGRAMAR_HORARIOSTUTORIA(ANIO       => PROCESAR_TUTORIA.ANIO,
                                   PERIODO    => PROCESAR_TUTORIA.PERIODO,
                                   T_CODIGO   => PROCESAR_TUTORIA.COD_TUTORIA,
                                   DIA        => PROCESAR_TUTORIA.DIA,
                                   FRECUENCIA => PROCESAR_TUTORIA.FRECUENCIA,
                                   HORA_INI   => PROCESAR_TUTORIA.H_INICIO,
                                   HORA_FIN   => PROCESAR_TUTORIA.H_FIN);
        
        UPDATE TUTORIA 
           SET P_CODIGO = PROCESAR_TUTORIA.COD_DOCENTE, 
               T_DIA    = PROCESAR_TUTORIA.DIA               
         WHERE T_CODIGO = PROCESAR_TUTORIA.COD_TUTORIA;
         
    END IF; 
    
    COMMIT;  

END PROCESAR_TUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure PROCESAR_TUTORIA_OBSERVADOS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."PROCESAR_TUTORIA_OBSERVADOS" 
/*---------------------------------------------------------------------------*/
/* Nombre    : PROCESAR_TUTORIA_OBSERVADOS                                   */
/* Objetivo  : Realizar las operaciones de inserci? y actualizaci? de las  */
/*             tutor?s de alumnos observados                                */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_ALUMNO     TUTORIA.A_CODIGO%TYPE,
    ALU_APE_PAT    ALUMNO.A_APELLIDOS%TYPE,
    ALU_APE_MAT    ALUMNO.A_APELLIDOS%TYPE,
    ALU_NOMBRES    ALUMNO.A_NOMBRE%TYPE,
    ALU_PLAN       ALUMNO.A_PLAN%TYPE,
    COD_CURSO      TUTORIA.C_CODIGO%TYPE,
    REPITENCIAS    TUTORIA.C_REPITENCIAS%TYPE,
    COD_DOCENTE    TUTORIA.P_CODIGO%TYPE,
    FRECUENCIA     TUTORIA.T_FRECUENCIA%TYPE,
    DIA            TUTORIA.T_DIA%TYPE,
    H_INICIO       PROGRAMACION_TUTORIA.HORA_INI%TYPE,
    H_FIN          PROGRAMACION_TUTORIA.HORA_FIN%TYPE,
    TIPO_ALUMNO    TUTORIA.T_TIPO_ALUMNO%TYPE,
    COD_USUARIO    USUARIO.ID_USUARIO%TYPE) AUTHID CURRENT_USER AS
    
    COD_TUTORIA_NUEVO   VARCHAR(8);
    COD_ALUMNO_AUX      TUTORIA.A_CODIGO%TYPE;
    ANIO_AUX            TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX         TUTORIA.T_PERIODO%TYPE;    
    
BEGIN            
    BEGIN
        SELECT A_CODIGO
          INTO COD_ALUMNO_AUX
          FROM ALUMNO
         WHERE A_CODIGO = PROCESAR_TUTORIA_OBSERVADOS.COD_ALUMNO;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            COD_ALUMNO_AUX := NULL;
    END;    
     
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX 
      FROM CICLO
     WHERE ACTIVO = 1;
    
    IF COD_ALUMNO_AUX IS NULL THEN
        INSERTAR_ALUMNO(A_CODIGO      => PROCESAR_TUTORIA_OBSERVADOS.COD_ALUMNO,
                        A_NOMBRE      => PROCESAR_TUTORIA_OBSERVADOS.ALU_NOMBRES,
                        A_APELLIDOS   => PROCESAR_TUTORIA_OBSERVADOS.ALU_APE_PAT 
                                         || ' ' ||
                                         PROCESAR_TUTORIA_OBSERVADOS.ALU_APE_MAT,
                        A_FNACIMIENTO => NULL,
                        A_DIRECCION   => NULL,
                        A_EMAIL       => NULL,
                        A_TELEFONO    => NULL,
                        A_DNI         => NULL,
                        A_PLAN        => PROCESAR_TUTORIA_OBSERVADOS.ALU_PLAN);
    END IF;
    
    GENERAR_TUTORIA(ANIO        => PROCESAR_TUTORIA_OBSERVADOS.ANIO_AUX,
                    PERIODO     => PROCESAR_TUTORIA_OBSERVADOS.PERIODO_AUX,
                    COD_TUTORIA => PROCESAR_TUTORIA_OBSERVADOS.COD_TUTORIA_NUEVO);

    
    INSERTAR_TUTORIA(T_ANIO        => PROCESAR_TUTORIA_OBSERVADOS.ANIO_AUX,
                     T_PERIODO     => PROCESAR_TUTORIA_OBSERVADOS.PERIODO_AUX,
                     T_CODIGO      => PROCESAR_TUTORIA_OBSERVADOS.COD_TUTORIA_NUEVO,
                     A_CODIGO      => PROCESAR_TUTORIA_OBSERVADOS.COD_ALUMNO,
                     P_CODIGO      => PROCESAR_TUTORIA_OBSERVADOS.COD_DOCENTE,
                     C_CODIGO      => PROCESAR_TUTORIA_OBSERVADOS.COD_CURSO,
                     C_REPITENCIAS => PROCESAR_TUTORIA_OBSERVADOS.REPITENCIAS,
                     T_FRECUENCIA  => PROCESAR_TUTORIA_OBSERVADOS.FRECUENCIA,
                     T_DIA         => PROCESAR_TUTORIA_OBSERVADOS.DIA,
                     T_TIPO_ALUMNO => PROCESAR_TUTORIA_OBSERVADOS.TIPO_ALUMNO);
                     
    PROGRAMAR_HORARIOSTUTORIA(ANIO       => PROCESAR_TUTORIA_OBSERVADOS.ANIO_AUX,
                              PERIODO    => PROCESAR_TUTORIA_OBSERVADOS.PERIODO_AUX,
                              T_CODIGO   => COD_TUTORIA_NUEVO,
                              DIA        => PROCESAR_TUTORIA_OBSERVADOS.DIA,
                              FRECUENCIA => PROCESAR_TUTORIA_OBSERVADOS.FRECUENCIA,
                              HORA_INI   => PROCESAR_TUTORIA_OBSERVADOS.H_INICIO,
                              HORA_FIN   => PROCESAR_TUTORIA_OBSERVADOS.H_FIN);
    COMMIT;
END PROCESAR_TUTORIA_OBSERVADOS;

/
--------------------------------------------------------
--  DDL for Procedure PROCESAR_TUTORIA_REGULARES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."PROCESAR_TUTORIA_REGULARES" 
/*---------------------------------------------------------------------------*/
/* Nombre    : PROCESAR_TUTORIA_REGULARES                                    */
/* Objetivo  : Realizar las operaciones de inserci? y actualizaci? de las  */
/*             tutor?s de alumnos regulares                                 */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,
    COD_CURSO      TUTORIA.C_CODIGO%TYPE,
    COD_ALUMNO     TUTORIA.A_CODIGO%TYPE,
    COD_DOCENTE    TUTORIA.P_CODIGO%TYPE,
    DIA            TUTORIA.T_DIA%TYPE,
    H_INICIO       PROGRAMACION_TUTORIA.HORA_INI%TYPE,
    H_FIN          PROGRAMACION_TUTORIA.HORA_FIN%TYPE,
    COD_TUTORIA    VARCHAR,
    FRECUENCIA     INTEGER,
    MODO           INT) AUTHID CURRENT_USER AS
    
    COD_TUTORIA_NUEVO   VARCHAR(8);
    DIA_AUX             TUTORIA.T_DIA%TYPE;
    
BEGIN            
    SELECT DECODE(DIA, 'MIERCOLES', 'MIÉRCOLES', 'SABADO', 'SÁBADO', DIA)
      INTO DIA_AUX
      FROM DUAL;
    
    IF MODO = 1 THEN
        GENERAR_TUTORIA(ANIO        => PROCESAR_TUTORIA_REGULARES.ANIO,
                        PERIODO     => PROCESAR_TUTORIA_REGULARES.PERIODO,
                        COD_TUTORIA => PROCESAR_TUTORIA_REGULARES.COD_TUTORIA_NUEVO);
                        
        IF COD_TUTORIA_NUEVO IS NULL THEN
            COD_TUTORIA_NUEVO := 'T0000001';
        END IF;
        
        INSERT INTO TUTORIA VALUES (ANIO      , PERIODO    , COD_TUTORIA_NUEVO,
                                    COD_ALUMNO, COD_DOCENTE, COD_CURSO        ,
                                    3         , FRECUENCIA , DIA_AUX          ,
                                    2);
                                    
        PROGRAMAR_HORARIOSTUTORIA(ANIO       => PROCESAR_TUTORIA_REGULARES.ANIO,
                                  PERIODO    => PROCESAR_TUTORIA_REGULARES.PERIODO,
                                  T_CODIGO   => COD_TUTORIA_NUEVO,
                                  DIA        => PROCESAR_TUTORIA_REGULARES.DIA_AUX,
                                  FRECUENCIA => PROCESAR_TUTORIA_REGULARES.FRECUENCIA,
                                  HORA_INI   => PROCESAR_TUTORIA_REGULARES.H_INICIO,
                                  HORA_FIN   => PROCESAR_TUTORIA_REGULARES.H_FIN);     
    ELSE
         DELETE PROGRAMACION_TUTORIA
          WHERE ANIO      = PROCESAR_TUTORIA_REGULARES.ANIO
            AND PERIODO   = PROCESAR_TUTORIA_REGULARES.PERIODO
            AND C_TUTORIA = PROCESAR_TUTORIA_REGULARES.COD_TUTORIA;            
            
         PROGRAMAR_HORARIOSTUTORIA(ANIO       => PROCESAR_TUTORIA_REGULARES.ANIO,
                                   PERIODO    => PROCESAR_TUTORIA_REGULARES.PERIODO,
                                   T_CODIGO   => PROCESAR_TUTORIA_REGULARES.COD_TUTORIA,
                                   DIA        => PROCESAR_TUTORIA_REGULARES.DIA_AUX,
                                   FRECUENCIA => PROCESAR_TUTORIA_REGULARES.FRECUENCIA,
                                   HORA_INI   => PROCESAR_TUTORIA_REGULARES.H_INICIO,
                                   HORA_FIN   => PROCESAR_TUTORIA_REGULARES.H_FIN);
        
        UPDATE TUTORIA 
           SET C_CODIGO = PROCESAR_TUTORIA_REGULARES.COD_CURSO,
               P_CODIGO = PROCESAR_TUTORIA_REGULARES.COD_DOCENTE, 
               T_DIA    = PROCESAR_TUTORIA_REGULARES.DIA_AUX
         WHERE T_CODIGO = PROCESAR_TUTORIA_REGULARES.COD_TUTORIA;
         
    END IF;   
    
    COMMIT;

END PROCESAR_TUTORIA_REGULARES;

/
--------------------------------------------------------
--  DDL for Procedure PROGRAMAR_HORARIOSTUTORIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."PROGRAMAR_HORARIOSTUTORIA" 
/*---------------------------------------------------------------------------*/
/* Nombre    : PROGRAMAR_HORARIOSTUTORIA                                     */
/* Objetivo  : Programar los horarios de las tutor?s que se registren       */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO           TUTORIA.T_ANIO%TYPE,
    PERIODO        TUTORIA.T_PERIODO%TYPE,    
    T_CODIGO       TUTORIA.T_CODIGO%TYPE,  
    DIA            TUTORIA.T_DIA%TYPE,
    FRECUENCIA     INTEGER,  
    HORA_INI       PROGRAMACION_TUTORIA.HORA_INI%TYPE DEFAULT NULL,
    HORA_FIN       PROGRAMACION_TUTORIA.HORA_FIN%TYPE DEFAULT NULL) AUTHID CURRENT_USER AS
    
    FECHA_INICIO   CICLO_TUTORIA.FECHA_INI%TYPE;
    FECHA_FIN      CICLO_TUTORIA.FECHA_FIN%TYPE;
    FECHA_AUX      DATE;
        
BEGIN            
    SELECT FECHA_INI, FECHA_FIN
      INTO FECHA_INICIO, FECHA_FIN
      FROM CICLO_TUTORIA
     WHERE ANIO = PROGRAMAR_HORARIOSTUTORIA.ANIO
       AND PERIODO = PROGRAMAR_HORARIOSTUTORIA.PERIODO;
       
     FECHA_AUX := FECHA_INICIO - 7;
     FECHA_AUX := NEXT_DAY(FECHA_AUX, DIA);     
     
     IF FRECUENCIA = 1 THEN
        FOR I IN 1..16 LOOP            
            IF FECHA_AUX <= FECHA_FIN THEN
                INSERT INTO PROGRAMACION_TUTORIA VALUES (PROGRAMAR_HORARIOSTUTORIA.ANIO,
                                                         PROGRAMAR_HORARIOSTUTORIA.PERIODO,
                                                         PROGRAMAR_HORARIOSTUTORIA.T_CODIGO,
                                                         I,
                                                         FECHA_AUX,
                                                         PROGRAMAR_HORARIOSTUTORIA.HORA_INI,
                                                         PROGRAMAR_HORARIOSTUTORIA.HORA_FIN,
                                                         'F',
                                                         NULL,
                                                         NULL,
                                                         0);
                
                FECHA_AUX := FECHA_AUX + 7;                           
            END IF;            
        END LOOP;        
     ELSE        
        IF FRECUENCIA = 2 THEN
            FOR I IN 1..8 LOOP            
            IF FECHA_AUX <= FECHA_FIN THEN
                INSERT INTO PROGRAMACION_TUTORIA VALUES (PROGRAMAR_HORARIOSTUTORIA.ANIO,
                                                         PROGRAMAR_HORARIOSTUTORIA.PERIODO,
                                                         PROGRAMAR_HORARIOSTUTORIA.T_CODIGO,
                                                         I,
                                                         FECHA_AUX,
                                                         PROGRAMAR_HORARIOSTUTORIA.HORA_INI,
                                                         PROGRAMAR_HORARIOSTUTORIA.HORA_FIN,
                                                         'F',
                                                         EMPTY_BLOB(),
                                                         NULL,
                                                         0);
                
                FECHA_AUX := FECHA_AUX + 14;                           
            END IF;            
        END LOOP;
        END IF;        
     END IF;

END PROGRAMAR_HORARIOSTUTORIA;

/
--------------------------------------------------------
--  DDL for Procedure REGISTRAR_ASISTENCIA_ALUMNO_T
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."REGISTRAR_ASISTENCIA_ALUMNO_T" 
/*---------------------------------------------------------------------------*/
/* Nombre    : REGISTRAR_ASISTENCIA_ALUMNO_T                                 */
/* Objetivo  : Registrar las asistencias de los alumnos a las sesiones       */
/*             de tutor?                                                    */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_TUTORIA      ASISTENCIA_T_ALUM.C_TUTORIA%TYPE,
    SESION           ASISTENCIA_T_ALUM.SESION%TYPE,    
    FECHA_TUT        VARCHAR,
    ASISTENCIA       ASISTENCIA_T_ALUM.ASISTENCIA_T%TYPE,
    JUSTIFICACION    ASISTENCIA_T_ALUM.JUSTIFICACION%TYPE,
    OBSERVACION      ASISTENCIA_T_ALUM.OBSERVACION_T%TYPE,
    TIPO_ALUMNO      INTEGER,
    MODO             INTEGER) AUTHID CURRENT_USER AS
    
    ANIO_AUX         TUTORIA.T_ANIO%TYPE;
    PERIODO_AUX      TUTORIA.T_PERIODO%TYPE;
    OBSERVACION_AUX  ASISTENCIA_T_ALUM.OBSERVACION_T%TYPE;
    FECHA_ACTUAL     VARCHAR(10);
   
BEGIN        
    SELECT ANIO, PERIODO
      INTO ANIO_AUX, PERIODO_AUX
      FROM CICLO
     WHERE ACTIVO = 1;    
    
    INSERTAR_TRAZA(OPERACION => 'ASISTENCIA',
                   PARAMETRO => OBSERVACION);
    
    PROCESAR_TILDES_CADENA(CAD_ENTRADA => OBSERVACION,
                           CAD_SALIDA  => OBSERVACION_AUX);
    
    INSERT INTO ASISTENCIA_T_ALUM VALUES (REGISTRAR_ASISTENCIA_ALUMNO_T.ANIO_AUX     , 
                                          REGISTRAR_ASISTENCIA_ALUMNO_T.PERIODO_AUX  , 
                                          REGISTRAR_ASISTENCIA_ALUMNO_T.COD_TUTORIA  , 
                                          REGISTRAR_ASISTENCIA_ALUMNO_T.SESION       , 
                                          REGISTRAR_ASISTENCIA_ALUMNO_T.FECHA_TUT    ,
                                          REGISTRAR_ASISTENCIA_ALUMNO_T.ASISTENCIA   ,
                                          REGISTRAR_ASISTENCIA_ALUMNO_T.JUSTIFICACION,
                                          OBSERVACION_AUX);
                                          
    SELECT TO_CHAR(SYSDATE, 'DD/MM/YYYY') 
      INTO FECHA_ACTUAL
      FROM DUAL;
      
    IF MODO = 1 THEN--MODO ADMIN
        IF FECHA_TUT < FECHA_ACTUAL THEN
            UPDATE PROGRAMACION_TUTORIA 
               SET ESTADO = 'E' 
             WHERE C_TUTORIA = REGISTRAR_ASISTENCIA_ALUMNO_T.COD_TUTORIA
               AND SESION = REGISTRAR_ASISTENCIA_ALUMNO_T.SESION;
        ELSE
            UPDATE PROGRAMACION_TUTORIA 
               SET ESTADO = 'R' 
             WHERE C_TUTORIA = REGISTRAR_ASISTENCIA_ALUMNO_T.COD_TUTORIA
               AND SESION = REGISTRAR_ASISTENCIA_ALUMNO_T.SESION;
        END IF;
    ELSE --MODO TUTOR
        UPDATE PROGRAMACION_TUTORIA 
               SET ESTADO = 'R' 
             WHERE C_TUTORIA = REGISTRAR_ASISTENCIA_ALUMNO_T.COD_TUTORIA
               AND SESION = REGISTRAR_ASISTENCIA_ALUMNO_T.SESION;
    END IF;
    
    COMMIT;                                        
   
END REGISTRAR_ASISTENCIA_ALUMNO_T;

/
--------------------------------------------------------
--  DDL for Procedure USP_AREA_TEMA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_AREA_TEMA" 
(COD_TEMA   TEMAS_PAR.CODIGO_TEMA%TYPE,
    AR_AREA OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
    OPEN AR_AREA FOR
        SELECT AR.* FROM CURSO C
				INNER JOIN  AREA_CONOCIMIENTO AR ON AR.COD_AREA = C.AREA_C
				INNER JOIN TEMAS_PAR TP ON C.C_CODIGO = TP.C_CODIGO
				WHERE TP. CODIGO_TEMA = USP_AREA_TEMA.COD_TEMA;
				
END USP_AREA_TEMA;

/
--------------------------------------------------------
--  DDL for Procedure USP_BUSCAR_MATRICULA_PAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_BUSCAR_MATRICULA_PAR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : USP_GUARDAR_MATRICULA_PAR                                             */
/* Objetivo  : Guarada la matricula de un alumno a la actividad acadÃ©mica  */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: @alexh                                          */
/*---------------------------------------------------------------------------*/
(
COD_ALUMNO MATRICULA_PAR.A_CODIGO%TYPE,
COD_PROG MATRICULA_PAR.COD_PROGR%TYPE,
OUT_CUR_MATRICULA  OUT SYS_REFCURSOR
 ) AUTHID CURRENT_USER AS
BEGIN 
		OPEN OUT_CUR_MATRICULA FOR
				SELECT * FROM MATRICULA_PAR MP WHERE MP.A_CODIGO=USP_BUSCAR_MATRICULA_PAR.COD_ALUMNO AND MP.COD_PROGR=USP_BUSCAR_MATRICULA_PAR.COD_PROG;
END USP_BUSCAR_MATRICULA_PAR;

/
--------------------------------------------------------
--  DDL for Procedure USP_CURSO_TEMA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_CURSO_TEMA" 
(COD_TEMA   TEMAS_PAR.CODIGO_TEMA%TYPE,
    C_CURSO   OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
    OPEN C_CURSO FOR
        SELECT C.* FROM CURSO C
				INNER JOIN TEMAS_PAR TP ON C.C_CODIGO = TP.C_CODIGO AND TP.CODIGO_TEMA= USP_CURSO_TEMA.COD_TEMA ;
END USP_CURSO_TEMA;

/
--------------------------------------------------------
--  DDL for Procedure USP_GUARDAR_ACTIVIDAD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_GUARDAR_ACTIVIDAD" 
/*---------------------------------------------------------------------------*/
/* Nombre    : USP_GUARDAR_ACTIVIDAD                                             */
/* Objetivo  : Guarada una actividades academica de un usuario  */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: @alexh                                          */
/*---------------------------------------------------------------------------*/
(COD_TUTOR  ALUMNO_PAR.A_CODIGO%TYPE, 
	NOMBRE_ACTIVIDAD PROG_ACADEMICA_PAR.NOMBRE%TYPE,
  COD_TEMA TEMAS_PAR.CODIGO_TEMA%TYPE,
	NUMERO_VANCANTES PROG_ACADEMICA_PAR.NUMVACANTES%TYPE,
	COD_PROG OUT NUMBER
 ) AUTHID CURRENT_USER AS

POSTULACION_ID NUMBER;

BEGIN 
 SELECT APTP. COD_POSTULACION INTO POSTULACION_ID  FROM  POSTULACION_PAR PP
					INNER JOIN CONVOCATORIA_PAR CP ON PP.CODCONVOCATOR = CP.CODCONVOCATOR
					INNER JOIN CICLO CI ON CP.ID_CICLO  = CI.ID_CICLO AND CI.ACTIVO=1
					INNER JOIN ASOC_POSTULACION_TEMA_PAR  APTP ON  APTP. COD_POSTULACION = PP.COD_POSTULACION AND APTP.FLAG_APROBACION=1 AND APTP.CODIGO_TEMA = USP_GUARDAR_ACTIVIDAD.COD_TEMA
					WHERE PP.A_CODIGO=USP_GUARDAR_ACTIVIDAD.COD_TUTOR;

INSERT INTO PROG_ACADEMICA_PAR( "COD_PROGR","COD_POSTULACION", "CODIGO_TEMA", "NUMVACANTES", "NOMBRE") VALUES ( SEC_COD_PROGRAM_ACADEMI.NEXTVAL,POSTULACION_ID , COD_TEMA, NUMERO_VANCANTES, NOMBRE_ACTIVIDAD);

SELECT SEC_COD_PROGRAM_ACADEMI.CURRVAL INTO COD_PROG  FROM DUAL;

END USP_GUARDAR_ACTIVIDAD;

/
--------------------------------------------------------
--  DDL for Procedure USP_GUARDAR_HORARIO_PAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_GUARDAR_HORARIO_PAR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : USP_GUARDAR_HORARIO_PAR                                             */
/* Objetivo  : Guarada un horario par de una actividad academica  */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: @alexh                                          */
/*---------------------------------------------------------------------------*/
(
IN_COD_PROGR HORARIO_PAR.COD_PROGR%TYPE,
IN_HORA_INI HORARIO_PAR.HORA_INI%TYPE,
IN_HORA_FIN HORARIO_PAR.HORA_FIN%TYPE,
IN_DIA_SEMANA HORARIO_PAR.DIA_SEMANA%TYPE,
OUT_ID_HORARIO OUT HORARIO_PAR.COD_PROGR%TYPE
 ) AUTHID CURRENT_USER AS
BEGIN 
INSERT INTO HORARIO_PAR  ("ID_HORARIO","COD_PROGR", "HORA_INI", "HORA_FIN", "DIA_SEMANA") VALUES (SEC_COD_ID_HORARIO.NEXTVAL,IN_COD_PROGR, IN_HORA_INI, IN_HORA_FIN, IN_DIA_SEMANA);
SELECT SEC_COD_ID_HORARIO.CURRVAL INTO OUT_ID_HORARIO FROM DUAL;
END USP_GUARDAR_HORARIO_PAR;

/
--------------------------------------------------------
--  DDL for Procedure USP_GUARDAR_MATRICULA_PAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_GUARDAR_MATRICULA_PAR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : USP_GUARDAR_MATRICULA_PAR                                             */
/* Objetivo  : Guarada la matricula de un alumno a la actividad acadÃ©mica  */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: @alexh                                          */
/*---------------------------------------------------------------------------*/
(
COD_ALUMNO MATRICULA_PAR.A_CODIGO%TYPE,
COD_PROG MATRICULA_PAR.COD_PROGR%TYPE,
OUT_COD_MATRICULA  OUT NUMBER
 ) AUTHID CURRENT_USER AS
BEGIN 
	INSERT INTO MATRICULA_PAR ("CODMATRICULA", "A_CODIGO", "COD_PROGR") VALUES (SEC_COD_MATRICULA.NEXTVAL, COD_ALUMNO, COD_PROG);
SELECT SEC_COD_MATRICULA.CURRVAL INTO OUT_COD_MATRICULA FROM DUAL;
END USP_GUARDAR_MATRICULA_PAR;

/
--------------------------------------------------------
--  DDL for Procedure USP_GUARDAR_SESION_PAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_GUARDAR_SESION_PAR" 
/*---------------------------------------------------------------------------*/
/* Nombre    : USP_GUARDAR_SESION_PAR                                             */
/* Objetivo  : Guarada una sesion par de una actividad academica  */
/*---------------------------------------------------------------------------*/
/*     InformaciÃ³n:                                                          */
/*     Autor: @alexh                                          */
/*---------------------------------------------------------------------------*/
(
IN_COD_TIPSES SESIONES_PAR.COD_TIPSES%TYPE,
IN_COD_PROGR SESIONES_PAR.COD_PROGR%TYPE,
IN_NUMERO_SESION SESIONES_PAR.NUMSESION%TYPE,
IN_FECHA SESIONES_PAR.FECHA%TYPE,
IN_DESCRIPCION SESIONES_PAR.DESCRIPCION%TYPE
 ) AUTHID CURRENT_USER AS
BEGIN 
			INSERT INTO SESIONES_PAR ("COD_TIPSES", "COD_PROGR", "NUMSESION", "FECHA", "DESCRIPCION") VALUES (IN_COD_TIPSES, IN_COD_PROGR, IN_NUMERO_SESION, IN_FECHA, IN_DESCRIPCION);
END USP_GUARDAR_SESION_PAR;

/
--------------------------------------------------------
--  DDL for Procedure USP_LISTAR_ACTIVIDADES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_LISTAR_ACTIVIDADES" 
( C_ACTIVITIES  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
OPEN C_ACTIVITIES FOR
	SELECT PA.* ,(SELECT  PA.NUMVACANTES-COUNT(COD_PROGR)  AS RESTANTES FROM MATRICULA_PAR WHERE MATRICULA_PAR.COD_PROGR = PA.COD_PROGR)  AS VACANTES_RESTANTES
			FROM PROG_ACADEMICA_PAR PA
			INNER JOIN ASOC_POSTULACION_TEMA_PAR APT ON PA.CODIGO_TEMA = APT.CODIGO_TEMA AND PA.COD_POSTULACION = APT.COD_POSTULACION AND APT.FLAG_APROBACION = 1
			INNER JOIN POSTULACION_PAR PP ON PP.COD_POSTULACION  = APT.COD_POSTULACION 
			INNER JOIN CONVOCATORIA_PAR CP ON CP.CODCONVOCATOR = PP.CODCONVOCATOR
			INNER JOIN CICLO C ON C.ID_CICLO = CP.ID_CICLO AND C.ACTIVO = 1;
END USP_LISTAR_ACTIVIDADES;

/
--------------------------------------------------------
--  DDL for Procedure USP_LISTAR_ACTIVIDADES_TUTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_LISTAR_ACTIVIDADES_TUTOR" 
( COD_TUTOR  ALUMNO_PAR.A_CODIGO%TYPE ,C_ACTIVITIES  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
OPEN C_ACTIVITIES FOR
	SELECT PA.*,(SELECT  PA.NUMVACANTES-COUNT(COD_PROGR)  AS RESTANTES FROM MATRICULA_PAR WHERE MATRICULA_PAR.COD_PROGR = PA.COD_PROGR)  AS VACANTES_RESTANTES
			FROM PROG_ACADEMICA_PAR PA
			INNER JOIN ASOC_POSTULACION_TEMA_PAR APT ON PA.CODIGO_TEMA = APT.CODIGO_TEMA AND PA.COD_POSTULACION = APT.COD_POSTULACION AND APT.FLAG_APROBACION = 1
			INNER JOIN POSTULACION_PAR PP ON PP.COD_POSTULACION  = APT.COD_POSTULACION 
			INNER JOIN CONVOCATORIA_PAR CP ON CP.CODCONVOCATOR = PP.CODCONVOCATOR
			INNER JOIN CICLO C ON C.ID_CICLO = CP.ID_CICLO AND C.ACTIVO = 1
			WHERE PP.A_CODIGO = COD_TUTOR;
END USP_LISTAR_ACTIVIDADES_TUTOR;

/
--------------------------------------------------------
--  DDL for Procedure USP_LISTAR_ACT_MAT_ALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_LISTAR_ACT_MAT_ALUMNO" 
( COD_ALUMNO  ALUMNO_PAR.A_CODIGO%TYPE ,C_ACTIVITIES  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
OPEN C_ACTIVITIES FOR
	SELECT PA.*, (SELECT  PA.NUMVACANTES-COUNT(COD_PROGR)  AS RESTANTES FROM MATRICULA_PAR WHERE MATRICULA_PAR.COD_PROGR = PA.COD_PROGR)  AS VACANTES_RESTANTES
			FROM PROG_ACADEMICA_PAR PA
			INNER JOIN ASOC_POSTULACION_TEMA_PAR APT ON PA.CODIGO_TEMA = APT.CODIGO_TEMA AND PA.COD_POSTULACION = APT.COD_POSTULACION AND APT.FLAG_APROBACION = 1
			INNER JOIN POSTULACION_PAR PP ON PP.COD_POSTULACION  = APT.COD_POSTULACION 
			INNER JOIN CONVOCATORIA_PAR CP ON CP.CODCONVOCATOR = PP.CODCONVOCATOR
			INNER JOIN CICLO C ON C.ID_CICLO = CP.ID_CICLO AND C.ACTIVO = 1
			INNER JOIN MATRICULA_PAR MP ON MP.COD_PROGR = PA.COD_PROGR
			WHERE MP.A_CODIGO = USP_LISTAR_ACT_MAT_ALUMNO.COD_ALUMNO;

END USP_LISTAR_ACT_MAT_ALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure USP_LISTAR_HORARIOS_PAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_LISTAR_HORARIOS_PAR" 
( IN_COD_ACT NUMBER ,C_HORARIOS  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
OPEN C_HORARIOS FOR
			SELECT * FROM HORARIO_PAR WHERE COD_PROGR=IN_COD_ACT;
END USP_LISTAR_HORARIOS_PAR;

/
--------------------------------------------------------
--  DDL for Procedure USP_LISTAR_SESIONES_PAR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_LISTAR_SESIONES_PAR" 
( IN_COD_ACT NUMBER ,C_SESIONES  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
OPEN C_SESIONES FOR
			SELECT * FROM SESIONES_PAR WHERE COD_PROGR=IN_COD_ACT;
END USP_LISTAR_SESIONES_PAR;

/
--------------------------------------------------------
--  DDL for Procedure USP_TUTOR_DE_ACTIVIDAD
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."USP_TUTOR_DE_ACTIVIDAD" 
(COD_ACT  PROG_ACADEMICA_PAR.COD_PROGR%TYPE,
    C_ALUMNO_PAR  OUT SYS_REFCURSOR) AUTHID CURRENT_USER AS
BEGIN
    OPEN C_ALUMNO_PAR FOR
       SELECT AL.*  FROM ALUMNO_PAR AL 
				INNER JOIN POSTULACION_PAR PP ON PP.A_CODIGO = AL.A_CODIGO
				INNER JOIN ASOC_POSTULACION_TEMA_PAR APT ON APT.COD_POSTULACION = PP.COD_POSTULACION
				INNER JOIN PROG_ACADEMICA_PAR PA ON PA.COD_POSTULACION= APT.COD_POSTULACION AND PA.CODIGO_TEMA = APT.CODIGO_TEMA 
				WHERE PA.COD_PROGR = COD_ACT;
END USP_TUTOR_DE_ACTIVIDAD;

/
--------------------------------------------------------
--  DDL for Procedure VALIDAR_CARGANOTAS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."VALIDAR_CARGANOTAS" 
/*---------------------------------------------------------------------------*/
/* Nombre    : VALIDAR_CARGANOTAS                                            */
/* Objetivo  : Valida la existencia de un registro en la tabla del historial */
/*             de notas                                                      */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (ANIO            HISTORIAL_NOTAS.ANIO%TYPE,
    PERIODO         HISTORIAL_NOTAS.PERIODO%TYPE,
    PLAN            HISTORIAL_NOTAS.PLAN%TYPE,
    COD_CURSO       HISTORIAL_NOTAS.COD_CURSO%TYPE,
    COD_ALUMNO      HISTORIAL_NOTAS.COD_ALUMNO%TYPE,
    NOTA_FINAL      HISTORIAL_NOTAS.NOTA_FINAL%TYPE,    
    VALIDAR         OUT INTEGER) AUTHID CURRENT_USER AS

BEGIN        
    SELECT COUNT(*) 
      INTO VALIDAR
      FROM HISTORIAL_NOTAS
     WHERE ANIO       = VALIDAR_CARGANOTAS.ANIO
       AND PERIODO    = VALIDAR_CARGANOTAS.PERIODO
       AND PLAN       = VALIDAR_CARGANOTAS.PLAN
       AND COD_CURSO  = VALIDAR_CARGANOTAS.COD_CURSO
       AND COD_ALUMNO = VALIDAR_CARGANOTAS.COD_ALUMNO
       AND NOTA_FINAL = VALIDAR_CARGANOTAS.NOTA_FINAL;
       
    IF (VALIDAR = NULL OR VALIDAR = 0) THEN
        VALIDAR := 0;
    ELSE
        VALIDAR := 1;
    END IF;
    
END VALIDAR_CARGANOTAS;

/
--------------------------------------------------------
--  DDL for Procedure VALIDAR_EXISTENCIA_ALUMNO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."VALIDAR_EXISTENCIA_ALUMNO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : VALIDAR_EXISTENCIA_ALUMNO                                     */
/* Objetivo  : Validar si un alumno existe                                   */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_ALUMNO          ALUMNO.A_CODIGO%TYPE,
    COD_ALUMNO_VAL      OUT ALUMNO.A_CODIGO%TYPE) AUTHID CURRENT_USER AS

BEGIN               
    SELECT A_CODIGO
      INTO COD_ALUMNO_VAL
      FROM ALUMNO
     WHERE A_CODIGO = VALIDAR_EXISTENCIA_ALUMNO.COD_ALUMNO;
     
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        COD_ALUMNO_VAL := NULL;                         
END VALIDAR_EXISTENCIA_ALUMNO;

/
--------------------------------------------------------
--  DDL for Procedure VALIDAR_EXISTENCIA_DOCENTE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."VALIDAR_EXISTENCIA_DOCENTE" 
/*---------------------------------------------------------------------------*/
/* Nombre    : VALIDAR_EXISTENCIA_DOCENTE                                    */
/* Objetivo  : Validar si un docente existe                                  */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_DOCENTE         PROFESOR.P_CODIGO%TYPE,
    COD_DOCENTE_VAL     OUT PROFESOR.P_CODIGO%TYPE) AUTHID CURRENT_USER AS

BEGIN               
    SELECT P_CODIGO
      INTO COD_DOCENTE_VAL
      FROM PROFESOR
     WHERE P_CODIGO = VALIDAR_EXISTENCIA_DOCENTE.COD_DOCENTE;
     
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        COD_DOCENTE_VAL := NULL;                         
END VALIDAR_EXISTENCIA_DOCENTE;

/
--------------------------------------------------------
--  DDL for Procedure VALIDAR_EXISTENCIA_USUARIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "DBTUTORIA"."VALIDAR_EXISTENCIA_USUARIO" 
/*---------------------------------------------------------------------------*/
/* Nombre    : VALIDAR_EXISTENCIA_USUARIO                                    */
/* Objetivo  : Validar si un usuario existe                                  */
/*---------------------------------------------------------------------------*/                                
/*     Informaci?:                                                          */
/*     Autor: Gerardo Jim?ez Baz?                                          */
/*---------------------------------------------------------------------------*/

   (COD_USUARIO         USUARIO.ID_USUARIO%TYPE,
    COD_USUARIO_VAL     OUT USUARIO.ID_USUARIO%TYPE) AUTHID CURRENT_USER AS

BEGIN               
    SELECT ID_USUARIO
      INTO COD_USUARIO_VAL
      FROM USUARIO
     WHERE ID_USUARIO = VALIDAR_EXISTENCIA_USUARIO.COD_USUARIO;
     
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        COD_USUARIO_VAL := NULL;                         
END VALIDAR_EXISTENCIA_USUARIO;

/
