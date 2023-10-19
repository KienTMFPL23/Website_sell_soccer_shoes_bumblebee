<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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