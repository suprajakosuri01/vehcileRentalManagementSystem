package com.vehicle.controller;

import com.vehicle.dao.LoginDataAccessObject;
import static com.vehicle.dao.ProjectConstants.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vehicle.dao.UserDataAccessObject;
import com.vehicle.pojo.User;

@Controller
public class LoginCntrllr {
    
    @RequestMapping("/")
    public String init() {
        return "redirect:/login.htm";
    }
    
    
    @GetMapping("/login.htm")
    public String fetchSignIn(Model model, User user) {
        return "login";
    }
    
    @PostMapping("/login.htm")
    public String userValidation(@ModelAttribute("user") User user, BindingResult result, SessionStatus status,HttpServletRequest req, LoginDataAccessObject logindao, Model model) {
        HttpSession session = fetchSession(req);

        boolean checkCustomer = false;
	boolean checkAdmin = false;
        boolean checkEmp = false;
        try {

            checkCustomer = logindao.checkCustmer(req.getParameter(USR_EMAIL), req.getParameter(USR_PASSWRD));
            System.out.println("customer validation check completed");
	     checkAdmin = logindao.checkAdmin(req.getParameter(USR_EMAIL), req.getParameter(USR_PASSWRD));
            System.out.println("admin validation completed");
            checkEmp = logindao.checkEmployee(req.getParameter(USR_EMAIL), req.getParameter(USR_PASSWRD));
            System.out.println("emp validation check completed");
        } catch (Exception e) {
            System.out.println("Encountered exception in userValidation method");
        }

        if (checkCustomer) {
            System.out.println("Logged in as customer");
            session.setAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
            return "customer/cusHome";

        } else if (checkEmp) {
            System.out.println("Logged in as Manager");
            session.setAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
            model.addAttribute(req.getParameter(USR_EMAIL));
            return "Manager/ManagerHome";
        }
        
        else if (checkAdmin) {
            System.out.println("Logged in as admin");
            session.setAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
            model.addAttribute(req.getParameter(USR_EMAIL));
            return "admin/adminHome";
        }
       
        model.addAttribute("error", "enter valid userEmail/user Pwd");
        return "login";
    }

    @GetMapping("/signout.htm")
    public String signout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.htm";
    }

    @GetMapping("/register.htm")
    public String fetchNewUser(ModelMap model, User user) {
        model.addAttribute("user", user);
        return "Registration";
    }

    @PostMapping("/register.htm")
    public String addNewUser(@ModelAttribute("user") User newUser, HttpServletRequest request, BindingResult result, SessionStatus status,
            UserDataAccessObject userdao, Model model) throws Exception {
        HttpSession session = fetchSession(request);
        User existingUser = userdao.fetchUsrByusrEmail(newUser.getUsrEmail());
        if (null != existingUser) {
            String excep = " Email already exits,try with another email!!";
            model.addAttribute("error", excep);
            return "Registration";
        }
        userdao.registerUser(newUser);
        if (result.hasErrors()) {
            System.out.println("faced error during registartion");
            return "Registration";
        }
        System.out.println("before login success is returned");
        status.setComplete();
        System.out.println("new user registered");
        return "loginSuccess";
    }
    
    
    private HttpSession fetchSession(HttpServletRequest req) {
    
        return req.getSession();
    }

}
