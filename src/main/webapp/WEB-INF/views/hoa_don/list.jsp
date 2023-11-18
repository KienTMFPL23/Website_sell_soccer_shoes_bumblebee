<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hóa Đơn</title>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ajaxy/1.6.1/scripts/jquery.ajaxy.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
</head>
<style>

    .hoa-don-thead tr th {
        background: #37517E;
        color: #FFFFFF;
        font-family: 'Inter', sans-serif;
    }

    .title {
        padding-top: 40px;
        margin-left: 110px;
    }

    #but {
        width: 120px;
        color: #FFFFFF;
        background: #37517E;
        border: none;
        border-radius: 5px;
    }


    .tbody-title tr td a {
        color: black;
        font-family: 'Inter', sans-serif;
        text-decoration: none;
    }

    .hoa-don-chi-tiet-thead tr th {
        background: #37517E;
        color: #FFFFFF;
        font-family: 'Inter', sans-serif;
    }

    .title {
        padding-top: 40px;
        margin-left: 110px;
    }


    #myInput {
        width: 350px;
        border: 2px solid #37517E;
        border-radius: 15px;
    }


    .start-input {
        width: 170px;
        height: 28.6px;
        border-radius: 15px;
        border: 1px solid #4e5b6c;
    }


    #searchButton {
        background: #37517E;
        width: 100px;
        border-radius: 10px;
        border: 0px;
        height: 30px;
        color: #FFFFFF;
    }

    .btnReset {
        margin-left: 50px;
        height: 28.6px;
        background-color: #FFFFFF;
        border: 2px solid #37517E;
        border-radius: 10px;
    }

    .-file-export {
        margin-left: 15px;
        height: 28.6px;
        background-color: #FFFFFF;
        border: 2px solid #37517E;
        border-radius: 10px;
    }

    .select-item {
        border-radius: 10px;
    }

    .selectTT {
        width: 150px;
        border-radius: 10px;
        padding: 2px;
    }

</style>

<body>

<div>
    <h3 style="text-align: center; margin-bottom: 30px;">Danh sách hóa đơn</h3>
</div>
<div class="row">
    <div class="col-lg-4">
        <input id="myInput" placeholder="Tìm kiếm theo mã, tên, số điện thoại" />
    </div>

    <div class="col-lg-6">
        <form:form action="/hoa-don/searchDate" method="post" modelAttribute="searchForm">
            <div class="row">
                <div class="col-lg-5">
                    Từ Ngày:
                    <form:input class="start-input" type="date" placeholder="dd/MM/yyyy" path="fromDate"/>
                </div>
                <div class="col-lg-5">
                    Đến Ngày:
                    <form:input class="start-input" type="date" placeholder="dd/MM/yyyy" path="toDate"/>
                </div>
                <div class="col-lg-2">
                    <button id="searchButton">Tìm</button>
                </div>
            </div>
        </form:form>
    </div>

    <div class="col-lg-2">
        <a href="/hoa-don/hien-thi">
            <button class="btnReset">
                <box-icon name='reset'></box-icon>
            </button>
        </a>
        <a href="/hoa-don/exportExcel">
            <button class="-file-export">
                <box-icon type='solid' name='file-export'></box-icon>
            </button>
        </a>
    </div>

    <div class="col-lg-3">
        <%--        <form:form action="/hoa-don/search-by-status" modelAttribute="searchForm" >--%>
        <%--            <label class="form-label">Trạng Thái: </label>--%>
        <%--            <form:select type="text" path="status" id="searchNameStatus">--%>
        <%--                <form:option value="1">Đã thanh toán</form:option>--%>
        <%--                <form:option value="2">Chờ thanh toán</form:option>--%>
        <%--            </form:select>--%>
        <%--        </form:form>--%>
        <form:form action="/hoa-don/search-by-status" modelAttribute="searchStatus">
            <form:select class="selectTT" path="status" onchange="submit()">
                <form:option value="0">Tất cả</form:option>
                <form:option value="1">Đã thanh toán</form:option>
                <form:option value="2">Chờ thanh toán</form:option>
            </form:select>
        </form:form>
    </div>
</div>

