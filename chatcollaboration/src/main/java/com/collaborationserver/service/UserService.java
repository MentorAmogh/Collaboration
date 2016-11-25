package com.collaborationserver.service;
import java.util.List;

import com.collaborationserver.model.Users;
 
 
 
public interface UserService {
     
     
    Users findByName(String name);
     
    String saveUser(Users user);
     
    String updateUser(Users user);
     
    String deleteUserById(String name);
 
    List<Users> findAllUsers(); 
     
   public boolean isUserExist(String name);
     
}