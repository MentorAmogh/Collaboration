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

import com.collaborationserver.model.Forum;

import com.collaborationserver.service.MasterForumService;

@RestController
public class ForumController {
	@Autowired
	MasterForumService mFService;
	//-------------List all forums--------------------------------------------------------
    @RequestMapping(value = "/forums", method = RequestMethod.GET)
    public ResponseEntity<List<Forum>> listAllForums() 
    {
        List<Forum> forums = mFService.findAllForums();
        if(forums.isEmpty())
        {
        	System.out.println("Empty No Rows Found");
return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        System.out.println("Rows Found");
        return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
    }
    //-------------------Retrieve Single Forum--------------------------------------------------------
    
    @RequestMapping(value = "/forums/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Forum> getForums(@PathVariable("name") String name)
    {
        System.out.println("Fetching forum with name " + name);
        Forum masterf= mFService.findById(name);
        if (masterf == null)
        {
            System.out.println("Forum With the name not found");
            return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Forum Found");
        return new ResponseEntity<Forum>(masterf, HttpStatus.OK);
    }
    //-------------------Create a Forum--------------------------------------------------------
      
    @RequestMapping(value = "/forums", method = RequestMethod.POST)
    public ResponseEntity<Void> createForum(@RequestBody Forum masterf, UriComponentsBuilder ucBuilder)
    {
        System.out.println("Creating Forum " + masterf.getForumtopic());
        String data=mFService.saveForum(masterf);
        System.out.println("Data is "+data);
        HttpHeaders headers = new HttpHeaders();
        
        headers.setLocation(ucBuilder.path("/forums/{name}").buildAndExpand(masterf.getForumid()).toUri());
       
        
        
        System.out.println("Forum " +masterf.getForumtopic()+" Created");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    //-------------------Delete a Forum--------------------------------------------------------
    @RequestMapping(value = "/forums/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Forum> deleteForum(@PathVariable("name") String name) {
        System.out.println("Fetching & Deleting User with name " + name);
  
        Forum forum = mFService.findById(name);
        if (forum == null) 
        {
            System.out.println("Unable to delete. Forum with name " + name + " not found");
            return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
        }
  
        mFService.deleteForumId(name);
        return new ResponseEntity<Forum>(HttpStatus.NO_CONTENT);
    }
   
 }
