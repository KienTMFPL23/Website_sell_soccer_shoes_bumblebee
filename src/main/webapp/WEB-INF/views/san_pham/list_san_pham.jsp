<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<h1 style="text-align: center">Quản lý sản phẩm</h1>

</br>
<div class="row">
    <div class="row">
        <div class="col-lg-9">
            <a href="/san-pham/view-add" class="btnAdd">
                <img src="https://cdn-icons-png.flaticon.com/512/1828/1828817.png" width="30px" height="30px"/>
                <p>Thêm mới</p>
            </a>
        </div>
        <div class="col-lg-3">
            <a href="/chi-tiet-san-pham/hien-thi" class="btnDSCT">
                <i class="bi bi-eye-fill"></i>Danh sách chi tiết
            </a>
        </div>
    </div>

</div>
</br>
<c:if test="${not empty page.content}">
    <table id="tableSanPham" class="ui celled table" width="100%" cellspacing="0">
        <thead style="color: #37517E">
        <tr style="background: #37517E;color: white ">
            <th scope="col">STT</th>
            <th scope="col">Mã sản phẩm</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Trạng thái</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.content}" varStatus="index" var="sp">
            <tr>
                <td> ${index.count} </td>
                <td> ${sp.maSanPham} </td>
                <td> ${sp.tenSanPham}</td>
                <td> ${sp.trangThai == 1 ? "Hoạt động" : "Ngừng hoạt động"} </td>
                <td>
                    <a href="/san-pham/view-update/${sp.id}" class="btn btn-primary"><i class="bi bi-pencil-square"></i>
                        <b>Chi tiết</b>
                    </a>
                    <a href="/chi-tiet-san-pham/list-san-pham/${sp.id}" class="btn btn-secondary"><i
                            class="bi bi-eye-fill"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty page.content}">
    <p>Không có sản phẩm.</p>
</c:if>
<%--<div>--%>
<%--    <nav aria-label="Page navigation example" style="text-align: center">--%>
<%--        <ul class="pagination">--%>
<%--            <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?p=${page.number-1}">Previous</a>--%>
<%--            </li>--%>
<%--            <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?p=${page.number+1}">Next</a></li>--%>
<%--        </ul>--%>
<%--    </nav>--%>
<%--</div>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('#tableSanPham').DataTable({
            info: false,
            language: {
                search: "",
                searchPlaceholder: "Tìm kiếm ......",
            }
        });
    });
</script>

