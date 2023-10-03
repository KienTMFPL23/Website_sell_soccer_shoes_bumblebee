<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
<script>

</script>
<div class="container">
    <div class="header">
        <nav class="navbar navHoaDon navbar-expand-lg">
            <div class="themSanPham">

            </div>
            <%-- list hóa đơn chờ--%>
            <c:forEach items="${listHoaDonCho}" var="hd">
                <div class="hoaDonCho">
                    <a href="/hoa-don-chi-tiet/${hd.id}" style="margin-left: 5px">HD1 - ${hd.ngayTao}</a>
                    <a href="/bumblebee/ban-hang-tai-quay/delete-hoadon/${hd.id}" class="btndele"><img
                            src="/images_template/deleteHD.png"></a>
                </div>
            </c:forEach>
            <div style="margin-left: 20px">
                <a form href="/bumblebee/ban-hang-tai-quay/create-hoadon"><img src="/images_template/add.png"></a>
            </div>
        </nav>
        <c:if test="${listHoaDonCho.size() > 5}">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Launch demo modal
            </button>

            <!-- Modal -->
            <div class="modal fade" id="getModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <b>Tối đa 5 hóa đơn chờ</b>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <div class="content">
        <div class="row">
            <div class="col-lg-6">
                <form>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Tìm kiếm sản phẩm
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Danh sách sản phẩm</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <input placeholder="Tìm kiếm sản phẩm theo tên hoặc mã">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">STT</th>
                                            <th scope="col">Tên</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Giá bán</th>
                                            <th scope="col">Hình ảnh</th>
                                            <th scope="col"></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listSanPham}" var="sp" varStatus="i">
                                            <tr>
                                                <td>${i.count}</td>
                                                <td>${sp.sanPham.tenSanPham}</td>
                                                <td>${sp.sanPham.soLuong}</td>
                                                    <%--                                                <td>${sp.sanPham.giaBan}</td>--%>
                                                <td><a href="">add</a>></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-6">
                <div class="ban-hang">
                    <form method="post" action="/ban-hang/scan-qr">
                        <video style="border: 1px solid" id="video" autoplay="true" width="200px"
                               height="120px"></video>
                        <input id="idSanPham" name="idCTSP" onchange="themGioHang()" type="hidden">
                    </form>
                </div>
            </div>
        </div>
        <b class="name">Giỏ hàng chi tiết</b>
        <table class="table">
            <tr class="row1" style="background-color:#34B6D3 ">
                <th scope="col">STT</th>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Thành tiền</th>
                <th scope="col"></th>
            </tr>
            <%--        <c:forEach items="${page.content}" var="item" varStatus="i">--%>
            <tr style="background-color: #fff">
                <td>1</td>
                <td>Giày puma view model</td>
                <td>5</td>
                <td>1500000</td>
                <td>250000000</td>
                <td><a href="#"><img src="../../../img/delete.png"></a></td>
            </tr>
            <%--        </c:forEach>--%>
        </table>
        <b class="name">Tổng tiền : 1 trịu đô</b>
        <a class="btn btn-primary" data-bs-toggle="offcanvas" href="#offcanvasExample" role="button"
           aria-controls="offcanvasExample">
            Thanh toán
        </a>

        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasExample"
             aria-labelledby="offcanvasExampleLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div>
                    Some text as placeholder. In real life you can have the elements you have chosen. Like,
                    text, images, lists, etc.
                </div>
                <div class="dropdown mt-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                        Dropdown button
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>