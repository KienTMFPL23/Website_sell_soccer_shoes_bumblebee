<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="content">
    <h1 style="text-align: center">Danh sách hóa đơn đổi trả</h1>
    <div class="row">
        <div class="col-lg-6">
            <button type="button" id="openCam" onclick="openWebcam()" class="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop">
                <i class="bi bi-qr-code-scan"></i>
                Quét QR
            </button>
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
                 data-bs-keyboard="false" tabindex="-1"
                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="vid">
                                <video
                                        style="border: 1px solid"
                                        id="camHoaDon"
                                        autoplay="camHoaDon"
                                        width="270px"
                                        height="150px"
                                ></video>
                            </div>
                            <div id="output"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" id="closeCam" onclick="closeWebcam()"
                                    class="btn btn-secondary"
                                    data-bs-dismiss="modal">Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">

        </div>
    </div>
    <br>
    <div class="status">
        <ul class="nav justify-content-center bg-gradient-light nav-pills">
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'all' ? 'active' : ''}" aria-current="page"
                   href="/bumblebee/doi-hang/list-tra-hang">Danh sách trả hàng
                    <span class="badge text-bg-secondary">${countHD}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'cho-xac-nhan' ? 'active' : ''}"
                   href="/bumblebee/doi-hang/list-doi-hang">Danh sách đổi hàng<span
                        class="badge text-bg-secondary">${countHDCho}</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  ${donHang == 'cho-xac-nhan' ? 'active' : ''}"
                   href="/bumblebee/doi-hang/list-doi-hang">Danh sách sản phẩm lỗi<span
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
                            <a class="btn btn-primary" href="/bumblebee/doi-hang/chi-tiet/${dt.hoaDon.id}">Chi tiết</a>
<%--                            <a id="openDoiTra"--%>
<%--                               onclick="showDoiTraCT(`${dt.hoaDon.id}`)"--%>
<%--                               class="btn btn-primary"--%>
<%--                               type="submit"--%>
<%--                               data-bs-toggle="modal"--%>
<%--                               data-bs-target="#modalDoiTraCT" style="border-radius: 20px; margin-top: 5px;"--%>
<%--                            >Chi Tiết--%>
<%--                            </a>--%>
                            <div class="modal fade" id="modalDoiTraCT" tabindex="-1"
                                 aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h2 class="modal-title fs-5" id="exampleModalLabel">Đơn đổi trả sản phẩm</h2>
                                        </div>
                                        <strong style="margin-left: 15px">Nhân viên xác nhận: ${dt.hoaDon.nhanVien.ten}</strong>
                                        <strong style="margin-left: 15px">Khách hàng: ${dt.hoaDon.tenNguoiNhan}</strong>
                                        <strong style="margin-left: 15px">Số điện thoại: ${dt.hoaDon.sdt}</strong>
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
