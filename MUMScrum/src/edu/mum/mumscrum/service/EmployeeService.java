package edu.mum.mumscrum.service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.datalayer.dao.EmployeeDAO;
import edu.mum.mumscrum.datalayer.model.Employee;

public class EmployeeService {
	EmployeeDAO employeeDAO;

	public EmployeeService() {
		employeeDAO = EmployeeDAO.getInstance();
	}

	public JsonObject getAllEmployees() {
		List<Employee> employeesList = employeeDAO.getAllEmployees();
		return prepareJsonObjectResponse(employeesList);
	}

	private JsonObject prepareJsonObjectResponse(List<Employee> employeesList) {
		Gson gsonObject = new Gson();
		JsonElement status = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement message = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement data = gsonObject.toJsonTree(employeesList);

		JsonObject jsonObject = new JsonObject();
		jsonObject.add("status", status);
		jsonObject.add("message", message);
		jsonObject.add("data", data);
		return jsonObject;
	}

	public JsonObject getEmployeeById(String id) {
		Employee employee = employeeDAO.getEmployeeById(id);
		return prepareJsonObjectResponse(employee);
	}

	public JsonObject addEmployee(Employee employee) {
		Employee newEmployee = employeeDAO.addEmployee(employee);
		return prepareJsonObjectResponse(newEmployee);
	}

	public JsonObject updateEmployee(Employee employee) {
		Employee updatedEmployee = employeeDAO.updateEmployee(employee);
		return prepareJsonObjectResponse(updatedEmployee);
	}

	public JsonObject deleteEmployee(Employee employee) {
		Employee deletedEmployee = employeeDAO.deleteEmployee(employee);
		return prepareJsonObjectResponse(deletedEmployee);
	}

	public JsonObject deleteEmployeeById(String id) {
		List<Employee> deletedEmployees = employeeDAO.deleteEmployeeById(id);
		return prepareJsonObjectResponse(deletedEmployees);
	}

	private JsonObject prepareJsonObjectResponse(Employee deletedEmployee) {
		Gson gsonObject = new Gson();
		JsonElement status = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement message = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement data = gsonObject.toJsonTree(deletedEmployee);

		JsonObject jsonObject = new JsonObject();
		jsonObject.add("status", status);
		jsonObject.add("message", message);
		jsonObject.add("data", data);
		return jsonObject;
	}

}