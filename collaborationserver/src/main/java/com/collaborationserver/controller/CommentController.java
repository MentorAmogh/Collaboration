package com.collaborationserver.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.collaborationserver.model.ForumComment;
import com.collaborationserver.service.ForumCommentServices;



@RestController
public class CommentController {
				
	 @Autowired
	 ForumCommentServices forumCommentServices;//Service which will do all data retrieval/manipulation work
	 
	//-------------------Retrieve All comments--------------------------------------------------------

	 @RequestMapping(value = "/forumComment", method= RequestMethod.GET)
	 public ResponseEntity<List<ForumComment>> listAllComments(){
	 	
	 	List<ForumComment> fComments = forumCommentServices.findAllComments();
	 	if(fComments.isEmpty()){
	 		return new ResponseEntity<List<ForumComment>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	 	}
	 	
	 	return new ResponseEntity<List<ForumComment>>(fComments, HttpStatus.OK);
	 	
	 }
	 
	//-------------------Retrieve Single comment--------------------------------------------------------
     
	    @RequestMapping(value = "/forumComment/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<ForumComment> getForum(@PathVariable("name") int name)
	    {
	        System.out.println("Fetching User with name " + name);
	        ForumComment fcObject = forumCommentServices.findById(name) ;
	        if (fcObject == null)
	        {
	            System.out.println("Comment with name " + name+ " not found");
	            return new ResponseEntity<ForumComment>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<ForumComment>(fcObject, HttpStatus.OK);
	    }
	  	
	 
	 
//-------------------Create a Comment--------------------------------------------------------
@RequestMapping(value = "/forumComment", method= RequestMethod.POST)
public ResponseEntity<Void> createComment(@RequestBody ForumComment forumComment, UriComponentsBuilder ucBuilder){
	
	System.out.println("Comment Created " + forumComment.getContent());
	String data= forumCommentServices.saveComment(forumComment);	
	System.out.println("Data is " + data);
	 HttpHeaders headers = new HttpHeaders(); 
	 
	 headers.setLocation(ucBuilder.path("/forumComment/{name}").buildAndExpand(forumComment.getContent()).toUri());
     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
 }

//------------------- Delete a User --------------------------------------------------------

@RequestMapping(value = "/forumComment/{name}", method= RequestMethod.DELETE)
public ResponseEntity<ForumComment> deleteComment(@PathVariable("name") int name){
	System.out.println("Fetching & Deleting User with comment " +  name);
	
	ForumComment forumComment = forumCommentServices.findById(name);
	if(forumComment == null){
		System.out.println("Unable to delete ");
		return new ResponseEntity<ForumComment>(HttpStatus.NOT_FOUND);
	}
	
	forumCommentServices.deleteComment(name);
	return new ResponseEntity<ForumComment>(HttpStatus.NO_CONTENT);
}










}
