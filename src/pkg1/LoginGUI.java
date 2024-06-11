package pkg1;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Dashboard.DashboardGUI;
import Operations.CRUD;
import Operations.User;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class LoginGUI extends JFrame { //Inheritance: Extending JFrame
	
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private String storedEmail;
	
	public LoginGUI() {
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
					LoginGUI.this.dispose();
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
		
		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAccountType.setBounds(104, 326, 107, 22);
		contentPane.add(lblAccountType);
		
		String[] accountType = {"Individual", "Corporate"};
		JComboBox cmbAccountType = new JComboBox(accountType);
		cmbAccountType.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbAccountType.setBounds(192, 326, 175, 22);
		contentPane.add(cmbAccountType);
		
		JLabel lblBox = new JLabel("");
		lblBox.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/square.png")));
		lblBox.setBounds(39, 114, 435, 400);
		contentPane.add(lblBox);
		
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("Arial", Font.ITALIC, 12));
		lblError.setBounds(104, 363, 265, 18);
		contentPane.add(lblError);
		
		
		JPanel panelSignInButton = new JPanel();
		panelSignInButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Email = txtEmail.getText();
				storedEmail = Email;
				String Password = txtPassword.getText();
				User user = new User(0, Email, Password);
				
				String selectedType = (String) cmbAccountType.getSelectedItem();
				
				boolean result = false;
				
				if("Individual".equals(selectedType)) {
					result = new CRUD().doLogin(user);
					
					if(result==true) {
						new DashboardGUI();
						dispose();
					}
					
					else {
						lblError.setText("Username or Password doesnot match!");
					}
				}
				else if ("Corporate".equals(selectedType)) {
					result = new CRUD().corporateLogin(user);
					
					if(result==true) {
						new DashboardGUI();
						dispose();
					}
					
					else {
						lblError.setText("Username or Password doesnot match!");
					}
				}
				
				
			}
		});
		panelSignInButton.setBackground(new Color(107, 142, 35));
		panelSignInButton.setBounds(104, 398, 266, 39);
		contentPane.add(panelSignInButton);
		panelSignInButton.setLayout(null);
		
		JLabel lbllogin = new JLabel("SIGN IN");
		lbllogin.setForeground(Color.WHITE);
		lbllogin.setFont(new Font("Arial", Font.BOLD, 14));
		lbllogin.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogin.setBounds(52, 11, 159, 17);
		panelSignInButton.add(lbllogin);
		
		JLabel lblSignInIcon = new JLabel("");
		lblSignInIcon.setBounds(162, 0, 41, 39);
		panelSignInButton.add(lblSignInIcon);
		lblSignInIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignInIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		Image signin_img = (new ImageIcon(LoginGUI.class.getResource("/resources/signin.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		lblSignInIcon.setIcon(new ImageIcon(signin_img));
		
		JPanel panelPassword = new JPanel();
		panelPassword.setBackground(Color.WHITE);
		panelPassword.setBounds(104, 245, 266, 39);
		contentPane.add(panelPassword);
		panelPassword.setLayout(null);
		
		JLabel lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordIcon.setBounds(226, 1, 39, 39);
		Image password_img = new ImageIcon(LoginGUI.class.getResource("/resources/password.png")).getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
		lblPasswordIcon.setIcon(new ImageIcon(password_img));
		panelPassword.add(lblPasswordIcon);
		
		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassword.getText().equals("Password")) {
					txtPassword.setEchoChar('●');
					txtPassword.setText("");
				}
				else {
					txtPassword.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("Password");
					txtPassword.setEchoChar((char)0);
				}
			}
		});
		txtPassword.setBorder(null);
		txtPassword.setEchoChar ((char)0);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		txtPassword.setText("Password");
		txtPassword.setBounds(10, 9, 213, 29);
		panelPassword.add(txtPassword);
		
		JCheckBox chkpassword = new JCheckBox("Show Password");
		chkpassword.setFont(new Font("Arial", Font.PLAIN, 11));
		chkpassword.setBackground(new Color(152, 172, 92));
		chkpassword.setBounds(103, 291, 108, 23);
		contentPane.add(chkpassword);
		
		chkpassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                txtPassword.setEchoChar(checkBox.isSelected() ? '\u0000' : '●');
            }
        });
		
		JPanel panelEmail = new JPanel();
		panelEmail.setBackground(Color.WHITE);
		panelEmail.setBounds(104, 184, 266, 39);
		contentPane.add(panelEmail);
		panelEmail.setLayout(null);
		
		JLabel lblUserNameIcon = new JLabel("");
		lblUserNameIcon.setBounds(230, 0, 36, 39);
		Image username_img = (new ImageIcon(LoginGUI.class.getResource("/resources/username.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		lblUserNameIcon.setIcon(new ImageIcon(username_img));
		panelEmail.add(lblUserNameIcon);
		
		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtEmail.getText().equals("Email")) {
					txtEmail.setText("");
				}
				
				else {
					txtEmail.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("Email");
				}
			}
		});
		txtEmail.setBorder(null);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		txtEmail.setText("Email");
		txtEmail.setBounds(10, 9, 213, 28);
		panelEmail.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/logo (1).png")));
		lblLogo.setBounds(429, 114, 492, 345);
		contentPane.add(lblLogo);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIconTextGap(0);
		lblBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/backgroundgreen.jpg")));
		lblBackground.setBounds(0, 0, 900, 600);
		contentPane.add(lblBackground);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	public String getStoredEmail() {
		return storedEmail;
	}
	public static void main(String[] args) {
		new LoginGUI();
	}
}
