package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Phong;

public interface PhongRemote extends Remote {
	public List<Phong> layPhong() throws RemoteException;
	
	public List<Phong> getAllPhong() throws RemoteException;

	public boolean capNhatPhong(Phong phong) throws RemoteException;

	public Integer laySoPhongChuaSuDung() throws RemoteException;

	public Phong timPhong(int soPhong) throws RemoteException;

	public double layTienPhong(int soPhong) throws RemoteException;
	
	public boolean addPhong(Phong phong) throws RemoteException;
}
