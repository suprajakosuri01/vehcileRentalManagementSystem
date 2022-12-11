package com.vehicle.controller;

import static com.vehicle.dao.ProjectConstants.*;
import com.vehicle.dao.UserDataAccessObject;
import com.vehicle.dao.VehicleDataAccessObject;
import com.vehicle.pojo.User;
import com.vehicle.pojo.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import java.io.File;
import java.time.Instant;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerCntrllr {

    @GetMapping("/managerhome.htm")
    public String fetchManagerHome(Model md) {
        return MANAGER_HOME;
    }

    @GetMapping("/vehiclesadd.htm")
    public String vehiclesAdd(ModelMap md, Vehicle vehicle, HttpServletRequest req) {
        md.addAttribute(VEHICLE, vehicle);
        return MANAGER_VEHICLE_ADD;
    }

    @PostMapping("/vehiclesadd.htm")
    public String registeredVehicle(@ModelAttribute(VEHICLE) Vehicle vehicle, BindingResult bindErr, SessionStatus s,
            VehicleDataAccessObject vehicleDAO, Model md) throws Exception {
        vehicle.setPickupReady(false);

        if (vehicleDAO.isValidLicense(vehicle.getLicensePlate())) {
            md.addAttribute(LISCENSE_PLATE_ERROR, "licencsePlate number pre-existing, "
                    + "Please Try with another number.");
            return MANAGER_VEHICLE_ADD;
        }

        String pic_path = "pic_" + Instant.now().toEpochMilli() + ".png";

        vehicle.setImagePath(pic_path);
        try {

            vehicle.getImgFile().transferTo(new File(STORED_IMG_PATH + pic_path));
        } catch (Exception e1) {
            throw new Exception("Failed to register vehicle in Manager Controller", e1);
        }

        vehicleDAO.registerVehicle(vehicle);
        if (bindErr.hasErrors()) {
            return MANAGER_VEHICLE_ADD;
        }
        s.setComplete();
        return MANAGER_VEHICLE_ADDED_SUCCESS;
    }

    @GetMapping("/editvehicle.htm")
    public String fetchEditVehicle(Model md, HttpServletRequest req, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userDataAccessObject)
            throws Exception {

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(
                Integer
                        .parseInt(req.getParameter("carId")));
        md.addAttribute(vehicle);

        return MANAGER_VEHICLE_EDIT;
    }

    @PostMapping("/editvehicle.htm")
    public String ModifyVehicle(SessionStatus s,
            VehicleDataAccessObject vehicleDAO, HttpServletRequest req, UserDataAccessObject userDataAccessObject) throws Exception {

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(Integer.parseInt(req.getParameter("c1")));
        String model = req.getParameter(MODEL);
        String year1 = req.getParameter(YEAR);
        int castedYear = Integer.parseInt(year1);

        vehicle.setModel(req.getParameter(MODEL));
        vehicle.setYear(castedYear);

        if (null != req.getParameter(DELETE_RSVN)) {
            resetDateFields(vehicle);
        } else {
            setRsvn(vehicle);
        }
        if (null != req.getParameter(DELETE_USER)) {
            resetDeletedUserRsvnDateFields(vehicle);
        } else {
            setUserRsvnFields(vehicle);
        }
        vehicle.setImagePath(vehicle.getImagePath());
        vehicleDAO.modifyVehicle(vehicle);

        s.setComplete();

        return MANAGER_EDITED;
    }

    private void setUserRsvnFields(Vehicle vehicle) {
        System.out.println("at delete user manager controller null");
        vehicle.setxUser(vehicle.getxUser());
        vehicle.setRentStartDate(vehicle.getRentStartDate());
        vehicle.setRentEndDate(vehicle.getRentEndDate());
        vehicle.setRentReturnDate(vehicle.getRentReturnDate());
    }

    private void resetDeletedUserRsvnDateFields(Vehicle vehicle) {
        System.out.println("in delete user manager controller");
        vehicle.setxUser(null);
        vehicle.setRentStartDate(null);
        vehicle.setRentEndDate(null);
        vehicle.setRentReturnDate(null);
    }

    private void setRsvn(Vehicle vehicle) {
        vehicle.setReservedByUser(vehicle.getReservedByUser());
        vehicle.setRentStartDate(vehicle.getRentStartDate());
        vehicle.setRentEndDate(vehicle.getRentEndDate());
        vehicle.setRentReturnDate(vehicle.getRentReturnDate());
    }

    private void resetDateFields(Vehicle vehicle) {
        vehicle.setReservedByUser(null);
        vehicle.setRentStartDate(null);
        vehicle.setRentEndDate(null);
        System.out.println("in delete reservation manager controller");
        vehicle.setRentReturnDate(null);
    }

    @GetMapping("/deleteall.htm")
    public String fetchAndDeleteAll(Model md, HttpServletRequest req, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userDataAccessObject)
            throws Exception {

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(Integer.parseInt(req.getParameter("carId")));

        md.addAttribute(vehicle);

        return MANAGER_DELETE_ALL;
    }

    @PostMapping("/deleteall.htm")
    public String DeleteAll(SessionStatus s, VehicleDataAccessObject vehicleDAO, HttpServletRequest req,
            UserDataAccessObject userDataAccessObject) throws Exception {

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(Integer.parseInt(req.getParameter("c1")));
        vehicleDAO.removeVehicle(vehicle);
        s.setComplete();
        return MANAGER_DELETE_SUCCESS;

    }

    @GetMapping("/pickup.htm")
    public String fetchPickupDetailsOfUser(Model md, HttpServletRequest req, 
            VehicleDataAccessObject vehicleDAO, 
            UserDataAccessObject userDataAccessObject)
            throws Exception {

        int carId = Integer.parseInt(req.getParameter("carId"));
        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
        md.addAttribute(VEHICLE, vehicle);
        md.addAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));
        return MANAGER_PICKUP;

    }

    @PostMapping("/pickup.htm")
    public String savePickupDetails(SessionStatus s, 
            VehicleDataAccessObject vehicleDAO, HttpServletRequest req,
            UserDataAccessObject userDataAccessObject) throws Exception {  
        
        User fetchUserByEmailObj = userDataAccessObject.fetchUsrByusrEmail(req.getParameter(USR_EMAIL));
        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(Integer.parseInt(req.getParameter("c4")));
        vehicle.setPickupReady(false);
        vehicle.setReservedByUser(null);
        vehicle.setxUser(fetchUserByEmailObj);
        vehicle.setCarId(vehicle.getCarId());
        vehicleDAO.modifyVehicle(vehicle);
        s.setComplete();
        return MANAGER_PICKUP_SUCCESS;
    }

    @GetMapping("/returnvehicle.htm")
    public String fetchvehRtn(Model md, VehicleDataAccessObject vehicleDAO, HttpServletRequest req) throws Exception {

        md.addAttribute(USR_EMAIL, req.getParameter(USR_EMAIL));

        LinkedHashMap<Vehicle, String> vehiclesReceived = vehicleDAO.fetchVehiclesCurrentlyUsedByUser();

        md.addAttribute(VEHICLES, vehiclesReceived);
        return MANAGER_RETURN_VEHICLE;

    }

    // accept return
    @GetMapping("/return.htm")
    public String fetchAcceptRtn(Model md, HttpServletRequest req, VehicleDataAccessObject vehicleDAO)
            throws Exception {
        String usrEmail = req.getParameter("usrEmail");
        String id2 = req.getParameter("carId");
        int cId1 = Integer.parseInt(id2);
        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId1);
        req.setAttribute("vehicle", vehicle);
        req.setAttribute("usrEmail", usrEmail);

        return MANAGER_RETURN;

    }

    @PostMapping("/return.htm")
    public String postAcceptRtn(SessionStatus status,
            VehicleDataAccessObject vehicleDAO, HttpServletRequest req) throws Exception {

        int cId1 = Integer.parseInt(req.getParameter("c5"));

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId1);

        vehicle.setRentStartDate(null);
        vehicle.setRentEndDate(null);
        vehicle.setRentReturnDate(null);
        vehicle.setPickupReady(false);
        vehicle.setReservedByUser(null);
        vehicle.setxUser(null);
        vehicle.setCarId(vehicle.getCarId());
        vehicle.setLicensePlate(vehicle.getLicensePlate());
        vehicle.setModel(vehicle.getModel());
        vehicle.setYear(vehicle.getYear());

        vehicle.setImagePath(vehicle.getImagePath());

        vehicleDAO.modifyVehicle(vehicle);

        status.setComplete();
        return MANAGER_RETURN_SUCCESS;
    }

    // vehciles list
    @GetMapping("/vehicles.htm")
    public String fetchVehicles(Model model, VehicleDataAccessObject vehicleDAO) throws Exception {

        System.out.println("in manager controller fetchall");
        List<Vehicle> vehicle = vehicleDAO.fetchAllVehicles();
        model.addAttribute("vehicle", vehicle);
        return MANAGER_VEHICLES;

    }

    // customer reservations
    @GetMapping("/vehiclereserve.htm")
    public String fetchVehicleResrvtns(Model model, VehicleDataAccessObject vehicleDAO, HttpServletRequest request, SessionStatus status)
            throws Exception {

        LinkedHashMap<Vehicle, String> vehcilesRsvd = vehicleDAO.fetchAllReservedVehicles();

        if (vehcilesRsvd != null) {
        }
        model.addAttribute("vehicles", vehcilesRsvd);
        return MANAGER_RESERVE_VEHICLE;
    }
}
