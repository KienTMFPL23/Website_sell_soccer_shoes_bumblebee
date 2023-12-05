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

    .bg-gradient-light {
        background-image: linear-gradient(180deg, #D9D9D9 10%, #D9D9D9 100%);
        background-size: cover;
        border-radius: 5px;
        width: 25%;
    }

    .nav {
        --bs-nav-link-padding-x: 1rem;
        --bs-nav-link-padding-y: 0.5rem;
        --bs-nav-link-color: #767676;
        --bs-nav-link-hover-color: black;
        --bs-nav-link-disabled-color: rgba(33, 37, 41, 0.75);
        display: flex;
        flex-wrap: wrap;
        padding-left: 0;
        margin-bottom: 0;
        list-style: none;
    }

    .nav-pills {
        --bs-nav-pills-link-active-bg: #A3A3A3;
    }

    .menu-nav {
        background-color: #D9D9D9;
    }

</style>
<body>


<div>

    <div class="menu-nav">
        <div class="status text-center">
            <ul class="nav justify-content-center bg-gradient-light nav-pills">
                <li class="nav-item">
                    <a class="nav-link  ${donHang == 'thong-tin-nhan-vien' ? 'active' : ''}" aria-current="page"
                       href="/nhan-vien/thong-tin">Thông tin nhân viên
                        <span class="badge text-bg-secondary">${countHD}</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link  ${donHang == 'tai-khoan-nhan-vien' ? 'active' : ''}"
                       href="/nhan-vien/tai-khoan">Tài khoản nhân viên<span
                            class="badge text-bg-secondary">${countHDCho}</span></a>
                </li>
            </ul>
        </div>
    </div>

    <h1 style="text-align: center; font-family: Nunito; padding-top: 20px;"> Quản Lý Tài Khoản Nhân Viên</h1>

    <div class="row">
        <div class="col-lg-6">
            <a href="/nhan-vien/tai-khoan/view-add" class="btnAdd">
                <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/>
                <p>Thêm mới</p>
            </a>
        </div>
    </div>

    <div class="container">
        <table id="tableTaiKhoanKhachHang" class="ui celled table" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Username</th>
                <th scope="col">Password</th>
                <th scope="col">Vai trò</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="nv" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${nv.username}</td>
                    <td>${nv.password}</td>
                    <td>
                        <c:if test="${nv.role == 2}">Nhân viên
                        </c:if>
                    </td>
                    <td>
                        <a href="/nhan-vien/tai-khoan/view-update/${nv.id}">
                            <img src="../../img/Edit_Notepad_Icon.svg" style="width: 30px; height: 30px;"/>
                        </a>
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
        $('#tableTaiKhoanKhachHang').DataTable({
            info: false,
            language: {
                search: "",
                searchPlaceholder: "Tìm kiếm ......",
            }
        });
    });
</script>
