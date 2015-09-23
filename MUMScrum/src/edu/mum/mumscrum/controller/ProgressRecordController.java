package edu.mum.mumscrum.controller;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import edu.mum.mumscrum.common.ConfigurationConstants;
import edu.mum.mumscrum.databean.BurndownChartDataBean;
import edu.mum.mumscrum.databean.ResponseDataBean;
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
				ConfigurationConstants.ResponseMessage.SUCCESS,
				ConfigurationConstants.ResponseMessage.SUCCESS,
				burndownChartDataList);
		JsonObject result = MUMScrumUtil
				.prepareJsonObjectResponse(responseObject);
		return Response.status(200).entity(result.toString()).build();
	}
}
