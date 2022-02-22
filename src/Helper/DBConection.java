package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConection {

	Connection c = null;

	public DBConection() {

	}

	public Connection ConnDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:2532/dershane?user=root&password=syso9697");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
