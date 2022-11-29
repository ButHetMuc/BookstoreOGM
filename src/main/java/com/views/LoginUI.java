package com.views;
import java.awt.Graphics;  
import java.awt.Image;  
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;


import com.utils.Placeholder;

import javax.swing.SwingConstants;

public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	public JButton btnDangNhap;
	public JButton btnDangKy;
	private JLabel lblMsg;
	private JCheckBox chcHienThiMatKhau;
	public JCheckBox chkIsNhanVien;
	public JButton btnThoat;
	public ImageIcon icon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		icon = new ImageIcon("data/images/snakelogo1.png");
		setIconImage(icon.getImage());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		contentPane = new JPanel() {  
			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(  
						LoginUI.class.getResource("/images/bg2.jpg"));  
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
		};
		
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(424, 176, 479, 265);
		panel.setBackground(new Color(0, 0, 0, 150));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDangNhap = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblDangNhap.setBounds(144, 11, 198, 49);
		panel.add(lblDangNhap);
		lblDangNhap.setForeground(new Color(0, 206, 209));
		lblDangNhap.setBackground(new Color(255, 255, 255));
		lblDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		txtUserName = new JTextField("Tài khoản");
		txtUserName.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtUserName.setBounds(41, 77, 398, 41);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		new Placeholder().placeholder(txtUserName, "Tài khoản");
		
		txtPassword = new JPasswordField("Mật khẩu");
		txtPassword.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtPassword.setEchoChar((char)0);
		txtPassword.setBounds(41, 129, 398, 41);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		new Placeholder().placeholder(txtPassword, "Mật khẩu");
		
		btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDangNhap.setBackground(new Color(0, 206, 209));
		btnDangNhap.setBounds(105, 207, 130, 41);
		panel.add(btnDangNhap);
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(Color.WHITE);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThoat.setBounds(259, 207, 130, 41);
		panel.add(btnThoat);
		
		chcHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");
		chcHienThiMatKhau.setBounds(309, 177, 130, 23);
		panel.add(chcHienThiMatKhau);
		
//		chkIsNhanVien = new JCheckBox("Admin");
//		chkIsNhanVien.setBounds(41, 177, 229, 23);
//		panel.add(chkIsNhanVien);
		
		
		
		chcHienThiMatKhau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtPassword.getText().equals("Mật khẩu")) {
					txtPassword.setEchoChar((char)0);	
					chcHienThiMatKhau.setSelected(false);
					return ;
				}
				
				if(chcHienThiMatKhau.isSelected()) {
		        	txtPassword.setEchoChar((char)0);
				}else {
					txtPassword.setEchoChar('*');
				}
			}
			
		});
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void renderError(String message){
        JOptionPane.showMessageDialog(contentPane, message);
    }
	
	public void clear() {
		txtUserName.setText("Tài khoản");
		txtPassword.setText("Mật khẩu");
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	public void requestFocus() {
		this.txtUserName.requestFocus();
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

//	public boolean checkPassword() {
//		TaiKhoan_dao taiKhoanDao = new TaiKhoan_dao();
//		String matKhau = taiKhoanDao.getMatKhau(txtUserName.getText());
//		if(txtPassword.getText().equals(matKhau))
//			return true;
//		else {
//			JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu", "Error", JOptionPane.WARNING_MESSAGE);
//		}
//		return false;
//	}

}
