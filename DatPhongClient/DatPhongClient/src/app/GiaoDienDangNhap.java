package app;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.TaiKhoanRemote;
import entity.TaiKhoan;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
public class GiaoDienDangNhap extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMK;
	private JButton btnLog;
	static TaiKhoanRemote taiKhoanRemote;
	public GiaoDienDangNhap() throws IOException, NotBoundException {
		setTitle("Đăng nhập");
		setSize(320, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		java.net.URL imgURLiconBg = GiaoDienDangNhap.class.getResource("bgLogin.jpg");
		ImageIcon iconBg = validationImage(imgURLiconBg, 320, 350);
        setContentPane(new JLabel(iconBg));
        java.net.URL imgURLicon = GiaoDienDangNhap.class.getResource("loginLogo.jpg");
		ImageIcon icon = validationImage(imgURLicon, 100, 100);
        JLabel logoLogin = new JLabel(icon);
        logoLogin.setBounds(110, 20, 100, 100);
        add(logoLogin);
        JLabel userName = new JLabel("Mã nhân viên:");
        userName.setBounds(50, 140, 80, 30);
        add(userName);
        txtTaiKhoan = new JTextField(10);
        txtTaiKhoan.setBounds(130, 140, 130, 30);
        add(txtTaiKhoan);
        JLabel passWord = new JLabel("Mật khẩu:");
        passWord.setBounds(50, 180, 80, 30);
        add(passWord);
        txtMK = new JPasswordField(10);
        txtMK.setBounds(130, 180, 130, 30);
        add(txtMK);
        btnLog = new JButton("Đăng nhập");
        btnLog.addActionListener(this);
        btnLog.setBounds(100, 230, 130, 40);
        btnLog.setForeground(new Color(255, 255, 255));
        btnLog.setBackground(new Color(30, 144, 255));
        btnLog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(btnLog);
        taiKhoanRemote =  (TaiKhoanRemote) Naming.lookup("rmi://192.168.1.4:2001/taiKhoanRemote");
        Image logo = Toolkit.getDefaultToolkit().getImage("DatPhongClient\\src\\img\\logohotel.png");  
        this.setIconImage(logo); 
	}
	private ImageIcon validationImage(java.net.URL imgURL, int width, int height) {
		ImageIcon image = null;
		 if (imgURL != null) {
			 image = (new ImageIcon(((new ImageIcon(imgURL)).getImage()).getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
	      } else {
	         JOptionPane.showMessageDialog(this, "Icon image not found.");
	      }
		 return image;
	}
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLog)) {
			try {
				List<TaiKhoan> list = new ArrayList<TaiKhoan>();
				list = taiKhoanRemote.layTaiKhoan();
				boolean kiemTraDangNhap = false;
				for (int i = 0; i < list.size(); i++) {
					if(txtTaiKhoan.getText().equals(list.get(i).getTenTaiKhoan().getMaNhanVien()) &&
							String.valueOf(txtMK.getPassword()).equals(list.get(i).getMatKhau()) == true)
					{
						kiemTraDangNhap = true ;
						ShareData.taiKhoanDangNhap = list.get(i);
						ShareData.taiKhoanDangNhap.setTrangThai("online");
						break;
					}
				}
				if(kiemTraDangNhap) {
					GiaoDienDatPhong gdc = new GiaoDienDatPhong(txtTaiKhoan.getText());
					gdc.setVisible(true);
					
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu của bạn không hợp lệ!");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				e1.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws IOException, NotBoundException {
		//set giao dien
		  FlatLightFlatIJTheme.setup();
		new GiaoDienDangNhap().setVisible(true);
	}
}
