-- -- SUPPLIERS
-- INSERT INTO supplier (calle, caracteristica, cel_contacto, email, email_contacto, empresa, localidad, nombre_contacto, nro, sector) VALUES ('Uruguay',54,'114524534','alter@hotmail.com','alterContacto@hotmail.com','ALTER','Bs As','Alter','2345','TEXTIL');
-- INSERT INTO supplier (calle, caracteristica, cel_contacto, email, email_contacto, empresa, localidad, nombre_contacto, nro, sector) VALUES ('Sarmiento',54,'115541534','textilex@textilex.com','juan@hotmail.com','Textilex','Bs As','Juan','8473','TEXTIL');
-- INSERT INTO supplier (calle, caracteristica, cel_contacto, email, email_contacto, empresa, localidad, nombre_contacto, nro, sector) VALUES ('Alsina',54,'126373299','mundo-textil@hotmail.com','mundo-textilo@hotmail.com','Mundo Textil','Bs As','Ramiro','2345','TEXTIL');

-- -- INPUTS
-- INSERT INTO input (proveedor_id, codigo, detalle, nombre) VALUES (3, 'h23j43', 'Detalle Argolla', 'Argolla 10mm/10mm (redonda)');
-- INSERT INTO input (proveedor_id, codigo, detalle, nombre) VALUES (3, 'gsdf4', 'Detalle Elastico', 'Elastico 20mm blanco');
-- INSERT INTO input (proveedor_id, codigo, detalle, nombre) VALUES (3, '234fsdf', 'Detalle Corredera', 'Corredera 20mm semac');
-- INSERT INTO input (proveedor_id, codigo, detalle, nombre) VALUES (3, '2353476', 'Detalle Gancho', 'Gancho 18mm semac plata (CASADO)');
-- INSERT INTO input (proveedor_id, codigo, detalle, nombre) VALUES (3, '64866', 'Detalle Shell', 'shell');
-- INSERT INTO input (proveedor_id, codigo, detalle, nombre) VALUES (3, '64337', 'Detalle Etiqueta', 'LoveAfrica');

-- -- ARGOLLAS
-- INSERT INTO argolla (cant_por_pack, precio_por_pack, precio_uni, stock_packs, id, circunferencia_externa, circunferencia_interna, color, forma, material) VALUES (100, 100, 1, 20, 1, '10mm', '8mm', 'plata', 'redonda', 'semac');

-- -- ELASTICOS

-- INSERT INTO elasticos (mts_por_rollo, precio_mtr, precio_rollo, stock_en_rollos, id, ancho, color, material) VALUES (200, 30, 6000, 3, 2, '20mm', 'Blanco', 'elastico');

-- -- CORREDERAS

-- INSERT INTO corredera (cant_por_pack, precio_por_pack, precio_uni, stock_packs, id, color, forma, material, medida) VALUES (1000, 1000, 1, 10, 3, 'plata', 'común', 'semac', "20mm");

-- -- GANCHOS

-- INSERT INTO gancho (cant_por_pack, precio_por_pack, precio_uni, stock_packs, id, color, material, medida, tipo_gancho) VALUES (1000, 1000, 1, 3, 4, 'plata', 'semac', '20mm', 'soltero');

-- -- APLIQUES

-- INSERT INTO aplique (cant_por_pack, precio_por_pack, precio_unidad, stock_packs, id, color) VALUES (1000, 1000, 1, 3, 5, 'plata');

-- -- ETIQUETAS

-- INSERT INTO etiqueta (precio_unidad, stock, id, marca) VALUES (20, 500, 6, 'Love Africa');



-- -- FABRICS

-- INSERT INTO fabric (code_bar_numb, precio, stock, proveedor_id, codigo, color, nombre_tela, tags, temporada, tipo, img) VALUES (0, 1000, 200, 1, '2d2ew', 'CELESTE', 'CELESTE', 'LYCRA CELESTE', '2025', 'LYCRA', null);
-- INSERT INTO fabric (code_bar_numb, precio, stock, proveedor_id, codigo, color, nombre_tela, tags, temporada, tipo, img) VALUES (0, 1000, 200, 1, '2d2ew', 'VERDE', 'VERDE', 'LYCRA VERDE', '2025', 'LYCRA', null);


-- -- TIRAS

-- INSERT INTO tira (ancho, largo) VALUES (3, null);
-- INSERT INTO tira (ancho, largo) VALUES (4, null);
-- INSERT INTO tira (ancho, largo) VALUES (6, null);
-- INSERT INTO tira (ancho, largo) VALUES (8, null);

-- -- WORKSHOPS

-- INSERT INTO workshops (nombre) VALUES ('Jobs');

-- INSERT INTO `tira` VALUES (3,NULL,1),(4,NULL,2),(6,NULL,3),(8,NULL,4),(7,NULL,5);


INSERT INTO `permission` (`id`, `permission_name`) VALUES (29,'CREATE_CUT_SPREADSHEET'),(2,'CREATE_FABRIC'),(11,'CREATE_INPUT'),(23,'CREATE_LOTE'),(15,'CREATE_MODEL'),(19,'CREATE_PRODUCT'),(46,'CREATE_PRODUCT_SET'),(6,'CREATE_SUPPLIER'),(40,'CREATE_WORKSHOP'),(3,'DELETE_FABRIC'),(12,'DELETE_INPUT'),(24,'DELETE_LOTE'),(16,'DELETE_MODEL'),(20,'DELETE_PRODUCT'),(47,'DELETE_PRODUCT_SET'),(7,'DELETE_SUPPLIER'),(41,'DELETE_WORKSHOP'),(49,'GET_PRODUCT_COST'),(37,'READ_CONTROL_SPREADSHEET'),(28,'READ_CUT_SPREADSHEET'),(1,'READ_FABRIC'),(10,'READ_INPUT'),(22,'READ_LOTE'),(26,'READ_LOTE_WORKSHOP'),(14,'READ_MODEL'),(9,'READ_NEW_COLLECTION'),(31,'READ_PREPARATION_SPREADSHEET'),(18,'READ_PRODUCT'),(45,'READ_PRODUCT_SET'),(5,'READ_SUPPLIER'),(43,'READ_WAREHOUSE'),(39,'READ_WORKSHOP'),(35,'READ_WORKSHOP_SPREADSHEET'),(38,'UPDATE_CONTROL_SPREADSHEET'),(30,'UPDATE_CUT_SPREADSHEET'),(4,'UPDATE_FABRIC'),(13,'UPDATE_INPUT'),(25,'UPDATE_LOTE'),(27,'UPDATE_LOTE_WORKSHOP'),(17,'UPDATE_MODEL'),(32,'UPDATE_PREPARATION_SPREADSHEET'),(21,'UPDATE_PRODUCT'),(48,'UPDATE_PRODUCT_SET'),(8,'UPDATE_SUPPLIER'),(44,'UPDATE_WAREHOUSE'),(42,'UPDATE_WORKSHOP'),(36,'UPDATE_WORKSHOP_SPREADSHEET');

