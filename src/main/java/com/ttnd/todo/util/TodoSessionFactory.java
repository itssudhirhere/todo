package com.ttnd.todo.util;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Resource
public class TodoSessionFactory {

	private SessionFactory todoSessionFactoryBean;

	public SessionFactory getTodoSessionFactoryBean() {
		return todoSessionFactoryBean;
	}

	public void setTodoSessionFactoryBean(SessionFactory todoSessionFactoryBean) {
		this.todoSessionFactoryBean = todoSessionFactoryBean;
	}

}
