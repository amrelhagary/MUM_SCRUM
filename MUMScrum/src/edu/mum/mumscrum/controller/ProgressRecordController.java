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
import edu.mum.mumscrum.databean.BurndownChartDataBean;
import edu.mum.mumscrum.databean.ResponseDataBean;
import edu.mum.mumscrum.datalayer.model.ProgressRecord;
import edu.mum.mumscrum.service.ProgressRecordService;
import edu.mum.mumscrum.utility.MUMScrumUtil;

@Path("/ProgressRecordControllerWS")
public class ProgressRecordController extends MUMScrumController {

	private ProgressRecordService progressRecordService;

	public ProgressRecordController() {
		progressRecordService = new ProgressRecordService();
	}

	@Path("/burndownchart/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBurndownChartDataBySprintId(@PathParam("id") String id) {
		List<BurndownChartDataBean> burndownChartDataList = progressRecordService
				.getBurndownChartDataBySprintId(id);
		responseObject = new ResponseDataBean(
				ConfigurationConstants.ErrorMessage.SUCCESS,
				ConfigurationConstants.ErrorMessage.SUCCESS,
				burndownChartDataList);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}
	
	@GET
	@Path("/progress_record/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProgressRecordById(@PathParam("id") String id)
	{
		ProgressRecord progressrecord = progressRecordService.getProgressRecordById(id);
		responseObject = new ResponseDataBean(
				ConfigurationConstants.ErrorMessage.SUCCESS,
				ConfigurationConstants.ErrorMessage.SUCCESS,
				progressrecord);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}
	
	@POST 
	@Path("/progress_record")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setStartTime ( ProgressRecord pr )
	{ 
		ProgressRecord prorec = progressRecordService.ckeckFlagStatus(pr);    	
    	responseObject = new ResponseDataBean(
				ConfigurationConstants.ErrorMessage.SUCCESS,
				ConfigurationConstants.ErrorMessage.SUCCESS,
				prorec);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}
	
}
