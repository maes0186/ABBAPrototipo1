package pruebaMario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TEST2")
public class Test2 implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String id2;
	

	@Id
    @Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    //@Transient Se hace para que este campo no se tenga en cuenta
	@Column(name="id2")
	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}
	
	
}
