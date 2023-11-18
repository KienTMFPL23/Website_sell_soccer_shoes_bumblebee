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
                <form method="post" onsubmit="return submitForm()">
                    <div class="product_details">
                        <div class="product_details_title">
                            <h2>${ctsp.sanPham.tenSanPham}</h2>
                            <p>${ctsp.moTaCT}</p>
                        </div>
                        <div style="display: flex;justify-content: space-between;align-items: center;margin-top: 20px">
                            <div class="product_price">
                                <c:if test="${ctsp.ctkm != null}">
                                    <c:forEach var="km" items="${ctsp.ctkm}">
                                        <c:if test="${km.khuyenMai.donVi == '%'}">
                                            <label>
                                                <fmt:formatNumber  value="${ctsp.giaBan - (ctsp.giaBan * km.khuyenMai.giaTri/100)}" type="number"/>
                                            </label>
                                        </c:if>
                                        <c:if test="${km.khuyenMai.donVi == 'VNÐ'}">
                                            <label>
                                                <fmt:formatNumber  value="${ctsp.giaBan - km.khuyenMai.giaTri}" type="number"/>
                                            </label>
                                        </c:if>
                                        <span><fmt:formatNumber  value="${ctsp.giaBan}" type="number"/></span>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty ctsp.ctkm}">
                                    <fmt:formatNumber  value="${ctsp.giaBan}" type="number"/>
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
                                    type="submit" onclick="return themVaoGioHang()" id="muaNgayButton">Mua ngay
                            </button>
                            <button class="btn-themgh"
                                    formaction="/bumblebee/add-to-cart?idMS=${ctsp.mauSac.id}&idSP=${ctsp.sanPham.id}&idCTSP=${idCTSP}"
                                    id="addToCartButton"
                                    type="submit" onclick="return themVaoGioHang()">
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
</main>
<div id="errorModal" class="modal" style="width: 400px;margin: 0 auto;top:50%;padding: 1.25rem">
    <div class="modal-content">
        <div class="bumblebee-alert-popup__message" bis_skin_checked="1" style="font-size: 16px">
            ${errorSL}
        </div>
        <div class="bumblebee-alert-popup__button-horizontal-layout" bis_skin_checked="1">
            <button style="width: 80px;font-size: 16px" type="button" onclick="closeErrorModal()"
                    class="btn btn-solid-primary btn--m btn--inline bumblebee-alert-popup__btn">OK
            </button>
        </div>
    </div>
</div>
<div id="errorKCModal" class="modal" style="width: 400px;margin: 0 auto;top:50%;padding: 1.25rem">
    <div class="modal-content">
        <div bis_skin_checked="1" style="font-size: 16px;text-align: center">
            Vui lòng chọn kích cỡ!
        </div>
        <div bis_skin_checked="1" style="display: flex;justify-content: center">
            <button style="background: black;
    margin-top: 20px;
    font-size: 16px;
    color: white;
    border: none;
    width: 120px;
    height: 30px;;font-size: 16px" type="button" onclick="closeErrorModal()">Đóng
            </button>
        </div>
    </div>
</div>
<script src="../../../js_template/single_custom.js"></script>
<script>
    var error = "${errorSL}";
    if (error) {
        var main = document.getElementById("main");
        var modal = document.getElementById("errorModal");
        modal.style.display = "block";
        main.style.opacity = "0,5";
    }
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

    var response = null;
    var response2 = null;

    function selectSize(kichCo) {
        document.getElementById("kichCoInput").value = kichCo;
        console.log(kichCo)
        document.getElementById("addToCartButton").disabled = false;
        document.getElementById("muaNgayButton").disabled = false;
        var idMS = "${ctsp.mauSac.id}";
        var idSP = "${ctsp.sanPham.id}";
        var idCTSP = "${ctsp.id}"
        var xhr = new XMLHttpRequest();
        var xhr2 = new XMLHttpRequest();
        xhr.open("GET", "/bumblebee/select-slsp?idMS=" + idMS + "&idSP=" + idSP + "&size=" + kichCo, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                response = xhr.responseText;
                if (selectedOption !== null) {
                    document.getElementById("spcosan").innerHTML = "Sản phẩm có sẵn: " + response;
                } else {
                    document.getElementById("spcosan").innerHTML = "";
                }
            }
        };
        xhr.send();
        //
        // xhr2.open("GET", "/bumblebee/detail?idSP=" + idSP + "&idCTSP=" + idCTSP + "&idMS=" + idMS + "&kichCo=" + kichCo, true);
        // xhr2.onreadystatechange = function () {
        //     if (xhr2.readyState === 4 && xhr2.status === 200){
        //         response2 = xhr2.response;
        //     }
        // };
        // xhr2.send();
        // xhr2.onerror = function() {
        //     console.error("Request failed");
        // };

    }

    function submitForm() {
        return true;
    }

    function themVaoGioHang() {
        if (selectedOption === null) {
            var main = document.getElementById("main");
            var modal = document.getElementById("errorKCModal");
            modal.style.display = "block";
            modal.classList.add('show');
            main.style.opacity = "0,5";
            return false;
        } else {
            return true;
        }

    }


</script>



