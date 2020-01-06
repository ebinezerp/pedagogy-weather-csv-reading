package pedagogy.csvfile_to_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DBConnection {

	private Connection connection;

	public Connection openConnection() throws SQLException {
		DriverManager.registerDriver(new Driver());
		return connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/weatherdb", "root", "root");
	}

	public void closeConnection() throws SQLException {
		connection.close();
	}

}
