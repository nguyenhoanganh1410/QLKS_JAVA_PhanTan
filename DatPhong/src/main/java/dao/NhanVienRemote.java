package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhanVien;

public interface NhanVienRemote extends Remote {
	public String layTenNhanVien(String maNhanVien) throws RemoteException;
	public boolean addNhanVien(NhanVien nv) throws RemoteException;
	public boolean deleteNhanVien(String maNV) throws RemoteException;
	public List<NhanVien> getAllNhanVien() throws RemoteException;
	public boolean updateNhanVien(NhanVien nv) throws RemoteException;
	public NhanVien getNhanVienByID(String maNV) throws RemoteException;
}
