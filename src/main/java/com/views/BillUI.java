package com.views;

import java.awt.*;

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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.dao.BillDao;
import com.dao.impl.BillDaoImpl;
import com.entities.Bill;
import com.utils.Constants;

//import dao.HoaDon_dao;

//import gui.XuatBillUI;
//import entity.HoaDon;

//import org.jdesktop.swingx.tree.DefaultXTreeCellEditor;
//
//import dao.DonDatHangDAO;
//import dao.HoaDonDAO;
//import entity.ChiTietHoaDon;
//import entity.HoaDon;
//import util.Currency;

import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class BillUI extends JFrame implements ActionListener, MouseListener, KeyListener {
	private DefaultTableModel modelHD;
	String[] colsHD = { "Mã hoá đơn", "Tên khách hàng", "Số điện thoại", "Tổng tiền", "Ngày lập" };
	public JPanel pnMain;
	private JTable tableHD;
	private JPanel panel_1;

	private JTextField txtTongTien;
	private JComboBox cboTimKiem;
	private JLabel lblMaHD;
	private JTextField txtMaHD;

	private JTextField txtSdt;
	private JTextField txtTenKH;
	private JTextField txtTimKiem;
	private JButton btnXuatHoaDon;

	private List<Bill> bills;
	private BillDao billDao ;

	public BillUI() throws SQLException {
		try {
			System.out.println("a1");
			billDao = (BillDao) Naming.lookup(Constants.BASE_PATH_RMI + Constants.STUB_BILL);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
			System.out.println("a2");
		}

		setTitle("Quản lý hóa đơn");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 584, 411);
		setContentPane(pnMain);

		JLabel lbTitle = new JLabel("Quản lý hóa đơn");
		lbTitle.setBounds(585, 11, 348, 30);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		pnMain.add(lbTitle);

		pnMain.setLayout(null);

		JPanel pn = new JPanel();
		pn.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi tiết",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pn.setBounds(10, 65, 347, 220);
		pnMain.add(pn);
		pn.setLayout(null);

		lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaHD.setBounds(10, 24, 93, 25);
		pn.add(lblMaHD);

		txtMaHD = new JTextField();
		txtMaHD.setEditable(false);
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(122, 24, 205, 25);
		pn.add(txtMaHD);

		JLabel lbTen = new JLabel("Tên khách hàng:");
		lbTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbTen.setBounds(10, 56, 93, 25);
		pn.add(lbTen);

		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(122, 56, 205, 25);
		pn.add(txtTenKH);

		JLabel lbSoDienThoai = new JLabel("Số điện thoại:");
		lbSoDienThoai.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbSoDienThoai.setBounds(10, 88, 93, 25);
		pn.add(lbSoDienThoai);

		txtSdt = new JTextField();
		txtSdt.setEditable(false);
		txtSdt.setColumns(10);
		txtSdt.setBounds(122, 88, 205, 25);
		pn.add(txtSdt);

		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTongTien.setBounds(10, 120, 93, 25);
		pn.add(lblTongTien);
		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(122, 120, 205, 25);
		pn.add(txtTongTien);

		btnXuatHoaDon = new JButton("Xem chi tiết");
		btnXuatHoaDon.setBackground(Color.WHITE);
		btnXuatHoaDon.setBounds(174, 160, 154, 35);
		pn.add(btnXuatHoaDon);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(370, 65, 900, 575);
		pnMain.add(panel_1);
		panel_1.setLayout(null);
		modelHD = new DefaultTableModel(colsHD, 0) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};

		tableHD = new JTable(modelHD);
		JScrollPane scHD = new JScrollPane(tableHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scHD.setBounds(10, 67, 875, 260);
		panel_1.add(scHD);

		JLabel lblKieuTimKiem = new JLabel("Tìm kiếm theo:");
		lblKieuTimKiem.setBounds(20, 29, 120, 25);
		panel_1.add(lblKieuTimKiem);

		DefaultComboBoxModel<String> modelTimKiem = new DefaultComboBoxModel<String>();
		cboTimKiem = new JComboBox(modelTimKiem);
		cboTimKiem.setBounds(110, 29, 120, 25);
		panel_1.add(cboTimKiem);
		modelTimKiem.addElement("Tên khách hàng");
		modelTimKiem.addElement("Số điện thoại");

		JLabel lblTimKiem = new JLabel("Nhập giá trị tìm kiếm:");
		lblTimKiem.setPreferredSize(new Dimension(130, 25));
		lblTimKiem.setBounds(260, 29, 120, 25);
		panel_1.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(390, 29, 120, 25);
		panel_1.add(txtTimKiem);
		txtTimKiem.setColumns(10);

		btnXuatHoaDon.addActionListener(this);
		tableHD.addMouseListener(this);
		txtTimKiem.addKeyListener(this);

		renderData();
		formatMoneyToDouble("100.000 đồng");

	}

	private String formatNumberForMoney(double money) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str1 = currencyVN.format(Math.round(money));
		str1 = str1.substring(0, str1.length() - 2);
		return str1 + " đồng";
	}

	private double formatMoneyToDouble(String str) {
		String[] s = str.split("[. đồng]");
		String tmp = "";

		for (String string : s) {
			tmp += string;
		}

		return Double.parseDouble(tmp);
	}

	public void renderData() {
		System.out.println("a");
		try {
			bills = billDao.getAllBills();
		} catch (RemoteException e) {
			System.out.println("b");
			e.printStackTrace();
		}

		tableHD.clearSelection();
		modelHD.getDataVector().removeAllElements();
		bills.forEach(b -> {
			modelHD.addRow(new Object[] { b.getId(), b.getCustomer().getName(), b.getCustomer().getPhoneNumber(),
					formatNumberForMoney(b.getTotal()), b.getCreateAt() });
		});
		tableHD.revalidate();
		tableHD.repaint();
	}

	public JPanel getContentPane() {
		return this.pnMain;
	}

	public void clear() {
		tableHD.clearSelection();
//		modelHD.setRowCount(0);

		txtMaHD.setText("");
		txtTenKH.setText("");
		txtSdt.setText("");
		txtTongTien.setText("");

	}

	public static void main(String[] args) throws SQLException {
		new BillUI().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();

		if (o.equals(btnXuatHoaDon)) {
			int idx = tableHD.getSelectedRow();
			if (idx == -1) {
				JOptionPane.showMessageDialog(pnMain, "Vui lòng chọn hóa đơn để xuất");
				return;
			}

			ExportBillUI export = new ExportBillUI();
			export.setBill(bills.get(idx));
			export.setVisible(true);
			export.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableHD.getSelectedRow();
		if (row == -1) {
			setEnabled(false);
			return;
		}

		txtMaHD.setText(tableHD.getValueAt(row, 0).toString());
		txtTenKH.setText(tableHD.getValueAt(row, 1).toString());
		txtSdt.setText(tableHD.getValueAt(row, 2).toString());
		txtTongTien.setText(tableHD.getValueAt(row, 3).toString());

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

		String type = (String) cboTimKiem.getSelectedItem();
		String value = txtTimKiem.getText();

		if (type.equals("Tên khách hàng")) {
			try {
				bills = billDao.findByCustomerName(value);
				renderData2();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (type.equals("Số điện thoại")) {
			try {
				bills = billDao.findByCustomerPhonenumber(value);
				renderData2();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	private void renderData2() {
		modelHD.getDataVector().removeAllElements();
		bills.forEach(b -> {
			modelHD.addRow(new Object[] { b.getId(), b.getCustomer().getName(), b.getCustomer().getPhoneNumber(),
					formatNumberForMoney(b.getTotal()), b.getCreateAt() });
		});
		tableHD.revalidate();
		tableHD.repaint();
//		}
	}
}