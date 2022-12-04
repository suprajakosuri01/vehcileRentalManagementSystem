package com.vehicle.controller;

import org.springframework.ui.Model;
import com.vehicle.dao.UserDataAccessObject;
import org.springframework.web.bind.annotation.GetMapping;
import com.vehicle.dao.VehicleDataAccessObject;
import com.vehicle.pojo.User;
import org.springframework.validation.BindingResult;
import com.vehicle.pojo.Vehicle;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.stereotype.Controller;

@Controller
public class AdminCntrllr {

    @GetMapping("/adminhome.htm")
    public String fetchAdminHomeView(Model model, HttpServletRequest req) {
        return "admin/adminHome";

    }

    @GetMapping("/listofusrs.htm")
    public String fetchUsers(Model model, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userdao, HttpServletRequest request)
            throws Exception {

        List<User> availableUsers = userdao.fetchEveryUsr();

        model.addAttribute("user", availableUsers);
        return "admin/ListofUsrs";

    }


    @GetMapping("/usermodify.htm")
    public String fetchModifyUsers(Model model, HttpServletRequest request, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userdao)
            throws Exception {

        HttpSession session = request.getSession();

        String usrid = request.getParameter("usrId");
        int castusrid = Integer.parseInt(usrid);

        User user1 = userdao.fetchUsrById(castusrid);

        model.addAttribute("user", user1);
        return "admin/userModify";

    }

    @PostMapping("/usermodify.htm")

    public String ModifyUsersPost(SessionStatus status, VehicleDataAccessObject vehicleDAO, HttpServletRequest request, UserDataAccessObject userdao, Model model)
            throws Exception {

        HttpSession session = request.getSession();

        int cid = Integer.parseInt(request.getParameter("uid2"));

        User user2 = userdao.fetchUsrById(cid);

        String homeAddr = request.getParameter("userAddress");
        String phonenum = request.getParameter("userPhonenum");
        user2.setTitle(user2.getTitle());
        user2.setUsrId(user2.getUsrId());
        user2.setUserPhonenum(phonenum);
        user2.setName(user2.getName());
        user2.setUsrPassword(user2.getUsrPassword());
        user2.setUserAddress(homeAddr);

        userdao.modifyUser(user2);
        return "admin/userModified";

    }

    @GetMapping("/userdelete.htm")
    public String fetchDeleteUsers(Model model, HttpServletRequest request, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userdao)
            throws Exception {

        HttpSession session = request.getSession();

        String usrid1 = request.getParameter("usrId");
        int castusrid1 = Integer.parseInt(usrid1);

        User userDelete = userdao.fetchUsrById(castusrid1);
        model.addAttribute("user", userDelete);
        return "admin/userDelete";

    }

    @PostMapping("/userdelete.htm")

    public String postDeleteUsers(SessionStatus status, VehicleDataAccessObject vehicleDAO, HttpServletRequest request, UserDataAccessObject userdao, BindingResult result)
            throws Exception {
        HttpSession session = request.getSession();

        int cid1 = Integer.parseInt(request.getParameter("usrdel"));
        User userdel = userdao.fetchUsrById(cid1);
        if (userdel != null) {
            System.out.println("got user");
        }

        List<Vehicle> vehiclesCurrentlyInUse = vehicleDAO.fetchVechUsingbyUsr(userdel);
        for (Vehicle vehicle : vehiclesCurrentlyInUse) {
            vehicle.setxUser(null);
            vehicle.setRentStartDate(null);
            vehicle.setRentEndDate(null);
            vehicle.setRentReturnDate(null);
            vehicleDAO.modifyVehicle(vehicle);
        }

        List<Vehicle> vehiclesrsvd = vehicleDAO.fetchReservedVehicleOfUser(userdel);
        for (Vehicle vehicle : vehiclesrsvd) {
             vehicle.setRentStartDate(null);
             vehicle.setRentReturnDate(null);
            vehicle.setReservedByUser(null);
            vehicle.setRentEndDate(null);
            
            vehicleDAO.modifyVehicle(vehicle);

        }
        userdao.removeUser(userdel);

        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
            }
            return "admin/ListofUsrs";
        }
        status.setComplete();
  System.out.println("user deleted");
        return "admin/userDeleted";
    }

}
