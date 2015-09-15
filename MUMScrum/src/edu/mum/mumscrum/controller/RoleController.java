package edu.mum.mumscrum.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.service.RoleService;

@Path("RoleControllerWS")
public class RoleController {
	private RoleService roleService;

	public RoleController() {
		roleService = new RoleService();
	}

	@GET
	@Path("/role")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllRoles() {
		JsonObject result = roleService.getAllRoles();
		return Response.status(200).entity(result.toString()).build();
	}
}
