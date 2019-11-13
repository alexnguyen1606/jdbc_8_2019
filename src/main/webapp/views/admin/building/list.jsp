<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/7/2019
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="buildingURL" value="/admin-building"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>

    <ul class="breadcrumb">
        <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="/">Home</a>
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
                <a href="${buildingURL}?action=LIST"><i class="ace-icon fa fa-angle-double-right"></i>
                Tòa nhà</a>
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
                                    <input class="form-control" name="name" id="name" value="${model.name}" placeholder="">
                                </div>
                                <div class="col-sm-6">
                                    <label for="buildingArea">Diện tích sàn</label>
                                    <input class="form-control" name="buildingArea" id="buildingArea" value="${model.buildingArea}" >
                                </div>
                                <div class="col-sm-4">
                                    <label for="district">Quận hiện có</label>
                                    <select id="district" name="district" class="form-control">
                                        <option value="">Chọn quận</option>
                                        <c:forEach var="item" items="${districts}">
                                            <option value="${item.key}" ${item.key == model.district ? "selected":""} >${item.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <label for="ward">Phường</label>
                                    <input class="form-control" name="ward" id="ward" value="${model.ward}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="street">Đường</label>
                                    <input class="form-control" name="street" id="street" value="${model.street}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="numberOfBasement">Số tầng hầm</label>
                                    <input type="number" class="form-control" name="numberOfBasement" id="numberOfBasement" value="${model.numberOfBasement}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="rentAreaFrom">Diện tích thuê từ</label>
                                    <input type="number" class="form-control" name="rentAreaFrom" id="rentAreaFrom" value="${model.rentAreaFrom}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="rentAreaTo">Diện tích thuê đến đến</label>
                                    <input type="number" class="form-control" name="rentAreaTo" id="rentAreaTo" value="${model.rentAreaTo}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="costRentFrom">Giá thuê từ</label>
                                    <input type="number" class="form-control" name="costRentFrom" id="costRentFrom" value="${model.costRentFrom}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="costRentTo">Giá thuê đến</label>
                                    <input type="number" class="form-control" name="costRentTo" id="costRentTo" value="${model.costRentTo}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="managerName">Tên quản lý</label>
                                    <input class="form-control" name="managerName" id="managerName" value="${model.managerName}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="managerPhone">Điện thoại quản lý</label>
                                    <input class="form-control" name="managerPhone" id="managerPhone" value="${model.managerPhone}">
                                </div>
                                <div class="col-sm-4">
                                    <label for="staffId">Chọn nhân viên phụ trách</label>
                                    <select id="staffId" name="staffId" class="form-control">
                                        <option value="">Chọn nhân viên</option>
                                        <c:forEach var="item" items="${users}">
                                            <option value="${item.id}" ${item.id==model.staffId ? "selected" : ""}>${item.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-sm-12">
                                    <c:forEach items="${buildingTypes}" var="item">
                                        <label class="checkbox-inline"><input type="checkbox" id="buildingTypes" name="buildingTypes" ${fn:contains(model.type,item.key) ? "checked" :""}  value="${item.key}">${item.value}</label>
                                    </c:forEach>
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
                <button class="btn btn-danger "  id="btnDeleteBuilding" data-toggle="tooltip" title="Xóa sản phẩm"><i class="fa fa-trash" ></i></button>
            </div>
        </div>
        <div class="col-sm-12">
            <table class="table table-striped table-hover" id="buildingList">
                <thead>
                <th></th>
                <th>Tên sản phẩm</th>
                <th>Địa chỉ</th>
                <th>Tên quản lý</th>
                <th>D.T Sàn</th>
                <th>Giá thuê</th>
                <th>Loại tòa nhà</th>
                <th>Diện tích thuê</th>
                <th>Điện thoại quản lý</th>
                <th>Thao tác</th>
                </thead>
                <tbody id="bodyBuildingList">
                <c:forEach items="${buildings}" var="item">
                    <tr>
                        <td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"></td>
                        <td>${item.name}</td>
                        <td>${item.address}</td>
                        <td>${item.managerName}</td>
                        <td>${item.buildingArea}</td>
                        <td>${item.costRent}</td>
                        <td>${item.areaRent}</td>
                        <td>${item.type}</td>
                        <td>${item.managerPhone}</td>
                        <td>
                            <button class="btn btn-primary" onclick="assignmentBuilding(${item.id})" data-toggle="tooltip"
                                    title="Giao tòa nhà ">
                                <i class="fa fa-bars"></i></button>
                            <a href="${buildingURL}?action=EDIT&id=${item.id}" class="btn btn-success"  data-toggle="tooltip"
                                title="Chỉnh sửa tòa nhà ">
                                <i class="fa fa-pencil"></i>
                            </a>    
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
                     <tr>
                    <th>Check</th>
                    <th>Tên nhân viên</th>
                    </tr>   
                </thead>
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
