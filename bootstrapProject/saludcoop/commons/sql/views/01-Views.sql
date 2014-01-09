-- DROP VIEW vista.tarifa_procedimiento_view
-- GO
-- DROP VIEW vista.tarifa_medicamento_view
-- GO
-- DROP VIEW vista.afiliado_programa_item_eximido_view
-- GO
-- DROP VIEW vista.ubicacion_sede_ips_efector_procedimiento
-- GO
-- DROP VIEW vista.ubicacion_sede_ips_proveedor_medicamento
-- GO

CREATE SCHEMA vista
GO

CREATE VIEW vista.tarifa_procedimiento_view
AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
        con.sede_ips_id as sede_ips_id,
		pta.procedimiento_id as procedimiento_id, 
		sco.servicio_id as servicio_id, 
		eco.especialidad_id as especialidad_id, 
		con.fecha_inicio_contrato as fecha_inicio_contrato,
		con.fecha_fin_contrato as fecha_finalizacion_contrato,
		sco.fecha_vencimiento as fecha_finalizacion_servicio_contratado,
		eco.fecha_vencimiento as fecha_finalizacion_especialidad_contratada,
		pco.fecha_vencimiento as fecha_finalizacion_procedimiento_contratado,
        tar.tipo_tarifario_enum as tipo_tarifario_id,
        pta.factor as factor,
        pta.uvr as uvr,
		pta.valor as valor,
		pro.es_quirurgico as quirurgico

FROM maestros.contrato con
	INNER JOIN maestros.servicio_contratado sco
		ON con.id = sco.contrato_id
	INNER JOIN maestros.especialidad_contratada eco
		ON sco.id = eco.servicio_contratado_id
	INNER JOIN maestros.procedimiento_contratado pco
		ON eco.id = pco.especialidad_contratada_id
	INNER JOIN maestros.procedimiento pro
		ON pro.id = pco.procedimiento_id
	INNER JOIN maestros.tarifario tar
		ON pco.tarifario_id = tar.id
	INNER JOIN maestros.procedimiento_tarifa pta
		ON tar.id = pta.tarifario_id
		AND pro.id = pta.procedimiento_id

WHERE con.fecha_delete IS NULL
	AND sco.fecha_delete IS NULL
	AND eco.fecha_delete IS NULL
	AND pco.fecha_delete IS NULL
	AND tar.fecha_delete IS NULL
	AND pta.fecha_delete IS NULL

GO

CREATE VIEW vista.tarifa_medicamento_view
AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
        con.sede_ips_id as sede_ips_id,
		mta.medicamento_id as medicamento_id, 
		sco.servicio_id as servicio_id, 
		eco.especialidad_id as especialidad_id, 
		con.fecha_inicio_contrato as fecha_inicio_contrato,
		con.fecha_fin_contrato as fecha_finalizacion_contrato,
		sco.fecha_vencimiento as fecha_finalizacion_servicio_contratado,
		eco.fecha_vencimiento as fecha_finalizacion_especialidad_contratada,
		mco.fecha_vencimiento as fecha_finalizacion_medicamento_contratado,
        tar.tipo_tarifario_enum as tipo_tarifario_id,
        mta.factor as factor,
        mta.uvr as uvr,
		mta.valor as valor,
		med.es_quirurgico as quirurgico

FROM maestros.contrato con
	INNER JOIN maestros.servicio_contratado sco
		ON con.id = sco.contrato_id
	INNER JOIN maestros.especialidad_contratada eco
		ON sco.id = eco.servicio_contratado_id
	INNER JOIN maestros.medicamento_contratado mco
		ON eco.id = mco.especialidad_contratada_id
	INNER JOIN maestros.medicamento med
		ON med.id = mco.medicamento_id
	INNER JOIN maestros.tarifario tar
		ON mco.tarifario_id = tar.id
	INNER JOIN maestros.medicamento_tarifa mta
		ON tar.id = mta.tarifario_id
		AND med.id = mta.medicamento_id

WHERE con.fecha_delete IS NULL
	AND sco.fecha_delete IS NULL
	AND eco.fecha_delete IS NULL
	AND mco.fecha_delete IS NULL
	AND tar.fecha_delete IS NULL
	AND mta.fecha_delete IS NULL

GO

CREATE VIEW vista.afiliado_programa_item_eximido_view
AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
		afp.afiliado_id as afiliado_id,
		afp.programa_id as programa_id,
		prp.procedimiento_id as procedimiento_id,
		prm.medicamento_id as medicamento_id

FROM maestros.afiliado_programa afp
	LEFT JOIN maestros.programa_procedimiento prp
		ON afp.programa_id = prp.programa_id 
		AND afp.fecha_delete IS NULL
		AND prp.fecha_delete IS NULL
	LEFT JOIN maestros.procedimiento pro
		ON prp.procedimiento_id = pro.id
	LEFT JOIN maestros.programa_medicamento prm
		ON afp.programa_id = prm.programa_id
		AND afp.fecha_delete IS NULL
		AND prp.fecha_delete IS NULL
	LEFT JOIN maestros.medicamento med
		ON prm.medicamento_id = med.id

WHERE (pro.es_p_y_pp = 1 OR med.es_p_y_pp = 1)
	AND afp.fecha_delete IS NULL
	AND prp.fecha_delete IS NULL
	AND pro.fecha_delete IS NULL
	AND prm.fecha_delete IS NULL
	AND med.fecha_delete IS NULL
GO

