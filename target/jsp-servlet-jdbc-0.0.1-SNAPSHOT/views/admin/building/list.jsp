<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/7/2019
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingURL" value="/admin-building"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>

    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="#">Home</a>
        </li>
        <li class="active">Dashboard</li>
    </ul><!-- /.breadcrumb -->
</div>
<div class="page-content">
    <%@ include file="/common/admin/ace-setting.jsp"%>
    <div class="page-header">
        <h1>
            Dashboard
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                overview &amp; stats
            </small>
        </h1>
    </div><!-- /.page-header -->

    <div class="row">
        <div class="col-sm-12">
            <div class="widget-box">
                <div class="widget-header">
                    <h4 class="widget-title">Tìm kiếm</h4>
                    <div class="widget-toolbar">
                        <a href="#" data-action="collapse">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <form action="${buildingURL}" method="get" id="formSearchBuilding">
                            <div class="row">
                                <div class="col-sm-6">
                                    <label for="name">Tên sản phẩm</label>
                                    <input class="form-control" name="name" id="name" placeholder="">
                                </div>
                                <div class="col-sm-6">
                                    <label for="buildingArea">Diện tích sàn</label>
                                    <input class="form-control" name="buildingArea" id="buildingArea" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="district">Quận hiện có</label>
                                    <select id="district" name="district" class="form-control">
                                        <option value="">Chọn quận</option>
                                        <option value="QUAN_1">Quận 1</option>
                                        <option value="QUAN_7">Quận 7</option>
                                        <option value="QUAN_2">Quận 2</option>

                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <label for="ward">Phường</label>
                                    <input class="form-control" name="ward" id="ward" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="street">Đường</label>
                                    <input class="form-control" name="street" id="street" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="numberOfBasement">Số tầng hầm</label>
                                    <input type="number" class="form-control" name="numberOfBasement" id="numberOfBasement" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="rentAreaFrom">Diện tích thuê từ</label>
                                    <input type="number" class="form-control" name="rentAreaFrom" id="rentAreaFrom" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="rentAreaTo">Diện tích thuê đến đến</label>
                                    <input type="number" class="form-control" name="rentAreaTo" id="rentAreaTo" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="costRentFrom">Giá thuê từ</label>
                                    <input type="number" class="form-control" name="costRentFrom" id="costRentFrom" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="costRentTo">Giá thuê đến</label>
                                    <input type="number" class="form-control" name="costRentTo" id="costRentTo" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="managerName">Tên quản lý</label>
                                    <input class="form-control" name="managerName" id="managerName" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="managerPhone">Điện thoại quản lý</label>
                                    <input class="form-control" name="managerPhone" id="managerPhone" placeholder="">
                                </div>
                                <div class="col-sm-4">
                                    <label for="staffId">Chọn nhân viên phụ trách</label>
                                    <select id="staffId" name="staffId" class="form-control">
                                        <option value="">Chọn nhân viên</option>
                                        <option value="volvo">Volvo</option>
                                        <option value="saab">Saab</option>
                                        <option value="opel">Opel</option>
                                        <option value="audi">Audi</option>
                                    </select>
                                </div>
                                <div class="col-sm-5">
                                    <label class="checkbox-inline"><input type="checkbox" id="buildingTypes" name="buildingTypes" value="TANG_TRET">Tầng trệt</label>
                                    <label class="checkbox-inline"><input type="checkbox" id="buildingTypes" name="buildingTypes" value="NGUYEN_CAN">Nguyên căn</label>
                                        <label class="checkbox-inline"><input type="checkbox" id="buildingTypes" name="buildingTypes" value="NOI_THAT">Nội thất</label>
                                </div>
                            </div>
                            <br>
                            <div class="">
                                <button type="button" id="btnSearchBuilding" class="btn btn-sm btn-success">
                                    Tìm kiếm
                                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                </button>
                            </div>
                            <input type="hidden" name="action" value="LIST">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="pull-right">
                <a href="/admin-building?action=EDIT" class="btn btn-primary " data-toggle="tooltip" title="Thêm sản phầm"><i class="fa fa-plus"></i></a>
                <button class="btn btn-danger " id="btnDeleteBuilding" data-toggle="tooltip" title="Xóa sản phẩm"><i class="fa fa-trash" ></i></button>
            </div>
        </div>
        <div class="col-sm-12">
            <table class="table table-striped table-hover" id="buildingList">
                <thead>
                <th></th>
                <th>Ngày</th>
                <th>Tên sản phẩm</th>
                <th>Địa chỉ</th>
                <th>Tên quản lý</th>
                <th>D.T Sàn</th>
                <th>Giá thuê</th>
                <th>Phí dịch vụ</th>
                <th>Điện thoại quản lý</th>
                <th>Thao tác</th>
                </thead>
                <tbody>
                <c:forEach items="${buildings}" var="item">
                    <tr>
                        <td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"></td>
                        <td> <fmt:formatDate value="${item.createdDate}" type="date" pattern="dd-MM-yyyy"/> </td>
                        <td>${item.name}</td>
                        <td>89 Cao Thăng</td>
                        <td>${item.managerName}</td>
                        <td>${item.buildingArea}</td>
                        <td>${item.costRent}</td>
                        <td>${item.serviceCost}</td>
                        <td>${item.managerPhone}</td>
                        <td>
                            <button class="btn btn-primary" onclick="assignmentBuilding(${item.id})" data-toggle="tooltip"
                                    title="Giao tòa nhà ">
                                <i class="fa fa-bars"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div><!-- /.row -->
</div><!-- /.page-content -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-hover" id="staffList">
                    <thead>
                    <th></th>
                    <th>Tên nhân viên</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="checkbox" value="1" id="checkbox_1" ></td>
                        <td>Nhân viên a</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" value="2" id="checkbox_2" ></td>
                        <td>Nhân viên a</td>
                    </tr>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
</body>
</html>
