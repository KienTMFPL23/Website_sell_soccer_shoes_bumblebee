<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<main class="ps-main">
    <div class="ps-content pb-80">
        <div class="ps-container">
            <div class="row" bis_skin_checked="1">
                <div class="col" bis_skin_checked="1">
                    <div class="breadcrumbs d-flex flex-row align-items-center" bis_skin_checked="1">
                        <ul>
                            <li><a style="font-size: 16px" href="/bumblebee/home">Home</a></li>
                            <li class="active"><a style="font-size: 16px" href="#"><i class="fa fa-angle-right"
                                                                                      aria-hidden="true"></i>Cart</a>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
            <div class="ps-cart-listing">
                ${thongBao}
                <form method="post">
                    <div class="row">
                        <div class="col-md-8">
                            <c:if test="${empty listGHCT}">
                                <tr>
                                    <h2 style="text-align: center">Không có sản phẩm nào trong giỏ hàng</h2>
                                </tr>
                            </c:if>

                            <c:if test="${not empty listGHCT}">
                                <table class="tb" id="tableCart">
                                    <c:forEach var="item" items="${listGHCT}">
                                        <tr style="background-color: ${item.ctsp.soLuong == 0 ? '#e8e8e8':'white'}">
                                            <td class="col-md-1">
                                                <c:if test="${item.ctsp.soLuong == 0}">
                                                    <p>Hết Hàng</p>
                                                </c:if>
                                                <c:if test="${item.ctsp.soLuong > 0}">
                                                    <input class="checkCart" type="checkbox" name="idListCartDetail"
                                                           value="${item.ctsp.id}">
                                                </c:if>
                                            </td>
                                            <td class="col-md-2">
                                                <a class="ps-product__preview" href="/bumblebee/detail/${item.ctsp.id}">
                                                    <img class="mr-15"
                                                         src="../../../uploads/${item.ctsp.hinhAnhs.tenanh}"
                                                         width="100px"
                                                         height="100px">
                                                </a>
                                            </td>
                                            <td style="margin-left: -15px;margin-right: -15px" class="col-md-3">
                                                <div>${item.ctsp.sanPham.tenSanPham}</div>
                                                <div>${item.ctsp.mauSac.ten} - ${item.ctsp.kichCo.size}</div>
                                                <c:if test="${item.ctsp.ctkm != null}">
                                                    <c:forEach var="km" items="${item.ctsp.ctkm}">
                                                        <c:if test="${km.khuyenMai.donVi == '%'}">
                                                            <input id="donGia_${item.id}" type="hidden"
                                                                   value="${item.ctsp.giaBan - (item.ctsp.giaBan * km.khuyenMai.giaTri/100)}">
                                                            <input id="donGiaChuaGiam_${item.id}" type="hidden"
                                                                   value="${item.ctsp.giaBan}">
                                                        </c:if>
                                                        <c:if test="${km.khuyenMai.donVi == 'VNÐ'}">
                                                            <input id="donGia_${item.id}" type="hidden"
                                                                   value="${(item.ctsp.giaBan - km.khuyenMai.giaTri)}">
                                                            <input id="donGiaChuaGiam_${item.id}" type="hidden"
                                                                   value="${item.ctsp.giaBan}">
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty item.ctsp.ctkm}">
                                                    <input id="donGia_${item.id}" type="hidden"
                                                           value="${item.ctsp.giaBan}">
                                                </c:if>
                                            </td>
                                            <td class="col-md-3">
                                                <div class="form-group--number">
                                                    <a style="color: white;display: ${item.ctsp.soLuong == 0 ? 'none':''}"
                                                       onclick="truSL('${item.id}')"
                                                       class="minus"><span style="color: black">-</span></a>
                                                    <input class="" id="soLuongCTSP_${item.id}" type="text"
                                                           value="${item.soLuong}"
                                                           style="font-size: 15px;top: 0" ${item.ctsp.soLuong == 0 ? 'disabled':''}
                                                           onchange="thayDoiSoLuong('${item.id}')">
                                                    <a style="background-color: black;border: none;display: ${item.ctsp.soLuong == 0 ? 'none':''}"
                                                       onclick="themSL('${item.id}')"
                                                       class="plus"><span style="color: white">+</span></a>
                                                    <input style="display: none" value="${item.ctsp.soLuong}"
                                                           id="slCTSP_${item.id}">
                                                </div>
                                            </td>
                                            <td class="tTien col-md-3">
                                                <c:if test="${item.ctsp.ctkm != null}">
                                                    <c:forEach var="km" items="${item.ctsp.ctkm}">
                                                        <c:if test="${km.khuyenMai.donVi == '%'}">
                                                            <label id="thanhTien_${item.id}"
                                                                   class="thanhTien"><fmt:formatNumber
                                                                    value="${(item.ctsp.giaBan - (item.ctsp.giaBan * km.khuyenMai.giaTri/100)*item.soLuong)}"
                                                                    type="number"/>đ</label>
                                                            <span id="thanhTienChuaGiam_${item.id}"><fmt:formatNumber
                                                                    value="${item.ctsp.giaBan*item.soLuong}"
                                                                    type="number"/></span>
                                                        </c:if>
                                                        <c:if test="${km.khuyenMai.donVi == 'VNÐ'}">
                                                            <label id="thanhTien_${item.id}"
                                                                   class="thanhTien"><fmt:formatNumber
                                                                    value="${(item.ctsp.giaBan - km.khuyenMai.giaTri)*item.soLuong}"
                                                                    type="number"/></label>
                                                            <span id="thanhTienChuaGiam_${item.id}"><fmt:formatNumber
                                                                    value="${item.ctsp.giaBan*item.soLuong}"
                                                                    type="number"/></span>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty item.ctsp.ctkm}">
                                                    <label id="thanhTien_${item.id}" class="thanhTien"><fmt:formatNumber
                                                            value="${item.donGia*item.soLuong}"
                                                            type="number"/>
                                                    </label>
                                                </c:if>
                                            </td>
                                            <td>
                                                <a href="/bumblebee/remove-ghct/${item.id}">
                                                    <div class="">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
                                                             fill="currentColor" class="bi bi-trash"
                                                             viewBox="0 0 16 16">
                                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                                            <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                                                        </svg>
                                                    </div>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                        <div class="col-md-4">
                            <div class="cart__actions">
                                <div class="cart__total">
                                    <p style="font-size: 16px">Tổng tiền thanh toán: <span id="tongTien">
                                    </span></p>
                                    <button formaction="/bumblebee/thanh-toan" type="submit"
                                            style="" class="btn-muahang">Mua Hàng<i
                                    ></i></button>
                                    <button formaction="/bumblebee/home" type="submit"
                                            style="" class="btn-muatiep">Tiếp Tục Mua Hàng<i
                                    ></i></button>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
    <div class="alert alert-success" id="alertCart" role="alert" style="display: none">
        This is a success alert—check it out!
    </div>
