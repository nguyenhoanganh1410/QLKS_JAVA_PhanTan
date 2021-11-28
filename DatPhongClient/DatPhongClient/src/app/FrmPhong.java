package app;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienRemote;
import dao.PhongRemote;
import entity.NhanVien;
import entity.Phong;
import sun.security.util.Length;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPhong extends JPanel {
	private JTextField txtMaPhong;
	private JTextField txtGiaGio;
	private JTextField txtGiaDem;
	private JTable tbPhong;
	private DefaultTableModel dftPhong;
	PhongRemote phongDao;
	ArrayList<Phong> phongList ;
	JComboBox cbLoaiGiuong;
	JComboBox cbTrangThai;
	JComboBox cbTang;
	JComboBox cbLoaiPhong;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public FrmPhong() throws MalformedURLException, RemoteException, NotBoundException {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 963, 118);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã phòng:");
		lblNewLabel.setBounds(10, 25, 63, 14);
		panel.add(lblNewLabel);
		
		JLabel lblLoiPhng = new JLabel("Loại phòng:");
		lblLoiPhng.setBounds(10, 74, 63, 14);
		panel.add(lblLoiPhng);
		
		JLabel lblTrngThi = new JLabel("Trạng thái:");
		lblTrngThi.setBounds(274, 25, 68, 14);
		panel.add(lblTrngThi);
		
		JLabel lblLoiGing = new JLabel("Loại giường");
		lblLoiGing.setBounds(274, 74, 63, 14);
		panel.add(lblLoiGing);
		
		JLabel lblTng = new JLabel("Tầng:");
		lblTng.setBounds(498, 25, 46, 14);
		panel.add(lblTng);
		
		JLabel lblGiPhngGi = new JLabel("Giá phòng giờ:");
		lblGiPhngGi.setBounds(499, 74, 79, 14);
		panel.add(lblGiPhngGi);
		
		JLabel lblGiPhngm = new JLabel("Giá phòng đêm:");
		lblGiPhngm.setBounds(730, 25, 87, 14);
		panel.add(lblGiPhngm);
		
		txtMaPhong = new JTextField();
		txtMaPhong.setForeground(Color.GRAY);
		txtMaPhong.setText("Nhập mã phòng...");
		txtMaPhong.setBounds(72, 17, 180, 30);
		panel.add(txtMaPhong);
		txtMaPhong.setColumns(10);
		
		 cbLoaiGiuong = new JComboBox();
		cbLoaiGiuong.setBounds(352, 66, 124, 30);
		panel.add(cbLoaiGiuong);
		cbLoaiGiuong.addItem("Đơn");
		cbLoaiGiuong.addItem("Đôi");
		
		
		 cbTrangThai = new JComboBox();
		cbTrangThai.setBounds(352, 17, 124, 30);
		panel.add(cbTrangThai);
		cbTrangThai.addItem("Chưa sử dụng");
		cbTrangThai.addItem("Đã sử dụng");
		cbTrangThai.addItem("Bảo trì");
		
		
		 cbTang = new JComboBox();
		cbTang.setBounds(582, 17, 138, 30);
		panel.add(cbTang);
		cbTang.addItem("1");
		cbTang.addItem("2");
		cbTang.addItem("3");
		cbTang.addItem("4");
		
		txtGiaGio = new JTextField();
		txtGiaGio.setText("50.000");
		txtGiaGio.setColumns(10);
		txtGiaGio.setBounds(582, 71, 136, 30);
		panel.add(txtGiaGio);
		
		txtGiaDem = new JTextField();
		txtGiaDem.setText("150.000");
		txtGiaDem.setForeground(Color.GRAY);
		txtGiaDem.setColumns(10);
		txtGiaDem.setBounds(817, 17, 136, 30);
		panel.add(txtGiaDem);
		
		 cbLoaiPhong = new JComboBox();
		cbLoaiPhong.setBounds(72, 66, 180, 30);
		cbLoaiPhong.addItem("Thường");
		cbLoaiPhong.addItem("Vip");
		panel.add(cbLoaiPhong);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 140, 963, 58);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(cbLoaiGiuong, "Chức năng đang nâng cấp!!");
			}
		});
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBackground(new Color(30, 144, 255));
		btnXoa.setBounds(864, 11, 89, 36);
		panel_1.add(btnXoa);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println("\n" + chuanHoaTien(txtGiaDem.getText().toString()));
				//System.out.println(nv.toString());
				try {
					 int tb = JOptionPane.showConfirmDialog(null, "Bạn  chắc chắn muốn cập nhật Phòng này không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
			            if (tb == JOptionPane.YES_OPTION) {
			            	 if(kiemTraData() ){
			            		 
			            		 Phong nv = restText();
			            		 if(phongDao.capNhatPhong( nv) ) {
			            			 xoaRongInput();
			            			 
			            			 phongList = (ArrayList<Phong>) phongDao.getAllPhong();
			            			 renderData(phongList);
			            			 JOptionPane.showMessageDialog(cbLoaiGiuong, "Cập nhật Thành công!!");
			            		 }
			            		 else {
			            			 JOptionPane.showMessageDialog(cbLoaiGiuong, "Cập nhật Faile!!");
			            		 }
			            	 }
			            }
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(btnCapNhat, "Cập nhật thất bại!!");
				}
			
			}
			
		});
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setBackground(new Color(30, 144, 255));
		btnCapNhat.setBounds(765, 11, 89, 36);
		panel_1.add(btnCapNhat);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if(kiemTraData()){
			            Phong nv = restText();
			            System.out.println(nv);
			            try {
							if(phongDao.addPhong(nv)){
								Locale localeVN = new Locale("vi", "VN");
							    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
								dftPhong.addRow(new Object[]{
										String.valueOf(nv.getSoPhong()), nv.getLoaiPhong(), nv.getTrangThai(),nv.getLoaiGiuong(),
										String.valueOf(nv.getSoTang()),currencyVN.format(nv.getGiaPhong()), currencyVN.format(nv.getGiaPhongDem())
							    });
								
				                    btnLuu.setEnabled(false);
				                    xoaRongInput();
							     JOptionPane.showMessageDialog(btnLuu, "Thêm thành công");
							}
							 else {
								  JOptionPane.showMessageDialog(btnLuu, "Mã phòng đã tồn tại trong hệ thống!");
							 }
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }
				
			}
			
			
			
			
		});
		btnLuu.setEnabled(false);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setBackground(new Color(30, 144, 255));
		btnLuu.setBounds(666, 11, 89, 36);
		panel_1.add(btnLuu);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColorInput();
				xoaRongInput();
				txtMaPhong.setEnabled(true);
				btnLuu.setEnabled(true);
				txtMaPhong.requestFocus();
			}
			
		});
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setBounds(567, 11, 89, 36);
		panel_1.add(btnThem);
		
		String[] header = {"Mã Phòng","Loại Phòng",
				"Trạng Thái","Loại Giường","Tầng", "Giá theo giờ", "Giá ngày đêm"};
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
				 int row = tbPhong.getSelectedRow();
				 
	               if(row != -1){
	            	   setColorInput();
	                     txtMaPhong.setEnabled(false);
	                     txtMaPhong.setText(tbPhong.getValueAt(row, 0).toString());
	                    txtGiaGio.setText(chuanHoaTien(tbPhong.getValueAt(row, 5).toString()));
	                    txtGiaDem.setText(chuanHoaTien(tbPhong.getValueAt(row, 6).toString()));
	                  
	                    
	                    if(tbPhong.getValueAt(row, 1).toString().toLowerCase().equals("vip".toLowerCase())) {
	                    	cbLoaiPhong.setSelectedIndex(1);
	                    }
	                    else {
	                    	cbLoaiPhong.setSelectedIndex(0);
	                    }
	                    
	                    
	                    String tinhTrang = tbPhong.getValueAt(row, 2).toString();
	                    cbTrangThai.setSelectedItem(tinhTrang);
	                    
	                    
	                    String loaiGiuong = tbPhong.getValueAt(row, 3).toString();
	                    cbLoaiGiuong.setSelectedItem(loaiGiuong);
	                    
	                
	                   
	                    
	                
	                    
	                    String soTang = tbPhong.getValueAt(row, 4).toString();
	                    cbTang.setSelectedItem(soTang);
	                    
	              
	                   
	               }
			}
		});
		
		this.add(jps);
		//jps.setBounds(565, 15, 72, 30);
		
		//JButton btnNewButton_1 = new JButton("New button");
		jps.setBounds(10, 209, 963, 331);
		//add(btnNewButton_1);
		tbPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tbPhong.setRowHeight(26);
		
		btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	
		btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	
		phongDao = (PhongRemote) Naming.lookup("rmi://192.168.1.4:2001/phongRemote");
		
		phongList =  (ArrayList<Phong>) phongDao.getAllPhong();
		//System.out.println(NhanVienList);
		if(phongList!=null) {
			renderData(phongList);
		}
	}
	
	public void renderData(ArrayList<Phong> arrPhong) {
		String[] header = {"Mã Phòng","Loại Phòng",
				"Trạng Thái","Loại Giường","Tầng", "Giá theo giờ", "Giá ngày đêm"};
		dftPhong = new DefaultTableModel(header, 0);
		Locale localeVN = new Locale("vi", "VN");
	    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
	    //String str1 = currencyVN.format(vnd);
			for(Phong nv : arrPhong) {
				String ns = "";
				String nvl = "";
				
				
				String[] rowData = {
						String.valueOf(nv.getSoPhong()), nv.getLoaiPhong(), nv.getTrangThai(),nv.getLoaiGiuong(),
						String.valueOf(nv.getSoTang()),currencyVN.format(nv.getGiaPhong()), currencyVN.format(nv.getGiaPhongDem())
				};
	                       
				dftPhong.addRow(rowData);
			}
	               
			tbPhong.setModel(dftPhong);
	}
	 //set color cho the input
    public void setColorInput(){
         txtMaPhong.setForeground(new java.awt.Color(26, 25, 25));
        // txtNhpMNhn.setForeground(new java.awt.Color(26, 25, 25));
         txtGiaDem.setForeground(new java.awt.Color(26, 25, 25));
         txtGiaGio.setForeground(new java.awt.Color(26, 25, 25));
        
         
         
    }
    //xoa rong cac input
    public void xoaRongInput(){
        
    	txtMaPhong.setText("");
        txtGiaDem.setText("");
        txtGiaGio.setText("");
    	
        
         
    }
    
    //láy dữ liệu từ textfiled
    public Phong restText(){
        int maPhong = Integer.parseInt(txtMaPhong.getText().toString());
        
        Double giaDem = Double.parseDouble(txtGiaDem.getText().toString());
       // System.out.println( chuanHoaTien(txtGiaDem.getText().toString()  ));
        
        Double giagio = Double.parseDouble(txtGiaGio.getText().toString());
        String loaiPhong = cbLoaiPhong.getSelectedItem().toString();
        String loaiGiuong = cbLoaiGiuong.getSelectedItem().toString();
        int tang = Integer.parseInt( cbTang.getSelectedItem().toString());
        String trangThai = cbTrangThai.getSelectedItem().toString();
        Phong p = new Phong(maPhong, loaiPhong, trangThai, loaiGiuong, tang, giagio, giaDem);
     
        return p;

    }
    
    //chuyen 80.000 d -> 80000
    public String chuanHoaTien(String money) {
    	if(money.indexOf(".") != -1) {
    		String newMoney = money.replace(".", "");
    		String m = newMoney.substring(0, newMoney.length()-2);
    		
    		
    		return m;
    		
    	}
    	return money;
    }
    public boolean kiemTraData() {
    	 String maPhong = txtMaPhong.getText().toString();
    	 String giaDem =txtGiaDem.getText().toString();
         String giagio = txtGiaGio.getText().toString();
         
         if(maPhong.length() < 0) {
        	 JOptionPane.showMessageDialog(cbLoaiGiuong, "Bạn chưa nhập mã phòng");
        	 return false;
         }
         else if(!maPhong.matches("^[0-9]{3}" ) ){
        	 JOptionPane.showMessageDialog(cbLoaiGiuong, "Mã phòng gồm 3 chữ số");
        	 return false;
         }
         else if(!giaDem.matches("^[0-9]{4,12}") ){
        	 JOptionPane.showMessageDialog(cbLoaiGiuong, "Giá phòng đêm không chứa ký tự đặc biêt!");
        	 return false;
         }
         else if(!giagio.matches("^[0-9]{4,12}") ){
        	 JOptionPane.showMessageDialog(cbLoaiGiuong, "Giá phòng giờ không chứa ký tự đặc biêt!");
        	 return false;
         }
         
    	return true;
    }
}
