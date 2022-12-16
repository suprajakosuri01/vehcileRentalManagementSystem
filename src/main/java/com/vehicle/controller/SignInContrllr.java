package com.vehicle.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import com.vehicle.dao.LoginDataAccessObject;
import static com.vehicle.dao.ProjectConstants.*;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.vehicle.pojo.User;
import javax.servlet.http.HttpServletRequest;

import com.vehicle.dao.UserDataAccessObject;

@Controller
public class SignInContrllr {

    @RequestMapping("/")
    public String init() {
        return REDIR_TO_SIGN_IN;
    }

    @GetMapping("/signin.htm")
    public String fetchSignIn(Model m, User user) {
        return SIGN_IN;
    }

    @PostMapping("/signin.htm")
    public String userValidation(@ModelAttribute(USER) User validateUserObj, BindingResult bindErr,
            SessionStatus s, HttpServletRequest req, LoginDataAccessObject signInDataAccessObject, Model m) {

        HttpSession userValidationSession = fetchSession(req);

        try {
            if (signInDataAccessObject.checkCustmer(req.getParameter(USR_EMAIL), req.getParameter(USR_PASSWRD))) {
                System.out.println("Welcome To Vehicle Rental Company - dear customer");
                userValidationSession.setAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
                return CUSTOMER_HOME;
            }
            if (signInDataAccessObject.checkManager(req.getParameter(USR_EMAIL), req.getParameter(USR_PASSWRD))) {
                System.out.println("Welcome To Vehicle Rental Company - dear Manager");
                userValidationSession.setAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
                m.addAttribute(req.getParameter(USR_EMAIL));
                return MANAGER_HOME;
            }
            if (signInDataAccessObject.checkAdmin(req.getParameter(USR_EMAIL), req.getParameter(USR_PASSWRD))) {
                System.out.println("Welcome To Vehicle Rental Company - dear admin");
                userValidationSession.setAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
                m.addAttribute(req.getParameter(USR_EMAIL));
                return ADMIN_HOME;
            }

        } catch (Exception e) {
            System.out.println("Encountered exception in userValidation method");
        }

        m.addAttribute(ERR, ERR_WHEN_USER_ENTERS_WRONG_INFO);
        return SIGN_IN;
    }

    @GetMapping("/signout.htm")
    public String signout(HttpSession signOutSession) {
        signOutSession.invalidate();
        return REDIR_TO_SIGN_IN;
    }

    @GetMapping("/register.htm")
    public String fetchNewUser(ModelMap md, User newUserObj) {
        md.addAttribute(USER, newUserObj);
        return REG_USR;
    }

    @PostMapping("/register.htm")
    public String addNewUser(@ModelAttribute(USER) User newUser,
            HttpServletRequest req, BindingResult bindErr,
            SessionStatus s, UserDataAccessObject userDataAccessObject,
            Model md) throws Exception {

        User existingUser = userDataAccessObject
                .fetchUsrByusrEmail(newUser.getUsrEmail());

        if (null != existingUser) {
            String excep = ERR_WHEN_USER_ALREADY_EXISTS;
            md.addAttribute(ERR, excep);
            return REG_USR;
        }
        userDataAccessObject
                .registerUser(newUser);

        if (bindErr.hasErrors()) {

            System.out.println("Encountered error when registering new user");
            return REG_USR;
        }

        s.setComplete();
        System.out.println(NEW_USER_REG_SUCCESS);
        return LOGIN_SUCCESS;
    }

    private HttpSession fetchSession(HttpServletRequest req) {
        return req.getSession();
    }

}
