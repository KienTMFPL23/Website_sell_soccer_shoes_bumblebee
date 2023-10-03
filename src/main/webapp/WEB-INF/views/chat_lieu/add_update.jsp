<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<html>
<head>
    <title>Thêm, sửa</title>
</head>
<body>
<style>

    #form {
        width: 600px;
        height: 400px;
        background-color: #37517E;
        color: white;
        margin: 150px auto 0;
        border-radius: 10px;
    }

    .input {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;

    }

    .input label{
        padding-left: 20px;
    }

    input {
        width: 400px;
        margin-right: 20px;
        border-radius: 10px;
        border: none;
    }

    #but {
        margin-left: 210px;
        width: 80px;
        border-radius: 10px;
        background-color: #D9D9D9;
        font-weight: bold;
        margin-top: 30px;
    }

    h3 {
        text-align: center;
    }

    #tt {
        display: flex;
        margin-top: 20px;
    }
    #tt label{
        margin-left: 20px;
    }

</style>


<div class="container">
    <div class="row">
        <div class="col-lg-2">
        </div>
        <div class="col-lg-6" id="form">
            <h3 style="margin-top: 30px">Thêm / Sửa Chất Liệu</h3>
            <sf:form class="was-validated" method="post" modelAttribute="cl" action="${action}">
                <div class="input">
                    <label>Mã</label>
                    <sf:input path="ma"/> <br/>
                    <sf:errors path="ma" cssStyle="color: red"/>
                </div>
                <div class="input">
                    <label>Tên</label>
                    <sf:input path="ten"/> <br/>
                    <sf:errors path="ten" cssStyle="color: red"/>
                </div>
                    <div id="tt">
                        <label>Trạng Thái</label>
                            <sf:radiobutton path="trangThai" value="1" checked="true" cssStyle="width: 25px;margin-left : 20px;"/>Còn
                            <sf:radiobutton path="trangThai" value="0" cssStyle="width: 25px;margin-left: 20px;"/>Hết
                    </div>
                <div class="mb-3">
                    <button id="but" class="btn" type="submit">Submit</button>
                </div>
            </sf:form>
        </div>
        <div class="col-lg-4">

        </div>
    </div>
</div>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>
