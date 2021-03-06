USE [KhachSan]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 11/28/2021 10:04:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[soGio] [varchar](255) NULL,
	[tienPhong] [float] NOT NULL,
	[maHoaDon] [int] NOT NULL,
	[soPhong] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[soPhong] ASC,
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 11/28/2021 10:04:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [int] NOT NULL,
	[ngayDen] [varchar](255) NULL,
	[ngayDi] [varchar](255) NULL,
	[tongTien] [float] NOT NULL,
	[trangThai] [nvarchar](255) NULL,
	[soCMND] [nvarchar](255) NULL,
	[maNhanVien] [varchar](255) NULL,
	[kieuThue] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 11/28/2021 10:04:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[soCMND] [nvarchar](255) NOT NULL,
	[DiaChi] [nvarchar](255) NULL,
	[loaiKhachHang] [nvarchar](255) NULL,
	[soDienThoai] [nvarchar](255) NULL,
	[tenKhachHang] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[soCMND] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 11/28/2021 10:04:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [varchar](255) NOT NULL,
	[chucVu] [nvarchar](255) NULL,
	[tenNhanVien] [nvarchar](255) NULL,
	[diaChi] [nvarchar](255) NULL,
	[ngaySinh] [datetime2](7) NULL,
	[ngayVaoLam] [datetime2](7) NULL,
	[sdt] [nvarchar](255) NULL,
	[tinhTrang] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 11/28/2021 10:04:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[soPhong] [int] NOT NULL,
	[giaPhong] [float] NOT NULL,
	[loaiGiuong] [nvarchar](255) NULL,
	[loaiPhong] [nvarchar](255) NULL,
	[soTang] [int] NOT NULL,
	[trangThai] [nvarchar](255) NULL,
	[GiaPhongDem] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[soPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 11/28/2021 10:04:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[matKhau] [varchar](255) NULL,
	[maNhanVien] [varchar](255) NOT NULL,
	[tenQuyen] [nvarchar](255) NULL,
	[hoatDong] [nvarchar](255) NULL,
	[trangThai] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK48lko1mwj5apm7o0w68jp3ugw] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK48lko1mwj5apm7o0w68jp3ugw]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FKj98ujjuq0vynori7w3lqdshie] FOREIGN KEY([soPhong])
REFERENCES [dbo].[Phong] ([soPhong])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FKj98ujjuq0vynori7w3lqdshie]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKaplnqoc7abpuj07lmuiukieyj] FOREIGN KEY([soCMND])
REFERENCES [dbo].[KhachHang] ([soCMND])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKaplnqoc7abpuj07lmuiukieyj]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKlr1g5d8b2338kpln7dlergfjg] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKlr1g5d8b2338kpln7dlergfjg]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK9lupyi9pufop3bkwjm4ig97u] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK9lupyi9pufop3bkwjm4ig97u]
GO
