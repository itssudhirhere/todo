
package com.ttnd.todo.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ttnd.todo.util.TodoListUtils;

import java.io.IOException;


public class StatusLabelTag extends SimpleTagSupport {

  
    private boolean status;

    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = getJspContext().getOut();
        String statusLabel = TodoListUtils.getStatusLabel(status);
        out.print(statusLabel);

    }

  

    public void setStatus(boolean status) {
        this.status = status;
    }

}
