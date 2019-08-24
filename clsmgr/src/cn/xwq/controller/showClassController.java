package cn.xwq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xwq.bean.Clazz;
import cn.xwq.bean.Student;
import cn.xwq.dao.ClazzDao;
import cn.xwq.dao.impl.ClazzDaoImpl;

public class showClassController extends HttpServlet {

	private ClazzDao clazzDao = new ClazzDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收用户的请求，对用户的请求作出响应.根据cmd的值确定选择跳转的界面
		String cmd = request.getParameter("cmd");
		if(cmd.equals("findClazz")){
			List<Clazz>cList = clazzDao.findAllClazz();
			
			//请求转发，将数据返回在classlist.jsp中
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("view/classlist.jsp").forward(request, response);
			
		}else if (cmd.equals("findStudent")) {
			String clazz_idStr = request.getParameter("clazz_id");
			int clazz_id = Integer.parseInt(clazz_idStr);
			List<Student> studentList = clazzDao.findStudentByClazzId(clazz_id);
			
			request.setAttribute("studentList", studentList);
			request.getRequestDispatcher("view/studentlist.jsp").forward(request, response);
		}
		
	}

}
