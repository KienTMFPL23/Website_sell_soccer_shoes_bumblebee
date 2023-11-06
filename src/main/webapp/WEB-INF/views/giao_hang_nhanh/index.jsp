<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách ProvinceID</title>
</head>
<body>

<%--<h1>Danh sách Tỉnh/Thành phố, Huyện và Xã</h1>--%>
<%--<form action="/tinhthanh" method="post"> --%>
<%--    <label for="provinceName">Chọn Tỉnh/Thành phố:</label>--%>
<%--    <select name="provinceName" id="provinceName">--%>
<%--        <c:forEach items="${locations}" var="location">--%>
<%--            <option value="${location.provinceName}">${location.provinceName}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>

<%--    <label for="districtName">Chọn Huyện/Quận:</label>--%>
<%--    <select name="districtName" id="districtName">--%>
<%--        <!-- Placeholder for districts -->--%>
<%--    </select>--%>

<%--    <label for="wardName">Chọn Xã/Phường:</label>--%>
<%--    <select name="wardName" id="wardName">--%>
<%--        <!-- Placeholder for wards -->--%>
<%--    </select>--%>

<%--    <input type="submit" value="Hiển thị tên">--%>
<%--</form>--%>

<%--<script>--%>
<%--    var locations = ${locations}; // Lấy danh sách locations từ server--%>

<%--    document.getElementById("provinceName").addEventListener("change", function () {--%>
<%--        var selectedProvince = this.value;--%>
<%--        var selectedDistricts = getDistrictsByProvince(selectedProvince);--%>
<%--        var districtSelect = document.getElementById("districtName");--%>
<%--        districtSelect.innerHTML = "<option value=''>Chọn Huyện/Quận</option>"; // Xóa các option cũ--%>
<%--        for (var i = 0; i < selectedDistricts.length; i++) {--%>
<%--            var option = document.createElement("option");--%>
<%--            option.value = selectedDistricts[i].districtName;--%>
<%--            option.text = selectedDistricts[i].districtName;--%>
<%--            districtSelect.appendChild(option);--%>
<%--        }--%>
<%--    });--%>

<%--    document.getElementById("districtName").addEventListener("change", function () {--%>
<%--        var selectedDistrict = this.value;--%>
<%--        var selectedWards = getWardsByDistrict(selectedDistrict);--%>
<%--        var wardSelect = document.getElementById("wardName");--%>
<%--        wardSelect.innerHTML = "<option value=''>Chọn Xã/Phường</option>"; // Xóa các option cũ--%>
<%--        for (var i = 0; i < selectedWards.length; i++) {--%>
<%--            var option = document.createElement("option");--%>
<%--            option.value = selectedWards[i].wardName;--%>
<%--            option.text = selectedWards[i].wardName;--%>
<%--            wardSelect.appendChild(option);--%>
<%--        }--%>
<%--    });--%>

<%--    function getDistrictsByProvince(selectedProvince) {--%>
<%--        // Tìm danh sách huyện tương ứng với tỉnh trong locations--%>
<%--        for (var i = 0; i < locations.length; i++) {--%>
<%--            if (locations[i].provinceName === selectedProvince) {--%>
<%--                return locations[i].districts;--%>
<%--            }--%>
<%--        }--%>
<%--        return [];--%>
<%--    }--%>

<%--    function getWardsByDistrict(selectedDistrict) {--%>
<%--        // Tìm danh sách xã tương ứng với huyện trong locations--%>
<%--        for (var i = 0; i < locations.length; i++) {--%>
<%--            var districts = locations[i].districts;--%>
<%--            for (var j = 0; j < districts.length; j++) {--%>
<%--                if (districts[j].districtName === selectedDistrict) {--%>
<%--                    return districts[j].wards;--%>
<%--                }--%>
<%--            }--%>
<%--        }--%>
<%--        return [];--%>
<%--    }--%>
<%--</script>--%>

<form:form action="your_action_url" method="post" cssClass="form-control"> <!-- Thay "your_action_url" bằng URL xử lý form -->
    <label for="provinceName">Chọn ProvinceName:</label>
    <select name="provinceName" id="provinceName" class="form-control-lg" >
        <c:forEach items="${provinceNames}" var="provinceName">
            <option value="${provinceName}">${provinceName}</option>
        </c:forEach>
    </select>
    <label for="districtID">Chọn Huyện/Quận:</label>
    <select name="districtID" id="districtID">
        <!-- Placeholder for districts -->
    </select>

    <label for="wardID">Chọn Xã/Phường:</label>
    <select name="wardID" id="wardID">
        <!-- Placeholder for wards -->
    </select>
    <input type="button" value="Hiển thị tên" onclick="showSelectedName()">
</form:form>

<script>
    function showSelectedName() {
        var select = document.getElementById("provinceName");
        var selectedOption = select.options[select.selectedIndex];
        var selectedName = selectedOption.text;
        alert("Tên được chọn: " + selectedName);
    }
</script>


</body>
</html>
