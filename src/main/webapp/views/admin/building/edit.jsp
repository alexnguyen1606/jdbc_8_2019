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
<%@include file="/common/admin/ace-setting.jsp"%>
    <div class="page-header">
        <h1>
            Dashboard
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                Tòa nhà
            </small>
            <small>
                <i class="ace-icon fa fa-angle-double-right"></i>
                Edit
            </small>
        </h1>
    </div><!-- /.page-header -->
</div>
<div class="row">
    <div class="col-sm-12">
        <form class="form-horizontal" role="form" id="formEdit">
            <input type="hidden" id="id" name="id" value="">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name">Tên sản phẩm </label>

                <div class="col-sm-9">
                    <input type="text" id="name" name="name" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="buildingArea">Diện tích sàn </label>

                <div class="col-sm-9">
                    <input type="number" id="buildingArea" name="buildingArea" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="district">Quận</label>

                <div class="col-sm-3">
                    <select class="form-control" name="district" id="district">
                        <option value="QUAN_1">Quận 1</option>
                        <option value="QUAN_7">Quận 7</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="ward">Phường </label>

                <div class="col-sm-9">
                    <input type="text" id="ward" name="ward" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="street">Đường</label>

                <div class="col-sm-9">
                    <input type="text" id="street" name="street" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="areaRent">Diện tích thuê</label>

                <div class="col-sm-9">
                    <input type="text" id="areaRent" name="areaRent" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="buildingTypes">Loại toàn nhà</label>

                <div class="col-sm-9">
                    <input type="checkbox" id="buildingTypes" name="buildingTypes" value="NGUYEN_CAN" class="" placeholder="" />Nguyên Căn
                    <input type="checkbox" id="buildingTypes" name="buildingTypes" value="TANG_TRET" placeholder="" class="" />Tầng trệt
                    <input type="checkbox" id="buildingTypes" name="buildingTypes" value="NOI_THAT" placeholder="" class="" />Nội thất
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="costRent">Giá thuê</label>

                <div class="col-sm-9">
                    <input type="number" id="costRent" name="costRent" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="serviceCost">Giá dịch vụ</label>

                <div class="col-sm-9">
                    <input type="number" id="serviceCost" name="serviceCost" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement">Số tầng hầm</label>

                <div class="col-sm-9">
                    <input type="number" id="numberOfBasement" name="numberOfBasement" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="managerName">Tên quản lý</label>

                <div class="col-sm-9">
                    <input type="text" id="managerName" name="managerName" placeholder="" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="managerPhone">Số điện thoại quản lý</label>

                <div class="col-sm-9">
                    <input type="text" id="managerPhone" name="managerPhone" placeholder="" class="col-xs-10 col-sm-5" />
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
