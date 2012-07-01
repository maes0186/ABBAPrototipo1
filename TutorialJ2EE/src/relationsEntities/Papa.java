package relationsEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

}
