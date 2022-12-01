package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


//import dao.HoaDon_dao;
//import dao.KhachHang_dao;
//import dao.NhanVien_dao;
//import dao.Thuoc_dao;
//import entity.ChiTietHoaDon;
//import entity.HoaDon;
//import entity.KhachHang;
//import entity.NhanVien;
//import entity.Thuoc;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

public class GenerateBillUI extends JFrame implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSDT;
	private JButton btnThemThuoc;
	private JButton btnBoThuoc;
	
	private DefaultTableModel modelThuoc;
	private JTable tblThuoc;
	
	private DefaultTableModel modelThuocTGH;
	private JTable tblThuocTGH;
	

	private JTextField txtSoLuong;
	private JButton btnThemHD; 
	private JTextField txtTongTien;
	private JTextField txtTenKH;
	
	private String strCheckFalse = "✘";
	private String strCheckTrue = "✔";
	private JLabel lblCheck;
	
	private JLabel lblTenKH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateBillUI frame = new GenerateBillUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public GenerateBillUI() throws SQLException {
		GUI();
	}
	
//	public GenerateBillUI(NhanVien nhanVien) throws SQLException {
//		this.nhanVien = nhanVien;
//		GUI();
//	}
			
	public void GUI() throws SQLException {
		setTitle("Tạo hóa đơn");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnThongTinKH = new JPanel();
		contentPane.add(pnThongTinKH, BorderLayout.WEST);
		
		JPanel pnThongTinKH2 = new JPanel();
		pnThongTinKH.add(pnThongTinKH2);
		pnThongTinKH2.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTTKH = new JPanel();
		pnTTKH.setBorder(new EmptyBorder(40, 0, 0, 0));
		pnThongTinKH2.add(pnTTKH, BorderLayout.NORTH);
		
		JLabel lblTTKH = new JLabel("Thông tin khách hàng");
		lblTTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTTKH.add(lblTTKH);
		
		JPanel pnThongTin = new JPanel();
		pnThongTinKH2.add(pnThongTin, BorderLayout.CENTER);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
		
		JPanel pnSoDienThoai = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnSoDienThoai.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnSoDienThoai);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setPreferredSize(new Dimension(100, 20));
		pnSoDienThoai.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setPreferredSize(new Dimension(7, 30));
		txtSDT.setColumns(18);
		pnSoDienThoai.add(txtSDT);
		
		lblCheck = new JLabel(strCheckFalse);
		
		lblCheck.setForeground(Color.RED);
		lblCheck.setPreferredSize(new Dimension(10, 20));
		pnSoDienThoai.add(lblCheck);
		

		JPanel pnTenKH = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnTenKH.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenKH);
		
		lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setPreferredSize(new Dimension(100, 20));
		pnTenKH.add(lblTenKH);
		
		txtTenKH = new JTextField();
