package com.vehicle.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CustomerController {

//home page

	@GetMapping("/cusHome.htm")
	public String customerHomePage(Model model, HttpServletRequest request) {
		return "customer/cusHome";
	}

////fetch vehicles

//	// ################################### BROWSE Vehciles ##########################################################	

	@GetMapping("/fetchVehicles.htm")
	public String getBrowseBooks(Model model, VehicleDAO vehicleDAO, UserDAO userdao, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();

		String usrEmail = request.getParameter("usrEmail");
		model.addAttribute("usrEmail", usrEmail);

		List<Vehicle> vehicles = vehicleDAO.fetchAllVehicles();
		model.addAttribute("vehicles", vehicles);

		// cars in use for the user
		User user = userdao.fetchUserByusrEmail(usrEmail);
		List<Vehicle> usrVehicles = vehicleDAO.fetchReservedVehicleByUser(user);
		usrVehicles.addAll(vehicleDAO.fetchVechiclesInUserByUser(user));
		System.out.println("usrVehicles:" + usrVehicles.size());
		request.setAttribute("usrVehicles", usrVehicles);

		return "customer/fetchVehicles";
	}

	// ################################### MY vehicles(orders)
	// ##########################################################

	@GetMapping("/orders.htm")
	public String getMyBooks(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
			throws Exception {

		HttpSession session = request.getSession();
		String usrEmail = request.getParameter("usrEmail");

		User user = userdao.fetchUserByusrEmail(usrEmail);
		List<Vehicle> vehicles = vehicleDAO.fetchVechiclesInUserByUser(user);

		if (vehicles != null) {
			System.out.println("My vehicles exists");

		}

		model.addAttribute("vehicles", vehicles);

		return "customer/orders";
	}

	// ################################### booked
	// vehicles##########################################################

	@GetMapping("/bookedVehicles.htm")
	public String getMyReservations(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
			throws Exception {
		HttpSession session = request.getSession();
		String usrEmail = request.getParameter("usrEmail");

		User user = userdao.fetchUserByusrEmail(usrEmail);
		List<Vehicle> vehicles = vehicleDAO.fetchReservedVehicleByUser(user);

		if (vehicles != null) {
			System.out.println("My  booked vehicles exists");

		}

		model.addAttribute("vehicles", vehicles);

		return "customer/bookedVehicles";
	}
	
	//  CONFIRM RESERVATION
	
		@GetMapping("/reservationconfirm.htm")
		public String fetchConfirmReservation(Model model, HttpServletRequest request,VehicleDAO vehicleDAO, UserDAO userdao,SessionStatus status)
				throws Exception {

			HttpSession session = request.getSession();
			String usrEmail = request.getParameter("usrEmail");
			
			String carid = request.getParameter("carId");

			int cId = Integer.parseInt(carid);
			
			Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(cId);
			
			User user = userdao.fetchUserByusrEmail(usrEmail);
			model.addAttribute("vehicle", vehicle);

			model.addAttribute("usrEmail", usrEmail);
			
			
			System.out.println("usrEmail:" + usrEmail);

			LocalDate rentStartDate = LocalDate.now();
			LocalDate rentEndDate = LocalDate.now().plusDays(1);
			LocalDate rentReturnDate = LocalDate.now().plusDays(2);

			model.addAttribute("rentStartDate", rentStartDate);
			model.addAttribute("rentEndDate", rentEndDate);
			model.addAttribute("rentReturnDate", rentReturnDate);

			return "customer/reservationConfirm";

		}

		@PostMapping("/reservationconfirm.htm")
		public String setConfirmReservation(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, SessionStatus status,
				VehicleDAO vehicleDAO, HttpServletRequest request, UserDAO userdao) throws Exception {

			System.out.println("############### CUSTOMER: Confirm Reservation Post Mapping ###############");
			HttpSession session = request.getSession();
			String usrEmail = request.getParameter("usrEmail");
			System.out.println("Post usrEmail:" + usrEmail);

			String carid = request.getParameter("carid");

			int cId = Integer.parseInt(carid);


			
			
			
			String rentStartDate = request.getParameter("rentStartDate");
			System.out.println(rentStartDate);

			String rentEndDate = request.getParameter("rentEndDate");
			System.out.println(rentEndDate);

			String rentReturnDate = request.getParameter("rentReturnDate");
			System.out.println(rentReturnDate);

			String usrId = request.getParameter("usrEmail");
			System.out.println(usrEmail);

			User user = userdao.fetchUserByusrEmail(usrEmail);		

			LocalDate rsd = LocalDate.parse(rentStartDate);
			LocalDate red = LocalDate.parse(rentEndDate);
			LocalDate rrd = LocalDate.parse(rentReturnDate);

			vehicle.setCarId(cId);
			vehicle.setLicensePlate(vehicle.getLicensePlate());
			vehicle.setModel(vehicle.getModel());
			vehicle.setYear(vehicle.getYear());
			vehicle.setRentStartDate(rsd);
			vehicle.setRentEndDate(red);
			vehicle.setRentReturnDate(rrd);
			vehicle.setPickupReady(true);
			vehicle.setReservedByUser(user);
			
			String imagePath=request.getParameter("imagePath");
			System.out.println("IMGWGE PTAH"+vehicle.getImagePath());
			
	
			
			
//	 	    Image path
			vehicle.setImagePath(imagePath);
			
			

			vehicleDAO.updateVehicle(vehicle);


			status.setComplete(); // mark it complete
			return "customer/successreserv";
		}
		
	

}


