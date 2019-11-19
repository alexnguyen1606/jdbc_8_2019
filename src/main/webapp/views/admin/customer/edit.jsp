<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/16/2019
  Time: 1:15 PM
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
                <form class="form-horizontal" role="form" id="formEdit">
                    <input type="hidden" id="id" name="id" value="${model.id}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="fullName">Tên khách hàng</label>

                        <div class="col-sm-9">
                            <input type="text" id="fullName" name="fullName" value="${model.fullName}" placeholder="" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="phoneNumber">Số điện thoại </label>

                        <div class="col-sm-9">
                            <input type="text" id="phoneNumber" name="phoneNumber" value="${model.phoneNumber}" placeholder="" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="email">Email </label>

                        <div class="col-sm-9">
                            <input type="text" id="email" name="email" value="${model.email}" placeholder="" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="companyName">Tên công ty </label>

                        <div class="col-sm-9">
                            <input type="text" id="companyName" name="companyName" value="${model.companyName}" placeholder="" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="request">Yêu cầu của khách hàng </label>

                        <div class="col-sm-9">
                            <input type="text" id="request" name="request" value="${model.request}" placeholder="" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="note">Note</label>

                        <div class="col-sm-9">
                            <input type="text" id="note" name="note" value="${model.note}" placeholder="" class="col-xs-10 col-sm-5" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-5">
                            <button type="button" class="btn btn-sm btn-success " id="btnAddCustomer" style="left:450px;">
                                Submit
                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                            </button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
</div>
</body>
</html>
