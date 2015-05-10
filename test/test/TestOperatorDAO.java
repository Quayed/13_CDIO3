package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cdio3.server.dal.Connector;
import com.cdio3.server.dal.DALException;
import com.cdio3.server.dal.IOperatorDAO;
import com.cdio3.server.dal.OperatorDAO;
import com.cdio3.shared.OperatorDTO;

public class TestOperatorDAO{

	private static int insertID;
	
	private static IOperatorDAO oprDAO;
	private OperatorDTO oprDTO;
	
	@BeforeClass
	public static void connect() {
		
		ConnectorTest.connect();
		
		oprDAO = new OperatorDAO();

	}
	
	@Test
	public void getOperator() {
		try {
			oprDTO = oprDAO.getOperator(3);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(oprDTO != null);
	}
	
	@Test
	public void getOperatorList() {
		try {
			oprDAO.getOperatorList();
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void createUpdateOperator() {
		oprDTO = new OperatorDTO(0, "Don Juan", "DJ", "000000-0000", "iloveyou");
		try {
			oprDAO.createOperator(oprDTO);
			insertID = oprDTO.getOprID();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			oprDTO = oprDAO.getOperator(insertID);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		oprDTO.setIni("DoJu");
		try {
			oprDAO.updateOperator(oprDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(oprDTO.getIni(), oprDAO.getOperator(insertID).getIni());
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertNull(oprDAO.getOperator(insertID+1));
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM operator WHERE opr_id = "+insertID);
		} catch (DALException e) {}
		ConnectorTest.close();
	}

}
