<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</br>
<style>
    .container-sm {
        margin: 0;
        padding: 0;
        background-color: #D9D9D9;

    }

    .item-right {
        padding-top: 20px;
    }

    #but {
        background-color: #1492AE;
        border-radius: 30px;
        border: none;
        width: 100px;
    }


    .left {
        width: 45%;
        padding-left: 20px;
    }

    .item {
        float: left;
    }

    .inp input {
        border: none;
        width: 280px;
        border-radius: 15px;
    }

    .form {
        display: flex;
        justify-content: space-between;
    }


    .img input {
        background-color: white;
    }

    .item input {
        margin-top: 20px;
    }


    .col-lg-6 select {
        height: 30px;
        margin-top: 20px;
        width: 550px;
        border: none

    }

    .form-label {
        padding-left: 30px;
    }
</style>
<div class="container-lg">

    <h2 class="h3 text-gray-900 mb-4">Thêm/Sửa Chi Tiết Sản Phẩm</h2>
    <form:form action="${action}" modelAttribute="sanpham" enctype="multipart/form-data"
               id="sanPhamForm">
        <form:input path="id" class="form-control" type="hidden"/>
        <div class="row ">
            <div class="col-lg-6 ">
                <div class="item inp">
                    <label class="form-label">Tên sản phẩm <span style="color: red">*</span> : </label>
                    <form:input path="sanPham" class="form-control form-control-user" cssStyle="margin-left: 12px"
                                type="hidden"/>

                    <form:input path="sanPham.tenSanPham" value="${tensp}" cssStyle="margin-left: 12px"
                                readonly="true"/>
                </div>
                    <%--                <div class="item inp">--%>
                    <%--                    <label class="form-label">Giá Bán <span style="color: red">*</span> : </label>--%>
                    <%--                    <form:input path="giaBan" type="number" min="1" cssStyle="margin-left: 50px"/>--%>
                    <%--                    <p><form:errors path="giaBan" cssStyle="color: crimson"/></p>--%>
                    <%--                </div>--%>

                    <%--                <div class="item inp">--%>
                    <%--                    <label class="form-label">Số lượng <span style="color: red">*</span> : </label>--%>
