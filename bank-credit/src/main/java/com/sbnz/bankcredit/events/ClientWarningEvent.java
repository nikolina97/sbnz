package com.sbnz.bankcredit.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class ClientWarningEvent {
	
	private String username;
	private String reason;
	
	public ClientWarningEvent() {
		super();
	}
	
	public ClientWarningEvent(String username, String reason) {
		super();
		this.username = username;
		this.reason = reason;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
