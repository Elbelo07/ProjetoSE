package pt.ulisboa.tecnico.learnjava.mbway.domain;

public class ClientMbway {
	
	private String iban;
	private String phoneNumber;
	
	public ClientMbway(String iban, String phoneNumber) {
		this.iban = iban;
		this.phoneNumber = phoneNumber;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
