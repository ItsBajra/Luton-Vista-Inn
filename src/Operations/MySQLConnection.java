package Operations;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {

	final String DRIVER = "com.mysql.cj.jdbc.Driver"; // mysql driver
	final String HOST = "127.0.0.1";// ip address of computer where mysql is installed
	final int PORT = 3306; // default port no
	final String DBUSER = "root"; // super administrator
	final String DBPASS = "9803367955"; // blank
	final String DBNAME = "assignment2";
	final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, DBUSER, DBPASS);
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
		return conn;
	}
	public void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
	}
}

