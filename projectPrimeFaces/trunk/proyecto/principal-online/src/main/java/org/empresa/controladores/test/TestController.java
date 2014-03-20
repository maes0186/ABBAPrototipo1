package org.empresa.controladores.test;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
		addMessage("Mensaje Procesado");
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
	 public void addMessage(String summary) {  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }  

}
