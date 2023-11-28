<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<table class="table table-bordered" style="margin-top: 10px">
    <thead>
    <tr>
        <th scope="col">Tên sản phẩm</th>
        <th scope="col">Màu sắc</th>
        <th scope="col">Kích cỡ</th>
        <th scope="col">Số lượng</th>
        <th scope="col">Đơn giá</th>
        <th scope="col">Hình thức đổi trả</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="hd" items="${listHDCT}">
        <tr>
            <td>${hd.chiTietSanPham.sanPham.tenSanPham}</td>
            <td>${hd.chiTietSanPham.mauSac.ten}</td>
            <td>${hd.chiTietSanPham.kichCo.size}</td>
            <td>
                <div>
                    <input type="number" id="soLuongTra"
                           min="1"
                           name="soLuong"
                           value=""
                           onblur="this.form.submit()"
                           style="width:50px;"> /${hd.soLuong}
                </div>
            </td>
            <td><fmt:formatNumber value="${hd.donGia}" type="number"/></td>
            <td>
                <a id="openModalSanPham"
                   onclick="showDataSP(`${hd.chiTietSanPham.id}`)"
                   class="btn btn-primary"
                   type="submit"
                   data-bs-toggle="modal"
                   data-bs-target="#modalSanPham" style="border-radius: 20px; margin-top: 5px;"
                >Đổi sản phẩm
                </a>
                <div class="modal fade" id="modalSanPham" tabindex="-1"
                     aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Danh sách sản phẩm đổi</h1>
                            </div>
                            <div class="modal-body">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">Tên</th>
                                        <th scope="col">Màu sắc</th>
                                        <th scope="col">Kích cỡ</th>
                                        <th scope="col">Số lượng</th>
                                        <th scope="col">Đơn giá</th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody id="tableSanPham">
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>
<strong>Sản phẩm đổi trả</strong>
<table class="table table-bordered" style="margin-top: 10px">
    <thead>
    <tr>
        <th scope="col"> Tên sản phẩm</th>
        <th scope="col"> Màu sắc</th>
        <th scope="col"> Kích cỡ</th>
        <th scope="col"> Số lượng</th>
        <th scope="col"> Đơn giá</th>
        <th scope="col"> Hiện trạng sản phẩm</th>
        <th scope="col"> Lý do đổi trả</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dtct" items="${listDoiTraCT}">
        <tr>
            <td>${dtct.chiTietSanPham.sanPham.tenSanPham}</td>
            <td>${dtct.chiTietSanPham.mauSac.ten}</td>
            <td>${dtct.chiTietSanPham.kichCo.size}</td>
            <td>
                <form:form method="post" modelAttribute="dtct" action="">
                    <input type="number" class="form-control"
                           min="1"
                           name="soLuong"
                           value="${dtct.soLuong}"
                           onblur="this.form.submit()"
                           style="width:100px;">
                </form:form>

            </td>
            <td><fmt:formatNumber value="${dtct.donGia}" type="number"/></td>
            <td>
                <select class="form-control">
                    <option>Sản phẩm đổi</option>
                    <option>Sản phẩm trả</option>
                </select>
            </td>
            <td>
                <select class="form-control">
                    <option>----</option>
                    <option>Hàng lỗi</option>
                    <option>Sai kích cỡ</option>
                    <option>Sai màu sắc</option>
                    <option>Other</option>
                </select>
            </td>
            <td>
                <a href="/bumblebee/don-hang/remove-doi-tra/${dtct.id}"> <img src="../../../img/delete.png"></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/bumblebee/don-hang/xac-nhan-doi" class="btn btn-primary">Đổi hàng</a>
<button data-bs-toggle="modal" type="button"
        data-bs-target="#staticBackdrop" class="btn btn-danger">Trả hàng
</button>
<form method="post"  action="/bumblebee/don-hang/create-tra-hang">
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Hoàn trả</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Màu sắc</th>
                            <th scope="col">Kích cỡ</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Đơn giá</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="hd" items="${listHDCT}">
                            <tr>
                                <td><input class="checkCart" type="checkbox"
                                           name="idListCartDetail"
                                           value="${hd.chiTietSanPham.id}"></td>
                                <td>${hd.chiTietSanPham.sanPham.tenSanPham}</td>
                                <td>${hd.chiTietSanPham.mauSac.ten}</td>
                                <td>${hd.chiTietSanPham.kichCo.size}</td>
                                <td>
                                    <div>
                                        <input type="number" id="soLuongTra"
                                               min="0"
                                               max="${hd.soLuong}"
                                               name="soLuong"
                                               value=""
                                               style="width:50px;"> /${hd.soLuong}
                                    </div>
                                </td>
                                <td><fmt:formatNumber value="${hd.donGia}" type="number"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <p>Lý do trả hàng</p>
                    <textarea type="text" name="lyDoDoiTra"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Xác nhận</button>
                </div>
            </div>
        </div>
    </div>
</form>


<a class="btn btn-success" href="/bumblebee/don-hang/huy-doi-tra">Hủy</a>
<script
        type="text/javascript"
        src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"
></script>


