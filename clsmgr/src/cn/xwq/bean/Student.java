package cn.xwq.bean;

public class Student {

	private int id;
	private String studentName;
	private int studentAge;
	private String studentGender;
	private String studentPhone;
	private int clazz_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public String getStudentGender() {
		return studentGender;
	}
	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public int getClazz_id() {
		return clazz_id;
	}
	public void setClazz_id(int clazz_id) {
		this.clazz_id = clazz_id;
	}
	public Student(int id, String studentName, int studentAge,
			String studentGender, String studentPhone, int clazz_id) {
		this.id = id;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentGender = studentGender;
		this.studentPhone = studentPhone;
		this.clazz_id = clazz_id;
	}
	public Student() {
	}
	
	
}
