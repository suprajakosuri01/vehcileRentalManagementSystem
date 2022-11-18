package com.vehicle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.vehicle.dao.UserDAO;
import com.vehicle.pojo.User;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String home() {
		return "redirect:/login.htm";
	}
	
	// ##################################### LOGIN ######################################	

	@GetMapping("/login.htm")
	public String loginGet(Model model, User user) {

		return "login";
	}
	@PostMapping("/login.htm")
	public String userValidation(@ModelAttribute("user") User user, BindingResult result, SessionStatus status,
			HttpServletRequest req, UserDAO userdao, Model model) {

		HttpSession session = req.getSession();
		String usrEmail = req.getParameter("usrEmail");
		String usrPassword = req.getParameter("usrPassword");
		
	
		boolean flagCustomer = false;
		boolean flagAdmin = false;
		boolean flagEmp = false;
		try {

			flagCustomer = userdao.Customervalid(usrEmail, usrPassword);
			flagAdmin = userdao.Adminvalid(usrEmail, usrPassword);
			flagEmp = userdao.Employeevalid(usrEmail, usrPassword);
		} catch (Exception e) {
			
			System.out.println("Error in validate customer method///userValidation");
		}

		if (flagCustomer) {
			System.out.println("user is a customer");
			session.setAttribute("usrEmail", usrEmail);
			
			return "customer/customer-dashboard";
		} else if (flagAdmin) {
			System.out.println("user is a admin");
			session.setAttribute("usrEmail", usrEmail);
			return "admin/admin-dashboard";
		} else if (flagEmp) {
			System.out.println("user is a employee");
			session.setAttribute("usrEmail", usrEmail);
			model.addAttribute(usrEmail);
			return "employee/employee-dashboard";
		}
		String error="Incorrect Username/password";
		model.addAttribute("error",error);
		return "login";
	}
	
	// ##################################### LOGOUT ######################################

		@GetMapping("/signout.htm")
		public String signout(HttpSession session) {

			session.invalidate();
			return "redirect:/login.htm";
		}
		
		// ##################################### REGISTRATION ######################################
		
		@GetMapping("/register.htm")
		public String addUserGet(ModelMap model, User user) {
			// command object
			model.addAttribute("user", user);

			// return form view
			return "Registration";
		}

		@PostMapping("/register.htm")
		public String addUserPost(@ModelAttribute("user") User user,HttpServletRequest request, BindingResult result, SessionStatus status,
				UserDAO userdao,Model model) throws Exception {

			System.out.println("check 0");

			System.out.println(user.getUsrEmail());
			System.out.println(user.getName());
			System.out.println(user.getUsrPassword());
			System.out.println(user.getTitle());
			System.out.println("check 1");
			
			HttpSession session=request.getSession();
			System.out.println("check 2");

			String UsrEmail=request.getParameter("UsrEmail");
			System.out.println("check 3");

			User User=userdao.fetchUserByusrEmail(UsrEmail);
			
			System.out.println("check 4");

			
			if(User !=null)
			{
				System.out.println("check 5");

				String error="User already exists";
				model.addAttribute("error",error);
				return "Registration";
			}

			System.out.println("check 6");

			userdao.saveUser(user);

			System.out.println("check 7");

			
			if (result.hasErrors()) {
				System.out.println("faced error");

				return "Registration";
			}
			System.out.println("before login success is returned");
			
			status.setComplete(); // mark it complete
			System.out.println("status is set");

			return "loginSuccess";
		}

	
}
