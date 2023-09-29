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

    body{
        font-family: Nunito;
    }

    .btnAdd {
        background-color: #EEEDED;
        margin-left: 50px;
        margin-bottom: 30px;
        border: 1px solid #37517E;
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


    .btnAdd:hover{
        background-color: #37517E;
        border: 1px solid #FFFFFF;
        color: #FFFFFF;
        text-decoration: none;
    }

    .btnAdd>img {
        margin-right: 10px;
        margin-top: 5px;
        margin-left: 15px;
        float: left;
    }

    .btnAdd>p{
        margin-top: 5px;
        margin-right: 10px;
    }

    .btnSearch {
        border: 0;
        border-bottom-left-radius: 20px;
        border-top-left-radius: 20px;
        height: 45px;
        background-color: #37517E;
        color: #FFFFFF;
        width: 100px;
        height: 40px;
    }

    .inputSearch{
        height: 40px;
        width: 400px;
        border: 1px solid #37517E;
        border-bottom-right-radius: 20px;
        border-top-right-radius: 20px;
    }
</style>
<br>
<body>
<h1 style="text-align: center; margin-top: -20px; margin-bottom: 20px;">QUẢN LÝ ĐẾ GIÀY</h1>
<body>

<div class="container">

    <div class="row">
        <div class="col-lg-6">
            <a href="/mau-sac/hien-thi-add" class="btnAdd">
                <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/> <p>Thêm mới</p>
            </a>
        </div>

        <form th:action="mau-sac/hien-thi" >
        <div class="col-lg-6">

                <div class="row mb-3">
                    <button type="submit" class="btnSearch">Tìm kiếm</button>
                    <input type="text" class="inputSearch" id="inputEmail3" name="key" th:value="${key}" required>
                </div>
        </div>
    </form>
    </div>
    <h1 style="text-align: center">Bảng Màu Sắc</h1>
    <table class="table">
        <thead>
        <tr style="color: #FFFFFF; background-color: #37517E; text-decoration: none;">
        <tr>
            <th scope="col">#</th>
            <th scope="col">
                <a href="/mau-sac/sort" style="text-decoration: none; color: #FFFFFF">
                    <img src="https://icons.veryicon.com/png/o/internet--web/industrial-icon/sort-2.png" width="20px" height="20px" >
                </a> Mã</th>
            <th scope="col">Tên</th>
            <th scope="col">Trạng Thái</th>
            <th scope="col">Chức Năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list.getContent()}" var="ms" varStatus="stt">
            <tr>
                <td scope="col">${stt.index+1}</td>
                <td scope="col">${ms.ma}</td>
                <td scope="col">${ms.ten}</td>
                <td scope="col">${ms.tt == 1?"hoạt động":"ko hoạt động"}</td>
                <td scope="col">

                    <a href="update/${ms.id}" class="btn">
                        <i class='bx bxs-edit' style="font-size: 23px;"></i>
                    </a>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <div class="text-center" style="margin-top: 50px;">
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="/mau-sac/hien-thi?pageNum=0">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="/mau-sac/hien-thi?pageNum=${page.number-1}"><<</a></li>
                        <li class="page-item"><a class="page-link" href="/mau-sac/hien-thi?pageNum=${page.number+1}">>></a></li>
                        <li class="page-item"><a class="page-link" href="/mau-sac/hien-thi?pageNum=${page.totalPages-1}">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-4"></div>
        </div>
    </div>

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