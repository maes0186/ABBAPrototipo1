/*
Script generado por Aqua Data Studio 10.0.7 en ene-09-2014 02:12:24 PM
Base de datos: db_saludcoop
Esquema: <Todos los esquemas>
Objetos: DATATYPE, DEFAULT, RULE, TABLE, VIEW, SYNONYM, PROCEDURE, FUNCTION, INDEX, TRIGGER
*/
ALTER TABLE "maestros"."vigencia"
	DROP CONSTRAINT "fk_vigencia_tipo_tecnologia"
GO
ALTER TABLE "maestros"."vigencia"
	DROP CONSTRAINT "fk_vigencia_tipo_ppm"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_usuario"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_user"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_sede_ips"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_profesional_especialidad"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "fk_usuario_entidad_linea_de_frente"
GO
ALTER TABLE "security"."usuario_entidad"
	DROP CONSTRAINT "FK6B6BE5D85767757B"
GO
ALTER TABLE "security"."user_role"
	DROP CONSTRAINT "fk_user_role_user"
GO
ALTER TABLE "security"."user_role"
	DROP CONSTRAINT "fk_user_role_role"
GO
ALTER TABLE "security"."user_role"
	DROP CONSTRAINT "FK143BF46AC0A9DBBE"
GO
ALTER TABLE "ticket"."ticket_item"
	DROP CONSTRAINT "fk_ticket_item_ticket_cabecera"
GO
ALTER TABLE "ticket"."ticket_cabecera"
	DROP CONSTRAINT "fk_ticket_cabecera_autorizacion"
GO
ALTER TABLE "maestros"."tarifario_excepcion"
	DROP CONSTRAINT "fk_tarifario_excepcion_tarifario"
GO
ALTER TABLE "maestros"."tarifario"
	DROP CONSTRAINT "fk_tarifario_tipo_tarifario_id"
GO
ALTER TABLE "transaccional"."solicitud_procedimiento"
	DROP CONSTRAINT "fk_solicitud_procedimiento_solicitud_item"
GO
ALTER TABLE "transaccional"."solicitud_procedimiento"
	DROP CONSTRAINT "fk_solicitud_procedimiento_procedimiento"
GO
ALTER TABLE "transaccional"."solicitud_procedimiento"
	DROP CONSTRAINT "fk_solicitud_procedimiento_especialidad"
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	DROP CONSTRAINT "fk_solicitud_parcial_user"
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	DROP CONSTRAINT "fk_solicitud_parcial_tipo_identificacion_afiliado"
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	DROP CONSTRAINT "fk_solicitud_parcial_sede_ips"
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	DROP CONSTRAINT "FK60D09097C0A9DBBE"
GO
ALTER TABLE "transaccional"."solicitud_medicamento"
	DROP CONSTRAINT "fk_solicitud_medicamento_solicitud_item"
GO
ALTER TABLE "transaccional"."solicitud_medicamento"
	DROP CONSTRAINT "fk_solicitud_medicamento_medicamento"
GO
ALTER TABLE "transaccional"."solicitud_item"
	DROP CONSTRAINT "fk_solicitud_item_tipo_tecnologia"
GO
ALTER TABLE "transaccional"."solicitud_item"
	DROP CONSTRAINT "fk_solicitud_item_tipo_servicio"
GO
ALTER TABLE "transaccional"."solicitud_item"
	DROP CONSTRAINT "fk_solicitud_item_tipo_ppm"
GO
ALTER TABLE "transaccional"."solicitud_item"
	DROP CONSTRAINT "fk_solicitud_item_solicitud"
GO
ALTER TABLE "transaccional"."solicitud_item"
	DROP CONSTRAINT "fk_solicitud_item_diagnostico"
GO
ALTER TABLE "transaccional"."solicitud_item"
	DROP CONSTRAINT "fk_solicitud_item_autorizacion"
GO
ALTER TABLE "transaccional"."solicitud_insumo"
	DROP CONSTRAINT "fk_solicitud_insumo_insumo"
GO
ALTER TABLE "transaccional"."solicitud_diagnostico"
	DROP CONSTRAINT "fk_solicitud_diagnostico_solicitud"
GO
ALTER TABLE "transaccional"."solicitud_diagnostico"
	DROP CONSTRAINT "fk_solicitud_diagnostico_diagnostico"
GO
ALTER TABLE "transaccional"."solicitud"
	DROP CONSTRAINT "fk_solicitud_user"
GO
ALTER TABLE "transaccional"."solicitud"
	DROP CONSTRAINT "fk_solicitud_sede_ips"
GO
ALTER TABLE "transaccional"."solicitud"
	DROP CONSTRAINT "fk_solicitud_resumen_historia_clinica"
GO
ALTER TABLE "transaccional"."solicitud"
	DROP CONSTRAINT "fk_solicitud_profesional"
GO
ALTER TABLE "transaccional"."solicitud"
	DROP CONSTRAINT "fk_solicitud_afiliado"
GO
ALTER TABLE "transaccional"."solicitud"
	DROP CONSTRAINT "FKAF52BEA4C0A9DBBE"
GO
ALTER TABLE "maestros"."servicio_especialidad"
	DROP CONSTRAINT "fk_servicio_especialidad_servicio"
GO
ALTER TABLE "maestros"."servicio_especialidad"
	DROP CONSTRAINT "fk_servicio_especialidad_especialidad"
GO
ALTER TABLE "maestros"."servicio_contratado"
	DROP CONSTRAINT "fk_servicio_contratado_unidad_tiempo"
GO
ALTER TABLE "maestros"."servicio_contratado"
	DROP CONSTRAINT "fk_servicio_contratado_tipo_minuta"
GO
ALTER TABLE "maestros"."servicio_contratado"
	DROP CONSTRAINT "fk_servicio_contratado_tarifario_excepcion"
GO
ALTER TABLE "maestros"."servicio_contratado"
	DROP CONSTRAINT "fk_servicio_contratado_servicio"
GO
ALTER TABLE "maestros"."servicio_contratado"
	DROP CONSTRAINT "fk_servicio_contratado_estado_item_contratado"
GO
ALTER TABLE "maestros"."servicio_contratado"
	DROP CONSTRAINT "fk_servicio_contratado_contrato"
GO
ALTER TABLE "maestros"."servicio_contratado"
	DROP CONSTRAINT "FK999C1722A271F0F0"
GO
ALTER TABLE "maestros"."servicio"
	DROP CONSTRAINT "fk_servicio_unidad_funcional"
GO
ALTER TABLE "maestros"."sede_ips_division_seccional"
	DROP CONSTRAINT "fk_sede_ips_division_seccional_sede_ips"
GO
ALTER TABLE "maestros"."sede_ips_division_seccional"
	DROP CONSTRAINT "fk_sede_ips_division_seccional_division_seccional"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_ubicacion"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_tipo_servicio"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_tipo_ips"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_regional"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_regimen_tributario"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_regimen_juridico"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_municipio"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_localidad"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_ips"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_estado_ips"
GO
ALTER TABLE "maestros"."sede_ips"
	DROP CONSTRAINT "fk_sede_ips_eps"
GO
ALTER TABLE "transaccional"."role_estado_visible"
	DROP CONSTRAINT "fk_role_estado_visible_role"
GO
ALTER TABLE "transaccional"."role_estado_visible"
	DROP CONSTRAINT "fk_role_estado_visible_estado_visible"
GO
ALTER TABLE "transaccional"."role_estado_estados"
	DROP CONSTRAINT "fk_role_estado_estados_role_estado"
GO
ALTER TABLE "transaccional"."role_estado_estados"
	DROP CONSTRAINT "fk_role_estado_estados_estado_autorizacion"
GO
ALTER TABLE "security"."role_authority"
	DROP CONSTRAINT "fk_role_authority_role"
GO
ALTER TABLE "security"."role_authority"
	DROP CONSTRAINT "fk_role_authority_authority"
GO
ALTER TABLE "transaccional"."resumen_historia_clinica"
	DROP CONSTRAINT "fk_resumen_historia_clinica_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."resumen_historia_clinica"
	DROP CONSTRAINT "fk_resumen_historia_clinica_causa_externa"
GO
ALTER TABLE "transaccional"."resumen_diagnostico"
	DROP CONSTRAINT "fk_resumen_diagnostico_resumen"
GO
ALTER TABLE "transaccional"."resumen_diagnostico"
	DROP CONSTRAINT "fk_resumen_diagnostico_diagnostico"
GO
ALTER TABLE "maestros"."regional"
	DROP CONSTRAINT "fk_regional_division_seccional"
GO
ALTER TABLE "security"."recovery_token"
	DROP CONSTRAINT "fk_recovery_token_user"
GO
ALTER TABLE "security"."recovery_token"
	DROP CONSTRAINT "FK3BF6D8CFC0A9DBBE"
GO
ALTER TABLE "maestros"."programa_procedimiento"
	DROP CONSTRAINT "fk_programa_procedimiento_programa"
GO
ALTER TABLE "maestros"."programa_procedimiento"
	DROP CONSTRAINT "fk_programa_procedimiento_procedimiento"
GO
ALTER TABLE "maestros"."programa_medicamento"
	DROP CONSTRAINT "fk_programa_medicamento_programa"
GO
ALTER TABLE "maestros"."programa_medicamento"
	DROP CONSTRAINT "fk_programa_medicamento_medicamento"
GO
ALTER TABLE "maestros"."programa_diagnostico"
	DROP CONSTRAINT "fk_programa_diagnostico_programa"
GO
ALTER TABLE "maestros"."programa_diagnostico"
	DROP CONSTRAINT "fk_programa_diagnostico_diagnostico"
GO
ALTER TABLE "maestros"."profesional_especialidad_x_sede_ips"
	DROP CONSTRAINT "fk_profesional_especialidad_x_sede_ips_sede_ips"
GO
ALTER TABLE "maestros"."profesional_especialidad_x_sede_ips"
	DROP CONSTRAINT "fk_profesional_especialidad_x_sede_ips_profesional_especialidad"
GO
ALTER TABLE "maestros"."profesional_especialidad"
	DROP CONSTRAINT "fk_profesional_especialidad_profesional"
GO
ALTER TABLE "maestros"."profesional_especialidad"
	DROP CONSTRAINT "fk_profesional_especialidad_nivel_autorizacion"
GO
ALTER TABLE "maestros"."profesional_especialidad"
	DROP CONSTRAINT "fk_profesional_especialidad_estado_profesional"
GO
ALTER TABLE "maestros"."profesional_especialidad"
	DROP CONSTRAINT "fk_profesional_especialidad_especialidad"
GO
ALTER TABLE "maestros"."profesional"
	DROP CONSTRAINT "fk_profesional_tipo_profesional"
GO
ALTER TABLE "maestros"."profesional"
	DROP CONSTRAINT "fk_profesional_municipio"
GO
ALTER TABLE "maestros"."profesional"
	DROP CONSTRAINT "fk_profesional_identificacion_profesional"
GO
ALTER TABLE "maestros"."profesional"
	DROP CONSTRAINT "fk_profesional_eps"
GO
ALTER TABLE "maestros"."profesional"
	DROP CONSTRAINT "fk_profesional_division_seccional"
GO
ALTER TABLE "maestros"."procedimiento_tarifa"
	DROP CONSTRAINT "fk_procedimiento_tarifa_tarifario"
GO
ALTER TABLE "maestros"."procedimiento_tarifa"
	DROP CONSTRAINT "fk_procedimiento_tarifa_procedimiento"
GO
ALTER TABLE "transaccional"."procedimiento_pos_previo"
	DROP CONSTRAINT "fk_procedimiento_pos_previo_respuesta_clinica_observada"
GO
ALTER TABLE "transaccional"."procedimiento_pos_previo"
	DROP CONSTRAINT "fk_procedimiento_pos_previo_procedimiento"
GO
ALTER TABLE "transaccional"."procedimiento_pos_previo"
	DROP CONSTRAINT "fk_procedimiento_pos_previo_formulario_ctc_procedimiento"
GO
ALTER TABLE "transaccional"."procedimiento_homologo"
	DROP CONSTRAINT "fk_procedimiento_homologo_procedimiento"
GO
ALTER TABLE "transaccional"."procedimiento_homologo"
	DROP CONSTRAINT "fk_procedimiento_homologo_objetivo_procedimiento"
GO
ALTER TABLE "transaccional"."procedimiento_homologo"
	DROP CONSTRAINT "fk_procedimiento_homologo_formulario_ctc_procedimiento"
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	DROP CONSTRAINT "fk_procedimiento_contratado_unidad_tiempo"
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	DROP CONSTRAINT "fk_procedimiento_contratado_tipo_minuta"
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	DROP CONSTRAINT "fk_procedimiento_contratado_tarifario"
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	DROP CONSTRAINT "fk_procedimiento_contratado_procedimiento"
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	DROP CONSTRAINT "fk_procedimiento_contratado_estado_procedimiento_contratado"
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	DROP CONSTRAINT "fk_procedimiento_contratado_especialidad_contratada"
GO
ALTER TABLE "maestros"."procedimiento"
	DROP CONSTRAINT "fk_procedimiento_tipo_ppm"
GO
ALTER TABLE "maestros"."procedimiento"
	DROP CONSTRAINT "fk_procedimiento_genero"
GO
ALTER TABLE "maestros"."ocupacion"
	DROP CONSTRAINT "fk_ocupacion_eps"
GO
ALTER TABLE "maestros"."municipio_division_seccional"
	DROP CONSTRAINT "FK172CBFC16F255DF6"
GO
ALTER TABLE "maestros"."municipio_division_seccional"
	DROP CONSTRAINT "FK172CBFC1525BD951"
GO
ALTER TABLE "maestros"."municipio"
	DROP CONSTRAINT "fk_municipio_regional"
GO
ALTER TABLE "maestros"."municipio"
	DROP CONSTRAINT "fk_municipio_departamento"
GO
ALTER TABLE "maestros"."montos_copago_subsidiado"
	DROP CONSTRAINT "fk_montos_copago_subsidiado_nivel_sisben"
GO
ALTER TABLE "maestros"."montos_copago_contributivo"
	DROP CONSTRAINT "fk_montos_copago_contributivo_nivel_ibc"
GO
ALTER TABLE "maestros"."medicamento_tarifa"
	DROP CONSTRAINT "fk_medicamento_tarifa_tarifario"
GO
ALTER TABLE "maestros"."medicamento_tarifa"
	DROP CONSTRAINT "fk_medicamento_tarifa_medicamento"
GO
ALTER TABLE "transaccional"."medicamento_pos_previo"
	DROP CONSTRAINT "fk_medicamento_pos_previo_respuesta_clinica_observada"
GO
ALTER TABLE "transaccional"."medicamento_pos_previo"
	DROP CONSTRAINT "fk_medicamento_pos_previo_medicamento"
GO
ALTER TABLE "transaccional"."medicamento_pos_previo"
	DROP CONSTRAINT "fk_medicamento_pos_previo_formulario_ctc_medicamento"
GO
ALTER TABLE "maestros"."medicamento_contratado"
	DROP CONSTRAINT "fk_medicamento_contratado_unidad_tiempo"
GO
ALTER TABLE "maestros"."medicamento_contratado"
	DROP CONSTRAINT "fk_medicamento_contratado_tipo_minuta"
GO
ALTER TABLE "maestros"."medicamento_contratado"
	DROP CONSTRAINT "fk_medicamento_contratado_tarifario"
GO
ALTER TABLE "maestros"."medicamento_contratado"
	DROP CONSTRAINT "fk_medicamento_contratado_medicamento"
GO
ALTER TABLE "maestros"."medicamento_contratado"
	DROP CONSTRAINT "fk_medicamento_contratado_estado_medicamento_contratado"
GO
ALTER TABLE "maestros"."medicamento_contratado"
	DROP CONSTRAINT "fk_medicamento_contratado_especialidad_contratada"
GO
ALTER TABLE "maestros"."medicamento_condicionado"
	DROP CONSTRAINT "fk_medicamento_condicionado_medicamento"
GO
ALTER TABLE "maestros"."medicamento_condicionado"
	DROP CONSTRAINT "FK9A66458933ED69B1"
GO
ALTER TABLE "maestros"."medicamento"
	DROP CONSTRAINT "fk_medicamento_tipo_ppm"
GO
ALTER TABLE "maestros"."medicamento"
	DROP CONSTRAINT "fk_medicamento_programa_medicamento"
GO
ALTER TABLE "maestros"."medicamento"
	DROP CONSTRAINT "fk_medicamento_homologo"
GO
ALTER TABLE "maestros"."medicamento"
	DROP CONSTRAINT "fk_medicamento_genero"
GO
ALTER TABLE "maestros"."medicamento"
	DROP CONSTRAINT "fk_medicamento_estado_medicamento"
GO
ALTER TABLE "maestros"."localidad"
	DROP CONSTRAINT "fk_localidad_regional"
GO
ALTER TABLE "maestros"."localidad"
	DROP CONSTRAINT "fk_localidad_municipio"
GO
ALTER TABLE "maestros"."ips"
	DROP CONSTRAINT "fk_ips_tipo_ips"
GO
ALTER TABLE "maestros"."ips"
	DROP CONSTRAINT "fk_ips_tipo_identificacion"
GO
ALTER TABLE "maestros"."ips"
	DROP CONSTRAINT "fk_ips_estado_ips"
GO
ALTER TABLE "maestros"."insumo_tope"
	DROP CONSTRAINT "fk_insumo_tope_insumo"
GO
ALTER TABLE "maestros"."insumo_tarifa"
	DROP CONSTRAINT "fk_insumo_tarifa_tarifario"
GO
ALTER TABLE "maestros"."insumo_tarifa"
	DROP CONSTRAINT "fk_insumo_tarifa_insumo"
GO
ALTER TABLE "transaccional"."insumo_pos_previo"
	DROP CONSTRAINT "fk_insumo_pos_previo_respuesta_clinica_observada"
GO
ALTER TABLE "transaccional"."insumo_pos_previo"
	DROP CONSTRAINT "fk_insumo_pos_previo_insumo"
GO
ALTER TABLE "transaccional"."insumo_pos_previo"
	DROP CONSTRAINT "fk_insumo_pos_previo_formulario_ctc_insumo"
GO
ALTER TABLE "maestros"."insumo_contratado"
	DROP CONSTRAINT "fk_insumo_contratado_unidad_tiempo"
GO
ALTER TABLE "maestros"."insumo_contratado"
	DROP CONSTRAINT "fk_insumo_contratado_tipo_minuta"
GO
ALTER TABLE "maestros"."insumo_contratado"
	DROP CONSTRAINT "fk_insumo_contratado_tarifario"
GO
ALTER TABLE "maestros"."insumo_contratado"
	DROP CONSTRAINT "fk_insumo_contratado_insumo"
GO
ALTER TABLE "maestros"."insumo_contratado"
	DROP CONSTRAINT "fk_insumo_contratado_estado_insumo_contratado"
GO
ALTER TABLE "maestros"."insumo_contratado"
	DROP CONSTRAINT "fk_insumo_contratado_especialidad_contratada"
GO
ALTER TABLE "maestros"."insumo"
	DROP CONSTRAINT "fk_insumo_tipo_ppm"
GO
ALTER TABLE "maestros"."insumo"
	DROP CONSTRAINT "fk_insumo_programa_insumo"
GO
ALTER TABLE "maestros"."insumo"
	DROP CONSTRAINT "fk_insumo_homologo"
GO
ALTER TABLE "maestros"."insumo"
	DROP CONSTRAINT "fk_insumo_genero"
GO
ALTER TABLE "maestros"."insumo"
	DROP CONSTRAINT "fk_insumo_estado_insumo"
GO
ALTER TABLE "transaccional"."informacion_tutela"
	DROP CONSTRAINT "fk_informacion_tutela_devolucion"
GO
ALTER TABLE "transaccional"."informacion_tutela"
	DROP CONSTRAINT "fk_informacion_tutela_anulacion"
GO
ALTER TABLE "transaccional"."informacion_adicional"
	DROP CONSTRAINT "fk_informacion_adicional_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."informacion_adicional"
	DROP CONSTRAINT "fk_informacion_adicional_finalidad"
GO
ALTER TABLE "transaccional"."informacion_adicional"
	DROP CONSTRAINT "fk_informacion_adicional_causa_externa"
GO
ALTER TABLE "transaccional"."grupo_autorizacion"
	DROP CONSTRAINT "fk_grupo_autorizacion_solicitud"
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	DROP CONSTRAINT "fk_formulario_ctc_procedimiento_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	DROP CONSTRAINT "fk_formulario_ctc_procedimiento_solicitud_procedimiento"
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	DROP CONSTRAINT "fk_formulario_ctc_procedimiento_finalidad"
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	DROP CONSTRAINT "fk_formulario_ctc_procedimiento_causa_externa"
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	DROP CONSTRAINT "fk_formulario_ctc_medicamento_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	DROP CONSTRAINT "fk_formulario_ctc_medicamento_solicitud_medicamento"
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	DROP CONSTRAINT "fk_formulario_ctc_medicamento_medicamento_homologo"
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	DROP CONSTRAINT "fk_formulario_ctc_medicamento_finalidad"
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	DROP CONSTRAINT "fk_formulario_ctc_medicamento_causa_externa"
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	DROP CONSTRAINT "fk_formulario_ctc_insumo_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	DROP CONSTRAINT "fk_formulario_ctc_insumo_insumo_homologo"
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	DROP CONSTRAINT "fk_formulario_ctc_insumo_finalidad"
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	DROP CONSTRAINT "fk_formulario_ctc_insumo_causa_externa"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_tipo_prestacion"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_solicitud_procedimiento"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_origen_repeticion"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_objetivo_procedimiento"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_lateralidad"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_finalidad"
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	DROP CONSTRAINT "fk_formula_item_procedimiento_causa_externa"
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	DROP CONSTRAINT "fk_formula_item_medicamento_via_administracion"
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	DROP CONSTRAINT "fk_formula_item_medicamento_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	DROP CONSTRAINT "fk_formula_item_medicamento_solicitud_medicamento"
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	DROP CONSTRAINT "fk_formula_item_medicamento_finalidad"
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	DROP CONSTRAINT "fk_formula_item_medicamento_causa_externa"
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	DROP CONSTRAINT "fk_formula_item_insumo_tipo_catastrofico"
GO
ALTER TABLE "transaccional"."formula_item_insumo"
	DROP CONSTRAINT "fk_formula_item_insumo_finalidad"
GO
ALTER TABLE "transaccional"."formula_item_insumo"
	DROP CONSTRAINT "fk_formula_item_insumo_causa_externa"
GO
ALTER TABLE "transaccional"."formula_item_insumo"
	DROP CONSTRAINT "FK91243B7CDFA9E6C"
GO
ALTER TABLE "maestros"."especialidad_procedimiento"
	DROP CONSTRAINT "fk_especialidad_procedimiento_procedimiento"
GO
ALTER TABLE "maestros"."especialidad_procedimiento"
	DROP CONSTRAINT "fk_especialidad_procedimiento_especialidad"
GO
ALTER TABLE "maestros"."especialidad_medicamento"
	DROP CONSTRAINT "fk_especialidad_medicamento_medicamento"
GO
ALTER TABLE "maestros"."especialidad_medicamento"
	DROP CONSTRAINT "fk_especialidad_medicamento_especialidad"
GO
ALTER TABLE "maestros"."especialidad_insumo"
	DROP CONSTRAINT "fk_especialidad_insumo_insumo"
GO
ALTER TABLE "maestros"."especialidad_insumo"
	DROP CONSTRAINT "fk_especialidad_insumo_especialidad"
GO
ALTER TABLE "maestros"."especialidad_contratada"
	DROP CONSTRAINT "fk_especialidad_contratada_unidad_tiempo"
GO
ALTER TABLE "maestros"."especialidad_contratada"
	DROP CONSTRAINT "fk_especialidad_contratada_tipo_minuta"
GO
ALTER TABLE "maestros"."especialidad_contratada"
	DROP CONSTRAINT "fk_especialidad_contratada_tarifario_excepcion"
GO
ALTER TABLE "maestros"."especialidad_contratada"
	DROP CONSTRAINT "fk_especialidad_contratada_servicio_contratado"
GO
ALTER TABLE "maestros"."especialidad_contratada"
	DROP CONSTRAINT "fk_especialidad_contratada_estado_especialidad_contratada"
GO
ALTER TABLE "maestros"."especialidad_contratada"
	DROP CONSTRAINT "fk_especialidad_contratada_especialidad"
GO
ALTER TABLE "maestros"."eps"
	DROP CONSTRAINT "fk_eps_tipo_identificacion"
GO
ALTER TABLE "transaccional"."entrega"
	DROP CONSTRAINT "fk_entrega_solicitud_medicamento"
GO
ALTER TABLE "transaccional"."documento_soporte"
	DROP CONSTRAINT "fk_documento_soporte_tipo_doc_soporte"
GO
ALTER TABLE "transaccional"."documento_soporte"
	DROP CONSTRAINT "fk_documento_soporte_solicitud"
GO
ALTER TABLE "transaccional"."documento_soporte"
	DROP CONSTRAINT "FKEAAE089D2D293645"
GO
ALTER TABLE "maestros"."division_seccional"
	DROP CONSTRAINT "fk_division_seccional_eps"
GO
ALTER TABLE "maestros"."director_medico_regional"
	DROP CONSTRAINT "fk_director_medico_regional_regional"
GO
ALTER TABLE "maestros"."diagnostico_procedimiento"
	DROP CONSTRAINT "fk_diagnostico_procedimiento_procedimiento"
GO
ALTER TABLE "maestros"."diagnostico_procedimiento"
	DROP CONSTRAINT "fk_diagnostico_procedimiento_diagnostico"
GO
ALTER TABLE "maestros"."diagnostico_medicamento"
	DROP CONSTRAINT "fk_diagnostico_medicamento_medicamento"
GO
ALTER TABLE "maestros"."diagnostico_medicamento"
	DROP CONSTRAINT "fk_diagnostico_medicamento_diagnostico"
GO
ALTER TABLE "maestros"."diagnostico_insumo"
	DROP CONSTRAINT "fk_diagnostico_insumo_insumo"
GO
ALTER TABLE "maestros"."diagnostico_insumo"
	DROP CONSTRAINT "fk_diagnostico_insumo_diagnostico"
GO
ALTER TABLE "maestros"."diagnostico"
	DROP CONSTRAINT "fk_diagnostico_tipo_diagnostico"
GO
ALTER TABLE "maestros"."departamento_regional"
	DROP CONSTRAINT "fk_departamento_regional_regional"
GO
ALTER TABLE "maestros"."departamento_regional"
	DROP CONSTRAINT "fk_departamento_regional_departamento"
GO
ALTER TABLE "maestros"."contrato_tarifario"
	DROP CONSTRAINT "fk_contrato_tarifario_tipo_minuta"
GO
ALTER TABLE "maestros"."contrato_tarifario"
	DROP CONSTRAINT "fk_contrato_tarifario_tarifario"
GO
ALTER TABLE "maestros"."contrato_tarifario"
	DROP CONSTRAINT "fk_contrato_tarifario_estado_contrato_tarifario"
GO
ALTER TABLE "maestros"."contrato_tarifario"
	DROP CONSTRAINT "fk_contrato_tarifario_contrato"
GO
ALTER TABLE "maestros"."contrato"
	DROP CONSTRAINT "fk_contrato_tipo_plan_contrato"
GO
ALTER TABLE "maestros"."contrato"
	DROP CONSTRAINT "fk_contrato_sede_ips"
GO
ALTER TABLE "maestros"."contrato"
	DROP CONSTRAINT "fk_contrato_estado_contrato"
GO
ALTER TABLE "maestros"."contrato"
	DROP CONSTRAINT "fk_contrato_eps"
GO
ALTER TABLE "transaccional"."consumo"
	DROP CONSTRAINT "fk_consumo_solicitud_item"
GO
ALTER TABLE "transaccional"."consumo"
	DROP CONSTRAINT "fk_consumo_profesional_efector"
GO
ALTER TABLE "transaccional"."consumo"
	DROP CONSTRAINT "FK38B6FC06F61A9D1"
GO
ALTER TABLE "transaccional"."concepto_autorizacion"
	DROP CONSTRAINT "fk_concepto_autorizacion_lateralidad"
GO
ALTER TABLE "transaccional"."concepto_autorizacion"
	DROP CONSTRAINT "fk_concepto_autorizacion_causal_devolucion"
GO
ALTER TABLE "transaccional"."concepto_autorizacion"
	DROP CONSTRAINT "fk_concepto_autorizacion_causal_anulacion"
GO
ALTER TABLE "transaccional"."con_autorizacion_cri_negacion"
	DROP CONSTRAINT "fk_con_autorizacion_cri_negacion_criterio_negacion"
GO
ALTER TABLE "transaccional"."con_autorizacion_cri_negacion"
	DROP CONSTRAINT "fk_con_autorizacion_cri_negacion_concepto_autorizacion"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_servicio"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_sede_ips_efectora"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_rol_destino"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_informacion_tutela"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_informacion_adicional"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_grupo_autorizacion"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_estado_autorizacion"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_especialidad"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_entidad_recobro"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_concepto_regional"
GO
ALTER TABLE "transaccional"."autorizacion"
	DROP CONSTRAINT "fk_autorizacion_concepto_nacional"
GO
ALTER TABLE "maestros"."afiliado_programa"
	DROP CONSTRAINT "fk_afiliado_programa_programa"
GO
ALTER TABLE "maestros"."afiliado_programa"
	DROP CONSTRAINT "fk_afiliado_programa_afiliado"
