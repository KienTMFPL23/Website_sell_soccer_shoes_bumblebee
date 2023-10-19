<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<main class="ps-main">
    <div class="container">
        <div class="row" style="margin-top: 30px;">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <div class="ps-checkout__order">
                    <c:forEach items="${listHD}" var="hd">
                        <header>
                            <div class="row">
                                <div class="col-lg-6">
                                    <h3 style="margin-bottom: 20px;">Đơn hàng của bạn</h3>
                                </div>

                                <div class="col-lg-6">
                                    <c:if test="${hd.trangThai == 3}">
                                        <p style="color: crimson; float: right">CHỜ THANH TOÁN</p>
                                    </c:if>
                                    <c:if test="${hd.trangThai == 1}">
                                        <p style="color: crimson; float: right">ĐÃ THANH TOÁN</p>
                                    </c:if>
                                </div>
                            </div>
                            <div>
                                <c:choose>
                                    <c:when test="${hd.tenNguoiNhan == '' && hd.sdt == '' && hd.diaChiShip == ''}">
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <p>Tên người
                                                    nhận: ${hd.khachHang.ho} ${hd.khachHang.tenDem} ${hd.khachHang.ten}</p>
                                                <p>Số điện thoại: ${hd.khachHang.soDienThoai}</p>
                                                <p>Địa chỉ: ${hd.khachHang.diaChi}</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <div>
                                                    <p>Ghi chú: ${hd.ghiChu}</p>
                                                    <c:if test="${hd.phuongThucThanhToan == 0}">
                                                        <p>Phương thức thanh toán: Thanh toán khi nhận hàng</p>
                                                    </c:if>
                                                    <c:if test="${hd.phuongThucThanhToan == 1}">
                                                        <p>Phương thức thanh toán: Thanh toán PayPal</p>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>

                                    <c:otherwise>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <p>Tên người nhận: ${hd.tenNguoiNhan}</p>
                                                <p>Số điện thoại: ${hd.sdt}</p>
                                                <p>Địa chỉ: ${hd.diaChiShip}</p>

                                            </div>
                                            <div class="col-lg-6">
                                                <div>
                                                    <p>Ghi chú: ${hd.ghiChu}</p>
                                                    <c:if test="${hd.phuongThucThanhToan == 1}">
                                                        <p>Phương thức thanh toán: Thanh toán khi nhận hàng</p>
                                                    </c:if>
                                                    <c:if test="${hd.phuongThucThanhToan == 2}">
                                                        <p>Phương thức thanh toán: Thanh toán PayPal</p>
                                                    </c:if>
                                                </div>
                                            </div>

                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </header>

                        <div class="content">
                            <table class="table ps-checkout__products">
                                <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th></th>
                                    <th style="text-align: center;">Số lượng</th>
                                    <th style="text-align: center;">Đơn giá</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${hd.hoaDons}" var="hdct">
                                    <tr>
                                        <td><img src="../../../uploads/${hdct.chiTietSanPham.hinhAnhs.tenanh}"
                                                 width="100px" height="100px"></td>
                                        <td style="padding-top: 25px;">
                                                ${hdct.chiTietSanPham.sanPham.tenSanPham}<br>
                                            <p>${hdct.chiTietSanPham.mauSac.ten}
                                                - ${hdct.chiTietSanPham.kichCo.size}</p>

                                        </td>
                                        <td style="padding-top: 25px; text-align: center">${hdct.soLuong}</td>
                                        <td style="text-align: center;padding-top: 25px;"><fmt:formatNumber
                                                value="${hdct.chiTietSanPham.giaBan}"
                                                type="currency"/>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>

                        <div class="row">
                            <div class="col-lg-6"></div>
                            <div class="col-lg-6" style="padding-bottom: 20px;">
                                <span style="font-size: 18px; font-weight: 600;">Số tiền phải trả: </span>
                                <span style="font-size: 18px; "><fmt:formatNumber value="${totalPrice}"
                                                                                  type="currency"/></span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-lg-2"></div>
        </div>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>