<%--                                        <form:input path="soLuong" type="number" min="1" cssStyle="margin-left: 44px"/>--%>
<%--                                        <p><form:errors path="soLuong" cssStyle="color: crimson"/></p>--%>
                    <%--                </div>--%>
                <div class="item">
                    <label class="form-label">Mô tả <span style="color: red">*</span> : </label>
                    <form:textarea cols="50" rows="5" path="moTaCT"
                                   cssStyle="border: none; width: 280px;height: 100px;border-radius: 15px;margin-left: 65px;margin-top: 20px"/>
                    <p><form:errors path="moTaCT" cssStyle="color: crimson"/></p>
                </div>
                <br>
                    <%--                <div class="item form-check-inline">--%>
                    <%--                    <br>--%>
                    <%--                    <label class="form-label">Trạng Thái <span style="color: red">*</span> :</label>--%>
                    <%--                    <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="form-check-input"--%>
                    <%--                                       cssStyle="margin-right: 15px;margin-left: 35px;"/>--%>
                    <%--                    <p><form:errors path="trangThai" cssStyle="color: crimson"/></p>--%>
                    <%--                </div>--%>
            </div>
            <div class="col-lg-6 ">
                <div class="item-right">
                    <label class="form-label" cssStyle="margin-right: 15px;margin-left: 35px;">Loại giầy <span
                            style="color: red">*</span> : </label>
                    <form:select type="text" id="searchName10" path="loaiGiay">
                        <form:option value="">Chọn loại giầy</form:option>
                        <form:options items="${listLoaiGiay}" itemLabel="tentheloai" itemValue="id"/>
                    </form:select>

                    <a data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                            class="bi bi-plus-circle-fill"></i></a>
                    <div><form:errors path="loaiGiay" cssStyle="color: crimson"/></div>
                </div>
                <div class="item-right">
                    <label class="form-label">Chất liệu <span style="color: red">*</span> : </label>
                    <form:select type="text" id="searchName14" path="chatLieu">
                        <form:option value="">Chọn chất liệu</form:option>
                        <form:options items="${listChatLieu}" itemLabel="ten" itemValue="id"/>
                    </form:select>

                    <a data-bs-toggle="modal" data-bs-target="#exampleModal4"><i
                            class="bi bi-plus-circle-fill"></i></a>
                    <div><form:errors path="chatLieu" cssStyle="color: crimson"/></div>
                </div>
                <div class="item-right">
                    <label class="form-label" cssStyle="margin-right: 15px;margin-left: 35px;">Đế giầy <span
                            style="color: red">*</span> : </label>
                    <form:select type="text" id="searchName13" path="deGiay">
                        <form:option value="">Chọn đế giầy</form:option>
                        <form:options items="${listDeGiay}" itemLabel="loaiDe" itemValue="id"/>
                    </form:select>

                    <a data-bs-toggle="modal" data-bs-target="#exampleModal5"><i
                            class="bi bi-plus-circle-fill"></i></a>
                    <div><form:errors path="deGiay" cssStyle="color: crimson"/></div>
                </div>
            </div>
        </div>
        <br>
        <h1 class="h4 text-gray-900 mb-4">Biến thể của sản phẩm: </h1>
        <div class="row">
            <div class="col-6">
                <label class="form-label">Kích cỡ <span style="color: red">*</span> : </label>
                <form:select class="js-example-basic-multiple" name="states[]" multiple="multiple" id="searchName11"
                             path="kichCo" cssClass=".searchName1" onChange="updateTable()">
                    <form:option value="">Chọn kích cỡ</form:option>
                    <form:options items="${listKichCo}" itemLabel="size" itemValue="id"/>
                </form:select>

                <a data-bs-toggle="modal" data-bs-target="#exampleModal2"><i
                        class="bi bi-plus-circle-fill"></i></a>
                <div><form:errors path="kichCo" cssStyle="color: crimson"/></div>
            </div>

            <div class="col-6">
                <label class="form-label">Màu sắc <span style="color: red">*</span> : </label>
                <form:select class="js-example-basic-multiple" name="states[]" multiple="multiple" id="searchName12"
                             path="mauSac" cssClass=".searchName2" onChange="updateTable()">
                    <form:option value="">Chọn màu sắc</form:option>
                    <form:options items="${listMau}" itemLabel="ten" itemValue="id"/>
                </form:select>

                <a data-bs-toggle="modal" data-bs-target="#exampleModal3"><i
                        class="bi bi-plus-circle-fill"></i></a>
                <div><form:errors path="mauSac" cssStyle="color: crimson"/></div>

            </div>
        </div>
        <br>
        <input type="hidden" id="submitStatus" name="submitStatus" value="${submitStatus}">

        <%--    22                onclick="return confirm('Bạn có chắc muốn thực hiện ?');"--%>
        <div class="text-right" style="padding-bottom: 20px">
            <button type="submit" id="but" class="btn btn-success" onclick=" submitForm()">
                Submit
            </button>
        </div>
        <c:if test="${not empty sanpham}">

            <table class="table table-bordered">

                <tr class="table-active">
                    <th>STT</th>
                    <th>Màu sắc</th>
                    <th>Kích cỡ</th>
                    <th>Giá Bán</th>
                    <th>Số lượng</th>
                    <th>Trạng Thái</th>
