package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.ChiTietHoaDon;
import entity.HoaDon;

public class ChiTietHoaDonDao extends UnicastRemoteObject implements ChiTietHoaDonRemote {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public ChiTietHoaDonDao() throws RemoteException {
		super();
		this.sessionFactory = new MySessionFactory().getSessionFactory();
	}
	public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(chiTietHoaDon);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public boolean capNhatChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.update(chiTietHoaDon);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public List<ChiTietHoaDon> layChiTietHoaDon(int maHoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select c from ChiTietHoaDon c where c.hoaDonID.maHoaDon = :x";
			List<ChiTietHoaDon> list = session.createQuery(sql, ChiTietHoaDon.class).setParameter("x", maHoaDon)
					.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public List<HoaDon> layHoaDon(int soPhong) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select c.hoaDonID from ChiTietHoaDon c left join c.hoaDonID where c.phongID.soPhong = :x and c.hoaDonID.trangThai like 'C%'";
			List<HoaDon> hoaDon = session.createQuery(sql, HoaDon.class).setParameter("x", soPhong).getResultList();
			transaction.commit();
			return hoaDon;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
}
