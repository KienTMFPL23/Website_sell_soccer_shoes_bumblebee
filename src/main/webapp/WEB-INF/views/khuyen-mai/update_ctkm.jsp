<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css"
/>
<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet"
/>
<style>

    body {
        background-color: #D9D9D9;
    }

    .formAdd {
        width: 700px;
        height: 800px;
        background-color: #37517E;
        border-radius: 20px;
        color: #FFFFFF;
        font-family: "Nunito";
        margin: 0 auto;
    }

    .row.mb-3 {
        margin-top: 30px;
    }

    .button {
        background-color: #FFFFFF;
        color: black;
        font-weight: bold;
        width: 120px;
        height: 40px;
        border-radius: 10px;
        border: 0px;
        margin-top: 30px;
        margin: auto;
        display: block;
    }

    label {
        font-size: 17px;
    }

    .error {
        color: red;
        font-size: 15px;
        padding-left: 190px;
        padding-top: 15px;
    }

</style>
<br>

<body>
<div class="formAdd">
    <h1 style="text-align: center; padding-top: 20px;">SỬA CHI TIẾT KHUYẾN MẠI</h1>
    <form action="${action}" method="post" id="formAddUpdate">
        <div class="row mb-3">
            <div class="col-sm-6">
                <input type="hidden" class="form-control" id="inputEmail3" value="${ctkm.id}"/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Tên sản phẩm:</label>
            </div>
            <div class="col-lg-8">
                <input type="text" class="form-control" value="${ctkm.ctsp.sanPham.tenSanPham}" disabled/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Mã khuyến mại:</label>
            </div>
            <div class="col-lg-8">
                <input type="text" class="form-control" value="${ctkm.khuyenMai.maKhuyenMai}" disabled/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Giá trị:</label>
            </div>
            <div class="col-lg-8">
                <c:if test="${ctkm.khuyenMai.donVi == '%'}">
                    <input type="text" class="form-control" value="${ctkm.khuyenMai.giaTri}%" disabled/>
                </c:if>
                <c:if test="${ctkm.khuyenMai.donVi == 'VNĐ'}">
                    <input type="text" class="form-control" value="${ctkm.khuyenMai.giaTri}" disabled/>
                </c:if>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Giá bán:</label>
            </div>
            <div class="col-lg-8">
                <input type="text" class="form-control" value="<fmt:formatNumber>${ctkm.ctsp.giaBan}</fmt:formatNumber>" disabled/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Giá khuyến mại:</label>
            </div>
            <div class="col-lg-8">
                <c:if test="${ctkm.khuyenMai.donVi == '%'}">
                    <input type="text" class="form-control" value="<fmt:formatNumber>${ctkm.ctsp.giaBan - ((ctkm.khuyenMai.giaTri / 100) * ctkm.ctsp.giaBan)}</fmt:formatNumber>" disabled/>
                </c:if>
                <c:if test="${ctkm.khuyenMai.donVi == 'VNĐ'}">
                    <input type="text" class="form-control" value="${ctkm.ctsp.giaBan- ctkm.khuyenMai.giaTri}" disabled/>
                </c:if>

            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Ngày bắt đầu:</label>
            </div>
            <div class="col-lg-8">
                <input type="datetime-local" class="form-control" value="${ctkm.ngayBatDau}" name="ngayBatDau"/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Ngày kết thúc:</label>
            </div>
            <div class="col-lg-8">
                <input type="datetime-local" class="form-control" value="${ctkm.ngayKetThuc}" name="ngayKetThuc"/>
            </div>
        </div>


        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Trạng thái:</label>
            </div>
            <div class="col-lg-8">
<%--                <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="radioButton" name="trangThai"/>--%>
<%--                <form:errors path="trangThai" cssStyle="color: crimson"></form:errors>--%>
            <c:if test="${ctkm.trangThai == 0}">Hoạt động</c:if>
            <c:if test="${ctkm.trangThai == 1}">Không hoạt động</c:if>
            </div>
        </div>
        <div class="buttonSubmit">
            <button class="button" type="submit">SUBMIT</button>
        </div>

    </form>
</div>
</body>