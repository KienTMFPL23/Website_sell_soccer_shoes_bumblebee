<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../../../js/trang_chu/detail/product_detail.js"></script>
<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../../../css_update_template/single_styles.css">
<link rel="stylesheet" type="text/css" href="../../../css_update_template/single_responsive.css">

<style>
    .bumblebee-alert-popup__message {
        margin-top: 2.5rem;
        font-size: 1rem;
    }

    .bumblebee-alert-popup__button-horizontal-layout {
        display: flex;
        margin-top: 6.25rem;
    }

    .btn-solid-primary {
        color: #fff;
        position: relative;
        overflow: visible;
        outline: 0;
        background: #ee4d2d;
    }

    .modal-content {
        padding: 1.25rem;
        overflow: visible;
    }
</style>
<main class="ps-main" id="main">
    <div class="container single_product_container">
        <div class="row">
            <div class="col">
                <div class="breadcrumbs d-flex flex-row align-items-center">
                    <ul>
                        <li><a href="/bumblebee/home">Home</a></li>
                        <li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>Single
                            Product</a></li>
                    </ul>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col-lg-7">
                <div class="single_product_pics">
                    <div class="row">
                        <div class="col-lg-3 thumbnails_col order-lg-1 order-2">
                            <div class="single_product_thumbnails">
                                <ul>
                                    <li><img src="../../../uploads/${hinhAnh.duongdan1}"
                                             data-image="../../../uploads/${hinhAnh.duongdan1}"></li>
                                    <li class="active"><img src="../../../uploads/${hinhAnh.tenanh}" alt=""
                                                            data-image="../../../uploads/${hinhAnh.tenanh}"></li>
                                    <li><img src="../../../uploads/${hinhAnh.duongdan3}" alt=""
                                             data-image="../../../uploads/${hinhAnh.duongdan3}"></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-9 image_col order-lg-2 order-1">
                            <div class="single_product_image">
                                <div class="single_product_image_background"
                                     style="background-image:url(../../../uploads/${hinhAnh.tenanh})"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-5">
                <form method="post" id="formDetail">
                    <div class="product_details">
                        <div class="product_details_title">
                            <h2>${ctsp.sanPham.tenSanPham}</h2>
                            <p>${ctsp.moTaCT}</p>
                        </div>
                        <div style="display: flex;justify-content: space-between;align-items: center;margin-top: 20px">
                            <div class="product_price">
                                <c:if test="${not empty ctsp.ctkm}">
                                    <c:set var="allTrangThai1" value="false"/>
                                    <c:forEach var="km" items="${ctsp.ctkm}">
                                        <c:if test="${km.trangThai == 0}">
                                            <label style="font-weight: 500" id="donGiaKhuyenMai">
                                                <fmt:formatNumber value="${km.giaKhuyenMai}"
                                                                  type="number"/> đ
                                            </label>
                                            <c:set var="allTrangThai1" value="true"/>
                                            <label style="font-weight: 500" id="giaSP"></label>
                                            <span id="donGiaChuaGiam"><fmt:formatNumber value="${ctsp.giaBan}"
                                                                                        type="number"/> đ</span>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${allTrangThai1 eq false}">
                                        <label style="font-weight: 500" id="giaSP"><fmt:formatNumber
                                                value="${ctsp.giaBan}"
                                                type="number"/>
                                            đ</label>
                                        <label style="font-weight: 500" id="donGiaKhuyenMai"></label>
                                        <span id="donGiaChuaGiam"></span>
                                        <c:set var="allTrangThai1" value="true"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${empty ctsp.ctkm}">
                                    <label style="font-weight: 500" id="giaSP"><fmt:formatNumber value="${ctsp.giaBan}"
                                                                                                 type="number"/>
                                        đ</label>
                                    <label style="font-weight: 500" id="donGiaKhuyenMai"></label>
                                    <span id="donGiaChuaGiam"></span>
                                </c:if>
                            </div>

                            <div id="spcosan" style="color: #fe4c50;font-weight: bold"></div>
                        </div>
                        <div class="product_size">
                            <span class="">Chọn kích cỡ:</span>
                            <div class="product_size">
                                <ul id="kichCoList">
                                    <c:forEach var="kc" items="${listKC}">
                                        <li onclick="selectSize('${kc}')" class="site-option">${kc}</li>
                                    </c:forEach>
                                </ul>
                                <input type="hidden" name="kichCo" id="kichCoInput" value=""/>
                            </div>
                        </div>
                        <div class="soluong">
                            <span class="">Số lượng:</span>
                            <div class="quantity_selector">
                                <span id="quantity_value">
                                   <input
                                           class="form-control"
                                           id="sl"
                                           style="font-size: 15px; border: none"
                                           value="1"
                                           type="number"
                                           name="soLuong"
                                           onchange="thayDoiSoLuong();"
                                   /></span>
                            </div>
                        </div>
                        <div class="quantity d-flex flex-column flex-sm-row align-items-sm-center">
                            <button
                                    formaction="/bumblebee/mua-ngay?idMS=${ctsp.mauSac.id}&idSP=${ctsp.sanPham.id}&idCTSP=${idCTSP}"
                                    class="btn-mua"
                                    type="submit" onclick="return muaNgay()" id="muaNgayButton">Mua ngay
                            </button>
                            <button class="btn-themgh"
                                    id="addToCartButton"
                                    type="button"
                                    onclick="return themVaoGioHang()"
                                    formaction="/bumblebee/add-to-cart?idMS=${ctsp.mauSac.id}&idSP=${ctsp.sanPham.id}&idCTSP=${ctsp.id}">
                                add to cart
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="benefit">
        <div class="container">
            <div class="row benefit_row">
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center">
                        <div class="benefit_icon"><i class="fa fa-truck" aria-hidden="true"></i></div>
                        <div class="benefit_content">
                            <h6>free shipping</h6>
                            <p>Suffered Alteration in Some Form</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center">
                        <div class="benefit_icon"><i class="fa fa-money" aria-hidden="true"></i></div>
                        <div class="benefit_content">
                            <h6>cach on delivery</h6>
                            <p>The Internet Tend To Repeat</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center">
                        <div class="benefit_icon"><i class="fa fa-undo" aria-hidden="true"></i></div>
                        <div class="benefit_content">
                            <h6>45 days return</h6>
                            <p>Making it Look Like Readable</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 benefit_col">
                    <div class="benefit_item d-flex flex-row align-items-center">
                        <div class="benefit_icon"><i class="fa fa-clock-o" aria-hidden="true"></i></div>
                        <div class="benefit_content">
                            <h6>opening all week</h6>
                            <p>8AM - 09PM</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col text-center">
            <div class="section_title new_arrivals_title">
                <h4>Sản Phẩm Liên Quan</h4>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="container">
            <div class="col">
                <div class="product_slider_container">
                    <div class="owl-carousel owl-theme product_slider owl-loaded owl-drag">
                        <div class="owl-stage-outer">
                            <div class="owl-stage"
                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2220px;">
                                <c:forEach var="item" items="${listCTSPBySP}">
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
                                                        <c:if test="${km.khuyenMai.trangThai == 0}">
                                                            <c:if test="${km.khuyenMai.donVi == '%'}">
                                                                <div class="product_bubble product_bubble_right product_bubble_red d-flex ">
                                                                    <span>- ${km.khuyenMai.giaTri}${km.khuyenMai.donVi}</span></div>
                                                            </c:if>
                                                            <c:if test="${km.khuyenMai.donVi == 'VNÐ'}">
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
    <div id="toast_warring_kich_co" style="display:none;">
        <div class="toast toast__warring">
            <div class="toast__icon">
                <i class="fa-solid fa-triangle-exclamation" style="color: #ffc021;"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Thất bại</h3>
                <p class="toast__msg">Bạn cần chọn kích cỡ để tiếp tục</p>
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
</main>
<script>
    function closeErrorModal(){
        var modal = document.getElementById("errorModal");
        modal.style.display = "none";
    }
    function toast() {
        var toastElement = document.getElementById("toast");
        toastElement.style.display = "block";
        setTimeout(function () {
            toastElement.style.display = "none";
        }, 1500);
    }
