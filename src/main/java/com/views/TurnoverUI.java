package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

//import org.jdesktop.swingx.prompt.PromptSupport;
//
//import dao.KhachHangDAO;
//import dao.NhanVienDAO;
//import util.Placeholder;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JComboBox;

public class TurnoverUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel out;
	private DefaultTableModel model;
	String[] colsHD = { "Mã sách", "Tên sách", "Số lượng", "Tổng tiền" };
	private JButton btnThongKe;

	private DefaultComboBoxModel<String> modelLoaiThongKe;
	private JComboBox<String> cbbLoaiThongKe;

	private DefaultComboBoxModel<String> modelSoNam;
	private JComboBox<String> cbbSoNam;

	private DefaultComboBoxModel<String> modelSoThang;
	private JComboBox<String> cbbSoThang;

	private DefaultComboBoxModel<String> modelSoNgay;
	private JComboBox<String> cbbSoNgay;

	private DefaultComboBoxModel<String> modelSoQuy;
	private JComboBox<String> cbbSoQuy;

	private JLabel lblMod;
	private JLabel lblModeValue;
	private JLabel lblSum;
	private JLabel lblSumValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TurnoverUI frame = new TurnoverUI();
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
	public TurnoverUI() throws SQLException {

		setTitle("Thống kê");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 1300, 700);

		out = new JPanel();
		out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
		setContentPane(out);

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel("Thống kê");
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
		pnLeft.setBorder(new TitledBorder("Lựa chọn thông số"));
		contentPane.add(pnLeft);

		JPanel pnThongTinThongKe = new JPanel();
		pnLeft.add(pnThongTinThongKe);
		pnThongTinThongKe.setLayout(new BoxLayout(pnThongTinThongKe, BoxLayout.Y_AXIS));
//		
//		Component verticalStrut_2 = Box.createVerticalStrut(35);
//		pnThongTinThongKe.add(verticalStrut_2);

		JPanel pnLoaiThongKe = new JPanel();
		pnThongTinThongKe.add(pnLoaiThongKe);

		JLabel lblLoaiThongKe = new JLabel("Thống ke theo: ");
		lblLoaiThongKe.setPreferredSize(new Dimension(100, 14));
		pnLoaiThongKe.add(lblLoaiThongKe);

		modelLoaiThongKe = new DefaultComboBoxModel<>();
		modelLoaiThongKe.addElement("Năm");
		modelLoaiThongKe.addElement("Quý");
		modelLoaiThongKe.addElement("Tháng");
		modelLoaiThongKe.addElement("Ngày");

		cbbLoaiThongKe = new JComboBox<String>(modelLoaiThongKe);
		cbbLoaiThongKe.setPreferredSize(new Dimension(100, 30));
		pnLoaiThongKe.add(cbbLoaiThongKe);

		// so nam
		JPanel pnSoNam = new JPanel();
		pnThongTinThongKe.add(pnSoNam);
		JLabel lblSoNam = new JLabel("Năm: ");
		lblSoNam.setPreferredSize(new Dimension(100, 14));
		pnSoNam.add(lblSoNam);

		modelSoNam = new DefaultComboBoxModel<>();
		modelSoNam.addElement("2000");
		modelSoNam.addElement("2001");
		modelSoNam.addElement("2002");
		modelSoNam.addElement("2003");

		cbbSoNam = new JComboBox<String>(modelSoNam);
		cbbSoNam.setPreferredSize(new Dimension(100, 30));
		pnSoNam.add(cbbSoNam);

		// so thang
		JPanel pnSoThang = new JPanel();
		pnThongTinThongKe.add(pnSoThang);
		JLabel lblSoThang = new JLabel("Tháng: ");
		lblSoThang.setPreferredSize(new Dimension(100, 14));
		pnSoThang.add(lblSoThang);

		modelSoThang = new DefaultComboBoxModel<>();
		modelSoThang.addElement("1");
		modelSoThang.addElement("2");
		modelSoThang.addElement("3");
		modelSoThang.addElement("4");
		modelSoThang.addElement("5");
		modelSoThang.addElement("6");
		modelSoThang.addElement("7");
		modelSoThang.addElement("8");
		modelSoThang.addElement("9");
		modelSoThang.addElement("10");
		modelSoThang.addElement("11");
		modelSoThang.addElement("12");

		cbbSoThang = new JComboBox<String>(modelSoThang);
		cbbSoThang.setPreferredSize(new Dimension(100, 30));
		pnSoThang.add(cbbSoThang);

