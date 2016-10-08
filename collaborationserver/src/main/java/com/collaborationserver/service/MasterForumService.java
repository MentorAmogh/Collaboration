package com.collaborationserver.service;

import java.util.List;

import com.collaborationserver.model.Forum;


public interface MasterForumService {
	
	
	Forum findById(String id);
    
    String saveForum(Forum masterf);
     
    String updateForum(Forum masterfor);
     
    String deleteForumId(String fid);
 
    List<Forum> findAllForums(); 
     
   public boolean isForumExists(String forname);
}
