package edu.mum.mumscrum.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.datalayer.model.Release;
import edu.mum.mumscrum.service.ReleaseService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

@Path("ReleaseControllerWS")
public class ReleaseController {

	private ReleaseService releaseservice;

	public ReleaseController() {

		releaseservice = new ReleaseService();
	}

	@GET
	@Path("/release")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllReleases() {
		List<Release> releaselist = releaseservice.getAllReleases();
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
				ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL,
				ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL,
				releaselist);
		return Response.status(200).entity(result.toString()).build();
	}

	@GET
	@Path("/release/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getReleaseById(@PathParam("id") String id) {
		Release release = releaseservice.getReleaseById(id);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
				ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL,
				ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL,
				release);
		return Response.status(200).entity(result.toString()).build();
	}

	@POST
	@Path("/release")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRelease(Release release) {
		Release rls = releaseservice.updateRelease(release);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
				ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL,
				ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL,
				rls);
		return Response.status(200).entity(result.toString()).build();
	}

}
