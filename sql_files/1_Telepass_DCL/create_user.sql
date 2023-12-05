alter session set "_ORACLE_SCRIPT"=true;
create user TELEPASS identified by telepass;

grant DBA, CONNECT, RESOURCE to TELEPASS;

connect TELEPASS/telepass;


--%%%%% DAI PRIVILEGI CONCEDERE TUTTI E DESELEZIONARE SOLO QUESTI:
--SYSBACKUP	false	false
--SYSDBA	false	false
--SYSDG	false	false
--SYSKM	false	false
--SYSOPER	false	false
--SYSRAC	false	false
--KEEP DATE TIME	false	false
--KEEP SYSGUID	false	false