package com.vehicle.dao;

import org.hibernate.Session;
import java.util.logging.Level;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

@Component
public class DataAccessObject {

	private static final Logger logger = Logger.getAnonymousLogger();
	private static final ThreadLocal localThread = new ThreadLocal();
	private static final SessionFactory sf = new Configuration().configure().buildSessionFactory();

	protected DataAccessObject() {
	}

	public static Session fetchSession() {
		Session s = (Session) DataAccessObject.localThread.get();
		if (s == null) {
			try {
				s = sf.openSession();
				DataAccessObject.localThread.set(s);
			} catch (HibernateException hibexe) {
				logger.log(Level.INFO, "Exception in fetchSession method", hibexe);
			}

		}
		return s;
	}

	public void startConn() {
		try {
			fetchSession().beginTransaction();
		} catch (HibernateException hibexe) {
			logger.log(Level.INFO, "Exception in startConn method", hibexe);
		}

	}

	public void saveTransaction() {
		try {
			fetchSession().getTransaction().commit();
		} catch (HibernateException hibexe) {
			logger.log(Level.INFO, "Exception in saveTransaction method", hibexe);
		}

	}

	public void revertTransaction() {
		try {
			fetchSession().getTransaction().rollback();
		} catch (HibernateException hibexe) {
			logger.log(Level.INFO, "excpetion in revertTransaction method", hibexe);
		}
		try {
			fetchSession().close();
		} catch (HibernateException hibexe) {
			logger.log(Level.INFO, "Exception in revertTransaction close method", hibexe);
		}
		DataAccessObject.localThread.set(null);
	}

	public static void closeConnection() {
		fetchSession().close();
		DataAccessObject.localThread.set(null);
	}

}
