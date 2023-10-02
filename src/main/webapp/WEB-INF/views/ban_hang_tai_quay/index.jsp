<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
      integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

<div class="container">
    <div class="header">
        <nav class="navbar navHoaDon navbar-expand-lg" style="background-color: #e3f2fd;">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link nameHoaDon" href="#">Link</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nameHoaDon" href="#">AAA</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
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
                        <div class="modal-dialog modal-dialog-scrollable">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <input placeholder="Tìm kiếm sản phẩm">
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
                                            <td><a href="#">Remove</a></td>
                                        </tr>
                                        <%--        </c:forEach>--%>
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
                <td><a href="#">Remove</a></td>
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