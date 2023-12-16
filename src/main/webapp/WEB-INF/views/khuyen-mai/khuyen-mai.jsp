<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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


    #myInput3 {
        width: 250px;
        height: 30px;
        border: 2px solid #37517E;
        border-radius: 20px;
        margin: 0px 0px 20px 10px;
    }


    .ui.form input[type=search] {
        background: #fff;
        border: 2px solid #37517E;
        border-radius: 20px;
        width: 400px;
    }

    .ui input {
        border-radius: 20px;
    }

    .ui.table > tbody > tr > td {
        font-size: 16px;
    }


    .btnSearch {
        background-color: #37517E;
        width: 100px;
        height: 25px;
        border-radius: 5px;
        color: #FFFFFF;
        border: 0px;
    }

    .error {
        font-size: 15px;
        color: crimson;
    }


</style>
<body>
<div class="container">

    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link ${donHang == 'khuyen-mai' ? 'active' : ''}" aria-current="page"
               href="/bumblebee/khuyen-mai/list">Quản lý khuyến mại</a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${donHang == 'khuyen-mai' ? 'active' : ''}" href="/bumblebee/san-pham-khuyen-mai/list">Sản
                phẩm khuyến mại</a>
        </li>
    </ul>


    <div>
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
        <form:form action="/bumblebee/khuyen-mai/search" method="post" modelAttribute="searchForm">
            <div class="row" style="margin-bottom: 20px;">
                <div class="col-lg-1"></div>
                <div class="col-lg-2">
                    <form:select path="donVi">
                        <option value="">-- Lọc đơn vị --</option>
                        <form:option value="VNĐ">VNĐ</form:option>
                        <form:option value="%">%</form:option>
                    </form:select>
                </div>
                <div class="col-lg-2">
                    <form:select path="trangThai">
                        <option value="">-- Lọc trạng thái --</option>
                        <form:option value="0">Hoạt động</form:option>
                        <form:option value="1">Không hoạt động</form:option>
                    </form:select>
                        <%--    <form:select type="text" path="trangThai">--%>
                        <%--        <form:option value="">-- Lọc trạng thái --</form:option>--%>
                        <%--        <form:options items="${dsTrangThai}"/>--%>
                        <%--    </form:select>--%>
                </div>
                <div class="col-lg-1">
                    <button type="submit" class="btnSearch">Tìm</button>
                </div>
            </div>
        </form:form>


        <table id="tableKhuyenMai" class="ui celled table" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>STT</th>
                <th>Mã khuyến mãi</th>
                <th>Tên khuyến mãi</th>
                <th>Giá trị</th>
                <th>Đơn vị</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Trạng thái</th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody id="myTable1">
            <c:forEach items="${page}" var="km" varStatus="i">
                <tr>
                    <td>${i.index + 1}</td>
                    <td>${km.maKhuyenMai}</td>
                    <td>${km.tenKhuyenMai}</td>
                    <td>
                        <fmt:formatNumber>${km.giaTri}</fmt:formatNumber>
                    </td>
                    <td>${km.donVi}</td>
                    <td>${km.ngayBatDau}</td>
                    <td>${km.ngayKetThuc}</td>
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
                        <c:if test="${km.trangThai == 0}">
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#Modal_${i.index}"
                                    style="border-radius: 20px;">Chọn
                            </button>
                            <!-- Modal -->
                            <form action="/bumblebee/khuyen-mai/them-san-pham/${km.id}" method="post"
                                  id="themSanPhamKhuyenMai">
                                <div class="modal fade" id="Modal_${i.index}" tabindex="-1"
                                     aria-labelledby="exampleModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-xl modal-dialog-scrollable">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel">Danh sách sản
                                                    phẩm</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-lg-4">
                                                        <input id="myInput3_${km.id}"
                                                               onclick="timKiemSanPham('${km.id}')"
                                                               placeholder="Tìm kiếm sản phẩm">
                                                    </div>
                                                </div>
                                                <span id="spnError" class="error" style="display: none"
                                                >Please select at-least one Fruit.</span
                                                >
                                                <div class="container">
                                                    <table id="tableCTSP">
                                                        <thead>
                                                        <tr>
                                                            <th class="col-md-1">
                                                                <input class="checkCart" style="width: 10px;"
                                                                       id="checkAll_${km.id}"
                                                                       type="checkbox"
                                                                       onclick="chkAll('${km.id}')">
                                                            </th>
                                                            <th></th>
                                                            <th scope="col">Tên sản phẩm</th>
                                                            <th scope="col">Số lượng</th>
                                                            <th scope="col">Giá bán</th>
                                                            <th scope="col">Màu sắc</th>
                                                            <th scope="col">Kích cỡ</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="myTable3">
                                                        <c:forEach items="${listCTSP}" var="sp" varStatus="i">
                                                            <tr>
                                                                <td>
                                                                    <c:if test="${not empty sp.ctkm}">
                                                                        <c:set var="allTrangThai1" value="false"/>
                                                                        <c:forEach var="ctkm" items="${sp.ctkm}">
                                                                            <c:if test="${ctkm.trangThai == 0}">
                                                                                <input disabled checked type="checkbox">
                                                                                <c:set var="allTrangThai1"
                                                                                       value="true"/>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                        <c:if test="${allTrangThai1 eq false}">
                                                                            <input
                                                                                    id="checkBoxSP"
                                                                                    class="checkCart"
                                                                                    type="checkbox"
                                                                                    name="idListCartDetail"
                                                                                    value="${sp.id}">
                                                                            <c:set var="allTrangThai1"
                                                                                   value="true"/>
                                                                        </c:if>
                                                                    </c:if>
                                                                    <c:if test="${empty sp.ctkm}">
                                                                        <input
                                                                                id="checkBoxSP"
                                                                                class="checkCart"
                                                                                type="checkbox"
                                                                                name="idListCartDetail"
                                                                                value="${sp.id}">
                                                                    </c:if>
                                                                </td>
                                                                <td><img src="../../../uploads/${sp.hinhAnhs.tenanh}"
                                                                         width="50px" height="50px"></td>
                                                                <td>${sp.sanPham.tenSanPham}</td>
                                                                <td>${sp.soLuong}</td>
                                                                <td>
                                                                    <fmt:formatNumber>${sp.giaBan}</fmt:formatNumber> đ
                                                                </td>
                                                                <td>${sp.mauSac.ten}</td>
                                                                <td>${sp.kichCo.size}</td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary"
                                                        onclick="return themKhuyenMai()"
                                                >Thêm
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${km.trangThai == 1}">
                            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#${km.id}"
                                    style="border-radius: 20px;" disabled>Chọn
                            </button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>
