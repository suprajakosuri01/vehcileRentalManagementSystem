package com.vehicle.controller;

import static com.vehicle.dao.ProjectConstants.*;
import com.vehicle.dao.UserDataAccessObject;
import com.vehicle.dao.VehicleDataAccessObject;
import com.vehicle.pojo.User;
import com.vehicle.pojo.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerCntrllr {


    @GetMapping("/managerhome.htm")
    public String fetchManagerHome(Model md) {
        return MANAGER_HOME;
    }

    @GetMapping("/vehiclesadd.htm")
    public String vehiclesAdd(ModelMap md, Vehicle vehicle, HttpServletRequest req)
    {
        md.addAttribute(VEHICLE, vehicle);
        return MANAGER_VEHICLE_ADD;
    }

    @PostMapping("/vehiclesadd.htm")
    public String vehicleSave(@ModelAttribute(VEHICLE) Vehicle vehicle, BindingResult bindErr, SessionStatus s,
            VehicleDataAccessObject vehicleDAO, Model md) throws Exception 
    {
        vehicle.setPickupReady(false);
        if (vehicleDAO.isValidLicense(vehicle.getLicensePlate())) {
            String licencsePlateError = "licencsePlate number already exists!";
            md.addAttribute(LISCENSE_PLATE_ERROR, licencsePlateError);
            return MANAGER_VEHICLE_ADD;
        }
        
        String imgp = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + ""
                + new Random().nextInt(100000000) + ".jpg";

        vehicle.setImagePath(imgp);
        try {

            vehicle.getImgFile().transferTo(new File("src/main/webapp/images/" + imgp));
        } catch (IllegalStateException e1) {

        } catch (IOException e1) {

        }

        vehicleDAO.registerVehicle(vehicle);
        if (bindErr.hasErrors()) {
            return MANAGER_VEHICLE_ADD;
        }
        s.setComplete();
        return MANAGER_VEHICLE_ADDED_SUCCESS;
    }

    //edit vehicle
    @GetMapping("/editvehicle.htm")
    public String fetchEditVehicle(Model model, HttpServletRequest request, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userdao)
            throws Exception {
        String cid = request.getParameter("carId");
        int carId = Integer.parseInt(cid);
        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
        model.addAttribute(vehicle);

        return MANAGER_VEHICLE_EDIT;
    }

    @PostMapping("/editvehicle.htm")
    public String EditVehiclePost(SessionStatus status,
            VehicleDataAccessObject vehicleDAO, HttpServletRequest request, UserDataAccessObject userdao) throws Exception {
      
        int carId = Integer.parseInt(request.getParameter("c1"));
        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
        String model = request.getParameter("model");
        String year1 = request.getParameter("year");
        int castedYear = Integer.parseInt(year1);

        String deleteRsvrtn = request.getParameter("deleteRsvrtn");

        String deleteUsr = request.getParameter("deleteUsr");
        vehicle.setRentStartDate(vehicle.getRentStartDate());
        vehicle.setRentEndDate(vehicle.getRentEndDate());
        vehicle.setRentReturnDate(vehicle.getRentReturnDate());
        vehicle.setPickupReady(vehicle.getPickupReady());
        vehicle.setCarId(vehicle.getCarId());
        vehicle.setModel(model);
        vehicle.setYear(castedYear);

        if (deleteRsvrtn != null) {
            vehicle.setReservedByUser(null);
            vehicle.setRentStartDate(null);
            vehicle.setRentEndDate(null);
            System.out.println("in delete reservation manager controller");
            vehicle.setRentReturnDate(null);
        } else {
            vehicle.setReservedByUser(vehicle.getReservedByUser());
            vehicle.setRentStartDate(vehicle.getRentStartDate());
            vehicle.setRentEndDate(vehicle.getRentEndDate());
            vehicle.setRentReturnDate(vehicle.getRentReturnDate());

        }

        if (deleteUsr != null) {
            System.out.println("in delete user manager controller");
            vehicle.setxUser(null);
            vehicle.setRentStartDate(null);
            vehicle.setRentEndDate(null);
            vehicle.setRentReturnDate(null);
        } else {
            System.out.println("at delete user manager controller null");
            vehicle.setxUser(vehicle.getxUser());
            vehicle.setRentStartDate(vehicle.getRentStartDate());
            vehicle.setRentEndDate(vehicle.getRentEndDate());
            vehicle.setRentReturnDate(vehicle.getRentReturnDate());
        }
        vehicle.setImagePath(vehicle.getImagePath());
        vehicleDAO.modifyVehicle(vehicle);

        status.setComplete();

        return MANAGER_EDITED;
    }

    // delete all
    @GetMapping("/deleteall.htm")
	public String fetchDeleteall(Model model, HttpServletRequest request, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userdao)
			throws Exception {



		System.out.println("IN delete method");

		String cid = request.getParameter("carId");
		


		int carId = Integer.parseInt(cid);
		

		


		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);

		model.addAttribute(vehicle);
	

		return MANAGER_DELETE_ALL;
	}

	@PostMapping("/deleteall.htm")
	public String postDeleteall(SessionStatus status, VehicleDataAccessObject vehicleDAO, HttpServletRequest request,
			UserDataAccessObject userdao) throws Exception {

		
		int carId = Integer.parseInt(request.getParameter("c1"));
		

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);

		vehicleDAO.removeVehicle(vehicle);

		status.setComplete();

		return MANAGER_DELETE_SUCCESS;
		
	}
    

    // accept pickup
    @GetMapping("/pickup.htm")
    public String fetchPickupdetails(Model model, HttpServletRequest request, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userdao)
            throws Exception {


        String usrEmail = request.getParameter("usrEmail");

        String carid = request.getParameter("carId");

        int carId = Integer.parseInt(carid);

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);

        model.addAttribute("vehicle", vehicle);

        model.addAttribute("usrEmail", usrEmail);
        return MANAGER_PICKUP;

    }

    @PostMapping("/pickup.htm")
    public String postPickupdetails(SessionStatus status, VehicleDataAccessObject vehicleDAO, HttpServletRequest request,
            UserDataAccessObject userdao) throws Exception {
        String usrEmail = request.getParameter("usrEmail");
        User user = userdao.fetchUsrByusrEmail(usrEmail);

        int carId = Integer.parseInt(request.getParameter("c4"));

        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
        vehicle.setImagePath(vehicle.getImagePath());
        vehicle.setRentStartDate(vehicle.getRentStartDate());
        vehicle.setRentEndDate(vehicle.getRentEndDate());
        vehicle.setRentReturnDate(vehicle.getRentReturnDate());
        vehicle.setPickupReady(false);
        vehicle.setReservedByUser(null);
        vehicle.setxUser(user);
        vehicle.setCarId(vehicle.getCarId());
        vehicle.setLicensePlate(vehicle.getLicensePlate());
        vehicle.setModel(vehicle.getModel());
        vehicle.setYear(vehicle.getYear());
        vehicleDAO.modifyVehicle(vehicle);
        status.setComplete();
        return MANAGER_PICKUP_SUCCESS;
    }

    //retrun vehicle
    @GetMapping("/returnvehicle.htm")
    public String fetchvehRtn(Model model, VehicleDataAccessObject vehicleDAO, HttpServletRequest request) throws Exception {

        String usrEmail = request.getParameter("usrEmail");
        model.addAttribute("usrEmail", usrEmail);

        LinkedHashMap<Vehicle, String> vehiclespickedup = vehicleDAO.fetchVechilesInUse();

        if (vehiclespickedup != null) {
        }

        model.addAttribute("vehicles", vehiclespickedup);
        return MANAGER_RETURN_VEHICLE;

    }

    // accept return
    @GetMapping("/return.htm")
    public String fetchAcceptRtn(Model model, HttpServletRequest request, VehicleDataAccessObject vehicleDAO, UserDataAccessObject userdao)
            throws Exception {
        String usrEmail = request.getParameter("usrEmail");
        String id2 = request.getParameter("carId");
        int cId1 = Integer.parseInt(id2);
        Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId1);
        request.setAttribute("vehicle", vehicle);
        request.setAttribute("usrEmail", usrEmail);

        return MANAGER_RETURN;

    }

    @PostMapping("/return.htm")
    public String postAcceptRtn(SessionStatus status,
            VehicleDataAccessObject vehicleDAO, HttpServletRequest request, UserDataAccessObject userdao) throws Exception {

        int cId1 = Integer.parseInt(request.getParameter("c5"));

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
    public String fetchVehicles(Model model, VehicleDataAccessObject vehicleDAO, HttpServletRequest request) throws Exception {

        System.out.println("in manager controller fetchall");
        List<Vehicle> vehicle = vehicleDAO.fetchAllVehicles();
        model.addAttribute("vehicle", vehicle);
        return MANAGER_VEHICLES;

    }

    // customer reservations
    @GetMapping("/vehiclereserve.htm")
    public String fetchVehicleResrvtns(Model model, VehicleDataAccessObject vehicleDAO, HttpServletRequest request, SessionStatus status)
            throws Exception {

        LinkedHashMap<Vehicle, String> vehcilesRsvd = vehicleDAO.fetchAllReservedVechiles();

        if (vehcilesRsvd != null) {
        }
        model.addAttribute("vehicles", vehcilesRsvd);
        return MANAGER_RESERVE_VEHICLE;

    }
}
