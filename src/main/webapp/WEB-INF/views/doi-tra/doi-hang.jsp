<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="content">
    <div class="content-list-doi">
        <table class="table table-bordered" style="margin-top: 10px">
            <thead>
            <tr>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Màu sắc</th>
                <th scope="col">Kích cỡ</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Đơn giá khi giảm</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hd" items="${listHDCT}">
                <tr>
                    <td>${hd.chiTietSanPham.sanPham.tenSanPham}</td>
                    <td>${hd.chiTietSanPham.mauSac.ten}</td>
                    <td>${hd.chiTietSanPham.kichCo.size}</td>
                    <td>${hd.soLuong}</td>
                    <td><fmt:formatNumber value="${hd.donGia}" type="number"/></td>
                    <td><fmt:formatNumber value="${hd.donGiaKhiGiam}" type="number"/></td>
                    <td>
                        </a>
                        <a id="openModalSanPham"
                           onclick="showDataSP(`${hd.chiTietSanPham.id}`)"
                           class="btn btn-primary"
                           type="submit"
                           data-bs-toggle="modal"
                           data-bs-target="#modalSanPham" style="border-radius: 20px; margin-top: 5px;"
                        >Chọn
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
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="show-sum">
        <strong>Tổng tiền <fmt:formatNumber value="${sumMoney}" type="number"/></strong>
    </div>
    <hr>
    <h2 style="text-align: center">Sản phẩm khách hàng đổi</h2>
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
                        <form action="/bumblebee/don-hang/update-so-Luong/${dtct.id}">
                            <input type="number" class="form-control"
                                   min="1"
                                   name="soLuong"
                                   value="${dtct.soLuong}"
                                   onchange="this.form.submit()"
                                   style="width:100px;">
                        </form>
                    </td>
                    <td><fmt:formatNumber value="${dtct.donGia}" type="number"/></td>
                    <td>
                        <form action="/bumblebee/don-hang/update-ly-do/${dtct.id}">
                            <select  class="form-control" style="width: 200px" name="lyDoDoiTra" onchange="this.form.submit()">
                                <option value="">-----</option>
                                <option value="Mẫu giao sai">Mẫu giao sai</option>
                                <option value="Sản phẩm lỗi">Sản phẩm lỗi</option>
                                <option value="Giao thiếu hàng">Giao thiếu hàng</option>
                            </select>
                        </form>
                    </td>
                    <td>
                        <a href="/bumblebee/don-hang/remove-doi-tra/${dtct.id}"> <img src="../../../img/delete.png"></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="/bumblebee/don-hang/xac-nhan-doi" class="btn btn-success">Đổi hàng</a>
        <a href="/bumblebee/don-hang/huy/${idDoiTra}" type="submit" class="btn btn-danger">Hủy</a>
</div>