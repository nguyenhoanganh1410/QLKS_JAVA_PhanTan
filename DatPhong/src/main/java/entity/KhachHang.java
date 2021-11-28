package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenKhachHang;
	@Id
	@Column(columnDefinition = "nvarchar(255)")
	private String soCMND;
	@Column(columnDefinition = "nvarchar(255)")
	private String soDienThoai;
	@Column(columnDefinition = "nvarchar(255)")
	private String loaiKhachHang;
	@Column(columnDefinition = "nvarchar(255)")
	private String DiaChi;

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getLoaiKhachHang() {
		return loaiKhachHang;
	}

	public void setLoaiKhachHang(String loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public KhachHang() {

	}

	public KhachHang(String tenKhachHang, String soCMND, String soDienThoai, String loaiKhachHang, String diaChi) {
		super();
		this.tenKhachHang = tenKhachHang;
		this.soCMND = soCMND;
		this.soDienThoai = soDienThoai;
		this.loaiKhachHang = loaiKhachHang;
		DiaChi = diaChi;
	}

	public KhachHang(String soCMND) {
		super();
		this.soCMND = soCMND;
	}

	@Override
	public String toString() {
		return "KhachHang [tenKhachHang=" + tenKhachHang + ", soCMND=" + soCMND + ", soDienThoai=" + soDienThoai
				+ ", loaiKhachHang=" + loaiKhachHang + ", DiaChi=" + DiaChi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((soCMND == null) ? 0 : soCMND.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		if (soCMND == null) {
			if (other.soCMND != null)
				return false;
		} else if (!soCMND.equals(other.soCMND))
			return false;
		return true;
	}
}
