package com.ttnd.todo.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ttnd.todo.enums.Priority;
import com.ttnd.todo.util.TodoListUtils;

import java.io.IOException;

public class PriorityIconTag extends SimpleTagSupport {

  
    private String priority;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String priorityIcon = TodoListUtils.getPriorityIcon(Priority.valueOf(priority));
        out.print(priorityIcon);
    }

    /*
     * setters for tag attributes
     */

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
