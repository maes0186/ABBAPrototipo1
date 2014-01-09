package com.conexia.saludcoop.web.controller.prestador;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.conexia.saludcoop.common.dto.DiagnosticoDto;
import com.conexia.saludcoop.common.dto.MedicamentoDto;
import com.conexia.saludcoop.common.dto.ProcedimientoDto;
import com.conexia.saludcoop.web.BaseValidatingController;
import com.conexia.saludcoop.web.form.SeachCodigoDescripcionForm;
import com.conexia.saludcoop.web.manager.DiagnosticoManager;
import com.conexia.saludcoop.web.manager.MedicamentoManager;
import com.conexia.saludcoop.web.manager.ProcedimientoManager;
import com.conexia.saludcoop.web.vo.ProcedimientoMedicamentoVO;
@Controller
@RequestMapping(value="*/search", method=RequestMethod.POST)
public class SearchController extends BaseValidatingController{
	@Autowired
	private ProcedimientoManager prestManager;
	@Autowired
	private MedicamentoManager medicamentosManager;
	@Autowired
	private DiagnosticoManager dxManager;
	
//	
	@RequestMapping(value="/procedimientos")
	protected @ResponseBody List<ProcedimientoMedicamentoVO> buscarPrestacion(SeachCodigoDescripcionForm form, ModelMap map){
		
		
		List<ProcedimientoDto> procedimientosDto = prestManager.getProcedimientoByCodigoDescripcion(form.getCodigo(),form.getDescripcion());
		List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
		for (ProcedimientoDto p : procedimientosDto ){
			pmvos.add(new ProcedimientoMedicamentoVO(p));
		}
		
		return pmvos;
		
		
	}
	
	@RequestMapping(value="/procedimientosPOS")
	protected @ResponseBody List<ProcedimientoMedicamentoVO> buscarProcedimientoPOS(SeachCodigoDescripcionForm form, ModelMap map){
		
		
		List<ProcedimientoDto> procedimientosDto = prestManager.getProcedimientosPosByCodigoDescripcion(form.getCodigo(),form.getDescripcion());
		List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
		for (ProcedimientoDto p : procedimientosDto ){
			pmvos.add(new ProcedimientoMedicamentoVO(p));
		}
		
		return pmvos;
		
		
	}
	
	@RequestMapping(value="/diagnosticos")
	protected @ResponseBody List<DiagnosticoDto> buscarDiagnosticos(SeachCodigoDescripcionForm form, ModelMap map){
		
		List<DiagnosticoDto> diagnosticos = dxManager.getDiagnosticosByCodigoDescripcion(form.getCodigo(),form.getDescripcion());
		
		return diagnosticos;
		
		
	}

	@RequestMapping(value="/medicamentos")
	protected @ResponseBody List<ProcedimientoMedicamentoVO> buscarMedicamentos(SeachCodigoDescripcionForm form, ModelMap map){
		
		List<MedicamentoDto> medicamentosDto = medicamentosManager.getMedicamentosByCodigoDescripcion(form.getCodigo(),form.getDescripcion());
		List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
		for (MedicamentoDto m : medicamentosDto ){
			pmvos.add(new ProcedimientoMedicamentoVO(m));
		}
		
		return pmvos;
		
		
	}
	
	@RequestMapping(value="/medicamentosPOS")
	protected @ResponseBody List<ProcedimientoMedicamentoVO> buscarMedicamentosPos(SeachCodigoDescripcionForm form, ModelMap map){
		
		List<MedicamentoDto> medicamentosDto = medicamentosManager.getMedicamentosPOSByCodigoDescripcion(form.getCodigo(),form.getDescripcion());
		List<ProcedimientoMedicamentoVO> pmvos = new Vector<ProcedimientoMedicamentoVO>();
		for (MedicamentoDto m : medicamentosDto ){
			pmvos.add(new ProcedimientoMedicamentoVO(m));
		}
		
		return pmvos;
		
	}
}