//		txtTenKH.setEditable(false);
		txtTenKH.setPreferredSize(new Dimension(7, 30));
		txtTenKH.setColumns(20);
		pnTenKH.add(txtTenKH);

		
		
		JPanel pnTongTien = new JPanel();
		pnThongTin.add(pnTongTien);
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setPreferredSize(new Dimension(100, 20));
		pnTongTien.add(lblTongTien);
		
		txtTongTien = new JTextField("0 đồng");
		txtTongTien.setPreferredSize(new Dimension(7, 30));
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(20);
		pnTongTien.add(txtTongTien);
		
		JPanel pnBtn = new JPanel();
		pnThongTin.add(pnBtn);
		
		btnThemHD = new JButton("Thêm hóa đơn", new ImageIcon("data/images/blueAdd_16.png"));
		btnThemHD.setBackground(Color.WHITE);
		pnBtn.add(btnThemHD);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Sách");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblNewLabel);
		
		
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		String[] cols = {"Tên sách", "giá", "Số lượng", "Nhà cung cấp"};
		modelThuoc = new DefaultTableModel(cols, 0);
		tblThuoc = new JTable(modelThuoc);
		JScrollPane scrollPane = new JScrollPane(tblThuoc);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_7.add(scrollPane);
		
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(250);
		panel_5.add(verticalStrut);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		btnThemThuoc = new JButton(">>");
		btnThemThuoc.setBackground(Color.WHITE);
		btnThemThuoc.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_9.add(btnThemThuoc);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setText("1");
		txtSoLuong.setPreferredSize(new Dimension(49, 30));
		txtSoLuong.setColumns(4);
		panel_10.add(txtSoLuong);
		
		JPanel panel_11 = new JPanel();
		panel_8.add(panel_11);
		
		btnBoThuoc = new JButton("<<");
		btnBoThuoc.setBackground(Color.WHITE);
		btnBoThuoc.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_11.add(btnBoThuoc);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("giỏ hàng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_1);
		
		String[] cols3 = {"Tên sách", "giá", "Số lượng", "Thành tiền"};
		modelThuocTGH = new DefaultTableModel(cols3, 0);
		tblThuocTGH = new JTable(modelThuocTGH);
		JScrollPane scrollPane_1 = new JScrollPane(tblThuocTGH);
		scrollPane_1.setPreferredSize(new Dimension(450, 500));
		panel_3.add(scrollPane_1, BorderLayout.CENTER);
		
		btnBoThuoc.addActionListener(this);
		btnThemHD.addActionListener(this);
		btnThemThuoc.addActionListener(this);
		txtSDT.addKeyListener(this);
		
		renderData();
	
	}
	
	private void renderData() throws SQLException {
		// TODO Auto-generated method stub
//		dsThuoc = new Thuoc_dao().getDsThuoc();
		tblThuoc.clearSelection();
		modelThuoc.setRowCount(0);
//		ArrayList<Thuoc> dsThuoc2 = new ArrayList<Thuoc>();
		
//		for(Thuoc t : dsThuoc) {
//			if(t.getSoLuong() == 0) {
//				continue;
//			}
//			dsThuoc2.add(t);
//			
//		}
//		dsThuoc = dsThuoc2;
//		
//		for(Thuoc t : dsThuoc) {
//			modelThuoc.addRow(new Object[] {
//					t.getTenThuoc(), 
//					formatNumberForMoney(t.getDonGia()), 
//					t.getSoLuong(),
//					t.getNhaCungCap().getTenNhaCungCap()
//				});
		}
		
