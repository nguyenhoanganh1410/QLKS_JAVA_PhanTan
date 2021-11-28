package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Phong;

public class PhongDao extends UnicastRemoteObject implements PhongRemote {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	public PhongDao() throws RemoteException {
		super();
		this.sessionFactory = new MySessionFactory().getSessionFactory();
	}
	public List<Phong> layPhong() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "SELECT p from Phong p where p.trangThai like 'C%'";
			List<Phong> list = session.createQuery(sql, Phong.class).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public boolean capNhatPhong(Phong phong) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.update(phong);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public Integer laySoPhongChuaSuDung() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		Integer count = 0;
		try {
			transaction.begin();
			String sql = "SELECT count(*) from Phong where trangThai like 'C%'";
			count = (Integer) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			return count;
		} catch (Exception e) {
			transaction.rollback();
		}
		return count;
	}
	public Phong timPhong(int soPhong) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			Phong phong = session.find(Phong.class, soPhong);
			transaction.commit();
			return phong;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public double layTienPhong(int soPhong) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select p.giaPhong from Phong p where p.soPhong = :x";
			double tienPhong = (Double) session.createQuery(sql).setParameter("x", soPhong).getSingleResult();
			transaction.commit();
			return tienPhong;
		} catch (Exception e) {
			transaction.rollback();
		}
		return 0;
	}
	public List<Phong> getAllPhong() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "SELECT p from Phong p";
			List<Phong> list = session.createQuery(sql, Phong.class).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public boolean addPhong(Phong phong) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(phong);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		
		return false;
	}
}
