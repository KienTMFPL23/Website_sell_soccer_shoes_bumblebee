<%@ page pageEncoding="utf-8" %>
<title>
    Giày đá bóng Bumblebee
</title>
<link rel="icon" href="../../../images_template/logo_bumblebee.png">


<header class="header" style="background-color: #37517E;padding: 0px 10px">
    <nav class="navigation">
        <div class="container-fluid" style="display: flex;align-items: center">
            <div class="navigation__column left">
                <div class="header__logo">
                    <a class="ps-logo" href="/bumblebee/home" style="padding: 0">
                        <img src="../../../images_template/logo_bumblebee.png" width="80px">
                    </a>
                    <span style="color: white;font-weight: 600;font-size: 16px;margin-left: 20px">BUMBLEBEE</span>
                </div>
            </div>
            <div class="navigation__column center">
                <ul class="main-menu menu">
                    <li class="menu-item menu-item-has-children dropdown"><a class="menu-link" href="/bumblebee/home">Trang
                        Chủ</a>
                    </li>
                    <li class="menu-item"><a href="/bumblebee/product_list">Sản Phẩm</a></li>
                    <li class="menu-item menu-item-has-children has-mega-menu"><a href="#">Giới Thiệu</a>
                    </li>
                    <li class="menu-item"><a href="#">Liên Hệ</a></li>
                </ul>
            </div>
            <div class="navigation__column right" style="display: flex;flex-direction: column;
    gap: 10px;">
                <div class="info" style="">
                    <div class="info_right">
                        <p style="margin-bottom: 0px;color: white;margin-right: 10px">${userLogged.username}</p>
                        <a href="#"><img src="../../../img/in4.png" alt="" width="30px"></a>
                    </div>
                </div>
                <div>
                    <form class="ps-search--header" action="/bumblebee/product_list" method="post"
                          style="top: 0px;margin-right: 30px">
                        <input class="form-control" type="text" placeholder="Search Product…">
                        <button><i class="ps-icon-search"></i></button>
                    </form>
                    <div class="ps-cart"><a class="ps-cart__toggle" href="/bumblebee/cart" style="top: 0px;"><span
                            style="background-color: #37517E"><i>20</i></span><i
                            class="ps-icon-shopping-cart"></i></a>
                    </div>
                </div>
            </div>
        </div>

    </nav>
</header>