CREATE VIEW vista.ubicacion_sede_ips_efector_procedimiento
AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
		eps.id as eps_id, sede.id as sede_ips_id, sede.nombre as sede_ips_nombre, muni.id as municipio_id, 
		divsec.id as division_seccional_id, reg.id as regional_id, 
		proce.id as procedimiento_id, servcont.servicio_id as servicio_id, espccont.especialidad_id as especialidad_id, 
		minuta.codigo as tipo_minuta_codigo, proccont.monto_fijo as procedimiento_monto_fijo,
		pta.valor as procedimiento_valor_original, proccont.porcentaje_negociacion as porcentaje_negociacion,
        pta.valor + (pta.valor * proccont.porcentaje_negociacion / 100) as procedimiento_valor_ajustado

FROM maestros.sede_ips sede
	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id
	INNER JOIN maestros.sede_ips_division_seccional sids ON sede.id = sids.sede_ips_id
    INNER JOIN maestros.division_seccional divsec ON sids.division_seccional_id = divsec.id
	INNER JOIN maestros.regional reg ON sede.regional_id = reg.id 
	INNER JOIN maestros.municipio muni ON sede.municipio_id = muni.id
	INNER JOIN maestros.contrato cont ON sede.id = cont.sede_ips_id
	INNER JOIN maestros.servicio_contratado servcont ON cont.id = servcont.contrato_id
	INNER JOIN maestros.especialidad_contratada espccont ON servcont.id = espccont.servicio_contratado_id
	INNER JOIN maestros.procedimiento_contratado proccont ON espccont.id = proccont.especialidad_contratada_id
	INNER JOIN maestros.procedimiento proce ON proccont.procedimiento_id = proce.id
	INNER JOIN maestros.tipo_minuta minuta ON proccont.tipo_minuta_id = minuta.id
	INNER JOIN maestros.tarifario tar ON proccont.tarifario_id = tar.id
	INNER JOIN maestros.procedimiento_tarifa pta ON tar.id = pta.tarifario_id AND proce.id = pta.procedimiento_id

WHERE sede.estado_ips_id <> 2
	AND cont.estado_contrato_id = 4
	AND proccont.estado_procedimiento_contratado_id = 4
	AND proce.estado_procedimiento_id = 1
	AND sede.fecha_delete IS NULL
	AND eps.fecha_delete IS NULL
	AND reg.fecha_delete IS NULL
	AND muni.fecha_delete IS NULL
	AND cont.fecha_delete IS NULL
	AND servcont.fecha_delete IS NULL
	AND espccont.fecha_delete IS NULL
	AND proccont.fecha_delete IS NULL
	AND proce.fecha_delete IS NULL
	AND minuta.fecha_delete IS NULL
	AND tar.fecha_delete IS NULL
	AND pta.fecha_delete IS NULL
	AND cont.fecha_inicio_contrato <= GETDATE()
	AND cont.fecha_fin_contrato >= GETDATE()
	AND servcont.fecha_vencimiento >= GETDATE()
	AND espccont.fecha_vencimiento >= GETDATE()
	AND proccont.fecha_vencimiento >= GETDATE()
	
GO

CREATE VIEW vista.ubicacion_sede_ips_proveedor_medicamento
AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
		eps.id as eps_id, sede.id as sede_ips_id, sede.nombre as sede_ips_nombre, muni.id as municipio_id, 
		divsec.id as division_seccional_id, reg.id as regional_id, 
		med.id as medicamento_id, servcont.servicio_id as servicio_id, espccont.especialidad_id as especialidad_id

FROM maestros.sede_ips sede
    INNER JOIN maestros.ips ips ON sede.ips_id = ips.id
	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id
	INNER JOIN maestros.sede_ips_division_seccional sids ON sede.id = sids.sede_ips_id
    INNER JOIN maestros.division_seccional divsec ON sids.division_seccional_id = divsec.id
	INNER JOIN maestros.regional reg ON sede.regional_id = reg.id 
	INNER JOIN maestros.municipio muni ON sede.municipio_id = muni.id
	INNER JOIN maestros.contrato cont ON sede.id = cont.sede_ips_id
	INNER JOIN maestros.servicio_contratado servcont ON cont.id = servcont.contrato_id
	INNER JOIN maestros.especialidad_contratada espccont ON servcont.id = espccont.servicio_contratado_id
	INNER JOIN maestros.medicamento_contratado medcont ON espccont.id = medcont.especialidad_contratada_id
	INNER JOIN maestros.medicamento med ON medcont.medicamento_id = med.id

WHERE sede.estado_ips_id <> 2
	AND cont.estado_contrato_id = 4
	AND medcont.estado_medicamento_contratado_id = 4
	AND med.estado_medicamento_id = 1
    AND (((med.es_epsifarma = 1 OR med.suministra_medicarte = 0) AND ips.tipo_identificacion_id = 1 AND ips.numero_identificacion = '999999999')
         OR
         (med.suministra_medicarte = 1 AND ips.tipo_identificacion_id = 1 AND ips.numero_identificacion = '900219866')
        )
    AND sede.fecha_delete IS NULL
	AND eps.fecha_delete IS NULL
	AND reg.fecha_delete IS NULL
	AND muni.fecha_delete IS NULL
	AND cont.fecha_delete IS NULL
	AND servcont.fecha_delete IS NULL
	AND espccont.fecha_delete IS NULL
	AND medcont.fecha_delete IS NULL
	AND med.fecha_delete IS NULL
	AND cont.fecha_inicio_contrato <= GETDATE()
	AND cont.fecha_fin_contrato >= GETDATE()
	AND servcont.fecha_vencimiento >= GETDATE()
	AND espccont.fecha_vencimiento >= GETDATE()
	AND medcont.fecha_vencimiento >= GETDATE()

GO

CREATE INDEX ix_solicitud_fecha_creacion ON transaccional.solicitud (fecha_creacion)
GO

alter table transaccional.grupo_autorizacion drop column clasificacion_servicio_enum
go

