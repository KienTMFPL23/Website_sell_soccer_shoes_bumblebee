<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="content">
    <h1 style="text-align: center">Danh sách hóa đơn đổi hàng</h1>
    <div class="row">
        <div class="col-lg-6">
            <a href="/bumblebee/doi-hang" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalHoaDon">Tạo
                mới</a>
            <div class="modal fade" id="modalHoaDon" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-body">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">STT</th>
                                    <th scope="col">Mã hóa đơn</th>
                                    <th scope="col">Tên khách hàng</th>
                                    <th scope="col">Số điện thoại</th>
                                    <th scope="col">Trạng thái</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="hd" items="${listDuDK}" varStatus="i">
                                    <tr>
                                        <td>${i.count}</td>
                                        <td>${hd.maHoaDon}</td>
                                        <td>${hd.tenNguoiNhan}</td>
                                        <td>${hd.sdt}</td>
                                        <td>${hd.trangThai}</td>
                                        <td>
                                            <a class="btn btn-danger" href="/bumblebee/don-hang/tao-doi-tra/${hd.maHoaDon}">
                                                Chọn
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <button type="button" id="startButton" onclick="startScan()" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop">
                Quet QR
            </button>

            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="ban-hang vid">
                                <video
                                        style="border: 1px solid"
                                        id="scanHoaDon"
                                        autoplay="true"
                                        width="270px"
                                        height="150px"
                                ></video>
                            </div>
                            <div id="output"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="stopButton" onclick="stopScan()" class="btn btn-secondary"
                                    data-bs-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="status">
        <ul class="nav justify-content-center bg-gradient-light nav-pills">
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'all' ? 'active' : ''}" aria-current="page"
                   href="/bumblebee/doi-hang/list-thanh-cong">Đổi trả thành công
                    <span class="badge text-bg-secondary">${countHD}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'cho-xac-nhan' ? 'active' : ''}"
                   href="/bumblebee/doi-hang/list-huy">Hủy đổi trả<span
                        class="badge text-bg-secondary">${countHDCho}</span></a>
            </li>
        </ul>
    </div>
    <br>
    <div class="content-listDoiTra">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Mã hóa đơn</th>
                <th scope="col">Nhân viên</th>
                <th scope="col">Khách hàng</th>
                <th scope="col">Ngày đổi trả</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach varStatus="i" items="${listDoiTra}" var="dt">
                    <tr>
                        <td>${i.count}</td>
                        <td>${dt.hoaDon.maHoaDon}</td>
                        <td>${dt.nhanVien.ho} ${dt.nhanVien.tenDem} ${dt.nhanVien.ten}</td>

                        <td>${dt.hoaDon.tenNguoiNhan}</td>
                        <td>
                            <a id="openDoiTra"
                               href="${dt.hoaDon.id}"
                               onclick="showDoiTraCT(`${dt.hoaDon.id}`)"
                               class="btn btn-primary"
                               type="submit"
                               data-bs-toggle="modal"
                               data-bs-target="#modalDoiTraCT" style="border-radius: 20px; margin-top: 5px;"
                            >Chi Tiết
                            </a>
                            <div class="modal fade" id="modalDoiTraCT" tabindex="-1"
                                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title fs-5" id="exampleModalLabel">Hóa đơn mua</h4>
                                        </div>
                                        <div class="modal-body">
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col">Số lượng</th>
                                                    <th scope="col">Đơn giá</th>
                                                    <th scope="col">Màu sắc</th>
                                                    <th scope="col">Kích cỡ</th>
                                                    <th scope="col">Trạng thái</th>
                                                </tr>
                                                </thead>
                                                <tbody id="tableHoaDonCT">
                                                </tbody>
                                            </table>
                                            <hr>
                                            <div><p style="text-align: center">Sản phẩm đổi trả</p></div>
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Tên sản phẩm</th>
                                                    <th scope="col">Số lượng</th>
                                                    <th scope="col">Đơn giá</th>
                                                    <th scope="col">Màu sắc</th>
                                                    <th scope="col">Kích cỡ</th>
                                                    <th scope="col">Lý do</th>
                                                    <th scope="col">Trạng thái</th>
                                                </tr>
                                                </thead>
                                                <tbody id="tableDoiTra">
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
    </div>
</div>