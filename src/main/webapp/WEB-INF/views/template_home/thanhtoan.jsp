<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
    .formInfor {
        padding: 30px;
    }

    .formInfor > .mb-3 > input {
        font-size: 14px;
        border-radius: 10px;
        height: 50px;
        line-height: 50px;
    }

    #textarea {
        font-size: 14px;
    }

    .ps-btn btnDatHang {
        margin-left: 100px;
    }

    .ps-payment-method li img {
        max-width: 150px;
        height: 40px;
        background-color: #F3F3F3;
        padding-left: 30px;
        margin-top: -10px;
    }

</style>

<main class="ps-main">
    <div class="container">
        <form class="ps-checkout__form" action="/bumblebee/dat-hang" method="post">
            <div class="row" style="padding-top: 20px;">
                <div class="col-lg-12">
                    <div class="ps-checkout__order">
                        <div class="row" style="padding: 20px">
                            <h2 style="color: crimson;">
                                <img src="https://cdn-icons-png.flaticon.com/512/684/684908.png" width="25px"
                                     height="25px"/>Thông tin
                                nhận hàng:
                            </h2>
                            <p style="font-size: 18px; color: black; font-weight: 600; margin-left: 10px;">${listKH.ho} ${listKH.tenDem} ${listKH.ten}
                                - ${listKH.soDienThoai} - ${listKH.diaChi}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="ps-checkout__order">
                        <div class="formInfor">
                            <div class="mb-3">
                                <label class="form-label"></label>
                                <input type="text" class="form-control" name="tenNguoiNhan"
                                       value="${listKH.ho} ${listKH.tenDem} ${listKH.ten}"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label"></label>
                                <input type="text" class="form-control" name="sdt"
                                       value="${listKH.soDienThoai}"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label"></label>
                                <input type="text" class="form-control" name="diaChiShip"
                                       value="${listKH.diaChi}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-lg-12">
                    <div class="ps-checkout__order">
                        <header>
                            <h3>Thanh toán</h3>
                        </header>
                        <div class="content">

                            <table class="table ps-checkout__products">
                                <thead>
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th></th>
                                    <th style="text-align: center;">Kích cỡ</th>
                                    <th style="text-align: center;">Màu sắc</th>
                                    <th style="text-align: center;">Số lượng</th>
                                    <th style="text-align: center;">Đơn giá</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listGHCT}" var="sp">
                                    <tr>
                                        <td><img src="../../../uploads/${sp.ctsp.hinhAnhs.tenanh}" width="60px"
                                                 height="60px"></td>
                                        <td style="padding-top: 25px;">${sp.ctsp.sanPham.tenSanPham}</td>
                                        <td style="text-align: center; padding-top: 25px;">${sp.ctsp.kichCo.size}</td>
                                        <td style="text-align: center;padding-top: 25px;">${sp.ctsp.mauSac.ten}</td>
                                        <td style="text-align: center;padding-top: 25px;">${sp.soLuong}</td>
                                        <td style="text-align: center;padding-top: 25px;" class="thanhTien">
                                            <c:if test="${not empty sp.ctsp.ctkm}">
                                                <fmt:formatNumber
                                                        value="${sp.donGiaKhiGiam * sp.soLuong}"
                                                        type="number"/>
                                            </c:if>
                                            <c:if test="${empty sp.ctsp.ctkm}">
                                                <fmt:formatNumber
                                                        value="${sp.donGia * sp.soLuong}"
                                                        type="number"/>
                                            </c:if>

                                        </td>
                                    </tr>
                                </c:forEach>
                                <td style="padding-top: 20px;">Ghi chú</td>
                                <td>
                                        <textarea class="form-control" id="textarea" rows="3"
                                                  name="ghiChu"
                                                  placeholder="Ghi chú cho người bán...">
                                        </textarea>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                </tbody>
                            </table>

                        </div>

                        <div class="row">
                            <div class="col-lg-8"></div>
                            <div class="col-lg-4" style="padding-bottom: 20px;">
                                <span style="font-size: 18px; font-weight: 600;">Tổng tiền hàng: </span>
                                <span style="font-size: 18px; " id="tongTien"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="ps-checkout__order">
                                <footer>
                                    <h3>Phương thức thanh toán</h3>
                                    <div style="height: 250px;">

                                        <div class="form-group cheque">
                                            <div class="ps-radio">
                                                <input class="form-control" type="radio" id="rdo01" name="payment"
                                                       value="1"
                                                       checked>
                                                <label for="rdo01">Thanh toán khi nhận hàng</label>
                                                <ul class="ps-payment-method">
                                                    <li><a href="#"><img src="../../../img/logo-cod.png" alt=""
                                                                         id="cod-logo"></a></li>
                                                </ul>
                                            </div>
                                        </div>

                                        <div class="form-group paypal">
                                            <div class="ps-radio ps-radio--inline">
                                                <input class="form-control" type="radio" name="payment" id="rdo02"
                                                       value="2">
                                                <label for="rdo02">Thanh toán VN Pay</label>
                                            </div>
                                            <ul class="ps-payment-method">
                                                <li><a href="#"><img src="../../../img/vnpay-logo.png" alt=""></a></li>
                                            </ul>
                                        </div>

                                        <div class="row">
                                            <div class="col-lg-4"></div>
                                            <div class="col-lg-4">
                                                <button class="ps-btn">Đặt hàng<i class="ps-icon-next"></i>
                                                </button>
                                            </div>
                                            <div class="col-lg-4"></div>
                                        </div>
                                    </div>
                                </footer>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</main>
<script>
    function conFirm() {
        alert("Đơn hàng của bạn được đặt thành công")
    }
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
capNhatTongTien()
</script>