<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="utf-8" %>
<main class="ps-main">
    <div class="container-fluid">
        <div class="ps-products-wrap pt-80 pb-80">
            <div class="ps-products" data-mh="product-listing">
                <div class="ps-product-action">
                    <form method="GET" action="/bumblebee/product_list/sort">
                        <form:form modelAttribute="sortForm">
                            <form:select id="mySelect" cssClass="selectSort" path="key"
                                         onchange="this.form.submit()">
                                <form:option value="no" label="Sắp xếp theo"/>
                                <form:option value="moiNhat" label="Mới nhất"/>
                                <form:option value="giaBanTangDan" label="Giá từ thấp đến cao"/>
                                <form:option value="giaBanGiamDan" label="Giá từ cao đến thấp"/>
                            </form:select>
                        </form:form>
                    </form>
                    <div class="pages d-flex flex-row align-items-center">
                        <div class="page_current">
                            <span>1</span>
                            <ul class="page_selection">
                                <li><a href="/bumblebee/product_list?p=0&keyword=${param.keyword}">1</a></li>
                                <li><a href="/bumblebee/product_list?p=1&keyword=${param.keyword}">2</a></li>
                                <li><a href="/bumblebee/product_list?p=2&keyword=${param.keyword}">3</a></li>
                            </ul>
                        </div>
                        <div class="page_total"><span>of</span>${pageSP.totalPages}</div>
                        <div id="next_page" class="page_next"><a href="/bumblebee/product_list?p=${pageSP.number+1}&keyword=${param.keyword}"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a></div>
                    </div>
                </div>
                <div class="ps-product__column row">
                    <c:forEach var="item" items="${pageSP.content}" varStatus="loop">
                        <div class="product-item">
                            <div class="product discount product_filter"
                                 style="border-right: 1px solid rgb(233, 233, 233);">
                                <div class="product_image">
                                    <a
                                            class="ps-shoe__overlay"
                                            href="/bumblebee/detail?idSP=${item.sanPham.id}&idCTSP=${item.id}&idMS=${item.mauSac.id}"><img
                                            src="../../../uploads/${item.hinhAnhs.tenanh}" alt=""></a>
                                </div>
                                <div class="favorite favorite_left"></div>
                                <div class="product_info">
                                    <h6 class="product_name"><a
                                            href="/bumblebee/detail?idSP=${item.sanPham.id}&idCTSP=${item.id}&idMS=${item.mauSac.id}">${item.sanPham.tenSanPham}</a>
                                    </h6>
                                    <div class="product_price"><fmt:formatNumber value="${item.giaBan}"
                                                                                 type="currency"/><span>$590.00</span>
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
                        <!-- Modaclass="soLuongAddCart"l -->
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
                                                            class="form-control"
                                                            style="width: 100px;font-size: 15px" name="kichCo"
                                                            onchange="selectSize('${item.sanPham.id}','${item.mauSac.id}')">
                                                        <option id=""></option>
                                                    </select>
                                                </div>
                                                <div class="soLuongAddCart">
                                                    <p>Chọn số lượng</p>
                                                    <input type="number"
                                                           style="width: 100px;font-size: 15px;padding-left: 10px"
                                                           name="soLuong" value="1" id="slchon"
                                                           onchange="thayDoiSoLuong('${item.sanPham.id}','${item.mauSac.id}')"
                                                           oninput="chonSoLuong('${item.sanPham.id}','${item.mauSac.id}',event)">
                                                </div>
                                                <p style="margin-top: 10px"><span
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
                                                    href="/bumblebee/add-to-cart">Thêm vào giỏ hàng
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
            <div class="ps-sidebar" data-mh="product-listing">
                <aside class="ps-widget--sidebar ps-widget--category">
                    <div class="ps-widget__header">
                        <h3>Loại Giày</h3>
                    </div>
                    <div class="ps-widget__content">
                        <form action="/bumblebee/product_list/searchbyloaigiay">
                            <ul>
                                <li class="current">
                                    <input class="check-theloai check-all" type="checkbox" name="idLoaiGiayList" value="all"
                                           checked>
                                    <a href="/bumblebee/product_list">Tất cả</a>
                                </li>
                                <c:forEach var="lg" items="${listLG}">
                                    <li>
                                        <input class="check-theloai" type="checkbox" name="idLoaiGiayList" value="${lg.id}">
                                        <a href="#">${lg.tentheloai}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <input class="ac-slider__filter ps-btn"
                                   type="submit"
                                   value="Tìm kiếm">
                        </form>
                    </div>
                </aside>
                <aside class="ps-widget--sidebar ps-widget--filter">
                    <div class="ps-widget__header">
                        <h3>Khoảng Giá</h3>
                    </div>
                    <div class="ps-widget__content">
                        <form method="GET" action="/bumblebee/product_list/searchbygiaban">
                            <form:form modelAttribute="searchFormByGiaban">
                                <form:input path="minPrice" placeholder="Từ"></form:input>
                                <form:input path="maxPrice" placeholder="Đến"></form:input>
                                <input class="ac-slider__filter ps-btn"
                                       type="submit"
                                       value="Tìm kiếm">
                            </form:form>
                        </form>
                    </div>
                </aside>
                <div class="ps-sticky desktop">
                    <aside class="ps-widget--sidebar">
                        <div class="ps-widget__header">
                            <h3>Kích Cỡ</h3>
                        </div>
                        <div class="ps-widget__content">
                            <table class="table ps-table--size">
                                <tbody>
                                <c:forEach var="kc" items="${listKC}" varStatus="loop">
                                    <c:if test="${loop.index % 5 == 0}">
                                        <tr>
                                    </c:if>
                                    <td><a href="/bumblebee/product_list/searchbykichco/${kc.id}">${kc.size}</a></td>
                                    <c:if test="${loop.index % 5 == 4 or loop.last}">
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </aside>
                    <aside class="ps-widget--sidebar">
                        <div class="ps-widget__header">
                            <h3>Màu Sắc</h3>
                        </div>
                        <div class="ps-widget__content">
                            <ul class="ps-list--color">
                                <c:forEach var="ms" items="${listMS}">
                                    <li style="border: 1px solid #ddd;border-radius: 100%"><a
                                            href="/bumblebee/product_list/searchbymausac?idMS=${ms.id}"
                                            style="background-color: ${ms.ten};"></a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </aside>
                </div>
            </div>
        </div>
    </div>

</main>
<script>
    var select = document.getElementById("mySelect");
    select.onchange = function () {
        var selectedValue = select.value;
        select.form.submit();
    };
</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var checkboxes = document.querySelectorAll(".check-theloai");
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener("change", function () {
                if (this !== document.querySelector(".check-all") && this.checked) {
                    document.querySelector(".check-all").checked = false;
                }

            });
        });
    });
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
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

    $(document).ready(function () {
        $(".addToCartBtn").click(function () {
            var itemId = $(this).data("item-id");
            var idMS = $(this).data("item-mausac");
            console.log(itemId);
            console.log(idMS)
            $.ajax({
                url: "/bumblebee/select-size?idSP=" + itemId + "&idMS=" + idMS,
                type: "GET",
                success: function (data) {
                    var selectElement = $("#kichCoSelect_"+itemId+"_"+idMS);
                    selectElement.empty();
                    data.forEach(function (kichCo) {
                        var option = $("<option></option>")
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
</script>
