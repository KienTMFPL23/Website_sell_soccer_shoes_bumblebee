<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chất liệu</title>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
</head>
<style>
    .title-h3 {
        text-align: center;
        margin: 35px 0px;
        padding-top: 50px;
    }

    .thead-form tr th {
        margin-top: 150px;
        background-color: #37517E;
    }

    #icon-class-add {
        margin-bottom: 30px;
        margin-left: 43px;
    }

    .icon-add {
        border: 1px solid #D9D9D9;
        border-radius: 13px;
        margin-left: 30px;
        padding: 5px;
        width: 150px;
        float: left;
    }

    .icon-add:hover {
        background: #37517E;
    }

    #add-icon-id {
        margin: auto;
    }

    .add-text {
        text-decoration: none;
    }

    .icon-add img {
        width: 35px;
        height: 35px;
    }


    .icon-update img {
        width: 35px;
        height: 35px;
    }

    .search input {
        padding: 10px;
        border: 1px solid #D9D9D9;
        width: 410px;
        background: #D9D9D9;
        border-radius: 15px;
    }

    .text {
        font-weight: bold;
        color: black;
    }

</style>

<body>
<div class="container">

    <div class="row" id="icon-class-add">
        <h3 class="title-h3" style="text-align: center; font-family: Nunito;font-size: 2.5rem">Danh Sách Chất Liệu</h3>
        <div class="col-lg-6">
            <button class="icon-add" id="add-icon-id"><a href="/chat-lieu/view-add" style="text-decoration: none">
                <div class="add-text"><img src="https://cdn-icons-png.flaticon.com/128/1828/1828817.png" alt=""/>
                    <span class="text">Thêm mới</span>
                </div>
            </a>
            </button>
        </div>
    </div>

    <table id="tableChatLieu" class="ui celled table" width="100%">
        <thead>
        <tr>
            <th scope="col">Mã</th>
            <th scope="col">Tên Chất Liệu</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
    </table>
</div>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>

</html>