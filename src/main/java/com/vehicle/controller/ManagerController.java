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
import com.vehicle.pojo.Vehicle;

@Controller
public class ManagerController {

//manager view page
		@GetMapping("/managerhome.htm")
	    public String fetchManagerview(Model model) {
			

	        return "Manager/ManagerHome";
	    }	
	
	//vehciles list
		
		@GetMapping("/vehicles.htm")
	    public String fetchVehicles(Model model,VehicleDAO vehicleDAO,HttpServletRequest request) throws Exception {
			
			HttpSession session=request.getSession();
	
			List<Vehicle> vehicles = vehicleDAO.fetchAllVehicles();
			model.addAttribute("vehicles", vehicles);
	        return "Manager/Vehicles";
	    }	
	
		//vehicles add
		
		
		
		@GetMapping("/vehiclesadd.htm")
		public String vehicleAdd(ModelMap model,Vehicle vehicle,HttpServletRequest request) {

			
		HttpSession session=request.getSession();
		model.addAttribute("vehicle", vehicle);

			// return form view
		
			return "Manager/VehiclesAdd";
		}
		
		@PostMapping("/vehiclesadd.htm")
		public String vehicleSave(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, SessionStatus status, VehicleDAO vehicleDAO,Model model) throws Exception {
			
				
				System.out.println(vehicle.getLicensePlate());
				System.out.println(vehicle.getModel());
				System.out.println(vehicle.getYear());
				
				vehicle.setPickupReady(false);
				
				
				if(vehicleDAO.licencsePlateExists(vehicle.getLicensePlate()))
				{
					String licencsePlateError="licencsePlate number exists";
					System.out.println(licencsePlateError);
					model.addAttribute("licencsePlateError",licencsePlateError);
					return "Manager/VehiclesAdd";
				}
				
				
				
				String imgp = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + "" + new Random().nextInt(100000000) + ".jpg";
				
				vehicle.setImagePath(imgp);
				try {
					
					vehicle.getImgFile().transferTo(new File("src/main/webapp/images/" + imgp));
				} catch (IllegalStateException e1) {
					
					System.out.println("IllegalStateException: " + e1.getMessage());
					
				} catch (IOException e1) {
					System.out.println("IOException: " + e1.getMessage());
				}
				
				
				vehicleDAO.saveVehicle(vehicle);
				
				if(result.hasErrors())
				{
					return "Manager/VehiclesAdd";
				}
				
				status.setComplete(); //mark it complete
				
			
			return "Manager/vehiclesAdded";
		}
	//delete all
		
		@GetMapping("/deleteall.htm")
		public String fetchDeleteall(Model model, HttpServletRequest request, VehicleDAO vehicleDAO, UserDAO userdao)
				throws Exception {
		
			HttpSession session = request.getSession();
			String carid = request.getParameter("carId");

			
			System.out.println("IN delete method");
			
			
			int carId = Integer.parseInt(carid);

			
			Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
			
			model.addAttribute(vehicle);
			
			return "Manager/DeleteAll";
		}

		@PostMapping("/deleteall.htm.htm")
		public String postDeleteall(SessionStatus status,
				VehicleDAO vehicleDAO	, HttpServletRequest request, UserDAO userdao) throws Exception {
			
			System.out.println("############### EMPLOYEE: Confirm EDIT Post Mapping ###############");
			HttpSession session = request.getSession();

            String cid = request.getParameter("carId");

			
            int carId = Integer.parseInt(cid);
		
			
		
			
			
            Vehicle vehicle = vehicleDAO.fetchVehiclesbyId(carId);
			
			
            vehicleDAO.deleteVehicle(vehicle);
			
			status.setComplete();
			
			return "Manager/Deleted";
		}
		
// user reservations
		@GetMapping("/vehiclereserve.htm")
	    public String getReservations(Model model,VehicleDAO vehicleDAO,HttpServletRequest request,SessionStatus status) throws Exception {

			
			
			LinkedHashMap<Vehicle,String> vehcilesRsvd = vehicleDAO.fetchReservedAllVechiles();
			
			if(vehcilesRsvd !=null) {
				System.out.println("Got resreved books");
			}

			model.addAttribute("vehicles", vehcilesRsvd);

	        return "Manager/VehicleReserve";
	        
	    }
		
		
			
		
		
}


