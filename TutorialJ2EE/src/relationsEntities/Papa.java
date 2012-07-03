package relationsEntities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PAPA")
public class Papa implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idPapa;
	private String descPapa;
	private List<Hijo> hijos;
	
	@Id
	@Column(name="idPapa")
	public int getIdPapa() {
		return idPapa;
	}
	public void setIdPapa(int idPapa) {
		this.idPapa = idPapa;
	}
	@Column(name="descPapa")
	public String getDescPapa() {
		return descPapa;
	}
	public void setDescPapa(String descPapa) {
		this.descPapa = descPapa;
	}
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="idHijo")
	public List<Hijo> getHijos() {
		return hijos;
	}
	public void setHijos(List<Hijo> hijos) {
		this.hijos = hijos;
	}

}
