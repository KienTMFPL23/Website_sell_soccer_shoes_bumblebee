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
        border-radius: 10px;
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
                    <a href="#"><img class="img" src="../../../img/1315638.png">Thông tin cá nhân</a>
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
                            MÃ ĐƠN HÀNG: ${id}
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <img src="../../../img/trang-thai-don-hang.png" class="trang-thai">
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
                                        <p>Tên người nhận: ${hoaDon.khachHang.ho} ${hoaDon.khachHang.tenDem} ${hoaDon.khachHang.ten}</p>
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
                        <span>Tổng tiền:<fmt:formatNumber
                                value="${sumMoney}"
                                type="currency"/> </span>
                    </div>
                </div>
            </div>

        </div>
    </div>
</main>
</body>