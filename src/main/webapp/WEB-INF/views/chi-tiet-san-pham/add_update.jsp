<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

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
        border-radius: 0;
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

    .ite {
        float: left;
    }


    .inp input {
        border: none;
        width: 280px;
        border-radius: 15px;
    }

    .right {
        width: 27%;
        margin-right: 150px;
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


    .item-right select {
        height: 30px;
        margin-top: 20px;
        width: 150px;
        border: none

    }

</style>
<div class="container-sm">

    <h2 style="text-align: center;padding-top: 20px;margin-bottom: 20px">Thêm/Sửa Chi Tiết Sản Phẩm</h2>
    <form:form action="${action}" modelAttribute="sanpham" cssClass="text-center" enctype="multipart/form-data">
        <form:input path="id" class="form-control" type="hidden"/>
        <div class="form">
            <div class="left">
                <div class="item inp">
                    <label class="form-label">Tên sản phẩm: </label>
                    <form:input path="sanPham" class="form-control" type="hidden"/>

                    <form:input path="sanPham.tenSanPham" value="${tensp}" readonly="true"/>
                </div>
                <div class="item inp">
                    <label class="form-label">Giá Bán: </label>
                    <form:input path="giaBan" cssStyle="margin-left: 50px"/>
                    <form:errors path="giaBan" cssStyle="color: crimson"/>
                </div>

                <div class="item inp">
                    <label class="form-label">Số lượng: </label>
                    <form:input path="soLuong" cssStyle="margin-left: 44px"/>
                    <form:errors path="soLuong" cssStyle="color: crimson"/>
                </div>
                <div class="item">
                    <label class="form-label">Mô tả: </label>
                    <form:textarea cols="50" rows="5" path="moTaCT"
                                   cssStyle="border: none; width: 280px;height: 100px;border-radius: 15px;margin-left: 65px;margin-top: 20px"/>
                    <form:errors path="moTaCT" cssStyle="color: crimson"/>
                </div>

                <div class="item form-check-inline">
                    <label class="form-label">Trạng Thái</label>
                    <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="form-check-input"
                                       cssStyle="margin-right: 15px;margin-left: 20px;"/>
                    <form:errors path="trangThai" cssStyle="color: crimson"/>
                </div>
            </div>
            <div class="right ">
                <div class="item-right">
                    <label class="form-label">Loại giầy: </label>
                    <form:select type="text" id="searchName10" path="loaiGiay">
                        <form:option value="">Chọn loại giầy</form:option>
                        <form:options items="${listLoaiGiay}" itemLabel="tentheloai" itemValue="id"/>
                    </form:select>
                    <span> <form:errors path="loaiGiay" cssStyle="color: crimson"/></span>
                    <a data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                            class="bi bi-plus-circle-fill"></i></a>

                </div>

                <div class="item-right">
                    <label class="form-label">Kích cỡ: </label>
                    <form:select type="text" id="searchName11" path="kichCo" cssClass=".searchName1">
                        <form:option value="">Chọn kích cỡ</form:option>
                        <form:options items="${listKichCo}" itemLabel="size" itemValue="id"/>
                    </form:select>
                    <span> <form:errors path="kichCo" cssStyle="color: crimson"/></span>
                    <a data-bs-toggle="modal" data-bs-target="#exampleModal2"><i
                            class="bi bi-plus-circle-fill"></i></a>
                </div>
                <div class="item-right">
                    <label class="form-label">Màu sắc: </label>
                    <form:select type="text" id="searchName12" path="mauSac" cssClass=".searchName2">
                        <form:option value="">Chọn màu sắc</form:option>
                        <form:options items="${listMau}" itemLabel="ten" itemValue="id"/>
                    </form:select>
                    <span> <form:errors path="mauSac" cssStyle="color: crimson"/></span>
                    <a data-bs-toggle="modal" data-bs-target="#exampleModal3"><i
                            class="bi bi-plus-circle-fill"></i></a>

                </div>
                <div class="item-right">
                    <label class="form-label">Chất liệu: </label>
                    <form:select type="text" id="searchName14" path="chatLieu">
                        <form:option value="">Chọn chất liệu</form:option>
                        <form:options items="${listChatLieu}" itemLabel="ten" itemValue="id"/>
                    </form:select>
                    <span> <form:errors path="chatLieu" cssStyle="color: crimson"/></span>
                    <a data-bs-toggle="modal" data-bs-target="#exampleModal4"><i
                            class="bi bi-plus-circle-fill"></i></a>
                </div>
                <div class="item-right">
                    <label class="form-label">Đế giầy: </label>
                    <form:select type="text" id="searchName13" path="deGiay" cssClass=".searchName4">
                        <form:option value="">Chọn đế giầy</form:option>
                        <form:options items="${listDeGiay}" itemLabel="loaiDe" itemValue="id"/>
                    </form:select>
                    <span> <form:errors path="deGiay" cssStyle="color: crimson"/></span>

                    <a data-bs-toggle="modal" data-bs-target="#exampleModal5"><i
                            class="bi bi-plus-circle-fill"></i></a>
                </div>
            </div>
        </div>
        <div class="text-center" style="padding-bottom: 20px">
            <button type="submit" id="but" class="btn btn-success"
                    onclick="return confirm('Bạn có chắc muốn thực hiện ?');">
                Submit
            </button>
        </div>
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
                            <div style="color: red" id="errorMa"></div>
                        </div>
                        <form:errors path="ma"/>
                        <div style="margin-left: 10px;color: red">${errorMa}</div>
                        <div class="input">
                            <p>Loại giày:</p>
                            <form:input path="tentheloai" class="form-control"/>

                        </div>
                        <form:errors path="tentheloai" id="tentheloai "/>
                        <div style="margin-left: 10px;color: red">${errorTen}</div>
                        <div class="mb-3 form-check-inline ">
                            <label class="form-label">Trạng Thái</label>
                            <form:radiobuttons items="${dsTrangThai}" path="trangthai"
                                               class="form-check-input"/>
                            <form:errors path="trangthai" id="trangthai" cssStyle="color: crimson"/>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button id="submitButton" type="button" class="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </form:form>
            </div>

        </div>
    </div>
    <%--    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
    <%--        <div class="modal-dialog">--%>
    <%--            <div class="modal-content">--%>
    <%--                <form:form modelAttribute="lg" method="post" action="${action4}">--%>
    <%--                    <div class="modal-header">--%>
    <%--                        <h1 class="modal-title fs-5" id="exampleModalLabel">Thêm Loại Giầy</h1>--%>
    <%--                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
    <%--                    </div>--%>
    <%--                    <div class="modal-body">--%>
    <%--                        <div class="input" style="">--%>
    <%--                            <p>Mã:</p>--%>
    <%--                            <form:input path="ma" class="form-control"/>--%>
    <%--                            <div style="color: red" id="errorMa"></div>--%>
    <%--                        </div>--%>
    <%--                        <div class="input">--%>
    <%--                            <p>Loại giày:</p>--%>
    <%--                            <form:input path="tentheloai" class="form-control"/>--%>
    <%--                            <div style="color: red" id="errorTen"></div>--%>
    <%--                        </div>--%>
    <%--                        <div class="mb-3 form-check-inline">--%>
    <%--                            <label class="form-label">Trạng Thái</label>--%>
    <%--                            <form:radiobuttons items="${dsTrangThai}" path="trangthai" class="form-check-input"/>--%>
    <%--                            <div style="color: crimson" id="errorTrangThai"></div>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                    <div class="modal-footer">--%>
    <%--                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
    <%--                        <button type="submit" class="btn btn-primary">Submit</button>--%>
    <%--                    </div>--%>
    <%--                </form:form>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>
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
                        <label class="form-label">Mã Kích Cỡ: </label>

                        <form:input path="maKichCo" class="form-control"/>
                        <span id="maKichCoError" class="text-danger"></span>
                        <form:errors path="maKichCo" cssStyle="color: crimson"/>
                    </div>
                    <div class="mb-3 form-check-inline">
                        <label class="form-label">Giới Tính</label>
                        <form:radiobuttons items="${dsGioiTinh}" path="gioiTinh" class="form-check-input"/>
                        <form:errors path="gioiTinh" cssStyle="color: crimson"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Size</label>
                        <form:input path="size" class="form-control"/>
                        <form:errors path="size" cssStyle="color: crimson"/>
                    </div>

                    <div class="mb-3 form-check-inline">
                        <label class="form-label">Trạng Thái</label>
                        <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="form-check-input"/>
                        <form:errors path="trangThai" cssStyle="color: crimson"/>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="submit" class="btn btn-primary">
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
                        </div>
                        <br>
                        <div class="mb-3">
                            <label> Tên</label>
                            <form:input class="form-control" path="ten"/>
                            <form:errors path="ten"/>
                        </div>
                        <div class="mb-3 form-check-inline ">
                            <label class="form-label">Trạng Thái</label>
                            <form:radiobuttons items="${dsTrangThai}" path="tt" class="form-check-input"/>
                            <form:errors path="tt" cssStyle="color: crimson"/>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="submit" class="btn btn-primary">
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
                        </div>
                        <div id="ten">
                            <label>Tên</label>
                            <form:input path="ten" class="form-control"/> <br/>
                            <form:errors path="ten" cssStyle="color: red"/>
                        </div>
                        <div id="tt" class="form-check-inline">
                            <label>Trạng Thái</label>
                            <form:radiobutton class="form-check-input" path="trangThai" value="1"
                                              checked="true"/>HĐ
                            <form:radiobutton class="form-check-input" path="trangThai" value="0"/>Ngưng HĐ
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                        </button>
                        <button type="submit" class="btn btn-primary">
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
                            <%--                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close--%>
                            <%--                        </button>--%>
                            <%--                        <button type="submit" class="btn btn-primary">--%>
                            <%--                            Submit--%>
                            <%--                        </button>--%>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="submitForm()">Submit</button>
                    </div>
                    </form:form>
                </div>

            </div>
        </div>
    </div>
</div>
<script>
    function submitForm() {
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
                        // Lấy đối tượng form trong modal và gọi phương thức reset()
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
        $("#duplicate-error-ma").empty();
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
            $("#duplicate-error-ma").append('<div style="color: crimson;">' + response.message + '</div>');
        }
        if (response.status === "error" && response.field === "loaiDe") {
            $("#duplicate-error-loaiDe").append('<div style="color: crimson;">' + response.message + '</div>');
        }
    }
    <%--function submitForm() {--%>
    <%--    var formData = $("#deGiayForm").serialize();--%>

    <%--    $.ajax({--%>
    <%--        type: "POST",--%>
    <%--        url: "${action5}",--%>
    <%--        data: formData,--%>
    <%--        success: function (response) {--%>
    <%--            if (response.status === "success") {--%>
    <%--                // Tắt modal nếu submit thành công--%>
    <%--                $("#exampleModal5").modal("hide");--%>
    <%--                $('body').removeClass('modal-open');--%>
    <%--                $('.modal-backdrop').remove();--%>
    <%--            } else {--%>
    <%--                displayErrors(response.errors, response.field);--%>
    <%--            }--%>
    <%--        },--%>
    <%--        error: function () {--%>
    <%--            console.error("Error submitting form");--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>

    <%--function displayErrors(errors, errorField) {--%>
    <%--    // Xóa thông báo lỗi cũ--%>
    <%--    $("#error-message").empty();--%>
    <%--    $("div[id$='-error']").empty();--%>
    <%--    $("#duplicate-error-ma").empty();--%>
    <%--    $("#duplicate-error-loaiDe").empty();--%>

    <%--    // Hiển thị lỗi tổng quát--%>
    <%--    errors.forEach(function (error) {--%>
    <%--        $("#error-message").append('<div style="color: crimson;">' + error + '</div>');--%>
    <%--    });--%>

    <%--    // Hiển thị lỗi cho từng ô nhập liệu--%>
    <%--    errors.forEach(function (error) {--%>
    <%--        var fieldName = error.split(":")[0].trim();--%>
    <%--        var errorMessage = error.split(":")[1].trim();--%>
    <%--        $("#" + fieldName + "-error").append('<div style="color: crimson;">' + errorMessage + '</div>');--%>
    <%--    });--%>

    <%--    // Hiển thị thông báo trùng mã hoặc tên--%>
    <%--    if (errorField === "ma") {--%>
    <%--        $("#duplicate-error-ma").append('<div style="color: crimson;">' + errors[0] + '</div>');--%>
    <%--    }--%>
    <%--    if (errorField === "loaiDe") {--%>
    <%--        $("#duplicate-error-loaiDe").append('<div style="color: crimson;">' + errors[0] + '</div>');--%>
    <%--    }--%>
    <%--}--%>
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


