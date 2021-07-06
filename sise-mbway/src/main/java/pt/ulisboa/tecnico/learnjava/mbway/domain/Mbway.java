package pt.ulisboa.tecnico.learnjava.mbway.domain;

import java.util.HashMap;

public class Mbway {
	
	private static HashMap<ClientMbway, Integer> mbways = new HashMap<ClientMbway,Integer>();
	
	public Mbway() {
		//criar hashmap vazia
		this.mbways = new HashMap<ClientMbway,Integer>();
		
	}
		
	public void addClient(ClientMbway client, Integer code) {			
		mbways.put(client, code);
	}
	

}
