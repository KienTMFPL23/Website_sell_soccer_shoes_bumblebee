<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <h2 style="text-align: center">Đổi trả hàng</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Mã hóa đơn</th>
            <th scope="col">Ngày tạo</th>
            <th scope="col">Ngày thanh toán</th>
            <th scope="col">Nhân viên</th>
            <th scope="col">Khách hàng</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hd" items="${listHoaDon}" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${hd.maHoaDon}</td>
                    <td><fmt:formatDate value="${hd.ngayTao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${hd.ngayThanhToan}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td>${hd.nhanVien.ho} ${hd.nhanVien.tenDem} ${hd.nhanVien.ten} </td>
                    <td>${hd.tenNguoiNhan} </td>
                    <td><a href="/bumblebee/doi-tra/hoa-don/${hd.id}" class="btn btn-primary">Chọn</a></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
</div>