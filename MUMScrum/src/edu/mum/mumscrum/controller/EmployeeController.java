package edu.mum.mumscrum.controller;

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

import edu.mum.mumscrum.datalayer.model.Employee;
import edu.mum.mumscrum.service.EmployeeService;

@Path("EmployeeControllerWS")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController() {
		employeeService = new EmployeeService();
	}

	// @GET here defines, this method will method will process HTTP GET
	// requests.
	// @GET

	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	// @Path("/name/{i}")

	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	// @Produces(MediaType.TEXT_XML)

	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	// @PathParam("i")

	@GET
	@Path("/employee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllEmployees() {
		JsonObject result = employeeService.getAllEmployees();
		return Response.status(200).entity(result.toString()).build();
	}

	@GET
	@Path("/employee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("id") String id) {
		JsonObject result = employeeService.getEmployeeById(id);
		return Response.status(200).entity(result.toString()).build();
	}

	@POST
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		JsonObject result = employeeService.addEmployee(employee);
		return Response.status(200).entity(result.toString()).build();
	}

	@PUT
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employee employee) {
		JsonObject result = employeeService.updateEmployee(employee);
		return Response.status(200).entity(result.toString()).build();
	}

	@DELETE
	@Path("/employee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEmployeeById(@PathParam("id") String id) {
		JsonObject result = employeeService.deleteEmployeeById(id);
		return Response.status(200).entity(result.toString()).build();
	}

	@DELETE
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEmployee(Employee employee) {
		JsonObject result = employeeService.deleteEmployee(employee);
		return Response.status(200).entity(result.toString()).build();
	}
}