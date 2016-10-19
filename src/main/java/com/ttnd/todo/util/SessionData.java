package com.ttnd.todo.util;

import java.io.Serializable;
import java.util.Locale;

import com.ttnd.todo.entities.User;


public class SessionData implements Serializable {

  
    private User user;

   
    private Locale locale;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
