package server.entidades;


import javax.persistence.*;

@Entity
@Table(name="TEST")
public class Test implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
    
	private int a;
    @Id
    @Column(name="A")
    public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
}

