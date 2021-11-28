package app;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.HoaDonRemote;
import entity.HoaDon;

public class FrmThongKe extends JPanel {
	private JTable tbPhong;
	private JTable tbKhachHang;
	private DefaultTableModel dftPhong;
	private DefaultTableModel dftKH;
	private DefaultTableModel dftDT;
	HoaDonRemote hoaDonDao ;
	List<Object[]> listKH;
	List<Object[]> listPhong;
	List<Object[]> listDT;
	private JTable tbDoahThu;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public FrmThongKe() throws MalformedURLException, RemoteException, NotBoundException {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 479, 303);
		add(panel);
		panel.setLayout(null);
		
		JComboBox cbTKPhong = new JComboBox();
		cbTKPhong.setBounds(362, 8, 107, 28);
		cbTKPhong.addItem("Hôm nay");
		cbTKPhong.addItem("7 ngày qua");
		cbTKPhong.addItem("Tháng này");
		cbTKPhong.addItemListener(new ItemListener() {
			 	public void itemStateChanged(ItemEvent e) {
			 		int idx = cbTKPhong.getSelectedIndex();
			 		Date date = new Date();
			 		DateFormat dateFormat = null;
			 		dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			 		String nowString = dateFormat.format(date);
			 		
			 		//thong ke phong theo ngay hien tai
			 		if(idx == 0) {
			 			 try {
							listPhong = hoaDonDao.ThongKePhongTheoNgay(nowString);
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(cbTKPhong, "Chức năng đang lỗi!!");
						}
			 			 renderDataPhong(listPhong);
			 		}
			 		else if(idx == 1) {//thong ke trong 7 ngay
			 			
			 			//lay 7 ngay truoc
			 		      Calendar calendar = Calendar.getInstance();      
			 		        
			 		        Date today = calendar.getTime();    
			 		        
			 		        calendar.add(Calendar.DAY_OF_YEAR, -7);
			 		        Date date2 = calendar.getTime();
			 		        String date2_str = dateFormat.format(date2);
			 		        
			 		        try {
								listPhong = hoaDonDao.ThongKePhongTheoHaiMocTG(nowString , date2_str);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(cbTKPhong, "Chức năng đang lỗi!!");
							}
			 			 renderDataPhong(listPhong);
			 		}
			 		else if(idx == 2) {//thong ke trong 30 ngay
			 			
			 			//lay 7 ngay truoc
			 		      Calendar calendar = Calendar.getInstance();      
			 		        
			 		        Date today = calendar.getTime();    
			 		        
			 		        calendar.add(Calendar.DAY_OF_YEAR, -30);
			 		        Date date2 = calendar.getTime();
			 		        String date2_str = dateFormat.format(date2);
			 		        
			 		        try {
								listPhong = hoaDonDao.ThongKePhongTheoHaiMocTG(nowString , date2_str);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(cbTKPhong, "Chức năng đang lỗi!!");
							}
			 			 renderDataPhong(listPhong);
			 		}
			 	}
			 });
		panel.add(cbTKPhong);
		
		JLabel llb_1 = new JLabel("Thống kê Phòng");
		llb_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		llb_1.setBounds(10, 14, 121, 14);
		panel.add(llb_1);
		
		//tbPhong = new JTable();
		//tbPhong.setBounds(214, 97, 1, 1);
		String[] header = {"Mã Phòng","Số lần", "Tổng doang thu" };
		dftPhong = new DefaultTableModel(header, 0);
		tbPhong = new JTable(dftPhong)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbPhong.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		//tbKhachHang.getTable.setFont(new Font("Arial", Font.PLAIN, 14));
		tbPhong.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane jps = new  JScrollPane(tbPhong);
		tbPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		
		jps.setBounds(10, 46, 459, 246);
		
		tbPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tbPhong.setRowHeight(26);
		panel.add(jps);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(499, 11, 471, 303);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel llb = new JLabel("Thống kê Khách hàng");
		llb.setBounds(10, 11, 147, 14);
		panel_1.add(llb);
		llb.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		//tbKhachHang = new JTable();
	//	tbKhachHang.setBounds(130, 128, 1, 1);
		String[] headerKH = {"Mã Khách hàng","Số lần", "Tổng tiền chi" };
		dftKH = new DefaultTableModel(headerKH, 0);
		tbKhachHang = new JTable(dftKH)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbKhachHang.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		//tbKhachHang.getTable.setFont(new Font("Arial", Font.PLAIN, 14));
		tbKhachHang.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane jpsKH = new  JScrollPane(tbKhachHang);
		tbKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		
		jpsKH.setBounds(10, 46, 439, 246);
		
		tbKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tbKhachHang.setRowHeight(26);
	
		panel_1.add(jpsKH);
		
		
		
		
		
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 325, 960, 237);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel llb_1_1 = new JLabel("Thống kê Doanh Thu");
		llb_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		llb_1_1.setBounds(10, 11, 149, 14);
		panel_2.add(llb_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(821, 11, 129, 29);
	
		comboBox.addItem("30 ngày qua");
		comboBox.addItem("Từng tháng trong năm");
		comboBox.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 	
		 		int idx = comboBox.getSelectedIndex();
		 		//thong ke phong theo ngay hien tai
		 		if(idx == 1) {
		 			 JOptionPane.showMessageDialog(comboBox, "Chức năng đang nâng cấp!!");
		 		}
		 		
		 		
		 	}
		 });
		panel_2.add(comboBox);
		
//		tbDoahThu = new JTable();
//		tbDoahThu.setBounds(121, 114, 1, 1);
		String[] headerDT = {"Ngày tháng","Số lần", "Tổng doang thu" };
		dftDT = new DefaultTableModel(headerDT, 0);
		tbDoahThu = new JTable(dftDT)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbDoahThu.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		//tbKhachHang.getTable.setFont(new Font("Arial", Font.PLAIN, 14));
		tbDoahThu.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane jpsDT = new  JScrollPane(tbDoahThu);
		tbDoahThu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		
		jpsDT.setBounds(10, 46, 940, 180);
		
		tbDoahThu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tbDoahThu.setRowHeight(26);
		panel_2.add(jpsDT);

		
		
		//render thong ke Khach hàng
		 hoaDonDao = (HoaDonRemote) Naming.lookup("rmi://192.168.1.4:2001/hoaDonRemote");
		 listKH = hoaDonDao.ThongKeKH();
		 renderData(listKH);
		 
		 //render thong ke phong
		Date date = new Date();
		 DateFormat dateFormat = null;
	     dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	      String nowString = dateFormat.format(date);
		 listPhong = hoaDonDao.ThongKePhongTheoNgay(nowString);
		 renderDataPhong(listPhong);
		 
		 
		 listDT = hoaDonDao.ThongKePhongDoanhThuTheoNgay();
		 renderDataDoanhThu(listDT);
	}
	
	public void renderData(List<Object[]> arrKH) {
		String[] headerKH = {"Mã Khách hàng","Số lần", "Tổng tiền chi" };
		dftKH = new DefaultTableModel(headerKH, 0);

		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    //String str1 = currencyVN.format(vnd);
	    
	    for(Object[] person : arrKH) {
			String name = (String) person[0];
			Number num = (Number) person[1];
			Number money = (Number) person[2];
			
			String[] rowData = {
					name, String.valueOf(num),  currencyVN.format(money)
				};
	                       
				dftKH.addRow(rowData);
		}
		 
			
			tbKhachHang.setModel(dftKH);
	}
	
	public void renderDataPhong(List<Object[]> arrP) {
		String[] headerKH = {"Mã Phòng","Số lần", "Tổng tiền" };
		dftPhong = new DefaultTableModel(headerKH, 0);

		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    //String str1 = currencyVN.format(vnd);
		for(Object[] person : arrP) {
		Number name = (Number) person[0];
		Number num = (Number) person[1];
		Number money = (Number) person[2];
		if((Double)money != 0) {
			String[] rowData = {
					String.valueOf(name), String.valueOf(num),  currencyVN.format(money)
			};
			
			
			dftPhong.addRow(rowData);
		}
		else {
			String[] rowData = {
					String.valueOf(name), String.valueOf(num),  "Chưa thanh toán"
			};
			
			
			dftPhong.addRow(rowData);
		}
		
	}
	    
		 
			
			tbPhong.setModel(dftPhong);
	}
	
	public void renderDataDoanhThu(List<Object[]> arrP) {
		String[] headerKH = {"Ngày tháng","Số lần", "Tổng doanh thu" };
		dftDT = new DefaultTableModel(headerKH, 0);

		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    //String str1 = currencyVN.format(vnd);
		for(Object[] person : arrP) {
		Date name = (Date) person[0];
		Number num = (Number) person[1];
		Number money = (Number) person[2];
		
		
			String[] rowData = {
					String.valueOf(name), String.valueOf(num),  currencyVN.format(money)
			};
			
			
			dftDT.addRow(rowData);
		
		
		}
	    
		 
			
			tbDoahThu.setModel(dftDT);
	}
}
