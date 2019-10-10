<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2019/9/27
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    // http://localhost:8080/logistics/
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,permission-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
    <meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
    <form  class="form form-horizontal" action="${empty permission ?'permission/insert.do' : 'permission/update.do'}" id="permissionForm">
        <!-- 修改用户隐藏域 id -->
        <input type="hidden" name="permissionId" value="${permission.permissionId}">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${permission.name}" placeholder="请输入权限名称" id="name" name="name">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限表达式：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${permission.expression}" placeholder="" id="expression" name="expression">
            </div>
        </div>

        <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">权限地址：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${permission.url}" placeholder="" id="url" name="url">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限类型：</label>
            <div class="formControls col-xs-8 col-sm-9 skin-minimal">
                <div class="radio-box">
                    <input name="type" type="radio" ${permission.type eq 'permission' ? 'checked' : ''} value="permission">
                    <label for="sex-1">普通权限</label>
                </div>
                <div class="radio-box">
                    <input name="type" type="radio" ${permission.type eq 'menu' ? 'checked' : ''} value="menu">
                    <label for="sex-2">菜单权限</label>
                </div>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">父权限：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="parentId" size="1">
				<option value="">顶级权限</option>
				<c:forEach items="${permissions}" var="p">
				    <option ${p.permissionId eq permission.parentId ? 'selected' : ''} value="${p.permissionId}">${p.name}</option>
				</c:forEach>
			</select>
			</span>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    $(function(){
        //jquery.validate插件表单验证
        $("#permissionForm").validate({
            rules:{
                name:{
                    required:true,
                },
                expression:{
                    required:true,
                },
                type:"required"

            },
            messages:{
                name:{
                    required:"权限名称不能为空",
                },
                expression:{
                    required:"权限表达式不能为空",
                },
                type:{
                    required:"权限类型不能为空",
                },


            },
            submitHandler:function (form) {
                var jqForm = $(form);
                console.log(jqForm);
                //提交，异步
                jqForm.ajaxSubmit(function (data) {

                    layer.msg(data.msg,{icon:data.code,time:1000},function () {
                        //刷新，关闭
                        window.parent.refreshTable();
                        parent.layer.closeAll();
                    });

                });
            }
        });


    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
