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
<div class="container">
    <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-all-tab"
         tabindex="0">

        </br>
        </hr>


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
</div>