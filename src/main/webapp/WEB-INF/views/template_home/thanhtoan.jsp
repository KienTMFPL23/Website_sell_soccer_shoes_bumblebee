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

    #city, #district, #ward {
        font-size: 14px;
        border-radius: 5px;
        height: 40px;
        line-height: 40px;
        border: 1px solid #A3A3A3;
        margin-right: 20px;
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
        <form class="ps-checkout__form" method="post">
            <div class="row" style="padding-top: 20px;">
                <div class="col-lg-12">
                    <div class="ps-checkout__order">
                        <header>
                            <h3>Thông tin nhận hàng</h3>
                        </header>
                        <div class="formInfor">
                            <div class="mb-3">
                                <label class="form-label"></label>
                                <input type="text" class="form-control" name="tenNguoiNhan" id="tenNguoiNhan"
                                       value="${listKH.ho} ${listKH.tenDem} ${listKH.ten}"
                                />
                            </div>
                            <div class="mb-3">
                                <label class="form-label"></label>
                                <input type="text" class="form-control" name="sdt" value="${listKH.soDienThoai}"
                                       id="sdt"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label"></label>
                                <input type="text" class="form-control" name="diaChiShip" id="diaChiShip"
                                       value="${listKH.diaChi}"
                                />
                            </div>
                            <div class="mb-3" id="province">
                                <select id="city" name="thanhPho" class="filterSelect">
                                    <option value="" selected>Chọn tỉnh thành</option>
                                </select>

                                <select id="district" name="huyen" class="filterSelect">
                                    <option value="" selected>Chọn quận huyện</option>
                                </select>

                                <select id="ward" name="xa" class="filterSelect">
                                    <option value="" selected>Chọn phường xã</option>
                                </select>
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

                                        <td style="text-align: center;padding-top: 25px;">
                                            <c:if test="${not empty sp.ctsp.ctkm}">
                                                <c:set var="allTrangThai1" value="false"/>
                                                <c:forEach var="km" items="${sp.ctsp.ctkm}">
                                                    <c:if test="${km.trangThai == 0}">
                                                        <c:set var="allTrangThai1" value="true"/>
                                                        <c:if test="${km.khuyenMai.donVi == '%'}">
                                                            <label id="thanhTien_${sp.id}"
                                                                   class="thanhTien"><fmt:formatNumber
                                                                    value="${(sp.ctsp.giaBan - (sp.ctsp.giaBan * km.khuyenMai.giaTri/100))*sp.soLuong}"
                                                                    type="number"/> đ</label>
                                                        </c:if>
                                                        <c:if test="${km.khuyenMai.donVi == 'VNĐ'}">
                                                            <label id="thanhTien_${sp.id}"
                                                                   class="thanhTien"><fmt:formatNumber
                                                                    value="${(sp.ctsp.giaBan - km.khuyenMai.giaTri)*sp.soLuong}"
                                                                    type="number"/> đ</label>
                                                        </c:if>
                                                        <p style="text-decoration: line-through"><fmt:formatNumber
                                                                value="${sp.donGia*sp.soLuong}"
                                                                type="number"/> đ</p>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${allTrangThai1 eq false}">
                                                    <label class="thanhTien">
                                                        <fmt:formatNumber
                                                                value="${sp.donGia * sp.soLuong}"
                                                                type="number"/> đ
                                                    </label>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty sp.ctsp.ctkm}">
                                                <label class="thanhTien">
                                                    <fmt:formatNumber
                                                            value="${sp.donGia * sp.soLuong}"
                                                            type="number"/> đ
                                                </label>
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
                                    <div style="height: 300px;">
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

                                        <div style="padding: 10px 30px">
                                            <input class="form-check-input" type="checkbox" value=""
                                                   id="flexCheckDefault" style="width: 10px">
                                            <label class="form-check-label" for="flexCheckDefault"
                                                   style="padding-left: 30px;font-size: 14px">
                                                Tôi đồng ý với chính sách của cửa hàng
                                            </label>
                                            <a style="cursor: pointer; font-size: 14px;margin-left: 30px;text-decoration: underline"
                                               type="button" data-toggle="modal" data-target="#exampleModalLong">
                                                chính sách
                                            </a>
                                            <!-- Modal -->
                                            <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog"
                                                 aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                                <div class="modal-dialog " role="document" style="max-width: 1200px">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLongTitle"
                                                                style="font-weight: 600;font-size: 20px">Chính sách cửa
                                                                hàng</h5>
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="container" style="margin-top: 70px">
                                                                <h2 style="text-align: center;font-size: 30px">Chính
                                                                    sách đổi sản phẩm khi mua hàng</h2>
                                                                <div>
                                                                    <strong style="font-size: 20px">I.Nội dung chính
                                                                        sách :</strong>
                                                                </div>
                                                                <div>
                                                                    <strong style="font-size: 15px">1. Các quy
                                                                        định</strong>
                                                                </div>
                                                                <p>Khi khách hàng mua sản phẩm, cửa hàng chỉ áp dụng đổi
                                                                    sản phẩm tương tự hoặc cùng giá với sản phẩm muốn
                                                                    đổi
                                                                    .Không áp dụng trả sản phẩm, hoàn tiền và đổi sản
                                                                    phẩm với giá thấp hoặc cao hơn với giá sản phẩm
                                                                    mua ban đầu</p>
                                                                <div>
                                                                    <strong style="font-size: 15px">2. Thời gian đổi
                                                                        hàng</strong>
                                                                </div>
                                                                <p> Thời gian áp dụng đổi hàng khi khách mua tại cửa
                                                                    hàng là 7 ngày</p>
                                                                <p> Đối với khách hàng mua online trên website thì thời
                                                                    gian đổi hàng là 7 ngày kể từ khi nhận được
                                                                    hàng </p>
                                                                <strong style="font-size: 20px">II. Điều kiện áp
                                                                    dụng</strong>
                                                                <p>* Cửa hàng gửi sai hàng khách đặt</p>
                                                                <p>* Hàng gửi không đủ số lượng</p>
                                                                <p>* Hàng có dấu hiệu cũ, đã qua sử dụng</p>
                                                                <p>* Khi đổi hàng cần còn nguyên tem, không được làm
                                                                    hỏng hoặc bẩn sản phẩm khi đổi</p>
                                                                <div>
                                                                    <strong style="font-size: 15px">III. Hướng dẫn đổi
                                                                        hàng</strong>
                                                                    <p>Bước 1: Kiểm tra điều kiện đổi hàng</p>
                                                                    <p>Khách hàng vui lòng tham khảo quy định và thời
                                                                        gian đổi hàng của chúng tôi ở trong mục I, II.
                                                                        Nhằm đảm bảo sản phẩm
                                                                        thỏa mãn điều kiện được đổi</p>
                                                                    <p>Bước 2: Đăng ký đổi sản phẩm</p>
                                                                    <p>Khách hàng cần nhập đầy đủ thông tin nhận
                                                                        hàng:</p>
                                                                    <p> + Họ tên người đặt hàng</p>
                                                                    <p> + Số điện thoại người đặt hàng</p>
                                                                    <p> + Họ tên người đặt hàng</p>
                                                                    <p> + Họ tên người đặt hàng</p>
                                                                    <p> + Họ tên người đặt hàng</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row" style="margin-top: 20px">
                                            <div class="col-lg-4"></div>
                                            <div class="col-lg-4">
                                                <button class="ps-btn" formaction="/bumblebee/dat-hang" type="submit"
                                                        onclick="return checkTrongThongTinNhanHang()">Đặt hàng<i
                                                        class="ps-icon-next" id="btnDatHang"></i>
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

    <div id="toast_warring_login" style="display:none;">
        <div class="toast toast__warring">
            <div class="toast__icon">
                <i class="fa-solid fa-triangle-exclamation" style="color: #ffc021;"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Thất bại</h3>
                <p class="toast__msg">Bạn cần đầy đủ thông tin nhận hàng</p>
            </div>
            <div class="toast__close">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg"
                     viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                </svg>
            </div>
        </div>
    </div>

    <div id="messeage_diaChiShip" style="display:none;">
        <div class="toast toast__warring">
            <div class="toast__icon">
                <i class="fa-solid fa-triangle-exclamation" style="color: #ffc021;"></i>
            </div>
            <div class="toast__body">
                <h3 class="toast__title">Thất bại</h3>
                <p class="toast__msg">Shop chỉ giao hàng trên địa phận thành phố Hà Nội</p>
            </div>
            <div class="toast__close">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg"
                     viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                </svg>
            </div>
        </div>
    </div>
