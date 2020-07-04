package com.sbnz.bankcredit.events;

import java.io.Serializable;

//import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
//@Expires("20m")
public class LoginEvent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Boolean successfull;
	
	public LoginEvent() {
		super();
	}

	public LoginEvent(String username, Boolean successfull) {
		super();
		this.username = username;
		this.successfull = successfull;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getSuccessfull() {
		return successfull;
	}

	public void setSuccessfull(Boolean successfull) {
		this.successfull = successfull;
	}	
}
