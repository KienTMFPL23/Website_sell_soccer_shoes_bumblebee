<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <h2 style="text-align: center">Thêm/Sửa sản phẩm</h2>
    <form:form modelAttribute="SP" method="post" action="${action}" cssClass="form">
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