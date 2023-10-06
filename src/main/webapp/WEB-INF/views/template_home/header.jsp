<%@ page pageEncoding="utf-8" %>
<div class="header--sidebar"></div>
<header class="header">
    <nav class="navigation">
        <div class="container-fluid">
            <div class="navigation__column left">
                <div class="header__logo">
                    <a class="ps-logo" href="/bumblebee/home">
                        <img src="../../../images_template/logo_bumblebee.png" width="50px">
                    </a>
                    <span style="color: #37517E;font-weight: 600;font-size: 16px;margin-left: 20px">BUMBLEBEE</span>
                </div>
            </div>
            <div class="navigation__column center">
                <ul class="main-menu menu">
                    <li class="menu-item menu-item-has-children dropdown"><a href="/bumblebee/home">Trang Chủ</a>
                    </li>
                    <li class="menu-item"><a href="/bumblebee/product_list">Sản Phẩm</a></li>
                    <li class="menu-item menu-item-has-children has-mega-menu"><a href="#">Giới Thiệu</a>
                    </li>
                    <li class="menu-item"><a href="#">Liên Hệ</a></li>
                </ul>
            </div>
            <div class="navigation__column right">
                <form class="ps-search--header" action="do_action" method="post" style="background-color: #e8e8e8;border-radius: 20px">
                    <input class="form-control" type="text" placeholder="Search Product…">
                    <button><i class="ps-icon-search"></i></button>
                </form>
                <div class="ps-cart"><a class="ps-cart__toggle" href="#"><span><i>20</i></span><i
                        class="ps-icon-shopping-cart"></i></a>
                    <div class="ps-cart__listing">
                        <div class="ps-cart__content">
                            <div class="ps-cart-item"><a class="ps-cart-item__close" href="#"></a>
                                <div class="ps-cart-item__thumbnail"><a href="product-detail.html"></a><img
                                        src="../../../images_template/cart-preview/1.jpg" alt=""></div>
                                <div class="ps-cart-item__content"><a class="ps-cart-item__title"
                                                                      href="product-detail.html">Amazin’ Glazin’</a>
                                    <p><span>Quantity:<i>12</i></span><span>Total:<i>£176</i></span></p>
                                </div>
                            </div>
                            <div class="ps-cart-item"><a class="ps-cart-item__close" href="#"></a>
                                <div class="ps-cart-item__thumbnail"><a href="product-detail.html"></a><img
                                        src="../../../images_template/cart-preview/2.jpg" alt=""></div>
                                <div class="ps-cart-item__content"><a class="ps-cart-item__title"
                                                                      href="product-detail.html">The Crusty
                                    Croissant</a>
                                    <p><span>Quantity:<i>12</i></span><span>Total:<i>£176</i></span></p>
                                </div>
                            </div>
                            <div class="ps-cart-item"><a class="ps-cart-item__close" href="#"></a>
                                <div class="ps-cart-item__thumbnail"><a href="product-detail.html"></a><img
                                        src="../../../images_template/cart-preview/3.jpg" alt=""></div>
                                <div class="ps-cart-item__content"><a class="ps-cart-item__title"
                                                                      href="product-detail.html">The Rolling Pin</a>
                                    <p><span>Quantity:<i>12</i></span><span>Total:<i>£176</i></span></p>
                                </div>
                            </div>
                        </div>
                        <div class="ps-cart__total">
                            <p>Number of items:<span>36</span></p>
                            <p>Item Total:<span>£528.00</span></p>
                        </div>
                        <div class="ps-cart__footer"><a class="ps-btn" href="cart.html">Check out<i
                                class="ps-icon-arrow-left"></i></a></div>
                    </div>
                </div>
                <div class="menu-toggle"><span></span></div>
            </div>
        </div>
    </nav>
</header>