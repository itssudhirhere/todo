package com.ttnd.todo.util;

import java.beans.PropertyEditorSupport;

import com.ttnd.todo.enums.Priority;


public class TodoPriorityPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Priority value = (Priority) getValue();
        return value.toString();
    }

    @Override
    public void setAsText(String text) {
        setValue(Priority.valueOf(text));
    }
}
