package edu.mum.mumscrum.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.databean.AuthenticationBean;
import edu.mum.mumscrum.service.AuthenticationService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

@Path("/AuthenticationControllerWS")
public class AuthenticationController {

	private AuthenticationService authenticationService;

	public AuthenticationController() {
		authenticationService = new AuthenticationService();
	}

	@POST
	@Path("/authenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticate(AuthenticationBean authenticationObject) {
		String authCredentials = authenticationService
				.authenticate(authenticationObject);
		JsonObject result = MUMScrumUtil.prepareJsonObjectResponse(
				authCredentials, authCredentials, authenticationObject);
		return Response.status(200).entity(result.toString()).build();
	}
}
