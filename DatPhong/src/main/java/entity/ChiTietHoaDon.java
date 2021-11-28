package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDonID;
	@Id
	@ManyToOne
	@JoinColumn(name = "soPhong")
	private Phong phongID;
	private String soGio;
	private double tienPhong;

	public HoaDon getHoaDonID() {
		return hoaDonID;
	}

	public void setHoaDonID(HoaDon hoaDonID) {
		this.hoaDonID = hoaDonID;
	}

	public Phong getPhongID() {
		return phongID;
	}

	public void setPhongID(Phong phongID) {
		this.phongID = phongID;
	}

	public String getSoGio() {
		return soGio;
	}

	public void setSoGio(String soGio) {
		this.soGio = soGio;
	}

	public double getTienPhong() {
		return tienPhong;
	}

	public void setTienPhong(double tienPhong) {
		this.tienPhong = tienPhong;
	}

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(HoaDon hoaDonID, Phong phongID, String soGio, double tienPhong) {
		super();
		this.hoaDonID = hoaDonID;
		this.phongID = phongID;
		this.soGio = soGio;
		this.tienPhong = tienPhong;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDonID=" + hoaDonID + ", phongID=" + phongID + ", soGio=" + soGio + ", tienPhong="
				+ tienPhong + "]";
	}
}
