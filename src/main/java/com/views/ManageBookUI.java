package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import connectdb.ConnectDB;
//import dao.LoaiThuoc_dao;
//import dao.NhaCungCap_dao;
//import dao.Thuoc_dao;
//import entity.LoaiThuoc;
//import entity.NhaCungCap;
//import entity.Thuoc;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.Box;

public class ManageBookUI extends JFrame implements ActionListener, MouseListener,KeyListener {

	private JPanel contentPane;
	private JComboBox cboLoaiThuoc;
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JComboBox cboNhaCC;
	private JTable tblDsThuoc;
	private DefaultTableModel modelDsThuoc;
	private JTextField txtTimKiem;
	private JTextField txtNgaySanXuat;
	private JTextField txtHanSuDung;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JButton btnThemMoi;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnTimThuoc;
//	private ArrayList<Thuoc> dsThuocs;
//	private ArrayList<NhaCungCap> dsNhaCungCaps;
//	private ArrayList<LoaiThuoc> dsLoaiThuocs;
//	private Thuoc_dao thuocDao;
//	private NhaCungCap_dao nhaCungCapDao;
//	private LoaiThuoc_dao loaiThuocDao;
	private DefaultComboBoxModel<String> modelCboLoaiThuoc;
	private DefaultComboBoxModel<String> modelCboNhaCungCap;
	private JComboBox cboTimTheo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBookUI frame = new ManageBookUI();
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
	public ManageBookUI() throws SQLException {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnTop = new JPanel();
		pnTop.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		pnTop.setFont(new Font("Tahoma", Font.BOLD, 20));
		FlowLayout flowLayout = (FlowLayout) pnTop.getLayout();
		contentPane.add(pnTop, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("Quản lí thuốc");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		pnTop.add(lblTieuDe);

		JPanel pnLeft = new JPanel();
		pnLeft.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		contentPane.add(pnLeft, BorderLayout.WEST);
		pnLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel pnThongTin = new JPanel();
		pnLeft.add(pnThongTin);
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));

		JPanel pnLblThongTin = new JPanel();
		pnLblThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnThongTin.add(pnLblThongTin);

		JLabel lblTitile = new JLabel("Thông Tin Thuốc");
		lblTitile.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnLblThongTin.add(lblTitile);

		JPanel pnLoaiTimKiem = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnLoaiTimKiem.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnThongTin.add(pnLoaiTimKiem);

