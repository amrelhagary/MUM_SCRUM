package edu.mum.mumscrum.controller;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.datalayer.model.Sprint;
import edu.mum.mumscrum.service.SprintService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

import java.util.List;

import javax.ws.rs.GET;;

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
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(sprintlist);
		return Response.status(200).entity(result.toString()).build();
	}
	
	@GET
	@Path("/sprint/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSprintById(@PathParam("id") String id)
	{
		Sprint sprint = sprintservice.getSprintById(id);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(sprint);
		return  Response.status(200).entity(result.toString()).build();
	}
}//SprintController
