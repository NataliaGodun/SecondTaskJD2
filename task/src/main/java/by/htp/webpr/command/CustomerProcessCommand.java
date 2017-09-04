package by.htp.webpr.command;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import by.htp.webpr.domain.User;

@Controller
@RequestMapping("/user")
public class CustomerProcessCommand {

	
	/*
	 * @InitBinder public void initBinder(WebDataBinder dataBinder) {
	 * 
	 * StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	 * dataBinder.registerCustomEditor(String.class, stringTrimmerEditor); }
	 * 
	 * @RequestMapping("/showCustomerForm") public String showForm(Model
	 * theModel) {
	 * 
	 * theModel.addAttribute("customer", new Customer());
	 * 
	 * return "customer-form"; }
	 */

	@RequestMapping("/readUsers")
	public String processForm(Model model) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		try {
			//System.out.println("Creating new student object...");
			User user= new User();

			 session.beginTransaction();
		     
			 List<User> result = session.createQuery("FROM User").list();
		     
			 session.getTransaction().commit();

		     System.out.println("-----" + result.get(0).getName());
		     
		     model.addAttribute("users", result);
		
		} finally {
			factory.close();
		}

		return "main-page";
	}
		    
		
		
	
	@RequestMapping("/createUsers")
	public String processAddUser(Model model) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating new student object...");
			User user= new User("Taiskinkaîîî","Virsha");

			 session.beginTransaction();
			 
		     session.save(user);    
			 List<User> result = session.createQuery("FROM User").list();
		     
			 session.getTransaction().commit();

			 model.addAttribute("users", result);
		
		} finally {
			factory.close();
		}

		return  "main-page";	}
}
