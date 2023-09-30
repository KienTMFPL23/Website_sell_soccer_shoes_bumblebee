<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>San Pham</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
    />
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet"
    />
</head>
<body>
<div class="container">
    <h2 style="text-align: center">Thêm/Sửa sản phẩm</h2>
    <form:form modelAttribute="SP" method="post" action="${action}">
        <p><form:input path="id" type="hidden"/></p>
        <div class="mb-3">
            <b class="form-label">Mã sản phẩm:</b>
            <form:input path="maSanPham" class="form-control"/>
            <form:errors path="maSanPham" cssStyle="color: red" element="div"/>
            <p style="color: red">${maspError}</p>
        </div>
        <div class="mb-3">
            <b class="form-label">Tên sản phẩm:</b>
            <form:input path="tenSanPham" class="form-control"/>
            <form:errors path="tenSanPham" cssStyle="color: red" element="div"/>
        </div>
        <div class="mb-3">
            <b class="form-label">Trạng thái</b>
            <div class="col-sm-8">
                <form:radiobuttons  items="${dsTrangThai}" path="trangThai" class="radioButton"/>
                <form:errors path="trangThai" cssStyle="color: crimson"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit" onclick="return confirm('Xác nhận')">Submit</button>
        <a class="btn btn-primary" href="/san-pham/hien-thi">Return</a>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>