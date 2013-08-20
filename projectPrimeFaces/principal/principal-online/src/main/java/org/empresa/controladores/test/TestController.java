package org.empresa.controladores.test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.empresa.fachada.test.TestFachada;
import org.empresa.modelo.test.TestModelo;

@ManagedBean
@SessionScoped
public class TestController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{testFachada}")
	private TestFachada testFachada;
	@ManagedProperty(value="#{testModelo}")
	private TestModelo testModelo;

	public String procesarMensaje() {
		String mensaje=testFachada.mensaje();
		testModelo.setTexto(mensaje);
		return mensaje;
	}

	public TestFachada getTestFachada() {
		return testFachada;
	}

	public void setTestFachada(TestFachada testFachada) {
		this.testFachada = testFachada;
	}

	public TestModelo getTestModelo() {
		return testModelo;
	}

	public void setTestModelo(TestModelo testModelo) {
		this.testModelo = testModelo;
	}

}
