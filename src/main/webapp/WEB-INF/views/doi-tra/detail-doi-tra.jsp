<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<div class="content">
    <h2 style="text-align: center">HÓA ĐƠN BÁN HÀNG</h2>
    <div class="infor-hoa-don">
        <p style="text-align: center"><strong>${hoaDon.maHoaDon}</strong></p>
        <p><strong> Nhân viên bán hàng: </strong>${hoaDon.nhanVien.ho} ${hoaDon.nhanVien.tenDem} ${hoaDon.nhanVien.ten} </p>
        <p><strong> Khách hàng: </strong>${hoaDon.tenNguoiNhan}</p>
        <p><strong> Số điện thoại: </strong>${hoaDon.sdt}</p>
    </div>
    <h4 style="text-align: center">DANH SÁCH SẢN PHẨM KHÁCH HÀNG MUA</h4>
    <div class="infor-san-pham-mua">
        <table class="table table-bordered" style="margin-top: 10px">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Màu sắc</th>
                <th scope="col">Kích cỡ</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Đơn giá khi giảm</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hd" items="${listHoaDonCT}" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${hd.chiTietSanPham.sanPham.tenSanPham}</td>
                    <td>${hd.chiTietSanPham.mauSac.ten}</td>
                    <td>${hd.chiTietSanPham.kichCo.size}</td>
                    <td>${hd.soLuong}</td>
                    <td><fmt:formatNumber value="${hd.donGia}" type="number"/></td>
                    <td><fmt:formatNumber value="${hd.donGiaKhiGiam}" type="number"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="5"><strong>Tổng tiền :</strong></td>
                <td colspan="2"><strong><fmt:formatNumber value="${sumHoaDon}" type="number"/></strong></td>
            </tr>
            </tbody>
        </table>
    </div>
    <hr>
    <c:if test="${listDoiTraCT.size() != 0}">
        <div class="infor-san-pham-mua">
            <h4 style="text-align: center">DANH SÁCH SẢN PHẨM KHÁCH HÀNG TRẢ</h4>
            <table class="table table-bordered" style="margin-top: 10px">
                <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Màu sắc</th>
                    <th scope="col">Kích cỡ</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Nhân viên thực hiện</th>
                    <th scope="col">Lý do</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="traHang" items="${listDoiTraCT}" varStatus="i">
                    <tr>
                        <td>${i.count}</td>
                        <td>${traHang.chiTietSanPham.sanPham.tenSanPham}</td>
                        <td>${traHang.chiTietSanPham.mauSac.ten}</td>
                        <td>${traHang.chiTietSanPham.kichCo.size}</td>
                        <td>${traHang.soLuong}</td>
                        <td><fmt:formatNumber value="${traHang.donGia}" type="number"/></td>
                        <td>${traHang.doiTra.nhanVien.ho} ${traHang.doiTra.nhanVien.tenDem} ${traHang.doiTra.nhanVien.ten}</td>
                        <td>${traHang.lyDoDoiTra}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5"><strong>Hoàn tiền :</strong></td>
                    <td colspan="3"><strong><fmt:formatNumber value="${sumDoiTra}" /></strong></td>
                </tr>
                </tbody>
            </table>
        </div>
        <hr>
    </c:if>
</div>


<a href="/bumblebee/doi-hang-doi-tra/print/${hoaDon.id}" style="border-radius: 20px; background-color: pink;" class="btn btn-primary">
    <i class="fas fa-print"></i> In hoá đơn
</a>

