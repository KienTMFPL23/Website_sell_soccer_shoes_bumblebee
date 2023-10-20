<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<main class="ps-main">
    <div class="ps-content pt-80 pb-80">
        <div class="ps-container">
            <div class="ps-cart-listing">
                ${thongBao}
                <form method="post">
                    <table class="table ps-cart__table" id="tableCart">
                        <thead>
                        <tr>
                            <th width="50px"></th>
                            <th>SẢN PHẨM</th>
                            <th>PHÂN LOẠI HÀNG</th>
                            <th>ĐƠN GIÁ</th>
                            <th>SỐ LƯỢNG</th>
                            <th>TỔNG TIỀN</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
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
                                        <img class="mr-15" src="../../../uploads/${item.ctsp.hinhAnhs.tenanh}"
                                             width="100px"
                                             height="100px">
                                            ${item.ctsp.sanPham.tenSanPham}
                                    </a>
                                </td>
                                <td>${item.ctsp.mauSac.ten} - ${item.ctsp.kichCo.size}</td>
                                <td id="donGia_${item.id}"><fmt:formatNumber value="${item.donGia}"
                                                                             type="currency"/></td>
                                <td>
                                    <div class="form-group--number">
                                        <a style="color: white; ${item.ctsp.soLuong == 0 ? 'disabled':''}" onclick="truSL('${item.id}')"
                                           class="minus"><span>-</span></a>
                                        <input class="form-control" id="soLuongCTSP_${item.id}" type="text"
                                               value="${item.soLuong}" style="font-size: 15px;top: 0" ${item.ctsp.soLuong == 0 ? 'disabled':''}>
                                        <a style="color: white;${item.ctsp.soLuong == 0 ? 'disabled':''}" onclick="themSL('${item.id}')"
                                           class="plus"><span>+</span></a>
                                    </div>
                                </td>

                                <td id="thanhTien_${item.id}"><fmt:formatNumber

                                        value="${item.donGia * item.soLuong}" type="currency"/></td>
                                <td>
                                    <a href="/bumblebee/remove-ghct/${item.id}">
                                        <div class="ps-remove"></div>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="ps-cart__actions">
                        <div class="ps-cart__promotion">
                            <div class="form-group">
                                <div class="ps-form--icon"><i class="fa fa-angle-right"></i>
                                    <input class="form-control" type="text" placeholder="Nhập mã giảm giá">
                                </div>
                            </div>
                            <div class="form-group">
                                <a href="/bumblebee/home" class="ps-btn ps-btn--gray">Tiếp tục xem hàng</a>
                            </div>
                        </div>
                        <div class="ps-cart__total">

                            <h3>Tổng tiền thanh toán: <span><fmt:formatNumber
                                    value="${totalPrice}" type="currency"/> </span></h3>
                            <c:if test="${idSPGHCT != null}">
                                <button formaction="/bumblebee/thanh-toan" class="ps-btn" type="submit"
                                        style="background-color: #37517E">Mua Hàng<i
                                        class="ps-icon-next"></i></button>
                            </c:if>
                            <c:if test="${idSPGHCT == null}"></c:if>
                            ${idSPGHCT}
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
        if (soLuongHienTai > 1) {
            var soLuongMoi = soLuongHienTai - 1;
        }
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

    function capNhatThanhTien(itemId) {
        var inputElement = document.getElementById("soLuongCTSP_" + itemId);
        var currentQuantity = parseInt(inputElement.value, 10);
        var donGia = parseFloat(document.getElementById("donGia_" + itemId).innerText.replace(/\D/g, ''));
        var thanhTien = currentQuantity * donGia;
        document.getElementById("thanhTien_" + itemId).innerText = formatCurrency(thanhTien);
    }

    function formatCurrency(number) {
        return number.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
    }
</script>

