package com.collaborationserver.model;

import javax.persistence.*;


@Entity
public class Forum {
	public String getForumid() {
		return Forumid;
	}
	public void setForumid(String forumid) {
		Forumid = forumid;
	}
	public String getForumtopic() {
		return Forumtopic;
	}
	public void setForumtopic(String forumtopic) {
		Forumtopic = forumtopic;
	}
	public String getForumsubtopic() {
		return Forumsubtopic;
	}
	public void setForumsubtopic(String forumsubtopic) {
		Forumsubtopic = forumsubtopic;
	}
	@Id
	private String Forumid;
	@Column
	private String Forumtopic;
	@Column
	private String Forumsubtopic;
}
