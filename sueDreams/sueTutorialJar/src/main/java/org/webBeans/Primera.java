package org.webBeans;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@SessionScoped @Named("primera")
public class Primera {
	
private String test;

public String getTest() {
	return test;
}

public void setTest(String test) {
	this.test = test;
}
}
