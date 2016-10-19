package com.ttnd.todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.todo.dao.TodoDao;
import com.ttnd.todo.entities.Todo;
import com.ttnd.todo.entities.User;
import com.ttnd.todo.enums.Priority;
import com.ttnd.todo.service.TodoService;
import com.ttnd.todo.util.SessionData;
import com.ttnd.todo.util.TodoListUtils;
import com.ttnd.todo.util.TodoPriorityPropertyEditor;
import com.ttnd.todo.util.TodoUtil;

@Controller
public class TodosController {
	 private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	    @Autowired
	    private SessionData sessionData;

	    @Autowired
	    private MessageSource messageProvider;

	    @Autowired
	    TodoService todoService;

	    @Resource
		TodoDao todoDao;
	    
	    @InitBinder
	    public void initBinder(WebDataBinder binder) {
	    	
	        SimpleDateFormat dateFormat = new SimpleDateFormat(TodoListUtils.DATE_FORMAT);
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	        binder.registerCustomEditor(Priority.class, new TodoPriorityPropertyEditor());
	    }

	    
	    @RequestMapping("/todo/new")
	    public String redirectToCreateTodoPage(Model model) {
	        model.addAttribute("today", new SimpleDateFormat(TodoListUtils.DATE_FORMAT).format(new Date()));
	        model.addAttribute("todo", new Todo());
	        return "todo/create";
	    }

	    @RequestMapping(value = "/todo/new", method = RequestMethod.POST)
	    public String doCreateTodo(@ModelAttribute Todo todo) {
	        todoService.create(todo);
	        return "redirect:/todo/todos";
	    }

	    
	    @RequestMapping("/todo/todos")
	    public ModelAndView loadTodoList(Integer offset, Integer maxResults) {

	        ModelAndView modelAndView = new ModelAndView();
	        // user login is ensured by the login filter/interceptor
	        List<Todo> todoList = todoService.getTodoListByUser(sessionData.getUser().getId(),offset,maxResults);
	        modelAndView.addObject("todoList", todoList);
	        int totalCount = todoList.size();
	        Long count = todoService.getTotalTodoCount(sessionData.getUser().getId());
	        int doneCount = TodoListUtils.countTotalDone(todoList);
	        int todoCount = totalCount - doneCount;
	        modelAndView.addObject("totalCount", totalCount);
	        modelAndView.addObject("doneCount", doneCount);
	        modelAndView.addObject("todoCount", todoCount);
	        modelAndView.addObject("homeTabStyle", "active");
	        modelAndView.addObject("offset",offset);
	        modelAndView.addObject("count",count);
	        modelAndView.setViewName("todo/home");
	        return modelAndView;

	    }
	    
	   
	    @RequestMapping(value="/todo/todos/{todoId}/update",method=RequestMethod.GET)
	    public String redirectToUpdateTodoPage(@PathVariable long todoId, Model model) {
	        Todo todo = todoService.getTodoById(todoId);
	        model.addAttribute(todo);
	        model.addAttribute("dueDate", new SimpleDateFormat(TodoListUtils.DATE_FORMAT).format(todo.getDueDate()));
	        TodoUtil todoUtil = new TodoUtil();
    		//todoUtil.ChangeDateFormate(todo.getDueDate());
	        
	        LOGGER.info("--todo-dfkgkl-updated---");
	        return "todo/update";
	    }
	    
	 
	    @ResponseBody
	    @RequestMapping(value="/todo/todos/update",method=RequestMethod.GET)
	    public ModelAndView redirectToUpdateTodoPageAjax(@RequestParam(value = "todoId", required = true) long todoId, Model model) {
	        Todo todo = todoService.getTodoById(todoId);
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.addObject("todo",todo);
	        modelAndView.addObject("dueDate", new SimpleDateFormat(TodoListUtils.DATE_FORMAT).format(todo.getDueDate()));
	        TodoUtil todoUtil = new TodoUtil();
	        modelAndView.setViewName("todo/updateAjax");
	        LOGGER.info("--todo---ajax---updated---GET");
	        return modelAndView;
	    }

