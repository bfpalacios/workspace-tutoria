package pe.edu.sistemas.unayoe.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

public class LifecycleListener  implements PhaseListener {
	private static final long serialVersionUID = 1L;
 
	private static final String sessionToken = "MULTI_PAGE_MESSAGES_SUPPORT";
 
	/**
	 * Return the identifier of the request processing phase during which this
	 * listener is interested in processing PhaseEvent events.
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
 
	/**
	 * Handle a notification that the processing for a particular phase of the
	 * request processing lifecycle is about to begin.
	 */
	public void beforePhase(PhaseEvent event) {
 
		FacesContext facesContext = event.getFacesContext();
        this.saveMessages(facesContext);

        if (PhaseId.RENDER_RESPONSE.equals(event.getPhaseId()))
        {
            if (!facesContext.getResponseComplete())
            {
                this.restoreMessages(facesContext);
            }
        }
		
		/*
		if(event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
			FacesContext facesContext = event.getFacesContext();
			restoreMessages(facesContext);
		}*/
	}
 
	/**
	 * Handle a notification that the processing for a particular phase has just
	 * been completed.
	 */
	public void afterPhase(PhaseEvent event) {
 
		if(event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES ||
				event.getPhaseId() == PhaseId.PROCESS_VALIDATIONS ||
				event.getPhaseId() == PhaseId.INVOKE_APPLICATION) {
 
			FacesContext facesContext = event.getFacesContext();
			
			Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(WebAttributes.AUTHENTICATION_EXCEPTION);
			
			saveMessages(facesContext);
		}
 
	}
 
	private int saveMessages(FacesContext facesContext) {
		// remove messages from the context
		List messages = new ArrayList();
		for(Iterator i = facesContext.getMessages(null); i.hasNext(); ) {
			messages.add(i.next());
			i.remove();
		}
		// store them in the session
		if(messages.size() == 0) {
			return 0;
		}
		Map sessionMap = facesContext.getExternalContext().getSessionMap();
		// if there already are messages
		@SuppressWarnings("unchecked")
		List existingMessages = (List) sessionMap.get(sessionToken);
		if(existingMessages != null) {
			existingMessages.addAll(messages);
		}
		else {
			sessionMap.put(sessionToken, messages); // if these are the first messages
		}
 
		return messages.size();
	}
 
	private int restoreMessages(FacesContext facesContext) {
		// remove messages from the session
		Map sessionMap = facesContext.getExternalContext().getSessionMap();
		@SuppressWarnings("unchecked")
		List messages = (List)sessionMap.remove(sessionToken);
		if(messages == null) {
			return 0;
		}
		int restoredCount = messages.size();
		for(Iterator i = messages.iterator(); i.hasNext(); ) {
			facesContext.addMessage(null, (FacesMessage)i.next());
		}
 
		return restoredCount;
	}
}