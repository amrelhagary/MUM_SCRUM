package edu.mum.mumscrum.utility;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MUMScrumUtil {
	public static <T> JsonObject prepareJsonObjectResponse(
			String responseStatus, String responseMessage, T responseObject) {
		Gson gsonObject = new Gson();
		JsonElement status = gsonObject.toJsonTree(responseStatus);
		JsonElement message = gsonObject.toJsonTree(responseMessage);
		JsonElement data = gsonObject.toJsonTree(responseObject);

		JsonObject jsonObject = new JsonObject();
		jsonObject.add("status", status);
		jsonObject.add("message", message);
		jsonObject.add("data", data);
		return jsonObject;
	}

}
