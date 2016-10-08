package com.collaborationserver.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaborationserver.model.BlogComment;
import com.collaborationserver.service.BlogCommentService;


@Repository
public class BlogCommentServiceImpl implements BlogCommentService {

	@Autowired
	SessionFactory sessionFactory;
	
	
	
	@Override
	public BlogComment findById(String blog) {
		if(isBlogExist(blog)){
			Session s = sessionFactory.openSession();
			Transaction tx = s.getTransaction();
			tx.begin();
		BlogComment bg = (BlogComment)s.get(BlogComment.class, blog);
		tx.commit();		
		s.flush();
		s.clear();
		s.close();
		return bg;
		}else
			return null;
		
	}

	@Override
	public String saveBlogComment(BlogComment blogComment) {
		if(!isBlogExist(blogComment.getComments())){
			Session s= sessionFactory.openSession();
			Transaction tx = s.getTransaction();
			tx.begin();
			s.save(blogComment);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "blog comment saved";
		}else
			
		return "Cant add your comment";
	}


	
	
		@Override
	public String deleteBlogComment(String blogComment) {
		if(!isBlogExist(blogComment)){
			Session s= sessionFactory.openSession();
			Transaction tx = s.getTransaction();
			tx.begin();
			BlogComment bgc = (BlogComment)s.get(BlogComment.class,  blogComment);
			s.delete(bgc);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "Blog deleted..!";
		}
		else
			return "Blog Not Found";
	}

	

	@Override
	public List<BlogComment> findAllBlogComment() {
		
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<BlogComment> showComment = s.createQuery("FROM BlogComment").list();
		System.out.println("Blog Comments are fetching..");
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		System.out.println("Listing all Blog Comments ");		
		System.out.println("Blog Comments end"+showComment);
		return showComment;
	}

	@Override
	public boolean isBlogExist(String blog) {
		Session s= sessionFactory.openSession();
		Transaction tx = s.getTransaction();
		tx.begin();
		BlogComment bgc= (BlogComment)s.get(BlogComment.class, blog);
		s.flush();
		s.clear();
		s.close();
		if(bgc==null)
			return false;
		else
			return true;
	}

	

}
