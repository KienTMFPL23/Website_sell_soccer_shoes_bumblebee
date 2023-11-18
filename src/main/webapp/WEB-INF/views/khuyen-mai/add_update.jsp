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
        height: 560px;
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

    .error {
        color: red;
        font-size: 15px;
        padding-left: 190px;
        padding-top: 15px;
    }


</style>
<br>

<body>
<div class="formAdd">
    <h1 style="text-align: center; padding-top: 20px;">THÊM/SỬA KHUYẾN MẠI</h1>
    <form:form action="${action}" modelAttribute="km" method="post" id="formAddUpdate">
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
                <form:input type="text" class="form-control" path="maKhuyenMai" name="maKhuyenMai"/>
                <form:errors path="maKhuyenMai" cssStyle="color: crimson"></form:errors>
                <span style="color: crimson">${mess_Ma}</span>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Tên:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" path="tenKhuyenMai" name="tenKhuyenMai"/>
                <form:errors path="tenKhuyenMai" cssStyle="color: crimson"></form:errors>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Đơn vị:</label>
            </div>
            <div class="col-lg-8">
                <form:select type="text" class="form-select" aria-label="Default select example" path="donVi" name="donVi">
                    <form:option value="VNĐ">VNĐ</form:option>
                    <form:option value="%">%</form:option>
                </form:select>
                <form:errors path="donVi" cssStyle="color: crimson"></form:errors>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Giá trị:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" path="giaTri" name="giaTri"/>
                <form:errors path="giaTri" cssStyle="color: crimson"></form:errors>
                <span style="color: crimson">${errorGiaTri}</span>
            </div>
        </div>


        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Trạng thái:</label>
            </div>
            <div class="col-lg-8">
                <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="radioButton" name="trangThai"/>
                <form:errors path="trangThai" cssStyle="color: crimson"></form:errors>
            </div>
        </div>
        <div class="buttonSubmit">
            <button class="button" type="submit">SUBMIT</button>
        </div>

    </form:form>
</div>
</body>