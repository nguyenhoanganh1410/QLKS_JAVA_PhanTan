package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.KhachHang;

public interface KhachHangRemote extends Remote {
	public List<KhachHang> layDanhSachKhachHang() throws RemoteException;

	public void themKhachHang(KhachHang khachHang) throws RemoteException;

	public String layTenKhachHang(String maKhachHang) throws RemoteException;
	
	public KhachHang getKhByID(String maKhachHang) throws RemoteException;
}