INSERT INTO `roles` (`id`, `role`) VALUES (1,'ADMIN'),(2,'MANAGER'),(3,'ASSISTANT'),(4,'CONTROLLER'),(5,'WORKSHOP'),(6,'SELLER'),(7,'CUTTER'),(8,'USER');

INSERT INTO `roles_permissions` (`permission_id`, `role_id`) VALUES (1,1),(1,4),(1,7),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(10,4),(11,1),(12,1),(13,1),(14,1),(14,4),(14,7),(15,1),(16,1),(17,1),(18,1),(18,4),(18,7),(19,1),(20,1),(21,1),(22,1),(22,4),(22,7),(23,1),(23,7),(24,1),(25,1),(26,1),(26,5),(27,1),(28,1),(28,4),(28,7),(29,1),(30,1),(30,7),(31,1),(31,4),(31,5),(31,7),(32,1),(32,4),(35,1),(35,4),(35,5),(35,7),(36,1),(36,5),(37,1),(37,4),(37,7),(38,1),(38,4),(39,1),(39,4),(39,7),(40,1),(41,1),(42,1),(43,1),(43,6),(43,7),(44,1),(45,1),(45,7),(46,1),(47,1),(48,1),(49,1);

INSERT INTO `users` (`account_not_expired`, `account_not_locked`, `credential_not_expired`, `enabled`, `id`, `password`, `username`) VALUES (_binary '',_binary '',_binary '',_binary '',1,'$2y$10$SnbMJdlvZ9vVLGWXu8ZtEOZEAaUb0.ZVg21adeBV5yUwzby4xYAYG','admin'),(_binary '',_binary '',_binary '',_binary '',2,'$2a$10$plr7JS.Z8mdRbJPMiI39bOh7GnbMWF/Zj34EaT1aIvL/eYvTXW5wO','jobs'),(_binary '',_binary '',_binary '',_binary '',3,'$2a$10$rvcb.nGP.GhTtgESHkmMNuxQ00aLob9IEJCFenhTbt2bu.svrKy3e','cristina'),(_binary '',_binary '',_binary '',_binary '',4,'$2a$10$XHdNItN5wybkK9iRa6W6FODzbGH9IlK7qxyPU3JM2B1eAUFw/5fQC','isabel'),(_binary '',_binary '',_binary '',_binary '',5,'$2a$10$zzG/31DN72TCBaNuOSyuleMcEJ5UHucMlvOaDOGDh6aHFKCjV63h6','carolina'),(_binary '',_binary '',_binary '',_binary '',6,'$2a$10$lCnwwoDpd2teaBvvpclQgOu.j8M4hmtKS5HXxaiTUH/6pVXSPOn2q','berni'),(_binary '',_binary '',_binary '',_binary '',7,'$2y$10$atActOfgwoEJO0ALSBYd.OP1nOGPvkluI/Z3xvEYXmau2M7ksLRx.','cutter'),(_binary '',_binary '',_binary '',_binary '',8,'$2a$10$n4Fx6KoKnU5PNo5Ixb.SlO6hNEWVqyRS0MDyP7tHe.wYdnNLxyAma','seller'),(_binary '',_binary '',_binary '',_binary '',9,'$2a$10$n5o0oD1bwz2mYa1Z6m.QGe//97wG1hZsrHx6vBWIniQs8xJFH2dx6','controller');

INSERT INTO `user_roles` (`role_id`, `user_id`) VALUES (1,1),(4,9),(5,2),(5,3),(5,4),(5,5),(5,6),(6,8),(7,7);

INSERT INTO `tira` (`ancho`, `largo`, `id`) VALUES (3,NULL,1),(4,NULL,2),(6,NULL,3),(8,NULL,4),(7,NULL,5);

-- INSERT INTO `supplier` VALUES (54,1,'Uruguay','114524534','alter@hotmail.com','alterContacto@hotmail.com','ALTER','Bs As','Alter','2345','TEXTIL'),(54,2,'Sarmiento','115541534','textilex@textilex.com','juan@hotmail.com','Textilex','Bs As','Juan','8473','TEXTIL'),(54,3,'Alsina','126373299','mundo-textil@hotmail.com','mundo-textilo@hotmail.com','Mundo Textil','Bs As','Ramiro','2345','TEXTIL'),(54,4,'LAVALLE','2235578908','rodatextelas@gmail.comm','rodatextelas@gmail.comm','Rodatex','C.A.B.A','rodatext','2443','TEXTIL'),(54,5,'Salta','112345342','Avios','juanavios@gmail.com','Avios','BsAs','Juan','23425','avios'),(54,6,'J.B. Justo','2235578908','asia@gmail.com','asia@asia.com','Asia','Mar del Plata','asia','4234','Etiquetas'),(54,7,'Champagnat ','2235578908','fox@fox.com','axel@fox.com','Fox','Mar del Plata','Axel','1234','Textil');
INSERT INTO `supplier` (`caracteristica`, `id`, `calle`, `cel_contacto`, `email`, `email_contacto`, `empresa`, `localidad`, `nombre_contacto`, `nro`, `sector`) VALUES (54,1,'Uruguay','114524534','alter@hotmail.com','alterContacto@hotmail.com','ALTER','Bs As','Alter','2345','TEXTIL'),(54,2,'Sarmiento','115541534','textilex@textilex.com','juan@hotmail.com','Textilex','Bs As','Juan','8473','TEXTIL'),(54,3,'Alsina','126373299','mundo-textil@hotmail.com','mundo-textilo@hotmail.com','Mundo Textil','Bs As','Ramiro','2345','TEXTIL'),(54,4,'LAVALLE','2235578908','rodatextelas@gmail.comm','rodatextelas@gmail.comm','Rodatex','C.A.B.A','rodatext','2443','TEXTIL'),(54,5,'Salta','112345342','Avios','juanavios@gmail.com','Avios','BsAs','Juan','23425','avios'),(54,6,'J.B. Justo','2235578908','asia@gmail.com','asia@asia.com','Asia','Mar del Plata','asia','4234','Etiquetas'),(54,7,'Champagnat ','2235578908','fox@fox.com','axel@fox.com','Fox','Mar del Plata','Axel','1234','Textil');

