package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.KhachHang;

public class KhachHangDao extends UnicastRemoteObject implements KhachHangRemote {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public KhachHangDao() throws RemoteException {
		super();
		this.sessionFactory = new MySessionFactory().getSessionFactory();
	}
	public List<KhachHang> layDanhSachKhachHang() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "SELECT k from KhachHang k";
			List<KhachHang> list = session.createQuery(sql, KhachHang.class).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public void themKhachHang(KhachHang khachHang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(khachHang);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}
	public String layTenKhachHang(String maKhachHang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select k.tenKhachHang from KhachHang k where k.soCMND = :x";
			String tenKhachHang = (String) session.createQuery(sql).setParameter("x", maKhachHang).getSingleResult();
			transaction.commit();
			return tenKhachHang;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public KhachHang getKhByID(String maKhachHang) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			KhachHang kh  = session.find(KhachHang.class, maKhachHang);
			transaction.commit();
			return kh;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
}
