package com.cdio3.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cdio3.server.dal.DALException;

public class Connector {

	private static Connection conn;
	private static Statement stm;

	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url
	 *            must have the form "jdbc:mysql://<server>/<database>" for
	 *            default port (3306) OR
	 *            "jdbc:mysql://<server>:<port>/<database>" for specific port
	 *            more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 */

	public static Connection connectToDatabase(String url, String username, String password) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}

	public Connector(String server, int port, String database, String username, String password) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		conn = connectToDatabase("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
		stm = conn.createStatement();
	}

	public Connector() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}

	public static ResultSet doQuery(String cmd) throws DALException {
		try {
			return stm.executeQuery(cmd);
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public static int doUpdate(String cmd) throws DALException {
		try {
			return stm.executeUpdate(cmd);
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public static PreparedStatement prepare(String sql) throws SQLException {
		return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}

	public static void close() throws SQLException {
		conn.close();
	}

	public static int getLastInsert(PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}

	// only for test
	public static void setAutoIncrement(String table, int autoIncrement) throws SQLException {
		conn.createStatement().execute("ALTER TABLE " + table + " AUTO_INCREMENT = " + autoIncrement);
	}
}