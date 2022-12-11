package com.vehicle.controller;

import static com.vehicle.dao.ProjectConstants.*;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.support.SessionStatus;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.vehicle.dao.UserDataAccessObject;
import com.vehicle.dao.VehicleDataAccessObject;
import com.vehicle.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import com.vehicle.pojo.Vehicle;

@Controller
public class CustomerCntrllr {

    @GetMapping("/cusHome.htm")
    public String customerHomePage(Model md, HttpServletRequest req) {
        return CUSTOMER_HOME;
    }

    @GetMapping("/fetchVehicles.htm")
    public String fetchvehicles(Model md, VehicleDataAccessObject vehicleDAO,
            UserDataAccessObject userDataAccessObject, HttpServletRequest req)
            throws Exception {

        try {

            md.addAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
            List<Vehicle> vehicles = vehicleDAO.fetchAllVehicles();
            md.addAttribute(VEHICLES, vehicles);

            User usrObj = userDataAccessObject.fetchUsrByusrEmail(
                    req.getParameter(USR_EMAIL));

            List<Vehicle> usrVehicles = vehicleDAO.fetchReservedVehicleOfUser(usrObj);

            usrVehicles.addAll(vehicleDAO.fetchVehcUsingbyUsr(usrObj));

            req.setAttribute(USER_VEHICLES, usrVehicles);
        } catch (Exception ex) {
            throw new Exception("Encountered exception in fetchvehicles method in Customer Controller", ex);
        }

        return CUSTOMER_FETCH_VEHICLES;
    }

    @GetMapping("/orders.htm")
    public String fetchVehicleOrders(Model md, HttpServletRequest req,
            VehicleDataAccessObject vehicleDAO,
            UserDataAccessObject userDataAccessObject)
            throws Exception {
        try {
            User userOrderObj = userDataAccessObject
                    .fetchUsrByusrEmail(req.getParameter(USR_EMAIL));
            List<Vehicle> vehicles = vehicleDAO.fetchVehcUsingbyUsr(userOrderObj);
            md.addAttribute(VEHICLES, vehicles);
        } catch (Exception ex) {
            throw new Exception("Encountered exception in fetchVehicleOrders method in Customer Controller", ex);

        }

        return CUSTOMER_ORDERS;
    }

    @GetMapping("/bookedVehicles.htm")
    public String getMyReservations(Model md, HttpServletRequest req,
            VehicleDataAccessObject vehicleDAO,
            UserDataAccessObject userDataAccessObject)
            throws Exception {
        try {
            User userReservationObj = userDataAccessObject.fetchUsrByusrEmail(req.getParameter(USR_EMAIL));
            List<Vehicle> vehicles = vehicleDAO.fetchReservedVehicleOfUser(userReservationObj);
            md.addAttribute(VEHICLES, vehicles);
        } catch (Exception ex) {
            throw new Exception("Encountered exception in getMyReservations method in Customer Controller", ex);
        }
        return CUSTOMER_BOOKED_VEHICLES;
    }

    @GetMapping("/reservationconfirm.htm")
    public String fetchRsvnCfrm(Model md, HttpServletRequest req, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userDataAccessObject, SessionStatus s)
            throws Exception {

        String cid = req.getParameter("carId");

        int castid = Integer.parseInt(cid.trim());

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(castid);

        md.addAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));

       
        md.addAttribute(VEHICLE, vehicle);
        
        md.addAttribute(VEHICLE_RENT_RETURN_DATE, getRentDate(3));
        md.addAttribute(VEHICLE_RENT_START_DATE, getCurrentDate());
        md.addAttribute(VEHICLE_RENT_END_DATE, getRentDate(2));
        
        System.out.println("RENT END DATE FROM fetchRsvnCfrm method - " + getRentDate(2).toString());
        
         

        return CUSTOMER_RESERVATION_CONFIRMATION;

    }

    public LocalDate getRentDate(long noOfDays) {
        return LocalDate.now().plusDays(noOfDays);
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @PostMapping("/reservationconfirm.htm")
    public String SaveRsvn(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult bindErr, SessionStatus s,
            VehicleDataAccessObject vehicleDAO, HttpServletRequest req, UserDataAccessObject userDataAccessObject)
            throws Exception {

        try {
            int carId = Integer.parseInt(req.getParameter("c1"));
            User user = userDataAccessObject.fetchUsrByusrEmail(req.getParameter(USR_EMAIL));
            vehicle.setRentEndDate(getDate(req.getParameter(VEHICLE_RENT_END_DATE)));
            vehicle.setRentReturnDate(getDate(req.getParameter(VEHICLE_RENT_RETURN_DATE)));
            vehicle.setPickupReady(true);
            vehicle.setReservedByUser(user);
            vehicle.setCarId(carId);
            vehicle.setLicensePlate(vehicle.getLicensePlate());
            vehicle.setModel(vehicle.getModel());
            vehicle.setYear(vehicle.getYear());
            vehicle.setRentStartDate(getDate(req.getParameter(VEHICLE_RENT_START_DATE)));
            vehicle.setImagePath(req.getParameter(IMG_SRC));
            vehicleDAO.modifyVehicle(vehicle);
            s.setComplete();
        } catch (Exception ex) {

            throw new Exception("Encountered exception in fetchRsvn method in Customer Controller", ex);
        }

        return CUSTOMER_SUCCESSFUL_RSVN;
    }

    public LocalDate getDate(String rentDate) {
        return LocalDate.parse(rentDate);

    }

}
