<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ajaxy/1.6.1/scripts/jquery.ajaxy.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
    button {
        border: none;
        width: 100%;
        font-weight: bold;
    }

    #searchButton {
        background: #37517E;
        width: 50px;
        border-radius: 10px;
        border: 0px;
        height: 30px;
        color: #FFFFFF;
        margin-top: 22px;
    }

    .status {
        background: #37517E;
        color: #FFFFFF;
    }

</style>
<div class="container">
    <div class="status text-center">
        <ul class="nav justify-content-center bg-gradient-light nav-pills">
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'all' ? 'active' : ''}" aria-current="page"
                   href="/don-hang/list-all">Tất cả
                    <span class="badge text-bg-secondary">${countHD}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'cho-xac-nhan' ? 'active' : ''}"
                   href="/don-hang/list-cho-xac-nhan">Chờ xác nhận <span
                        class="badge text-bg-secondary">${countHDCho}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'chuan-bi' ? 'active' : ''}"
                   href="/don-hang/list-chuan-bi">Đang chuẩn bị<span
                        class="badge text-bg-secondary">${countHDXacNhan}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'dang-giao' ? 'active' : ''}"
                   href="/don-hang/list-dang-giao">Đang giao<span
                        class="badge text-bg-secondary">${countHDDangGiao}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'hoan-thanh' ? 'active' : ''}"
                   href="/don-hang/list-hoan-thanh">Hoàn thành<span
                        class="badge text-bg-secondary">${countHDHT}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'huy' ? 'active' : ''}" href="/don-hang/list-huy">Đã
                    huỷ<span
                            class="badge text-bg-secondary">${countHDHuy}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'don-doi' ? 'active' : ''}"
                   href="/don-hang/list-don-doi">Đổi hàng<span
                        class="badge text-bg-secondary">${countHDDoiHang}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'don-da-doi' ? 'active' : ''}"
                   href="/don-hang/list-don-da-doi">Đã đổi hàng<span
                        class="badge text-bg-secondary">${countHDDaDoi}</span></a>
            </li>
        </ul>
    </div>
    <br>
    <div class="row">
        <div class="col-lg-7">
            <form:form action="/don-hang/searchDate" method="post" modelAttribute="searchForm">
                <div class="row">
                    <div class="col-lg-3 col-md-3 col-sm-3" style="color: #003eff">
                        Từ Ngày:
                        <form:input class="form-control" type="date" path="fromDate"/>

                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3" style="color: #003eff">
                        Đến Ngày:
                        <form:input class="form-control" type="date" path="toDate"/>
                    </div>
                    <div class="col-lg-1">
                        <button id="searchButton">Tìm</button>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-lg-3">
            <input class="form-control" placeholder="Nhập mã hoặc loại tên sản phẩm" id="myInput"/>

        </div>
        <div class="col-lg-2">
            <a href="/don-hang/list-all" class=" btn btn-primary">
                <i class="bi bi-arrow-clockwise text-md"></i>
            </a>

            <a href="/don-hang/exportExcel" class=" btn btn-secondary">
                <i class="bi bi-file-earmark-arrow-down text-md"></i>
            </a>
        </div>
    </div>
    </br>
    <div class="row">
        <div class="col-lg-3">
            <form:form action="/don-hang/search-loai-don" modelAttribute="searchLoaiDon">
                <form:select class="form-control" path="key" onchange="return submit()">
                    <option value="-1">--Loại hoá đơn--</option>
                    <c:forEach var="loaiDon" items="${dsLoaiDon}">
                        <form:option value="${loaiDon.key}">${loaiDon.value}</form:option>
                    </c:forEach>
                </form:select>
            </form:form>
        </div>
    </div>

    </br>
    <div class="tab-content" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-all-tab"
             tabindex="0">
            <c:if test="${not empty page.content}">

                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
                        <th>STT</th>
                        <th>Mã hoá đơn</th>
                        <th>Tên khách hàng</th>
                        <th>Ngày tạo</th>
                        <th>Ngày thanh toán</th>
                        <th>Trạng Thái</th>
                        <th>Tổng tiền</th>
                        <th>Loại hoá đơn</th>
                        <th>Action</th>
                    </tr>

                    <tbody id="myTable">
                    <c:forEach items="${page.content}" var="hd" varStatus="stt">
                        <tr>
                            <td>${stt.index+1}</td>
                            <td>${hd.maHoaDon}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty hd.tenNguoiNhan}">
                                        ${hd.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty hd.khachHang.ten}">
                                        ${hd.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>

                            <td>${hd.ngayTao}</td>
                            <td>${hd.ngayThanhToan}</td>

                            <td>
                                <c:if test="${hd.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${hd.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${hd.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${hd.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${hd.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${hd.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${hd.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${hd.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${hd.hoaDons}" var="hdct">
                                    <c:set var="total" value="${total+(hdct.soLuong * hdct.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>

                            </td>
                            <td>
                                <c:if test="${hd.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${hd.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>
                                <a href="/don-hang/xem-don-hang/${hd.id}" style="border-radius: 20px"
                                   class="btn btn-primary">Xem Chi tiết</a>
                                <a id="content7" type="submit" class="btn btn-secondary" data-bs-toggle="modal"
                                   data-bs-target="#${hd.id}" style="border-radius: 20px">Xem
                                </a>
                                    <%--        trạng thái chờ xác nhận--%>
                                <c:if test="${hd.trangThai== 1}">
                                    <a href="/don-hang/update-xac-nhan/${hd.id}" style="border-radius: 20px"
                                       class="btn btn-warning" onclick="return confirm('Chờ xác nhận ?');">Xác nhận</a>

                                    <a href="/don-hang/huy-don-hang/${hd.id}" class="btn btn-danger"
                                       onclick="return confirm('Bạn có chắc muốn huỷ đơn hàng ?');"
                                       style="border-radius: 20px">Huỷ</a>
                                </c:if>
                                    <%--        trạng thái đang chuẩn bị--%>
                                <c:if test="${hd.trangThai== 2 && hd.loaiHoaDon==0}">
                                    <a href="/don-hang/update-chuan-bi/${hd.id}" style="border-radius: 20px"
                                       class="btn btn-success"
                                       onclick="return confirm('Bạn có chắc muốn giao hàng ?');">Giao hàng</a>
                                    <a href="/don-hang/huy-don-hang/${hd.id}" class="btn btn-danger"
                                       style="border-radius: 20px">Huỷ</a>
                                </c:if>

                                <c:if test="${hd.trangThai== 3 && hd.loaiHoaDon==0}">
                                    <a href="/don-hang/dang-giao/${hd.id}" style="border-radius: 20px"
                                       class="btn btn-success" onclick="return confirm('Xác nhận đã giao cho DVVC ?');">Xác
                                        nhận</a>
                                </c:if>
                                <c:if test="${hd.trangThai== 4 && hd.loaiHoaDon==0}">
                                    <a href="/don-hang/xac-nhan-giao/${hd.id}" style="border-radius: 20px"
                                       class="btn btn-warning" onclick="return confirm('Xác nhận đang giao?');">Xác
                                        nhận</a>
                                    <a href="/don-hang/huy-don-hang/${hd.id}" class="btn btn-danger"
                                       style="border-radius: 20px">Huỷ</a>
                                </c:if>
                                <c:if test="${hd.trangThai== 5}">
                                    <a href="/don-hang/doi-hang/${hd.id}" style="border-radius: 20px"
                                       class="btn btn-warning" onclick="return confirm('Xác nhận đổi hàng ?');">Đổi
                                        hàng</a>
                                </c:if>
                                <c:if test="${hd.trangThai== 6}">

                                    <a href="/don-hang/da-doi-hang/${hd.id}" style="border-radius: 20px"
                                       class="btn btn-warning" onclick="return confirm('Bạn có chắc muốn đổi hàng ?');">Xác
                                        nhận</a>
                                </c:if>
                                <div class="modal fade" id="${hd.id}" data-bs-backdrop="static"
                                     data-bs-keyboard="false"
                                     tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Thông tin đơn
                                                    hàng</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
                                                        <h5 style="color: #003eff">Hóa đơn chi tiết</h5>
                                                        <table class="table table-striped">
                                                            <thead class="hoa-don-chi-tiet-thead">
                                                            <tr>
                                                                <th scope="col">STT</th>
                                                                <th scope="col">Tên Sản Phẩm</th>
                                                                <th scope="col">Đơn Giá</th>
                                                                <th scope="col">Số lượng</th>
                                                                <th scope="col">Thành Tiền</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach items="${hd.hoaDons}" varStatus="stt"
                                                                       var="hdct">
                                                                <tr>
                                                                    <th scope="row">${stt.index + 1}</th>
                                                                    <td>${hdct.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                    <td>
                                                                        <fmt:formatNumber value="${hdct.donGia}"
                                                                                          type="number"/>
                                                                    </td>
                                                                    <td>${hdct.soLuong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber
                                                                                value="${hdct.donGia * hdct.soLuong}"
                                                                                type="number"/>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
                                                        <h5 style="color: #003eff">Thông tin người nhận</h5>
                                                        <table class="table table-striped">
                                                            <thead class="hoa-don-chi-tiet-thead">
                                                            <tr>
                                                                <th scope="col">Tên khách hàng</th>
                                                                <th scope="col">Địa chỉ</th>
                                                                <th scope="col">Số điện thoại</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>

                                                            <tr>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${not empty hd.tenNguoiNhan }">
                                                                            ${hd.tenNguoiNhan}
                                                                        </c:when>
                                                                        <c:when test="${not empty hd.khachHang.ten}">
                                                                            ${hd.khachHang.ho} ${hd.khachHang.tenDem} ${hd.khachHang.ten}
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${not empty hd.sdt }">
                                                                            ${hd.sdt}
                                                                        </c:when>
                                                                        <c:when test="${not empty hd.khachHang.soDienThoai}">
                                                                            ${hd.khachHang.soDienThoai}
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${not empty hd.diaChiShip }">
                                                                            ${hd.diaChiShip}
                                                                        </c:when>
                                                                        <c:when test="${not empty hd.khachHang.diaChi}">
                                                                            ${hd.khachHang.diaChi}
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>

                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty page.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>

                </td>
                <td colspan="8" class="text-center">${messError}</td>
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-end">
                        <c:if test="${page.totalPages > 0}">
                            <c:forEach begin="0" end="${page.totalPages - 1}" varStatus="loop">
                                <li class="page-item">
                                    <a class="page-link" href="/don-hang/list-${donHang}?p=${loop.begin + loop.count - 1}">
                                            ${loop.begin + loop.count }
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr,#myTable tbody true").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

</script>