	    @ResponseBody
	    @RequestMapping(value = "todo/todos/updateAjax", method = RequestMethod.POST)
	    public ModelAndView doUpdateTodoAjax(@ModelAttribute Todo todo, ModelMap model) {
	    	ModelAndView modelAndView = new ModelAndView();
	    	System.out.println("--todo ajax --update post--");
	    	Todo todo1 = todoDao.findTodoByUserIdAndTodoIdAndTitle(todo.getUserId(), todo.getId(), todo.getTitle());
	    	Map<String, String> response = new HashMap<String, String>();
	    	if(todo1 != null){
	    		try{
	    			todo = todoService.update(todo);
	    			//modelAndView.addObject("messageCss","alert-success");
		    		//modelAndView.addObject("message","Todo Updated Successfully !!");
	    			model.addAttribute("type", "success");
	    			model.addAttribute("message", "Todo Updated Successfully !!");
	    		}
	    		catch(Exception e){
	    			System.out.println(e.getMessage());
//	    			modelAndView.addObject("messageCss","alert-danger");
	//	    		modelAndView.addObject("message","Something Went Wrong !!");
	    			modelAndView.addObject("type", "danger");
	    			modelAndView.addObject("message","Something Went Wrong !!");
	    		}
	    	}else{
	    	//	modelAndView.addObject("messageCss","alert-danger");
	    		//modelAndView.addObject("message","Invalid Todo !!");
	    		modelAndView.addObject("type", "danger");
	    		modelAndView.addObject("message","Invalid Todo !!");
	    	}
	    		//modelAndView.setViewName("/todo/update");
	    		TodoUtil todoUtil = new TodoUtil();
	    		todoUtil.ChangeDateFormate(todo.getDueDate());
	    		//modelAndView.addObject(todo);
	    		System.out.println("+++++++=model=========="+model);
	        return modelAndView;
	    }
	    
	    
	    @RequestMapping(value = "/todo/update", method = RequestMethod.POST)
	    public ModelAndView doUpdateTodo(@ModelAttribute Todo todo) {
	    	ModelAndView modelAndView = new ModelAndView();
	    	Todo todo1 = todoDao.findTodoByUserIdAndTodoIdAndTitle(todo.getUserId(), todo.getId(), todo.getTitle());
	    	if(todo1 != null){
	    		try{
	    			todo = todoService.update(todo);
	    			modelAndView.addObject("messageCss","alert-success");
		    		modelAndView.addObject("message","Todo Updated Successfully !!");
	    		}
	    		catch(Exception e){
	    			System.out.println(e.getMessage());
	    			modelAndView.addObject("messageCss","alert-danger");
		    		modelAndView.addObject("message","Something Went Wrong !!");
	    		}
	    	}else{
	    		modelAndView.addObject("messageCss","alert-danger");
	    		modelAndView.addObject("message","Invalid Todo !!");
	    	}
	    		modelAndView.setViewName("/todo/update");
	    		TodoUtil todoUtil = new TodoUtil();
	    		todoUtil.ChangeDateFormate(todo.getDueDate());
	    		modelAndView.addObject(todo);
	        return modelAndView;
	    }

	    

	    @RequestMapping(value = "/todo/todos/{todoId}/delete", method = RequestMethod.POST)
	    public ModelAndView deleteTodo(@PathVariable long todoId) {
	        ModelAndView modelAndView = new ModelAndView();
	      
	        Todo todo = todoService.getTodoById(todoId);
	        if (todo == null) {
	            modelAndView.addObject("error", messageProvider.getMessage("no.such.todo", new Object[]{todoId}, sessionData.getLocale()));
	            modelAndView.setViewName("error");
	        } else {
	            todoService.remove(todo);
	            modelAndView.setViewName("redirect:/todo/todos");
	        }
	        return modelAndView;
	    }

	  
	    @RequestMapping(value = "/todo/todos/search", method = RequestMethod.GET)
	    public String searchTodoList(@RequestParam String title, Model model) {
	    	System.out.println(sessionData.getUser().getId());
	    	System.out.println(title);
	        List<Todo> todoList = todoService.searchTodoListByTitle(sessionData.getUser().getId(), title);
	        model.addAttribute("todoList", todoList);
	        model.addAttribute("title", title);
	        return "todo/search";
	    }

}
