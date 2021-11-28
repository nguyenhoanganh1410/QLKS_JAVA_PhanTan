package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String maNhanVien;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenNhanVien;
	@Column(columnDefinition = "nvarchar(255)")
	private String chucVu;

	@Column(columnDefinition = "nvarchar(255)")
	 private String sdt;
	@Column(columnDefinition = "nvarchar(255)")
	    private String diaChi;
	  @Temporal(TemporalType.DATE)
	    private Date ngaySinh;
	  @Temporal(TemporalType.DATE)
	    private Date ngayVaoLam;
	    @Column(columnDefinition = "nvarchar(255)")
	    private String tinhTrang;
	  
	
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public NhanVien() {

	}

	public NhanVien(String maNhanVien, String tenNhanVien, String chucVu) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.chucVu = chucVu;
	}

	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
	}

	
	public NhanVien(String maNhanVien, String tenNhanVien, String chucVu, String sdt, String diaChi, Date ngaySinh,
			Date ngayVaoLam, String tinhTrang) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.chucVu = chucVu;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.ngayVaoLam = ngayVaoLam;
		this.tinhTrang = tinhTrang;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", chucVu=" + chucVu + ", sdt="
				+ sdt + ", diaChi=" + diaChi + ", ngaySinh=" + ngaySinh + ", ngayVaoLam=" + ngayVaoLam + ", tinhTrang="
				+ tinhTrang + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNhanVien == null) ? 0 : maNhanVien.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (maNhanVien == null) {
			if (other.maNhanVien != null)
				return false;
		} else if (!maNhanVien.equals(other.maNhanVien))
			return false;
		return true;
	}
}
