package com.conexia.saludcoop.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneradorZip {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeneradorZip.class);	
	
	private ZipOutputStream zipOutputStream;
	
	private byte[] buffer;

	public GeneradorZip(String destino) {
		buffer = new byte[4096];
		try {
	        FileOutputStream fileOutputStream = new FileOutputStream(destino);
	        zipOutputStream = new ZipOutputStream(fileOutputStream);
        } catch (FileNotFoundException e) {}
	}
	
	public GeneradorZip(OutputStream outputStream) {
		buffer = new byte[1024];
        zipOutputStream = new ZipOutputStream(outputStream);
	}
	
	/**
	 * Recibe una lista de archivos que convertira en un .zip
	 * @param archivos lista de archivos.
	 * @param destino ruta y nombre del .zip a crear.
	 */
	public void generarZip(Collection<File> archivos) {
		try {
			//Se crea un FileOutputStream que contiene el nombre y direccion del zip que se va a generar.
			//Se crea un ZipOutputStream que contiene el fileOutputStream creado anteriormente.
			for (File file : archivos) {
				//Se envia el archivo, buffer y zipOutputStream.
				ejecutar(file, buffer, zipOutputStream);
			}
		} catch (IOException ioe) {
			LOGGER.error(ioe.toString());
		} finally {
			try {
				zipOutputStream.close();
			} catch (IOException e) {
				LOGGER.error(e.toString());
			}
		}
	}
	
	/**
	 * Recibe un archivo que convertira en un .zip
	 * @param archivo a convertir en .zip.
	 * @param destino ruta y nombre del .zip a crear.
	 */
	public void generarZip(File archivo) {
		try {
			ejecutar(archivo, buffer, this.zipOutputStream);
		} catch (IOException ioe) {
			LOGGER.error(ioe.toString());
		} finally {
			try {
				zipOutputStream.close();
			} catch (IOException e) {
				LOGGER.error(e.toString());
			}
		}
	}	
	
	private void ejecutar(File archivo, byte[] buffer, ZipOutputStream zipOutputStream) throws IOException {
		//Se crea un FileInputStream que contiene el archivo recibido por parametro.
		FileInputStream fileInputStream = new FileInputStream(archivo);
		//prepara el zipOutputStream para escribir el .zip
		zipOutputStream.putNextEntry(new ZipEntry(archivo.getName()));
		int length;
		while ((length = fileInputStream.read(buffer)) > 0) {
			//escirbe el .zip.
			zipOutputStream.write(buffer, 0, length);
		}
		zipOutputStream.closeEntry();
		fileInputStream.close();
	}
	

	public static void main(String[] args) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		GeneradorZip gz = new GeneradorZip(outputStream);
    }

}
