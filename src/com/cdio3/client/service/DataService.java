package com.cdio3.client.service;

import java.util.List;

import com.cdio3.shared.OperatorDTO;
import com.cdio3.shared.ServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataConnection")
public interface DataService extends RemoteService {
	OperatorDTO createOperator(OperatorDTO operator) throws ServiceException;

	List<OperatorDTO> getAllOperators() throws ServiceException;

	void updateOperator(OperatorDTO operator) throws ServiceException;
	
	void deleteOperator(int id) throws ServiceException;
	
	Integer login(String userID, String password) throws ServiceException;

	void changePassword(String passwordOld, String passwordNew, String passwordNewRepeat) throws ServiceException;
	
	double calculateNetto(double brutto, double tare);
}
