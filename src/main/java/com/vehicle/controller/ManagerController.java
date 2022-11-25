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
		System.out.println("in manager conteoller fetchall");
		
		
		List<Vehicle> vehicle = vehicleDAO.fetchAllVehicles();

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
			

			
			String cid = request.getParameter("carId");
			


			int carId = Integer.parseInt(cid);
			

			
			System.out.println("IN confirm edit method1");
			
			Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
			

			System.out.println("succesfully fetched - model in edit vehicle"+vehicle.getModel());
			System.out.println("year in edit vehicle"+vehicle.getYear());

			
			model.addAttribute(vehicle);
			
			return "Manager/EditVehicle";
		}
		
		
		@PostMapping("/editvehicle.htm")
		public String postConfirmEdit(SessionStatus status,
				VehicleDAO vehicleDAO, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
			HttpSession session = request.getSession();

			
//			String c1 = request.getParameter("carId");

			int carId = Integer.parseInt(request.getParameter("c1"));
			
			

			System.out.println("IN confirm edit method 1.5");
			
			Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
			
			String model=request.getParameter("model");
			
			System.out.println("before fetching year");
			String year1 = request.getParameter("year");
			System.out.println("Got year value from front end - before type casting ");
			
			 int castedYear =Integer.parseInt(year1);
			System.out.println("Casted year value - " + castedYear);

			
			String deleteRsvrtn=request.getParameter("deleteRsvrtn");
			
			String deleteUsr=request.getParameter("deleteUsr");
			
			System.out.println("IN confirm edit method 2");
			
			vehicle.setCarId(vehicle.getCarId());
			
			System.out.println("IN confirm edit method 3");
			
			vehicle.setModel(model);
			

			vehicle.setYear(castedYear);
			
			
			
//			vehicle.setRentStartDate(vehicle.getRentStartDate());
//			vehicle.setRentEndDate(vehicle.getRentEndDate());
			System.out.println("IN confirm edit method 5");
//			vehicle.setRentReturnDate(vehicle.getRentReturnDate());
//			vehicle.setPickupReady(vehicle.getPickupReady());
			
		
			
			if(deleteRsvrtn !=null) {
				vehicle.setReservedByUser(null);
				vehicle.setRentStartDate(null);
				vehicle.setRentEndDate(null);
				System.out.println("IN confirm edit method 4");
				vehicle.setRentReturnDate(null);
			}
			else {
				vehicle.setReservedByUser(vehicle.getReservedByUser());
				vehicle.setRentStartDate(vehicle.getRentStartDate());
				vehicle.setRentEndDate(vehicle.getRentEndDate());
				vehicle.setRentReturnDate(vehicle.getRentReturnDate());

				
			}
			
			if(deleteUsr !=null) {
				System.out.println("IN confirm edit metho 5");
				vehicle.setxUser(null);
				vehicle.setRentStartDate(null);
				vehicle.setRentEndDate(null);
				vehicle.setRentReturnDate(null);
			}
			else {
				System.out.println("IN confirm edit method 6");
				vehicle.setxUser(vehicle.getxUser());
				vehicle.setRentStartDate(vehicle.getRentStartDate());
				vehicle.setRentEndDate(vehicle.getRentEndDate());
				vehicle.setRentReturnDate(vehicle.getRentReturnDate());
			}
			
//			SET IMAGE PATH
			
//			vehicle.setImagePath(vehicle.getImagePath());
			
			vehicleDAO.updateVehicle(vehicle);
			
			status.setComplete();
			System.out.println("IN confirm edit method 7 ");
			
			return "Manager/Edited";
		}

	// delete all


	@GetMapping("/deleteall.htm")
	public String fetchDeleteall(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
			throws Exception {

		HttpSession session = request.getSession();


		System.out.println("IN delete method");

		String cid = request.getParameter("carId");
		


		int carId = Integer.parseInt(cid);
		

		


		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
		System.out.println("IN delete method" +carId);

		model.addAttribute(vehicle);
		System.out.println("IN delete method success" +carId);

		return "Manager/DeleteAll";
	}

	@PostMapping("/deleteall.htm")
	public String postDeleteall(SessionStatus status, VehicleDAO vehicleDAO, HttpServletRequest request,
			UserDAO userdao) throws Exception {

		System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
		HttpSession session = request.getSession();

		

		
		
		int carId = Integer.parseInt(request.getParameter("c1"));
		

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);

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

		String carid = request.getParameter("carId");

		int carId = Integer.parseInt(carid);
		

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);

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

		

		
		int carId = Integer.parseInt(request.getParameter("c4"));
		

		Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);

	
		

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
		System.out.println("pickup successfull");
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
					

				String id2 = request.getParameter("carId");

				System.out.println(id2 +"in get confrim return id2");
				int cId1 = Integer.parseInt(id2);
				

				Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId1);
				
				

				
				
				User user = userdao.fetchUserByusrEmail(usrEmail);

				request.setAttribute("vehicle", vehicle);
				request.setAttribute("usrEmail", usrEmail);

				


				return "Manager/Return";
				
			}
			
			@PostMapping("/return.htm")
			public String postConfirmReturn(SessionStatus status,
					VehicleDAO vehicleDAO, HttpServletRequest request, UserDAO userdao) throws Exception {
				
				System.out.println("############### EMPLOYEE: Confirm RETURN Post Mapping ###############");
				
				HttpSession session = request.getSession();
				String usrEmail = request.getParameter("usrEmail");
				System.out.println("Post username:" + usrEmail);
				User user = userdao.fetchUserByusrEmail(usrEmail);


				int cId1 = Integer.parseInt(request.getParameter("c5"));
				

				

				

				Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId1);
				
				
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