<%--                    <th>Hình Ảnh</th>--%>
                    <th>Action</th>
                </tr>

                <tbody id="myTable">

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty sanpham}">
            <td colspan="8" class="text-center">Không có sản phẩm.</td>
        </c:if>

    </form:form>

    <%--    modal--%>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="lg" method="post" action="${action4}">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Thêm Loại Giầy</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="input" style="">
                            <p>Mã:</p>
                            <form:input path="ma" class="form-control"/>
                            <div id="ma-error4" style="color: crimson;"></div>
                            <div id="duplicate4-error4-ma" style="color: crimson;"></div>
                        </div>
                        <form:errors path="ma"/>
                        <div class="input">
                            <p>Loại giày:</p>
                            <form:input path="tentheloai" class="form-control"/>

                        </div>
                        <form:errors path="tentheloai" id="tentheloai "/>
                        <div id="tentheloai-error4" style="color: crimson;"></div>
                        <div id="duplicate4-error4-tentheloai" style="color: crimson;"></div>
                        <br>

                        <div class="mb-3 form-check-inline ">
                            <label>Trạng Thái</label>
                            <form:radiobuttons items="${dsTrangThai}" path="trangthai"
                                               class="form-check-input"/>
                            <form:errors path="trangthai" id="trangthai" cssStyle="color: crimson"/>
                            <div id="trangthai-error4" style="color: crimson;"></div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button onclick="submitFormLoaiGiay()" type="button" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form:form>
            </div>

        </div>
    </div>

    <%--    modal2--%>
    <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="kichco" action="${action2}">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel2">Thêm kích cỡ</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                        <%--                    <form:input path="id" class="form-control" type="hidden"/>--%>
                    <div class="mb-3">
                        <label>Mã Kích Cỡ: </label>

                        <form:input path="maKichCo" class="form-control"/>
                        <span id="maKichCoError" class="text-danger"></span>
                        <form:errors path="maKichCo" cssStyle="color: crimson"/>
                        <div id="maKichCo-error3" style="color: crimson;"></div>
                        <div id="duplicate3-error3-maKichCo" style="color: crimson;"></div>
                    </div>
                        <%--                    <div class="mb-3 form-check-inline">--%>
                        <%--                        <label class="form-label">Giới Tính</label>--%>
                        <%--                        <form:radiobuttons items="${dsGioiTinh}" path="gioiTinh" class="form-check-input"/>--%>
                        <%--                        <form:errors path="gioiTinh" cssStyle="color: crimson"/>--%>
                        <%--                    </div>--%>
                    <div class="mb-3">
                        <label>Size</label>
                        <form:input path="size" class="form-control"/>
                        <form:errors path="size" cssStyle="color: crimson"/>
                        <div id="size-error3" style="color: crimson;"></div>
                        <div id="duplicate3-error3-size" style="color: crimson;"></div>
                    </div>

                    <div class="mb-3 form-check-inline">
                        <label>Trạng Thái</label>
                        <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="form-check-input"/>
                        <form:errors path="trangThai" cssStyle="color: crimson"/>
                        <div id="trangThai-error3" style="color: crimson;"></div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="button" onclick="submitFormKichCo();" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
    <%--    modal3--%>
    <div class="modal fade" id="exampleModal3" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="ms" action="${action3}">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel3">Thêm màu sắc</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label> Mã</label>
                            <form:input class="form-control" path="ma"/>
                            <form:errors path="ma"/>
                            <div id="ma-error2" style="color: crimson;"></div>
                            <div id="duplicate2" style="color: crimson;"></div>
                        </div>
                        <br>
                        <div class="mb-3">
                            <label> Tên</label>
                            <form:input class="form-control" path="ten"/>
                            <form:errors path="ten"/>
                            <div id="ten-error2" style="color: crimson;"></div>
                            <div id="duplicate2-error2-ten" style="color: crimson;"></div>
                        </div>
                        <div class="mb-3 form-check-inline ">
                            <label>Trạng Thái</label>
                            <form:radiobuttons items="${dsTrangThai}" path="tt" class="form-check-input"/>
                            <form:errors path="tt" cssStyle="color: crimson"/>
                            <div id="tt-error2" style="color: crimson;"></div>

                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="button" onclick="submitFormMauSac()" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal4" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="vm" action="${action6}">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel4">Thêm chất liệu</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div id="ma">
                            <label>Mã</label>
                            <form:input path="ma" class="form-control"/> <br/>
                            <form:errors path="ma" cssStyle="color: red"/>
                            <div id="ma-error1" style="color: crimson;"></div>
                            <div id="duplicate1" style="color: crimson;"></div>

                        </div>
                        <div id="ten">
                            <label>Tên</label>
                            <form:input path="ten" class="form-control"/> <br/>
                            <form:errors path="ten" cssStyle="color: red"/>
                            <div id="ten-error1" style="color: crimson;"></div>
                            <div id="duplicate1-error-ten" style="color: crimson;"></div>

                        </div>
                        <div id="tt" class="form-check-inline">
                            <label>Trạng Thái</label>
                            <form:radiobutton class="form-check-input" path="trangThai" value="1"
                                              checked="true"/>HĐ
                            <form:radiobutton class="form-check-input" path="trangThai" value="0"/>Ngưng HĐ
                            <form:errors path="trangThai" cssStyle="color: crimson"></form:errors>
                            <div id="trangThai-error1" style="color: crimson;"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="button" onclick="submitFormChatLieu()" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form:form>
            </div>

        </div>
    </div>
    <%--    modal4--%>
    <div class="modal fade" id="exampleModal5" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form:form modelAttribute="degiay" action="${action5}" id="deGiayForm">

                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel5">Thêm đế giầy</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Mã</label>
                    <div class="mb-3">
                        <form:input type="text" class="form-control" id="inputEmail3" path="ma"/>
                        <form:errors path="ma" cssStyle="color: crimson"></form:errors>
                            ${mess_Ma}
                        <div id="ma-error" style="color: crimson;"></div>
                        <div id="duplicate-error" style="color: crimson;"></div>

                    </div>

                    <label for="inputEmail3" class="col-sm-2 col-form-label">Loại đế</label>
                    <div class="mb-3">
                        <form:input type="text" class="form-control" id="inputEmail3" path="loaiDe"/>
                        <form:errors path="loaiDe" cssStyle="color: crimson"></form:errors>
                        <div id="loaiDe-error" style="color: crimson;"></div>
                        <div id="duplicate-error-loaiDe" style="color: crimson;"></div>

                    </div>

                    <label for="inputEmail3" class="col-sm-2 col-form-label">Trạng thái</label>
                    <div class="mb-3 form-check-inline">
                        <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="form-check-input"/>
                        <form:errors path="trangThai" cssStyle="color: crimson"></form:errors>
                        <div id="trangThai-error" style="color: crimson;"></div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="submitFormDeGiay()">Submit</button>
                    </div>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/dist/toastr.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastr@2.1.4/dist/toastr.min.css"/>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