</main>

</select>
</div>


<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        referrerpolicy="no-referrer"
></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script>
    var citis = document.getElementById("city");
    var districts = document.getElementById("district");
    var wards = document.getElementById("ward");
    var Parameter = {
        url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
        method: "GET",
        responseType: "application/json",
    };
    var promise = axios(Parameter);
    promise.then(function (result) {
        renderCity(result.data);
    });

    function renderCity(data) {
        for (const x of data) {
            var opt = document.createElement('option');
            opt.value = x.Name;
            opt.text = x.Name;
            opt.setAttribute('data-id', x.Id);
            citis.options.add(opt);
        }
        citis.onchange = function () {
            district.length = 1;
            ward.length = 1;
            if (this.options[this.selectedIndex].dataset.id != "") {
                const result = data.filter(n => n.Id === this.options[this.selectedIndex].dataset.id);

                for (const k of result[0].Districts) {
                    var opt = document.createElement('option');
                    opt.value = k.Name;
                    opt.text = k.Name;
                    opt.setAttribute('data-id', k.Id);
                    district.options.add(opt);
                }
            }
        };
        district.onchange = function () {
            ward.length = 1;
            const dataCity = data.filter((n) => n.Id === citis.options[citis.selectedIndex].dataset.id);
            if (this.options[this.selectedIndex].dataset.id != "") {
                const dataWards = dataCity[0].Districts.filter(n => n.Id === this.options[this.selectedIndex].dataset.id)[0].Wards;

                for (const w of dataWards) {
                    var opt = document.createElement('option');
                    opt.value = w.Name;
                    opt.text = w.Name;
                    opt.setAttribute('data-id', w.Id);
                    wards.options.add(opt);
                }
            }
        };
    }
