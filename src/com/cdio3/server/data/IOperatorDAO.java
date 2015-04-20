package com.cdio3.server.data;

import java.util.List;

public interface IOperatorDAO {
	OperatorDTO getOperatoer(int oprId) throws DALException;

	List<OperatorDTO> getOperatorList() throws DALException;

	void createOperator(OperatorDTO opr) throws DALException;

	void updateOperator(OperatorDTO opr) throws DALException;
}
