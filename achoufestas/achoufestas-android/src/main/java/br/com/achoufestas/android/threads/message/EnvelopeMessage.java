package br.com.achoufestas.android.threads.message;

import br.com.achoufestas.android.exception.DefaultException;

public class EnvelopeMessage {

	private Object originalMessage;
	private DefaultException exception;
	
	public Object getOriginalMessage() {
		return originalMessage;
	}
	public void setOriginalMessage(Object originalMessage) {
		this.originalMessage = originalMessage;
	}
	public DefaultException getException() {
		return exception;
	}
	public void setException(DefaultException exception) {
		this.exception = exception;
	}
	
	

}
