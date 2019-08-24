package cn.xwq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.xwq.bean.City;
import cn.xwq.dao.AddressDao;

public class FindCityServlet extends HttpServlet {

	private AddressDao addressDao = new AddressDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		
		String pcode = request.getParameter("pcode");
		
		//根据省code查询所属下市
		List<City> cList = addressDao.findCity(pcode);
		
		String cListStr = JSON.toJSONString(cList);
		
		PrintWriter out = response.getWriter();
		out.write(cListStr);
	}

}
