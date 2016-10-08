package com.collaborationserver.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaborationserver.model.ForumComment;

import com.collaborationserver.service.ForumCommentServices;


@Repository
public class CommentServiceImpl implements 
ForumCommentServices {

	@Autowired
	SessionFactory sessionFactory;
	
	

	public ForumComment findById(int comment) {
		if(isForumCommentExists(comment)){
			Session s = sessionFactory.openSession();
			Transaction tx = s.getTransaction();
			tx.begin();
			ForumComment fc =(ForumComment)s.get(ForumComment.class, comment);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return fc;
		}else
				
			return null;
	}

	@Override
	public String saveComment(ForumComment fouComment) {
		if(!isForumCommentExists(fouComment.getFourmId())){
			Session s= sessionFactory.openSession();
			Transaction tx = s.getTransaction();			
			tx.begin();
			s.save(fouComment);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "Comment added";
		}
		else
			return "something wrong";
	}

	public String deleteComment(int comment) {
		if(isForumCommentExists(comment)){
			Session s= sessionFactory.openSession();
			Transaction tx = s.getTransaction();
			tx.begin();
			ForumComment fcomment = (ForumComment)s.get(ForumComment.class, comment);
			s.delete(fcomment);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "Comment Deleted";
		}else
		return "NO comments found";
	}

	@Override
	public List<ForumComment> findAllComments() {
		
		Session s= sessionFactory.openSession();
		Transaction tx = s.getTransaction();
		tx.begin();
		List<ForumComment> showComment = s.createQuery("FROM ForumComment").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showComment;
	}

	@Override
	public boolean isForumCommentExists(int comment) {
		Session s = sessionFactory.openSession();
		Transaction tx = s.getTransaction();
		tx.begin();
		ForumComment fc = (ForumComment)s.get(ForumComment.class, comment);
		s.flush();
		s.clear();
		s.close();
		if(fc==null)
		return false;
		else
		return true;
	}

	

}
