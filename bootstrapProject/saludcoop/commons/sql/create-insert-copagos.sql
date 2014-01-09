CREATE TABLE maestros.montos_copago_contributivo (
	id serial not null,
	anio int4 not null,
	nivel_ibc_id int4 not null,
	valor_cuota_moderadora decimal not null,
	copago_porcentaje_valor_servicio decimal not null,
	copago_tope_anual_misma_patologia decimal not null,
	copago_tope_anual_cualquier_patologia decimal not null)
GO

CREATE UNIQUE INDEX uq_montos_copago_contributivo_anio_nivel_ibc 
ON maestros.montos_copago_contributivo (anio, nivel_ibc_id)
GO

ALTER TABLE maestros.montos_copago_contributivo
ADD CONSTRAINT fk_montos_copago_contributivo_nivel_ibc
FOREIGN KEY (nivel_ibc_id)
REFERENCES maestros.nivel_ibc (id)
GO

CREATE TABLE maestros.montos_copago_subsidiado (
	id serial not null,
	anio int4 not null,
	nivel_sisben_id int4 not null,
	copago_porcentaje_valor_servicio decimal not null,
	copago_tope_anual_misma_patologia decimal not null,
	copago_tope_anual_cualquier_patologia decimal not null)
GO

CREATE UNIQUE INDEX uq_montos_copago_subsidiado_anio_nivel_sisben 
ON maestros.montos_copago_subsidiado (anio, nivel_sisben_id)
GO

ALTER TABLE maestros.montos_copago_subsidiado
ADD CONSTRAINT fk_montos_copago_contributivo_nivel_sisben
FOREIGN KEY (nivel_sisben_id)
REFERENCES maestros.nivel_sisben (id)
GO

INSERT INTO maestros.montos_copago_contributivo (anio, nivel_ibc_id, 
	valor_cuota_moderadora, 
	copago_porcentaje_valor_servicio, copago_tope_anual_misma_patologia, copago_tope_anual_cualquier_patologia)
VALUES (2013, (SELECT id FROM maestros.nivel_ibc WHERE codigo = '1'),
	2300,
	11.5, 169187, 338963)
GO

INSERT INTO maestros.montos_copago_contributivo (anio, nivel_ibc_id, 
	valor_cuota_moderadora, 
	copago_porcentaje_valor_servicio, copago_tope_anual_misma_patologia, copago_tope_anual_cualquier_patologia)
VALUES (2013, (SELECT id FROM maestros.nivel_ibc WHERE codigo = '2'),
	9100,
	17.3, 677925, 1355850)
GO

INSERT INTO maestros.montos_copago_contributivo (anio, nivel_ibc_id, 
	valor_cuota_moderadora, 
	copago_porcentaje_valor_servicio, copago_tope_anual_misma_patologia, copago_tope_anual_cualquier_patologia)
VALUES (2013, (SELECT id FROM maestros.nivel_ibc WHERE codigo = '3'),
	23900,
	23, 1355850, 2711700)
GO

INSERT INTO maestros.montos_copago_subsidiado (anio, nivelsisben_id, 
	copago_porcentaje_valor_servicio, copago_tope_anual_misma_patologia, copago_tope_anual_cualquier_patologia)
VALUES (2013, (SELECT id FROM maestros.nivel_ibc WHERE codigo = '1'),
	0, 0, 0)
GO

INSERT INTO maestros.montos_copago_subsidiado (anio, nivelsisben_id, 
	copago_porcentaje_valor_servicio, copago_tope_anual_misma_patologia, copago_tope_anual_cualquier_patologia)
VALUES (2013, (SELECT id FROM maestros.nivel_ibc WHERE codigo = '2'),
	10, 294750, 589500)
GO

INSERT INTO maestros.montos_copago_subsidiado (anio, nivelsisben_id, 
	copago_porcentaje_valor_servicio, copago_tope_anual_misma_patologia, copago_tope_anual_cualquier_patologia)
VALUES (2013, (SELECT id FROM maestros.nivel_ibc WHERE codigo = '3'),
	10, 294750, 589500)
GO
