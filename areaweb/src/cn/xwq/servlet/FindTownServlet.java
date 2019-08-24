package cn.xwq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.xwq.bean.Town;
import cn.xwq.dao.AddressDao;

public class FindTownServlet extends HttpServlet {

	private AddressDao addressDao = new AddressDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		
		String ccode = request.getParameter("ccode");
		
		List<Town>tList = addressDao.findTown(ccode);
		String tListStr = JSON.toJSONString(tList);
		PrintWriter out = response.getWriter();
		
		out.write(tListStr);
		
	}

}
