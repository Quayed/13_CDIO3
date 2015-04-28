package com.cdio3.server.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.cdio3.shared.OperatorDTO;

public class OperatorDAO implements IOperatorDAO {
	
	private PreparedStatement ps;
	private ResultSet rs;
	
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
	
	public void createOperator(OperatorDTO opr) throws DALException {
		try {
			ps = Connector.prepare("INSERT INTO operator(opr_id, opr_name, ini, cpr, password) VALUES " + "(?, ?, ?, ?, ?)");
			ps.setString(1, null);
			ps.setString(2, opr.getOprName());
			ps.setString(3, opr.getIni());
			ps.setString(4, opr.getCpr());
			ps.setString(5, opr.getPassword());
			ps.execute();
			opr.setOprID(Connector.getLastInsert(ps));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public void updateOperator(OperatorDTO opr) throws DALException {
		try {
			ps = Connector.prepare("UPDATE operator SET opr_name = ?, ini = ?, cpr = ?, password = ? WHERE opr_id = ?");
			ps.setString(1, opr.getOprName());
			ps.setString(2, opr.getIni());
			ps.setString(3, opr.getCpr());
			ps.setString(4, opr.getPassword());
			ps.setInt(5, opr.getOprID());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
