<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

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

    .ui.table > thead > tr > th {
        background-color: #37517E;
        color: #FFFFFF;
        font-size: 18px;
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

    <h2 STYLE="text-align: center;padding-top: 30px; font-family: Nunito">QUẢN LÝ HÌNH ẢNH</h2>
    <div class="row">
        <div class="col-lg-6">
            <a href="/hinh-anh/view-add" class="btnAdd">
                <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/>
                <p>Thêm mới</p>
            </a>
        </div>
    </div>
    <table id="tableHinhAnh" class="ui celled table" width="100%">
        <thead>
            <tr>
                <th>ID CTSP</th>
                <th>Ảnh 1</th>
                <th>Ảnh 2</th>
                <th>Ảnh 3</th>
                <th>Ảnh 4</th>
                <th>Ảnh 5</th>
                <th>Ảnh 6</th>
                <th>Action</th>
            </tr>
        </thead>
    </table>