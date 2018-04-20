package rodeo.agile.impress.pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ZaikoDao {
	
	String dbPath;
	
	public ZaikoDao(String path) {
		this.dbPath = path;
	}

	public void insert(String name, int amount) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbPath);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("INSERT INTO zaiko (name , amount) VALUES ('" + name + "', " + amount + ");");
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw e;
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
}
