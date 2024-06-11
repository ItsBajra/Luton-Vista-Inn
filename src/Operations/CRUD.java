package Operations;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pkg1.Global;

public class CRUD extends MySQLConnection { //Inheritance: Extending MySQLConnection
//login
	public boolean doLogin(User user) {
		String sql = "SELECT * FROM ClientProfile WHERE Email=? and Password=?";
		boolean result = false;
		try {
			// connect
			Connection conn = connect();
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getEmail());
			pstat.setString(2, user.getPassword());
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				user.setProfileID(rs.getInt("ProfileID"));
				Global.currentUser=user;
				result = true;
			}
			// select
			// return
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
		return result;
	}
	
	public boolean corporateLogin(User user) {
		String sql = "SELECT * FROM CorporateClientProfile WHERE Email=? and Password=?";
		boolean result = false;
		try {
			// connect
			Connection conn = connect();
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getEmail());
			pstat.setString(2, user.getPassword());
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				user.setProfileID(rs.getInt("ProfileID"));
				Global.currentUser=user;
				result = true;
			}
			// select
			// return
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
		return result;
	}
	
	public boolean receptionistLogin(User user) {
		String sql = "SELECT * FROM ReceptionistProfile WHERE Email=? and Password=?";
		boolean result = false;
		try {
			// connect
			Connection conn = connect();
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getEmail());
			pstat.setString(2, user.getPassword());
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				user.setReceptionistID(rs.getInt("ReceptionistID"));
				Global.currentUser=user;
				result = true;
			}
			// select
			// return
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
		return result;
	}
	
	public void insert(String FirstName, String LastName, String Email, String ContactNumber, String Address, String City, String ZIPCode, String Country, String Password, String CCNumber, String NameOnCard, String ExpiryDate, String CVVCode ) {
		final String sql = "INSERT INTO ClientProfile VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object
				pstat.setString(1, FirstName); // position; value
				pstat.setString(2, LastName);
				pstat.setString(3, Email);
				pstat.setString(4, ContactNumber);
				pstat.setString(5, Address);
				pstat.setString(6, City);
				pstat.setString(7, ZIPCode);
				pstat.setString(8, Country);
				pstat.setString(9, Password);
				pstat.setString(10, CCNumber);
				pstat.setString(11, NameOnCard);
				pstat.setString(12, ExpiryDate);
				pstat.setString(13, CVVCode);

				pstat.executeUpdate();// insert, update, delete
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				System.out.println("Profile Created successfully!");
			} else {
				System.out.println("Error to connect with database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
	}
	
	public void corporateinsert(String FirstName, String LastName, String Email, String CompanyName, String ContactNumber, String Address, String City, String ZIPCode, String Country, String Password, String CCNumber, String NameOnCard, String ExpiryDate, String CVVCode ) {
		final String sql = "INSERT INTO CorporateClientProfile VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object
				pstat.setString(1, FirstName); // position; value
				pstat.setString(2, LastName);
				pstat.setString(3, Email);
				pstat.setString(4, CompanyName);
				pstat.setString(5, ContactNumber);
				pstat.setString(6, Address);
				pstat.setString(7, City);
				pstat.setString(8, ZIPCode);
				pstat.setString(9, Country);
				pstat.setString(10, Password);
				pstat.setString(11, CCNumber);
				pstat.setString(12, NameOnCard);
				pstat.setString(13, ExpiryDate);
				pstat.setString(14, CVVCode);

				pstat.executeUpdate();// insert, update, delete
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				System.out.println("Profile Created successfully!");
			} else {
				System.out.println("Error to connect with database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
	}
	
	public void booking(String Email, Date CheckInDateSql, Date CheckOutDateSql , String RoomType) {
		final String sql = "INSERT INTO Reservation VALUES(0, ?, ?, ?, ?, ?, 0, 0);";
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object
				pstat.setString(1, Email); // position; value
				pstat.setDate(2, CheckInDateSql);
				pstat.setDate(3, CheckOutDateSql);
				pstat.setString(4, RoomType);
				pstat.setString(5, "Pending");

				pstat.executeUpdate();// insert, update, delete
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				System.out.println("Booking Request sent successfully!");
			} else {
				System.out.println("Error to connect with database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
	}
	
	
	public ArrayList<User> search(String Email) {
		
		ArrayList<User> bookings = new ArrayList<>();
		final String sql = "SELECT * FROM Reservation WHERE Email=?";
		
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object

				pstat.setString(1, Email); // position; value

				ResultSet rs = pstat.executeQuery();// select all, search
				while (rs.next()) {
	                User booking = new User(
	                    rs.getInt("BookingID"), rs.getString("Email"),
	                    rs.getDate("CheckInDate"), rs.getDate("CheckOutDate"),
	                    rs.getString("RoomType"), rs.getString("BookingStatus"),
	                    rs.getInt("RoomID"), rs.getInt("ReceptionistID")
	                );
	                bookings.add(booking); // Add each booking to the list
	            }
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				System.out.println("Search record successfully");
			} else {
				System.out.println("Error connecting to the database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
		return bookings;
	}
	
public ArrayList<User> DisplayAll() {
		
		ArrayList<User> bookings = new ArrayList<>();
		final String sql = "SELECT * FROM Reservation";
		
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object 

				ResultSet rs = pstat.executeQuery();// select all, search
				while (rs.next()) {
	                User booking = new User(
	                    rs.getInt("BookingID"), rs.getString("Email"),
	                    rs.getDate("CheckInDate"), rs.getDate("CheckOutDate"),
	                    rs.getString("RoomType"), rs.getString("BookingStatus"),
	                    rs.getInt("RoomID"), rs.getInt("ReceptionistID")
	                );
	                bookings.add(booking); // Add each booking to the list
	            }
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				System.out.println("Search record successfully");
			} else {
				System.out.println("Error connecting to the database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
		return bookings;
	}
	
	public boolean update(int BookingID, String RoomType, Date CheckInDate, Date CheckOutDate) {
		final String sql = "UPDATE Reservation SET RoomType=?, CheckInDate=?, CheckOutDate=? WHERE BookingID=?";
		boolean result = false;
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object

				pstat.setString(1, RoomType);
				pstat.setDate(2, CheckInDate);
				pstat.setDate(3, CheckOutDate);
				pstat.setInt(4,  BookingID);// position; value

				pstat.executeUpdate();// insert, update, delete
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				result = true;
				System.out.println("Update record successfully");
			} else {
				System.out.println("Error to connect with database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
		return result;
	}
	
	public boolean updateReceptionist(int BookingID, String BookingStatus, int Roomno, int ReceptionistID) {
		final String sql = "UPDATE Reservation SET BookingStatus=?, RoomID=?, ReceptionistID=? WHERE BookingID=?";
		boolean result = false;
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object

				pstat.setString(1, BookingStatus);
				pstat.setInt(2, Roomno);
				pstat.setInt(3, ReceptionistID);
				pstat.setInt(4, BookingID); // position; value

				pstat.executeUpdate();// insert, update, delete
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				result = true;
				System.out.println("Update record successfully");
			} else {
				System.out.println("Error to connect with database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
		return result;
	}
	
	public User searchID(int BookingID) {

		final String sql = "SELECT * FROM Reservation WHERE BookingID=?";
		User user = null;
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object

				pstat.setInt(1, BookingID); // position; value

				ResultSet rs = pstat.executeQuery();// select all, search
				while (rs.next()) { // if record found
					user = new User(rs.getInt("BookingID"), rs.getDate("CheckInDate"), rs.getDate("CheckOutDate"), rs.getString("RoomType"), rs.getString("BookingStatus"), rs.getInt("RoomID"));
				}
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				System.out.println("Record Searched successfully");
			} else {
				System.out.println("Error to connect with database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
		return user;
	}
	
	public boolean delete(int BookingID, String Email) {
		final String sql = "DELETE FROM Reservation WHERE BookingID=? AND Email=?";
		boolean result = false;
		try {
			Connection conn = connect();
			if (conn != null) {
				PreparedStatement pstat = conn.prepareStatement(sql);// declare and initialize Statement object

				pstat.setInt(1, BookingID);
				pstat.setString(2, Email);// position; value

				pstat.executeUpdate();// insert, update, delete
				pstat.close();// Close Statement
				conn.close(); // Close Connection
				result = true;
				System.out.println("Deleted the record successfully");
			} else {
				System.out.println("Error to connect with database");
			}
		} catch (Exception ex) {
			// error message
			System.out.println("Error : " + ex.getMessage());
		}
		return result;
	}
}
