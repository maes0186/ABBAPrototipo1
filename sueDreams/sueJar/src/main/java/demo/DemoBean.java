package demo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.SessionScoped;



	public class DemoBean implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
			
		public DemoBean() {
			
		}
		
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	    public Date getCurrentDate() {
	    	return Calendar.getInstance().getTime();
	    }
		
	}

