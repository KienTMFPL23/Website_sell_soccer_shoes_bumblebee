<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<br>
<br>

<style>
    .formAdd {
        width: 700px;
        height: 500px;
        background-color: #37517E;
        border-radius: 20px;
        color: #FFFFFF;
        font-family: "Nunito";
        margin: 0 auto;
    }

    .row.mb-3 {
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

    label {
        font-size: 17px;
    }
</style>

<div class="formAdd">
    <h1 style="text-align: center; padding-top: 20px;">THÊM/SỬA KÍCH CỠ</h1>
    <form:form action="${action}" modelAttribute="kichco">
        <div class="row mb-3">
            <div class="col-sm-6">
                <form:input type="hidden" class="form-control" id="inputEmail3" path="id"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Mã kích cỡ:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" path="maKichCo"/>
                <form:errors path="maKichCo" cssStyle="color: crimson"></form:errors>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Size:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" path="size" />
                <form:errors path="size" cssStyle="color: crimson"></form:errors>
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
            <button type="submit" id="but" class="button"
                    onclick="return confirm('Bạn có chắc muốn thực hiện ?');">
                Submit
            </button>
        </div>

    </form:form>
</div>
<div class="text-center" style="color: crimson">${mess}</div>


