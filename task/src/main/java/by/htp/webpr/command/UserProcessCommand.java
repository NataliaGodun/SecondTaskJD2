package by.htp.webpr.command;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.htp.webpr.domain.User;

@Controller
@RequestMapping("/user")
public class UserProcessCommand {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@RequestMapping("/showUserForm")
	public String showForm(Model theModel) {

		theModel.addAttribute("user", new User());

		return "UserForm";
	}

	@RequestMapping("/processUserForm")
	public String processForm1(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult,
			Model model) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();
		
		String page=null;
		User user=null;
		if (theBindingResult.hasErrors()) {
			page="UserForm";
			
		} else {
	
			try {
				session.beginTransaction();
				
				user=(User) session.createQuery("from User where login = "+theUser.getLogin()+" and password="+theUser.getPassword()).list().get(0);
				
				session.getTransaction().commit();
				
				model.addAttribute("user", user);
				
				page ="main";
				
			}catch(Exception e){
				//log wrong login or password
				 page= "error";
				

			} finally {
				factory.close();
			}
			
		}
		return page;
	}

	@RequestMapping("/showRegistrationForm")
	public String registration(Model theModel) {

		theModel.addAttribute("user", new User());

		return "registrationForm";
	}

	@RequestMapping("/registration")
	public String registrationForm(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult,
			Model model) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();
		System.out.println("theBindingResult: "+theBindingResult);

		if (theBindingResult.hasErrors()) {
			
			return "registrationForm";
			
		} else {
			try {
				session.beginTransaction();
				session.save(theUser);
				session.getTransaction().commit();
				
			} finally {
				factory.close();
			}
			return "main";
		}
	}

	@RequestMapping("/readUsers")
	public String processForm(Model model) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		try {
		
			User user = new User();

			session.beginTransaction();

			List<User> result = session.createQuery("FROM User").list();

			session.getTransaction().commit();

			model.addAttribute("users", result);

		} finally {
			factory.close();
		}

		return "WorkPage";
	}
	@RequestMapping("/delete")
	public String processDelete(@RequestParam("userId")int theId) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		try {
			 System.out.println("delete student object...");
			

			session.beginTransaction();
			
			
			User user =(User)session.createQuery("FROM User where id="+theId).list().get(0);
			session.delete(user);
			session.getTransaction().commit();

			

		} finally {
			factory.close();
		}

		return "redirect:/user/readUsers";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormUpdate(@RequestParam("userId")int theId, Model theModel) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		try {
			 
			session.beginTransaction();
			
			User user =(User)session.createQuery("FROM User where id="+theId).list().get(0);
			
			theModel.addAttribute("user", user);
			
			session.getTransaction().commit();


		} finally {
			factory.close();
		}

		return "DataForm";
	}
	@RequestMapping("/update")
	public String processUpdate(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult,
			Model TheModel,@RequestParam ("id") int theId) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		if (theBindingResult.hasErrors()) {
			System.out.println(theBindingResult);
			return "DataForm";
		} else {
			try {
				session.beginTransaction();
				session.update(theUser);
				session.getTransaction().commit();
			
				
			} finally {
				factory.close();
			}
			return "redirect:/user/readUsers";
		}
	}

}
