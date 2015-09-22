package edu.mum.mumscrum.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.databean.ResponseBean;
import edu.mum.mumscrum.datalayer.model.Role;
import edu.mum.mumscrum.service.RoleService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

@Path("RoleControllerWS")
public class RoleController extends MUMScrumController {
	private RoleService roleService;
	private ResponseBean responseObject;

	public RoleController() {
		roleService = new RoleService();
	}

	@GET
	@Path("/role")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllRoles() {
		List<Role> rolesList = roleService.getAllRoles();
		responseObject
				.setStatus(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject
				.setMessage(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject.setData(rolesList);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}
}
