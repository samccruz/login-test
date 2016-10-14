package com.loginrecord.exam.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.loginrecord.exam.entity.Login;

/**
 * Contains all of the abstract methods that should be implemented to satisfy
 * the needed business logic.
 * 
 * @author Sam Christoffer Cruz
 *
 */
public interface LoginService {

	/**
	 * Simply calls the repository to insert the dummy data to the database.
	 * 
	 * @return returns 1 if the operation was successful.
	 */
	public long insert();

	/**
	 * Calls the repository to retrieve all of the unique dates in the database.
	 * 
	 * @return the list of unique dates.
	 */
	public List<Login> getUniqueDates();

	/**
	 * Calls the repository to retrieve all of the unique users on the given
	 * dates.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return the list of logins with unique users.
	 */
	public List<Login> getUsers(Date startDate, Date endDate);

	/**
	 * Calls the repository to retrieve all of the users with their login count
	 * on the given attributes and dates.
	 * 
	 * @param start
	 *            the minimum date
	 * @param end
	 *            the maximum date
	 * @param attribute1
	 *            the values of the field attribute1
	 * @param attribute2
	 *            the values of the field attribute2
	 * @param attribute3
	 *            the values of the field attribute3
	 * @param attribute4
	 *            the values of the field attribute4
	 * @return the list of unique users with their logins.
	 */
	public Map<String, Long> getLogins(Date start, Date end, String[] attribute1, String[] attribute2,
			String[] attribute3, String[] attribute4);
}
