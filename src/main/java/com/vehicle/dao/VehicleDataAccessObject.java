package com.vehicle.dao;

import com.vehicle.pojo.User;
import static com.vehicle.dao.ProjectConstants.*;
import java.util.ArrayList;
import com.vehicle.exception.VehicleException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import com.vehicle.pojo.Vehicle;
import org.hibernate.query.Query;
import java.util.LinkedHashMap;

public class VehicleDataAccessObject extends DataAccessObject {

	public VehicleDataAccessObject() {
	}

	/*
	 * This method is useful to fetch all vehicles from database.
	 */
	public List<Vehicle> fetchAllVehicles() throws VehicleException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			startConn();
			Criteria criteria = fetchSession().createCriteria(Vehicle.class);
			vehicles = criteria.list();

			saveTransaction();
		} catch (HibernateException hie) {
			throw new VehicleException("Encountered exception in fetchAllVehicles method ", hie);
		} finally {

			closeConnection();
		}
		return vehicles;

	}

	public LinkedHashMap<Vehicle, String> fetchAllReservedVechiles() throws VehicleException {
		LinkedHashMap<Vehicle, String> vehicles = new LinkedHashMap<Vehicle, String>();
		for (Vehicle vehicle : this.fetchAllVehicles()) {
			if (vehicle.getReservedByUser() != null && vehicle.getxUser() == null) {
				String UsrEmail = vehicle.getReservedByUser().getUsrEmail();
				vehicles.put(vehicle, UsrEmail);
			}
		}
		return vehicles;
	}

	public LinkedHashMap<Vehicle, String> fetchVechilesInUse() throws VehicleException {
		LinkedHashMap<Vehicle, String> vehicles = new LinkedHashMap<Vehicle, String>();
		for (Vehicle vehicle : this.fetchAllVehicles()) {
			if (vehicle.getxUser() != null && vehicle.getReservedByUser() == null) {
				String UsrEmail = vehicle.getxUser().getUsrEmail();
				vehicles.put(vehicle, UsrEmail);
			}
		}
		return vehicles;
	}

	public List<Vehicle> fetchReservedVehicleOfUser(User user) throws VehicleException {

		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			startConn();
			Query queryToFetchReservedVehiclesByUser;
			queryToFetchReservedVehiclesByUser = fetchSession().createQuery("from Vehicle where reservedByUser =:user");
			queryToFetchReservedVehiclesByUser.setParameter("user", user);
			vehicles = (List<Vehicle>) queryToFetchReservedVehiclesByUser.list();
			saveTransaction();
		} catch (HibernateException hie) {
			throw new VehicleException("Encountered exception in fetchReservedVehicleOfUser method ", hie);
		} finally {
			closeConnection();
		}
		return vehicles;
	}

	public List<Vehicle> fetchVechUsingbyUsr(User user) throws VehicleException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			startConn();
			Query queryToFetchVehicleUsingUser;
			queryToFetchVehicleUsingUser = fetchSession().createQuery("from Vehicle where xUser =:user");
			queryToFetchVehicleUsingUser.setParameter(USER, user);
			vehicles = (List<Vehicle>) queryToFetchVehicleUsingUser.list();
			saveTransaction();
		} catch (HibernateException hie) {
			throw new VehicleException("Encountered exception in fetchVechUsingbyUsr method ", hie);
		} finally {
			closeConnection();
		}
		return vehicles;
	}

	public Vehicle fetchVehiclesbyId(int carId) throws VehicleException {
		try {
			startConn();
			Query queryToFetchVehicleById;
			queryToFetchVehicleById = fetchSession().createQuery("from Vehicle where carId = :carId");
			queryToFetchVehicleById.setParameter(CAR_ID, carId);
			Vehicle vehicle = (Vehicle) queryToFetchVehicleById.uniqueResult();
			saveTransaction();
			return vehicle;

		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception in fetchVehiclesbyId method ", hie);
		} finally {
			closeConnection();
		}

	}

	public boolean isValidLicense(String licensePlate) throws VehicleException {
		try {

			Query queryToCheckIfLicensePlateIsPresent;
			queryToCheckIfLicensePlateIsPresent = fetchSession()
					.createQuery("from Vehicle where licensePlate = :licensePlate");
			queryToCheckIfLicensePlateIsPresent.setParameter(LICENSE_PLATE, licensePlate);
			Object res = queryToCheckIfLicensePlateIsPresent.uniqueResult();
			if (res == null) {
				return false;
			}

		} catch (Exception e) {
			revertTransaction();
			throw new VehicleException("Encountered exception in isValidLicense method ", e);
		} finally {
			closeConnection();
		}
		return true;

	}

	public void registerVehicle(Vehicle vehicle) throws VehicleException {

		try {
			startConn();
			fetchSession().save(vehicle);
			saveTransaction();
		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception in registerVehicle method ", hie);
		} finally {
			closeConnection();
		}

	}

	public void modifyVehicle(Vehicle vehicle) throws VehicleException {
		try {
			startConn();
			fetchSession().update(vehicle);
			saveTransaction();
		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception in modifyVehicle method ", hie);
		} finally {
			closeConnection();
		}
	}

	public void removeVehicle(Vehicle vehicle) throws VehicleException {
		try {
			startConn();
			fetchSession().delete(vehicle);
			saveTransaction();
		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception in removeVehicle method ", hie);
		} finally {
			closeConnection();
		}
	}

}
