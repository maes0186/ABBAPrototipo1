package server.entidades;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@NamedQueries( { @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a ORDER BY a.artnr") })
@Table(name="ARTICULO")
public class Articulo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    
	private int artnr;
    
	private String des;
    
	private BigDecimal precio;
    
	private int cantidad;
    @Id
    @Column(name="ARTNR")
    public int getArtnr() {
		return artnr;
	}

	public void setArtnr(int artnr) {
		this.artnr = artnr;
	}
	@Column(name="DES")
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	@Column(name="PRECIO")
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Column(name="CANTIDAD")
	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}

