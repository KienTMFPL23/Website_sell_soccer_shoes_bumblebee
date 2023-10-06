<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
    #myInput {
        height: 40px;
        border: 2px solid #37517E;
        border-radius: 15px;
        margin-bottom: 15px;
    }


    th, tr {
        line-height: 40px;
    }

</style>

<div class="header">
    <nav class="navbar navHoaDon navbar-expand-lg">
        <%-- list hóa đơn chờ--%>
        <c:forEach items="${listHoaDonCho}" var="hd" varStatus="i">
            <div class="hoaDonCho">
                <a class="theHoaDon" href="/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/${hd.id}"
                   style="margin-left: 5px"><b>Hóa đơn${i.count}</b>
                </a>
                <a href="/bumblebee/ban-hang-tai-quay/delete-hoadon/${hd.id}" class="btndele"><img
                        src="/images_template/deleteHD.png"></a>
            </div>
        </c:forEach>
        <div style="margin-left: 20px">
            <a form href="/bumblebee/ban-hang-tai-quay/create-hoadon"><img src="/images_template/add.png"></a>
        </div>
    </nav>
    <c:if test="${soLuongHD >= 5}">
        <p>Toi da </p>>
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
                    <div class="modal-dialog modal-xl">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Danh sách sản phẩm</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">

                                <table>
                                    <thead>
                                    <tr>
                                        <th scope="col"><input id="myInput" placeholder="Tìm kiếm sản phẩm"></th>
                                        <th scope="col"><%-- Màu sắc--%>
                                            <select id="selectMS">
                                                <option>---------</option>
                                                <c:forEach items="${listChatLieu}" var="ms">
                                                    <option>${ms.ten}</option>
                                                </c:forEach>
                                            </select></th>
                                        <th scope="col"><%-- Chất liệu --%>
                                            <select id="selectCL">
                                                <option>---------</option>
                                                <c:forEach items="${listMauSac}" var="cl">
                                                    <option>${cl.ten}</option>
                                                </c:forEach>
                                            </select></th>
                                        <th scope="col"> <%-- Đế giày--%>
                                            <select id="selectDG">
                                                <option>---------</option>
                                                <c:forEach items="${listDeGiay}" var="dg">
                                                    <option>${dg.loaiDe}</option>
                                                </c:forEach>
                                            </select></th>
                                        <th scope="col"> <%-- Kích cỡ--%>
                                            <select id="selectKC">
                                                <option>---------</option>
                                                <c:forEach items="${listKC}" var="kc">
                                                    <option>${kc.size}</option>
                                                </c:forEach>
                                            </select></th>
                                        <th scope="col"><%-- Loại giày--%>
                                            <select id="selectLG">
                                                <option>------------</option>
                                                <c:forEach items="${listLoaiGiay}" var="lg">
                                                    <option>${lg.tentheloai}</option>
                                                </c:forEach>
                                            </select></th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                </table>

                                <table class="table" id="tableModal">
                                    <thead>
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
                                            <td>${sp.giaBan}</td>
                                            <td><a href="/bumblebee/ban-hang-tai-quay/add-gio-hang/${sp.id}"
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
            <div class="ban-hang">
                <video
                        style="border: 1px solid"
                        id="video"
                        autoplay="true"
                        width="200px"
                        height="120px"
                ></video>
            </div>
        </div>
    </div>
    <table class="table">
        <tr class="row1" style="background-color:#34B6D3 ">
            <th scope="col">STT</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Đơn giá</th>
            <th scope="col">Thành tiền</th>
            <th scope="col"></th>
        </tr>
        <c:forEach items="${listHDCT}" var="hdct" varStatus="i">
            <form:form action="/bumblebee/ban-hang-tai-quay/update-cart/${hdct.id}" method="post">
                <tr style="background-color: #fff">
                    <td>${i.count}</td>
                    <td>${hdct.chiTietSanPham.sanPham.tenSanPham}</td>
                    <td>
                        <input type="number" class="form-control"
                               min="1"
                               name="soLuong"
                               value="${hdct.soLuong}"
                               onblur="this.form.submit()"
                               style="width:100px;">
                    </td>
                    <td>
                        <fmt:formatNumber value="${hdct.chiTietSanPham.giaBan}" type="number"/>
                    </td>
                    <td>
                        <fmt:formatNumber
                                value="${hdct.soLuong * hdct.chiTietSanPham.giaBan}" type="number"/></td>
                    <td>
                        <a onclick="return confirm('Bạn có muốn xóa không')"
                           href="/bumblebee/ban-hang-tai-quay/delete-hdct/${hdct.id}">
                            <img src="../../../img/delete.png">
                        </a>
                    </td>
                </tr>
            </form:form>
        </c:forEach>
    </table>
    <div>
        <b style="float: right;margin-right: 20px" class="name">Tổng tiền : <fmt:formatNumber value="${sumMoney}"
                                                                                              type="number"/></b>
    </div>
    <c:if test="${sumMoney != 0}">
        <a class="btn btn-primary" data-bs-toggle="offcanvas" href="#offcanvasExample" role="button"
           aria-controls="offcanvasExample">
            Thanh toán
        </a>
    </c:if>


    <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasExample"
         aria-labelledby="offcanvasExampleLabel">
        <div class="offcanvas-header">
            <h5 class="offcanvas-title" id="offcanvasExampleLabel">Thanh Toán</h5>
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
            <form:form method="post" modelAttribute="hoaDon"
                       action="/bumblebee/ban-hang-tai-quay/thanhtoan/${idHoaDon}">
                <%--                    <input value="${idHoaDon}" name="id" type="hidden">--%>
                <div class="row">
                    <p>Khách hàng: <form:input path="tenNguoiNhan" id="phoneNumber"
                                               onchange="getTenKhachHang()" class="form-control"/>
                    <b>Nhân viên bán hàng: <input class="form-control" value="${fullNameStaff}" readonly>
                    </b>
                    <p><b>Tổng tiền :</b> <fmt:formatNumber value="${sumMoney}" type="number"/>
                        đ</p>
                        <%--                    <p><b>Giảm giá :</b> 0đ</p>--%>
                    <p><b>Tổng tiền phải thu : </b> <fmt:formatNumber value="${sumMoney}" type="number"
                    /> đ</p>
                    <p><b>Tiền khách đưa: </b><input class="form-control" type="number" id="change"
                                                     onchange="getMoneyChange()"></p>
                    <p><b>Tiền thừa:</b> <label type="number" id="tienThua" name="tienThua" readonly
                                                groupingUsed="false"/>đ</p>
                    <p><b style="color: red">${errorThanhToan}</b></p>
                    <p><b>Ghi chú:</b> <form:textarea path="ghiChu" type="text" style="width: 300px"/></p>
                </div>
                <div class="dropdown mt-3">
                        <%--                    <button class="btn btn-primary">In hóa đơn</button>--%>
                    <button type="submit" class="btn btn-primary"
                            onclick="return confirm('Bạn có muốn thanh toán không')">Thanh toán
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>