//		Thuốc trong giỏ hàng
//		tblThuocTGH.clearSelection();
//		modelThuocTGH.setRowCount(0);
//		dscthd.forEach(cthd -> {
//			modelThuocTGH.addRow(new Object[] {cthd.getThuoc().getTenThuoc(), formatNumberForMoney(cthd.getThuoc().getDonGia()), cthd.getSoLuong(),formatNumberForMoney(cthd.tinhThanhTien())});
//		});
//		tblThuocTGH.revalidate();
//		tblThuocTGH.repaint();
//	}
	
	private void renderData2() throws SQLException {
		// TODO Auto-generated method stub
//		tblThuoc.clearSelection();
//		modelThuoc.setRowCount(0);
//		ArrayList<Thuoc> dsThuoc2 = new ArrayList<>();
//		
//		for(Thuoc t : dsThuoc) {
//			if(t.getSoLuong() == 0) {
//				continue;
//			}
//			dsThuoc2.add(t);
//			
//		}
//		dsThuoc = dsThuoc2;
//		
//		for(Thuoc t : dsThuoc2) {
//			modelThuoc.addRow(new Object[] {
//					t.getTenThuoc(), 
//					formatNumberForMoney(t.getDonGia()), 
//					t.getSoLuong(),
//					t.getNhaCungCap().getTenNhaCungCap()
//				});
//		}
//		
////		Thuốc trong giỏ hàng
//		tblThuocTGH.clearSelection();
//		modelThuocTGH.setRowCount(0);
//		dscthd.forEach(cthd -> {
//		
//			modelThuocTGH.addRow(new Object[] {cthd.getThuoc().getTenThuoc(), formatNumberForMoney(cthd.getThuoc().getDonGia()), cthd.getSoLuong(),formatNumberForMoney(cthd.tinhThanhTien())});
//		});
	}

	private String formatNumberForMoney(double money) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    String str1 = currencyVN.format(Math.round(money));
	    str1 = str1.substring(0,str1.length() - 2);
	    return str1 + " đồng";
	}

	public void tinhTongTien() {
		double tongTien = 0;
//		for(int i=0; i<dscthd.size(); i++) {
//			
//			tongTien += dscthd.get(i).tinhThanhTien();
//		}
//		txtTongTien.setText(formatNumberForMoney(tongTien));
	}
	
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		
		if(o.equals(btnThemHD)) {
			
			String tenKh = txtTenKH.getText();
			String sdt = txtSDT.getText();
			System.out.println(tenKh);
			
			if(!tenKh.matches("([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ][a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s{1}[A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ][a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)*)")) {
				JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ!");
				txtTenKH.selectAll();
				txtTenKH.requestFocus();
				return;
			}else if(!sdt.matches("0[1-9][0-9]{8}")) {
				JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
				txtSDT.selectAll();
				txtSDT.requestFocus();
				return;
			}}
			
