package app;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangRemote;
import entity.KhachHang;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FrmKhachHang extends JPanel {
	private JTable table;
	private JTextField txtCMND;
	private JTextField txtTenKH;
	private JTextField txtDiaChii;
	private JTextField txtSDT;
	private JLabel lblNewLabel_1_2;
	private JTextField txtNhpCmnd;
	KhachHangRemote khachHangDao;
	private DefaultTableModel dftKhachHang;
	private JTable tbKhachHang;
	private JTable table_1;
	JButton btnHinThDs;
	ArrayList<KhachHang> listKH ;
	JComboBox comboBox;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public void renderData(ArrayList<KhachHang> arrKH) {
		String[] header = {"CMND","Tên Khách hàng",
				"SĐT","Địa chỉ","Quốc gia"};
		dftKhachHang = new DefaultTableModel(header, 0);
			
			for(KhachHang kh : arrKH) {
				String[] rowData = {
					kh.getSoCMND(),kh.getTenKhachHang(),kh.getSoDienThoai(),kh.getDiaChi(),kh.getLoaiKhachHang()
				};
	                       
				dftKhachHang.addRow(rowData);
			}
	               
			tbKhachHang.setModel(dftKhachHang);
	}
	
	public ArrayList<KhachHang> searchKH(String CMND) {
		ArrayList<KhachHang> list = new ArrayList<KhachHang>();
		listKH.forEach(val->{
			if(val.getSoCMND().trim().equals(CMND.trim())){
				list.add(val);
			}
		});
		
		return list;
	}
	
	public KhachHang getKhFromTxt() {
		String cmnd = txtCMND.getText();
		String ten = txtTenKH.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChii.getText();
		String loaiKH = comboBox.getSelectedItem().toString();
		
		KhachHang kh = new KhachHang(ten, cmnd, sdt, loaiKH, diaChi);
		return kh;
		
			
	}
	
	public boolean checkvalue() {
		String cmnd = txtCMND.getText();
		String ten = txtTenKH.getText();
		String sdt = txtSDT.getText();
		String diaChi = txtDiaChii.getText();
		String loaiKH = comboBox.getSelectedItem().toString();
		
		String regexPhone = "^[0-9]{10}";
		String regexCMND = "^[0-9]{9,11}";
		
		  if (!(cmnd.length() > 0 && cmnd.matches(regexCMND))) {
				JOptionPane.showMessageDialog(btnHinThDs, "CMND từ 9-11 số");
				return false;
		   }
		    if(ten.length()<=0) {
				JOptionPane.showMessageDialog(btnHinThDs, "Tên khách hàng không được để trống");			
				return false;
		            }
		           if(diaChi.length() <=0){
				JOptionPane.showMessageDialog(btnHinThDs, "Địa chỉ khách hàng không được để trống");			
				return false;             
		           }
		             if(!sdt.matches(regexPhone)){
				JOptionPane.showMessageDialog(btnHinThDs, "Số điện thoại có 10 số");			
				return false;             
		           }
		return true;
	}
	
	public FrmKhachHang() throws MalformedURLException, RemoteException, NotBoundException {
		setBackground(new Color(248, 248, 255));
		setLayout(null);
		this.setSize(987, 536);
		
		
		JLabel lblCmnd = new JLabel("Số CMND");
		lblCmnd.setBounds(23, 27, 115, 14);
		add(lblCmnd);
		
		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCMND.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtCMND.getText().equals("Nhập số cmnd...")) {
					txtCMND.setText("");
				}
			}
		});
		txtCMND.setForeground(new Color(192, 192, 192));
		txtCMND.setText("Nhập số cmnd...");
		txtCMND.setBounds(148, 19, 226, 30);
		add(txtCMND);
		txtCMND.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tên Khách hàng");
		lblNewLabel.setBounds(22, 66, 116, 14);
		add(lblNewLabel);
		
		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTenKH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTenKH.getText().equals("Nhập tên khách hàng...")) {
					txtTenKH.setText("");
				}
			}
		});
		txtTenKH.setForeground(new Color(192, 192, 192));
		txtTenKH.setText("Nhập tên khách hàng...");
		txtTenKH.setBounds(149, 58, 225, 30);
		add(txtTenKH);
		txtTenKH.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Số Điện Thoại:");
		lblNewLabel_1.setBounds(433, 66, 84, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1.setBounds(433, 27, 46, 14);
		add(lblNewLabel_1_1);
		
		txtDiaChii = new JTextField();
		txtDiaChii.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDiaChii.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtDiaChii.getText().equals("Nhập địa chỉ...")) {
					txtDiaChii.setText("");
				}
			}
		});
		txtDiaChii.setForeground(new Color(192, 192, 192));
		txtDiaChii.setText("Nhập địa chỉ...");
		txtDiaChii.setColumns(10);
		txtDiaChii.setBounds(527, 19, 187, 30);
		add(txtDiaChii);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSDT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtSDT.getText().equals("Nhập số điện thoại...")) {
					txtSDT.setText("");
				}
			}
		});
		txtSDT.setText("Nhập số điện thoại...");
		txtSDT.setForeground(new Color(192, 192, 192));
		txtSDT.setColumns(10);
		txtSDT.setBounds(527, 58, 186, 30);
		add(txtSDT);
		
		lblNewLabel_1_2 = new JLabel("Quốc gia");
		lblNewLabel_1_2.setBounds(747, 27, 69, 14);
		add(lblNewLabel_1_2);
		String[] loaiKH = {"Việt Nam", "Nước Ngoài"};
		
		 comboBox = new JComboBox<String>();
		comboBox.setBounds(814, 20, 140, 28);
		comboBox.addItem("Việt Nam");
		comboBox.addItem("Nước Ngoài");
		add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(23, 112, 931, 54);
		add(panel);
		panel.setLayout(null);
		
		txtNhpCmnd = new JTextField();
		txtNhpCmnd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNhpCmnd.setText("");
				txtNhpCmnd.setForeground(new java.awt.Color(26, 25, 25));
			}
		});
		txtNhpCmnd.setForeground(Color.LIGHT_GRAY);
		txtNhpCmnd.setText("Nhập cmnd để tìm kiếm...");
		txtNhpCmnd.setColumns(10);
		txtNhpCmnd.setBounds(10, 11, 225, 32);
		panel.add(txtNhpCmnd);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmnd =	txtNhpCmnd.getText();
				ArrayList<KhachHang> arr = searchKH(cmnd);
				//System.out.println(arr);
				if(arr.size()==0) {
					JOptionPane.showMessageDialog(btnNewButton, "Không tìm thấy khách hàng!");
				}
				else {
					renderData(arr);
					btnHinThDs.setEnabled(true);
				}
			}
		});
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setBounds(247, 11, 91, 32);
		panel.add(btnNewButton);
		
		 btnHinThDs = new JButton("Hiển thị DS");
		btnHinThDs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderData(listKH);
				btnHinThDs.setEnabled(false);
			}
		});
		btnHinThDs.setForeground(new Color(255, 255, 255));
		btnHinThDs.setToolTipText("HIển thị lại danh sách");
		btnHinThDs.setBackground(new Color(30, 144, 255));
		btnHinThDs.setBounds(348, 11, 96, 32);
		panel.add(btnHinThDs);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setBackground(new Color(30, 144, 255));
		btnXoa.setBounds(828, 11, 72, 34);
		panel.add(btnXoa);
		
		JButton btnCpNht = new JButton("Cập nhật");
		btnCpNht.setForeground(new Color(255, 255, 255));
		btnCpNht.setBackground(new Color(30, 144, 255));
		btnCpNht.setBounds(729, 11, 89, 34);
		panel.add(btnCpNht);
		
		JButton btnLu = new JButton("Lưu");
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkvalue()) {
					KhachHang kh = getKhFromTxt();
					try {
						khachHangDao.themKhachHang(kh);
						JOptionPane.showMessageDialog(btnLu, "Thêm Khách hàng thành công");
						 Object[] row = {txtCMND.getText(),
			                     txtTenKH.getText(), txtSDT.getText(), txtDiaChii.getText(), comboBox.getSelectedItem().toString() };
						 dftKhachHang.addRow(row);
			                    JOptionPane.showMessageDialog(btnLu, "Lưu thành công");
			                    btnLu.setEnabled(false);
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(btnLu, "Thêm Khách hàng thất bại");
					}
						
					
				}
				
				
			}
		});
		btnLu.setForeground(new Color(255, 255, 255));
		btnLu.setBackground(new Color(30, 144, 255));
		btnLu.setBounds(647, 11, 72, 34);
		panel.add(btnLu);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRongInput();
				setColorInput();
				txtCMND.setEnabled(true);
				txtCMND.requestFocus();
			}
		});
		btnThem.setForeground(new Color(255, 255, 255));
		btnThem.setBackground(new Color(30, 144, 255));
	btnThem.setBounds(565, 11, 72, 34);
				//btnThem.setBounds(49, 458, 899, -251);
		panel.add(btnThem);
		
		btnHinThDs.setEnabled(false);
		// table Jtapfield Hóa đơn
				String[] header = {"CMND","Tên Khách hàng",
						"SĐT","Địa chỉ","Quốc gia"};
				dftKhachHang = new DefaultTableModel(header, 0);
				tbKhachHang = new JTable(dftKhachHang)
				{
					private static final long serialVersionUID = 1L;
					public boolean editCellAt(int row, int column, java.util.EventObject e) {
						return false;
					}
				};
				tbKhachHang.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
				//tbKhachHang.getTable.setFont(new Font("Arial", Font.PLAIN, 14));
				tbKhachHang.setFont(new Font("Arial", Font.PLAIN, 12));
				JScrollPane jps = new  JScrollPane(tbKhachHang);
				tbKhachHang.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						  int row = tbKhachHang.getSelectedRow();
						 
			               if(row != -1){
			                    setColorInput();
			                     txtCMND.setEnabled(false);
			                     txtCMND.setText(tbKhachHang.getValueAt(row, 0).toString());
			                    txtTenKH.setText(tbKhachHang.getValueAt(row, 1).toString());
			                    txtDiaChii.setText(tbKhachHang.getValueAt(row, 2).toString());
			                    txtSDT.setText(tbKhachHang.getValueAt(row, 3).toString());
			                    String loaiKH = tbKhachHang.getValueAt(row, 4).toString();
			                    if(loaiKH.equals("Việt Nam")) {
			                    	comboBox.setSelectedIndex(1);
			                    }
			                    else {
			                    	comboBox.setSelectedIndex(0);
			                    }
			         
			                    
			               }
					}
				});
				this.add(jps);
				//jps.setBounds(565, 15, 72, 30);
				
				//JButton btnNewButton_1 = new JButton("New button");
				jps.setBounds(23, 200, 931, 340);
				//add(btnNewButton_1);
				tbKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				tbKhachHang.setRowHeight(26);
     
				btnLu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				btnCpNht.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				btnHinThDs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				btnNewButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				
			 khachHangDao = (KhachHangRemote) Naming.lookup("rmi://192.168.1.4:2001/khachHangRemote");
			 listKH =  (ArrayList<KhachHang>) khachHangDao.layDanhSachKhachHang();
			 if(listKH!=null) {
				 renderData(listKH);
			 }
	}
	 //set color cho the input
    public void setColorInput(){
         txtCMND.setForeground(new java.awt.Color(26, 25, 25));
         txtTenKH.setForeground(new java.awt.Color(26, 25, 25));
         txtDiaChii.setForeground(new java.awt.Color(26, 25, 25));
         txtSDT.setForeground(new java.awt.Color(26, 25, 25));
        
         
         
    }
    //xoa rong cac input
    public void xoaRongInput(){
        
    	txtCMND.setText("");
    	txtTenKH.setText("");
    	txtDiaChii.setText("");
    	txtSDT.setText("");
        
         
    }
}
