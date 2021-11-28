package app;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.JDateChooser;

import dao.KhachHangRemote;
import dao.NhanVienDao;
import dao.NhanVienRemote;
import entity.KhachHang;
import entity.NhanVien;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrmNhanVien extends JPanel {
	private JTextField txtNhpMNhn;
	private JTextField txtTenNV;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTextField txtSearch;
	private JTable tbNhanVien;
	private DefaultTableModel dftNhanVien;
	ArrayList<NhanVien> NhanVienList ;
	NhanVienRemote nvDao;
	JDateChooser dcNgaySinh;
	JDateChooser dcNgayVaoLam;
	JComboBox cbChuVu;
	JComboBox cbTinhTrang;
	JButton btnShowDS;
	/**
	 * Create the panel.
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	public void renderData(ArrayList<NhanVien> arrNV) {
		String[] header = {"Mã nhân viên","Tên nhân viên",
				"SĐT","Địa chỉ","Ngày sinh", "Ngày vào làm", "Chức vụ", "Tình trạng"};
		dftNhanVien = new DefaultTableModel(header, 0);
			
			for(NhanVien nv : arrNV) {
				String ns = "";
				String nvl = "";
				if(nv.getNgaySinh()!=null) {
					 ns = nv.getNgaySinh().toString();
				}
				if(nv.getNgayVaoLam()!=null) {
					 nvl = nv.getNgayVaoLam().toString();
				}
				
				String[] rowData = {
						nv.getMaNhanVien(),nv.getTenNhanVien(),nv.getSdt(),nv.getDiaChi(),ns,nvl,nv.getChucVu(),nv.getTinhTrang()
				};
	                       
				dftNhanVien.addRow(rowData);
			}
	               
			tbNhanVien.setModel(dftNhanVien);
	}
	
	 //láy dữ liệu từ textfiled
    public NhanVien restText(){
        String maNv = txtNhpMNhn.getText().toString();
        String tenNv = txtTenNV.getText().toString();
        String sdt = txtSDT.getText().toString();
        String diaChi = txtDiaChi.getText().toString();
        Date ngaySinh = dcNgaySinh.getDate();
        Date ngayLam = dcNgayVaoLam.getDate();
        
        
        
        String tenCv = cbChuVu.getSelectedItem().toString();
       String tinhTrang = cbTinhTrang.getSelectedItem().toString();
        
        return new NhanVien(maNv,tenNv,tenCv,sdt,diaChi,ngaySinh,ngayLam,tinhTrang);

    }
    
    public ArrayList<NhanVien> searchNV(String maNV) {
    	ArrayList<NhanVien> list = new ArrayList<NhanVien>();
		NhanVienList.forEach(val->{
			if(val.getMaNhanVien().trim().equals(maNV.trim())){
				list.add(val);
			}
		});
		
		return list;
	}
	public FrmNhanVien() throws MalformedURLException, RemoteException, NotBoundException {
		setBackground(new Color(230, 230, 250));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 966, 100);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
		lblMaNV.setBounds(10, 24, 87, 14);
		panel.add(lblMaNV);
		
		txtNhpMNhn = new JTextField();
		txtNhpMNhn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNhpMNhn.getText().equals("Nhập mã nhân viên...")) {
					txtNhpMNhn.setText("");
				}
			}
		});
		txtNhpMNhn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtNhpMNhn.setForeground(new Color(192, 192, 192));
		txtNhpMNhn.setText("Nhập mã nhân viên...");
		txtNhpMNhn.setBounds(96, 14, 160, 34);
		panel.add(txtNhpMNhn);
		txtNhpMNhn.setColumns(10);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên:");
		lblTnNhnVin.setBounds(10, 66, 87, 14);
		panel.add(lblTnNhnVin);
		
		JLabel lblSt = new JLabel("Số ĐT:");
		lblSt.setBounds(282, 24, 76, 14);
		panel.add(lblSt);
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setBounds(282, 66, 42, 14);
		panel.add(lblaCh);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setBounds(520, 24, 76, 14);
		panel.add(lblNgySinh);
		
		JLabel lblNgyVoLm = new JLabel("Ngày vào làm");
		lblNgyVoLm.setBounds(520, 66, 76, 14);
		panel.add(lblNgyVoLm);
		
		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setBounds(760, 24, 76, 14);
		panel.add(lblChcV);
		
		JLabel lblTnhTrng = new JLabel("Tình trạng:");
		lblTnhTrng.setBounds(760, 66, 76, 14);
		panel.add(lblTnhTrng);
		
		 cbChuVu = new JComboBox();
		cbChuVu.setBounds(819, 14, 137, 34);
		cbChuVu.addItem("Quản lý");
		cbChuVu.addItem("Nhân viên");
		panel.add(cbChuVu);
		
		 cbTinhTrang = new JComboBox();
		cbTinhTrang.setBounds(819, 58, 137, 30);
		cbTinhTrang.addItem("Đang làm");
		cbTinhTrang.addItem("Đã nghỉ");
		panel.add(cbTinhTrang);
		
		 dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(606, 14, 130, 30);
		panel.add(dcNgaySinh);
		
		 dcNgayVaoLam = new JDateChooser();
		dcNgayVaoLam.setBounds(606, 60, 130, 29);
		panel.add(dcNgayVaoLam);
		
		txtTenNV = new JTextField();
		txtTenNV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtTenNV.getText().equals("Nhập tên nhân viên...")) {
					txtTenNV.setText("");
				}
			}
			
		});
		txtTenNV.setText("Nhập tên nhân viên...");
		txtTenNV.setForeground(new Color(192, 192, 192));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(96, 55, 160, 33);
		panel.add(txtTenNV);
		
		txtSDT = new JTextField();
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
		txtSDT.setBounds(334, 16, 160, 32);
		panel.add(txtSDT);
		
		txtDiaChi = new JTextField();
		txtDiaChi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtDiaChi.getText().equals("Nhập địa chỉ...")) {
					txtDiaChi.setText("");
				}
			}
		});
		txtDiaChi.setText("Nhập địa chỉ...");
		txtDiaChi.setForeground(new Color(192, 192, 192));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(334, 58, 160, 31);
		panel.add(txtDiaChi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 122, 966, 56);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnXoa, "Chức năng đang nâng cấp!!");
			}
		});
		btnXoa.setForeground(new Color(255, 255, 255));
		btnXoa.setBackground(new Color(30, 144, 255));
		btnXoa.setBounds(879, 11, 77, 34);
		panel_1.add(btnXoa);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhanVien nv = restText();
				System.out.println(nv.toString());
				try {
					 int tb = JOptionPane.showConfirmDialog(null, "Bạn  chắc chắn muốn cập nhật nhân viên này không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
			            if (tb == JOptionPane.YES_OPTION) {
			            	if(nvDao.updateNhanVien( nv) ) {
								xoaRongInput();
								
								NhanVienList = (ArrayList<NhanVien>) nvDao.getAllNhanVien();
								renderData(NhanVienList);
								JOptionPane.showMessageDialog(btnCapNhat, "Cập nhật Thành công!!");
							}
			            	else {
			            		JOptionPane.showMessageDialog(btnCapNhat, "Cập nhật Faile!!");
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
		btnCapNhat.setBounds(772, 11, 97, 34);
		panel_1.add(btnCapNhat);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(kiemTraData()){
			            NhanVien nv = restText();
			            try {
							if(nvDao.addNhanVien(nv)){
								 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							        String ns = formatter.format(nv.getNgaySinh());
								  
							        String ngayLam = formatter.format(nv.getNgayVaoLam());
								dftNhanVien.addRow(new Object[]{
							    nv.getMaNhanVien(), nv.getTenNhanVien(),nv.getSdt(), nv.getDiaChi(),
							    ns, ngayLam,nv.getChucVu(),nv.getTinhTrang()
							    });
								
				                    btnLuu.setEnabled(false);
				                    xoaRongInput();
							     JOptionPane.showMessageDialog(btnLuu, "Thêm thành công");
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
		btnLuu.setBounds(685, 11, 77, 34);
		panel_1.add(btnLuu);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColorInput();
				txtNhpMNhn.requestFocus();
				txtNhpMNhn.setEnabled(true);
				btnLuu.setEnabled(true);
				xoaRongInput();
			}
		});
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(30, 144, 255));
		btnThem.setBounds(598, 11, 77, 34);
		panel_1.add(btnThem);
		
		txtSearch = new JTextField();
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				//Nhập mã nhân viên để tìm...
				if(txtSearch.getText().equals("Nhập mã nhân viên để tìm...")) {
					txtSearch.setText("");
					txtSearch.setForeground(new java.awt.Color(26, 25, 25));
				}
			}
		});
		txtSearch.setForeground(new Color(192, 192, 192));
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setText("Nhập mã nhân viên để tìm...");
		txtSearch.setColumns(10);
		txtSearch.setBounds(10, 11, 237, 34);
		panel_1.add(txtSearch);
		
		JButton btnTim = new JButton("Tìm kiếm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String maNV =  txtSearch.getText();
				 ArrayList<NhanVien> arr = searchNV(maNV);
					
					if(arr.size()==0) {
						JOptionPane.showMessageDialog(btnTim, "Không tìm thấy khách hàng!");
					}
					else {
						renderData(arr);
						btnShowDS.setEnabled(true);
					}
			}
		});
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(new Color(30, 144, 255));
		btnTim.setBounds(257, 11, 87, 34);
		panel_1.add(btnTim);
		
		 btnShowDS = new JButton("Hiển thị DS");
		btnShowDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderData(NhanVienList);
				btnShowDS.setEnabled(false);
			}
		});
		btnShowDS.setToolTipText("Hiển thị lại ds");
		btnShowDS.setForeground(Color.WHITE);
		btnShowDS.setBackground(new Color(30, 144, 255));
		btnShowDS.setBounds(354, 11, 97, 34);
		panel_1.add(btnShowDS);
		btnShowDS.setEnabled(false);
		tbNhanVien = new JTable();
		tbNhanVien.setBounds(569, 219, -542, 154);
		add(tbNhanVien);
		
		String[] header = {"Mã nhân viên","Tên nhân viên",
				"SĐT","Địa chỉ","Ngày sinh", "Ngày vào làm", "Chức vụ", "Tình trạng"};
		dftNhanVien = new DefaultTableModel(header, 0);
		tbNhanVien = new JTable(dftNhanVien)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbNhanVien.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		//tbKhachHang.getTable.setFont(new Font("Arial", Font.PLAIN, 14));
		tbNhanVien.setFont(new Font("Arial", Font.PLAIN, 12));
		JScrollPane jps = new  JScrollPane(tbNhanVien);
		tbNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 int row = tbNhanVien.getSelectedRow();
				 
	               if(row != -1){
	                    setColorInput();
	                     txtNhpMNhn.setEnabled(false);
	                     txtNhpMNhn.setText(tbNhanVien.getValueAt(row, 0).toString());
	                    txtTenNV.setText(tbNhanVien.getValueAt(row, 1).toString());
	                    txtSDT.setText(tbNhanVien.getValueAt(row, 2).toString());
	                    txtDiaChi.setText(tbNhanVien.getValueAt(row, 3).toString());
	              
	                    try {
	                        Date ngaySinh=new SimpleDateFormat("yyyy-MM-dd").parse(tbNhanVien.getValueAt(row, 4).toString() );
	                         System.out.println("ngay sinh "+ngaySinh);
	                        Date ngayLam= new SimpleDateFormat("yyyy-MM-dd").parse(tbNhanVien.getValueAt(row, 5).toString() );
	                        
	                        dcNgaySinh.setDate(ngayLam);
	                        dcNgayVaoLam.setDate(ngaySinh);
	                     } catch (Exception ex) {
	                         ex.printStackTrace();
	                    }
	                    if(tbNhanVien.getValueAt(row, 6).toString().toLowerCase().equals("Nhân Viên".toLowerCase())) {
	                    	cbChuVu.setSelectedIndex(1);
	                    }
	                    else {
	                    	cbChuVu.setSelectedIndex(0);
	                    }
	                   
	                    if(tbNhanVien.getValueAt(row, 7).toString().toLowerCase().equals("Đang làm".toLowerCase())) {
	                    	cbTinhTrang.setSelectedIndex(0);
	                    }
	                    else {
	                    	cbTinhTrang.setSelectedIndex(1);
	                    }
	                   
	               }
			}
		});
		
		this.add(jps);
		//jps.setBounds(565, 15, 72, 30);
		
		//JButton btnNewButton_1 = new JButton("New button");
		jps.setBounds(10, 189, 966, 351);
		//add(btnNewButton_1);
		tbNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tbNhanVien.setRowHeight(26);
		
		btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnTim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnShowDS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		
		nvDao = (NhanVienRemote) Naming.lookup("rmi://192.168.1.4:2001/nhanVienRemote");
		
			NhanVienList =  (ArrayList<NhanVien>) nvDao.getAllNhanVien();
			System.out.println(NhanVienList);
			if(NhanVienList!=null) {
				renderData(NhanVienList);
			}
	}
	
	 //set color cho the input
    public void setColorInput(){
         txtDiaChi.setForeground(new java.awt.Color(26, 25, 25));
         txtNhpMNhn.setForeground(new java.awt.Color(26, 25, 25));
         txtSDT.setForeground(new java.awt.Color(26, 25, 25));
         txtTenNV.setForeground(new java.awt.Color(26, 25, 25));
        
         
         
    }
    //xoa rong cac input
    public void xoaRongInput(){
        
    	txtDiaChi.setText("");
    	txtNhpMNhn.setText("");
    	txtTenNV.setText("");
    	txtSDT.setText("");
        
         
    }
    //kiem tra dữ liệu nhập
    public boolean kiemTraData() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd"); 
        String tenNv = txtTenNV.getText().trim();
        String sdt = txtSDT.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        Date dateNgayLam = dcNgayVaoLam.getDate();
        Date dateNgaySinh = dcNgaySinh.getDate();
        Date date = new Date();
        int d1 = Integer.parseInt(formatter.format(dateNgaySinh)); 
        System.out.println("age1: "+d1);
        int d2 = Integer.parseInt(formatter.format(date)); 
        System.out.println("age2: "+d2);
        int age =(d2-d1)/10000;
        System.out.println("age: "+age);
        
        //tên nhân viên 
        if (!(tenNv.length() > 0)) {
            JOptionPane.showMessageDialog(this, "Tên Nhân Viên không được để trống");
            return false;
        }
        if (!(sdt.length() > 0 && sdt.matches("^[0-9]{10}"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại có 10 số");
            return false;
        }
        //Địa chỉ
        if (!(diaChi.length() > 0 )) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
            return false;
        }
        if ((dateNgayLam.getTime() <date.getTime())) {
            JOptionPane.showMessageDialog(this, "Ngày vào làm phải sau ngày hiện tại!");
            return false;
        }
        if (!(age>18)) {
            System.out.println("aggg"+age);
            JOptionPane.showMessageDialog(this, "Nhân Viên Phải Đủ 18 Tuổi Trở Lên!");
            return false;
        }
        return true;
    }
    //xóa model Nhân Viên
    public void xoaModelNV() {
        DefaultTableModel del = (DefaultTableModel) tbNhanVien.getModel();
        del.getDataVector().removeAllElements();
    }
}
