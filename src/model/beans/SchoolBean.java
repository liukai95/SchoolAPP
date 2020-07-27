package model.beans;

import model.utils.ModelDataExecute;

public class SchoolBean {
	private int idschool;
	private String schoolname;
	public int getIdschool() {
		return idschool;
	}
	public void setIdschool(int idschool) {
		this.idschool = idschool;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	
//	public void insert() {
//		ModelDataExecute m = new ModelDataExecute();
//		//判断合法性
//		m.modify("INSERT INTO `school`.`school` (`idschool`, `schoolname`) VALUES ('"+idschool+"', '"+schoolname+"')");
//	}
//	public static void main(String[] args) {
//		//测试成功
//		SchoolBean schoolbean = new SchoolBean();
//		schoolbean.setIdschool(5);
//		schoolbean.setSchoolname("华科");
//		schoolbean.insert();
//	}
}
