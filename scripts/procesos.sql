-- ejecutar como sys as sysdba 
-- luego reiniciar el servicio de oracle
ALTER SYSTEM SET PROCESSES=1050 SCOPE=SPFILE;
ALTER SYSTEM SET OPEN_CURSORS=500;
select * from v$resource_limit where resource_name = 'processes';