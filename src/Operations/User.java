package Operations;

import java.sql.Date;

public class User {
	private int ProfileID;
	private String Email;
	private String Password;
	private int BookingID;
	private Date CheckInDate;
	private Date CheckOutDate;
	private String RoomType;
	private String BookingStatus;
	private int RoomID;
	private int ReceptionistID;
	
	//Constructors
	
	
	public User(int BookingID, String Email, Date CheckInDate, Date CheckOutDate, String RoomType, String BookingStatus, int RoomID, int ReceptionistID) {
		this.BookingID = BookingID;
		this.Email = Email;
		this.CheckInDate = CheckInDate;
		this.CheckOutDate = CheckOutDate;
		this.RoomType = RoomType;
		this.BookingStatus = BookingStatus;
		this.RoomID = RoomID;
		this.ReceptionistID = ReceptionistID;
	}

	public User(int ProfileID, String Email, String Password) {
		this.ProfileID = ProfileID;
		this.Email = Email;
		this.Password = Password;
	}

	public User(int BookingID, Date CheckInDate, Date CheckOutDate, String RoomType, String BookingStatus, int RoomID) {
		this.BookingID = BookingID;
		this.CheckInDate = CheckInDate;
		this.CheckOutDate = CheckOutDate;
		this.RoomType = RoomType;
		this.BookingStatus = BookingStatus;
		this.RoomID = RoomID;
	}

	//Getters and Setters
	public int getBookingID() {
		return BookingID;
	}

	public void setBookingID(int BookingID) {
		this.BookingID = BookingID;
	}
	
	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public Date getCheckInDate() {
		return CheckInDate;
	}

	public void setCheckInDate(Date CheckInDate) {
		this.CheckInDate = CheckInDate;
	}
	
	public Date getCheckOutDate() {
		return CheckOutDate;
	}

	public void setCheckOutDate(Date CheckOutDate) {
		this.CheckOutDate = CheckOutDate;
	}
	
	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String RoomType) {
		this.RoomType = RoomType;
	}
	
	public String getBookingStatus() {
		return BookingStatus;
	}

	public void setBookingStatus(String BookingStatus) {
		this.BookingStatus = BookingStatus;
	}
	
	public int getRoomID() {
		return RoomID;
	}

	public void setRoomID(int RoomID) {
		this.RoomID = RoomID;
	}
	
	public int getReceptionistID() {
		return ReceptionistID;
	}

	public void setReceptionistID(int ReceptionistID) {
		this.ReceptionistID = ReceptionistID;
	}
	
	public int getProfileID() {
		return ProfileID;
	}
	

	public void setProfileID(int ProfileID) {
		this.ProfileID = ProfileID;
		
	}
	
	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	@Override
	public String toString() {
		return "User [ProfileID=" + ProfileID + ", Email=" + Email + ", Password=" + Password + "]";
	}

}
