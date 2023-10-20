<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
        width: 100px;
        border-radius: 10px;
        border: 0px;
        height: 30px;
        color: #FFFFFF;
        margin-top: 22px;
    }

    .btnReload {
        background: #37517E;
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
                <button class="nav-link rounded-5" id="pills-choxacnhan-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-choxacnhan" type="button" role="tab" aria-controls="pills-choxacnhan"
                        aria-selected="false">Chờ xác nhận
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-chuanbi-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-chuanbi"
                        type="button" role="tab" aria-controls="pills-chuanbi" aria-selected="false">Đang chuẩn bị
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-danggiao-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-danggiao"
                        type="button" role="tab" aria-controls="pills-danggiao" aria-selected="false">Đang giao
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-hoanthanh-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-hoanthanh" type="button" role="tab" aria-controls="pills-hoanthanh"
                        aria-selected="false">Hoàn thành
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-dahuy-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-dahuy"
                        type="button" role="tab" aria-controls="pills-dahuy" aria-selected="false">Đã huỷ
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-trahang-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-trahang"
                        type="button" role="tab" aria-controls="pills-trahang" aria-selected="false">Trả hàng
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-dahoantra-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-dahoantra"
                        type="button" role="tab" aria-controls="pills-dahoantra" aria-selected="false">Đã hoàn trả
                </button>
            </li>
        </ul>
    </div>
    </br>
    <div class="row">

        <div class="col-lg-10">
            <form:form action="/don-hang/searchDate" method="post" modelAttribute="searchForm">
                <div class="row">
                    <div class="col-lg-4">
                        Từ Ngày:
                        <form:input class="form-control" type="date" placeholder="dd/MM/yyyy" path="fromDate"/>
                    </div>
                    <div class="col-lg-4">
                        Đến Ngày:
                        <form:input class="form-control" type="date" placeholder="dd/MM/yyyy" path="toDate"/>
                    </div>
                    <div class="col-lg-2">
                        <button id="searchButton">Tìm</button>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-lg-2">
            <a href="/don-hang/list-all" class=" btn btn-primary btnReload">
                <i class="bi bi-arrow-clockwise"></i>
            </a>
        </div>
    </div>
    </br>
    <div class="tab-content" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-all-tab"
             tabindex="0">

            </br>
            </hr>
            <%--            <c:if test="${not empty page.content}">--%>

            <table class="table table-bordered">
                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>Tổng tiền</th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">
                <c:forEach items="${page.content}" var="hd">
                    <tr>
                        <td>${hd.maHoaDon}</td>
                        <td>${hd.khachHang.ma}</td>
                        <td>${hd.diaChiShip}</td>

                        <td>${hd.ngayTao}</td>
                        <td>${hd.ngayThanhToan}</td>

                        <td>
                            <c:if test="${hd.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${hd.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${hd.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${hd.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${hd.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${hd.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${hd.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${hd.hoaDons}" var="hdct">
                                <c:set var="total" value="${total+(hdct.soLuong * hdct.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${hd.ghiChu}</td>
                        <td>

                            <button id="content" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#${hd.id}" style="border-radius: 20px">Xem
                            </button>
                            <div class="modal fade" id="${hd.id}" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${hd.hoaDons}" varStatus="stt" var="hd">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hd.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hd.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hd.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hd.donGia * hd.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <h5 class="title" style="margin-top: 30px">Thông tin Khách Hàng</h5>
                                            <div class="row" style="margin-top: 20px">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-10">
                                                    <table class="table table-striped">
                                                        <thead class="hoa-don-chi-tiet-thead">
                                                        <tr>
                                                            <th scope="col">Mã KH</th>
                                                            <th scope="col">Tên Khách Hàng</th>
                                                            <th scope="col">SĐT</th>
                                                            <th scope="col">Địa Chỉ</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%--                                                     <c:forEach var="hd" items="${hd.khachHang}">--%>
                                                        <tr>
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                        </tr>
                                                            <%--                                                     </c:forEach>--%>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="col-lg-1"></div>
                                            </div>
                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
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
            <table class="table table-bordered">

                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>Tổng tiền</th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">
                <c:forEach items="${listChoXacNhan.content}" var="hd1">
                    <tr>
                        <td>${hd1.maHoaDon}</td>
                        <td>${hd1.khachHang.ma}</td>
                        <td>${hd1.diaChiShip}</td>

                        <td>${hd1.ngayTao}</td>
                        <td>${hd1.ngayThanhToan}</td>

                        <td>
                            <c:if test="${hd1.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${hd1.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${hd1.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${hd1.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${hd1.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${hd1.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${hd1.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${hd1.hoaDons}" var="hdct">
                                <c:set var="total" value="${total+(hdct.soLuong * hdct.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${hd1.ghiChu}</td>
                        <td>
                            <p><a href="/don-hang/update-xac-nhan/${hd1.id}" style="border-radius: 20px"
                                  class="btn btn-warning">Xác nhận</a>
                                <a id="content1" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#${hd1.id}1" style="border-radius: 20px">Xem
                                </a>
                            </p>
                            <span><a href="/don-hang/huy-don-hang/${hd1.id}" class="btn btn-danger"
                                     style="border-radius: 20px">Huỷ</a>
                            </span>

                            <div class="modal fade" id="${hd1.id}1" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${hd1.hoaDons}" varStatus="stt" var="hdct">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hdct.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdct.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hdct.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdct.donGia * hdct.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
            <div class="text-center">
                <nav aria-label="Page navigation text-center">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="/don-hang/list-all?p=0">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChoXacNhan.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChoXacNhan.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/don-hang/list-all?p=${listChoXacNhan.totalPages-1}">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="tab-pane fade" id="pills-chuanbi" role="tabpanel" aria-labelledby="pills-chuanbi-tab" tabindex="0">

            <table class="table table-bordered">

                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>Tổng tiền</th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">
                <c:forEach items="${listChuanBi.content}" var="hdcb">
                    <tr>
                        <td>
                                ${hdcb.maHoaDon}
                        </td>
                        <td>${hdcb.khachHang.ma}</td>
                        <td>${hdcb.diaChiShip}</td>

                        <td>${hdcb.ngayTao}</td>
                        <td>${hdcb.ngayThanhToan}</td>

                        <td>
                            <c:if test="${hdcb.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${hdcb.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${hdcb.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${hdcb.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${hdcb.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${hdcb.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${hdcb.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${hdcb.hoaDons}" var="hdctcb">
                                <c:set var="total" value="${total+(hdctcb.soLuong * hdctcb.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${hdcb.ghiChu}</td>
                        <td>
                            <span>   <a id="content2" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                        data-bs-target="#${hdcb.id}2" style="border-radius: 20px">Xem
                            </a><a href="/don-hang/update-chuan-bi/${hdcb.id}" style="border-radius: 20px"
                                   class="btn btn-warning">Giao hàng</a>

                            </span>
                            <span><a href="/don-hang/huy-don-hang/${hdcb.id}" class="btn btn-danger"
                                     style="border-radius: 20px">Huỷ</a>
                            </span>
                            <div class="modal fade" id="${hdcb.id}2" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${hdcb.hoaDons}" varStatus="stt" var="hdctdb2">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hdctdb2.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctdb2.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hdctdb2.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctdb2.donGia * hdctdb2.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
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
            <table class="table table-bordered">

                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>Tổng tiền</th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">
                <c:forEach items="${listDonGiao.content}" var="hd3">
                    <tr>
                        <td>${hd3.maHoaDon}</td>
                        <td>${hd3.khachHang.ma}</td>
                        <td>${hd3.diaChiShip}</td>

                        <td>${hd3.ngayTao}</td>
                        <td>${hd3.ngayThanhToan}</td>

                        <td>  <c:if test="${hd3.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${hd3.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${hd3.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${hd3.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${hd3.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${hd3.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${hd3.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${hd3.hoaDons}" var="hdct3">
                                <c:set var="total" value="${total+(hdct3.soLuong * hdct3.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${hd3.ghiChu}</td>
                        <td>
                            <p>
                                <a id="content3" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#${hd3.id}3" style="border-radius: 20px">Xem
                                </a><a href="/don-hang/dang-giao/${hd3.id}" style="border-radius: 20px"
                                       class="btn btn-warning">Hoàn Thành</a>
                            </p>

                            <div class="modal fade" id="${hd3.id}3" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${hd3.hoaDons}" varStatus="stt" var="hdct33">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hdct33.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdct33.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hd.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdct33.donGia * hdct33.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>


                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
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
            <table class="table table-bordered">

                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>Tổng tiền</th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">
                <c:forEach items="${listHoanThanh.content}" var="hdht">
                    <tr>
                        <td>${hdht.maHoaDon}</td>
                        <td>${hdht.khachHang.ma}</td>
                        <td>${hdht.diaChiShip}</td>

                        <td>${hdht.ngayTao}</td>
                        <td>${hdht.ngayThanhToan}</td>

                        <td>
                            <c:if test="${hdht.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${hdht.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${hdht.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${hdht.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${hdht.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${hdht.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${hdht.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${hdht.hoaDons}" var="hdctht">
                                <c:set var="total" value="${total+(hdctht.soLuong * hdctht.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${hdht.ghiChu}</td>
                        <td>
                            <p><a href="/don-hang/tra-hang/${hdht.id}" style="border-radius: 20px"
                                  class="btn btn-warning">Trả hàng</a>
                                <a id="content4" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#${hdht.id}4" style="border-radius: 20px">Xem
                                </a>
                            </p>

                            <div class="modal fade" id="${hdht.id}4" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${hdht.hoaDons}" varStatus="stt" var="hdctht1">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hdctht1.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctht1.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hdctht1.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctht1.donGia * hdctht1.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>


                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
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

            <table class="table table-bordered">

                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>
                        <c:set var="total" value="0"/>
                        <c:forEach items="${hd.hoaDons}" var="hdct">
                            <c:set var="total" value="${total+(hdct.soLuong * hdct.donGia)}"/>
                        </c:forEach>
                        ${total}
                    </th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>
                <tbody id="myTable">
                <c:forEach items="${listHuy.content}" var="donHuy">
                    <tr>
                        <td>${donHuy.maHoaDon}</td>
                        <td>${donHuy.khachHang.ma}</td>
                        <td>${donHuy.diaChiShip}</td>

                        <td>${donHuy.ngayTao}</td>
                        <td>${donHuy.ngayThanhToan}</td>

                        <td>
                            <c:if test="${donHuy.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${donHuy.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${donHuy.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${donHuy.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${donHuy.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${donHuy.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${donHuy.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${donHuy.hoaDons}" var="hdctHuy">
                                <c:set var="total" value="${total+(hdctHuy.soLuong * hdctHuy.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${donHuy.ghiChu}</td>
                        <td>
                            <p>

                                <a id="content5" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#${donHuy.id}5" style="border-radius: 20px">Xem
                                </a>
                            </p>


                            <div class="modal fade" id="${donHuy.id}5" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${donHuy.hoaDons}" varStatus="stt" var="hdDaHuy">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hdDaHuy.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdDaHuy.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hdDaHuy.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdDaHuy.donGia * hdDaHuy.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>


                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
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
            dna
            </br>
            </hr>
            <%--            <c:if test="${not empty page.content}">--%>

            <table class="table table-bordered">

                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>Tổng tiền</th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">
                <c:forEach items="${listDonTra.content}" var="hdtra">
                    <tr>
                        <td>${hdtra.maHoaDon}</td>
                        <td>${hdtra.khachHang.ma}</td>
                        <td>${hdtra.diaChiShip}</td>

                        <td>${hdtra.ngayTao}</td>
                        <td>${hdtra.ngayThanhToan}</td>

                        <td>
                            <c:if test="${hdtra.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${hdtra.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${hdtra.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${hdtra.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${hdtra.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${hdtra.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${hdtra.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${hdtra.hoaDons}" var="hdctTra">
                                <c:set var="total" value="${total+(hdctTra.soLuong * hdctTra.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${hdtra.ghiChu}</td>
                        <td>
                            <p><a href="/don-hang/da-tra-hang/${hdtra.id}" style="border-radius: 20px"
                                  class="btn btn-warning">Xác nhận</a>
                                <a id="content6" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                   data-bs-target="#${hdtra.id}6" style="border-radius: 20px">Xem
                                </a>
                            </p>

                            <div class="modal fade" id="${hdtra.id}6" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${hdtra.hoaDons}" varStatus="stt" var="hdctTraHang">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hdctTraHang.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctTraHang.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hdctTraHang.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctTraHang.donGia * hdctTraHang.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>


                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
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
             tabindex="0">dak
            <table class="table table-bordered">

                <tr style="background-color: #2c9faf;color: white ">
                    <th>Mã hoá đơn</th>
                    <th>Mã khách hàng</th>
                    <th>Địa chỉ</th>
                    <th>Ngày tạo</th>
                    <th>Ngày cập nhật</th>
                    <th>Trạng Thái</th>
                    <th>Tổng tiền</th>
                    <th>Ghi chú</th>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">
                <c:forEach items="${listDonDaTra.content}" var="hddt">
                    <tr>
                        <td>${hddt.maHoaDon}</td>
                        <td>${hddt.khachHang.ma}</td>
                        <td>${hddt.diaChiShip}</td>

                        <td>${hddt.ngayTao}</td>
                        <td>${hddt.ngayThanhToan}</td>

                        <td>
                            <c:if test="${hddt.trangThai== 1  }">Chờ xác nhận</c:if>
                            <c:if test="${hddt.trangThai== 2 }">Đang chuẩn bị</c:if>
                            <c:if test="${hddt.trangThai== 3 }">Đang giao</c:if>
                            <c:if test="${hddt.trangThai== 4 }">Hoàn thành</c:if>
                            <c:if test="${hddt.trangThai== 5 }">Đã huỷ</c:if>
                            <c:if test="${hddt.trangThai== 6 }">Trả hàng</c:if>
                            <c:if test="${hddt.trangThai== 7 }">Đã hoàn trả</c:if>
                        </td>
                        <td>
                            <c:set var="total" value="0"/>
                            <c:forEach items="${hddt.hoaDons}" var="hdctDaTra">
                                <c:set var="total" value="${total+(hdctDaTra.soLuong * hdctDaTra.donGia)}"/>
                            </c:forEach>
                                ${total}
                        </td>
                        <td>${hddt.ghiChu}</td>
                        <td>

                            <a id="content7" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                               data-bs-target="#${hddt.id}7" style="border-radius: 20px">Xem
                            </a>

                            </span>
                            <div class="modal fade" id="${hddt.id}7" data-bs-backdrop="static" data-bs-keyboard="false"
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
                                                        <c:forEach items="${hddt.hoaDons}" varStatus="stt" var="hdctdatra">
                                                            <tr>
                                                                <th scope="row">${stt.index + 1}</th>
                                                                <td>${hdctdatra.chiTietSanPham.sanPham.tenSanPham}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctdatra.donGia}"
                                                                                      type="number"/>
                                                                </td>
                                                                <td>${hd.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber value="${hdctdatra.donGia * hdctdatra.soLuong}"
                                                                                      type="number"/>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-footer left">
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
            <%--            </c:if>--%>
            <%--            <c:if test="${empty page.content}">--%>
            <%--                <td colspan="8" class="text-center">Không có sản phẩm.</td>--%>
            <%--            </c:if>--%>
            <div class="text-center">
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
