package com.ttnd.todo.service;

import java.util.List;

import com.ttnd.todo.entities.Todo;

public interface TodoService {

	public Todo create(Todo todo);
	public List<Todo> getTodoListByUser(Long id,Integer offset, Integer maxResults);
	public Todo getTodoById(Long todoId);
	public Todo update(Todo todo)throws Exception;
	public List<Todo> searchTodoListByTitle(Long userId, String title);
	public void remove(Todo todo);
	public Long getTotalTodoCount(Long id);
}
