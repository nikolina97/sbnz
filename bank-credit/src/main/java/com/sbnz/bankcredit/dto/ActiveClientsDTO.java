package com.sbnz.bankcredit.dto;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.bankcredit.model.Client;

public class ActiveClientsDTO {
	
	private List<Client> clients = new ArrayList<>();

	public ActiveClientsDTO() {
		super();
	}

	public ActiveClientsDTO(List<Client> clients) {
		super();
		this.clients = clients;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}
