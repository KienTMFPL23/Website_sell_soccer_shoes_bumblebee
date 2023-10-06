<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hóa Đơn</title>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
</head>
<style>
    .container {
        border: 1px solid #D9D9D9;
        border-radius: 15px;
        margin-top: 50px;
    }

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
    .input-text{
        border: 1px solid #767676;
        border-radius: 5px;
    }
</style>

<body>
<div class="container">
    <div class="row">
        <h3 class="title">Danh sách hóa đơn</h3>
        <div class="col-lg-1"></div>
        <div class="col-lg-2">
            <b>Mã hóa đơn</b>
            <input class="input-text" type="text"/>
        </div>
        <div class="col-lg-2">
            <b>Tên khách hàng</b>
            <input class="input-text" type="text"/>
        </div>
        <div class="col-lg-2">
            <b>Ngày bắt đầu</b>
            <input type="date">
        </div>
        <div class="col-lg-2">
            <b>Ngày kết thúc</b>
            <input type="date">
        </div>
        <div class="col-lg-2">
            <button id="but" type="submit" style="margin-top: 22px">Tìm Kiếm</button>
        </div>
        <div class="col-lg-1"></div>
    </div>
    <div class="row" style="margin-top: 27px">
        <div class="col-lg-1"></div>

        <div class="col-lg-10">
            <table class="table table-striped">
                <thead class="hoa-don-thead">
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Mã Hóa Đơn</th>
                    <th scope="col">Nhân Viên</th>
                    <th scope="col">Ngày Tạo</th>
                    <th scope="col">Ngày Thanh Toán</th>
                    <th scope="col">Tên Khách Hàng</th>
                    <th scope="col">Trạng Thái</th>
                </tr>
                </thead>

                <tbody>
                    <c:forEach var="hd" items="${list}" varStatus="stt">
                        <tr>
                            <th scope="row">${stt.index + 1}</th>
                            <td>${hd.maHoaDon}</td>
                            <td>${hd.nhanVien.ten}</td>
                            <td>${hd.ngayTao}</td>
                            <td>${hd.ngayThanhToan}</td>
                            <td>${hd.tenNguoiNhan}</td>
                            <td>${hd.trangThai == 0 ? 'Đã Thanh Toán':'Chưa Thanh Toán'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-1"></div>
    </div>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>

</html>