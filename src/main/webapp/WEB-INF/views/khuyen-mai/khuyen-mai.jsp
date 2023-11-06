<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>

    /* Style the tab */
    .tab {
        overflow: hidden;
        border: 1px solid #ccc;
        background-color: #f1f1f1;
    }

    /* Style the buttons inside the tab */
    .tab button {
        background-color: inherit;
        float: left;
        border: none;
        outline: none;
        cursor: pointer;
        padding: 14px 16px;
        transition: 0.3s;
        font-size: 17px;
    }

    /* Change background color of buttons on hover */
    .tab button:hover {
        background-color: #ddd;
    }

    /* Create an active/current tablink class */
    .tab button.active {
        background-color: #ccc;
    }

    /* Style the tab content */
    .tabcontent {
        display: none;
        padding: 6px 12px;
        border: 1px solid #ccc;
        border-top: none;
    }

    table {
        width: 100%;
    }

    .table > table > thead > tr {
        background-color: #37517E;
        color: #FFFFFF;
    }

    .table > table > thead > .th1 {
        background-color: #FFFFFF;
    }

    .btnAdd {
        background-color: #EEEDED;
        margin-left: 50px;
        margin-bottom: 30px;
        border: 2px solid #37517E;
        border-radius: 10px;
        width: 150px;
        float: left;
        height: 40px;
        font-size: 18px;
        font-weight: 500;
        text-decoration: none;
        color: black;
        text-align: center;
    }

    .btnAdd:hover {
        background-color: #37517E;
        border: 2px solid #FFFFFF;
        color: #FFFFFF;
        text-decoration: none;
    }

    .btnAdd > img {
        margin-right: 10px;
        margin-top: 3px;
        margin-left: 15px;
        float: left;
    }

    .btnAdd > p {
        margin-top: 5px;
        margin-right: 10px;
    }

    #myInput1 {
        width: 250px;
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
        margin-bottom: 20px;
    }

    #myInput2 {
        width: 250px;
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
        margin-bottom: 20px;
    }

    #myInput3 {
        width: 250px;
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
        margin: 0px 0px 20px 10px;
    }

    .date {
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
        margin-left: 10px;
        margin-bottom: 10px;
    }


</style>
<body>

<div class="tab">
    <button class="tablinks active" onclick="openCity(event, 'London')">Quản lý khuyến mại</button>
    <button class="tablinks" onclick="openCity(event, 'Paris')">Sản phẩm khuyến mại</button>
