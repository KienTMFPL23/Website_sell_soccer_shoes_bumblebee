<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<div class="content">
    <h1 style="text-align: center">Danh sách hóa đơn đổi hàng</h1>
    <div class="row">
        <div class="col-lg-6">
            <a href="/bumblebee/doi-hang" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalHoaDon">Đổi
                hàng</a>
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
                                            <a class="btn btn-danger" href="/bumblebee/don-hang/${hd.maHoaDon}">
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
        <ul class="nav nav-pills nav-fill gap-2 p-1 small  rounded-5 shadow-sm" id="pillNav2" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-choxacnhan-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-choxacnhan" type="button" role="tab" aria-controls="pills-choxacnhan"
                        aria-selected="false">Chờ xác nhận đổi hàng
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-chuanbi-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-chuanbi"
                        type="button" role="tab" aria-controls="pills-chuanbi" aria-selected="false">Từ chối đổi hàng
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="pills-danggiao-tab" data-bs-toggle="pill"
                        data-bs-target="#pills-danggiao"
                        type="button" role="tab" aria-controls="pills-danggiao" aria-selected="false">Đổi thành công
                </button>
            </li>
        </ul>
    </div>
</div>