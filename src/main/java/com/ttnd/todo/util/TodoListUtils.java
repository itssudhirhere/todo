
package com.ttnd.todo.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ttnd.todo.entities.Todo;
import com.ttnd.todo.enums.Priority;

public class TodoListUtils {

    public static final String SESSION_USER = "user";

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    
    private TodoListUtils() { }

    public static String getPriorityIcon(Priority priority) {

        String priorityIcon = "";

        switch (priority) {
            case HIGH:
                priorityIcon = "up";
                break;
            case MEDIUM:
                priorityIcon = "right";
                break;
            case LOW:
                priorityIcon = "down";
        }

        return priorityIcon;
    }

    /**
     * Return the css style to apply for the given status.
     *
     * @param status the status of the todo
     * @return the css style of the status
     */
    public static String getStatusStyle(boolean status) {
        return status ? "label-success" : "";
    }

   
    public static String getStatusLabel(boolean status) {
        return status ? "DONE" : "TODO";
    }

    public static String highlight(final String input, final String pattern) {

        String cssClass = "label label-warning";
        String startSpanTag = "<span class=\"" + cssClass + "\">";
        String endSpanTag = "</span>";

        StringBuilder stringBuilder = new StringBuilder(startSpanTag);
        stringBuilder.append(pattern);
        stringBuilder.append(endSpanTag);

        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(input);

        return matcher.replaceAll(stringBuilder.toString());

    }

    /**
     * Count the number of todos that are done.
     * @param todoList the todos list
     * @return the number of todos done
     */
    public static int countTotalDone(List<Todo> todoList) {
        int count = 0;
        for (Todo todo : todoList) {
            if (todo.isDone()) {
                count ++;
            }
        }
        return count;
    }


}
