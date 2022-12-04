package com.vehicle.dao;

import org.springframework.stereotype.Component;
import static com.vehicle.dao.ProjectConstants.*;
import org.hibernate.HibernateException;
import com.vehicle.exception.VehicleException;
import org.hibernate.query.Query;

@Component
public class LoginDataAccessObject extends DataAccessObject {

	public LoginDataAccessObject() {
	}

	public boolean checkCustmer(String userEmail, String usrPassword) throws VehicleException {
		try {
			Query createQueryToCheckIfCustomerAlreadyExists;
			createQueryToCheckIfCustomerAlreadyExists = fetchSession().createQuery(
					"from User where usrEmail=:usrEmail and usrPassword=:usrPassword and title='customer'");
			createQueryToCheckIfCustomerAlreadyExists.setParameter(USR_EMAIL, userEmail);
			createQueryToCheckIfCustomerAlreadyExists.setParameter(USR_PASSWRD, usrPassword);
			Object res = createQueryToCheckIfCustomerAlreadyExists.uniqueResult();
			if (null == res) {
				return false;
			}
		} catch (HibernateException hie) {
			throw new VehicleException("Encountered exception in customercheck method ", hie);
		} finally {
			closeConnection();
		}
		return true;

	}

	public boolean checkAdmin(String userEmail, String usrPassword) throws VehicleException {
		try {
			Query createQueryToCheckIfAdminAlreadyExists;
			createQueryToCheckIfAdminAlreadyExists = fetchSession()
					.createQuery("from User where  usrEmail=:usrEmail and usrPassword=:usrPassword and title='admin'");
			createQueryToCheckIfAdminAlreadyExists.setParameter(USR_EMAIL, userEmail);
			createQueryToCheckIfAdminAlreadyExists.setParameter(USR_PASSWRD, usrPassword);
			Object res = createQueryToCheckIfAdminAlreadyExists.uniqueResult();
			if (null == res) {
				return false;
			}

		} catch (HibernateException hie) {
			throw new VehicleException("Encountered exception in Admincheck method ", hie);
		} finally {
			closeConnection();
		}
		return true;
	}

	public boolean checkEmployee(String userEmail, String usrPassword) throws VehicleException {
		try {
			Query createQueryToCheckIfEmployeeAlreadyExists;
			createQueryToCheckIfEmployeeAlreadyExists = fetchSession().createQuery(
					"from User where usrEmail=:usrEmail and usrPassword=:usrPassword and title='employee'");
			createQueryToCheckIfEmployeeAlreadyExists.setParameter(USR_EMAIL, userEmail);
			createQueryToCheckIfEmployeeAlreadyExists.setParameter(USR_PASSWRD, usrPassword);
			Object res = createQueryToCheckIfEmployeeAlreadyExists.uniqueResult();
			if (null == res) {
				return false;
			}
		} catch (HibernateException hie) {
			throw new VehicleException("Encountered exception in checkEmployee method ", hie);
		} finally {
			closeConnection();
		}
		return true;
	}
}
