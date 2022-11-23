package com.vehicle.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.vehicle.dao.UserDAO;
import com.vehicle.dao.VehicleDAO;
import com.vehicle.pojo.User;
import com.vehicle.pojo.Vehicle;

@Controller
public class ManagerController {

//manager view page
	@GetMapping("/managerhome.htm")
	public String fetchManagerview(Model model) {

		return "Manager/ManagerHome";
	}

	// vehciles list


	@GetMapping("/vehicles.htm")
	public String fetchVehicles(Model model, VehicleDAO vehicleDAO, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		List<Vehicle> vehicle = vehicleDAO.fetchAllVehicles();
		
		System.out.println("getModel in fetch vehicle" +vehicle.get(0).getModel());
		model.addAttribute("vehicle", vehicle);

		
		return "Manager/Vehicles";
		
	}

	// vehicles add

	@GetMapping("/vehiclesadd.htm")
	public String vehicleAdd(ModelMap model, Vehicle vehicle, HttpServletRequest request) {

		HttpSession session = request.getSession();
		model.addAttribute("vehicle", vehicle);

		

		return "Manager/VehiclesAdd";
	}

	@PostMapping("/vehiclesadd.htm")
	public String vehicleSave(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, SessionStatus status,
			VehicleDAO vehicleDAO, Model model) throws Exception {

		System.out.println(vehicle.getLicensePlate());
		System.out.println(vehicle.getModel());
		System.out.println(vehicle.getYear());

		vehicle.setPickupReady(false);

		if (vehicleDAO.licencsePlateExists(vehicle.getLicensePlate())) {
			String licencsePlateError = "licencsePlate number exists";
			System.out.println(licencsePlateError);
			model.addAttribute("licencsePlateError", licencsePlateError);
			return "Manager/VehiclesAdd";
		}

		String imgp = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + ""
				+ new Random().nextInt(100000000) + ".jpg";

		vehicle.setImagePath(imgp);
		try {

			vehicle.getImgFile().transferTo(new File("src/main/webapp/images/" + imgp));
		} catch (IllegalStateException e1) {

			System.out.println("IllegalStateException: " + e1.getMessage());

		} catch (IOException e1) {
			System.out.println("IOException: " + e1.getMessage());
		}

		vehicleDAO.saveVehicle(vehicle);

		if (result.hasErrors()) {
			return "Manager/VehiclesAdd";
		}

		status.setComplete(); // mark it complete

		return "Manager/vehicleAdded";
	}
	
	
	//CONFIRM EDIT 
		
		@GetMapping("/editvehicle.htm")
		public String getConfirmEdit(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
				throws Exception {
		
			HttpSession session = request.getSession();
			
//			String carid = request.getParameter("carId");
//			int carId = Integer.parseInt(carid);
//			carid
			
			String cid = request.getParameter("carId");

			int cId = Integer.parseInt(cid);

			
			System.out.println("IN confirm edit method");
			
			Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);
			

			model.addAttribute(vehicle);
			
			return "Manager/EditVehicle";
		}
		
		
		@PostMapping("/editvehicle.htm")
		public String postConfirmEdit(SessionStatus status,
				VehicleDAO vehicleDAO, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
			HttpSession session = request.getSession();

//			String carid = request.getParameter("carId");
//			int carId = Integer.parseInt(carid);
			
			String c1 = request.getParameter("carId");

			int cId = Integer.parseInt(c1);
			
			
			
			Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);
			
			String model=request.getParameter("model");
			
			String year=request.getParameter("year");
			String deleteRsvrtn=request.getParameter("deleteRsvrtn");
			String deleteUsr=request.getParameter("deleteUsr");
			
			
			vehicle.setCarId(vehicle.getCarId());
			vehicle.setLicensePlate(vehicle.getLicensePlate());
			vehicle.setModel(vehicle.getModel());
			
			vehicle.setYear(vehicle.getYear());
			vehicle.setRentStartDate(vehicle.getRentStartDate());
			vehicle.setRentEndDate(vehicle.getRentEndDate());
			vehicle.setRentReturnDate(vehicle.getRentReturnDate());
			vehicle.setPickupReady(vehicle.getPickupReady());
			
		
			
