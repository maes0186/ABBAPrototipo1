
CREATE TABLE maestros.insumo_tope  ( 
	id               	bigint IDENTITY NOT NULL,
	tipo_insumo_enum 	bigint NOT NULL,
	tope             	int NOT NULL,
	insumo_id   	bigint NOT NULL,
	periodicidad_dias	int NOT NULL,
	CONSTRAINT PK__insumo_t__3213E83F0D2F417A PRIMARY KEY CLUSTERED(id)
)
ON PRIMARY
GO


-- Kit Glucometria
-- Glucometro
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (1, 1 ,(SELECT id FROM maestros.insumo WHERE codigo = '43212.0'), 365)
GO

-- tiras antes
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (1, 100 ,(SELECT id FROM maestros.insumo WHERE codigo = '26949.0'), 30)
GO

-- tiras ahora
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (1, 100 ,(SELECT id FROM maestros.insumo WHERE codigo = '43213.0'), 30)
GO

-- lanceta antes
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (1, 100 ,(SELECT id FROM maestros.insumo WHERE codigo = '3768.0'), 30)
GO

-- lanceta ahora
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (1, 100 ,(SELECT id FROM maestros.insumo WHERE codigo = '43214.0'), 30)
GO


-- Kit Ostomia
-- Pinza
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '29861.0'), 365)
GO

--Pasta Protectora
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '3719.0'), 365)
GO

--Bolsas Drenaje
--1018.0	BOLSA DE DRENAJE P/ OSTOMIA PEDIATRICA x32mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1018.0'), 365)
GO

--3706.0	BOLSA DE DRENAJE P/ OSTOMIA PEDIATRICA x45mm - 50 mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '3706.0'), 365)
GO

--1019.0	BOLSA DE DRENAJE P/ OSTOMIA x38mm - 40 mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1019.0'), 365)
GO

--1025.0	BOLSA DE DRENAJE P/ OSTOMIA x45mm - 50 mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1025.0'), 365)
GO

--1026.0	BOLSA DE DRENAJE P/ OSTOMIA x57mm - 60mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1026.0'), 365)
GO

--1027.0	BOLSA DE DRENAJE P/ OSTOMIA x70mm - 80 mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1027.0'), 365)
GO

--42599.0	BOLSA DE DRENAJE P/ OSTOMIA x100mm (UND) NO ESTA EN LA BD
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '42599.0'), 365)
GO

--Barreras
--1036.0	BARRERA PROTECTORA P/ BOLSA DE OSTOMIA x57mm - 60mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1036.0'), 365)
GO

--1037.0	BARRERA PROTECTORA P/ BOLSA DE OSTOMIA PEDIATRICA x32mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1037.0'), 365)
GO

--1038.0	BARRERA PROTECTORA P/ BOLSA DE OSTOMIA x38mm - 40 mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1038.0'), 365)
GO

--1039.0	BARRERA PROTECTORA P/ BOLSA DE OSTOMIA x70mm - 80 mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1039.0'), 365)
GO

--1040.0	BARRERA PROTECTORA P/ BOLSA DE OSTOMIA x45mm - 50 mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '1040.0'), 365)
GO

--3702.0	BARRERA FLEXIBLE P/ BOLSA DE OSTOMIA x45mm - 50 mm (UND) Tiene varios .2, .3 etc
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '3702.0'), 365)
GO

--3703.0	BARRERA FLEXIBLE P/ BOLSA DE OSTOMIA x57mm -  60 mm(UND) Tiene varios .2, .3 etc
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '3703.0'), 365)
GO

--3704.0	BARRERA FLEXIBLE P/ BOLSA DE OSTOMIA x70mm - 80 mm(UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '3704.0'), 365)
GO

--3707.0	BARRERA PEDIATRICA FLEXIBLE P/ BOLSA DE OSTOMIA x45mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '3707.0'), 365)
GO

--30801.0	BARRERA CONVEXA P/BOLSA DE OSTOMIA 40mm - 45mm (UND)
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '30801.0'), 365)
GO

--42600.0	BARRERA PROTECTORA P/ BOLSA DE OSTOMIA x100mm (UND) NO ESTA EN LA BD
INSERT INTO maestros.insumo_tope (tipo_insumo_enum, tope, insumo_id, periodicidad_dias)
VALUES (2, 104 ,(SELECT id FROM maestros.insumo WHERE codigo = '42600.0'), 365)
GO