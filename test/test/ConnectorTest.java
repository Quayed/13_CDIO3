package test;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

import com.cdio3.server.dal.Connector;

public class ConnectorTest {

	@Test
	public static void connect() {
		try {
			new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public static void close() {
		try {
			Connector.close();
		} catch (SQLException e) {}
	}
	
}
