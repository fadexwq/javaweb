package cn.xwq.dao;

import java.util.List;

import cn.xwq.bean.Clazz;
import cn.xwq.bean.Student;

public interface ClazzDao {
	public List<Clazz> findAllClazz();
	
	public List<Student> findStudentByClazzId(int clazz_id);
}
