package dao;



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportDao extends UnicastRemoteObject implements JasperReports_Remote {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7641872216659072497L;
	private SessionFactory sessionFactory;

	public JasperReportDao() throws RemoteException {
		this.sessionFactory = new MySessionFactory().getSessionFactory();
	}

	public void printBill(int maHD) throws RemoteException {
		try {
			Connection conn = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=KhachSan", "sa",
						"sa");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			Hashtable map = new Hashtable();
			JasperReport report = JasperCompileManager
					.compileReport("C:\\Users\\GMT\\Desktop\\phanTan\\DatPhongClient\\DatPhongClient\\src\\app\\HoaDonLuxury.jrxml");
			map.put("MaHD", maHD);
			JasperPrint p = JasperFillManager.fillReport(report, map, conn);
			JasperViewer.viewReport(p, false);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

	}

}
