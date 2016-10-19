package com.ttnd.todo.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TodoInputValidator {

@Autowired
private TodoSessionFactory todoSessionFactory;

public TodoSessionFactory getTodoSessionFactory() {
return todoSessionFactory;
}

public void setTodoSessionFactory(TodoSessionFactory todoSessionFactory) {
this.todoSessionFactory = todoSessionFactory;
}

public SessionFactory getSessionFactory() {
return todoSessionFactory.getTodoSessionFactoryBean();
}
}
