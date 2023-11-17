<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<br>
<br>
<style>
    #myInput {
        height: 40px;
        border: 2px solid #37517E;
        border-radius: 15px;
        margin-bottom: 15px;
        float: right;
    }

</style>
<div class="container">
    <div class="row">
        <div class="col-8">
            <div class="col-4 col-md-4 col-sm-4">
                <a href="/chi-tiet-san-pham/view-add/${idsp}" class="btn"
                   style="background: #0d6efd; color: whitesmoke">
                    <i class="bi bi-plus-circle-fill"></i><span>Thêm CT Sản Phẩm</span></a>
            </div>
            <br>
            <div class="col-4 col-md-4 col-sm-4">
                <a href="/chi-tiet-san-pham/hien-thi" class="btn"
                   style="background: #0d6efd; color: whitesmoke"><i class="bi bi-eye-fill"></i>Danh sách
                    chi tiết</a>
                <br>
            </div>
        </div>
        <div class="col-4">

            <input class="form-control" placeholder="Nhập mã hoặc loại tên sản phẩm" id="myInput"/>

        </div>
    </div>
    <br>
    <div class="row" style="padding-bottom: 30px">
        <div class="col-2 col-md-2 col-sm-2">
            <form:form modelAttribute="lg" action="/chi-tiet-san-pham/search-by-loaigiay">
                <label class="form-label">Loại giầy: </label>
                <form:select type="text" id="searchName0" path="idLG" onchange="submit()" cssClass="form-control">
                    <form:option value="">Tất cả</form:option>
                    <form:options items="${listLoaiGiay}" itemLabel="tentheloai" itemValue="id"/>
                </form:select>
            </form:form>
        </div>

        <div class="col-2 col-md-2 col-sm-2">
            <form:form modelAttribute="searchChatLieu" action="/chi-tiet-san-pham/search-by-chatlieu">
                <label class="form-label">Chất liệu: </label>
                <form:select type="text" id="searchName4" path="idChatLieu" onchange="submit()"
                             cssClass="form-control">
                    <form:option value="">Tất cả</form:option>
                    <form:options items="${listChatLieu}" itemLabel="ten" itemValue="id"/>
                </form:select>

            </form:form>
        </div>

        <div class="col-2 col-md-2 col-sm-2">
            <form:form action="/chi-tiet-san-pham/search-by-degiay" modelAttribute="searchDG">
                <label class="form-label">Đế giày: </label>
                <form:select type="text" id="searchName3" path="idDe" onchange="submit()" cssClass="form-control">
                    <form:option value="">Tất cả</form:option>
                    <form:options items="${listDeGiay}" itemLabel="loaiDe" itemValue="id"/>
                </form:select>

            </form:form>
        </div>
        <div class="col-2 col-md-2 col-sm-2">
            <form:form action="/chi-tiet-san-pham/search-by-kichco" modelAttribute="searchKC">

                <label class="form-label">Kích cỡ: </label>
                <form:select type="text" id="searchName1" path="idKC" onchange="submit()" cssClass="form-control">
                    <form:option value="">Tất cả</form:option>
                    <form:options items="${listKichCo}" itemLabel="size" itemValue="id"/>
                </form:select>

            </form:form>
        </div>
        <div class="col-2 col-md-2 col-sm-2">
            <form:form action="/chi-tiet-san-pham/search-by-mausac" modelAttribute="searchFormByMau">

                <label class="form-label">Màu sắc:</label>
                <form:select type="text" id="searchName2" path="idMau" onchange="submit()" cssClass="form-control">
                    <form:option value="">Tất cả</form:option>
                    <form:options items="${listMau}" itemLabel="ten" itemValue="id"/>
                </form:select>

            </form:form>
        </div>
    </div>
    </br>

    <table class="table table-bordered">
        <tr style="background: #37517E;color: white ">
            <th>STT</th>
            <th>Mã sản phẩm</th>
            <th>Tên sản phẩm</th>
            <th>Giá Bán</th>
            <th>Số lượng</th>
            <th>Trạng Thái</th>
            <th>Hình Ảnh</th>
            <th>Action</th>
        </tr>
        <c:if test="${not empty page.content}">
            <tbody id="myTable">
            <c:forEach items="${page.content}" var="sp" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${sp.sanPham.maSanPham}</td>
                    <td>${sp.sanPham.tenSanPham}</td>
                    <td><fmt:formatNumber value="${sp.giaBan}" maxFractionDigits="20" type="number"/></td>

                    <td>${sp.soLuong}</td>
                    <td>${sp.trangThai==0?"Không hoạt động":"Hoạt động"}</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty sp.hinhAnhs.tenanh}">
                                <i class="bi bi-image"></i><span class="text-muted">no image</span>
                            </c:when>
                            <c:otherwise>
                                <img src="../../uploads/${sp.hinhAnhs.tenanh}" width="100px" height="100px"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="/chi-tiet-san-pham/view-update-ctsp/${sp.id}" class="btn btn-warning"><i
                                class="bi bi-pencil-square"></i></a>
                        <a href="/chi-tiet-san-pham/hinh-anh-sp/view-add/${sp.id}" class="btn btn-warning"><i
                                class="bi bi-file-earmark-image"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </c:if>
        <c:if test="${empty page.content}">
            <td colspan="8" class="text-center">Không có sản phẩm.</td>
        </c:if>
    </table>

    <div class="text-center">
        <nav aria-label="Page navigation text-center">
            <ul class="pagination justify-content-center">
                <li class="page-item"><a class="page-link"
                                         href="/chi-tiet-san-pham/list-san-pham/${idsp}?p=0">Previous</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="/chi-tiet-san-pham/list-san-pham/${idsp}?p=${page.number-1}"><<</a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="/chi-tiet-san-pham/list-san-pham/${idsp}?p=${page.number+1}">>></a>
                </li>
                <li class="page-item"><a class="page-link"
                                         href="/chi-tiet-san-pham/list-san-pham/${idsp}?p=${page.totalPages-1}">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<script>
    function submitForm() {
        document.getElementById('searchMauSac').submit();
    }

    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#filterLG").click(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).val().toLowerCase().indexOf(value) > -1)
        });
    });
</script>

<script src="../../../../../webapp/js/chi-tiet-san-pham.js"></script>
