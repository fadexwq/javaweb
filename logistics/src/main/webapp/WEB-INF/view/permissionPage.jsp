<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2019/9/25
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    // http://localhost:8080/logistics/
%>
<!DOCTYPE HTML>
<html>
<head>
    <!-- 设置页面的 基本路径，页面所有资源引入和页面的跳转全部基于 base路径 -->
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,permission-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="lib/bootstrap-table/bootstrap-table.min.css" />
    <title>权限列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 权限管理 <span class="c-gray en">&gt;</span> 权限列表
</nav>
<div class="page-container">

	<span class="l" id="toolbar">
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
		<a href="javascript:;" onclick="permission_add()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加权限</a>
	</span>


    <!-- bootstrap-table 显示数据的表格 -->
    <table  id="permissionTable"></table>

</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>


<script type="text/javascript">
    //文档加载完毕以后再执行内部代码
    $(function() {
        $('#permissionTable').bootstrapTable({
            url: 'permission/list.do',//ajax请求的url地址
            method:'get',
            /*
                ajax请求以后回调函数的处理
                后台使用返回的PageInfo对象中的 结果 级的key是list，总条数是total
                而前台bootstrapTable插件需要的数据的key叫做rows ，总条数也是叫做total
                那么出现一个问题 : 总条数的key能对上，结果集对不上，就需要在ajax请求完成回调
                responseHandler 这个函数方法处理一下
                并且在自定义一个 json,rows做为key，返回json的 list作为值
                    total：还是total
                这样才能满足 bootstrapTable插件数据的需要
            */
            responseHandler: function(res) {
                /*
                    res: 后台分页对象PageInfo返回对应的json对象
                    res.list : 结果集
                    res.total : 总记录数
                */
                var data =  {rows: res.list,total: res.total};

                return data;
            },
            pagination: true,
            toolbar: "#toolbar",//顶部显示的工具条（添加和批量删除的）
            contentType: 'application/x-www-form-urlencoded',//条件搜索的时候ajax请求给后台数据的数据类型（条件搜索post提交必须设置）
            search: true,//是否显示搜索框
            pageNumber: 1,//默认的页面 第一页
            pageSize: 10,//默认的每页条数
            //pageList:[10,25,50,100],//每页能显示的条数
            sidePagination: "server",//是否是服务器分页，每次请求都是对应的10条数据，下一页发送ajax请求
            paginationHAlign: 'right', //底部分页条
            //showToggle: true, //是否显示详细视图和列表视图的切换按钮
            //cardView: false, //是否显示详细视图
            //showColumns: true, //是否显示所有的列
            showRefresh: true, //是否显示刷新按钮
            columns: [ //表格显示数据对应的表头设置，
                { checkbox: true},//是否显示前台的复选框（多选）
                /*
                    每列数据的表头的设置
                    filed:返回json数据对应数据的key
                    title:表头要显示的名
                */
                {field: 'id',title: '编号'},
                {field: 'name',title: '权限名称'},
                {field: 'url',title: '权限地址'},
                {field: 'expression',title: '权限表达式'},
                {field: 'type',title: '权限类型',formatter:menuFormatter},
                {field: 'parentName',title: '父权限'},
                //操作列的设置（删除，修改）
                /*
                formatter: 格式化这一行，回调一个函数
                */
                {
                    field:'id',
                    title:'操作',
                    align:'center',
                    formatter:operationFormatter

                }
            ],
            /*发送请求的参数，
                params: bootstrapTable的插件内部参数对象包含如下参数
                limit, offset, search, sort
                limit：每页条数
                offset：每页的结束位置
                search:搜索框对应的值
                sort：排序
            */

            queryParams: function(params) {
                console.log("params :",params);


                var paramData = {pageNum:params.offset / params.limit + 1,
                    pageSize:params.limit,
                    keyword:params.search}

                return paramData;

                //此方法在用户分页或者搜索的时候回自动发送ajax请求调用，并把对应的参数传递给后台
                /* return {
                    pageNum: params.offset / params.limit + 1, //页码
                    pageSize: params.limit, //页面大小
                    keyword: params.search
                }; */
            },
        })
    });


    function menuFormatter(value,row,index){
        if ("menu" == value) {
            return "<span style='color:orangered'>菜单权限</span>";
        }
        return "<span style='color:deepskyblue'>普通权限</span>"
    }
    /*
    *
    * value :当前行 field的值
    * index ：索引 从0开始
    * row ：每一行的对象
    */
    function operationFormatter(value,row,index){
        //console.log(value,row,index);

        var html = "<span onclick='permission_edit("+value+")' style='cursor: pointer;color:blue' class='glyphicon glyphicon-edit'></span>&nbsp;&nbsp;&nbsp;";

        html += "<span onclick='permission_del("+value+")' style='cursor: pointer;color:red' class='glyphicon glyphicon-trash'></span>"

        return html;
    }


    /**
     * 刷新表格方法，在删除，修改，添加成功以后调用
     */
    function refreshTable(){
        $("#permissionTable").bootstrapTable("refresh");
    }


</script>






<script type="text/javascript">


    /*权限-增加*/
    function permission_add(){
        layer_show("添加权限","permission/add.do",800,500);
    }

    /*权限-编辑*/
    function permission_edit(id){
        layer_show("修改权限","permission/add.do?permissionId="+id,800,500);
    }

    /*权限-删除*/
    function permission_del(id){
        layer.confirm('确认要删除吗？',function(index){
            $.post("permission/delete.do",{permissionId:id},function(data){
                //弹出一个提示消息
                layer.msg(data.msg, {time: 1000, icon:data.code});
                //如果是1说明删除成功，刷新表格 ，调用 bootstrap插件的刷新一下表格
                if(data.code == 1){
                    refreshTable();
                }
            });
        });
    }



</script>
</body>
</html>
