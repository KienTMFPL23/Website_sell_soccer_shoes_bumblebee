<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet"
/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<style>
    body {

        color: #333;
        background-color: #F3F3F3;
    }

    .ps-main {
        padding: 50px 100px 50px 100px;
        font-family: Nunito;
    }

    .infor {
        padding: 15px 0px 0px 20px;
        border-bottom: 2px solid #D9D9D9;
    }

    .infor-img > img {
        float: left;
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }

    .infor-name > h2 {
        padding: 15px;
        color: #0b0b0b;
        font-weight: 600;
    }

    .img {
        margin: 10px;
        width: 30px;
        height: 30px;
    }

    .menu-left > a {
        color: #0b0b0b;
        text-decoration: none;
        font-size: 15px;
    }

    .menu-left > a:hover {
        color: #37517E;
        text-decoration: none;
        font-size: 15px;
    }

    .col-lg-6 {
        background-color: #FFFFFF;
        border-radius: 5px;

    }

    .h2 {
        padding: 0px 15px 0px 15px;
        border-bottom: 2px solid #D9D9D9;
        font-family: Nunito;
    }

    form {
        padding: 30px 40px 30px 40px;
    }

    label {
        font-size: 15px;
        font-family: Nunito;
        width: 110px;
    }

    input {
        border: 0px;
        border-bottom: 2px solid #D9D9D9;
        height: 40px;
        width: 70%;
        font-size: 17px;
        padding-top: 25px;
        margin-left: 10px;
        font-family: Nunito;

    }

    .btnSubmit {
        background-color: #37517E;
        color: #FFFFFF;
        font-size: 15px;
        width: 70px;
        height: 30px;
        border: 0px;
        border-radius: 5px;
        display: flex;
        margin-left: auto;
        margin-right: auto;
        margin-top: 30px;
    }

    .img-user {

    }

    .img-user > img {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        display: flex;
        margin: 40px auto 0px auto;
    }

    .upload {
        background-color: #FFFFFF;
        border: 2px solid #D9D9D9;
        border-radius: 5px;
        height: 30px;
        width: 100px;
        display: flex;
        margin: 30px auto auto auto;
        text-align: center;
    }

    .btnChooseImage > a {
        font-size: 16px;
        text-align: center;
    }

    .radio {
        width: 15px;
    }
    #city, #district, #ward {
        font-size: 14px;
        border-radius: 5px;
        width: 130px;
        height: 25px;
        line-height: 25px;
        border: 1px solid #A3A3A3;
        margin-top: 15px;
    }
</style>
<body>
<main class="ps-main">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <form:form action="/bumblebee/thong-tin-nguoi-dung" method="post" modelAttribute="kh">
                <div class="h2">
                    <h1>Hồ sơ của tôi</h1>
                </div>

                <div class="row">
                    <div>
                        <form:input type="hidden" path="id"/>
                    </div>

                    <div>
                        <label>Họ: </label>
                        <form:input type="text" path="ho"/>
                    </div>

                    <div>
                        <label>Tên đệm: </label>
                        <form:input type="text" path="tenDem"/>
                    </div>

                    <div>
                        <label>Tên: </label>
                        <form:input type="text" path="ten"/>
                    </div>

                    <div>
                        <label>Giới tính: </label>
                        <form:radiobuttons items="${dsGioiTinh}" class="radio" path="gioiTinh"/>
                    </div>

                    <div>
                        <label>Ngày sinh: </label>
                        <form:input type="date" path="ngaySinh"/>
                    </div>

                    <div>
                        <label>Số điện thoại: </label>
                        <form:input type="text" path="soDienThoai"/>
                    </div>

                    <div>
                        <label>Địa chỉ: </label>
                        <select id="city" name="thanhPho">
                            <option value="" selected>Chọn tỉnh thành</option>
                        </select>

                        <select id="district" name="huyen">
                            <option value="" selected>Chọn quận huyện</option>
                        </select>

                        <select id="ward" name="xa">
                            <option value="" selected>Chọn phường xã</option>
                        </select>
                    </div>

                    <div>
                        <label>Email: </label>
                        <form:input type="text" path="email"/>
                    </div>

                    <div>
                        <button class="btnSubmit" type="submit">Lưu</button>
                    </div>

                        <%--                    <div class="col-lg-4">--%>
                        <%--                        <div class="img-user">--%>
                        <%--                            <img src="../../../uploads/aaaf46616a81b3e60a1302bb80200c30.jpg">--%>
                        <%--                        </div>--%>
                        <%--                        <div>--%>
                        <%--                            <input type="file" id="upload" hidden/>--%>
                        <%--                            <label class="upload" for="upload">Choose file</label>--%>
                        <%--                        </div>--%>
                        <%--                    </div>--%>
                </div>
            </form:form>
        </div>
        <div class="col-lg-3"></div>

    </div>
</main>
</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script>
    var citis = document.getElementById("city");
    var districts = document.getElementById("district");
    var wards = document.getElementById("ward");
    var Parameter = {
        url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
        method: "GET",
        responseType: "application/json",
    };
    var promise = axios(Parameter);
    promise.then(function (result) {
        renderCity(result.data);
    });

    function renderCity(data) {
        for (const x of data) {
            var opt = document.createElement('option');
            opt.value = x.Name;
            opt.text = x.Name;
            opt.setAttribute('data-id', x.Id);
            citis.options.add(opt);
        }
        citis.onchange = function () {
            district.length = 1;
            ward.length = 1;
            if(this.options[this.selectedIndex].dataset.id != ""){
                const result = data.filter(n => n.Id === this.options[this.selectedIndex].dataset.id);

                for (const k of result[0].Districts) {
                    var opt = document.createElement('option');
                    opt.value = k.Name;
                    opt.text = k.Name;
                    opt.setAttribute('data-id', k.Id);
                    district.options.add(opt);
                }
            }
        };
        district.onchange = function () {
            ward.length = 1;
            const dataCity = data.filter((n) => n.Id === citis.options[citis.selectedIndex].dataset.id);
            if (this.options[this.selectedIndex].dataset.id != "") {
                const dataWards = dataCity[0].Districts.filter(n => n.Id === this.options[this.selectedIndex].dataset.id)[0].Wards;

                for (const w of dataWards) {
                    var opt = document.createElement('option');
                    opt.value = w.Name;
                    opt.text = w.Name;
                    opt.setAttribute('data-id', w.Id);
                    wards.options.add(opt);
                }
            }
        };
    }
</script>