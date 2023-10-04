<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<style>
    body {
        background-color: #D9D9D9;
    }

    .formAdd {
        width: 700px;
        height: 600px;
        background-color: #37517E;
        border-radius: 20px;
        color: #FFFFFF;
        font-family: "Nunito";
    }

    .row.mb-3{
        margin-top: 30px;
    }
    .button {
        background-color: #FFFFFF;
        color: black;
        font-weight: bold;
        width: 120px;
        height: 40px;
        border-radius: 10px;
        border: 0px;
        margin-top: 30px;
        margin: auto;
        display: block;

    }

    label{
        font-size: 17px;
    }

    .error{
        color: red;
        font-size: 15px;
        padding-left: 190px;
        padding-top: 15px;
    }
</style>
<div class="formAdd">
    <h1 style="text-align: center; padding-top: 20px;">SỬA LOẠI GIÀY</h1>
    <form:form modelAttribute="loaiGiay" method="post" id="formAddUpdate">
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Mã:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control"  path="ma" />
                <form:errors path="ma"></form:errors>
                <div style="margin-left: 10px;color: red">${errorMa}</div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Loại Giày:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control"  path="tentheloai" />
                <form:errors path="tentheloai"></form:errors>
                <div style="margin-left: 10px;color: red">${errorTen}</div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Trạng thái:</label>
            </div>
            <div class="col-lg-8">
                <form:radiobuttons items="${dsTrangThai}" path="trangthai" class="radioButton"/>
            </div>
        </div>
        <div class="buttonSubmit">
            <button class="button" formaction="/admin/loai-giay/update/${id}" type="submit" >SUBMIT</button>
        </div>
    </form:form>
</div>