package com.ktc.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description tb_nofriend实体类
 * @author admin
 * @date 2020-11-27 11:11:16
 */
@Entity
@Table(name="tb_nofriend")
@IdClass(NoFriend.class)
public class NoFriend implements Serializable{

	@Id
	private String userid;//主键ID


	@Id
	private String friendid;//主键ID

	public NoFriend() {
	}

	public NoFriend(String userid, String friendid) {
		this.userid = userid;
		this.friendid = friendid;
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

	@Override
	public String toString() {
		return "NoFriend{" +
				"userid='" + userid + '\'' +
				", friendid='" + friendid + '\'' +
				'}';
	}
}



