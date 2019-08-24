package cn.xwq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.xwq.bean.Province;
import cn.xwq.dao.AddressDao;

public class FindProvinceServlet extends HttpServlet {

	private AddressDao addressDao = new AddressDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		List<Province>pList = addressDao.findProvince();
		
		PrintWriter out = response.getWriter();
		
		if(pList != null & pList.size() != 0){
			out.write(JSON.toJSONString(pList));
			System.out.println(pList.size());
		}else {
			out.write("未查询到数据");
		}
	}

}
