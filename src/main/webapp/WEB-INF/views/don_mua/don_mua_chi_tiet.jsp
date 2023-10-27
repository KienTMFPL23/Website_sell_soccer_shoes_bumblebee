<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
    .img {
        width: 30px;
        height: 30px;
    }

    /*==Style cơ bản cho website==*/
    body {
        font-family: Nunito;
        color: #333;
        background-color: #F3F3F3;
    }

    .ps-main {
        padding: 50px 100px 50px 100px;
    }

    .don-mua {
        background-color: #FFFFFF;
        margin-top: 20px;
        border-radius: 10px;
    }

    .don-hang {
        padding-left: 20px;
        padding-right: 20px;
    }

    .trang-thai-don-hang > p {
        color: crimson;
        float: right;
        padding-top: 15px;
        padding-bottom: -10px;
    }

    .table {
        margin-bottom: 20px;
    }

    .tong-tien {
        padding-bottom: 10px;
        color: crimson;
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

    .table > a {
        text-decoration: none;

    }

    .menu-right {
        background-color: #FFFFFF;
        height: 40px;
        line-height: 40px;
        border-radius: 10px;
    }

    .col-lg-2 > a {
        color: black;
        text-decoration: none;
        font-size: 15px;
        padding-left: 15px;
    }

    .col-lg-10 > .ma-don {
        float: right;
        padding-right: 15px;
        font-size: 15px;
    }

    span {
        color: crimson;
        font-size: 15px;
    }

    .menu-right {
        margin-bottom: 20px;
    }

    .trang-thai {
        border: 4px solid #D9D9D9;
        border-radius: 50%;
        display: flex;
        margin: auto;


    }

    .status-order {
        padding: 10px 0px 10px 0px;
        background-color: #FFFFFF;
        border-radius: 10px;
    }

    .status-order > .row > .col-lg-2 {
        z-index: 1;
    }


    /*.inline{*/
    /*    background-color: rgb(45, 194, 88);*/
    /*    width: 100%;*/
    /*    margin: 30px 60px 0px 60px;*/
    /*    height: 3px;*/
    /*    position: absolute;*/
    /*    transition: width 1s cubic-bezier(.4,0,.2,1);*/
    /*}*/

    .stepper__line {
        position: absolute;
        top: 100px;
        height: 4px;
        width: 100%;

    }

    .stepper__line-background, .stepper__line-foreground {
        position: absolute;
        width: calc(100% - 140px);
        margin: 0 80px;
        height: 100%;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    /*#2dc258*/
    .stepper__line-foreground {
        background: #D9D9D9;
        /*transition: width 1s cubic-bezier(.4, 0, .2, 1);*/
    }

    .stepper__line-background, .stepper__line-foreground {
        position: absolute;
        width: calc(100% - 140px);
        margin: 0 80px;
        height: 100%;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    .col-lg-2 > p, h4 {
        text-align: center;
        margin-top: 10px;
    }

    .table {
        margin-top: 30px;
        border-radius: 10px;
    }

    .dia-chi {
        background-color: #FFFFFF;
        padding-left: 30px;
        padding-top: 10px;
        border-radius: 10px;
    }

</style>
<body>
<main class="ps-main" id="app">
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
            <div class="menu-right">
                <div class="row">
                    <div class="col-lg-2">
                        <a href="/bumblebee/don-mua" class="return">
                            <img class="img-return" src="../../../img/left-arrow.png" width="15px" height="15px">
                            TRỞ LẠI
                        </a>
                    </div>
                    <div class="col-lg-10">
                        <div class="ma-don">
                            MÃ ĐƠN HÀNG: <span style="font-weight: 600; color: black">${id}</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="status-order">
                <div class="row" >
                    <div class="col-lg-2" >
                        <img src="../../../img/order.png" style="width: 60px; height: 60px;" class="trang-thai"
                             id="img1">
                        <h4>Đơn hàng đã được đặt</h4>
                        <p>14:27 20/10/2023</p>
                    </div>
                    <div class="col-lg-2" >
                        <img src="../../../img/payment.jpg" style="width: 60px; height: 60px;" class="trang-thai"
                             id="img2">
                        <h4>Đã xác nhận thanh toán</h4>
                        <p>14:27 20/10/2023</p>
                    </div>
                    <div class="col-lg-2" >
                        <img src="../../../img/truck.jpg" style="width: 60px; height: 60px;" class="trang-thai"
                             id="img3">
                        <h4>Đơn hàng đang được giao</h4>
                        <p>14:27 20/10/2023</p>
                    </div>
                    <div class="col-lg-2" >
                        <img src="../../../img/receive-order.jpg" style="width: 60px; height: 60px;" class="trang-thai">
                        <h4>Đã nhận được hàng</h4>
                        <p>14:27 20/10/2023</p>
                    </div>
                    <div class="col-lg-2">
                        <img src="../../../img/istockphoto-1707893816-612x612.jpg" style="width: 60px; height: 60px;"
                             class="trang-thai">
                        <h4>Đổi trả</h4>
                        <p>14:27 20/10/2023</p>
                    </div>
                    <div class="col-lg-2" style="display: none;">
                        <img src="../../../img/cancel-order.jpg" style="width: 60px; height: 60px;" class="trang-thai">
                        <h4>Đơn hàng đã được hủy</h4>
                        <p>14:27 20/10/2023</p>
                    </div>

                    <%--                    <div class="inline"></div>--%>
                    <div class="stepper__line">
                        <div class="stepper__line-background" id="content2"></div>
                        <div class="stepper__line-foreground" id="content1"></div>
                    </div>
                </div>
                <div>
                    <button type="button" id="id1">Button</button>
                    <button type="button" id="id2">Button</button>
                    <button type="button" id="id3">Button</button>
                </div>
            </div>

            <div class="table">
                <table class="table ps-checkout__products">
                    <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listHoaDonChiTiet}" var="hdct">
                        <tr>
                            <td>
                                <img src="../../../uploads/${hdct.chiTietSanPham.hinhAnhs.tenanh}"
                                     width="100px" height="100px">
                            </td>
                            <td style="padding-top: 25px;">
                                    ${hdct.chiTietSanPham.sanPham.tenSanPham}<br>
                                <p>${hdct.chiTietSanPham.mauSac.ten}
                                    - ${hdct.chiTietSanPham.kichCo.size}</p>
                                <h5>Số lượng: ${hdct.soLuong}</h5>
                            </td>
                            <td style="text-align: center;padding-top: 25px;"><fmt:formatNumber
                                    value="${hdct.donGia}"
                                    type="currency"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="dia-chi">
                <div class="row">
                    <div class="col-lg-9">
                        <c:choose>
                            <c:when test="${hoaDon.tenNguoiNhan == '' && hoaDon.sdt == '' && hoaDon.diaChiShip == ''}">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <h3>ĐỊA CHỈ NHẬN HÀNG</h3>
                                        <p>Tên người
                                            nhận: ${hoaDon.khachHang.ho} ${hoaDon.khachHang.tenDem} ${hoaDon.khachHang.ten}</p>
                                        <p>Số điện thoại: ${hoaDon.khachHang.soDienThoai}</p>
                                        <p>Địa chỉ: ${hoaDon.khachHang.diaChi}</p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <h3>ĐỊA CHỈ NHẬN HÀNG</h3>
                                        <p>Tên người nhận: ${hoaDon.tenNguoiNhan}</p>
                                        <p>Số điện thoại: ${hoaDon.sdt}</p>
                                        <p>Địa chỉ: ${hoaDon.diaChiShip}</p>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="col-lg-3">
                        <span>Tổng tiền: <fmt:formatNumber
                                value="${sumMoney}"
                                type="currency"/> </span>
                    </div>
                </div>
            </div>

        </div>
    </div>
</main>
</body>


