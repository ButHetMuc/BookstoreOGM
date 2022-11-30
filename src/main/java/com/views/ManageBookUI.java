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
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

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

import org.bson.types.ObjectId;

import com.dao.BookDao;
import com.dao.IAuthorDao;
import com.dao.ICategoriesDao;
import com.dao.impl.AuthorDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.CategoryDaoImpl;
import com.entities.Author;
import com.entities.Book;
import com.entities.Category;
import com.entities.Publisher;

//import connectdb.ConnectDB;
//import dao.LoaiBook_dao;
//import dao.NhaCungCap_dao;
//import dao.Book_dao;
//import entity.LoaiBook;
//import entity.NhaCungCap;
//import entity.Book;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.Box;

public class ManageBookUI extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JPanel contentPane;
	private JComboBox cboLoaiBook;
	private JTextField txtMaBook;
	private JTextField txtTenBook;
	private JComboBox cboTacGia;
	private JTable tblDsBook;
	private DefaultTableModel modelDsBook;
	private JTextField txtTimKiem;
	private JTextField txtNamXuatBan;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JButton btnThemMoi;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnTimBook;
	private List<Book> dsBooks;
//	private ArrayList<NhaCungCap> dsNhaCungCaps;
//	private ArrayList<LoaiBook> dsLoaiBooks;
	private BookDao bookDao;
	private IAuthorDao authorDao;
	private ICategoriesDao categoryDao;
//	private NhaCungCap_dao nhaCungCapDao;
//	private LoaiBook_dao loaiBookDao;
	private DefaultComboBoxModel<String> modelCboLoaiBook;
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

		JLabel lblTieuDe = new JLabel("Quản lí sách");
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

		JLabel lblTitile = new JLabel("Thông Tin Sách");
		lblTitile.setFont(new Font("Tahoma", Font.BOLD, 17));
		pnLblThongTin.add(lblTitile);

		JPanel pnLoaiTimKiem = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnLoaiTimKiem.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnThongTin.add(pnLoaiTimKiem);

		JPanel pnLoaiBook = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnLoaiBook.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnLoaiBook);

		JLabel lblLoaiBook = new JLabel("Thể loại");
		lblLoaiBook.setPreferredSize(new Dimension(80, 14));
		pnLoaiBook.add(lblLoaiBook);

		addDataCboLoaiBook();
		cboLoaiBook.setPreferredSize(new Dimension(204, 23));
		pnLoaiBook.add(cboLoaiBook);

		JPanel pnMaBook = new JPanel();
		FlowLayout fl_pnMaBook = (FlowLayout) pnMaBook.getLayout();
		fl_pnMaBook.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaBook);

		JLabel lblMaBook = new JLabel("Mã sách");
		lblMaBook.setPreferredSize(new Dimension(80, 14));
		pnMaBook.add(lblMaBook);

		txtMaBook = new JTextField();
		txtMaBook.setPreferredSize(new Dimension(7, 23));
		pnMaBook.add(txtMaBook);
		txtMaBook.setColumns(22);
		txtMaBook.disable();

		JPanel pnTenBook = new JPanel();
		FlowLayout fl_pnTenBook = (FlowLayout) pnTenBook.getLayout();
		fl_pnTenBook.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenBook);

		JLabel lblTenBook = new JLabel("Tên sách");
		lblTenBook.setPreferredSize(new Dimension(80, 14));
		pnTenBook.add(lblTenBook);

		txtTenBook = new JTextField();
		txtTenBook.setPreferredSize(new Dimension(7, 23));
		pnTenBook.add(txtTenBook);
		txtTenBook.setColumns(22);

		JPanel pnNhaCC = new JPanel();
		FlowLayout fl_pnNhaCC = (FlowLayout) pnNhaCC.getLayout();
		fl_pnNhaCC.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnNhaCC);

		JLabel lblTacGia = new JLabel("Tác giả");
		lblTacGia.setPreferredSize(new Dimension(80, 14));
		pnNhaCC.add(lblTacGia);

		addDatacboTacGia();
		cboTacGia.setPreferredSize(new Dimension(204, 23));

		pnNhaCC.add(cboTacGia);

		JPanel pnNgaySanXuat = new JPanel();
		pnThongTin.add(pnNgaySanXuat);

		JLabel lblNgaySanXuat = new JLabel("Năm xuất bản");
		lblNgaySanXuat.setPreferredSize(new Dimension(80, 14));
		pnNgaySanXuat.add(lblNgaySanXuat);

		txtNamXuatBan = new JTextField();
		txtNamXuatBan.setPreferredSize(new Dimension(7, 23));
		txtNamXuatBan.setColumns(22);
		pnNgaySanXuat.add(txtNamXuatBan);
		txtNamXuatBan.setText("1");

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
		btnSua.setEnabled(false);

		ImageIcon iconXoa = new ImageIcon("data//images//trash.png");
		btnXoa = new JButton("Xóa", iconXoa);
		btnXoa.setBackground(Color.WHITE);
		pnChucNang.add(btnXoa);
		btnXoa.setEnabled(false);

		ImageIcon iconLamMoi = new ImageIcon("data//images//refresh.png");
		btnLamMoi = new JButton("làm mới", iconLamMoi);
		btnLamMoi.setBackground(Color.WHITE);
		pnChucNang.add(btnLamMoi);
		btnLamMoi.setEnabled(false);

		JPanel pnCenter = new JPanel();
