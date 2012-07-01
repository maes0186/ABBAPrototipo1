package relationsEntities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="HIJO")
public class Hijo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idHijo;
	private String descHijo;
	private Papa idPapaFk;
	
	
	@Id
	@Column(name ="idHijo")
	public int getIdHijo() {
		return idHijo;
	}
	public void setIdHijo(int idHijo) {
		this.idHijo = idHijo;
	}
	@Column(name="descHijo")
	public String getDescHijo() {
		return descHijo;
	}
	public void setDescHijo(String descHijo) {
		this.descHijo = descHijo;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idPapaFk")
	public Papa getIdPapaFk() {
		return idPapaFk;
	}
	public void setIdPapaFk(Papa idPapaFk) {
		this.idPapaFk = idPapaFk;
	}

}