</div>
<div id="London" class="tabcontent" style="display: block;">
    ${error}
    <div>
        <h1 style="text-align: center; font-family: Nunito;">Quản lý khuyến mại</h1>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <a href="/bumblebee/khuyen-mai/view-add" class="btnAdd">
                <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/>
                <p>Thêm mới</p>
            </a>
        </div>
    </div>

    <input id="myInput1" placeholder="Tìm kiếm sản phẩm">
    <div>

    </div>
    <div class="table">
        <table>
            <thead>
            <tr>
                <th>STT</th>
                <th>Mã khuyến mãi</th>
                <th>Tên khuyến mãi</th>
                <th>Giá trị</th>
                <th>Ngày tạo</th>
                <th>Trạng thái</th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody id="myTable1">
            <c:forEach items="${page.content}" var="km" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${km.maKhuyenMai}</td>
                    <td>${km.tenKhuyenMai}</td>
                    <td>${km.giaTri}</td>
                    <td>${km.ngayTao}</td>
                    <td>
                        <c:if test="${km.trangThai == 0}">Hoạt động</c:if>
                        <c:if test="${km.trangThai == 1}">Không hoạt động</c:if>
                    </td>
                    <td>
                        <a href="/bumblebee/khuyen-mai/view-update/${km.id}">
                            <img src="../../img/Edit_Notepad_Icon.svg" style="width: 30px; height: 30px;"/>
                        </a>
                    </td>
                    <td>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#${km.id}"
                                style="border-radius: 20px;">Chọn
                        </button>
                        <!-- Modal -->
                        <form action="/bumblebee/khuyen-mai/them-san-pham/${km.id}" method="post">
                            <div class="modal fade" id="${km.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
                                 aria-hidden="true">
                                <div class="modal-dialog modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Danh sách sản phẩm</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-lg-4">
                                                    <input id="myInput3" placeholder="Tìm kiếm sản phẩm">
                                                </div>
                                            </div>

                                                <%-- Ngày tạo--%>
                                            <div class="row">
                                                <div class="col-lg-4">
                                                    <span>Ngày bắt đầu:</span>
                                                    <input class="date" type="datetime-local" name="ngayBatDau"
                                                           id="ngayBatDau">
                                                </div>
                                                <div class="col-lg-4">
                                                    <span>Ngày kết thúc:</span>
                                                    <input class="date" type="datetime-local" name="ngayKetThuc"
                                                           id="ngayKetThuc">
                                                </div>
                                            </div>

                                            <div class="table">
                                                <table>
                                                    <thead>
                                                        <%--                                                    <tr class="th1">--%>
                                                        <%--                                                        <th scope="col" colspan="2">--%>
                                                        <%--                                                            <input id="myInput3" placeholder="Tìm kiếm sản phẩm">--%>
                                                        <%--                                                        </th>--%>
                                                        <%--                                                        <th scope="col" colspan="3">--%>
                                                        <%--                                                        </th>--%>
                                                        <%--                                                    </tr>--%>


                                                    <tr>
                                                        <th></th>
                                                        <th scope="col">Tên sản phẩm</th>
                                                        <th scope="col">Số lượng</th>
                                                        <th scope="col">Giá bán</th>
                                                        <th scope="col">Màu sắc</th>
                                                        <th scope="col">Kích cỡ</th>
                                                        <th scope="col">Ngày tạo</th>
                                                    </tr>
                                                    </thead>

                                                    <tbody id="myTable3">
                                                    <c:forEach items="${listCTSP}" var="sp" varStatus="i">
                                                        <tr>
                                                            <td><input class="checkCart" type="checkbox"
                                                                       name="idListCartDetail"
                                                                       value="${sp.id}" id="idListCartDetail"></td>
                                                            <td>${sp.sanPham.tenSanPham}</td>
                                                            <td>${sp.soLuong}</td>
                                                            <td>${sp.giaBan}</td>
                                                            <td>${sp.mauSac.ten}</td>
                                                            <td>${sp.kichCo.size}</td>
                                                            <td><fmt:formatDate value="${sp.ngayTao}"
                                                                                pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary"
                                                <%--                                                    data-bs-toggle="modal"--%>
                                                <%--                                                    data-bs-target="#exampleModalToggleLabel2"--%>
                                                <%--                                                                                                    onclick="checkValidation()"--%>
                                                <%--                                                    id="btnSubmit"--%>
                                            >Thêm
                                            </button>

                                                <%--                                            <div class="modal fade" id="exampleModalToggle2" aria-hidden="true"--%>
                                                <%--                                                 aria-labelledby="exampleModalToggleLabel2" tabindex="-1">--%>
                                                <%--                                                <div class="modal-dialog modal-dialog-centered">--%>
                                                <%--                                                    <div class="modal-content">--%>
                                                <%--                                                        <div class="modal-header">--%>
                                                <%--                                                            <h1 class="modal-title fs-5" id="exampleModalToggleLabel2">--%>
                                                <%--                                                                Modal 2</h1>--%>
                                                <%--                                                            <button type="button" class="btn-close"--%>
                                                <%--                                                                    data-bs-dismiss="modal" aria-label="Close"></button>--%>
                                                <%--                                                        </div>--%>
                                                <%--                                                        <div class="modal-body">--%>
                                                <%--                                                            Hide this modal and show the first with the button below.--%>
                                                <%--                                                        </div>--%>
                                                <%--                                                    </div>--%>
                                                <%--                                                </div>--%>
                                                <%--                                            </div>--%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center">
        <nav aria-label="Page navigation text-center">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href=/bumblebee/khuyen-mai/list?p1=0">Previous</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="/bumblebee/khuyen-mai/list?p1=${page.number-1}"><<</a></li>
                <li class="page-item"><a class="page-link"
                                         href="/bumblebee/khuyen-mai/list?p1=${page.number+1}">>></a></li>
                <li class="page-item"><a class="page-link"
                                         href="/bumblebee/khuyen-mai/list?p1=${page.totalPages-1}">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>

