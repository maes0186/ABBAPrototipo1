package com.conexia.saludcoop.common.entity.maestro;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class DescriptivoEntityCreator {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String[] entities = {
				"criterio_negacion","causal_anulacion","causal_devolucion"};
		
		for (String e : entities){
			
			String[] splited = e.split("_");
			String entityName = "";
			for (String s : splited)
				entityName += s.substring(0,1).toUpperCase()+s.substring(1);
			
			PrintWriter writer = new PrintWriter("src/main/java/com/conexia/saludcoop/common/entity/maestro/"+entityName+".java", "UTF-8");
			
			writer.println("package com.conexia.saludcoop.common.entity.maestro;");

			writer.println("import javax.persistence.Entity;");
			writer.println("import javax.persistence.Table;");

			writer.println("@Entity");
			writer.println("@Table(name=\""+e+"\",schema=\"maestros\")");
			writer.println("public class "+entityName+" extends Descriptivo{");
			writer.println("}");
			
			writer.close();
		}
	}
}
