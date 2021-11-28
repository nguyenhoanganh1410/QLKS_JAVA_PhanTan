package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.TaiKhoan;

public interface TaiKhoanRemote extends Remote {
	public List<TaiKhoan> layTaiKhoan() throws RemoteException;
	public boolean addTaiKhoan(TaiKhoan tk) throws RemoteException;
	public boolean deleteTaiKhoan(String tenTk) throws RemoteException;
	public boolean updateTaiKhoan(TaiKhoan tk) throws RemoteException;
	public TaiKhoan getTaiKhoanByMaNV(String maNV) throws RemoteException;
	
	//cap nhat trang thai hoat dong cua tai khoan
//	public boolean changeHoaDong(String hd, String userName) throws RemoteException;
}
