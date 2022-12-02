package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.bson.types.ObjectId;

import com.dao.IPublisherDao;
import com.dao.impl.PublisherDaoImpl;
import com.entities.Publisher;

//import org.jdesktop.swingx.prompt.PromptSupport;
//
//import dao.KhachHangDAO;
//import dao.NhanVienDAO;
//import util.Placeholder;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class CustomerUI extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JPanel contentPane;
	private JPanel out;
	private JTextField txtNhapLieu;
	private JTable tblKhachHang;
	private JTextField txtMaKh, txtTenKh, txtSdt, txtDiachi;
	private DefaultTableModel model;
	private JButton btnSuaKh, btnLuu;
	private JButton btnLayToanBoDuLieu;
	private DefaultComboBoxModel<String> cboLoaiTimKiem;
	private JComboBox<String> cmbLoaiTimKiem;
	private List<Publisher> publishers;
	private IPublisherDao pubDao;
	private JButton btnXoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerUI frame = new CustomerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public CustomerUI() throws SQLException {

		try {
			pubDao = new PublisherDaoImpl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("nhà xuất bản");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Quản lí nhà xuất bản");
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		top.add(title);
		out.add(top);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		out.add(bottom);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		bottom.add(contentPane, BorderLayout.CENTER);
		JPanel pnLeft = new JPanel();
		Border compound = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),
				BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		pnLeft.setBorder(compound);
		contentPane.add(pnLeft);

		JPanel pnThongTinKh = new JPanel();
		pnLeft.add(pnThongTinKh);
		pnThongTinKh.setLayout(new BoxLayout(pnThongTinKh, BoxLayout.Y_AXIS));

		JPanel pnTieuDe = new JPanel();
		pnThongTinKh.add(pnTieuDe);

		JLabel lblTieuDe = new JLabel("Chỉnh sửa thông tin");
		lblTieuDe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTieuDe.add(lblTieuDe);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut_1);

		JPanel pnMaKh = new JPanel();
		FlowLayout fl_pnMaKh = (FlowLayout) pnMaKh.getLayout();
		fl_pnMaKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnMaKh);

		JLabel lblMaKh = new JLabel("Mã KH             ");
		lblMaKh.setPreferredSize(new Dimension(100, 14));
		pnMaKh.add(lblMaKh);

		txtMaKh = new JTextField();
		txtMaKh.setEnabled(false);
		txtMaKh.setPreferredSize(new Dimension(7, 30));
		pnMaKh.add(txtMaKh);
		txtMaKh.setColumns(20);

		JPanel pnTenKh = new JPanel();
		FlowLayout fl_pnTenKh = (FlowLayout) pnTenKh.getLayout();
		fl_pnTenKh.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnTenKh);

		JLabel lblTenKh = new JLabel("Tên");
		lblTenKh.setPreferredSize(new Dimension(100, 14));
		pnTenKh.add(lblTenKh);

		txtTenKh = new JTextField();
		txtTenKh.setPreferredSize(new Dimension(7, 30));
		txtTenKh.setColumns(20);
		pnTenKh.add(txtTenKh);

		JPanel pnSoDienThoai = new JPanel();
		FlowLayout fl_pnSoDienThoai = (FlowLayout) pnSoDienThoai.getLayout();
		fl_pnSoDienThoai.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnSoDienThoai);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setPreferredSize(new Dimension(100, 14));
		pnSoDienThoai.add(lblSdt);

		txtSdt = new JTextField();
		txtSdt.setPreferredSize(new Dimension(7, 30));
		txtSdt.setColumns(20);
		pnSoDienThoai.add(txtSdt);

		JPanel pnDiachi = new JPanel();
		FlowLayout flpndiachi = (FlowLayout) pnDiachi.getLayout();
		flpndiachi.setAlignment(FlowLayout.LEFT);
		pnThongTinKh.add(pnDiachi);

		JLabel lblDiachi = new JLabel("Địa chỉ");
		lblDiachi.setPreferredSize(new Dimension(100, 14));
		pnDiachi.add(lblDiachi);

		txtDiachi = new JTextField();
		txtDiachi.setPreferredSize(new Dimension(7, 30));
		txtDiachi.setColumns(20);
		pnDiachi.add(txtDiachi);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnThongTinKh.add(verticalStrut);

		JPanel pnChucNang = new JPanel();
		pnThongTinKh.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(0, 1, 0, 5));

		btnSuaKh = new JButton("Sửa");
		btnSuaKh.setBackground(Color.WHITE);
		btnSuaKh.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
		btnSuaKh.setEnabled(false);
