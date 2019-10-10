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
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    // http://localhost:8080/logistics/
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,role-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"/>
    <title>添加管理员 - 管理员管理 - H-ui.admin v3.1</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" action="${empty role ?'role/insert.do' : 'role/update.do'}" id="roleForm">
        <!-- 修改用户隐藏域 id -->
        <input type="hidden" name="roleId" value="${role.roleId}">
        <%--权限id的隐藏域--%>
        <input id="permissionIds" type="hidden" name="permissionIds">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${role.rolename}" placeholder="请输入角色名称" id="rolename"
                       name="rolename">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="remark" cols="" rows="" class="textarea" placeholder="请输入角色描述">${role.remark}</textarea>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">系统权限：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <%--zTree生成树的列表--%>
                <ul id="permissionTree" class="ztree"></ul>
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
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

    /*创建zTree的设置对象(json)
        check指可选择的复选框
        simpleData指简单json，因为有两种
        async是获取url的数据，json
    */
    var setting = {
        check:{
            enable:true
        },
        data:{
            simpleData:{
                enable:true
            }
        },
        async:{
            enable:true,
            url:"permission/getAllPermission.do"
        },
        // 在zTree异步加载完毕之后回调函数
        callback:{
            onAsyncSuccess:zTreeOnAsyncSuccess
        }
    };

    function zTreeOnAsyncSuccess(event,treeId,treeNode,msg){
        var permissionIds = "${role.permissionIds}";//获取角色的权限
        var treeObj = $.fn.zTree.getZTreeObj("permissionTree");//获取树对象

        var permissionIdsArr = permissionIds.split(",");
        for (var i = 0; i < permissionIdsArr.length; i++) {
            var permissionId = permissionIdsArr[i];//获取每个id
            var node = treeObj.getNodeByParam("id",permissionId,null);//获取对应节点

            //让其节点选中，(如果有子节点不能选中，否则所有子节点全部选中)
            if (node.children == undefined){
                treeObj.checkNode(node,true,true);
            }
        }
    };

    $(function () {
        /*初始化zTree*/
        $.fn.zTree.init($("#permissionTree"),setting)
    });




    $(function () {
        //jquery.validate插件表单验证
        $("#roleForm").validate({
            rules: {
                rolename: {
                    required: true
                }
            },
            messages: {
                rolename: {
                    required: "角色名称不能为空"
                }
            },
            submitHandler: function (form) {

                /*
                提交表单步骤：
                    获取zTree数据，组装成字符串
                    在表中添加一个隐藏域，zTree数据设置到隐藏域
                    正常提交
                */
                getCheckedData();

                var jqForm = $(form);
                //提交，异步
                jqForm.ajaxSubmit(function (data) {
                    layer.msg(data.msg, {icon: data.code, time: 1000}, function () {
                        //刷新，关闭
                        window.parent.refreshTable();
                        parent.layer.closeAll();
                    });
                });
            }
        });
    });


    /**
     * 获取选中的zTree数据
     *
     */
    function getCheckedData(){
        var treeObj = $.fn.zTree.getZTreeObj("permissionTree");//获取树对象
        var nodes = treeObj.getCheckedNodes(true);//获取选中节点
        var permissionIdsArr = [];//用来添加权限id的数组
        for (var i = 0; i < nodes.length; i++) {
            var node = nodes[i];//获取每个节点
            var permissionId = node.id;//获取每个节点的id
            permissionIdsArr.push(permissionId);//添加进去
        }
        var permissionIds = permissionIdsArr.join(",");
        $("#permissionIds").val(permissionIds);
    }


</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
