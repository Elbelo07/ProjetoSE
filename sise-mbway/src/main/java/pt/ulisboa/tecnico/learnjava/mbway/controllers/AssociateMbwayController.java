package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.cli.Vistas;
import pt.ulisboa.tecnico.learnjava.mbway.domain.ClientMbway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.Mbway;

public class AssociateMbwayController {

	private Mbway mbway;
	private ClientMbway clientMbway;
	private Account account;
	private Vistas vistas;
	private Services services;
	private String iban;
	private String phoneNumber;
	private int code;
	
	
	
	public AssociateMbwayController(String iban, String phoneNumber) {
		
		
		this.iban = iban;
		this.phoneNumber = phoneNumber;
//		code++;
//		checkAccount(iban,phoneNumber);
//		this.clientMbway = new 
//		clientMbway.setIban(iban);
//		clientMbway.setPhoneNumber(phoneNumber);
	}
	
	public void process(Services services) {
		if(services.getAccountByIban(this.iban) != null) {
			this.clientMbway = new ClientMbway(this.iban, this.phoneNumber);
			this.code = (int) (Math.random()*(100 + 1));
			this.mbway.addClient(this.clientMbway, this.code);
		}
		
	}
	
	public int getCode(){
		return this.code;	
	}
	
//	conta 1 -> n1 / iban 1
//	
//	conta 2 -> n1 / iban 1
//	
//	conta 3 -> n1 / iban 2
//	conta 4 -> n2 / iban 1
	
	
//	private void checkAccount(String iban, String phoneNumber) {
//		for (String i: mbway.)
//			if i != iban ou do phoneNumber
//				mbway.addClient(ClienteMbway(iban,phoneNumber));
//			else
//				utilizador ja existe
//	}
	
	
//	private void checkCode(String code) throws BankException {
//		// code size is three
//		if (code == null || code.length() != 3) {
//			throw new MbwayException();
//		}
//
//		// banks have a unique code
//		if (getBankByCode(code) != null) {
//			throw new BankException();
//		}
//
//	}
}