<div id="Paris" class="tabcontent">
    <div>
        <h1 style="text-align: center; font-family: Nunito; margin-bottom: 50px;">Sản phẩm khuyến mại</h1>
    </div>

    <input id="myInput2" placeholder="Tìm kiếm sản phẩm">

    <div class="table">
        <table>
            <thead>
            <tr>
                <th>STT</th>
                <th>Tên sản phẩm</th>
                <th>Mã khuyến mại</th>
                <th>Giá trị khuyến mại</th>
                <th>Giá bán</th>
                <th>Giá khuyến mại</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Trạng thái</th>
            </tr>
            </thead>

            <tbody id="myTable2">
            <c:forEach items="${listCTKM.content}" var="ctkm" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${ctkm.ctsp.sanPham.tenSanPham}</td>
                    <td>${ctkm.khuyenMai.maKhuyenMai}</td>
                    <td>${ctkm.khuyenMai.giaTri}</td>
                    <td>${ctkm.ctsp.giaBan}</td>
                    <td>${ctkm.ctsp.giaBan - (ctkm.khuyenMai.giaTri * ctkm.ctsp.giaBan)} </td>
                    <td>${ctkm.ngayBatDau}</td>
                    <td>${ctkm.ngayKetThuc}</td>
                    <td>
                        <c:if test="${ctkm.trangThai == 0}">Hoạt động</c:if>
                        <c:if test="${ctkm.trangThai == 1}">Không hoạt động</c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

    <div class="text-center">
        <nav aria-label="Page navigation text-center">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link" href=/bumblebee/khuyen-mai/list?p2=0">Previous</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="/bumblebee/khuyen-mai/list?p2=${listCTKM.number-1}"><<</a></li>
                <li class="page-item"><a class="page-link"
                                         href="/bumblebee/khuyen-mai/list?p2=${listCTKM.number+1}">>></a></li>
                <li class="page-item"><a class="page-link"
                                         href="/bumblebee/khuyen-mai/list?p2=${listCTKM.totalPages-1}">Next</a>
                </li>
            </ul>
        </nav>
    </div>

</div>


<script>
    function openCity(evt, cityName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    $("#myInput1").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable1 tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#myInput2").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable2 tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#myInput3").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable3 tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    function checkValidation() {
        if (${ngayBatDau == null}) {
            alert("Ngày bắt đầu không dc trống")
        } else if (${ngayKetThuc == null}) {
            alert("Ngày kết thúc không dc trống")
        } else if (${idListCartDetail == null}) {
            alert("Chưa chọn sản phẩm")
        } else {
            return "http://localhost:8080//bumblebee/khuyen-mai/them-san-pham/${idKM}";
        }
    }

    let isModalOpen = false;

    function openModal() {
        isModalOpen = true;
        // Hiển thị modal
    }

    function closeModal() {
        if (isModalOpen) {
            // Đóng modal
            isModalOpen = false;
        }
    }

    document.getElementById('btnSubmit').addEventListener('click', function (event) {
        // if (isModalOpen) {
        //     closeModal();
        // }
        var ngayBatDau = document.getElementById("ngayBatDau").value;
        var ngayKetThuc = document.getElementById("ngayKetThuc").value;
        var idListCartDetail = document.getElementById("idListCartDetail").value;
        if (ngayBatDau == "") {
            event.preventDefault();
            alert("Ngày bắt đầu không được trống");
        }
        if (ngayKetThuc == "") {
            event.preventDefault();
            alert("Ngày kết thúc không được trống");
        }
        if (idListCartDetail == "") {
            event.preventDefault();
            alert("Chưa chọn sản phẩm nào");
        } else {
            return "http://localhost:8080//bumblebee/khuyen-mai/them-san-pham/${idKM}";
        }

    });


</script>
</body>


