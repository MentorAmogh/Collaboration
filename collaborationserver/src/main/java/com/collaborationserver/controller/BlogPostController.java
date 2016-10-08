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

import com.collaborationserver.model.BlogPost;
import com.collaborationserver.service.BlogPostServices;

@RestController
public class BlogPostController {
	@Autowired
	BlogPostServices blogPostService;
	
	//-------------List all Blog--------------------------------------------------------
    @RequestMapping(value = "/blogPostC", method = RequestMethod.GET)
    public ResponseEntity<List<BlogPost>> listAllForums() 
    {
        List<BlogPost>  blogPost = blogPostService.findAllBlog();
        if(blogPost.isEmpty())
        {
        	System.out.println("Empty No Blog Found");
        	return new ResponseEntity<List<BlogPost>>(HttpStatus.NO_CONTENT);
        }
        System.out.println("Rows Found");
        return new ResponseEntity<List<BlogPost>>(blogPost, HttpStatus.OK);
    }

 //-------------------Retrieve Single Blog--------------------------------------------------------
    
    @RequestMapping(value = "/blogPostC/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BlogPost> getBlogs(@PathVariable("name") int name)
    {
        System.out.println("Fetching Blog with id " + name);
        BlogPost blogp = blogPostService.findByBlogId(name);
        
        if (blogp == null)
        {
            System.out.println("BlogPost With the id "+name + " not found");
            return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Blog Found");
        return new ResponseEntity<BlogPost>(blogp, HttpStatus.OK);
    }
	
  //-------------------Create a Blog--------------------------------------------------------
    
    @RequestMapping(value = "/blogPostC", method = RequestMethod.POST)
    public ResponseEntity<Void> creatBlog(@RequestBody BlogPost blogPost, UriComponentsBuilder ucBuilder)
    {
        System.out.println("Creating Blog " + blogPost.getBlogpostcontent());
        String data=blogPostService.saveBlog(blogPost);
        System.out.println("Data is "+data);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/blogPostC/{name}").buildAndExpand(blogPost.getBlogpostcontent()).toUri());
        System.out.println("Blog " +blogPost.getBlogpostcontent()+" Created");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    
  //-------------------Delete a Bloggg--------------------------------------------------------
    @RequestMapping(value = "/blogPostC/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<BlogPost> deleteBlog(@PathVariable("name") int name) {
        System.out.println("Fetching & Deleting Blog with name " + name);
        
        BlogPost blogPost = blogPostService.findByBlogId(name);
        if (blogPost == null) 
        {
            System.out.println("Unable to delete blog with name " + name + " not found");
            return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
        }
        blogPostService.deleteBlog(name);
        return new ResponseEntity<BlogPost>(HttpStatus.NO_CONTENT);
    }
   
    
    //------------------- Update a Blog --------------------------------------------------------
    
    @RequestMapping(value = "/blogPostC/{name}", method = RequestMethod.PUT)
    public ResponseEntity<BlogPost> updateBlog(@PathVariable("name") int name, @RequestBody BlogPost blogPost) {
        System.out.println("Updating User " + name);	          
        BlogPost blogP = blogPostService.findByBlogId(name);          
        if (blogP==null) 
        {
            System.out.println("Blog with name " + name + " not found");
            return new ResponseEntity<BlogPost>(HttpStatus.NOT_FOUND);
        }
        blogP.setBlogpostcontent(blogPost.getBlogpostcontent());
        blogPostService.updateBlog(blogP);
        return new ResponseEntity<BlogPost>(blogP, HttpStatus.OK);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
