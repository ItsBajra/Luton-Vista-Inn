package pkg1;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Operations.CRUD;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterIndividualGUI extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtContactNumber;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtZipCode;
	private JTextField txtCountry;
	private JTextField txtCVVCode;
	private JTextField txtNameOnCard;
	private JTextField txtCreditCardNumber;
	private JTextField txtPasswordAgain;
	private JTextField txtPassword;
	private JTextField txtExpiryDate;

	public RegisterIndividualGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to close this app?", "Confirm", JOptionPane.YES_NO_OPTION)== 0) {
					RegisterIndividualGUI.this.dispose();
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
		
		txtExpiryDate = new JTextField();
		txtExpiryDate.setColumns(10);
		txtExpiryDate.setBorder(null);
		txtExpiryDate.setBounds(524, 404, 308, 31);
		contentPane.add(txtExpiryDate);
		
		JLabel lblAccountTypeIndividual = new JLabel("Account Type: Individual Account");
		lblAccountTypeIndividual.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountTypeIndividual.setForeground(Color.WHITE);
		lblAccountTypeIndividual.setFont(new Font("Arial", Font.BOLD, 10));
		lblAccountTypeIndividual.setBounds(304, 39, 307, 14);
		contentPane.add(lblAccountTypeIndividual);
		
		JLabel lblRegisterYourAccount = new JLabel("Register Your Account");
		lblRegisterYourAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterYourAccount.setForeground(Color.WHITE);
		lblRegisterYourAccount.setFont(new Font("Arial", Font.BOLD, 14));
		lblRegisterYourAccount.setBounds(304, 10, 307, 14);
		contentPane.add(lblRegisterYourAccount);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String FirstName = txtFirstName.getText();
				String LastName = txtLastName.getText();
				String Email = txtEmail.getText();
				String ContactNumber = txtContactNumber.getText();
				String Address = txtAddress.getText();
				String City = txtCity.getText();
				String ZipCode = txtZipCode.getText();
				String Country = txtCountry.getText();
				String Password = txtPassword.getText();
				String CCNumber = txtCreditCardNumber.getText();
				String NameOnCard = txtNameOnCard.getText();
				String ExpiryDate = txtExpiryDate.getText();
				String CVVCode = txtCVVCode.getText();
				
				new CRUD().insert(FirstName, LastName, Email, ContactNumber, Address, City, ZipCode, Country, Password, CCNumber, NameOnCard, ExpiryDate, CVVCode);
				clearAll();
				
				if (JOptionPane.showConfirmDialog(null, "Account created successfully! Go to login page?", "Confirm", JOptionPane.YES_NO_OPTION)== 0) {
					RegisterIndividualGUI.this.dispose();
					new LoginGUI();
					}
			}

			private void clearAll() {
				txtFirstName.setText("");
				txtLastName.setText("");
				txtEmail.setText("");
				txtContactNumber.setText("");
				txtAddress.setText("");
				txtCity.setText("");
				txtZipCode.setText("");
				txtCountry.setText("");
				txtPassword.setText("");
				txtCreditCardNumber.setText("");
				txtNameOnCard.setText("");
				txtExpiryDate.setText("");
				txtCVVCode.setText("");
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSubmit.setBackground(new Color(0x49be25));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSubmit.setBackground(new Color(0x6b8e23));
			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSubmit.setBorder(null);
		btnSubmit.setBackground(new Color(107, 142, 35));
		btnSubmit.setBounds(612, 514, 141, 33);
		contentPane.add(btnSubmit);
		
		JLabel lblNameOnCard = new JLabel("Name on Card");
		lblNameOnCard.setForeground(Color.WHITE);
		lblNameOnCard.setFont(new Font("Arial", Font.BOLD, 12));
		lblNameOnCard.setBounds(525, 323, 118, 14);
		contentPane.add(lblNameOnCard);
		
		JLabel lblEnterAPassword = new JLabel("Enter a password");
		lblEnterAPassword.setForeground(Color.WHITE);
		lblEnterAPassword.setFont(new Font("Arial", Font.BOLD, 12));
		lblEnterAPassword.setBounds(524, 111, 141, 14);
		contentPane.add(lblEnterAPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBorder(null);
		txtPassword.setColumns(10);
		txtPassword.setBounds(524, 125, 308, 31);
		contentPane.add(txtPassword);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setForeground(Color.WHITE);
		lblExpiryDate.setFont(new Font("Arial", Font.BOLD, 12));
		lblExpiryDate.setBounds(524, 385, 118, 14);
		contentPane.add(lblExpiryDate);
		
		JLabel lblCvvCode = new JLabel("CVV Code");
		lblCvvCode.setForeground(Color.WHITE);
		lblCvvCode.setFont(new Font("Arial", Font.BOLD, 12));
		lblCvvCode.setBounds(524, 446, 118, 14);
		contentPane.add(lblCvvCode);
		
		JLabel lblEnterThePassword = new JLabel("Enter the password again");
		lblEnterThePassword.setForeground(Color.WHITE);
		lblEnterThePassword.setFont(new Font("Arial", Font.BOLD, 12));
		lblEnterThePassword.setBounds(524, 171, 168, 14);
		contentPane.add(lblEnterThePassword);
		
		JLabel lblCreditCardNumber = new JLabel("Credit Card Number");
		lblCreditCardNumber.setForeground(Color.WHITE);
		lblCreditCardNumber.setFont(new Font("Arial", Font.BOLD, 12));
		lblCreditCardNumber.setBounds(525, 261, 118, 14);
		contentPane.add(lblCreditCardNumber);
		
		JLabel lblEnterYourCredit = new JLabel("Enter your credit card details (Optional)");
		lblEnterYourCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourCredit.setForeground(Color.WHITE);
		lblEnterYourCredit.setFont(new Font("Arial", Font.BOLD, 12));
		lblEnterYourCredit.setBounds(524, 233, 307, 14);
		contentPane.add(lblEnterYourCredit);
		
		txtNameOnCard = new JTextField();
		txtNameOnCard.setBorder(null);
		txtNameOnCard.setColumns(10);
		txtNameOnCard.setBounds(524, 338, 308, 31);
		contentPane.add(txtNameOnCard);
		
		txtPasswordAgain = new JTextField();
		txtPasswordAgain.setBorder(null);
		txtPasswordAgain.setColumns(10);
		txtPasswordAgain.setBounds(524, 185, 308, 31);
		contentPane.add(txtPasswordAgain);
		
		txtCVVCode = new JTextField();
		txtCVVCode.setBorder(null);
		txtCVVCode.setColumns(10);
		txtCVVCode.setBounds(524, 461, 308, 31);
		contentPane.add(txtCVVCode);
		
		txtCreditCardNumber = new JTextField();
		txtCreditCardNumber.setBorder(null);
		txtCreditCardNumber.setColumns(10);
		txtCreditCardNumber.setBounds(524, 275, 308, 31);
		contentPane.add(txtCreditCardNumber);
		
		JLabel lblSetUpA = new JLabel("Set up a password");
		lblSetUpA.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetUpA.setForeground(Color.WHITE);
		lblSetUpA.setFont(new Font("Arial", Font.BOLD, 12));
		lblSetUpA.setBounds(525, 83, 307, 14);
		contentPane.add(lblSetUpA);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setForeground(Color.WHITE);
		lblCountry.setFont(new Font("Arial", Font.BOLD, 12));
		lblCountry.setBounds(75, 505, 118, 14);
		contentPane.add(lblCountry);
		
		JLabel lblZipCode = new JLabel("ZIP Code");
		lblZipCode.setForeground(Color.WHITE);
		lblZipCode.setFont(new Font("Arial", Font.BOLD, 12));
		lblZipCode.setBounds(75, 444, 118, 14);
		contentPane.add(lblZipCode);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setForeground(Color.WHITE);
		lblCity.setFont(new Font("Arial", Font.BOLD, 12));
		lblCity.setBounds(76, 382, 118, 14);
		contentPane.add(lblCity);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 12));
		lblAddress.setBounds(76, 320, 118, 14);
		contentPane.add(lblAddress);
		
		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setForeground(Color.WHITE);
		lblContactNumber.setFont(new Font("Arial", Font.BOLD, 12));
		lblContactNumber.setBounds(76, 261, 118, 14);
		contentPane.add(lblContactNumber);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
		lblEmail.setBounds(76, 202, 77, 14);
		contentPane.add(lblEmail);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Arial", Font.BOLD, 12));
		lblLastName.setBounds(75, 143, 77, 14);
		contentPane.add(lblLastName);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(75, 83, 77, 14);
		contentPane.add(lblNewLabel);
		
		txtCountry = new JTextField();
		txtCountry.setBorder(null);
		txtCountry.setColumns(10);
		txtCountry.setBounds(75, 520, 308, 31);
		contentPane.add(txtCountry);
		
		txtZipCode = new JTextField();
		txtZipCode.setBorder(null);
		txtZipCode.setColumns(10);
		txtZipCode.setBounds(75, 459, 308, 31);
		contentPane.add(txtZipCode);
		
		txtCity = new JTextField();
		txtCity.setBorder(null);
		txtCity.setColumns(10);
		txtCity.setBounds(75, 397, 308, 31);
		contentPane.add(txtCity);
		
		txtAddress = new JTextField();
		txtAddress.setBorder(null);
		txtAddress.setColumns(10);
		txtAddress.setBounds(75, 334, 308, 31);
		contentPane.add(txtAddress);
		
		txtContactNumber = new JTextField();
		txtContactNumber.setBorder(null);
		txtContactNumber.setColumns(10);
		txtContactNumber.setBounds(75, 275, 308, 31);
		contentPane.add(txtContactNumber);
		
		txtLastName = new JTextField();
		txtLastName.setBorder(null);
		txtLastName.setColumns(10);
		txtLastName.setBounds(75, 157, 308, 31);
		contentPane.add(txtLastName);
		
		txtEmail = new JTextField();
		txtEmail.setBorder(null);
		txtEmail.setColumns(10);
		txtEmail.setBounds(75, 216, 308, 31);
		contentPane.add(txtEmail);
		
		txtFirstName = new JTextField();
		txtFirstName.setBorder(null);
		txtFirstName.setBounds(75, 97, 308, 31);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblbox2 = new JLabel("");
		lblbox2.setIcon(new ImageIcon(RegisterIndividualGUI.class.getResource("/resources/SquareStretched.png")));
		lblbox2.setHorizontalAlignment(SwingConstants.CENTER);
		lblbox2.setBounds(467, 28, 423, 600);
		contentPane.add(lblbox2);
		
		JLabel lblbox1 = new JLabel("");
		lblbox1.setHorizontalAlignment(SwingConstants.CENTER);
		lblbox1.setIcon(new ImageIcon(RegisterIndividualGUI.class.getResource("/resources/SquareStretched.png")));
		lblbox1.setBounds(0, 28, 457, 600);
		contentPane.add(lblbox1);
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(865, 0, 35, 31);
		contentPane.add(lblExit);
		

		JLabel lblGoBack = new JLabel("<");
		lblGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					dispose();
					ChooseRegister ChooseRegister = new ChooseRegister();
					ChooseRegister.setVisible(true);
				
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
		
		String[] accountType = {"Individual", "Corporate"};
		
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("Arial", Font.ITALIC, 12));
		lblError.setBounds(104, 363, 265, 18);
		contentPane.add(lblError);
		Image signin_img = (new ImageIcon(RegisterIndividualGUI.class.getResource("/resources/signin.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		Image password_img = new ImageIcon(RegisterIndividualGUI.class.getResource("/resources/password.png")).getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
		Image username_img = (new ImageIcon(RegisterIndividualGUI.class.getResource("/resources/username.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIconTextGap(0);
		lblBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(RegisterIndividualGUI.class.getResource("/resources/backgroundgreen.jpg")));
		lblBackground.setBounds(0, 0, 900, 600);
		contentPane.add(lblBackground);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	
	public static void main(String[] args) {
		new RegisterIndividualGUI();
	}
}
