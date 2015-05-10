package com.cdio3.server.service;

import java.util.List;

import com.cdio3.client.service.DataService;
import com.cdio3.server.dal.IOperatorDAO;
import com.cdio3.server.dal.OperatorDAO;
import com.cdio3.server.dal.DALException;
import com.cdio3.shared.OperatorDTO;
import com.cdio3.shared.PasswordGenerator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.cdio3.shared.FieldVerifier;
import com.cdio3.shared.ServiceException;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	private static final long serialVersionUID = 1L;
	private int oprID;
	
	@Override
	public OperatorDTO createOperator(OperatorDTO operator) throws ServiceException {
		if (FieldVerifier.isValidCPR(operator.getCpr()) && FieldVerifier.isValidName(operator.getOprName())
				&& FieldVerifier.isValidInitials(operator.getIni())) {
			OperatorDAO dao = new OperatorDAO();

			operator.setPassword(PasswordGenerator.generatePassword());

			try {
				dao.createOperator(operator);
			} catch (DALException e) {
				throw new ServiceException("Database Error");
			}

		} else if(!FieldVerifier.isValidCPR(operator.getCpr())){
			throw new ServiceException("Ugyldigt CPR");
		} else if(!FieldVerifier.isValidInitials(operator.getIni())){
			throw new ServiceException("Ugyldige initialer");
		} else if(!FieldVerifier.isValidName(operator.getOprName())){
			throw new ServiceException("Ugyldigt navn");
		}
		return operator;
	}

	@Override
	public List<OperatorDTO> getAllOperators() throws ServiceException {
		IOperatorDAO dao = new OperatorDAO();
		List<OperatorDTO> operators = null;
		try {
			operators = dao.getOperatorList();
		} catch (DALException e) {
			throw new ServiceException("Database error");
		}
		return operators;
	}

	@Override
	public void updateOperator(OperatorDTO operator) throws ServiceException {
		if (FieldVerifier.isValidCPR(operator.getCpr()) && FieldVerifier.isValidName(operator.getOprName())
				&& FieldVerifier.isValidInitials(operator.getIni()) && FieldVerifier.isValidPassword(operator.getPassword())) {
			OperatorDAO dao = new OperatorDAO();
			try {
				dao.updateOperator(operator);
			} catch (DALException e) {
				throw new ServiceException("Database error");
			}
		} else if(!FieldVerifier.isValidCPR(operator.getCpr())){
			throw new ServiceException("Ugyldigt CPR");
		} else if(!FieldVerifier.isValidInitials(operator.getIni())){
			throw new ServiceException("Ugyldige initialer");
		} else if(!FieldVerifier.isValidName(operator.getOprName())){
			throw new ServiceException("Ugyldigt navn");
		} else if(!FieldVerifier.isValidPassword(operator.getPassword())){
			throw new ServiceException("Ugyldigt password");
		}
	}

	@Override
	public void deleteOperator(int id) throws ServiceException{
		OperatorDAO dao = new OperatorDAO();
		try {
			dao.deleteOperator(id);
		} catch (DALException e) {
			throw new ServiceException("Database error");
		}
	}

	@Override
	/**
	 * @param 
	 * @return int representing; 0 = wrong info , 1 = admin , 2 = user
	 */
	public Integer login(String userID, String password) throws ServiceException {
		OperatorDAO dao = new OperatorDAO();
		int oprID;
		OperatorDTO operator;
		try {
			oprID = Integer.parseInt(userID);
		} catch (NumberFormatException e) {
			throw new ServiceException("Ugyldigt ID");
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
		
		this.oprID = oprID;
		
		if (operator.getPassword().equals(password)) {
			return 2;
		} else
			return 0;
	}

	@Override
	public void changePassword(String passwordOld, String passwordNew, String passwordNewRepeat) throws ServiceException {
		OperatorDAO dao = new OperatorDAO();
		OperatorDTO operator;
		try {
			operator = dao.getOperator(oprID);
		} catch (DALException e) {
			throw new ServiceException("Database error");
		}
		
		if(operator == null){
			throw new ServiceException("");
		}
		
		if(!operator.getPassword().equals(passwordOld)){
			throw new ServiceException("Old password incorrect");
		}
		if(!FieldVerifier.isValidPassword(passwordNew)){
			throw new ServiceException("Not a valid password");
		}
		if(!passwordNew.equals(passwordNewRepeat)){
			throw new ServiceException("The password do not match");
		}
		
		operator.setPassword(passwordNew);
		try {
			dao.updateOperator(operator);
		} catch (DALException e) {
			throw new ServiceException("Database error");
		}
	}
	
	public double calculateNetto(double brutto, double tare){
		return brutto - tare;
	}
}
