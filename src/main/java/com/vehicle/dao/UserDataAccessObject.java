package com.vehicle.dao;

import org.hibernate.query.Query;
import static com.vehicle.dao.ProjectConstants.*;
import java.util.ArrayList;
import com.vehicle.pojo.User;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;
import org.hibernate.HibernateException;
import com.vehicle.exception.VehicleException;

import java.util.List;

@Component
public class UserDataAccessObject extends DataAccessObject {

	public UserDataAccessObject() {
	}

	public void registerUser(User user) throws VehicleException {
		try {
			startConn();
			fetchSession().save(user);
			saveTransaction();
		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception in register user method ", hie);
		} finally {
			closeConnection();
		}
	}

	public void modifyUser(User user) throws VehicleException {
		try {
			startConn();
			fetchSession().update(user);
			saveTransaction();
		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception in modify user method ", hie);
		} finally {
			closeConnection();
		}
	}

	public void removeUser(User user) throws VehicleException {
		try {
			startConn();
			fetchSession().delete(user);
			saveTransaction();
		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception in remove user method ", hie);
		} finally {
			closeConnection();
		}
	}

	public User fetchUsrById(int usrId) throws VehicleException {
		try {
			startConn();
			Query fetchUserByUserIdQuery;
			fetchUserByUserIdQuery = fetchSession().createQuery("from User where usrId = :usrId");
			fetchUserByUserIdQuery.setParameter(USER_ID, usrId);
			User user = (User) fetchUserByUserIdQuery.uniqueResult();
			saveTransaction();
			return user;
		} catch (HibernateException hie) {
			revertTransaction();
			throw new VehicleException("Encountered exception - to fetch user with user id: ", hie);
		} finally {
			closeConnection();
		}

	}

	public User fetchUsrByusrEmail(String usrEmail) throws VehicleException {
		try {
			Query fetchUserByUserEmailId;
			fetchUserByUserEmailId = fetchSession().createQuery("from User where usrEmail = :usrEmail");

			fetchUserByUserEmailId.setParameter(USR_EMAIL, usrEmail);
			User user = (User) fetchUserByUserEmailId.uniqueResult();

			return user;
		} catch (Exception e) {
			revertTransaction();
			throw new VehicleException("Encountered exception - to fetch user with user user email: ", e);
		} finally {
			closeConnection();
		}
	}

	public List<User> fetchEveryUsr() throws VehicleException {
		List<User> users = new ArrayList<User>();
		try {
			startConn();
			Criteria criteria = fetchSession().createCriteria(User.class);
			users = criteria.list();
			saveTransaction();
			return users;
		} catch (HibernateException hie) {
			throw new VehicleException("Encountered exception, unable to fetch all users: ", hie);
		} finally {
			closeConnection();
		}

	}
}
