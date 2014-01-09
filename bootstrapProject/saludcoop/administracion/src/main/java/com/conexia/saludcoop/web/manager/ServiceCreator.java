package com.conexia.saludcoop.web.manager;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ServiceCreator {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String[] entities = {"Objetivo","TipoPrestacion",
				"OrigenRepeticion"};
		
		for (String e : entities){
			PrintWriter writer = new PrintWriter("src/main/java/com/conexia/saludcoop/web/manager/"+e+"Manager.java", "UTF-8");
			writer.println("package com.conexia.saludcoop.web.manager;");
			writer.println("import java.util.List;");
			writer.println("import org.springframework.beans.factory.annotation.Autowired;");
			writer.println("import org.springframework.stereotype.Service;");
			writer.println("import org.springframework.transaction.annotation.Transactional;");
			writer.println("import com.conexia.saludcoop.common.dao."+e+"Repository;");
			writer.println("import com.conexia.saludcoop.common.dto.DescriptivoDto;");
			writer.println("import com.conexia.saludcoop.common.entity."+e+";");
			writer.println("@Service");
			writer.println("@Transactional");
			writer.println("public class "+e+"Manager{");
			writer.println("	@Autowired");
			writer.println("	private "+e+"Repository descRepo;");
			writer.println("	@Autowired");
			writer.println("	private DescriptivoManagerUtils<"+e+"> descManager;");
			writer.println("	public List<DescriptivoDto> getAll() {");
			writer.println("		Iterable<"+e+"> entidades = descRepo.findAll();");
			writer.println("		return descManager.getDtos(entidades);");
			writer.println("	}");
			writer.println("}");
			
			writer.close();
		}
	}
}
