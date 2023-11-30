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
<strong>Sản phẩm đổi</strong>
<form method="post" action="/bumblebee/don-hang/xac-nhan-doi">
    <table class="table table-bordered" style="margin-top: 10px">
        <thead>
        <tr>
            <th scope="col"> Tên sản phẩm</th>
            <th scope="col"> Màu sắc</th>
            <th scope="col"> Kích cỡ</th>
            <th scope="col"> Số lượng</th>
            <th scope="col"> Đơn giá</th>
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
                    <input type="number" class="form-control"
                           min="1"
                           name="soLuong"
                           value="${dtct.soLuong}"
                           onblur="this.form.submit()"
                           style="width:100px;">
                </td>
                <td><fmt:formatNumber value="${dtct.donGia}" type="number"/></td>
                <td>
                    <textarea type="text" name="lyDoDoiTra"></textarea>
                </td>
                <td>
                    <a href="/bumblebee/don-hang/remove-doi-tra/${dtct.id}"> <img src="../../../img/delete.png"></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="submit" class="btn btn-primary">Đổi hàng</button>
</form>
<button data-bs-toggle="modal" type="button"
        data-bs-target="#modalTraHang" class="btn btn-danger">Trả hàng
</button>
<a class="btn btn-success" href="/bumblebee/don-hang/huy-doi-tra">Hủy</a>
<form method="post" action="/bumblebee/don-hang/create-tra-hang">
    <div class="modal fade" id="modalTraHang" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
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
                            <th scope="col"></th>
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
                                           id="checkBoxSP"
                                           name="idListCartDetail"
                                           value="${hd.chiTietSanPham.id}"></td>
                                <td>${hd.chiTietSanPham.sanPham.tenSanPham}</td>
                                <td>${hd.chiTietSanPham.mauSac.ten}</td>
                                <td>${hd.chiTietSanPham.kichCo.size}</td>
                                <td>
                                    <div>
                                        <input type="number" id="soLuongTra"
                                               min="1"
                                               max="${hd.soLuong}"
                                               name="soLuong"
                                               oninput="validateSoLuong(${hd.soLuong})"
                                               value=""
                                               style="width:50px;"> /${hd.soLuong}
                                        <p id="errorMsg" style="color: red; display: none">
                                            Không hợp lệ
                                        </p>
                                    </div>
                                </td>
                                <td><fmt:formatNumber value="${hd.donGia}" type="number"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <strong><p style="color: red" id="logError"></p></strong>
                    <div>
                        <strong>Lý do trả hàng</strong>
                        <select id="lyDoTra" class="form-control" style="width: 200px" name="lyDoDoiTra">
                            <option value="">-----</option>
                            <option value="Mẫu sai">Mẫu sai</option>
                            <option value="Sản phẩm lỗi">Sản phẩm lỗi</option>
                        </select>
                        <%--                        <textarea type="text" id="lyDoTra" name="lyDoDoiTra"></textarea>--%>
                        <strong><p style="color: red" id="erorText"></p></strong>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" onclick="return taoDoiTra()" id="xacNhanTra" class="btn btn-primary">Xác
                        nhận
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>

