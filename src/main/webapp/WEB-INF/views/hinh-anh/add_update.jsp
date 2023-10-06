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
        width: 80%;
        height: 1300px;
        background-color: #37517E;
        border-radius: 20px;
        color: #FFFFFF;
        font-family: "Nunito";
        margin: 0 auto;
    }

    .row.mb-3 {
        margin-top: 30px;
        margin-bottom: 50px;
    }

    .button {
        background-color: #FFFFFF;
        color: black;
        font-weight: bold;
        width: 120px;
        height: 40px;
        border-radius: 10px;
        border: 0px;
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

    .preview-img {
        max-width: 150px;
        margin: 0 1em 1em 0;
        padding: 0.5em;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    .row{
        padding-left: 50px;
        padding-right: 50px;
    }

</style>
<br>

<body>
<div class="formAdd">
    <h1 style="text-align: center; padding-top: 20px;">THÊM/SỬA HÌNH ẢNH</h1>
    <form action="${action}" method="post" enctype="multipart/form-data">
        <div class="row mb-3">
            <div class="col-sm-6">
                <input type="hidden" class="form-control" name="id" value="${listHinhAnh.id}"/>
            </div>
        </div>

        <%--   Row 1     --%>
        <div class="row" style="margin-bottom: 30px;">
            <div class="col-lg-5">
                <label>ID CTSP:</label>
                <%--                <select id="selectCTSP" class="form-control" name="ctsp" name="keyword" style="margin-bottom: 30px;"/>--%>
                <%--                <form:errors path="ma" cssStyle="color: crimson"></form:errors>--%>
                <input type="text" class="form-control" id="inputEmail3" name="ctsp" value="${listHinhAnh.ctsp.id}"/>
            </div>
            <div class="col-lg-2"></div>
            <div class="col-lg-5">

            </div>
        </div>


        <%--   Row 2     --%>
        <div class="row">
            <div class="col-lg-5">
                <label>Ảnh 1:</label>
                <input type="file" class="form-control" name="tenanh" onchange="imageFileAsUrl1(this)" value="${listHinhAnh.tenanh}"/>
            </div>
            <div class="col-lg-2"></div>
            <div class="col-lg-5">
                <label>Ảnh 2:</label>
                <input type="file"  class="form-control" name="duongdan1" onchange="imageFileAsUrl2(this)"/>
            </div>
        </div>


        <div class="row mb-3">
            <div class="col-lg-5">
                <img src="../../../uploads/${listHinhAnh.tenanh}" id="image1" width="200px" height="200px"/>
            </div>
            <div class="col-lg-2"></div>

            <div class="col-lg-5">
                <img src="../../../uploads/${listHinhAnh.duongdan1}" id="image2" width="200px" height="200px"/>

            </div>
        </div>

        <%--   Row 3     --%>
        <div class="row mb-3">
            <div class="col-lg-5">
                <label>Ảnh 3:</label>
                <input type="file" id="upload" class="form-control" name="duongdan2" onchange="imageFileAsUrl3(this)"/>
            </div>
            <div class="col-lg-2"></div>

            <div class="col-lg-5">
                <label>Ảnh 4:</label>
                <input type="file" id="upload" class="form-control" name="duongdan3" onchange="imageFileAsUrl4(this)"/>

            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-5">
                <img src="../../../uploads/${listHinhAnh.duongdan2}" id="image3" width="200px" height="200px"/>
            </div>
            <div class="col-lg-2"></div>

            <div class="col-lg-5">
                <img src="../../../uploads/${listHinhAnh.duongdan3}" id="image4" width="200px" height="200px"/>
            </div>
        </div>


        <%--   Row 3   --%>
        <div class="row mb-3">
            <div class="col-lg-5">
                <label>Ảnh 5:</label>
                <input type="file" id="upload" class="form-control" name="duongdan4" onchange="imageFileAsUrl5(this)"/>
            </div>
            <div class="col-lg-2"></div>

            <div class="col-lg-5">
                <label>Ảnh 6:</label>
                <input type="file" id="upload" class="form-control" name="duongdan5" onchange="imageFileAsUrl6(this)"/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-lg-5">
                <img src="../../../uploads/${listHinhAnh.duongdan4}" id="image5" width="200px" height="200px"/>
            </div>
            <div class="col-lg-2"></div>

            <div class="col-lg-5">
                <img src="../../../uploads/${listHinhAnh.duongdan5}" id="image6" width="200px" height="200px"/>
            </div>
        </div>

        <div class="buttonSubmit">
            <button class="button" type="submit">SUBMIT</button>
        </div>

    </form>
</div>
<script>
    function imageFileAsUrl1(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image1').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }

    function imageFileAsUrl2(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image2').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }

    function imageFileAsUrl3(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image3').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }

    function imageFileAsUrl4(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image4').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }

    function imageFileAsUrl5(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image5').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }

    function imageFileAsUrl6(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image6').attr('src', e.target.result);
            }
            reader.readAsDataURL(fileInput.files[0]);
        }
    }
</script>
</body>

