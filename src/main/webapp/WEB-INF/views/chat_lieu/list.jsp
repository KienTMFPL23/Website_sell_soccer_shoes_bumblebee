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
    .title-h3 {
        text-align: center;
        margin: 35px 0px;
        padding-top: 50px;
    }

    .thead-form tr th {
        margin-top: 150px;
        background-color: #37517E;
    }
    #icon-class-add{
        margin-bottom: 40px;
    }

    .icon-add {
        border: 1px solid #D9D9D9;
        border-radius: 13px;
    }
    .icon-add:hover{
        background: #37517E;
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

    .search {
        margin-top: 3px;
        border: 1px solid black;
        border-radius: 25px;

    }

    .search button {
        border: none;
        background: #37517E;
        font-weight: bold;
        border-radius: 15px;
        color: #D9D9D9;
    }

    .search input {
        margin-left: 6px;
        width: 320px;
        border-radius: 15px;
        border: none;
    }
    .text{
        font-weight: bold;
        color: black;
    }

</style>

<body>
<div class="container">
    <div class="row" id="icon-class-add">
        <h3 class="title-h3">Danh Sách Chất Liệu</h3>
        <div class="col-lg-1">

        </div>
        <div class="col-lg-3">
            <button class="icon-add"><a href="/chat-lieu/view-add" style="text-decoration: none">
                <div class="add-text"><img src="https://cdn-icons-png.flaticon.com/128/1828/1828817.png" alt=""/>
                    <span class="text">Thêm mới</span></div>
            </a>
            </button>
        </div>
        <div class="col-lg-2">
            <button class="icon-add">
                <a href="/chat-lieu/sort" method="get" style="text-decoration: none">
                    <div class="add-text"><img src="https://cdn-icons-png.flaticon.com/128/11463/11463063.png" alt=""/>
                        <span class="text">Sắp xếp</span>
                    </div>
                </a>
            </button>
        </div>
        <div class="col-lg-1">
        </div>
        <div class="col-lg-4">
            <div class="form">
                <sf:form  action="/chat-lieu/search" method="get" modelAttribute="searchForm">
                    <div class="search">
                        <button>Tìm Kiếm</button>
                        <sf:input path="keyword"/>
                    </div>
                </sf:form>
            </div>
        </div>
        <div class="col-lg-1">
        </div>
    </div>

    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-10">
            <table class="table">
                <thead class="thead-form">
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">ID</th>
                    <th scope="col">Mã Chất Liệu</th>
                    <th scope="col">Tên Chất Liệu</th>
                    <th scope="col">Trạng Thái</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list.content}" var="cl" varStatus="stt">
                        <tr>
                            <th scope="row">${stt.index +1}</th>
                            <td>${cl.id}</td>
                            <td>${cl.ma}</td>
                            <td>${cl.ten}</td>
                            <td>${cl.trangThai == 1? "Còn" : "Hết"}</td>
                            <td>
                                <button class="icon-update">
                                    <a href="/chat-lieu/edit/${cl.id}">
                                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCYRg6-Wukvj4tChzvnqe0FHoB2eDbrKN0-g&usqp=CAU"
                                        style="height: 25px;width: 25px"   alt=""/>
                                    </a>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-1"></div>
        <div class="text-center">
            <nav aria-label="Page navigation example">
                <ul class="pagination" style="padding-left: 500px">
                    <li class="page-item"><a class="page-link"
                                             href="/chat-lieu/hien-thi?page=0&keyword=${param.keyword}"
                                             >Previous</a></li>
                    <li class="page-item"><a class="page-link"
                                             href="/chat-lieu/hien-thi?page=${list.number-1}&keyword=${param.keyword}"
                                             >1</a></li>
                    <li class="page-item"><a class="page-link"
                                             href="/chat-lieu/hien-thi?page=${list.number+1}&keyword=${param.keyword}"
                                             >2</a></li>
                    <li class="page-item"><a class="page-link"
                                             href="/chat-lieu/hien-thi?page=${list.totalPages-1}&keyword=${param.keyword}"
                                             >Next</a></li>
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

</html>