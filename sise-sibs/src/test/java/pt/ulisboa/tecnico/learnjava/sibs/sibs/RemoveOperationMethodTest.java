package pt.ulisboa.tecnico.learnjava.sibs.sibs;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class RemoveOperationMethodTest {
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
	public void success() throws SibsException {
		this.sibs.removeOperation(0); //? a posi??o

		assertEquals(0, sibs.getNumberOfOperations());
	}

	@Test(expected = SibsException.class)
	public void negativePosition() throws SibsException {
		this.sibs.getOperation(-1);
	}

	@Test(expected = SibsException.class)
	public void positionAboveLength() throws SibsException {
		this.sibs.getOperation(4);
	}

	@After
	public void tearDown() {
		this.sibs = null;
	}

}
