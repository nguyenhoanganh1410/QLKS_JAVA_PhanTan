package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HoaDon")
public class HoaDon implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int maHoaDon;
	private String ngayDen;
	private String ngayDi;
	@Column(columnDefinition = "nvarchar(255)")
	private String trangThai;
	private double tongTien;
	@ManyToOne
	@JoinColumn(name = "soCMND")
	KhachHang khachHangID;
	@ManyToOne
	@JoinColumn(name = "maNhanVien")
	NhanVien nhanVienID;
	
	
	//Thuê theo giờ, qua đêm
	@Column(columnDefinition = "nvarchar(255)")
	private String kieuThue;

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(String ngayDen) {
		this.ngayDen = ngayDen;
	}

	public String getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(String ngayDi) {
		this.ngayDi = ngayDi;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public KhachHang getKhachHangID() {
		return khachHangID;
	}

	public void setKhachHangID(KhachHang khachHangID) {
		this.khachHangID = khachHangID;
	}

	public NhanVien getNhanVienID() {
		return nhanVienID;
	}

	public void setNhanVienID(NhanVien nhanVienID) {
		this.nhanVienID = nhanVienID;
	}

	public String getKieuThue() {
		return kieuThue;
	}

	public void setKieuThue(String kieuThue) {
		this.kieuThue = kieuThue;
	}

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDon(int maHoaDon, String ngayDen, String ngayDi, String trangThai, double tongTien,
			KhachHang khachHangID, NhanVien nhanVienID) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.trangThai = trangThai;
		this.tongTien = tongTien;
		this.khachHangID = khachHangID;
		this.nhanVienID = nhanVienID;
	}

	public HoaDon(int maHoaDon, String ngayDen, String ngayDi, String trangThai, double tongTien, String kieuThue) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.trangThai = trangThai;
		this.tongTien = tongTien;
		this.kieuThue = kieuThue;
	}

	public HoaDon(int maHoaDon, String ngayDen, String ngayDi, String trangThai, double tongTien, KhachHang khachHangID,
			NhanVien nhanVienID, String kieuThue) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.trangThai = trangThai;
		this.tongTien = tongTien;
		this.khachHangID = khachHangID;
		this.nhanVienID = nhanVienID;
		this.kieuThue = kieuThue;
	}

	public HoaDon(int maHoaDon, String ngayDi, String trangThai, double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayDi = ngayDi;
		this.trangThai = trangThai;
		this.tongTien = tongTien;
	}

	public HoaDon(int maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	
}
