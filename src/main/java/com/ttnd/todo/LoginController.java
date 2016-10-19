package com.ttnd.todo;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.todo.entities.User;
import com.ttnd.todo.service.UserService;
import com.ttnd.todo.util.SessionData;
import com.ttnd.todo.web.forms.LoginForm;



@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	private MessageSource messageProvider;

	@Autowired
	private SessionData sessionData ;//=  new SessionData();

	    
	@RequestMapping("/login")
    public ModelAndView redirectToLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("loginTabStyle", "active");
        modelAndView.addObject("loginForm", new LoginForm());
        modelAndView.setViewName("user/login");
        return modelAndView;
    }
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(@Valid LoginForm loginForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        final String view = "user/login";

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("error", messageProvider.getMessage("login.error.global", null, null));
        	modelAndView.setViewName(view);
            return modelAndView;
        }


        User user = userService.getUserByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        
        if (user != null) {
            sessionData.setUser(user);
            sessionData.setLocale(Locale.ENGLISH);
            modelAndView.setViewName("redirect:/todo/todos");
            return modelAndView;       
        }else{
        modelAndView.addObject("error", messageProvider.getMessage("login.error.global.invalid", null, null));
        modelAndView.setViewName(view);
        return modelAndView;
        }
    }
	
	@RequestMapping("/logout")
    public String logout(HttpSession session) {
        sessionData.setUser(null); //sessionData = null causes NPE in next login
        session.invalidate();
        return "index";
    }
}
