<%@include file="/common/taglib.jsp" %>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/11/2019
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="customerURL" value="/admin-customer"></c:url>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                    Khách hàng</a>
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
                    <form action="${customerURL}" method="get" id="formSearchBuilding">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="fullName">Tên khách hàng</label>
                                <input class="form-control" name="fullName" id="fullName" value="${model.fullName}">
                            </div>
                            <div class="col-sm-6">
                                <label for="email">Email</label>
                                <input class="form-control" name="email" id="email" value="${model.email}">
                            </div>
                            <div class="col-sm-6">
                                <label for="phoneNumber">Số điện thoại</label>
                                <input class="form-control" name="phoneNumber" id="phoneNumber" value="${model.phoneNumber}">
                            </div>
                            <div class="col-sm-6">
                                <label for="userId">Chọn nhân viên phụ trách</label>
                                <select id="userId" name="userId" class="form-control">
                                    <option value="">Chọn nhân viên</option>
                                    <c:forEach var="item" items="${users}">
                                        <option value="${item.id}" ${item.id == model.userId ? "selected":""}>${item.fullName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="">
                            <button type="submit" id="btnSearchCustomer" class="btn btn-sm btn-success">
                                Tìm kiếm
                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                            </button>
                        </div>
                        <input type="hidden" name="action" value="LIST">
                    </form>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="pull-right">
                    <a href="/admin-building?action=EDIT" class="btn btn-primary " data-toggle="tooltip" title="Thêm khách hàng"><i class="fa fa-plus"></i></a>
                    <button class="btn btn-danger "  id="btnDeleteCustomer" data-toggle="tooltip" title="Xóa khách hàng"><i class="fa fa-trash" ></i></button>
                </div>
            </div>
            <div class="col-sm-12">
                <table class="table table-striped table-hover" id="buildingList">
                    <thead>
                    <th></th>
                    <th>Tên khách hàng</th>
                    <th>Yêu cầu</th>
                    <th>Email</th>
                    <th>Số điện thoại</th>
                    <th>Công ty</th>
                    <th>Thao tác</th>
                    </thead>
                    <tbody id="bodyBuildingList">
                    <c:forEach items="${customers}" var="item">
                        <tr>
                            <td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"></td>
                            <td>${item.fullName}</td>
                            <td>${item.request}</td>
                            <td>${item.email}</td>
                            <td>${item.phoneNumber}</td>
                            <td>${item.companyName}</td>
                            <td>
                                <button class="btn btn-primary" onclick="" data-toggle="tooltip"
                                        title="Giao tòa nhà ">
                                    <i class="fa fa-bars"></i></button>
                                <a href="${customerURL}?action=EDIT&id=${item.id}" class="btn btn-success"  data-toggle="tooltip"
                                   title="Chỉnh sửa khách hàng ">
                                    <i class="fa fa-pencil"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div> <!-- end row -->
</div> <!--  end page content-->
</body>
</html>
