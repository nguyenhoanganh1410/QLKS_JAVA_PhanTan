package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.HoaDon;

public class HoaDonDao extends UnicastRemoteObject implements HoaDonRemote {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	public HoaDonDao() throws RemoteException {
		super();
		this.sessionFactory = new MySessionFactory().getSessionFactory();
	}
	public boolean themHoaDon(HoaDon hoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.save(hoaDon);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public boolean capNhatHoaDon(HoaDon hoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			session.update(hoaDon);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
		}
		return false;
	}
	public HoaDon timHoaDonBangMaHoaDon(int maHoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			HoaDon hoaDon = session.find(HoaDon.class, maHoaDon);
			transaction.commit();
			return hoaDon;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public String layNgayDen(int maHoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select h.ngayDen from HoaDon h where h.maHoaDon = :x";
			String ngayDen = (String) session.createQuery(sql).setParameter("x", maHoaDon).getSingleResult();
			transaction.commit();
			return ngayDen;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public List<HoaDon> layHoaDonChuaThanhToan() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select h from HoaDon h where h.trangThai like 'C%'";
			List<HoaDon> list = session.createQuery(sql, HoaDon.class).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public HoaDon timHoaDonTheoMa(int maHoaDon) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select h from HoaDon h where h.maHoaDon = :x and h.trangThai like 'C%'";
			HoaDon hoaDon = session.createQuery(sql, HoaDon.class).setParameter("x", maHoaDon).getSingleResult();
			transaction.commit();
			return hoaDon;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public Integer laySoHoaDon() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		Integer count = 0;
		try {
			transaction.begin();
			String sql = "SELECT count(*) from HoaDon";
			count = (Integer) session.createNativeQuery(sql).getSingleResult();
			transaction.commit();
			return count;
		} catch (Exception e) {
			transaction.rollback();
		}
		return count;
	}
	public List<HoaDon> layDsHoaDon() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			String sql = "Select h from HoaDon h order by h.ngayDen desc";
			List<HoaDon> list = session.createQuery(sql, HoaDon.class).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public List<Object[]> ThongKeKH() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			
			 List<Object[]> list = session.createNativeQuery(
					"select a.soCMND, COUNT(a.soCMND), SUM(tongTien) as tong from HoaDon a\r\n"
					+ "group by soCMND\r\n"
					+ "order by tong desc\r\n"
					+ "" )
					.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public List<Object[]> ThongKePhongTheoNgay(String query) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			
			 List<Object[]> list = session.createNativeQuery("select b.soPhong, count(b.soPhong), sum(a.tongTien) as tong from HoaDon a\r\n"
			 		+ "inner join ChiTietHoaDon b on a.maHoaDon = b.maHoaDon\r\n"
			 		+ "where CAST(a.ngayDen AS DATE)  = '"+query+"'\r\n"
			 		+ "group by b.soPhong\r\n"
			 		+ "order by tong desc")
					.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public List<Object[]> ThongKePhongTheoHaiMocTG(String date1, String date2) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			
			 List<Object[]> list = session.createNativeQuery("select b.soPhong, count(b.soPhong), sum(a.tongTien) as tong from HoaDon a\r\n"
			 		+ "inner join ChiTietHoaDon b on a.maHoaDon = b.maHoaDon\r\n"
			 		+ "where CAST(a.ngayDen AS DATE)  between  '"+date2+"' and '"+date1+"'\r\n"
			 		+ "group by b.soPhong\r\n"
			 		+ "order by tong desc\r\n"
			 		+ "")
					.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}
	public List<Object[]> ThongKePhongDoanhThuTheoNgay() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			
			 List<Object[]> list = session.createNativeQuery("select top(30)  CAST(ngayDen AS DATE) as d ,COUNT( CAST(ngayDen AS DATE)) as c ,sum( tongTien) from HoaDon\r\n"
			 		+ "group by  CAST(ngayDen AS DATE)\r\n"
			 		+ "order by  d desc"
			 	)
					.list();
			transaction.commit();
	
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
	public List<Object[]> getHoaDonChuaTT() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.getTransaction();
		try {
			transaction.begin();
			
			 List<Object[]> list = session.createNativeQuery("select h.maHoaDon, h.ngayDen, h.ngayDi, h.trangThai, k.tenKhachHang,c.soPhong, h.kieuThue from HoaDon h \r\n"
			 		+ "inner join ChiTietHoaDon c on h.maHoaDon = c.maHoaDon\r\n"
			 		+ "inner join KhachHang k on h.soCMND = k.soCMND\r\n"
			 		+ "where trangThai like 'C%'\r\n"
			 		+ "order by h.ngayDen desc"
			 	)
					.list();
			transaction.commit();
	
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
}
