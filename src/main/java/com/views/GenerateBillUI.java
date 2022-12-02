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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.bson.types.ObjectId;

import com.dao.BillDao;
import com.dao.BookDao;
import com.dao.impl.BillDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.entities.Bill;
import com.entities.BillDetails;
import com.entities.Book;
import com.entities.Customer;
import com.utils.Constants;

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

public class GenerateBillUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
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

//	private String strCheckFalse = "✘";
	private String strCheckTrue = "✔";
	private JLabel lblCheck;

	private JLabel lblTenKH;
	private BillDao billDao;

	private BookDao bookDao;

	private List<Bill> bills;
	private List<Book> books;
	private List<Book> books2;
	private List<BillDetails> cartBooks;

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
	 * 
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

		try {
			billDao = (BillDao) Naming.lookup(Constants.BASE_PATH_RMI + Constants.STUB_BILL);

		} catch (MalformedURLException | RemoteException | NotBoundException e) {

			e.printStackTrace();
		}
		try {
			bookDao = (BookDao) Naming.lookup(Constants.BASE_PATH_RMI + Constants.STUB_BOOK);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {

			e.printStackTrace();
		}

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

//		lblCheck = new JLabel(strCheckFalse);
//
//		lblCheck.setForeground(Color.RED);
//		lblCheck.setPreferredSize(new Dimension(10, 20));
//		pnSoDienThoai.add(lblCheck);

		JPanel pnTenKH = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnTenKH.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenKH);

		lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setPreferredSize(new Dimension(100, 20));
		pnTenKH.add(lblTenKH);

		txtTenKH = new JTextField();
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

		String[] cols = { "Tên sách", "giá", "Số lượng", "Tác giả" };
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

		String[] cols3 = { "Tên sách", "giá", "Số lượng", "Thành tiền" };
		modelThuocTGH = new DefaultTableModel(cols3, 0);
		tblThuocTGH = new JTable(modelThuocTGH);
		JScrollPane scrollPane_1 = new JScrollPane(tblThuocTGH);
		scrollPane_1.setPreferredSize(new Dimension(450, 500));
		panel_3.add(scrollPane_1, BorderLayout.CENTER);

		btnBoThuoc.addActionListener(this);
		btnThemHD.addActionListener(this);
		btnThemThuoc.addActionListener(this);

		renderData();

	}

	private void renderData() throws SQLException {
		books = new ArrayList<Book>();
		books2 = new ArrayList<Book>();
		cartBooks = new ArrayList<BillDetails>();
		try {
			books = bookDao.getAllBook();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblThuoc.clearSelection();
		modelThuoc.setRowCount(0);
		for (Book b : books) {
			if (b.getQuantity() == 0) {
				continue;
			}
			books2.add(b);
		}
		for (Book b : books2) {
			modelThuoc.addRow(new Object[] { b.getName(), formatNumberForMoney(b.getPrice()), b.getQuantity(),
					b.getAuthor().getName() });
		}

//		Thuốc trong giỏ hàng
		tblThuocTGH.clearSelection();
		modelThuocTGH.setRowCount(0);
		cartBooks.forEach(cthd -> {
			modelThuocTGH
					.addRow(new Object[] { cthd.getBook().getName(), formatNumberForMoney(cthd.getBook().getPrice()),
							cthd.getQuantity(), formatNumberForMoney(cthd.countSubTotal()) });
		});
		tblThuocTGH.revalidate();
		tblThuocTGH.repaint();
	}

	private void renderData2() throws SQLException {
		// TODO Auto-generated method stub
		tblThuoc.clearSelection();
		modelThuoc.setRowCount(0);
		ArrayList<Book> bills2 = new ArrayList<>();

		for (Book b : books) {
			if (b.getQuantity() == 0) {
				continue;
			}
			bills2.add(b);

		}

		for (Book b : bills2) {
			modelThuoc.addRow(new Object[] { b.getName(), formatNumberForMoney(b.getPrice()), b.getQuantity(),
					b.getAuthor().getName() });
		}

//		Thuốc trong giỏ hàng
		tblThuocTGH.clearSelection();
		modelThuocTGH.setRowCount(0);
		cartBooks.forEach(cthd -> {

			modelThuocTGH
					.addRow(new Object[] { cthd.getBook().getName(), formatNumberForMoney(cthd.getBook().getPrice()),
							cthd.getQuantity(), formatNumberForMoney(cthd.countSubTotal()) });
		});
	}

	private String formatNumberForMoney(double money) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str1 = currencyVN.format(Math.round(money));
		str1 = str1.substring(0, str1.length() - 2);
		return str1 + " đồng";
	}

	public double tinhTongTien() {
		double tongTien = 0;
		for (int i = 0; i < cartBooks.size(); i++) {

			tongTien += cartBooks.get(i).countSubTotal();
		}
		txtTongTien.setText(formatNumberForMoney(tongTien));
		return tongTien;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnThemHD)) {

			String tenKh = txtTenKH.getText();
			String sdt = txtSDT.getText();
			System.out.println(tenKh);

			if (!sdt.matches("0[1-9][0-9]{8}")) {
				JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
				txtSDT.selectAll();
				txtSDT.requestFocus();
				return;

			} else if (!tenKh.matches(
					"([A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ][a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s{1}[A-VXYỲỌÁẦẢẤỜỄÀẠẰỆẾÝỘẬỐŨỨĨÕÚỮỊỖÌỀỂẨỚẶÒÙỒỢÃỤỦÍỸẮẪỰỈỎỪỶỞÓÉỬỴẲẸÈẼỔẴẺỠƠÔƯĂÊÂĐ][a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)*)")) {
				JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ!");
				txtTenKH.selectAll();
				txtTenKH.requestFocus();
				return;
			}

			if (cartBooks.size() == 0) {
				JOptionPane.showMessageDialog(contentPane, "Vui lòng thêm sách vào giỏ");
				return;
			}

			Customer cus = new Customer(new ObjectId(), tenKh, sdt);
			Bill bill = new Bill(new ObjectId(), new Date(), tinhTongTien(), cus, cartBooks);

			try {
				try {
					if (billDao.add(bill)) {

						int choose2 = JOptionPane.showConfirmDialog(contentPane,
								"Đã thêm hóa đơn thành công, bạn có muốn xuất hóa đơn không ?");
						if (choose2 == 0) {
							ExportBillUI export = new ExportBillUI();
							export.setBill(bill);
							export.setVisible(true);
							export.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						}

						tinhTongTien();
//					renderData2();
						for (BillDetails d : cartBooks) {

							Book a = d.getBook();
							bookDao.update(a);
						}
						cartBooks.clear();
						renderData();

					} else {
						JOptionPane.showMessageDialog(contentPane, "opps");
					}
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (o.equals(btnBoThuoc)) {

			int idx = tblThuocTGH.getSelectedRow();
			if (idx == -1)
				return;
			Book b = cartBooks.get(idx).getBook();
			int indexInDsThuoc = -1;

			for (int i = 0; i < books2.size(); i++) {
				if (b.getId() == books2.get(i).getId()) {
					indexInDsThuoc = i;
				}
			}
			if (idx != -1) {
				int soLuong = 0;
				try {
					soLuong = Integer.parseInt(txtSoLuong.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
					txtSoLuong.requestFocus();
					return;
				}

				if (soLuong > cartBooks.get(idx).getQuantity()) {
					JOptionPane.showMessageDialog(contentPane, "Số lượng không hợp lệ");
					txtSoLuong.requestFocus();
					return;
				} else if (soLuong == cartBooks.get(idx).getQuantity()) {
					cartBooks.remove(idx);
					if (indexInDsThuoc != -1) {
						books2.get(indexInDsThuoc).setQuantity(books2.get(indexInDsThuoc).getQuantity() + soLuong);
					} else {
						b.setQuantity(soLuong);
						books2.add(b);
					}

				} else {
					cartBooks.get(idx).setQuantity(cartBooks.get(idx).getQuantity() - soLuong);
					if (indexInDsThuoc != -1) {
						books2.get(indexInDsThuoc).setQuantity(books2.get(indexInDsThuoc).getQuantity() + soLuong);
					} else {
						b.setQuantity(soLuong);
						books2.add(b);
					}
				}
//				
				tinhTongTien();
				try {
					renderData2();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (o.equals(btnThemThuoc)) {
			if (tblThuoc.getSelectedRow() == -1) {
				return;
			}

			int idx = tblThuoc.getSelectedRow();
			System.out.println(idx);
			Book b = books2.get(idx);

			int soLuong = 0;
			try {
				soLuong = Integer.parseInt(txtSoLuong.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
				txtSoLuong.requestFocus();
				return;
			}

			if (soLuong <= 0) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng phải lớn hơn 0");
				txtSoLuong.requestFocus();
				return;
			}

			if (soLuong > b.getQuantity()) {
				JOptionPane.showMessageDialog(contentPane, "Số lượng không đủ");
				txtSoLuong.requestFocus();
				return;
			}

//			kiểm tra xem đã có thuốc đó trong giỏ chưa
			int vt = -1;
			for (int i = 0; i < cartBooks.size(); i++) {
				if (cartBooks.get(i).getBook().getId() == b.getId())
					vt = i;
			}
			if (vt != -1) { // thêm số lượng
				cartBooks.get(vt).setQuantity(cartBooks.get(vt).getQuantity() + soLuong);
				books2.get(idx).setQuantity(books2.get(idx).getQuantity() - soLuong);
				if (books2.get(idx).getQuantity() == 0)
					books2.remove(idx);

			} else { // thêm thuốc
				BillDetails d = new BillDetails(new ObjectId(), b, soLuong);
				cartBooks.add(d);
				books2.get(idx).setQuantity(books2.get(idx).getQuantity() - soLuong);
				if (books2.get(idx).getQuantity() == 0)
					books2.remove(idx);
			}
			tinhTongTien();
			try {
				renderData2();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}