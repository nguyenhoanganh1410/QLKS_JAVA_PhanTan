package app;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangRemote;
import dao.NhanVienRemote;
import dao.TaiKhoanDao;
import dao.TaiKhoanRemote;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmTaiKhoan extends JPanel {
	private JTextField txtMaNV;
	private JTextField txtMK;
	private JTable tbTaiKhoan;
	private DefaultTableModel dftTaiKhoan;
	JComboBox cbTenQuyen;
	JComboBox cbTrangThai;
	TaiKhoanRemote tkDao;
	ArrayList<TaiKhoan> listTK ;
	ArrayList<NhanVien> NhanVienList ;
	NhanVienRemote nvDao;
	JComboBox cbNhanVien;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public FrmTaiKhoan() throws MalformedURLException, RemoteException, NotBoundException {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 965, 109);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setBounds(24, 22, 82, 14);
		panel.add(lblMa);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setBounds(426, 22, 67, 14);
		panel.add(lblMtKhu);
		
		JLabel lblTnQuyn = new JLabel("Tên quyền:");
		lblTnQuyn.setBounds(750, 22, 82, 14);
		panel.add(lblTnQuyn);
		
		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setBounds(426, 68, 82, 14);
		panel.add(lblTrngThi);
		
		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setForeground(new Color(192, 192, 192));
		txtMaNV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMaNV.getText().equals("Nhập mã nhân viên...")) {
					txtMaNV.setText("");
				}
			}
		});
		txtMaNV.setText("Nhập mã nhân viên...");
		txtMaNV.setBounds(104, 16, 221, 33);
		panel.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		txtMK = new JTextField();
		txtMK.setForeground(new Color(192, 192, 192));
		txtMK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMK.getText().equals("Nhập mã nhân viên...")) {
					txtMK.setText("");
				}
			}
			
		});
		txtMK.setText("Nhập mật khẩu...");
		txtMK.setColumns(10);
		txtMK.setBounds(497, 13, 221, 33);
		panel.add(txtMK);
		
		 cbTenQuyen = new JComboBox();
		cbTenQuyen.setBounds(832, 13, 123, 33);
		panel.add(cbTenQuyen);
		cbTenQuyen.addItem("Nhân viên");
		cbTenQuyen.addItem("Quản lý");
		 cbTrangThai = new JComboBox();
		cbTrangThai.setBounds(502, 65, 216, 33);
		panel.add(cbTrangThai);
		cbTrangThai.addItem("Bình thường");
		cbTrangThai.addItem("Đã khóa");
		
		JLabel lblNewLabel = new JLabel("Nhân viên");
		lblNewLabel.setBounds(24, 74, 64, 14);
		panel.add(lblNewLabel);
		
		 cbNhanVien = new JComboBox();
		 cbNhanVien.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		int tennvIDX = cbNhanVien.getSelectedIndex();
		 		String maNV = NhanVienList.get(tennvIDX).getMaNhanVien();
		 		txtMaNV.setText(maNV);
		 	}
		 });
		 cbNhanVien.addInputMethodListener(new InputMethodListener() {
		 	public void caretPositionChanged(InputMethodEvent event) {
		 	}
		 	public void inputMethodTextChanged(InputMethodEvent event) {
		 		
		 	}
		 });
		 cbNhanVien.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