//		pnCenter.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
//				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		contentPane.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));

		JPanel pnCenterTop = new JPanel();
		pnCenterTop.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnCenter.add(pnCenterTop, BorderLayout.NORTH);

		String[] arrCachTim = { "Tên Sách", "Tên tác giả", "Tên nhà xuất bản" };
		cboTimTheo = new JComboBox<String>(arrCachTim);
		cboTimTheo.setPreferredSize(new Dimension(120, 23));
		pnCenterTop.add(cboTimTheo);

		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(7, 23));
		pnCenterTop.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimBook = new JButton("Tìm kiếm");
		btnTimBook.setBackground(Color.WHITE);
		pnCenterTop.add(btnTimBook);

		JPanel pnCenterMiddle = new JPanel();
		pnCenter.add(pnCenterMiddle, BorderLayout.SOUTH);

		String[] cols = { "Mã sách", "Tên sách", "Thể loại", "Năm xuất bản", "Tác giả", "Đơn giá", "số lượng" };
		modelDsBook = new DefaultTableModel(cols, 0);
		tblDsBook = new JTable(modelDsBook);
		JScrollPane scrtbl = new JScrollPane(tblDsBook, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		pnCenter.add(scrtbl, BorderLayout.CENTER);

		// init dao
		bookDao = new BookDaoImpl();
		authorDao = new AuthorDaoImpl();
		categoryDao = new CategoryDaoImpl();

		renderData();
		addDataCboLoaiBook();

		btnLamMoi.addActionListener(this);
		btnThemMoi.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimBook.addActionListener(this);
		tblDsBook.addMouseListener(this);
		txtTimKiem.addKeyListener(this);

	}

	private void addDatacboTacGia() {
//		nhaCungCapDao = new NhaCungCap_dao();
//		dsNhaCungCaps = new ArrayList<NhaCungCap>();
//		dsNhaCungCaps = nhaCungCapDao.getDsNhaCC();
//
//		modelCboNhaCungCap = new DefaultComboBoxModel<String>();
//		for (NhaCungCap ncc : dsNhaCungCaps) {
//			modelCboNhaCungCap.addElement(ncc.getTenNhaCungCap());
//		}
//		cboTacGia = new JComboBox<String>(modelCboNhaCungCap);

		String[] nhacungcap = { "Kim Dong", "So Ha" };
		cboTacGia = new JComboBox<String>(nhacungcap);
	}

	private void addDataCboLoaiBook() {
//		loaiBookDao = new LoaiBook_dao();
//		dsLoaiBooks = new ArrayList<LoaiBook>();
//		dsLoaiBooks = loaiBookDao.getDsLoaiBook();
//		modelCboLoaiBook = new DefaultComboBoxModel<String>();
//		for (LoaiBook lt : dsLoaiBooks) {
//			modelCboLoaiBook.addElement(lt.getTenLoai());
//		}
//		cboLoaiBook = new JComboBox<String>(modelCboLoaiBook);
		String[] loaisach = { "Novel", "Comic" };
		cboLoaiBook = new JComboBox<String>(loaisach);
	}

	private void renderData() {
		dsBooks = bookDao.getAllBook();

		System.out.println("render data," + dsBooks.size());
		modelDsBook.setRowCount(0);
		for (Book book : dsBooks) {
			Set<Category> cates = book.getCategories();
			String cateString = "";
			for (Category cate : cates) {
				cateString += cate.getName() + ",";
			}
			Object[] row = { book.getId(), book.getName(), cateString, book.getYear(), book.getAuthor().getName(),
					book.getPrice(), book.getQuantity() };
			modelDsBook.addRow(row);
		}

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
				btnSua.setText("Lưu");
				btnThemMoi.setText("Hủy");
				btnXoa.setEnabled(false);
				btnSua.setEnabled(true);
				btnLamMoi.setEnabled(true);
			} else if (btnThemMoi.getText().equals("Hủy")) {
				btnSua.setText("Sửa");
				btnThemMoi.setText("Thêm");
				btnXoa.setEnabled(true);
				btnLamMoi.setEnabled(true);
			}
		}

		if (o.equals(btnSua) && btnSua.getText().equals("Lưu")) {
			if (checkData()) {

				// static data

				Author a = new Author(new ObjectId(), "cong", "0868283916");
				Publisher p = new Publisher(new ObjectId(), "publisher 1", "095848333", "Ha Giang");
				Set<Category> cates = new HashSet<>();
				cates.add(new Category(new ObjectId(), "category 1"));
				cates.add(new Category(new ObjectId(), "category 4"));

				String name = txtTenBook.getText();
				int namXuatBan = Integer.parseInt(txtNamXuatBan.getText().trim());
				int donGia = Integer.parseInt(txtDonGia.getText().trim());
				int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

				Book b = new Book(new ObjectId(), name, a, cates, p, namXuatBan, donGia, soLuong);
				boolean kq = bookDao.add(b);

				if (kq) {
					JOptionPane.showMessageDialog(null, "Thêm sách thành công");

					renderData();
				} else {
					JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
					return;
				}
				setNull();
			}
		}

		if (o.equals(btnSua) && btnSua.getText().equals("Sửa")) {
			int index = tblDsBook.getSelectedRow();
			if (index != -1) {
				if (checkData()) {
					// static data
					Author a = dsBooks.get(index).getAuthor();
					Publisher p = dsBooks.get(index).getPublisher();
					Set<Category> cates = dsBooks.get(index).getCategories();

					String name = txtTenBook.getText();
					int namXuatBan = Integer.parseInt(txtNamXuatBan.getText().trim());
					int donGia = Integer.parseInt(txtDonGia.getText().trim());
					int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

					ObjectId bookId = new ObjectId(tblDsBook.getValueAt(index, 0).toString());

					Book newBook = new Book(bookId, name, a, cates, p, namXuatBan, donGia, soLuong);

					boolean kq = bookDao.update(newBook);
					if (kq) {
						JOptionPane.showMessageDialog(null, "Sửa thành công");
						renderData();
						setNull();
					} else {
						JOptionPane.showMessageDialog(null, "Có lỗi xảy ra");
						return;
					}

				}
			}
		}
		// done
		if (o.equals(btnXoa)) {
			int index = tblDsBook.getSelectedRow();
			if (index != -1) {

				int choose = JOptionPane.showConfirmDialog(contentPane, "Chắc chắn xóa!", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					tblDsBook.clearSelection();

					boolean kq = bookDao.delete(new ObjectId(tblDsBook.getValueAt(index, 0).toString()));

					if (kq) {
						JOptionPane.showMessageDialog(contentPane, "Xóa thành công");
						renderData();
						return;
					} else {
						JOptionPane.showMessageDialog(contentPane, "Không thể xóa quyển sách này");
						return;
					}

				}
			}
		}

	}

	private void setNull() {
//		renderData();
		cboLoaiBook.setSelectedIndex(0);
		cboTacGia.setSelectedIndex(0);
		txtMaBook.setText("");
		txtTenBook.setText("");
		txtNamXuatBan.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		txtTimKiem.setText("");
	}

	private boolean checkData() {

//		String maBook = txtMaBook.getText().trim();
//		String tenBook = txtTenBook.getText().trim();
//		String ngaySX = txtNamXuatBan.getText().trim();
//		String donGia = txtDonGia.getText().trim();
//		String soLuong = txtSoLuong.getText().trim();
//
//		if (tenBook.equals("")) {
//			JOptionPane.showMessageDialog(null, "tên thuốc không được bổ trống");
//			txtTenBook.requestFocus();
//			return false;
//		} else {
//			if (!tenBook.matches("^(\\w+\\s*)+$")) {
//				JOptionPane.showMessageDialog(null,
//						"Tên thuốc không chứa kí tự đặc biệt, có thể có khoảng trắng");
//				txtTenBook.selectAll();
//				txtTenBook.requestFocus();
//				return false;
//			}
//		}
//		if (ngaySX.equals("")) {
//			JOptionPane.showMessageDialog(null, "Ngày sản xuất không được bỏ trống");
//			txtNamXuatBan.requestFocus();
//			return false;
//		} else {
//			if (!ngaySX.matches("^\\d{4}(-\\d{2}){2}$")) {
//				JOptionPane.showMessageDialog(null, "Sai định dạng ngày yyyy-mm-dd");
//				txtNamXuatBan.selectAll();
//				txtNamXuatBan.requestFocus();
//				return false;
//			}
//		}
//
//		if (donGia.equals("")) {
//			JOptionPane.showMessageDialog(null, "Vui lòng nhập đơn giá");
//			txtDonGia.requestFocus();
//			return false;
//		} else {
//			try {
//				double dg = Double.parseDouble(donGia);
//				if (dg <= 0) {
//					JOptionPane.showMessageDialog(null, "đơn giá > 0");
//					txtDonGia.selectAll();
//					txtDonGia.requestFocus();
//					return false;
//				}
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "Đơn giá phải là số thực");
//				txtDonGia.selectAll();
//				txtDonGia.requestFocus();
//				return false;
//			}
//		}
//		if (soLuong.equals("")) {
//			JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng");
//			txtSoLuong.requestFocus();
//			return false;
//		} else {
//			try {
//				int sl = Integer.parseInt(soLuong);
//				if (sl <= 0) {
//					JOptionPane.showMessageDialog(null, "số lượng >= 0");
//					txtSoLuong.selectAll();
//					txtSoLuong.requestFocus();
//					return false;
//				}
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "Số lượng phải là định dạng số nguyên");
//				txtSoLuong.selectAll();
//				txtSoLuong.requestFocus();
//				return false;
//			}
//		}
		return true;
	}

	public JPanel getContentpane() {
		return this.contentPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblDsBook.getSelectedRow();

		if (row == -1) {
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		}

		btnSua.setEnabled(true);
		btnXoa.setEnabled(true);

		txtMaBook.setText(modelDsBook.getValueAt(row, 0).toString());
		txtTenBook.setText((String) modelDsBook.getValueAt(row, 1));
//		cboLoaiBook.setSelectedItem(modelDsBook.getValueAt(row, 2));
		txtNamXuatBan.setText(modelDsBook.getValueAt(row, 3).toString());

		txtDonGia.setText(modelDsBook.getValueAt(row, 5).toString());
		txtSoLuong.setText(modelDsBook.getValueAt(row, 6).toString());
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
	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void renderDataTimKiem(List<Book> list) {
		modelDsBook.setRowCount(0);
		for (Book book : list) {
			Set<Category> cates = book.getCategories();
			String cateString = "";
			for (Category cate : cates) {
				cateString += cate.getName() + ",";
			}
			Object[] row = { book.getId(), book.getName(), cateString, book.getYear(), book.getAuthor().getName(),
					book.getPrice(), book.getQuantity() };
			modelDsBook.addRow(row);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String timTheo = cboTimTheo.getSelectedItem().toString();

		if (timTheo.equals("Tên Sách")) {
			List<Book> list = bookDao.findManyByName(txtTimKiem.getText());
			renderDataTimKiem(list);
			System.out.println(list.size());
		}
		
		else if(timTheo.equals("Tên Tác Giả")) {
			List<Book> list = bookDao.findManyByAuthorName(txtTimKiem.getText());
			renderDataTimKiem(list);
			System.out.println(list.size());
		}

//		String key = txtTimKiem.getText().trim();
//		String type = cboTimTheo.getSelectedItem().toString().trim();
//		dsBooks = new ArrayList<Book>();
//		if(type.equals("Tên thuốc")) {
//			dsBooks = thuocDao.TimBook("tenBook",key);
//			renderDataTimKiem();
//		}else if(type.equals("Nhà cung cấp")) {
//			dsBooks = thuocDao.TimBook("tenNhaCungCap",key);
//			renderDataTimKiem();
//		}

	}

	private String formatNumberForMoney(double money) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str1 = currencyVN.format(Math.round(money));
		str1 = str1.substring(0, str1.length() - 2);
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

	public Date stringToDate(String strDate) {
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
