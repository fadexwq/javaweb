package cn.xwq.bean;

public class Town {
	private int id;
	private String code;
	private String name;
	private String ccode;
	public Town(int id, String code, String name, String ccode) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.ccode = ccode;
	}
	public Town() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	
}