//sna
<%--<div id="toast" style="display:none;">--%>
<%--    <div class="toast toast__succes">--%>
<%--        <div class="toast__icon">--%>
<%--            <i class="fa-sharp fa-solid fa-circle-check" style="color: #47d864;"></i>--%>
<%--        </div>--%>
<%--        <div class="toast__body">--%>
<%--            <h3 class="toast__title">Success</h3>--%>
<%--            <p class="toast__msg">Thêm vào giỏ hàng thành công</p>--%>
<%--        </div>--%>
<%--        <div class="toast__close">--%>
<%--            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg"--%>
<%--                 viewBox="0 0 16 16">--%>
<%--                <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>--%>
<%--            </svg>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<script>
    function updateTable() {
        // var selectedSizes = $('#searchName11').val() || [];
        // var selectedColors = $('#searchName12').val() || [];
        var selectedSizes = $("#searchName11").select2("data");
        var selectedColors = $("#searchName12").select2("data");

        var table = $('#myTable');
        table.empty();

        var stt = 1;

        selectedSizes.forEach(function (selectedSize) {
            selectedColors.forEach(function (selectedColor) {
                console.log('selectedColor:', selectedColor);
                console.log('selectedSize:', selectedSize);
                var row = '<tr>';
                row += '<td>' + stt + '</td>';
                row += '<td>' + selectedColor.text + '</td>';
                row += '<td>' + selectedSize.text + '</td>';
                row += '<td><input type="number" name="giaBan" required class="form-control"></td>';
                row += '<td><input type="number" name="soLuong" required class="form-control"></td>';
                row += '<td><select class="js-example-basic-single form-control" name="trangThai"><option value="1">Hoạt động</option><option value="0">Không hoạt động</option></select></td>';
                row += '<td><a href="#" class="btn btn-danger btn-circle delete-row" onclick="deleteRow(this)"><i class="fas fa-trash"></i></a></td>';
                row += '</tr>';
                table.append(row);
                stt++;
            });
        });
    }

    function deleteRow(button) {
        var row = button.closest('tr');
        row.remove();
    }

    $(document).ready(function () {
        updateTable();

        $('#searchName11, #searchName12').on('change', function () {
            updateTable();
        });
    });
