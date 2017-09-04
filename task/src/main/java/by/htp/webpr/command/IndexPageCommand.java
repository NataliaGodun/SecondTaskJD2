package by.htp.webpr.command;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageCommand {
	
	@RequestMapping("/")
	public String showForm(Model theModel) {
		
		return "redirect:user/showUserForm";
	}

}