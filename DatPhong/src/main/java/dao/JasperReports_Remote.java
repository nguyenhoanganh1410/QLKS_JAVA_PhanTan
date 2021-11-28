package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JasperReports_Remote extends Remote{
	/**
	 *
	 * In hóa đơn
	 */
	public void printBill(int maHD)throws RemoteException;
}
