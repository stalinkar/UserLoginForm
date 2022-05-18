package com.userlogin.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadWriteJSONService {
	JSONObject jsonObj;
	String jsonFilePath = System.getProperty("user.dir")+"UserJSONFile.json";

	public void WriteJSONToFile(String strName, String strUserName, String strPassword) {

		File file = new File(jsonFilePath);
		if (!file.exists()) {
			jsonObj = new JSONObject();
			jsonObj.put("Name", strName);
			jsonObj.put("Username", strUserName);
			jsonObj.put("Password", new EncodeDecodeService().encodePassword(strPassword));
			try {
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(jsonObj.toJSONString());
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.appendDataToFile(strName, strUserName, strPassword);
		}
	}

	public String getPasswordFromFile(String strUsername) {

		JSONArray jsonArray = new JSONArray();;
		try {
			jsonArray = (JSONArray) new JSONParser().parse(new FileReader(jsonFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strPassword = null;
	    for(int i=0;i<jsonArray.size();i++){
	    	jsonObj = (JSONObject) jsonArray.get(i);
	        if(jsonObj!=null && strUsername.equals(jsonObj.get("Username"))){
	        	strPassword = (String) jsonObj.get("Password");
	        	break;
	        }
	    }
		return new EncodeDecodeService().decodePassword(strPassword);
	}

	public void appendDataToFile(String strName, String strUserName, String strPassword) {
		JSONArray jsonArray = new JSONArray();;
		try {
			Object obj = new JSONParser().parse(new FileReader(jsonFilePath));
			jsonArray.add(obj);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("Name", strName);
			jsonObj.put("Username", strUserName);
			jsonObj.put("Password", new EncodeDecodeService().encodePassword(strPassword));

			jsonArray.add(jsonObj);

			FileWriter file = new FileWriter(jsonFilePath);
			file.write(jsonArray.toJSONString());
			file.flush();
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}