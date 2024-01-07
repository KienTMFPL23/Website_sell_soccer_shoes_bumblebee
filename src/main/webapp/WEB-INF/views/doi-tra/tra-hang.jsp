<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="content">
    <h2 style="text-align: center">Hóa đơn khách hàng đã mua</h2>
    <div><strong>Mã hóa đơn: </strong>${hoaDonMua.maHoaDon}</div>
    <div><strong>Nhân viên bán
        hàng: </strong>${hoaDonMua.nhanVien.ho} ${hoaDonMua.nhanVien.tenDem} ${hoaDonMua.nhanVien.ten} </div>
    <div><strong>Khác hàng: </strong>${hoaDonMua.tenNguoiNhan}</div>
    <div><strong>Số điện thoại: </strong>${hoaDonMua.sdt}</div>
    <div class="content-list-tra">
        <table class="table table-bordered" style="margin-top: 10px">
            <thead>
            <tr>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Màu sắc</th>
                <th scope="col">Kích cỡ</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Đơn giá khi giảm</th>
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="show-sum">
        <strong>Tổng tiền <fmt:formatNumber value="${sumMoney}" type="number"/></strong>
    </div>
    <hr>
    <c:if test="${hoaDonMua.loaiHoaDon == 0}">
        <button data-bs-toggle="modal" type="button"
                data-bs-target="#modalTraHang" class="btn btn-danger">Trả hàng
        </button>
    </c:if>
    <a href="/bumblebee/don-hang/doi-san-pham" class="btn btn-primary">Đổi hàng
    </a>
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
                                <th scope="col"><input class="checkCart" type="checkbox"
                                                       id="checkAll">
                                </th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Màu sắc</th>
                                <th scope="col">Kích cỡ</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Đơn giá</th>
                                <th scope="col">Lý do</th>
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
                                            <input type="number" id="soLuongTra_${hd.chiTietSanPham.id}" class="soLuongtra"
                                                   min="1"
                                                   max="${hd.soLuong}"
                                                   name="soLuong"
                                                   oninput="return validSoLuong('${hd.soLuong}','${hd.id}',event)"
                                                   style="width:50px;"> /${hd.soLuong}
                                            <strong><p style="color: red" id="erorSluong"></p></strong>
                                        </div>
                                    </td>
                                    <td><fmt:formatNumber value="${hd.donGia}" type="number"/></td>
                                    <td>
                                        <select id="lyDoTra_${hd.chiTietSanPham.id}" class="form-control" style="width: 200px"
                                                name="lyDoDoiTra">
                                            <option value="">-----</option>
                                            <option value="Mẫu giao sai">Mẫu giao sai</option>
                                            <option value="Sản phẩm lỗi">Sản phẩm lỗi</option>
                                            <option value="Giao thiếu hàng">Giao thiếu hàng</option>
                                        </select>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <strong><p style="color: red" id="logError"></p></strong>
                        <div>

                            <%--                        <textarea type="text" id="lyDoTra" name="lyDoDoiTra"></textarea>--%>
                            <strong><p style="color: red" id="erorText"></p></strong>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" onclick="return taoDoiTra()" id="xacNhanTra"
                                class="btn btn-primary">Xác
                            nhận
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>