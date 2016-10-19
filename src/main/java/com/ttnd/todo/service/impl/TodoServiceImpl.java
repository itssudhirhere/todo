package com.ttnd.todo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttnd.todo.dao.TodoDao;
import com.ttnd.todo.dao.UserDao;
import com.ttnd.todo.entities.Todo;
import com.ttnd.todo.entities.User;
import com.ttnd.todo.service.TodoService;
import com.ttnd.todo.util.SessionData;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
    private SessionData sessionData;

	@Resource
	TodoDao todoDao;
	
	@Override
	@Transactional
	public Todo create(Todo todo) {
		final User user = sessionData.getUser();
        todo.setDone(false);
        todo.setUserId(user.getId());
		todo = todoDao.create(todo);
		return todo;
	}

	@Override
	public List<Todo> getTodoListByUser(Long id,Integer offset, Integer maxResults) {
		List<Todo> todos = todoDao.getTodoListByUser(id,offset,maxResults);
		return todos;
	}

	@Override
	public Todo getTodoById(Long todoId) {
		Todo todo = todoDao.getTodoById(todoId);
		return todo;
	}

	@Override
    @Transactional
	public Todo update(Todo todo) throws Exception {
		try{
			final User user = sessionData.getUser();
			todo.setUserId(user.getId());
			todo = todoDao.update(todo);
		}catch(Exception e){
			throw new Exception(e);
		}
        
		return todo;
	}

	@Override
	public List<Todo> searchTodoListByTitle(Long userId, String title) {
		List<Todo> todos = todoDao.searchTodoListByTitle(userId, title);
		return todos;
	}

	@Override
	//@Transactional
	public void remove(Todo todo) {
		todoDao.remove(todo);

	}

	@Override
	public Long getTotalTodoCount(Long id) {
		return todoDao.getTotalTodoCount(id);
		
	}

}
