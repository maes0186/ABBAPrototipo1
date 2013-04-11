package org.ejb.beans.tutorial;

import javax.ejb.Remote;

@Remote
public interface RemoteIntDatos {
	public int cantidad();

}
