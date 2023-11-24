<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<br>

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

    /*==Style cho menu===*/
    #menu ul {
        background: #FFFFFF;
        list-style-type: none;
        height: 50px;
        width: 100%;
        border-bottom: 1px solid #D9D9D9;
        border-radius: 10px;
        margin-bottom: 20px;
        display: flex;
        justify-content: space-between;
    }

    #menu li {
        color: #37517E;
        display: inline-block;
        height: 50px;
        line-height: 50px;
        border--radius: 10px;
        padding-right: 2rem;
    }

    #menu a {
        text-decoration: none;
        color: #37517E;
        display: block;
        font-size: 16px;
    }

    #menu a:hover {
        background: #F1F1F1;
        color: #333;
    }

    .btn-search {
        height: 40px;
        line-height: 40px;
        width: 20px;
        border: 0px solid;
        background-color: #E7E6E6;
        float: left;
        border-bottom-left-radius: 10px;
        border-top-left-radius: 10px;
    }

    .input-search {
        height: 40px;
        width: 100%;
        border: 0px solid;
        background-color: #E7E6E6;
        border-radius: 10px;

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

    .phuong-thuc-thanh-toan > p {
        color: crimson;
        padding-top: 15px;
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
            <div id="menu">
                <ul>
                    <li><a href="/bumblebee/don-mua">Tất cả</a></li>
                    <li><a href="/bumblebee/don-mua/cho-xac-nhan">Chờ xác nhận</a></li>
                    <li><a href="/bumblebee/don-mua/dang-chuan-bi">Đang chuẩn bị</a></li>
                    <li><a href="/bumblebee/don-mua/dang-giao">Đang giao</a></li>
                    <li><a href="/bumblebee/don-mua/hoan-thanh">Hoàn thành</a></li>
                    <li><a href="/bumblebee/don-mua/da-huy">Đã hủy</a></li>
                    <li><a href="/bumblebee/don-mua/tra-hang">Trả hàng</a></li>
                    <li><a href="/bumblebee/don-mua/da-hoan-tra">Đã hoàn trả</a></li>
                </ul>
            </div>

            <c:choose>
                <c:when test="${listHoaDonMua != null}">
                    <div class="search">
                        <input class="input-search" type="text" placeholder=" Tìm kiếm sản phẩm">
                    </div>

                    <c:forEach items="${listHoaDonMua}" var="hd">
                        <div class="don-mua">
                            <div class="don-hang">
                                <div class="trang-thai-don-hang">
                                    <c:if test="${hd.trangThai == 1}"><p>CHỜ XÁC NHẬN</p></c:if>
                                    <c:if test="${hd.trangThai == 2}"><p>ĐANG CHUẨN BỊ</p></c:if>
                                    <c:if test="${hd.trangThai == 3}"><p>ĐANG GIAO</p></c:if>
                                    <c:if test="${hd.trangThai == 4}"><p>HOÀN THÀNH</p></c:if>
                                    <c:if test="${hd.trangThai == 5}"><p>ĐÃ HỦY</p></c:if>
                                    <c:if test="${hd.trangThai == 6}"><p>TRẢ HÀNG</p></c:if>
                                    <c:if test="${hd.trangThai == 7}"><p>ĐÃ TRẢ HÀNG</p></c:if>
                                </div>

                                <div class="phuong-thuc-thanh-toan">
                                    <c:if test="${hd.phuongThucThanhToan == 1}"><p>Chưa thanh toán</p></c:if>
                                    <c:if test="${hd.phuongThucThanhToan == 2}"><p>Đã thanh toán</p></c:if>
                                </div>
                                <div class="table">
                                    <a href="/bumblebee/don-mua/${hd.id}">
                                        <table class="table ps-checkout__products">
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <c:forEach items="${hd.hoaDons}" var="hdct">
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
                                        </table>
                                    </a>
                                </div>
                                <hr>

                                <div class="tong-tien">
                                    <h3>Thành tiền: ${sumMoney}</h3>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <img src="../../../img/order.png">
                    Chưa có đơn hàng nào
                </c:otherwise>

            </c:choose>
        </div>
    </div>
</main>
</body>