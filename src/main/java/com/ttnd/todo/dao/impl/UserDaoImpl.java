package com.ttnd.todo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttnd.todo.dao.TodoDao;
import com.ttnd.todo.dao.UserDao;
import com.ttnd.todo.entities.User;
import com.ttnd.todo.util.TodoInputValidator;

@Repository
public class UserDaoImpl implements UserDao{


	@Autowired
	TodoInputValidator todoInputValidator;
	
	@Resource
	TodoDao todoDao;
	
	Session session = null;
	
	@Override
	public User createUser(User user) throws Exception {
		try{
		System.out.println("todoInputValidator=1="+todoInputValidator);
		System.out.println("todoInputValidator=2="+todoInputValidator.getTodoSessionFactory());
		System.out.println("todoInputValidator=3="+todoInputValidator.getSessionFactory());
		System.out.println("todoInputValidator=4="+todoInputValidator.getSessionFactory().openSession().toString());

		session = todoInputValidator.getSessionFactory().openSession();
		System.out.println("====session class"+session.getClass());
		user.setId((Long)session.save(user));
		session.beginTransaction().commit();
		session.flush();
		
		return user;
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new Exception(e);
			
		}finally{
			session.close();
		}
		
	}

	@Override
	public void showUser() {
		session = todoInputValidator.getSessionFactory().openSession();
		User user = (User) session.get(User.class, 3);
		System.out.println("user name====="+user);
		session.close();
	}

	@Override
	public User getUserByEmail(String email) {
		session = todoInputValidator.getSessionFactory().openSession();
		
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", email));
		User user = (User)cr.uniqueResult();
		return user;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String Password){
		session = todoInputValidator.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", email));
		cr.add(Restrictions.eq("password", Password));
		return (User)cr.uniqueResult();
	}

	@Override
	@Transactional
	public void remove(User user) {
		System.out.println(user);
		todoDao.removeAllTodoByUser(user.getId());
		session = todoInputValidator.getSessionFactory().openSession();
		session.delete(user);
		session.beginTransaction().commit();
		session.flush();
		session.close();
	}

	@Override
	public void update(User user) {
		session = todoInputValidator.getSessionFactory().openSession();
		session.update(user);
		session.beginTransaction().commit();
		session.flush();
		session.close();
	}
}
