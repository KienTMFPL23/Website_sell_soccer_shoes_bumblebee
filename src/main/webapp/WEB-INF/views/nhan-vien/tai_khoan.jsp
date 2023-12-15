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
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link ${donHang == 'tai-khoan-nhan-vien' ? 'active' : ''}" aria-current="page"
               href="/nhan-vien/tai-khoan">Tài khoản nhân viên</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${donHang == 'thong-tin-nhan-vien' ? 'active' : ''}" href="/nhan-vien/thong-tin">Thông tin nhân viên</a>
        </li>
    </ul>

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
                <th scope="col"></th>
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
                    <td>
                        <c:if test="${nv.nhanVien.id == null}">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#${nv.id}"
                                style="border-radius: 20px;">Chọn
                        </button>
                        <!-- Modal -->
                        <form:form action="/nhan-vien/them-thong-tin/${nv.id}" modelAttribute="nv" method="post">
                            <div class="modal fade" id="${nv.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-lg modal-dialog-scrollable">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Thông tin nhân viên</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row mb-3">
                                                <div class="col-sm-6">
                                                    <form:input type="hidden" class="form-control" id="inputEmail3"
                                                                path="id"/>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Mã:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="text" class="form-control" path="ma" name="ma"/>
                                                    <form:errors path="ma" cssStyle="color: crimson"></form:errors>
                                                        ${mess_Ma}
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Họ:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="text" class="form-control" path="ho" name="ho"/>
                                                    <form:errors path="ho" cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Tên đệm:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="text" class="form-control" path="tenDem"
                                                                name="tenDem"/>
                                                    <form:errors path="tenDem" cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Tên:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="text" class="form-control" path="ten" name="ten"/>
                                                    <form:errors path="ten" cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Giới tính:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:radiobuttons items="${dsGioiTinh}" path="gioiTinh"
                                                                       class="radioButton" name="gioiTinh"/>
                                                    <form:errors path="gioiTinh"
                                                                 cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Ngày sinh:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="date" class="form-control" path="ngaySinh"/>
                                                    <form:errors path="ngaySinh"
                                                                 cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Địa chỉ:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="text" class="form-control" path="diaChi"
                                                                name="diaChi"/>
                                                    <form:errors path="diaChi" cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Số điện thoại:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="text" class="form-control" path="soDienThoai"
                                                                name="soDienThoai"/>
                                                    <form:errors path="soDienThoai"
                                                                 cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Email:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:input type="text" class="form-control" path="email"
                                                                name="email"/>
                                                    <form:errors path="email" cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>

                                            <div class="row mb-3">
                                                <div class="col-lg-1"></div>
                                                <div class="col-lg-2">
                                                    <label>Trạng thái:</label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <form:radiobuttons items="${dsTrangThai}" path="trangThai"
                                                                       class="radioButton" name="trangThai"/>
                                                    <form:errors path="trangThai"
                                                                 cssStyle="color: crimson"></form:errors>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary"
                                            >Thêm
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                        </c:if>
                        <c:if test="${nv.nhanVien.id != null}">
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#${nv.id}"
                                    style="border-radius: 20px;" disabled>Chọn
                            </button>
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
        $('#tableTaiKhoanKhachHang').DataTable({
            info: false,
            language: {
                search: "",
                searchPlaceholder: "Tìm kiếm ......",
            }
        });
    });
</script>

