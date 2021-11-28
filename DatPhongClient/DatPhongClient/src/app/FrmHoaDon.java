package app;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.HoaDonRemote;
import dao.NhanVienRemote;
import entity.HoaDon;
import entity.NhanVien;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmHoaDon extends JPanel {
	private JTextField txtSearch;
	private JTable tbHoaDon;
	private DefaultTableModel dftHoaDon;
	ArrayList<HoaDon> hdList  = new ArrayList<HoaDon>();
	JButton btnShow;
	JLabel lblSoHD;
	HoaDonRemote hoaDonDao ;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public FrmHoaDon() throws MalformedURLException, RemoteException, NotBoundException {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 960, 95);
		add(panel);
		panel.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtSearch.getText().equals("Nhập mã hóa đơn...")) {
					txtSearch.setText("");
					txtSearch.setForeground(new java.awt.Color(26, 25, 25));
				}
			}
		});
		txtSearch.setForeground(new Color(192, 192, 192));
		txtSearch.setText("Nhập mã hóa đơn...");
		txtSearch.setBounds(10, 47, 368, 40);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnTK = new JButton("Tìm kiếm");
		btnTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maHD = txtSearch.getText();
				
				
				 ArrayList<HoaDon> arr = searchHD(maHD);
					
					if(arr.size()==0) {
						JOptionPane.showMessageDialog(btnTK, "Không tìm thấy hóa đơn!");
					}
					else {
						renderData(arr);
						btnShow.setEnabled(true);
					}
			}
			
		});
		btnTK.setForeground(new Color(255, 255, 255));
		btnTK.setBackground(new Color(30, 144, 255));
		btnTK.setBounds(388, 47, 93, 40);
		panel.add(btnTK);
		
		 btnShow = new JButton("Hiển thị DS");
		 btnShow.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		ArrayList<HoaDon> hd = new ArrayList<HoaDon>();
		 		try {
					hd = (ArrayList<HoaDon>) hoaDonDao.layDsHoaDon();
					renderData(hd);
				
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 		
		 	}
		 });
		btnShow.setForeground(Color.WHITE);
		btnShow.setBackground(new Color(30, 144, 255));
		btnShow.setBounds(10, 7, 100, 33);
		panel.add(btnShow);
		
		JLabel lblNewLabel = new JLabel("Số hóa đơn:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(826, 73, 79, 14);
		panel.add(lblNewLabel);
		
		 lblSoHD = new JLabel("100");
		lblSoHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoHD.setBounds(904, 73, 46, 14);
		panel.add(lblSoHD);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 49, 832, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 49, 832, 2);
		add(separator_1);
		
	
		String[] header = {"Mã hóa đơn","Khách hàng",
				"Ngày đến","Ngày đi", "Kiểu thuê","Tổng tiền", "Trạng thái", "Nhân viên"};
		dftHoaDon = new DefaultTableModel(header, 0);
		tbHoaDon = new JTable(dftHoaDon)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbHoaDon.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		//tbKhachHang.getTable.setFont(new Font("Arial", Font.PLAIN, 14));
		tbHoaDon.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane jps = new  JScrollPane(tbHoaDon);
		tbHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int row = tbHoaDon.getSelectedRow();
				 
	               if(row != -1){
	                  }
	                   
	               }
			
		});
		
		this.add(jps);
		//jps.setBounds(565, 15, 72, 30);
		
		//JButton btnNewButton_1 = new JButton("New button");
		jps.setBounds(10, 123, 956, 435);
		
		btnTK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnShow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		 hoaDonDao = (HoaDonRemote) Naming.lookup("rmi://192.168.1.4:2001/hoaDonRemote");
		
		hdList =  (ArrayList<HoaDon>) hoaDonDao.layDsHoaDon();
		
		if(	hdList!=null) {
			renderData(hdList);
		}
	}
	
	
	public void renderData(ArrayList<HoaDon> arrHD) {
		String[] header = {"Mã hóa đơn","Khách hàng",
				"Ngày đến","Ngày đi",  "Kiểu thuê","Tổng tiền", "Trạng thái", "Nhân viên"};
		dftHoaDon = new DefaultTableModel(header, 0);
		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    //String str1 = currencyVN.format(vnd);
		 int i = 0;
			for(HoaDon s : arrHD) {
	                   //  System.out.print(i++);
				String[] rowData = {
					String.valueOf(s.getMaHoaDon()),s.getKhachHangID().getTenKhachHang(),s.getNgayDen()
					,s.getNgayDi(), s.getKieuThue(), currencyVN.format(s.getTongTien()), s.getTrangThai()
					,s.getNhanVienID().getTenNhanVien()
				};
	                       
				dftHoaDon.addRow(rowData);
	                        
			} 	
			lblSoHD.setText(String.valueOf(arrHD.size()));
			tbHoaDon.setModel(dftHoaDon);
	}
	 public String changeDateToString(Date date){
         
         DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
         String dateString  = df.format(date);
         return dateString;
   }
	   public ArrayList<HoaDon> searchHD(String maHD) {
	    	ArrayList<HoaDon> list = new ArrayList<HoaDon>();
			hdList.forEach(val->{
				if(String.valueOf(val.getMaHoaDon()).trim().equals(maHD.trim())){
					list.add(val);
				}
			});
			
			return list;
		}
}
