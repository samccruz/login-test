package com.loginrecord.exam.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loginrecord.exam.entity.Login;
import com.loginrecord.exam.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class handles HTTP request for the different operations.
 * 
 * @author Sam Christoffer Cruz
 *
 */
@RestController
@Api(value = "Login Test")
public class RecordController {

	/**
	 * Service containing methods and business logic for operations on login
	 * times.
	 */
	private LoginService loginService;

	/**
	 * Constructs a new record controller that initializes the loginService by
	 * the injected service in the constructor argument.
	 * 
	 * @param loginService
	 *            the service that contains the business logic for login times.
	 */
	@Autowired
	public RecordController(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * Retrieves all of the unique dates (ignoring time) in the table. All of
	 * the dates are sorted ascending.
	 * 
	 * @return all of the unique dates in the table.
	 */
	@GetMapping("/dates")
	@ApiOperation(value = "Returns all of the unique dates in the table.", response = Login.class,
	notes = "Retrieves all of the unique dates (ignoring time) in the table. All of the dates are sorted ascending.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of user detail", response = Login.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Object getUniqueDates() {
		return loginService.getUniqueDates();
	}

	/**
	 * Retrieves all of the unique users for which there is a login record
	 * between the start and end date.
	 * 
	 * <p>
	 * <b>NOTE:</b> Both parameters are optional, so there can be a start date,
	 * an end date, or both. The resulting JSON array needs to be sorted
	 * ascending.
	 * 
	 * @param startDate
	 *            the minimum date
	 * @param endDate
	 *            the maximum date
	 * @return the list of unique users.
	 */
	@GetMapping("/users")
	@ApiOperation(value = "Returns the list of unique users.", response = Login.class,
	notes = "Retrieves all of the unique users for which there is a login record between the start and end date.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of user detail", response = Login.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Object getUsers(
			@ApiParam(name = "start", value = "The minimum date (format: yyyyMMdd)", required = false)
			@RequestParam(name = "start", required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date startDate,
			@ApiParam(name = "end", value = "The maximum date (format: yyyyMMdd)", required = false)
			@RequestParam(name = "end", required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date endDate) {
		return loginService.getUsers(startDate, endDate);
	}

	/**
	 * Retrieves an object where the key is the user name and the value is the
	 * number of times a user logged on between the start and the end date.
	 * 
	 * <p>
	 * <b>NOTE:</b> All parameters are optional. The values used for the
	 * attributes are used as filters, i.e. only the records should be counted
	 * for which the attribute values are equal to the ones specified in the
	 * parameters.
	 * 
	 * @param startDate
	 *            the minimum date
	 * @param endDate
	 *            the maximum date
	 * @param attribute1
	 *            the values of attribute1
	 * @param attribute2
	 *            the values of attribute2
	 * @param attribute3
	 *            the values of attribute3
	 * @param attribute4
	 *            the values of attribute4
	 * @return the list of unique users with their logins.
	 */
	@GetMapping("/logins")
	@ApiOperation(value = "Returns the list of unique users with their logins.", response = Login.class,
		notes = "Retrieves an object where the key is the user name and the value is the number of times a user logged on between the start and the end date.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful retrieval of user detail", response = Login.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Object getLogins(
			@ApiParam(name = "start", value = "The minimum date (format: yyyyMMdd)", required = false)
			@RequestParam(name = "start", required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date startDate,
			@ApiParam(name = "end", value = "The maximum date (format: yyyyMMdd)", required = false)
			@RequestParam(name = "end", required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date endDate,
			@ApiParam(name = "attribute1", value = "The values for attribute1", required = false)
			@RequestParam(name = "attribute1", required = false) String[] attribute1,
			@ApiParam(name = "attribute2", value = "The values for attribute2", required = false)
			@RequestParam(name = "attribute2", required = false) String[] attribute2,
			@ApiParam(name = "attribute3", value = "The values for attribute3", required = false)
			@RequestParam(name = "attribute3", required = false) String[] attribute3,
			@ApiParam(name = "attribute4", value = "The values for attribute4", required = false)
			@RequestParam(name = "attribute4", required = false) String[] attribute4) {
		return loginService.getLogins(startDate, endDate, attribute1, attribute2, attribute3, attribute4);
	}

	/**
	 * Provisions the database with 100,000 dummy records.
	 * 
	 * @return returns 1 if the operation was successful.
	 */
	@GetMapping("/logins/insert")
	@ApiOperation(value = "Returns 1 if the operation was successful.",
		notes = "Provisions the database with 100,000 dummy records.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successful retrieval of user detail"),
		@ApiResponse(code = 500, message = "Internal server error") })
	public Object insertDummy() {
		return loginService.insert();
	}
}
