package pt.ulisboa.tecnico.learnjava.sibs.sibs;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class GetNumberOfOperationsMethodTest {
	private static final String TARGET_IBAN = "TargetIban";
	private static final String SOURCE_IBAN = "SourceIban";
	private static final int VALUE = 100;
	private Sibs sibs;
	
	
	@Before
	public void setUp() throws OperationException, SibsException {
		this.sibs = new Sibs(3, new Services());
		this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
	}


	@Test
	public void getNumberOperationMethodAdd() throws SibsException, OperationException {
		assertEquals(1, sibs.getNumberOfOperations());
	}
	
	@Test
	public void getNumberOperationMethodRemove() throws SibsException, OperationException {
		this.sibs.removeOperation(0);

		assertEquals(0, sibs.getNumberOfOperations());
	}
	
	
	
	

}
