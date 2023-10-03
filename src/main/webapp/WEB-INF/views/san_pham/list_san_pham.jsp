<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>

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
                    <button type="submit" class="btn btn-primary btn-search">Tìm kiếm</button>
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
                    <a href="/chi-tiet-san-pham/view-add/${sp.id}" class="btn btn-primary"><i
                            class="bi bi-pencil-square"></i>
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