// số ngày
		JPanel pnSoNgay = new JPanel();
		pnThongTinThongKe.add(pnSoNgay);
		JLabel lblSoNgay = new JLabel("Ngày: ");
		lblSoNgay.setPreferredSize(new Dimension(100, 14));
		pnSoNgay.add(lblSoNgay);

		modelSoNgay = new DefaultComboBoxModel<>();
		modelSoNgay.addElement("1");

		cbbSoNgay = new JComboBox<String>(modelSoNgay);
		cbbSoNgay.setPreferredSize(new Dimension(100, 30));
		pnSoNgay.add(cbbSoNgay);

// theo ki
		JPanel pnSoQuy = new JPanel();
		pnThongTinThongKe.add(pnSoQuy);
		JLabel lblSoQuy = new JLabel("Quý: ");
		lblSoQuy.setPreferredSize(new Dimension(100, 14));
		pnSoQuy.add(lblSoQuy);

		modelSoQuy = new DefaultComboBoxModel<>();
		modelSoQuy.addElement("I");
		modelSoQuy.addElement("II");
		modelSoQuy.addElement("III");
		modelSoQuy.addElement("IV");

		cbbSoQuy = new JComboBox<String>(modelSoQuy);
		cbbSoQuy.setPreferredSize(new Dimension(100, 30));
		pnSoQuy.add(cbbSoQuy);

		Component verticalStrut = Box.createVerticalStrut(20);
		pnThongTinThongKe.add(verticalStrut);

		JPanel pnChucNang = new JPanel();
		pnThongTinThongKe.add(pnChucNang);
		pnChucNang.setLayout(new GridLayout(0, 1, 0, 5));

		btnThongKe = new JButton("Thống kê doanh thu");
		btnThongKe.setBackground(Color.WHITE);
		btnThongKe.setIcon(new ImageIcon("data\\images\\repairing-service.png"));
//		btnThongKe.setIconTextGap(30);
		pnChucNang.add(btnThongKe);

// right side

		JPanel pnRight = new JPanel();
		pnRight.setBorder(new TitledBorder("Kết quả thống kê"));
		contentPane.add(pnRight);
		pnRight.setLayout(new BorderLayout());

		JTable tableHD;
		model = new DefaultTableModel(colsHD, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
				// Không cho chỉnh sửa trên table
			}
		};

		tableHD = new JTable(model);
		JScrollPane scHD = new JScrollPane(tableHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scHD.setBounds(10, 67, 875, 260);
		pnRight.add(scHD, BorderLayout.CENTER);

		JPanel pn_detail = new JPanel();
		pn_detail.setLayout(new BoxLayout(pn_detail, BoxLayout.Y_AXIS));
		pnRight.add(pn_detail, BorderLayout.SOUTH);

		JPanel pn_2 = new JPanel();
		FlowLayout fl_2 = (FlowLayout) pn_2.getLayout();
		fl_2.setAlignment(FlowLayout.LEFT);
		pn_detail.add(pn_2);
		lblSum = new JLabel("Tổng doanh thu: ");
		lblSumValue = new JLabel("0 đồng");
		pn_2.add(lblSum);
		pn_2.add(lblSumValue);

		JPanel pn_3 = new JPanel();
		FlowLayout fl_3 = (FlowLayout) pn_3.getLayout();
		fl_3.setAlignment(FlowLayout.LEFT);
		pn_detail.add(pn_3);
		lblMod = new JLabel("Thuốc bán nhiều nhất: ");
		lblModeValue = new JLabel("");
		pn_3.add(lblMod);
		pn_3.add(lblModeValue);

		cbbSoThang.setEnabled(false);
		cbbSoQuy.setEnabled(false);
		cbbSoNgay.setEnabled(false);

		// add event
		cbbLoaiThongKe.addActionListener(this);
		cbbSoNam.addActionListener(this);
		cbbSoThang.addActionListener(this);
		btnThongKe.addActionListener(this);
		// load data from database
//		loadAllData();
		renderCbbNgay();
	}

	private void renderCbbNgay() {
		// TODO Auto-generated method stub
		int nam;
		int thang;

		try {
			nam = Integer.parseInt(cbbSoNam.getSelectedItem().toString());
			thang = Integer.parseInt(cbbSoThang.getSelectedItem().toString());

		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
		modelSoNgay.removeAllElements();

		if (nam % 4 == 0 && nam % 100 != 0 || nam % 400 == 0) {
			// nam nhuan
			if (thang == 2) {
				for (int i = 1; i <= 29; i++) {
					modelSoNgay.addElement(i + "");
				}
			}
		} else {
			if (thang == 2) {
				for (int i = 1; i <= 28; i++) {
					modelSoNgay.addElement(i + "");
				}
			}
		}

		if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
			for (int i = 1; i <= 31; i++) {
				modelSoNgay.addElement(i + "");
			}
		} else if (thang != 2) {
			for (int i = 1; i <= 30; i++) {
				modelSoNgay.addElement(i + "");
			}
		}

	}

