package com.sbnz.bankcredit.events;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Expires("5m")
public class SuspiciousUserEvent {
	
	private String username;
	private String description;
	
	public SuspiciousUserEvent() {
		super();
	}
	
	public SuspiciousUserEvent(String username, String description) {
		super();
		this.username = username;
		this.description = description;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
