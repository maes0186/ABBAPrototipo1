CREATE SCHEMA auxiliar
GO

CREATE TABLE auxiliar.totalizacion_copago_afiliado (
	id serial not null,
	anio int4 not null,
	afiliado_id int8 not null,
	total_copagos decimal not null)
GO

CREATE UNIQUE INDEX uq_totalizacion_copago_afiliado_anio_afiliado_id
ON auxiliar.totalizacion_copago_afiliado (anio, afiliado_id)
GO

ALTER TABLE auxiliar.totalizacion_copago_afiliado
ADD CONSTRAINT fk_totalizacion_copago_afiliado_afiliado
FOREIGN KEY (afiliado_id)
REFERENCES maestros.afiliado (id)
GO

CREATE TABLE auxiliar.totalizacion_copago_afiliado_diagnostico (
	id serial not null,
	anio int4 not null,
	afiliado_id int8 not null,
	diagnostico_id int8 not null,
	total_copagos decimal not null)
GO

CREATE UNIQUE INDEX uq_totalizacion_copago_afiliado_anio_afiliado_id_diagnostico_id
ON auxiliar.totalizacion_copago_afiliado_diagnostico (anio, afiliado_id, diagnostico_id)
GO

ALTER TABLE auxiliar.totalizacion_copago_afiliado_diagnostico
ADD CONSTRAINT fk_totalizacion_copago_afiliado_afiliado
FOREIGN KEY (afiliado_id)
REFERENCES maestros.afiliado (id)
GO

ALTER TABLE auxiliar.totalizacion_copago_afiliado_diagnostico
ADD CONSTRAINT fk_totalizacion_copago_afiliado_diagnostico
FOREIGN KEY (diagnostico_id)
REFERENCES maestros.diagnostico (id)
GO
