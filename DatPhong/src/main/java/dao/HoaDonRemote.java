package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.HoaDon;

public interface HoaDonRemote extends Remote {
	public boolean themHoaDon(HoaDon hoaDon) throws RemoteException;

	public boolean capNhatHoaDon(HoaDon hoaDon) throws RemoteException;

	public HoaDon timHoaDonBangMaHoaDon(int maHoaDon) throws RemoteException;

	public String layNgayDen(int maHoaDon) throws RemoteException;

	public List<HoaDon> layHoaDonChuaThanhToan() throws RemoteException;

	public HoaDon timHoaDonTheoMa(int maHoaDon) throws RemoteException;

	public Integer laySoHoaDon() throws RemoteException;
	
	public List<HoaDon> layDsHoaDon() throws RemoteException;
	
	public List<Object[]> ThongKeKH() throws RemoteException;
	public List<Object[]> ThongKePhongTheoNgay(String query) throws RemoteException;
	
	public List<Object[]> ThongKePhongTheoHaiMocTG(String date1, String date2) throws RemoteException;
	
	//thong ke doah thu trong 30 ngay gan day
	public List<Object[]> ThongKePhongDoanhThuTheoNgay() throws RemoteException;
	
	public List<Object[]> getHoaDonChuaTT() throws RemoteException;
}
