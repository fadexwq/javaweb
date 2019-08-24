package cn.xwq.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.xwq.bean.Clazz;
import cn.xwq.bean.Student;
import cn.xwq.dao.ClazzDao;
import cn.xwq.util.DBUtil;

public class ClazzDaoImpl implements ClazzDao{

	@Override
	public List<Clazz> findAllClazz() {
		//查询数据库，返回所有的clazz,List返回
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Clazz>list = new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from t_clazz");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String clazzName = rs.getString("clazzName");
				String clazzLocation = rs.getString("clazzLocation");
				list.add(new Clazz(id,clazzName,clazzLocation));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Student> findStudentByClazzId(int clazz_id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from t_student where clazz_id = ?");
			ps.setInt(1, clazz_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String studentName = rs.getString("studentName");
				int studentAge = rs.getInt("studentAge");
				String studentGender = rs.getString("studentGender");
				String studentPhone = rs.getString("studentPhone");
				list.add(new Student(id, studentName, studentAge, studentGender, studentPhone, clazz_id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

}
