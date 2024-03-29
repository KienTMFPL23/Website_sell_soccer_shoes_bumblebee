<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="utf-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ban Hang</title>
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css"
            rel="stylesheet"
    />


    <link
            href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <%--    Logo--%>
    <link rel="icon" href="../../../images_template/logo_bumblebee.png">
    <%--    Css--%>
    <link href="/css/ban-hang/ban-hang.css" rel="stylesheet" type="text/css">
    <%--    bootstrap 5--%>
    <link
            href="../../../template_bootstrap/css/plugin/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
    />

</head>
<style>
    #myInput {
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
    }

    #selectCL, #selectDG, #selectKC, #selectLG, #selectMS {
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
    }

</style>
<body>
<div class="bodyBanHang">
    <div class="header">
        <nav class="navbar navHoaDon navbar-expand-lg">
            <%-- list hóa đơn chờ--%>
            <c:forEach items="${listHoaDonCho}" var="hd" varStatus="i">
                <div class="hoaDonCho" id="hoaDonStatus_${hd.id}">
                    <a id="content" class="theHoaDon"
                       href="/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/${hd.id}"
                       style="margin-left: 5px"><b>Hóa đơn${i.count}</b>
                    </a>

                    <a href="/bumblebee/ban-hang-tai-quay/delete-hoadon/${hd.id}"
                       onclick="return confirm('Bạn có muốn xóa không ?')"

                       class="btndele"><img
                            src="/images_template/deleteHD.png"></a>

                </div>
            </c:forEach>
            <div style="margin-left: 20px">
                <a id="themHoaDon" onclick="showAlertHoaDon(event)"
                   href="/bumblebee/ban-hang-tai-quay/create-hoadon"><img
                        src="/images_template/add.png"></a>
            </div>
            <div style="margin-right: 20px;position: absolute;right: 20px">
                <a href="/admin/dashboard"><img src="/img/house.png"></a>
            </div>
        </nav>
    </div>
    <div class="content">
        <div class="row">
            <div class="col-lg-8">
                <div class="row">
                    <div class="col-lg-8">
                        <!-- Modal -->
                        <div class="modal fade" id="getCodeModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-label="Close"><span
                                                aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel"> API CODE </h4>
                                    </div>
                                    <div class="modal-body" id="getCode" style="overflow-x: scroll;">
                                        //ajax success content here.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="content">
                        <div class="row">
                            <div class="col-lg-6">
                                <form>
                                    <!-- Button TIM KIEM -->

                                    <c:if test="${idHoaDon != null}">
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                                data-bs-target="#exampleModal"
                                                style="background-color: #37517E;border: none">
                                            Tìm kiếm sản phẩm
                                        </button>
                                    </c:if>

                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1"
                                         aria-labelledby="exampleModalLabel"
                                         aria-hidden="true">
                                        <div class="modal-dialog modal-xl modal-dialog-scrollable">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Danh sách
                                                        sản
                                                        phẩm</h1>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">

                                                    <table>
                                                        <thead>

                                                        </thead>
                                                    </table>

                                                    <table class="table" border="1px solid" id="tableModal">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col" colspan="2"><input id="myInput"
                                                                                               placeholder="Tìm kiếm sản phẩm">
                                                            </th>
                                                            <th scope="col"></th>
                                                            <th scope="col"><%-- Màu sắc--%>
                                                                <select id="selectMS">
                                                                    <option value="all">Lọc màu sắc</option>
                                                                    <c:forEach items="${listMauSac}" var="ms">
                                                                        <option>${ms.ten}</option>
                                                                    </c:forEach>
                                                                </select></th>
                                                            <th scope="col"><%-- Chất liệu --%>
                                                                <select id="selectCL">
                                                                    <option value="all">Lọc chất liệu</option>
                                                                    <c:forEach items="${listChatLieu}" var="cl">
                                                                        <option>${cl.ten}</option>
                                                                    </c:forEach>
                                                                </select></th>
                                                            <th scope="col"> <%-- Đế giày--%>
                                                                <select id="selectDG">
                                                                    <option value="all">Lọc đế giày</option>
                                                                    <c:forEach items="${listDeGiay}" var="dg">
                                                                        <option>${dg.loaiDe}</option>
                                                                    </c:forEach>
                                                                </select></th>
                                                            <th scope="col"> <%-- Kích cỡ--%>
                                                                <select id="selectKC">
                                                                    <option value="all">Lọc kích cỡ</option>
                                                                    <c:forEach items="${listKC}" var="kc">
                                                                        <option>${kc.size}</option>
                                                                    </c:forEach>
                                                                </select></th>
                                                            <th scope="col"><%-- Loại giày--%>
                                                                <select id="selectLG">
                                                                    <option value="all">Lọc loại giày</option>
                                                                    <c:forEach items="${listLoaiGiay}" var="lg">
                                                                        <option>${lg.tentheloai}</option>
                                                                    </c:forEach>
                                                                </select></th>
                                                            <th scope="col"></th>
                                                            <th scope="col"></th>
                                                        </tr>
                                                        <tr>
                                                            <th scope="col">STT</th>
                                                            <th scope="col">Tên</th>
                                                            <th scope="col">Số lượng</th>
                                                            <th scope="col">Màu sắc</th>
                                                            <th scope="col">Chất liệu</th>
                                                            <th scope="col">Đế giày</th>
                                                            <th scope="col">Kích cỡ</th>
                                                            <th scope="col">Loại giày</th>
                                                            <th scope="col">Giá bán</th>
                                                            <th scope="col"></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="myTable">

                                                        <c:forEach items="${listCTSPByTrangThaiAndSoLuong}" var="sp"
                                                                   varStatus="i">
                                                            <tr>
                                                                <td>${i.count}</td>
                                                                <td>${sp.sanPham.tenSanPham}</td>
                                                                <td>${sp.soLuong}</td>
                                                                <td id="mauSac">${sp.mauSac.ten}</td>
                                                                <td id="chatLieu">${sp.chatLieu.ten}</td>
                                                                <td id="loaiDe">${sp.deGiay.loaiDe}</td>
                                                                <td id="kichCo">${sp.kichCo.size}</td>
                                                                <td id="loaiGiay">${sp.loaiGiay.tentheloai}</td>
                                                                <td>
                                                                    <c:if test="${not empty sp.ctkm}">
                                                                        <c:set var="allTrangThai1" value="false"/>
                                                                        <c:forEach items="${sp.ctkm}" var="ctkm">
                                                                            <c:if test="${ctkm.trangThai == 0}">
                                                                                <fmt:formatNumber>${ctkm.giaKhuyenMai}</fmt:formatNumber>
                                                                                <del style="color: crimson">
                                                                                    <fmt:formatNumber>${ctkm.ctsp.giaBan}</fmt:formatNumber></del>
                                                                                <c:set var="allTrangThai1"
                                                                                       value="true"/>
                                                                            </c:if>

                                                                        </c:forEach>
                                                                        <c:if test="${allTrangThai1 eq false}">
                                                                            <fmt:formatNumber>${sp.giaBan}</fmt:formatNumber>
                                                                            <c:set var="allTrangThai1" value="true"/>
                                                                        </c:if>
                                                                    </c:if>
                                                                    <c:if test="${empty sp.ctkm}">
                                                                        <fmt:formatNumber>${sp.giaBan}</fmt:formatNumber>
                                                                    </c:if>
                                                                </td>
                                                                <td>
                                                                    <a href="/bumblebee/ban-hang-tai-quay/add-gio-hang/${sp.id}"
                                                                       class="btn btn-primary">Add</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-lg-6">
                                <c:if test="${idHoaDon != null}">
                                    <button type="button" id="startButton" onclick="startScan()" class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#staticBackdrop">
                                        Quét QR
                                    </button>
                                </c:if>
                                <h5 style="color: red">${erorSP}</h5>
                                <!-- Modal -->
                                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
                                     data-bs-keyboard="false" tabindex="-1"
                                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <div class="ban-hang vid">
                                                    <video
                                                            style="border: 1px solid"
                                                            id="video"
                                                            autoplay="true"
                                                            width="270px"
                                                            height="150px"
                                                    ></video>
                                                </div>
                                                <div id="output"></div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" id="stopButton" onclick="stopScan()"
                                                        class="btn btn-secondary"
                                                        data-bs-dismiss="modal">Close
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input id="searchSPGioHang" placeholder="Tìm kiếm sản phẩm đã chọn"
                               style="width: 400px;border: 2px solid #37517E;height: 40px;margin-bottom: 20px;border-radius: 20px;margin-top: 20px;padding-left: 20px">
                        <table class="table table-bordered">
                            <tr class="row1">
                                <th scope="col" style="background-color: #37517E;color: white">STT</th>
                                <th scope="col" style="background-color: #37517E;color: white">Tên sản phẩm</th>
                                <th scope="col" style="background-color: #37517E;color: white">Màu</th>
                                <th scope="col" style="background-color: #37517E;color: white">Size</th>
                                <th scope="col" style="background-color: #37517E;color: white">Số lượng</th>
                                <th scope="col" style="background-color: #37517E;color: white">Đơn giá</th>
                                <th scope="col" style="background-color: #37517E;color: white">Thành tiền</th>
                                <th scope="col" style="background-color: #37517E;color: white"></th>
                            </tr>
                            <tbody id="sanPhamMua">
                            <c:forEach items="${listHDCT}" var="hdct" varStatus="i">
                                <form:form action="/bumblebee/ban-hang-tai-quay/update-cart/${hdct.id}"
                                           method="post">
                                    <tr style="background-color: #fff">
                                        <td>${i.count}</td>
                                        <td>${hdct.chiTietSanPham.sanPham.tenSanPham}</td>
                                        <td>${hdct.chiTietSanPham.mauSac.ten}</td>
                                        <td>${hdct.chiTietSanPham.kichCo.size}</td>
                                        <td>
                                            <input type="number" class="form-control"
                                                   min="1"
                                                   name="soLuong"
                                                   id="soLuongCTSP_${hdct.id}"
                                                   value="${hdct.soLuong}"
                                                   onblur="this.form.submit()"
                                                   oninput="chonSoLuong('${hdct.id}',event)"
                                                   style="width:100px;">
                                        </td>
                                        <td>
                                            <c:if test="${not empty hdct.chiTietSanPham.ctkm}">
                                                <c:set var="allTrangThai1" value="false"/>
                                                <c:forEach items="${hdct.chiTietSanPham.ctkm}" var="ctkm">
                                                    <c:if test="${ctkm.trangThai == 0}">
                                                        <del style="color: crimson">
                                                            <fmt:formatNumber>${hdct.donGia}</fmt:formatNumber></del>
                                                        <fmt:formatNumber>${ctkm.giaKhuyenMai}</fmt:formatNumber>
                                                        <c:set var="allTrangThai1" value="true"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${allTrangThai1 eq false}">
                                                    <fmt:formatNumber>${hdct.donGia}</fmt:formatNumber>
                                                    <c:set var="allTrangThai1" value="true"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty hdct.chiTietSanPham.ctkm}">
                                                <fmt:formatNumber>${hdct.donGia}</fmt:formatNumber>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${not empty hdct.chiTietSanPham.ctkm}">
                                                <c:set var="allTrangThai1" value="false"/>
                                                <c:forEach items="${hdct.chiTietSanPham.ctkm}" var="ctkm">
                                                    <c:if test="${ctkm.trangThai == 0}">
                                                        <fmt:formatNumber>${hdct.soLuong * ctkm.giaKhuyenMai}</fmt:formatNumber>
                                                        <c:set var="allTrangThai1" value="true"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${allTrangThai1 eq false}">
                                                    <fmt:formatNumber>${hdct.donGia}</fmt:formatNumber>
                                                    <c:set var="allTrangThai1" value="true"/>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty hdct.chiTietSanPham.ctkm}">
                                                <fmt:formatNumber>${hdct.soLuong * hdct.chiTietSanPham.giaBan}</fmt:formatNumber>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a
                                                    href="/bumblebee/ban-hang-tai-quay/delete-hdct/${hdct.id}">
                                                <img src="../../../img/delete.png">
                                            </a>
                                        </td>
                                    </tr>
                                </form:form>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div>
                            <b style="float: right;margin-right: 20px; font-size: 20px" class="name">Tổng tiền :
                                <fmt:formatNumber value="${sumMoney}" type="number"/></b>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <form:form method="post" modelAttribute="hoaDon"
                           action="/bumblebee/ban-hang-tai-quay/thanhtoan/${idHoaDon}">
                    <%--                    <input value="${idHoaDon}" name="id" type="hidden">--%>
                <div class="row">
                    <b>Khách hàng:</b>
                    <div class="row">
                        <div class="col-sm-10">
                                <%-- <form:input  class="form-control" path="sdt" id="phoneNumber" name="soDienThoai" type="number"--%>
                                <%--  onchange="getTenKhachHang(this.value)" placeholder="Nhập số điện thoại" />--%>
                            <div class="input-group">
                                <form:input path="sdt" min="0" type="number" class="form-control"
                                            placeholder="Nhập số điện thoại..."
                                            oninput="getTenKhachHang(this.value)" id="phoneNumber"/>
                            </div>
                            <form:errors path="sdt" cssStyle="color: red"></form:errors>
                            <p style="color: red">${errorSDT}</p>
                            <p id="checkNull" style="color: red"></p>
                            <p style="color: red" id="check0"></p>
                        </div>
                        <div class="col-sm-2">

                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" id="openKhachHang"
                                    data-bs-target="#modalKhachHang" style="background-color: #37517E;border: none">

                                <img src="/images_template/add.png" width="20px" style="background-color: #37517E">
                            </button>
                        </div>
                    </div>
                    <b name="ten" style="color: red" id="tenKhachHang"></b></p>
                    <b>Nhân viên bán hàng: <input class="form-control" value="${fullNameStaff}" readonly>
                    </b>
                    <p><b>Tổng tiền : </b> <fmt:formatNumber value="${sumMoney}" type="number"/>
                        đ</p>
                        <%--                    <p><b>Giảm giá :</b> 0đ</p>--%>

                        <%--                    <p><b>Tổng tiền phải thu : </b> <fmt:formatNumber value="${sumMoney}" type="number"/> đ</p>--%>

                    <p><b>Tiền khách đưa: </b><input class="form-control" type="number" id="change"
                                                     oninput="getMoneyChange()"></p>
                    <p><b>Tiền thừa:</b> <label type="number" id="tienThua" name="tienThua" readonly/> đ</p>
                    <p><b style="color: red">${errorThanhToan}</b></p>
                    <p><b>Ghi chú:</b> <form:textarea path="ghiChu" type="text" style="width: 300px"/></p>
                    <div class="dropdown mt-3">
                            <%--                        <a class="btn btn-primary" type="submit" href="/bumblebee/ban-hang-tai-quay/print/${idHoaDon}"--%>
                            <%--                           download="hoadon.pdf" onclick="return downloadComplete()">In hóa đơn</a>--%>
                        <button id="btnThanhToan" type="submit" style="display: none" class="btn btn-primary"
                                onclick="return alertThanhToan()">
                            Thanh toán
                        </button>
                        <button id="btnThanhToan_disable" type="button" class="btn btn-danger">
                            Thanh toán
                        </button>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="modalKhachHang" tabindex="-1" aria-labelledby="themKhachHang"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="themKhachHang">Thêm khách hàng</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form method="post" modelAttribute="khachHang" id="sendKhachHang"
                                   action="/bumblebee/ban-hang-tai-quay/them-khach-hang">
                            <div class="mb-3">
                                <label class="form-label">Tên khách hàng</label>
                                <form:input path="ten" id="customer" class="form-control"/>
                                <form:errors path="ten" cssStyle="color: crimson"></form:errors>
                                <div id="ten-error" style="color: crimson;"></div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Số điện thoại</label>
                                <form:input path="soDienThoai" id="getSDT" class="form-control"/>
                                <form:errors path="soDienThoai" cssStyle="color: crimson"></form:errors>
                                <div id="soDienThoai-error" style="color: crimson;"></div>
                                <div id="duplicate" style="color: crimson;"></div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" onclick="submitFormKhachHang()">Submit
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="erorSP" value="${erorSP}" />
<!-- jQuery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Select2 JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script
        src="../../../template_bootstrap/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"
