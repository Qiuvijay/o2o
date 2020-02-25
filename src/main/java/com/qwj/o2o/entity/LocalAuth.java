package com.qwj.o2o.entity;

import java.util.Date;

public class LocalAuth {
	private Long localAuthId;
	private String username;
	private String password;
	private Date createtime;
	private Date lastEditTime;
	private PersonInfo personInfo;

	public Long getLocalAuthId() {
		return localAuthId;
	}

	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	@Override
	public String toString() {
		return "LocalAuth [localAuthId=" + localAuthId + ", username=" + username + ", password=" + password
				+ ", createtime=" + createtime + ", lastEditTime=" + lastEditTime + ", personInfo=" + personInfo + "]";
	}

}
