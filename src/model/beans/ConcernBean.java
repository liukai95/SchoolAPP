package model.beans;

public class ConcernBean {
	private long idconcern;//关注号ID，唯一标识这一关注
	private long user1;//关注人
	private long user2;//被关注人
	public long getIdconcern() {
		return idconcern;
	}
	public void setIdconcern(long idconcern) {
		this.idconcern = idconcern;
	}
	public long getUser1() {
		return user1;
	}
	public void setUser1(long user1) {
		this.user1 = user1;
	}
	public long getUser2() {
		return user2;
	}
	public void setUser2(long user2) {
		this.user2 = user2;
	}
}
