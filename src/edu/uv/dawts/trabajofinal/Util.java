package edu.uv.dawts.trabajofinal;

import com.google.gson.Gson;

public class Util<T> {
	public  String dataToJson(T model) {
		Gson gson = new Gson();
		String result = gson.toJson(model);
		return result;
	}
	
	public static String test() {
		return "test";
	}
}
