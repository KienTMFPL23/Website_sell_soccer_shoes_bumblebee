<%@ page pageEncoding="utf-8" %>
<link rel="icon" href="../../../images_template/logo_bumblebee.png">
<div class="header">
    <div class="top_nav">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                </div>
                <div class="col-md-6 text-right">
                    <div class="top_nav_right">
                        <ul class="top_nav_menu">
                            <li class="account">
                                <a href="#">
                                    My Account
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="account_selection">
                                    <li><a href="/bumblebee/logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Đăng xuất</a></li>
                                    <li><a href="/bumblebee/login"><i class="fa fa-sign-in" aria-hidden="true"></i>Đăng nhập</a></li>
                                    <li><a href="/bumblebee/register"><i class="fa fa-user-plus" aria-hidden="true"></i>Đăng kí</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main_nav_container">
        <div class="container-fluid" style="padding: 0 40px;">
            <div class="row">
                <div class="col-lg-12 text-right">
                    <div class="logo_container">
                        <a href="/bumblebee/home">Bumblebee</a>
                    </div>
                    <nav class="navbar">
                        <ul class="navbar_menu" style="margin-bottom: 0px">
                            <li><a class="menu-link" href="/bumblebee/home">Trang
                                Chủ</a></li>
                            <li><a href="/bumblebee/product_list">Sản Phẩm</a></li>
                            <li><a href="#">Liên Hệ</a></li>

                            <li><a href="/bumblebee/chinh-sach-doi-tra">Chính Sách</a></li>

                        </ul>
                        <ul class="navbar_user">
                            <li><a href="#"><i class="fa fa-search" aria-hidden="true"></i></a></li>
                            <c:if test="${userLogged.username == null}">
                                <li><a href="/bumblebee/don-mua"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                            </c:if>
                            <c:if test="${userLogged.username != null}">
                                <li><a href="/bumblebee/don-mua"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                            </c:if>

                            <li class="checkout">
                                <a href="/bumblebee/cart">
                                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                    <span id="checkout_items" class="checkout_items" style="display: ${slGioHang == null ?"none":"flex"}">${slGioHang}</span>
                                </a>
                            </li>
                        </ul>
                        <div class="hamburger_container">
                            <i class="fa fa-bars" aria-hidden="true"></i>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../../js_template/custom.js"></script>


