package pkg1;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeGUI extends JFrame { //Inheritance: Extending JFrame
	
	private JPanel mainPanel;

	public HomeGUI() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		mainPanel = new JPanel();
		mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mainPanel.setBackground(Color.WHITE);

		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		//top right x button
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you really want to close this app?", "Confirm", JOptionPane.YES_NO_OPTION)== 0) {
					HomeGUI.this.dispose();
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
		
		//receptionist login panel button
		JPanel panelReceptionistLogin = new JPanel();
		panelReceptionistLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelReceptionistLogin.setBackground(new Color(0x4bbd5d));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelReceptionistLogin.setBackground(new Color(0x6b8e23));
			}
		});
		panelReceptionistLogin.setLayout(null);
		panelReceptionistLogin.setBackground(new Color(107, 142, 35));
		panelReceptionistLogin.setBounds(529, 371, 279, 39);
		mainPanel.add(panelReceptionistLogin);
		
		JLabel lblReceptionistLogin = new JLabel("RECEPTIONIST LOG IN");
		lblReceptionistLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceptionistLogin.setForeground(Color.WHITE);
		lblReceptionistLogin.setFont(new Font("Arial", Font.BOLD, 14));
		lblReceptionistLogin.setBounds(10, 11, 259, 17);
		panelReceptionistLogin.add(lblReceptionistLogin);
		panelReceptionistLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelReceptionistLogin.setBackground(new Color(0x4bbd5d));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelReceptionistLogin.setBackground(new Color(0x6b8e23));
			}
			@Override
			public void mouseClicked(MouseEvent e) { //opens the receptionistLoginGui class
				dispose(); 

				ReceptionistLoginGUI receptionistLoginGUI = new ReceptionistLoginGUI();
				receptionistLoginGUI.setVisible(true);
			}
		});
		
		JLabel lblPlease = new JLabel("Kindly sign in or sign up to proceed");
		lblPlease.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlease.setForeground(Color.WHITE);
		lblPlease.setFont(new Font("Bell MT", Font.ITALIC, 14));
		lblPlease.setBounds(529, 208, 266, 25);
		mainPanel.add(lblPlease);
		
		JLabel lblStaff = new JLabel("For Official Use Only");
		lblStaff.setForeground(Color.WHITE);
		lblStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaff.setFont(new Font("Bell MT", Font.BOLD, 16));
		lblStaff.setBounds(529, 335, 279, 25);
		mainPanel.add(lblStaff);
		
		JLabel lblWelcome = new JLabel("Welcome to Luton Vista Inn!");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Bell MT", Font.BOLD, 18));
		lblWelcome.setBounds(529, 156, 279, 25);
		mainPanel.add(lblWelcome);
		
		//CLient login jpanel button
		JPanel panelLoginClient = new JPanel();
		panelLoginClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelLoginClient.setBackground(new Color(0x4bbd5d));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelLoginClient.setBackground(new Color(0x6b8e23));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose(); 

                LoginGUI loginGUI = new LoginGUI();
                loginGUI.setVisible(true);
			}
		});
		
		panelLoginClient.setLayout(null);
		panelLoginClient.setBackground(new Color(107, 142, 35));
		panelLoginClient.setBounds(529, 248, 132, 39);
		mainPanel.add(panelLoginClient);
		
		JLabel lbllogin_2 = new JLabel("LOG IN");
		lbllogin_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogin_2.setForeground(Color.WHITE);
		lbllogin_2.setFont(new Font("Arial", Font.BOLD, 14));
		lbllogin_2.setBounds(20, 11, 91, 17);
		panelLoginClient.add(lbllogin_2);
		
		JPanel panelSignUpClient = new JPanel();
		panelSignUpClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelSignUpClient.setBackground(new Color(0x4bbd5d));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelSignUpClient.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose(); 

                ChooseRegister ChooseRegister = new ChooseRegister();
                ChooseRegister.setVisible(true);
			}
		});
		panelSignUpClient.setLayout(null);
		panelSignUpClient.setBackground(new Color(107, 142, 35));
		panelSignUpClient.setBounds(676, 248, 132, 39);
		mainPanel.add(panelSignUpClient);
		
		JLabel lbllogin_1 = new JLabel("SIGN UP");
		lbllogin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogin_1.setForeground(Color.WHITE);
		lbllogin_1.setFont(new Font("Arial", Font.BOLD, 14));
		lbllogin_1.setBounds(23, 11, 84, 17);
		panelSignUpClient.add(lbllogin_1);
		
		JLabel lblBox = new JLabel("");
		lblBox.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/square.png")));
		lblBox.setBounds(465, 115, 435, 400);
		mainPanel.add(lblBox);
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(865, 0, 35, 31);
		mainPanel.add(lblExit);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/logo (1).png")));
		lblLogo.setBounds(-14, 115, 492, 345);
		mainPanel.add(lblLogo);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIconTextGap(0);
		lblBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(LoginGUI.class.getResource("/resources/backgroundgreen.jpg")));
		lblBackground.setBounds(0, 0, 900, 600);
		mainPanel.add(lblBackground);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//main
	public static void main(String[] args) {
		new HomeGUI();
	}
}
