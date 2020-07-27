package model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.ModelDataExecute;

public class PostgraduateBean {
	private long idpostgraduate;// ID
	private long user_iduser;// 发帖人ID
	private String content;// 文字内容，经验贴等
	private String pic;// 图片的路径
	private long time;// 发帖时间

	public PostgraduateBean() {
		super();
	}

	public PostgraduateBean(long idpostgraduate, long user_iduser, String content, String pic, long time) {
		super();
		this.idpostgraduate = idpostgraduate;
		this.user_iduser = user_iduser;
		this.content = content;
		this.pic = pic;
		this.time = time;
	}

	public long getIdpostgraduate() {
		return idpostgraduate;
	}

	public void setIdpostgraduate(long idpostgraduate) {
		this.idpostgraduate = idpostgraduate;
	}

	public long getUser_iduser() {
		return user_iduser;
	}

	public void setUser_iduser(long user_iduser) {
		this.user_iduser = user_iduser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	public List<PostgraduateBean> listPostInfo() {
		List<PostgraduateBean> list = new ArrayList<>();// 所有评论信息
		String sql = "select * from postgraduate";
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			while (result.next()) {
				idpostgraduate = result.getLong("idpostgraduate");// ID
				user_iduser = result.getLong("user_iduser");// 发帖人ID
				content = result.getString("content");// 文字内容，经验贴等
				pic = result.getString("pic");// 图片的路径
				time = result.getTimestamp("time").getTime();// 发帖时间
				PostgraduateBean pBean = new PostgraduateBean(idpostgraduate, user_iduser, content, pic, time);
				list.add(pBean);
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
		if (idpostgraduate <= 0) {
			return false;
		}
		String sql = new String("select * from postgraduate where idpostgraduate=" + idpostgraduate);
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			if (result.next()) {
				user_iduser = result.getLong("user_iduser");// 发帖人ID
				content = result.getString("content");// 文字内容，经验贴等
				pic = result.getString("pic");// 图片的路径
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
	public List<PostgraduateBean> listUserInfo() {
		List<PostgraduateBean> list = new ArrayList<>();// 所有评论信息
		String sql = "select * from postgraduate where user_iduser=" + user_iduser;
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			while (result.next()) {
				idpostgraduate = result.getLong("idpostgraduate");// ID
				content = result.getString("content");// 文字内容，经验贴等
				pic = result.getString("pic");// 图片的路径
				time = result.getTimestamp("time").getTime();// 发帖时间
				PostgraduateBean pBean = new PostgraduateBean(idpostgraduate, user_iduser, content, pic, time);
				list.add(pBean);
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
		String sql = "select * from postgraduate where idpostgraduate=" + idpostgraduate + " and user_iduser="
				+ user_iduser;
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

	// 更新内容
	public boolean updateContent() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update postgraduate set content='");
			String sql = sBuilder.append(content).append("' where idpostgraduate=").append(idpostgraduate)
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

	// 更新图片路径
	public boolean updatePic() {
		ModelDataExecute model = new ModelDataExecute();
		try {

			StringBuilder sBuilder = new StringBuilder("update postgraduate set pic='");
			String sql = sBuilder.append(pic).append("' where idpostgraduate=").append(idpostgraduate)
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
