<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'level.jsp' starting page</title>
  </head>
  <script src="js/jquery.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  		//页面就绪时，查询所有省的信息
  		$.ajax({
  			type:"get",
  			url:"province",
  			async:true,
  			dataType:"json",
  			success:function(data){
  				for(var i = 0;i < data.length;i++){
  					var opt = "<option value='"+data[i].code+"'>"+data[i].name+"</option>"
					$("#pid").append(opt);
  				}
  			}
  		})
  		
  		$("#pid").on("change",function(){
  			var pcode = $("#pid").val();
  			
  			$.ajax({
  				//异步、动态请求数据，将省数据显示
  				type:"get",
  				url:"city",
  				async:true,
  				data:{"pcode":pcode},
  				dataType:"json",
  				success:function(data){
  					$("#cid").html("<option>--请选择--</option>");
  					$("#tid").html("<option>--请选择--</option>");
  					for(var i = 0;i < data.length;i++){
  						var opt = "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
  						$("#cid").append(opt);
  					}
  				}
  			})
  		})
  		
  		
  		$("#cid").on("change",function(){
  			var ccode = $("#cid").val();
  			
  			$.ajax({
  				type:"get",
				url:"town",
				data : {"ccode":ccode},
				async:true,
				dataType:"json",
				success:function(data){
					$("#tid").html("<option>--请选择--</option>");
					for (var i = 0; i < data.length; i++) {
						var opt = "<option value='"+data[i].code+"'>"+data[i].name+"</option>";
						$("#tid").append(opt);
					}
				}
  			})
  		})
  		
  		
  	})
  
  
  </script>
  
  <body>
  <select id="pid">
  	<option>--请选择--</option>
  </select>
  <select id="cid">
  	<option>--请选择--</option>
  </select>
  <select id="tid">
  	<option>--请选择--</option>
  </select>
  </body>
</html>
