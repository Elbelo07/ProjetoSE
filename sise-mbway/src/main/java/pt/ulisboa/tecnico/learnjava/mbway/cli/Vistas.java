package pt.ulisboa.tecnico.learnjava.mbway.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.AssociateMbwayController;
import pt.ulisboa.tecnico.learnjava.mbway.domain.Mbway;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;

public class Vistas {

 
	public static void main(String[] args) throws BankException, ClientException, AccountException {

		Services services = new Services();
		Sibs sibs = new Sibs(100, services);
		Bank bank1 = new Bank("CGD");
		Bank bank2 = new Bank("BPI");
		
		Client client1 = new Client(bank1, "Maria", "Soares", "123456789", "912346987", "Rua Alves Redol", 25);
		Client client2 = new Client(bank2, "Clara", "Rodrigues", "987654321", "917895234", "Rua Redol", 30);
		Client client3 = new Client(bank2, "Marco", "Andrade", "345123678", "967896734", "Rua da Figueira", 40);
		
		String iban1 = bank1.createAccount(AccountType.CHECKING, client1, 1000, 0);
		String iban2 = bank2.createAccount(AccountType.CHECKING, client2, 5000, 0);
		String iban3 = bank2.createAccount(AccountType.CHECKING, client3, 2500, 0);
		
		System.out.println(iban1);
		//CGDCK1
		while (true) {
			try {
				InputStreamReader input = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(input);
				System.out.println("Insira o comando desejado:");
				String command = br.readLine();

				switch(command) {
				case "associate-mbway":
					AssociateMbwayController ctrl = new AssociateMbwayController(iban3, client3.getPhoneNumber());
					ctrl.process(services);
					System.out.println("Code:" + ctrl.getCode() + "Don't share this code with anyone!");
					break;
				case "exit":
					System.out.println("MBWAY terminated with success.");
					System.exit(0);
					break;
				case "salgado":
					System.out.println("Da-me jola");
				}
		
			}

			catch (IOException ioe) {
				System.out.println("Something went wrong, please try again!");
			}
		}
	}
}
