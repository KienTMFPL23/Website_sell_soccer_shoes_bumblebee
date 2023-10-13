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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
            integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
            crossorigin="anonymous"></script>
    <link href="/css/ban-hang/ban-hang.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="bodyBanHang">
    <div class="header">
        <nav class="navbar navHoaDon navbar-expand-lg">
            <%-- list hóa đơn chờ--%>
            <c:forEach items="${listHoaDonCho}" var="hd" varStatus="i">
                <div class="hoaDonCho">
                    <a id="content" class="theHoaDon"
                       href="/bumblebee/ban-hang-tai-quay/hoa-don-chi-tiet/${hd.id}"
                       style="margin-left: 5px"><b>Hóa đơn${i.count}</b>
                    </a>
                    <a href="/bumblebee/ban-hang-tai-quay/delete-hoadon/${hd.id}" class="btndele"><img
                            src="/images_template/deleteHD.png"></a>
                </div>
            </c:forEach>
            <div style="margin-left: 20px">
                <a id="themHoaDon" form href="/bumblebee/ban-hang-tai-quay/create-hoadon"><img
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
                                                data-bs-target="#exampleModal" style="background-color: #37517E;border: none">
                                            Tìm kiếm sản phẩm
                                        </button>
                                    </c:if>

                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1"
                                         aria-labelledby="exampleModalLabel"
                                         aria-hidden="true">
                                        <div class="modal-dialog modal-xl">
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
                                                        <tr>
                                                            <th scope="col"><input id="myInput"
                                                                                   placeholder="Tìm kiếm sản phẩm">
                                                            </th>
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

                                                    <table class="table" border="1px solid" id="tableModal">
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
                        <input id="searchSPGioHang" placeholder="Tìm kiếm sản phẩm đã chọn"
                               style="width: 400px;border: 2px solid #37517E;height: 40px;margin-bottom: 20px;border-radius: 20px;margin-top: 20px;padding-left: 20px">
                        <table class="table table-bordered">
                            <tr class="row1">
                                <th scope="col" style="background-color: #37517E;color: white">STT</th>
                                <th scope="col" style="background-color: #37517E;color: white">Tên sản phẩm</th>
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
                                                    value="${hdct.soLuong * hdct.chiTietSanPham.giaBan}"
                                                    type="number"/></td>
                                        <td>
                                            <a onclick="deleleHDCT()"
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
                            <input id="phoneNumber" type="number" onchange="getTenKhachHang(this.value)"
                                   class="form-control"/>
                        </div>
                        <div class="col-sm-2">
                            <!-- Them Khach hang -->

<!--                             <a type="button" id="openThemKH" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#khachHang">
                                <img src="/images_template/add.png" style="height: 25px;height: 25px">
                            </a> -->

                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#khachHang" style="background-color: #37517E;border: none">
                                <img src="/images_template/add.png" width="20px" style="background-color: #37517E">
                            </button>
                        </div>
                    </div>
                    <b style="color: red" id="tenKhachHang"></b></p>
                    <b>Nhân viên bán hàng: <input class="form-control" value="${fullNameStaff}" readonly>
                    </b>
                    <p><b>Tổng tiền : </b> <fmt:formatNumber value="${sumMoney}" type="number"/>
                        đ</p>
                        <%--                    <p><b>Giảm giá :</b> 0đ</p>--%>
                    <p><b>Tổng tiền phải thu : </b> <fmt:formatNumber value="${sumMoney}" type="number"/> đ</p>
                    <p><b>Tiền khách đưa: </b><input class="form-control" type="number" id="change"
                                                     onchange="getMoneyChange()"></p>
                    <p><b>Tiền thừa:</b> <label type="number" id="tienThua" name="tienThua" readonly/> đ</p>
                    <p><b style="color: red">${errorThanhToan}</b></p>
                    <p><b>Ghi chú:</b> <form:textarea path="ghiChu" type="text" style="width: 300px"/></p>
                    <div class="dropdown mt-3">
                            <%--                    <button class="btn btn-primary">In hóa đơn</button>--%>
                        <button disabled="true" id="btnThanhToan" type="submit" class=" btn-primary"
                                onclick="return confirm('Banj co muon thanh toan')" style="background-color: #37517E;cursor: pointer;color: white;border: none;padding: 10px 20px;border-radius: 10px">Thanh toán
                        </button>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="khachHang" tabindex="-1" aria-labelledby="themKhachHang"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="themKhachHang">Thêm khách hàng</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form method="post" modelAttribute="khachHang"
                                   action="/bumblebee/ban-hang-tai-quay/them-khach-hang">
                            <div class="mb-3">
                                <label class="form-label">Tên khách hàng</label>
                                <form:input path="ten" class="form-control"/>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Số điện thoại</label>
                                <form:input path="soDienThoai" id="getSDT" class="form-control"/>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </form:form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    function deleleHDCT() {
        Swal.fire({
            title: 'Bạn có muốn xóa không',
            showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: 'Yes',
            customClass: {
                actions: 'my-actions',
                cancelButton: 'order-1 right-gap',
                confirmButton: 'order-2',
                denyButton: 'order-3',
            }
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire('Saved!', '', 'success')
            } else if (result.isDenied) {
                Swal.fire('Changes are not saved', '', 'info')
            }
        })
    }
</script>
<script>
    var data = {
        <c:forEach items="${listKhachHang}" var="k">
        "${k.soDienThoai}": "${k.ho} ${k.tenDem} ${k.ten}",
        </c:forEach>
    };
    function getTenKhachHang(sdt) {
        let textName = document.getElementById("tenKhachHang");
        if (sdt === "") {
            textName.innerText = "Không tìm thấy khách hàng nào";
        } else if (data[sdt] !== undefined) {
            textName.innerText = data[sdt];
        } else {
            textName.innerText = "Không tìm thấy khách hàng nào";
        }
    }
    let dataInput = document.getElementById("tenKhachHang").value;
    var openModal = document.getElementById("openThemKH");
    var soDienThoai = document.getElementById("getSDT")
    //
    openModal.addEventListener("click",function (){
        var inputData = dataInput.value;
        soDienThoai.value = inputData;
    })
</script>
<script>
    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#searchSPGioHang").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#sanPhamMua tr").filter(function () {
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
</script>
<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
<script src="../../../js/ban_hang_tai_quay/ban_hang.js"></script>
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
    function getMoneyChange() {
        var change = document.getElementById('change').value;
        document.getElementById('tienThua').innerHTML = change - ${sumMoney};
        var soDu = change - ${sumMoney};
        if (soDu >= 0) {
            document.getElementById('btnThanhToan').disabled = false;
        } else {
            document.getElementById('btnThanhToan').disabled = true;
        }
    }
</script>
</body>
</html>