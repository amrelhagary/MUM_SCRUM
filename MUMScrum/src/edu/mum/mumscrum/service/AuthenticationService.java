package edu.mum.mumscrum.service;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.databean.AuthenticationDataBean;
import edu.mum.mumscrum.datalayer.model.Employee;

public class AuthenticationService {
	private EmployeeService employeeService;

	public AuthenticationService() {
		employeeService = new EmployeeService();
	}

	public String authenticate(AuthenticationDataBean authenticationObject) {
		if (authenticationObject.getUsername() == null
				|| authenticationObject.getPassword() == null) {
			return ConfigurationConstants.ResponseMessage.AUTHENTICATION_FAILED;
		}

		Employee employee = employeeService
				.getEmployeeByUsername(authenticationObject.getUsername());

		if (employee != null) {
			if (!employee.getPassword().equals(
					authenticationObject.getPassword())) {
				return ConfigurationConstants.ResponseMessage.AUTHENTICATION_FAILED;
			}
		}

		authenticationObject.setRoleDesc(employee.getRole().getRoleDesc());
		String homeRoute = getAuthObjHomeRoute(employee);
		if (homeRoute == null) {
			return ConfigurationConstants.ResponseMessage.AUTHENTICATION_FAILED;
		}
		authenticationObject.setHomeRoute(homeRoute);

		return ConfigurationConstants.ResponseMessage.SUCCESS;
	}

	private String getAuthObjHomeRoute(Employee employee) {
		String roleDesc = employee.getRole().getRoleDesc();
		if (ConfigurationConstants.RoleTypeHomeRoute.PRODUCT_OWNER.getName()
				.equals(roleDesc)) {
			return ConfigurationConstants.RoleTypeHomeRoute.PRODUCT_OWNER
					.getHomeRoute();
		}
		if (ConfigurationConstants.RoleTypeHomeRoute.SCRUM_MASTER.getName()
				.equals(roleDesc)) {
			return ConfigurationConstants.RoleTypeHomeRoute.SCRUM_MASTER
					.getHomeRoute();
		}
		if (ConfigurationConstants.RoleTypeHomeRoute.DEVELOPER.getName()
				.equals(roleDesc)) {
			return ConfigurationConstants.RoleTypeHomeRoute.DEVELOPER
					.getHomeRoute();
		}
		if (ConfigurationConstants.RoleTypeHomeRoute.TESTER.getName().equals(
				roleDesc)) {
			return ConfigurationConstants.RoleTypeHomeRoute.TESTER
					.getHomeRoute();
		}
		if (ConfigurationConstants.RoleTypeHomeRoute.HR_ADMIN.getName().equals(
				roleDesc)) {
			return ConfigurationConstants.RoleTypeHomeRoute.HR_ADMIN
					.getHomeRoute();
		}
		return null;
	}

	public boolean authenticate(String authCredentials) {
		boolean authenticationStatus = false;
		if (null == authCredentials) {
			return authenticationStatus;
		}
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (usernameAndPassword.endsWith(":")
				|| usernameAndPassword.startsWith(":")) {
			return authenticationStatus;
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		Employee employee = employeeService.getEmployeeByUsername(username);

		if (employee != null) {
			authenticationStatus = employee.getPassword().equals(password);
		}

		return authenticationStatus;
	}

}
