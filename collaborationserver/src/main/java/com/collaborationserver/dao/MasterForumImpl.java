package com.collaborationserver.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaborationserver.model.Forum;

import com.collaborationserver.service.MasterForumService;
@Repository
public class MasterForumImpl implements MasterForumService {
	
	@Autowired
	SessionFactory sessionFactory;
		
		
	public Forum findById(String Forumid) {
		
		if(isForumExists(Forumid))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			Forum c=(Forum)s.get(Forum.class,Forumid);
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
	public String saveForum(Forum masterf) {
		if (!isForumExists(masterf.getForumid())){
			Session s1 = sessionFactory.openSession();
			Transaction tx = s1.getTransaction();
			tx.begin();
			s1.save(masterf);		
			tx.commit();
			s1.flush();
			s1.clear();
			s1.close();
			return "forum added";
		}else
			return "forum exists";
		
	}

	@Override
	public String updateForum(Forum masterfor) {
		if(isForumExists(masterfor.getForumid()))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			Forum c=(Forum)s.get(Forum.class,masterfor.getForumid());
			c.setForumtopic(masterfor.getForumsubtopic());
			c.setForumsubtopic(masterfor.getForumsubtopic());
			s.update(c);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "Forum Updated";
		}
		else
			return "Forum Not Found";
	}

	@Override
	public String deleteForumId(String fid) {
		if(isForumExists(fid))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			Forum c=(Forum)s.get(Forum.class,fid);
			s.delete(c);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "Forum Deleted";
		}
		else
			return "Forum Does Not Exists";
	}

	@Override
	public List<Forum> findAllForums() 
	{
		Session s=sessionFactory.openSession();
		Transaction tx = s.getTransaction();
		tx.begin();
		List<Forum> showForum = s.createQuery("FROM Forum").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		System.out.println("Listing all forums");
		return showForum;
	}



	@Override
	public boolean isForumExists(String forname) {
		Session s1 = sessionFactory.openSession();
	
		Forum mf = (Forum)s1.get(Forum.class, forname);
		s1.flush();
		s1.clear();
		s1.close();
		if(mf== null)
			return false;
		else
			return true;
	}
	
	

}
