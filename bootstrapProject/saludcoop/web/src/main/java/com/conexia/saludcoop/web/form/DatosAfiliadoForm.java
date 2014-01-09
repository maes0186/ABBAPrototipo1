package com.conexia.saludcoop.web.form;

public class DatosAfiliadoForm {
    private Long id;
    private Long municipioId;
    private String direccionResidencial;
    private String emailPersonal;
    private Long departamentoId;
    
    public Long getMunicipioId() {
        return municipioId;
    }
    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }
    public String getDireccionResidencial() {
        return direccionResidencial;
    }
    public void setDireccionResidencial(String direccionResidencial) {
        this.direccionResidencial = direccionResidencial;
    }
    public String getEmailPersonal() {
        return emailPersonal;
    }
    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }
    public String getTelefonoResidencial() {
        return telefonoResidencial;
    }
    public void setTelefonoResidencial(String telefonoResidencial) {
        this.telefonoResidencial = telefonoResidencial;
    }
    public String getTelefonoCelular() {
        return telefonoCelular;
    }
    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }
    private String telefonoResidencial;
    private String telefonoCelular;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDepartamentoId() {
        return departamentoId;
    }
    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }
}