INSERT INTO `input` (`id`, `proveedor_id`, `codigo`, `detalle`, `nombre`) VALUES (1,3,'h23j43','Detalle Argolla','Argolla 10mm/10mm (redonda)'),(2,3,'gsdf4','Detalle Elastico','Elastico 20mm blanco'),(3,3,'234fsdf','Detalle Corredera','Corredera 11mm zamac'),(4,3,'2353476','Detalle Gancho','Gancho 18mm semac plata (CASADO)'),(5,3,'64866','Detalle Shell','shell'),(6,3,'64337','Detalle Etiqueta','LoveAfrica'),(7,5,'234','','Argolla 11mm/11mm'),(8,5,'1234','s','Argolla 11mm/11mm'),(9,5,'rewq','a','Argolla 15/15'),(10,5,'123432','','Argolla 20/20'),(11,5,'tt34','','Argolla 30/30'),(12,5,'gf4we','','Argolla 35/35'),(13,5,'2whyh','','Argolla 40/40'),(14,5,'hrfnbft','','Argolla 45/45'),(15,5,'fhtf','','Argolla 50/50'),(16,5,'fa3rd','fe','Corredera 11mm zamac'),(17,5,'htrth4','','Corredera 20mm metal'),(19,5,'2rew','','Elastico 7mm'),(20,5,'fid0f3','r','Elastico 1.5cm'),(21,5,'r22ds','','Gancho  zamac metal (CASADO)'),(22,5,'2edsf3','','Gancho  zamac metal (SOLTERO)'),(23,5,'2d21r','','Chapita shell'),(24,5,'sf3sdf','','Elastico 2cm');

INSERT INTO `apliques` (`color`, `precio_unidad`, `stock`, `id`) VALUES ('metal',865,NULL,23);

INSERT INTO `argolla` (`circunferencia_externa`, `circunferencia_interna`, `color`, `material`, `precio_uni`, `stock`, `id`) VALUES ('11mm','11mm','plata','zamac',1000,5000,8),('15','15','plateado','Zamac',1000,5000,9),('20','20','metal','zamac',100,1000,10),('30','30','metal','zamac',1000,10000,11),('35','35','metal','zamac',100,10000,12),('40','40','metal','zamac',200,100000,13),('45','45','metal','zamac',1000,110000,14),('50','50','metal','zamac',1000,10000,15);

INSERT INTO `corredera` (`color`, `material`, `medida`, `precio_uni`, `stock`, `id`) VALUES ('metal','zamac','11mm',300,10000,16),('metal','metal','20mm',100,20000,17);

INSERT INTO `elasticos` (`ancho`, `color`, `material`, `mts_por_rollo`, `precio_mtr`, `stock`, `id`) VALUES ('7mm','','',0,100,20000,19),('1.5cm','','',0,200,10000,20),('2cm','','',0,80,12345,24);

INSERT INTO `etiqueta` (`precio_unidad`, `stock`, `id`, `marca`) VALUES (20,500,6,'Love Africa');

INSERT INTO `gancho` (`color`, `material`, `medida`, `precio_uni`, `stock`, `tipo_gancho`, `id`) VALUES ('metal','zamac','',100,10000,'CASADO',21),('metal','zamac','',199,24526,'SOLTERO',22);

