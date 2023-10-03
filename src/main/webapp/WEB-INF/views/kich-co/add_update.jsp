<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<br>
<br>

<style>
    .container-sm {
        padding-bottom: 20px;
        background-color: #37517E;
        color: white;
    }

    .form-label {
        padding-right: 30px;
        justify-content: space-between;
    }

    input {
        margin-right: 10px;
        width: 390px;
        border-radius: 10px;
        border: none;
        height: 30px;

    }

    .input {
        display: flex;
        justify-content: space-between;
        margin-top: 30px;
    }

    .input p {
        margin-left: 10px;
    }

    h3 {
        text-align: center;
    }

    #but {
        border-radius: 10px;
    }
</style>


<div class="container-sm">
    <div class="row">
        <div class="col-6 col-md-6 col-sm-6">
            <%--            <a href="/kich-co/hien-thi" class="btn btn-primary"><i class="bi bi-house-fill"></i></a>--%>
            <br>
        </div>
        <h3>Thêm Kích Cỡ</h3>
    </div>
    <form:form action="${action}" modelAttribute="kichco">
        <form:input path="id" class="form-control" type="hidden"/>
        <div class="mb-3">
            <label class="form-label">Mã Kích Cỡ: </label>

            <form:input path="maKichCo" class="form-control"/>
            <form:errors path="maKichCo" cssStyle="color: crimson"/>
        </div>
        <div class="mb-3">
            <label class="form-label">Size</label>
            <form:input path="size" class="form-control"/>
            <form:errors path="size" cssStyle="color: crimson"/>
        </div>

        <div class="mb-3">
            <label class="form-label">Giới Tính</label>
            <form:radiobuttons items="${dsGioiTinh}" path="gioiTinh" class="form-check-input"/>
            <form:errors path="gioiTinh" cssStyle="color: crimson"/>
        </div>

        <div class="mb-3">
            <label class="form-label">Trạng Thái</label>
            <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="form-check-input"/>
            <form:errors path="trangThai" cssStyle="color: crimson"/>
        </div>
        <div class="text-center">
            <button type="submit" id="but" class="btn btn-secondary text-center"
                    onclick="return confirm('Bạn có chắc muốn thực hiện ?');">
                Submit
            </button>
        </div>
    </form:form>
    <div class="text-center" style="color: crimson">${mess}</div>
</div>