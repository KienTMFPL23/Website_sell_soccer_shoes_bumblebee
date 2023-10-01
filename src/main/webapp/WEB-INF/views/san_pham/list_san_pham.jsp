<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>San Pham</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
    />
</head>
<body>
<div class="container">
    <h2 style="text-align: center">QUẢN LÝ SẢN PHẨM</h2>
    <div class="row">
        <div class="col-lg-3">
            <a class="btnAdd" href="/san-pham/view-add">
                <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/>
                <p>Thêm mới </p>
            </a>
        </div>
        <div class="col-9 col-md-9 col-sm-9">
            <form:form action="/san-pham/search" modelAttribute="search" method="post">
                <div class="search">
                    <button type="submit" class="btn btn-primary btn-search" >Tìm kiếm</button>
                    <form:input placeholder="Tìm kiếm theo mã hoặc tên sản phẩm" path="keyword" cssClass="inputSearch"/>
                </div>
            </form:form>
        </div>
    </div>
    <table class="table" border="border 1 px solid">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Mã sản phẩm</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Trạng thái</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.content}" varStatus="index" var="sp">
            <tr>
                <td>${index.count}</td>
                <td>${sp.maSanPham}</td>
                <td>${sp.tenSanPham}</td>
                <td>${sp.trangThai == 1 ? 'Hoạt động' : 'Ngừng hoạt động'}</td>
                <td>
                    <a href="/san-pham/view-update/${sp.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i>
                        <b>Chi tiết</b>
                    </a>
                    <a href="/chi-tiet-san-pham/view-add/${sp.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i>
                        <b>Thêm chi tiết sản phẩm</b>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div>
        <nav aria-label="Page navigation example" style="text-align: center">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?p=${page.number-1}">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?p=${page.number+1}">Next</a></li>
            </ul>
        </nav>
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