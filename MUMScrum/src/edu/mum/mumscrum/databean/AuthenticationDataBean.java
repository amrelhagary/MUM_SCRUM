package edu.mum.mumscrum.databean;

public class AuthenticationDataBean {

	// TODO: implement employee object
	private String username;
	private String password;
	private String roleDesc;
	private String homeRoute;

	public AuthenticationDataBean(String username, String password,
			String roleDesc, String homeRoute) {
		this.username = username;
		this.password = password;
		this.roleDesc = roleDesc;
		this.homeRoute = homeRoute;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getHomeRoute() {
		return homeRoute;
	}

	public void setHomeRoute(String homeRoute) {
		this.homeRoute = homeRoute;
	}
}
