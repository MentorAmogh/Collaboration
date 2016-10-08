package com.collaborationserver.dao;
import java.util.List;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.collaborationserver.model.UserCredentials;
import com.collaborationserver.model.Users;
import com.collaborationserver.service.UserService;


@Repository
public class UserServiceImpl implements UserService 
{
	@Autowired
	SessionFactory sessionFactory;
	
	
	

	public Users findByName(String name) 
	{
		if(isUserExist(name))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			System.out.println("i am searching for " + name);
			Users c=(Users)s.get(Users.class,name);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return c;
		}
		else
			return null;
	}

	public String saveUser(Users user) 
	{
		if(!isUserExist(user.getUsername()))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			s.save(user);
			UserCredentials uc=new UserCredentials();
			uc.setUsername(user.getUsername());
			uc.setPassword(user.getPassword());
			s.save(uc);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "User Add";
		}
		else
			return "User Exsist";
	}

	public String updateUser(Users user)
	{	
		if(isUserExist(user.getUsername()))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			Users c=(Users)s.get(Users.class,user.getUsername());
			c.setPhno(user.getPhno());
			c.setEmail(user.getEmail());
			s.update(c);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "User Updated";
		}
		else
			return "User Not Found";
	}

	public String deleteUserById(String name)
	{
		if(isUserExist(name))
		{
			Session s=sessionFactory.openSession();
			Transaction tx=s.getTransaction();
			tx.begin();
			Users c=(Users)s.get(Users.class,name);
			UserCredentials uc=(UserCredentials)s.get(UserCredentials.class,name);
			s.delete(uc);
			s.delete(c);
			tx.commit();
			s.flush();
			s.clear();
			s.close();
			return "User Updated";
		}
		else
			return "User Not Found";
	}


	public List<Users> findAllUsers() 
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		List<Users> showuser = s.createQuery("FROM Users").list();
		tx.commit();
		s.flush();
		s.clear();
		s.close();
		return showuser;
	}

	public boolean isUserExist(String name)
	{
		Session s=sessionFactory.openSession();
		Transaction tx=s.getTransaction();
		tx.begin();
		Users c=(Users)s.get(Users.class,name);
		s.flush();
		s.clear();
		s.close();
		if(c==null)
			return false;
		else
			return true;
	}
	
}
