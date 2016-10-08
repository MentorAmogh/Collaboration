package com.collaborationserver.service;

import java.util.List;

import com.collaborationserver.model.BlogPost;


public interface BlogPostServices {

	   BlogPost findByBlogId(int blogid);
	     
	    String saveBlog(BlogPost blogPost);
	     
	    String updateBlog(BlogPost blogPost);
	     
	    String deleteBlog(int name);
	 
	    List<BlogPost> findAllBlog(); 
	     
	   public boolean isBlogExists(int name);	     
	
}
