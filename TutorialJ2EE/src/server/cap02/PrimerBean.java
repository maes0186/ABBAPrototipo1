package server.cap02;

import javax.ejb.Stateless;

@Stateless
public class PrimerBean implements PrimerBeanRemote{

	
	public int adieren(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

}
