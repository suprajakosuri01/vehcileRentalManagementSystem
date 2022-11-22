package com.vehicle.controller;

import java.util.List;

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
		public String addBook(ModelMap model,Vehicle vehicle,HttpServletRequest request) {

			
		HttpSession session=request.getSession();
		model.addAttribute("vehicle", vehicle);

			// return form view
		
			return "Manager/VehiclesAdd";
		}
		
		@PostMapping("/vehiclesadd.htm")
		public String saveBook(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, SessionStatus status, VehicleDAO vehicleDAO,Model model) throws Exception {
			
				
				System.out.println(vehicle.getLicensePlate());
				System.out.println(vehicle.getModel());
				System.out.println(vehicle.getYear());
				
				vehicle.setPickupReady(false);
				
				
				if(bookdao.isbnExists(book.getIsbn()))
				{
					String isbnExistsErr="ISBN already exists";
					System.out.println(isbnExistsErr);
					model.addAttribute("isbnExistsErr",isbnExistsErr);
					return "employee/add-books";
				}
				
				String fileName = "img_" + System.currentTimeMillis() + "" + new Random().nextInt(100000000) + "" + new Random().nextInt(100000000) + ".jpg";
				book.setImagePath(fileName);
				try {
					book.getImageFile().transferTo(new File("src/main/webapp/images/" + fileName));
				} catch (IllegalStateException e1) {
					System.out.println("IllegalStateException: " + e1.getMessage());
				} catch (IOException e1) {
					System.out.println("IOException: " + e1.getMessage());
				}
				
				
				bookdao.save(book);
				
				if(result.hasErrors())
				{
					return "employee/add-books";
				}
				
				status.setComplete(); //mark it complete
				
			
			return "employee/book-add-success";
		}
		
		
		
		
}


