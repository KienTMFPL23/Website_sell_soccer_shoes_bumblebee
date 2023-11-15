<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chất liệu</title>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
</head>
<style>
    body {
        font-family: Nunito;
    }

    .btnAdd {
        background-color: #EEEDED;
        margin-left: 50px;
        margin-bottom: 30px;
        border: 2px solid #37517E;
        border-radius: 10px;
        width: 150px;
        float: left;
        height: 40px;
        font-size: 18px;
        font-weight: 500;
        text-decoration: none;
        color: black;
        text-align: center;
    }

    .btnAdd:hover {
        background-color: #37517E;
        border: 2px solid #FFFFFF;
        color: #FFFFFF;
        text-decoration: none;
    }

    .btnAdd > img {
        margin-right: 10px;
        margin-top: 3px;
        margin-left: 15px;
        float: left;
    }

    .btnAdd > p {
        margin-top: 5px;
        margin-right: 10px;
    }

    .ui.table > thead > tr > th {
        background-color: #37517E;
        color: #FFFFFF;
        font-size: 18px;
    }

    .ui.form input[type=search] {
        background: #fff;
        border: 2px solid #37517E;
        border-radius: 20px;
        width: 400px;
    }

    .ui input {
        border-radius: 20px;
    }

    .ui.table > tbody > tr > td {
        font-size: 16px;
    }

</style>

<body>
<h1 style="text-align: center; font-family: Nunito">Quản Lý Khách Hàng</h1>


<div class="row">
    <div class="col-lg-6">
        <a href="/khach-hang/view-add" class="btnAdd">
            <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/>
            <p>Thêm mới</p>
        </a>
    </div>
</div>

<table class="ui celled table" width="100%">
    <thead>
    <tr>
        <th scope="col">Mã Khách Hàng</th>
        <th scope="col">Họ Tên</th>
        <th scope="col">Giới Tính</th>
        <th scope="col">Ngày Sinh</th>
        <th scope="col">Địa Chỉ</th>
        <th scope="col">SĐT</th>
        <th scope="col">Email</th>
        <th scope="col">Trạng Thái</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list.content}" var="kh">
        <td>${kh.ma}</td>
        <td>${kh.ten}</td>
        <td>${kh.gioiTinh == true ? "Nam" : "Nữ"}</td>
        <td>${kh.ngaySinh}</td>
        <td>${kh.diaChi}</td>
        <td>${kh.soDienThoai}</td>
        <td>${kh.email}</td>
        <td>${kh.trangThai == 1?"Hoạt Động" : "Ngưng hoạt động"}</td>
    </c:forEach>
    </tbody>
</table>
<div class="text-center">
    <nav aria-label="Page navigation example" class="text-center">
        <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/khach-hang/hien-thi?page=0">Previous</a>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/khach-hang/hien-thi?page=${list.number-1}"><<</a>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/khach-hang/hien-thi?page=${list.number+1}">>></a>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="${pageContext.request.contextPath}/khach-hang/hien-thi?page=${list.totalPages-1}">Next</a>
            </li>
        </ul>
    </nav>
</div>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
/>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</body>

</html>

