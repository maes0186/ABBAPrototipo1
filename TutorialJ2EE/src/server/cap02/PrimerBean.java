package server.cap02;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class PrimerBean implements PrimerBeanRemote{

	
	public int adieren(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}
	
@Resource(name="FormatString")String formatString;
public String getTimeString(){
	
	SimpleDateFormat sdf=null;
	if(formatString!=null)sdf= new SimpleDateFormat(formatString);
	else return null;//sdf = new SimpleDateFormat();
	return sdf.format(new Date());
}

public String mFecha() {
	// TODO Auto-generated method stub
	return getTimeString()==null?"Vacio":getTimeString();
}
}