//		 		int tennvIDX = cbNhanVien.getSelectedIndex();
//		 		String maNV = NhanVienList.get(tennvIDX).getMaNhanVien();
//		 		txtMaNV.setText(maNV);
		 		
		 		
		 	}
		 });
		cbNhanVien.setBounds(104, 64, 221, 34);
		panel.add(cbNhanVien);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 131, 965, 56);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TaiKhoan tk = getKTkFromTxt();
					 int tb = JOptionPane.showConfirmDialog(null, "Bạn  chắc chắn xóa tài khoản này không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
			            if (tb == JOptionPane.YES_OPTION) {
			            	if(tkDao.deleteTaiKhoan(tk.getTenTaiKhoan().getMaNhanVien())) {
			            			xoaRongInput();
								
								listTK = (ArrayList<TaiKhoan>) tkDao.layTaiKhoan();
								renderData(listTK);
								JOptionPane.showMessageDialog(txtMK, "Xóa Thành công!!");
							}
			            	else {
			            		JOptionPane.showMessageDialog(txtMK, "Hãy chọn một tài khoản để cập nhật!!");
			            	}
			            }
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnXoa.setBackground(new Color(30, 144, 255));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBounds(866, 11, 89, 34);
		panel_1.add(btnXoa);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					TaiKhoan tk = getKTkFromTxt();
					try {
						 int tb = JOptionPane.showConfirmDialog(null, "Bạn  chắc chắn muốn cập nhật tài khoản  này không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
				            if (tb == JOptionPane.YES_OPTION) {
				            	if(tkDao.updateTaiKhoan( tk) ) {
									xoaRongInput();
									
									listTK = (ArrayList<TaiKhoan>) tkDao.layTaiKhoan();
									renderData(listTK);
									JOptionPane.showMessageDialog(txtMK, "Cập nhật Thành công!!");
								}
				            	else {
				            		JOptionPane.showMessageDialog(txtMK, "Hãy chọn một tài khoản để cập nhật!!");
				            	}
				            }
						
					} catch (RemoteException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(txtMK, "Cập nhật thất bại!!");
					}
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				
			}
				
			
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(30, 144, 255));
		btnUpdate.setBounds(768, 11, 89, 34);
		panel_1.add(btnUpdate);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkMK()) {
					try {
						TaiKhoan tk = getKTkFromTxt();
						if(tkDao.addTaiKhoan(tk)) {
							JOptionPane.showMessageDialog(btnLuu, "Thêm tài khoản thành công");
							Object[] row = {tk.getTenTaiKhoan().getMaNhanVien(), tk.getMatKhau(), tk.getTenQuyen(),tk.getTrangThai(),tk.getHoatDong() };
							dftTaiKhoan.addRow(row);
							
							btnLuu.setEnabled(false);
							
						}
						else {
							JOptionPane.showMessageDialog(btnLuu, "Nhân viên đã có tài khoản!");
						}
						
						
						
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
		btnLuu.setBounds(669, 11, 89, 34);
		panel_1.add(btnLuu);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColorInput();
				xoaRongInput();
				
				btnLuu.setEnabled(true);
				
			}
		});
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setBounds(570, 11, 89, 34);
		panel_1.add(btnThem);
		
		
		String[] header = {"Mã nhân viên","Mật khẩu",
				"Tên quyền","Trạng thái","Hoạt động"};
		dftTaiKhoan = new DefaultTableModel(header, 0);
		tbTaiKhoan = new JTable(dftTaiKhoan)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbTaiKhoan.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		//tbKhachHang.getTable.setFont(new Font("Arial", Font.PLAIN, 14));
		tbTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane jps = new  JScrollPane(tbTaiKhoan);
		tbTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int row = tbTaiKhoan.getSelectedRow();
				 
	               if(row != -1){
	                    setColorInput();
	                     txtMaNV.setEnabled(false);
	                     String ma = tbTaiKhoan.getValueAt(row, 0).toString();
	                     txtMaNV.setText(ma);
	                     txtMK.setText(tbTaiKhoan.getValueAt(row, 1).toString());
	                    
	                    
	                    if(tbTaiKhoan.getValueAt(row, 2).toString().toLowerCase().equals("Nhân Viên".toLowerCase())) {
	                    	cbTenQuyen.setSelectedIndex(0);
	                    }
	                    else {
	                    	cbTenQuyen.setSelectedIndex(1);
	                    }
	                   
	                    if(tbTaiKhoan.getValueAt(row, 3).toString().toLowerCase().equals("bình thường".toLowerCase())) {
	                    	cbTrangThai.setSelectedIndex(0);
	                    }
	                    else {
	                    	cbTrangThai.setSelectedIndex(1);
	                    }
	                   
	                    for(int i = 0; i < NhanVienList.size();i++) {
	                    	
	                    	if(NhanVienList.get(i).getMaNhanVien().equals(ma  )) {
	                    		cbNhanVien.setSelectedIndex(i);
	                    	}
	                    }
	               }
				
				
			}
		});
		this.add(jps);
		jps.setBounds(10, 200, 965, 340);
		btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));	
		btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	
		
		
		
		  tkDao = (TaiKhoanRemote) Naming.lookup("rmi://192.168.1.4:2001/taiKhoanRemote");
		 listTK =  (ArrayList<TaiKhoan>) tkDao.layTaiKhoan();
		 if(listTK!=null) {
			 renderData(listTK);
		 }
		 renderNhanVien();
		 
	}
	
	//render data len table
	public void renderData(ArrayList<TaiKhoan> arrTK) {
		String[] header = {"Mã nhân viên","Mật khẩu",
				"Tên quyền","Trạng thái","Hoạt động"};
		dftTaiKhoan = new DefaultTableModel(header, 0);
			
			for(TaiKhoan kh : arrTK) {
				String[] rowData = {
					kh.getTenTaiKhoan().getMaNhanVien(),kh.getMatKhau(),kh.getTenQuyen(),kh.getTrangThai(),kh.getHoatDong()
				};
	                       
				dftTaiKhoan.addRow(rowData);
			}
	               
			tbTaiKhoan.setModel(dftTaiKhoan);
			
	}
	
	//render ten cac nhanvien len jcombo
	public void renderNhanVien() throws MalformedURLException, RemoteException, NotBoundException {
		nvDao = (NhanVienRemote) Naming.lookup("rmi://192.168.1.4:2001/nhanVienRemote");
		
		NhanVienList =  (ArrayList<NhanVien>) nvDao.getAllNhanVien();
		NhanVienList.forEach(val->{
			cbNhanVien.addItem(val.getTenNhanVien());
		});
	}
	
	//set color cho the input
    public void setColorInput(){
         txtMaNV.setForeground(new java.awt.Color(26, 25, 25));
         txtMK.setForeground(new java.awt.Color(26, 25, 25));
        
        
         
         
    }
    //xoa rong cac input
    public void xoaRongInput(){
        
    	txtMaNV.setText("");
    	txtMK.setText("");
    	
        
         
    }
    
    /**
     * kiểm tra mật khẩu từ 6-18 ký tự và không có ký tự đặc biệt
     * @return boolean
     */
    public boolean checkMK() {
    	 //check mật khẩu từ 6-18 ký tự và không có ký tự đặc biệt
        ///^[a-z0-9_-]{6,18}$/
        String passWorld = txtMK.getText().trim();
         String regexPasWorld = "^[a-z0-9_-]{6,18}$";//check mail
          if ( !passWorld.matches("^[a-z0-9_-]{6,18}$")) {
			JOptionPane.showMessageDialog(txtMK, "Mật khẩu 6-18 ký tự và không chứa ký tự đặc biệt");
			return false;
          }
          
          return true;
    }
    
    /**
     * Lấy dữ liệu ở các input
     * @return TaiKhoan tk
     */
    public TaiKhoan getKTkFromTxt() throws RemoteException {
		String maNV = txtMaNV.getText();
		String mk = txtMK.getText();
		String tenQuyen = cbTenQuyen.getSelectedItem().toString();
		String trangThai = cbTrangThai.getSelectedItem().toString();
		NhanVien nv = nvDao.getNhanVienByID(maNV);
		
		String hd = "offline";
		TaiKhoan tk = new TaiKhoan(nv,mk,tenQuyen,hd, trangThai);
		return tk;
		
			
	}
}
