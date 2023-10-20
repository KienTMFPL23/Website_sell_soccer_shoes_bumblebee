<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet"
/>
<style>
    body {

        color: #333;
        background-color: #F3F3F3;
    }

    .ps-main {
        padding: 50px 100px 50px 100px;
        font-family: Nunito;
    }

    .infor {
        padding: 15px 0px 0px 20px;
        border-bottom: 2px solid #D9D9D9;
    }

    .infor-img > img {
        float: left;
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }

    .infor-name > h2 {
        padding: 15px;
        color: #0b0b0b;
        font-weight: 600;
    }

    .img {
        margin: 10px;
        width: 30px;
        height: 30px;
    }

    .menu-left > a {
        color: #0b0b0b;
        text-decoration: none;
        font-size: 15px;
    }

    .menu-left > a:hover {
        color: #37517E;
        text-decoration: none;
        font-size: 15px;
    }

    .col-lg-9 {
        background-color: #FFFFFF;
        border-radius: 5px;

    }

    .h2 {
        padding: 0px 15px 0px 15px;
        border-bottom: 2px solid #D9D9D9;
        font-family: Nunito;
    }

    form {
        padding: 30px 40px 30px 40px;
    }

    label {
        font-size: 15px;
        font-family: Nunito;
        width: 110px;
    }

    input {
        border: 0px;
        border-bottom: 2px solid #D9D9D9;
        width: 300px;
        height: 40px;
        font-size: 17px;
        padding-top: 25px;
        margin-left: 10px;
        font-family: Nunito;

    }

    .btnSubmit {
        background-color: #37517E;
        color: #FFFFFF;
        font-size: 15px;
        width: 70px;
        height: 30px;
        border: 0px;
        border-radius: 5px;
        display: flex;
        margin-left: auto;
        margin-right: auto;
        margin-top: 30px;
    }

    .img-user {

    }

    .img-user > img {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        display: flex;
        margin: 40px auto 0px auto;
    }

    .upload {
        background-color: #FFFFFF;
        border: 2px solid #D9D9D9;
        border-radius: 5px;
        height: 30px;
        width: 100px;
        display: flex;
        margin: 30px auto auto auto;
        text-align: center;
    }

    .btnChooseImage > a {
        font-size: 16px;
        text-align: center;
    }

    .radio {
        width: 15px;
    }
</style>
<body>
<main class="ps-main">
    <div class="row">
        <div class="col-lg-3">
            <div class="infor">
                <div class="row">
                    <div class="infor-img">
                        <img src="../../../uploads/aaaf46616a81b3e60a1302bb80200c30.jpg">
                    </div>
                    <div class="infor-name">
                        <h2>${userLogged.khachHangKH.ho} ${userLogged.khachHangKH.tenDem} ${userLogged.khachHangKH.ten}</h2>
                    </div>
                </div>
            </div>

            <div>
                <div class="menu-left">
                    <a href="/bumblebee/thong-tin-ca-nhan"><img class="img" src="../../../img/1315638.png">Thông tin cá
                        nhân</a>
                </div>
                <div class="menu-left">
                    <a href="#"><img class="img" src="../../../img/lock.png">Đổi mật khẩu</a>
                </div>
                <div class="menu-left">
                    <a href="/bumblebee/don-mua"><img class="img" src="../../../img/bill.png">Đơn mua</a>
                </div>

            </div>
        </div>


        <div class="col-lg-9">
            <form:form action="/bumblebee/thong-tin-ca-nhan" method="post" modelAttribute="kh">
                <div class="h2">
                    <h1>Hồ sơ của tôi</h1>
                </div>

                <div class="row">
                    <div class="col-lg-6">

                        <div>
                            <form:input type="hidden" path="id" />
                        </div>

                        <div>
                            <label>Họ: </label>
                            <form:input type="text" path="ho"/>
                        </div>

                        <div>
                            <label>Tên đệm: </label>
                            <form:input type="text" path="tenDem"/>
                        </div>

                        <div>
                            <label>Tên: </label>
                            <form:input type="text" path="ten"/>
                        </div>

                        <div>
                            <label>Giới tính: </label>
                            <form:radiobuttons items="${dsGioiTinh}" class="radio" path="gioiTinh"/>
                        </div>

                        <div>
                            <label>Ngày sinh: </label>
                            <form:input type="date" path="ngaySinh"/>
                        </div>

                        <div>
                            <label>Số điện thoại: </label>
                            <form:input type="text" path="soDienThoai"/>
                        </div>

                        <div>
                            <label>Email: </label>
                            <form:input type="text" path="email"/>
                        </div>

                        <div>
                            <button class="btnSubmit" type="submit">Lưu</button>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="img-user">
                            <img src="../../../uploads/aaaf46616a81b3e60a1302bb80200c30.jpg">
                        </div>
                        <div>
                            <input type="file" id="upload" hidden/>
                            <label class="upload" for="upload">Choose file</label>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>


    </div>
</main>
</body>