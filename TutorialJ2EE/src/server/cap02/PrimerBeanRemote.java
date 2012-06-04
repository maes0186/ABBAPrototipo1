package server.cap02;
import javax.ejb.*;

@Remote
public interface PrimerBeanRemote {
	public int adieren(int a,int b);
}