			if(deleteRsvrtn !=null) {
				vehicle.setReservedByUser(null);
				vehicle.setRentStartDate(null);
				vehicle.setRentEndDate(null);
				vehicle.setRentReturnDate(null);
			}
			else {
				vehicle.setReservedByUser(vehicle.getReservedByUser());
				vehicle.setRentStartDate(vehicle.getRentStartDate());
				vehicle.setRentEndDate(vehicle.getRentEndDate());
				vehicle.setRentReturnDate(vehicle.getRentReturnDate());

				
			}
			
			if(deleteUsr !=null) {
				vehicle.setxUser(null);
				vehicle.setRentStartDate(null);
				vehicle.setRentEndDate(null);
				vehicle.setRentReturnDate(null);
			}
			else {
				
				vehicle.setxUser(vehicle.getxUser());
				vehicle.setRentStartDate(vehicle.getRentStartDate());
				vehicle.setRentEndDate(vehicle.getRentEndDate());
				vehicle.setRentReturnDate(vehicle.getRentReturnDate());
			}
			
//			SET IMAGE PATH
			
			vehicle.setImagePath(vehicle.getImagePath());
			
			vehicleDAO.updateVehicle(vehicle);
			
			status.setComplete();
			
			return "Manager/edited";
		}

	// delete all


	@GetMapping("/deleteall.htm")
	public String fetchDeleteall(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
			throws Exception {

		HttpSession session = request.getSession();
//		String carid3 = request.getParameter("carId");

		System.out.println("IN delete method");

//		int carId = Integer.parseInt(carid3);
		
		String carid = request.getParameter("carId");

		int cId = Integer.parseInt(carid);

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);

		model.addAttribute(vehicle);

		return "Manager/DeleteAll";
	}

	@PostMapping("/deleteall.htm.")
	public String postDeleteall(SessionStatus status, VehicleDAO vehicleDAO, HttpServletRequest request,
			UserDAO userdao) throws Exception {

		System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
		HttpSession session = request.getSession();

//		String cid = request.getParameter("carId");
//
//		int carId = Integer.parseInt(cid);
		
		String carid = request.getParameter("carId");

		int cId = Integer.parseInt(carid);

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);

		vehicleDAO.deleteVehicle(vehicle);

		status.setComplete();

		return "Manager/Deleted";
	}

// user reservations
	@GetMapping("/vehiclereserve.htm")
	public String getReservations(Model model, VehicleDAO vehicleDAO, HttpServletRequest request, SessionStatus status)
			throws Exception {

		LinkedHashMap<Vehicle, String> vehcilesRsvd = vehicleDAO.fetchReservedAllVechiles();

		if (vehcilesRsvd != null) {
			System.out.println("Got resreved vehicles");
		}

		model.addAttribute("vehicles", vehcilesRsvd);

		return "Manager/VehicleReserve";

	}

	// confirm pickup

	@GetMapping("/pickup.htm")
	public String getPickup(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
			throws Exception {

		HttpSession session = request.getSession();
		String usrEmail = request.getParameter("usrEmail");
//		String cid2 = request.getParameter("carId");
//
//		int carId = Integer.parseInt(cid2);
		String carid = request.getParameter("carId");

		int cId = Integer.parseInt(carid);
		

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);

		User user = userdao.fetchUserByusrEmail(usrEmail);

		model.addAttribute("vehicle", vehicle);

		model.addAttribute("usrEmail", usrEmail);

		System.out.println("usrId:" + usrEmail);

		return "Manager/Pickup";

	}

	@PostMapping("/pickup.htm")
	public String setConfirmReservation(SessionStatus status, VehicleDAO vehicleDAO, HttpServletRequest request,
			UserDAO userdao) throws Exception {

		System.out.println("############### EMPLOYEE: Confirm Pickup Post Mapping ###############");
		HttpSession session = request.getSession();
		String usrEmail = request.getParameter("usrEmail");
		System.out.println("Post usrEmail:" + usrEmail);
		User user = userdao.fetchUserByusrEmail(usrEmail);

//		String cid = request.getParameter("carId");
//
//		int carId = Integer.parseInt(cid);
//
//		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
		
		String carid = request.getParameter("carId");

		int cId = Integer.parseInt(carid);
		

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);

		System.out.println(vehicle.getCarId());

		vehicle.setCarId(vehicle.getCarId());
		vehicle.setLicensePlate(vehicle.getLicensePlate());
		vehicle.setModel(vehicle.getModel());
		vehicle.setYear(vehicle.getYear());
		vehicle.setRentStartDate(vehicle.getRentStartDate());
		vehicle.setRentEndDate(vehicle.getRentEndDate());
		vehicle.setRentReturnDate(vehicle.getRentReturnDate());
		vehicle.setPickupReady(false);
		vehicle.setReservedByUser(null);
		vehicle.setxUser(user);

