package com.collaborationserver.service;

import java.util.List;

import com.collaborationserver.model.ForumComment;


public interface ForumCommentServices {

	
	ForumComment findById(int comment);

	String saveComment(ForumComment fouComment);

	String deleteComment(int comment);

	List<ForumComment> findAllComments();
	
	public boolean isForumCommentExists(int comment);
	
/*
 * WE ARE NOT REQUIRE TO UPDATE THE COMMENT
 */
	
	
	
	
	
}
