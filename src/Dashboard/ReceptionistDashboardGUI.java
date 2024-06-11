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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Operations.CRUD;
import Operations.User;

import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReceptionistDashboardGUI extends JFrame { //Inheritance: Extending JFrame
	
	//GUI components and data fields
	private JPanel contentPane;
	private Image logo = new ImageIcon(ReceptionistDashboardGUI.class.getResource("/resources/logo (1).png")).getImage().getScaledInstance(160, 151, Image.SCALE_SMOOTH);
	JTable tblBookingInfo;
	private JTextField txtBookingID;
	private JTextField txtRoomType;
	private JTextField txtCheckInDate;
	private JTextField txtCheckOutDate;
	private JTextField txtBookingStatus;
	private JTextField txtRoomNo;
	
	//constructor
	public ReceptionistDashboardGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JFrame frame = new JFrame();
		
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to close this app?", "Confirm", JOptionPane.YES_NO_OPTION)== 0) {
					ReceptionistDashboardGUI.this.dispose();
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
		
		//cardlayout to switch panels
		CardLayout cardLayout = new CardLayout();

		Panel cardPanel = new Panel();
		cardPanel.setBackground(new Color(0, 128, 0));
		cardPanel.setBounds(291, 30, 599, 548);
		contentPane.add(cardPanel);
		cardPanel.setLayout(cardLayout);
		
		//home panel 
		JPanel pnlHomeMain = new JPanel();
		pnlHomeMain.setBackground(new Color(34, 139, 34));
		pnlHomeMain.setBounds(280, 29, 610, 560);
		pnlHomeMain.setLayout(null);
		pnlHomeMain.setVisible(true);
		cardPanel.add(pnlHomeMain, "1");
		
		//Creating an array of room types for the combo box
		String[] roomTypes = {"Single Room", "Double Room", "Twin Room", "Family Room"};
		
		//menu panel that shows all the jpanels/button to switch panels
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
		
		//adding mouse listener for changing the panel to home panel
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
				updateBookingTable(); // udpates the view booking info table
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
		
		//table that shows the booking requests of clients
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
		scrollPane.setBounds(10, 180, 579, 357);
		pnlHomeMain.add(scrollPane);
		
		ArrayList<User> viewBooking = new CRUD().DisplayAll();
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
		JLabel lblWelcomeToLuton = new JLabel("Luton Vista Inn");
		lblWelcomeToLuton.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToLuton.setForeground(Color.WHITE);
		lblWelcomeToLuton.setFont(new Font("Gill Sans MT", Font.PLAIN, 26));
		lblWelcomeToLuton.setBounds(20, 30, 569, 47);
		pnlHomeMain.add(lblWelcomeToLuton);
		
		JLabel lblThankYouFor = new JLabel("Receptionist Dashboard");
		lblThankYouFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblThankYouFor.setForeground(Color.WHITE);
		lblThankYouFor.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblThankYouFor.setBounds(20, 92, 569, 30);
		pnlHomeMain.add(lblThankYouFor);
		
		JLabel lblViewBooking = new JLabel("Bookings");
		lblViewBooking.setBounds(173, 155, 249, 14);
		pnlHomeMain.add(lblViewBooking);
		lblViewBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewBooking.setForeground(Color.WHITE);
		lblViewBooking.setFont(new Font("Arial", Font.BOLD, 13));
		
		//jpanel that holds the content for updating a booking
		JPanel pnlUpdate = new JPanel();
		pnlUpdate.setBackground(new Color(34, 139, 34));
		cardPanel.add(pnlUpdate, "3");
		pnlUpdate.setLayout(null);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the Booking ID you want to Update:");
		lblPleaseEnterThe.setBounds(112, 54, 367, 14);
		lblPleaseEnterThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterThe.setForeground(Color.WHITE);
		lblPleaseEnterThe.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdate.add(lblPleaseEnterThe);
		
		txtBookingID = new JTextField();
		txtBookingID.setBounds(193, 79, 217, 20);
		txtBookingID.setHorizontalAlignment(SwingConstants.CENTER);
		pnlUpdate.add(txtBookingID);
		txtBookingID.setColumns(10);
		
		JLabel lblUpdateYourBooking_1 = new JLabel("Manage Bookings");
		lblUpdateYourBooking_1.setBounds(193, 11, 217, 14);
		lblUpdateYourBooking_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateYourBooking_1.setForeground(Color.WHITE);
		lblUpdateYourBooking_1.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdate.add(lblUpdateYourBooking_1);
		
		JLabel lblSelectRoomType_1 = new JLabel("Select Room Type:");
		lblSelectRoomType_1.setBounds(115, 196, 146, 20);
		lblSelectRoomType_1.setForeground(Color.WHITE);
		lblSelectRoomType_1.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdate.add(lblSelectRoomType_1);
		
		JLabel lblCheckinDate_1 = new JLabel("Select Check-In Date:");
		lblCheckinDate_1.setBounds(113, 233, 146, 20);
		lblCheckinDate_1.setForeground(Color.WHITE);
		lblCheckinDate_1.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdate.add(lblCheckinDate_1);
		
		JLabel lblSelectCheckoutDate1 = new JLabel("Select Check-Out Date:");
		lblSelectCheckoutDate1.setBounds(113, 273, 146, 20);
		lblSelectCheckoutDate1.setForeground(Color.WHITE);
		lblSelectCheckoutDate1.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdate.add(lblSelectCheckoutDate1);

		//update button to update a booking 
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(227, 458, 146, 29);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));
		btnUpdate.setBorder(null);
		btnUpdate.setBackground(new Color(107, 142, 35));
		pnlUpdate.add(btnUpdate);
		
		txtRoomType = new JTextField();
		txtRoomType.setBounds(263, 194, 217, 20);
		txtRoomType.setHorizontalAlignment(SwingConstants.CENTER);
		txtRoomType.setColumns(10);
		pnlUpdate.add(txtRoomType);
		
		txtCheckInDate = new JTextField();
		txtCheckInDate.setBounds(263, 233, 217, 20);
		txtCheckInDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckInDate.setColumns(10);
		pnlUpdate.add(txtCheckInDate);
		
		txtCheckOutDate = new JTextField();
		txtCheckOutDate.setBounds(263, 273, 217, 20);
		txtCheckOutDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtCheckOutDate.setColumns(10);
		pnlUpdate.add(txtCheckOutDate);
		
		JLabel lblSelectCheckoutDate1_1 = new JLabel("Booking Status:");
		lblSelectCheckoutDate1_1.setBounds(114, 311, 146, 20);
		lblSelectCheckoutDate1_1.setForeground(Color.WHITE);
		lblSelectCheckoutDate1_1.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdate.add(lblSelectCheckoutDate1_1);
		
		JLabel lblSelectCheckoutDate1_2 = new JLabel("RoomNo:");
		lblSelectCheckoutDate1_2.setBounds(116, 350, 146, 20);
		lblSelectCheckoutDate1_2.setForeground(Color.WHITE);
		lblSelectCheckoutDate1_2.setFont(new Font("Arial", Font.BOLD, 13));
		pnlUpdate.add(lblSelectCheckoutDate1_2);
		
		txtBookingStatus = new JTextField();
		txtBookingStatus.setBounds(263, 311, 217, 20);
		txtBookingStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtBookingStatus.setColumns(10);
		pnlUpdate.add(txtBookingStatus);
		
		txtRoomNo = new JTextField();
		txtRoomNo.setBounds(264, 351, 217, 20);
		txtRoomNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtRoomNo.setColumns(10);
		pnlUpdate.add(txtRoomNo);
		
		//search button to search a booking
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 12));
		btnSearch.setBorder(null);
		btnSearch.setBackground(new Color(107, 142, 35));
		btnSearch.setBounds(227, 120, 146, 29);
		pnlUpdate.add(btnSearch);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			    // Get the text from the Booking ID field
			    String bookingIDText = txtBookingID.getText();
			    
			    // Check if the Booking ID field is not empty
			    if (!bookingIDText.isEmpty()) {
			        try {
			            
			            int BookingID = Integer.parseInt(bookingIDText);
			            User user = new CRUD().searchID(BookingID);
			            
			            if (user != null) {
			                txtRoomType.setText(user.getRoomType());
			                
			                Date checkInDate = user.getCheckInDate();
			                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			                String formattedCheckInDate = dateFormat.format(checkInDate);
			                txtCheckInDate.setText(formattedCheckInDate);
			                
			                Date checkOutDate = user.getCheckOutDate();
			                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			                String formattedCheckOutDate = dateFormat1.format(checkOutDate);
			                txtCheckOutDate.setText(formattedCheckOutDate);
			                
			                txtBookingStatus.setText(user.getBookingStatus());
			                txtRoomNo.setText(Integer.toString(user.getRoomID()));
			            } else {
			                JOptionPane.showMessageDialog(null, "Record not found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
			                clearAll(); 
			            }
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Invalid Booking ID", "Error", JOptionPane.ERROR_MESSAGE);
			            clearAll(); 
			        }
			    } else {
			        JOptionPane.showMessageDialog(null, "Please enter a Booking ID", "Error", JOptionPane.ERROR_MESSAGE);
			        clearAll();
			    }
			}

			// Method to clear all the text fields
			private void clearAll() {
			    txtBookingID.setText("");
			    txtRoomType.setText("");
			    txtCheckInDate.setText("");
			    txtCheckOutDate.setText("");
			    txtBookingStatus.setText("");
			    txtRoomNo.setText("");
			}

		});
		
		//update button mouselistener to update bookings
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
			    String BookingStatus = txtBookingStatus.getText();
			    int RoomNo = Integer.parseInt(txtRoomNo.getText());
			    int ReservationID = Global.currentUser.getReceptionistID();

			    boolean result = new CRUD().updateReceptionist(BookingID, BookingStatus, RoomNo, ReservationID);
			        if (result) {
			            JOptionPane.showMessageDialog(ReceptionistDashboardGUI.this, "Record updated successfully");
			            clearAll();
			        } else {
			            JOptionPane.showMessageDialog(ReceptionistDashboardGUI.this, "Error updating record");
			            clearAll();
			        }
			    }

			private void clearAll() {
				txtBookingID.setText("");
			    txtRoomType.setText("");
			    txtCheckInDate.setText("");
			    txtCheckOutDate.setText("");
			    txtBookingStatus.setText("");
			    txtRoomNo.setText("");
			}
			});

		//mouse listener for pnlUpdateyourbooking to switch panels
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
		pnlUpdateYourBooking.setBounds(1, 245, 269, 39);
		menuPanel.add(pnlUpdateYourBooking);
		pnlUpdateYourBooking.setLayout(null);
		
		JLabel lblUpdateYourBooking = new JLabel("Manage Bookings");
		lblUpdateYourBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateYourBooking.setForeground(Color.WHITE);
		lblUpdateYourBooking.setFont(new Font("Arial", Font.BOLD, 13));
		lblUpdateYourBooking.setBounds(10, 11, 249, 14);
		pnlUpdateYourBooking.add(lblUpdateYourBooking);
		
		//signout button to signout and go back to login page
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
					ReceptionistDashboardGUI.this.dispose();
					new HomeGUI();
				}
			}
		});
		pnlSignOut.setForeground(Color.WHITE);
		pnlSignOut.setFont(new Font("Arial", Font.BOLD, 13));
		pnlSignOut.setBorder(null);
		pnlSignOut.setBackground(new Color(107, 142, 35));
		pnlSignOut.setBounds(1, 287, 269, 39);
		menuPanel.add(pnlSignOut);
		pnlSignOut.setLayout(null);
		
		JLabel lblSignOut = new JLabel("Sign Out");
		lblSignOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignOut.setForeground(Color.WHITE);
		lblSignOut.setFont(new Font("Arial", Font.BOLD, 13));
		lblSignOut.setBounds(10, 11, 249, 14);
		pnlSignOut.add(lblSignOut);
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(865, 0, 35, 31);
		contentPane.add(lblExit);
		
		//mouse listener to go back to previous place
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
		lblBackground.setIcon(new ImageIcon(ReceptionistDashboardGUI.class.getResource("/resources/backgroundgreen.jpg")));
		lblBackground.setBounds(0, 0, 900, 600);
		contentPane.add(lblBackground);
		
		
		
		setLocationRelativeTo(null);
		setVisible(true);
		

	}
	//
	private void updateBookingTable() {
	    DefaultTableModel tableModel = (DefaultTableModel) tblBookingInfo.getModel();
	    tableModel.setRowCount(0); // Clear existing data
	    
	    ArrayList<User> viewBooking = new CRUD().DisplayAll();
	    for (User booking : viewBooking) {
	        Object[] rowData = {
	            booking.getBookingID(), booking.getEmail(), booking.getCheckInDate(),
	            booking.getCheckOutDate(), booking.getRoomType(), booking.getBookingStatus(),
	            booking.getRoomID(), booking.getReceptionistID()
	        };
	        tableModel.addRow(rowData);
	    }
	}


	
	public static void main(String[] args) {
		new ReceptionistDashboardGUI();
	}
}
