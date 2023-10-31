<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="utf-8" %>
<main class="ps-main">
    <div class="ps-products-wrap pt-80 pb-80">
        <div class="ps-products" data-mh="product-listing">
            <div class="ps-product-action">
                <form method="GET" action="/bumblebee/product_list/sort">
                    <form:form modelAttribute="sortForm">
                        <form:select id="mySelect" path="key"
                                     cssStyle="height: 50px;background-color: #ddd; border: none; font-family: Montserrat; font-size: 17px"
                                     onchange="this.form.submit()">
                            <form:option value="" label="Sắp xếp theo"/>
                            <form:option value="sanPham.tenSanPham" label="Tên sản phẩm"/>
                            <form:option value="giaBan" label="Tăng dần theo giá"/>
                        </form:select>
                    </form:form>
                </form>
            </div>
            <div class="ps-product__column row">
                <c:forEach var="item" items="${pageSP.content}" varStatus="loop">
                    <div class="ps-product__column col-md-3">
                        <div class="ps-shoe mb-30">
                            <div class="ps-shoe__thumbnail">
                                <a class="ps-shoe__favorite" href="#"><i class="ps-icon-heart"></i></a>
                                <img
                                        src="../../../uploads/${item.hinhAnhs.tenanh}" height="250px" width="250px"
                                        alt=""><a
                                    class="ps-shoe__overlay" href="/bumblebee/detail?idSP=${item.sanPham.id}&idCTSP=${item.id}&idMS=${item.mauSac.id}"></a>
                            </div>
                            <div class="ps-shoe__content">
                                <div class="ps-shoe__variants" style="margin-top: 10px">
                                    <div class="ps-shoe__variant normal">
                                        <img src="../../../uploads/${item.hinhAnhs.duongdan1}">
                                        <img src="../../../uploads/${item.hinhAnhs.duongdan2}">
                                        <img src="../../../uploads/${item.hinhAnhs.duongdan3}">
                                        <img src="../../../uploads/${item.hinhAnhs.duongdan4}">
                                    </div>
                                    <div class=" butAddCart">
                                        <button class="addToCartBtn"
                                                data-toggle="modal"
                                                data-target="#kichCoModal_${loop.index}"
                                                style="color: white; width: 240px"
                                                data-item-id="${item.sanPham.id}"
                                                data-item-mausac="${item.mauSac.id}"
                                        >Thêm giỏ hàng
                                        </button>
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
                    <!-- Modal -->
                    <form method="post" action="/bumblebee/add-to-cart?idMS=${item.mauSac.id}&idSP=${item.sanPham.id}&idCTSP=${item.id}">
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
                                                 width="80px"
                                                 height="80px">
                                        </div>
                                        <div class="col-md-7">
                                            <div class="sizeAddCart">
                                                <p>Chọn Size</p>
                                                <select id="kichCoSelect_${item.sanPham.id}_${item.mauSac.id}" class="form-control"
                                                        style="width: 100px;font-size: 15px" name="kichCo">
                                                </select>
                                            </div>
                                            <div class="soLuongAddCart">
                                                <p>Chọn số lượng</p>
                                                <input type="number" style="width: 100px;font-size: 15px"
                                                       name="soLuong" value="1">
                                            </div>
                                            <p style="margin-top: 10px">${item.soLuong} sản phẩm có sẵn</p>
                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button style="font-size: 15px" type="button" class="btn btn-danger"
                                                data-dismiss="modal">Đóng
                                        </button>
                                        <button class="btn"
                                                style="font-size:15px;background-color: #37517E; color: white"
                                                href="/bumblebee/add-to-cart">Thêm vào giỏ hàng
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </div>
            <div class="ps-product-action">
                <div class="ps-pagination">
                    <ul class="pagination">
                        <li><a href="/bumblebee/product_list?p=${pageSP.number-1}&keyword=${param.keyword}">Pre</a></li>
                        <li><a href="/bumblebee/product_list?p=0&keyword=${param.keyword}">1</a></li>
                        <li><a href="/bumblebee/product_list?p=1&keyword=${param.keyword}">2</a></li>
                        <li><a href="/bumblebee/product_list?p=2&keyword=${param.keyword}">3</a></li>
                        <li><a href="/bumblebee/product_list?p=${pageSP.number+1}&keyword=${param.keyword}">Next</a>
                        </li>
                    </ul>
                </div>
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
                               style="padding: 5px 30px;font-size: 12px;line-height: 15px;background-color: #37517E"
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
                            <button class="ac-slider__filter ps-btn" type="submit" style="background-color: #37517E">Tìm kiếm</button>
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
</script>
