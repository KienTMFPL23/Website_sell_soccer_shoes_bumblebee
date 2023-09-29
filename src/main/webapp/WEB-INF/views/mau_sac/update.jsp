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

    body{
        background-color: #D9D9D9;
    }
    .formAdd{
        width: 700px;
        height: 600px;
        margin: 0 auto;
        background-color: #37517E;
        border-radius: 20px;
        color: #FFFFFF;
        font-family: "Nunito";
    }

    form{
        margin-left: 50px;
    }

    .formAdd>form>.mb-3>.col-sm-8{
        margin-top: 20px;
    }

    .formAdd>h1{
        text-align: center;
        padding-top: 40px;
    }
    .formAdd>form>.mb-3>label{
        margin-top: 20px;
    }

    .buttonSubmit{
        margin-top: 50px;
        margin-left: -50px;
        text-align: center;
    }
    .buttonSubmit>button{
        background-color: #FFFFFF;
        color: black;
        font-weight: bold;
        width: 120px;
        height: 40px;
        border-radius: 10px;
        border: 0px;
    }

    .radioButton{
        margin-top: 10px;
    }
</style>
<br>

<body>
<div class="formAdd">
    <h1>THÊM/SỬA ĐẾ GIÀY</h1>

    <form:form action="/mau-sac/update" modelAttribute="ms" method="post">

        <div class="row mb-3">
            <div class="col-sm-6">
                <form:input type="hidden" class="form-control" id="inputEmail3" path="id"/>
            </div>
        </div>

        <div class="row mb-3">
            <label for="inputEmail3" class="col-sm-2 col-form-label">Mã:</label>
            <div class="col-sm-8">
                <form:input type="text" class="form-control" id="inputEmail3" path="ma"/>
                <form:errors path="ma" cssStyle="color: crimson"></form:errors>
                <p style="color: crimson">${mess_Ma}</p>
            </div>
        </div>

        <div class="row mb-3">
            <label for="inputEmail3" class="col-sm-2 col-form-label">Tên màu :</label>
            <div class="col-sm-8">
                <form:input type="text" class="form-control" id="inputEmail3" path="ten"/>
                <form:errors path="ten" cssStyle="color: crimson"></form:errors>
            </div>
        </div>

        <%--        <div class="row mb-3">--%>
        <%--            <label for="inputEmail3" class="col-sm-2 col-form-label">Mô tả:</label>--%>
        <%--            <div class="col-sm-8">--%>
        <%--                <form:input type="text" class="form-control" id="inputEmail3" path="moTa"/>--%>
        <%--                <form:errors path="moTa" cssStyle="color: crimson"></form:errors>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <div class="row mb-3">
            <label for="inputEmail3" class="col-sm-2 col-form-label">Trạng thái:</label>
            <div class="col-sm-8">
                <form:radiobuttons items="${dsTrangThai}" path="tt" class="radioButton"/>
                <form:errors path="tt" cssStyle="color: crimson"></form:errors>
            </div>
        </div>


        <div class="buttonSubmit">
            <button type="submit">SUBMIT</button>
        </div>

    </form:form>
</div>
</body>