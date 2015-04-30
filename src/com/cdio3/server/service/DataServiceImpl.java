package com.cdio3.server.service;

import java.util.List;

import com.cdio3.client.service.DataService;
import com.cdio3.server.PasswordGenerator;
import com.cdio3.server.dal.DALException;
import com.cdio3.server.dal.OperatorDAO;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class DataServiceImpl extends RemoteServiceServlet implements DataService{

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

}