</script>


<script>
    function conFirm() {
        alert("Đơn hàng của bạn được đặt thành công")
    }

    function capNhatTongTien() {
        var thanhTienList = document.getElementsByClassName("thanhTien");
        var total = 0;
        for (let i = 0; i < thanhTienList.length; i++) {
            var donGia = parseInt(thanhTienList.item(i).innerHTML.trim().replace(/[^\d]/g, ''), 10);
            total += donGia;
        }
        var totalFormatted = total.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
        document.getElementById("tongTien").innerHTML = totalFormatted;
    }

    capNhatTongTien()

</script>

<script>
    var ten = document.getElementById("tenNguoiNhan");
    var sdt = document.getElementById("sdt");
    var diaChiShip = document.getElementById("diaChiShip");
    var thanhPho = document.getElementById("city");
    var huyen = document.getElementById("district");
    var xa = document.getElementById("ward");
    var province = document.getElementById("province");

    function checkTrongThongTinNhanHang() {
        var checkbox = document.getElementById("flexCheckDefault");
        if (!checkbox.checked) {
            alert("Vui lòng đồng ý với chính sách của cửa hàng trước khi đặt hàng.");
            return false; // Ngăn chặn hành động tiếp theo nếu checkbox không được chọn
        }
        // Nếu checkbox được chọn, cho phép hành động tiếp theo

        if (ten.value === "" || sdt.value === "" || diaChiShip.value === "") {
            var toastElement = document.getElementById("toast_warring_login");
            toastElement.style.display = "block";
            setTimeout(function () {
                toastElement.style.display = "none";
            }, 1500);
            return false;
        }

        if (thanhPho.value === "") {
            return true;
        } else if (thanhPho.value !== "Thành phố Hà Nội") {
            var toastElement = document.getElementById("messeage_diaChiShip");
            toastElement.style.display = "block";
            setTimeout(function () {
                toastElement.style.display = "none";
            }, 10000);
            return false;
        }

        if (thanhPho.value !== "" && (xa.value === "" || huyen.value === "")) {
            var toastElement = document.getElementById("toast_warring_login");
            toastElement.style.display = "block";
            setTimeout(function () {
                toastElement.style.display = "none";
            }, 1500);
            return false;
        }
        return true;
    }


</script>

<script>
    var filterSelects = document.getElementsByClassName("filterSelect");

    for (var i = 0; i < filterSelects.length; i++) {
        filterSelects[i].addEventListener("change", function () {
            updateSelectedValues();
        });
    }

    function updateSelectedValues() {
        var selectedValues = [];

        for (var i = 0; i < filterSelects.length; i++) {
            var selectedValue = filterSelects[i].value;
            selectedValues.push(selectedValue);
        }

        // Hiển thị giá trị đã chọn lên trang HTML
        document.getElementById("diaChiShip").value = selectedValues.join("- ");
    }
</script>


</script>

