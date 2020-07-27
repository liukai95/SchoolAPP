package model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.ModelDataExecute;

public class LostAndFoundBean {
	private long idLostandFound;// ID，唯一标识
	private long user_iduser;// 用户ID
	private int school_idschool;// 学校
	private String itemtype;// 失物类型
	private String description;// 对失物的具体描述
	private String contactinformation;// 联系方式
	private String pic;// 存储失物图片的路径
	private long time;// 发帖时间

	public LostAndFoundBean() {
		super();
	}

	public LostAndFoundBean(long idLostandFound, long user_iduser, int school_idschool, String itemtype,
			String description, String contactinformation, String pic, long time) {
		super();
		this.idLostandFound = idLostandFound;
		this.user_iduser = user_iduser;
		this.school_idschool = school_idschool;
		this.itemtype = itemtype;
		this.description = description;
		this.contactinformation = contactinformation;
		this.pic = pic;
		this.time = time;
	}

	public long getIdLostandFound() {
		return idLostandFound;
	}

	public void setIdLostandFound(long idLostandFound) {
		this.idLostandFound = idLostandFound;
	}

	public long getUser_iduser() {
		return user_iduser;
	}

	public void setUser_iduser(long user_iduser) {
		this.user_iduser = user_iduser;
	}

	public int getSchool_idschool() {
		return school_idschool;
	}

	public void setSchool_idschool(int school_idschool) {
		this.school_idschool = school_idschool;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactinformation() {
		return contactinformation;
	}

	public void setContactinformation(String contactinformation) {
		this.contactinformation = contactinformation;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	// 显示所有信息
	public List<LostAndFoundBean> listLostInfo() {
		List<LostAndFoundBean> list = new ArrayList<>();// 所有评论信息
		String sql = "select * from lostandfound";
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			while (result.next()) {
				idLostandFound = result.getLong("idLostandFound");// ID，唯一标识
				user_iduser = result.getLong("user_iduser");// 用户ID
				school_idschool = result.getInt("school_idschool");// 学校
				itemtype = result.getString("itemtype");// 失物类型
				description = result.getString("description");// 对失物的具体描述
				contactinformation = result.getString("contactinformation");// 联系方式
				pic = result.getString("pic");// 存储失物图片的路径
				time = result.getTimestamp("time").getTime();// 发帖时间

				LostAndFoundBean lost = new LostAndFoundBean(idLostandFound, user_iduser, school_idschool, itemtype,
						description, contactinformation, pic, time);
				list.add(lost);
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
		if (idLostandFound <= 0) {
			return false;
		}
		String sql = new String("select * from lostandfound where idLostandFound=" + idLostandFound);
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			if (result.next()) {
				user_iduser = result.getLong("user_iduser");// 发帖人ID
				school_idschool = result.getInt("school_idschool");// 学校
				itemtype = result.getString("itemtype");// 失物类型
				description = result.getString("description");// 对失物的具体描述
				contactinformation = result.getString("contactinformation");// 联系方式
				pic = result.getString("pic");// 存储失物图片的路径
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
	//查询某用户发布的所有信息
	public List<LostAndFoundBean> listUserInfo() {
		List<LostAndFoundBean> list = new ArrayList<>();// 所有评论信息
		String sql = "select * from lostandfound where user_iduser="+user_iduser;
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			while (result.next()) {
				idLostandFound = result.getLong("idLostandFound");// ID，唯一标识
				school_idschool = result.getInt("school_idschool");// 学校
				itemtype = result.getString("itemtype");// 失物类型
				description = result.getString("description");// 对失物的具体描述
				contactinformation = result.getString("contactinformation");// 联系方式
				pic = result.getString("pic");// 存储失物图片的路径
				time = result.getTimestamp("time").getTime();// 发帖时间

				LostAndFoundBean lost = new LostAndFoundBean(idLostandFound, user_iduser, school_idschool, itemtype,
						description, contactinformation, pic, time);
				list.add(lost);
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
		String sql = "select * from lostandfound where idLostandFound=" + idLostandFound + " and user_iduser=" + user_iduser;
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

	// 更新学校
	public boolean updateSchool() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update lostandfound set school_idschool='");
			String sql = sBuilder.append(school_idschool).append("' where idLostandFound=").append(idLostandFound)
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
	// 更新失物类型
	public boolean updateIitemtype() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update lostandfound set itemtype='");
			String sql = sBuilder.append(itemtype).append("' where idLostandFound=").append(idLostandFound)
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
	// 更新描述
	public boolean updateDescription() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update lostandfound set description='");
			String sql = sBuilder.append(description).append("' where idLostandFound=").append(idLostandFound)
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
	// 更新联系方式
	public boolean updateContactin() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update lostandfound set contactinformation='");
			String sql = sBuilder.append(contactinformation).append("' where idLostandFound=").append(idLostandFound)
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
	// 更新图片
	public boolean updatePic() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update lostandfound set pic='");
			String sql = sBuilder.append(pic).append("' where idLostandFound=").append(idLostandFound)
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
