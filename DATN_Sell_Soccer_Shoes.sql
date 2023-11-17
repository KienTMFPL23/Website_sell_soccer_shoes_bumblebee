
Create
    Database JAVA_DATN_Sell_Soccer_Shoes

GO
Use JAVA_DATN_Sell_Soccer_Shoes
GO
CREATE TABLE SanPham
(
    Id         UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
    MaSanPham  VARCHAR(100) UNIQUE,
    TenSanPham NVARCHAR(150),
    TrangThai  INT
)
GO

CREATE TABLE ChatLieu
(
    Id          UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    MaChatLieu  VARCHAR(100) UNIQUE,
    TenChatLieu NVARCHAR(150),
    TrangThai   INT
)

GO


CREATE TABLE KichCo
(
    Id        UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    MaKichCo  VARCHAR(100) UNIQUE,
    Size      INT UNIQUE,
    GioiTinh  BIT,
    TrangThai INT
)

GO


CREATE TABLE LoaiGiay
(
    Id         UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    Ma         VARCHAR(100) UNIQUE,
    TenTheLoai NVARCHAR(150),
    TrangThai  INT
)
GO

	select * from HoaDon

CREATE TABLE DeGiay
(
    Id        UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    Ma        VARCHAR(100) UNIQUE,
    LoaiDe    NVARCHAR(150),
    TrangThai INT
)

GO


CREATE TABLE MauSac
(
    Id        UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    Ma        VARCHAR(100) UNIQUE,
    TenMau    NVARCHAR(150),
    TrangThai INT
)


CREATE TABLE HinhAnh
(
    Id        UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdCTSP    UNIQUEIDENTIFIER REFERENCES ChiTietSanPham (id),
    TenAnh    VARCHAR(255),
    DuongDan1 VARCHAR(255),
    DuongDan2 VARCHAR(255),
    DuongDan3 VARCHAR(255),
    DuongDan4 VARCHAR(255),
    DuongDan5 VARCHAR(255),
    TrangThai INT
)



CREATE TABLE ChiTietSanPham
(
    Id         UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdSP       UNIQUEIDENTIFIER REFERENCES SanPham (Id),
    IdMauSac   UNIQUEIDENTIFIER REFERENCES MauSac (Id),
    IdTheLoai  UNIQUEIDENTIFIER REFERENCES LoaiGiay (Id),
    IdKichCo   UNIQUEIDENTIFIER REFERENCES KichCo (Id),
    IdChatLieu UNIQUEIDENTIFIER REFERENCES ChatLieu (Id),
    IdDeGiay   UNIQUEIDENTIFIER REFERENCES DeGiay (Id),
    GiaBan     MONEY,
    SoLuong    INT,
    MoTaCT     NVARCHAR(255),
    NgayTao    DATETIME,
    TrangThai  INT
)
GO

CREATE TABLE HinhAnh
(
    Id        UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdCTSP    UNIQUEIDENTIFIER REFERENCES ChiTietSanPham (id),
    TenAnh    VARCHAR(255),
    DuongDan1 VARCHAR(255),
    DuongDan2 VARCHAR(255),
    DuongDan3 VARCHAR(255),
    DuongDan4 VARCHAR(255),
    DuongDan5 VARCHAR(255),
    TrangThai INT
)
GO

CREATE TABLE TaiKhoan
(
    Id       UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    Username VARCHAR(100) UNIQUE,
    Password VARCHAR(100),
    Role     INT

)

GO


if OBJECT_ID('KhachHang') is not null
    drop table KhachHang
go
CREATE TABLE KhachHang
(
    Id          UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdTaiKhoan  UNIQUEIDENTIFIER REFERENCES TaiKhoan (Id),
    Ma          VARCHAR(100) UNIQUE,
    Ho          NVARCHAR(50),
    TenDem      NVARCHAR(50),
    Ten         NVARCHAR(50),
    GioiTinh    BIT,
    NgaySinh    DATE,
    DiaChi      NVARCHAR(255),
    SoDienThoai VARCHAR(15),
    Email       VARCHAR(100),
    TrangThai   INT
)
GO
CREATE TABLE NhanVien
(
    Id          UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdTaiKhoan  UNIQUEIDENTIFIER REFERENCES TaiKhoan (Id),
    Ma          VARCHAR(100) UNIQUE,
    Ho          NVARCHAR(50),
    TenDem      NVARCHAR(50),
    Ten         NVARCHAR(50),
    GioiTinh    BIT,
    NgaySinh    DATE,
    DiaChi      NVARCHAR(255),
    SoDienThoai VARCHAR(15) UNIQUE,
    Email       VARCHAR(100),
    TrangThai   INT
)

GO


CREATE TABLE GioHang
(
    Id            UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdKH          UNIQUEIDENTIFIER REFERENCES KhachHang (Id),
    IdNV          UNIQUEIDENTIFIER REFERENCES NhanVien (Id),
    NgayTao       DATETIME,
    NgayThanhToan DATETIME,
    TrangThai     INT
)
GO

CREATE TABLE HoaDon
(
    Id                  UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdNV                UNIQUEIDENTIFIER REFERENCES NhanVien (Id),
    IdKH                UNIQUEIDENTIFIER REFERENCES KhachHang (Id),
    MaHD                VARCHAR(150) UNIQUE,
    NgayTao             DATETIME,
    NgayThanhToan       DATETIME,
    DiaChiShip          NVARCHAR(255),
    SoDienThoai         VARCHAR(15),
    TenNguoiNhan        NVARCHAR(100),
    GhiChu              NVARCHAR( MAX),
    LoaiHoaDon          INT,
    PhuongThucThanhToan INT,
    TrangThai           INT
)
GO

CREATE TABLE HoaDonChiTiet
(
    Id            UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdHoaDon      UNIQUEIDENTIFIER REFERENCES HoaDon (Id),
    IdChiTietSP   UNIQUEIDENTIFIER REFERENCES ChiTietSanPham (Id),
    SoLuong       INT,
    DonGia        MONEY,
    DonGiaKhiGiam MONEY,
    TrangThai     INT
)
GO

CREATE TABLE PhieuGiaoHang
(
    Id            UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdHoaDon      UNIQUEIDENTIFIER REFERENCES HoaDon (Id),
    NgayTao       DATETIME,
    NgayThanhToan DATETIME,
    NgayShip      DATETIME,
    NgayNhan      DATETIME,
    DiaChi        NVARCHAR(200)
)
GO

CREATE TABLE GioHangChiTiet
(
    Id            UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdChiTietSP   UNIQUEIDENTIFIER REFERENCES ChiTietSanPham (Id),
    IdGioHang     UNIQUEIDENTIFIER REFERENCES GioHang (Id),
    SoLuong       INT,
    DonGia        MONEY,
    DonGiaKhiGiam MONEY

)

CREATE TABLE KhuyenMai
(
    Id				UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    MaKhuyenMai		VARCHAR(100) UNIQUE,
    TenKhuyenMai	NVARCHAR(100),
    GiaTri			Float,
    NgayTao			DateTime,
    TrangThai		INT
)

CREATE TABLE ChiTietKhuyenMai
(
    Id						UNIQUEIDENTIFIER DEFAULT NEWID() PRIMARY KEY,
    IdChiTietSanPham		UNIQUEIDENTIFIER ,
    IdKhuyenMai				UNIQUEIDENTIFIER ,
    NgayBatDau				DateTime,
    NgayKetThuc				DateTime,
    NgayCapNhat			DateTime,
    TrangThai		INT
)




