package com.collaborationserver.model;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="forumComment")
public class ForumComment {	
	@Id
	@Column
	private int fourmId;
	@Column
	private int userId;
	@Column
	private String content;
	@Column
	private Date fdate;
	
	public int getFourmId() {
		return fourmId;
	}
	public void setFourmId(int fourmId) {
		this.fourmId = fourmId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}	
}

