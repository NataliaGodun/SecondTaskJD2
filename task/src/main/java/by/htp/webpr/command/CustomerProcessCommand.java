package by.htp.webpr.command;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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
	public String processForm1(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		Session session = factory.openSession();

		System.out.println("Last name: |" + theUser.getSurname() + "|");

		System.out.println("theBindingResult: " + theBindingResult);

		try {
			session.beginTransaction();
			Query query = session.createQuery("from User where name = :paramName");
			query.setParameter("paramName", theUser.getName());
			List list = query.list();

			session.getTransaction().commit();

		} finally {
			factory.close();
		}

		if (theBindingResult.hasErrors()) {
			return "UserForm";
		} else {
			return "main-page";
		}
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
					int i = theUser.getId();
					List<User> user = session.createQuery("from User s where " + "s.id=" + i).getResultList();

					session.getTransaction().commit();

					model.addAttribute("user", user);
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
			// System.out.println("Creating new student object...");
			User user = new User();

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
			User user = new User("Taiskinkaîîî", "Virsha");

			session.beginTransaction();

			session.save(user);
			List<User> users = session.createQuery("FROM User s where s.name='Natasha15'").getResultList();

			session.getTransaction().commit();

			model.addAttribute("users", users);

		} finally {
			factory.close();
		}

		return "main-page";
	}
}
