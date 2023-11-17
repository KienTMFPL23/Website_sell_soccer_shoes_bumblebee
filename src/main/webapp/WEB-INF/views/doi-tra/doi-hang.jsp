<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<form:form action="/bumblebee/don-hang/search" method="post" modelAttribute="SearchForm">
    <div class="row">
        <div class="col-lg-6">
            <div class="input-group">
                <form:input
                        type="text"
                        class="form-control "
                        placeholder="Tìm kiếm hóa đơn"
                        aria-label="Search"
                        aria-describedby="basic-addon2"
                        path="key"
                        style="width: 50px"
                />
                <div class="input-group-append">
                    <button class="btn btn-primary" type="submit">
                        <i class="fas fa-search fa-sm"></i>
                    </button>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <a class="btn btn-primary"></a>
        </div>
    </div>
</form:form>
<table class="table border 1px solid" style="margin-top: 10px">
    <thead>
    <tr>
        <th scope="col">STT</th>
        <th scope="col">Tên sản phẩm</th>
        <th scope="col">Màu sắc</th>
        <th scope="col">Kích cỡ</th>
        <th scope="col">Số lượng</th>
        <th scope="col">Đơn giá</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="hd" items="${listHDCT}" varStatus="i">
        <tr>
            <td>${i.count}</td>
            <td>${hd.chiTietSanPham.sanPham.tenSanPham}</td>
            <td>${hd.chiTietSanPham.mauSac.ten}</td>
            <td>${hd.chiTietSanPham.kichCo.size}</td>
            <td>${hd.soLuong}</td>
            <td><fmt:formatNumber value="${hd.donGia}" type="number"/></td>
            <td><a class="btn btn-success">Đổi sản phẩm</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>