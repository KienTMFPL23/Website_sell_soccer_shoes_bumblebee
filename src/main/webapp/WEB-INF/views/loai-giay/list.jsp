<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<style>
    .container {
        background-color: #f5f5f5;
        margin-top: 10px;
    }

    .row1 {
        color: white;
    }

    .search {
        width: 500px;
        background-color: #fff;
        border-radius: 15px;
        display: flex;
        justify-content: space-between;
    }

    .btn-timkiem {
        background-color: #34B6D3;
        width: 130px;
        border: none;
        border-radius: 15px 0px 0px 15px;
        font-weight: bold;
    }

    .inputSearch {
        padding-left: 16px;
        border: none;
        width: 370px;
        background-color: #fff;
        border-radius: 15px;
        outline: 0;
    }

    .inputSearch::placeholder {
        font-size: 14px;
    }

    .header {
        margin: 30px 0px;
        display: flex;
        justify-content: space-between;
    }

    .themSP {
        width: 140px;
        background-color: #fff;
        border: 1px solid #34B6D3;
        border-radius: 10px;
        display: flex;
        align-items: center;
        justify-content: space-evenly;
    }

    .themSP span {
        font-weight: 600;
    }

    .text-center {
        display: flex;
        margin-top: 30px;
        justify-content: center;
    }

    .page-item a {
        color: #000;
    }
</style>
<div class="container">

    <h2 STYLE="text-align: center;padding-top: 30px">DANH SÁCH LOẠI GIÀY</h2>
    <div class="header">
        <div class="themSP">
            <a href="/loai-giay/form">
                <img src="https://cdn.pixabay.com/photo/2017/03/19/03/51/material-icon-2155448_1280.png" width="50px">
                <span>Thêm mới</span>

            </a>
        </div>
        <form:form modelAttribute="searchForm">
            <div class="search">
                <button formaction="/admin/loai-giay" class="btn btn-success btn-timkiem">Tìm Kiếm</button>
                <form:input path="keyword" cssClass="inputSearch"
                            placeholder="bạn đang tìm kiếm sản phẩm..."></form:input>
            </div>
        </form:form>

    </div>
    <div><a href="/loai-giay/sort">Sắp xếp theo tên</a></div>
    <table class="table">
        <tr class="row1" style="background-color:#34B6D3 ">
            <th scope="col">STT</th>
            <th scope="col">ID</th>
            <th scope="col">Mã
            </th>
            <th scope="col">Loại Giày
                <a href="/loai-giay/sort" style="text-decoration: none; color: #FFFFFF">
                    <img src="https://icons.veryicon.com/png/o/internet--web/industrial-icon/sort-2.png" width="20px"
                         height="20px">
                </a></th>
            <th scope="col">Action</th>
        </tr>
        <c:forEach items="${page.content}" var="item" varStatus="i">
            <tr style="background-color: #fff">
                <td scope="col">${i.index + 1}</td>
                <td scope="col">${item.id}</td>
                <td scope="col">${item.ma}</td>
                <td scope="col">${item.tentheloai}</td>
                <td scope="col" class="action">
                    <a href="/loai-giay/edit/${item.id}"><img
                            src="https://cdn-icons-png.flaticon.com/512/1827/1827951.png" width="24px"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="text-center">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link"
                                         href="/admin/loai-giay?p=${page.number-1}&keyword=${param.keyword}">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="/admin/loai-giay?p=0&keyword=${param.keyword}">1</a>
                </li>
                <li class="page-item"><a class="page-link" href="/admin/loai-giay?p=1&keyword=${param.keyword}">2</a>
                </li>
                <li class="page-item"><a class="page-link" href="/admin/loai-giay?p=2&keyword=${param.keyword}">3</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="/admin/loai-giay?p=${page.number+1}&keyword=${param.keyword}">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>