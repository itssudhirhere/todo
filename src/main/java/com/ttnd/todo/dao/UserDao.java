package com.ttnd.todo.dao;

import com.ttnd.todo.entities.User;

public interface UserDao {

	public User createUser(User user) throws Exception;;
	public void showUser();
	public User getUserByEmail(String email);
	public User getUserByEmailAndPassword(String email, String Password);
	public void remove(User user);
	public void update(User user);
}
