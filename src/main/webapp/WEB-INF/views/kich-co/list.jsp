<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<br>
<p>Add lightweight datatables to your project with using the <a href="https://github.com/fiduswriter/Simple-DataTables" target="_blank">Simple DataTables</a> library. Just add <code>.datatable</code> class name to any table you wish to conver to a datatable</p>

<div class="container">
    <div class="row">
        <div class="col-6 col-md-6 col-sm-6">
            <a href="/kich-co/view-add" class="btn btn-secondary"><i class="bi bi-plus-circle-fill"></i> Thêm Kích
                Cỡ</a>
            <br>
        </div>
        <div class="col-6 col-md-6 col-sm-6">

            <form:form action="/kich-co/search" modelAttribute="searchForm">
                <div class="row">
                    <div class="col-2 col-md-2 col-sm-5">
                        <button class="btn btn-secondary" type="submit">Search</button>
                    </div>
                    <div class="col-8 col-md-8 col-sm-7">
                        <form:input path="keyword" class="form-control" placeholder="Nhập mã hoặc loại size"/>
                    </div>
                </div>
            </form:form>
        </div>

    </div>

    <br>
    <table class="table table-bordered">
        <thead class="table ">
        <tr style="background: #37517E;color: white ">
            <th>#</th>
            <th>ID</th>
            <th>Mã</th>
            <th><a style="color: white; text-decoration: none" href="/kich-co/sort">Size</a></th>
            <th>Giới Tính</th>
            <th>Trạng Thái</th>
            <th>Loại size</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${page.content}" var="kc" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${kc.id}</td>
                <td>${kc.maKichCo}</td>
                <td>${kc.size}</td>
                <td>
                    <c:if test="${kc.gioiTinh==true}">Nam</c:if>
                    <c:if test="${kc.gioiTinh==false}">Nữ</c:if>
                </td>
                <td>${kc.trangThai==1?"Hoạt động":"Không hoạt động"}</td>
                <td>${kc.loaiSize}</td>
                <td>
                    <a href="/kich-co/view-update/${kc.id}" class="btn btn-warning"><i class="bi bi-pencil-square"></i></a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-center">
        <nav aria-label="Page navigation example" class="text-center">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href="/kich-co/hien-thi?p=0">Previous</a></li>
                <li class="page-item"><a class="page-link" href="/kich-co/hien-thi?p=${page.number-1}"><<</a></li>
                <li class="page-item"><a class="page-link" href="/kich-co/hien-thi?p=${page.number+1}">>></a></li>
                <li class="page-item"><a class="page-link" href="/kich-co/hien-thi?p=${page.totalPages-1}">Next</a></li>
            </ul>
        </nav>
    </div>
</div>