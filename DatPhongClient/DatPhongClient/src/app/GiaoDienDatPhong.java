package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDonRemote;
import dao.HoaDonRemote;
import dao.JasperReports_Remote;
import dao.KhachHangRemote;
import dao.NhanVienRemote;
import dao.PhongRemote;

import dao.TaiKhoanRemote;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;
import entity.TaiKhoan;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GiaoDienDatPhong extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel lbUserName;
	private JTextField jtfTenKhachHang;
	private JTextField jtfSoCCCDSoHoChieu;
	private DefaultTableModel dftHoaDon;
	private JTable tbHoaDon;
	private JButton btnTaoHoaDon;
	private JButton btnThanhToanHoaDon;
	private DefaultTableModel dftPhong;
	private JTable tbPhong;
	private JButton btnThemPhongVaoHoaDon;
	private JButton btnDSPhongTrong;
	private JButton btnChiTietHoaDon;
	private JFrame chiTietHoaDon;
	private DefaultTableModel dftChiTietHoaDon;
	private JTable tbChiTietHoaDon;

	private JTextField jtfSoDienThoai;
	private JComboBox<String> jcbLoaiKH;
	private JTextField jtfDiaChi;
	private JButton btnHoaDonChuaThanhToan;
	private JTextField timJTF;
	private JButton btnTimHoaDon;
	private JTextField timKiemPhongJTF;
	private JButton btnTimPhong;
	private JLabel lbMaNhanVien;
	private JButton btnLogOut;
	private JLabel titleDanhSachPhong;
	private JLabel soPhongTrongLabel;
	ChiTietHoaDonRemote chiTietHoaDonDao;
	HoaDonRemote hoaDonDao;
	PhongRemote phongDao;
	TaiKhoanRemote taiKhoanDao;
	NhanVienRemote nhanVienDao;
	KhachHangRemote khachHangDao;
	JasperReports_Remote jspDao;
	JRadioButton hourRent;
	JRadioButton dateRent;
	 ButtonGroup group;
	public GiaoDienDatPhong(String maNhanVien) throws RemoteException, MalformedURLException, NotBoundException {
		setTitle("Luxy Hotel");
		setSize(1000, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	//	setResizable(false);  
		
		
		JPanel userJP = new JPanel();
		userJP.setBorder(new EmptyBorder(5, 5, 5, 5));
		userJP.setLayout(new BorderLayout());
		java.net.URL imgURLiconLogo = GiaoDienDatPhong.class.getResource("loginLogo.jpg");
		ImageIcon iconLogo = this.validationImage(imgURLiconLogo, 40, 40);
		Image sacleLogo = iconLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconSacleLogo = new ImageIcon(sacleLogo);
		JLabel logoLogin = new JLabel(iconSacleLogo);
		
		java.net.URL imgURLiconLogOut = GiaoDienDatPhong.class.getResource("logout.png");
		ImageIcon iconLogOut = this.validationImage(imgURLiconLogOut, 30, 30);
		Image sacleLogOut = iconLogOut.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconSacleLogOut = new ImageIcon(sacleLogOut);
		userJP.add(logoLogin, BorderLayout.WEST);
		
		
		lbUserName = new JLabel();
		
		lbMaNhanVien = new JLabel(maNhanVien);
		
		
		userJP.add(lbUserName, BorderLayout.CENTER);
		btnLogOut = new JButton("????ng xu???t", iconSacleLogOut);
	//	btnLogOut.setBackground(Color.CYAN);
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(30, 144, 255));
		btnLogOut.addActionListener(this);
		btnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		userJP.add(btnLogOut, BorderLayout.EAST);
		
		

		JTabbedPane jtp = new JTabbedPane();
		JPanel hoaDon = new JPanel();
		hoaDon.setLayout(new BorderLayout());
		JPanel nhapThongTinHoaDon = new JPanel();
		//nhapThongTinHoaDon.setBackground(Color.CYAN);
		nhapThongTinHoaDon.setBackground(new Color(248, 248, 255));
		nhapThongTinHoaDon.setLayout(new GridLayout(6, 2, -700, 5));
		nhapThongTinHoaDon.setBorder(new TitledBorder("Th??ng tin h??a ????n m???i"));
		nhapThongTinHoaDon.add(new JLabel("T??n kh??ch h??ng: "));
		jtfTenKhachHang = new JTextField(10);
		nhapThongTinHoaDon.add(jtfTenKhachHang);
		nhapThongTinHoaDon.add(new JLabel("S??? CCCD/S??? h??? chi???u: "));
		jtfSoCCCDSoHoChieu = new JTextField(10);
		nhapThongTinHoaDon.add(jtfSoCCCDSoHoChieu);
		nhapThongTinHoaDon.add(new JLabel("S??? ??i???n tho???i: "));
		
		
		//kiem tra cmnd ???? t???n t??i trong h??? th???ng ch??a
		//n???u KH ???? c?? trong h??? th???ng th??ng b??o c?? mu???n ??k ph??ng cho ng n??y k -> hi???n th??? th??ng tin KH l??n textfield
		jtfSoCCCDSoHoChieu.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		
			 	//JOptionPane.showMessageDialog(nhapThongTinHoaDon, "ok");
			 		try {
						
						String cmnd = jtfSoCCCDSoHoChieu.getText();
						KhachHang kh = khachHangDao.getKhByID(cmnd);
						System.out.println(kh);
						if(kh != null) {
							int tb = JOptionPane.showConfirmDialog(null, "Kh??ch h??ng ???? c?? trong h??? th???ng\n B???n mu???n ????ng k?? ph??ng cho kh??ch h??ng n??y kh??ng?", "C???p nh???t", JOptionPane.YES_NO_OPTION);
				            if (tb == JOptionPane.YES_OPTION) {
				            	jtfTenKhachHang.setText(kh.getTenKhachHang());
				            	jtfDiaChi.setText(kh.getDiaChi());
				            	jtfSoDienThoai.setText(kh.getSoDienThoai());
				            	jtfSoCCCDSoHoChieu.setText(kh.getSoCMND());
				            }
						}
					
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 		
			 	}
			 });
		
		
		
		
		
		
		jtfSoDienThoai = new JTextField(10);
		nhapThongTinHoaDon.add(jtfSoDienThoai);
		nhapThongTinHoaDon.add(new JLabel("Lo???i kh??ch h??ng: "));
		String[] loaiKH = {"Vi????t Nam", "N??????c Ngoa??i"};
		jcbLoaiKH = new JComboBox<String>(loaiKH);
		nhapThongTinHoaDon.add(jcbLoaiKH);
		nhapThongTinHoaDon.add(new JLabel("?????a ch???: "));
		jtfDiaChi = new JTextField(10);
		nhapThongTinHoaDon.add(jtfDiaChi);
		
		JLabel txtKieuThue = new JLabel("Ki???u thu??: ");
		nhapThongTinHoaDon.add(txtKieuThue);
		  hourRent = new JRadioButton("Theo gi???");
	         dateRent = new JRadioButton("Ng??y ????m");
	          group = new ButtonGroup();
	         JPanel jpChoise = new JPanel();
	         jpChoise.setLayout(new GridLayout(1, 4));
	         group.add(hourRent);
	         group.add(dateRent);
	         jpChoise.add(hourRent);
	         jpChoise.add(dateRent);
	         nhapThongTinHoaDon.add(jpChoise);
	         
	         
	         
		hoaDon.add(nhapThongTinHoaDon, BorderLayout.NORTH);
		// table Jtapfield Ho??a ????n
		String[] header = {"M?? phi???u thu?? ph??ng","Kh??ch h??ng",
				"Ng??y ?????n","Ng??y ??i d??? ki???n", "Ki???u thu??","Ph??ng","Tr???ng th??i"};
		dftHoaDon = new DefaultTableModel(header, 0);
		tbHoaDon = new JTable(dftHoaDon)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbHoaDon.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		hoaDon.add(new JScrollPane(tbHoaDon), BorderLayout.CENTER);
		tbHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tbHoaDon.setRowHeight(26);
		
		
		
		
		JPanel thaoTacJPanel = new JPanel();
		thaoTacJPanel.setLayout(new BoxLayout(thaoTacJPanel, BoxLayout.X_AXIS));
		//thaoTacJPanel.setBackground(Color.CYAN);
		thaoTacJPanel.setBackground(new Color(248, 248, 255));
		java.net.URL imgURLHD = GiaoDienDatPhong.class.getResource("taoHoaDon.png");
		ImageIcon themHoaDonIcon = this.validationImage(imgURLHD, 30, 30);
		btnTaoHoaDon = new JButton("T???o h??a ????n", themHoaDonIcon);

		java.net.URL imgURLThanhToanHD = GiaoDienDatPhong.class.getResource("thanhToan.png");
		ImageIcon thanhtoanHoaDonIcon = this.validationImage(imgURLThanhToanHD, 30, 30);
		btnThanhToanHoaDon = new JButton("Thanh to??n", thanhtoanHoaDonIcon);

		java.net.URL imgURLChiTietHD = GiaoDienDatPhong.class.getResource("chiTietHoaDon.jpg");
		ImageIcon chitietHoaDonIcon = this.validationImage(imgURLChiTietHD, 30, 30);
		btnChiTietHoaDon = new JButton("Chi ti???t h??a ????n", chitietHoaDonIcon);


		java.net.URL imgURLchuaThanhToanHD = GiaoDienDatPhong.class.getResource("hoaDonChuaThanhToan.jpg");
		ImageIcon chuaThanhToanHoaDonIcon = this.validationImage(imgURLchuaThanhToanHD, 30, 30);
		btnHoaDonChuaThanhToan = new JButton("H??a ????n ch??a thanh to??n", chuaThanhToanHoaDonIcon);

		timJTF = new JTextField(10);
		java.net.URL imgURLtimKiem = GiaoDienDatPhong.class.getResource("timKiem.jpg");
		ImageIcon TimKiemIcon = this.validationImage(imgURLtimKiem, 30, 30);
		btnTimHoaDon = new JButton(TimKiemIcon);

		btnTaoHoaDon.setForeground(Color.WHITE);
		btnTaoHoaDon.setBackground(new Color(30, 144, 255));
		btnTaoHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		btnThanhToanHoaDon.setForeground(Color.WHITE);
		btnThanhToanHoaDon.setBackground(new Color(30, 144, 255));
		btnThanhToanHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		btnChiTietHoaDon.setForeground(Color.WHITE);
		btnChiTietHoaDon.setBackground(new Color(30, 144, 255));
		btnChiTietHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		btnHoaDonChuaThanhToan.setForeground(Color.WHITE);
		btnHoaDonChuaThanhToan.setBackground(new Color(30, 144, 255));
		btnHoaDonChuaThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		btnTimHoaDon.setForeground(Color.WHITE);
		btnTimHoaDon.setBackground(new Color(30, 144, 255));
		btnTimHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		thaoTacJPanel.add(btnTaoHoaDon);
		thaoTacJPanel.add(btnThanhToanHoaDon);
		thaoTacJPanel.add(btnChiTietHoaDon);
		thaoTacJPanel.add(btnHoaDonChuaThanhToan);
		thaoTacJPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		thaoTacJPanel.add(timJTF);
		thaoTacJPanel.add(btnTimHoaDon);
		hoaDon.add(thaoTacJPanel, BorderLayout.SOUTH);

		JPanel phong = new JPanel();
		phong.setLayout(new BorderLayout());
		JPanel danhSachPhong = new JPanel();
		//danhSachPhong.setBackground(Color.CYAN);
		danhSachPhong.setBackground(new Color(248, 248, 255));
		danhSachPhong.setLayout(new BorderLayout());
		danhSachPhong.setBorder(new EmptyBorder(5, 5, 5, 5));
		titleDanhSachPhong = new JLabel("DANH S??CH PH??NG", SwingConstants.CENTER);
		soPhongTrongLabel = new JLabel();
		titleDanhSachPhong.setFont(new Font("Arial", Font.BOLD, 32));
		danhSachPhong.add(titleDanhSachPhong, BorderLayout.CENTER);
		danhSachPhong.add(soPhongTrongLabel, BorderLayout.SOUTH);

		String[] headerPhong = {"S??? ph??ng","Lo???i ph??ng","Tr???ng th??i", "Lo???i gi?????ng", "T???ng", "Gi?? ph??ng gi???", "Gi?? ph??ng ng??y ????m"};
		dftPhong = new DefaultTableModel(headerPhong, 0);
		tbPhong = new JTable(dftPhong)
		{
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tbPhong.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));
		tbHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		tbHoaDon.setRowHeight(26);
		phong.add(new JScrollPane(tbPhong), BorderLayout.CENTER);
		phong.add(danhSachPhong, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setBackground(Color.CYAN);

		java.net.URL imgURLthemVaoHD = GiaoDienDatPhong.class.getResource("themVaoHoaDon.png");
		ImageIcon themVaoHDIcon = this.validationImage(imgURLthemVaoHD, 30, 30);
		btnThemPhongVaoHoaDon = new JButton("Th??m ph??ng v??o h??a ????n", themVaoHDIcon);

		java.net.URL imgURLphongTrong = GiaoDienDatPhong.class.getResource("phongTrong.png");
		ImageIcon phongTrongIcon = this.validationImage(imgURLphongTrong, 30, 30);
		btnDSPhongTrong = new JButton("Danh s??ch ph??ng tr???ng", phongTrongIcon);

		timKiemPhongJTF = new JTextField(10);
		btnTimPhong = new JButton(TimKiemIcon);

		buttonPanel.add(btnThemPhongVaoHoaDon);
		buttonPanel.add(btnDSPhongTrong);
		buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		buttonPanel.add(timKiemPhongJTF);
		buttonPanel.add(btnTimPhong);
		phong.add(buttonPanel, BorderLayout.SOUTH);

		java.net.URL imgURLicon = GiaoDienDatPhong.class.getResource("hoaDon.jpg");
		ImageIcon icon = this.validationImage(imgURLicon, 30, 30);
		Image sacle = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconSacle = new ImageIcon(sacle);

		java.net.URL imgURLiconPhong = GiaoDienDatPhong.class.getResource("phong.jpg");
		ImageIcon iconPhong = this.validationImage(imgURLiconPhong, 30, 30);
		Image saclePhong = iconPhong.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon iconSaclePhong = new ImageIcon(saclePhong);
		
//		java.net.URL imgURLiconKH = GiaoDienDatPhong.class.getResource("img\\customer.png");
//		ImageIcon iconKH = this.validationImage(imgURLiconKH, 30, 30);
//		Image sacleKH = iconKH.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//		ImageIcon iconSacleKH = new ImageIcon(sacleKH);
		Image iconKH = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\people.png"); 
		ImageIcon iconSacleKH = new ImageIcon(iconKH);
		
		Image iconTK = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\user.png"); 
		ImageIcon iconSacleTK = new ImageIcon(iconTK);
		Image iconNV = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\participant.png"); 
		ImageIcon iconSacleNV = new ImageIcon(iconNV);
		
		Image iconHD = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\invoice.png"); 
		ImageIcon iconSacleHD = new ImageIcon(iconHD);
		
		jtp.addTab("?????t Ph??ng", iconSacle, hoaDon);
		jtp.addTab("Ch???n Ph??ng",iconSaclePhong, phong);
		jtp.addTab("Kh??ch H??ng",iconSacleKH, new FrmKhachHang());
		
		Image iconRoom = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\bedroom.png"); 
		ImageIcon iconSacleRoom = new ImageIcon(iconRoom);
		
		Image iconThongke = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\analytics.png"); 
		ImageIcon iconSacleThongKe = new ImageIcon(iconThongke);
		if(ShareData.taiKhoanDangNhap.getTenQuyen().toLowerCase().equals("Qu???n l??".toLowerCase())) {
			jtp.addTab("Nh??n Vi??n",iconSacleNV, new FrmNhanVien());
			jtp.addTab("H??a ????n",iconSacleHD, new FrmHoaDon());
			jtp.addTab("Ph??ng",iconSacleRoom, new FrmPhong());
			jtp.addTab("T??i Kho???n",iconSacleTK, new FrmTaiKhoan());
			jtp.addTab("Th???ng k??",iconSacleThongKe, new FrmThongKe());
		}
		
		
		chiTietHoaDon = new JFrame();
		chiTietHoaDon.setSize(500, 400);
		chiTietHoaDon.setTitle("Chi Ti???t H??a ????n");
		chiTietHoaDon.setLocationRelativeTo(null);
		chiTietHoaDon.setVisible(false);
		String[] headerChiTietHoaDon = {"M?? phi???u thu?? ph??ng", "S??? ph??ng", "Th???i gian s??? d???ng", "Ti???n ph??ng t???m t??nh"};
		dftChiTietHoaDon = new DefaultTableModel(headerChiTietHoaDon, 0);
		tbChiTietHoaDon = new JTable(dftChiTietHoaDon);
		chiTietHoaDon.add(new JScrollPane(tbChiTietHoaDon));
		//????ng ky?? s???? ki????n
		btnThemPhongVaoHoaDon.addActionListener(this);
		btnTimPhong.addActionListener(this);
		btnDSPhongTrong.addActionListener(this);
		btnTimHoaDon.addActionListener(this);
		btnHoaDonChuaThanhToan.addActionListener(this);
		btnThanhToanHoaDon.addActionListener(this);
		btnTaoHoaDon.addActionListener(this);
		btnChiTietHoaDon.addActionListener(this);

		add(jtp, BorderLayout.CENTER);
		add(userJP, BorderLayout.NORTH);
		phongDao =  (PhongRemote) Naming.lookup("rmi://192.168.1.4:2001/phongRemote");
		hoaDonDao =  (HoaDonRemote) Naming.lookup("rmi://192.168.1.4:2001/hoaDonRemote");
		chiTietHoaDonDao =  (ChiTietHoaDonRemote) Naming.lookup("rmi://192.168.1.4:2001/chiTietHoaDonRemote");
		nhanVienDao =  (NhanVienRemote) Naming.lookup("rmi://192.168.1.4:2001/nhanVienRemote");
		khachHangDao =  (KhachHangRemote) Naming.lookup("rmi://192.168.1.4:2001/khachHangRemote");
		taiKhoanDao =  (TaiKhoanRemote) Naming.lookup("rmi://192.168.1.4:2001/taiKhoanRemote");
		jspDao=  (JasperReports_Remote) Naming.lookup("rmi://192.168.1.4:2001/jspRemote");
		
		 Image logo = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\logohotel.png");  
	        this.setIconImage(logo);
		
		lbUserName.setText(nhanVienDao.layTenNhanVien(lbMaNhanVien.getText()) + " - " + ShareData.taiKhoanDangNhap.getTenQuyen());
		soPhongTrongLabel.setText("S??? ph??ng tr???ng: " + phongDao.laySoPhongChuaSuDung());
		
		//su kien dong frame
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
               //thay doi trang thai tai khoan thanh offline
            	ShareData.taiKhoanDangNhap.setTrangThai("offline");
            }
        });
		
		renderHoaDonChuaThanhToan();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		Object o = e.getSource();
		DecimalFormat df = new DecimalFormat("#,000 VND");
		if(o.equals(btnTaoHoaDon))
		{
			if(jtfTenKhachHang.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "B???n ch??a nh???p t??n kh??ch h??ng!");
				jtfTenKhachHang.requestFocus();
			}
			else if(jtfSoCCCDSoHoChieu.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "B???n ch??a nh???p s??? CMND/H??? chi???u kh??ch h??ng!");
				jtfSoCCCDSoHoChieu.requestFocus();
			}
			else if(jtfDiaChi.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "B???n ch??a nh???p ?????a ch??? kh??ch h??ng!");
				jtfDiaChi.requestFocus();
			}
			else if(dateRent.isSelected() == false && hourRent.isSelected() == false) {
				JOptionPane.showMessageDialog(null, "B???n ch??a ch???n ki???u thu??!");
			}
			else
			{
				List<KhachHang> khachHangList = new ArrayList<KhachHang>();
				try {
					khachHangList = khachHangDao.layDanhSachKhachHang();
					String tenKhachHang = jtfTenKhachHang.getText();
					String soCCCDSoHoChieu =jtfSoCCCDSoHoChieu.getText();
					String soDT = jtfSoDienThoai.getText();
					String loaiKH = (String) jcbLoaiKH.getSelectedItem();
					String diaChiKH = jtfDiaChi.getText();
					Date thoiGianDenDate = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String thoiGianDen = format.format(thoiGianDenDate);
					
					//them hoadon vao table
					Locale localeVN = new Locale("vi", "VN");
				    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
				    //String str1 = currencyVN.format(vnd);
				    
				    //kiem tra thue theo gio hay ngaydem
				    String kieuThue  = "";
				    if(dateRent.isSelected()) {
				    	kieuThue="ng??y ????m";
				    	// get a calendar instance, which defaults to "now"
				        Calendar calendar = Calendar.getInstance();
				        
				        // get a date to represent "today"
				        Date today = calendar.getTime();
				        
				     
				        // add one day to the date/calendar
				        calendar.add(Calendar.DAY_OF_YEAR, 1);
				        
				        // now get "tomorrow"
				        Date tomorrow = calendar.getTime();
				        String tomorrowString = format.format(tomorrow);
				        // print out tomorrow's date
				    	Object[] hoaDon = {hoaDonDao.laySoHoaDon() + 1, tenKhachHang, 
				    			thoiGianDen, tomorrowString,kieuThue,"Ch??a ch???n ph??ng","Ch??a thanh to??n"};
				    	dftHoaDon.addRow(hoaDon);
					    
						KhachHang khachHangMoi = new KhachHang(tenKhachHang, soCCCDSoHoChieu, soDT, loaiKH, diaChiKH);
						boolean exitKhachHang = false;
						
						if(khachHangList != null)
						{
							for (int i = 0; i < khachHangList.size(); i++) {
								if(khachHangList.get(i).getSoCMND().toString().equalsIgnoreCase(jtfSoCCCDSoHoChieu.getText().toString()) == true)
								{
									exitKhachHang = true;
								}
							}
							if(exitKhachHang == false)
							{
								khachHangDao.themKhachHang(khachHangMoi);
							}
						}
						else
						{
							khachHangDao.themKhachHang(khachHangMoi);
						}
						
						NhanVien nhanVien = new NhanVien(lbMaNhanVien.getText());
						KhachHang khachHang = new KhachHang(soCCCDSoHoChieu);
						
						HoaDon hoaDonMoi = new HoaDon(hoaDonDao.laySoHoaDon() + 1, thoiGianDen, tomorrowString, "Ch??a thanh to??n", 0.0, khachHang, nhanVien, kieuThue);
						hoaDonDao.themHoaDon(hoaDonMoi);
				    }
				    else {
				    	kieuThue="theo gi???";
				    	Object[] hoaDon = {hoaDonDao.laySoHoaDon() + 1, tenKhachHang, 
				    			thoiGianDen, null,kieuThue, "Ch??a ch???n ph??ng","Ch??a thanh to??n"};
				    	dftHoaDon.addRow(hoaDon);
					    
						KhachHang khachHangMoi = new KhachHang(tenKhachHang, soCCCDSoHoChieu, soDT, loaiKH, diaChiKH);
						boolean exitKhachHang = false;
						
						if(khachHangList != null)
						{
							for (int i = 0; i < khachHangList.size(); i++) {
								if(khachHangList.get(i).getSoCMND().toString().equalsIgnoreCase(jtfSoCCCDSoHoChieu.getText().toString()) == true)
								{
									exitKhachHang = true;
								}
							}
							if(exitKhachHang == false)
							{
								khachHangDao.themKhachHang(khachHangMoi);
							}
						}
						else
						{
							khachHangDao.themKhachHang(khachHangMoi);
						}
						
						NhanVien nhanVien = new NhanVien(lbMaNhanVien.getText());
						KhachHang khachHang = new KhachHang(soCCCDSoHoChieu);
						
						HoaDon hoaDonMoi = new HoaDon(hoaDonDao.laySoHoaDon() + 1, thoiGianDen, "", "Ch??a thanh to??n", 0.0, khachHang, nhanVien, kieuThue);
						hoaDonDao.themHoaDon(hoaDonMoi);
				    }
				    
			
					JOptionPane.showMessageDialog(null, "T???o h??a ????n th??nh c??ng!");
					
					jtfTenKhachHang.setText("");
					jtfSoCCCDSoHoChieu.setText("");
					jtfDiaChi.setText("");
					jtfSoDienThoai.setText("");
					jtfTenKhachHang.requestFocus();
					
					
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if (o.equals(btnChiTietHoaDon)) {
			while(tbChiTietHoaDon.getRowCount() > 0)
			{
				dftChiTietHoaDon.removeRow(0);
			}
			if(tbHoaDon.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "B???n ch??a ch???n h??a ????n c???n xem!");
				
			}
			else
			{
				try {
					Locale localeVN = new Locale("vi", "VN");
				    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
				    //String str1 = currencyVN.format(vnd);
				    String thoiGianSuDungString = "";
				    String gio = "";
				    String phut = "";
					try {
						//System.out.println((int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0) + 1);
						String thoiGianDen = hoaDonDao.layNgayDen((int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0));
						Date thoiGianDi = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date thoiGianDenDate;
						thoiGianDenDate = format.parse(thoiGianDen);
						
						String thoiGianDiString = format.format(thoiGianDi);
						
						
						long tongGio = (thoiGianDi.getTime() - thoiGianDenDate.getTime()) / (60 * 1000);
						long thoiGianSuDung = (thoiGianDi.getTime() - thoiGianDenDate.getTime()) / 1000;
						 gio = Long.toString(thoiGianSuDung / 3600);
						 phut = Long.toString(thoiGianSuDung % 3600 / 60);
						String giay = Long.toString(thoiGianSuDung % 3600 % 60);
						 thoiGianSuDungString = gio + ":" + phut + ":" + giay;
						 
						 
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
				    
					
					List<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
					int maHoaDon = (int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0);
					
					listChiTietHoaDon = chiTietHoaDonDao.layChiTietHoaDon(maHoaDon);
					System.out.println(listChiTietHoaDon);
					if(listChiTietHoaDon.size() == 0) {
						JOptionPane.showMessageDialog(tbHoaDon, "Ch??a ch???n ph??ng cho kh??ch h??ng n??y!");
					}
					else {
						chiTietHoaDon.setVisible(true);

						if (listChiTietHoaDon != null) {
							//kiem tra thue phong theo gio hay ngaydem
							if( dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 4).toString().toLowerCase().equals("theo gi???".toLowerCase())  ) {
								for (int i = 0; i < listChiTietHoaDon.size(); i++) {
									Double giaPhong = listChiTietHoaDon.get(i).getPhongID().getGiaPhong();
									int hour = Integer.parseInt(gio);
									if(Integer.parseInt(phut) > 30) {
										hour = Integer.parseInt(gio) + 1;
									}
									else if(Integer.parseInt(phut) < 30 && Integer.parseInt(gio) == 0) {
										hour = 1;
									}
									
									Double tienPhongTamThoi = giaPhong * hour;
									
									Object[] chiTietHD = {listChiTietHoaDon.get(i).getHoaDonID().getMaHoaDon(), listChiTietHoaDon.get(i).getPhongID().getSoPhong(),
											thoiGianSuDungString, currencyVN.format(tienPhongTamThoi)};
									dftChiTietHoaDon.addRow(chiTietHD);
								}
								
							}
							else {
								for (int i = 0; i < listChiTietHoaDon.size(); i++) {
									Double giaPhong = listChiTietHoaDon.get(i).getPhongID().getGiaPhongDem();
				
									
									Object[] chiTietHD = {listChiTietHoaDon.get(i).getHoaDonID().getMaHoaDon(), listChiTietHoaDon.get(i).getPhongID().getSoPhong(),
											thoiGianSuDungString, currencyVN.format(giaPhong)};
									dftChiTietHoaDon.addRow(chiTietHD);
							}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Kh??ng c?? ph??ng n??o trong h??a ????n n??y!");
						}
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if(o.equals(btnThanhToanHoaDon))
		{
			if(tbHoaDon.getSelectedRow() == -1)
			{
				JOptionPane.showMessageDialog(null, "B???n ch??a ch???n h??a ????n c???n thanh to??n!");
				return;
			}
			if(dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 4).equals("???? thanh to??n"))
			{
				JOptionPane.showMessageDialog(null, "H??a ????n n??y ???? thanh to??n!");
				return;
			}
			else
			{
				try {
					double tongTien = 0;
					int row = tbHoaDon.getSelectedRow();
					
					
					
					String thoiGianDen = hoaDonDao.layNgayDen((int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0));
					Date thoiGianDi = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date thoiGianDenDate = format.parse(thoiGianDen);
					String thoiGianDiString = format.format(thoiGianDi);
					
					
					long tongGio = (thoiGianDi.getTime() - thoiGianDenDate.getTime()) / (60 * 1000);
					long thoiGianSuDung = (thoiGianDi.getTime() - thoiGianDenDate.getTime()) / 1000;
					String gio = Long.toString(thoiGianSuDung / 3600);
					String phut = Long.toString(thoiGianSuDung % 3600 / 60);
					String giay = Long.toString(thoiGianSuDung % 3600 % 60);
					String thoiGianSuDungString = gio + ":" + phut + ":" + giay;
					
					
					List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
					dsChiTietHoaDon = chiTietHoaDonDao.layChiTietHoaDon((int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0));
					
					List<Phong> dsPhong = phongDao.layPhong();
					
					
					if (dsChiTietHoaDon != null) {
							for (int i = 0; i < dsChiTietHoaDon.size(); i++) {
								HoaDon hoaDon = new HoaDon((int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0));
								Phong phong = new Phong(dsChiTietHoaDon.get(i).getPhongID().getSoPhong());
								
								//neu phong la dk theo gio
								if( dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 4).toString().toLowerCase().equals("theo gi???".toLowerCase()) ){
									Double giaPhong = dsChiTietHoaDon.get(i).getPhongID().getGiaPhong();
									int hour = Integer.parseInt(gio);
									if(Integer.parseInt(phut) > 30) {
										hour = Integer.parseInt(gio) + 1;
									}
									else if(Integer.parseInt(phut) < 30 && Integer.parseInt(gio) == 0) {
										hour = 1;
									}
									
									Double tienPhong= giaPhong * hour;
									
									
									ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, phong, thoiGianSuDungString, tienPhong);
									
									tongTien+=tienPhong;
									Phong phongCapNhat = phongDao.timPhong(dsChiTietHoaDon.get(i).getPhongID().getSoPhong());
									phongCapNhat.setTrangThai("Ch??a s??? d???ng");
									phongDao.capNhatPhong(phongCapNhat);
									chiTietHoaDonDao.capNhatChiTietHoaDon(chiTietHoaDon);
									
								}
								else {//nau phong dk theo ngay dem
									Double giaPhong = dsChiTietHoaDon.get(i).getPhongID().getGiaPhongDem();
									tongTien+=giaPhong;
									ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, phong, thoiGianSuDungString, giaPhong);
									Phong phongCapNhat = phongDao.timPhong(dsChiTietHoaDon.get(i).getPhongID().getSoPhong());
									phongCapNhat.setTrangThai("Ch??a s??? d???ng");
									phongDao.capNhatPhong(phongCapNhat);
									chiTietHoaDonDao.capNhatChiTietHoaDon(chiTietHoaDon);
								}
							}

							
							dftHoaDon.setValueAt("???? thanh to??n", row, 6);
							dftHoaDon.setValueAt(thoiGianDiString, row, 3);
						
							JOptionPane.showMessageDialog(null, "Thanh to??n h??a ????n th??nh c??ng!");
							while(tbPhong.getRowCount() > 0)
							{
								dftPhong.removeRow(0);
							}
							for (int i = 0; i < dsPhong.size(); i++) {
								Object[] phongTrong = {dsPhong.get(i).getSoPhong(), dsPhong.get(i).getLoaiPhong(), dsPhong.get(i).getTrangThai(),
										dsPhong.get(i).getLoaiGiuong(), dsPhong.get(i).getSoTang(), dsPhong.get(i).getGiaPhong(), dsPhong.get(i).getGiaPhongDem()};
								dftPhong.addRow(phongTrong);
							}
							
							HoaDon hoaDonCapNhat = hoaDonDao.timHoaDonBangMaHoaDon((int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0));
							hoaDonCapNhat.setNgayDi(thoiGianDiString);
							hoaDonCapNhat.setTongTien(tongTien);
							hoaDonCapNhat.setTrangThai("???? thanh to??n");
							hoaDonDao.capNhatHoaDon(hoaDonCapNhat);
							
							soPhongTrongLabel.setText("S??? ph??ng tr???ng: " + phongDao.laySoPhongChuaSuDung());
							
							//in hoa don 
						
						String maHD = dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0 ).toString();
						jspDao.printBill( Integer.parseInt(maHD));
					}
					else{
						JOptionPane.showMessageDialog(null, "Kh??ng c?? ph??ng n??o trong h??a ????n n??y!");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		}
		else if(o.equals(btnThemPhongVaoHoaDon))
		{
			try {
				int row = tbPhong.getSelectedRow();
				int soPhong = (int) dftPhong.getValueAt(row, 0);
				if(tbHoaDon.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "B???n ch??a ch???n h??a ????n c???n th??m ph??ng!");
					return;
				}
				if(dftPhong.getValueAt(row, 2).equals("??ang s??? d???ng"))
				{
					JOptionPane.showMessageDialog(this,"Ph??ng ??ang s??? d???ng");
					return;
				}
				if(((String) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 4)).equalsIgnoreCase("???? thanh to??n")) {
					JOptionPane.showMessageDialog(this,"Ho??a ????n na??y ??a?? ????????c thanh toa??n");
					return;
				}
				dftPhong.setValueAt("??ang s??? d???ng", row, 2);
				
				
				
				
				
				Phong phongCapNhat;
				phongCapNhat = phongDao.timPhong(soPhong);
				phongCapNhat.setTrangThai("??ang s??? d???ng");
				phongDao.capNhatPhong(phongCapNhat);
				HoaDon maHoaDon = new HoaDon((int) dftHoaDon.getValueAt(tbHoaDon.getSelectedRow(), 0));
				Phong phongID = new Phong((int) dftPhong.getValueAt(tbPhong.getSelectedRow(), 0));
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maHoaDon, phongID, "", 0);
				chiTietHoaDonDao.themChiTietHoaDon(chiTietHoaDon);
				dftPhong.removeRow(tbPhong.getSelectedRow());
				soPhongTrongLabel.setText("S??? ph??ng tr???ng: " + phongDao.laySoPhongChuaSuDung());
				
				  int rowKH = tbHoaDon.getSelectedRow();
				  System.out.println(rowKH);
				  tbHoaDon.setValueAt(soPhong, rowKH, 5);
				  
				JOptionPane.showMessageDialog(null, "Th??m ph??ng v??o h??a ????n th??nh c??ng!");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		else if(o.equals(btnDSPhongTrong))
		{
			try {
				while(tbPhong.getRowCount() > 0)
				{
					dftPhong.removeRow(0);
				}
				List<Phong> dsPhong;
				dsPhong = phongDao.layPhong();
				for (int i = 0; i < dsPhong.size(); i++) {
					Object[] phongTrong = {dsPhong.get(i).getSoPhong(), dsPhong.get(i).getLoaiPhong(),
							dsPhong.get(i).getTrangThai(), dsPhong.get(i).getLoaiGiuong(),
							dsPhong.get(i).getSoTang(), df.format(dsPhong.get(i).getGiaPhong()),
							 df.format(dsPhong.get(i).getGiaPhongDem())};
					dftPhong.addRow(phongTrong);
				}
				if(dsPhong.size() == 0)
					JOptionPane.showMessageDialog(null, "Kh??ng c??n ph??ng tr???ng!");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		else if(o.equals(btnHoaDonChuaThanhToan))
		{
			if(renderHoaDonChuaThanhToan());
			else
			{
				JOptionPane.showMessageDialog(null, "Kh??ng c?? h??a ????n ch??a thanh to??n!");
			}
		}
		else if (o.equals(btnTimHoaDon)) {
			try {
				List<HoaDon> hoaDonList;
				hoaDonList = chiTietHoaDonDao.layHoaDon(Integer.parseInt(timJTF.getText()));
				HoaDon hoaDonTim = null;
				for (HoaDon hoaDon : hoaDonList) {
					hoaDonTim = hoaDon;
				}
				if(hoaDonTim == null)
					JOptionPane.showMessageDialog(null, "H??a ????n kh??ng t???n t???i!");
				else
				{
					while (tbHoaDon.getRowCount() > 0) {
						dftHoaDon.removeRow(0);
					}
					Object[] hoaDonTimHienThi = {hoaDonTim.getMaHoaDon(), khachHangDao.layTenKhachHang(hoaDonTim.getKhachHangID().getSoCMND()),
							hoaDonTim.getNgayDen(), hoaDonTim.getNgayDi(), hoaDonTim.getTrangThai(), hoaDonTim.getTongTien()};
					dftHoaDon.addRow(hoaDonTimHienThi);
				}
			} catch (NumberFormatException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (o.equals(btnTimPhong)) {
			if(!kiemTraSoNguyen(timKiemPhongJTF.getText()))
				hienThiThongBaoLoiNhapSoNguyen(timKiemPhongJTF);
			else
			{
				try {
					Phong phongTim;
					phongTim = phongDao.timPhong(Integer.parseInt(timKiemPhongJTF.getText()));
					if(phongTim == null)
						JOptionPane.showMessageDialog(null, "S??? ph??ng kh??ng t???n t???i!");
					else
					{
						while (tbPhong.getRowCount() > 0) {
							dftPhong.removeRow(0);
						}
						Object[] phongTimHienThi = {phongTim.getSoPhong(), phongTim.getLoaiPhong(),
								phongTim.getTrangThai(), phongTim.getLoaiGiuong(), phongTim.getSoTang(), df.format(phongTim.getGiaPhong())};
						dftPhong.addRow(phongTimHienThi);
					}
				}
				catch (NumberFormatException | RemoteException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if(o.equals(btnLogOut))
		{
			dispose();
			try {
				new GiaoDienDangNhap().setVisible(true);
				ShareData.taiKhoanDangNhap.setTrangThai("offline");
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	private ImageIcon validationImage(java.net.URL imgURL, int width, int height) {
		ImageIcon image = null;
		if (imgURL != null) {
			image = (new ImageIcon(((new ImageIcon(imgURL)).getImage()).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
		} else {
			JOptionPane.showMessageDialog(this, "Kh??ng t??m th???y ???nh!");
		}
		return image;
	}
	public boolean kiemTraSoNguyen(String text) {
		boolean kiemTra = true;
		try {
			Integer.parseInt(text);
		} catch (Exception e) {
			kiemTra = false;
		}
		return kiemTra;
	}
	public void hienThiThongBaoLoiNhapSoNguyen(JTextField jtf) {
		JOptionPane.showMessageDialog(null, "B???n nh???p sai d??? li???u! D??? li???u b???n c???n nh???p ph???i l?? s???!");
		jtf.selectAll();
		jtf.requestFocus();
	}
	
	public boolean renderHoaDonChuaThanhToan() {
		
		try {
			Locale localeVN = new Locale("vi", "VN");
		    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		    //String str1 = currencyVN.format(vnd);
			while(tbHoaDon.getRowCount() > 0)
			{
				dftHoaDon.removeRow(0);
			}
			//List<HoaDon> list = new ArrayList<HoaDon>();
			//list = hoaDonDao.layHoaDonChuaThanhToan();
			List<Object[]> list = hoaDonDao.getHoaDonChuaTT();
			if(list.size() > 0)
			{
				
				for(Object[] person : list) {
					int maHD =  (int) person[0];
					String ngayDen = (String) person[1];
					String ngayDi = (String) person[2];
					String trangThai = (String) person[3];
					String soCMND = (String) person[4];
					int soPhong =  (Integer) person[5];
					String kieuThue = (String) person[6];
					Object[] hoaDonChuaThanhToan = {(maHD),soCMND,ngayDen,
							ngayDi, kieuThue, (soPhong), trangThai };
					dftHoaDon.addRow(hoaDonChuaThanhToan);
				}
				
//				for (int i = 0; i < list.size(); i++) {
//					Object[] hoaDonChuaThanhToan = {list.get(i).getMaHoaDon(), list.get(i).getKhachHangID().getTenKhachHang(),
//							list.get(i).getNgayDen(), list.get(i).getNgayDi(),list.get(i).getKieuThue(),"Ch??a ch???n ph??ng", list.get(i).getTrangThai() };
//					dftHoaDon.addRow(hoaDonChuaThanhToan);
//				}
				return true;
			}
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		return false;
	}
	
	public void khachCach2MocTG(String dateStart, String dateStop) {
//		   String dateStart = "2012-03-14 09:33:58";
//
//	        String dateStop = "2012-03-14 10:34:59";

	        // Custom date format

	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        Date d1 = null;

	        Date d2 = null;

	        try {

	            d1 = format.parse(dateStart);

	            d2 = format.parse(dateStop);

	        } catch (ParseException e) {

	        }

	        // Get msec from each, and subtract.

	        long diff = d2.getTime() - d1.getTime();

	        long diffSeconds = diff / 1000;

	        long diffMinutes = diff / (60 * 1000);

	        long diffHours = diff / (60 * 60 * 1000);

	        System.out.println("S??? gi??y : " + diffSeconds + " seconds.");

	        System.out.println("S??? ph??t: " + diffMinutes + " minutes.");

	        System.out.println("S??? gi???: " + diffHours + " hours.");
	}
//	
//	  //T???o h??m xu???t h??a ????n
//    public void printBill(int maHD){
//        try {
//            
//            Hashtable map = new Hashtable();
//            JasperReport report = JasperCompileManager.compileReport("C:\\Users\\GMT\\Desktop\\phanTan\\DatPhongClient\\DatPhongClient\\src\\app\\HoaDonLuxury.jrxml");
//            
//            map.put("MaHD", maHD);
//                  
//            JasperPrint p = JasperFillManager.fillReport(report,  map );
//            JasperViewer.viewReport(p, false);
//           // JasperExportManager.exportReportToPdfFile(p, "test.pdf");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//           
//        }
//    }
}
