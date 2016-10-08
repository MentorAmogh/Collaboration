package com.collaborationserver.service;

import java.util.List;

import com.collaborationserver.model.BlogComment;

public interface BlogCommentService {

	
	
	BlogComment findById(String blog);
	
	String saveBlogComment(BlogComment blogComment);
	
	String deleteBlogComment(String blogComment);
	
	
	List<BlogComment> findAllBlogComment();
	
	public boolean isBlogExist(String blog);
	
	
}
