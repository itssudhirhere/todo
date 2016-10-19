package com.ttnd.todo.dao;

import java.util.List;

import com.ttnd.todo.entities.Todo;

public interface TodoDao {
	public Todo create(Todo todo);
	public List<Todo> getTodoListByUser(Long id,Integer offset, Integer maxResults);
	public Todo getTodoById(Long todoId);
	public Todo update(Todo todo)throws Exception;
	public List<Todo> searchTodoListByTitle(Long userId, String title);
	public void remove(Todo todo);
	public Todo findTodoByUserIdAndTodoIdAndTitle(Long userId,Long todoId, String title);
	public Long getTotalTodoCount(Long id);
	public void removeAllTodoByUser(Long userId);
}
