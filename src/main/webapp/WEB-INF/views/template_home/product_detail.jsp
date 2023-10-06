<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main class="ps-main">
    <div class="test">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 ">
                </div>
            </div>
        </div>
    </div>
    <div class="ps-product--detail pt-60">
        <div class="ps-container">
            <div class="row">
                <div class="col-lg-10 col-md-12 col-lg-offset-1">
                    <div class="ps-product__thumbnail">
                        <div class="ps-product__preview">
                            <div class="ps-product__variations">
                                <div class="item"><img src="../../../images_template/shoe-detail/1.jpg" alt=""></div>
                                <div class="item"><img src="../../../images_template/shoe-detail/2.jpg" alt=""></div>
                                <div class="item"><img src="../../../images_template/shoe-detail/2.jpg" alt=""></div>
                                <div class="item"><img src="../../../images_template/shoe-detail/2.jpg" alt=""></div>
                                <div class="item"><img src="../../../images_template/shoe-detail/2.jpg" alt=""></div>
                            </div>
                        </div>
                        <div class="ps-product__image">
                            <div class="item"><img class="zoom" src="../../../images_template/shoe-detail/1.jpg" alt=""
                                                   data-zoom-image="images/shoe-detail/1.jpg"></div>
                            <div class="item"><img class="zoom" src="../../../images_template/shoe-detail/2.jpg" alt=""
                                                   data-zoom-image="images/shoe-detail/2.jpg"></div>
                            <div class="item"><img class="zoom" src="../../../images_template/shoe-detail/3.jpg" alt=""
                                                   data-zoom-image="images/shoe-detail/3.jpg"></div>
                        </div>
                    </div>
                    <div class="ps-product__thumbnail--mobile">
                        <div class="ps-product__main-img"><img src="../../../images_template/shoe-detail/1.jpg" alt="">
                        </div>
                        <div class="ps-product__preview owl-slider" data-owl-auto="true" data-owl-loop="true"
                             data-owl-speed="5000" data-owl-gap="20" data-owl-nav="true" data-owl-dots="false"
                             data-owl-item="3" data-owl-item-xs="3" data-owl-item-sm="3" data-owl-item-md="3"
                             data-owl-item-lg="3" data-owl-duration="1000" data-owl-mousedrag="on"><img
                                src="../../../images_template/shoe-detail/1.jpg" alt=""><img
                                src="../../../images_template/shoe-detail/2.jpg" alt=""><img
                                src="../../../images_template/shoe-detail/3.jpg" alt=""></div>
                    </div>
                    <div class="ps-product__info">
                        <h1>${ctsp.sanPham.tenSanPham}</h1>
                        <h3 class="ps-product__price"><fmt:formatNumber value="${ctsp.giaBan}" type="currency" />
                            <del style="color: red">2.000.000đ</del>
                        </h3>
                        <div class="ps-product__block ps-product__style">
                            <h4>Chọn màu sắc</h4>
                            <ul>
                                <li><a href="product-detail.html"><img src="../../../images_template/shoe/sidebar/1.jpg"
                                                                       alt=""></a></li>
                                <li><a href="product-detail.html"><img src="../../../images_template/shoe/sidebar/2.jpg"
                                                                       alt=""></a></li>
                                <li><a href="product-detail.html"><img src="../../../images_template/shoe/sidebar/3.jpg"
                                                                       alt=""></a></li>
                                <li><a href="product-detail.html"><img src="../../../images_template/shoe/sidebar/2.jpg"
                                                                       alt=""></a></li>
                            </ul>
                        </div>
                        <div class="ps-product__block ps-product__size">
                            <h4>Kích cỡ<a href="#">Size chart</a></h4>
                            <div style="display: flex;justify-content: space-between">
                                <select class="ps-select ">
                                    <option value="1">Chọn kích cỡ</option>
                                    <c:forEach var="kc" items="${listKC}">
                                        <option value="">${kc.size}</option>
                                    </c:forEach>
                                </select>
                                <div  style="display: flex;align-items: center">
                                    <p>Số lượng</p>
                                    <div class="form-group">
                                        <input class="form-control" type="number" value="1">
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="ps-product__shopping"><a class="ps-btn mb-10" href="cart.html" style="background-color: #37517E;font-size: 15px">Thêm vào giỏ hàng<i
                                class="ps-icon-next"></i></a>
                            <div class="ps-product__actions"><a class="mr-10" href="whishlist.html"><i
                                    class="ps-icon-heart"></i></a><a href="compare.html"><i
                                    class="ps-icon-share"></i></a></div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="ps-product__content mt-50">
                        <ul class="tab-list" role="tablist">
                            <li class="active"><a href="#tab_01" aria-controls="tab_01" role="tab" data-toggle="tab">Mô tả</a>
                            </li>
                            <li><a href="#tab_02" aria-controls="tab_02" role="tab" data-toggle="tab">Chính sách đổi trả</a></li>
                            <li><a href="#tab_03" aria-controls="tab_03" role="tab" data-toggle="tab">Đánh giá</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tab-content mb-60">
                        <div class="tab-pane active" role="tabpanel" id="tab_01">
                            <p>${ctsp.moTaCT}</p>
                        </div>
                        <div class="tab-pane" role="tabpanel" id="tab_02">
                            <p class="mb-20">1 review for <strong>Shoes Air Jordan</strong></p>
                            <div class="ps-review">
                                <div class="ps-review__thumbnail"><img src="../../../images_template/user/1.jpg" alt="">
                                </div>
                                <div class="ps-review__content">
                                    <header>
                                        <select class="ps-rating">
                                            <option value="1">1</option>
                                            <option value="1">2</option>
                                            <option value="1">3</option>
                                            <option value="1">4</option>
                                            <option value="5">5</option>
                                        </select>
                                        <p>By<a href=""> Alena Studio</a> - November 25, 2017</p>
                                    </header>
                                    <p>Soufflé danish gummi bears tart. Pie wafer icing. Gummies jelly beans powder.
                                        Chocolate bar pudding macaroon candy canes chocolate apple pie chocolate cake.
                                        Sweet caramels sesame snaps halvah bear claw wafer. Sweet roll soufflé muffin
                                        topping muffin brownie. Tart bear claw cake tiramisu chocolate bar gummies
                                        dragée lemon drops brownie.</p>
                                </div>
                            </div>
                            <form class="ps-product__review" action="_action" method="post">
                                <h4>ADD YOUR REVIEW</h4>
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
                                        <div class="form-group">
                                            <label>Name:<span>*</span></label>
                                            <input class="form-control" type="text" placeholder="">
                                        </div>
                                        <div class="form-group">
                                            <label>Email:<span>*</span></label>
                                            <input class="form-control" type="email" placeholder="">
                                        </div>
                                        <div class="form-group">
                                            <label>Your rating<span></span></label>
                                            <select class="ps-rating">
                                                <option value="1">1</option>
                                                <option value="1">2</option>
                                                <option value="1">3</option>
                                                <option value="1">4</option>
                                                <option value="5">5</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12 ">
                                        <div class="form-group">
                                            <label>Your Review:</label>
                                            <textarea class="form-control" rows="6"></textarea>
                                        </div>
                                        <div class="form-group">
                                            <button class="ps-btn ps-btn--sm">Submit<i class="ps-icon-next"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="tab-pane" role="tabpanel" id="tab_03">
                            <p>Add your tag <span> *</span></p>
                            <form class="ps-product__tags" action="_action" method="post">
                                <div class="form-group">
                                    <input class="form-control" type="text" placeholder="">
                                    <button class="ps-btn ps-btn--sm">Add Tags</button>
                                </div>
                            </form>
                        </div>
                        <div class="tab-pane" role="tabpanel" id="tab_04">
                            <div class="form-group">
                                <textarea class="form-control" rows="6"
                                          placeholder="Enter your addition here..."></textarea>
                            </div>
                            <div class="form-group">
                                <button class="ps-btn" type="button">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ps-section ps-section--top-sales ps-owl-root pt-40 pb-80">
        <div class="ps-container">
            <div class="ps-section__header mb-50">
                <div class="row">
                    <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 ">
                        <h3 class="ps-section__title" data-mask="Related item">- YOU MIGHT ALSO LIKE</h3>
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
                    <div class="ps-shoes--carousel">
                        <div class="ps-shoe">
                            <div class="ps-shoe__thumbnail">
                                <div class="ps-badge"><span>New</span></div>
                                <a class="ps-shoe__favorite" href="#"><i class="ps-icon-heart"></i></a><img
                                    src="../../../images_template/shoe/1.jpg" alt=""><a class="ps-shoe__overlay"
                                                                                        href="product-detail.html"></a>
                            </div>
                            <div class="ps-shoe__content">
                                <div class="ps-shoe__variants">
                                    <div class="ps-shoe__variant normal"><img src="../../../images_template/shoe/2.jpg"
                                                                              alt=""><img
                                            src="../../../images_template/shoe/3.jpg" alt=""><img
                                            src="../../../images_template/shoe/4.jpg" alt=""><img
                                            src="../../../images_template/shoe/5.jpg" alt=""></div>
                                    <select class="ps-rating ps-shoe__rating">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                        <option value="1">4</option>
                                        <option value="2">5</option>
                                    </select>
                                </div>
                                <div class="ps-shoe__detail"><a class="ps-shoe__name" href="product-detai.html">Air
                                    Jordan 7 Retro</a>
                                    <p class="ps-shoe__categories"><a href="#">Men shoes</a>,<a href="#"> Nike</a>,<a
                                            href="#"> Jordan</a></p><span class="ps-shoe__price"> £ 120</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ps-shoes--carousel">
                        <div class="ps-shoe">
                            <div class="ps-shoe__thumbnail">
                                <div class="ps-badge"><span>New</span></div>
                                <div class="ps-badge ps-badge--sale ps-badge--2nd"><span>-35%</span></div>
                                <a class="ps-shoe__favorite" href="#"><i class="ps-icon-heart"></i></a><img
                                    src="../../../images_template/shoe/2.jpg" alt=""><a class="ps-shoe__overlay"
                                                                                        href="product-detail.html"></a>
                            </div>
                            <div class="ps-shoe__content">
                                <div class="ps-shoe__variants">
                                    <div class="ps-shoe__variant normal"><img src="../../../images_template/shoe/2.jpg"
                                                                              alt=""><img
                                            src="../../../images_template/shoe/3.jpg" alt=""><img
                                            src="../../../images_template/shoe/4.jpg" alt=""><img
                                            src="../../../images_template/shoe/5.jpg" alt=""></div>
                                    <select class="ps-rating ps-shoe__rating">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                        <option value="1">4</option>
                                        <option value="2">5</option>
                                    </select>
                                </div>
                                <div class="ps-shoe__detail"><a class="ps-shoe__name" href="product-detai.html">Air
                                    Jordan 7 Retro</a>
                                    <p class="ps-shoe__categories"><a href="#">Men shoes</a>,<a href="#"> Nike</a>,<a
                                            href="#"> Jordan</a></p><span class="ps-shoe__price">
                        <del>£220</del> £ 120</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ps-shoes--carousel">
                        <div class="ps-shoe">
                            <div class="ps-shoe__thumbnail">
                                <div class="ps-badge"><span>New</span></div>
                                <a class="ps-shoe__favorite" href="#"><i class="ps-icon-heart"></i></a><img
                                    src="../../../images_template/shoe/3.jpg" alt=""><a class="ps-shoe__overlay"
                                                                                        href="product-detail.html"></a>
                            </div>
                            <div class="ps-shoe__content">
                                <div class="ps-shoe__variants">
                                    <div class="ps-shoe__variant normal"><img src="../../../images_template/shoe/2.jpg"
                                                                              alt=""><img
                                            src="../../../images_template/shoe/3.jpg" alt=""><img
                                            src="../../../images_template/shoe/4.jpg" alt=""><img
                                            src="../../../images_template/shoe/5.jpg" alt=""></div>
                                    <select class="ps-rating ps-shoe__rating">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                        <option value="1">4</option>
                                        <option value="2">5</option>
                                    </select>
                                </div>
                                <div class="ps-shoe__detail"><a class="ps-shoe__name" href="product-detai.html">Air
                                    Jordan 7 Retro</a>
                                    <p class="ps-shoe__categories"><a href="#">Men shoes</a>,<a href="#"> Nike</a>,<a
                                            href="#"> Jordan</a></p><span class="ps-shoe__price"> £ 120</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ps-shoes--carousel">
                        <div class="ps-shoe">
                            <div class="ps-shoe__thumbnail"><a class="ps-shoe__favorite" href="#"><i
                                    class="ps-icon-heart"></i></a><img src="../../../images_template/shoe/4.jpg" alt=""><a
                                    class="ps-shoe__overlay" href="product-detail.html"></a>
                            </div>
                            <div class="ps-shoe__content">
                                <div class="ps-shoe__variants">
                                    <div class="ps-shoe__variant normal"><img src="../../../images_template/shoe/2.jpg"
                                                                              alt=""><img
                                            src="../../../images_template/shoe/3.jpg" alt=""><img
                                            src="../../../images_template/shoe/4.jpg" alt=""><img
                                            src="../../../images_template/shoe/5.jpg" alt=""></div>
                                    <select class="ps-rating ps-shoe__rating">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                        <option value="1">4</option>
                                        <option value="2">5</option>
                                    </select>
                                </div>
                                <div class="ps-shoe__detail"><a class="ps-shoe__name" href="product-detai.html">Air
                                    Jordan 7 Retro</a>
                                    <p class="ps-shoe__categories"><a href="#">Men shoes</a>,<a href="#"> Nike</a>,<a
                                            href="#"> Jordan</a></p><span class="ps-shoe__price"> £ 120</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ps-shoes--carousel">
                        <div class="ps-shoe">
                            <div class="ps-shoe__thumbnail">
                                <div class="ps-badge"><span>New</span></div>
                                <a class="ps-shoe__favorite" href="#"><i class="ps-icon-heart"></i></a><img
                                    src="../../../images_template/shoe/5.jpg" alt=""><a class="ps-shoe__overlay"
                                                                                        href="product-detail.html"></a>
                            </div>
                            <div class="ps-shoe__content">
                                <div class="ps-shoe__variants">
                                    <div class="ps-shoe__variant normal"><img src="../../../images_template/shoe/2.jpg"
                                                                              alt=""><img
                                            src="../../../images_template/shoe/3.jpg" alt=""><img
                                            src="../../../images_template/shoe/4.jpg" alt=""><img
                                            src="../../../images_template/shoe/5.jpg" alt=""></div>
                                    <select class="ps-rating ps-shoe__rating">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                        <option value="1">4</option>
                                        <option value="2">5</option>
                                    </select>
                                </div>
                                <div class="ps-shoe__detail"><a class="ps-shoe__name" href="product-detai.html">Air
                                    Jordan 7 Retro</a>
                                    <p class="ps-shoe__categories"><a href="#">Men shoes</a>,<a href="#"> Nike</a>,<a
                                            href="#"> Jordan</a></p><span class="ps-shoe__price"> £ 120</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ps-shoes--carousel">
                        <div class="ps-shoe">
                            <div class="ps-shoe__thumbnail"><a class="ps-shoe__favorite" href="#"><i
                                    class="ps-icon-heart"></i></a><img src="../../../images_template/shoe/6.jpg" alt=""><a
                                    class="ps-shoe__overlay" href="product-detail.html"></a>
                            </div>
                            <div class="ps-shoe__content">
                                <div class="ps-shoe__variants">
                                    <div class="ps-shoe__variant normal"><img src="../../../images_template/shoe/2.jpg"
                                                                              alt=""><img
                                            src="../../../images_template/shoe/3.jpg" alt=""><img
                                            src="../../../images_template/shoe/4.jpg" alt=""><img
                                            src="../../../images_template/shoe/5.jpg" alt=""></div>
                                    <select class="ps-rating ps-shoe__rating">
                                        <option value="1">1</option>
                                        <option value="1">2</option>
                                        <option value="1">3</option>
                                        <option value="1">4</option>
                                        <option value="2">5</option>
                                    </select>
                                </div>
                                <div class="ps-shoe__detail"><a class="ps-shoe__name" href="product-detai.html">Air
                                    Jordan 7 Retro</a>
                                    <p class="ps-shoe__categories"><a href="#">Men shoes</a>,<a href="#"> Nike</a>,<a
                                            href="#"> Jordan</a></p><span class="ps-shoe__price"> £ 120</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>