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
		if (theBindingResult.hasErrors()) {
			page="UserForm";
			//return "UserForm";
		} else {
	
			try {
				
				
	           
				
				
				session.beginTransaction();
				System.out.println(theUser.getLogin());
				//User user=(User) session.createQuery("from User where login = :paramName and password=:paramPassword").list().get(0);
				Query query  = session.createQuery("from User where login = :paramName and password=:paramPassword");
				query.setParameter("paramName", theUser.getLogin());
				query.setParameter("paramPassword", theUser.getPassword());
				List list = query.list();

				session.getTransaction().commit();
				System.out.println(list);
				if (list.isEmpty()) page ="UserForm";
				else page= "1";
				model.addAttribute("user", list);

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

		if (theBindingResult.hasErrors()) {
			System.out.println(theBindingResult);
			return "registrationForm";
		} else {
			try {
				session.beginTransaction();
				session.save(theUser);
				session.getTransaction().commit();
				/*int i = theUser.getId();
				List<User> user = session.createQuery("from User s where " + "s.id=" + i).getResultList();

				session.getTransaction().commit();

				model.addAttribute("user", user);*/
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
			 System.out.println("Creating new student object...");
			User user = new User();

			session.beginTransaction();

			List<User> result = session.createQuery("FROM User").list();

			session.getTransaction().commit();

			System.out.println("-----" + result.get(0).getLogin());

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

	
}
