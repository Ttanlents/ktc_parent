package com.ktc.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description tb_friend实体类
 * @author admin
 * @date 2020-11-27 09:55:45
 */
@Entity
@Table(name="tb_friend")
@IdClass(Friend.class) //联合主键
public class Friend implements Serializable{

	@Id
	private String userid;//联合主键ID

	@Id
	private String friendid;

	private String islike;

	public Friend() {
	}

	public Friend(String userid, String friendid, String islike) {
		this.userid = userid;
		this.friendid = friendid;
		this.islike = islike;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	public String getIslike() {
		return islike;
	}

	public void setIslike(String islike) {
		this.islike = islike;
	}

	@Override
	public String toString() {
		return "Friend{" +
				"userid='" + userid + '\'' +
				", friendid='" + friendid + '\'' +
				", islike='" + islike + '\'' +
				'}';
	}
}



