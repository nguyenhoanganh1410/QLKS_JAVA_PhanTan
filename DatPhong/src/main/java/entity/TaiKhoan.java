package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable {
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name = "maNhanVien")
	@Id
	private NhanVien tenTaiKhoan;
	private String matKhau;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenQuyen;
	
	//online
	//offline
	@Column(columnDefinition = "nvarchar(255)")
	private String hoatDong;
	
	//da khoa
	//binh thuong
		@Column(columnDefinition = "nvarchar(255)")
		private String trangThai;
	
	
	public String getHoatDong() {
			return hoatDong;
		}

		public void setHoatDong(String hoatDong) {
			this.hoatDong = hoatDong;
		}

		public String getTrangThai() {
			return trangThai;
		}

		public void setTrangThai(String trangThai) {
			this.trangThai = trangThai;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	public TaiKhoan(NhanVien tenTaiKhoan, String matKhau, String tenQuyen, String hoatDong, String trangThai) {
			super();
			this.tenTaiKhoan = tenTaiKhoan;
			this.matKhau = matKhau;
			this.tenQuyen = tenQuyen;
			this.hoatDong = hoatDong;
			this.trangThai = trangThai;
		}

	public NhanVien getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(NhanVien tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTenQuyen() {
		return tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	public TaiKhoan(NhanVien tenTaiKhoan, String matKhau, String tenQuyen) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.tenQuyen = tenQuyen;
	}

	public TaiKhoan(NhanVien tenTaiKhoan, String matKhau) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tenTaiKhoan == null) ? 0 : tenTaiKhoan.hashCode());
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
		TaiKhoan other = (TaiKhoan) obj;
		if (tenTaiKhoan == null) {
			if (other.tenTaiKhoan != null)
				return false;
		} else if (!tenTaiKhoan.equals(other.tenTaiKhoan))
			return false;
		return true;
	}
}