//		btnSuaKh.setIconTextGap(30);
		pnChucNang.add(btnSuaKh);

		btnLuu = new JButton("Lưu");
		btnLuu.setBackground(Color.WHITE);
		btnLuu.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
		btnLuu.setEnabled(false);
//		btnLuu.setIconTextGap(30);
		pnChucNang.add(btnLuu);

		btnXoa = new JButton("xóa");
		btnXoa.setBackground(Color.WHITE);
//		btnXoa.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
//		btnXoa.setEnabled(false);
		pnChucNang.add(btnXoa);

		JPanel pnRight = new JPanel();
		contentPane.add(pnRight);
		pnRight.setLayout(new BorderLayout(0, 0));

		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		pnRight.add(pnTimKiem, BorderLayout.NORTH);

		JLabel lblKieuTimKiem = new JLabel("Tìm kiếm theo:");
		pnTimKiem.add(lblKieuTimKiem);

		cboLoaiTimKiem = new DefaultComboBoxModel<String>();
		cmbLoaiTimKiem = new JComboBox<String>(cboLoaiTimKiem);
		cmbLoaiTimKiem.setToolTipText("tìm kiếm theo");
		cmbLoaiTimKiem.setBackground(Color.WHITE);
		cmbLoaiTimKiem.setPreferredSize(new Dimension(130, 22));
		pnTimKiem.add(cmbLoaiTimKiem);
		cboLoaiTimKiem.addElement((String) "Tên");
		cboLoaiTimKiem.addElement((String) "Số điện thoại");

		JLabel lblTimKiem = new JLabel("Nhập giá trị tìm kiếm:");
		lblTimKiem.setPreferredSize(new Dimension(130, 25));
		pnTimKiem.add(lblTimKiem);

		txtNhapLieu = new JTextField();
		txtNhapLieu.setPreferredSize(new Dimension(7, 25));
		pnTimKiem.add(txtNhapLieu);
		txtNhapLieu.setColumns(20);

		btnLayToanBoDuLieu = new JButton("Xem tất cả");
		btnLayToanBoDuLieu.setPreferredSize(new Dimension(130, 25));
		btnLayToanBoDuLieu.setBackground(Color.WHITE);
		btnLayToanBoDuLieu.setIcon(new ImageIcon("data\\images\\refresh.png"));
		pnTimKiem.add(btnLayToanBoDuLieu);

		JPanel pnTableKh = new JPanel();
		pnRight.add(pnTableKh, BorderLayout.CENTER);
		pnTableKh.setLayout(new BorderLayout(0, 0));

		String[] cols_dskh = { "Mã Khách hàng", "Tên Khách hàng", "Số điện thoại", "Địa chỉ" };
		model = new DefaultTableModel(cols_dskh, 0);
		tblKhachHang = new JTable(model);
		JScrollPane scrTableNhanVien = new JScrollPane(tblKhachHang);
		scrTableNhanVien.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrTableNhanVien.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTableKh.add(scrTableNhanVien);

		btnLayToanBoDuLieu.addActionListener(this);
		btnSuaKh.addActionListener(this);
		tblKhachHang.addMouseListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		txtNhapLieu.addKeyListener(this);

		renderData();
		setEnabledEditForm(false);

	}

	private void renderData() {
		try {
			publishers = new ArrayList<Publisher>();
			publishers = pubDao.getAll();
			System.out.println(publishers.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (!Objects.isNull(publishers)) {
			model.setRowCount(0);
			for (Publisher kh : publishers) {
				model.addRow(new Object[] { kh.getId(), kh.getName(), kh.getPhoneNumber(), kh.getAddress() });
			}
		}

	}

	private void renderData2() {
		if (!Objects.isNull(publishers)) {
			model.setRowCount(0);
			for (Publisher kh : publishers) {
				model.addRow(new Object[] { kh.getId(), kh.getName(), kh.getPhoneNumber(), kh.getAddress() });
			}
		}

	}

	public boolean kiemTraSo(String ten) {
		char arrTen[] = ten.toCharArray();
		for (int i = 0; i < ten.length(); i++) {
			String cTen = String.valueOf(arrTen[i]);
			if (cTen.matches("[0-9]"))
				return true;

		}
		return false;
	}

	public JPanel getContentPane() {
		return this.out;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o.equals(btnSuaKh)) {
			if (btnSuaKh.getText().equals("Hủy")) {
				setEnabledEditForm(false);
				int r = tblKhachHang.getSelectedRow();
				if (r == -1)
					return;
				txtMaKh.setText(tblKhachHang.getValueAt(r, 0).toString());
				txtTenKh.setText(tblKhachHang.getValueAt(r, 1).toString());
				txtSdt.setText(tblKhachHang.getValueAt(r, 2).toString());

			} else {
				setEnabledEditForm(true);
				txtTenKh.requestFocus();
				btnSuaKh.setText("Hủy");
			}

		} else if (o.equals(btnLuu)) {

			if (!checkDataUpdate())
				return;

			Publisher kh = new Publisher(new ObjectId(txtMaKh.getText()), txtTenKh.getText(), txtSdt.getText(),
					txtDiachi.getText());
			boolean isSuccess = false;
			try {
				isSuccess = pubDao.update(kh);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (isSuccess) {
				JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				renderData();
				return;
			} else {
				JOptionPane.showMessageDialog(null, "Cập nhật không thành công, vui lòng thử lại");
			}
		} else if (o.equals(btnXoa)) {
			int r = tblKhachHang.getSelectedRow();
			System.out.println(r);
			if (r == -1) {
				return;
			}
			ObjectId id = new ObjectId(tblKhachHang.getValueAt(r, 0).toString());
			System.out.println(id);
			try {
				pubDao.delete(id);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
				renderData();
				return;
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
		}

	}

	private boolean checkDataUpdate() {
		// TODO Auto-generated method stub
		String ten = txtTenKh.getText().trim();
		String sdt = txtSdt.getText().trim();

		if (!ten.matches(
				"([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ][a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s{1}[A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ][a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)*)")) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ!");
			txtTenKh.selectAll();
			txtTenKh.requestFocus();
			return false;
		} else if (!sdt.matches("0[1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
			txtSdt.selectAll();
			txtSdt.requestFocus();
			return false;
		}

		return true;
	}

	private void setEnabledEditForm(boolean is) {
		txtTenKh.setEnabled(is);
		txtSdt.setEnabled(is);
		btnLuu.setEnabled(is);

		if (!is) {
			btnSuaKh.setText("Sửa");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int r = tblKhachHang.getSelectedRow();
		if (r == -1)
			return;

		setEnabledEditForm(false);
		btnSuaKh.setEnabled(true);

		String maString = tblKhachHang.getValueAt(r, 0).toString();
		String tenString = tblKhachHang.getValueAt(r, 1).toString();
		String sdt = tblKhachHang.getValueAt(r, 2).toString();
		String diachi = tblKhachHang.getValueAt(r, 3).toString();

		txtMaKh.setText(maString);
		txtTenKh.setText(tenString);
		txtSdt.setText(sdt);
		txtDiachi.setText(diachi);

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
		System.out.println('a');
		String type = (String) cboLoaiTimKiem.getSelectedItem();
		String value = txtNhapLieu.getText();

		if (type.equals("Tên")) {
			System.out.println("b");
			try {
				publishers = pubDao.findByName(value);
				renderData2();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (type.equals("Số điện thoại")) {
			Publisher p = null;
			try {
				p = pubDao.findBySdt(value);
				publishers = new ArrayList<Publisher>();
				publishers.add(p);
				renderData2();

			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}