package edu.mum.mumscrum.controller;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.datalayer.model.Sprint;
import edu.mum.mumscrum.service.SprintService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;


@Path("SprintControllerWS")
public class SprintController {
 
	private SprintService sprintservice;
	
	public SprintController() {
	 sprintservice = new SprintService();
	}
	
	@GET
	@Path("/sprint")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSprints()
	{
		List<Sprint> sprintlist  = sprintservice.getAllSprints();
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
					        ConfigurationConstants.ResponseMessage.SUCCESS,
							ConfigurationConstants.ResponseMessage.SUCCESS,
							sprintlist);
		return Response.status(200).entity(result.toString()).build();
	}
	
	@GET
	@Path("/sprint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSprintById(@PathParam("id") String id)
	{
		Sprint sprint = sprintservice.getSprintById(id);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse( 
				ConfigurationConstants.ResponseMessage.SUCCESS,
				ConfigurationConstants.ResponseMessage.SUCCESS,sprint);
		return  Response.status(200).entity(result.toString()).build();
	}
	
	@POST
	@Path("/sprint")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSprint(Sprint sprint)
	{
		Sprint spr = sprintservice.addSprint(sprint);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
				ConfigurationConstants.ResponseMessage.SUCCESS,
				ConfigurationConstants.ResponseMessage.SUCCESS,
				spr);
		return Response.status(200).entity(result.toString()).build();
	}
	
	@PUT
	@Path("/sprint")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSprint(Sprint sprint)
	{
		Sprint spr = sprintservice.updateSprint(sprint);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
					ConfigurationConstants.ResponseMessage.SUCCESS,
					ConfigurationConstants.ResponseMessage.SUCCESS,
					spr);
		return Response.status(200).entity(result.toString()).build();
	}
	
	@DELETE
	@Path("/sprint")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSprint(Sprint sprint)
	{
		Sprint spr = sprintservice.deleteSprint(sprint);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
				ConfigurationConstants.ResponseMessage.SUCCESS, 
				ConfigurationConstants.ResponseMessage.SUCCESS 
				,spr);
		return Response.status(200).entity(result.toString()).build();
	}
	
	@DELETE
	@Path("/sprint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSprintById(@PathParam ("id") String id)
	{
		sprintservice.setSprintIdNull(id);
		List<Sprint> spr = sprintservice.deleteSprintById(id);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
				ConfigurationConstants.ResponseMessage.SUCCESS, 
				ConfigurationConstants.ResponseMessage.SUCCESS 
				,spr);
		return Response.status(200).entity(result.toString()).build();
	}
}//SprintController
