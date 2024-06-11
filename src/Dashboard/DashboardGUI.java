package Dashboard;


import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import pkg1.Global;
import pkg1.HomeGUI;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import Operations.CRUD;
import Operations.User;

import java.awt.Panel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class DashboardGUI extends JFrame { //Inheritance - Extending JFrame
	
	//GUI Components and data fields
	private JPanel contentPane;
	private Image logo = new ImageIcon(DashboardGUI.class.getResource("/resources/logo (1).png")).getImage().getScaledInstance(160, 151, Image.SCALE_SMOOTH);
	private JDateChooser CheckInDate;
	private JDateChooser CheckOutDate;
	private JDateChooser CheckInDate1;
	private JDateChooser CheckOutDate1;
	JTable tblBookingInfo;
	private JTextField txtBookingID;
	private JTextField txtBookingIDCancel;
	
	//constructor
	public DashboardGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel(); 
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Top Right X Button
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to close this app?", "Confirm", JOptionPane.YES_NO_OPTION)== 0) {
					DashboardGUI.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(Color.GREEN);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.WHITE);
			}
		});
		
		//Abstraction: Creating a CardLayout for switching between panels and holding content in different panels
		CardLayout cardLayout = new CardLayout(); 
		
		//Main panel for cardLayout
		Panel cardPanel = new Panel();
		cardPanel.setBackground(new Color(0, 128, 0));
		cardPanel.setBounds(291, 30, 599, 548);
		contentPane.add(cardPanel);
		cardPanel.setLayout(cardLayout);
		
		
		//Home panel 
		JPanel pnlHomeMain = new JPanel();
		pnlHomeMain.setBackground(new Color(34, 139, 34));
		pnlHomeMain.setBounds(280, 29, 610, 560);
		pnlHomeMain.setLayout(null);
		pnlHomeMain.setVisible(true);
		cardPanel.add(pnlHomeMain, "1"); //switch to home panel
		
		//Booking panel
		JPanel pnlBookingMain = new JPanel();
		pnlBookingMain.setBackground(new Color(34, 139, 34));
		pnlBookingMain.setBounds(280, 29, 610, 560);
		pnlBookingMain.setLayout(null);
		pnlBookingMain.setVisible(true);
		cardPanel.add(pnlBookingMain, "2"); //switch to booking panel
		
		/**JPanel pnlViewBookings = new JPanel(); 
		pnlViewBookings.setBackground(new Color(34, 139, 34));
		pnlViewBookings.setBounds(280, 29, 610, 560);
		pnlViewBookings.setLayout(null);
		pnlViewBookings.setVisible(true);
		cardPanel.add(pnlViewBookings, "3"); **/
		
		CheckInDate = new JDateChooser();
		CheckInDate.setBounds(175, 118, 191, 20);
		CheckInDate.setMinSelectableDate(new Date()); //sets the minimum check in date to todays date
		pnlBookingMain.add(CheckInDate);
		
		//adding property change listener to make the checkoutdate atleast one day after checkindate
		CheckInDate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	CheckOutDateUpdater();
            }
        });
		
		JLabel lblCheckinDate = new JLabel("Select Check-In Date:");
		lblCheckinDate.setForeground(Color.WHITE);
		lblCheckinDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblCheckinDate.setBounds(10, 118, 139, 20);
		pnlBookingMain.add(lblCheckinDate);
		
		JLabel lblSelectCheckoutDate = new JLabel("Select Check-Out Date:");
		lblSelectCheckoutDate.setForeground(Color.WHITE);
		lblSelectCheckoutDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblSelectCheckoutDate.setBounds(10, 149, 146, 20);
		pnlBookingMain.add(lblSelectCheckoutDate);
		
		CheckOutDate = new JDateChooser();
		CheckOutDate.setBounds(175, 149, 191, 20);
		pnlBookingMain.add(CheckOutDate);
		
		JLabel lblSelectRoomType = new JLabel("Select Room Type:");
		lblSelectRoomType.setForeground(Color.WHITE);
		lblSelectRoomType.setFont(new Font("Arial", Font.BOLD, 13));
		lblSelectRoomType.setBounds(10, 25, 139, 20);
		pnlBookingMain.add(lblSelectRoomType);
		
		JLabel lblPriceOfThe = new JLabel("Price of the Room:");
		lblPriceOfThe.setForeground(Color.WHITE);
		lblPriceOfThe.setFont(new Font("Arial", Font.BOLD, 13));
		lblPriceOfThe.setBounds(10, 56, 139, 20);
		pnlBookingMain.add(lblPriceOfThe);
		
		JLabel lblPrice = new JLabel("");
		lblPrice.setOpaque(true);
		lblPrice.setBackground(Color.WHITE);
		lblPrice.setBorder(null);
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setFont(new Font("Arial", Font.BOLD, 13));
		lblPrice.setBounds(175, 56, 191, 20);
		pnlBookingMain.add(lblPrice);
		
		//Creating an array of room types for the combo box
		String[] roomTypes = {"Single Room", "Double Room", "Twin Room", "Family Room"}; 
		JComboBox cmbRoom = new JComboBox(roomTypes);
		cmbRoom.setBounds(175, 23, 191, 22);
		pnlBookingMain.add(cmbRoom); 
		
		JLabel lblSelectTheServices = new JLabel("Services Included in the Room");
		lblSelectTheServices.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheServices.setForeground(Color.WHITE);
		lblSelectTheServices.setFont(new Font("Arial", Font.BOLD, 13));
		lblSelectTheServices.setBounds(10, 201, 356, 20);
		pnlBookingMain.add(lblSelectTheServices);
	
		Panel panel = new Panel();
		panel.setBackground(new Color(189, 183, 107));
		panel.setBounds(372, 0, 227, 548);
		pnlBookingMain.add(panel);
		panel.setLayout(null);
		
		//Creating a jlabel and jtextpane that displays the details of the room
		JLabel lblNewLabel = new JLabel("Room Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 3, 178, 26);
		panel.add(lblNewLabel);
		
		JTextPane txtRoomDetails = new JTextPane();
		txtRoomDetails.setFont(new Font("Arial", Font.PLAIN, 11));
		txtRoomDetails.setText("SINGLE ROOM: Queen Size Bed\r\n\r\nDOUBLE ROOM: King Size Bed\r\n\r\nTWIN ROOM: Two Queen Size Bed\r\n\r\nFAMILY ROOM: Three Queen Size Bed (Customizable)");
		txtRoomDetails.setBounds(10, 28, 207, 110);
		panel.add(txtRoomDetails);
		txtRoomDetails.setEditable(false);
		
		//Creating a jlabel and jtextpane that displays the details of the services included in the room
		JLabel lblServiceDetails = new JLabel("Service Details");
		lblServiceDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceDetails.setBounds(20, 170, 178, 26);
		panel.add(lblServiceDetails);
		
		JTextPane txtpnFitnessCenterIncludes = new JTextPane();
		txtpnFitnessCenterIncludes.setText("FITNESS CENTER: Includes GYM and Yoga center\r\n\r\nSWIMMING POOL: Access to 4FT - 12FT Pool \r\n\r\nMASSAGE CENTER: Includes 1 Free Massage for each client\r\n\r\nROOM SERVICES: Includes Housekeeping, In Room dining and Laundry Services\r\n\r\nTRANSPORTATION: Airport Pick Up and Drop Off Service\r\n\r\nCHILDREN PARK: A small play area with game zone and trampoline park for children.\r\n\r\nINFO: Please reach out to the Reception for additional services. Extra charges will be applied.");
		txtpnFitnessCenterIncludes.setFont(new Font("Arial", Font.PLAIN, 11));
		txtpnFitnessCenterIncludes.setEditable(false);
		txtpnFitnessCenterIncludes.setBounds(10, 201, 207, 336);
		panel.add(txtpnFitnessCenterIncludes);
		
		JLabel lblRoomCapacity = new JLabel("Room Capacity:");
		lblRoomCapacity.setForeground(Color.WHITE);
		lblRoomCapacity.setFont(new Font("Arial", Font.BOLD, 13));
		lblRoomCapacity.setBounds(10, 87, 139, 20);
		pnlBookingMain.add(lblRoomCapacity);
		
		JLabel lblCapacity = new JLabel("");
		lblCapacity.setOpaque(true);
		lblCapacity.setForeground(Color.BLACK);
		lblCapacity.setFont(new Font("Arial", Font.BOLD, 13));
		lblCapacity.setBorder(null);
		lblCapacity.setBackground(Color.WHITE);
		lblCapacity.setBounds(175, 87, 191, 20);
		pnlBookingMain.add(lblCapacity);
		
		//Submit button to submit the booking request
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBorder(null);
		btnSubmit.setBackground(new Color(107, 142, 35));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 13));
		btnSubmit.setBounds(108, 430, 160, 29);
		pnlBookingMain.add(btnSubmit); //Polymorphism : Adding a button for booking
		
		JTextPane txtServiceIncluded = new JTextPane();
		txtServiceIncluded.setText("");
		txtServiceIncluded.setFont(new Font("Arial", Font.PLAIN, 11));
		txtServiceIncluded.setEditable(false);
		txtServiceIncluded.setBounds(130, 232, 111, 187);
		pnlBookingMain.add(txtServiceIncluded);
		
		//mouse listener for submit button 
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnSubmit.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String Email = Global.currentUser.getEmail();
				String RoomType = (String) cmbRoom.getSelectedItem();
				java.util.Date CheckInDate1 = CheckInDate.getDate();
				java.sql.Date CheckInDateSql = new java.sql.Date(CheckInDate1.getTime());
				java.util.Date CheckOutDate1 = CheckOutDate.getDate();
				java.sql.Date CheckOutDateSql = new java.sql.Date(CheckOutDate1.getTime());
				
				if (JOptionPane.showConfirmDialog(null, "Do you really want to send a booking request?", "Confirm", JOptionPane.OK_OPTION)== 0) {
					new CRUD().booking(Email, CheckInDateSql, CheckOutDateSql, RoomType);
					}
			}
		});
		
		//action listener for combobox to display the price, capacity and services included in the room
		cmbRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedRoom = (String) cmbRoom.getSelectedItem();

				if (selectedRoom == "Single Room") {
					lblCapacity.setText("2 people");
					lblPrice.setText("$100 per night");
					txtServiceIncluded.setText("Fitness Center\r\n\r\nSwimming Pool\r\n\r\nRoom Services");
				}
				
				else if (selectedRoom == "Double Room") {
					lblPrice.setText("$150 per night");
					lblCapacity.setText("2 people");
					txtServiceIncluded.setText("Fitness Center\r\n\r\nSwimming Pool\r\n\r\nRoom Services\r\n\r\nMassage Center");
				}
				
				else if (selectedRoom == "Twin Room") {
					lblPrice.setText("$180 per night");
					lblCapacity.setText("4 people");
					txtServiceIncluded.setText("Fitness Center\r\n\r\nSwimming Pool\r\n\r\nRoom Services\r\n\r\nMassage Center\r\n\r\nTransportation");
				}
				
				else if (selectedRoom == "Family Room") {
					lblPrice.setText("$200 per night");
					lblCapacity.setText("6 people");
					txtServiceIncluded.setText("Fitness Center\r\n\r\nSwimming Pool\r\n\r\nRoom Services\r\n\r\nMassage Center\r\n\r\nTransportation\r\n\r\nChildren's Play Area");
				}
			}
			
		});
		
		//menu panel displays all the panels/buttons to change the panel and other functions
		JPanel menuPanel = new JPanel();
		menuPanel.setOpaque(false);
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuPanel.setBounds(-1, -6, 271, 614);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(10, 25, 251, 156);
		lblLogo.setIcon(new ImageIcon(logo));
		menuPanel.add(lblLogo);
		
		//mouse listener for pnlHome to switch to Home panel
		JPanel pnlHome = new JPanel();
		pnlHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlHome.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pnlHome.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardPanel, "1");
				updateBookingTable(); //updates the booking table
			}
		});
		pnlHome.setForeground(Color.WHITE);
		pnlHome.setFont(new Font("Arial", Font.BOLD, 13));
		pnlHome.setBackground(new Color(107, 142, 35));
		pnlHome.setBorder(null);
		pnlHome.setBounds(1, 203, 269, 39);
		menuPanel.add(pnlHome);
		pnlHome.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setForeground(Color.WHITE);
		lblHome.setFont(new Font("Arial", Font.BOLD, 13));
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setBounds(10, 11, 249, 14);
		pnlHome.add(lblHome);
		
		//Mouse listener for pnlBooking to switch to Booking Panel
		JPanel pnlBooking = new JPanel();
		pnlBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlBooking.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pnlBooking.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardPanel, "2");
			}
		});
		pnlBooking.setForeground(Color.WHITE);
		pnlBooking.setFont(new Font("Arial", Font.BOLD, 13));
		pnlBooking.setBorder(null);
		pnlBooking.setBackground(new Color(107, 142, 35));
		pnlBooking.setBounds(1, 244, 269, 39);
		menuPanel.add(pnlBooking);
		pnlBooking.setLayout(null);
		
		JLabel lblBooking = new JLabel("Booking");
		lblBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblBooking.setForeground(Color.WHITE);
		lblBooking.setFont(new Font("Arial", Font.BOLD, 13));
		lblBooking.setBounds(10, 11, 249, 14);
		pnlBooking.add(lblBooking);
		
		//table for viewing the bookings 
		DefaultTableModel tableModel = new DefaultTableModel();
		tblBookingInfo = new JTable(new DefaultTableModel());
		tblBookingInfo.setDefaultEditor(Object.class, null);

				String[] tableColumns = {
			            "BookingID", "Email", "CheckInDate", "CheckOutDate",
			            "RoomType", "BookingStatus", "RoomID", "ReceptionistID"
			        };
			        tableModel = new DefaultTableModel(tableColumns, 0);
		tblBookingInfo.setOpaque(false);
		tblBookingInfo.setFont(new Font("Arial", Font.PLAIN, 9));
		JScrollPane scrollPane = new JScrollPane(tblBookingInfo);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(10, 274, 579, 263);
		pnlHomeMain.add(scrollPane); //Encapsulation: using a jtable to display data
		
		//adding the data to the table 
		String Email = Global.currentUser.getEmail();
		ArrayList<User> viewBooking = new CRUD().search(Email);
		for (User booking : viewBooking) {
		    Object[] rowData = {
		        booking.getBookingID(), booking.getEmail(), booking.getCheckInDate(),
		        booking.getCheckOutDate(), booking.getRoomType(), booking.getBookingStatus(),
		        booking.getRoomID(), booking.getReceptionistID()
		    };
		    tableModel.addRow(rowData);
		}
		tblBookingInfo.setModel(tableModel);
		JTableHeader tableHeader = tblBookingInfo.getTableHeader();
		tableHeader.setFont(new Font("Arial", Font.BOLD, 9));

		//jlabels
		JLabel lblWelcomeToLuton = new JLabel("Welcome To Luton Vista Inn!");
		lblWelcomeToLuton.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToLuton.setForeground(Color.WHITE);
		lblWelcomeToLuton.setFont(new Font("Gill Sans MT", Font.PLAIN, 26));
		lblWelcomeToLuton.setBounds(20, 30, 569, 47);
		pnlHomeMain.add(lblWelcomeToLuton);
		
		JLabel lblThankYouFor = new JLabel(" Where Your Comfort is Our Sole Priority.");
		lblThankYouFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblThankYouFor.setForeground(Color.WHITE);
		lblThankYouFor.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblThankYouFor.setBounds(20, 92, 569, 30);
		pnlHomeMain.add(lblThankYouFor);
		
		JLabel lblYourJourneyTo = new JLabel("Your Journey to Unmatched Hospitality Begins Here.");
		lblYourJourneyTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourJourneyTo.setForeground(Color.WHITE);
		lblYourJourneyTo.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblYourJourneyTo.setBounds(20, 119, 569, 30);
		pnlHomeMain.add(lblYourJourneyTo);
		
		JLabel lblViewBooking = new JLabel("View your Bookings Here");
		lblViewBooking.setBounds(174, 249, 249, 14);
		pnlHomeMain.add(lblViewBooking);
		lblViewBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewBooking.setForeground(Color.WHITE);
		lblViewBooking.setFont(new Font("Arial", Font.BOLD, 13));
		
		JPanel pnlUpdate = new JPanel();
		pnlUpdate.setBackground(new Color(34, 139, 34));
		cardPanel.add(pnlUpdate, "3");
		pnlUpdate.setLayout(null);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the Booking ID you want to Update:");
		lblPleaseEnterThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterThe.setForeground(Color.WHITE);
		lblPleaseEnterThe.setFont(new Font("Arial", Font.BOLD, 13));
		lblPleaseEnterThe.setBounds(112, 54, 367, 14);
		pnlUpdate.add(lblPleaseEnterThe);
		
		txtBookingID = new JTextField();
		txtBookingID.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookingID.setBounds(193, 79, 217, 20);
		pnlUpdate.add(txtBookingID);
		txtBookingID.setColumns(10);
		
		JLabel lblUpdateYourBooking_1 = new JLabel("Update your booking here");
		lblUpdateYourBooking_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateYourBooking_1.setForeground(Color.WHITE);
		lblUpdateYourBooking_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblUpdateYourBooking_1.setBounds(193, 11, 217, 14);
		pnlUpdate.add(lblUpdateYourBooking_1);
		
		JLabel lblSelectRoomType_1 = new JLabel("Select Room Type:");
		lblSelectRoomType_1.setForeground(Color.WHITE);
		lblSelectRoomType_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblSelectRoomType_1.setBounds(112, 144, 146, 20);
		pnlUpdate.add(lblSelectRoomType_1);
		
		JComboBox cmbRoom_1 = new JComboBox(roomTypes);
		cmbRoom_1.setBounds(268, 143, 191, 22);
		pnlUpdate.add(cmbRoom_1);
		
		JLabel lblCheckinDate_1 = new JLabel("Select Check-In Date:");
		lblCheckinDate_1.setForeground(Color.WHITE);
		lblCheckinDate_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblCheckinDate_1.setBounds(112, 189, 146, 20);
		pnlUpdate.add(lblCheckinDate_1);
		
		CheckInDate1 = new JDateChooser();
		CheckInDate1.setBounds(268, 189, 191, 20);
		CheckInDate1.setMinSelectableDate(new Date()); //sets the checkindate atleast todays date
		pnlUpdate.add(CheckInDate1);
		
		//adding property change listener to make the checkoutdate atleast one day after checkindate
		CheckInDate1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	CheckOutDateUpdater1();
            }
        });
		
		JLabel lblSelectCheckoutDate1 = new JLabel("Select Check-Out Date:");
		lblSelectCheckoutDate1.setForeground(Color.WHITE);
		lblSelectCheckoutDate1.setFont(new Font("Arial", Font.BOLD, 13));
		lblSelectCheckoutDate1.setBounds(112, 229, 146, 20);
		pnlUpdate.add(lblSelectCheckoutDate1);
		
		CheckOutDate1 = new JDateChooser();
		CheckOutDate1.setBounds(268, 229, 191, 20);
		pnlUpdate.add(CheckOutDate1);

		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(new Color(107, 142, 35));
		btnUpdate.setBounds(222, 299, 146, 29);
		pnlUpdate.add(btnUpdate);
		
		JLabel lblallFieldMust = new JLabel("**All field must be filled**");
		lblallFieldMust.setHorizontalAlignment(SwingConstants.CENTER);
		lblallFieldMust.setForeground(Color.WHITE);
		lblallFieldMust.setFont(new Font("Arial", Font.ITALIC, 11));
		lblallFieldMust.setBounds(112, 274, 367, 14);
		pnlUpdate.add(lblallFieldMust);
		
		JPanel pnlCancel = new JPanel();
		pnlCancel.setBackground(new Color(34, 139, 34));
		cardPanel.add(pnlCancel, "4");
		pnlCancel.setLayout(null);
		
		JLabel lblUpdateYourBooking_1_1 = new JLabel("Cancel your booking here");
		lblUpdateYourBooking_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateYourBooking_1_1.setForeground(Color.WHITE);
		lblUpdateYourBooking_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblUpdateYourBooking_1_1.setBounds(188, 11, 217, 14);
		pnlCancel.add(lblUpdateYourBooking_1_1);
		
		JLabel lblPleaseEnterThe_2 = new JLabel("Please enter the Booking ID you want to Cancel:");
		lblPleaseEnterThe_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterThe_2.setForeground(Color.WHITE);
		lblPleaseEnterThe_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblPleaseEnterThe_2.setBounds(111, 50, 367, 14);
		pnlCancel.add(lblPleaseEnterThe_2);
		
		txtBookingIDCancel = new JTextField();
		txtBookingIDCancel.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookingIDCancel.setColumns(10);
		txtBookingIDCancel.setBounds(188, 75, 217, 20);
		pnlCancel.add(txtBookingIDCancel);
		
		//JButton to cancel a booking
		JButton btnCancel = new JButton("Cancel Booking");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Arial", Font.BOLD, 12));
		btnCancel.setBorder(null);
		btnCancel.setBackground(new Color(107, 142, 35));
		btnCancel.setBounds(220, 134, 146, 29);
		pnlCancel.add(btnCancel);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancel.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancel.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to cancel the booking?", "Confirm", JOptionPane.YES_NO_OPTION)== 0) {
					int BookingID = Integer.parseInt(txtBookingIDCancel.getText());
					String Email = Global.currentUser.getEmail(); //gets email of the current user
					
					   //send to library to update
					   boolean result = new CRUD().delete(BookingID, Email);// sends the booking id and the email to CRUD
					   if(result==true) {
						   JOptionPane.showMessageDialog(DashboardGUI.this, "Booking Canceled successfully");
					        clearAll();
					   }
					   else {
						   JOptionPane.showMessageDialog(DashboardGUI.this, "Error to Cancel the Booking");
					        clearAll();
					   }
				}
			}

			private void clearAll() {
				txtBookingIDCancel.setText("");
				
			}
		});
		
		//mouse listener for update button to update bookings 
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnUpdate.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnUpdate.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				 int BookingID = Integer.parseInt(txtBookingID.getText());
				 String RoomType = (String) cmbRoom_1.getSelectedItem();
				 //converting java.util.Date to java.sql.Date
				 java.util.Date CheckInDate2 = CheckInDate1.getDate();
				 java.sql.Date CheckInDateSql = new java.sql.Date(CheckInDate2.getTime());
				 java.util.Date CheckOutDate2 = CheckOutDate1.getDate();
				 java.sql.Date CheckOutDateSql = new java.sql.Date(CheckOutDate2.getTime());
				   
				   //send to library to update
				   boolean result = new CRUD().update(BookingID, RoomType, CheckInDateSql, CheckOutDateSql);
				   if(result==true) {
					   JOptionPane.showMessageDialog(DashboardGUI.this, "Record update successfully");
				        clearAll();
				   }
				   else {
					   JOptionPane.showMessageDialog(DashboardGUI.this, "Error to update record");
				        clearAll();
				   }
			}

			private void clearAll() {
				
				txtBookingID.setText("");
				cmbRoom_1.setSelectedItem(null);
				CheckInDate.setDate(null);
				CheckOutDate.setDate(null);
			}
		});

		//jpanel to switch to Update booking panel
		JPanel pnlUpdateYourBooking = new JPanel();
		pnlUpdateYourBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlUpdateYourBooking.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pnlUpdateYourBooking.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardPanel, "3");
			}
		});
		pnlUpdateYourBooking.setForeground(Color.WHITE);
		pnlUpdateYourBooking.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdateYourBooking.setBorder(null);
		pnlUpdateYourBooking.setBackground(new Color(107, 142, 35));
		pnlUpdateYourBooking.setBounds(1, 286, 269, 39);
		menuPanel.add(pnlUpdateYourBooking);
		pnlUpdateYourBooking.setLayout(null);
		
		JLabel lblUpdateYourBooking = new JLabel("Update your booking");
		lblUpdateYourBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateYourBooking.setForeground(Color.WHITE);
		lblUpdateYourBooking.setFont(new Font("Arial", Font.BOLD, 13));
		lblUpdateYourBooking.setBounds(10, 11, 249, 14);
		pnlUpdateYourBooking.add(lblUpdateYourBooking);
		
		//jpanel to signout upon clicking it
		JPanel pnlSignOut = new JPanel();
		pnlSignOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlSignOut.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pnlSignOut.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to Sign Out?", "Confirm", JOptionPane.YES_NO_OPTION)== 0) {
					DashboardGUI.this.dispose();
					new HomeGUI();
				}
			}
		});
		pnlSignOut.setForeground(Color.WHITE);
		pnlSignOut.setFont(new Font("Arial", Font.BOLD, 13));
		pnlSignOut.setBorder(null);
		pnlSignOut.setBackground(new Color(107, 142, 35));
		pnlSignOut.setBounds(1, 370, 269, 39);
		menuPanel.add(pnlSignOut);
		pnlSignOut.setLayout(null);
		
		JLabel lblSignOut = new JLabel("Sign Out");
		lblSignOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignOut.setForeground(Color.WHITE);
		lblSignOut.setFont(new Font("Arial", Font.BOLD, 13));
		lblSignOut.setBounds(10, 11, 249, 14);
		pnlSignOut.add(lblSignOut);
		
		//jpanel to switch to cancel booking panel
		JPanel pnlCancelBooking = new JPanel();
		pnlCancelBooking.setLayout(null);
		pnlCancelBooking.setForeground(Color.WHITE);
		pnlCancelBooking.setFont(new Font("Arial", Font.BOLD, 13));
		pnlCancelBooking.setBorder(null);
		pnlCancelBooking.setBackground(new Color(107, 142, 35));
		pnlCancelBooking.setBounds(1, 328, 269, 39);
		menuPanel.add(pnlCancelBooking);
		pnlCancelBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlCancelBooking.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				pnlCancelBooking.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cardPanel, "4");
			}
		});
		
		JLabel lblCancelBooking = new JLabel("Cancel Your Booking");
		lblCancelBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelBooking.setForeground(Color.WHITE);
		lblCancelBooking.setFont(new Font("Arial", Font.BOLD, 13));
		lblCancelBooking.setBounds(10, 11, 249, 14);
		pnlCancelBooking.add(lblCancelBooking);
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(865, 0, 35, 31);
		contentPane.add(lblExit);

		//jlabel to go back to previous page
		JLabel lblGoBack = new JLabel("<");
		lblGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					dispose();
					HomeGUI HomeGUI = new HomeGUI();
	                HomeGUI.setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGoBack.setForeground(Color.GREEN);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblGoBack.setForeground(Color.WHITE);
			}
		});
		lblGoBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoBack.setForeground(Color.WHITE);
		lblGoBack.setFont(new Font("Snap ITC", Font.BOLD, 18));
		lblGoBack.setBounds(0, -1, 29, 31);
		contentPane.add(lblGoBack);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setOpaque(true);
		lblBackground.setBorder(null);
		lblBackground.setIconTextGap(0);
		lblBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(DashboardGUI.class.getResource("/resources/backgroundgreen.jpg")));
		lblBackground.setBounds(0, 0, 900, 600);
		contentPane.add(lblBackground);
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		

	}
	
	
	//sets the minimum checkoutdate to one day after the checkindate
	private void CheckOutDateUpdater() {
	    Date selectedCheckInDate = CheckInDate.getDate();

	    if (selectedCheckInDate != null) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(selectedCheckInDate);
	        calendar.add(Calendar.DAY_OF_MONTH, 1); // Add one day to check-in date
	        
	        CheckOutDate.setMinSelectableDate(calendar.getTime()); // Set the minimum check-out date
	    }
	}
	
	//sets the minimum checkoutdate to one day after the checkindate
	private void CheckOutDateUpdater1() {
	    Date selectedCheckInDate1 = CheckInDate1.getDate();

	    if (selectedCheckInDate1 != null) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(selectedCheckInDate1);
	        calendar.add(Calendar.DAY_OF_MONTH, 1); // Add one day to check-in date
	        
	        CheckOutDate1.setMinSelectableDate(calendar.getTime()); // Set the minimum check-out date
	    }
	}
	
	//updates the booking table each time you open home panel
	private void updateBookingTable() {
	    DefaultTableModel tableModel = (DefaultTableModel) tblBookingInfo.getModel();
	    tableModel.setRowCount(0); // Clear existing data
	    
	    String userEmail = Global.currentUser.getEmail();
	    ArrayList<User> viewBooking = new CRUD().search(userEmail);
	    for (User booking : viewBooking) {
	        Object[] rowData = {
	            booking.getBookingID(), booking.getEmail(), booking.getCheckInDate(),
	            booking.getCheckOutDate(), booking.getRoomType(), booking.getBookingStatus(),
	            booking.getRoomID(), booking.getReceptionistID()
	        };
	        tableModel.addRow(rowData);
	    }
	}


	//main
	public static void main(String[] args) {
		new DashboardGUI();
	}
}
