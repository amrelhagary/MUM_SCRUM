package edu.mum.mumscrum.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.datalayer.model.Userstory;
import edu.mum.mumscrum.service.UserStoryService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

@Path("UserStoryControllerWS")
public class UserStoryController {

	private UserStoryService userStoryService;

	public UserStoryController() {
		userStoryService = new UserStoryService();
	}

	@GET
	@Path("/userstory")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllUserStorys() {
		List<Userstory> userStorysList = userStoryService.getAllUserStorys();
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(userStorysList);
		return Response.status(200).entity(result.toString()).build();
	}

	@GET
	@Path("/userstory/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserStoryById(@PathParam("id") String id) {
		Userstory userStoryResultObject = userStoryService.getUserStoryById(id);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(userStoryResultObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@POST
	@Path("/userstory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserStory(Userstory userstory) {
		Userstory userStoryResultObject = userStoryService
				.addUserStory(userstory);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(userStoryResultObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@PUT
	@Path("/userstory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserStory(Userstory userstory) {
		Userstory userStoryResultObject = userStoryService
				.updateUserStory(userstory);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(userStoryResultObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@DELETE
	@Path("/userstory/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserStoryById(@PathParam("id") String id) {
		List<Userstory> userStorysList = userStoryService
				.deleteUserStoryById(id);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(userStorysList);
		return Response.status(200).entity(result.toString()).build();
	}

	@DELETE
	@Path("/userstory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserStory(Userstory userstory) {
		Userstory userStoryResultObject = userStoryService
				.deleteUserStory(userstory);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(userStoryResultObject);
		return Response.status(200).entity(result.toString()).build();
	}

}
