package com.conexia.saludcoop.web.manager;

import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class EspecialidadManager {


	public List<String> getEspecialidades(){
		List<String> especialidades = new Vector<String>();
		especialidades.add("Especialidad1");
		especialidades.add("Especialidad2");
		especialidades.add("Especialidad3");
		especialidades.add("Especialidad4");
		return especialidades;
	}
	
}
