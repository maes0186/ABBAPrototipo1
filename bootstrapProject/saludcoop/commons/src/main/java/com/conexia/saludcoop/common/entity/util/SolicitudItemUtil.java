package com.conexia.saludcoop.common.entity.util;

import com.conexia.saludcoop.common.entity.maestro.Medicamento;
import com.conexia.saludcoop.common.entity.transaccional.SolicitudItem;
import com.conexia.saludcoop.common.enumerator.PeriodosVigencia;
/**
 * 
 * @author mortega
 *
 */
public class SolicitudItemUtil {
	/**
	 * Obtiene el periodo basado en tipo de
	 * autorizacion
	 * @return
	 */
	public static Integer obtenerPeriodoVigencia(SolicitudItem item) {
		Boolean aplicaTutela = item.getAplicaTutela();
		if (aplicaTutela!=null&&aplicaTutela) {
			return PeriodosVigencia.PERIODO_30.getPeriodo();
		} else {
			//Medicamanto
			if (item.getSolMedicamento() != null) {
				Medicamento medicamento = item.getSolMedicamento()
						.getMedicamento();
				Boolean esVisibleCtc = medicamento.getVisibleCtc();
				Boolean esAltoCosto = medicamento.getAltoCosto();
				if((esVisibleCtc!=null&&esVisibleCtc)||(esAltoCosto!=null&&esAltoCosto)){
					return PeriodosVigencia.PERIODO_30.getPeriodo();
				}
				else{
					//POS
					return PeriodosVigencia.PERIODO_70.getPeriodo();
				}
				//Procedimiento
			} else if (item.getSolProcedimiento() != null) {
				return PeriodosVigencia.PERIODO_90.getPeriodo();
			}
		}
		return null;
	}

}
