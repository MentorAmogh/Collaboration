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
	 @Autowired
	 UserService userService;  //Service which will do all data retrieval/manipulation work
	 @RequestMapping(value = "/savecompany", method = RequestMethod.POST)
	 public  @ResponseBody String saveCompany( @RequestParam("name") String name,
	 		@RequestParam("employees") long employees,
	 		@RequestParam("headoffice") String headoffice) {		
	 	//
	 	// Code processing the input parameters
	 	//		
	 	return "The company data (name: " + name + ", employees: "+ String.valueOf( employees ) + ", headoffice: " + headoffice + ") is saved";
	 }
	 	
	 //-------------------Retrieve All Users--------------------------------------------------------
	    @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public ResponseEntity<List<Users>> listAllUsers() 
	    {
	        List<Users> users = userService.findAllUsers();
	        if(users.isEmpty())
	        {
	            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
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