</script>
//hdsk
<script>
    function clearErrors() {
        $("#error-message").empty();
        $("div[id$='-error']").empty();
        $("#duplicate-error").empty();
        $("#ma-error").empty();
        $("#trangThai-error").empty();
        $("#loaiDe-error").empty();
        $("#ten").empty();
        $("#duplicate-error-loaiDe").empty();
        $("#error-message").empty();
        $("div[id$='-error1']").empty();
        $("#duplicate1").empty();
        $("#ma-error1").empty();
        $("#ten-error1").empty();
        $("#trangThai-error1").empty();
        $("#duplicate1-error-ten").empty();
        $("#error-message").empty();
        $("div[id$='-error2']").empty();
        $("#duplicate2").empty();
        $("#ma-error2").empty();
        $("#ten-error2").empty();
        $("#tt-error2").empty();
        $("#duplicate2-error-ten").empty();
        $("#error-message").empty();
        $("div[id$='-error3']").empty();
        $("#duplicate3").empty();
        $("#trangThai-error3").empty();
        $("#maKichCo-error3").empty();
        $("#size-error3").empty();
        $("#duplicate3-error3-size").empty();
        $("#duplicate3-error3-maKichCo").empty();
        $("#error-message").empty();
        $("div[id$='-error4']").empty();
        $("#ma-error4").empty();
        $("#tentheloai-error4").empty();
        $("#duplicate4-error4-tentheloai").empty
    }

    //de giay
    function submitFormDeGiay() {
        var formData = $("#deGiayForm").serialize();
        $.ajax({
            type: "POST",
            url: "${action5}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal5").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal5').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#deGiayForm')[0].reset();
                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Your data has been saved",
                        showConfirmButton: false,
                        timer: 1500
                    });
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
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error']").empty();
        $("#duplicate-error").empty();
        $("#ma-error").empty();
        $("#trangThai-error").empty();
        $("#loaiDe-error").empty();
        $("#ten").empty();
        $("#duplicate-error-loaiDe").empty();

        errors.forEach(function (error) {
            $("#error-message").append('<div style="color: crimson;">' + error + '</div>');
        });


        errors.forEach(function (error) {
            var fieldName = error.split(":")[0].trim();
            var errorMessage = error.split(":")[1].trim();
            $("#" + fieldName + "-error").append('<div style="color: crimson;">' + errorMessage + '</div>');
        });


        if (response.status === "error" && response.field === "ma") {
            $("#duplicate-error").append('<div style="color: crimson;">' + response.message + '</div>');
        }
        if (response.status === "error" && response.field === "loaiDe") {
            $("#duplicate-error-loaiDe").append('<div style="color: crimson;">' + response.message + '</div>');
        }
    }

    // chat lieu
    function submitFormChatLieu() {
        var formData = $("#vm").serialize();
        $.ajax({
            type: "POST",
            url: "${action6}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal4").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal4').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#vm')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Your data has been saved",
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    displayErrorsChatLieu(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsChatLieu(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error1']").empty();
        $("#duplicate1").empty();
        $("#ma-error1").empty();
        $("#ten-error1").empty();
        $("#trangThai-error1").empty();
        $("#duplicate1-error-ten").empty();

        errors.forEach(function (error1) {
            $("#error-message").append('<div style="color: crimson;">' + error1 + '</div>');
        });

        errors.forEach(function (error) {
            var fieldName1 = error.split(":")[0].trim();
            var errorMessage1 = error.split(":")[1].trim();
            $("#" + fieldName1 + "-error1").append('<div style="color: crimson;">' + errorMessage1 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error1" && response.field === "ma") {
                $("#duplicate1").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error1" && response.field === "ten") {
                $("#duplicate1-error-ten").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

    //mau sac
    function submitFormMauSac() {
        var formData = $("#ms").serialize();
        $.ajax({
            type: "POST",
            url: "${action3}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal3").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal3').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#ms')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Your data has been saved",
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    displayErrorsMauSac(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsMauSac(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error2']").empty();
        $("#duplicate2").empty();
        $("#ma-error2").empty();
        $("#ten-error2").empty();
        $("#tt-error2").empty();
        $("#duplicate2-error-ten").empty();

        errors.forEach(function (error2) {
            $("#error-message").append('<div style="color: crimson;">' + error2 + '</div>');
        });

        errors.forEach(function (error) {
            var fieldName2 = error.split(":")[0].trim();
            var errorMessage2 = error.split(":")[1].trim();
            $("#" + fieldName2 + "-error2").append('<div style="color: crimson;">' + errorMessage2 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error2" && response.field === "ma") {
                $("#duplicate2-error-ma").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error2" && response.field === "ten") {
                $("#duplicate2-error-ten").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

    //kich co
    function submitFormKichCo() {
        var formData = $("#kichco").serialize();
        $.ajax({
            type: "POST",
            url: "${action2}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal2").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal2').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#kichco')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Your data has been saved",
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    displayErrorsKichCo(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsKichCo(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error3']").empty();
        $("#duplicate3").empty();
        $("#trangThai-error3").empty();
        $("#maKichCo-error3").empty();
        $("#size-error3").empty();
        $("#duplicate3-error3-size").empty();
        $("#duplicate3-error3-maKichCo").empty();

        errors.forEach(function (error3) {
            $("#error-message").append('<div style="color: crimson;">' + error3 + '</div>');
        });

        errors.forEach(function (error3) {
            var fieldName3 = error3.split(":")[0].trim();
            var errorMessage3 = error3.split(":")[1].trim();
            $("#" + fieldName3 + "-error3").append('<div style="color: crimson;">' + errorMessage3 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error3" && response.field === "ma") {
                $("#duplicate3-error3-makichCo").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error3" && response.field === "size") {
                $("#duplicate3-error3-size").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

    //loai giay
    function submitFormLoaiGiay() {
        var formData = $("#lg").serialize();
        $.ajax({
            type: "POST",
            url: "${action4}",
            data: formData,
            success: function (response) {
                if (response.status === "success") {
                    // Tắt modal nếu submit thành công
                    $("#exampleModal").modal("hide");
                    $('body').removeClass('modal-open');
                    $('.modal-backdrop').remove();
                    $('#exampleModal').on('hidden.bs.modal', function (e) {
                        clearErrors();
                        $('#lg')[0].reset();

                    });
                    Swal.fire({
                        position: "center",
                        icon: "success",
                        title: "Your data has been saved",
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    displayErrorsLoaiGiay(response.errors);
                }
            },
            error: function () {
                console.error("Error submitting form");
            }
        });
    }

    function displayErrorsLoaiGiay(errors) {
        // Xóa thông báo lỗi cũ
        $("#error-message").empty();
        $("div[id$='-error4']").empty();
        $("#ma-error4").empty();
        $("#tentheloai-error4").empty();
        $("#duplicate4-error4-tentheloai").empty();
        $("#duplicate4-error4-ma").empty();

        errors.forEach(function (error4) {
            $("#error-message").append('<div style="color: crimson;">' + error4 + '</div>');
        });

        errors.forEach(function (error4) {
            var fieldName4 = error4.split(":")[0].trim();
            var errorMessage4 = error4.split(":")[1].trim();
            $("#" + fieldName4 + "-error4").append('<div style="color: crimson;">' + errorMessage4 + '</div>');
        });

        if (errors.length > 0) {
            var response = errors[0]; // Lấy thông báo lỗi đầu tiên để kiểm tra trường và giá trị
            if (response.status === "error4" && response.field === "ma") {
                $("#duplicate4-error4-ma").append('<div style="color: crimson;">' + response.message + '</div>');
            }
            if (response.status === "error4" && response.field === "tentheloai") {
                $("#duplicate4-error4-tentheloai").append('<div style="color: crimson;">' + response.message + '</div>');
            }
        }
    }

</script>

<div class="text-center" style="color: crimson">${mess}</div>
<script src="../../../js/chi_tiet_san_pham/chi_tiet_san_pham.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>

<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<!-- Include SweetAlert2 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<script>

    $(document).ready(function () {
        $('.js-example-basic-multiple').select2();
    });
</script>