<script>

    const checkbox = document.getElementById('checkBoxSP');
    var checkboxes = document.querySelectorAll('.checkCart');


    function chkAll(idKM) {
        var checkedAllCheckbox = document.getElementById('checkAll_' + idKM);
        checkboxes.forEach(function (checkbox) {
            checkbox.checked = checkedAllCheckbox.checked;
        });
    }

    // checkedAllCheckbox.addEventListener('click', function () {
    //
    // });

    function themKhuyenMai() {
        var numberOfCheckedCheckboxes = 0;
        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                numberOfCheckedCheckboxes++;
            }
        });
        if (numberOfCheckedCheckboxes === 0) {
            alert("chọn sản phẩm để thêm khuyến mại");
            return false;
        } else {
            return true;
        }
    }

    $(document).ready(function () {
        $('#tableKhuyenMai').DataTable({
            info: false,
            language: {
                search: "",
                searchPlaceholder: "Tìm kiếm ......",
            }
        });
    });
    $(document).ready(function () {
        $('#tableDanhSachSanPham').DataTable({
            info: false,
            language: {
                search: "",
                searchPlaceholder: "Tìm kiếm ......",
            }
        });
    });


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
    var idKM = null;
    var inPut = null;

    function timKiemSanPham(idkm) {
        idKM = idkm;
        console.log("click" + idKM)
        inPut = document.getElementById("myInput3_" + idKM);
        if (inPut){
            inPut.addEventListener("keyup", function () {
                console.log("search")
                var value = this.value.toLowerCase();
                $("#myTable3 tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });

        }
    }



    function filterTable() {
        // Get the start and end date values
        var startDate = new Date(document.getElementById("startDate").value);
        var endDate = new Date(document.getElementById("endDate").value);

        // Get all rows in the table
        var rows = document.getElementById("tableKhuyenMai").getElementsByTagName("tbody")[0].getElementsByTagName("tr");

        // Loop through all rows and hide/show them based on the date range
        for (var i = 0; i < rows.length; i++) {
            var dateValue = new Date(rows[i].getElementsByTagName("td")[5].innerText);

            if (dateValue >= startDate && dateValue <= endDate) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }

</script>
<script>
    // Initialization for ES Users
    import {Tab, initMDB} from "mdb-ui-kit";

    initMDB({Tab});
</script>
</body>


