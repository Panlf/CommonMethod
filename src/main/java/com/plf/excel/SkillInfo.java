package com.plf.excel;

/**
 * ������Ϣģ��
 * @author plf 2017��6��12������9:45:24
 *
 */
public class SkillInfo {
	private String company;
	private String year;
	private String info;
	
	public SkillInfo(String company, String year, String info) {
		super();
		this.company = company;
		this.year = year;
		this.info = info;
	}
	
	
	
	public SkillInfo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
