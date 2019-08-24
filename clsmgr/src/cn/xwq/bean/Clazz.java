package cn.xwq.bean;

public class Clazz {

	private int id;
	private String clazzName;
	private String clazzLocation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getClazzLocation() {
		return clazzLocation;
	}
	public void setClazzLocation(String clazzLocation) {
		this.clazzLocation = clazzLocation;
	}
	public Clazz(int id, String clazzName, String clazzLocation) {
		this.id = id;
		this.clazzName = clazzName;
		this.clazzLocation = clazzLocation;
	}
	public Clazz() {
	}
	
}
