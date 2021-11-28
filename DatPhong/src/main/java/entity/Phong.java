package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Phong")
public class Phong implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int soPhong;
	@Column(columnDefinition = "nvarchar(255)")
	private String loaiPhong;
	@Column(columnDefinition = "nvarchar(255)")
	private String trangThai;
	@Column(columnDefinition = "nvarchar(255)")
	private String loaiGiuong;
	private int soTang;
	private double giaPhong;
	
	@Column(name = "GiaPhongDem", nullable = true)
	private double giaPhongDem;

	public int getSoPhong() {
		return soPhong;
	}

	public void setSoPhong(int soPhong) {
		this.soPhong = soPhong;
	}

	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getLoaiGiuong() {
		return loaiGiuong;
	}

	public void setLoaiGiuong(String loaiGiuong) {
		this.loaiGiuong = loaiGiuong;
	}

	public int getSoTang() {
		return soTang;
	}

	public void setSoTang(int soTang) {
		this.soTang = soTang;
	}

	public double getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(double giaPhong) {
		this.giaPhong = giaPhong;
	}

	public Phong() {

	}

	public double getGiaPhongDem() {
		return giaPhongDem;
	}

	public void setGiaPhongDem(double giaPhongDem) {
		this.giaPhongDem = giaPhongDem;
	}

	public Phong(int soPhong, String loaiPhong, String trangThai, String loaiGiuong, int soTang, double giaPhong) {
		super();
		this.soPhong = soPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
		this.loaiGiuong = loaiGiuong;
		this.soTang = soTang;
		this.giaPhong = giaPhong;
	}

	public Phong(int soPhong, String trangThai) {
		super();
		this.soPhong = soPhong;
		this.trangThai = trangThai;
	}

	public Phong(int soPhong) {
		super();
		this.soPhong = soPhong;
	}

	@Override
	public String toString() {
		return "Phong [soPhong=" + soPhong + ", loaiPhong=" + loaiPhong + ", trangThai=" + trangThai + ", loaiGiuong="
				+ loaiGiuong + ", soTang=" + soTang + ", giaPhong=" + giaPhong + "]";
	}

	public Phong(int soPhong, String loaiPhong, String trangThai, String loaiGiuong, int soTang, double giaPhong,
			double giaPhongDem) {
		super();
		this.soPhong = soPhong;
		this.loaiPhong = loaiPhong;
		this.trangThai = trangThai;
		this.loaiGiuong = loaiGiuong;
		this.soTang = soTang;
		this.giaPhong = giaPhong;
		this.giaPhongDem = giaPhongDem;
	}
}
