package com.collaborationserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.collaborationserver.dao.UserServiceImpl;
import com.collaborationserver.model.Users;
import com.collaborationserver.service.UserService;


@RestController
public class UserController
{
	 private static final Logger log = LoggerFactory.getLogger(UserController.class);
		
	
	 @Autowired
	 UserServiceImpl userService;  //Service which will do all data retrieval/manipulation work
	 	
	 //trying request parameter
	 //--------------------------------------------------------------------------------------------
	// http://locahost:8080/collaboration/users
	    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Users> getUsers(@PathVariable("user_name") String uname,@PathVariable("passwd") String passwd)
	    {
	        System.out.println("Fetching User with name " + uname);
	        Users user = userService.findByName(uname);
	        if (user == null)
	        {
	            System.out.println("User with name " + uname+ " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        System.out.println("Invalid Data");
	        return new ResponseEntity<Users>(user, HttpStatus.OK);
	    }
	 
	 
	 
	 //-------------------Retrieve All Users--------------------------------------------------------
	    @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public ResponseEntity<List<Users>> listAllUsers() 
	    {
	    	log.debug("Starting of the method getAllUsers" );
	        List<Users> users = userService.findAllUsers();
	        if(users.isEmpty())
	        {
	        	System.out.println("404");
				System.out.println("No Users are available");
	            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        log.debug("Ending of the method getAllUsers" );
	        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	    }
	  
	    
	   
	    
	    	
	    	
	   
	     
	    //-------------------Retrieve Single User--------------------------------------------------------
	      
	    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Users> getUser(@PathVariable("name") String name)
	    {
	        System.out.println("Fetching User with name " + name);
	        Users user = userService.findByName(name);
	        if (user == null)
	        {
	            System.out.println("User with name " + name+ " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Users>(user, HttpStatus.OK);
	    }
	  
	      
	      
	    //-------------------Create a User--------------------------------------------------------
	      
	    @RequestMapping(value = "/user", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody Users user, UriComponentsBuilder ucBuilder)
	    {
	        System.out.println("Creating User " + user.getUsername());
	        userService.saveUser(user);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{name}").buildAndExpand(user.getUsername()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	  
	     
	      
	    //------------------- Update a User --------------------------------------------------------
	      
	    @RequestMapping(value = "/user/{name}", method = RequestMethod.PUT)
	    public ResponseEntity<Users> updateUser(@PathVariable("name") String name, @RequestBody Users user) {
	        System.out.println("Updating User " + name);	          
	        Users currentUser = userService.findByName(name);
	          
	        if (currentUser==null) 
	        {
	            System.out.println("User with name " + name + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        currentUser.setUsername(user.getUsername());
	        currentUser.setAddress(user.getAddress());
	        currentUser.setEmail(user.getEmail());
	        userService.updateUser(currentUser);
	        return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
	    }
	  
	     
	     
	    //------------------- Delete a User --------------------------------------------------------
	      
	    @RequestMapping(value = "/user/{name}", method = RequestMethod.DELETE)
	    public ResponseEntity<Users> deleteUser(@PathVariable("name") String name) {
	        System.out.println("Fetching & Deleting User with name " + name);
	  
	        Users user = userService.findByName(name);
	        if (user == null) 
	        {
	            System.out.println("Unable to delete. User with name " + name + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	  
	        userService.deleteUserById(name);
	        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	    }
}
