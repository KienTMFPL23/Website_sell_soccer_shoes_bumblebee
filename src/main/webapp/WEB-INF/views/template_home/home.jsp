<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<main class="ps-main">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="/img/banner3.jpg" height="565px" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="/img/banner1.jpg" height="565px" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block w-100" src="/img/banner2.jpg" height="565px" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div class="ps-section--features-product ps-section masonry-root pt-100 pb-100">
        <div class="ps-container">
            <div class="ps-section__header mb-50">
                <h3 class="ps-section__title" data-mask="features">SẢN PHẨM TIÊU BIỂU</h3>
                <ul class="ps-masonry__filter">
                    <li class="current"><a href="#" data-filter="*">All </a></li>
                    <li><a href="#" data-filter=".nike">Nike </a></li>
                    <li><a href="#" data-filter=".adidas">Adidas </a></li>
                    <li><a href="#" data-filter=".men">Men </a></li>
                    <li><a href="#" data-filter=".women">Women </a></li>
                    <li><a href="#" data-filter=".kids">Kids </a></li>
                </ul>
            </div>
            <div class="ps-section__content pb-50">
                <div class="masonry-wrapper" data-col-md="4" data-col-sm="2" data-col-xs="1" data-gap="30"
                     data-radio="100%">
                    <div class="ps-masonry row">
                        <div class="grid-sizer"></div>
                        <c:forEach var="item" items="${listSP.content}">
                            <div class="grid-item col-md-3">
                                <div class="grid-item__content-wrapper">
                                    <div class="ps-shoe mb-30">
                                        <div class="ps-shoe__thumbnail">
                                            <a class="ps-shoe__favorite" href="#"><i class="ps-icon-heart"></i></a>
                                            <img
                                                    src="../../../uploads/${item.hinhAnhs.tenanh}" height="250px"
                                                    alt=""><a
                                                class="ps-shoe__overlay" href="/bumblebee/detail/${item.id}"></a>
                                        </div>
                                        <div class="ps-shoe__content">
                                            <div class="ps-shoe__variants" style="margin-top: 10px">
                                                <div class="ps-shoe__variant normal">
                                                    <img src="../../../uploads/${item.hinhAnhs.duongdan1}">
                                                    <img src="../../../uploads/${item.hinhAnhs.duongdan2}">
                                                    <img src="../../../uploads/${item.hinhAnhs.duongdan3}">
                                                    <img src="../../../uploads/${item.hinhAnhs.duongdan4}">
                                                </div>
                                                <div class="ps-shoe__variant butAddCart">
                                                    <a data-toggle="modal"
                                                       data-target="#myModal"
                                                       style="color: white;width: 240px"
                                                       href="">Thêm giỏ
                                                        hàng</a>
                                                </div>
                                            </div>
                                            <div class="ps-shoe__detail" style="margin-top: 10px">
                                                <div class="product_name">
                                                    <a href="#" style="font-weight: 600;">${item.sanPham.tenSanPham}</a>
                                                </div>
                                                <div class="product_price">
                                                <span>
                                                    <fmt:formatNumber value="${item.giaBan}"
                                                                      type="currency"/>
                                                </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModal" style="margin-top: 200px">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm Sản phẩm vào giỏ hàng</h4>
                    <button type="button" class="close"
                            data-dismiss="modal">&times;
                    </button>
                </div>
                <div class="modal-body">
                    <p>Nội dung của modal ở đây...</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                            data-dismiss="modal">Đóng
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="ps-section--offer">
        <div class="ps-column"><a class="ps-offer" href="#"><img
                src="../../../img/banner4.png" width="100%" height="295px" alt=""></a></div>
        <div class="ps-column"><a class="ps-offer" href="product-listing.html"><img
                src="../../../img/banner5.png" width="100%" height="295px" alt=""></a></div>
    </div>
    <div class="ps-section--sale-off ps-section pt-80 pb-40">
        <div class="ps-container">
            <div class="ps-section__header mb-50">
                <h3 class="ps-section__title" data-mask="Sale off">- Hot Deal Today</h3>
            </div>
            <div class="ps-section__content">
                <div class="row">
                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 ">
                        <div class="ps-hot-deal">
                            <h3>Nike DUNK Max 95 OG</h3>
                            <p class="ps-hot-deal__price">Only: <span>£155</span></p>
                            <ul class="ps-countdown" data-time="December 13, 2017 15:37:25">
                                <li><span class="hours"></span>
                                    <p>Hours</p></li>
                                <li class="divider">:</li>
                                <li><span class="minutes"></span>
                                    <p>minutes</p></li>
                                <li class="divider">:</li>
                                <li><span class="seconds"></span>
                                    <p>Seconds</p></li>
                            </ul>
                            <a class="ps-btn" href="#">Order Today<i class="ps-icon-next"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-12 ">
                        <div class="ps-hotspot"><a class="point first active" href="javascript:;"><i
                                class="fa fa-plus"></i>
                            <div class="ps-hotspot__content">
                                <p class="heading">JUMP TO HEADER</p>
                                <p>Dynamic Fit Collar en la zona del tobillo que une la parte inferior de la pierna y el
                                    pie sin reducir la libertad de movimiento.</p>
                            </div>
                        </a><a class="point second" href="javascript:;"><i class="fa fa-plus"></i>
                            <div class="ps-hotspot__content">
                                <p class="heading">JUMP TO HEADER</p>
                                <p>Dynamic Fit Collar en la zona del tobillo que une la parte inferior de la pierna y el
                                    pie sin reducir la libertad de movimiento.</p>
                            </div>
                        </a><a class="point third" href="javascript:;"><i class="fa fa-plus"></i>
                            <div class="ps-hotspot__content">
                                <p class="heading">JUMP TO HEADER</p>
                                <p>Dynamic Fit Collar en la zona del tobillo que une la parte inferior de la pierna y el
                                    pie sin reducir la libertad de movimiento.</p>
                            </div>
                        </a><img src="../../../images_template/hot-deal.png" alt=""></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ps-section ps-section--top-sales ps-owl-root pt-80 pb-80">
        <div class="ps-container">
            <div class="ps-section__header mb-50">
                <div class="row">
                    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 ">
                        <h3 class="ps-section__title" data-mask="BEST SALE">- Top Sales</h3>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 ">
                        <div class="ps-owl-actions"><a class="ps-prev" href="#"><i class="ps-icon-arrow-right"></i>Prev</a><a
                                class="ps-next" href="#">Next<i class="ps-icon-arrow-left"></i></a></div>
                    </div>
                </div>
            </div>
            <div class="ps-section__content">
                <div class="ps-owl--colection owl-slider" data-owl-auto="true" data-owl-loop="true"
                     data-owl-speed="5000" data-owl-gap="30" data-owl-nav="false" data-owl-dots="false"
                     data-owl-item="4" data-owl-item-xs="1" data-owl-item-sm="2" data-owl-item-md="3"
                     data-owl-item-lg="4" data-owl-duration="1000" data-owl-mousedrag="on">
                    <c:forEach var="item" items="${listSP.content}">
                        <div class="ps-shoes--carousel">
                            <div class="ps-shoe">
                                <div class="ps-shoe__thumbnail">

                                    <a class="ps-shoe__favorite" href="#"><i class="ps-icon-heart"></i></a>
                                    <img
                                            src="../../../uploads/${item.hinhAnhs.tenanh}" height="250px" alt=""><a
                                        class="ps-shoe__overlay" href="/bumblebee/detail/${item.id}"></a>

                                </div>
                                <div class="ps-shoe__content">
                                    <div class="ps-shoe__variants" style="margin-top: 10px">
                                        <div class="ps-shoe__variant normal">
                                            <img src="../../../uploads/${item.hinhAnhs.duongdan1}">
                                            <img src="../../../uploads/${item.hinhAnhs.duongdan2}">
                                            <img src="../../../uploads/${item.hinhAnhs.duongdan3}">
                                            <img src="../../../uploads/${item.hinhAnhs.duongdan4}">
                                        </div>
                                        <div class="ps-shoe__variant butAddCart">
                                            <button>Thêm giỏ hàng</button>
                                        </div>
                                    </div>
                                    <div class="ps-shoe__detail">
                                        <div class="product_name">
                                            <a href="#" style="font-weight: 600;">${item.sanPham.tenSanPham}</a>
                                        </div>
                                        <div class="product_price">
                                            <span id="formattedPrice"><fmt:formatNumber value="${item.giaBan}"
                                                                                        type="currency"/></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>

</main>