//	private void loadAllData() {
//		int minYear = dt_dao.layNamCuNhat();
//		int maxYear = dt_dao.layNamMoiNhat();
//		modelSoNam.removeAllElements();
//		for(int i = minYear; i <= maxYear; i++) {
//			modelSoNam.addElement(i + "");		}
//		
//	}
//
//	private void renderData(ResultSet rs) {
//		// TODO Auto-generated method stub
//		if(Objects.isNull(rs)) {
//			JOptionPane.showMessageDialog(null,"Không có toa thuốc nào được bán trong thời gian này");
//			return;
//		}
//		double sum = 0;
//		
//		try {
//			model.setRowCount(0);
//			while(rs.next()) {
//				model.addRow(new Object[] {rs.getInt(1),rs.getString(2),rs.getInt(4),formatNumberForMoney(Math.round(rs.getDouble(3) ))});
//				sum += rs.getDouble(3);
//			}
//			
//		    lblSumValue.setText(formatNumberForMoney(sum));
//		    if(model.getRowCount() >= 1) {
//		    	lblModeValue.setText(model.getValueAt( model.getRowCount() -1, 1).toString());		    	
//		    }else {
//		    	lblSumValue.setText("0 đồng");
//		    	lblModeValue.setText("");
//		    	JOptionPane.showMessageDialog(null,"Không có toa thuốc nào được bán trong thời gian này");
//		    }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}	
	public boolean kiemTraSo(String ten) {
		char arrTen[] = ten.toCharArray();
		for (int i = 0; i < ten.length(); i++) {
			String cTen = String.valueOf(arrTen[i]);
			if (cTen.matches("[0-9]"))
				return true;
		}
		return false;
	}

	private String formatNumberForMoney(double money) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String str1 = currencyVN.format(Math.round(money));
		str1 = str1.substring(0, str1.length() - 2);
		return str1 + " đồng";
	}

	public JPanel getContentPane() {
		return this.out;
	}

//	
	public void renderLeftSide(String loaiThongKe) {
		if (loaiThongKe.equals("Năm")) {
			cbbSoNam.setEnabled(true);
			cbbSoThang.setEnabled(false);
			cbbSoQuy.setEnabled(false);
			cbbSoNgay.setEnabled(false);
		} else if (loaiThongKe.equals("Tháng")) {
			cbbSoNam.setEnabled(true);
			cbbSoThang.setEnabled(true);
			cbbSoQuy.setEnabled(false);
			cbbSoNgay.setEnabled(false);
		} else if (loaiThongKe.equals("Ngày")) {
			cbbSoNam.setEnabled(true);
			cbbSoThang.setEnabled(true);
			cbbSoQuy.setEnabled(false);
			cbbSoNgay.setEnabled(true);
		} else if (loaiThongKe.equals("Quý")) {
			cbbSoNam.setEnabled(true);
			cbbSoQuy.setEnabled(true);
			cbbSoThang.setEnabled(false);
			cbbSoNgay.setEnabled(false);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(cbbLoaiThongKe)) {
			renderLeftSide(cbbLoaiThongKe.getSelectedItem().toString());
		} else if (o.equals(cbbSoNam)) {
			renderCbbNgay();
		} else if (o.equals(cbbSoThang)) {
			renderCbbNgay();
		} else if (o.equals(btnThongKe)) {
			String loaiThongKe = cbbLoaiThongKe.getSelectedItem().toString();
			int nam = Integer.parseInt(cbbSoNam.getSelectedItem().toString());
			int thang = Integer.parseInt(cbbSoThang.getSelectedItem().toString());
			int ngay = Integer.parseInt(cbbSoNgay.getSelectedItem().toString());
			int quy = cbbSoQuy.getSelectedIndex() + 1;

			if (loaiThongKe.equals("Năm")) {
//				renderData(dt_dao.layDoanhThuTheoNam(nam));
			} else if (loaiThongKe.equals("Tháng")) {
//				renderData(dt_dao.layDoanhThuTheoThang(nam, thang));
			} else if (loaiThongKe.equals("Ngày")) {
//				renderData(dt_dao.layDoanhThuTheoNgay(nam, thang, ngay));
			} else if (loaiThongKe.equals("Quý")) {

//				renderData(dt_dao.layDoanhThuTheoQuy(nam, quy));
			}
		}
	}

	public  JPanel getContenpain() {
		return this.out;
	}

}