</main>
<script>

    function capNhatTongTien(){
        var thanhTienList = document.getElementsByClassName("thanhTien");
        var total = 0;
        for (let i = 0; i < thanhTienList.length; i++) {
            var donGia = parseInt(thanhTienList.item(i).innerHTML.trim().replace(/[^\d]/g, ''), 10);
            total += donGia;
        }
        var totalFormatted = total.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        document.getElementById("tongTien").innerHTML = totalFormatted;
    }
    capNhatTongTien();

    function truSL(itemId) {
        var soLuongHienTai = parseInt(document.getElementById("soLuongCTSP_" + itemId).value);
        if (soLuongHienTai - 1 < 1) {
            return;
        }
        var soLuongMoi = soLuongHienTai - 1;
        var inputElement = document.getElementById("soLuongCTSP_" + itemId);
        inputElement.value = soLuongMoi;
        capNhatThanhTien(itemId);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/bumblebee/update-cart?idGHCT=" + itemId, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                document.getElementById("soLuongCTSP_" + itemId).value = response.soLuong;
                document.getElementById("thanhTien_" + itemId).textContent = response.thanhTien;
            }
        };
        var data = JSON.stringify({productId: itemId, soLuong: soLuongMoi});
        xhr.send(data);
    }

    function themSL(itemId) {
        var soLuongHienTai = parseInt(document.getElementById("soLuongCTSP_" + itemId).value);
        var soLuongMoi = soLuongHienTai + 1;
        var inputElement = document.getElementById("soLuongCTSP_" + itemId);
        inputElement.value = soLuongMoi;
        capNhatThanhTien(itemId);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/bumblebee/update-cart?idGHCT=" + itemId, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                document.getElementById("soLuongCTSP_" + itemId).value = response.soLuong;
                document.getElementById("thanhTien_" + itemId).textContent = response.thanhTien;
            }
        };
        var data = JSON.stringify({productId: itemId, soLuong: soLuongMoi});
        xhr.send(data);


    }

    function thayDoiSoLuong(itemId) {
        var soLuongMoi = parseInt(document.getElementById("soLuongCTSP_" + itemId).value);
        var slCTSP = parseInt(document.getElementById("slCTSP_" + itemId).value);
        if (Number(soLuongMoi) < 0) {
            document.getElementById("soLuongCTSP_" + itemId).value = Number(1);
            capNhatThanhTien(itemId);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/bumblebee/update-cart?idGHCT=" + itemId, true);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    document.getElementById("soLuongCTSP_" + itemId).value = Number(1);
                    document.getElementById("thanhTien_" + itemId).textContent = response.thanhTien;
                }
            };
            var data = JSON.stringify({productId: itemId, soLuong: Number(1)});
            xhr.send(data);
        } else if (soLuongMoi > slCTSP) {
            capNhatThanhTien(itemId);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/bumblebee/update-cart?idGHCT=" + itemId, true);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    document.getElementById("soLuongCTSP_" + itemId).value = slCTSP;
                    document.getElementById("thanhTien_" + itemId).textContent = response.thanhTien;
                }
            };
            var data = JSON.stringify({productId: itemId, soLuong: slCTSP});
            xhr.send(data);
        } else if (Number(soLuongMoi) === 0) {
            document.getElementById("soLuongCTSP_" + itemId).value = Number(1);
            capNhatThanhTien(itemId);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/bumblebee/update-cart?idGHCT=" + itemId, true);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    document.getElementById("soLuongCTSP_" + itemId).value = Number(1);
                    document.getElementById("thanhTien_" + itemId).textContent = response.thanhTien;
                }
            };
            var data = JSON.stringify({productId: itemId, soLuong: Number(1)});
            xhr.send(data);
        } else {
            capNhatThanhTien(itemId);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/bumblebee/update-cart?idGHCT=" + itemId, true);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    document.getElementById("soLuongCTSP_" + itemId).value = response.soLuong;
                    document.getElementById("thanhTien_" + itemId).textContent = response.thanhTien;
                }
            };
            var data = JSON.stringify({productId: itemId, soLuong: soLuongMoi});
            xhr.send(data);
        }

    }

    function capNhatThanhTien(itemId) {
        var inputElement = document.getElementById("soLuongCTSP_" + itemId);
        var currentQuantity = parseInt(inputElement.value);
        var donGia = parseFloat(document.getElementById("donGia_" + itemId).value.replace(/[^0-9.]/g, ''));
        if (document.getElementById("donGiaChuaGiam_" + itemId) !== null) {
            var donGiaChuaGiam = parseFloat(document.getElementById("donGiaChuaGiam_" + itemId).value.replace(/[^0-9.]/g, ''));
            var thanhTienChuaGiam = currentQuantity * donGiaChuaGiam;
            document.getElementById("thanhTienChuaGiam_" + itemId).innerText = formatCurrency(thanhTienChuaGiam);
        }
        var thanhTien = currentQuantity * donGia;
        console.log(currentQuantity, donGia, thanhTien)
        document.getElementById("thanhTien_" + itemId).innerText = formatCurrency(thanhTien);
        capNhatTongTien();
    }

    function formatCurrency(number) {
        return number.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
    }
</script>

