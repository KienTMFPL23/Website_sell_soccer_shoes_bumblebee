<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%--<%--%>

<%--    Date currentDate = new Date();--%>

<%--    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");--%>
<%--    String formattedDate = dateFormat.format(currentDate);--%>
<%--%>--%>

<style>
    .img {
        width: 30px;
        height: 30px;
    }

    /*==Style cơ bản cho website==*/
    body {
        font-family: Nunito;
        color: #333;
        background-color: #F3F3F3;
    }

    .ps-main {
        padding: 50px 100px 50px 100px;
    }

    .don-mua {
        background-color: #FFFFFF;
        margin-top: 20px;
        border-radius: 10px;
    }

    .don-hang {
        padding-left: 20px;
        padding-right: 20px;
    }

    .trang-thai-don-hang > p {
        color: crimson;
        float: right;
        padding-top: 15px;
        padding-bottom: -10px;
    }

    .table {
        margin-bottom: 20px;
    }

    .tong-tien {
        padding-bottom: 10px;
        color: crimson;
    }

    .infor {
        padding: 15px 0px 0px 20px;
        border-bottom: 2px solid #D9D9D9;
    }

    .infor-img > img {
        float: left;
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }

    .infor-name > h2 {
        padding: 15px;
        color: #0b0b0b;
        font-weight: 600;
    }

    .img {
        margin: 10px;
    }

    .menu-left > a {
        color: #0b0b0b;
        text-decoration: none;
        font-size: 15px;
    }

    .menu-left > a:hover {
        color: #37517E;
        text-decoration: none;
        font-size: 15px;
    }

    .table > a {
        text-decoration: none;

    }

    .menu-right {
        background-color: #FFFFFF;
        height: 40px;
        line-height: 40px;
        border-radius: 10px;
    }

    .col-lg-2 > a {
        text-decoration: none;
        font-size: 15px;
        padding-left: 15px;

    }

    .col-lg-10 > .ma-don {
        float: right;
        padding-right: 15px;
        font-size: 15px;
    }

    span {
        color: crimson;
        font-size: 15px;
    }

    .menu-right {
        margin-bottom: 20px;
    }

    .trang-thai {
        /*border: 4px solid #D9D9D9;*/
        border-radius: 50%;
        display: flex;
        margin: auto;

    }

    .status-order {
        padding: 10px 0px 10px 0px;
        background-color: #FFFFFF;
        border-radius: 10px;

    }


    .status-order > .row > .col-lg-2 {
        z-index: 1;
    }


    /*.inline{*/
    /*    background-color: rgb(45, 194, 88);*/
    /*    width: 100%;*/
    /*    margin: 30px 60px 0px 60px;*/
    /*    height: 3px;*/
    /*    position: absolute;*/
    /*    transition: width 1s cubic-bezier(.4,0,.2,1);*/
    /*}*/

    .stepper__line {
        position: absolute;
        top: 120px;
        height: 4px;
        width: 80%;
    }

    .stepper__line-background, .stepper__line-foreground {
        position: absolute;
        width: calc(100% - 500px);
        margin: 0 70px;
        height: 100%;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    .stepper__line-foreground {
        background: #D9D9D9;
        transition: width 1s cubic-bezier(.4, 0, .2, 1);
    }

    .stepper__line2 {
        position: absolute;
        top: 120px;
        height: 4px;
        width: 80%;
    }

    .stepper__line-background2, .stepper__line-foreground2 {
        position: absolute;
        width: calc(100% - 900px);
        margin: 0 70px;
        height: 100%;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    .stepper__line-foreground2 {
        background: #D9D9D9;
        transition: width 1s cubic-bezier(.4, 0, .2, 1);
    }

    /*.stepper__line-background, .stepper__line-foreground {*/
    /*    position: absolute;*/
    /*    width: calc(100% - 120px);*/
    /*    margin: 0 70px;*/
    /*    height: 100%;*/
    /*    -moz-box-sizing: border-box;*/
    /*    box-sizing: border-box;*/
    /*}*/

    .col-lg-2 > p, h4 {
        text-align: center;
        margin-top: 10px;

    }

    .table {
        margin-top: 30px;
        border-radius: 10px;
    }

    .dia-chi {
        background-color: #FFFFFF;
        padding-left: 30px;
        padding-top: 10px;
        border-radius: 10px;
    }
</style>
<div class="container">
    <form:form modelAttribute="hoaDon">
        <c:if test="${hoaDon.trangThai < 6 && hoaDon.loaiHoaDon==0}">
            <div class="status-order" id="status-order">
                <div class="row">
                        <%--                <c:if test="${hoaDon.trangThai}"></c:if>--%>
                    <div class="col-lg-2 status-item" id="status" data-status-id="1" data-status="Chờ xác nhận">
                        <img src="../../../img/order.png" style="width: 60px; height: 60px;" class="trang-thai">
                        <h4>Chờ xác nhận</h4>
                            <%--                        <p><%= formattedDate %>--%>
                            <%--                        </p>--%>
                    </div>
                    <div class="col-lg-2 status-item" id="status" data-status-id="2"
                         data-status="Đã xác nhận thanh toán">
                        <img src="../../../img/payment.jpg" style="width: 60px; height: 60px;" class="trang-thai">
                        <h4>Đang chuẩn bị</h4>
                            <%--                        <p>14:27 20/10/2023</p>--%>
                            <%--                        <p><%= formattedDate %>--%>
                            <%--                        </p>--%>
                    </div>
                        <%--                    <div class="col-lg-2 status-item" id="status" data-status-id="3" data-status="Đã nhận được hàng">--%>
                        <%--                        <img src="../../../img/receive-order.jpg" style="width: 60px; height: 60px;" class="trang-thai">--%>
                        <%--                        <h4> Đã giao cho DVVC</h4>--%>
                        <%--                        <p><%= formattedDate %>--%>
                        <%--                        </p>--%>
                        <%--                    </div>--%>
                    <div class="col-lg-2 status-item" id="status" data-status-id="4"
                         data-status="Đơn hàng đang được giao">
                        <img src="../../../img/truck.jpg" style="width: 60px; height: 60px;" class="trang-thai">
                        <h4>Đơn hàng đang được giao</h4>
                            <%--                        <p><%= formattedDate %>--%>
                            <%--                        </p>--%>
                    </div>
                    <div class="col-lg-2 status-item" id="status" data-status-id="5" data-status="Đã nhận được hàng">
                        <img src="../../../img/receive-order.jpg" style="width: 60px; height: 60px;"
                             class="trang-thai">
                        <h4>Hoàn thành</h4>
                            <%--                        <p><%= formattedDate %>--%>
                            <%--                        </p>--%>
                    </div>
                        <%--                    <div class="col-lg-2 status-item" id="status" data-status-id="5" data-status="Đã nhận được hàng">--%>
                        <%--                        <img src="../../../img/cancel-order.jpg" style="width: 60px; height: 60px;"--%>
                        <%--                             class="trang-thai">--%>
                        <%--                        <h4>Đã huỷ</h4>--%>
                        <%--&lt;%&ndash;                        <p><%= formattedDate %>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                        </p>&ndash;%&gt;--%>
                        <%--                    </div>--%>
                    <div class="inline"></div>
                    <div class="stepper__line">
                        <div class="stepper__line-background"></div>
                        <div class="stepper__line-foreground"></div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${hoaDon.trangThai < 6 &&hoaDon.loaiHoaDon==1}">
            <div class="status-order" id="status-order">
                <div class="row">
                        <%--                <c:if test="${hoaDon.trangThai}"></c:if>--%>
                    <div class="col-lg-2 status-item" id="status" data-status-id="1" data-status="Chờ xác nhận">
                        <img src="../../../img/order.png" style="width: 60px; height: 60px;" class="trang-thai">
                        <h4>Chờ xác nhận</h4>
                            <%--                        <p><%= formattedDate %>--%>
                            <%--                        </p>--%>
                    </div>

                    <div class="col-lg-2 status-item" id="status" data-status-id="5" data-status="Đã nhận được hàng">
                        <img src="../../../img/receive-order.jpg" style="width: 60px; height: 60px;"
                             class="trang-thai">
                        <h4>Hoàn thành</h4>
                            <%--                        <p><%= formattedDate %>--%>
                            <%--                        </p>--%>
                    </div>

                    <div class="inline"></div>
                    <div class="stepper__line2">
                        <div class="stepper__line-background2"></div>
                        <div class="stepper__line-foreground2"></div>
                    </div>
                </div>
            </div>
        </c:if>

        <%--        trạng thái chờ xác nhận--%>
        <c:if test="${hoaDon.trangThai== 1}">
            <h1 style="color: #2c9faf">Chờ xác nhận <i class="bi bi-calendar-check"></i></h1>
            <hr class="border border-primary border-2 opacity-50">
            <%--            22.11 --%>
            <%--            <a href="/don-hang/update-xac-nhan/${hoaDon.id}" style="border-radius: 20px"--%>
            <%--               class="btn btn-warning" onclick="return confirm('Chờ xác nhận ?');">Xác nhận</a>--%>

            <%--            <a href="/don-hang/huy-don-hang/${hoaDon.id}" class="btn btn-danger"--%>
            <%--               onclick="return confirm('Bạn có chắc muốn huỷ đơn hàng ?');" style="border-radius: 20px">Huỷ</a>--%>
        </c:if>
        <%--        trạng thái đang chuẩn bị--%>
        <c:if test="${hoaDon.trangThai== 2 && hoaDon.loaiHoaDon==0}">
            <h1 style="color: red">Chuẩn bị <i class="bi bi-box-seam"></i></h1>
            <hr class="border border-primary border-2 opacity-50">

            <%--            <a href="/don-hang/update-chuan-bi/${hoaDon.id}" style="border-radius: 20px"--%>
            <%--               class="btn btn-primary" onclick="return confirm('Bạn có chắc muốn giao hàng ?');">Giao hàng</a>--%>
            <%--            <a href="/don-hang/huy-don-hang/${hoaDon.id}" class="btn btn-danger"--%>
            <%--               style="border-radius: 20px">Huỷ</a>--%>
        </c:if>

        <c:if test="${hoaDon.trangThai== 3 && hoaDon.loaiHoaDon==0}">
            <h1 style="color: red"><i class="bi bi-truck"></i> Đã giao cho DVVC</h1>
            <hr class="border border-primary border-2 opacity-50">

            <%--            <a href="/don-hang/dang-giao/${hoaDon.id}" style="border-radius: 20px"--%>
            <%--               class="btn btn-success" onclick="return confirm('Xác nhận đã giao cho DVVC ?');">Xác nhận</a>--%>
        </c:if>
        <c:if test="${hoaDon.trangThai== 4 && hoaDon.loaiHoaDon==0}">
            <h1 style="color: red">Đang giao</h1>
            <hr class="border border-primary border-2 opacity-50">
            <%--            <a href="/don-hang/xac-nhan-giao/${hoaDon.id}" style="border-radius: 20px"--%>
            <%--               class="btn btn-warning" onclick="return confirm('Xác nhận đang giao?');">Xác nhận</a>--%>
        </c:if>
        <c:if test="${hoaDon.trangThai== 5}">
            <h1 style="color: red">Hoàn thành</h1>
            <hr class="border border-primary border-2 opacity-50">

            <%--            <a href="/don-hang/tra-hang/${hoaDon.id}" style="border-radius: 20px"--%>
            <%--               class="btn btn-warning" onclick="return confirm('Xác nhận trả hàng ?');">Trả hàng</a>--%>
        </c:if>
        <c:if test="${hoaDon.trangThai== 6}">
            <h1 style="color: red"><img src="../../../img/istockphoto-1707893816-612x612.jpg"
                                        style="width: 60px; height: 60px;"> Đơn hàng trả
            </h1>
            <hr class="border border-primary border-2 opacity-50">
            <%--            <a href="/don-hang/da-tra-hang/${hoaDon.id}" style="border-radius: 20px"--%>
            <%--               class="btn btn-warning" onclick="return confirm('Bạn có chắc muốn trả hàng ?');">Xác nhận</a>--%>
        </c:if>
        <c:if test="${hoaDon.trangThai== 7}">
            <h1 style="color: red">Đơn hàng đổi</h1>
            <hr class="border border-primary border-2 opacity-50">
        </c:if>
        <c:if test="${hoaDon.trangThai== 8}">
            <h1 style="color: red">Đơn hàng đã huỷ</h1>
            <hr class="border border-primary border-2 opacity-50">
        </c:if>
        <%--        <hr class="border border-secondary border-2 opacity-50">--%>
        <div class="row">
            <div class="col-10">
                <h2>Thông tin đơn hàng</h2>
                <br>
            </div>
            <div class="col-2">
                <div class="col-lg-2 rev-cpicker-column-inner-right">
                    <a href="/don-hang/list-all" class=" btn btn-primary btnReload">
                        <i class="bi bi-arrow-return-right"></i>
                    </a>
                </div>
            </div>
        </div>

        <%--        </hr>--%>
        <div class="row">
            <div class="col-6">
                <table>
                    <tr>
                        <th scope="col">Mã đơn hàng</th>
                        <td></td>
                        <td></td>
                        <td>${hoaDon.maHoaDon}</td>
                    </tr>

                    <tr>
                        <th scope="col">Trạng thái đơn hàng</th>
                        <td></td>
                        <td></td>
                        <td>
                            <c:if test="${hoaDon.trangThai== 1 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-primary">Chờ xác
                                    nhận
                                </button>
                            </c:if>
                            <c:if test="${hoaDon.trangThai== 2 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-warning">Đang chuẩn
                                    bị
                                </button>
                            </c:if>
                            <c:if test="${hoaDon.trangThai== 3 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-primary">Đã giao cho
                                    đơn vị vận chuyển
                                </button>

                            </c:if>
                            <c:if test="${hoaDon.trangThai== 4 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-warning">Đang giao
                                </button>

                            </c:if>
                            <c:if test="${hoaDon.trangThai== 5 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-danger"> Hoàn thành
                                </button>

                            </c:if>
                            <c:if test="${hoaDon.trangThai== 6 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-warning">Đơn hàng trả
                                </button>

                            </c:if>
                            <c:if test="${hoaDon.trangThai== 7 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-primary">Đơn hàng đổi
                                </button>

                            </c:if>
                            <c:if test="${hoaDon.trangThai== 8 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-primary">Đã huỷ
                                </button>

                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th scope="col">Loại đơn hàng</th>
                        <td></td>
                        <td></td>
                        <td>
                            <c:if test="${hoaDon.loaiHoaDon== 0 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-secondary">Bán Online
                                </button>

                            </c:if>
                            <c:if test="${hoaDon.loaiHoaDon== 1 }">
                                <button type="button" style="border-radius: 30px;" class="btn btn-primary">Bán tại
                                    quầy
                                </button>

                            </c:if>
                        </td>
                    </tr>

                </table>

            </div>
            <div class="col-6">

                <table>
                    <tr>
                        <th scope="col">Tên khách hàng</th>
                        <td></td>
                        <td></td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty hoaDon.tenNguoiNhan }">
                                    ${hoaDon.tenNguoiNhan}
                                </c:when>
                                <c:when test="${not empty hoaDon.khachHang.ten}">
                                    ${hoaDon.khachHang.ho} ${hoaDon.khachHang.tenDem} ${hoaDon.khachHang.ten}
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th scope="col">SĐT</th>
                        <td></td>
                        <td></td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty hoaDon.sdt }">
                                    ${hoaDon.sdt}
                                </c:when>
                                <c:when test="${not empty hoaDon.khachHang.soDienThoai}">
                                    ${hoaDon.khachHang.soDienThoai}
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th scope="col">Địa chỉ</th>
                        <td></td>
                        <td></td>

                        <td>
                            <c:choose>
                                <c:when test="${not empty hoaDon.diaChiShip }">
                                    ${hoaDon.diaChiShip}
                                </c:when>
                                <c:when test="${not empty hoaDon.khachHang.diaChi}">
                                    ${hoaDon.khachHang.diaChi}
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>

                </table>
            </div>

        </div>
    </form:form>

    <hr class="border border-danger border-2 opacity-50">

    <form:form modelAttribute="hoaDon">
        <%--        <a href="/don-hang/update-xac-nhan/${hoaDon.id}" style="border-radius: 20px"--%>
        <%--           class="btn btn-warning">Cập nhật</a>--%>
        <div class="row">
            <div class="col-10">
                <h2>Thông tin sản phẩm</h2>
            </div>
            <div class="col-lg-2">
                <form>
                    <!-- Button TIM KIEM -->

                    <c:if test="${ hoaDon.phuongThucThanhToan ==1 && hoaDon.loaiHoaDon!=1 && hoaDon.trangThai<4 }">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#exampleModal"
                                style="background-color: #37517E;border: none">
                            <i class="bi bi-plus-circle"></i> Thêm sản phẩm
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
                                                    <c:forEach items="${listMau}" var="ms">
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
                                                    <c:forEach items="${listKichCo}" var="kc">
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
                                        <c:forEach items="${listSanPham}" var="sp" varStatus="i">
                                            <tr>
                                                <td>${i.count}</td>
                                                <td>${sp.sanPham.tenSanPham}</td>
                                                <td>${sp.soLuong}</td>
                                                <td>${sp.mauSac.ten}</td>
                                                <td>${sp.chatLieu.ten}</td>
                                                <td>${sp.deGiay.loaiDe}</td>
                                                <td>${sp.kichCo.size}</td>
                                                <td>${sp.loaiGiay.tentheloai}</td>
                                                <td>

                                                    <c:if test="${not empty sp.ctkm}">
                                                        <c:set var="allTrangThai1" value="false"/>
                                                        <c:forEach items="${sp.ctkm}" var="ctkm">
                                                            <c:if test="${ctkm.trangThai == 0}">
                                                                <fmt:formatNumber>${ctkm.giaKhuyenMai}</fmt:formatNumber>
                                                                <del style="color: crimson">
                                                                    <fmt:formatNumber>${ctkm.ctsp.giaBan}</fmt:formatNumber></del>
                                                                <c:set var="allTrangThai1" value="true"/>
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
                                                    <a href="/don-hang/add-don-hang/${sp.id}"
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
        </div>

        <br>
        <div class="row text-center">
            <table class="table table-striped">
                <thead class="hoa-don-chi-tiet-thead">
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên Sản Phẩm</th>
                    <th scope="col">Kích cỡ</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Hình ảnh</th>
                    <th scope="col">Đơn Giá</th>
                    <th scope="col">Thành Tiền</th>
                    <c:if test="${ hoaDon.phuongThucThanhToan ==1 && hoaDon.loaiHoaDon!=1 && hoaDon.trangThai<4 }">
                        <th></th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${hoaDon.hoaDons}" varStatus="stt" var="hdct">
                    <tr>
                        <th scope="row">${stt.index + 1}</th>
                        <td>${hdct.chiTietSanPham.sanPham.tenSanPham}</td>
                        <td>${hdct.chiTietSanPham.kichCo.size}</td>
                            <%--                        <td>--%>
                            <%--                            <fmt:formatNumber value="${hdct.donGia}"--%>
                            <%--                                              type="number"/>--%>
                            <%--                        </td>--%>
                        <td>
                            <c:choose>
                                <c:when test="${hoaDon.loaiHoaDon==0 &&  hoaDon.phuongThucThanhToan ==1  && hoaDon.trangThai<4 }">
                                    <form:form action="/don-hang/update-cart/${hdct.id}" method="post">
                                        <input type="number" class="form-control"
                                               min="1"
                                               name="soLuong"
                                               value="${hdct.soLuong}"
                                               onblur="this.form.submit()"
                                               style="width:100px;">
                                    </form:form>
                                </c:when>

                                <c:otherwise>
                                    ${hdct.soLuong}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <img src="../../uploads/${hdct.chiTietSanPham.hinhAnhs.tenanh}" width="100px"
                                 height="100px"/>

                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${hdct.donGiaKhiGiam == 0 || hdct.donGiaKhiGiam == null}">

                                    <fmt:formatNumber>${hdct.donGia}</fmt:formatNumber>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatNumber>${hdct.donGiaKhiGiam}</fmt:formatNumber>
                                    <del style="color: crimson">
                                        <fmt:formatNumber>${hdct.donGia}</fmt:formatNumber>
                                    </del>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${hdct.donGiaKhiGiam == 0 || hdct.donGiaKhiGiam == null}">

                                    <fmt:formatNumber>${hdct.soLuong * hdct.donGia}</fmt:formatNumber>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatNumber>${hdct.soLuong * hdct.donGiaKhiGiam}</fmt:formatNumber>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <c:set var="totalProducts" value="${fn:length(hoaDon.hoaDons)}" />
                        <c:if test="${ totalProducts > 1 && hoaDon.phuongThucThanhToan ==1 && hoaDon.loaiHoaDon!=1 && hoaDon.trangThai<4 }">
                            <td>
                                <c:set var="totalProducts" value="${fn:length(hoaDon.hoaDons)}"/>
                                <c:if test="${ totalProducts > 1 && hoaDon.phuongThucThanhToan ==1 && hoaDon.loaiHoaDon!=1 && hoaDon.trangThai<4 }">
                                    <a href="/don-hang/delete-hdct/${hdct.id}"
                                       onclick="return confirm('Bạn có chắc muốn xoá sản phẩm này ?');">
                                        <img src="../../../img/delete.png">
                                    </a>
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:set var="hoaDonId" value="${hoaDon.id}"/>
        <div class="text-right">
            <td>
                <h3><b>

                    <c:set var="total" value="0"/>
                    <c:set var="giaGoc" value="0"/>
                    <c:set var="giaKhuyenMai" value="0"/>
                    <c:forEach items="${hoaDon.hoaDons}" var="hdct">
                        <c:if test="${hdct.donGiaKhiGiam != null || hdct.donGiaKhiGiam != 0}">
                            <c:set var="giaKhuyenMai" value="${hdct.donGiaKhiGiam * hdct.soLuong}"/>
                        </c:if>
                        <c:if test="${hdct.donGiaKhiGiam == null || hdct.donGiaKhiGiam == 0}">
                            <c:set var="giaGoc" value="${hdct.soLuong * hdct.donGia}"/>
                        </c:if>
                        <c:set var="total" value="${total + giaKhuyenMai + giaGoc}"/>
                        <c:set var="giaGoc" value="0"/>
                        <c:set var="giaKhuyenMai" value="0"/>
                    </c:forEach>
                    Tổng tiền: <fmt:formatNumber value="${total}" type="number"/> đ
                </b></h3>

            </td>
        </div>
    </form:form>
    <hr>
    <c:if test="${hoaDon.doiTra != null}">
        <c:if test="${hoaDon.trangThai == 6}">
            <h1 style="color: red">Sản phẩm trả</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Kích cỡ</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Hình ảnh</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Thành tiền</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${hoaDon.doiTra.doiTraChiTiets}" varStatus="i" var="dt">
                    <tr>
                        <td>${i.count}</td>
                        <td>${dt.chiTietSanPham.sanPham.tenSanPham}</td>
                        <td>${dt.chiTietSanPham.kichCo.size}</td>
                        <td>${dt.soLuong}</td>
                        <td> <img src="../../uploads/${dt.chiTietSanPham.hinhAnhs.tenanh}" width="100px"
                                  height="100px"/></td>
                        <td>${dt.donGia}</td>
                        <td>${dt.donGia * dt.soLuong}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${hoaDon.trangThai == 7}">
            <h1 style="color: red">Sản phẩm đổi</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Kích cỡ</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Hình ảnh</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Thành tiền</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${hoaDon.doiTra.doiTraChiTiets}" varStatus="i" var="dt">
                    <tr>
                        <td>${i.count}</td>
                        <td>${dt.chiTietSanPham.sanPham.tenSanPham}</td>
                        <td>${dt.chiTietSanPham.kichCo.size}</td>
                        <td>${dt.soLuong}</td>
                        <td> <img src="../../uploads/${dt.chiTietSanPham.hinhAnhs.tenanh}" width="100px"
                                  height="100px"/></td>
                        <td>${dt.donGia}</td>
                        <td>${dt.donGia * dt.soLuong}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const statusItems = document.querySelectorAll(".status-item");
        const hoaDonTrangThai = "${hoaDon.trangThai}";

        statusItems.forEach(function (item) {
            const status = item.getAttribute("data-status-id");
            const imgElement = item.querySelector("img");

            item.style.border = "none";
            item.style.borderRadius = "0%";
            imgElement.style.border = "4px solid rgb(45, 194, 88)";
            imgElement.style.borderRadius = "90%";
            if (status == 8) {
                document.getElementById("status-order").hidden = true;
            }

            if (status > hoaDonTrangThai) {

                document.getElementById("status").textShadow = "inset 6px -1px 15px 20px #888888";
                imgElement.style.border = "4px solid #D9D9D9";
                imgElement.style.borderRadius = "90%";

            } else {
                imgElement.style.border = "4px solid rgb(45, 194, 88)";
                imgElement.style.textShadow = "inset 6px -1px 15px 20px #888888";
                imgElement.style.borderRadius = "90%";


            }

        });
    });
</script>

<script>

    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        var table = document.getElementById("myTable");
        $("#myTable tr").filter(function () {
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
