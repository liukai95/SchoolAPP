package model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.utils.ModelDataExecute;

public class UserBean {
	private long userid;//用户id
	private String tel;//用户的手机号
	private String password;//用户的密码
	private String nickName;//用户昵称
	private String jobTitle;//用户职称
	private String headPortrait;//头像
	
	
	/**
	 * @param userid
	 * @param tel
	 * @param password
	 * @param nickName
	 * @param jobTitle
	 * @param headPortrait
	 */
	public UserBean(long userid, String tel, String password, String nickName, String jobTitle, String headPortrait) {
		super();
		this.userid = userid;
		this.tel = tel;
		this.password = password;
		this.nickName = nickName;
		this.jobTitle = jobTitle;
		this.headPortrait = headPortrait;
	}

	public UserBean() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @return userid
	 */
	public long getUserid() {
		return userid;
	}
	/**
	 * @param userid 要设置的 userid
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}
	/**
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel 要设置的 tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password 要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName 要设置的 nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle 要设置的 jobTitle
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * @return headPortrait
	 */
	public String getHeadPortrait() {
		return headPortrait;
	}
	/**
	 * @param headPortrait 要设置的 headPortrait
	 */
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	 
	/**
	 * 实现登录的功能
	 * @return
	 */
	public boolean doLogin(){
		String sql=new String("select * from user where tel='"+tel+"' and password='"+password+"'");
		System.out.println(sql);
		ModelDataExecute model=new ModelDataExecute();
		ResultSet result=model.select(sql);
		try {
			if(result.next()) {
				userid=result.getLong("iduser");
				tel=result.getString("tel");
				password="";
				nickName=result.getString("nickname");
				jobTitle=result.getString("jobtitle");
				headPortrait=result.getString("headportrait");
				return true;
			}
			else return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			
		}finally{
			model.close();
		}
		return false;	
	}
	/**
	 * 实现注册查询,是否已经注册
	 */
	public boolean isRegistedByTel(){
		String sql=new String("select * from user where tel='"+tel+"'");
		ModelDataExecute model=new ModelDataExecute();
		ResultSet result=model.select(sql);
		try {
			if(result.next()) return true;
			else return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			model.close();
		}
		return false;	
	}
	public boolean isRegistedById(){
		String sql=new String("select * from user where iduser="+userid+"'");
		ModelDataExecute model=new ModelDataExecute();
		ResultSet result=model.select(sql);
		try {
			if(result.next()) return true;
			else return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			model.close();
		}
		return false;	
	}
	/**
	 * 实现注册的功能
	 */
	public boolean doRegist(){
		//不符合手机号格式，则返回错误
		String pattern = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		if(!Pattern.matches(pattern, tel)) return false;
		if(password.equals("")||nickName.equals("")||jobTitle.equals("")) return false;
		String sql=new String("INSERT INTO `user` (`iduser`, `tel`, `password`, `nickname`, `jobtitle`, `headportrait`) "
				+ "VALUES (NULL, '"+tel+"', '"+password+"', '"+nickName+"', '"+jobTitle+"', '')");
		System.out.println(sql);
		ModelDataExecute model=new ModelDataExecute();
		userid=model.modifyByAutoId(sql);
		if(userid==0){
			model.close();
			return false;
		}
			
		else {
			model.close();
			return true;
		}
	}
	/**
	 * 根据id查询某一条信息
	 * @return
	 */
		public boolean inquireInfo() {
			ModelDataExecute model = new ModelDataExecute();
			// 判断id是否存在,并查询
			try {
				StringBuilder sBuilder = new StringBuilder("select * from user where iduser='");
				String sql = sBuilder.append(userid).append("'").toString();
				ResultSet result = model.select(sql);
				if (result.next()) {
					userid=result.getLong("iduser");
					tel=result.getString("tel");
					password="";
					nickName=result.getString("nickname");
					jobTitle=result.getString("jobtitle");
					headPortrait=result.getString("headportrait");
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				model.close();
			}
			return false;

		}

		/**
		 * 修改密码
		 * @return 是否正确修改
		 */
		public boolean updatePassword() {
			ModelDataExecute model = new ModelDataExecute();
			try {
				StringBuilder sBuilder = new StringBuilder("select * from user where tel='");
				String sql = sBuilder.append(tel).append("'").toString();
				ResultSet result = model.select(sql);
				// 判断tel是否存在
				if (!result.next()) {
					return false;
				}
				sBuilder = new StringBuilder("update user set password='");
				sql = sBuilder.append(password).append("' where tel='").append(tel).append("'").toString();
				if (model.modify(sql)) {
					return true;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				model.close();
			}
			return false;
		}

		// 修改昵称
		public boolean updateNickname() {
			ModelDataExecute model = new ModelDataExecute();
			try {
				StringBuilder sBuilder = new StringBuilder("select * from user where tel='");
				String sql = sBuilder.append(tel).append("'").toString();
				ResultSet result = model.select(sql);
				// 判断tel是否存在
				if (!result.next()) {
					return false;
				}
//				//昵称唯一，还需要判断昵称是否存在
//				sBuilder = new StringBuilder("select * from user where nickname='");
//				sql = sBuilder.append(nickName).append("'").toString();
//				result = model.select(sql);
//				// 判断nickname是否存在
//				if (result.next()) {
//					return false;
//				}
				
				sBuilder = new StringBuilder("update user set nickname='");
				sql = sBuilder.append(nickName).append("' where tel='").append(tel).append("'").toString();
				if (model.modify(sql)) {
					return true;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				model.close();
			}
			return false;
		}
}
