<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">


<style>

    /* Style the tab */
    .tab {
        overflow: hidden;
        border: 1px solid #ccc;
        background-color: #f1f1f1;
    }

    /* Style the buttons inside the tab */
    .tab button {
        background-color: inherit;
        float: left;
        border: none;
        outline: none;
        cursor: pointer;
        padding: 14px 16px;
        transition: 0.3s;
        font-size: 17px;
    }

    /* Change background color of buttons on hover */
    .tab button:hover {
        background-color: #ddd;
    }

    /* Create an active/current tablink class */
    .tab button.active {
        background-color: #ccc;
    }

    /* Style the tab content */
    .tabcontent {
        display: none;
        padding: 6px 12px;
        border: 1px solid #ccc;
        border-top: none;
    }

    table {
        width: 100%;
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


    #myInput3 {
        width: 250px;
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
        margin: 0px 0px 20px 10px;
    }

    .date {
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
        margin-left: 10px;
        margin-bottom: 10px;
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

    #tableDanhSachSanPham_length {
        display: none;
    }

    #filterDonVi {
        margin-bottom: 15px;
        border-radius: 10px;
        border: 1px solid #37517E;
    }

    .filterDate {
        border-radius: 5px;
        border: 1px solid #37517E;
    }

    .btnSearch {
        background-color: #37517E;
        width: 100px;
        height: 25px;
        border-radius: 5px;
        color: #FFFFFF;
        border: 0px;
    }

    .error {
        font-size: 15px;
        color: crimson;
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

    .menu-nav{
        background-color: #D9D9D9;
    }
</style>
<body>

<div class="menu-nav">
    <div class="status text-center">
        <ul class="nav justify-content-center bg-gradient-light nav-pills">
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'khuyen-mai' ? 'active' : ''}" aria-current="page"
                   href="/bumblebee/khuyen-mai/list">Quản lý khuyến mại
                    <span class="badge text-bg-secondary">${countHD}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'san-pham-khuyen-mai' ? 'active' : ''}"
                   href="/bumblebee/san-pham-khuyen-mai/list">Sản phẩm khuyến mại<span
                        class="badge text-bg-secondary">${countHDCho}</span></a>
            </li>
        </ul>
    </div>
</div>


<div>
    <div>
        <h1 style="text-align: center; font-family: Nunito; margin-bottom: 50px;">Sản phẩm khuyến mại</h1>
    </div>

<%--    <form method="post" action="/bumblebee/khuyen-mai/search-khoang-ngay">--%>
<%--        <div class="row">--%>
<%--            <div class="col-lg-2">--%>
<%--                Từ ngày: <input type="date" class="filterDate" name="ngayBatDau">--%>
<%--            </div>--%>
<%--            <div class="col-lg-2">--%>
<%--                Đến ngày: <input type="date" class="filterDate" name="ngayKetThuc">--%>
<%--            </div>--%>
<%--            <div class="col-lg-1">--%>
<%--                <button class="btnSearch" onclick="filterTable()">Tìm</button>--%>
<%--            </div>--%>

<%--        </div>--%>
<%--    </form>--%>

    <div class="container">
        <table id="tableChiTietKhuyenMai" class="ui celled table" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>STT</th>
                <th>Tên sản phẩm</th>
                <th>Mã khuyến mại</th>
                <th>Giá trị khuyến mại</th>
                <th>Giá bán</th>
                <th>Giá khuyến mại</th>
                <th>Trạng thái</th>
            </tr>
            </thead>

            <tbody id="myTable2">
            <c:forEach items="${listCTKM}" var="ctkm" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${ctkm.ctsp.sanPham.tenSanPham}</td>
                    <td>${ctkm.khuyenMai.maKhuyenMai}</td>
                    <td>
                        <c:if test="${ctkm.khuyenMai.donVi == 'VNĐ'}">
                            <fmt:formatNumber>${ctkm.khuyenMai.giaTri}</fmt:formatNumber>
                        </c:if>
                        <c:if test="${ctkm.khuyenMai.donVi == '%'}">
                            ${ctkm.khuyenMai.giaTri}%
                        </c:if>
                    </td>
                    <td><fmt:formatNumber>${ctkm.ctsp.giaBan}</fmt:formatNumber></td>
                    <td>
                        <c:if test="${ctkm.khuyenMai.donVi == 'VNĐ'}">
                            <fmt:formatNumber> ${ctkm.ctsp.giaBan - ctkm.khuyenMai.giaTri}</fmt:formatNumber>
                        </c:if>
                        <c:if test="${ctkm.khuyenMai.donVi == '%'}">
                            <fmt:formatNumber> ${ctkm.ctsp.giaBan - ((ctkm.khuyenMai.giaTri / 100) * ctkm.ctsp.giaBan)}</fmt:formatNumber>
                        </c:if>

                    </td>
                    <td>
                        <c:if test="${ctkm.khuyenMai.trangThai == 0}">Hoạt động</c:if>
                        <c:if test="${ctkm.khuyenMai.trangThai == 1}">Không hoạt động</c:if>
                    </td>
<%--                    <td>--%>
<%--                        <a href="/bumblebee/khuyen-mai/view-update-ctkm/${ctkm.id}">--%>
<%--                            <img src="../../img/Edit_Notepad_Icon.svg" style="width: 30px; height: 30px;"/>--%>
<%--                        </a>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
<script>

    $(document).ready(function () {
        $('#tableChiTietKhuyenMai').DataTable({
            info: false,
            language: {
                search: "",
                searchPlaceholder: "Tìm kiếm ......",
            }
        });
    });
</script>