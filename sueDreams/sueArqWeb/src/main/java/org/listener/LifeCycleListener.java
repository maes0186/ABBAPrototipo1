package org.listener;
 
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
public class LifeCycleListener implements PhaseListener {
     
    private static final long serialVersionUID = -2504278325379445246L;
 
    private static final Log log = LogFactory.getLog(LifeCycleListener.class);
     
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
 
    public void beforePhase(PhaseEvent event) {
        if (log.isTraceEnabled()){
            log.info("BeforePhase: " + event.getPhaseId());
            System.out.println("BeforePhase: " + event.getPhaseId());
        }
    }
 
    public void afterPhase(PhaseEvent event) {
        if (log.isTraceEnabled()){
    	log.info("AfterPhase: " + event.getPhaseId());
    	System.out.println("AfterPhase: " + event.getPhaseId());
        }
    }
 
}