GO
ALTER TABLE "maestros"."afiliado_cotizante"
	DROP CONSTRAINT "fk_afiliado_cotizante_cotizante"
GO
ALTER TABLE "maestros"."afiliado_cotizante"
	DROP CONSTRAINT "fk_afiliado_cotizante_afiliado"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_tipo_identificacion"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_tipo_afiliado"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_sede_ips_afiliacion"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_ocupacion"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_nivel_sisben"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_nivel_ibc"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_municipio"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_localidad"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_grupo_poblacional"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_genero"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_estado_civil"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_estado_afiliacion"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_eps"
GO
ALTER TABLE "maestros"."afiliado"
	DROP CONSTRAINT "fk_afiliado_departamento_seccional"
GO
ALTER TABLE "security"."user"
	DROP CONSTRAINT "uq_user_username"
GO
ALTER TABLE "ticket"."ticket_cabecera"
	DROP CONSTRAINT "uk_ticket_cabecera_autorizacion_nro_entrega"
GO
ALTER TABLE "maestros"."servicio_especialidad"
	DROP CONSTRAINT "uq_servicio_especialidad__servicio_especialidad_fdelete"
GO
ALTER TABLE "transaccional"."role_estado_visible"
	DROP CONSTRAINT "uq_role_estado_visible__role_estado_visible"
GO
ALTER TABLE "maestros"."profesional_especialidad_x_sede_ips"
	DROP CONSTRAINT "uq_profesional_especialidad_x_sede_ips__profespe_sedeips_fdelete"
GO
ALTER TABLE "maestros"."historial_variacion_ipc"
	DROP CONSTRAINT "uq_historial_variacion_ipc_anio"
GO
ALTER TABLE "maestros"."historial_smldv"
	DROP CONSTRAINT "uq_historial_smldv_anio"
GO
ALTER TABLE "maestros"."especialidad_procedimiento"
	DROP CONSTRAINT "uq_especialidad_procedimiento__especialidad_procedimiento_fdelete"
GO
ALTER TABLE "maestros"."especialidad_medicamento"
	DROP CONSTRAINT "uq_especialidad_medicamento__especialidad_medicamento_fdelete"
GO
ALTER TABLE "maestros"."especialidad_insumo"
	DROP CONSTRAINT "uq_especialidad_insumo__especialidad_insumo_fdelete"
GO
ALTER TABLE "maestros"."diagnostico_procedimiento"
	DROP CONSTRAINT "uq_diagnostico_procedimiento__diagnostico_procedimiento_fdelete"
GO
ALTER TABLE "maestros"."diagnostico_medicamento"
	DROP CONSTRAINT "uq_diagnostico_medicamento__diagnostico_medicamento_fdelete"
GO
ALTER TABLE "maestros"."departamento_regional"
	DROP CONSTRAINT "uq_departamento_regional__departamento_regional_fdelete"
GO
ALTER TABLE "maestros"."afiliado_programa"
	DROP CONSTRAINT "uq_afiliado_programa__afiliado_programa_diagnostico"
GO
DROP INDEX "transaccional"."solicitud"."ix_solicitud_user"
GO
DROP INDEX "transaccional"."solicitud"."ix_solicitud_sede_ips"
GO
DROP INDEX "transaccional"."solicitud"."ix_solicitud_resumen_historia_clinica"
GO
DROP INDEX "transaccional"."solicitud"."ix_solicitud_profesional"
GO
DROP INDEX "transaccional"."solicitud_procedimiento"."ix_solicitud_procedimiento_procedimiento"
GO
DROP INDEX "transaccional"."solicitud_procedimiento"."ix_solicitud_procedimiento_especialidad"
GO
DROP INDEX "transaccional"."solicitud"."ix_solicitud_primera_formulacion_anio"
GO
DROP INDEX "transaccional"."solicitud_parcial"."ix_solicitud_parcial_user"
GO
DROP INDEX "transaccional"."solicitud_parcial"."ix_solicitud_parcial_tipo_identificacion_afiliado"
GO
DROP INDEX "transaccional"."solicitud_parcial"."ix_solicitud_parcial_sede_ips"
GO
DROP INDEX "transaccional"."solicitud_medicamento"."ix_solicitud_medicamento_medicamento"
GO
DROP INDEX "transaccional"."solicitud_item"."ix_solicitud_item_tipo_servicio"
GO
DROP INDEX "transaccional"."solicitud_item"."ix_solicitud_item_tipo_ppm"
GO
DROP INDEX "transaccional"."solicitud_item"."ix_solicitud_item_solicitud"
GO
DROP INDEX "transaccional"."solicitud_item"."ix_solicitud_item_diagnostico"
GO
DROP INDEX "transaccional"."solicitud_insumo"."ix_solicitud_insumo_insumo"
GO
DROP INDEX "transaccional"."solicitud"."ix_solicitud_fecha_creacion"
GO
DROP INDEX "transaccional"."solicitud_diagnostico"."ix_solicitud_diagnostico_solicitud"
GO
DROP INDEX "transaccional"."solicitud_diagnostico"."ix_solicitud_diagnostico_diagnostico"
GO
DROP INDEX "transaccional"."solicitud_item"."ix_solicitud_autorizacion"
GO
DROP INDEX "transaccional"."solicitud"."ix_solicitud_afiliado"
GO
DROP INDEX "transaccional"."role_estado_visible"."ix_role_estado_visible_role"
GO
DROP INDEX "transaccional"."role_estado_visible"."ix_role_estado_visible_estado_visible"
GO
DROP INDEX "transaccional"."role_estado_estados"."ix_role_estado_estados_role_estado"
GO
DROP INDEX "transaccional"."role_estado_estados"."ix_role_estado_estados_estado_autorizacion"
GO
DROP INDEX "transaccional"."resumen_historia_clinica"."ix_resumen_historia_clinica_tipo_catastrofico"
GO
DROP INDEX "transaccional"."resumen_historia_clinica"."ix_resumen_historia_clinica_causa_externa"
GO
DROP INDEX "transaccional"."resumen_diagnostico"."ix_resumen_diagnostico_resumen"
GO
DROP INDEX "transaccional"."resumen_diagnostico"."ix_resumen_diagnostico_diagnostico"
GO
DROP INDEX "transaccional"."procedimiento_pos_previo"."ix_procedimiento_pos_previo_respuesta_clinica_observada"
GO
DROP INDEX "transaccional"."procedimiento_pos_previo"."ix_procedimiento_pos_previo_procedimiento"
GO
DROP INDEX "transaccional"."procedimiento_homologo"."ix_procedimiento_homologo_procedimiento"
GO
DROP INDEX "transaccional"."procedimiento_homologo"."ix_procedimiento_homologo_objetivo_procedimiento"
GO
DROP INDEX "transaccional"."medicamento_pos_previo"."ix_medicamento_pos_previo_respuesta_clinica_observada"
GO
DROP INDEX "transaccional"."medicamento_pos_previo"."ix_medicamento_pos_previo_medicamento"
GO
DROP INDEX "transaccional"."insumo_pos_previo"."ix_insumo_pos_previo_respuesta_clinica_observada"
GO
DROP INDEX "transaccional"."insumo_pos_previo"."ix_insumo_pos_previo_insumo"
GO
DROP INDEX "transaccional"."informacion_adicional"."ix_informacion_adicional_tipo_catastrofico"
GO
DROP INDEX "transaccional"."informacion_adicional"."ix_informacion_adicional_finalidad"
GO
DROP INDEX "transaccional"."informacion_adicional"."ix_informacion_adicional_causa_externa"
GO
DROP INDEX "transaccional"."formulario_ctc_procedimiento"."ix_formulario_ctc_procedimiento_tipo_catastrofico"
GO
DROP INDEX "transaccional"."formulario_ctc_procedimiento"."ix_formulario_ctc_procedimiento_finalidad"
GO
DROP INDEX "transaccional"."formulario_ctc_procedimiento"."ix_formulario_ctc_procedimiento_causa_externa"
GO
DROP INDEX "transaccional"."formulario_ctc_medicamento"."ix_formulario_ctc_medicamento_tipo_catastrofico"
GO
DROP INDEX "transaccional"."formulario_ctc_medicamento"."ix_formulario_ctc_medicamento_medicamento_homologo"
GO
DROP INDEX "transaccional"."formulario_ctc_medicamento"."ix_formulario_ctc_medicamento_finalidad"
GO
DROP INDEX "transaccional"."formulario_ctc_medicamento"."ix_formulario_ctc_medicamento_causa_externa"
GO
DROP INDEX "transaccional"."formulario_ctc_insumo"."ix_formulario_ctc_insumo_tipo_catastrofico"
GO
DROP INDEX "transaccional"."formulario_ctc_insumo"."ix_formulario_ctc_insumo_insumo_homologo"
GO
DROP INDEX "transaccional"."formulario_ctc_insumo"."ix_formulario_ctc_insumo_finalidad"
GO
DROP INDEX "transaccional"."formulario_ctc_insumo"."ix_formulario_ctc_insumo_causa_externa"
GO
DROP INDEX "transaccional"."formula_item_procedimiento"."ix_formula_item_procedimiento_tipo_prestacion"
GO
DROP INDEX "transaccional"."formula_item_procedimiento"."ix_formula_item_procedimiento_origen_repeticion"
GO
DROP INDEX "transaccional"."formula_item_procedimiento"."ix_formula_item_procedimiento_objetivo_procedimiento"
GO
DROP INDEX "transaccional"."formula_item_procedimiento"."ix_formula_item_procedimiento_lateralidad"
GO
DROP INDEX "transaccional"."formula_item_medicamento"."ix_formula_item_medicamento_via_administracion"
GO
DROP INDEX "transaccional"."entrega"."ix_entrega_solicitud_medicamento"
GO
DROP INDEX "transaccional"."documento_soporte"."ix_documento_soporte_tipo_doc_soporte"
GO
DROP INDEX "transaccional"."documento_soporte"."ix_documento_soporte_solicitud"
GO
DROP INDEX "transaccional"."consumo"."ix_consumo_solicitud_item"
GO
DROP INDEX "transaccional"."consumo"."ix_consumo_profesional_efector"
GO
DROP INDEX "transaccional"."concepto_autorizacion"."ix_concepto_autorizacion_lateralidad"
GO
DROP INDEX "transaccional"."concepto_autorizacion"."ix_concepto_autorizacion_causal_devolucion"
GO
DROP INDEX "transaccional"."concepto_autorizacion"."ix_concepto_autorizacion_causal_anulacion"
GO
DROP INDEX "transaccional"."con_autorizacion_cri_negacion"."ix_con_autorizacion_cri_negacion_criterio_negacion"
GO
DROP INDEX "transaccional"."con_autorizacion_cri_negacion"."ix_con_autorizacion_cri_negacion_concepto_autorizacion"
GO
DROP INDEX "transaccional"."autorizacion"."ix_autorizacion_sede_ips_efectora"
GO
DROP INDEX "transaccional"."autorizacion"."ix_autorizacion_rol_destino"
GO
DROP INDEX "transaccional"."autorizacion"."ix_autorizacion_informacion_adicional"
GO
DROP INDEX "transaccional"."autorizacion"."ix_autorizacion_estado_autorizacion"
GO
DROP INDEX "transaccional"."autorizacion"."ix_autorizacion_entidad_recobro"
GO
DROP INDEX "transaccional"."autorizacion"."ix_autorizacion_concepto_regional"
GO
DROP INDEX "transaccional"."autorizacion"."ix_autorizacion_concepto_nacional"
GO
DROP INDEX "security"."usuario_entidad"."ix_usuario_entidad_user"
GO
DROP INDEX "security"."usuario_entidad"."ix_usuario_entidad_sede_ips"
GO
DROP INDEX "security"."usuario_entidad"."ix_usuario_entidad_profesional_especialidad"
GO
DROP INDEX "security"."usuario_entidad"."ix_usuario_entidad_linea_de_frente"
GO
DROP INDEX "security"."user_role"."ix_user_role_user"
GO
DROP INDEX "security"."user_role"."ix_user_role_role"
GO
DROP INDEX "security"."role_authority"."ix_role_authority_role"
GO
DROP INDEX "security"."role_authority"."ix_role_authority_authority"
GO
DROP INDEX "security"."recovery_token"."ix_recovery_token_user"
GO
DROP INDEX "maestros"."tipo_minuta"."ix_tipo_minuta_codigo"
GO
DROP INDEX "maestros"."tipo_identificacion"."ix_tipo_identificacion_descripcion"
GO
DROP INDEX "maestros"."tipo_identificacion"."ix_tipo_identificacion_codigo"
GO
DROP INDEX "maestros"."tarifario_excepcion"."ix_tarifario_excepcion_tarifario"
GO
DROP INDEX "maestros"."servicio"."ix_servicio_unidad_funcional"
GO
DROP INDEX "maestros"."servicio_especialidad"."ix_servicio_especialidad_servicio"
GO
DROP INDEX "maestros"."servicio_especialidad"."ix_servicio_especialidad_especialidad"
GO
DROP INDEX "maestros"."servicio_contratado"."ix_servicio_contratado_unidad_tiempo"
GO
DROP INDEX "maestros"."servicio_contratado"."ix_servicio_contratado_tipo_minuta"
GO
DROP INDEX "maestros"."servicio_contratado"."ix_servicio_contratado_tarifario_excepcion"
GO
DROP INDEX "maestros"."servicio_contratado"."ix_servicio_contratado_servicio"
GO
DROP INDEX "maestros"."servicio_contratado"."ix_servicio_contratado_estado_item_contratado"
GO
DROP INDEX "maestros"."servicio_contratado"."ix_servicio_contratado_contrato"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_ubicacion"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_tipo_servicio"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_tipo_ips"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_regional"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_regimen_tributario"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_regimen_juridico"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_municipio"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_ips"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_estado_ips"
GO
DROP INDEX "maestros"."sede_ips"."ix_sede_ips_eps"
GO
DROP INDEX "maestros"."sede_ips_division_seccional"."ix_sede_ips_division_seccional_sede_ips"
GO
DROP INDEX "maestros"."sede_ips_division_seccional"."ix_sede_ips_division_seccional_division_seccional"
GO
DROP INDEX "maestros"."regional"."ix_regional_division_seccional"
GO
DROP INDEX "maestros"."regional"."ix_regional_descripcion"
GO
DROP INDEX "maestros"."regional"."ix_regional_codigo"
GO
DROP INDEX "maestros"."profesional"."ix_profesional_tipo_profesional"
GO
DROP INDEX "maestros"."profesional"."ix_profesional_municipio"
GO
DROP INDEX "maestros"."profesional"."ix_profesional_identificacion_profesional"
GO
DROP INDEX "maestros"."profesional_especialidad_x_sede_ips"."ix_profesional_especialidad_x_sede_ips_sede_ips"
GO
DROP INDEX "maestros"."profesional_especialidad_x_sede_ips"."ix_profesional_especialidad_x_sede_ips_profesional_especialidad"
GO
DROP INDEX "maestros"."profesional_especialidad"."ix_profesional_especialidad_profesional"
GO
DROP INDEX "maestros"."profesional_especialidad"."ix_profesional_especialidad_nivel_autorizacion"
GO
DROP INDEX "maestros"."profesional_especialidad"."ix_profesional_especialidad_estado_profesional"
GO
DROP INDEX "maestros"."profesional_especialidad"."ix_profesional_especialidad_especialidad"
GO
DROP INDEX "maestros"."profesional"."ix_profesional_division_seccional"
GO
DROP INDEX "maestros"."profesional"."ix_profecional_numero_identificacion"
GO
DROP INDEX "maestros"."procedimiento"."ix_procedimiento_tipo_ppm"
GO
DROP INDEX "maestros"."procedimiento_tarifa"."ix_procedimiento_tarifa_tarifario"
GO
DROP INDEX "maestros"."procedimiento_tarifa"."ix_procedimiento_tarifa_procedimiento"
GO
DROP INDEX "maestros"."procedimiento"."ix_procedimiento_genero"
GO
DROP INDEX "maestros"."procedimiento"."ix_procedimiento_descripcion"
GO
DROP INDEX "maestros"."procedimiento_contratado"."ix_procedimiento_contratado_unidad_tiempo"
GO
DROP INDEX "maestros"."procedimiento_contratado"."ix_procedimiento_contratado_tipo_minuta"
GO
DROP INDEX "maestros"."procedimiento_contratado"."ix_procedimiento_contratado_tarifario"
GO
DROP INDEX "maestros"."procedimiento_contratado"."ix_procedimiento_contratado_procedimiento"
GO
DROP INDEX "maestros"."procedimiento_contratado"."ix_procedimiento_contratado_fecha_vencimiento"
GO
DROP INDEX "maestros"."procedimiento_contratado"."ix_procedimiento_contratado_estado_procedimiento_contratado"
GO
DROP INDEX "maestros"."procedimiento_contratado"."ix_procedimiento_contratado_especialidad_contratada"
GO
DROP INDEX "maestros"."procedimiento"."ix_procedimiento_codigo"
GO
DROP INDEX "maestros"."contrato"."ix_nombre_contrato"
GO
DROP INDEX "maestros"."municipio"."ix_municipio_regional"
GO
DROP INDEX "maestros"."municipio_division_seccional"."ix_municipio_division_seccional_municipio"
GO
DROP INDEX "maestros"."municipio_division_seccional"."ix_municipio_division_seccional_division_seccional"
GO
DROP INDEX "maestros"."municipio"."ix_municipio_departamento"
GO
DROP INDEX "maestros"."montos_copago_subsidiado"."ix_montos_copago_subsidiado_nivel_sisben"
GO
DROP INDEX "maestros"."montos_copago_contributivo"."ix_montos_copago_contributivo_nivel_ibc"
GO
DROP INDEX "maestros"."medicamento"."ix_medicamento_tipo_ppm"
GO
DROP INDEX "maestros"."medicamento_tarifa"."ix_medicamento_tarifa_tarifario"
GO
DROP INDEX "maestros"."medicamento_tarifa"."ix_medicamento_tarifa_medicamento"
GO
DROP INDEX "maestros"."medicamento"."ix_medicamento_homologo"
GO
DROP INDEX "maestros"."medicamento"."ix_medicamento_genero"
GO
DROP INDEX "maestros"."medicamento"."ix_medicamento_estado_medicamento"
GO
DROP INDEX "maestros"."medicamento"."ix_medicamento_descripcion"
GO
DROP INDEX "maestros"."medicamento_contratado"."ix_medicamento_contratado_unidad_tiempo"
GO
DROP INDEX "maestros"."medicamento_contratado"."ix_medicamento_contratado_tipo_minuta"
GO
DROP INDEX "maestros"."medicamento_contratado"."ix_medicamento_contratado_tarifario"
GO
DROP INDEX "maestros"."medicamento_contratado"."ix_medicamento_contratado_medicamento"
GO
DROP INDEX "maestros"."medicamento_contratado"."ix_medicamento_contratado_estado_medicamento_contratado"
GO
DROP INDEX "maestros"."medicamento_contratado"."ix_medicamento_contratado_especialidad_contratada"
GO
DROP INDEX "maestros"."medicamento"."ix_medicamento_codigo"
GO
DROP INDEX "maestros"."localidad"."ix_localidad_regional"
GO
DROP INDEX "maestros"."localidad"."ix_localidad_municipio"
GO
DROP INDEX "maestros"."ips"."ix_ips_tipo_ips"
GO
DROP INDEX "maestros"."ips"."ix_ips_tipo_identificacion"
GO
DROP INDEX "maestros"."ips"."ix_ips_numero_identificacion"
GO
DROP INDEX "maestros"."ips"."ix_ips_estado_ips"
GO
DROP INDEX "maestros"."insumo"."ix_insumo_tipo_ppm"
GO
DROP INDEX "maestros"."insumo_tarifa"."ix_insumo_tarifa_tarifario"
GO
DROP INDEX "maestros"."insumo_tarifa"."ix_insumo_tarifa_insumo"
GO
DROP INDEX "maestros"."insumo"."ix_insumo_homologo"
GO
DROP INDEX "maestros"."insumo"."ix_insumo_genero"
GO
DROP INDEX "maestros"."insumo"."ix_insumo_estado_insumo"
GO
DROP INDEX "maestros"."insumo"."ix_insumo_descripcion"
GO
DROP INDEX "maestros"."insumo"."ix_insumo_codigo"
GO
DROP INDEX "maestros"."especialidad_procedimiento"."ix_especialidad_procedimiento_procedimiento"
GO
DROP INDEX "maestros"."especialidad_procedimiento"."ix_especialidad_procedimiento_especialidad"
GO
DROP INDEX "maestros"."especialidad_medicamento"."ix_especialidad_medicamento_medicamento"
GO
DROP INDEX "maestros"."especialidad_medicamento"."ix_especialidad_medicamento_especialidad"
GO
DROP INDEX "maestros"."especialidad_insumo"."ix_especialidad_insumo_insumo"
GO
DROP INDEX "maestros"."especialidad_insumo"."ix_especialidad_insumo_especialidad"
GO
DROP INDEX "maestros"."especialidad_contratada"."ix_especialidad_contratada_unidad_tiempo"
GO
DROP INDEX "maestros"."especialidad_contratada"."ix_especialidad_contratada_tipo_minuta"
GO
DROP INDEX "maestros"."especialidad_contratada"."ix_especialidad_contratada_tarifario_excepcion"
GO
DROP INDEX "maestros"."especialidad_contratada"."ix_especialidad_contratada_servicio_contratado"
GO
DROP INDEX "maestros"."especialidad_contratada"."ix_especialidad_contratada_estado_especialidad_contratada"
GO
DROP INDEX "maestros"."especialidad_contratada"."ix_especialidad_contratada_especialidad"
GO
DROP INDEX "maestros"."eps"."ix_eps_tipo_identificacion"
GO
DROP INDEX "maestros"."eps"."ix_eps_razon_social"
GO
DROP INDEX "maestros"."division_seccional"."ix_division_seccional_descripcion"
GO
DROP INDEX "maestros"."division_seccional"."ix_division_seccional_codigo"
GO
DROP INDEX "maestros"."director_medico_regional"."ix_director_medico_regional_regional"
GO
DROP INDEX "maestros"."diagnostico"."ix_diagnostico_tipo_diagnostico"
GO
DROP INDEX "maestros"."diagnostico_procedimiento"."ix_diagnostico_procedimiento_procedimiento"
GO
DROP INDEX "maestros"."diagnostico_procedimiento"."ix_diagnostico_procedimiento_diagnostico"
GO
DROP INDEX "maestros"."diagnostico_medicamento"."ix_diagnostico_medicamento_medicamento"
GO
DROP INDEX "maestros"."diagnostico_medicamento"."ix_diagnostico_medicamento_diagnostico"
GO
DROP INDEX "maestros"."diagnostico"."ix_diagnostico_descripcion"
GO
DROP INDEX "maestros"."diagnostico"."ix_diagnostico_codigo"
GO
DROP INDEX "maestros"."departamento_regional"."ix_departamento_regional_regional"
GO
DROP INDEX "maestros"."departamento_regional"."ix_departamento_regional_departamento"
GO
DROP INDEX "maestros"."departamento"."ix_departamento_descripcion"
GO
DROP INDEX "maestros"."departamento"."ix_departamento_codigo"
GO
DROP INDEX "maestros"."contrato"."ix_contrato_tipo_plan_contrato"
GO
DROP INDEX "maestros"."contrato_tarifario"."ix_contrato_tarifario_tipo_minuta"
GO
DROP INDEX "maestros"."contrato_tarifario"."ix_contrato_tarifario_tarifario"
GO
DROP INDEX "maestros"."contrato_tarifario"."ix_contrato_tarifario_estado_contrato_tarifario"
GO
DROP INDEX "maestros"."contrato_tarifario"."ix_contrato_tarifario_contrato"
GO
DROP INDEX "maestros"."contrato"."ix_contrato_sede_ips"
GO
DROP INDEX "maestros"."contrato"."ix_contrato_fecha_inicio_contrato"
GO
DROP INDEX "maestros"."contrato"."ix_contrato_fecha_fin_contrato"
GO
DROP INDEX "maestros"."contrato"."ix_contrato_fecha_aprobado"
GO
DROP INDEX "maestros"."contrato"."ix_contrato_estado_contrato"
GO
DROP INDEX "maestros"."contrato"."ix_contrato_eps"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_tipo_identificacion"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_tipo_afiliado"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_segundo_nombre"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_segundo_apellido"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_sede_ips_afiliacion"
GO
DROP INDEX "maestros"."afiliado_programa"."ix_afiliado_programa_programa"
GO
DROP INDEX "maestros"."afiliado_programa"."ix_afiliado_programa_afiliado"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_primer_nombre"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_primer_apellido"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_numero_identificacion"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_nivel_sisben"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_nivel_ibc"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_municipio"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_localidad"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_grupo_poblacional"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_genero"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_estado_civil"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_estado_afiliacion"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_eps"
GO
DROP INDEX "maestros"."afiliado"."ix_afiliado_departamento_seccional"
GO
DROP INDEX "auxiliar"."totalizacion_copago_afiliado_diagnostico"."uq_totalizacion_copago_afiliado_anio_afiliado_id_diagnostico_id"
GO
DROP INDEX "auxiliar"."totalizacion_copago_afiliado"."uq_totalizacion_copago_afiliado_anio_afiliado_id"
GO
DROP FUNCTION "transaccional"."fn_get_pba"
GO
DROP PROCEDURE "transaccional"."get_pba"
GO
DROP PROCEDURE "transaccional"."get_numero_solicitud"
GO
DROP VIEW "vista"."ubicacion_sede_ips_proveedor_medicamento"
GO
DROP VIEW "vista"."ubicacion_sede_ips_proveedor_insumo"
GO
DROP VIEW "vista"."ubicacion_sede_ips_efector_procedimiento"
GO
DROP VIEW "vista"."tarifa_procedimiento_view"
GO
DROP VIEW "vista"."tarifa_medicamento_view"
GO
DROP VIEW "vista"."afiliado_programa_item_eximido_view"
GO
DROP TABLE "maestros"."zona"
GO
DROP TABLE "transaccional"."xxx2"
GO
DROP TABLE "transaccional"."xxx1"
GO
DROP TABLE "maestros"."vigencia"
GO
DROP TABLE "maestros"."via_administracion"
GO
DROP TABLE "security"."usuario_entidad"
GO
DROP TABLE "security"."user_role"
GO
DROP TABLE "security"."user"
GO
DROP TABLE "maestros"."unidad_tiempo"
GO
DROP TABLE "maestros"."unidad_funcional"
GO
DROP TABLE "maestros"."ubicacion"
GO
DROP TABLE "auxiliar"."totalizacion_copago_afiliado_diagnostico"
GO
DROP TABLE "auxiliar"."totalizacion_copago_afiliado"
GO
DROP TABLE "maestros"."tipo_tecnologia"
GO
DROP TABLE "maestros"."tipo_tarifario"
GO
DROP TABLE "maestros"."tipo_servicio"
GO
DROP TABLE "maestros"."tipo_profesional"
GO
DROP TABLE "maestros"."tipo_producto"
GO
DROP TABLE "maestros"."tipo_prestacion"
GO
DROP TABLE "maestros"."tipo_ppm"
GO
DROP TABLE "maestros"."tipo_plan_contrato"
GO
DROP TABLE "maestros"."tipo_plan_afiliado"
GO
DROP TABLE "maestros"."tipo_perioricidad"
GO
DROP TABLE "maestros"."tipo_origen_procedimiento"
GO
DROP TABLE "maestros"."tipo_operacion_excepcion"
GO
DROP TABLE "maestros"."tipo_minuta"
GO
DROP TABLE "maestros"."tipo_ips"
GO
DROP TABLE "maestros"."tipo_identificacion"
GO
DROP TABLE "maestros"."tipo_funcionalidad"
GO
DROP TABLE "maestros"."tipo_documento_soporte"
GO
DROP TABLE "maestros"."tipo_diagnostico"
GO
DROP TABLE "maestros"."tipo_catastrofico"
GO
DROP TABLE "maestros"."tipo_afiliado"
GO
DROP TABLE "ticket"."ticket_item"
GO
DROP TABLE "ticket"."ticket_cabecera"
GO
DROP TABLE "auxiliar"."tarifas_contratos_saludcoop"
GO
DROP TABLE "auxiliar"."tarifas_contratos_cafesaludsub"
GO
DROP TABLE "auxiliar"."tarifas_contratos_cafesalud"
GO
DROP TABLE "maestros"."tarifario_excepcion"
GO
DROP TABLE "maestros"."tarifario"
GO
DROP TABLE "auxiliar"."tarifa_procedimiento"
GO
DROP TABLE "transaccional"."solicitud_procedimiento"
GO
DROP TABLE "transaccional"."solicitud_parcial"
GO
DROP TABLE "transaccional"."solicitud_medicamento"
GO
DROP TABLE "transaccional"."solicitud_item"
GO
DROP TABLE "transaccional"."solicitud_insumo"
GO
DROP TABLE "transaccional"."solicitud_diagnostico"
GO
DROP TABLE "transaccional"."solicitud"
GO
DROP TABLE "maestros"."servicio_especialidad"
GO
DROP TABLE "maestros"."servicio_contratado"
GO
DROP TABLE "maestros"."servicio"
GO
DROP TABLE "maestros"."sede_ips_division_seccional"
GO
DROP TABLE "maestros"."sede_ips"
GO
DROP TABLE "transaccional"."role_estado_visible"
GO
DROP TABLE "transaccional"."role_estado_estados"
GO
DROP TABLE "security"."role_authority"
GO
DROP TABLE "security"."role"
GO
DROP TABLE "transaccional"."resumen_historia_clinica"
GO
DROP TABLE "transaccional"."resumen_diagnostico"
GO
DROP TABLE "maestros"."respuesta_clinica_observada"
GO
DROP TABLE "auxiliar"."regional_departemento"
GO
DROP TABLE "maestros"."regional"
GO
DROP TABLE "maestros"."regimen_tributario"
GO
DROP TABLE "maestros"."regimen_juridico"
GO
DROP TABLE "security"."recovery_token"
GO
DROP TABLE "maestros"."razon_negacion"
GO
DROP TABLE "maestros"."razon_estado_afiliacion"
GO
DROP TABLE "configuracion"."properties"
GO
DROP TABLE "maestros"."programa_procedimiento"
GO
DROP TABLE "auxiliar"."programa_procedimiento"
GO
DROP TABLE "maestros"."programa_medicamento_alto_costo"
GO
DROP TABLE "maestros"."programa_medicamento"
GO
DROP TABLE "auxiliar"."programa_medicamento"
GO
DROP TABLE "maestros"."programa_diagnostico"
GO
DROP TABLE "auxiliar"."programa_diagnostico"
GO
DROP TABLE "maestros"."programa"
GO
DROP TABLE "maestros"."profesional_especialidad_x_sede_ips"
GO
DROP TABLE "maestros"."profesional_especialidad"
GO
DROP TABLE "homologacion"."profesional_especialidad"
GO
DROP TABLE "auxiliar"."profesional_especialidad"
GO
DROP TABLE "maestros"."profesional"
GO
DROP TABLE "homologacion"."profesional"
GO
DROP TABLE "maestros"."procedimiento_tarifa"
GO
DROP TABLE "transaccional"."procedimiento_pos_previo"
GO
DROP TABLE "transaccional"."procedimiento_homologo"
GO
DROP TABLE "maestros"."procedimiento_contratado"
GO
DROP TABLE "maestros"."procedimiento"
GO
DROP TABLE "maestros"."prioridad"
GO
DROP TABLE "maestros"."parentesco"
GO
DROP TABLE "maestros"."origen_solicitud"
GO
DROP TABLE "maestros"."origen_repeticion"
GO
DROP TABLE "maestros"."origen_autorizacion"
GO
DROP TABLE "maestros"."ocupacion"
GO
DROP TABLE "maestros"."objetivo_procedimiento"
GO
DROP TABLE "maestros"."nivel_sisben"
GO
DROP TABLE "maestros"."nivel_ibc"
GO
DROP TABLE "maestros"."nivel_autorizacion"
GO
DROP TABLE "maestros"."municipio_division_seccional"
GO
DROP TABLE "maestros"."municipio"
GO
DROP TABLE "maestros"."montos_copago_subsidiado"
GO
DROP TABLE "maestros"."montos_copago_contributivo"
GO
DROP TABLE "menu"."menu"
GO
DROP TABLE "maestros"."medicamento_tarifa"
GO
DROP TABLE "transaccional"."medicamento_pos_previo"
GO
DROP TABLE "maestros"."medicamento_contratado"
GO
DROP TABLE "maestros"."medicamento_condicionado"
GO
DROP TABLE "maestros"."medicamento"
GO
DROP TABLE "configuracion"."mail_content"
GO
DROP TABLE "maestros"."localidad"
GO
DROP TABLE "maestros"."linea_de_frente"
GO
DROP TABLE "maestros"."lateralidad"
GO
DROP TABLE "maestros"."ips"
GO
DROP TABLE "maestros"."insumo_tope"
GO
DROP TABLE "maestros"."insumo_tarifa"
GO
DROP TABLE "transaccional"."insumo_pos_previo"
GO
DROP TABLE "maestros"."insumo_contratado"
GO
DROP TABLE "maestros"."insumo"
GO
DROP TABLE "transaccional"."informacion_tutela"
GO
DROP TABLE "transaccional"."informacion_adicional"
GO
DROP TABLE "auxiliar"."homologos"
GO
DROP TABLE "maestros"."historial_variacion_ipc"
GO
DROP TABLE "historial"."historial_solicitud"
GO
DROP TABLE "maestros"."historial_smldv"
GO
DROP TABLE "maestros"."grupo_poblacional"
GO
DROP TABLE "transaccional"."grupo_autorizacion"
GO
DROP TABLE "maestros"."genero"
GO
DROP TABLE "transaccional"."formulario_ctc_procedimiento"
GO
DROP TABLE "transaccional"."formulario_ctc_medicamento"
GO
DROP TABLE "transaccional"."formulario_ctc_insumo"
GO
DROP TABLE "transaccional"."formula_item_procedimiento"
GO
DROP TABLE "transaccional"."formula_item_medicamento"
GO
DROP TABLE "transaccional"."formula_item_insumo"
GO
DROP TABLE "maestros"."finalidad"
GO
DROP TABLE "maestros"."estado_visible"
GO
DROP TABLE "maestros"."estado_profesional"
GO
DROP TABLE "maestros"."estado_procedimiento"
GO
DROP TABLE "maestros"."estado_item_contratado"
GO
DROP TABLE "maestros"."estado_ips"
GO
DROP TABLE "maestros"."estado_contrato"
GO
DROP TABLE "maestros"."estado_civil"
GO
DROP TABLE "maestros"."estado_autorizacion"
GO
DROP TABLE "homologacion"."estado_autorizacion"
GO
DROP TABLE "maestros"."estado_afiliacion"
GO
DROP TABLE "maestros"."especialidad_procedimiento"
GO
DROP TABLE "maestros"."especialidad_medicamento"
GO
DROP TABLE "maestros"."especialidad_insumo"
GO
DROP TABLE "maestros"."especialidad_contratada"
GO
DROP TABLE "maestros"."especialidad"
GO
DROP TABLE "maestros"."eps"
GO
DROP TABLE "transaccional"."entrega"
GO
DROP TABLE "maestros"."entidad_recobro"
GO
DROP TABLE "transaccional"."documento_soporte"
GO
DROP TABLE "maestros"."division_seccional"
GO
DROP TABLE "maestros"."director_medico_regional"
GO
DROP TABLE "auxiliar"."diagnosticos"
GO
DROP TABLE "maestros"."diagnostico_procedimiento"
GO
DROP TABLE "maestros"."diagnostico_medicamento"
GO
DROP TABLE "maestros"."diagnostico_insumo"
GO
DROP TABLE "maestros"."diagnostico"
GO
DROP TABLE "maestros"."departamento_regional"
GO
DROP TABLE "maestros"."departamento"
GO
DROP TABLE "maestros"."criterio_negacion"
GO
DROP TABLE "maestros"."contrato_tarifario"
GO
DROP TABLE "maestros"."contrato"
GO
DROP TABLE "transaccional"."consumo"
GO
DROP TABLE "transaccional"."concepto_autorizacion"
GO
DROP TABLE "transaccional"."con_autorizacion_cri_negacion"
GO
DROP TABLE "auxiliar"."comprobar_municipio_departamento_regional"
GO
DROP TABLE "auxiliar"."commproducto_2"
GO
DROP TABLE "auxiliar"."codigo_seven_cie10"
GO
DROP TABLE "maestros"."causal_devolucion"
GO
DROP TABLE "maestros"."causal_anulacion"
GO
DROP TABLE "maestros"."causa_externa"
GO
DROP TABLE "transaccional"."autorizacion"
GO
DROP TABLE "security"."authority"
GO
DROP TABLE "maestros"."afiliado_programa"
GO
DROP TABLE "auxiliar"."afiliado_datos_prestar"
GO
DROP TABLE "auxiliar"."afiliado_datos_habilitar"
GO
DROP TABLE "maestros"."afiliado_cotizante"
GO
DROP TABLE "maestros"."afiliado"
GO
DROP TABLE "dbo"."C3P0_TEST_TABLE"
GO

