package app.tracker;

import java.util.List;
import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.application.FacesMessage;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class PhaseTracker implements PhaseListener {

    private static final Logger logger = Logger.getLogger("org.exadel.helper");

    public void beforePhase(PhaseEvent e) {
        List<FacesMessage> msgs = e.getFacesContext().getMessageList();
        for (FacesMessage msg : msgs) {
            logger.info("before msg " + msg.getSummary() + " :: " + msg.getDetail());
        }
        logger.info("BEFORE " + e.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public void afterPhase(PhaseEvent e) {
        logger.info("AFTER " + e.getPhaseId());
    }
}