<div class="row" style="margin-top: 27px">
    <div class="col-lg-12">
        <table class="table table-striped">
            <thead class="hoa-don-thead">
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Mã Hóa Đơn</th>
                <th scope="col">Nhân Viên</th>
                <th scope="col">Ngày Tạo</th>
                <th scope="col">Ngày Thanh Toán</th>
                <th scope="col">Tên Khách Nhận</th>
                <th scope="col">Số Điện Thoại</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">Trạng Thái</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody class="tbody-title" id="hoaDonTbody">
            <c:forEach var="hd" items="${list.content}" varStatus="stt">
                <tr>
                    <td scope="row">${stt.index + 1}</td>
                    <td>${hd.maHoaDon}</td>
                    <td>${hd.nhanVien.ten}</td>
                    <td><fmt:formatDate value="${hd.ngayTao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${hd.ngayThanhToan}" pattern="dd/MM/yyyy HH:mm:ss"/></td>

                    <c:choose>
                        <c:when test="${hd.tenNguoiNhan == null && hd.sdt == null && hd.diaChiShip == null}">
                            <td>${hd.khachHang.ten}</td>
                            <td>${hd.khachHang.soDienThoai}</td>
                            <td>${hd.khachHang.diaChi}</td>
                        </c:when>

                        <c:otherwise>
                            <td>${hd.tenNguoiNhan}</td>
                            <td>${hd.sdt}</td>
                            <td>${hd.diaChiShip}</td>
                        </c:otherwise>
                    </c:choose>

                    <td>
                        <span>
                                ${hd.trangThai == 1 ? 'Đã thanh toán' : hd.trangThai == 2 ? 'chờ thanh toán' : ''}
                        </span>-
                        <select class="select-item" onchange="updateStatus('${hd.maHoaDon}',${hd.trangThai})"
                                id="${hd.maHoaDon}">`
                            <option value="1" ${hd.trangThai == 1 ? 'selected' : ''} id="${hd.maHoaDon}1">Đã thanh
                                toán
                            </option>
                            <option value="2" ${hd.trangThai == 2 ? 'selected' : ''} id="${hd.maHoaDon}2">Chờ thanh toán
                            </option>
                                <%--                            <option value="3" ${hd.trangThai == 3 ? 'selected' : ''} id="${hd.maHoaDon}2">Chờ xác nhận--%>
                                <%--                            </option>--%>
                        </select>
                    </td>
                    <td>

                        <a href="/hoa-don-chi-tiet/hien-thi/${hd.id}" id="content" class="btn btn-primary"
                           data-bs-toggle="modal"
                           data-bs-target="#${hd.id}" style="border-radius: 20px"
                        >Detail
                        </a>

                        <div class="modal fade" id="${hd.id}" data-bs-backdrop="static" data-bs-keyboard="false"
                             tabindex="-1"
                             aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Thông tin đơn hàng</h1>
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
                                            <%--                                        <h5 class="title" style="margin-top: 30px">Thông tin Khách Hàng</h5>--%>
                                            <%--                                        <div class="row" style="margin-top: 20px">--%>
                                            <%--                                            <div class="col-lg-1"></div>--%>
                                            <%--                                            <div class="col-lg-10">--%>
                                            <%--                                                <table class="table table-striped">--%>
                                            <%--                                                    <thead class="hoa-don-chi-tiet-thead">--%>
                                            <%--                                                    <tr>--%>
                                            <%--                                                        <th scope="col">Mã KH</th>--%>
                                            <%--                                                        <th scope="col">Tên Khách Hàng</th>--%>
                                            <%--                                                        <th scope="col">SĐT</th>--%>
                                            <%--                                                        <th scope="col">Địa Chỉ</th>--%>
                                            <%--                                                    </tr>--%>
                                            <%--                                                    </thead>--%>
                                            <%--                                                    <tbody>--%>
                                            <%--                                                        <c:choose>--%>
                                            <%--                                                            <c:when test="${hd.tenNguoiNhan == null && hd.sdt == null && hd.diaChiShip == null}">--%>
                                            <%--                                                                <td>${hd.khachHang.ten}</td>--%>
                                            <%--                                                                <td>${hd.khachHang.soDienThoai}</td>--%>
                                            <%--                                                                <td>${hd.khachHang.diaChi}</td>--%>
                                            <%--                                                            </c:when>--%>

                                            <%--                                                            <c:otherwise>--%>
                                            <%--                                                                <td>${hd.tenNguoiNhan}</td>--%>
                                            <%--                                                                <td>${hd.sdt}</td>--%>
                                            <%--                                                                <td>${hd.diaChiShip}</td>--%>
                                            <%--                                                            </c:otherwise>--%>
                                            <%--                                                        </c:choose>--%>

                                            <%--                                                    </tbody>--%>
                                            <%--                                                </table>--%>
                                            <%--                                            </div>--%>
                                            <%--                                            <div class="col-lg-1"></div>--%>
                                            <%--                                        </div>--%>
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
        <div class="text-center">
            <nav aria-label="Page navigation example" class="text-center">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/hoa-don/hien-thi?page=0">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/hoa-don/hien-thi?page=${list.number-1}"><<</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/hoa-don/hien-thi?page=${list.number+1}">>></a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/hoa-don/hien-thi?page=${list.totalPages-1}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<!-- -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>

<script type="text/javascript">
    function updateStatus(ma, trangThai) {
        let size = document.getElementById(ma)
        console.log(size.value);
        if (confirm("Bạn có chắc ?")) {
            $.post('http://localhost:8080/hoa-don/update/' + ma, size.value,
                function (response) {
                    console.log(size.value);
                    window.location.href = "http://localhost:8080/hoa-don/hien-thi";
                    window.location.reset();
                }
            ).fail(function (error) {
                console.error("lỗi", error);
            })
        }
        var select = document.getElementById(ma + trangThai);
        select.selected = true
    }

    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#hoaDonTbody tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

</script>
</html>