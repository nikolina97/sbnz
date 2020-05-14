package com.sbnz.bankcredit.model;

public class Admin extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}
	
	public Admin(User user) {
		super(user.getUsername(),user.getPassword(), user.getFirstName(), user.getLastName(), user.getAuthorities());
	}
	
}
