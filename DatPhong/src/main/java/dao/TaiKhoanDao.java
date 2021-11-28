package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoanDao extends UnicastRemoteObject implements TaiKhoanRemote {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	public TaiKhoanDao() throws RemoteException {
		super();
		this.sessionFactory = new MySessionFactory().getSessionFactory();
	}
	public List<TaiKhoan> layTaiKhoan() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select t from TaiKhoan t";
			List<TaiKhoan> list = session.createQuery(sql, TaiKhoan.class).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public boolean addTaiKhoan(TaiKhoan tk) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(tk);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public boolean deleteTaiKhoan(String tenTk) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.delete(session.find(TaiKhoan.class, tenTk));
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public boolean updateTaiKhoan(TaiKhoan tk) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.update(tk);;
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		
		return false;
	}
	
	public TaiKhoan getTaiKhoanByMaNV(String maNV) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			TaiKhoan nv = session.find(TaiKhoan.class, maNV);
			transaction.commit();
			return nv;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	
}
