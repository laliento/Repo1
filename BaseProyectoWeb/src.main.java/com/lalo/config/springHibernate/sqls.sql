INSERT INTO 
`WEBPROJECTBASE`.`CAT_MENU` 
(`ID_MENU`, `DESC`, `ID_MENU_PADRE`, `URL`, `ORDEN`, `ICONO`) 
VALUES (NULL, 'menuPrueba', NULL, null, '1', 'fa-anchor');

INSERT INTO 
`WEBPROJECTBASE`.`CAT_MENU` 
(`ID_MENU`, `DESC`, `ID_MENU_PADRE`, `URL`, `ORDEN`, `ICONO`) 
VALUES (NULL, 'Submenu', '1', '/pages/admin/otro.xhtml', '1', 'fa-arrows-v');


INSERT INTO 
`WEBPROJECTBASE`.`REL_PERFIL_MENU` 
(`ID_REL_PERFIL_MENU`, `ID_PERFIL`, `ID_MENU`) 
VALUES (NULL, 1,1);

INSERT INTO 
`WEBPROJECTBASE`.`REL_PERFIL_MENU` 
(`ID_REL_PERFIL_MENU`, `ID_PERFIL`, `ID_MENU`) 
VALUES (NULL, 1,1);

INSERT INTO 
`WEBPROJECTBASE`.`REL_PERFIL_MENU` 
(`ID_REL_PERFIL_MENU`, `ID_PERFIL`, `ID_MENU`) 
VALUES (NULL, 1,2);

SELECT * FROM CAT_PERFIL
SELECT * FROM CAT_MENU