package com.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HomeUI extends JFrame {
	private JPanel contentPane;
	JButton btnThoat;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					HomeUI frame = new HomeUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public HomeUI() throws SQLException {
		setTitle("Trang quản lý");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		contentPane = new JPanel() {  
			public void paintComponent(Graphics g) {  
				Image img = Toolkit.getDefaultToolkit().getImage(  
						LoginUI.class.getResource("/images/bg2.jpg"));  
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
            }  
		};
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnThoat = new JButton("Thoát chương trình", new ImageIcon("data/images/shut-down.png"));
		
		btnThoat.setBounds((int) getSize().getWidth() - 250, 594, 200, 40);

		btnThoat.setBackground(Color.WHITE);
		contentPane.add(btnThoat);
		
		btnThoat.addActionListener((e) -> {
			System.exit(0);
		});
	}

	public JPanel getContentpane() {
		return this.contentPane	;
	}
}
