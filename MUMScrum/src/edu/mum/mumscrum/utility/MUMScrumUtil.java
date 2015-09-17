package edu.mum.mumscrum.utility;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import edu.mum.mumscrum.common.ConfigurationConstants;

public class MUMScrumUtil {
	public static <T> JsonObject prepareJsonObjectResponse(T object) {
		Gson gsonObject = new Gson();
		JsonElement status = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement message = gsonObject
				.toJsonTree(ConfigurationConstants.ResponseMessage.OPERATION_SUCCESSFUL);
		JsonElement data = gsonObject.toJsonTree(object);

		JsonObject jsonObject = new JsonObject();
		jsonObject.add("status", status);
		jsonObject.add("message", message);
		jsonObject.add("data", data);
		return jsonObject;
	}

}
