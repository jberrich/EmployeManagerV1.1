package ma.jberrich.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.dbcp.BasicDataSource;

public class DataSource {

	private static DataSource datasource;
	private BasicDataSource ds;

	private DataSource() throws IOException, SQLException, PropertyVetoException {
		ds = new BasicDataSource();
		Configuration configuration = ConfigFactory.create(Configuration.class);
		ds.setDriverClassName(configuration.getDriver());
		ds.setUsername(configuration.getUsername());
		ds.setPassword(configuration.getPassword());
		ds.setUrl(configuration.getUrl());
	}

	public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if(datasource == null) {
			datasource = new DataSource();
			return datasource;
		}else {
			return datasource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.ds.getConnection();
	}

	protected void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {}
		}
	}
	
}
