package cn.xwq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.xwq.bean.City;
import cn.xwq.bean.Province;
import cn.xwq.bean.Town;
import cn.xwq.util.DBUtil;

public class AddressDao {

	public List<Province> findProvince() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Province>pList = new ArrayList<Province>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from t_address_province");
			rs = ps.executeQuery();
			while (rs.next()) {
				Province p = new Province(rs.getInt("id"),rs.getString("code"),rs.getString("name"));
				pList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}		
		return pList;
	}
	
	
	
	public List<City> findCity(String provinceCode) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<City>cList = new ArrayList<City>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from t_address_city where provinceCode = ?");
			ps.setString(1, provinceCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				City city = new City(rs.getInt("id"),rs.getString("code"),rs.getString("name"),provinceCode);
				cList.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}		
		return cList;
	}
	
	
	public List<Town> findTown(String cityCode) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Town>tList = new ArrayList<Town>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from t_address_town where cityCode = ?");
			ps.setString(1, cityCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				Town town = new Town(rs.getInt("id"),rs.getString("code"),rs.getString("name"),cityCode);
				tList.add(town);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}		
		return tList;
	}
}