		JPanel pnLoaiThuoc = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnLoaiThuoc.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnLoaiThuoc);

		JLabel lblLoaiThuoc = new JLabel("Loại thuốc");
		lblLoaiThuoc.setPreferredSize(new Dimension(80, 14));
		pnLoaiThuoc.add(lblLoaiThuoc);

		addDataCboLoaiThuoc();
		cboLoaiThuoc.setPreferredSize(new Dimension(204, 23));
		pnLoaiThuoc.add(cboLoaiThuoc);

		JPanel pnMaThuoc = new JPanel();
		FlowLayout fl_pnMaThuoc = (FlowLayout) pnMaThuoc.getLayout();
		fl_pnMaThuoc.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaThuoc);

		JLabel lblMaThuoc = new JLabel("Mã thuốc");
		lblMaThuoc.setPreferredSize(new Dimension(80, 14));
		pnMaThuoc.add(lblMaThuoc);

		txtMaThuoc = new JTextField();
		txtMaThuoc.setPreferredSize(new Dimension(7, 23));
		pnMaThuoc.add(txtMaThuoc);
		txtMaThuoc.setColumns(22);
		txtMaThuoc.disable();

		JPanel pnTenThuoc = new JPanel();
		FlowLayout fl_pnTenThuoc = (FlowLayout) pnTenThuoc.getLayout();
		fl_pnTenThuoc.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenThuoc);

		JLabel lblTenThuoc = new JLabel("Tên thuốc");
		lblTenThuoc.setPreferredSize(new Dimension(80, 14));
		pnTenThuoc.add(lblTenThuoc);

		txtTenThuoc = new JTextField();
		txtTenThuoc.setPreferredSize(new Dimension(7, 23));
		pnTenThuoc.add(txtTenThuoc);
		txtTenThuoc.setColumns(22);

		JPanel pnNhaCC = new JPanel();
		FlowLayout fl_pnNhaCC = (FlowLayout) pnNhaCC.getLayout();
		fl_pnNhaCC.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnNhaCC);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setPreferredSize(new Dimension(80, 14));
		pnNhaCC.add(lblNhaCungCap);

		addDataCboNhaCC();
		cboNhaCC.setPreferredSize(new Dimension(204, 23));

		pnNhaCC.add(cboNhaCC);

		JPanel pnNgaySanXuat = new JPanel();
		pnThongTin.add(pnNgaySanXuat);

		JLabel lblNgaySanXuat = new JLabel("Ngày sản xuất");
		lblNgaySanXuat.setPreferredSize(new Dimension(80, 14));
		pnNgaySanXuat.add(lblNgaySanXuat);

		txtNgaySanXuat = new JTextField();
		txtNgaySanXuat.setPreferredSize(new Dimension(7, 23));
		txtNgaySanXuat.setColumns(22);
		pnNgaySanXuat.add(txtNgaySanXuat);

		JPanel pnHanSuDung = new JPanel();
		pnThongTin.add(pnHanSuDung);

		JLabel lblHanSuDung = new JLabel("Hạn sử dụng");
		lblHanSuDung.setPreferredSize(new Dimension(80, 14));
		pnHanSuDung.add(lblHanSuDung);

		txtHanSuDung = new JTextField();
		txtHanSuDung.setPreferredSize(new Dimension(7, 23));
		txtHanSuDung.setColumns(22);
		pnHanSuDung.add(txtHanSuDung);

		JPanel pnDonGia = new JPanel();
		pnThongTin.add(pnDonGia);

		JLabel lblDonGia = new JLabel("Đơn giá");
		lblDonGia.setPreferredSize(new Dimension(80, 14));
		pnDonGia.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setPreferredSize(new Dimension(7, 23));
		txtDonGia.setColumns(22);
		pnDonGia.add(txtDonGia);

		JPanel pnSoLuong = new JPanel();
		pnThongTin.add(pnSoLuong);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setPreferredSize(new Dimension(80, 14));
		pnSoLuong.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setPreferredSize(new Dimension(7, 23));
		txtSoLuong.setColumns(22);
		pnSoLuong.add(txtSoLuong);

		JPanel pnChucNang = new JPanel();
		pnThongTin.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(0, 1, 0, 0));

		Box horizontalBox = Box.createHorizontalBox();
		pnChucNang.add(horizontalBox);

		Box horizontalBox_1 = Box.createHorizontalBox();
		pnChucNang.add(horizontalBox_1);

		ImageIcon iconThem = new ImageIcon("data//images//blueAdd_16.png");
		btnThemMoi = new JButton("Thêm", iconThem);
		btnThemMoi.setBackground(Color.WHITE);
		pnChucNang.add(btnThemMoi);

		ImageIcon iconSua = new ImageIcon("data//images//repare.png");
		btnSua = new JButton("Sửa", iconSua);
		btnSua.setBackground(Color.WHITE);
		pnChucNang.add(btnSua);

		ImageIcon iconXoa = new ImageIcon("data//images//trash.png");
		btnXoa = new JButton("Xóa", iconXoa);
		btnXoa.setBackground(Color.WHITE);
		pnChucNang.add(btnXoa);

		ImageIcon iconLamMoi = new ImageIcon("data//images//refresh.png");
		btnLamMoi = new JButton("làm mới", iconLamMoi);
		btnLamMoi.setBackground(Color.WHITE);
		pnChucNang.add(btnLamMoi);

		JPanel pnCenter = new JPanel();
