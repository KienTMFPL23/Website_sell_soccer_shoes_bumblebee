<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Doi tra san pham</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="/css/ban-hang/ban-hang.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
<div class="bodyBanHang">
    <div class="header">
        <nav class="navbar navHoaDon navbar-expand-lg">
            <input placeholder="Tìm sản phẩm muốn trả">
            <div style="margin-right: 20px;position: absolute;right: 20px">
                <a href="/bumblebee/doi-tra/hien-thi"><i style="color: white;size: 30px"
                                                         class="bi bi-arrow-return-left"></i></a>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="col-lg-8">
            <div class="tableHoaDonChiTiet">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã sản phẩm</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="i" var="hdct" items="${listHDCT}">
                        <tr>
                            <td>${i.count}</td>
                            <td>${hdct.chiTietSanPham.sanPham.maSanPham}</td>
                            <td>${hdct.chiTietSanPham.sanPham.tenSanPham}</td>
                            <td>
                                <input type="number" class="form-control"
                                       min="1"
                                       name="soLuong"
                                       value="${hdct.soLuong}"
                                       onblur="this.form.submit()"
                                       style="width:100px;">
                            </td>
                            <td>${hdct.donGia}</td>
                            <td><a onclick="deleleHDCT()"
                                   href="/bumblebee/ban-hang-tai-quay/delete-hdct/${hdct.id}">
                                <img src="../../../img/delete.png">
                            </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="tableThemSanPham">
                <nav class="navbar navHoaDon navbar-expand-lg">
                    <input placeholder="Tìm sản phẩm muốn đổi">
                </nav>
                <table class="table" border="1px solid">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Tên</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Màu sắc</th>
                        <th scope="col">Chất liệu</th>
                        <th scope="col">Đế giày</th>
                        <th scope="col">Kích cỡ</th>
                        <th scope="col">Loại giày</th>
                        <th scope="col">Giá bán</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody id="myTable">
                    <c:forEach items="${listSanPham}" var="sp" varStatus="i">
                        <tr>
                            <td>${i.count}</td>
                            <td>${sp.sanPham.tenSanPham}</td>
                            <td>${sp.soLuong}</td>
                            <td>${sp.mauSac.ten}</td>
                            <td>${sp.chatLieu.ten}</td>
                            <td>${sp.deGiay.loaiDe}</td>
                            <td>${sp.kichCo.size}</td>
                            <td>${sp.loaiGiay.tentheloai}</td>
                            <td>${sp.giaBan}</td>
                            <td>
                                <a href="/bumblebee/ban-hang-tai-quay/add-gio-hang/${sp.id}"
                                   class="btn btn-primary">Add</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-lg-4">
            <form:form method="post" modelAttribute="hoaDon"
                       action="/bumblebee/ban-hang-tai-quay/thanhtoan/${idHoaDon}">
                <%--                    <input value="${idHoaDon}" name="id" type="hidden">--%>
                <p>
                    <strong>Khách hàng:</strong>
                    <input type="text" readonly value="${hoaDon.tenNguoiNhan}" class="form-control"/>
                </p>
                <p>
                    <strong>Nhân viên bán hàng: </strong>
                    <input class="form-control" value="${fullNameStaff}" readonly>
                </p>
                <p>
                    <strong>Tổng tiền hàng trả : </strong>
                    <fmt:formatNumber value="${sumMoney}" type="number"/>đ
                </p>
                <strong>Mua hàng</strong>
                <p>
                    <strong>Tổng tiền mua : </strong> <fmt:formatNumber value="${sumMoney}" type="number"/> đ
                </p>
                <p>
                    <strong>Khách cần trả: </strong><input class="form-control" type="number" id="change"
                                                            onchange="getMoneyChange()">
                </p>
                <p>
                    <strong>Tiền khách đưa:</strong>
                    <label type="number" id="tienThua" name="tienThua" readonly> đ</label>
                </p>
                <%--            <p><b>Ghi chú:</b> <form:textarea path="ghiChu" type="text" style="width: 300px"/></p>--%>
                <div class="dropdown mt-3">
                        <%--                    <button class="btn btn-primary">In hóa đơn</button>--%>
                    <button disabled="true" id="btnThanhToan" type="submit" class=" btn-primary"
                            onclick="return confirm('Banj co muon thanh toan')"
                            style="background-color: #37517E;cursor: pointer;color: white;border: none;padding: 10px 20px;border-radius: 10px">
                        Trả hàng
                    </button>
                </div>
            </form:form>

        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>
</html>