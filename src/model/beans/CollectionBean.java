package model.beans;

public class CollectionBean {
	private int idcolletion;//收藏号ID，唯一标识这一收藏
	private long user_iduser;//收藏人ID
	private long post_id;//收藏的帖子ID
	private int type;//收藏帖子的类型，指明对应是考研、招聘、失物招领中哪个
	public int getIdcolletion() {
		return idcolletion;
	}
	public void setIdcolletion(int idcolletion) {
		this.idcolletion = idcolletion;
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
}
