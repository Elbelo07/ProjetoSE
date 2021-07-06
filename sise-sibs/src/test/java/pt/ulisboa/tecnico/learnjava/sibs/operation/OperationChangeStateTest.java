package pt.ulisboa.tecnico.learnjava.sibs.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Cancelled;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Completed;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Deposited;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Registered;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Withdrawn;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class OperationChangeStateTest {

	private static final String ADDRESS = "Ave.";
	private static final String PHONE_NUMBER = "987654321";
	private static final String NIF = "123456789";
	private static final String LAST_NAME = "Silva";
	private static final String FIRST_NAME = "António";
	private String sourceIban;
	private String targetIban;
	

	private Sibs sibs;
	private Bank sourceBank;
	private Bank targetBank;
	private Client sourceClient;
	private Client targetClient;
	private Services services;
	
	
	@Before
	public void setUp() throws Exception   {
		this.services = new Services();
		this.sibs = new Sibs(100, services);		
		this.sourceBank = new Bank("CGD");
		this.targetBank = new Bank("BPI");
		this.sourceClient = new Client(this.sourceBank, FIRST_NAME, LAST_NAME, NIF, PHONE_NUMBER, ADDRESS, 33);
		this.targetClient = new Client(this.targetBank, FIRST_NAME, LAST_NAME, NIF, PHONE_NUMBER, ADDRESS, 22);
	    sourceIban = sourceBank.createAccount(AccountType.CHECKING, sourceClient, 3000, 0);
	    targetIban = targetBank.createAccount(AccountType.CHECKING, targetClient, 3000, 0);
	    this.sibs.transfer(sourceIban, targetIban, 1000);
	}
	
	@Test
	public void checkRegistered() throws Exception {
		assertTrue(sibs.getOperation(0).getState() instanceof Registered);
	}
	
	@Test
	public void checkWithdrawn() throws SibsException, AccountException {
		sibs.getOperation(0).process(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Withdrawn);
		assertEquals(2000, this.services.getAccountByIban(sourceIban).getBalance());
	}
	
	@Test
	public void checkDeposited() throws SibsException, AccountException {
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).process(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Deposited);
		assertEquals(4000, this.services.getAccountByIban(targetIban).getBalance());
	}
	
	@Test
	public void checkCompleted() throws SibsException, AccountException {
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).process(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Completed);
		assertEquals(1980, this.services.getAccountByIban(sourceIban).getBalance());
		assertEquals(20, this.sibs.getOperation(0).commission());
	}
	
	@Test
	public void checkCancelledIfRegistered() throws SibsException, AccountException {
		sibs.getOperation(0).cancel(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Cancelled);
		assertEquals(3000, this.services.getAccountByIban(sourceIban).getBalance());
		assertEquals(3000, this.services.getAccountByIban(targetIban).getBalance());
	}
	
	@Test
	public void checkCancelledIfWithdrawn() throws SibsException, AccountException {
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).cancel(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Cancelled);
		assertEquals(3000, this.services.getAccountByIban(sourceIban).getBalance());
		assertEquals(3000, this.services.getAccountByIban(targetIban).getBalance());
	}
	
	@Test
	public void checkCancelledIfDeposited() throws SibsException, AccountException {
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).cancel(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Cancelled);
		assertEquals(3000, this.services.getAccountByIban(sourceIban).getBalance());
		assertEquals(3000, this.services.getAccountByIban(targetIban).getBalance());
	}
	
	@Test
	public void checkNoCancelledIfCompleted() throws SibsException, AccountException {
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).cancel(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Completed);
		assertEquals(1980, this.services.getAccountByIban(sourceIban).getBalance());
		assertEquals(20, this.sibs.getOperation(0).commission());
	}
	
	@Test
	public void checkNoChangeIfCancelled() throws SibsException, AccountException {
		sibs.getOperation(0).cancel(this.services);
		sibs.getOperation(0).process(this.services);
		sibs.getOperation(0).cancel(this.services);
		assertTrue(sibs.getOperation(0).getState() instanceof Cancelled);
		assertEquals(3000, this.services.getAccountByIban(sourceIban).getBalance());
		
	}
	

	@After
	public void tearDown() {
		Bank.clearBanks();
	}
}
