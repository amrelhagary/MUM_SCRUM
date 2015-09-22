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

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.datalayer.model.Product;
import edu.mum.mumscrum.service.ProductService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

@Path("ProductControllerWS")
public class ProductController extends MUMScrumController {

	private ProductService productService;

	public ProductController() {
		productService = new ProductService();
	}

	@GET
	@Path("/product")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		List<Product> productsList = productService.getAllProducts();
		responseObject
				.setStatus(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject
				.setMessage(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject.setData(productsList);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@GET
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("id") String id) {
		Product productResultObject = productService.getProductById(id);
		responseObject
				.setStatus(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject
				.setMessage(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject.setData(productResultObject);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@POST
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) {
		Product productResultObject = productService.addProduct(product);
		responseObject
				.setStatus(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject
				.setMessage(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject.setData(productResultObject);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@PUT
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(Product product) {
		Product productResultObject = productService.updateProduct(product);
		responseObject
				.setStatus(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject
				.setMessage(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject.setData(productResultObject);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@DELETE
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProductById(@PathParam("id") String id) {
		List<Product> productsList = productService.deleteProductById(id);
		responseObject
				.setStatus(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject
				.setMessage(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject.setData(productsList);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}

	@DELETE
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(Product product) {
		Product productResultObject = productService.deleteProduct(product);
		responseObject
				.setStatus(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject
				.setMessage(ConfigurationConstants.ResponseMessage.SUCCESS);
		responseObject.setData(productResultObject);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}

}
