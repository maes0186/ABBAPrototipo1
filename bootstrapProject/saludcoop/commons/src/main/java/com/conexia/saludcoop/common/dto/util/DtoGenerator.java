package com.conexia.saludcoop.common.dto.util;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public class  DtoGenerator<T> {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
/*
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
		        null, null, null);

		StandardLocation location = StandardLocation.CLASS_PATH;
		String packageName = "com.conexia.saludcoop.common.entity.transaccional";
		Set<JavaFileObject.Kind> kinds = new HashSet<JavaFileObject.Kind>();
		kinds.add(JavaFileObject.Kind.CLASS);
		boolean recurse = false;

		Iterable<JavaFileObject> list = fileManager.list(location, packageName,
		        kinds, recurse);
		
		for (JavaFileObject jfo : list){
			Class<?> c = Class.forName("com.conexia.saludcoop.common.entity.transaccional."+jfo.getName().substring(jfo.getName().lastIndexOf("\\")+1, jfo.getName().indexOf(".class")));
			if (c!=null && !c.isInterface() && !(c.getModifiers() == Modifier.ABSTRACT)  && !c.getSuperclass().equals(Descriptivo.class) && !c.getSuperclass().equals(TipoIdentificacion.class)){
				PrintWriter writer = new PrintWriter("src\\main\\java\\com\\conexia\\saludcoop\\common\\dto\\transaccional\\"+c.getSimpleName()+"Dto.java", "UTF-8");
				writer.println("package com.conexia.saludcoop.common.dto.transaccional;");
				writer.println();
				
				Set<Class<?>> typesToImport = new HashSet<Class<?>>();
				
				for (Field f : c.getDeclaredFields()){
					typesToImport.add(f.getType());
				}
				for(Class<?> t : typesToImport){
					if(t.getSimpleName().equals("Date") || t.getSimpleName().equals("Set")){
						writer.println("import java.util."+t.getSimpleName()+";");
					}
				}

				writer.println();				
				writer.println("public class "+c.getSimpleName()+"Dto { ");

				for (Field f : c.getDeclaredFields()){
					
					String type = f.getType().getSimpleName();
					if (f.getType().isAnnotationPresent(Entity.class) || f.getType().isAnnotationPresent(Embeddable.class)){
						if (f.getType().getSuperclass().equals(Descriptivo.class)){
							type="DescriptivoDto";
						}else{
							type=f.getType().getSimpleName()+"Dto";
						}
						
					}
					
					writer.println("\tprivate "+type+" "+f.getName()+";");
				}
				writer.println();
				for (Field f : c.getDeclaredFields()){
					
					String type = f.getType().getSimpleName();
					if (f.getType().isAnnotationPresent(Entity.class) || f.getType().isAnnotationPresent(Embeddable.class)){
						if (f.getType().getSuperclass().equals(Descriptivo.class)){
							type="DescriptivoDto";
						}else{
							type=f.getType().getSimpleName()+"Dto";
						}
						
					}
					writer.println("\tpublic void set"+ConverterUtil.capitalizeString(f.getName())+"("+type+" "+f.getName()+"){");
					writer.println("\t\tthis."+f.getName()+" = "+f.getName()+";");
					writer.println("\t}");
					writer.println("");
					writer.println("\tpublic "+type +" get"+ConverterUtil.capitalizeString(f.getName())+"(){");
					writer.println("\t\treturn this."+f.getName()+";");
					writer.println("\t}");
				}
				writer.println();
				writer.println("}");
				
				writer.close();
			}
		}*/

		
		new DtoGenerator<String>().getType();
	}
	
	public void getType(){
		Class genericParameter0OfThisClass = 
			    (Class)
			        ((ParameterizedType)
			            getClass()
			                .getGenericSuperclass())
			                    .getActualTypeArguments()[0];
		
		System.out.println(genericParameter0OfThisClass);
	}
}
