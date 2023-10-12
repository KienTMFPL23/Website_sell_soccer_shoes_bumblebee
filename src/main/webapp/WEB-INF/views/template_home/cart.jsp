<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<main class="ps-main">
    <div class="ps-content pt-80 pb-80">
        <div class="ps-container">
            <div class="ps-cart-listing">
                ${thongBao}
                <form method="post">
                    <table class="table ps-cart__table">
                        <thead>
                        <tr>
                            <th width="50px"></th>
                            <th>SẢN PHẨM</th>
                            <th style="text-align: center">ĐƠN GIÁ</th>
                            <th style="text-align: center">SỐ LƯỢNG</th>
                            <th style="text-align: center">TỔNG TIỀN</th>
                            <th style="text-align: center"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${listGHCT}">
                            <tr>
                                <td><input class="checkCart" type="checkbox" name="idListCartDetail" value="${item.ctsp.id}"></td>
                                <td><a class="ps-product__preview" href="/bumblebee/detail/${item.ctsp.id}">
                                    <img class="mr-15" src="../../../uploads/${item.ctsp.hinhAnhs.tenanh}" width="100px" height="100px"> ${item.ctsp.sanPham.tenSanPham}</a></td>
                                <td><fmt:formatNumber value="${item.donGia}" type="currency"/></td>
                                <td>
                                    <div class="form-group--number">
                                        <button class="minus"><span>-</span></button>
                                        <input class="form-control" type="text" value="${item.soLuong}" style="font-size: 15px">
                                        <button class="plus"><span>+</span></button>
                                    </div>
                                </td>
                                <td><fmt:formatNumber value="${item.donGia * item.soLuong}" type="currency"/></td>                           
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
                                <button class="ps-btn ps-btn--gray">Tiếp tục xem hàng</button>
                            </div>
                        </div>
                        <div class="ps-cart__total">

                            <h3>Tổng tiền thanh toán: <span> ${totalPrice}</span></h3>
                            <button formaction="/bumblebee/thanh-toan" class="ps-btn" type="submit"
                                    style="background-color: #37517E">Thanh Toán<i
                                    class="ps-icon-next"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>

