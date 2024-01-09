<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
    .modal-content {
        padding: 1.25rem;
        overflow: visible;
        position: relative;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        width: 100%;
        pointer-events: auto;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid rgba(0,0,0,.2);
        border-radius: 0.3rem;
        outline: 0;
    }
</style>
<main class="ps-main">
    <div class="main_slider" style="background-image: url(../../../img/banner1.jpg)">
        <%--        <img src="/img/slider_1.jpg" class="img_banner">--%>
        <div class="container fill_height">
            <div class="row align-items-center">
                <div class="col" style="padding: 0 40px">
                    <div class="main_slider_content">
                        <h6>Spring / Summer Collection 2023</h6>
                        <h1>Get up to 30% Off New Arrivals</h1>
                        <div class="red_button shop_now_button"><a href="#">shop now</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="new_arrivals">
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <div class="section_title new_arrivals_title">
                        <h2>All Products</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="product-grid"
                         data-isotope="{ &quot;itemSelector&quot;: &quot;.product-item&quot;, &quot;layoutMode&quot;: &quot;fitRows&quot; }"
                         style="position: relative; height: 760px;">
                        <!-- Product 1 -->
                        <c:forEach var="item" items="${pageSP.content}" varStatus="loop">
                            <div class="product-item">
                                <div class="product discount product_filter"
                                     style="border-right: 1px solid rgb(233, 233, 233);">
                                    <div class="product_image">
                                        <a
                                                class="ps-shoe__overlay"
                                                href="/bumblebee/detail?idSP=${item.sanPham.id}&idCTSP=${item.id}&idMS=${item.mauSac.id}"><img
                                                src="../../../uploads/${item.hinhAnhs.tenanh}" alt="" width="221px"
                                                height="221px"></a>
                                    </div>
                                    <div class="favorite favorite_left"></div>
                                    <c:forEach var="km" items="${item.ctkm}">
                                        <c:if test="${km.trangThai == 0}">
                                            <c:if test="${km.khuyenMai.donVi == '%'}">
                                                <div class="product_bubble product_bubble_right product_bubble_red d-flex ">
                                                    <span>- ${km.khuyenMai.giaTri}${km.khuyenMai.donVi}</span></div>
                                            </c:if>
                                            <c:if test="${km.khuyenMai.donVi == 'VNĐ'}">
                                                <div class="product_bubble product_bubble_left product_bubble_green ">
                                                <span>- <fmt:formatNumber value="${km.khuyenMai.giaTri}"
                                                                          type="number"/>đ</span>
                                                </div>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                    <div class="product_info">
                                        <h6 class="product_name"><a
                                                href="/bumblebee/detail?idSP=${item.sanPham.id}&idCTSP=${item.id}&idMS=${item.mauSac.id}">${item.sanPham.tenSanPham}
                                            - ${item.mauSac.ten}</a>
                                        </h6>
                                        <div class="product_price">
                                            <c:if test="${not empty item.ctkm}">
                                                <c:set var="allTrangThai1" value="false"/>
                                                <c:forEach var="km" items="${item.ctkm}">
                                                    <c:if test="${km.trangThai == 0}">
                                                        <c:set var="allTrangThai1" value="true"/>
                                                        <label style="color: crimson;font-size: 15px"><fmt:formatNumber
                                                                value="${km.giaKhuyenMai}"
                                                                type="number"/> đ</label>
                                                        <span><fmt:formatNumber value="${item.giaBan}"
                                                                                type="number"/> đ</span>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${allTrangThai1 eq false}">
                                                    <label>
                                                        <fmt:formatNumber value="${item.giaBan}" type="number"/> đ
                                                    </label>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty item.ctkm}">
                                                <label>
                                                    <fmt:formatNumber value="${item.giaBan}" type="number"/> đ
                                                </label>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>

                                <div class="red_button add_to_cart_button butAddCart">
                                    <button class="addToCartBtn" data-toggle="modal"
                                            data-target="#kichCoModal_${loop.index}"
                                            style="color: white"
                                            data-item-id="${item.sanPham.id}"
                                            data-item-mausac="${item.mauSac.id}"> add to cart
                                    </button>
                                </div>
                            </div>
                            <!-- Modal -->
                            <form method="post"
                                  action="/bumblebee/add-to-cart?idMS=${item.mauSac.id}&idSP=${item.sanPham.id}&idCTSP=${item.id}">
                                <div class="modal fade" id="kichCoModal_${loop.index}" style="margin-top: 200px">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title">Thêm Sản phẩm vào giỏ hàng</h4>
                                                <button type="button" class="close"
                                                        data-dismiss="modal">&times;
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="col-md-5">
                                                    <img src="../../../uploads/${item.hinhAnhs.tenanh}"
                                                         width="150px"
                                                         height="150px">
                                                </div>
                                                <div class="col-md-7">
                                                    <div class="sizeAddCart">
                                                        <p>Chọn Size</p>
                                                        <select id="kichCoSelect_${item.sanPham.id}_${item.mauSac.id}"
                                                                style="width: 100px;font-size: 15px;height: 27px;border: 1px solid #b1b1b8"
                                                                name="kichCo"
                                                                onchange="selectSize('${item.sanPham.id}','${item.mauSac.id}')">
                                                            <option id=""></option>
                                                        </select>
                                                    </div>
                                                    <div class="soLuongAddCart">
                                                        <p>Chọn số lượng</p>
                                                        <input type="number"
                                                               style="width: 100px;font-size: 15px;padding-left: 10px;height: 27px;border: 1px solid #b1b1b8"
                                                               name="soLuong" value="1"
                                                               id="slchon_${item.sanPham.id}_${item.mauSac.id}"
                                                               onchange="thayDoiSoLuong('${item.sanPham.id}','${item.mauSac.id}')"
                                                               oninput="chonSoLuong('${item.sanPham.id}','${item.mauSac.id}',event)">
                                                    </div>
                                                    <p style="margin-top: 10px;"><span
                                                            id="slsp_${item.sanPham.id}_${item.mauSac.id}">${item.soLuong}</span>
                                                        sản phẩm có sẵn</p>
                                                    <input type="hidden"
                                                           id="spcosan_${item.sanPham.id}_${item.mauSac.id}">
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button style="font-size: 15px;background-color: black;color: white;border: none"
                                                        type="button" class="btn btn-danger"
                                                        data-dismiss="modal">Đóng
                                                </button>
                                                <button class="btn"
                                                        id="btn-themgh_${item.sanPham.id}_${item.mauSac.id}"
                                                        style="font-size:15px;background-color: white; color: black;border: 1px solid black"
                                                        type="button"
                                                        onclick="return themVaoGioHang('${item.sanPham.id}','${item.mauSac.id}')">
                                                    Thêm vào giỏ hàng
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="banner">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="banner_item align-items-center" style="background-image: url(../../../img/banner2.jpg)">
                        <div class="banner_category">
                            <a href="#">women's</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="banner_item align-items-center" style="background-image: url(../../../img/banner3.jpg)">
                        <div class="banner_category">
                            <a href="#">accessories's</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="banner_item align-items-center" style="background-image: url(../../../img/banner4.png)">
                        <div class="banner_category">
                            <a href="#">men's</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="best_sellers">
        <div class="container">
            <div class="row">
                <div class="col text-center">
                    <div class="section_title new_arrivals_title">
                        <h2>Best Sellers</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="product_slider_container">
                        <div class="owl-carousel owl-theme product_slider owl-loaded owl-drag">
                            <div class="owl-stage-outer">
                                <div class="owl-stage"
                                     style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2220px;">
                                    <c:forEach var="item" items="${pageSP.content}">
                                        <div class="owl-item active" style="width: 222px;">
                                            <div class="owl-item product_slider_item">
                                                <div class="product-item">
                                                    <div class="product discount product_filter"
                                                         style="border-right: 1px solid rgb(233, 233, 233);">
                                                        <div class="product_image">
                                                            <a
                                                                    class="ps-shoe__overlay"
                                                                    href="/bumblebee/detail?idSP=${item.sanPham.id}&idCTSP=${item.id}&idMS=${item.mauSac.id}"><img
                                                                    src="../../../uploads/${item.hinhAnhs.tenanh}" alt="" width="221px"
                                                                    height="221px"></a>
                                                        </div>
                                                        <div class="favorite favorite_left"></div>
                                                        <c:forEach var="km" items="${item.ctkm}">
                                                            <c:if test="${km.trangThai == 0}">
                                                                <c:if test="${km.khuyenMai.donVi == '%'}">
                                                                    <div class="product_bubble product_bubble_right product_bubble_red d-flex ">
                                                                        <span>- ${km.khuyenMai.giaTri}${km.khuyenMai.donVi}</span>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${km.khuyenMai.donVi == 'VNĐ'}">
                                                                    <div class="product_bubble product_bubble_left product_bubble_green ">
                                                <span>- <fmt:formatNumber value="${km.khuyenMai.giaTri}"
                                                                          type="number"/>đ</span>
                                                                    </div>
                                                                </c:if>
                                                            </c:if>
                                                        </c:forEach>
                                                        <div class="product_info">
                                                            <h6 class="product_name"><a
                                                                    href="/bumblebee/detail?idSP=${item.sanPham.id}&idCTSP=${item.id}&idMS=${item.mauSac.id}">${item.sanPham.tenSanPham}
                                                                - ${item.mauSac.ten}</a>
                                                            </h6>
                                                            <div class="product_price">
                                                                <c:if test="${not empty item.ctkm}">
                                                                    <c:set var="allTrangThai1" value="false"/>
                                                                    <c:forEach var="km" items="${item.ctkm}">
                                                                        <c:if test="${km.trangThai == 0}">
                                                                            <c:set var="allTrangThai1" value="true"/>
                                                                            <label style="color: crimson;font-size: 15px"><fmt:formatNumber
                                                                                    value="${km.giaKhuyenMai}"
                                                                                    type="number"/> đ</label>
                                                                            <span><fmt:formatNumber value="${item.giaBan}"
                                                                                                    type="number"/> đ</span>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    <c:if test="${allTrangThai1 eq false}">
                                                                        <label>
                                                                            <fmt:formatNumber value="${item.giaBan}" type="number"/> đ
                                                                        </label>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${empty item.ctkm}">
                                                                    <label>
                                                                        <fmt:formatNumber value="${item.giaBan}" type="number"/> đ
                                                                    </label>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="owl-nav disabled">
                                <div class="owl-prev">prev</div>
                                <div class="owl-next">next</div>
                            </div>
                            <div class="owl-dots disabled"></div>
                        </div>

                        <!-- Slider Navigation -->

                        <div class="product_slider_nav_left product_slider_nav d-flex align-items-center justify-content-center flex-column">
                            <i class="fa fa-chevron-left" aria-hidden="true"></i>
                        </div>
                        <div class="product_slider_nav_right product_slider_nav d-flex align-items-center justify-content-center flex-column">
                            <i class="fa fa-chevron-right" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="errorModal" class="modal" style="width: 400px;margin: 0 auto;top:40%;padding: 1.25rem">
        <div class="modal-content">
            <div class="bumblebee-alert-popup__message" bis_skin_checked="1" style="font-size: 16px">
                <p id="errolSL"></p>
            </div>
            <div class="bumblebee-alert-popup__button-horizontal-layout" bis_skin_checked="1">
                <button style="width: 80px;font-size: 16px" type="button" onclick="closeErrorModal()"
                        class="btn btn-solid-primary btn--m btn--inline bumblebee-alert-popup__btn">OK
                </button>
            </div>
        </div>
    </div>
    <div id="toast" style="display:none;">
        <div class="toast toast__succes">
            <div class="toast__icon">
                <i class="fa-sharp fa-solid fa-circle-check" style="color: #47d864;"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Success</h3>
                <p class="toast__msg">Thêm vào giỏ hàng thành công</p>
            </div>
            <div class="toast__close">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg"
                     viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                </svg>
            </div>
        </div>
    </div>
    <div id="toast_warring_so_luong" style="display:none;">
        <div class="toast toast__warring">
            <div class="toast__icon">
                <i class="fa-solid fa-triangle-exclamation" style="color: #ffc021;"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Thất bại</h3>
                <p class="toast__msg">Sản phẩm đã hết hàng</p>
            </div>
            <div class="toast__close">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg"
                     viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                </svg>
            </div>
        </div>
    </div>
    <div id="toast_warring_login" style="display:none;">
        <div class="toast toast__warring">
            <div class="toast__icon">
                <i class="fa-solid fa-triangle-exclamation" style="color: #ffc021;"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Thất bại</h3>
                <p class="toast__msg">Bạn cần đăng nhập để tiếp tục</p>
            </div>
            <div class="toast__close">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg"
                     viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                </svg>
            </div>
        </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        $(document).on('hidden.bs.modal', '[id^="kichCoModal_"]', function () {
            // Xử lý khi modal được đóng
            console.log('Modal đóng');
            $('body').removeClass('modal-open');
            $('body').css('padding-right', '0');
        });
    });

    function closeErrorModal(){
        var modal = document.getElementById("errorModal");
        modal.style.display = "none";
    }

    function themVaoGioHang(idsp, idms) {
        <c:if test="${userLogged.username == null}">
        var toastElement = document.getElementById("toast_warring_login");
        toastElement.style.display = "block";
        setTimeout(function () {
            toastElement.style.display = "none";
        }, 1500);
        return false;
        </c:if>
        var kichCo = document.getElementById("kichCoSelect_" + idsp + "_" + idms).value;
        var soLuong = document.getElementById("slchon_" + idsp + "_" + idms).value;
        var slsp = document.getElementById("slsp_" + idsp + "_" + idms).innerText;
        if (Number(slsp) === 0){
            var toastElement = document.getElementById("toast_warring_so_luong");
            toastElement.style.display = "block";
            setTimeout(function () {
                toastElement.style.display = "none";
            }, 1500);
            return false;
        }
        var formData = new FormData();
        formData.append("kichCo", kichCo);
        formData.append("soLuong", soLuong);
        $.ajax({
            type: "POST",
            url: "/bumblebee/add-to-cart?idMS=" + idms + "&idSP=" + idsp,
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                console.log("Success: " + response);
                if(response !== ""){
                    var error = response;
                    if (error) {
                        var main = document.getElementById("main");
                        var modal = document.getElementById("errorModal");
                        document.getElementById("errolSL").innerText = error;
                        modal.style.display = "block";
                        main.style.opacity = "0,5";
                    }
                    return;
                }
                var toastElement = document.getElementById("toast");
                toastElement.style.display = "block";
                setTimeout(function () {
                    toastElement.style.display = "none";
                }, 1500);
            },
            error: function (error) {
                console.log("Error1: " + JSON.stringify(error));
            }
        });
        return false;
    }

    $(document).ready(function () {
        $(".addToCartBtn").click(function () {
            var itemId = $(this).data("item-id");
            var idMS = $(this).data("item-mausac");
            $.ajax({
                url: "/bumblebee/select-size?idSP=" + itemId + "&idMS=" + idMS,
                type: "GET",
                success: function (data) {
                    var selectElement = $("#kichCoSelect_" + itemId + "_" + idMS);
                    selectElement.empty();
                    data.forEach(function (kichCo) {
                        var option = $("<option id='op-size'></option>")
                            .attr("value", kichCo)
                            .text(kichCo);
                        selectElement.append(option);
                    });
                },
                error: function () {
                    console.log("Error fetching kich co data.");
                }
            });
        });
    });
    var response = null;

    function selectSize(idsp, idms) {
        var kichCo = document.getElementById("kichCoSelect_" + idsp + "_" + idms).value;
        var idMS = idms;
        var idSP = idsp;
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/bumblebee/select-slsp?idMS=" + idMS + "&idSP=" + idSP + "&size=" + kichCo, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                response = xhr.responseText;
                console.log(response);
                document.getElementById("slsp_" + idSP + "_" + idMS).innerHTML = response;
                if (Number(response) === 0) {
                    $("#btn-themgh").prop("disabled", true);
                }
            }
        };
        xhr.send();
    }

    function chonSoLuong(idSP, idMS, event) {
        var slcosan = document.getElementById("slsp_" + idSP + "_" + idMS).innerHTML;
        console.log(slcosan);
        const newValue = event.target.value;
        console.log(newValue)
        if (Number(newValue) > Number(slcosan) || Number(newValue) <= 0) {
            $("#btn-themgh_" + idSP + "_" + idMS).prop("disabled", true);
        } else {
            $("#btn-themgh_" + idSP + "_" + idMS).prop("disabled", false);
        }
    }


    //


</script>








