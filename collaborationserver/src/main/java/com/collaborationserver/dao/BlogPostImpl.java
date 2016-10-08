package com.collaborationserver.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaborationserver.model.BlogPost;
import com.collaborationserver.model.Forum;

import com.collaborationserver.service.BlogPostServices;

@Repository
public class BlogPostImpl implements BlogPostServices {

	@Autowired
	SessionFactory sessionFactory;
	
	
	
	
	public BlogPost findByBlogId(int blogid) {
		
		if(isBlogExists(blogid))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();			
			BlogPost c=(BlogPost)s.get(BlogPost.class,blogid);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return c;
		}
		else
			
			return null;
	}

	@Override
	public String saveBlog(BlogPost blogPost) {
		if (!isBlogExists(blogPost.getBlogid())){
			Session s1 = sessionFactory.openSession();
			Transaction tx = s1.getTransaction();
			tx.begin();
			s1.save(blogPost);		
			tx.commit();
			s1.flush();
			s1.clear();
			s1.close();
			return "Blog is  added";
		}else
			return "Blog is not exists";
	}

	@Override
	public String updateBlog(BlogPost blogPost) {
		if(isBlogExists(blogPost.getBlogid()))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			BlogPost c=(BlogPost)s.get(BlogPost.class,blogPost.getBlogid());
			c.setBlogpostcontent(blogPost.getBlogpostcontent());
			c.setBlogtitle(blogPost.getBlogtitle());
			c.setTags(blogPost.getTags());
			c.setCdate(blogPost.getCdate());
			c.setUserid(blogPost.getUserid());
			s.update(c);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
System.out.println("Blog Updated");
			return "Blog Updated";
		}
		else
			return "Blog Not Found";
	}


	public String deleteBlog(int name) {
		if(isBlogExists(name))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			BlogPost b=(BlogPost)s.get(BlogPost.class,name);
			s.delete(b);			
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "Blog Updated";
		}
		else
			return "Blog Not Found";
	}

	@Override
	public List<BlogPost> findAllBlog() {
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<BlogPost> showBlog = s.createQuery("FROM BlogPost").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showBlog;

	}

	public boolean isBlogExists(int name) {
		Session s= sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		BlogPost c=(BlogPost)s.get(BlogPost.class,name);
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		if(c==null)
			return false;
		else	
			return true;
	}





}