<script>
    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#selectMS").click(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#selectCL").click(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#selectDG").click(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#selectLG").click(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#selectKC").click(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });


    // $(document).ready(function () {
    //     $('#selectLG').select2({
    //         width: 150,
    //         placeholder: "Lọc loại giày",
    //         ajax: {
    //             type: 'GET',
    //             url: '',
    //             data: function (params) {
    //                 return {
    //                     keyword: params.term || '',
    //
    //                 };
    //             },
    //             processResults: function (data) {
    //                 return {
    //                     results: $.map(data, function (item) {
    //                         return {
    //                             text: item.tentheloai,
    //                             id: item.id
    //                         }
    //                     })
    //                 };
    //             }
    //         }
    //     });
    // });
    //
    // $(document).ready(function () {
    //     $('#selectKC').select2({
    //         width: 150,
    //         placeholder: "Lọc kích cỡ",
    //         ajax: {
    //             type: 'GET',
    //             url: '',
    //             data: function (params) {
    //                 return {
    //                     keyword: params.term || '',
    //
    //                 };
    //             },
    //             processResults: function (data) {
    //                 return {
    //                     results: $.map(data, function (item) {
    //                         return {
    //                             text: item.size,
    //                             id: item.id
    //                         }
    //                     })
    //                 };
    //             }
    //         }
    //     });
    // });
    // $(document).ready(function () {
    //     $('#selectMS').select2({
    //         width: 150,
    //         placeholder: "Lọc màu sắc",
    //         ajax: {
    //             type: 'GET',
    //             url: '',
    //             data: function (params) {
    //                 return {
    //                     keyword: params.term || '',
    //
    //                 };
    //             },
    //             processResults: function (data) {
    //                 return {
    //                     results: $.map(data, function (item) {
    //                         return {
    //                             text: item.ten,
    //                             id: item.id
    //                         }
    //                     })
    //                 };
    //             }
    //         }
    //     });
    // });

    // $(document).ready(function () {
    //     $('#selectDG').select2({
    //         width: 150,
    //         placeholder: "Lọc đế giày",
    //         allowClear: true,
    //         ajax: {
    //             type: 'GET',
    //             url: '/bumblebee/ban-hang-tai-quay/search-de-giay/modal',
    //             data: function (params) {
    //                 return {
    //                     keyword: params.term || '',
    //
    //                 };
    //             },
    //             processResults: function (data) {
    //                 return {
    //                     results: $.map(data, function (item) {
    //                         return {
    //                             text: item.loaiDe,
    //                             id: item.id
    //                         }
    //                     })
    //                 };
    //             }
    //         }
    //     });
    // });


    // $(document).ready(function () {
    //     $('#selectCL').select2({
    //         width: 150,
    //         placeholder: "Lọc chất liệu",
    //         ajax: {
    //             type: 'GET',
    //             url: '',
    //             data: function (params) {
    //                 return {
    //                     keyword: params.term || '',
    //
    //                 };
    //             },
    //             processResults: function (data) {
    //                 return {
    //                     results: $.map(data, function (item) {
    //                         return {
    //                             text: item.ten,
    //                             id: item.id
    //                         }
    //                     })
    //                 };
    //             }
    //         }
    //     });
    // });
</script>

<script>
    function getMoneyChange() {
        var change = document.getElementById('change').value;
        document.getElementById('tienThua').innerHTML = change - ${sum};
        var tienThua = change - ${sum};
        const showThanhToan = document.getElementById("thanhToan");
        const showHoaDon = document.getElementById("inHoaDon");
        if (tienThua < 0) {
            showThanhToan.style.display = "none";
            showHoaDon.style.display = "none";
        } else {
            showThanhToan.style.display = "inline-block";
            showHoaDon.style.display = "inline-block";
        }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>
</html>

