package model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.utils.ModelDataExecute;

public class CommentBean {
	private long idcomment;// 评论ID
	private long user_iduser;// 评论人ID
	private long post_id;// 评论的帖子ID
	private int type;// 评论类型，指明对应是考研、招聘、失物招领中哪个
	private String content;// 评论内容
	private long time;// 评论时间

	public CommentBean(long idcomment, long user_iduser, long post_id, int type, String content, long time) {
		super();
		this.idcomment = idcomment;
		this.user_iduser = user_iduser;
		this.post_id = post_id;
		this.type = type;
		this.content = content;
		this.time = time;
	}

	public CommentBean() {
		super();
	}

	public long getIdcomment() {
		return idcomment;
	}

	public void setIdcomment(long idcomment) {
		this.idcomment = idcomment;
	}

	public long getUser_iduser() {
		return user_iduser;
	}

	public void setUser_iduser(long user_iduser) {
		this.user_iduser = user_iduser;
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	// 插入一条评论
	public int insert() {

		if (user_iduser <= 0) {
			return 0;
		}
		String sql = "insert into comment (user_iduser, post_id, type,content,time) values (";
		sql += "'" + user_iduser + "', '" + post_id + "', '" + type + "', '" + content + "', now())";
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		int result = model.modifyByAutoId(sql);
		model.close();
		return result;
	}

	// 列出所有评论
	public List<CommentBean> listCom() {
		List<CommentBean> list = new ArrayList<>();// 所有评论信息
		String sql = "select * from comment where type=" + type + " and post_id=" + post_id;
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		ResultSet result = model.select(sql);
		try {
			while (result.next()) {
				long idcomment = result.getLong("idcomment");// 评论ID
				long user_iduser = result.getLong("user_iduser");// 评论人ID
				String content = result.getString("content");// 评论内容
				Timestamp t = result.getTimestamp("time");// 评论时间
				CommentBean cb = new CommentBean(idcomment, user_iduser, post_id, type, content, t.getTime());
				list.add(cb);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	// 删除一个用户的已经评论过的所有评论
	public boolean delete() {
		if (user_iduser <= 0) {
			return false;
		}
		String sql = "delete from comment where user_iduser = '" + user_iduser + "'";
		System.out.println(sql);
		ModelDataExecute model = new ModelDataExecute();
		boolean b = model.modify(sql);
		model.close();
		return b;
	}
	// public static void main(String[] args) {
	// CommentBean comm = new CommentBean();
	// comm.setPost_id(1);
	// comm.setType(1);
	// List<CommentBean> list = comm.listCom();// 得到所有评论信息
	// System.out.println(list.size());
	// }
}
