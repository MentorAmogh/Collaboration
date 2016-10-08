package com.collaborationserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.collaborationserver.model.BlogComment;
import com.collaborationserver.model.Forum;
import com.collaborationserver.service.BlogCommentService;

@RestController
public class BlogCommentController {

	 @Autowired
	 BlogCommentService blogService;
	//-------------------Retrieve All Users--------------------------------------------------------	 
	@RequestMapping(value="/blogComment", method= RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> listAllBComment(){
		
		List<BlogComment> blogComment = blogService.findAllBlogComment();
		
		if(blogComment.isEmpty()){
			return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
	
	}
		return new ResponseEntity<List<BlogComment>>(blogComment, HttpStatus.OK);
		
	}
	
	
  //-------------------Retrieve Single Blog Comment--------------------------------------------------------
    
    @RequestMapping(value = "/blogComment/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogComment> getBlog(@PathVariable("name") String name)
    {
        System.out.println("Fetching blog with name " + name);
        BlogComment blogC= blogService.findById(name);
        if (blogC == null)
        {
            System.out.println("Blog With the name not found");
            return new ResponseEntity<BlogComment>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Blog Found");
        return new ResponseEntity<BlogComment>(blogC, HttpStatus.OK);
    }
    
    //-------------------Create a Blog--------------------------------------------------------
      
    @RequestMapping(value = "/blogComment", method = RequestMethod.POST)
    public ResponseEntity<Void> createBlog(@RequestBody BlogComment bgC, UriComponentsBuilder ucBuilder)
    {
        System.out.println("Creating Comment " + bgC.getBlogCommentId());
        String Comment=blogService.saveBlogComment(bgC);
        System.out.println("Comment is "+Comment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/blogComment/{name}").buildAndExpand(bgC.getBlogCommentId()).toUri());
             
        System.out.println("Forum " +bgC.getComments()+" Created");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    
    //-------------------Delete a Forum--------------------------------------------------------
    @RequestMapping(value = "/blogComment/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<BlogComment> deletBlog(@PathVariable("name") String name) {
        System.out.println("Fetching & Deleting Comment with name " + name);
        BlogComment bgC = blogService.findById(name);
       if (bgC == null) 
        {
            System.out.println("Unable to delete. Blog with name " + name + " not found");
            return new ResponseEntity<BlogComment>(HttpStatus.NOT_FOUND);
        }
       	blogService.deleteBlogComment(name);

        return new ResponseEntity<BlogComment>(HttpStatus.NO_CONTENT);
    }
   
 }

