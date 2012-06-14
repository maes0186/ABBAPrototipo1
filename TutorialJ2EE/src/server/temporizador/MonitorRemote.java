package server.temporizador;

import javax.ejb.Remote;

@Remote
public interface MonitorRemote {
	public void startMonitor();

	public void stopMonitor();
}
