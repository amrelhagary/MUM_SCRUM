package edu.mum.mumscrum.service;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.datalayer.dao.RoleDAO;
import edu.mum.mumscrum.datalayer.model.Role;

public class RoleService {

	private RoleDAO roleDAO;

	public RoleService() {
		roleDAO = RoleDAO.getInstance();
	}

	public JsonObject getAllRoles() {
		List<Role> rolesList = roleDAO.getAllRoles();

		Gson gsonObject = new Gson();
		JsonElement status = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement message = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement data = gsonObject.toJsonTree(rolesList);

		JsonObject jsonObject = new JsonObject();
		jsonObject.add("status", status);
		jsonObject.add("message", message);
		jsonObject.add("data", data);
		return jsonObject;
	}

}
