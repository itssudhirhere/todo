package com.ttnd.todo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttnd.todo.dao.UserDao;
import com.ttnd.todo.entities.User;
import com.ttnd.todo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	UserDao userDao;
	
	@Override
	@Transactional
	public User createUser(User user) {
		try{
		return userDao.createUser(user);
		}catch(Exception e){
			System.out.println("exception in creating User");
			return null;
		}
	}

	@Override
	public void showUser() {
		userDao.showUser();
		
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
		
	}
	@Override
	public User getUserByEmailAndPassword(String email, String Password){
		return userDao.getUserByEmailAndPassword(email, Password);
	}

	@Override
	public void remove(User user) {
		userDao.remove(user);
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

}