//			SET IMAGE PATH

		vehicle.setImagePath(vehicle.getImagePath());

		vehicleDAO.updateVehicle(vehicle);

		status.setComplete();
		return "Manager/PickedUp";
	}
	
	
	//RETURNS
	
		@GetMapping("/returnvehicle.htm")
	    public String getBookReturns(Model model,VehicleDAO vehicleDAO,HttpServletRequest request) throws Exception {
			
			HttpSession session=request.getSession();
			String usrEmail = request.getParameter("usrEmail");
			model.addAttribute("usrEmail", usrEmail);
			
			LinkedHashMap<Vehicle,String> vehiclespickedup = vehicleDAO.fetchAllVechilesInUse();

			
			if(vehiclespickedup !=null) {
				System.out.println("Got all vehicles in use");
			}


			model.addAttribute("vehicles", vehiclespickedup);
	        return "Manager/ReturnVehicle";
	        
	      
	    }
		
		// cONFIRM RETURN 
		
			@GetMapping("/return.htm")
			public String getConfirmReturn(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
					throws Exception {
				
				HttpSession session = request.getSession();
				String usrEmail = request.getParameter("usrEmail");
				
//				String id = request.getParameter("carId");
//
//				int carId = Integer.parseInt(id);
//				
//				
//				Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
				
				String carid = request.getParameter("carId");

				int cId = Integer.parseInt(carid);
				

				Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);
				
				
				User user = userdao.fetchUserByusrEmail(usrEmail);

				request.setAttribute("vehicle", vehicle);
				request.setAttribute("usrEmail", usrEmail);

				System.out.println("usrId:" + usrEmail);


				return "Manager/Return";
				
			}
			
			@PostMapping("/confirm-return.htm")
			public String postConfirmReturn(SessionStatus status,
					VehicleDAO vehicleDAO, HttpServletRequest request, UserDAO userdao) throws Exception {
				
				System.out.println("############### EMPLOYEE: Confirm RETURN Post Mapping ###############");
				
				HttpSession session = request.getSession();
				String usrEmail = request.getParameter("usrEmail");
				System.out.println("Post username:" + usrEmail);
				User user = userdao.fetchUserByusrEmail(usrEmail);

//				String cid1 = request.getParameter("carId");
//
//				int carId = Integer.parseInt(cid1);
//				
//				
//
//				
//				Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
				
//				
				String carid = request.getParameter("carId");

				int cId = Integer.parseInt(carid);
				

				Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);
				
				
				vehicle.setCarId(vehicle.getCarId());
				vehicle.setLicensePlate(vehicle.getLicensePlate());
				vehicle.setModel(vehicle.getModel());
				vehicle.setYear(vehicle.getYear());
				vehicle.setRentStartDate(null);
				vehicle.setRentEndDate(null);
				vehicle.setRentReturnDate(null);
				vehicle.setPickupReady(false);
				vehicle.setReservedByUser(null);
				vehicle.setxUser(null);

				
			
				
//				SET IMAGE PATH
				vehicle.setImagePath(vehicle.getImagePath());
				
				vehicleDAO.updateVehicle(vehicle);
				
				status.setComplete();
				return "Manager/ReturnComplete";
			}
		
		
		
		

}