//			KhachHang kh = null;
//			try {
//				kh = new KhachHang_dao().findKhBySdt(sdt);
//			} catch (SQLException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//			
//			if(kh == null) {
//				kh = new KhachHang(tenKh, sdt);
//			}
//			
//			if(dscthd.size() == 0) {
//				JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm thuốc vào giỏ");
//				return;
//			}
//				HoaDon hd = new HoaDon(this.nhanVien,kh , dscthd);
//				
//				try {
//					HoaDon_dao hoaDonDao = new HoaDon_dao();
//					if(hoaDonDao.themHoaDon(hd)) {
//						
//						int choose2 = JOptionPane.showConfirmDialog(contentPane, "Đã thêm hóa đơn thành công, bạn có muốn xuất hóa đơn không ?");
//						if(choose2 == 0) {
//							hd.setMaHD(hoaDonDao.getLastestMaHD());
//							XuatHoaDon_gui xuaHoaDonGUI = new XuatHoaDon_gui();
//							xuaHoaDonGUI.setHoaDon(hd);
//							xuaHoaDonGUI.setVisible(true);
//							xuaHoaDonGUI.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//						}
//						dscthd.clear();
//						tinhTongTien();
//						renderData2();
//					}else {
//						JOptionPane.showMessageDialog(contentPane, hoaDonDao.getError());
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//		}else if(o.equals(btnBoThuoc)) {
//			int idx = tblThuocTGH.getSelectedRow();
//			if(idx == -1) return;
//			Thuoc thuoc = dscthd.get(idx).getThuoc();
//			int indexInDsThuoc = -1;
//			
//			for(int i = 0; i<dsThuoc.size(); i++) {
//				if(thuoc.getMaThuoc() == dsThuoc.get(i).getMaThuoc()) {
//					indexInDsThuoc = i;
//				}
//			}
//
//			if(idx != -1) {
//				int soLuong = 0;
//				try {
//					soLuong = Integer.parseInt(txtSoLuong.getText());
//				}catch (Exception e2) {
//					JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
//					txtSoLuong.requestFocus();
//					return;
//				}
//				
//				if(soLuong > dscthd.get(idx).getSoLuong()) {
//					JOptionPane.showMessageDialog(contentPane, "Số lượng không hợp lệ");
//					txtSoLuong.requestFocus();
//					return;
//				}else if(soLuong == dscthd.get(idx).getSoLuong()) {	
//					dscthd.remove(idx);
//					if(indexInDsThuoc != -1) {
//						dsThuoc.get(indexInDsThuoc).setSoLuong(dsThuoc.get(indexInDsThuoc).getSoLuong() + soLuong);
//					}else {
//						thuoc.setSoLuong(soLuong);
//						dsThuoc.add(thuoc);
//					}
//					
//				}else {
//					dscthd.get(idx).setSoLuong(dscthd.get(idx).getSoLuong() - soLuong);
//					if(indexInDsThuoc != -1) {
//						dsThuoc.get(indexInDsThuoc).setSoLuong(dsThuoc.get(indexInDsThuoc).getSoLuong() + soLuong);
//					}else {
//						thuoc.setSoLuong(soLuong);
//						dsThuoc.add(thuoc);
//					}
//				}
//				
//				tinhTongTien();
//				try {
//					renderData2();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//			
//		}else if(o.equals(btnThemThuoc)) {
//			if(tblThuoc.getSelectedRow() == -1) {
//				return;
//			}
//			
//			int	idx = tblThuoc.getSelectedRow();
//			System.out.println(idx);
//			Thuoc thuoc = dsThuoc.get(idx); // Thuốc
//			
//			
//			int soLuong = 0;
//			try {
//				soLuong = Integer.parseInt(txtSoLuong.getText());
//			}catch (Exception e2) {
//				JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
//				txtSoLuong.requestFocus();
//				return;
//			}
//			
//			if(soLuong <= 0) {
//				JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
//				txtSoLuong.requestFocus();
//				return;
//			}
//			
//			if(soLuong > thuoc.getSoLuong()) {
//				JOptionPane.showMessageDialog(contentPane, "Số lượng không đủ");
//				txtSoLuong.requestFocus();
//				return;
//			}
//			
//			
////			kiểm tra xem đã có thuốc đó trong giỏ chưa
//			int vt = -1;
//			for(int i=0; i<dscthd.size(); i++) {
//				if(dscthd.get(i).getThuoc().getMaThuoc() == thuoc.getMaThuoc())
//					vt = i;
//			}
//			if(vt != -1) { // thêm số lượng
//				dscthd.get(vt).setSoLuong(dscthd.get(vt).getSoLuong() + soLuong);
//				dsThuoc.get(idx).setSoLuong(dsThuoc.get(idx).getSoLuong() - soLuong);
//				if(dsThuoc.get(idx).getSoLuong() == 0) dsThuoc.remove(idx);
//				
//			}else { // thêm thuốc
//				ChiTietHoaDon cthd = new ChiTietHoaDon(thuoc, soLuong);
//				dscthd.add(cthd);
//				dsThuoc.get(idx).setSoLuong(dsThuoc.get(idx).getSoLuong() - soLuong);
//				if(dsThuoc.get(idx).getSoLuong() == 0) dsThuoc.remove(idx);
//			}
//			tinhTongTien();
//			try {
//				renderData2();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		
		}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		String sdt = txtSDT.getText();
		
		if(!sdt.matches("0[1-9]{1}[0-9]{8}")) {
			lblCheck.setText(strCheckFalse);
			lblCheck.setForeground(Color.RED);
			txtTenKH.setEditable(false);
			txtTenKH.setText("");
			return;
		}else {
			lblCheck.setText(strCheckTrue);
			lblCheck.setForeground(Color.GREEN);
//			KhachHang kh = null;
//			try {
//				kh =  new KhachHang_dao().findKhBySdt(sdt);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//			if(Objects.isNull(kh)) {
//				txtTenKH.setText("");
//				txtTenKH.setEditable(true);
//				
//			}else {
//				txtTenKH.setText(kh.getTenKhachHang());
//				txtTenKH.setEditable(false);
//			}
		}
		
		
	}

	private Object KhachHang_dao() {
		// TODO Auto-generated method stub
		return null;
	}

}