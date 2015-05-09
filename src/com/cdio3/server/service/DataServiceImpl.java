package com.cdio3.server.service;

import java.util.List;

import com.cdio3.client.service.DataService;
import com.cdio3.server.dal.OperatorDAO;
import com.cdio3.shared.DALException;
import com.cdio3.shared.OperatorDTO;
import com.cdio3.shared.PasswordGenerator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.cdio3.shared.FieldVerifier;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	private static final long serialVersionUID = 1L;

	@Override
	public OperatorDTO createOperator(OperatorDTO operator) throws DALException {
		if (FieldVerifier.isValidCPR(operator.getCpr()) && FieldVerifier.isValidName(operator.getOprName())
				&& FieldVerifier.isValidInitials(operator.getIni())) {
			OperatorDAO dao = new OperatorDAO();

			operator.setPassword(PasswordGenerator.generatePassword());

			dao.createOperator(operator);

		} else {
			// Do some error handling
		}
		return operator;
	}

	@Override
	public List<OperatorDTO> getAllOperators() {
		OperatorDAO dao = new OperatorDAO();
		List<OperatorDTO> operators = null;
		try {
			operators = dao.getOperatorList();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return operators;
	}

	@Override
	public void updateOperator(OperatorDTO operator)  {
		if (FieldVerifier.isValidCPR(operator.getCpr()) && FieldVerifier.isValidName(operator.getOprName())
				&& FieldVerifier.isValidInitials(operator.getIni()) && FieldVerifier.isValidPassword(operator.getPassword())) {
			OperatorDAO dao = new OperatorDAO();
			try {
				dao.updateOperator(operator);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// Do some error handling.
		}
	}

	@Override
	public void deleteOperator(int id) {
		OperatorDAO dao = new OperatorDAO();
		try {
			dao.deleteOperator(id);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * @param 
	 * @return int representing; 0 = wrong info , 1 = admin , 2 = user
	 */
	public Integer login(String userID, String password) {
		OperatorDAO dao = new OperatorDAO();
		int oprID;
		OperatorDTO operator;
		try {
			oprID = Integer.parseInt(userID);
		} catch (NumberFormatException e) {
			System.out.println("Ikke et gyldigt ID");
			return 0;
		}

		// is admin
		if (oprID == 10) {
			System.out.println("pass: " + password);
			if (password.equals("02324it!"))
				return 1;
			else
				return 0;
		}

		try {
			operator = dao.getOperator(oprID);
			if (operator == null) {
				return 0;
			}
		} catch (DALException e) {
			System.out.println("Datalag's fejl!");
			return 0;
		}

		if (operator.getPassword().equals(password)) {
			return 2;
		} else
			return 0;
	}
}