CREATE TABLE "dbo"."C3P0_TEST_TABLE"  ( 
	"a"	char(1) NULL 
	)
GO
CREATE TABLE "maestros"."afiliado"  ( 
	"id"                       	int IDENTITY NOT NULL,
	"primer_nombre"            	varchar(30) NOT NULL,
	"segundo_nombre"           	varchar(30) NOT NULL,
	"primer_apellido"          	varchar(30) NOT NULL,
	"segundo_apellido"         	varchar(30) NOT NULL,
	"tipo_identificacion_id"   	int NOT NULL,
	"numero_identificacion"    	varchar(20) NOT NULL,
	"telefono_celular"         	varchar(20) NULL,
	"telefono_residencial"     	varchar(20) NULL,
	"genero_id"                	int NOT NULL,
	"estado_civil_id"          	int NOT NULL,
	"email_personal"           	varchar(50) NOT NULL,
	"direccion_de_residencia"  	varchar(100) NULL,
	"localidad_id"             	int NULL,
	"municipio_residencia_id"  	int NOT NULL,
	"departamento_seccional_id"	int NOT NULL,
	"eps_id"                   	int NOT NULL,
	"tipo_afiliado_id"         	int NOT NULL,
	"regimen_afiliacion_enum"  	int NULL,
	"estado_afiliacion_id"     	int NOT NULL,
	"razon_estado_afiliacion"  	varchar(100) NULL,
	"fecha_afiliacion_ips"     	datetime NOT NULL,
	"fecha_afiliacion_sgsss"   	datetime NOT NULL,
	"fecha_expedicion"         	datetime NULL,
	"fecha_fin_urgencias"      	datetime NOT NULL,
	"nivel_sisben_id"          	int NOT NULL,
	"nivel_ibc_id"             	int NOT NULL,
	"dias_continuos"           	int NOT NULL,
	"sede_ips_afiliacion_id"   	int NOT NULL,
	"sem_cotizadas"            	int NULL,
	"sem_eps"                  	int NULL,
	"sem_sgsss"                	int NULL,
	"semanas_cotizadas"        	int NOT NULL,
	"tutela"                   	smallint NULL,
	"cliente_pk"               	int NULL,
	"fecha_insert"             	datetime NOT NULL CONSTRAINT "DF__afiliado__fecha___17C286CF"  DEFAULT (getdate()),
	"fecha_update"             	datetime NOT NULL CONSTRAINT "DF__afiliado__fecha___18B6AB08"  DEFAULT (getdate()),
	"fecha_delete"             	datetime NULL,
	"version"                  	int NOT NULL CONSTRAINT "DF__afiliado__versio__19AACF41"  DEFAULT ((1)),
	"grupo_poblacional_id"     	int NULL,
	"fecha_nacimiento"         	datetime NULL,
	"ocupacion_id"             	int NULL,
	CONSTRAINT "pk_afiliado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."afiliado_cotizante"  ( 
	"afiliado_id" 	int NOT NULL,
	"cotizante_id"	int NOT NULL,
	"id"          	int IDENTITY NOT NULL,
	CONSTRAINT "pk_afiliado_cotizante" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."afiliado_datos_habilitar"  ( 
	"id"                       	int IDENTITY NOT NULL,
	"primer_nombre"            	varchar(30) NOT NULL,
	"segundo_nombre"           	varchar(30) NOT NULL,
	"primer_apellido"          	varchar(30) NOT NULL,
	"segundo_apellido"         	varchar(30) NOT NULL,
	"tipo_identificacion_id"   	int NOT NULL,
	"numero_identificacion"    	varchar(20) NOT NULL,
	"telefono_celular"         	varchar(20) NULL,
	"telefono_residencial"     	varchar(20) NULL,
	"genero_id"                	int NOT NULL,
	"estado_civil_id"          	int NOT NULL,
	"email_personal"           	varchar(50) NOT NULL,
	"direccion_de_residencia"  	varchar(100) NULL,
	"localidad_id"             	int NULL,
	"municipio_residencia_id"  	int NOT NULL,
	"departamento_seccional_id"	int NOT NULL,
	"eps_id"                   	int NOT NULL,
	"tipo_afiliado_id"         	int NOT NULL,
	"regimen_afiliacion_enum"  	int NULL,
	"estado_afiliacion_id"     	int NOT NULL,
	"razon_estado_afiliacion"  	varchar(100) NULL,
	"fecha_afiliacion_ips"     	datetime NOT NULL,
	"fecha_afiliacion_sgsss"   	datetime NOT NULL,
	"fecha_expedicion"         	datetime NULL,
	"fecha_fin_urgencias"      	datetime NOT NULL,
	"nivel_sisben_id"          	int NOT NULL,
	"nivel_ibc_id"             	int NOT NULL,
	"dias_continuos"           	int NOT NULL,
	"sede_ips_afiliacion_id"   	int NOT NULL,
	"sem_cotizadas"            	int NULL,
	"sem_eps"                  	int NULL,
	"sem_sgsss"                	int NULL,
	"semanas_cotizadas"        	int NOT NULL,
	"tutela"                   	smallint NULL,
	"cliente_pk"               	int NULL,
	"fecha_insert"             	datetime NOT NULL CONSTRAINT "DF__afiliado__datos_habilitar_fecha___17C286CF"  DEFAULT (getdate()),
	"fecha_update"             	datetime NOT NULL CONSTRAINT "DF__afiliado__datos_habilitar_fecha___18B6AB08"  DEFAULT (getdate()),
	"fecha_delete"             	datetime NULL,
	"version"                  	int NOT NULL CONSTRAINT "DF__afiliado__datos_habilitar_versio__19AACF41"  DEFAULT ((1)),
	"grupo_poblacional_id"     	int NULL,
	"fecha_nacimiento"         	datetime NULL,
	CONSTRAINT "pk_afiliado_datos_habilitar" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."afiliado_datos_prestar"  ( 
	"id"                       	int IDENTITY NOT NULL,
	"primer_nombre"            	varchar(30) NOT NULL,
	"segundo_nombre"           	varchar(30) NOT NULL,
	"primer_apellido"          	varchar(30) NOT NULL,
	"segundo_apellido"         	varchar(30) NOT NULL,
	"tipo_identificacion_id"   	int NOT NULL,
	"numero_identificacion"    	varchar(20) NOT NULL,
	"telefono_celular"         	varchar(20) NULL,
	"telefono_residencial"     	varchar(20) NULL,
	"genero_id"                	int NOT NULL,
	"estado_civil_id"          	int NOT NULL,
	"email_personal"           	varchar(50) NOT NULL,
	"direccion_de_residencia"  	varchar(100) NULL,
	"localidad_id"             	int NULL,
	"municipio_residencia_id"  	int NOT NULL,
	"departamento_seccional_id"	int NOT NULL,
	"eps_id"                   	int NOT NULL,
	"tipo_afiliado_id"         	int NOT NULL,
	"regimen_afiliacion_enum"  	int NULL,
	"estado_afiliacion_id"     	int NOT NULL,
	"razon_estado_afiliacion"  	varchar(100) NULL,
	"fecha_afiliacion_ips"     	datetime NOT NULL,
	"fecha_afiliacion_sgsss"   	datetime NOT NULL,
	"fecha_expedicion"         	datetime NULL,
	"fecha_fin_urgencias"      	datetime NOT NULL,
	"nivel_sisben_id"          	int NOT NULL,
	"nivel_ibc_id"             	int NOT NULL,
	"dias_continuos"           	int NOT NULL,
	"sede_ips_afiliacion_id"   	int NOT NULL,
	"sem_cotizadas"            	int NULL,
	"sem_eps"                  	int NULL,
	"sem_sgsss"                	int NULL,
	"semanas_cotizadas"        	int NOT NULL,
	"tutela"                   	smallint NULL,
	"cliente_pk"               	int NULL,
	"fecha_insert"             	datetime NOT NULL CONSTRAINT "DF__afiliado__datos_prestar_fecha___17C286CF"  DEFAULT (getdate()),
	"fecha_update"             	datetime NOT NULL CONSTRAINT "DF__afiliado__datos_prestar_fecha___18B6AB08"  DEFAULT (getdate()),
	"fecha_delete"             	datetime NULL,
	"version"                  	int NOT NULL CONSTRAINT "DF__afiliado__datos_prestar_versio__19AACF41"  DEFAULT ((1)),
	"grupo_poblacional_id"     	int NULL,
	"fecha_nacimiento"         	datetime NULL,
	CONSTRAINT "pk_afiliado_datos_prestar" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."afiliado_programa"  ( 
	"id"            	int IDENTITY NOT NULL,
	"afiliado_id"   	int NOT NULL,
	"programa_id"   	int NOT NULL,
	"fecha_delete"  	datetime NULL,
	"diagnostico_id"	int NULL,
	CONSTRAINT "pk_afiliado_programa" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."authority"  ( 
	"id"       	int NOT NULL,
	"authority"	varchar(255) NULL,
	"inserted" 	datetime NOT NULL,
	"deleted"  	bit NOT NULL,
	CONSTRAINT "pk_authority" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."autorizacion"  ( 
	"id"                        	int IDENTITY NOT NULL,
	"dosis_medicamento_homologo"	int NULL,
	"fecha_autorizacion"        	datetime NULL,
	"justificacion_ips"         	varchar(500) NULL,
	"informacion_adicional_id"  	int NULL,
	"concepto_nacional_id"      	int NULL,
	"concepto_regional_id"      	int NULL,
	"entidad_recobro_id"        	int NULL,
	"sede_ips_efectora_id"      	int NULL,
	"estado_autorizacion_id"    	int NOT NULL,
	"fecha_update_cambio_estado"	datetime NULL,
	"rol_destino_id"            	int NULL,
	"fecha_update"              	datetime NULL,
	"autorizacion_anterior_id"  	bigint NULL,
	"justificacion"             	varchar(500) NULL,
	"grupo_autorizacion_id"     	int NOT NULL,
	"informacion_tutela_id"     	bigint NULL,
	"fecha_vigencia_hasta"      	datetime NULL,
	"fecha_envio_intercambio"   	datetime NULL,
	"servicio_id"               	int NULL,
	"especialidad_id"           	int NULL,
	CONSTRAINT "pk_autorizacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."causa_externa"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__causa_ext__fecha__1A9EF37A"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__causa_ext__fecha__1B9317B3"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__causa_ext__versi__1C873BEC"  DEFAULT ((1)),
	CONSTRAINT "pk_causa_externa" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."causal_anulacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__causal_an__fecha__1D7B6025"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__causal_an__fecha__1E6F845E"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__causal_an__versi__1F63A897"  DEFAULT ((1)),
	CONSTRAINT "pk_causal_anulacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."causal_devolucion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__causal_de__fecha__2057CCD0"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__causal_de__fecha__214BF109"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__causal_de__versi__22401542"  DEFAULT ((1)),
	CONSTRAINT "pk_causal_devolucion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."codigo_seven_cie10"  ( 
	"CODIGO SEVEN"	varchar(150) NULL,
	"CIE-10"      	varchar(150) NULL 
	)
GO
CREATE TABLE "auxiliar"."commproducto_2"  ( 
	"cprConsecProducto"    	varchar(150) NULL,
	"cprCodigoProducto"    	varchar(150) NULL,
	"cprDescripProducto"   	varchar(1500) NULL,
	"cprCodPos"            	varchar(150) NULL,
	"cprTipoProducto"      	varchar(150) NULL,
	"ctrControlados"       	varchar(150) NULL,
	"cprHomologo"          	varchar(150) NULL,
	"cprPrograma"          	varchar(150) NULL,
	"cprEstado"            	varchar(150) NULL,
	"cprPyP"               	varchar(150) NULL,
	"cprAltoCosto"         	varchar(150) NULL,
	"cprTutelasCTC"        	varchar(150) NULL,
	"cprUnidadFraccionable"	varchar(150) NULL,
	"cprNombreAlterno"     	varchar(150) NULL,
	"cprDescripAbreviada"  	varchar(150) NULL,
	"cprMaxEntrega"        	varchar(150) NULL,
	"cprACMenorC"          	varchar(150) NULL,
	"cprRegInvima"         	varchar(150) NULL,
	"cprVisibleCTC"        	varchar(150) NULL,
	"cprVerClinica"        	varchar(150) NULL,
	"cprTipoProductoSeven" 	varchar(150) NULL,
	"cprCodigoCUM"         	varchar(150) NULL 
	)
GO
CREATE TABLE "auxiliar"."comprobar_municipio_departamento_regional"  ( 
	"municipio_id"   	varchar(150) NULL,
	"departamento_id"	varchar(150) NULL,
	"regional_id"    	varchar(150) NULL 
	)
GO
CREATE TABLE "transaccional"."con_autorizacion_cri_negacion"  ( 
	"concepto_autorizacion_id"	int NOT NULL,
	"criterio_negacion_id"    	int NOT NULL,
	CONSTRAINT "pk_con_autorizacion_cri_negacion" PRIMARY KEY CLUSTERED("criterio_negacion_id","concepto_autorizacion_id")
)
GO
CREATE TABLE "transaccional"."concepto_autorizacion"  ( 
	"id"                  	int IDENTITY NOT NULL,
	"dias_x_periodo"      	int NULL,
	"dosis_aprobadas"     	int NULL,
	"justificacion"       	varchar(2000) NULL,
	"numero_entregas"     	int NULL,
	"periodo_aprobado"    	int NULL,
	"unidades_aprobadas"  	int NULL,
	"causal_anulacion_id" 	int NULL,
	"causal_devolucion_id"	int NULL,
	"lateralidad_id"      	int NULL,
	CONSTRAINT "pk_concepto_autorizacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."consumo"  ( 
	"id"                    	int IDENTITY NOT NULL,
	"fecha_consumo"         	datetime NOT NULL,
	"profesional_efector_id"	int NULL,
	"cantidad_consumida"    	int NOT NULL,
	"solicitud_item_id"     	int NOT NULL,
	"fecha_insert"          	datetime NOT NULL CONSTRAINT "DF__consumo__fecha_i__062DE679"  DEFAULT (getdate()),
	"cuota_moderadora"      	numeric(12,2) NOT NULL CONSTRAINT "DF__consumo__cuota_m__3C1FE2D6"  DEFAULT ((0)),
	"copago"                	numeric(12,2) NOT NULL CONSTRAINT "DF__consumo__copago__3D14070F"  DEFAULT ((0)),
	"valor"                 	numeric(15,5) NULL,
	"tarifario_id"          	int NULL,
	CONSTRAINT "pk_consumo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."contrato"  ( 
	"id"                   	int IDENTITY NOT NULL,
	"nombre_contrato"      	varchar(100) NOT NULL,
	"sede_ips_id"          	int NOT NULL,
	"fecha_fin_contrato"   	datetime NOT NULL,
	"fecha_inicio_contrato"	datetime NOT NULL,
	"estado_contrato_id"   	int NULL,
	"aprobado_abogado"     	smallint NULL,
	"fecha_aprobado"       	datetime NULL,
	"fecha_dias_plazo"     	datetime NOT NULL,
	"legalizacion_contrato"	smallint NOT NULL,
	"plazo_dias_pago"      	int NOT NULL,
	"porcentaje_negociado" 	numeric(5,2) NULL,
	"renovacion_automatica"	smallint NOT NULL,
	"eps_id"               	int NULL,
	"tipo_plan_contrato_id"	int NOT NULL,
	"cliente_pk"           	int NULL,
	"fecha_insert"         	datetime NOT NULL CONSTRAINT "DF__contrato__fecha___2334397B"  DEFAULT (getdate()),
	"fecha_update"         	datetime NOT NULL CONSTRAINT "DF__contrato__fecha___24285DB4"  DEFAULT (getdate()),
	"fecha_delete"         	datetime NULL,
	"version"              	int NOT NULL CONSTRAINT "DF__contrato__versio__251C81ED"  DEFAULT ((1)),
	CONSTRAINT "pk_contrato" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."contrato_tarifario"  ( 
	"id"                          	int IDENTITY NOT NULL,
	"contrato_id"                 	int NOT NULL,
	"tarifario_id"                	int NOT NULL,
	"nivel_atencion"              	smallint NOT NULL,
	"porcentaje_descuento"        	numeric(5,2) NOT NULL,
	"tipo_minuta_id"              	int NOT NULL,
	"estado_contrato_tarifario_id"	int NOT NULL,
	"fecha_insert"                	datetime NOT NULL CONSTRAINT "DF__contrato___fecha__2610A626"  DEFAULT (getdate()),
	"fecha_update"                	datetime NOT NULL CONSTRAINT "DF__contrato___fecha__2704CA5F"  DEFAULT (getdate()),
	"fecha_delete"                	datetime NULL,
	"version"                     	int NOT NULL CONSTRAINT "DF__contrato___versi__27F8EE98"  DEFAULT ((1)),
	CONSTRAINT "pk_contrato_tarifario" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."criterio_negacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__criterio___fecha__28ED12D1"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__criterio___fecha__29E1370A"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__criterio___versi__2AD55B43"  DEFAULT ((1)),
	CONSTRAINT "pk_criterio_negacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."departamento"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(10) NOT NULL,
	"descripcion" 	varchar(50) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__departame__fecha__2BC97F7C"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__departame__fecha__2CBDA3B5"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__departame__versi__2DB1C7EE"  DEFAULT ((1)),
	CONSTRAINT "pk_departamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."departamento_regional"  ( 
	"id"             	int IDENTITY NOT NULL,
	"departamento_id"	int NOT NULL,
	"regional_id"    	int NOT NULL,
	"fecha_delete"   	datetime NULL,
	CONSTRAINT "pk_departamento_regional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."diagnostico"  ( 
	"id"                 	int IDENTITY NOT NULL,
	"codigo"             	varchar(6) NOT NULL,
	"descripcion"        	varchar(500) NOT NULL,
	"tipo_diagnostico_id"	int NULL,
	"cliente_pk"         	int NULL,
	"fecha_insert"       	datetime NOT NULL CONSTRAINT "DF__diagnosti__fecha__2EA5EC27"  DEFAULT (getdate()),
	"fecha_update"       	datetime NOT NULL CONSTRAINT "DF__diagnosti__fecha__2F9A1060"  DEFAULT (getdate()),
	"fecha_delete"       	datetime NULL,
	"version"            	int NOT NULL CONSTRAINT "DF__diagnosti__versi__308E3499"  DEFAULT ((1)),
	CONSTRAINT "pk_diagnostico" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."diagnostico_insumo"  ( 
	"id"            	int IDENTITY NOT NULL,
	"diagnostico_id"	int NOT NULL,
	"insumo_id"     	int NOT NULL,
	"fecha_delete"  	datetime NULL,
	CONSTRAINT "pk_diagnostico_insumo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."diagnostico_medicamento"  ( 
	"id"            	int IDENTITY NOT NULL,
	"diagnostico_id"	int NOT NULL,
	"medicamento_id"	int NOT NULL,
	"fecha_delete"  	datetime NULL,
	CONSTRAINT "pk_diagnostico_medicamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."diagnostico_procedimiento"  ( 
	"id"              	int IDENTITY NOT NULL,
	"fecha_delete"    	datetime NULL,
	"diagnostico_id"  	int NOT NULL,
	"procedimiento_id"	int NOT NULL,
	CONSTRAINT "pk_diagnostico_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."diagnosticos"  ( 
	"IdDiagnosticoHeOn"	varchar(150) NULL,
	"CIE10"            	varchar(8000) NULL,
	"Descripcion"      	varchar(8000) NULL 
	)
GO
CREATE TABLE "maestros"."director_medico_regional"  ( 
	"id"             	int IDENTITY NOT NULL,
	"email"          	varchar(50) NOT NULL,
	"nombre_apellido"	varchar(50) NOT NULL,
	"regional_id"    	int NULL,
	"fecha_insert"   	datetime NOT NULL CONSTRAINT "DF__director___fecha__318258D2"  DEFAULT (getdate()),
	"fecha_update"   	datetime NOT NULL CONSTRAINT "DF__director___fecha__32767D0B"  DEFAULT (getdate()),
	"fecha_delete"   	datetime NULL,
	"version"        	int NOT NULL CONSTRAINT "DF__director___versi__336AA144"  DEFAULT ((1)),
	CONSTRAINT "pk_director_medico_regional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."division_seccional"  ( 
	"id"         	int IDENTITY NOT NULL,
	"codigo"     	varchar(10) NULL,
	"descripcion"	varchar(100) NOT NULL,
	"cliente_pk" 	int NULL,
	"eps_id"     	int NOT NULL,
	CONSTRAINT "pk_division_seccional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."documento_soporte"  ( 
	"id"                     	int IDENTITY NOT NULL,
	"nombre_archivo_servidor"	varchar(20) NOT NULL,
	"nombre_archivo_original"	varchar(255) NULL,
	"tipo_doc_soporte_id"    	int NOT NULL,
	"solicitud_id"           	int NULL,
	"solicitud_item_id"      	int NULL,
	CONSTRAINT "pk_documento_soporte" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."entidad_recobro"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__entidad_r__fecha__345EC57D"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__entidad_r__fecha__3552E9B6"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__entidad_r__versi__36470DEF"  DEFAULT ((1)),
	CONSTRAINT "pk_entidad_recobro" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."entrega"  ( 
	"cantidad_entrega"        	int NOT NULL,
	"fecha_activacion"        	datetime NOT NULL,
	"fecha_fin_vigencia"      	datetime NOT NULL,
	"fecha_inicio_vigencia"   	datetime NOT NULL,
	"id"                      	bigint IDENTITY NOT NULL,
	"numero"                  	int NOT NULL,
	"solicitud_medicamento_id"	int NOT NULL,
	CONSTRAINT "pk_entrega" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."eps"  ( 
	"id"                    	int IDENTITY NOT NULL,
	"numero_identificacion" 	varchar(255) NOT NULL,
	"razon_social"          	varchar(100) NOT NULL,
	"tipo_identificacion_id"	int NOT NULL,
	"cliente_pk"            	int NULL,
	"fecha_insert"          	datetime NOT NULL CONSTRAINT "DF__eps__fecha_inser__373B3228"  DEFAULT (getdate()),
	"fecha_update"          	datetime NOT NULL CONSTRAINT "DF__eps__fecha_updat__382F5661"  DEFAULT (getdate()),
	"fecha_delete"          	datetime NULL,
	"version"               	int NOT NULL CONSTRAINT "DF__eps__version__39237A9A"  DEFAULT ((1)),
	CONSTRAINT "pk_eps" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."especialidad"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__especiali__fecha__3A179ED3"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__especiali__fecha__3B0BC30C"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__especiali__versi__3BFFE745"  DEFAULT ((1)),
	CONSTRAINT "pk_especialidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."especialidad_contratada"  ( 
	"id"                               	int IDENTITY NOT NULL,
	"fecha_vencimiento"                	datetime NOT NULL,
	"monto_fijo"                       	numeric(12,2) NULL,
	"otro_si"                          	int NULL,
	"porcentaje_negociacion"           	numeric(12,2) NOT NULL,
	"especialidad_id"                  	int NOT NULL,
	"estado_especialidad_contratada_id"	int NULL,
	"servicio_contratado_id"           	int NOT NULL,
	"tarifario_excepcion_id"           	int NULL,
	"tipo_minuta_id"                   	int NULL,
	"unidad_tiempo_id"                 	int NULL,
	"cliente_pk"                       	int NULL,
	"fecha_insert"                     	datetime NOT NULL CONSTRAINT "DF__especiali__fecha__3CF40B7E"  DEFAULT (getdate()),
	"fecha_update"                     	datetime NOT NULL CONSTRAINT "DF__especiali__fecha__3DE82FB7"  DEFAULT (getdate()),
	"fecha_delete"                     	datetime NULL,
	"version"                          	int NULL,
	"nivel_complejidad"                	int NULL,
	CONSTRAINT "pk_especialidad_contratada" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."especialidad_insumo"  ( 
	"id"             	int IDENTITY NOT NULL,
	"especialidad_id"	int NOT NULL,
	"insumo_id"      	int NOT NULL,
	"fecha_delete"   	datetime NULL,
	CONSTRAINT "pk_especialidad_insumo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."especialidad_medicamento"  ( 
	"id"             	int IDENTITY NOT NULL,
	"especialidad_id"	int NOT NULL,
	"medicamento_id" 	int NOT NULL,
	"fecha_delete"   	datetime NULL,
	CONSTRAINT "pk_especialidad_medicamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."especialidad_procedimiento"  ( 
	"id"              	int IDENTITY NOT NULL,
	"especialidad_id" 	int NOT NULL,
	"procedimiento_id"	int NOT NULL,
	"fecha_delete"    	datetime NULL,
	CONSTRAINT "pk_especialidad_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_afiliacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_af__fecha__3EDC53F0"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_af__fecha__3FD07829"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_af__versi__40C49C62"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_afiliacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "homologacion"."estado_autorizacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo_heon" 	varchar(255) NOT NULL,
	"descripcion" 	varchar(100) NOT NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_autorizacion__fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_autorizacion__fecha_update"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	CONSTRAINT "pk_estado_autorizacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_autorizacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_au__fecha__41B8C09B"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_au__fecha__42ACE4D4"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_au__versi__43A1090D"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_autorizacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_civil"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_ci__fecha__44952D46"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_ci__fecha__4589517F"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_ci__versi__467D75B8"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_civil" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_contrato"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_co__fecha__477199F1"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_co__fecha__4865BE2A"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_co__versi__4959E263"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_contrato" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_ips"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_ip__fecha__4A4E069C"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_ip__fecha__4B422AD5"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_ip__versi__4C364F0E"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_ips" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_item_contratado"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_it__fecha__4D2A7347"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_it__fecha__4E1E9780"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_it__versi__4F12BBB9"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_item_contratado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_procedimiento"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_pr__fecha__5006DFF2"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_pr__fecha__50FB042B"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_pr__versi__51EF2864"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_profesional"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_pr__fecha__52E34C9D"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_pr__fecha__53D770D6"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_pr__versi__54CB950F"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_profesional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."estado_visible"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__estado_vi__fecha__55BFB948"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__estado_vi__fecha__56B3DD81"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__estado_vi__versi__57A801BA"  DEFAULT ((1)),
	CONSTRAINT "pk_estado_visible" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."finalidad"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__finalidad__fecha__589C25F3"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__finalidad__fecha__59904A2C"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__finalidad__versi__5A846E65"  DEFAULT ((1)),
	CONSTRAINT "pk_finalidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."formula_item_insumo"  ( 
	"id"                  	int IDENTITY NOT NULL,
	"cantidad"            	int NOT NULL,
	"duracion"            	int NOT NULL,
	"solicitud_insumo_id" 	bigint NOT NULL,
	"causa_externa_id"    	int NOT NULL,
	"tipo_catastrofico_id"	int NOT NULL,
	"finalidad_id"        	int NOT NULL,
	CONSTRAINT "pk_formula_item_insumo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."formula_item_medicamento"  ( 
	"dosis"                   	int NOT NULL,
	"duracion"                	int NOT NULL,
	"efectosadversos"         	varchar(500) NULL,
	"frecuencia"              	int NOT NULL,
	"id"                      	int IDENTITY NOT NULL,
	"posologia"               	varchar(255) NOT NULL,
	"solicitud_medicamento_id"	int NULL,
	"via_administracion_id"   	int NOT NULL,
	"causa_externa_id"        	int NOT NULL,
	"tipo_catastrofico_id"    	int NOT NULL,
	"finalidad_id"            	int NOT NULL,
	"tipo_frecuencia_enum"    	bigint NOT NULL,
	CONSTRAINT "pk_formula_item_medicamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."formula_item_procedimiento"  ( 
	"id"                        	int IDENTITY NOT NULL,
	"lateralidad_id"            	int NOT NULL,
	"objetivo_procedimiento_id" 	int NULL,
	"origen_repeticion_id"      	int NULL,
	"posologia"                 	varchar(255) NOT NULL,
	"solicitud_procedimiento_id"	int NULL,
	"tipo_prestacion_id"        	int NULL,
	"causa_externa_id"          	int NOT NULL,
	"tipo_catastrofico_id"      	int NOT NULL,
	"finalidad_id"              	int NOT NULL,
	CONSTRAINT "pk_formula_item_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."formulario_ctc_insumo"  ( 
	"id"                            	int IDENTITY NOT NULL,
	"autorizado_invima"             	varchar(255) NOT NULL,
	"existe_riesgo_inminente"       	bit NOT NULL,
	"justificacion_medico"          	varchar(500) NOT NULL,
	"justificacion_riesgo_inminente"	varchar(500) NULL,
	"justificacion_sin_pos_previo"  	varchar(500) NULL,
	"posibilidades_pos_agotadas"    	bit NOT NULL,
	"resumen_historia_clinica"      	varchar(500) NOT NULL,
	"sin_alternativa_pos"           	bit NOT NULL,
	"causa_externa_id"              	int NOT NULL,
	"finalidad_id"                  	int NOT NULL,
	"insumo_homologo_id"            	int NULL,
	"tipo_catastrofico_id"          	int NOT NULL,
	"solicitud_insumo_id"           	bigint NOT NULL,
	CONSTRAINT "pk_formulario_ctc_insumo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."formulario_ctc_medicamento"  ( 
	"id"                            	int IDENTITY NOT NULL,
	"autorizado_invima"             	varchar(255) NOT NULL,
	"existe_riesgo_inminente"       	bit NOT NULL,
	"justificacion_medico"          	varchar(500) NOT NULL,
	"justificacion_riesgo_inminente"	varchar(500) NULL,
	"justificacion_sin_pos_previo"  	varchar(500) NULL,
	"posibilidades_pos_agotadas"    	bit NOT NULL,
	"resumen_historia_clinica"      	varchar(500) NOT NULL,
	"sin_alternativa_pos"           	bit NOT NULL,
	"causa_externa_id"              	int NOT NULL,
	"finalidad_id"                  	int NOT NULL,
	"medicamento_homologo_id"       	int NULL,
	"tipo_catastrofico_id"          	int NOT NULL,
	"solicitud_medicamento_id"      	int NULL,
	CONSTRAINT "pk_formulario_ctc_medicamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."formulario_ctc_procedimiento"  ( 
	"id"                            	int IDENTITY NOT NULL,
	"autorizado_invima"             	varchar(255) NOT NULL,
	"existe_riesgo_inminente"       	bit NOT NULL,
	"justificacion_medico"          	varchar(500) NOT NULL,
	"justificacion_riesgo_inminente"	varchar(500) NULL,
	"justificacion_sin_pos_previo"  	varchar(500) NULL,
	"posibilidades_pos_agotadas"    	bit NOT NULL,
	"resumen_historia_clinica"      	varchar(500) NOT NULL,
	"sin_alternativa_pos"           	bit NOT NULL,
	"causa_externa_id"              	int NOT NULL,
	"finalidad_id"                  	int NOT NULL,
	"tipo_catastrofico_id"          	int NOT NULL,
	"solicitud_procedimiento_id"    	int NULL,
	CONSTRAINT "pk_formulario_ctc_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."genero"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__genero__fecha_in__5B78929E"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__genero__fecha_up__5C6CB6D7"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__genero__version__5D60DB10"  DEFAULT ((1)),
	CONSTRAINT "pk_genero" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."grupo_autorizacion"  ( 
	"id"                        	int IDENTITY NOT NULL,
	"cuota_moderadora_estimada" 	numeric(12,2) NOT NULL CONSTRAINT "DF__grupo_aut__cuota__43C1049E"  DEFAULT ((0)),
	"cuota_moderadora_pagada"   	bit NOT NULL,
	"tipo_pago_enum"            	smallint NOT NULL,
	"autorizado_automaticamente"	bit NOT NULL,
	"solicitud_id"              	int NOT NULL,
	CONSTRAINT "pk_grupo_autorizacion" PRIMARY KEY NONCLUSTERED("id")

WITH (ALLOW_ROW_LOCKS = OFF, ALLOW_PAGE_LOCKS = OFF)
	)
GO
CREATE TABLE "maestros"."grupo_poblacional"  ( 
	"id"                        	int IDENTITY NOT NULL,
	"codigo"                    	varchar(50) NULL,
	"descripcion"               	varchar(500) NOT NULL,
	"cliente_pk"                	int NULL,
	"fecha_insert"              	datetime NOT NULL CONSTRAINT "DF__grupo_pob__fecha__5E54FF49"  DEFAULT (getdate()),
	"fecha_update"              	datetime NOT NULL CONSTRAINT "DF__grupo_pob__fecha__5F492382"  DEFAULT (getdate()),
	"fecha_delete"              	datetime NULL,
	"version"                   	int NOT NULL CONSTRAINT "DF__grupo_pob__versi__603D47BB"  DEFAULT ((1)),
	"eximido_regimen_subsidiado"	smallint NULL,
	CONSTRAINT "pk_grupo_poblacional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."historial_smldv"  ( 
	"id"   	int IDENTITY NOT NULL,
	"anio" 	int NOT NULL,
	"valor"	decimal(12,2) NOT NULL,
	CONSTRAINT "pk_historial_smldv" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "historial"."historial_solicitud"  ( 
	"id"                                      	int IDENTITY NOT NULL,
	"solicitud_numero_solicitud"              	int NOT NULL,
	"regional_id"                             	int NOT NULL,
	"regional_descripcion"                    	varchar(255) NOT NULL,
	"fecha_prescripcion"                      	datetime NOT NULL,
	"fecha_autorizacion"                      	datetime NULL,
	"fecha_consumo"                           	datetime NULL,
	"producto_codigo"                         	varchar(255) NOT NULL,
	"producto_descripcion"                    	varchar(1500) NOT NULL,
	"estado_autorizacion_id"                  	int NOT NULL,
	"estado_autorizacion_descripcion"         	varchar(500) NOT NULL,
	"tipo_tecnologia_id"                      	int NOT NULL,
	"tipo_tecnologia_descripcion"             	varchar(500) NOT NULL,
	"concepto_autorizacion_unidades_aprobadas"	int NULL,
	"concepto_autorizacion_periodo_aprobado"  	int NULL,
	"concepto_autorizacion_dias_por_periodo"  	int NULL,
	"afiliado_tipo_identificacion_id"         	int NOT NULL,
	"afiliado_numero_identificacion"          	varchar(255) NOT NULL,
	"sede_ips_prescriptor_id"                 	int NOT NULL,
	"sede_ips_prescriptor_nombre"             	varchar(255) NOT NULL,
	"ips_prescriptor_tipo_identificacion_id"  	int NOT NULL,
	"ips_prescriptor_numero_identificacion"   	varchar(255) NOT NULL,
	"sede_ips_efector_id"                     	int NULL,
	"sede_ips_efector_nombre"                 	varchar(255) NULL,
	"ips_efector_tipo_identificacion_id"      	int NULL,
	"ips_efector_numero_identificacion"       	varchar(255) NULL,
	CONSTRAINT "pk_historial_solicitud" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."historial_variacion_ipc"  ( 
	"id"   	int IDENTITY NOT NULL,
	"anio" 	int NOT NULL,
	"valor"	decimal(12,2) NOT NULL,
	CONSTRAINT "pk_historial_variacion_ipc" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."homologos"  ( 
	"principal"	int NULL,
	"homologo" 	int NULL 
	)
GO
CREATE TABLE "transaccional"."informacion_adicional"  ( 
	"id"                  	int IDENTITY NOT NULL,
	"descripcion_entidad" 	varchar(255) NULL,
	"causa_externa_id"    	int NULL,
	"finalidad_id"        	int NULL,
	"tipo_catastrofico_id"	int NULL,
	CONSTRAINT "pk_informacion_adicional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."informacion_tutela"  ( 
	"id"                        	bigint IDENTITY NOT NULL,
	"es_tutela_integral"        	bit NULL,
	"excenta_copago"            	bit NULL,
	"justificacion_concepto"    	varchar(2000) NULL,
	"justificacion_concepto_ldf"	varchar(2000) NULL,
	"justificacion_conexidad"   	varchar(2000) NULL,
	"numero_entregas"           	int NULL,
	"numeroTutela"              	varchar(255) NULL,
	"causal_anulacion_id"       	int NULL,
	"causal_devolucion_id"      	int NULL,
	"numero_fallo"              	varchar(100) NULL,
	CONSTRAINT "pk_informacion_tutela" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."insumo"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"nivel_autorizacion"         	smallint NOT NULL,
	"ac_menor_c"                 	int NULL,
	"alto_costo"                 	smallint NULL,
	"cantidad_maxima_autorizada" 	int NULL,
	"codigo"                     	varchar(20) NOT NULL,
	"controlados"                	smallint NULL,
	"descripcion"                	varchar(1500) NOT NULL,
	"es_pos"                     	smallint NULL,
	"es_p_y_pp"                  	smallint NOT NULL,
	"nombre_alterno"             	varchar(255) NULL,
	"suministra_medicarte"       	smallint NOT NULL,
	"visible_ctc"                	smallint NULL,
	"estado_insumo_id"           	int NOT NULL,
	"genero_id"                  	int NULL,
	"homologo_id"                	int NULL,
	"programa_alto_costo_id"     	int NULL,
	"tipo_ppm_id"                	int NOT NULL,
	"cliente_pk"                 	int NULL,
	"fecha_insert"               	datetime NOT NULL CONSTRAINT "DF__insumo__fecha_in__05E3CDB6"  DEFAULT (getdate()),
	"fecha_update"               	datetime NOT NULL CONSTRAINT "DF__insumo__fecha_up__06D7F1EF"  DEFAULT (getdate()),
	"fecha_delete"               	datetime NULL,
	"version"                    	int NOT NULL CONSTRAINT "DF__insumo__version__07CC1628"  DEFAULT ((1)),
	"tipo_pago_requerido_enum"   	int NULL,
	"clasificacion_servicio_enum"	int NOT NULL CONSTRAINT "DF__insumo__clasific__08C03A61"  DEFAULT ((0)),
	"es_epsifarma"               	smallint NULL,
	"es_insumo"                  	smallint NULL,
	"es_quirurgico"              	bit NOT NULL CONSTRAINT "DF__insumo__es_quiru__0CE5D100"  DEFAULT ((0)),
	CONSTRAINT "pk_insumo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."insumo_contratado"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"fecha_vencimiento"          	datetime NOT NULL,
	"monto_fijo"                 	numeric(12,2) NOT NULL,
	"otro_si"                    	smallint NOT NULL,
	"porcentaje_negociacion"     	numeric(12,2) NOT NULL,
	"requiere_auto"              	numeric(12,2) NOT NULL,
	"especialidad_contratada_id" 	int NOT NULL,
	"estado_insumo_contratado_id"	int NOT NULL,
	"insumo_id"                  	int NOT NULL,
	"tarifario_id"               	int NOT NULL,
	"tipo_minuta_id"             	int NOT NULL,
	"unidad_tiempo_id"           	int NOT NULL,
	"cliente_pk"                 	int NULL,
	"fecha_insert"               	datetime NOT NULL CONSTRAINT "DF__insumo_contratado__fecha_insert"  DEFAULT (getdate()),
	"fecha_update"               	datetime NOT NULL CONSTRAINT "DF__insumo_contratado__fecha_update"  DEFAULT (getdate()),
	"fecha_delete"               	datetime NULL,
	"version"                    	int NOT NULL CONSTRAINT "DF__insumo_contratado__version"  DEFAULT ((1)),
	CONSTRAINT "pk_insumo_contratado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."insumo_pos_previo"  ( 
	"id"                            	int IDENTITY NOT NULL,
	"diastratamiento"               	int NOT NULL,
	"dosis"                         	int NOT NULL,
	"insumo_id"                     	int NULL,
	"respuesta_clinica_observada_id"	int NULL,
	"formulario_ctc_insumo_id"      	int NOT NULL,
	"cantidad"                      	int NOT NULL,
	CONSTRAINT "pk_insumo_pos_previo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."insumo_tarifa"  ( 
	"id"          	int IDENTITY NOT NULL,
	"factor"      	numeric(12,2) NULL,
	"uvr"         	numeric(12,2) NULL,
	"valor"       	numeric(12,2) NOT NULL,
	"tarifario_id"	int NOT NULL,
	"insumo_id"   	int NOT NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__insumo_tarifa__fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__insumo__fecha_update"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__insumo_tarifa__version"  DEFAULT ((1)),
	CONSTRAINT "pk_insumo_tarifa" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."insumo_tope"  ( 
	"id"               	bigint IDENTITY NOT NULL,
	"tipo_insumo_enum" 	bigint NOT NULL,
	"tope"             	int NOT NULL,
	"insumo_id"        	int NULL,
	"periodicidad_dias"	int NOT NULL,
	CONSTRAINT "PK__insumo_t__3213E83F0D2F417A" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."ips"  ( 
	"id"                    	int IDENTITY NOT NULL,
	"direccion"             	varchar(100) NOT NULL,
	"numero_identificacion" 	varchar(10) NOT NULL,
	"razon_social"          	varchar(100) NOT NULL,
	"telefono"              	varchar(50) NULL,
	"estado_ips_id"         	int NOT NULL,
	"tipo_identificacion_id"	int NOT NULL,
	"tipo_ips_id"           	int NOT NULL,
	"cliente_pk"            	int NULL,
	"fecha_insert"          	datetime NOT NULL CONSTRAINT "DF__ips__fecha_inser__61316BF4"  DEFAULT (getdate()),
	"fecha_update"          	datetime NOT NULL CONSTRAINT "DF__ips__fecha_updat__6225902D"  DEFAULT (getdate()),
	"fecha_delete"          	datetime NULL,
	"version"               	int NOT NULL CONSTRAINT "DF__ips__version__6319B466"  DEFAULT ((1)),
	CONSTRAINT "pk_ips" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."lateralidad"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__lateralid__fecha__640DD89F"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__lateralid__fecha__6501FCD8"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__lateralid__versi__65F62111"  DEFAULT ((1)),
	CONSTRAINT "pk_lateralidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."linea_de_frente"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__linea_de___fecha__66EA454A"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__linea_de___fecha__67DE6983"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__linea_de___versi__68D28DBC"  DEFAULT ((1)),
	CONSTRAINT "pk_linea_de_frente" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."localidad"  ( 
	"id"          	int IDENTITY NOT NULL,
	"descripcion" 	varchar(50) NOT NULL,
	"municipio_id"	int NOT NULL,
	"regional_id" 	int NOT NULL,
	"cliente_pk"  	int NOT NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__localidad__fecha__69C6B1F5"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__localidad__fecha__6ABAD62E"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__localidad__versi__6BAEFA67"  DEFAULT ((1)),
	CONSTRAINT "pk_localidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "configuracion"."mail_content"  ( 
	"id"     	int IDENTITY NOT NULL,
	"body"   	varchar(700) NOT NULL,
	"subject"	varchar(100) NOT NULL,
	CONSTRAINT "pk_mail_content" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."medicamento"  ( 
	"id"                                	int IDENTITY NOT NULL,
	"ac_menor_c"                        	int NULL,
	"alto_costo"                        	smallint NULL,
	"cantidad_maxima_autorizada"        	int NULL,
	"codigo"                            	varchar(20) NOT NULL,
	"controlados"                       	smallint NULL,
	"descripcion"                       	varchar(1500) NOT NULL,
	"es_pos"                            	smallint NULL,
	"es_p_y_pp"                         	smallint NOT NULL,
	"nombre_alterno"                    	varchar(255) NULL,
	"suministra_medicarte"              	smallint NOT NULL,
	"visible_ctc"                       	smallint NULL,
	"estado_medicamento_id"             	int NOT NULL,
	"genero_id"                         	int NULL,
	"homologo_id"                       	int NULL,
	"programa_medicamento_alto_costo_id"	int NULL,
	"tipo_ppm_id"                       	int NOT NULL,
	"cliente_pk"                        	int NULL,
	"fecha_insert"                      	datetime NOT NULL CONSTRAINT "DF__medicamen__fecha__6CA31EA0"  DEFAULT (getdate()),
	"fecha_update"                      	datetime NOT NULL CONSTRAINT "DF__medicamen__fecha__6D9742D9"  DEFAULT (getdate()),
	"fecha_delete"                      	datetime NULL,
	"version"                           	int NOT NULL CONSTRAINT "DF__medicamen__versi__6E8B6712"  DEFAULT ((1)),
	"tipo_pago_requerido_enum"          	int NULL,
	"clasificacion_servicio_enum"       	int NOT NULL CONSTRAINT "DF__medicamen__clasi__6F7F8B4B"  DEFAULT ((0)),
	"nivel_autorizacion"                	int NULL,
	"es_insumo"                         	smallint NULL,
	"es_comercial"                      	smallint NOT NULL CONSTRAINT "DF__medicamen__es_co__735B0927"  DEFAULT ((0)),
	"es_quirurgico"                     	bit NOT NULL,
	"es_epsifarma"                      	bit NOT NULL CONSTRAINT "DF__medicamen__es_ep__492FC531"  DEFAULT ((1)),
	"es_proveeduria"                    	bit NOT NULL CONSTRAINT "DF__medicamen__es_pr__288DEB75"  DEFAULT ((0)),
	CONSTRAINT "pk_medicamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."medicamento_condicionado"  ( 
	"id"                      	int IDENTITY NOT NULL,
	"medicamento_id"          	int NOT NULL,
	"medicamento_codigo_seven"	varchar(10) NOT NULL,
	"diagnostico_id"          	int NOT NULL,
	"diagnostico_cie10"       	varchar(10) NOT NULL,
	"fecha_delete"            	datetime NULL,
	"fecha_insert"            	datetime NOT NULL CONSTRAINT "DF__medicamen__fecha__4F32B74A"  DEFAULT (getdate()),
	"fecha_update"            	datetime NULL CONSTRAINT "DF_maestros_medicamento_condicionado_fecha_update"  DEFAULT (getdate()),
	"version"                 	int NOT NULL CONSTRAINT "DF__medicamen__versi__68F2894D"  DEFAULT ((1)),
	CONSTRAINT "pk_medicamento_condicionado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."medicamento_contratado"  ( 
	"id"                              	int IDENTITY NOT NULL,
	"fecha_vencimiento"               	datetime NOT NULL,
	"monto_fijo"                      	numeric(12,2) NOT NULL,
	"otro_si"                         	smallint NOT NULL,
	"porcentaje_negociacion"          	numeric(12,2) NOT NULL,
	"requiere_auto"                   	numeric(12,2) NOT NULL,
	"especialidad_contratada_id"      	int NOT NULL,
	"estado_medicamento_contratado_id"	int NOT NULL,
	"medicamento_id"                  	int NOT NULL,
	"tarifario_id"                    	int NOT NULL,
	"tipo_minuta_id"                  	int NOT NULL,
	"unidad_tiempo_id"                	int NOT NULL,
	"cliente_pk"                      	int NULL,
	"fecha_insert"                    	datetime NOT NULL CONSTRAINT "DF__medicamen__fecha__7073AF84"  DEFAULT (getdate()),
	"fecha_update"                    	datetime NOT NULL CONSTRAINT "DF__medicamen__fecha__7167D3BD"  DEFAULT (getdate()),
	"fecha_delete"                    	datetime NULL,
	"version"                         	int NOT NULL CONSTRAINT "DF__medicamen__versi__725BF7F6"  DEFAULT ((1)),
	CONSTRAINT "pk_medicamento_contratado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."medicamento_pos_previo"  ( 
	"id"                            	int IDENTITY NOT NULL,
	"diastratamiento"               	int NOT NULL,
	"dosis"                         	int NOT NULL,
	"medicamento_id"                	int NULL,
	"respuesta_clinica_observada_id"	int NULL,
	"formulario_ctc_medicamento_id" 	int NOT NULL,
	CONSTRAINT "pk_medicamento_pos_previo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."medicamento_tarifa"  ( 
	"id"            	int IDENTITY NOT NULL,
	"factor"        	numeric(12,2) NULL,
	"uvr"           	numeric(12,2) NULL,
	"valor"         	numeric(12,2) NOT NULL,
	"tarifario_id"  	int NOT NULL,
	"medicamento_id"	int NOT NULL,
	"fecha_insert"  	datetime NOT NULL CONSTRAINT "DF__medicamen__fecha__73501C2F"  DEFAULT (getdate()),
	"fecha_update"  	datetime NOT NULL CONSTRAINT "DF__medicamen__fecha__74444068"  DEFAULT (getdate()),
	"fecha_delete"  	datetime NULL,
	"version"       	int NOT NULL CONSTRAINT "DF__medicamen__versi__753864A1"  DEFAULT ((1)),
	CONSTRAINT "pk_medicamento_tarifa" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "menu"."menu"  ( 
	"id"          	int NOT NULL,
	"authority_id"	int NULL,
	"icon"        	varchar(255) NULL,
	"parent_id"   	int NULL,
	"link"        	varchar(255) NULL,
	"title"       	varchar(255) NULL,
	"tooltip"     	varchar(255) NULL,
	CONSTRAINT "pk_menu" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."montos_copago_contributivo"  ( 
	"id"                                   	int IDENTITY NOT NULL,
	"anio"                                 	int NOT NULL,
	"copago_porcentaje_valor_servicio"     	numeric(19,2) NOT NULL,
	"copago_tope_anual_cualquier_patologia"	numeric(19,2) NOT NULL,
	"copago_tope_anual_misma_patologia"    	numeric(19,2) NOT NULL,
	"valor_cuota_moderadora"               	numeric(19,2) NOT NULL,
	"nivel_ibc_id"                         	int NOT NULL,
	"fecha_insert"                         	datetime NOT NULL CONSTRAINT "DF__montos_co__fecha__762C88DA"  DEFAULT (getdate()),
	"fecha_update"                         	datetime NOT NULL CONSTRAINT "DF__montos_co__fecha__7720AD13"  DEFAULT (getdate()),
	"fecha_delete"                         	datetime NULL,
	"version"                              	int NOT NULL CONSTRAINT "DF__montos_co__versi__7814D14C"  DEFAULT ((1)),
	CONSTRAINT "pk_montos_copago_contributivo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."montos_copago_subsidiado"  ( 
	"id"                                   	int IDENTITY NOT NULL,
	"anio"                                 	int NOT NULL,
	"copago_porcentaje_valor_servicio"     	numeric(19,2) NOT NULL,
	"copago_tope_anual_cualquier_patologia"	numeric(19,2) NOT NULL,
	"copago_tope_anual_misma_patologia"    	numeric(19,2) NOT NULL,
	"nivelsisben_id"                       	int NOT NULL,
	"fecha_insert"                         	datetime NOT NULL CONSTRAINT "DF__montos_co__fecha__7908F585"  DEFAULT (getdate()),
	"fecha_update"                         	datetime NOT NULL CONSTRAINT "DF__montos_co__fecha__79FD19BE"  DEFAULT (getdate()),
	"fecha_delete"                         	datetime NULL,
	"version"                              	int NOT NULL CONSTRAINT "DF__montos_co__versi__7AF13DF7"  DEFAULT ((1)),
	CONSTRAINT "pk_montos_copago_subsidiado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."municipio"  ( 
	"id"             	int IDENTITY NOT NULL,
	"codigo"         	varchar(10) NOT NULL,
	"descripcion"    	varchar(50) NOT NULL,
	"departamento_id"	int NULL,
	"regional_id"    	int NULL,
	"cliente_pk"     	int NULL,
	"fecha_insert"   	datetime NOT NULL CONSTRAINT "DF__municipio__fecha__7BE56230"  DEFAULT (getdate()),
	"fecha_update"   	datetime NOT NULL CONSTRAINT "DF__municipio__fecha__7CD98669"  DEFAULT (getdate()),
	"fecha_delete"   	datetime NULL,
	"version"        	int NOT NULL CONSTRAINT "DF__municipio__versi__7DCDAAA2"  DEFAULT ((1)),
	CONSTRAINT "pk_municipio" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."municipio_division_seccional"  ( 
	"municipio_id"         	int NOT NULL,
	"division_seccional_id"	int NOT NULL,
	CONSTRAINT "pk_municipio_division_seccional" PRIMARY KEY CLUSTERED("municipio_id","division_seccional_id")
)
GO
CREATE TABLE "maestros"."nivel_autorizacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__nivel_aut__fecha__7EC1CEDB"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__nivel_aut__fecha__7FB5F314"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__nivel_aut__versi__00AA174D"  DEFAULT ((1)),
	CONSTRAINT "pk_nivel_autorizacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."nivel_ibc"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__nivel_ibc__fecha__019E3B86"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__nivel_ibc__fecha__02925FBF"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__nivel_ibc__versi__038683F8"  DEFAULT ((1)),
	CONSTRAINT "pk_nivel_ibc" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."nivel_sisben"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__nivel_sis__fecha__047AA831"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__nivel_sis__fecha__056ECC6A"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__nivel_sis__versi__0662F0A3"  DEFAULT ((1)),
	CONSTRAINT "pk_nivel_sisben" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."objetivo_procedimiento"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__objetivo___fecha__075714DC"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__objetivo___fecha__084B3915"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__objetivo___versi__093F5D4E"  DEFAULT ((1)),
	CONSTRAINT "pk_objetivo_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."ocupacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF_ocupacion_fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF_ocupacion_fecha_update"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF_ocupacion_version"  DEFAULT ((1)),
	"eps_id"      	int NOT NULL,
	CONSTRAINT "pk_ocupacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."origen_autorizacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"nombre"      	varchar(500) NOT NULL,
	"visible"     	smallint NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__origen_autorizacion_fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__origen_autorizacion_fecha_update"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__origen_autorizacion_version"  DEFAULT ((1)),
	CONSTRAINT "pk_origen_autorizacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."origen_repeticion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__origen_re__fecha__0A338187"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__origen_re__fecha__0B27A5C0"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__origen_re__versi__0C1BC9F9"  DEFAULT ((1)),
	CONSTRAINT "pk_origen_repeticion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."origen_solicitud"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__origen_solicitud_fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "fecha_update"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__origen_solicitud_version"  DEFAULT ((1)),
	CONSTRAINT "pk_origen_solicitud" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."parentesco"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__parentesc__fecha__0D0FEE32"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__parentesc__fecha__0E04126B"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__parentesc__versi__0EF836A4"  DEFAULT ((1)),
	CONSTRAINT "pk_parentesco" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."prioridad"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__prioridad_fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__prioridad_fecha_update"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__prioridad_version"  DEFAULT ((1)),
	CONSTRAINT "pk_prioridad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."procedimiento"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"autorizado"                 	smallint NULL,
	"codigo"                     	varchar(20) NOT NULL,
	"descripcion"                	varchar(1500) NOT NULL,
	"es_p_y_pp"                  	smallint NOT NULL,
	"nivel_autorizacion"         	int NOT NULL,
	"nivel_complejidad"          	int NOT NULL,
	"requiere_autorizacion"      	smallint NULL,
	"estado_procedimiento_id"    	int NOT NULL,
	"genero_id"                  	int NULL,
	"tipo_ppm_id"                	int NOT NULL,
	"tipo_pago_requerido_enum"   	int NULL,
	"cliente_pk"                 	int NULL,
	"fecha_insert"               	datetime NOT NULL CONSTRAINT "DF__procedimi__fecha__0FEC5ADD"  DEFAULT (getdate()),
	"fecha_update"               	datetime NOT NULL CONSTRAINT "DF__procedimi__fecha__10E07F16"  DEFAULT (getdate()),
	"fecha_delete"               	datetime NULL,
	"version"                    	int NOT NULL CONSTRAINT "DF__procedimi__versi__11D4A34F"  DEFAULT ((1)),
	"clasificacion_servicio_enum"	int NOT NULL CONSTRAINT "DF__procedimi__clasi__12C8C788"  DEFAULT ((0)),
	"es_quirurgico"              	bit NOT NULL CONSTRAINT "DF__procedimi__es_qu__1B68FA81"  DEFAULT ((0)),
	"es_proveeduria"             	bit NOT NULL CONSTRAINT "DF__procedimi__es_pr__29820FAE"  DEFAULT ((0)),
	CONSTRAINT "pk_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."procedimiento_contratado"  ( 
	"id"                                	int IDENTITY NOT NULL,
	"fecha_vencimiento"                 	datetime NOT NULL,
	"monto_fijo"                        	numeric(12,2) NOT NULL,
	"otro_si"                           	smallint NOT NULL,
	"porcentaje_negociacion"            	numeric(12,2) NOT NULL,
	"requiere_auto"                     	numeric(12,2) NOT NULL,
	"especialidad_contratada_id"        	int NOT NULL,
	"estado_procedimiento_contratado_id"	int NOT NULL,
	"procedimiento_id"                  	int NOT NULL,
	"tarifario_id"                      	int NOT NULL,
	"tipo_minuta_id"                    	int NOT NULL,
	"unidad_tiempo_id"                  	int NOT NULL,
	"cliente_pk"                        	int NULL,
	"fecha_insert"                      	datetime NOT NULL CONSTRAINT "DF__procedimi__fecha__13BCEBC1"  DEFAULT (getdate()),
	"fecha_update"                      	datetime NOT NULL CONSTRAINT "DF__procedimi__fecha__14B10FFA"  DEFAULT (getdate()),
	"fecha_delete"                      	datetime NULL,
	"version"                           	int NOT NULL CONSTRAINT "DF__procedimi__versi__15A53433"  DEFAULT ((1)),
	CONSTRAINT "pk_procedimiento_contratado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."procedimiento_homologo"  ( 
	"id"                             	int IDENTITY NOT NULL,
	"cantidad_periodo"               	int NOT NULL,
	"dias_de_uso"                    	int NOT NULL,
	"frecuencia_de_uso"              	int NOT NULL,
	"objetivo_procedimiento_id"      	int NOT NULL,
	"procedimiento_id"               	int NOT NULL,
	"formulario_ctc_procedimiento_id"	int NOT NULL,
	CONSTRAINT "pk_procedimiento_homologo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."procedimiento_pos_previo"  ( 
	"id"                             	int IDENTITY NOT NULL,
	"procedimiento_id"               	int NULL,
	"respuesta_clinica_observada_id" 	int NULL,
	"formulario_ctc_procedimiento_id"	int NOT NULL,
	CONSTRAINT "pk_procedimiento_pos_previo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."procedimiento_tarifa"  ( 
	"id"              	int IDENTITY NOT NULL,
	"factor"          	numeric(12,2) NULL,
	"uvr"             	numeric(12,2) NULL,
	"valor"           	numeric(12,2) NOT NULL,
	"tarifario_id"    	int NOT NULL,
	"procedimiento_id"	int NOT NULL,
	"fecha_insert"    	datetime NOT NULL CONSTRAINT "DF__procedimi__fecha__1699586C"  DEFAULT (getdate()),
	"fecha_update"    	datetime NOT NULL CONSTRAINT "DF__procedimi__fecha__178D7CA5"  DEFAULT (getdate()),
	"fecha_delete"    	datetime NULL,
	"version"         	int NOT NULL CONSTRAINT "DF__procedimi__versi__1881A0DE"  DEFAULT ((1)),
	CONSTRAINT "pk_procedimiento_tarifa" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "homologacion"."profesional"  ( 
	"id"                    	int IDENTITY NOT NULL,
	"profesional_cliente_pk"	int NULL,
	"profesional_id"        	int NULL,
	"eps_id"                	int NULL,
	"fecha_delete"          	datetime NULL,
	CONSTRAINT "pk_profesional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."profesional"  ( 
	"id"                           	int IDENTITY NOT NULL,
	"beeper"                       	varchar(50) NULL,
	"celular"                      	varchar(50) NULL,
	"direccion"                    	varchar(100) NULL,
	"email"                        	varchar(50) NULL,
	"fax"                          	varchar(50) NULL,
	"firma_impresa"                	varchar(70) NULL,
	"numero_identificacion"        	varchar(50) NOT NULL,
	"primer_apellido"              	varchar(100) NOT NULL,
	"primer_nombre"                	varchar(100) NOT NULL,
	"registro_medico"              	varchar(50) NULL,
	"segundo_apellido"             	varchar(100) NULL,
	"segundo_nombre"               	varchar(100) NULL,
	"telefono1"                    	varchar(50) NULL,
	"telefono2"                    	varchar(50) NULL,
	"telefono_atencion"            	varchar(50) NULL,
	"division_seccional_id"        	int NULL,
	"identificacion_profesional_id"	int NULL,
	"municipio_id"                 	int NULL,
	"tipo_profesional_id"          	int NULL,
	"cliente_pk"                   	int NULL,
	"fecha_insert"                 	datetime NOT NULL CONSTRAINT "DF__profesion__fecha__1975C517"  DEFAULT (getdate()),
	"fecha_update"                 	datetime NOT NULL CONSTRAINT "DF__profesion__fecha__1A69E950"  DEFAULT (getdate()),
	"fecha_delete"                 	datetime NULL,
	"version"                      	int NULL,
	"eps_id"                       	int NULL,
	CONSTRAINT "pk_profesional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."profesional_especialidad"  ( 
	"prfeIDProfEspecialidad" 	varchar(150) NULL,
	"prfeIDProf"             	varchar(150) NULL,
	"prfeIDEspeTT"           	varchar(150) NULL,
	"prfeNivelAutorizacionTT"	varchar(150) NULL,
	"prfeEstProfEspeTT"      	varchar(150) NULL,
	"prfeUsuario"            	varchar(150) NULL,
	"prfeFchAccion"          	varchar(150) NULL,
	"prfeTipAccionTT"        	varchar(150) NULL 
	)
GO
CREATE TABLE "homologacion"."profesional_especialidad"  ( 
	"id"                                 	int IDENTITY NOT NULL,
	"profesional_especialidad_cliente_pk"	int NULL,
	"profesional_especialidad_id"        	int NULL,
	"eps_id"                             	int NULL,
	"fecha_delete"                       	datetime NULL,
	CONSTRAINT "pk_profesional_especialidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."profesional_especialidad"  ( 
	"id"                   	int IDENTITY NOT NULL,
	"especialidad_id"      	int NOT NULL,
	"estado_profesional_id"	int NOT NULL,
	"nivel_autorizacion_id"	int NULL,
	"profesional_id"       	int NOT NULL,
	"cliente_pk"           	int NULL,
	"fecha_insert"         	datetime NOT NULL CONSTRAINT "DF__profesion__fecha__1B5E0D89"  DEFAULT (getdate()),
	"fecha_update"         	datetime NOT NULL CONSTRAINT "DF__profesion__fecha__1C5231C2"  DEFAULT (getdate()),
	"fecha_delete"         	datetime NULL,
	"version"              	int NULL,
	CONSTRAINT "pk_profesional_especialidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."profesional_especialidad_x_sede_ips"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"profesional_especialidad_id"	int NOT NULL,
	"sede_ips_id"                	int NOT NULL,
	"fecha_delete"               	datetime NULL,
	CONSTRAINT "pk_profesional_especialidad_x_sede_ips" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."programa"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__programa__fecha___1D4655FB"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__programa__fecha___1E3A7A34"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__programa__versio__1F2E9E6D"  DEFAULT ((1)),
	CONSTRAINT "pk_programa" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."programa_diagnostico"  ( 
	"dxIDDiagnostico"	varchar(150) NULL,
	"dypProgramPYP"  	varchar(150) NULL 
	)
GO
CREATE TABLE "maestros"."programa_diagnostico"  ( 
	"id"            	int IDENTITY NOT NULL,
	"programa_id"   	int NOT NULL,
	"diagnostico_id"	int NOT NULL,
	"fecha_delete"  	datetime NULL,
	CONSTRAINT "pk_programa_diagnostico" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."programa_medicamento"  ( 
	"cprConsecProducto"	varchar(150) NULL,
	"medCodProgramaPYP"	varchar(150) NULL 
	)
GO
CREATE TABLE "maestros"."programa_medicamento"  ( 
	"id"            	int IDENTITY NOT NULL,
	"programa_id"   	int NOT NULL,
	"medicamento_id"	int NOT NULL,
	"fecha_delete"  	datetime NULL,
	CONSTRAINT "pk_programa_medicamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."programa_medicamento_alto_costo"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__programa___fecha__2022C2A6"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__programa___fecha__2116E6DF"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__programa___versi__220B0B18"  DEFAULT ((1)),
	CONSTRAINT "pk_programa_medicamento_alto_costo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."programa_procedimiento"  ( 
	"proIDProcedimiento"	varchar(150) NULL,
	"procCodProgramaPYP"	varchar(150) NULL 
	)
GO
CREATE TABLE "maestros"."programa_procedimiento"  ( 
	"id"              	int IDENTITY NOT NULL,
	"programa_id"     	int NOT NULL,
	"procedimiento_id"	int NOT NULL,
	"fecha_delete"    	datetime NULL,
	CONSTRAINT "pk_programa_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "configuracion"."properties"  ( 
	"id"        	int IDENTITY NOT NULL,
	"aplicacion"	varchar(255) NOT NULL,
	"clave"     	varchar(255) NOT NULL,
	"valor"     	varchar(255) NOT NULL,
	CONSTRAINT "pk_properties" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."razon_estado_afiliacion"  ( 
	"id"                      	int IDENTITY NOT NULL,
	"codigo"                  	varchar(50) NULL,
	"descripcion"             	varchar(500) NOT NULL,
	"codigo_estado_afiliacion"	varchar(50) NULL,
	"cliente_pk"              	int NULL,
	"fecha_insert"            	datetime NOT NULL CONSTRAINT "DF__razon_est__fecha__22FF2F51"  DEFAULT (getdate()),
	"fecha_update"            	datetime NOT NULL CONSTRAINT "DF__razon_est__fecha__23F3538A"  DEFAULT (getdate()),
	"fecha_delete"            	datetime NULL,
	"version"                 	int NOT NULL CONSTRAINT "DF__razon_est__versi__24E777C3"  DEFAULT ((1)),
	CONSTRAINT "pk_razon_estado_afiliacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."razon_negacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__razon_negacion_fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__razon_negacion_fecha_update"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__razon_negacion_version"  DEFAULT ((1)),
	CONSTRAINT "pk_razon_negacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."recovery_token"  ( 
	"id"      	int IDENTITY NOT NULL,
	"user_id" 	int NOT NULL,
	"token"   	varchar(255) NOT NULL,
	"inserted"	datetime NOT NULL,
	"deleted" 	bit NOT NULL,
	CONSTRAINT "pk_recovery_token" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."regimen_juridico"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__regimen_j__fecha__25DB9BFC"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__regimen_j__fecha__26CFC035"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__regimen_j__versi__27C3E46E"  DEFAULT ((1)),
	CONSTRAINT "pk_regimen_juridico" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."regimen_tributario"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__regimen_t__fecha__28B808A7"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__regimen_t__fecha__29AC2CE0"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__regimen_t__versi__2AA05119"  DEFAULT ((1)),
	CONSTRAINT "pk_regimen_tributario" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."regional"  ( 
	"id"                   	int IDENTITY NOT NULL,
	"codigo"               	varchar(10) NOT NULL,
	"descripcion"          	varchar(50) NOT NULL,
	"division_seccional_id"	int NOT NULL,
	"cliente_pk"           	int NULL,
	"fecha_insert"         	datetime NOT NULL CONSTRAINT "DF__regional__fecha___2B947552"  DEFAULT (getdate()),
	"fecha_update"         	datetime NOT NULL CONSTRAINT "DF__regional__fecha___2C88998B"  DEFAULT (getdate()),
	"fecha_delete"         	datetime NULL,
	"version"              	int NOT NULL CONSTRAINT "DF__regional__versio__2D7CBDC4"  DEFAULT ((1)),
	CONSTRAINT "pk_regional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."regional_departemento"  ( 
	"regional"       	varchar(150) NULL,
	"id_departamento"	varchar(150) NULL 
	)
GO
CREATE TABLE "maestros"."respuesta_clinica_observada"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__respuesta__fecha__2E70E1FD"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__respuesta__fecha__2F650636"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__respuesta__versi__30592A6F"  DEFAULT ((1)),
	CONSTRAINT "pk_respuesta_clinica_observada" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."resumen_diagnostico"  ( 
	"id"            	int IDENTITY NOT NULL,
	"es_principal"  	bit NOT NULL,
	"diagnostico_id"	int NULL,
	"resumen_id"    	int NOT NULL,
	CONSTRAINT "pk_resumen_diagnostico" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."resumen_historia_clinica"  ( 
	"id"                  	int IDENTITY NOT NULL,
	"conducta"            	varchar(255) NOT NULL,
	"evolucion"           	varchar(255) NOT NULL,
	"fecha_fin"           	datetime NOT NULL,
	"fecha_inicio"        	datetime NOT NULL,
	"causa_externa_id"    	int NOT NULL,
	"tipo_catastrofico_id"	int NOT NULL,
	CONSTRAINT "pk_resumen_historia_clinica" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."role"  ( 
	"id"      	int NOT NULL,
	"deleted" 	bit NOT NULL,
	"inserted"	datetime NOT NULL,
	"role"    	varchar(30) NOT NULL,
	CONSTRAINT "pk_role" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."role_authority"  ( 
	"role_id"     	int NOT NULL,
	"authority_id"	int NOT NULL,
	CONSTRAINT "pk_role_authority" PRIMARY KEY CLUSTERED("role_id","authority_id")
)
GO
CREATE TABLE "transaccional"."role_estado_estados"  ( 
	"role_estado_id"        	int NOT NULL,
	"estado_autorizacion_id"	int NOT NULL,
	CONSTRAINT "pk_role_estado_estados" PRIMARY KEY CLUSTERED("estado_autorizacion_id","role_estado_id")
)
GO
CREATE TABLE "transaccional"."role_estado_visible"  ( 
	"id"               	int IDENTITY NOT NULL,
	"estado_visible_id"	int NOT NULL,
	"role_id"          	int NOT NULL,
	CONSTRAINT "pk_role_estado_visible" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."sede_ips"  ( 
	"id"                   	int IDENTITY NOT NULL,
	"codigo_min_salud"     	varchar(20) NULL,
	"direccion"            	varchar(100) NOT NULL,
	"email"                	varchar(50) NULL,
	"es_especialista"      	smallint NULL,
	"fecha_habilitacion"   	datetime NULL,
	"fecha_inactivacion"   	datetime NULL,
	"nivel_de_atencion"    	int NOT NULL,
	"nombre"               	varchar(100) NULL,
	"pag_web"              	varchar(100) NULL,
	"telefono1"            	varchar(50) NULL,
	"telefono2"            	varchar(50) NULL,
	"estado_ips_id"        	int NOT NULL,
	"ips_id"               	int NULL,
	"localidad_id"         	int NULL,
	"municipio_id"         	int NOT NULL,
	"regimen_juridico_id"  	int NOT NULL,
	"regimen_tributario_id"	int NOT NULL,
	"regional_id"          	int NOT NULL,
	"tipo_ips_id"          	int NOT NULL,
	"tipo_servicio_id"     	int NOT NULL,
	"ubicacion_id"         	int NULL,
	"cliente_pk"           	int NULL,
	"fecha_insert"         	datetime NOT NULL CONSTRAINT "DF__sede_ips__fecha___314D4EA8"  DEFAULT (getdate()),
	"fecha_update"         	datetime NOT NULL CONSTRAINT "DF__sede_ips__fecha___324172E1"  DEFAULT (getdate()),
	"fecha_delete"         	datetime NULL,
	"version"              	int NOT NULL CONSTRAINT "DF__sede_ips__versio__3335971A"  DEFAULT ((1)),
	"es_epsifarma"         	smallint NULL CONSTRAINT "DF__sede_ips__es_eps__3429BB53"  DEFAULT ((0)),
	"eps_id"               	int NULL CONSTRAINT "DF__sede_ips__eps_id__351DDF8C"  DEFAULT ((1)),
	CONSTRAINT "pk_sede_ips" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."sede_ips_division_seccional"  ( 
	"sede_ips_id"          	int NOT NULL,
	"division_seccional_id"	int NOT NULL,
	CONSTRAINT "pk_sede_ips_division_seccional" PRIMARY KEY CLUSTERED("sede_ips_id","division_seccional_id")
)
GO
CREATE TABLE "maestros"."servicio"  ( 
	"id"                     	int IDENTITY NOT NULL,
	"codigo_ministerio_salud"	int NOT NULL,
	"descripcion"            	varchar(50) NOT NULL,
	"homologacion"           	smallint NOT NULL,
	"hospitalario"           	smallint NOT NULL,
	"nivel_de_atencion"      	int NOT NULL,
	"unidad_funcional_id"    	int NULL,
	"cliente_pk"             	int NULL,
	"fecha_insert"           	datetime NOT NULL CONSTRAINT "DF__servicio__fecha___361203C5"  DEFAULT (getdate()),
	"fecha_update"           	datetime NOT NULL CONSTRAINT "DF__servicio__fecha___370627FE"  DEFAULT (getdate()),
	"fecha_delete"           	datetime NULL,
	"version"                	int NOT NULL CONSTRAINT "DF__servicio__versio__37FA4C37"  DEFAULT ((1)),
	CONSTRAINT "pk_servicio" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."servicio_contratado"  ( 
	"id"                           	int IDENTITY NOT NULL,
	"cantidad_promedio"            	int NULL,
	"fecha_vencimiento"            	datetime NOT NULL,
	"monto_fijo"                   	numeric(12,2) NOT NULL,
	"observaciones"                	varchar(255) NULL,
	"otro_si"                      	smallint NOT NULL,
	"porcentaje_negociacion"       	numeric(12,2) NOT NULL,
	"contrato_id"                  	int NOT NULL,
	"estado_servicio_contratado_id"	int NOT NULL,
	"servicio_id"                  	int NOT NULL,
	"tarifario_excepcion_id"       	int NULL,
	"tipo_minuta_id"               	int NOT NULL,
	"unidad_tiempo_id"             	int NOT NULL,
	"cliente_pk"                   	int NULL,
	"fecha_insert"                 	datetime NOT NULL CONSTRAINT "DF__servicio___fecha__38EE7070"  DEFAULT (getdate()),
	"fecha_update"                 	datetime NOT NULL CONSTRAINT "DF__servicio___fecha__39E294A9"  DEFAULT (getdate()),
	"fecha_delete"                 	datetime NULL,
	"version"                      	int NOT NULL CONSTRAINT "DF__servicio___versi__3AD6B8E2"  DEFAULT ((1)),
	"nivel_complejidad"            	int NOT NULL,
	CONSTRAINT "pk_servicio_contratado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."servicio_especialidad"  ( 
	"id"             	int IDENTITY NOT NULL,
	"servicio_id"    	int NOT NULL,
	"especialidad_id"	int NOT NULL,
	"fecha_delete"   	datetime NULL,
	CONSTRAINT "pk_servicio_especialidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."solicitud"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"fecha_creacion"             	datetime NULL,
	"afiliado_id"                	int NOT NULL,
	"profesional_id"             	int NOT NULL,
	"resumen_historia_clinica_id"	int NULL,
	"sede_ips_id"                	int NOT NULL,
	"user_id"                    	int NOT NULL,
	"observaciones"              	varchar(500) NULL,
	"primera_formulacion_anio"   	bit NOT NULL CONSTRAINT "DF__solicitud__prime__216BEC9A"  DEFAULT ((0)),
	"numero_solicitud"           	int NOT NULL CONSTRAINT "DF__solicitud__numer__0777106D"  DEFAULT (NEXT VALUE FOR [transaccional].[seq_numero_solicitud]),
	CONSTRAINT "pk_solicitud" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."solicitud_diagnostico"  ( 
	"id"            	int IDENTITY NOT NULL,
	"solicitud_id"  	int NOT NULL,
	"diagnostico_id"	int NULL,
	"es_principal"  	bit NOT NULL,
	CONSTRAINT "pk_solicitud_diagnostico" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."solicitud_insumo"  ( 
	"id"               	int IDENTITY NOT NULL,
	"insumo_id"        	int NULL,
	"solicitud_item_id"	bigint NOT NULL,
	CONSTRAINT "pk_solicitud_insumo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."solicitud_item"  ( 
	"id"                 	int IDENTITY NOT NULL,
	"cantidad_solicitada"	int NOT NULL,
	"diagnostico_id"     	int NULL,
	"solicitud_id"       	int NOT NULL,
	"tipo_ppm_id"        	int NOT NULL,
	"tipo_servicio_id"   	int NOT NULL,
	"autorizacion_id"    	int NOT NULL,
	"saldo_a_consumir"   	int NOT NULL,
	"tipo_tecnologia_id" 	int NOT NULL CONSTRAINT "DF__solicitud__tipo___6DEC4894"  DEFAULT ((4)),
	"aplica_tutela"      	bit NULL,
	"copago_estimado"    	numeric(12,2) NOT NULL CONSTRAINT "DF__solicitud__copag__0DD9F539"  DEFAULT ((0)),
	CONSTRAINT "pk_solicitud_item" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."solicitud_medicamento"  ( 
	"id"               	int IDENTITY NOT NULL,
	"medicamento_id"   	int NULL,
	"solicitud_item_id"	int NULL,
	CONSTRAINT "pk_solicitud_medicamento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."solicitud_parcial"  ( 
	"id"                             	int IDENTITY NOT NULL,
	"fecha_creacion"                 	datetime NULL,
	"form_data"                      	text NULL,
	"numero_identificacion_afiliado" 	varchar(30) NULL,
	"sede_ips_id"                    	int NULL,
	"tipo_identificacion_afiliado_id"	int NULL,
	"user_id"                        	int NULL,
	"fecha_update"                   	datetime NOT NULL,
	"numero_solicitud"               	int NOT NULL CONSTRAINT "DF__solicitud__numer__086B34A6"  DEFAULT (NEXT VALUE FOR [transaccional].[seq_numero_solicitud]),
	"nombre_completo_afiliado"       	varchar(120) NOT NULL CONSTRAINT "DF__solicitud__nombr__0E0EFF63"  DEFAULT (' '),
	CONSTRAINT "pk_solicitud_parcial" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."solicitud_procedimiento"  ( 
	"id"               	int IDENTITY NOT NULL,
	"especialidad_id"  	int NOT NULL,
	"procedimiento_id" 	int NULL,
	"solicitud_item_id"	int NULL,
	CONSTRAINT "pk_solicitud_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."tarifa_procedimiento"  ( 
	"id"                	int IDENTITY NOT NULL,
	"especialidad_id"   	int NOT NULL,
	"fecha_finalizacion"	datetime NOT NULL,
	"fecha_inicio"      	datetime NOT NULL,
	"procedimiento_id"  	int NOT NULL,
	"sede_ips_id"       	int NOT NULL,
	"servicio_id"       	int NOT NULL,
	"valor"             	numeric(19,2) NOT NULL,
	CONSTRAINT "PK__tarifa_p__3213E83F369C6A9F" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tarifario"  ( 
	"id"                 	int IDENTITY NOT NULL,
	"porcentaje_ajuste"  	numeric(12,2) NOT NULL,
	"fecha_insert"       	datetime NOT NULL CONSTRAINT "DF__tarifario__fecha__3BCADD1B"  DEFAULT (getdate()),
	"fecha_update"       	datetime NOT NULL CONSTRAINT "DF__tarifario__fecha__3CBF0154"  DEFAULT (getdate()),
	"fecha_delete"       	datetime NULL,
	"version"            	int NOT NULL CONSTRAINT "DF__tarifario__versi__3DB3258D"  DEFAULT ((1)),
	"nombre"             	varchar(500) NULL,
	"cliente_pk"         	int NULL,
	"tipo_tarifario_enum"	tinyint NOT NULL CONSTRAINT "DF__tarifario__tipo___02133CD2"  DEFAULT ((5)),
	CONSTRAINT "pk_tarifario" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tarifario_excepcion"  ( 
	"id"                  	int IDENTITY NOT NULL,
	"tarifario_id"        	int NOT NULL,
	"porcentaje_negociado"	int NOT NULL,
	"fecha_insert"        	datetime NOT NULL CONSTRAINT "DF__tarifario__fecha__3EA749C6"  DEFAULT (getdate()),
	"fecha_update"        	datetime NOT NULL CONSTRAINT "DF__tarifario__fecha__3F9B6DFF"  DEFAULT (getdate()),
	"fecha_delete"        	datetime NULL,
	"version"             	int NOT NULL CONSTRAINT "DF__tarifario__versi__408F9238"  DEFAULT ((1)),
	CONSTRAINT "pk_tarifario_excepcion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."tarifas_contratos_cafesalud"  ( 
	"cttIDContrato"         	varchar(150) NULL,
	"cttIDTipTarifa"        	varchar(150) NULL,
	"cttNivelAtencionTT"    	varchar(150) NULL,
	"cttPorcentajeDescuento"	varchar(150) NULL,
	"cttIDTipMinuta"        	varchar(150) NULL,
	"cttEstContratoTarifa"  	varchar(150) NULL,
	"cttUsuario"            	varchar(150) NULL,
	"cttFchAccion"          	varchar(150) NULL,
	"cttTipAccionTT"        	varchar(150) NULL,
	"cttFchVigencia"        	varchar(150) NULL,
	"cttMontoFijo"          	varchar(150) NULL,
	"cttUnidadTiempo,,"     	varchar(150) NULL 
	)
GO
CREATE TABLE "auxiliar"."tarifas_contratos_cafesaludsub"  ( 
	"cttIDContrato"         	varchar(150) NULL,
	"cttIDTipTarifa"        	varchar(150) NULL,
	"cttNivelAtencionTT"    	varchar(150) NULL,
	"cttPorcentajeDescuento"	varchar(150) NULL,
	"cttIDTipMinuta"        	varchar(150) NULL,
	"cttEstContratoTarifa"  	varchar(150) NULL,
	"cttUsuario"            	varchar(150) NULL,
	"cttFchAccion"          	varchar(150) NULL,
	"cttTipAccionTT"        	varchar(150) NULL,
	"cttFchVigencia"        	varchar(150) NULL,
	"cttMontoFijo"          	varchar(150) NULL,
	"cttUnidadTiempo,,"     	varchar(150) NULL 
	)
GO
CREATE TABLE "auxiliar"."tarifas_contratos_saludcoop"  ( 
	"cttIDContrato"         	varchar(150) NULL,
	"cttIDTipTarifa"        	varchar(150) NULL,
	"cttNivelAtencionTT"    	varchar(150) NULL,
	"cttPorcentajeDescuento"	varchar(150) NULL,
	"cttIDTipMinuta"        	varchar(150) NULL,
	"cttEstContratoTarifa"  	varchar(150) NULL,
	"cttUsuario"            	varchar(150) NULL,
	"cttFchAccion"          	varchar(150) NULL,
	"cttTipAccionTT"        	varchar(150) NULL,
	"cttFchVigencia"        	varchar(150) NULL,
	"cttMontoFijo"          	varchar(150) NULL,
	"cttUnidadTiempo,,"     	varchar(150) NULL 
	)
GO
CREATE TABLE "ticket"."ticket_cabecera"  ( 
	"id"                            	int IDENTITY NOT NULL,
	"autorizacion_id"               	int NOT NULL,
	"numero_autorizacion"           	int NOT NULL,
	"cantidad_de_entregas"          	int NOT NULL CONSTRAINT "DF__ticket_ca__canti__1C5D1EBA"  DEFAULT ((1)),
	"numero_de_entrega"             	int NOT NULL CONSTRAINT "DF__ticket_ca__numer__1D5142F3"  DEFAULT ((1)),
	"eps"                           	varchar(100) NOT NULL,
	"nombre_del_paciente"           	varchar(100) NOT NULL,
	"tipo_afiliado"                 	varchar(25) NOT NULL,
	"tipo_de_identificacion"        	varchar(50) NOT NULL,
	"numero_identificacion"         	varchar(25) NOT NULL,
	"edad"                          	int NOT NULL,
	"nivel"                         	varchar(25) NOT NULL,
	"plan_afiliado"                 	varchar(25) NOT NULL,
	"ips_primaria"                  	varchar(100) NOT NULL,
	"entidad_solicitante"           	varchar(100) NOT NULL,
	"fecha"                         	date NOT NULL,
	"usuario_transcriptor"          	varchar(100) NOT NULL,
	"causa_externa"                 	varchar(100) NULL,
	"entidad_recobro"               	varchar(100) NULL,
	"origen"                        	varchar(50) NOT NULL,
	"diagnostico_principal"         	varchar(500) NOT NULL,
	"diagnosticos_secundarios"      	varchar(50) NULL,
	"pago_compartido_eps"           	varchar(25) NOT NULL,
	"pago_compartido_usuario"       	varchar(25) NOT NULL,
	"copago_porcentaje"             	varchar(25) NOT NULL,
	"copago_valor"                  	varchar(25) NOT NULL,
	"cuota_moderadora"              	varchar(25) NOT NULL,
	"descuento_capitacion_ips"      	varchar(25) NOT NULL,
	"institucion_remitida_nombre"   	varchar(100) NOT NULL,
	"institucion_remitida_direccion"	varchar(100) NOT NULL,
	"institucion_remitida_telefono" 	varchar(25) NOT NULL,
	"firma_medico"                  	varchar(50) NOT NULL,
	"nombre_completo_medico"        	varchar(100) NOT NULL,
	"registro_medico"               	varchar(25) NOT NULL,
	"numero_solicitud"              	int NOT NULL,
	"tipo_ppm"                      	varchar(10) NOT NULL,
	"vigencia"                      	varchar(20) NOT NULL CONSTRAINT "DF__ticket_ca__vigen__68536ACF"  DEFAULT ('eliminar default'),
	"fecha_impresion"               	datetime NULL,
	"cantidad_copias"               	smallint NOT NULL CONSTRAINT "DF__ticket_ca__canti__69478F08"  DEFAULT ((0)),
	"tipo_servicio"                 	smallint NOT NULL,
	CONSTRAINT "pk_ticket_cabecera" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "ticket"."ticket_item"  ( 
	"id"                	int IDENTITY NOT NULL,
	"ticket_cabecera_id"	int NOT NULL,
	"codigo"            	varchar(25) NOT NULL,
	"descripcion"       	varchar(500) NOT NULL,
	"cantidad"          	int NOT NULL,
	"finalidad"         	varchar(100) NOT NULL,
	"causa_externa"     	varchar(100) NULL,
	"lateralidad"       	varchar(100) NULL,
	"observaciones"     	varchar(500) NOT NULL,
	"tipo_catastrofico" 	varchar(100) NULL,
	CONSTRAINT "pk_ticket_item" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_afiliado"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_afil__fecha__4183B671"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_afil__fecha__4277DAAA"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_afil__versi__436BFEE3"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_afiliado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_catastrofico"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_cata__fecha__4460231C"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_cata__fecha__45544755"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_cata__versi__46486B8E"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_catastrofico" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_diagnostico"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_diag__fecha__4A18FC72"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_diag__fecha__4B0D20AB"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_diag__versi__4C0144E4"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_diagnostico" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_documento_soporte"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_docu__fecha__4CF5691D"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_docu__fecha__4DE98D56"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_docu__versi__4EDDB18F"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_documento_soporte" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_funcionalidad"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_func__fecha__4FD1D5C8"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_func__fecha__50C5FA01"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_func__versi__51BA1E3A"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_funcionalidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_identificacion"  ( 
	"id"                	int IDENTITY NOT NULL,
	"codigo"            	varchar(50) NULL,
	"descripcion"       	varchar(100) NOT NULL,
	"es_alfanumerico"   	smallint NOT NULL,
	"max_length"        	int NOT NULL,
	"min_length"        	int NOT NULL,
	"cliente_pk"        	int NULL,
	"fecha_insert"      	datetime NOT NULL CONSTRAINT "DF__tipo_iden__fecha__52AE4273"  DEFAULT (getdate()),
	"fecha_update"      	datetime NOT NULL CONSTRAINT "DF__tipo_iden__fecha__53A266AC"  DEFAULT (getdate()),
	"fecha_delete"      	datetime NULL,
	"version"           	int NOT NULL CONSTRAINT "DF__tipo_iden__versi__54968AE5"  DEFAULT ((1)),
	"aplica_afiliado"   	smallint NULL,
	"aplica_ips"        	smallint NULL,
	"aplica_profesional"	smallint NULL,
	CONSTRAINT "pk_tipo_identificacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_ips"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_ips__fecha___558AAF1E"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_ips__fecha___567ED357"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_ips__versio__5772F790"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_ips" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_minuta"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_minu__fecha__58671BC9"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_minu__fecha__595B4002"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_minu__versi__5A4F643B"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_minuta" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_operacion_excepcion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_oper__fecha__5B438874"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_oper__fecha__5C37ACAD"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_oper__versi__5D2BD0E6"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_operacion_excepcion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_origen_procedimiento"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_orig__fecha__5E1FF51F"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_orig__fecha__5F141958"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_orig__versi__60083D91"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_origen_procedimiento" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_perioricidad"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_peri__fecha__60FC61CA"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_peri__fecha__61F08603"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_peri__versi__62E4AA3C"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_perioricidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_plan_afiliado"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_plan__fecha__63D8CE75"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_plan__fecha__64CCF2AE"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_plan__versi__65C116E7"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_plan_afiliado" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_plan_contrato"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_plan__fecha__66B53B20"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_plan__fecha__67A95F59"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_plan__versi__689D8392"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_plan_contrato" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_ppm"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_ppm__fecha___6991A7CB"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_ppm__fecha___6A85CC04"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_ppm__versio__6B79F03D"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_ppm" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_prestacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_pres__fecha__6C6E1476"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_pres__fecha__6D6238AF"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_pres__versi__6E565CE8"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_prestacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_producto"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_prod__fecha__6F4A8121"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_prod__fecha__703EA55A"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_prod__versi__7132C993"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_producto" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_profesional"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_prof__fecha__7226EDCC"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_prof__fecha__731B1205"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_prof__versi__740F363E"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_profesional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_servicio"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_serv__fecha__75035A77"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_serv__fecha__75F77EB0"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_serv__versi__76EBA2E9"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_servicio" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_tarifario"  ( 
	"codigo"      	char(1) NOT NULL,
	"descripcion" 	varchar(30) NOT NULL,
	"fecha_delete"	datetime NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_tarifa_fecha_insert"  DEFAULT (getdate()),
	"fecha_update"	datetime NULL,
	"id"          	tinyint IDENTITY NOT NULL,
	"version"     	smallint NOT NULL CONSTRAINT "DF__tipo_tarifa_fecha_update"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_tarifario" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."tipo_tecnologia"  ( 
	"id"          	int NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_delete"	datetime NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__tipo_tecn__fecha__7D2E8C24"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__tipo_tecn__fecha__7E22B05D"  DEFAULT (getdate()),
	"version"     	int NOT NULL CONSTRAINT "DF__tipo_tecn__versi__7F16D496"  DEFAULT ((1)),
	CONSTRAINT "pk_tipo_tecnologia" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."totalizacion_copago_afiliado"  ( 
	"id"           	int IDENTITY NOT NULL,
	"afiliado_id"  	int NOT NULL,
	"anio"         	int NOT NULL,
	"total_copagos"	numeric(19,2) NOT NULL,
	CONSTRAINT "PK__totaliza__3213E83FD5AFCD31" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "auxiliar"."totalizacion_copago_afiliado_diagnostico"  ( 
	"id"            	int IDENTITY NOT NULL,
	"afiliado_id"   	int NOT NULL,
	"anio"          	int NOT NULL,
	"diagnostico_id"	int NOT NULL,
	"total_copagos" 	numeric(19,2) NOT NULL,
	CONSTRAINT "PK__totaliza__3213E83F6058B50E" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."ubicacion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__ubicacion__fecha__77DFC722"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__ubicacion__fecha__78D3EB5B"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__ubicacion__versi__79C80F94"  DEFAULT ((1)),
	CONSTRAINT "pk_ubicacion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."unidad_funcional"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__unidad_fu__fecha__7ABC33CD"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__unidad_fu__fecha__7BB05806"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__unidad_fu__versi__7CA47C3F"  DEFAULT ((1)),
	CONSTRAINT "pk_unidad_funcional" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."unidad_tiempo"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__unidad_ti__fecha__7D98A078"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__unidad_ti__fecha__7E8CC4B1"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__unidad_ti__versi__7F80E8EA"  DEFAULT ((1)),
	CONSTRAINT "pk_unidad_tiempo" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."user"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"username"                   	varchar(25) NOT NULL,
	"password"                   	varchar(32) NOT NULL,
	"name"                       	varchar(50) NOT NULL,
	"email"                      	varchar(80) NOT NULL,
	"enabled"                    	bit NOT NULL,
	"credentials_expiration_date"	datetime NOT NULL,
	"failed_logins"              	int NOT NULL,
	"inserted"                   	datetime NOT NULL,
	"deleted"                    	bit NOT NULL,
	CONSTRAINT "pk_user" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "security"."user_role"  ( 
	"user_id"	int NOT NULL,
	"role_id"	int NOT NULL,
	CONSTRAINT "pk_user_role" PRIMARY KEY CLUSTERED("user_id","role_id")
)
GO
CREATE TABLE "security"."usuario_entidad"  ( 
	"id"                         	int IDENTITY NOT NULL,
	"ldf_id"                     	int NULL,
	"profesional_especialidad_id"	int NULL,
	"sede_ips_id"                	int NULL,
	"usuario_id"                 	int NULL,
	CONSTRAINT "pk_usuario_entidad" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."via_administracion"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__via_admin__fecha__00750D23"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__via_admin__fecha__0169315C"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__via_admin__versi__025D5595"  DEFAULT ((1)),
	CONSTRAINT "pk_via_administracion" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "maestros"."vigencia"  ( 
	"dias_vigencia"     	int NULL,
	"fecha_delete"      	datetime NULL,
	"fecha_insert"      	datetime NOT NULL CONSTRAINT "DF__vigencia__fecha___09B45E9A"  DEFAULT (getdate()),
	"fecha_update"      	datetime NOT NULL CONSTRAINT "DF__vigencia__fecha___0AA882D3"  DEFAULT (getdate()),
	"horas_vigencia"    	int NULL,
	"id"                	int NOT NULL,
	"tipo_ppm_id"       	int NOT NULL,
	"tipo_tecnologia_id"	int NOT NULL,
	CONSTRAINT "pk_vigencia" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE TABLE "transaccional"."xxx1"  ( 
	"s"	int NULL 
	)
GO
CREATE TABLE "transaccional"."xxx2"  ( 
	"s"	int NULL 
	)
GO
CREATE TABLE "maestros"."zona"  ( 
	"id"          	int IDENTITY NOT NULL,
	"codigo"      	varchar(50) NULL,
	"descripcion" 	varchar(500) NOT NULL,
	"cliente_pk"  	int NULL,
	"fecha_insert"	datetime NOT NULL CONSTRAINT "DF__zona__fecha_inse__035179CE"  DEFAULT (getdate()),
	"fecha_update"	datetime NOT NULL CONSTRAINT "DF__zona__fecha_upda__04459E07"  DEFAULT (getdate()),
	"fecha_delete"	datetime NULL,
	"version"     	int NOT NULL CONSTRAINT "DF__zona__version__0539C240"  DEFAULT ((1)),
	CONSTRAINT "pk_zona" PRIMARY KEY CLUSTERED("id")
)
GO
CREATE VIEW "vista"."afiliado_programa_item_eximido_view"
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
CREATE VIEW "vista"."tarifa_medicamento_view"
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
CREATE VIEW "vista"."tarifa_procedimiento_view"
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
CREATE VIEW "vista"."ubicacion_sede_ips_efector_procedimiento"
AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
		eps.id as eps_id, sede.id as sede_ips_id, sede.nombre as sede_ips_nombre, muni.id as municipio_id, 
        depto.id as departamento_id,
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
    INNER JOIN maestros.departamento depto ON muni.departamento_id = depto.id
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
CREATE VIEW "vista"."ubicacion_sede_ips_proveedor_insumo"

AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
		eps.id as eps_id, sede.id as sede_ips_id, sede.nombre as sede_ips_nombre, muni.id as municipio_id, 
		divsec.id as division_seccional_id, reg.id as regional_id,
        depto.id as departamento_id,
		ins.id as insumo_id, servcont.servicio_id as servicio_id, espccont.especialidad_id as especialidad_id

FROM maestros.sede_ips sede
    INNER JOIN maestros.ips ips ON sede.ips_id = ips.id
	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id
	INNER JOIN maestros.sede_ips_division_seccional sids ON sede.id = sids.sede_ips_id
    INNER JOIN maestros.division_seccional divsec ON sids.division_seccional_id = divsec.id
	INNER JOIN maestros.regional reg ON sede.regional_id = reg.id 
	INNER JOIN maestros.municipio muni ON sede.municipio_id = muni.id
    INNER JOIN maestros.departamento depto ON muni.departamento_id = depto.id
	INNER JOIN maestros.contrato cont ON sede.id = cont.sede_ips_id
	INNER JOIN maestros.servicio_contratado servcont ON cont.id = servcont.contrato_id
	INNER JOIN maestros.especialidad_contratada espccont ON servcont.id = espccont.servicio_contratado_id
	INNER JOIN maestros.insumo_contratado inscont ON espccont.id = inscont.especialidad_contratada_id
	INNER JOIN maestros.insumo ins ON inscont.insumo_id = ins.id

WHERE sede.estado_ips_id <> 2
	AND cont.estado_contrato_id = 4
	AND inscont.estado_insumo_contratado_id = 4
	AND ins.estado_insumo_id = 1
    AND (((ins.es_epsifarma = 1 OR ins.suministra_medicarte = 0) AND ips.tipo_identificacion_id = 1 AND ips.numero_identificacion = '999999999')
         OR
         (ins.suministra_medicarte = 1 AND ips.tipo_identificacion_id = 1 AND ips.numero_identificacion = '900219866')
        )
    AND sede.fecha_delete IS NULL
	AND eps.fecha_delete IS NULL
	AND reg.fecha_delete IS NULL
	AND muni.fecha_delete IS NULL
	AND cont.fecha_delete IS NULL
	AND servcont.fecha_delete IS NULL
	AND espccont.fecha_delete IS NULL
	AND inscont.fecha_delete IS NULL
	AND ins.fecha_delete IS NULL
	AND cont.fecha_inicio_contrato <= GETDATE()
	AND cont.fecha_fin_contrato >= GETDATE()
	AND servcont.fecha_vencimiento >= GETDATE()
	AND espccont.fecha_vencimiento >= GETDATE()
	AND inscont.fecha_vencimiento >= GETDATE()
GO
CREATE VIEW "vista"."ubicacion_sede_ips_proveedor_medicamento"
AS

SELECT ABS(CHECKSUM(NewId())) % 9999999999999 as id, -- Se usa solo para poder mapear la entidad con Hibernate
		eps.id as eps_id, sede.id as sede_ips_id, sede.nombre as sede_ips_nombre, muni.id as municipio_id, 
		divsec.id as division_seccional_id, reg.id as regional_id,
        depto.id as departamento_id,
		med.id as medicamento_id, servcont.servicio_id as servicio_id, espccont.especialidad_id as especialidad_id

FROM maestros.sede_ips sede
    INNER JOIN maestros.ips ips ON sede.ips_id = ips.id
	INNER JOIN maestros.eps eps ON sede.eps_id = eps.id
	INNER JOIN maestros.sede_ips_division_seccional sids ON sede.id = sids.sede_ips_id
    INNER JOIN maestros.division_seccional divsec ON sids.division_seccional_id = divsec.id
	INNER JOIN maestros.regional reg ON sede.regional_id = reg.id 
	INNER JOIN maestros.municipio muni ON sede.municipio_id = muni.id
    INNER JOIN maestros.departamento depto ON muni.departamento_id = depto.id
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
CREATE PROCEDURE "transaccional"."get_numero_solicitud" @numero_solicitud int output
AS  
   set @numero_solicitud = next value for transaccional.seq_numero_solicitud;
   return
GO
CREATE PROCEDURE "transaccional"."get_pba" @numero_solicitud bigint output
AS  
   set @numero_solicitud = next value for transaccional.seq_pba;
   return
GO
CREATE FUNCTION "transaccional"."fn_get_pba" ()
returns int
as
begin 
	DECLARE @RC int
	DECLARE @numero_solicitud bigint
	EXECUTE @RC = [db_saludcoop].[transaccional].[get_pba] 
		@numero_solicitud OUTPUT
    return @numero_solicitud 
end     

GO
CREATE UNIQUE NONCLUSTERED INDEX "uq_totalizacion_copago_afiliado_anio_afiliado_id"
	ON "auxiliar"."totalizacion_copago_afiliado"("anio", "afiliado_id")
GO
CREATE UNIQUE NONCLUSTERED INDEX "uq_totalizacion_copago_afiliado_anio_afiliado_id_diagnostico_id"
	ON "auxiliar"."totalizacion_copago_afiliado_diagnostico"("anio", "afiliado_id", "diagnostico_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_departamento_seccional"
	ON "maestros"."afiliado"("departamento_seccional_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_eps"
	ON "maestros"."afiliado"("eps_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_estado_afiliacion"
	ON "maestros"."afiliado"("estado_afiliacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_estado_civil"
	ON "maestros"."afiliado"("estado_civil_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_genero"
	ON "maestros"."afiliado"("genero_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_grupo_poblacional"
	ON "maestros"."afiliado"("grupo_poblacional_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_localidad"
	ON "maestros"."afiliado"("localidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_municipio"
	ON "maestros"."afiliado"("municipio_residencia_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_nivel_ibc"
	ON "maestros"."afiliado"("nivel_ibc_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_nivel_sisben"
	ON "maestros"."afiliado"("nivel_sisben_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_numero_identificacion"
	ON "maestros"."afiliado"("numero_identificacion")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_primer_apellido"
	ON "maestros"."afiliado"("primer_apellido")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_primer_nombre"
	ON "maestros"."afiliado"("primer_nombre")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_programa_afiliado"
	ON "maestros"."afiliado_programa"("afiliado_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_programa_programa"
	ON "maestros"."afiliado_programa"("programa_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_sede_ips_afiliacion"
	ON "maestros"."afiliado"("sede_ips_afiliacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_segundo_apellido"
	ON "maestros"."afiliado"("segundo_apellido")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_segundo_nombre"
	ON "maestros"."afiliado"("segundo_nombre")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_tipo_afiliado"
	ON "maestros"."afiliado"("tipo_afiliado_id")
GO
CREATE NONCLUSTERED INDEX "ix_afiliado_tipo_identificacion"
	ON "maestros"."afiliado"("tipo_identificacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_eps"
	ON "maestros"."contrato"("eps_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_estado_contrato"
	ON "maestros"."contrato"("estado_contrato_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_fecha_aprobado"
	ON "maestros"."contrato"("fecha_aprobado")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_fecha_fin_contrato"
	ON "maestros"."contrato"("fecha_fin_contrato")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_fecha_inicio_contrato"
	ON "maestros"."contrato"("fecha_inicio_contrato")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_sede_ips"
	ON "maestros"."contrato"("sede_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_tarifario_contrato"
	ON "maestros"."contrato_tarifario"("contrato_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_tarifario_estado_contrato_tarifario"
	ON "maestros"."contrato_tarifario"("estado_contrato_tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_tarifario_tarifario"
	ON "maestros"."contrato_tarifario"("tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_tarifario_tipo_minuta"
	ON "maestros"."contrato_tarifario"("tipo_minuta_id")
GO
CREATE NONCLUSTERED INDEX "ix_contrato_tipo_plan_contrato"
	ON "maestros"."contrato"("tipo_plan_contrato_id")
GO
CREATE NONCLUSTERED INDEX "ix_departamento_codigo"
	ON "maestros"."departamento"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_departamento_descripcion"
	ON "maestros"."departamento"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_departamento_regional_departamento"
	ON "maestros"."departamento_regional"("departamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_departamento_regional_regional"
	ON "maestros"."departamento_regional"("regional_id")
GO
CREATE NONCLUSTERED INDEX "ix_diagnostico_codigo"
	ON "maestros"."diagnostico"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_diagnostico_descripcion"
	ON "maestros"."diagnostico"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_diagnostico_medicamento_diagnostico"
	ON "maestros"."diagnostico_medicamento"("diagnostico_id")
GO
CREATE NONCLUSTERED INDEX "ix_diagnostico_medicamento_medicamento"
	ON "maestros"."diagnostico_medicamento"("medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_diagnostico_procedimiento_diagnostico"
	ON "maestros"."diagnostico_procedimiento"("diagnostico_id")
GO
CREATE NONCLUSTERED INDEX "ix_diagnostico_procedimiento_procedimiento"
	ON "maestros"."diagnostico_procedimiento"("procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_diagnostico_tipo_diagnostico"
	ON "maestros"."diagnostico"("tipo_diagnostico_id")
GO
CREATE NONCLUSTERED INDEX "ix_director_medico_regional_regional"
	ON "maestros"."director_medico_regional"("regional_id")
GO
CREATE NONCLUSTERED INDEX "ix_division_seccional_codigo"
	ON "maestros"."division_seccional"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_division_seccional_descripcion"
	ON "maestros"."division_seccional"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_eps_razon_social"
	ON "maestros"."eps"("razon_social")
GO
CREATE NONCLUSTERED INDEX "ix_eps_tipo_identificacion"
	ON "maestros"."eps"("tipo_identificacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_contratada_especialidad"
	ON "maestros"."especialidad_contratada"("especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_contratada_estado_especialidad_contratada"
	ON "maestros"."especialidad_contratada"("estado_especialidad_contratada_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_contratada_servicio_contratado"
	ON "maestros"."especialidad_contratada"("servicio_contratado_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_contratada_tarifario_excepcion"
	ON "maestros"."especialidad_contratada"("tarifario_excepcion_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_contratada_tipo_minuta"
	ON "maestros"."especialidad_contratada"("tipo_minuta_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_contratada_unidad_tiempo"
	ON "maestros"."especialidad_contratada"("unidad_tiempo_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_insumo_especialidad"
	ON "maestros"."especialidad_insumo"("especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_insumo_insumo"
	ON "maestros"."especialidad_insumo"("insumo_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_medicamento_especialidad"
	ON "maestros"."especialidad_medicamento"("especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_medicamento_medicamento"
	ON "maestros"."especialidad_medicamento"("medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_procedimiento_especialidad"
	ON "maestros"."especialidad_procedimiento"("especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_especialidad_procedimiento_procedimiento"
	ON "maestros"."especialidad_procedimiento"("procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_codigo"
	ON "maestros"."insumo"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_descripcion"
	ON "maestros"."insumo"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_estado_insumo"
	ON "maestros"."insumo"("estado_insumo_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_genero"
	ON "maestros"."insumo"("genero_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_homologo"
	ON "maestros"."insumo"("homologo_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_tarifa_insumo"
	ON "maestros"."insumo_tarifa"("insumo_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_tarifa_tarifario"
	ON "maestros"."insumo_tarifa"("tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_tipo_ppm"
	ON "maestros"."insumo"("tipo_ppm_id")
GO
CREATE NONCLUSTERED INDEX "ix_ips_estado_ips"
	ON "maestros"."ips"("estado_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_ips_numero_identificacion"
	ON "maestros"."ips"("numero_identificacion")
GO
CREATE NONCLUSTERED INDEX "ix_ips_tipo_identificacion"
	ON "maestros"."ips"("tipo_identificacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_ips_tipo_ips"
	ON "maestros"."ips"("tipo_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_localidad_municipio"
	ON "maestros"."localidad"("municipio_id")
GO
CREATE NONCLUSTERED INDEX "ix_localidad_regional"
	ON "maestros"."localidad"("regional_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_codigo"
	ON "maestros"."medicamento"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_contratado_especialidad_contratada"
	ON "maestros"."medicamento_contratado"("especialidad_contratada_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_contratado_estado_medicamento_contratado"
	ON "maestros"."medicamento_contratado"("estado_medicamento_contratado_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_contratado_medicamento"
	ON "maestros"."medicamento_contratado"("medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_contratado_tarifario"
	ON "maestros"."medicamento_contratado"("tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_contratado_tipo_minuta"
	ON "maestros"."medicamento_contratado"("tipo_minuta_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_contratado_unidad_tiempo"
	ON "maestros"."medicamento_contratado"("unidad_tiempo_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_descripcion"
	ON "maestros"."medicamento"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_estado_medicamento"
	ON "maestros"."medicamento"("estado_medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_genero"
	ON "maestros"."medicamento"("genero_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_homologo"
	ON "maestros"."medicamento"("homologo_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_tarifa_medicamento"
	ON "maestros"."medicamento_tarifa"("medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_tarifa_tarifario"
	ON "maestros"."medicamento_tarifa"("tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_tipo_ppm"
	ON "maestros"."medicamento"("tipo_ppm_id")
GO
CREATE NONCLUSTERED INDEX "ix_montos_copago_contributivo_nivel_ibc"
	ON "maestros"."montos_copago_contributivo"("nivel_ibc_id")
GO
CREATE NONCLUSTERED INDEX "ix_montos_copago_subsidiado_nivel_sisben"
	ON "maestros"."montos_copago_subsidiado"("nivelsisben_id")
GO
CREATE NONCLUSTERED INDEX "ix_municipio_departamento"
	ON "maestros"."municipio"("departamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_municipio_division_seccional_division_seccional"
	ON "maestros"."municipio_division_seccional"("division_seccional_id")
GO
CREATE NONCLUSTERED INDEX "ix_municipio_division_seccional_municipio"
	ON "maestros"."municipio_division_seccional"("municipio_id")
GO
CREATE NONCLUSTERED INDEX "ix_municipio_regional"
	ON "maestros"."municipio"("regional_id")
GO
CREATE NONCLUSTERED INDEX "ix_nombre_contrato"
	ON "maestros"."contrato"("nombre_contrato")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_codigo"
	ON "maestros"."procedimiento"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_contratado_especialidad_contratada"
	ON "maestros"."procedimiento_contratado"("especialidad_contratada_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_contratado_estado_procedimiento_contratado"
	ON "maestros"."procedimiento_contratado"("estado_procedimiento_contratado_id")
	INCLUDE ("especialidad_contratada_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_contratado_fecha_vencimiento"
	ON "maestros"."procedimiento_contratado"("fecha_vencimiento")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_contratado_procedimiento"
	ON "maestros"."procedimiento_contratado"("procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_contratado_tarifario"
	ON "maestros"."procedimiento_contratado"("tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_contratado_tipo_minuta"
	ON "maestros"."procedimiento_contratado"("tipo_minuta_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_contratado_unidad_tiempo"
	ON "maestros"."procedimiento_contratado"("unidad_tiempo_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_descripcion"
	ON "maestros"."procedimiento"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_genero"
	ON "maestros"."procedimiento"("genero_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_tarifa_procedimiento"
	ON "maestros"."procedimiento_tarifa"("procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_tarifa_tarifario"
	ON "maestros"."procedimiento_tarifa"("tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_tipo_ppm"
	ON "maestros"."procedimiento"("estado_procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_profecional_numero_identificacion"
	ON "maestros"."profesional"("numero_identificacion")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_division_seccional"
	ON "maestros"."profesional"("division_seccional_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_especialidad_especialidad"
	ON "maestros"."profesional_especialidad"("especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_especialidad_estado_profesional"
	ON "maestros"."profesional_especialidad"("estado_profesional_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_especialidad_nivel_autorizacion"
	ON "maestros"."profesional_especialidad"("nivel_autorizacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_especialidad_profesional"
	ON "maestros"."profesional_especialidad"("profesional_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_especialidad_x_sede_ips_profesional_especialidad"
	ON "maestros"."profesional_especialidad_x_sede_ips"("profesional_especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_especialidad_x_sede_ips_sede_ips"
	ON "maestros"."profesional_especialidad_x_sede_ips"("sede_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_identificacion_profesional"
	ON "maestros"."profesional"("identificacion_profesional_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_municipio"
	ON "maestros"."profesional"("municipio_id")
GO
CREATE NONCLUSTERED INDEX "ix_profesional_tipo_profesional"
	ON "maestros"."profesional"("tipo_profesional_id")
GO
CREATE NONCLUSTERED INDEX "ix_regional_codigo"
	ON "maestros"."regional"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_regional_descripcion"
	ON "maestros"."regional"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_regional_division_seccional"
	ON "maestros"."regional"("division_seccional_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_division_seccional_division_seccional"
	ON "maestros"."sede_ips_division_seccional"("division_seccional_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_division_seccional_sede_ips"
	ON "maestros"."sede_ips_division_seccional"("sede_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_eps"
	ON "maestros"."sede_ips"("eps_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_estado_ips"
	ON "maestros"."sede_ips"("estado_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_ips"
	ON "maestros"."sede_ips"("ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_municipio"
	ON "maestros"."sede_ips"("municipio_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_regimen_juridico"
	ON "maestros"."sede_ips"("regimen_juridico_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_regimen_tributario"
	ON "maestros"."sede_ips"("regimen_tributario_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_regional"
	ON "maestros"."sede_ips"("regional_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_tipo_ips"
	ON "maestros"."sede_ips"("tipo_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_tipo_servicio"
	ON "maestros"."sede_ips"("tipo_servicio_id")
GO
CREATE NONCLUSTERED INDEX "ix_sede_ips_ubicacion"
	ON "maestros"."sede_ips"("ubicacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_contratado_contrato"
	ON "maestros"."servicio_contratado"("contrato_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_contratado_estado_item_contratado"
	ON "maestros"."servicio_contratado"("estado_servicio_contratado_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_contratado_servicio"
	ON "maestros"."servicio_contratado"("servicio_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_contratado_tarifario_excepcion"
	ON "maestros"."servicio_contratado"("tarifario_excepcion_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_contratado_tipo_minuta"
	ON "maestros"."servicio_contratado"("tipo_minuta_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_contratado_unidad_tiempo"
	ON "maestros"."servicio_contratado"("unidad_tiempo_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_especialidad_especialidad"
	ON "maestros"."servicio_especialidad"("especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_especialidad_servicio"
	ON "maestros"."servicio_especialidad"("servicio_id")
GO
CREATE NONCLUSTERED INDEX "ix_servicio_unidad_funcional"
	ON "maestros"."servicio"("unidad_funcional_id")
GO
CREATE NONCLUSTERED INDEX "ix_tarifario_excepcion_tarifario"
	ON "maestros"."tarifario_excepcion"("tarifario_id")
GO
CREATE NONCLUSTERED INDEX "ix_tipo_identificacion_codigo"
	ON "maestros"."tipo_identificacion"("codigo")
GO
CREATE NONCLUSTERED INDEX "ix_tipo_identificacion_descripcion"
	ON "maestros"."tipo_identificacion"("descripcion")
GO
CREATE NONCLUSTERED INDEX "ix_tipo_minuta_codigo"
	ON "maestros"."tipo_minuta"("codigo")
	INCLUDE ("id")
GO
CREATE NONCLUSTERED INDEX "ix_recovery_token_user"
	ON "security"."recovery_token"("user_id")
GO
CREATE NONCLUSTERED INDEX "ix_role_authority_authority"
	ON "security"."role_authority"("authority_id")
GO
CREATE NONCLUSTERED INDEX "ix_role_authority_role"
	ON "security"."role_authority"("role_id")
GO
CREATE NONCLUSTERED INDEX "ix_user_role_role"
	ON "security"."user_role"("role_id")
GO
CREATE NONCLUSTERED INDEX "ix_user_role_user"
	ON "security"."user_role"("user_id")
GO
CREATE NONCLUSTERED INDEX "ix_usuario_entidad_linea_de_frente"
	ON "security"."usuario_entidad"("ldf_id")
GO
CREATE NONCLUSTERED INDEX "ix_usuario_entidad_profesional_especialidad"
	ON "security"."usuario_entidad"("profesional_especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_usuario_entidad_sede_ips"
	ON "security"."usuario_entidad"("sede_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_usuario_entidad_user"
	ON "security"."usuario_entidad"("usuario_id")
GO
CREATE NONCLUSTERED INDEX "ix_autorizacion_concepto_nacional"
	ON "transaccional"."autorizacion"("concepto_nacional_id")
GO
CREATE NONCLUSTERED INDEX "ix_autorizacion_concepto_regional"
	ON "transaccional"."autorizacion"("concepto_regional_id")
GO
CREATE NONCLUSTERED INDEX "ix_autorizacion_entidad_recobro"
	ON "transaccional"."autorizacion"("entidad_recobro_id")
GO
CREATE NONCLUSTERED INDEX "ix_autorizacion_estado_autorizacion"
	ON "transaccional"."autorizacion"("estado_autorizacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_autorizacion_informacion_adicional"
	ON "transaccional"."autorizacion"("informacion_adicional_id")
GO
CREATE NONCLUSTERED INDEX "ix_autorizacion_rol_destino"
	ON "transaccional"."autorizacion"("rol_destino_id")
GO
CREATE NONCLUSTERED INDEX "ix_autorizacion_sede_ips_efectora"
	ON "transaccional"."autorizacion"("sede_ips_efectora_id")
GO
CREATE NONCLUSTERED INDEX "ix_con_autorizacion_cri_negacion_concepto_autorizacion"
	ON "transaccional"."con_autorizacion_cri_negacion"("concepto_autorizacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_con_autorizacion_cri_negacion_criterio_negacion"
	ON "transaccional"."con_autorizacion_cri_negacion"("criterio_negacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_concepto_autorizacion_causal_anulacion"
	ON "transaccional"."concepto_autorizacion"("causal_anulacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_concepto_autorizacion_causal_devolucion"
	ON "transaccional"."concepto_autorizacion"("causal_devolucion_id")
GO
CREATE NONCLUSTERED INDEX "ix_concepto_autorizacion_lateralidad"
	ON "transaccional"."concepto_autorizacion"("lateralidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_consumo_profesional_efector"
	ON "transaccional"."consumo"("profesional_efector_id")
GO
CREATE NONCLUSTERED INDEX "ix_consumo_solicitud_item"
	ON "transaccional"."consumo"("solicitud_item_id")
GO
CREATE NONCLUSTERED INDEX "ix_documento_soporte_solicitud"
	ON "transaccional"."documento_soporte"("solicitud_id")
GO
CREATE NONCLUSTERED INDEX "ix_documento_soporte_tipo_doc_soporte"
	ON "transaccional"."documento_soporte"("tipo_doc_soporte_id")
GO
CREATE NONCLUSTERED INDEX "ix_entrega_solicitud_medicamento"
	ON "transaccional"."entrega"("solicitud_medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_formula_item_medicamento_via_administracion"
	ON "transaccional"."formula_item_medicamento"("via_administracion_id")
GO
CREATE NONCLUSTERED INDEX "ix_formula_item_procedimiento_lateralidad"
	ON "transaccional"."formula_item_procedimiento"("lateralidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_formula_item_procedimiento_objetivo_procedimiento"
	ON "transaccional"."formula_item_procedimiento"("objetivo_procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_formula_item_procedimiento_origen_repeticion"
	ON "transaccional"."formula_item_procedimiento"("origen_repeticion_id")
GO
CREATE NONCLUSTERED INDEX "ix_formula_item_procedimiento_tipo_prestacion"
	ON "transaccional"."formula_item_procedimiento"("tipo_prestacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_insumo_causa_externa"
	ON "transaccional"."formulario_ctc_insumo"("causa_externa_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_insumo_finalidad"
	ON "transaccional"."formulario_ctc_insumo"("finalidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_insumo_insumo_homologo"
	ON "transaccional"."formulario_ctc_insumo"("insumo_homologo_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_insumo_tipo_catastrofico"
	ON "transaccional"."formulario_ctc_insumo"("tipo_catastrofico_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_medicamento_causa_externa"
	ON "transaccional"."formulario_ctc_medicamento"("causa_externa_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_medicamento_finalidad"
	ON "transaccional"."formulario_ctc_medicamento"("finalidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_medicamento_medicamento_homologo"
	ON "transaccional"."formulario_ctc_medicamento"("medicamento_homologo_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_medicamento_tipo_catastrofico"
	ON "transaccional"."formulario_ctc_medicamento"("tipo_catastrofico_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_procedimiento_causa_externa"
	ON "transaccional"."formulario_ctc_procedimiento"("causa_externa_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_procedimiento_finalidad"
	ON "transaccional"."formulario_ctc_procedimiento"("finalidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_formulario_ctc_procedimiento_tipo_catastrofico"
	ON "transaccional"."formulario_ctc_procedimiento"("tipo_catastrofico_id")
GO
CREATE NONCLUSTERED INDEX "ix_informacion_adicional_causa_externa"
	ON "transaccional"."informacion_adicional"("causa_externa_id")
GO
CREATE NONCLUSTERED INDEX "ix_informacion_adicional_finalidad"
	ON "transaccional"."informacion_adicional"("finalidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_informacion_adicional_tipo_catastrofico"
	ON "transaccional"."informacion_adicional"("tipo_catastrofico_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_pos_previo_insumo"
	ON "transaccional"."insumo_pos_previo"("insumo_id")
GO
CREATE NONCLUSTERED INDEX "ix_insumo_pos_previo_respuesta_clinica_observada"
	ON "transaccional"."insumo_pos_previo"("respuesta_clinica_observada_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_pos_previo_medicamento"
	ON "transaccional"."medicamento_pos_previo"("medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_medicamento_pos_previo_respuesta_clinica_observada"
	ON "transaccional"."medicamento_pos_previo"("respuesta_clinica_observada_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_homologo_objetivo_procedimiento"
	ON "transaccional"."procedimiento_homologo"("objetivo_procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_homologo_procedimiento"
	ON "transaccional"."procedimiento_homologo"("procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_pos_previo_procedimiento"
	ON "transaccional"."procedimiento_pos_previo"("procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_procedimiento_pos_previo_respuesta_clinica_observada"
	ON "transaccional"."procedimiento_pos_previo"("respuesta_clinica_observada_id")
GO
CREATE NONCLUSTERED INDEX "ix_resumen_diagnostico_diagnostico"
	ON "transaccional"."resumen_diagnostico"("diagnostico_id")
GO
CREATE NONCLUSTERED INDEX "ix_resumen_diagnostico_resumen"
	ON "transaccional"."resumen_diagnostico"("resumen_id")
GO
CREATE NONCLUSTERED INDEX "ix_resumen_historia_clinica_causa_externa"
	ON "transaccional"."resumen_historia_clinica"("causa_externa_id")
GO
CREATE NONCLUSTERED INDEX "ix_resumen_historia_clinica_tipo_catastrofico"
	ON "transaccional"."resumen_historia_clinica"("tipo_catastrofico_id")
GO
CREATE NONCLUSTERED INDEX "ix_role_estado_estados_estado_autorizacion"
	ON "transaccional"."role_estado_estados"("estado_autorizacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_role_estado_estados_role_estado"
	ON "transaccional"."role_estado_estados"("role_estado_id")
GO
CREATE NONCLUSTERED INDEX "ix_role_estado_visible_estado_visible"
	ON "transaccional"."role_estado_visible"("estado_visible_id")
GO
CREATE NONCLUSTERED INDEX "ix_role_estado_visible_role"
	ON "transaccional"."role_estado_visible"("role_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_afiliado"
	ON "transaccional"."solicitud"("afiliado_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_autorizacion"
	ON "transaccional"."solicitud_item"("autorizacion_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_diagnostico_diagnostico"
	ON "transaccional"."solicitud_diagnostico"("diagnostico_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_diagnostico_solicitud"
	ON "transaccional"."solicitud_diagnostico"("solicitud_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_fecha_creacion"
	ON "transaccional"."solicitud"("fecha_creacion")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_insumo_insumo"
	ON "transaccional"."solicitud_insumo"("insumo_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_item_diagnostico"
	ON "transaccional"."solicitud_item"("diagnostico_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_item_solicitud"
	ON "transaccional"."solicitud_item"("solicitud_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_item_tipo_ppm"
	ON "transaccional"."solicitud_item"("tipo_ppm_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_item_tipo_servicio"
	ON "transaccional"."solicitud_item"("tipo_servicio_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_medicamento_medicamento"
	ON "transaccional"."solicitud_medicamento"("medicamento_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_parcial_sede_ips"
	ON "transaccional"."solicitud_parcial"("sede_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_parcial_tipo_identificacion_afiliado"
	ON "transaccional"."solicitud_parcial"("tipo_identificacion_afiliado_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_parcial_user"
	ON "transaccional"."solicitud_parcial"("user_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_primera_formulacion_anio"
	ON "transaccional"."solicitud"("primera_formulacion_anio")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_procedimiento_especialidad"
	ON "transaccional"."solicitud_procedimiento"("especialidad_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_procedimiento_procedimiento"
	ON "transaccional"."solicitud_procedimiento"("procedimiento_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_profesional"
	ON "transaccional"."solicitud"("profesional_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_resumen_historia_clinica"
	ON "transaccional"."solicitud"("resumen_historia_clinica_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_sede_ips"
	ON "transaccional"."solicitud"("sede_ips_id")
GO
CREATE NONCLUSTERED INDEX "ix_solicitud_user"
	ON "transaccional"."solicitud"("user_id")
GO

ALTER TABLE "maestros"."afiliado_programa"
	ADD CONSTRAINT "uq_afiliado_programa__afiliado_programa_diagnostico"
	UNIQUE ("afiliado_id", "programa_id", "diagnostico_id")
GO
ALTER TABLE "maestros"."departamento_regional"
	ADD CONSTRAINT "uq_departamento_regional__departamento_regional_fdelete"
	UNIQUE ("departamento_id", "regional_id", "fecha_delete")
GO
ALTER TABLE "maestros"."diagnostico_medicamento"
	ADD CONSTRAINT "uq_diagnostico_medicamento__diagnostico_medicamento_fdelete"
	UNIQUE ("diagnostico_id", "medicamento_id", "fecha_delete")
GO
ALTER TABLE "maestros"."diagnostico_procedimiento"
	ADD CONSTRAINT "uq_diagnostico_procedimiento__diagnostico_procedimiento_fdelete"
	UNIQUE ("diagnostico_id", "procedimiento_id", "fecha_delete")
GO
ALTER TABLE "maestros"."especialidad_insumo"
	ADD CONSTRAINT "uq_especialidad_insumo__especialidad_insumo_fdelete"
	UNIQUE ("especialidad_id", "insumo_id", "fecha_delete")
GO
ALTER TABLE "maestros"."especialidad_medicamento"
	ADD CONSTRAINT "uq_especialidad_medicamento__especialidad_medicamento_fdelete"
	UNIQUE ("especialidad_id", "medicamento_id", "fecha_delete")
GO
ALTER TABLE "maestros"."especialidad_procedimiento"
	ADD CONSTRAINT "uq_especialidad_procedimiento__especialidad_procedimiento_fdelete"
	UNIQUE ("especialidad_id", "procedimiento_id", "fecha_delete")
GO
ALTER TABLE "maestros"."historial_smldv"
	ADD CONSTRAINT "uq_historial_smldv_anio"
	UNIQUE ("anio")
GO
ALTER TABLE "maestros"."historial_variacion_ipc"
	ADD CONSTRAINT "uq_historial_variacion_ipc_anio"
	UNIQUE ("anio")
GO
ALTER TABLE "maestros"."profesional_especialidad_x_sede_ips"
	ADD CONSTRAINT "uq_profesional_especialidad_x_sede_ips__profespe_sedeips_fdelete"
	UNIQUE ("profesional_especialidad_id", "sede_ips_id", "fecha_delete")
GO
ALTER TABLE "transaccional"."role_estado_visible"
	ADD CONSTRAINT "uq_role_estado_visible__role_estado_visible"
	UNIQUE ("role_id", "estado_visible_id")
GO
ALTER TABLE "maestros"."servicio_especialidad"
	ADD CONSTRAINT "uq_servicio_especialidad__servicio_especialidad_fdelete"
	UNIQUE ("servicio_id", "especialidad_id", "fecha_delete")
GO
ALTER TABLE "ticket"."ticket_cabecera"
	ADD CONSTRAINT "uk_ticket_cabecera_autorizacion_nro_entrega"
	UNIQUE ("autorizacion_id", "numero_de_entrega")
GO
ALTER TABLE "security"."user"
	ADD CONSTRAINT "uq_user_username"
	UNIQUE ("username")
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_tipo_identificacion"
	FOREIGN KEY("tipo_identificacion_id")
	REFERENCES "maestros"."tipo_identificacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_tipo_afiliado"
	FOREIGN KEY("tipo_afiliado_id")
	REFERENCES "maestros"."tipo_afiliado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_sede_ips_afiliacion"
	FOREIGN KEY("sede_ips_afiliacion_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_ocupacion"
	FOREIGN KEY("ocupacion_id")
	REFERENCES "maestros"."ocupacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_nivel_sisben"
	FOREIGN KEY("nivel_sisben_id")
	REFERENCES "maestros"."nivel_sisben"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_nivel_ibc"
	FOREIGN KEY("nivel_ibc_id")
	REFERENCES "maestros"."nivel_ibc"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_municipio"
	FOREIGN KEY("municipio_residencia_id")
	REFERENCES "maestros"."municipio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_localidad"
	FOREIGN KEY("localidad_id")
	REFERENCES "maestros"."localidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_grupo_poblacional"
	FOREIGN KEY("grupo_poblacional_id")
	REFERENCES "maestros"."grupo_poblacional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_genero"
	FOREIGN KEY("genero_id")
	REFERENCES "maestros"."genero"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_estado_civil"
	FOREIGN KEY("estado_civil_id")
	REFERENCES "maestros"."estado_civil"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_estado_afiliacion"
	FOREIGN KEY("estado_afiliacion_id")
	REFERENCES "maestros"."estado_afiliacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_eps"
	FOREIGN KEY("eps_id")
	REFERENCES "maestros"."eps"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado"
	ADD CONSTRAINT "fk_afiliado_departamento_seccional"
	FOREIGN KEY("departamento_seccional_id")
	REFERENCES "maestros"."departamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado_cotizante"
	ADD CONSTRAINT "fk_afiliado_cotizante_cotizante"
	FOREIGN KEY("cotizante_id")
	REFERENCES "maestros"."afiliado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado_cotizante"
	ADD CONSTRAINT "fk_afiliado_cotizante_afiliado"
	FOREIGN KEY("afiliado_id")
	REFERENCES "maestros"."afiliado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado_programa"
	ADD CONSTRAINT "fk_afiliado_programa_programa"
	FOREIGN KEY("programa_id")
	REFERENCES "maestros"."programa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."afiliado_programa"
	ADD CONSTRAINT "fk_afiliado_programa_afiliado"
	FOREIGN KEY("afiliado_id")
	REFERENCES "maestros"."afiliado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_servicio"
	FOREIGN KEY("servicio_id")
	REFERENCES "maestros"."servicio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_sede_ips_efectora"
	FOREIGN KEY("sede_ips_efectora_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_rol_destino"
	FOREIGN KEY("rol_destino_id")
	REFERENCES "security"."role"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_informacion_tutela"
	FOREIGN KEY("informacion_tutela_id")
	REFERENCES "transaccional"."informacion_tutela"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_informacion_adicional"
	FOREIGN KEY("informacion_adicional_id")
	REFERENCES "transaccional"."informacion_adicional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_grupo_autorizacion"
	FOREIGN KEY("grupo_autorizacion_id")
	REFERENCES "transaccional"."grupo_autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_estado_autorizacion"
	FOREIGN KEY("estado_autorizacion_id")
	REFERENCES "maestros"."estado_autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_entidad_recobro"
	FOREIGN KEY("entidad_recobro_id")
	REFERENCES "maestros"."entidad_recobro"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_concepto_regional"
	FOREIGN KEY("concepto_regional_id")
	REFERENCES "transaccional"."concepto_autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."autorizacion"
	ADD CONSTRAINT "fk_autorizacion_concepto_nacional"
	FOREIGN KEY("concepto_nacional_id")
	REFERENCES "transaccional"."concepto_autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."con_autorizacion_cri_negacion"
	ADD CONSTRAINT "fk_con_autorizacion_cri_negacion_criterio_negacion"
	FOREIGN KEY("criterio_negacion_id")
	REFERENCES "maestros"."criterio_negacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."con_autorizacion_cri_negacion"
	ADD CONSTRAINT "fk_con_autorizacion_cri_negacion_concepto_autorizacion"
	FOREIGN KEY("concepto_autorizacion_id")
	REFERENCES "transaccional"."concepto_autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."concepto_autorizacion"
	ADD CONSTRAINT "fk_concepto_autorizacion_lateralidad"
	FOREIGN KEY("lateralidad_id")
	REFERENCES "maestros"."lateralidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."concepto_autorizacion"
	ADD CONSTRAINT "fk_concepto_autorizacion_causal_devolucion"
	FOREIGN KEY("causal_devolucion_id")
	REFERENCES "maestros"."causal_devolucion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."concepto_autorizacion"
	ADD CONSTRAINT "fk_concepto_autorizacion_causal_anulacion"
	FOREIGN KEY("causal_anulacion_id")
	REFERENCES "maestros"."causal_anulacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."consumo"
	ADD CONSTRAINT "fk_consumo_solicitud_item"
	FOREIGN KEY("solicitud_item_id")
	REFERENCES "transaccional"."solicitud_item"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."consumo"
	ADD CONSTRAINT "fk_consumo_profesional_efector"
	FOREIGN KEY("profesional_efector_id")
	REFERENCES "maestros"."profesional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."consumo"
	ADD CONSTRAINT "FK38B6FC06F61A9D1"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato"
	ADD CONSTRAINT "fk_contrato_tipo_plan_contrato"
	FOREIGN KEY("tipo_plan_contrato_id")
	REFERENCES "maestros"."tipo_plan_contrato"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato"
	ADD CONSTRAINT "fk_contrato_sede_ips"
	FOREIGN KEY("sede_ips_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato"
	ADD CONSTRAINT "fk_contrato_estado_contrato"
	FOREIGN KEY("estado_contrato_id")
	REFERENCES "maestros"."estado_contrato"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato"
	ADD CONSTRAINT "fk_contrato_eps"
	FOREIGN KEY("eps_id")
	REFERENCES "maestros"."eps"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato_tarifario"
	ADD CONSTRAINT "fk_contrato_tarifario_tipo_minuta"
	FOREIGN KEY("tipo_minuta_id")
	REFERENCES "maestros"."tipo_minuta"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato_tarifario"
	ADD CONSTRAINT "fk_contrato_tarifario_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato_tarifario"
	ADD CONSTRAINT "fk_contrato_tarifario_estado_contrato_tarifario"
	FOREIGN KEY("estado_contrato_tarifario_id")
	REFERENCES "maestros"."estado_item_contratado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."contrato_tarifario"
	ADD CONSTRAINT "fk_contrato_tarifario_contrato"
	FOREIGN KEY("contrato_id")
	REFERENCES "maestros"."contrato"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."departamento_regional"
	ADD CONSTRAINT "fk_departamento_regional_regional"
	FOREIGN KEY("regional_id")
	REFERENCES "maestros"."regional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."departamento_regional"
	ADD CONSTRAINT "fk_departamento_regional_departamento"
	FOREIGN KEY("departamento_id")
	REFERENCES "maestros"."departamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."diagnostico"
	ADD CONSTRAINT "fk_diagnostico_tipo_diagnostico"
	FOREIGN KEY("tipo_diagnostico_id")
	REFERENCES "maestros"."tipo_diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."diagnostico_insumo"
	ADD CONSTRAINT "fk_diagnostico_insumo_insumo"
	FOREIGN KEY("insumo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."diagnostico_insumo"
	ADD CONSTRAINT "fk_diagnostico_insumo_diagnostico"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "maestros"."diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."diagnostico_medicamento"
	ADD CONSTRAINT "fk_diagnostico_medicamento_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."diagnostico_medicamento"
	ADD CONSTRAINT "fk_diagnostico_medicamento_diagnostico"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "maestros"."diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."diagnostico_procedimiento"
	ADD CONSTRAINT "fk_diagnostico_procedimiento_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."diagnostico_procedimiento"
	ADD CONSTRAINT "fk_diagnostico_procedimiento_diagnostico"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "maestros"."diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."director_medico_regional"
	ADD CONSTRAINT "fk_director_medico_regional_regional"
	FOREIGN KEY("regional_id")
	REFERENCES "maestros"."regional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."division_seccional"
	ADD CONSTRAINT "fk_division_seccional_eps"
	FOREIGN KEY("eps_id")
	REFERENCES "maestros"."eps"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."documento_soporte"
	ADD CONSTRAINT "fk_documento_soporte_tipo_doc_soporte"
	FOREIGN KEY("tipo_doc_soporte_id")
	REFERENCES "maestros"."tipo_documento_soporte"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."documento_soporte"
	ADD CONSTRAINT "fk_documento_soporte_solicitud"
	FOREIGN KEY("solicitud_id")
	REFERENCES "transaccional"."solicitud"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."documento_soporte"
	ADD CONSTRAINT "FKEAAE089D2D293645"
	FOREIGN KEY("solicitud_item_id")
	REFERENCES "transaccional"."solicitud_item"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."entrega"
	ADD CONSTRAINT "fk_entrega_solicitud_medicamento"
	FOREIGN KEY("solicitud_medicamento_id")
	REFERENCES "transaccional"."solicitud_medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."eps"
	ADD CONSTRAINT "fk_eps_tipo_identificacion"
	FOREIGN KEY("tipo_identificacion_id")
	REFERENCES "maestros"."tipo_identificacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_contratada"
	ADD CONSTRAINT "fk_especialidad_contratada_unidad_tiempo"
	FOREIGN KEY("unidad_tiempo_id")
	REFERENCES "maestros"."unidad_tiempo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_contratada"
	ADD CONSTRAINT "fk_especialidad_contratada_tipo_minuta"
	FOREIGN KEY("tipo_minuta_id")
	REFERENCES "maestros"."tipo_minuta"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_contratada"
	ADD CONSTRAINT "fk_especialidad_contratada_tarifario_excepcion"
	FOREIGN KEY("tarifario_excepcion_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_contratada"
	ADD CONSTRAINT "fk_especialidad_contratada_servicio_contratado"
	FOREIGN KEY("servicio_contratado_id")
	REFERENCES "maestros"."servicio_contratado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_contratada"
	ADD CONSTRAINT "fk_especialidad_contratada_estado_especialidad_contratada"
	FOREIGN KEY("estado_especialidad_contratada_id")
	REFERENCES "maestros"."estado_item_contratado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_contratada"
	ADD CONSTRAINT "fk_especialidad_contratada_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_insumo"
	ADD CONSTRAINT "fk_especialidad_insumo_insumo"
	FOREIGN KEY("insumo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_insumo"
	ADD CONSTRAINT "fk_especialidad_insumo_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_medicamento"
	ADD CONSTRAINT "fk_especialidad_medicamento_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_medicamento"
	ADD CONSTRAINT "fk_especialidad_medicamento_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_procedimiento"
	ADD CONSTRAINT "fk_especialidad_procedimiento_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."especialidad_procedimiento"
	ADD CONSTRAINT "fk_especialidad_procedimiento_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_insumo"
	ADD CONSTRAINT "fk_formula_item_insumo_finalidad"
	FOREIGN KEY("finalidad_id")
	REFERENCES "maestros"."finalidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_insumo"
	ADD CONSTRAINT "fk_formula_item_insumo_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_insumo"
	ADD CONSTRAINT "FK91243B7CDFA9E6C"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	ADD CONSTRAINT "fk_formula_item_medicamento_via_administracion"
	FOREIGN KEY("via_administracion_id")
	REFERENCES "maestros"."via_administracion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	ADD CONSTRAINT "fk_formula_item_medicamento_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	ADD CONSTRAINT "fk_formula_item_medicamento_solicitud_medicamento"
	FOREIGN KEY("solicitud_medicamento_id")
	REFERENCES "transaccional"."solicitud_medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	ADD CONSTRAINT "fk_formula_item_medicamento_finalidad"
	FOREIGN KEY("finalidad_id")
	REFERENCES "maestros"."finalidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	ADD CONSTRAINT "fk_formula_item_medicamento_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_medicamento"
	ADD CONSTRAINT "fk_formula_item_insumo_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_tipo_prestacion"
	FOREIGN KEY("tipo_prestacion_id")
	REFERENCES "maestros"."tipo_prestacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_solicitud_procedimiento"
	FOREIGN KEY("solicitud_procedimiento_id")
	REFERENCES "transaccional"."solicitud_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_origen_repeticion"
	FOREIGN KEY("origen_repeticion_id")
	REFERENCES "maestros"."origen_repeticion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_objetivo_procedimiento"
	FOREIGN KEY("objetivo_procedimiento_id")
	REFERENCES "maestros"."objetivo_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_lateralidad"
	FOREIGN KEY("lateralidad_id")
	REFERENCES "maestros"."lateralidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_finalidad"
	FOREIGN KEY("finalidad_id")
	REFERENCES "maestros"."finalidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formula_item_procedimiento"
	ADD CONSTRAINT "fk_formula_item_procedimiento_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	ADD CONSTRAINT "fk_formulario_ctc_insumo_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	ADD CONSTRAINT "fk_formulario_ctc_insumo_insumo_homologo"
	FOREIGN KEY("insumo_homologo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	ADD CONSTRAINT "fk_formulario_ctc_insumo_finalidad"
	FOREIGN KEY("finalidad_id")
	REFERENCES "maestros"."finalidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_insumo"
	ADD CONSTRAINT "fk_formulario_ctc_insumo_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	ADD CONSTRAINT "fk_formulario_ctc_medicamento_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	ADD CONSTRAINT "fk_formulario_ctc_medicamento_solicitud_medicamento"
	FOREIGN KEY("solicitud_medicamento_id")
	REFERENCES "transaccional"."solicitud_medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	ADD CONSTRAINT "fk_formulario_ctc_medicamento_medicamento_homologo"
	FOREIGN KEY("medicamento_homologo_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	ADD CONSTRAINT "fk_formulario_ctc_medicamento_finalidad"
	FOREIGN KEY("finalidad_id")
	REFERENCES "maestros"."finalidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_medicamento"
	ADD CONSTRAINT "fk_formulario_ctc_medicamento_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	ADD CONSTRAINT "fk_formulario_ctc_procedimiento_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	ADD CONSTRAINT "fk_formulario_ctc_procedimiento_solicitud_procedimiento"
	FOREIGN KEY("solicitud_procedimiento_id")
	REFERENCES "transaccional"."solicitud_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	ADD CONSTRAINT "fk_formulario_ctc_procedimiento_finalidad"
	FOREIGN KEY("finalidad_id")
	REFERENCES "maestros"."finalidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."formulario_ctc_procedimiento"
	ADD CONSTRAINT "fk_formulario_ctc_procedimiento_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."grupo_autorizacion"
	ADD CONSTRAINT "fk_grupo_autorizacion_solicitud"
	FOREIGN KEY("solicitud_id")
	REFERENCES "transaccional"."solicitud"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."informacion_adicional"
	ADD CONSTRAINT "fk_informacion_adicional_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."informacion_adicional"
	ADD CONSTRAINT "fk_informacion_adicional_finalidad"
	FOREIGN KEY("finalidad_id")
	REFERENCES "maestros"."finalidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."informacion_adicional"
	ADD CONSTRAINT "fk_informacion_adicional_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."informacion_tutela"
	ADD CONSTRAINT "fk_informacion_tutela_devolucion"
	FOREIGN KEY("causal_devolucion_id")
	REFERENCES "maestros"."causal_devolucion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."informacion_tutela"
	ADD CONSTRAINT "fk_informacion_tutela_anulacion"
	FOREIGN KEY("causal_anulacion_id")
	REFERENCES "maestros"."causal_anulacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo"
	ADD CONSTRAINT "fk_insumo_tipo_ppm"
	FOREIGN KEY("tipo_ppm_id")
	REFERENCES "maestros"."tipo_ppm"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo"
	ADD CONSTRAINT "fk_insumo_programa_insumo"
	FOREIGN KEY("programa_alto_costo_id")
	REFERENCES "maestros"."programa_medicamento_alto_costo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo"
	ADD CONSTRAINT "fk_insumo_homologo"
	FOREIGN KEY("homologo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo"
	ADD CONSTRAINT "fk_insumo_genero"
	FOREIGN KEY("genero_id")
	REFERENCES "maestros"."genero"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo"
	ADD CONSTRAINT "fk_insumo_estado_insumo"
	FOREIGN KEY("estado_insumo_id")
	REFERENCES "maestros"."estado_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_contratado"
	ADD CONSTRAINT "fk_insumo_contratado_unidad_tiempo"
	FOREIGN KEY("unidad_tiempo_id")
	REFERENCES "maestros"."unidad_tiempo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_contratado"
	ADD CONSTRAINT "fk_insumo_contratado_tipo_minuta"
	FOREIGN KEY("tipo_minuta_id")
	REFERENCES "maestros"."tipo_minuta"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_contratado"
	ADD CONSTRAINT "fk_insumo_contratado_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_contratado"
	ADD CONSTRAINT "fk_insumo_contratado_insumo"
	FOREIGN KEY("insumo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_contratado"
	ADD CONSTRAINT "fk_insumo_contratado_estado_insumo_contratado"
	FOREIGN KEY("estado_insumo_contratado_id")
	REFERENCES "maestros"."estado_item_contratado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_contratado"
	ADD CONSTRAINT "fk_insumo_contratado_especialidad_contratada"
	FOREIGN KEY("especialidad_contratada_id")
	REFERENCES "maestros"."especialidad_contratada"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."insumo_pos_previo"
	ADD CONSTRAINT "fk_insumo_pos_previo_respuesta_clinica_observada"
	FOREIGN KEY("respuesta_clinica_observada_id")
	REFERENCES "maestros"."respuesta_clinica_observada"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."insumo_pos_previo"
	ADD CONSTRAINT "fk_insumo_pos_previo_insumo"
	FOREIGN KEY("insumo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."insumo_pos_previo"
	ADD CONSTRAINT "fk_insumo_pos_previo_formulario_ctc_insumo"
	FOREIGN KEY("formulario_ctc_insumo_id")
	REFERENCES "transaccional"."formulario_ctc_insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_tarifa"
	ADD CONSTRAINT "fk_insumo_tarifa_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_tarifa"
	ADD CONSTRAINT "fk_insumo_tarifa_insumo"
	FOREIGN KEY("insumo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."insumo_tope"
	ADD CONSTRAINT "fk_insumo_tope_insumo"
	FOREIGN KEY("insumo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."ips"
	ADD CONSTRAINT "fk_ips_tipo_ips"
	FOREIGN KEY("tipo_ips_id")
	REFERENCES "maestros"."tipo_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."ips"
	ADD CONSTRAINT "fk_ips_tipo_identificacion"
	FOREIGN KEY("tipo_identificacion_id")
	REFERENCES "maestros"."tipo_identificacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."ips"
	ADD CONSTRAINT "fk_ips_estado_ips"
	FOREIGN KEY("estado_ips_id")
	REFERENCES "maestros"."estado_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."localidad"
	ADD CONSTRAINT "fk_localidad_regional"
	FOREIGN KEY("regional_id")
	REFERENCES "maestros"."regional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."localidad"
	ADD CONSTRAINT "fk_localidad_municipio"
	FOREIGN KEY("municipio_id")
	REFERENCES "maestros"."municipio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento"
	ADD CONSTRAINT "fk_medicamento_tipo_ppm"
	FOREIGN KEY("tipo_ppm_id")
	REFERENCES "maestros"."tipo_ppm"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento"
	ADD CONSTRAINT "fk_medicamento_programa_medicamento"
	FOREIGN KEY("programa_medicamento_alto_costo_id")
	REFERENCES "maestros"."programa_medicamento_alto_costo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento"
	ADD CONSTRAINT "fk_medicamento_homologo"
	FOREIGN KEY("homologo_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento"
	ADD CONSTRAINT "fk_medicamento_genero"
	FOREIGN KEY("genero_id")
	REFERENCES "maestros"."genero"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento"
	ADD CONSTRAINT "fk_medicamento_estado_medicamento"
	FOREIGN KEY("estado_medicamento_id")
	REFERENCES "maestros"."estado_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_condicionado"
	ADD CONSTRAINT "fk_medicamento_condicionado_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_condicionado"
	ADD CONSTRAINT "FK9A66458933ED69B1"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "maestros"."diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_contratado"
	ADD CONSTRAINT "fk_medicamento_contratado_unidad_tiempo"
	FOREIGN KEY("unidad_tiempo_id")
	REFERENCES "maestros"."unidad_tiempo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_contratado"
	ADD CONSTRAINT "fk_medicamento_contratado_tipo_minuta"
	FOREIGN KEY("tipo_minuta_id")
	REFERENCES "maestros"."tipo_minuta"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_contratado"
	ADD CONSTRAINT "fk_medicamento_contratado_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_contratado"
	ADD CONSTRAINT "fk_medicamento_contratado_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_contratado"
	ADD CONSTRAINT "fk_medicamento_contratado_estado_medicamento_contratado"
	FOREIGN KEY("estado_medicamento_contratado_id")
	REFERENCES "maestros"."estado_item_contratado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_contratado"
	ADD CONSTRAINT "fk_medicamento_contratado_especialidad_contratada"
	FOREIGN KEY("especialidad_contratada_id")
	REFERENCES "maestros"."especialidad_contratada"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."medicamento_pos_previo"
	ADD CONSTRAINT "fk_medicamento_pos_previo_respuesta_clinica_observada"
	FOREIGN KEY("respuesta_clinica_observada_id")
	REFERENCES "maestros"."respuesta_clinica_observada"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."medicamento_pos_previo"
	ADD CONSTRAINT "fk_medicamento_pos_previo_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."medicamento_pos_previo"
	ADD CONSTRAINT "fk_medicamento_pos_previo_formulario_ctc_medicamento"
	FOREIGN KEY("formulario_ctc_medicamento_id")
	REFERENCES "transaccional"."formulario_ctc_medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_tarifa"
	ADD CONSTRAINT "fk_medicamento_tarifa_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."medicamento_tarifa"
	ADD CONSTRAINT "fk_medicamento_tarifa_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."montos_copago_contributivo"
	ADD CONSTRAINT "fk_montos_copago_contributivo_nivel_ibc"
	FOREIGN KEY("nivel_ibc_id")
	REFERENCES "maestros"."nivel_ibc"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."montos_copago_subsidiado"
	ADD CONSTRAINT "fk_montos_copago_subsidiado_nivel_sisben"
	FOREIGN KEY("nivelsisben_id")
	REFERENCES "maestros"."nivel_sisben"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."municipio"
	ADD CONSTRAINT "fk_municipio_regional"
	FOREIGN KEY("regional_id")
	REFERENCES "maestros"."regional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."municipio"
	ADD CONSTRAINT "fk_municipio_departamento"
	FOREIGN KEY("departamento_id")
	REFERENCES "maestros"."departamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."municipio_division_seccional"
	ADD CONSTRAINT "FK172CBFC16F255DF6"
	FOREIGN KEY("division_seccional_id")
	REFERENCES "maestros"."division_seccional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."municipio_division_seccional"
	ADD CONSTRAINT "FK172CBFC1525BD951"
	FOREIGN KEY("municipio_id")
	REFERENCES "maestros"."municipio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."ocupacion"
	ADD CONSTRAINT "fk_ocupacion_eps"
	FOREIGN KEY("eps_id")
	REFERENCES "maestros"."eps"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento"
	ADD CONSTRAINT "fk_procedimiento_tipo_ppm"
	FOREIGN KEY("estado_procedimiento_id")
	REFERENCES "maestros"."estado_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento"
	ADD CONSTRAINT "fk_procedimiento_genero"
	FOREIGN KEY("genero_id")
	REFERENCES "maestros"."genero"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	ADD CONSTRAINT "fk_procedimiento_contratado_unidad_tiempo"
	FOREIGN KEY("unidad_tiempo_id")
	REFERENCES "maestros"."unidad_tiempo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	ADD CONSTRAINT "fk_procedimiento_contratado_tipo_minuta"
	FOREIGN KEY("tipo_minuta_id")
	REFERENCES "maestros"."tipo_minuta"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	ADD CONSTRAINT "fk_procedimiento_contratado_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	ADD CONSTRAINT "fk_procedimiento_contratado_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	ADD CONSTRAINT "fk_procedimiento_contratado_estado_procedimiento_contratado"
	FOREIGN KEY("estado_procedimiento_contratado_id")
	REFERENCES "maestros"."estado_item_contratado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_contratado"
	ADD CONSTRAINT "fk_procedimiento_contratado_especialidad_contratada"
	FOREIGN KEY("especialidad_contratada_id")
	REFERENCES "maestros"."especialidad_contratada"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."procedimiento_homologo"
	ADD CONSTRAINT "fk_procedimiento_homologo_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."procedimiento_homologo"
	ADD CONSTRAINT "fk_procedimiento_homologo_objetivo_procedimiento"
	FOREIGN KEY("objetivo_procedimiento_id")
	REFERENCES "maestros"."objetivo_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."procedimiento_homologo"
	ADD CONSTRAINT "fk_procedimiento_homologo_formulario_ctc_procedimiento"
	FOREIGN KEY("formulario_ctc_procedimiento_id")
	REFERENCES "transaccional"."formulario_ctc_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."procedimiento_pos_previo"
	ADD CONSTRAINT "fk_procedimiento_pos_previo_respuesta_clinica_observada"
	FOREIGN KEY("respuesta_clinica_observada_id")
	REFERENCES "maestros"."respuesta_clinica_observada"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."procedimiento_pos_previo"
	ADD CONSTRAINT "fk_procedimiento_pos_previo_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."procedimiento_pos_previo"
	ADD CONSTRAINT "fk_procedimiento_pos_previo_formulario_ctc_procedimiento"
	FOREIGN KEY("formulario_ctc_procedimiento_id")
	REFERENCES "transaccional"."formulario_ctc_procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_tarifa"
	ADD CONSTRAINT "fk_procedimiento_tarifa_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."procedimiento_tarifa"
	ADD CONSTRAINT "fk_procedimiento_tarifa_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional"
	ADD CONSTRAINT "fk_profesional_tipo_profesional"
	FOREIGN KEY("tipo_profesional_id")
	REFERENCES "maestros"."tipo_profesional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional"
	ADD CONSTRAINT "fk_profesional_municipio"
	FOREIGN KEY("municipio_id")
	REFERENCES "maestros"."municipio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional"
	ADD CONSTRAINT "fk_profesional_identificacion_profesional"
	FOREIGN KEY("identificacion_profesional_id")
	REFERENCES "maestros"."tipo_identificacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional"
	ADD CONSTRAINT "fk_profesional_eps"
	FOREIGN KEY("eps_id")
	REFERENCES "maestros"."eps"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional"
	ADD CONSTRAINT "fk_profesional_division_seccional"
	FOREIGN KEY("division_seccional_id")
	REFERENCES "maestros"."division_seccional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional_especialidad"
	ADD CONSTRAINT "fk_profesional_especialidad_profesional"
	FOREIGN KEY("profesional_id")
	REFERENCES "maestros"."profesional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional_especialidad"
	ADD CONSTRAINT "fk_profesional_especialidad_nivel_autorizacion"
	FOREIGN KEY("nivel_autorizacion_id")
	REFERENCES "maestros"."nivel_autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional_especialidad"
	ADD CONSTRAINT "fk_profesional_especialidad_estado_profesional"
	FOREIGN KEY("estado_profesional_id")
	REFERENCES "maestros"."estado_profesional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional_especialidad"
	ADD CONSTRAINT "fk_profesional_especialidad_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional_especialidad_x_sede_ips"
	ADD CONSTRAINT "fk_profesional_especialidad_x_sede_ips_sede_ips"
	FOREIGN KEY("sede_ips_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."profesional_especialidad_x_sede_ips"
	ADD CONSTRAINT "fk_profesional_especialidad_x_sede_ips_profesional_especialidad"
	FOREIGN KEY("profesional_especialidad_id")
	REFERENCES "maestros"."profesional_especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."programa_diagnostico"
	ADD CONSTRAINT "fk_programa_diagnostico_programa"
	FOREIGN KEY("programa_id")
	REFERENCES "maestros"."programa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."programa_diagnostico"
	ADD CONSTRAINT "fk_programa_diagnostico_diagnostico"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "maestros"."diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."programa_medicamento"
	ADD CONSTRAINT "fk_programa_medicamento_programa"
	FOREIGN KEY("programa_id")
	REFERENCES "maestros"."programa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."programa_medicamento"
	ADD CONSTRAINT "fk_programa_medicamento_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."programa_procedimiento"
	ADD CONSTRAINT "fk_programa_procedimiento_programa"
	FOREIGN KEY("programa_id")
	REFERENCES "maestros"."programa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."programa_procedimiento"
	ADD CONSTRAINT "fk_programa_procedimiento_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."recovery_token"
	ADD CONSTRAINT "fk_recovery_token_user"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."recovery_token"
	ADD CONSTRAINT "FK3BF6D8CFC0A9DBBE"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."regional"
	ADD CONSTRAINT "fk_regional_division_seccional"
	FOREIGN KEY("division_seccional_id")
	REFERENCES "maestros"."division_seccional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."resumen_diagnostico"
	ADD CONSTRAINT "fk_resumen_diagnostico_resumen"
	FOREIGN KEY("resumen_id")
	REFERENCES "transaccional"."resumen_historia_clinica"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."resumen_diagnostico"
	ADD CONSTRAINT "fk_resumen_diagnostico_diagnostico"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "maestros"."diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."resumen_historia_clinica"
	ADD CONSTRAINT "fk_resumen_historia_clinica_tipo_catastrofico"
	FOREIGN KEY("tipo_catastrofico_id")
	REFERENCES "maestros"."tipo_catastrofico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."resumen_historia_clinica"
	ADD CONSTRAINT "fk_resumen_historia_clinica_causa_externa"
	FOREIGN KEY("causa_externa_id")
	REFERENCES "maestros"."causa_externa"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."role_authority"
	ADD CONSTRAINT "fk_role_authority_role"
	FOREIGN KEY("role_id")
	REFERENCES "security"."role"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."role_authority"
	ADD CONSTRAINT "fk_role_authority_authority"
	FOREIGN KEY("authority_id")
	REFERENCES "security"."authority"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."role_estado_estados"
	ADD CONSTRAINT "fk_role_estado_estados_role_estado"
	FOREIGN KEY("role_estado_id")
	REFERENCES "transaccional"."role_estado_visible"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."role_estado_estados"
	ADD CONSTRAINT "fk_role_estado_estados_estado_autorizacion"
	FOREIGN KEY("estado_autorizacion_id")
	REFERENCES "maestros"."estado_autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."role_estado_visible"
	ADD CONSTRAINT "fk_role_estado_visible_role"
	FOREIGN KEY("role_id")
	REFERENCES "security"."role"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."role_estado_visible"
	ADD CONSTRAINT "fk_role_estado_visible_estado_visible"
	FOREIGN KEY("estado_visible_id")
	REFERENCES "maestros"."estado_visible"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_ubicacion"
	FOREIGN KEY("ubicacion_id")
	REFERENCES "maestros"."ubicacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_tipo_servicio"
	FOREIGN KEY("tipo_servicio_id")
	REFERENCES "maestros"."tipo_servicio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_tipo_ips"
	FOREIGN KEY("tipo_ips_id")
	REFERENCES "maestros"."tipo_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_regional"
	FOREIGN KEY("regional_id")
	REFERENCES "maestros"."regional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_regimen_tributario"
	FOREIGN KEY("regimen_tributario_id")
	REFERENCES "maestros"."regimen_tributario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_regimen_juridico"
	FOREIGN KEY("regimen_juridico_id")
	REFERENCES "maestros"."regimen_juridico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_municipio"
	FOREIGN KEY("municipio_id")
	REFERENCES "maestros"."municipio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_localidad"
	FOREIGN KEY("localidad_id")
	REFERENCES "maestros"."localidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_ips"
	FOREIGN KEY("ips_id")
	REFERENCES "maestros"."ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_estado_ips"
	FOREIGN KEY("estado_ips_id")
	REFERENCES "maestros"."estado_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips"
	ADD CONSTRAINT "fk_sede_ips_eps"
	FOREIGN KEY("eps_id")
	REFERENCES "maestros"."eps"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips_division_seccional"
	ADD CONSTRAINT "fk_sede_ips_division_seccional_sede_ips"
	FOREIGN KEY("sede_ips_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."sede_ips_division_seccional"
	ADD CONSTRAINT "fk_sede_ips_division_seccional_division_seccional"
	FOREIGN KEY("division_seccional_id")
	REFERENCES "maestros"."division_seccional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio"
	ADD CONSTRAINT "fk_servicio_unidad_funcional"
	FOREIGN KEY("unidad_funcional_id")
	REFERENCES "maestros"."unidad_funcional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_contratado"
	ADD CONSTRAINT "fk_servicio_contratado_unidad_tiempo"
	FOREIGN KEY("unidad_tiempo_id")
	REFERENCES "maestros"."unidad_tiempo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_contratado"
	ADD CONSTRAINT "fk_servicio_contratado_tipo_minuta"
	FOREIGN KEY("tipo_minuta_id")
	REFERENCES "maestros"."tipo_minuta"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_contratado"
	ADD CONSTRAINT "fk_servicio_contratado_tarifario_excepcion"
	FOREIGN KEY("tarifario_excepcion_id")
	REFERENCES "maestros"."tarifario_excepcion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_contratado"
	ADD CONSTRAINT "fk_servicio_contratado_servicio"
	FOREIGN KEY("servicio_id")
	REFERENCES "maestros"."servicio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_contratado"
	ADD CONSTRAINT "fk_servicio_contratado_estado_item_contratado"
	FOREIGN KEY("estado_servicio_contratado_id")
	REFERENCES "maestros"."estado_item_contratado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_contratado"
	ADD CONSTRAINT "fk_servicio_contratado_contrato"
	FOREIGN KEY("contrato_id")
	REFERENCES "maestros"."contrato"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_contratado"
	ADD CONSTRAINT "FK999C1722A271F0F0"
	FOREIGN KEY("tarifario_excepcion_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_especialidad"
	ADD CONSTRAINT "fk_servicio_especialidad_servicio"
	FOREIGN KEY("servicio_id")
	REFERENCES "maestros"."servicio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."servicio_especialidad"
	ADD CONSTRAINT "fk_servicio_especialidad_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud"
	ADD CONSTRAINT "fk_solicitud_user"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud"
	ADD CONSTRAINT "fk_solicitud_sede_ips"
	FOREIGN KEY("sede_ips_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud"
	ADD CONSTRAINT "fk_solicitud_resumen_historia_clinica"
	FOREIGN KEY("resumen_historia_clinica_id")
	REFERENCES "transaccional"."resumen_historia_clinica"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud"
	ADD CONSTRAINT "fk_solicitud_profesional"
	FOREIGN KEY("profesional_id")
	REFERENCES "maestros"."profesional"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud"
	ADD CONSTRAINT "fk_solicitud_afiliado"
	FOREIGN KEY("afiliado_id")
	REFERENCES "maestros"."afiliado"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud"
	ADD CONSTRAINT "FKAF52BEA4C0A9DBBE"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_diagnostico"
	ADD CONSTRAINT "fk_solicitud_diagnostico_solicitud"
	FOREIGN KEY("solicitud_id")
	REFERENCES "transaccional"."solicitud"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_diagnostico"
	ADD CONSTRAINT "fk_solicitud_diagnostico_diagnostico"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "maestros"."diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_insumo"
	ADD CONSTRAINT "fk_solicitud_insumo_insumo"
	FOREIGN KEY("insumo_id")
	REFERENCES "maestros"."insumo"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_item"
	ADD CONSTRAINT "fk_solicitud_item_tipo_tecnologia"
	FOREIGN KEY("tipo_tecnologia_id")
	REFERENCES "maestros"."tipo_tecnologia"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_item"
	ADD CONSTRAINT "fk_solicitud_item_tipo_servicio"
	FOREIGN KEY("tipo_servicio_id")
	REFERENCES "maestros"."tipo_servicio"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_item"
	ADD CONSTRAINT "fk_solicitud_item_tipo_ppm"
	FOREIGN KEY("tipo_ppm_id")
	REFERENCES "maestros"."tipo_ppm"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_item"
	ADD CONSTRAINT "fk_solicitud_item_solicitud"
	FOREIGN KEY("solicitud_id")
	REFERENCES "transaccional"."solicitud"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_item"
	ADD CONSTRAINT "fk_solicitud_item_diagnostico"
	FOREIGN KEY("diagnostico_id")
	REFERENCES "transaccional"."solicitud_diagnostico"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_item"
	ADD CONSTRAINT "fk_solicitud_item_autorizacion"
	FOREIGN KEY("autorizacion_id")
	REFERENCES "transaccional"."autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_medicamento"
	ADD CONSTRAINT "fk_solicitud_medicamento_solicitud_item"
	FOREIGN KEY("solicitud_item_id")
	REFERENCES "transaccional"."solicitud_item"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_medicamento"
	ADD CONSTRAINT "fk_solicitud_medicamento_medicamento"
	FOREIGN KEY("medicamento_id")
	REFERENCES "maestros"."medicamento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	ADD CONSTRAINT "fk_solicitud_parcial_user"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	ADD CONSTRAINT "fk_solicitud_parcial_tipo_identificacion_afiliado"
	FOREIGN KEY("tipo_identificacion_afiliado_id")
	REFERENCES "maestros"."tipo_identificacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	ADD CONSTRAINT "fk_solicitud_parcial_sede_ips"
	FOREIGN KEY("sede_ips_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_parcial"
	ADD CONSTRAINT "FK60D09097C0A9DBBE"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_procedimiento"
	ADD CONSTRAINT "fk_solicitud_procedimiento_solicitud_item"
	FOREIGN KEY("solicitud_item_id")
	REFERENCES "transaccional"."solicitud_item"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_procedimiento"
	ADD CONSTRAINT "fk_solicitud_procedimiento_procedimiento"
	FOREIGN KEY("procedimiento_id")
	REFERENCES "maestros"."procedimiento"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "transaccional"."solicitud_procedimiento"
	ADD CONSTRAINT "fk_solicitud_procedimiento_especialidad"
	FOREIGN KEY("especialidad_id")
	REFERENCES "maestros"."especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."tarifario"
	ADD CONSTRAINT "fk_tarifario_tipo_tarifario_id"
	FOREIGN KEY("tipo_tarifario_enum")
	REFERENCES "maestros"."tipo_tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."tarifario_excepcion"
	ADD CONSTRAINT "fk_tarifario_excepcion_tarifario"
	FOREIGN KEY("tarifario_id")
	REFERENCES "maestros"."tarifario"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "ticket"."ticket_cabecera"
	ADD CONSTRAINT "fk_ticket_cabecera_autorizacion"
	FOREIGN KEY("autorizacion_id")
	REFERENCES "transaccional"."autorizacion"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "ticket"."ticket_item"
	ADD CONSTRAINT "fk_ticket_item_ticket_cabecera"
	FOREIGN KEY("ticket_cabecera_id")
	REFERENCES "ticket"."ticket_cabecera"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."user_role"
	ADD CONSTRAINT "fk_user_role_user"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."user_role"
	ADD CONSTRAINT "fk_user_role_role"
	FOREIGN KEY("role_id")
	REFERENCES "security"."role"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."user_role"
	ADD CONSTRAINT "FK143BF46AC0A9DBBE"
	FOREIGN KEY("user_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_usuario"
	FOREIGN KEY("usuario_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_user"
	FOREIGN KEY("usuario_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_sede_ips"
	FOREIGN KEY("sede_ips_id")
	REFERENCES "maestros"."sede_ips"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_profesional_especialidad"
	FOREIGN KEY("profesional_especialidad_id")
	REFERENCES "maestros"."profesional_especialidad"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "fk_usuario_entidad_linea_de_frente"
	FOREIGN KEY("ldf_id")
	REFERENCES "maestros"."linea_de_frente"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "security"."usuario_entidad"
	ADD CONSTRAINT "FK6B6BE5D85767757B"
	FOREIGN KEY("usuario_id")
	REFERENCES "security"."user"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."vigencia"
	ADD CONSTRAINT "fk_vigencia_tipo_tecnologia"
	FOREIGN KEY("tipo_tecnologia_id")
	REFERENCES "maestros"."tipo_tecnologia"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
ALTER TABLE "maestros"."vigencia"
	ADD CONSTRAINT "fk_vigencia_tipo_ppm"
	FOREIGN KEY("tipo_ppm_id")
	REFERENCES "maestros"."tipo_ppm"("id")
	ON DELETE NO ACTION 
	ON UPDATE NO ACTION 
GO
