package com.ttnd.todo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ttnd.todo.dao.TodoDao;
import com.ttnd.todo.entities.Todo;
import com.ttnd.todo.util.TodoInputValidator;

@Repository
public class TopicDaoImpl implements TodoDao {

	@Autowired
	TodoInputValidator todoInputValidator;
	
	Session session = null;
	
	@Override
	public Todo create(Todo todo) {
		session = todoInputValidator.getSessionFactory().openSession();
		session.save(todo);
		session.beginTransaction().commit();
		session.flush();
		//session.close();
		return todo;
	}

	@Override
	public List<Todo> getTodoListByUser(Long id,Integer offset, Integer maxResults) {
		session = todoInputValidator.getSessionFactory().openSession();
		Criteria cr =  session.createCriteria(Todo.class);
		cr.add(Restrictions.eq("userId", id));
		cr.setFirstResult(offset!=null?offset:0);
		cr.setMaxResults(maxResults!=null?maxResults:10);
		List<Todo> todos = cr.list();
		//session.close();
		return todos;
	}

	@Override
	public Todo getTodoById(Long todoId) {
		session = todoInputValidator.getSessionFactory().openSession();
		Todo todo = (Todo)session.get(Todo.class, todoId);
		
		return todo;
	}

	@SuppressWarnings("finally")
	@Override
	public Todo update(Todo todo) throws Exception{
		try{
		session =  todoInputValidator.getSessionFactory().openSession();
		session.update(todo);
		session.beginTransaction().commit();
		session.flush();
		todo = (Todo)session.get(Todo.class, todo.getId());
		}catch(Exception e){
			System.out.println(" Exception occure uring update todo"+e.getMessage());
			throw new Exception(e);
		}
		finally{
			return todo;
		}
	}

	@Override
	public List<Todo> searchTodoListByTitle(Long userId, String title) {
		session = todoInputValidator.getSessionFactory().openSession();
		
		Criteria cr = session.createCriteria(Todo.class);
		cr.add(Restrictions.eq("userId", userId));
		cr.add(Restrictions.ilike("title", "%"+title+"%"));
		List<Todo> todos = cr.list();
		return todos;
	}

	@Override
	public void remove(Todo todo) {
		session = todoInputValidator.getSessionFactory().openSession();
		session.delete(todo);
		session.beginTransaction().commit();
		session.flush();
	}

	@Override
	public Todo findTodoByUserIdAndTodoIdAndTitle(Long userId, Long todoId, String title) {
		session = todoInputValidator.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(Todo.class);
		cr.add(Restrictions.eq("id",todoId));
		cr.add(Restrictions.eq("title", title));
		cr.add(Restrictions.eq("userId", userId));
		Todo todo = (Todo)cr.uniqueResult();
		return todo;
	}

	@Override
	public Long getTotalTodoCount(Long id) {
		session =  todoInputValidator.getSessionFactory().openSession();
		Criteria cr  =  session.createCriteria(Todo.class);
		cr.add(Restrictions.eq("userId",id));
		cr.setProjection(Projections.rowCount());
		return (Long) cr.uniqueResult();
	}

	@Override
	public void removeAllTodoByUser(Long userId) {
		session =  todoInputValidator.getSessionFactory().openSession();
		Criteria cr =  session.createCriteria(Todo.class);
		cr.add(Restrictions.eq("userId", userId));
		List<Todo> todos =  cr.list();
		for (Todo todo : todos) {
			session.delete(todo);
		}
		session.beginTransaction().commit();
		session.flush();
		
	}

}