</script>

<script src="../../../js_template/single_custom.js"></script>
<script>


    var selectedOption = null;
    document.addEventListener("DOMContentLoaded", function () {
        var siteOptions = document.querySelectorAll(".site-option");
        siteOptions.forEach(function (option) {
            option.addEventListener("click", function () {
                if (selectedOption) {
                    // Nếu đã có phần tử được chọn trước đó, hủy bỏ lựa chọn
                    selectedOption.classList.remove("selected");
                }
                if (selectedOption !== option) {
                    // Nếu phần tử được click khác với phần tử trước đó, thì chọn phần tử mới
                    option.classList.add("selected");
                    selectedOption = option;
                } else {
                    selectedOption = null;
                }
            });
        });
    });
    function formatGiaSP(number) {
        var formattedString = String(number).split('.')[0];
        formattedString = formattedString.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
        return formattedString;
    }
    var response = null;
    var response2 = null;
    var slctsp = 0;

    function selectSize(kichCo) {
        document.getElementById("kichCoInput").value = kichCo;
        console.log(kichCo);
        var idMS = "${ctsp.mauSac.id}";
        var idSP = "${ctsp.sanPham.id}";
        var idCTSP = "${ctsp.id}"
        var giaSP = "${ctsp.giaBan}"
        var xhr = new XMLHttpRequest();
        var xhr2 = new XMLHttpRequest();
        xhr.open("GET", "/bumblebee/select-slsp?idMS=" + idMS + "&idSP=" + idSP + "&size=" + kichCo, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                response = xhr.responseText;
                if (selectedOption !== null) {
                    document.getElementById("spcosan").innerHTML = "Sản phẩm có sẵn: " + response;
                    slctsp = response;
                } else {
                    document.getElementById("spcosan").innerHTML = "";
                }
            }
        };
        xhr.send();
        xhr2.open("GET", "/bumblebee/select-giaban?idSP=" + idSP + "&idMS=" + idMS + "&size=" + kichCo, true);
        xhr2.onreadystatechange = function () {
            if (xhr2.readyState === 4 && xhr2.status === 200) {
                response2 = xhr2.response;
                console.log(response2);
                console.log(giaSP);
                if (Number(response2) !== Number(giaSP)) {
                    document.getElementById("donGiaKhuyenMai").innerHTML = formatGiaSP(response2) + " đ";
                    document.getElementById("donGiaChuaGiam").innerHTML = formatGiaSP(giaSP) + " đ";
                    document.getElementById("giaSP").innerHTML = "";
                } else {
                    document.getElementById("giaSP").innerText = formatGiaSP(giaSP) + " đ";
                    document.getElementById("donGiaKhuyenMai").innerHTML = "";
                    document.getElementById("donGiaChuaGiam").innerHTML = "";
                }
            } else {
                console.error("Request failed with status: " + xhr2.status);
            }
        };
        xhr2.send();

    }

    function muaNgay() {
        <c:if test="${userLogged.username == null}">
        var toastElement = document.getElementById("toast_warring_login");
        toastElement.style.display = "block";
        setTimeout(function () {
            toastElement.style.display = "none";
        }, 1500);
        return false;
        </c:if>
        if (selectedOption === null) {
            var toastElement1 = document.getElementById("toast_warring_kich_co");
            toastElement1.style.display = "block";
            setTimeout(function () {
                toastElement1.style.display = "none";
            }, 1500);
            return false;
        }
        if (Number(slctsp) === 0){
            var toastElement = document.getElementById("toast_warring_so_luong");
            toastElement.style.display = "block";
            setTimeout(function () {
                toastElement.style.display = "none";
            }, 1500);
            return false;
        }else {
            return true;
        }

    }


    function themVaoGioHang() {
        <c:if test="${userLogged.username == null}">
        var toastElement = document.getElementById("toast_warring_login");
        toastElement.style.display = "block";
        setTimeout(function () {
            toastElement.style.display = "none";
        }, 1500);
        return false;
        </c:if>
        if (selectedOption === null) {
            var toastElement1 = document.getElementById("toast_warring_kich_co");
            toastElement1.style.display = "block";
            setTimeout(function () {
                toastElement1.style.display = "none";
            }, 1500);
            return false;
        }
        if (Number(slctsp) === 0){
            var toastElement = document.getElementById("toast_warring_so_luong");
            toastElement.style.display = "block";
            setTimeout(function () {
                toastElement.style.display = "none";
            }, 1500);
            return false;
        } else {
            var idMS = "${ctsp.mauSac.id}";
            var idSP = "${ctsp.sanPham.id}";
            var kichCo = document.getElementById("kichCoInput").value;
            var soLuong = document.getElementById("sl").value;
            var formData = new FormData();
            formData.append("kichCo", kichCo);
            formData.append("soLuong", soLuong);
            $.ajax({
                type: "POST",
                url: "/bumblebee/add-to-cart?idMS=" + idMS + "&idSP=" + idSP,
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
    }

</script>



