package app;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import dao.ChiTietHoaDonDao;
import dao.ChiTietHoaDonRemote;
import dao.HoaDonDao;
import dao.HoaDonRemote;
import dao.KhachHangDao;
import dao.KhachHangRemote;
import dao.MySessionFactory;
import dao.NhanVienDao;
import dao.NhanVienRemote;
import dao.PhongDao;
import dao.PhongRemote;
import dao.TaiKhoanDao;
import dao.TaiKhoanRemote;
import entity.KhachHang;

public class App {
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException{
//		PhongRemote phongRemote = new PhongDao();
//		HoaDonRemote hoaDonRemote = new HoaDonDao();
//		NhanVienRemote nhanVienRemote = new NhanVienDao();
//		ChiTietHoaDonRemote chiTietHoaDonRemote = new ChiTietHoaDonDao();
//		TaiKhoanRemote taiKhoanRemote = new TaiKhoanDao();
//		KhachHangRemote khachHangRemote = new KhachHangDao();
//		LocateRegistry.createRegistry(2014);
//		Naming.bind("rmi://192.168.24.105:2001/phongRemote", phongRemote);
//		Naming.bind("rmi://192.168.24.105:2001/hoaDonRemote", hoaDonRemote);
//		Naming.bind("rmi://192.168.24.105:2001/nhanVienRemote", nhanVienRemote);
//		Naming.bind("rmi://192.168.24.105:2001/chiTietHoaDonRemote", chiTietHoaDonRemote);
//		Naming.bind("rmi://192.168.24.105:2001/taiKhoanRemote", taiKhoanRemote);
//		Naming.bind("rmi://192.168.24.105:2001/khachHangRemote", khachHangRemote);
//		System.out.println("Done!");
//		SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
//		NhanVienRemote productService = new NhanVienDao();
//		System.out.println(productService.getAllNhanVien());
		
		
		SessionFactory sessionFactory = new MySessionFactory().getSessionFactory();
		HoaDonRemote tk = new HoaDonDao();
//		Date date = new Date();
//		 DateFormat dateFormat = null;
//	     dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//	      String nowString = dateFormat.format(date);
//	     
////	      //lay 7 ngay truoc
////	      Calendar calendar = Calendar.getInstance();      
////	        
////	        Date today = calendar.getTime();    
////	        
////	        calendar.add(Calendar.DAY_OF_YEAR, -7);
////	        
////	       
////	        Date date2 = calendar.getTime();
////	        String date2_str = dateFormat.format(date2);
////	        System.out.println(date2_str);
		List<Object[]> list = tk.getHoaDonChuaTT();
		System.out.println(list);
		for(Object[] person : list) {
			int maHD =  (Integer) person[0];
			String ngayDen = (String) person[1];
			String ngayDi = (String) person[2];
			String trangThai = (String) person[3];
			String soCMND = (String) person[4];
			int soPhong =  (Integer) person[5];
			
			System.out.println(maHD + " " + ngayDen + " " + ngayDi + " " +trangThai + " " + soCMND + " " + soPhong + "\n");
		}
		
//		
//		
//    
//    
	}
}