></script>
<script src="../../../template_bootstrap/js/bootstrap.min.js"
        crossorigin="anonymous"></script>

<script>
    function submitFormKhachHang() {
        var formData = $("#sendKhachHang").serialize();

        $.ajax({
            type: "POST",
            url: '/bumblebee/ban-hang-tai-quay/them-khach-hang',
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    $("#modalKhachHang").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#modalKhachHang').on('hidden.bs.modal', function (e) {
                        $("#error-message").empty();
                        $("div[id$='-error']").empty();
                        $("#soDienThoai-error").empty();
                        $("#ten-error").empty();
                        $("#duplicate").empty();
                        $('#sendKhachHang')[0].reset();
                    });

                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Your data has been saved",
                        showConfirmButton: false,
                        timer: 1500
                    });
                    location.reload();
                } else {
                    displayErrors(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrors(errors) {
        $("#soDienThoai-error").empty();
        $("#ten-error").empty();
        $("#duplicate").empty();

        errors.forEach(function (error) {
            var fieldName = error.split(":")[0].trim();
            var errorMessage = error.split(":")[1].trim();
            $("#" + fieldName + "-error").text(errorMessage);
        });

        if (errors.some(error => error.field === "soDienThoai")) {
            $("#duplicate").text("Lỗi! Số điện thoại đã tồn tại!");
        }
    }
</script>
<script>
    $(document).ready(function () {
        $("#phoneNumber").select2();
    });
</script>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>

<script>
    var data = {
        <c:forEach items="${listKhachHang}" var="k">
        "${k.soDienThoai}": "${k.ho} ${k.tenDem} ${k.ten}",
        </c:forEach>
    };

    function getTenKhachHang() {
        let textName = document.getElementById("tenKhachHang");
        var comboBox = document.getElementById("phoneNumber");
        var sdt = comboBox.value;
        if (sdt === "") {
            textName.innerText = "Khách vãng lai";
        } else if (data[sdt] !== undefined) {
            textName.innerText = data[sdt];
        } else {
            textName.innerText = "Khách vãng lai";
        }
    }


</script>
<script>
    const dataInput = document.getElementById("phoneNumber");
    const openModalButton = document.getElementById("openKhachHang");
    const modalInput = document.getElementById("getSDT");

    $(document).ready(function () {
        $('#openKhachHang').click(function () {
            $('#modalKhachHang').modal('show');
            modalInput.value = dataInput.value;
        });
    });

</script>
<script>

    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        var table = document.getElementById("myTable");
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            // table.style.display = "block"
        });

        // var table = document.getElementById("myTable");
        // if (table.style.display === "none") {
        //     table.style.display = "block";
        // } else {
        //     table.style.display = "none";
        // }
    });

    $("#searchSPGioHang").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#sanPhamMua tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#selectMS").change(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });

        if ("all" == value) {
            $("#myTable tr").show();
        }
    });

    $("#selectCL").change(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });

        if ("all" == value) {
            $("#myTable tr").show();
        }
    });

    $("#selectDG").change(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });

        if ("all" == value) {
            $("#myTable tr").show();
        }
    });

    $("#selectLG").change(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });

        if ("all" == value) {
            $("#myTable tr").show();
        }
    });

    $("#selectKC").change(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });

        if ("all" == value) {
            $("#myTable tr").show();
        }
    });
