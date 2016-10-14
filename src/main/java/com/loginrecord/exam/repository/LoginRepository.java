package com.loginrecord.exam.repository;

import java.util.Date;
import java.util.List;

import com.loginrecord.exam.entity.Login;

/**
 * Contains all of the abstract methods that should be implemented to satisfy
 * the needed data from the database.
 * 
 * @author Sam Christoffer Cruz
 *
 */
public interface LoginRepository {

	/**
	 * Generates data to provision the database with 100,000 dummy records.
	 * 
	 * @return returns 1 if the operation was successful.
	 */
	public long insert();

	/**
	 * Queries all of the unique dates (ignoring time) in the table in ascending
	 * order.
	 * 
	 * @return the list of unique dates.
	 */
	public List<Login> getUniqueDates();

	/**
	 * Queries all of the unique users for which there is a login record between
	 * the given start and end date.
	 * 
	 * <b>NOTE:</b> Both parameters are optional, so there can be a start date,
	 * an end date, or both. The resulting JSON array needs to be sorted
	 * ascending.
	 * 
	 * @param startDate
	 *            the minimum date
	 * @param endDate
	 *            the maximum date
	 * @return the list of logins with unique users.
	 */
	public List<Login> getUsers(Date startDate, Date endDate);

	/**
	 * Queries an object where the key is the user name and the value is the
	 * number of times a user logged on between the start and the end date.
	 * 
	 * <p>
	 * <b>NOTE:</b> All parameters are optional. The values used for the
	 * attributes are used as filters, i.e. only the records should be counted
	 * for which the attribute values are equal to the ones specified in the
	 * parameters.
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
	public List<Login> getLogins(Date start, Date end, String[] attribute1, String[] attribute2, String[] attribute3,
			String[] attribute4);
}
