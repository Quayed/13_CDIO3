package com.cdio3.server.service;

import java.util.List;

import org.eclipse.jdt.core.dom.PostfixExpression.Operator;

import com.cdio3.client.service.DataService;
import com.cdio3.server.PasswordGenerator;
import com.cdio3.server.dal.DALException;
import com.cdio3.server.dal.OperatorDAO;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	@Override
	public String sayHello(String message) {
		System.out.println(message);
		return message.toUpperCase();
	}

	@Override
	public OperatorDTO createOperator(OperatorDTO operator) {
		String newPassword;
		newPassword = PasswordGenerator.generatePassword();
		operator.setPassword(newPassword);
		OperatorDAO dao = new OperatorDAO();
		try {
			operator = dao.createOperator(operator);
		} catch (DALException e) {
			e.printStackTrace();
			// Something needs to happen here - don't know what tho.
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
	public void updateOperator(OperatorDTO operator) {
		OperatorDAO dao = new OperatorDAO();
		System.out.println(dao);
		try {
			dao.updateOperator(operator);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		try {
			OperatorDTO operator = dao.getOperator(Integer.parseInt(userID));
			if(operator.getPassword().equals(password)){
				if(Integer.parseInt(userID) == 10){
					return 1;
				} else return 2;
			} else return 0;
		} catch (NumberFormatException e) {
			System.out.println("Ikke et gyldigt ID");
			e.printStackTrace();
		} catch (DALException e) {
			System.out.println("Datalag's fejl!");
			e.printStackTrace();
		}
		return -1;
	}
}
