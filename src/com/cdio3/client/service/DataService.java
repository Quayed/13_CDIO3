package com.cdio3.client.service;

import java.util.List;

import com.cdio3.shared.DALException;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataConnection")
public interface DataService extends RemoteService {
	OperatorDTO createOperator(OperatorDTO operator) throws DALException;

	List<OperatorDTO> getAllOperators();

	void updateOperator(OperatorDTO operator);
	
	void deleteOperator(int id);
	
	Integer login(String userID, String password);

	void changePassword(String passwordOld, String passwordNew, String passwordNewRepeat) throws DALException;
}
