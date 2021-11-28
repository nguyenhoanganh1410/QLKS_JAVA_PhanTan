package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.ChiTietHoaDon;
import entity.HoaDon;

public interface ChiTietHoaDonRemote extends Remote {
	public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) throws RemoteException;
	public boolean capNhatChiTietHoaDon(ChiTietHoaDon chiTietHoaDonDao) throws RemoteException;
	public List<ChiTietHoaDon> layChiTietHoaDon(int maHoaDon) throws RemoteException;
	public List<HoaDon> layHoaDon(int soPhong) throws RemoteException;
}
