<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<main class="ps-main">
<%--    <p style="text-align: center;font-size: 33px;line-height: 90px">Giỏ hàng</p>--%>
    <div class="ps-content pb-80 pt-80">
        <div class="ps-container">
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
                                <hr style="margin-bottom: 20px">
                                <table class="tb" id="tableCart">
                                    <c:forEach var="item" items="${listGHCT}">
                                        <tr style="background-color: ${item.ctsp.soLuong == 0 ? '#e8e8e8':'white'}">
                                            <td>
                                                <c:if test="${item.ctsp.soLuong == 0}">
                                                    <p>Hết Hàng</p>
                                                </c:if>
                                                <c:if test="${item.ctsp.soLuong > 0}">
                                                    <input class="checkCart" type="checkbox" name="idListCartDetail"
                                                           value="${item.ctsp.id}">
                                                </c:if>
                                            </td>
                                            <td>
                                                <a class="ps-product__preview" href="/bumblebee/detail/${item.ctsp.id}">
                                                    <img class="mr-15"
                                                         src="../../../uploads/${item.ctsp.hinhAnhs.tenanh}"
                                                         width="100px"
                                                         height="100px">
                                                </a>
                                            </td>
                                            <td style="margin-left: -15px;margin-right: -15px">
                                                <div>${item.ctsp.sanPham.tenSanPham}</div>
                                                <div>${item.ctsp.mauSac.ten} - ${item.ctsp.kichCo.size}</div>
                                                <input id="donGia_${item.id}" type="hidden" value="${item.ctsp.giaBan}">

                                            </td>
                                            <td>
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
                                            <td id="thanhTien_${item.id}"><fmt:formatNumber
                                                    value="${item.donGia * item.soLuong}" type="currency"/></td>
                                            <td >
                                                <a href="/bumblebee/remove-ghct/${item.id}">
                                                    <div class=""><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
                                                        <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
                                                    </svg></div>
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
                                    <h3>Tổng tiền thanh toán: <span><fmt:formatNumber
                                            value="${totalPrice}" type="currency"/> </span></h3>
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
</main>
<script>
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
        var thanhTien = currentQuantity * donGia;
        console.log(currentQuantity,donGia,thanhTien)
        document.getElementById("thanhTien_" + itemId).innerText = formatCurrency(thanhTien);
    }

    function formatCurrency(number) {
        return number.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
    }
</script>

