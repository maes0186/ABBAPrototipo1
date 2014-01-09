DROP SCHEMA IF EXISTS historial CASCADE
GO

CREATE SCHEMA historial
GO

CREATE TABLE historial.historial_solicitud ( 
	id                       			SERIAL NOT NULL,
	numero_solicitud					int8 NOT NULL,
	id_sede_ips							int8 NOT NULL,
	id_regional							int8 NOT NULL,
	descripcion_regional				varchar(50) NOT NULL,
	id_tipo_documento_afiliado			int4 NOT NULL,
	codigo_tipo_documento_afiliado		varchar(50) NOT NULL,
	descripcion_tipo_documento_afiliado	varchar(100) NOT NULL,
	numero_documento_afiliado			varchar(20) NOT NULL,
	fecha								timestamp NOT NULL,
	descripcion							varchar(255) NOT NULL,
	id_estado							int8 NOT NULL,
	descripcion_estado					varchar(255) NOT NULL,
	tipo_solicitud						varchar(100) NOT NULL,
	codigo_producto						varchar(100) NOT NULL,
	unidades_aprobadas					int4 NOT NULL,
	periodo_aprobado					int4 NOT NULL,
	dias_por_periodo					int4 NOT NULL,
	
	PRIMARY KEY (id)
)
GO

CREATE INDEX IDX_historial_solicitud_numero_solicitud on historial.historial_solicitud(numero_solicitud)
GO
 
CREATE INDEX IDX_historial_solicitud_id_sede_ips on historial.historial_solicitud(id_sede_ips)
GO
	
CREATE INDEX IDX_historial_solicitud_codigo_regional on historial.historial_solicitud(codigo_regional)
GO
	
CREATE INDEX IDX_historial_solicitud_id_tipo_documento_afiliado on historial.historial_solicitud(id_tipo_documento_afiliado)
GO
	
CREATE INDEX IDX_historial_solicitud_numero_documento_afiliado on historial.historial_solicitud(numero_documento_afiliado)
GO
	
CREATE INDEX IDX_historial_solicitud_fecha on historial.historial_solicitud(fecha)
GO
	
CREATE INDEX IDX_historial_solicitud_estado on historial.historial_solicitud(estado)
GO
	
CREATE INDEX IDX_historial_solicitud_tipo_solicitud on historial.historial_solicitud(tipo_solicitud)
GO
