package pt.ulisboa.tecnico.learnjava.sibs.sibs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class AddOperationMethodTest {
	private static final String TARGET_IBAN = "TargetIban";
	private static final String SOURCE_IBAN = "SourceIban";
	private static final int VALUE = 100;
	private Sibs sibs;
	private int operation;
	
	
	@Before
	public void setUp() throws OperationException, SibsException {
		this.sibs = new Sibs(3, new Services());
		operation = this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
	}
	
	@Test
	public void addOperationMethod() throws SibsException, OperationException {	
		assertEquals(TARGET_IBAN, this.sibs.getOperation(operation).getTargetIban());
		assertEquals(SOURCE_IBAN, this.sibs.getOperation(operation).getSourceIban());
		assertEquals(VALUE, this.sibs.getOperation(operation).getValue());
	}
	
	@Test(expected = SibsException.class)
	public void positionAboveLength() throws SibsException, OperationException {
		int operation2 = this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		int operation3 = this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		int operation4 = this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
	}
	

}
