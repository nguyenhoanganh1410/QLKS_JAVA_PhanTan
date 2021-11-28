package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.KhachHang;
import entity.NhanVien;

public class NhanVienDao extends UnicastRemoteObject implements NhanVienRemote {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	public NhanVienDao() throws RemoteException {
		super();
		this.sessionFactory = new MySessionFactory().getSessionFactory();
	}
	
	public String layTenNhanVien(String maNhanVien) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "SELECT n.tenNhanVien from NhanVien n where n.maNhanVien = :x";
			String tenNhanVien = (String) session.createQuery(sql).setParameter("x", maNhanVien).getSingleResult();
			transaction.commit();
			return tenNhanVien;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public boolean addNhanVien(NhanVien nv) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(nv);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public boolean deleteNhanVien(String maNV) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.delete(getNhanVienByID(maNV));
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public List<NhanVien> getAllNhanVien() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		
		try {
			transaction.begin();
			String sql = "select n from NhanVien n";
			List<NhanVien> list = session.createQuery(sql, NhanVien.class).getResultList();
			System.out.println("nhan");
			transaction.commit();
			System.out.println("nhanVien");
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public boolean updateNhanVien( NhanVien nv) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.update(nv);;
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		
		return false;
	}
	public NhanVien getNhanVienByID(String maNV) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			NhanVien nv = session.find(NhanVien.class, maNV);
			transaction.commit();
			return nv;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
}
