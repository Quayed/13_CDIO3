package com.cdio3.server.service;

import java.util.List;

import com.cdio3.client.service.DataService;
import com.cdio3.server.dal.OperatorDAO;
import com.cdio3.shared.DALException;
import com.cdio3.shared.OperatorDTO;
import com.cdio3.shared.PasswordGenerator;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	@Override
	public String sayHello(String message) {
		System.out.println(message);
		return message.toUpperCase();
	}

	@Override
	public OperatorDTO createOperator(OperatorDTO operator) throws DALException {
		String newPassword;
		newPassword = PasswordGenerator.generatePassword();
		operator.setPassword(newPassword);
		OperatorDAO dao = new OperatorDAO();
		operator = dao.createOperator(operator);

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
	public boolean login(String userName, String password) {
		return true;
	/*	if(userName.equals("10") && password.equals("02324it!")){
			return true;
		}
		return false;
*/	}

}