</script>

<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
<script src="../../../js/ban_hang_tai_quay/ban_hang.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="../../../js/ban_hang_tai_quay/them-khach-hang.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
    function getMoneyChange() {
        var change = document.getElementById('change').value;
        document.getElementById('tienThua').innerHTML = change - ${sumMoney};
        var soDu = change - ${sumMoney};
        if (soDu >= 0) {
            document.getElementById('btnThanhToan').style.display = 'block';
            document.getElementById('btnThanhToan_disable').style.display = 'none';
        } else {
            document.getElementById('btnThanhToan_disable').style.display = 'block';
            document.getElementById('btnThanhToan').style.display = 'none';
        }
    }
</script>
<script>
    var soLuongHoaDon = ${soLuongHD};

    function showAlertHoaDon(event) {
        if (soLuongHoaDon === 5) {
            Swal.fire({
                icon: 'error',
                title: 'Tối đa tạo 5 hóa đơn chờ !!!',
            })
            event.preventDefault();
        }
    }
</script>
<script>
    function confirmDelete(event) {

        var result = confirm('Bạn có muốn thanh toán không ??');
        if (result) {
            let timerInterval;
            Swal.fire({
                title: "Thành công !!!",
                position: "top-end",
                icon: "success",
                timer: 1500,
                timerProgressBar: true,
                didOpen: () => {
                    // Swal.showLoading();
                    const timer = Swal.getPopup().querySelector("b");
                    // timerInterval = setInterval(() => {
                    //   timer.textContent = `${Swal.getTimerLeft()}`;
                    // }, 100);
                },
                willClose: () => {
                    clearInterval(timerInterval);
                },
            }).then((result) => {
                /* Read more about handling dismissals below */
                if (result.dismiss === Swal.DismissReason.timer) {
                    console.log("I was closed by the timer");
                }
            });
            return true;
        } else {
            event.preventDefault();
            return false;
        }
    }

</script>

<script>
    function alertThanhToan(event) {
        var idHoaDon = "${idHoaDon}"
        var result = confirm('Bạn có muốn thanh toán không ??');
        if (result) {
            // Thực hiện thanh toán
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Thanh toán thành công",
                showConfirmButton: false,
                timer: 3000
            });
            setTimeout(function () {
                return true;
            }, 3000);

            var link = document.createElement('a');
            link.href = '/bumblebee/ban-hang-tai-quay/download-pdf/' + idHoaDon;
            link.target = '_blank';
            link.download = 'hoadon_' + ${hdct.hoaDon.maHoaDon} + '.pdf';
            document.body.appendChild(link);

            // Yêu cầu sự tương tác người dùng
            link.click();
            document.body.removeChild(link);
            // Tạo và tải hóa đơn PDF

        } else {
            // Người dùng đã hủy thanh toán
            return false;
        }

    }

</script>
<script>
    function chonSoLuong(itemId) {
        const newValue = event.target.value;
        if (newValue == "") {
            document.getElementById("soLuongCTSP_" + itemId).value = 1;
            alert("Số lượng sản phẩm không được để trống");
        }
    }
</script>
</body>
</html>