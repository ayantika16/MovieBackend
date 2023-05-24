package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.demo.exception.SecretNameMismatchException;
import com.example.demo.exception.UserNotFoundException;
import com.example.entity.Role;
import com.example.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    

    public void initRoleAndUser() {

    	Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("USER");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        //adminUser.setId(1);
        adminUser.setUsername("admin.admin");
        adminUser.setPassword("Password@123");
        adminUser.setEmail("admin@gmail.com");
        adminUser.setPetname("dog");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
        
        
        User normalUser = new User();
        //normalUser.setId(2);
        normalUser.setUsername("user.user");
        normalUser.setPassword("Password@123");
        normalUser.setEmail("user@gmail.com");
        normalUser.setPetname("cat");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        normalUser.setRole(userRoles);
        userDao.save(normalUser);
    }

    public User registerNewUser(User user) {
//        Role role = roleDao.findById("User").get();
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(role);
//        user.setRole(userRoles);
//        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
//
//        return userDao.save(user);
    	
    	if(user!=null)
		{
			 Role role = roleDao.findById(2).get();
		        Set<Role> userRoles = new HashSet<>();
		        userRoles.add(role);
		        user.setRole(userRoles);
		        
			return userDao.saveAndFlush(user);
			
		}
		return null;
    }

    
	public List<User> getAllUsers() {
	
		List<User> userList = userDao.findAll();
		
		if(userList!=null & userList.size() >0)
		{
			return userList;
		}
		else
			return null;
	}
	
public Optional<User> getUserByName(String username){
		
		return this.userDao.findByUserName(username);
	}

public String forgotPassword(String username) throws UserNotFoundException{
	
	Optional<User> user=userDao.findByUserName(username);
	
	if(!user.isPresent()) {
		throw new UserNotFoundException();
	}
	
	return "Please provide your petname for updating password";
}

public User updatePassword(String name, String petname, String password) throws UserNotFoundException,SecretNameMismatchException{
	
	Optional<User> user=userDao.findByUserName(name);
	
	if(!user.isPresent()) {
		throw new UserNotFoundException();
	}
	
	User founduser=user.get();
//	System.out.println((founduser.getPetname()));
//	System.out.println(secret);
//	System.out.println(founduser.getPetname().equalsIgnoreCase(password));
	if(founduser.getPetname().equalsIgnoreCase(petname)) {
		founduser.setPassword(password);
	}else {
		throw new SecretNameMismatchException();
	}
	
	return userDao.saveAndFlush(founduser);
	
	
}
}
