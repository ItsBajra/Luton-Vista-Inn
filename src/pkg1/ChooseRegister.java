package pkg1;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseRegister extends JFrame {
	
	private JPanel contentPane;

	public ChooseRegister() {
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
					ChooseRegister.this.dispose();
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
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setForeground(Color.WHITE);
		lblOr.setFont(new Font("Arial", Font.BOLD, 14));
		lblOr.setBounds(93, 289, 295, 31);
		contentPane.add(lblOr);
		
		JButton btnIndividual = new JButton("Individual Account");
		btnIndividual.setFocusPainted(false);
		btnIndividual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnIndividual.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnIndividual.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose(); 

                RegisterIndividualGUI RegisterIndividualGUI = new RegisterIndividualGUI();
                RegisterIndividualGUI.setVisible(true);
			}
		});
		btnIndividual.setForeground(Color.WHITE);
		btnIndividual.setFont(new Font("Arial", Font.BOLD, 12));
		btnIndividual.setBorder(null);
		btnIndividual.setBackground(new Color(107, 142, 35));
		btnIndividual.setBounds(92, 224, 295, 46);
		contentPane.add(btnIndividual);
		
		JButton btnCorporate = new JButton("Corporate Account");
		btnCorporate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCorporate.setBackground(new Color(0x4bbd5d));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnCorporate.setBackground(new Color(0x6b8e23));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose(); 

                RegisterCorporateGUI RegisterCorporateGUI = new RegisterCorporateGUI();
                RegisterCorporateGUI.setVisible(true);
			}
		});
		btnCorporate.setFont(new Font("Arial", Font.BOLD, 12));
		btnCorporate.setForeground(Color.WHITE);
		btnCorporate.setBackground(new Color(107, 142, 35));
		btnCorporate.setBorder(null);
		btnCorporate.setBounds(92, 341, 295, 46);
		contentPane.add(btnCorporate);
		
		JLabel lblTxt = new JLabel("Choose Account Type For Registration");
		lblTxt.setForeground(Color.WHITE);
		lblTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblTxt.setFont(new Font("Arial", Font.BOLD, 14));
		lblTxt.setBounds(92, 158, 295, 31);
		contentPane.add(lblTxt);
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
		
		String[] accountType = {"Individual", "Corporate"};
		
		JLabel lblBox = new JLabel("");
		lblBox.setIcon(new ImageIcon(ChooseRegister.class.getResource("/resources/square.png")));
		lblBox.setBounds(39, 114, 435, 400);
		contentPane.add(lblBox);
		
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.WHITE);
		lblError.setFont(new Font("Arial", Font.ITALIC, 12));
		lblError.setBounds(104, 363, 265, 18);
		contentPane.add(lblError);
		Image signin_img = (new ImageIcon(ChooseRegister.class.getResource("/resources/signin.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		Image password_img = new ImageIcon(ChooseRegister.class.getResource("/resources/password.png")).getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
		Image username_img = (new ImageIcon(ChooseRegister.class.getResource("/resources/username.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(ChooseRegister.class.getResource("/resources/logo (1).png")));
		lblLogo.setBounds(429, 114, 492, 345);
		contentPane.add(lblLogo);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIconTextGap(0);
		lblBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(ChooseRegister.class.getResource("/resources/backgroundgreen.jpg")));
		lblBackground.setBounds(0, 0, 900, 600);
		contentPane.add(lblBackground);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new ChooseRegister();
	}
}
