<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <div class="row">
        <div class="col-lg-6">
            <a href="/de-giay/view-add" class="btnAdd">
                <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/> <p>Thêm mới</p>
            </a>
        </div>

        <div class="col-lg-6">
            <form action="/de-giay/search" method="get" >
                <div class="row mb-3">
                    <button type="submit" class="btnSearch">Tìm kiếm</button>
                    <input type="text" class="inputSearch" id="inputEmail3" name="keyword">
                </div>
            </form>
        </div>
    </div>


<table class="table">
    <thead>
    <tr style="color: #FFFFFF; background-color: #37517E; text-decoration: none;">
        <th scope="col">STT</th>
        <th scope="col">ID</th>
        <th scope="col">
            Mã
            <a href="/de-giay/sort" style="text-decoration: none; color: #FFFFFF">
                <img src="https://icons.veryicon.com/png/o/internet--web/industrial-icon/sort-2.png" width="20px" height="20px" >
            </a>
        </th>
        <th scope="col">Loại Đế</th>
        <th scope="col">Mô tả</th>
        <th scope="col">Trạng thái</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listDeGiay.content}" var="de" varStatus="i">
        <tr>
            <td>${i.index+1}</td>
            <td>${de.id}</td>
            <td>${de.ma}</td>
            <td>${de.loaiDe}</td>
            <td>${de.moTa}</td>
            <td>
                <c:if test="${de.trangThai == 1}">
                    Còn hàng
                </c:if>
                <c:if test="${de.trangThai == 0}">
                    Hết hàng
                </c:if>
            </td>
            <td>
                <a href="/de-giay/view-update/${de.id}" class="btn">
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
                    <li class="page-item"><a class="page-link" href="/de-giay/hien-thi?pageNum=0">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="/de-giay/hien-thi?pageNum=${page.number-1}"><<</a></li>
                    <li class="page-item"><a class="page-link" href="/de-giay/hien-thi?pageNum=${page.number+1}">>></a></li>
                    <li class="page-item"><a class="page-link" href="/de-giay/hien-thi?pageNum=${page.totalPages-1}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="col-lg-4"></div>
    </div>
</div>

</body>