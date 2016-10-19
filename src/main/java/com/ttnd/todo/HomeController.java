package com.ttnd.todo;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.todo.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		System.out.println("---------------before user service----");
		//userService.addUser();
		System.out.println("---------------after user service-------");
		//userService.showUser();
		model.addAttribute("serverTime", formattedDate );
		
//		return "home";
		return "redirect:about";
	}

	@RequestMapping("/about")
    public ModelAndView redirectToAboutPage() {
        ModelAndView modelAndView = new ModelAndView("about");
        modelAndView.addObject("aboutTabStyle", "active");
        return modelAndView;
    }
	
	@RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("aboutTabStyle", "active");
        return modelAndView;
    }
	
}
