package server.temporizador;

import javax.annotation.Resource;
import javax.ejb.*;

@Stateless
public class MonitorBean implements MonitorRemote {
	@Resource
	TimerService timerService;

	public static final String KOMMANDO = "HSP";

	public void startMonitor() {
		timerService.createTimer(1 * 1000, 1 * 1000, KOMMANDO);
	}

	public void stopMonitor() {
		for (Object obj : timerService.getTimers()) {
			Timer timer = (Timer) obj;
			String bez = (String) timer.getInfo();
			if (bez.equals(KOMMANDO)) {
				timer.cancel();
			}
		}
	}

	@Timeout
	public void timeout(Timer timer) {
		String bez = (String) timer.getInfo();
		if (bez.equals(KOMMANDO)) {
			System.out.println("Freier HSP: "
					+ Runtime.getRuntime().freeMemory());
		}
	}
}