//		pnCenter.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
//				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		contentPane.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));

		JPanel pnCenterTop = new JPanel();
		pnCenterTop.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnCenter.add(pnCenterTop, BorderLayout.NORTH);

		String[] arrCachTim = { "Tên thuốc", "Nhà cung cấp" };
		cboTimTheo = new JComboBox<String>(arrCachTim);
		cboTimTheo.setPreferredSize(new Dimension(120, 23));
		pnCenterTop.add(cboTimTheo);

		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(7, 23));
		pnCenterTop.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		ImageIcon iconTim = new ImageIcon("data//images//search_16.png");
		btnTimThuoc = new JButton("Tìm", iconTim);
		btnTimThuoc.setBackground(Color.WHITE);
		pnCenterTop.add(btnTimThuoc);

		JPanel pnCenterMiddle = new JPanel();
		pnCenter.add(pnCenterMiddle, BorderLayout.SOUTH);

		String[] cols = { "Mã thuốc", "Tên thuốc", "Loại", "Ngày sản xuất", "Hạn sử dụng", "Nhà cung cấp",
				"Đơn giá", "số lượng" };
		modelDsThuoc = new DefaultTableModel(cols, 0);
		tblDsThuoc = new JTable(modelDsThuoc);
		JScrollPane scrtbl = new JScrollPane(tblDsThuoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		pnCenter.add(scrtbl, BorderLayout.CENTER);

		renderData();
		addDataCboLoaiThuoc();

		btnLamMoi.addActionListener(this);
		btnThemMoi.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimThuoc.addActionListener(this);
		tblDsThuoc.addMouseListener(this);
		txtTimKiem.addKeyListener(this);

	}

	private void addDataCboNhaCC() {
//		nhaCungCapDao = new NhaCungCap_dao();
//		dsNhaCungCaps = new ArrayList<NhaCungCap>();
//		dsNhaCungCaps = nhaCungCapDao.getDsNhaCC();
//
//		modelCboNhaCungCap = new DefaultComboBoxModel<String>();
//		for (NhaCungCap ncc : dsNhaCungCaps) {
//			modelCboNhaCungCap.addElement(ncc.getTenNhaCungCap());
//		}
//		cboNhaCC = new JComboBox<String>(modelCboNhaCungCap);
		String[] nhacungcap = {"Kim Dong","So Ha" };
		cboNhaCC = new JComboBox<String>(nhacungcap);
	}

	private void addDataCboLoaiThuoc() {
//		loaiThuocDao = new LoaiThuoc_dao();
//		dsLoaiThuocs = new ArrayList<LoaiThuoc>();
//		dsLoaiThuocs = loaiThuocDao.getDsLoaiThuoc();
//		modelCboLoaiThuoc = new DefaultComboBoxModel<String>();
//		for (LoaiThuoc lt : dsLoaiThuocs) {
//			modelCboLoaiThuoc.addElement(lt.getTenLoai());
//		}
//		cboLoaiThuoc = new JComboBox<String>(modelCboLoaiThuoc);
		String[] loaisach = {"Novel","Comic" };
		cboLoaiThuoc = new JComboBox<String>(loaisach);
	}

	private void renderData() {
//		thuocDao = new Thuoc_dao();
//		dsThuocs = thuocDao.getDsThuoc();
//		modelDsThuoc.setRowCount(0);
//		for (Thuoc th : dsThuocs) {
//			Object[] row = { th.getMaThuoc(), th.getTenThuoc(), th.getLoaiThuoc().getTenLoai(), th.getNgaySanXuat(),
//					th.getNgayHetHan(), th.getNhaCungCap().getTenNhaCungCap(), formatNumberForMoney(th.getDonGia()), th.getSoLuong() };
//			modelDsThuoc.addRow(row);
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLamMoi)) {
			setNull();
		}
		if (o.equals(btnThemMoi)) {
			if (btnThemMoi.getText().equals("Thêm")) {
				setNull();
				btnXoa.setEnabled(false);
				btnSua.setText("Lưu");
				btnThemMoi.setText("Hủy");
			} else if (btnThemMoi.getText().equals("Hủy")) {
				btnSua.setText("Sửa");
				btnThemMoi.setText("Thêm");
				btnXoa.setEnabled(true);
				btnLamMoi.setEnabled(true);
			}
		}

		if (o.equals(btnSua) && btnThemMoi.getText().equals("Hủy")) {
			if (checkData()) {
				int maLoaiThuoc = cboLoaiThuoc.getSelectedIndex() + 1;
				int maNhaCC = cboNhaCC.getSelectedIndex() + 1;
				String tenThuoc = txtTenThuoc.getText().trim();
				String ngaySX = txtNgaySanXuat.getText().trim();
				String hanSD = txtHanSuDung.getText().trim();
				double donGia = Double.parseDouble(txtDonGia.getText().trim());
				int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

//				Thuoc thuoc = new Thuoc(new LoaiThuoc(maLoaiThuoc), tenThuoc, new NhaCungCap(maNhaCC), ngaySX, hanSD,
//						donGia, soLuong);
//				boolean kq = thuocDao.themThuoc(thuoc);
//
//				if (kq) {
//					JOptionPane.showMessageDialog(null, "Thêm thuốc thành công");
//					renderData();
//				} else {
//					JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
//					return;
//				}
				setNull();
			}
		}
		if (o.equals(btnSua) && btnSua.getText().equals("Sửa")) {
			int index = tblDsThuoc.getSelectedRow();
			if(index != -1) {
				if(checkData()) {
					int maLoaiThuoc = cboLoaiThuoc.getSelectedIndex() + 1;
					int maNhaCC = cboNhaCC.getSelectedIndex() + 1;
					int maThuoc = Integer.parseInt(txtMaThuoc.getText().trim()); 
					String tenThuoc = txtTenThuoc.getText().trim();
					String ngaySX = txtNgaySanXuat.getText().trim();
					String hanSD = txtHanSuDung.getText().trim();
					double donGia = Double.parseDouble(txtDonGia.getText().trim());
					int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
					
//					Thuoc thuoc = new Thuoc(maThuoc,new LoaiThuoc(maLoaiThuoc), tenThuoc, new NhaCungCap(maNhaCC), ngaySX, hanSD,
//							donGia, soLuong);
//					System.out.println(thuoc.toString());
//					boolean kq = thuocDao.updateThuoc(thuoc);
//					if (kq) {
//						JOptionPane.showMessageDialog(null, "Sửa thành công");
//						renderData();
//					} else {
//						JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
//						return;
//					}
					setNull();
				}
			}
		}
		if(o.equals(btnXoa)) {
			int index = tblDsThuoc.getSelectedRow();
			if(index != -1) {
				
				int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn xóa!","Xác nhận", JOptionPane.YES_NO_OPTION);
				if(choose == 0) {
//					tblDsThuoc.clearSelection();
//					boolean kq = thuocDao.xoaThuoc(dsThuocs.get(index));
////						//System.out.println(kq);
//					if(kq) {
//						JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
//						renderData();
//						return;
//					}else {
//						JOptionPane.showMessageDialog(contentPane, "Không thể xóa nhân viên này");
//						return;
//					}
				}
			}
		}

	}

	private void setNull() {
		renderData();
		cboLoaiThuoc.setSelectedIndex(0);
		cboNhaCC.setSelectedIndex(0);
		txtMaThuoc.setText("");
		txtTenThuoc.setText("");
		txtNgaySanXuat.setText("");
		txtHanSuDung.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		txtTimKiem.setText("");
	}

	private boolean checkData() {

		String maThuoc = txtMaThuoc.getText().trim();
		String tenThuoc = txtTenThuoc.getText().trim();
		String ngaySX = txtNgaySanXuat.getText().trim();
		String hanSD = txtHanSuDung.getText().trim();
		String donGia = txtDonGia.getText().trim();
		String soLuong = txtSoLuong.getText().trim();

		if (tenThuoc.equals("")) {
			JOptionPane.showMessageDialog(null, "tên thuốc không được bổ trống");
			txtTenThuoc.requestFocus();
			return false;
		} else {
			if (!tenThuoc.matches("^(\\w+\\s*)+$")) {
				JOptionPane.showMessageDialog(null,
						"Tên thuốc không chứa kí tự đặc biệt, có thể có khoảng trắng");
				txtTenThuoc.selectAll();
				txtTenThuoc.requestFocus();
				return false;
			}
		}
		if (ngaySX.equals("")) {
			JOptionPane.showMessageDialog(null, "Ngày sản xuất không được bỏ trống");
			txtNgaySanXuat.requestFocus();
			return false;
		} else {
			if (!ngaySX.matches("^\\d{4}(-\\d{2}){2}$")) {
				JOptionPane.showMessageDialog(null, "Sai định dạng ngày yyyy-mm-dd");
				txtNgaySanXuat.selectAll();
				txtNgaySanXuat.requestFocus();
				return false;
			}
		}

		if (hanSD.equals("")) {
			JOptionPane.showMessageDialog(null, "Hạn sử dụng không được bỏ trống");
			txtHanSuDung.requestFocus();
			return false;
		} else {
			if (!hanSD.matches("^^\\d{4}(-\\d{2}){2}$")) {
				JOptionPane.showMessageDialog(null, "Sai định dạng ngày yyyy-mm-dd");
				txtHanSuDung.selectAll();
				txtHanSuDung.requestFocus();
				return false;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date date1 = sdf.parse(ngaySX);
				java.util.Date date2 = sdf.parse(hanSD);
				if(date2.before(date1)) {
					JOptionPane.showMessageDialog(null, "Hạn sử dụng phải sau ngày ngày sản xuất");
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		}

		if (donGia.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đơn giá");
			txtDonGia.requestFocus();
			return false;
		} else {
			try {
				double dg = Double.parseDouble(donGia);
				if (dg <= 0) {
					JOptionPane.showMessageDialog(null, "đơn giá > 0");
					txtDonGia.selectAll();
					txtDonGia.requestFocus();
					return false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Đơn giá phải là số thực");
				txtDonGia.selectAll();
				txtDonGia.requestFocus();
				return false;
			}
		}
		if (soLuong.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng");
			txtSoLuong.requestFocus();
			return false;
		} else {
			try {
				int sl = Integer.parseInt(soLuong);
				if (sl <= 0) {
					JOptionPane.showMessageDialog(null, "số lượng >= 0");
					txtSoLuong.selectAll();
					txtSoLuong.requestFocus();
					return false;
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Số lượng phải là định dạng số nguyên");
				txtSoLuong.selectAll();
				txtSoLuong.requestFocus();
				return false;
			}
		}
		return true;
	}

	public JPanel getContentpane() {
		return this.contentPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblDsThuoc.getSelectedRow();
		if (row == -1) {
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}
		txtMaThuoc.setText(modelDsThuoc.getValueAt(row, 0).toString());
		txtTenThuoc.setText((String) modelDsThuoc.getValueAt(row, 1));
		cboLoaiThuoc.setSelectedItem(modelDsThuoc.getValueAt(row, 2));
		txtNgaySanXuat.setText((String) modelDsThuoc.getValueAt(row, 3));
		txtHanSuDung.setText((String) modelDsThuoc.getValueAt(row, 4));
		cboNhaCC.setSelectedItem(modelDsThuoc.getValueAt(row, 5));
		txtDonGia.setText(modelDsThuoc.getValueAt(row, 6).toString());
		txtSoLuong.setText(modelDsThuoc.getValueAt(row, 7).toString());

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
	private void renderDataTimKiem() {
//		modelDsThuoc.setRowCount(0);
//		for (Thuoc th : dsThuocs) {
//			Object[] row = { th.getMaThuoc(), th.getTenThuoc(), th.getLoaiThuoc().getTenLoai(), th.getNgaySanXuat(),
//					th.getNgayHetHan(), th.getNhaCungCap().getTenNhaCungCap(), th.getDonGia(), th.getSoLuong() };
//			modelDsThuoc.addRow(row);
//		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
//		String key = txtTimKiem.getText().trim();
//		String type = cboTimTheo.getSelectedItem().toString().trim();
//		dsThuocs = new ArrayList<Thuoc>();
//		if(type.equals("Tên thuốc")) {
//			dsThuocs = thuocDao.TimThuoc("tenThuoc",key);
//			renderDataTimKiem();
//		}else if(type.equals("Nhà cung cấp")) {
//			dsThuocs = thuocDao.TimThuoc("tenNhaCungCap",key);
//			renderDataTimKiem();
//		}
		
	}
	private String formatNumberForMoney(double money) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    String str1 = currencyVN.format(Math.round(money));
	    str1 = str1.substring(0,str1.length() - 2);
	    return str1 + " VND";
	}
	
	private double formatMoneyToDouble(String str) {
		String[] s = str.split("[. VND]");
		String tmp = "";
		
		for (String string : s) {
			tmp += string;
		}
		return Double.parseDouble(tmp);
	}
	
	public Date stringToDate (String strDate) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date date = (Date) formater.parse(strDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
