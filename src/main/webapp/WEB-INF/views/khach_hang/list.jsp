<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
    body {
        font-family: Nunito;
    }

    .ui.form input[type=search] {
        background: #fff;
        border: 2px solid #37517E;
        border-radius: 20px;
        width: 400px;
    }

    .ui input {
        border-radius: 20px;
    }

    .ui.table > tbody > tr > td {
        font-size: 16px;
    }

</style>
<body>
<div>
    <h1 style="text-align: center; font-family: Nunito">Quản Lý Khách Hàng</h1>


    <div class="container">
        <table id="tableKhachHang" class="ui celled table" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Mã Khách Hàng</th>
                <th scope="col">Họ Tên</th>
                <th scope="col">Giới Tính</th>
                <th scope="col">Ngày Sinh</th>
                <th scope="col">Địa Chỉ</th>
                <th scope="col">SĐT</th>
                <th scope="col">Email</th>
                <th scope="col">Trạng Thái</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="kh" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${kh.ma}</td>
                    <td>${kh.ho} ${kh.tenDem} ${kh.ten}</td>
                    <td>
                        <c:if test="${kh.gioiTinh == true}">Nam
                        </c:if>
                        <c:if test="${kh.gioiTinh == false}">Nữ
                        </c:if>
                    </td>
                    <td>
                        <fmt:formatDate value="${kh.ngaySinh}" pattern="dd/MM/yyyy"></fmt:formatDate>
                    </td>
                    <td>${kh.diaChi}</td>
                    <td>${kh.soDienThoai}</td>
                    <td>${kh.email}</td>
                    <td>
                        <c:if test="${kh.trangThai == 0}">Hoạt động
                        </c:if>
                        <c:if test="${kh.trangThai == 1}">Không hoạt động
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#tableKhachHang').DataTable({
            info: false,
            language: {
                search: "",
                searchPlaceholder: "Tìm kiếm ......",
            }
        });
    });
</script>

