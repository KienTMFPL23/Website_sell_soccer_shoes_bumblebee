<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<%@page language="java" pageEncoding="UTF-8" %>
<head>
    <title>Bảng Màu Sắc </title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
/>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
    .title-h3 {
        text-align: center;
        margin: 35px 0px;
        padding-top: 50px;
    }

    .thead-form tr th {
        margin-top: 150px;
        background-color: #37517E;
    }

    #icon-class-add {
        margin-bottom: 40px;
    }

    .icon-add {
        border: 1px solid #D9D9D9;
        border-radius: 13px;
        margin-left: 30px;
    }

    .icon-add:hover {
        background: #37517E;
    }

    #add-icon-id {
        margin: auto;
    }

    .add-text {
        text-decoration: none;
    }

    .icon-add img {
        width: 35px;
        height: 35px;
    }

    .icon-update {
        border: none;
    }

    .icon-update img {
        width: 35px;
        height: 35px;
    }

    .search input {
        padding: 10px;
        border: 1px solid #D9D9D9;
        width: 410px;
        background: #D9D9D9;
        border-radius: 15px;
    }

    .text {
        font-weight: bold;
        color: black;
    }

</style>

<br>
<body>
<%--<h1 style="text-align: center; margin-top: -20px; margin-bottom: 20px;">QUẢN LÝ MÀU SẮC</h1>--%>
<body>

<div class="container">

<%--    <form action="/mau-sac/import" method="post" enctype="multipart/form-data">--%>
<%--        <input type="file" name="file" accept=".xls,.xlsx"/>--%>
<%--        <button type="submit">Import</button>--%>
<%--    </form>--%>

    <div class="row" id="icon-class-add">
        <h3 class="title-h3">Danh Sách Màu Sắc</h3>
        <div class="col-lg-1">
        </div>
        <div class="col-lg-6">
            <button class="icon-add" id="add-icon-id"><a href="/hien-thi-add" style="text-decoration: none">
                <div class="add-text"><img src="https://cdn-icons-png.flaticon.com/128/1828/1828817.png" alt=""/>
                    <span class="text">Thêm mới</span></div>
            </a>
            </button>
        </div>
        <div class="col-lg-1">
        </div>
    </div>

    <table id="tableMauSac" class="ui celled table" width="100%">
        <thead>
        <tr>
            <th scope="col">Mã</th>
            <th scope="col">Tên Màu </th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
    </table>
</div>
<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"
        integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    function clearFilter(){
        window.location='/mau-sac/hien-thi';
    }
</script>
</body>
</html>