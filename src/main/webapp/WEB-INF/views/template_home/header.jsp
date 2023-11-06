<%@ page pageEncoding="utf-8" %>
<title>
    Giày đá bóng Bumblebee
</title>
<link rel="icon" href="../../../images_template/logo_bumblebee.png">


<header class="header_index header">
    <div>
        <div class="header_top">
            <div class="container">
                <div class="container-flex">
                    <form class="ps-search" action="/bumblebee/product_list" style="width: 300px" method="post">
                        <input type="text" placeholder="Search Product…">
                        <button><i class="ps-icon-search"></i></button>
                    </form>
                    <div class="header-policy">
                        <div class="item-policy item_policy_top_hed flex middle" bis_skin_checked="1">
                            <a href="#">
                                <img src="https://www.mayxaydungtudong.com/img/policy1.png" alt="">
                            </a>
                            <div class="info a-left" bis_skin_checked="1">
                                <a href="#"><b>Giao hàng</b></a>
                                <p>Miễn phí trên toàn quốc</p>
                            </div>
                        </div>
                        <div class="item-policy flex middle" bis_skin_checked="1">
                            <a href="#">
                                <img src="https://www.mayxaydungtudong.com/img/icon-phone-header.jpg" alt="">
                            </a>
                            <div class="info a-left" bis_skin_checked="1">
                                <a href="#"><b>Hỗ trợ 24/7</b></a>
                                <div class="tit_header_box">
                                    <p> Tư vấn khách hàng: <a href="tel:032 878 6363"> <b>032 878 6363</b></a></p>
                                    <p> Chăm sóc khách hàng: <a href="tel:032 878 6363"> <b>094 357 2345</b></a></p>
                                </div>
                            </div>
                        </div>
                        <div class="item-policy flex middle" bis_skin_checked="1">
                            <a href="#">
                                <img src="https://www.mayxaydungtudong.com/img/policy3.png" alt="x`">
                            </a>
                            <div class="info a-left" bis_skin_checked="1">
                                <a href="#"><b>Giờ làm việc</b></a>
                                <p>Tất cả các ngày trong tuần</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="header_bottom">
            <div class="container">
                <div class="container-flex">
                    <div class="header_bottom--logo">
                        <div class="img-logo">
                            <a href="/bumblebee/home">
                                <img src="../../../images_template/logo_bumblebee.png" width="50px">
                                <span style="font-size: 18px;color: white;margin-left: 10px">Bumblebee</span>
                            </a>
                        </div>
                    </div>
                    <div class="header_bottom--menu">
                        <ul class="main-menu">
                            <li class="menu-item"><a class="menu-link" href="/bumblebee/home">Trang
                                Chủ</a>
                            </li>
                            <li class="menu-item"><a href="/bumblebee/product_list">Sản Phẩm</a></li>
                            <li class="menu-item"><a href="#">Giới Thiệu</a>
                            </li>
                            <li class="menu-item"><a href="#">Liên Hệ</a></li>
                        </ul>
                    </div>
                    <div>
                        <div class="navigation__column right">
                            <div class="info  user-icon" style="">
                                <div class="info_right">
                                    <c:if test="${userLogged.username == null}">
                                        <p style="margin-bottom: 0px;color: white;margin-right: 10px"></p>
                                        <a href="/bumblebee/don-mua"><img src="../../../img/in4.png" alt=""
                                                                          width="30px"></a>
                                    </c:if>
                                    <c:if test="${userLogged.username != null}">
                                        <p style="margin-bottom: 0px;color: white;margin-right: 10px;font-size: 15px">${userLogged.username}</p>
                                        <a href="/bumblebee/don-mua"><img src="../../../img/in4.png" alt=""
                                                                          width="30px"></a>
                                    </c:if>

                                </div>
                                <div class="dropdown-content">
                                    <a class="dropdown-item" href="#">Đăng nhập</a>
                                    <a class="dropdown-item" href="#">Đăng Ký</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">Đăng Xuất</a>
                                </div>
                            </div>
                            <div>
                                <div class="ps-cart">
                                    <a class="ps-cart__toggle" href="/bumblebee/cart" style="top: 0px;">
                                        <span style="background-color: #37517E;display: ${slGioHang == null ?"none":"block"}"><i>${slGioHang}</i></span>
                                        <i class="ps-icon-shopping-cart"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