INSERT INTO `fabric` (`code_bar_numb`, `precio`, `stock`, `id`, `proveedor_id`, `codigo`, `color`, `nombre_tela`, `tags`, `temporada`, `tipo`, `img`, `url_file`) VALUES (NULL,10000,500,3,1,'ds3f3','negro','BLACK','negro, black,','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/BLACK3'),(NULL,10000,300,4,1,'f3s3as','Amarillo','Amarillo taksi','','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Amarillo taksi4'),(NULL,15000,200,5,1,'2eds','tierra','Dune','marrón, tierra, unidireccional ','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Dune5'),(NULL,11000,250,6,4,'f3s3wq3','rojo, bordo','Dream Fyre','','2025','lycra brillante',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Dream Fyre6'),(NULL,18000,200,7,7,'','verde','Gen Z','','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Gen Z7'),(NULL,15000,100,8,1,'fes32','','King','sublimado,','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/King8'),(NULL,9000,400,9,1,'f32','blanco','White','','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/White9'),(NULL,17000,300,10,7,'fs34r2','','Highlight','sublimado','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Highlight10'),(NULL,12998,270,11,4,'42e2er','Naranja','Naranja Toxic','','2025','lycra',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Naranja Toxic11'),(NULL,13000,300,12,4,'fs4332','floreado','Blossom','','2025','lycra brillante',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Blossom12'),(NULL,13000,300,13,4,'sf32d','Mostaza','Mustard','','2025','Lycra Brillante',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Mustard13'),(NULL,15000,300,14,1,'sf32s','verde, negro','Ninja','','2025','lycra sublimada',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Ninja14'),(NULL,12000,500,15,4,'42df2','Gris','Gris','','2025','Lycra Brillante',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Gris15'),(NULL,13000,200,16,4,'f23wf','celeste','Avatar','','2025','Lycra brillante',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Avatar16'),(NULL,0,0,26,4,'','','ver6','','','','','https://file-uploads-la.s3.sa-east-1.amazonaws.com/ver626');

INSERT INTO `model` (`fecha_de_registro`, `id`, `detalle`, `nombre`, `tipo_prenda`) VALUES ('2024-08-06',1,'','Lena','CORPI'),('2024-08-06',2,'','Sally','CORPI'),('2024-08-06',3,'','Amber','CORPI'),('2024-08-06',4,'','Pony','CORPI'),('2024-08-06',5,'','Turbo','CORPI'),('2024-08-06',6,'','Web','CORPI'),('2024-08-06',7,'','Strapless','TOP'),('2024-08-06',8,'','Tirita','CORPI'),('2024-08-06',9,'','Triangulo Fijo','CORPI'),('2024-08-06',10,'','Hunter','CORPI'),('2024-08-06',11,'','Forever','CORPI'),('2024-08-06',12,'','Shake','CORPI'),('2024-08-06',13,'','Why','TOP'),('2024-08-06',14,'','Giga','TOP'),('2024-08-06',15,'','Britney','CORPI'),('2024-08-06',16,'','Millenial','CORPI'),('2024-08-06',17,'','Millenial','LESS'),('2024-08-06',18,'','Web','LESS'),('2024-08-06',19,'','Amber','LESS'),('2024-08-06',20,'','Turbo','LESS'),('2024-08-06',21,'','Why','LESS'),('2024-08-06',22,'','Tirita','LESS'),('2024-08-06',23,'','Brynn','LESS'),('2024-08-07',24,'','Jelly','LESS'),('2024-08-07',26,'','Airport','LESS'),('2024-08-07',27,'','Paris','LESS');

INSERT INTO `strip_details_for_product` (`width`, `fabric_id`, `id`) VALUES (3,13,1),(3,13,2),(3,5,3),(6,5,4),(3,5,5),(7,4,9),(7,4,10),(3,6,11),(3,6,12),(7,8,13),(3,7,14),(3,7,15),(3,9,16),(3,9,17),(3,10,18),(3,10,19),(3,11,20),(3,11,21),(3,12,22),(6,12,23),(3,12,24),(3,16,25),(6,16,26),(3,16,27);

INSERT INTO `map_strips_per_size_for_product` (`cantidad_tiras`, `id`, `talle`) VALUES (1,1,'L'),(1,1,'M'),(1,1,'S'),(1,1,'XL'),(1,1,'XXL'),(1,2,'L'),(1,2,'M'),(1,2,'S'),(1,2,'XL'),(1,3,'L'),(1,3,'M'),(1,3,'S'),(1,3,'XL'),(1,3,'XXL'),(1,4,'L'),(1,4,'M'),(1,4,'S'),(1,4,'XL'),(1,4,'XXL'),(1,5,'L'),(1,5,'M'),(1,5,'S'),(1,5,'XL'),(1.5,9,'L'),(1.5,9,'M'),(1.5,9,'S'),(1.5,9,'XL'),(1.5,9,'XXL'),(1,10,'L'),(1,10,'M'),(1,10,'S'),(1,10,'XL'),(1,11,'L'),(1,11,'M'),(1,11,'S'),(1,11,'XL'),(1,11,'XXL'),(1,12,'L'),(1,12,'M'),(1,12,'S'),(1,12,'XL'),(1,13,'L'),(1,13,'M'),(1,13,'S'),(1,13,'XL'),(1,14,'L'),(1,14,'M'),(1,14,'S'),(1,14,'XL'),(1,14,'XXL'),(1,15,'L'),(1,15,'M'),(1,15,'S'),(1,15,'XL'),(1.5,16,'L'),(1.5,16,'M'),(1.5,16,'S'),(1.5,16,'XL'),(1.5,16,'XXL'),(1.5,17,'L'),(1.5,17,'M'),(1.5,17,'S'),(1.5,17,'XL'),(1.5,18,'L'),(1.5,18,'M'),(1.5,18,'S'),(1.5,18,'XL'),(1.5,18,'XXL'),(1.5,19,'L'),(1.5,19,'M'),(1.5,19,'S'),(1.5,19,'XL'),(1,20,'L'),(1,20,'M'),(1,20,'S'),(1.5,20,'XL'),(1.5,20,'XXL'),(1,21,'L'),(1,21,'M'),(1,21,'S'),(1,21,'XL'),(1,22,'L'),(1,22,'M'),(1,22,'S'),(1,22,'XL'),(1,22,'XXL'),(1,23,'L'),(1,23,'M'),(1,23,'S'),(1,23,'XL'),(1,23,'XXL'),(1,24,'L'),(1,24,'M'),(1,24,'S'),(1,24,'XL'),(1,25,'L'),(1,25,'M'),(1,25,'S'),(1,25,'XL'),(1,25,'XXL'),(1,26,'L'),(1,26,'M'),(1,26,'S'),(1,26,'XL'),(1,26,'XXL'),(1,27,'L'),(1,27,'M'),(1,27,'S'),(1,27,'XL');

INSERT INTO `model_and_strips_for_product` (`id`, `model_id`) VALUES (44,1),(60,2),(58,3),(48,5),(52,5),(50,7),(64,8),(62,10),(54,13),(56,13),(46,14),(47,17),(51,17),(59,19),(49,20),(53,20),(55,21),(57,21),(43,22),(63,23),(45,24),(61,27);

INSERT INTO `model_and_strips_for_product_strip_details_for_products` (`model_and_strips_for_product_id`, `strip_details_for_products_id`) VALUES (43,2),(44,3),(44,4),(45,5),(46,9),(47,10),(48,11),(49,12),(51,13),(52,14),(53,15),(54,16),(55,17),(56,18),(57,19),(58,20),(59,21),(60,22),(60,23),(61,24),(62,25),(62,26),(63,27),(64,1);

INSERT INTO `product` (`cost`, `fecha_registro`, `fabric_id`, `id`, `model_and_strips_for_product_id`, `code_bar_number`, `color_forro`, `nombre`, `img`, `url_file`) VALUES (NULL,'2024-08-07',13,1,64,'000000000001','Blancos','Tirita Mustard',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Tirita Mustard1'),(NULL,'2024-08-07',13,2,43,'000000000002','Blanco','Tirita Mustard',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Tirita Mustard2'),(NULL,'2024-08-07',5,3,44,'000000000003','Blanco','Lena Dune',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Lena Dune3'),(NULL,'2024-08-07',5,4,45,'000000000004','Blanco','Jelly Dune',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Jelly Dune4'),(NULL,'2024-08-07',3,8,46,'000000000008','Negro','Giga Taksi',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Giga Taksi8'),(NULL,'2024-08-07',3,9,47,'000000000009','Negro','Millenial Taksi',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Millenial Taksi9'),(NULL,'2024-08-07',6,10,48,'000000000010','Blanco','Turbo DreamFyre',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Turbo DreamFyre10'),(NULL,'2024-08-07',6,11,49,'000000000011','Blanco','Turbo DreamFyre',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Turbo DreamFyre11'),(NULL,'2024-08-07',8,12,50,'000000000012','Negro','Strapless King',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Strapless King12'),(NULL,'2024-08-07',8,13,51,'000000000013','Negro','Millenial King',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Millenial King13'),(NULL,'2024-08-07',7,14,52,'000000000014','Blanco','Turbo Gen Z',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Turbo Gen Z14'),(NULL,'2024-08-07',7,15,53,'000000000015','Blanco','Turbo Gen Z',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Turbo Gen Z15'),(NULL,'2024-08-07',3,16,54,'000000000016','Negro','Bling',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Bling16'),(NULL,'2024-08-07',3,17,55,'000000000017','Negro','Bling',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Bling17'),(NULL,'2024-08-07',10,18,56,'000000000018','Blanco','Why Highlight',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Why Highlight18'),(NULL,'2024-08-07',10,19,57,'000000000019','Blanco','Why Highlight',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Why Highlight19'),(NULL,'2024-08-07',15,20,58,'000000000020','Blanco','Amber Toxic',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Amber Toxic20'),(NULL,'2024-08-07',15,21,59,'000000000021','Blanco','Amber Toxic',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Amber Toxic21'),(NULL,'2024-08-07',12,22,60,'000000000022','Negro','Sally Blossom',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Sally Blossom22'),(NULL,'2024-08-07',12,23,61,'000000000023','Negro','Paris Blossom',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Paris Blossom23'),(NULL,'2024-08-07',16,24,62,'000000000024','Blanco','Hunter Avatar',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Hunter Avatar24'),(NULL,'2024-08-07',16,25,63,'000000000025','Blanco','Brynn Avatar',NULL,'https://file-uploads-la.s3.sa-east-1.amazonaws.com/Brynn Avatar25');

INSERT INTO `workshops` (`id`, `user_id`, `name`) VALUES (1,2,'JOBS'),(2,3,'CRISTINA'),(3,4,'ISABEL'),(4,5,'CAROLINA'),(5,6,'BERNI');

INSERT INTO `cut_spreadsheets` (`is_finished`, `publication_date`, `table_length`, `id`, `details`) VALUES (_binary '\0',NULL,2.6,1,NULL),(_binary '\0',NULL,2.6,2,NULL);

INSERT INTO `preparation_spreadsheets` (`is_finished`, `publication_date`, `id`, `details`, `img`) VALUES (_binary '\0',NULL,1,NULL,NULL),(_binary '\0',NULL,2,NULL,NULL);

INSERT INTO `workshop-spreadsheet` (`is_finished`, `publication_date`, `id`, `details`) VALUES (_binary '\0',NULL,1,NULL),(_binary '\0',NULL,2,'nueva prueba');

INSERT INTO `lotes` (`creation_date`, `finished_date`, `is_finished`, `cut_spreadsheet_id`, `id`, `preparation_spreadsheet_id`, `workshop_id`, `workshop_spreadsheet_id`, `additional_details`, `collection`, `status`) VALUES ('2024-08-08',NULL,_binary '\0',1,1,1,2,1,'[PRELOAD]',NULL,'FINALIZADO'),('2024-08-08',NULL,_binary '\0',2,2,2,2,2,'',NULL,'FINALIZADO');

INSERT INTO `control_spread_sheet` (`is_finished`, `publication_date`, `id`, `lote_id`, `details`) VALUES (_binary '\0',NULL,1,1,NULL),(_binary '\0',NULL,2,2,'detalle control');

INSERT INTO `lotes_productos` (`lote`, `producto`) VALUES (1,22),(1,23),(2,22),(2,23);

INSERT INTO `detalle_insumo` (`cantidad`, `id`, `input_id`) VALUES (2,1,16),(2,2,8),(NULL,3,20),(1,4,6),(2,5,16),(2,6,8),(NULL,7,19),(NULL,8,20),(1,9,6),(1,10,9),(2,11,16),(2,12,8),(1,13,6),(NULL,14,19),(1,15,15),(NULL,16,19),(1,17,6),(1,18,6),(0,19,11),(NULL,20,19),(1,21,6),(NULL,22,19),(1,23,6),(NULL,24,19),(1,25,6),(2,26,16),(2,27,8),(1,28,22),(NULL,29,20),(1,30,6),(2,31,16),(10,32,9),(2,33,8),(NULL,34,19),(NULL,35,20),(1,36,6),(2,37,16),(1,38,12),(2,39,8),(1,40,6),(NULL,41,19),(1,42,14),(1,43,6),(1,44,6),(2,45,17),(NULL,46,24),(NULL,47,19),(1,48,6),(6,49,9),(NULL,50,19),(1,51,6),(2,52,17),(2,53,10),(1,54,21),(1,55,6),(2,56,17),(2,57,10),(NULL,58,19),(1,59,6),(1,60,23),(2,61,11),(NULL,62,19),(1,63,6),(1,64,23),(2,65,16),(2,66,8),(1,67,6),(1,68,23),(2,69,15),(NULL,70,19),(1,71,23),(1,72,6),(1,73,6),(1,74,23),(NULL,75,19),(1,76,6),(1,77,23),(4,78,9),(NULL,79,19),(1,80,6),(1,81,23),(NULL,82,19),(1,83,6),(1,84,23),(NULL,88,19),(1,89,6),(1,90,23),(2,91,16),(2,92,8),(NULL,93,19),(1,94,6),(1,95,23);

INSERT INTO `cant_por_talle` (`cantidad`, `id`, `talle`) VALUES (100,3,'L'),(100,3,'M'),(100,3,'S'),(100,3,'XL'),(100,3,'XXL'),(100,7,'L'),(100,7,'M'),(100,7,'S'),(100,7,'XL'),(100,7,'XXL'),(100,8,'L'),(100,8,'M'),(100,8,'S'),(100,8,'XL'),(100,8,'XXL'),(100,14,'L'),(100,14,'M'),(100,14,'S'),(100,14,'XL'),(100,14,'XXL'),(100,16,'L'),(100,16,'M'),(100,16,'S'),(100,16,'XL'),(100,16,'XXL'),(100,20,'L'),(100,20,'M'),(100,20,'S'),(100,20,'XL'),(100,20,'XXL'),(100,22,'L'),(100,22,'M'),(100,22,'S'),(100,22,'XL'),(100,22,'XXL'),(100,24,'L'),(100,24,'M'),(100,24,'S'),(100,24,'XL'),(100,24,'XXL'),(100,29,'L'),(100,29,'M'),(100,29,'S'),(100,29,'XL'),(100,29,'XXL'),(100,34,'L'),(100,34,'M'),(100,34,'S'),(100,34,'XL'),(100,34,'XXL'),(100,35,'L'),(100,35,'M'),(100,35,'S'),(100,35,'XL'),(100,35,'XXL'),(100,41,'L'),(100,41,'M'),(100,41,'S'),(100,41,'XL'),(100,41,'XXL'),(100,46,'L'),(100,46,'M'),(100,46,'S'),(100,46,'XL'),(100,46,'XXL'),(100,47,'L'),(100,47,'M'),(100,47,'S'),(100,47,'XL'),(100,47,'XXL'),(100,50,'L'),(100,50,'M'),(100,50,'S'),(100,50,'XL'),(100,50,'XXL'),(100,58,'L'),(100,58,'M'),(100,58,'S'),(100,58,'XL'),(100,62,'L'),(100,62,'M'),(100,62,'S'),(100,62,'XL'),(100,70,'L'),(100,70,'M'),(100,70,'S'),(100,70,'XL'),(100,75,'L'),(100,75,'M'),(100,75,'S'),(100,75,'XL'),(100,79,'L'),(100,79,'M'),(100,79,'S'),(100,79,'XL'),(100,82,'L'),(100,82,'M'),(100,82,'S'),(100,82,'XL'),(100,88,'L'),(100,88,'M'),(100,88,'S'),(100,88,'XL'),(100,93,'L'),(100,93,'M'),(100,93,'S'),(100,93,'XL');

INSERT INTO `size_amount_details_spreadsheet` (`id`, `id_product`, `collection`, `type`) VALUES (1,22,NULL,NULL),(2,23,NULL,NULL),(3,22,NULL,NULL),(4,23,NULL,NULL),(5,22,NULL,NULL),(6,23,NULL,NULL),(7,22,NULL,NULL),(8,23,NULL,NULL),(9,22,NULL,NULL),(10,23,NULL,NULL),(11,22,NULL,NULL),(12,23,NULL,NULL),(13,22,NULL,NULL),(14,23,NULL,NULL),(15,22,NULL,NULL),(16,23,NULL,NULL),(17,22,NULL,NULL),(18,23,NULL,NULL),(19,22,NULL,NULL),(20,23,NULL,NULL);

INSERT INTO `cantidad_por_talle_producto` (`cantidad`, `id`, `talle`) VALUES (64,1,'L'),(64,1,'M'),(64,1,'S'),(16,1,'XL'),(16,1,'XXL'),(16,2,'L'),(144,2,'M'),(128,2,'S'),(0,2,'XL'),(60,3,'L'),(60,3,'M'),(75,3,'S'),(15,3,'XL'),(16,3,'XXL'),(15,4,'L'),(135,4,'M'),(120,4,'S'),(0,4,'XL'),(0,5,'L'),(0,5,'M'),(1,5,'S'),(0,5,'XL'),(1,5,'XXL'),(0,6,'L'),(1,6,'M'),(0,6,'S'),(0,6,'XL'),(60,7,'L'),(60,7,'M'),(74,7,'S'),(15,7,'XL'),(15,7,'XXL'),(15,8,'L'),(134,8,'M'),(120,8,'S'),(0,8,'XL'),(60,9,'L'),(60,9,'M'),(74,9,'S'),(15,9,'XL'),(15,9,'XXL'),(15,10,'L'),(134,10,'M'),(120,10,'S'),(0,10,'XL'),(64,11,'L'),(80,11,'M'),(48,11,'S'),(32,11,'XL'),(16,11,'XXL'),(16,12,'L'),(112,12,'M'),(96,12,'S'),(16,12,'XL'),(64,13,'L'),(79,13,'M'),(48,13,'S'),(32,13,'XL'),(16,13,'XXL'),(16,14,'L'),(112,14,'M'),(96,14,'S'),(16,14,'XL'),(0,15,'L'),(0,15,'M'),(0,15,'S'),(0,15,'XL'),(0,15,'XXL'),(0,16,'L'),(2,16,'M'),(0,16,'S'),(0,16,'XL'),(64,17,'L'),(79,17,'M'),(48,17,'S'),(32,17,'XL'),(16,17,'XXL'),(16,18,'L'),(110,18,'M'),(96,18,'S'),(15,18,'XL'),(64,19,'L'),(79,19,'M'),(48,19,'S'),(32,19,'XL'),(16,19,'XXL'),(16,20,'L'),(110,20,'M'),(96,20,'S'),(15,20,'XL');

INSERT INTO `warehouse` (`enable`, `id`, `product_id`, `location`, `section`) VALUES (_binary '\0',1,1,NULL,NULL),(_binary '\0',2,2,NULL,NULL),(_binary '\0',3,3,NULL,NULL),(_binary '\0',4,4,NULL,NULL),(_binary '\0',8,8,NULL,NULL),(_binary '\0',9,9,NULL,NULL),(_binary '\0',10,10,NULL,NULL),(_binary '\0',11,11,NULL,NULL),(_binary '\0',12,12,NULL,NULL),(_binary '\0',13,13,NULL,NULL),(_binary '\0',14,14,NULL,NULL),(_binary '\0',15,15,NULL,NULL),(_binary '\0',16,16,NULL,NULL),(_binary '\0',17,17,NULL,NULL),(_binary '\0',18,18,NULL,NULL),(_binary '\0',19,19,NULL,NULL),(_binary '\0',20,20,NULL,NULL),(_binary '\0',21,21,NULL,NULL),(_binary '\0',22,22,NULL,NULL),(_binary '\0',23,23,NULL,NULL),(_binary '\0',24,24,NULL,NULL),(_binary '\0',25,25,NULL,NULL);

INSERT INTO `cantidad_por_talle_producto_warehouse` (`cantidad`, `id`, `talle`) VALUES (0,1,'L'),(0,1,'M'),(0,1,'S'),(0,1,'XL'),(0,1,'XXL'),(0,2,'L'),(0,2,'M'),(0,2,'S'),(0,2,'XL'),(49,3,'L'),(38,3,'M'),(26,3,'S'),(3,3,'XL'),(6,3,'XXL'),(0,4,'L'),(0,4,'M'),(0,4,'S'),(0,4,'XL'),(0,8,'L'),(0,8,'M'),(0,8,'S'),(0,8,'XL'),(0,8,'XXL'),(0,9,'L'),(0,9,'M'),(0,9,'S'),(0,9,'XL'),(0,10,'L'),(0,10,'M'),(0,10,'S'),(0,10,'XL'),(0,10,'XXL'),(0,11,'L'),(0,11,'M'),(0,11,'S'),(0,11,'XL'),(0,12,'L'),(0,12,'M'),(0,12,'S'),(0,12,'XL'),(0,12,'XXL'),(0,13,'L'),(0,13,'M'),(0,13,'S'),(0,13,'XL'),(0,14,'L'),(0,14,'M'),(0,14,'S'),(0,14,'XL'),(0,14,'XXL'),(0,15,'L'),(0,15,'M'),(0,15,'S'),(0,15,'XL'),(0,16,'L'),(0,16,'M'),(0,16,'S'),(0,16,'XL'),(0,16,'XXL'),(0,17,'L'),(0,17,'M'),(0,17,'S'),(0,17,'XL'),(0,18,'L'),(0,18,'M'),(0,18,'S'),(0,18,'XL'),(0,18,'XXL'),(0,19,'L'),(0,19,'M'),(0,19,'S'),(0,19,'XL'),(0,20,'L'),(0,20,'M'),(0,20,'S'),(0,20,'XL'),(0,20,'XXL'),(0,21,'L'),(0,21,'M'),(0,21,'S'),(0,21,'XL'),(0,22,'L'),(0,22,'M'),(0,22,'S'),(0,22,'XL'),(0,22,'XXL'),(0,23,'L'),(0,23,'M'),(0,23,'S'),(0,23,'XL'),(0,24,'L'),(0,24,'M'),(0,24,'S'),(0,24,'XL'),(0,24,'XXL'),(0,25,'L'),(0,25,'M'),(0,25,'S'),(0,25,'XL');

INSERT INTO `control_spread_sheet_amount_per_size_received_for_products` (`amount_per_size_received_for_products_id`, `control_spread_sheet_id`) VALUES (9,1),(10,1),(19,2),(20,2);

INSERT INTO `cut_spreadsheets_amount_per_size_for_products` (`amount_per_size_for_products_id`, `cut_spread_sheet_id`) VALUES (1,1),(2,1),(11,2),(12,2);

INSERT INTO `length_fabrics_details` (`number_of_layers`, `fabric_id`, `id`) VALUES (16,12,1),(16,12,2);

INSERT INTO `cut_spreadsheets_fabric_length_details` (`cut_spread_sheet_id`, `fabric_length_details_id`) VALUES (1,1),(2,2);

INSERT INTO `detalle_tira_modelo` (`id`, `tira_id`) VALUES (1,1),(3,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(12,1),(15,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(27,1),(28,1),(14,2),(2,3),(4,3),(11,3),(13,3),(16,5),(17,5),(18,5);

INSERT INTO `input_quantity_for_spread_sheet` (`quantity`, `id`, `input_id`) VALUES (0,1,16),(0,2,19),(0,3,20),(0,4,6),(0,5,23),(0,6,8),(0,7,9),(0,8,16),(0,9,19),(0,10,20),(0,11,6),(0,12,23),(0,13,8),(0,14,9);

INSERT INTO `map_tiras_por_talle` (`cantidad_tiras`, `id`, `talle`) VALUES (1,1,'L'),(1,1,'M'),(1,1,'S'),(1,1,'XL'),(1,1,'XXL'),(1,2,'L'),(1,2,'M'),(1,2,'S'),(1,2,'XL'),(1,2,'XXL'),(1,3,'L'),(1,3,'M'),(1,3,'S'),(1,3,'XL'),(1,3,'XXL'),(1,4,'L'),(1,4,'M'),(1,4,'S'),(1,4,'XL'),(1,4,'XXL'),(1,5,'L'),(1,5,'M'),(1,5,'S'),(1.5,5,'XL'),(1.5,5,'XXL'),(1,6,'L'),(1,6,'M'),(1,6,'S'),(1,6,'XL'),(1,6,'XXL'),(1,7,'L'),(1,7,'M'),(1,7,'S'),(1,7,'XL'),(1,7,'XXL'),(2,8,'L'),(2,8,'M'),(2,8,'S'),(2,8,'XL'),(2,8,'XXL'),(1,9,'L'),(1,9,'M'),(1,9,'S'),(1,9,'XL'),(1,9,'XXL'),(1,10,'L'),(1,10,'M'),(1,10,'S'),(1,10,'XL'),(1,10,'XXL'),(1,11,'L'),(1,11,'M'),(1,11,'S'),(1,11,'XL'),(1,11,'XXL'),(1,12,'L'),(1,12,'M'),(1,12,'S'),(1,12,'XL'),(1,12,'XXL'),(1,13,'L'),(1,13,'M'),(1,13,'S'),(1,13,'XL'),(1,13,'XXL'),(1.5,14,'L'),(1.5,14,'M'),(1.5,14,'S'),(1.5,14,'XL'),(1.5,14,'XXL'),(1.5,15,'L'),(1.5,15,'M'),(1.5,15,'S'),(1.5,15,'XL'),(1.5,15,'XXL'),(1.5,16,'L'),(1.5,16,'M'),(1.5,16,'S'),(1.5,16,'XL'),(1.5,16,'XXL'),(2,17,'L'),(2,17,'M'),(2,17,'S'),(2,17,'XL'),(2,17,'XXL'),(1,18,'L'),(1,18,'M'),(1,18,'S'),(1,18,'XL'),(1,19,'L'),(1,19,'M'),(1,19,'S'),(1,19,'XL'),(1,20,'L'),(1,20,'M'),(1,20,'S'),(1,20,'XL'),(1,21,'L'),(1,21,'M'),(1,21,'S'),(1,21,'XL'),(1.5,22,'L'),(1.5,22,'M'),(1.5,22,'S'),(1.5,22,'XL'),(1,23,'L'),(1,23,'M'),(1,23,'S'),(1,23,'XL'),(1,24,'L'),(1,24,'M'),(1,24,'S'),(1,24,'XL'),(1,25,'L'),(1,25,'M'),(1,25,'S'),(1,25,'XL'),(1,27,'L'),(1,27,'M'),(1,27,'S'),(1,27,'XL'),(1,28,'L'),(1,28,'M'),(1,28,'S'),(1,28,'XL');

INSERT INTO `model_detalle_insumos` (`detalle_insumos_id`, `model_id`) VALUES (1,1),(2,1),(3,1),(4,1),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,3),(12,3),(13,3),(14,4),(18,4),(15,5),(16,5),(17,5),(19,6),(20,6),(21,6),(22,7),(23,7),(24,8),(25,8),(26,9),(27,9),(28,9),(29,9),(30,9),(31,10),(32,10),(33,10),(34,10),(35,10),(36,10),(37,11),(38,11),(39,11),(40,11),(41,11),(42,12),(43,12),(44,13),(45,14),(46,14),(47,14),(48,14),(49,15),(50,15),(51,15),(52,16),(53,16),(54,16),(55,16),(56,17),(57,17),(58,17),(59,17),(60,17),(61,18),(62,18),(63,18),(64,18),(65,19),(66,19),(67,19),(68,19),(69,20),(70,20),(71,20),(72,20),(73,21),(74,21),(75,22),(76,22),(77,22),(78,23),(79,23),(80,23),(81,23),(82,24),(83,24),(84,24),(88,26),(89,26),(90,26),(91,27),(92,27),(93,27),(94,27),(95,27);

INSERT INTO `model_detalle_tira_modelo` (`detalle_tira_modelo_id`, `model_id`) VALUES (1,1),(2,1),(3,2),(4,2),(5,3),(6,4),(7,5),(8,6),(9,8),(10,9),(11,9),(12,10),(13,10),(14,11),(15,13),(16,14),(17,16),(18,17),(19,18),(20,19),(21,20),(22,21),(23,22),(24,23),(25,24),(27,26),(28,27);

INSERT INTO `model_talles_disponibles` (`model_id`, `talles_disponibles`) VALUES (1,'S'),(1,'M'),(1,'L'),(1,'XL'),(1,'XXL'),(2,'S'),(2,'M'),(2,'L'),(2,'XL'),(2,'XXL'),(3,'S'),(3,'M'),(3,'XL'),(3,'L'),(3,'XXL'),(5,'S'),(5,'M'),(5,'L'),(5,'XL'),(5,'XXL'),(4,'S'),(4,'M'),(4,'L'),(4,'XL'),(4,'XXL'),(6,'S'),(6,'M'),(6,'L'),(6,'XL'),(6,'XXL'),(7,'S'),(7,'M'),(7,'L'),(7,'XL'),(7,'XXL'),(8,'S'),(8,'M'),(8,'L'),(8,'XL'),(8,'XXL'),(9,'S'),(9,'M'),(9,'L'),(9,'XL'),(9,'XXL'),(10,'S'),(10,'M'),(10,'L'),(10,'XL'),(10,'XXL'),(11,'S'),(11,'M'),(11,'L'),(11,'XL'),(11,'XXL'),(12,'S'),(12,'M'),(12,'L'),(12,'XL'),(12,'XXL'),(13,'S'),(13,'M'),(13,'L'),(13,'XL'),(13,'XXL'),(14,'S'),(14,'M'),(14,'L'),(14,'XL'),(14,'XXL'),(15,'S'),(15,'M'),(15,'L'),(15,'XL'),(15,'XXL'),(16,'S'),(16,'M'),(16,'L'),(16,'XL'),(16,'XXL'),(17,'S'),(17,'M'),(17,'L'),(17,'XL'),(18,'S'),(18,'M'),(18,'L'),(18,'XL'),(19,'S'),(19,'M'),(19,'L'),(19,'XL'),(20,'S'),(20,'M'),(20,'L'),(20,'XL'),(21,'S'),(21,'M'),(21,'L'),(21,'XL'),(22,'S'),(22,'M'),(22,'L'),(22,'XL'),(23,'S'),(23,'M'),(23,'L'),(23,'XL'),(24,'S'),(24,'M'),(24,'L'),(24,'XL'),(26,'S'),(26,'M'),(26,'L'),(26,'XL'),(27,'S'),(27,'M'),(27,'L'),(27,'XL');

INSERT INTO `preparation_spreadsheets_amount_per_size_for_products` (`amount_per_size_for_products_id`, `preparation_spread_sheet_id`) VALUES (3,1),(4,1),(13,2),(14,2);

INSERT INTO `preparation_spreadsheets_input_quantity_for_spread_sheet` (`input_quantity_for_spread_sheet_id`, `preparation_spread_sheet_id`) VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2);

INSERT INTO `workshop-spreadsheet_amount_per_size_defective_for_products` (`amount_per_size_defective_for_products_id`, `work_shop_spread_sheet_id`) VALUES (5,1),(6,1),(15,2),(16,2);

INSERT INTO `workshop-spreadsheet_amount_per_size_for_products` (`amount_per_size_for_products_id`, `work_shop_spread_sheet_id`) VALUES (7,1),(8,1),(17,2),(18,2);


-- INSERT INTO `supplier` (`caracteristica`, `id`, `calle`, `cel_contacto`, `email`, `email_contacto`, `empresa`, `localidad`, `nombre_contacto`, `nro`, `sector`) VALUES
-- (54, 1, 'Uruguay', '114524534', 'alter@hotmail.com', 'alterContacto@hotmail.com', 'ALTER', 'Bs As', 'Alter', '2345', 'TEXTIL'),
-- (54, 2, 'Sarmiento', '115541534', 'textilex@textilex.com', 'juan@hotmail.com', 'Textilex', 'Bs As', 'Juan', '8473', 'TEXTIL'),
-- (54, 3, 'Alsina', '126373299', 'mundo-textil@hotmail.com', 'mundo-textilo@hotmail.com', 'Mundo Textil', 'Bs As', 'Ramiro', '2345', 'TEXTIL'),
-- (54, 4, 'LAVALLE', '2235578908', 'rodatextelas@gmail.comm', 'rodatextelas@gmail.comm', 'Rodatex', 'C.A.B.A', 'rodatext', '2443', 'TEXTIL'),
-- (54, 5, 'Salta', '112345342', 'Avios', 'juanavios@gmail.com', 'Avios', 'BsAs', 'Juan', '23425', 'avios'),
-- (54, 6, 'J.B. Justo', '2235578908', 'asia@gmail.com', 'asia@asia.com', 'Asia', 'Mar del Plata', 'asia', '4234', 'Etiquetas'),
-- (54, 7, 'Champagnat ', '2235578908', 'fox@fox.com', 'axel@fox.com', 'Fox', 'Mar del Plata', 'Axel', '1234', 'Textil');



