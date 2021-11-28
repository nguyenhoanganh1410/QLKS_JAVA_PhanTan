package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import entity.ChiTietHoaDon;

import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import entity.Phong;
import entity.TaiKhoan;

public class MySessionFactory {
	private SessionFactory sessionFactory;
	
	public MySessionFactory() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.configure() 
				.build();
		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(ChiTietHoaDon.class)
				.addAnnotatedClass(HoaDon.class)
				.addAnnotatedClass(KhachHang.class)
				.addAnnotatedClass(NhanVien.class)
				.addAnnotatedClass(TaiKhoan.class)
				.addAnnotatedClass(Phong.class)
				.getMetadataBuilder()
				.build();
		
		sessionFactory = metadata
				.getSessionFactoryBuilder()
				.build();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void close() {
		sessionFactory.close();
	}
}
