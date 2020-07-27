package model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.utils.ModelDataExecute;

public class JobInfoBean {
	private long idjobinfo;// ID
	private long user_iduser;// 发帖人ID
	private String post;// 职位
	private String company;// 招聘公司
	private String money;// 薪资
	private String workingplace;// 工作地点
	private String jobcontent;// 工作职责
	private String workhour;// 工作时长
	private long time;// 发帖时间

	public JobInfoBean() {
		super();
	}

	public JobInfoBean(long idjobinfo, long user_iduser, String post, String company, String money, String workingplace,
			String jobcontent, String workhour, long time) {
		super();
		this.idjobinfo = idjobinfo;
		this.user_iduser = user_iduser;
		this.post = post;
		this.company = company;
		this.money = money;
		this.workingplace = workingplace;
		this.jobcontent = jobcontent;
		this.workhour = workhour;
		this.time = time;
	}

	public long getIdjobinfo() {
		return idjobinfo;
	}

	public void setIdjobinfo(long idjobinfo) {
		this.idjobinfo = idjobinfo;
	}

	public long getUser_iduser() {
		return user_iduser;
	}

	public void setUser_iduser(long user_iduser) {
		this.user_iduser = user_iduser;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getWorkingplace() {
		return workingplace;
	}

	public void setWorkingplace(String workingplace) {
		this.workingplace = workingplace;
	}

	public String getJobcontent() {
		return jobcontent;
	}

	public void setJobcontent(String jobcontent) {
		this.jobcontent = jobcontent;
	}

	public String getWorkhour() {
		return workhour;
	}

	public void setWorkhour(String workhour) {
		this.workhour = workhour;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	// 插入一条信息
	public int doInsert() {
		if (this.jobcontent.equals("") || this.money.equals("") || this.workhour.equals("") || this.post.equals("")
				|| this.company.equals("") || this.user_iduser <= 0 || this.workingplace.equals("as")) {
			return 0;
		}
		String sql = "INSERT INTO `jobinfo` (`idjobinfo`, `user_iduser`, `post`, `company`, "
				+ "`money`, `workingplace`, `jobcontent`, `workhour`, `time`) " + "VALUES (NULL, ";
		sql += "'" + user_iduser + "', '" + post + "', '" + company + "', '" + money + "', '" + workingplace + "', '"
				+ jobcontent + "', '" + workhour + "', now() ";
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		int result = model.modifyByAutoId(sql);
		model.close();
		return result;
	}

	// 显示所有信息
	public List<JobInfoBean> listJob() {
		List<JobInfoBean> list = new ArrayList<>();// 所有评论信息
		String sql = "select * from jobinfo";
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			while (result.next()) {
				idjobinfo = result.getLong("idjobinfo");// ID
				user_iduser = result.getLong("user_iduser");// 发帖人ID
				post = result.getString("post");// 职位
				company = result.getString("company");// 招聘公司
				money = result.getString("money");// 薪资
				workingplace = result.getString("workingplace");// 工作地点
				jobcontent = result.getString("jobcontent");// 工作职责
				workhour = result.getString("workhour");// 工作时长
				time = result.getTimestamp("time").getTime();// 发帖时间
				JobInfoBean job = new JobInfoBean(idjobinfo, user_iduser, post, company, money, workingplace,
						jobcontent, workhour, time);
				list.add(job);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	// 查询一条消息
	public boolean inquire() {
		if (idjobinfo <= 0) {
			return false;
		}
		String sql = new String("select * from jobinfo where idjobinfo=" + idjobinfo);
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			if (result.next()) {
				user_iduser = result.getLong("user_iduser");// 发帖人ID
				post = result.getString("post");// 职位
				company = result.getString("company");// 招聘公司
				money = result.getString("money");// 薪资
				workingplace = result.getString("workingplace");// 工作地点
				jobcontent = result.getString("jobcontent");// 工作职责
				workhour = result.getString("workhour");// 工作时长
				time = result.getTimestamp("time").getTime();// 发帖时间
				return true;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			model.close();
		}
		return false;
	}

	// 查询某用户发布的所有信息
	public List<JobInfoBean> listUserJob() {
		List<JobInfoBean> list = new ArrayList<>();// 所有评论信息
		String sql = "select * from jobinfo where user_iduser=" + user_iduser;
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			while (result.next()) {
				idjobinfo = result.getLong("idjobinfo");// ID
				post = result.getString("post");// 职位
				company = result.getString("company");// 招聘公司
				money = result.getString("money");// 薪资
				workingplace = result.getString("workingplace");// 工作地点
				jobcontent = result.getString("jobcontent");// 工作职责
				workhour = result.getString("workhour");// 工作时长
				time = result.getTimestamp("time").getTime();// 发帖时间
				JobInfoBean job = new JobInfoBean(idjobinfo, user_iduser, post, company, money, workingplace,
						jobcontent, workhour, time);
				list.add(job);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	// 判断该条信息是否存在
	public boolean isFlagById() {
		String sql = "select * from jobinfo where idjobinfo=" + idjobinfo + " and user_iduser=" + user_iduser;
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			if (result.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			model.close();
		}
		return false;
	}

	// 更新职位
	public boolean updatePost() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update jobinfo set post='");
			String sql = sBuilder.append(post).append("' where idjobinfo=").append(idjobinfo)
					.append(" and user_iduser=").append("user_iduser").toString();
			System.out.println(sql);
			if (model.modify(sql)) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			model.close();
		}
		return false;
	}

	// 更新公司
	public boolean updateCompany() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update jobinfo set company='");
			String sql = sBuilder.append(company).append("' where idjobinfo=").append(idjobinfo)
					.append(" and user_iduser=").append("user_iduser").toString();
			System.out.println(sql);
			if (model.modify(sql)) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			model.close();
		}
		return false;
	}

	// 更新职位
	public boolean updateMoney() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update jobinfo set money='");
			String sql = sBuilder.append(money).append("' where idjobinfo=").append(idjobinfo)
					.append(" and user_iduser=").append("user_iduser").toString();
			System.out.println(sql);
			if (model.modify(sql)) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			model.close();
		}
		return false;
	}

	// 更新工作地点
	public boolean updateWorkingplace() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update jobinfo set workingplace='");
			String sql = sBuilder.append(workingplace).append("' where idjobinfo=").append(idjobinfo)
					.append(" and user_iduser=").append("user_iduser").toString();
			System.out.println(sql);
			if (model.modify(sql)) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			model.close();
		}
		return false;
	}

	// 更新工作职责
	public boolean updateJobcontent() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update jobinfo set jobcontent='");
			String sql = sBuilder.append(jobcontent).append("' where idjobinfo=").append(idjobinfo)
					.append(" and user_iduser=").append("user_iduser").toString();
			System.out.println(sql);
			if (model.modify(sql)) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			model.close();
		}
		return false;
	}

	// 更新工作时长
	public boolean updateWorkhour() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update jobinfo set workhour='");
			String sql = sBuilder.append(workhour).append("' where idjobinfo=").append(idjobinfo)
					.append(" and user_iduser=").append("user_iduser").toString();
			System.out.println(sql);
			if (model.modify(sql)) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			model.close();
		}
		return false;
	}

}
