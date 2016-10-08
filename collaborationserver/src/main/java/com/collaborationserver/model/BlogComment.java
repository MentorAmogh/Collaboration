package com.collaborationserver.model;

import javax.persistence.*;

@Entity
public class BlogComment {

	@Id
	private String blogCommentId;
	@Column
	private String blogid;
	@Column
	private String userid;
	@Column
	private String comments;
	
	
	
	
	public String getBlogCommentId() {
		return blogCommentId;
	}
	public void setBlogCommentid(String blogCommentId) {
		this.blogCommentId = blogCommentId;
	}
	public String getBlogid() {
		return blogid;
	}
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
