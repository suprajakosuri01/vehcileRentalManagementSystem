package com.vehicle.controller;

import static com.vehicle.dao.ProjectConstants.*;
import org.springframework.ui.Model;
import com.vehicle.dao.UserDataAccessObject;
import org.springframework.web.bind.annotation.GetMapping;
import com.vehicle.dao.VehicleDataAccessObject;
import com.vehicle.pojo.User;
import org.springframework.validation.BindingResult;
import com.vehicle.pojo.Vehicle;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.stereotype.Controller;

@Controller
public class AdminCntrllr {

    @GetMapping("/adminhome.htm")
    public String fetchAdminHomeView(Model md) {
        return ADMIN_HOME;

    }

    @GetMapping("/listofusrs.htm")
    public String fetchAllUsrs(Model md, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userDataAccessObj)
            throws Exception {
        try {
            List<User> availableUsers = userDataAccessObj.fetchEveryUsr();
            md.addAttribute(USER, availableUsers);
        } catch (Exception exe) {

            throw new Exception("Failed to fetch list of users", exe);
        }

        return ADMIN_LIST_OF_USERS;

    }

    @GetMapping("/usermodify.htm")
    public String fetchModifyUsers(Model md, HttpServletRequest req,
            VehicleDataAccessObject vehicleDAO, UserDataAccessObject userDataAccessObj)
            throws Exception {
        try {
            int castUserId = Integer.parseInt(req.getParameter(USER_ID));
            md.addAttribute(USER,
                    userDataAccessObj.fetchUsrById(castUserId));

        } catch (Exception exe) {

            throw new Exception("Failed to modify user", exe);
        }

        return ADMIN_USER_MODIFY;

    }

    @PostMapping("/usermodify.htm")
    public String ModifyUserInfo(SessionStatus s,
            HttpServletRequest req,
            UserDataAccessObject userDataAccessObj)
            throws Exception {

        try {
            int cid = Integer.parseInt(req.getParameter(ADMIN_UID2));

            User modifyUser = userDataAccessObj.fetchUsrById(cid);

            String homeAddr = req.getParameter(USER_ADDR);
            String phonenum = req.getParameter(USER_PHNNUM);

            modifyUser.setUserPhonenum(phonenum);

            modifyUser.setUserAddress(homeAddr);

            userDataAccessObj.modifyUser(modifyUser);
        } catch (Exception exe) {
            throw new Exception("Failed to update user information", exe);
        }

        return ADMIN_USER_MODIFIED;

    }

    @GetMapping("/userdelete.htm")
    public String fetchDeleteUserObj(Model md, HttpServletRequest req,
            UserDataAccessObject userDataAccessObj)
            throws Exception {

        try {
            int castedUserId = Integer.parseInt(req.getParameter(USER_ID));
            md.addAttribute(USER,
                    userDataAccessObj.fetchUsrById(castedUserId));
        } catch (Exception exe) {
            throw new Exception("Encountered exception "
                    + "in fetchDeleteUsers method in Admin Controller", exe);
        }

        return ADMIN_USER_DELETE;

    }

    @PostMapping("/userdelete.htm")
    public String DeleteUserObj(SessionStatus s,
            VehicleDataAccessObject vehicleDAO, HttpServletRequest req,
            UserDataAccessObject userDataAccessObj, BindingResult bindErr)
            throws Exception {

        User usrDelete = userDataAccessObj.fetchUsrById(Integer.parseInt(req.getParameter(USER_DELETE)));
        if (null != usrDelete) {
            System.out.println("Encountered VALID - USER value, HURRY !! ");
        }

        List<Vehicle> vehiclesCurrentlyInUse = vehicleDAO
                .fetchVehcUsingbyUsr(usrDelete);

        resetVehicleInUseByUser(vehiclesCurrentlyInUse, vehicleDAO);

        List<Vehicle> vehiclesrsvd = vehicleDAO.fetchReservedVehicleOfUser(usrDelete);
        for (Vehicle vehicle : vehiclesrsvd) {
            vehicle.setRentStartDate(null);
            vehicle.setRentReturnDate(null);
            vehicle.setReservedByUser(null);
            vehicle.setRentEndDate(null);

            vehicleDAO.modifyVehicle(vehicle);

        }
        userDataAccessObj.removeUser(usrDelete);

        if (bindErr.hasErrors()) {
            return ADMIN_LIST_OF_USERS;
        }
        s.setComplete();
        System.out.println("User deleted successfully");
        return ADMIN_USER_DELETED;
    }

    public void resetVehicleInUseByUser(List<Vehicle> vehiclesCurrentlyInUse,
            VehicleDataAccessObject vehicleDAO) throws Exception {
        
        for (Vehicle vehicle : vehiclesCurrentlyInUse) {
            vehicle.setxUser(null);
            vehicle.setRentStartDate(null);
            vehicle.setRentEndDate(null);
            vehicle.setRentReturnDate(null);
            try {
                vehicleDAO.modifyVehicle(vehicle);
            } catch (Exception ex) {
                throw new Exception("Encountered exception "
                        + "in resetVehicleInUseByUser method in Admin Controller", ex);
            }
        }

    }

}
