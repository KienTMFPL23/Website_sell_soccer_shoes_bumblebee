<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
    body {
        font-family: Nunito;
    }

    table {
        width: 100%;
    }

    .table > table > thead > tr {
        background-color: #37517E;
        color: #FFFFFF;
    }

    .table > table > thead > .th1 {
        background-color: #FFFFFF;
    }

    .btnAdd {
        background-color: #EEEDED;
        margin-left: 50px;
        margin-bottom: 30px;
        border: 2px solid #37517E;
        border-radius: 10px;
        width: 150px;
        float: left;
        height: 40px;
        font-size: 18px;
        font-weight: 500;
        text-decoration: none;
        color: black;
        text-align: center;
    }

    .btnAdd:hover {
        background-color: #37517E;
        border: 2px solid #FFFFFF;
        color: #FFFFFF;
        text-decoration: none;
    }

    .btnAdd > img {
        margin-right: 10px;
        margin-top: 3px;
        margin-left: 15px;
        float: left;
    }

    .btnAdd > p {
        margin-top: 5px;
        margin-right: 10px;
    }


</style>
<body>
<div>
    <h1 style="text-align: center; font-family: Nunito;">Sản phẩm khuyến mãi</h1>
</div>


<div class="table">
    <table>
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã khuyến mãi</th>
            <th>Tên khuyến mãi</th>
            <th>Giá trị</th>
            <th>Ngày bắt đầu</th>
            <th>Trạng thái</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${page.content}" var="km" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${km.maKhuyenMai}</td>
                <td>${km.tenKhuyenMai}</td>
                <td>${km.giaTri}</td>
                <td>${km.ngayTao}</td>
                <td>
                    <c:if test="${km.trangThai == 0}">Hoạt động</c:if>
                    <c:if test="${km.trangThai == 1}">Không hoạt động</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>


