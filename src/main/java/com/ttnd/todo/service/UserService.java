package com.ttnd.todo.service;

import com.ttnd.todo.entities.User;

public interface UserService {
	public User createUser(User user);
	public void showUser();
	public User getUserByEmail(String email);
	public User getUserByEmailAndPassword(String email, String Password);
	public void remove(User user);
	public void update(User user);
}
