package com.cdio3.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cdio3.server.dal.DALException;
import com.cdio3.shared.OperatorDTO;

public class OperatorDAO implements IOperatorDAO {

	private PreparedStatement ps;
	private ResultSet rs;

	public OperatorDAO() {
		try {
			new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public OperatorDTO getOperator(int oprId) throws DALException {
		try {
			ps = Connector.prepare("SELECT opr_name, ini, cpr, password FROM operator WHERE opr_id = ?");
			ps.setInt(1, oprId);
			rs = ps.executeQuery();
			
			if (!rs.first()) {
				return null;
			} else {
				return new OperatorDTO(oprId, rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"),
					rs.getString("password"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public List<OperatorDTO> getOperatorList() throws DALException {
		List<OperatorDTO> list = new ArrayList<OperatorDTO>();
		try {
			rs = Connector.doQuery("SELECT opr_id, opr_name, ini, cpr, password FROM operator");
			while (rs.next()) {
				list.add(new OperatorDTO(rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"),
						rs.getString("password")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
	
	public void createOperator(OperatorDTO operator) throws DALException {
		try {
			ps = Connector.prepare("INSERT INTO operator(opr_id, opr_name, ini, cpr, password) VALUES " + "(?, ?, ?, ?, ?)");
			ps.setString(1, null);
			ps.setString(2, operator.getOprName());
			ps.setString(3, operator.getIni());
			ps.setString(4, operator.getCpr());
			ps.setString(5, operator.getPassword());
			ps.execute();
			operator.setOprID(Connector.getLastInsert(ps));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public void updateOperator(OperatorDTO operator) throws DALException {
		try {
			ps = Connector.prepare("UPDATE operator SET opr_name = ?, ini = ?, cpr = ?, password = ? WHERE opr_id = ?");
			ps.setString(1, operator.getOprName());
			ps.setString(2, operator.getIni());
			ps.setString(3, operator.getCpr());
			ps.setString(4, operator.getPassword());
			ps.setInt(5, operator.getOprID());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void deleteOperator(int id) throws DALException {
		try{
			ps = Connector.prepare("DELETE FROM operator WHERE opr_id = ?");
			ps.setInt(1, id);
			ps.execute();
		} catch(SQLException e){
			throw new DALException(e);
		}
		
	}

}
