<%@ page pageEncoding="utf-8" %>
<link rel="icon" href="../../../images_template/logo_bumblebee.png">
<title>
    Giày đá bóng Bumblebee
</title>
<div class="info" style="margin-top: 10px">
    <div class="container-fluid">
        <div class="info_right">
            <p style="margin-bottom: 0px">${userLogged.username}</p>
            <a href="#"><img src="../../../img/in4.png" alt="" width="30px"></a>
        </div>
    </div>
</div>
<hr>
<header class="header">
    <nav class="navigation">
        <div class="container-fluid" style="display: flex;align-items: center">
            <div class="navigation__column left">
                <div class="header__logo">
                    <a class="ps-logo" href="/bumblebee/home" style="padding: 0">
                        <img src="../../../images_template/logo_bumblebee.png" width="50px">
                    </a>
                    <span style="color: #37517E;font-weight: 600;font-size: 16px;margin-left: 20px">BUMBLEBEE</span>
                </div>
            </div>
            <div class="navigation__column center">
                <ul class="main-menu menu">
                    <li class="menu-item menu-item-has-children dropdown"><a class="menu-link" href="/bumblebee/home">Trang Chủ</a>
                    </li>
                    <li class="menu-item"><a href="/bumblebee/product_list">Sản Phẩm</a></li>
                    <li class="menu-item menu-item-has-children has-mega-menu"><a href="#">Giới Thiệu</a>
                    </li>
                    <li class="menu-item"><a href="#">Liên Hệ</a></li>
                </ul>
            </div>
            <div class="navigation__column right">
                <form class="ps-search--header" action="/bumblebee/product_list" method="post"
                      style="top: 0px;margin-right: 30px">
                    <input class="form-control" type="text" placeholder="Search Product…">
                    <button><i class="ps-icon-search"></i></button>
                </form>
                <div class="ps-cart"><a class="ps-cart__toggle" href="/bumblebee/cart" style="top: 0px;"><span
                        style="background-color: #37517E"><i>20</i></span><i
                        class="ps-icon-shopping-cart"></i></a>
                </div>
                <div class="menu-toggle"><span></span></div>
            </div>
        </div>
    </nav>
</header>
