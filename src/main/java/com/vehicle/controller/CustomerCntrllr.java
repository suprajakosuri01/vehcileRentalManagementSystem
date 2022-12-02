package com.vehicle.controller;

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
import com.vehicle.dao.UserDAO;
import com.vehicle.dao.VehicleDAO;
import com.vehicle.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import com.vehicle.pojo.Vehicle;

@Controller
public class CustomerCntrllr {

//home page
    @GetMapping("/cusHome.htm")
    public String customerHomePage(Model model, HttpServletRequest request) {
        return "customer/cusHome";
    }

//fetch vehicles
    // cars in use for the user
    @GetMapping("/fetchVehicles.htm")
    public String fetchvehicles(Model model, VehicleDAO vehicleDAO, UserDAO userdao, HttpServletRequest request)
            throws Exception {

        HttpSession session = request.getSession();
        String usrEmail = request.getParameter("usrEmail");
        model.addAttribute("usrEmail", usrEmail);

        List<Vehicle> vehicles = vehicleDAO.fetchAllVehicles();
        model.addAttribute("vehicles", vehicles);
        User user = userdao.fetchUsrByusrEmail(usrEmail);
        List<Vehicle> usrVehicles = vehicleDAO.fetchReservedVehicleofUsr(user);
        usrVehicles.addAll(vehicleDAO.fetchVechUsingbyUsr(user));

        request.setAttribute("usrVehicles", usrVehicles);

        return "customer/fetchVehicles";
    }

    //(orders)
    @GetMapping("/orders.htm")
    public String fetchVehicleOrders(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
            throws Exception {

        HttpSession session = request.getSession();
        String usrEmail = request.getParameter("usrEmail");

        User user = userdao.fetchUsrByusrEmail(usrEmail);
        List<Vehicle> vehicles = vehicleDAO.fetchVechUsingbyUsr(user);
        if (vehicles != null) {

        }
        model.addAttribute("vehicles", vehicles);
        return "customer/orders";
    }

    //booked vehicles
    @GetMapping("/bookedVehicles.htm")
    public String getMyReservations(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
            throws Exception {
        HttpSession session = request.getSession();
        String usrEmail = request.getParameter("usrEmail");
        User user = userdao.fetchUsrByusrEmail(usrEmail);
        List<Vehicle> vehicles = vehicleDAO.fetchReservedVehicleofUsr(user);

        if (vehicles != null) {
        }
        model.addAttribute("vehicles", vehicles);

        return "customer/bookedVehicles";
    }

    //cfrm reserv
    @GetMapping("/reservationconfirm.htm")
    public String fetchRsvnCfrm(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao, SessionStatus status)
            throws Exception {

        HttpSession session = request.getSession();
        String usrEmail = request.getParameter("usrEmail");
        System.out.println("email id obtained from session scope -- " + usrEmail);

        String cid = request.getParameter("carId");
        System.out.println("carId obtained from session scope -- " + cid);

        int castid = Integer.parseInt(cid.trim());

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(castid);

        User user = userdao.fetchUsrByusrEmail(usrEmail);
        model.addAttribute("usrEmail", usrEmail);

        model.addAttribute("vehicle", vehicle);

        LocalDate rentEndDate = LocalDate.now().plusDays(2);
        LocalDate rentStartDate = LocalDate.now();
        LocalDate rentReturnDate = LocalDate.now().plusDays(3);
        model.addAttribute("rentReturnDate", rentReturnDate);
        model.addAttribute("rentStartDate", rentStartDate);
        model.addAttribute("rentEndDate", rentEndDate);

        return "customer/reservationConfirm";

    }

    @PostMapping("/reservationconfirm.htm")
    public String postRsvnCfrm(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, SessionStatus status,
            VehicleDAO vehicleDAO, HttpServletRequest request, UserDAO userdao) throws Exception {
        HttpSession session = request.getSession();

        String usrEmail = request.getParameter("usrEmail");
        int carId = Integer.parseInt(request.getParameter("c1"));
         String rentEndDate = request.getParameter("rentEndDate");
        String rentStartDate = request.getParameter("rentStartDate");

     

        String rentReturnDate = request.getParameter("rentReturnDate");

        String usrId = request.getParameter("usrEmail");

        User user = userdao.fetchUsrByusrEmail(usrEmail);
       LocalDate rrd = LocalDate.parse(rentReturnDate);
        LocalDate rsd = LocalDate.parse(rentStartDate);
        LocalDate red = LocalDate.parse(rentEndDate);
    
        vehicle.setRentEndDate(red);
        vehicle.setRentReturnDate(rrd);
        vehicle.setPickupReady(true);
        vehicle.setReservedByUser(user);
        vehicle.setCarId(carId);
        vehicle.setLicensePlate(vehicle.getLicensePlate());
        vehicle.setModel(vehicle.getModel());
        vehicle.setYear(vehicle.getYear());
        vehicle.setRentStartDate(rsd);
        String imagePath = request.getParameter("imagePath");
        vehicle.setImagePath(imagePath);
        vehicleDAO.updateVehicle(vehicle);
        status.setComplete();
        System.out.println("reserv success");
        return "customer/successreserv";
    }

}
