<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
/>
<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet"
/>
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


</style>
<br>

<body>
<div class="formAdd">
    <h1 style="text-align: center; padding-top: 20px;">THÊM/SỬA ĐẾ GIÀY</h1>

    <form:form action="${action}" modelAttribute="degiay" method="post">

        <div class="row mb-3">
            <div class="col-sm-6">
                <form:input type="hidden" class="form-control" id="inputEmail3" path="id"/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Mã:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" id="inputEmail3" path="ma"/>
                <form:errors path="ma" cssStyle="color: crimson"></form:errors>
                <p style="color: crimson">${mess_Ma}</p>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Loại đế:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" id="inputEmail3" path="loaiDe"/>
                <form:errors path="loaiDe" cssStyle="color: crimson"></form:errors>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Mô tả:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" id="inputEmail3" path="moTa"/>
                <form:errors path="moTa" cssStyle="color: crimson"></form:errors>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Trạng thái:</label>
            </div>
            <div class="col-lg-8">
                <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="radioButton"/>
                <form:errors path="trangThai" cssStyle="color: crimson"></form:errors>
            </div>
        </div>


        <div class="buttonSubmit">
            <button class="button" type="submit">SUBMIT</button>
        </div>


    </form:form>
</div>
</body>