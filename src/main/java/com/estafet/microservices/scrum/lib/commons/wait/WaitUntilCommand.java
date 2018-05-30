package com.estafet.microservices.scrum.lib.commons.wait;

public abstract class WaitUntilCommand<T> {

	private final int timeout;
	private static final int DELAY = 500;
	
	abstract public boolean isReady(T result);
	
	abstract public T result();
		
	public WaitUntilCommand() {
		this(5000);
	}
	
	public WaitUntilCommand(int timeout) {
		this.timeout = timeout;
	}
	
	public T execute() {
		try {
			int wait = 0;
			T result = result();
			while (!isReady(result)) {
				Thread.sleep(DELAY);
				wait+= DELAY;
				if (wait >= timeout) {
					throw new RuntimeException("Timeout after " + wait + "ms");
				} else {
					result = result();	
				}
			}
			return result;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
