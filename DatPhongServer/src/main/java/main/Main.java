package main;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import dao.ChiTietHoaDonDao;
import dao.ChiTietHoaDonRemote;
import dao.HoaDonDao;
import dao.HoaDonRemote;
import dao.JasperReportDao;
import dao.JasperReports_Remote;
import dao.KhachHangDao;
import dao.KhachHangRemote;
import dao.NhanVienDao;
import dao.NhanVienRemote;
import dao.PhongDao;
import dao.PhongRemote;
import dao.TaiKhoanDao;
import dao.TaiKhoanRemote;

public class Main {
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy", "lib/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		PhongRemote phongRemote = new PhongDao();
		HoaDonRemote hoaDonRemote = new HoaDonDao();
		NhanVienRemote nhanVienRemote = new NhanVienDao();
		ChiTietHoaDonRemote chiTietHoaDonRemote = new ChiTietHoaDonDao();
		TaiKhoanRemote taiKhoanRemote = new TaiKhoanDao();
		KhachHangRemote khachHangRemote = new KhachHangDao();
		JasperReports_Remote jspRemote = new JasperReportDao();
		LocateRegistry.createRegistry(2001);
	
		Naming.bind("rmi://192.168.1.4:2001/phongRemote", phongRemote);
		Naming.bind("rmi://192.168.1.4:2001/hoaDonRemote", hoaDonRemote);
		Naming.bind("rmi://192.168.1.4:2001/nhanVienRemote", nhanVienRemote);
		Naming.bind("rmi://192.168.1.4:2001/chiTietHoaDonRemote", chiTietHoaDonRemote);
		Naming.bind("rmi://192.168.1.4:2001/taiKhoanRemote", taiKhoanRemote);
		Naming.bind("rmi://192.168.1.4:2001/khachHangRemote", khachHangRemote);
		Naming.bind("rmi://192.168.1.4:2001/jspRemote", jspRemote);
		System.out.println("Done!");
	}
}
