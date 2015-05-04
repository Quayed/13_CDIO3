package com.cdio3.server.dal;

import java.util.List;

import com.cdio3.shared.DALException;
import com.cdio3.shared.OperatorDTO;

public interface IOperatorDAO {
	OperatorDTO getOperator(int oprId) throws DALException;

	List<OperatorDTO> getOperatorList() throws DALException;

	OperatorDTO createOperator(OperatorDTO opr) throws DALException;

	void updateOperator(OperatorDTO opr) throws DALException;
	
	void deleteOperator(int id) throws DALException;
}
