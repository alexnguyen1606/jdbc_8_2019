<%@include file="/common/taglib.jsp" %>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/8/2019
  Time: 1:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%@include file="/common/admin/ace-setting.jsp"%>
    <div class="page-header">
        <h1>
            Dashboard
            <small>
                <a href="/admin-building?action=LIST"><i class="ace-icon fa fa-angle-double-right"></i>
                    Tòa nhà</a>
            </small>
            <small>
                <a href="/admin-building?action=EDIT"><i class="ace-icon fa fa-angle-double-right"></i>
                    EDIT</a>
            </small>
        </h1>
    </div><!-- /.page-header -->
</div>
<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal" role="form" id="formEdit">
            <input type="hidden" id="id" name="id" value="${model.id}">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name">Tên sản phẩm </label>

                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${model.name}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="buildingArea">Diện tích sàn </label>

                <div class="col-sm-9">
                    <input type="number" id="buildingArea" name="buildingArea" value="${model.buildingArea}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="district">Quận</label>

                <div class="col-sm-3">
                    <select class="form-control" name="district" id="district">
                        <option value="">Chọn quận</option>
                        <c:forEach var="item" items="${districts}">
                            <option value="${item.key}" ${item.key == model.district ? "selected":""}>${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="ward">Phường </label>

                <div class="col-sm-9">
                    <input type="text" id="ward" name="ward" value="${model.ward}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="street">Đường</label>

                <div class="col-sm-9">
                    <input type="text" id="street" name="street" value="${model.street}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="areaRent">Diện tích thuê</label>

                <div class="col-sm-9">
                    <input type="text" id="areaRent" name="areaRent" value="${model.areaRent}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="buildingTypes">Loại toàn nhà</label>

                <div class="col-sm-9">
                    <c:forEach items="${buildingTypes}" var="item">
                        <label class="checkbox-inline"><input type="checkbox"
                                                              id="buildingTypes" name="buildingTypes" ${fn:contains(model.type,item.key) ? "checked" :""}  value="${item.key}">${item.value}</label>
                    </c:forEach>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="costRent">Giá thuê</label>

                <div class="col-sm-9">
                    <input type="number" id="costRent" name="costRent" value="${model.costRent}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="serviceCost">Giá dịch vụ</label>

                <div class="col-sm-9">
                    <input type="number" id="serviceCost" name="serviceCost" value="${model.serviceCost}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement">Số tầng hầm</label>

                <div class="col-sm-9">
                    <input type="number" id="numberOfBasement" name="numberOfBasement" value="${model.numberOfBasement}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="managerName">Tên quản lý</label>

                <div class="col-sm-9">
                    <input type="text" id="managerName" name="managerName" value="${model.managerName}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="managerPhone">Số điện thoại quản lý</label>

                <div class="col-sm-9">
                    <input type="text" id="managerPhone" name="managerPhone" value="${model.managerPhone}" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-5">
                    <button type="button" class="btn btn-sm btn-success " id="btnAddBuilding" style="left:450px;">
                        Submit
                        <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                    </button>
                </div>

            </div>
        </form>
    </div><!-- /.col -->
</div><!-- /.row -->
</body>
</html>
