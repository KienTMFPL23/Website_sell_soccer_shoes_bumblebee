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
                            <th>ĐƠN GIÁ</th>
                            <th>SỐ LƯỢNG</th>
                            <th>TỔNG TIỀN</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${listGHCT}">
                            <tr>
                                <td><input class="checkCart" type="checkbox" name="idListCartDetail"
                                           value="${item.ctsp.id}"></td>
                                <td><a class="ps-product__preview" href="/bumblebee/detail/${item.ctsp.id}">
                                    <img class="mr-15" src="../../../uploads/${item.ctsp.hinhAnhs.tenanh}" width="100px"
                                         height="100px"> ${item.ctsp.sanPham.tenSanPham}</a></td>
                                <td id="donGia_${item.ctsp.id}"><fmt:formatNumber value="${item.donGia}"
                                                                                  type="currency"/></td>
                                <td>
                                    <div class="form-group--number">
                                        <a style="color: white" onclick="truSL('${item.ctsp.id}')"
                                           class="minus"><span>-</span></a>
                                        <input class="form-control" id="soLuongCTSP_${item.ctsp.id}" type="text"
                                               value="${item.soLuong}" style="font-size: 15px;top: 0">
                                        <a style="color: white" onclick="themSL('${item.ctsp.id}')"
                                           class="plus"><span>+</span></a>
                                    </div>
                                </td>

                                <td id="thanhTien_${item.ctsp.id}"><fmt:formatNumber
                                        value="${item.donGia * item.soLuong}" type="currency"/></td>
                                <td>
                                    <div class="ps-remove"></div>
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
                            <button formaction="/bumblebee/thanh-toan" class="ps-btn" type="submit"
                                    style="background-color: #37517E">Mua Hàng<i
                                    class="ps-icon-next"></i></button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</main>
<script>
    function truSL(itemId) {
        var inputElement = document.getElementById("soLuongCTSP_" + itemId);
        var currentQuantity = parseInt(inputElement.value, 10);
        if (currentQuantity > 1) {
            inputElement.value = currentQuantity - 1;
            capNhatThanhTien(itemId);
        }
    }

    function themSL(itemId) {
        var inputElement = document.getElementById("soLuongCTSP_" + itemId);
        var currentQuantity = parseInt(inputElement.value, 10);
        inputElement.value = currentQuantity + 1;
        capNhatThanhTien(itemId);
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

