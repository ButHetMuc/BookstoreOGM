package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bson.types.ObjectId;
import org.hibernate.internal.build.AllowSysOut;

import com.dao.BillDao;
import com.dao.BookDao;
import com.dao.IAuthorDao;
import com.dao.ICategoriesDao;
import com.dao.IPublisherDao;
import com.dao.impl.AuthorDaoImpl;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.CategoryDaoImpl;
import com.dao.impl.PublisherDaoImpl;
import com.entities.Author;
import com.entities.Book;
import com.entities.Category;
import com.entities.Publisher;
import com.utils.Constants;

//import connectdb.ConnectDB;
//import dao.LoaiBook_dao;
//import dao.NhaCungCap_dao;
//import dao.Book_dao;
//import entity.LoaiBook;
//import entity.NhaCungCap;
//import entity.Book;

import javax.swing.ImageIcon;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;

public class ManageBookUI extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtMaBook;
	private JTextField txtTenBook;
	private JComboBox cboNhaXuatBan;
	private JComboBox cboTacGia;
	private JTable tblDsBook;
	private DefaultTableModel modelDsBook;
	private JTextField txtTimKiem;
	private JTextField txtNamXuatBan;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;

	private JTextField txtTenTacGia;
	private JTextField txtSdtTacGia;
	private JTextField txtTenNhaXuatBan;
	private JTextField txtSdtNhaXuatBan;
	private JTextField txtDiaChiNhaXuatBan;
	private JTextField txtTheLoai;

	private JPanel pnListTheLoai;

	private JButton btnThemMoi;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLamMoi;
	private JButton btnTimBook;
	private JButton btnSuaLoaiSach;
	private JButton btnThemTacGia;

	private List<Book> dsBooks;
	private List<Category> dsLoaiBook;
	private List<Author> dsTacGia;
	private List<Author> dsNhaXuatBan;
	private JList<String> JListTheLoai;
	private ArrayList<Category> dsLoaiBooks;

	private BookDao bookDao;
	private IAuthorDao authorDao;
	private IPublisherDao publisherDao;
	private ICategoriesDao categoryDao;

	private DefaultComboBoxModel<String> modelCboCategories;
	private DefaultComboBoxModel<String> modelCboNhaXuatBan;
	private DefaultComboBoxModel<String> modelCboTacGia;
	private DefaultListModel<String> modelLoai;
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

		// init dao

		try {
			bookDao = (BookDao) Naming.lookup(Constants.BASE_PATH_RMI + Constants.STUB_BOOK);

			authorDao = (IAuthorDao) Naming.lookup(Constants.BASE_PATH_RMI + Constants.STUB_AUTHOR);
			categoryDao = (ICategoriesDao) Naming.lookup(Constants.BASE_PATH_RMI + Constants.STUB_CATEGORY);
			publisherDao = (IPublisherDao) Naming.lookup(Constants.BASE_PATH_RMI + Constants.STUB_PUBLISHER);
		} catch (MalformedURLException | RemoteException | NotBoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("remote erro");
		}

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

		JLabel lblTieuDe = new JLabel("Qua??n li?? s??ch");
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

		JLabel lblTitile = new JLabel("Th??ng Tin S??ch");
		lblTitile.setFont(new Font("Tahoma", Font.BOLD, 17));

		pnLblThongTin.add(lblTitile);

		JPanel pnMaBook = new JPanel();
		FlowLayout fl_pnMaBook = (FlowLayout) pnMaBook.getLayout();
		fl_pnMaBook.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnMaBook);

		JLabel lblMaBook = new JLabel("Ma?? s??ch");
		lblMaBook.setPreferredSize(new Dimension(80, 14));
		pnMaBook.add(lblMaBook);

		txtMaBook = new JTextField();
		txtMaBook.setPreferredSize(new Dimension(7, 23));
		pnMaBook.add(txtMaBook);
		txtMaBook.setColumns(22);

		JPanel pnLoaiTimKiem = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnLoaiTimKiem.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnThongTin.add(pnLoaiTimKiem);

		JPanel pnTenBook = new JPanel();
		FlowLayout fl_pnTenBook = (FlowLayout) pnTenBook.getLayout();
		fl_pnTenBook.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTenBook);

		JLabel lblTenBook = new JLabel("T??n s??ch");
		lblTenBook.setPreferredSize(new Dimension(80, 14));
		pnTenBook.add(lblTenBook);

		txtTenBook = new JTextField();
		txtTenBook.setPreferredSize(new Dimension(7, 23));
		pnTenBook.add(txtTenBook);
		txtTenBook.setColumns(22);

		// loai
		JPanel pnTheLoai = new JPanel();
		pnThongTin.add(pnTheLoai);
		FlowLayout fl_pnTheLoai = (FlowLayout) pnTheLoai.getLayout();
		fl_pnTheLoai.setAlignment(FlowLayout.LEFT);
		pnThongTin.add(pnTheLoai);

		JLabel lblTheLoai = new JLabel("Th??? lo???i");
		lblTheLoai.setPreferredSize(new Dimension(80, 14));
		pnTheLoai.add(lblTheLoai);

		txtTheLoai = new JTextField();
		txtTheLoai.setPreferredSize(new Dimension(7, 23));
		pnTheLoai.add(txtTheLoai);
		txtTheLoai.setColumns(16);
		txtTheLoai.setEditable(false);

		btnSuaLoaiSach = new JButton("S???a");
		pnTheLoai.add(btnSuaLoaiSach);

		pnListTheLoai = new JPanel();
		pnThongTin.add(pnListTheLoai);
		addDataCboLoaiBook();
		pnListTheLoai.add(JListTheLoai);
		pnListTheLoai.hide();

		// tac-gia
		JPanel pnTacGiaWrap = new JPanel();
		pnTacGiaWrap.setLayout(new BoxLayout(pnTacGiaWrap, BoxLayout.Y_AXIS));
		pnThongTin.add(pnTacGiaWrap);
		Border border = BorderFactory.createTitledBorder("Th??ng tin t??c gi???");
		pnTacGiaWrap.setBorder(border);

		JPanel pnSdtTacGia = new JPanel();
		pnTacGiaWrap.add(pnSdtTacGia);

		JLabel lblSdtTacGia = new JLabel("S??t t??c gi???");
		lblSdtTacGia.setPreferredSize(new Dimension(80, 14));
		pnSdtTacGia.add(lblSdtTacGia);

		txtSdtTacGia = new JTextField();
		txtSdtTacGia.setPreferredSize(new Dimension(7, 23));
		txtSdtTacGia.setColumns(22);
		pnSdtTacGia.add(txtSdtTacGia);

		JPanel pnTenTacGia = new JPanel();
		pnTacGiaWrap.add(pnTenTacGia);

		JLabel lblTenTacGia = new JLabel("T??n t??c gi???");
		lblTenTacGia.setPreferredSize(new Dimension(80, 14));
		pnTenTacGia.add(lblTenTacGia);

		txtTenTacGia = new JTextField();
		txtTenTacGia.setPreferredSize(new Dimension(7, 23));
		txtTenTacGia.setColumns(22);
		pnTenTacGia.add(txtTenTacGia);
		// end-tacgia

		// start nha xuat ban

		JPanel pnNhaXuatBanWrap = new JPanel();
		pnNhaXuatBanWrap.setLayout(new BoxLayout(pnNhaXuatBanWrap, BoxLayout.Y_AXIS));
		pnThongTin.add(pnNhaXuatBanWrap);
		Border border2 = BorderFactory.createTitledBorder("Th??ng tin nh?? xu???t b???n");
		pnNhaXuatBanWrap.setBorder(border2);

		JPanel pnSdtNhaXuatBan = new JPanel();
		pnNhaXuatBanWrap.add(pnSdtNhaXuatBan);

		JLabel lblSdtNhaXuatBan = new JLabel("S??t nh?? xu???t b???n");
		lblSdtNhaXuatBan.setPreferredSize(new Dimension(80, 14));
		pnSdtNhaXuatBan.add(lblSdtNhaXuatBan);

		txtSdtNhaXuatBan = new JTextField();
		txtSdtNhaXuatBan.setPreferredSize(new Dimension(7, 23));
		txtSdtNhaXuatBan.setColumns(22);
		pnSdtNhaXuatBan.add(txtSdtNhaXuatBan);

		JPanel pnTenNhaXuatBan = new JPanel();
		pnNhaXuatBanWrap.add(pnTenNhaXuatBan);

		JLabel lblTenNhaXuatBan = new JLabel("T??n nh?? xu???t b???n");
		lblTenNhaXuatBan.setPreferredSize(new Dimension(80, 14));
		pnTenNhaXuatBan.add(lblTenNhaXuatBan);

		txtTenNhaXuatBan = new JTextField();
		txtTenNhaXuatBan.setPreferredSize(new Dimension(7, 23));
		txtTenNhaXuatBan.setColumns(22);
		pnTenNhaXuatBan.add(txtTenNhaXuatBan);

		JPanel pnDiaChiNhaXuatBan = new JPanel();
		pnNhaXuatBanWrap.add(pnDiaChiNhaXuatBan);

		JLabel lblDiaChiNhaXuatBan = new JLabel("?????a ch???");
		lblDiaChiNhaXuatBan.setPreferredSize(new Dimension(80, 14));
		pnDiaChiNhaXuatBan.add(lblDiaChiNhaXuatBan);

		txtDiaChiNhaXuatBan = new JTextField();
		txtDiaChiNhaXuatBan.setPreferredSize(new Dimension(7, 23));
		txtDiaChiNhaXuatBan.setColumns(22);
		pnDiaChiNhaXuatBan.add(txtDiaChiNhaXuatBan);

		// end nha xuat ban

		JPanel pnNgaySanXuat = new JPanel();
		pnThongTin.add(pnNgaySanXuat);

		JLabel lblNgaySanXuat = new JLabel("N??m xu???t b???n");
		lblNgaySanXuat.setPreferredSize(new Dimension(80, 14));
		pnNgaySanXuat.add(lblNgaySanXuat);

		txtNamXuatBan = new JTextField();
		txtNamXuatBan.setPreferredSize(new Dimension(7, 23));
		txtNamXuatBan.setColumns(22);
		pnNgaySanXuat.add(txtNamXuatBan);
		txtNamXuatBan.setText("1");

		JPanel pnDonGia = new JPanel();
		pnThongTin.add(pnDonGia);

		JLabel lblDonGia = new JLabel("????n gia??");
		lblDonGia.setPreferredSize(new Dimension(80, 14));
		pnDonGia.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setPreferredSize(new Dimension(7, 23));
		txtDonGia.setColumns(22);
		pnDonGia.add(txtDonGia);

		JPanel pnSoLuong = new JPanel();
		pnThongTin.add(pnSoLuong);

		JLabel lblSoLuong = new JLabel("S???? l??????ng");
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
		btnThemMoi = new JButton("Th??m", iconThem);
		btnThemMoi.setBackground(Color.WHITE);
		pnChucNang.add(btnThemMoi);

		ImageIcon iconSua = new ImageIcon("data//images//repare.png");
		btnSua = new JButton("Ch???nh s???a", iconSua);
		btnSua.setBackground(Color.WHITE);
		pnChucNang.add(btnSua);
		btnSua.setEnabled(false);

		ImageIcon iconXoa = new ImageIcon("data//images//trash.png");
		btnXoa = new JButton("Xo??a", iconXoa);
		btnXoa.setBackground(Color.WHITE);
		pnChucNang.add(btnXoa);
		btnXoa.setEnabled(false);

		ImageIcon iconLamMoi = new ImageIcon("data//images//refresh.png");
		btnLamMoi = new JButton("la??m m????i", iconLamMoi);
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

		String[] arrCachTim = { "T??n S??ch", "T??n t??c gi???", "T??n nh?? xu???t b???n" };
		cboTimTheo = new JComboBox<String>(arrCachTim);
		cboTimTheo.setPreferredSize(new Dimension(120, 23));
		pnCenterTop.add(cboTimTheo);

		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(7, 23));
		pnCenterTop.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnTimBook = new JButton("Ti??m ki???m");
		btnTimBook.setBackground(Color.WHITE);
		pnCenterTop.add(btnTimBook);

		JPanel pnCenterMiddle = new JPanel();
		pnCenter.add(pnCenterMiddle, BorderLayout.SOUTH);

		String[] cols = { "Ma?? s??ch", "T??n s??ch", "Th??? lo???i", "Nh?? xu???t b???n", "N??m xu???t b???n", "T??c gi???", "????n gia??",
				"s???? l??????ng" };
		modelDsBook = new DefaultTableModel(cols, 0);
		tblDsBook = new JTable(modelDsBook);
		JScrollPane scrtbl = new JScrollPane(tblDsBook, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		pnCenter.add(scrtbl, BorderLayout.CENTER);

		disableEdit();
		renderData();

		btnLamMoi.addActionListener(this);
		btnThemMoi.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimBook.addActionListener(this);
		tblDsBook.addMouseListener(this);
		txtTimKiem.addKeyListener(this);

		btnSuaLoaiSach.addActionListener(this);
		JListTheLoai.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				List<String> list = JListTheLoai.getSelectedValuesList();
				String str = "";
				for (String string : list) {
					str += string + ",";
				}
				txtTheLoai.setText(str);

			}
		});

		txtSdtTacGia.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				String sdtAuthor = txtSdtTacGia.getText();
				// s???a if th??nh regex s??? ??i???n tho???i
				if (sdtAuthor.length() == 10) {
					Author author = null;
					try {
						author = authorDao.findBySdt(sdtAuthor);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (author != null) {
						txtTenTacGia.setText(author.getName());
					} else {
						txtTenTacGia.setEditable(true);
					}
				} else {
					txtTenTacGia.setEditable(false);
					txtTenTacGia.setText("");
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		txtSdtNhaXuatBan.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				String sdtNhaXuatBan = txtSdtNhaXuatBan.getText();
				// s???a if th??nh regex s??? ??i???n tho???i
				if (sdtNhaXuatBan.length() == 10) {
					Publisher nhaXuatBan = null;
					try {
						nhaXuatBan = publisherDao.findBySdt(sdtNhaXuatBan);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (nhaXuatBan != null) {
						txtTenNhaXuatBan.setText(nhaXuatBan.getName());
						txtDiaChiNhaXuatBan.setText(nhaXuatBan.getAddress());
					} else {
						txtTenNhaXuatBan.setEditable(true);
						txtDiaChiNhaXuatBan.setEditable(true);
					}
				} else {
					txtTenNhaXuatBan.setEditable(false);
					txtDiaChiNhaXuatBan.setEditable(false);
					txtTenNhaXuatBan.setText("");
					txtDiaChiNhaXuatBan.setText("");
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void addDatacboTacGia() {

		String[] nhacungcap = { "Kim Dong", "So Ha" };
		cboTacGia = new JComboBox<String>(nhacungcap);
	}

	private void addDataCboLoaiBook() {

		try {
			dsLoaiBook = categoryDao.getAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelLoai = new DefaultListModel<String>();

		for (Category category : dsLoaiBook) {
			modelLoai.addElement(category.getName());
		}

		JListTheLoai = new JList<>(modelLoai);

	}

	private void renderData() {
		try {
			dsBooks = bookDao.getAllBook();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("render data," + dsBooks.size());
		modelDsBook.setRowCount(0);
		for (Book book : dsBooks) {
			Set<Category> cates = book.getCategories();
			String cateString = "";
			for (Category cate : cates) {
				cateString += cate.getName() + ",";
			}
			Object[] row = { book.getId(), book.getName(), cateString, book.getPublisher().getName(), book.getYear(),
					book.getAuthor().getName(), book.getPrice(), book.getQuantity() };
			modelDsBook.addRow(row);
		}

		disableEdit();
		setNull();
		btnThemMoi.setText("Th??m");
		btnSua.setText("Ch???nh s???a");
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnLamMoi)) {
			setNull();
			return;
		}

		if (o.equals(btnThemMoi)) {

			if (btnThemMoi.getText().equals("Th??m")) {
				setNull();
				btnSua.setText("L??u");
				btnThemMoi.setText("Hu??y");
				enableEdit();
				btnXoa.disable();
				btnSua.setEnabled(true);
				return;
			} else if (btnThemMoi.getText().equals("Hu??y")) {
				btnSua.setText("Ch???nh s???a");
				btnThemMoi.setText("Th??m");
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				disableEdit();
			} else

			// l??u thay ?????i
			if (o.equals(btnThemMoi) && btnThemMoi.getText() == "L??u thay ?????i") {

				int index = tblDsBook.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "Vui l??ng ch???n d??ng c???n c???p nh???t");
					return;
				}
				if (index != -1) {
					if (checkData()) {
						// static data

						Author author = null;
						try {
							author = authorDao.findBySdt(txtSdtTacGia.getText());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (author == null) {
							author = new Author(new ObjectId(), txtTenTacGia.getText(), txtSdtTacGia.getText());
							try {
								authorDao.add(author);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("add new author");
						}
						Publisher publisher = null;
						try {
							publisher = publisherDao.findBySdt(txtSdtNhaXuatBan.getText());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (publisher == null) {
							publisher = new Publisher(new ObjectId(), txtTenNhaXuatBan.getText(),
									txtSdtNhaXuatBan.getText(), txtDiaChiNhaXuatBan.getText());
							try {
								publisherDao.add(publisher);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("add new pub");

						}
						System.out.println("add new err");

						Set<Category> cates = new HashSet<>();
						String[] listCategoryName = txtTheLoai.getText().split(",");
						for (String categoryName : listCategoryName) {
							Category category = null;
							try {
								category = categoryDao.findByName(categoryName);
								
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (category == null) {
								System.out.println("err category empty");
							}
							cates.add(category);
						}

						String name = txtTenBook.getText();
						int namXuatBan = Integer.parseInt(txtNamXuatBan.getText().trim());
						int donGia = Integer.parseInt(txtDonGia.getText().trim());
						int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

						ObjectId bookId = new ObjectId(tblDsBook.getValueAt(index, 0).toString());
						Book newBook = new Book(bookId, name, author, cates, publisher, namXuatBan, donGia, soLuong);

						boolean kq = false;
						try {
							kq = bookDao.update(newBook);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (kq) {
							JOptionPane.showMessageDialog(null, "S????a tha??nh c??ng");

							renderData();
							setNull();
							disableEdit();

						} else {
							JOptionPane.showMessageDialog(null, "Co?? l????i xa??y ra");
							return;
						}

					}
				}
				return;
			}
			return;
		}

		// th??m m???i
		if (o.equals(btnSua) && btnSua.getText().equals("L??u")) {
			if (checkData()) {

				// static data

				Author author = null;
				try {
					author = authorDao.findBySdt(txtSdtTacGia.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (author == null) {
					author = new Author(new ObjectId(), txtTenTacGia.getText(), txtSdtTacGia.getText());
					try {
						authorDao.add(author);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Publisher publisher = null;
				try {
					publisher = publisherDao.findBySdt(txtSdtNhaXuatBan.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (publisher == null) {
					publisher = new Publisher(new ObjectId(), txtTenNhaXuatBan.getText(), txtSdtNhaXuatBan.getText(),
							txtDiaChiNhaXuatBan.getText());
					try {
						publisherDao.add(publisher);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				Set<Category> cates = new HashSet<>();
				String[] listCategoryName = txtTheLoai.getText().split(",");
				for (String categoryName : listCategoryName) {
					Category category = null;
					System.out.println(categoryName);
					try {
						category = categoryDao.findByName(categoryName);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					}
					if (category == null) {
						System.out.println("err category empty");
					}
					cates.add(category);
				}

				String name = txtTenBook.getText();
				int namXuatBan = Integer.parseInt(txtNamXuatBan.getText().trim());
				int donGia = Integer.parseInt(txtDonGia.getText().trim());
				int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

				Book b = new Book(new ObjectId(), name, author, cates, publisher, namXuatBan, donGia, soLuong);
				boolean kq = false;
				try {
					kq = bookDao.add(b);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (kq) {
					JOptionPane.showMessageDialog(null, "Th??m s??ch tha??nh c??ng");

					renderData();
				} else {
					JOptionPane.showMessageDialog(null, "Co?? l????i xa??y ra");
					return;
				}
				setNull();
			}
			return;
		}

		if (o.equals(btnSua) && btnSua.getText() == "Ch???nh s???a") {
			btnThemMoi.setText("L??u thay ?????i");
			enableEdit();
			btnSua.setText("H???y");
			return;
		}

		if (o.equals(btnSua) && btnSua.getText() == "H???y") {
			btnThemMoi.setText("Th??m");
			btnSua.setText("Ch???nh s???a");
			disableEdit();
			return;
		}

		// done
		if (o.equals(btnXoa)) {
			int index = tblDsBook.getSelectedRow();
			if (index != -1) {

				int choose = JOptionPane.showConfirmDialog(contentPane, "Ch???c ch???n x??a!", "X??c nh???n",
						JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					tblDsBook.clearSelection();

					boolean kq = false;
					try {
						kq = bookDao.delete(new ObjectId(tblDsBook.getValueAt(index, 0).toString()));
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (kq) {
						JOptionPane.showMessageDialog(contentPane, "X??a th??nh c??ng");
						renderData();
						return;
					} else {
						JOptionPane.showMessageDialog(contentPane, "Kh??ng th??? x??a quy???n s??ch n??y");
						return;
					}

				}
			}
			return;
		}

		// s???a lo???i s??ch
		if (o.equals(btnSuaLoaiSach) && btnSuaLoaiSach.getText() == "S???a") {

			toggleShowJList(true);
			return;
		}

		if (o.equals(btnSuaLoaiSach) && btnSuaLoaiSach.getText() == "L??u") {
			toggleShowJList(false);
			return;
		}

	}

	private void setNull() {
//		renderData();
		txtMaBook.setText("");
		txtTenBook.setText("");
		txtNamXuatBan.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		txtTimKiem.setText("");
		txtTenTacGia.setText("");
		txtSdtTacGia.setText("");
		txtTheLoai.setText("");
		txtTenNhaXuatBan.setText("");
		txtDiaChiNhaXuatBan.setText("");
		txtSdtNhaXuatBan.setText("");

	}

	private void disableEdit() {
		txtMaBook.setEditable(false);
		txtTenBook.setEditable(false);
		txtTenTacGia.setEditable(false);
		txtSdtTacGia.setEditable(false);
		txtNamXuatBan.setEditable(false);
		txtSoLuong.setEditable(false);
		txtDonGia.setEditable(false);
		txtTenNhaXuatBan.setEditable(false);
		txtSdtNhaXuatBan.setEditable(false);
		txtDiaChiNhaXuatBan.setEditable(false);
		btnSuaLoaiSach.setEnabled(false);

	}

	private void enableEdit() {
		txtMaBook.setEditable(false);
		txtTenBook.setEditable(true);
		txtSdtTacGia.setEditable(true);
		txtNamXuatBan.setEditable(true);
		txtSoLuong.setEditable(true);
		txtDonGia.setEditable(true);
		txtSdtNhaXuatBan.setEditable(true);

		btnSuaLoaiSach.setEnabled(true);
	}

	private boolean checkData() {
//
//		String maBook = txtMaBook.getText().trim();
//		String tenBook = txtTenBook.getText().trim();
//		String ngaySX = txtNamXuatBan.getText().trim();
//		String donGia = txtDonGia.getText().trim();
//		String soLuong = txtSoLuong.getText().trim();
//
//		if (tenBook.equals("")) {
//			JOptionPane.showMessageDialog(null, "t??n thu????c kh??ng ????????c b???? tr????ng");
//			txtTenBook.requestFocus();
//			return false;
//		} else {
//			if (!tenBook.matches("^(\\w+\\s*)+$")) {
//				JOptionPane.showMessageDialog(null,
//						"T??n thu????c kh??ng ch????a ki?? t???? ??????c bi????t, co?? th???? co?? khoa??ng tr????ng");
//				txtTenBook.selectAll();
//				txtTenBook.requestFocus();
//				return false;
//			}
//		}
//		if (ngaySX.equals("")) {
//			JOptionPane.showMessageDialog(null, "Nga??y sa??n xu????t kh??ng ????????c bo?? tr????ng");
//			txtNamXuatBan.requestFocus();
//			return false;
//		} else {
//			if (!ngaySX.matches("^\\d{4}(-\\d{2}){2}$")) {
//				JOptionPane.showMessageDialog(null, "Sai ??i??nh da??ng nga??y yyyy-mm-dd");
//				txtNamXuatBan.selectAll();
//				txtNamXuatBan.requestFocus();
//				return false;
//			}
//		}
//
//		if (donGia.equals("")) {
//			JOptionPane.showMessageDialog(null, "Vui lo??ng nh????p ????n gia??");
//			txtDonGia.requestFocus();
//			return false;
//		} else {
//			try {
//				double dg = Double.parseDouble(donGia);
//				if (dg <= 0) {
//					JOptionPane.showMessageDialog(null, "????n gia?? > 0");
//					txtDonGia.selectAll();
//					txtDonGia.requestFocus();
//					return false;
//				}
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "????n gia?? pha??i la?? s???? th????c");
//				txtDonGia.selectAll();
//				txtDonGia.requestFocus();
//				return false;
//			}
//		}
//		if (soLuong.equals("")) {
//			JOptionPane.showMessageDialog(null, "Vui lo??ng nh????p s???? l??????ng");
//			txtSoLuong.requestFocus();
//			return false;
//		} else {
//			try {
//				int sl = Integer.parseInt(soLuong);
//				if (sl <= 0) {
//					JOptionPane.showMessageDialog(null, "s???? l??????ng >= 0");
//					txtSoLuong.selectAll();
//					txtSoLuong.requestFocus();
//					return false;
//				}
//
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "S???? l??????ng pha??i la?? ??i??nh da??ng s???? nguy??n");
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

	private void toggleShowJList(boolean isShow) {
		if (isShow) {
			btnSuaLoaiSach.setText("L??u");
			pnListTheLoai.show();
		} else {
			btnSuaLoaiSach.setText("S???a");
			pnListTheLoai.hide();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblDsBook.getSelectedRow();

		if (row == -1) {
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
		} else {
			JListTheLoai.clearSelection();
			toggleShowJList(false);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);

			txtMaBook.setText(modelDsBook.getValueAt(row, 0).toString());
			txtTenBook.setText((String) modelDsBook.getValueAt(row, 1));
			Set<Category> categories = dsBooks.get(row).getCategories();
			String str = "";
			int[] selectedIndies = new int[categories.size()];
			int count = 0;
			for (Category category : categories) {
				str += category.getName() + ",";
				selectedIndies[count] = modelLoai.indexOf(category.getName());
				count++;
			}
			JListTheLoai.setSelectedIndices(selectedIndies);
			txtTheLoai.setText(str);
			txtTenNhaXuatBan.setText(dsBooks.get(row).getPublisher().getName());
			txtSdtNhaXuatBan.setText(dsBooks.get(row).getPublisher().getPhoneNumber());
			txtDiaChiNhaXuatBan.setText(dsBooks.get(row).getPublisher().getAddress());
			txtNamXuatBan.setText(modelDsBook.getValueAt(row, 4).toString());

			txtTenTacGia.setText(dsBooks.get(row).getAuthor().getName());
			txtSdtTacGia.setText(dsBooks.get(row).getAuthor().getPhoneNumber());

			txtDonGia.setText(modelDsBook.getValueAt(row, 6).toString());
			txtSoLuong.setText(modelDsBook.getValueAt(row, 7).toString());
		}
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
			Object[] row = { book.getId(), book.getName(), cateString, book.getPublisher().getName(), book.getYear(),
					book.getAuthor().getName(), book.getPrice(), book.getQuantity() };
			modelDsBook.addRow(row);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String timTheo = cboTimTheo.getSelectedItem().toString();

		if (timTheo.equals("T??n S??ch")) {
			List<Book> list = null;
			try {
				list = bookDao.findManyByName(txtTimKiem.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			renderDataTimKiem(list);
			System.out.println(list.size());
		}

		else if (timTheo.equals("T??n t??c gi???")) {
			List<Book> list = null;
			try {
				list = bookDao.findManyByAuthorName(txtTimKiem.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			renderDataTimKiem(list);
		} else if (timTheo.equals("T??n nh?? xu???t b???n")) {
			List<Book> list = null;
			try {
				list = bookDao.findManyByPublisherName(txtTimKiem.getText());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			renderDataTimKiem(list);
		}

//		String key = txtTimKiem.getText().trim();
//		String type = cboTimTheo.getSelectedItem().toString().trim();
//		dsBooks = new ArrayList<Book>();
//		if(type.equals("T??n thu????c")) {
//			dsBooks = thuocDao.TimBook("tenBook",key);
//			renderDataTimKiem();
//		}else if(type.equals("Nha?? cung c????p")) {
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
