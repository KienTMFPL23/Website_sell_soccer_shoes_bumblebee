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
    <div class="status">
        <ul class="nav nav-pills nav-fill gap-2 p-1 small  rounded-5 shadow-sm" id="pillNav2" role="tablist"
            style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-green); --bs-nav-pills-link-active-bg: var(--bs-white);">
            <li class="nav-item" role="presentation">
                <button class="nav-link active rounded-5" id="pills-all-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-home"
                        type="button" role="tab" aria-controls="pills-all" aria-selected="true">Tất Cả
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5 position-relative" id="pills-choxacnhan-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-choxacnhan" type="button" role="tab" aria-controls="pills-choxacnhan"
                        aria-selected="false">Chờ xác nhận
                    <span class="badge text-bg-secondary">${countHDCho}</span>
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5 position-relative" id="pills-chuanbi-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-chuanbi"
                        type="button" role="tab" aria-controls="pills-chuanbi" aria-selected="false">Đang chuẩn bị
                    <span class="badge text-bg-secondary">${countHDXacNhan}</span>
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-danggiao-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-danggiao"
                        type="button" role="tab" aria-controls="pills-danggiao" aria-selected="false">Đang giao
                    <span class="badge text-bg-secondary">${countHDDangGiao}</span>
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-hoanthanh-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-hoanthanh" type="button" role="tab" aria-controls="pills-hoanthanh"
                        aria-selected="false">Hoàn thành
                    <span class="badge text-bg-secondary">${countHDHT}</span>
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-dahuy-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-dahuy"
                        type="button" role="tab" aria-controls="pills-dahuy" aria-selected="false">Đã huỷ
                    <span class="badge text-bg-secondary">${countHDHuy}</span>
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-trahang-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-trahang"
                        type="button" role="tab" aria-controls="pills-trahang" aria-selected="false">Trả hàng
                    <span class="badge text-bg-secondary">${countHDTraHang}</span>
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-dahoantra-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-dahoantra"
                        type="button" role="tab" aria-controls="pills-dahoantra" aria-selected="false">Đã hoàn trả
                    <span class="badge text-bg-secondary">${countHDDaTra}</span>
                </button>
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
                        <form:input class="form-control" type="date" placeholder="dd/MM/yyyy" path="fromDate"/>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-3" style="color: #003eff">
                        Đến Ngày:
                        <form:input class="form-control" type="date" placeholder="dd/MM/yyyy" path="toDate"/>
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
                <form:select class="form-control" path="key" onchange="submit()">
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
                    <c:forEach items="${page.content}" var="hd">
                        <tr>
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
                                   class="btn btn-primary">Xem</a>

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
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${page.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${page.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${page.totalPages-1}">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-choxacnhan" role="tabpanel" aria-labelledby="pills-choxacnhan-tab"
             tabindex="0">
            <c:if test="${not empty listChoXacNhan.content}">

                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
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
                    <c:forEach items="${listChoXacNhan.content}" var="hd1">
                        <tr>
                            <td>${hd1.maHoaDon}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty hd1.tenNguoiNhan}">
                                        ${hd1.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty hd1.khachHang.ten}">
                                        ${hd1.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${hd1.ngayTao}</td>
                            <td>${hd1.ngayThanhToan}</td>

                            <td>
                                <c:if test="${hd1.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${hd1.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${hd1.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${hd1.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${hd1.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${hd1.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${hd1.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${hd1.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${hd1.hoaDons}" var="hdct">
                                    <c:set var="total" value="${total+(hdct.soLuong * hdct.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>

                            </td>
                            <td>
                                <c:if test="${hd1.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${hd1.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>
                                <p><a onchange="submit()" href="/don-hang/update-xac-nhan/${hd1.id}"
                                      style="border-radius: 20px"
                                      class="btn btn-warning">Xác nhận</a>
                                    <a id="content1" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                       data-bs-target="#${hd1.id}1" style="border-radius: 20px">Xem
                                    </a>
                                </p>
                                <span><a onchange="submit()" href="/don-hang/huy-don-hang/${hd1.id}"
                                         class="btn btn-danger"
                                         style="border-radius: 20px">Huỷ</a>
                            </span>

                                <div class="modal fade" id="${hd1.id}1" data-bs-backdrop="static"
                                     data-bs-keyboard="false"
                                     tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel2">Thông tin đơn
                                                    hàng</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <h5 class="title">Hóa đơn chi tiết</h5>
                                                <div class="row" style="margin-top: 20px">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
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
                                                            <c:forEach items="${hd1.hoaDons}" varStatus="stt"
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
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Closes
                                                </button>
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
            <c:if test="${empty listChoXacNhan.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>
                </td>
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChoXacNhan.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChoXacNhan.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChoXacNhan.totalPages-1}">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-chuanbi" role="tabpanel" aria-labelledby="pills-chuanbi-tab" tabindex="0">
            <c:if test="${not empty listChuanBi.content}">

                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
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
                    <c:forEach items="${listChuanBi.content}" var="hdcb">
                        <tr>
                            <td>
                                    ${hdcb.maHoaDon}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty hdcb.tenNguoiNhan}">
                                        ${hdcb.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty hdcb.khachHang.ten}">
                                        ${hdcb.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${hdcb.ngayTao}</td>
                            <td>${hdcb.ngayThanhToan}</td>

                            <td>
                                <c:if test="${hdcb.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${hdcb.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${hdcb.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${hdcb.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${hdcb.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${hdcb.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${hdcb.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${hdcb.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${hdcb.hoaDons}" var="hdctcb">
                                    <c:set var="total" value="${total+(hdctcb.soLuong * hdctcb.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>

                            </td>
                            <td>
                                <c:if test="${hdcb.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${hdcb.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>
                            <span>
                                <a id="content2" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#${hdcb.id}2" style="border-radius: 20px">Xem
                            </a><a href="/don-hang/update-chuan-bi/${hdcb.id}" onchange="submit()"
                                   style="border-radius: 20px"
                                   class="btn btn-warning">Giao hàng</a>

                            </span>
                                <span><a onchange="submit()" href="/don-hang/huy-don-hang/${hdcb.id}"
                                         class="btn btn-danger"
                                         style="border-radius: 20px">Huỷ</a>
                            </span>
                                <div class="modal fade" id="${hdcb.id}2" data-bs-backdrop="static"
                                     data-bs-keyboard="false"
                                     tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel3">Thông tin đơn
                                                    hàng</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <h5 class="title">Hóa đơn chi tiết</h5>
                                                <div class="row" style="margin-top: 20px">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
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
                                                            <c:forEach items="${hdcb.hoaDons}" varStatus="stt"
                                                                       var="hdctdb2">
                                                                <tr>
                                                                    <th scope="row">${stt.index + 1}</th>
                                                                    <td>${hdctdb2.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                    <td>
                                                                        <fmt:formatNumber value="${hdctdb2.donGia}"
                                                                                          type="number"/>
                                                                    </td>
                                                                    <td>${hdctdb2.soLuong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber
                                                                                value="${hdctdb2.donGia * hdctdb2.soLuong}"
                                                                                type="number"/>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Closes
                                                </button>
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
            <c:if test="${empty listChuanBi.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>
                </td>
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChuanBi.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChuanBi.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChuanBi.totalPages-1}">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-danggiao" role="tabpanel" aria-labelledby="pills-danggiao-tab"
             tabindex="0">
            <c:if test="${not empty listDonGiao.content}">

                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
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
                    <c:forEach items="${listDonGiao.content}" var="hd3">
                        <tr>
                            <td>${hd3.maHoaDon}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty hd3.tenNguoiNhan}">
                                        ${hd3.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty hd3.khachHang.ten}">
                                        ${hd3.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${hd3.ngayTao}</td>
                            <td>${hd3.ngayThanhToan}</td>

                            <td><c:if test="${hd3.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${hd3.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${hd3.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${hd3.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${hd3.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${hd3.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${hd3.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${hd3.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${hd3.hoaDons}" var="hdct3">
                                    <c:set var="total" value="${total+(hdct3.soLuong * hdct3.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>

                            </td>
                            <td>
                                <c:if test="${hd3.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${hd3.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>
                                <p>
                                    <a id="content3" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                       data-bs-target="#${hd3.id}3" style="border-radius: 20px">Xem
                                    </a><a href="/don-hang/dang-giao/${hd3.id}" style="border-radius: 20px"
                                           class="btn btn-warning" onchange="submit()">Hoàn Thành</a>
                                    <a href="/don-hang/huy-don-hang/${hd3.id}" class="btn btn-danger"
                                       style="border-radius: 20px" onchange="submit()">Huỷ</a>

                                </p>

                                <div class="modal fade" id="${hd3.id}3" data-bs-backdrop="static"
                                     data-bs-keyboard="false"
                                     tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel4">Thông tin đơn
                                                    hàng</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <h5 class="title">Hóa đơn chi tiết</h5>
                                                <div class="row" style="margin-top: 20px">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
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
                                                            <c:forEach items="${hd3.hoaDons}" varStatus="stt"
                                                                       var="hdct33">
                                                                <tr>
                                                                    <th scope="row">${stt.index + 1}</th>
                                                                    <td>${hdct33.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                    <td>
                                                                        <fmt:formatNumber value="${hdct33.donGia}"
                                                                                          type="number"/>
                                                                    </td>
                                                                    <td>${hd.soLuong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber
                                                                                value="${hdct33.donGia * hdct33.soLuong}"
                                                                                type="number"/>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Closes
                                                </button>
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
            <c:if test="${empty listDonGiao.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>
                </td>
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonGiao.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonGiao.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonGiao.totalPages-1}">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-hoanthanh" role="tabpanel" aria-labelledby="pills-hoanthanh-tab"
             tabindex="0">
            <c:if test="${not empty listHoanThanh.content}">

                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
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
                    <c:forEach items="${listHoanThanh.content}" var="hdht">
                        <tr>
                            <td>${hdht.maHoaDon}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${not empty hdht.tenNguoiNhan}">
                                        ${hdht.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty hdht.khachHang.ten}">
                                        ${hdht.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${hdht.ngayTao}</td>
                            <td>${hdht.ngayThanhToan}</td>

                            <td>
                                <c:if test="${hdht.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${hdht.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${hdht.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${hdht.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${hdht.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${hdht.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${hdht.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${hdht.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${hdht.hoaDons}" var="hdctht">
                                    <c:set var="total" value="${total+(hdctht.soLuong * hdctht.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>

                            </td>
                            <td>
                                <c:if test="${hdht.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${hdht.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>
                                <p><a href="/don-hang/tra-hang/${hdht.id}" style="border-radius: 20px"
                                      class="btn btn-warning" onchange="submit()">Trả hàng</a>
                                    <a id="content4" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                       data-bs-target="#${hdht.id}4" style="border-radius: 20px">Xem
                                    </a>
                                </p>

                                <div class="modal fade" id="${hdht.id}4" data-bs-backdrop="static"
                                     data-bs-keyboard="false"
                                     tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel5">Thông tin đơn
                                                    hàng</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <h5 class="title">Hóa đơn chi tiết</h5>
                                                <div class="row" style="margin-top: 20px">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
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
                                                            <c:forEach items="${hdht.hoaDons}" varStatus="stt"
                                                                       var="hdctht1">
                                                                <tr>
                                                                    <th scope="row">${stt.index + 1}</th>
                                                                    <td>${hdctht1.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                    <td>
                                                                        <fmt:formatNumber value="${hdctht1.donGia}"
                                                                                          type="number"/>
                                                                    </td>
                                                                    <td>${hdctht1.soLuong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber
                                                                                value="${hdctht1.donGia * hdctht1.soLuong}"
                                                                                type="number"/>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Closes
                                                </button>
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
            <c:if test="${empty listHoanThanh.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>
                </td>
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listHoanThanh.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listHoanThanh.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listHoanThanh.totalPages-1}">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-dahuy" role="tabpanel" aria-labelledby="pills-dahuy-tab" tabindex="0">
            <c:if test="${not empty listHuy.content}">

                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
                        <th>Mã hoá đơn</th>
                        <th>Tên khách hàng</th>

                        <th>Ngày tạo</th>
                        <th>Ngày thanh toán</th>
                        <th>Trạng Thái</th>
                        <th>
                            Tổng Tiền

                        </th>
                        <th>Loại hoá đơn</th>
                        <th>Action</th>
                    </tr>
                    <tbody id="myTable">
                    <c:forEach items="${listHuy.content}" var="donHuy">
                        <tr>
                            <td>${donHuy.maHoaDon}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty donHuy.tenNguoiNhan}">
                                        ${donHuy.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty donHuy.khachHang.ten}">
                                        ${donHuy.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${donHuy.ngayTao}</td>
                            <td>${donHuy.ngayThanhToan}</td>

                            <td>
                                <c:if test="${donHuy.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${donHuy.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${donHuy.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${donHuy.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${donHuy.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${donHuy.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${donHuy.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${donHuy.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${donHuy.hoaDons}" var="hdct1">
                                    <c:set var="total" value="${total+(hdct1.soLuong * hdct1.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>
                            </td>
                            <td>
                                <c:if test="${donHuy.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${donHuy.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>
                                <p>

                                    <a id="content5" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                       data-bs-target="#${donHuy.id}5" style="border-radius: 20px">Xem
                                    </a>
                                </p>


                                <div class="modal fade" id="${donHuy.id}5" data-bs-backdrop="static"
                                     data-bs-keyboard="false"
                                     tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel12">Thông tin đơn
                                                    hàng</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <h5 class="title">Hóa đơn chi tiết</h5>
                                                <div class="row" style="margin-top: 20px">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
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
                                                            <c:forEach items="${donHuy.hoaDons}" varStatus="stt"
                                                                       var="hdDaHuy">
                                                                <tr>
                                                                    <th scope="row">${stt.index + 1}</th>
                                                                    <td>${hdDaHuy.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                    <td>
                                                                        <fmt:formatNumber value="${hdDaHuy.donGia}"
                                                                                          type="number"/>
                                                                    </td>
                                                                    <td>${hdDaHuy.soLuong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber
                                                                                value="${hdDaHuy.donGia * hdDaHuy.soLuong}"
                                                                                type="number"/>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Closes
                                                </button>
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
            <c:if test="${empty listHuy.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>
                </td>
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listHuy.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listHuy.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listHuy.totalPages-1}">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-trahang" role="tabpanel" aria-labelledby="pills-trahang-tab" tabindex="0">
            </br>
            <c:if test="${not empty listDonTra.content}">


                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
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
                    <c:forEach items="${listDonTra.content}" var="hdtra">
                        <tr>
                            <td>${hdtra.maHoaDon}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty hdtra.tenNguoiNhan}">
                                        ${hdtra.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty hdtra.khachHang.ten}">
                                        ${hdtra.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${hdtra.ngayTao}</td>
                            <td>${hdtra.ngayThanhToan}</td>

                            <td>
                                <c:if test="${hdtra.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${hdtra.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${hdtra.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${hdtra.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${hdtra.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${hdtra.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${hdtra.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${hdtra.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${hdtra.hoaDons}" var="hdctTra">
                                    <c:set var="total" value="${total+(hdctTra.soLuong * hdctTra.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>

                            </td>
                            <td>
                                <c:if test="${hdtra.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${hdtra.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>
                                <p><a href="/don-hang/da-tra-hang/${hdtra.id}" style="border-radius: 20px"
                                      class="btn btn-warning" onchange="submit()">Xác nhận</a>
                                    <a id="content6" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                       data-bs-target="#${hdtra.id}6" style="border-radius: 20px">Xem
                                    </a>
                                </p>

                                <div class="modal fade" id="${hdtra.id}6" data-bs-backdrop="static"
                                     data-bs-keyboard="false"
                                     tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="staticBackdropLabel111">Thông tin đơn
                                                    hàng</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <h5 class="title">Hóa đơn chi tiết</h5>
                                                <div class="row" style="margin-top: 20px">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
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
                                                            <c:forEach items="${hdtra.hoaDons}" varStatus="stt"
                                                                       var="hdctTraHang">
                                                                <tr>
                                                                    <th scope="row">${stt.index + 1}</th>
                                                                    <td>${hdctTraHang.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                    <td>
                                                                        <fmt:formatNumber value="${hdctTraHang.donGia}"
                                                                                          type="number"/>
                                                                    </td>
                                                                    <td>${hdctTraHang.soLuong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber
                                                                                value="${hdctTraHang.donGia * hdctTraHang.soLuong}"
                                                                                type="number"/>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Closes
                                                </button>
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
            <c:if test="${empty listDonTra.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>
                </td>
            </c:if>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonTra.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonTra.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonTra.totalPages-1}">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-dahoantra" role="tabpanel" aria-labelledby="pills-dahoantra-tab"
             tabindex="0">
            <c:if test="${not empty listDonDaTra.content}">

                <table class="table table-responsive-lg table-bordered">
                    <tr class="table-primary" style="background: #2c9faf;color: white ">
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
                    <c:forEach items="${listDonDaTra.content}" var="hddt">
                        <tr>
                            <td>${hddt.maHoaDon}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty hddt.tenNguoiNhan}">
                                        ${hddt.tenNguoiNhan}
                                    </c:when>
                                    <c:when test="${not empty hddt.khachHang.ten}">
                                        ${hddt.khachHang.ten}
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>${hddt.ngayTao}</td>
                            <td>${hddt.ngayThanhToan}</td>

                            <td>
                                <c:if test="${hddt.trangThai== 1  }">Chờ xác nhận</c:if>
                                <c:if test="${hddt.trangThai== 2 }">Đang chuẩn bị(xác nhận thanh toán)</c:if>
                                <c:if test="${hddt.trangThai== 3 }">Giao cho DVVC</c:if>
                                <c:if test="${hddt.trangThai== 4 }">Đang giao</c:if>
                                <c:if test="${hddt.trangThai== 5 }">Hoàn thành</c:if>
                                <c:if test="${hddt.trangThai== 6 }">Trả hàng</c:if>
                                <c:if test="${hddt.trangThai== 7 }">Đã hoàn trả</c:if>
                                <c:if test="${hddt.trangThai== 8 }">Đã huỷ</c:if>
                            </td>
                            <td>
                                <c:set var="total" value="0"/>
                                <c:forEach items="${hddt.hoaDons}" var="hdctDaTra">
                                    <c:set var="total" value="${total+(hdctDaTra.soLuong * hdctDaTra.donGia)}"/>
                                </c:forEach>
                                <fmt:formatNumber value="${total}" type="number"/>

                            </td>
                            <td>
                                <c:if test="${hddt.loaiHoaDon== 0 }"><span
                                        class="badge text-bg-info">Bán Online</span></c:if>
                                <c:if test="${hddt.loaiHoaDon== 1 }"><span
                                        class="badge text-bg-warning">Bán tại quầy</span></c:if>
                            </td>
                            <td>

                                <a id="content7" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#${hddt.id}7" style="border-radius: 20px">Xem
                                </a>
                                <div class="modal fade" id="${hddt.id}7" data-bs-backdrop="static"
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
                                                <h5 class="title">Hóa đơn chi tiết</h5>
                                                <div class="row" style="margin-top: 20px">
                                                    <div class="col-lg-1"></div>
                                                    <div class="col-lg-10">
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
                                                            <c:forEach items="${hddt.hoaDons}" varStatus="stt"
                                                                       var="hdctdatra">
                                                                <tr>
                                                                    <th scope="row">${stt.index + 1}</th>
                                                                    <td>${hdctdatra.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                    <td>
                                                                        <fmt:formatNumber value="${hdctdatra.donGia}"
                                                                                          type="number"/>
                                                                    </td>
                                                                    <td>${hd.soLuong}</td>
                                                                    <td>
                                                                        <fmt:formatNumber
                                                                                value="${hdctdatra.donGia * hdctdatra.soLuong}"
                                                                                type="number"/>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="modal-footer ">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                    Closes
                                                </button>
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
            <c:if test="${empty listDonDaTra.content}">
                <td colspan="8" class="text-center">
                    <img src="../../../img/order.png" style="width: 200px" class="text-center">
                    <span style="font-size: 50px">NO Data</span>
                </td>
            </c:if>
            <div class="text-center" id="myPagination">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonDaTra.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonDaTra.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listDonDaTra.totalPages-1}">Next</a></li>
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
<%--<script>--%>
<%--    function loadData(){--%>
<%--    window.location.href="/don-hang/list-all";--%>
<%--    }--%>
<%--</script>--%>
