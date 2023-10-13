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

    #kh-input {
        margin-left: 60px;
    }

    #kh-input input {
        border: 1px solid #4e5b6c;
        padding: 3px;
        border-radius: 5px;
    }

    #kh-input button {
        margin-bottom: 5px;
    }

    span {
        font-weight: bold;
    }


    .select-item {
        border: 1px solid #96dbe4;
        padding: 3px;
        border-radius: 5px;
    }

    #start-input {
        padding-top: 5px;
    }

    #start-input2 {
        padding-top: 5px;
    }

    #start-input input {
        width: 170px;
        height: 28.6px;
        border-radius: 5px;
        border: 1px solid #4e5b6c;
    }

    #start-input2 input {
        width: 170px;
        height: 28.6px;
        border-radius: 5px;
        border: 1px solid #4e5b6c;
    }

    #searchButton {
        display: flex;
        margin-top: 22px;
    }

    #reset{
        margin-top: 13px;
    }

</style>

<body>

<div class="row">
    <h3 class="title">Danh sách hóa đơn</h3>
</div>
<div class="row">
    <div class="col-lg-5">
        <div class="form">
            <form:form action="/hoa-don/search" modelAttribute="searchForm">
                <div class="row">
                    <div id="kh-input">
                        <span>Khách Hàng</span> </br>
                        <form:input path="keyword"/>
                        <button class="btn btn-success" style="margin-left: 10px">Tìm</button>
                    </div>
                </div>
            </form:form>
        </div>

    </div>
    <div class="col-lg-5">
        <div class="form">
            <form:form action="/hoa-don/searchDate" method="post" modelAttribute="searchForm">
                <div class="row">
                    <div class="col-lg-4" id="start-input">
                        <span>Từ Ngày</span> </br>
                        <form:input type="date" placeholder="dd/MM/yyyy" path="fromDate"/>
                    </div>
                    <div class="col-lg-4" id="start-input2">
                        <span>Đến Ngày</span> </br>
                        <form:input type="date" placeholder="dd/MM/yyyy" path="toDate"/>
                    </div>
                    <div class="col-lg-1" id="searchButton">
                        <button class="btn btn-success">Tìm</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
    <div class="col-lg-2" id="reset">
        <a href="/hoa-don/hien-thi">
            <button class="btn btn-primary" style="margin-left: 10px">
                <box-icon name='reset'></box-icon>
            </button>
        </a>
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
                <th scope="col">Tên Khách Hàng</th>
                <th scope="col">Số Điện Thoại</th>
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
                    <td>${hd.ngayTao}</td>
                    <td>${hd.ngayThanhToan}</td>
                    <td>${hd.tenNguoiNhan}</td>
                    <td>${hd.sdt}</td>
                    <td>
                        <span>${hd.trangThai == 1 ? 'Đã Thanh Toán' : 'Đang Xử Lý'}</span>-
                        <select class="select-item" onchange="updateStatus('${hd.maHoaDon}',${hd.trangThai})"
                                id="${hd.maHoaDon}">
                            <option value="1" ${hd.trangThai == 1 ? 'selected' : ''} id="${hd.maHoaDon}1">Đã Thanh
                                Toán
                            </option>
                            <option value="2" ${hd.trangThai == 2 ? 'selected' : ''} id="${hd.maHoaDon}2">Đang Xử Lý
                            </option>
                        </select>
                    </td>
                    <td>
                        <button id="content" type="submit" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#${hd.id}" style="border-radius: 20px" onclick="click(${hd.id})"
                        >Detail
                        </button>
                        <div class="modal fade" id="${hd.id}" data-bs-backdrop="static" data-bs-keyboard="false"
                             tabindex="-1"
                             aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h2 class="title">Thông tin đơn hàng</h2>
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
                                                        <%--                                                    <c:forEach var="ma" items="${hd.khachHang}">--%>
                                                    <tr>
                                                        <td>${hd.khachHang.ma}</td>
                                                        <td>${hd.khachHang.ten}</td>
                                                        <td>${hd.khachHang.soDienThoai}</td>
                                                        <td>${hd.khachHang.diaChi}</td>
                                                    </tr>
                                                        <%--                                                    </c:forEach>--%>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col-lg-1"></div>
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
        <div class="text-center">
            <nav aria-label="Page navigation example" class="text-center">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="/hoa-don/hien-thi?page=0&keyword=${param.keyword}">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="/hoa-don/hien-thi?page=${list.number-1}&keyword=${param.keyword}"><<</a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="/hoa-don/hien-thi?page=${list.number+1}&keyword=${param.keyword}">>></a>
                    </li>
                    <li class="page-item"><a class="page-link"
                                             href="//hoa-don/hien-thi?page=${list.totalPages-1}&keyword=${param.keyword}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>


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


</script>
</html>

