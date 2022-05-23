package com.userlogin.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;

public class ReadWriteJSONService {
	JSONObject jsonObj;
	JSONArray jsonArray = new JSONArray();;
	String jsonFilePath = System.getProperty("user.dir") + "\\UserJSONFile.json";
	ObjectMapper ObjectMapper = new ObjectMapper();
	public void WriteJSONToFile(String strName, String strUserName, String strPassword) {
		ObjectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		File file = new File(jsonFilePath);
		if (!file.exists()) {
			jsonObj = new JSONObject();
			User user = new User();
			user.setName(strName);
			user.setUsername(strUserName);
			user.setPassword(new EncodeDecodeService().encodePassword(strPassword));
			try {
				ObjectWriter writer = ObjectMapper.writer(new DefaultPrettyPrinter());
				writer.writeValue(file, user);
			} catch (StreamWriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DatabindException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			this.appendDataToFile(strName, strUserName, strPassword);
		}

		try {
			jsonObj = (JSONObject) new JSONParser().parse(new FileReader(jsonFilePath));
			jsonArray.add(jsonObj);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(jsonArray.toJSONString());
			fileWriter.flush();
			fileWriter.close();
			Object obj = ObjectMapper.readValue(((JSONArray) new JSONParser().parse(new FileReader(jsonFilePath))).toJSONString(),Object.class);
			FileWriter fw = new FileWriter(file);
			fw.write(ObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPasswordFromFile(String strUsername) {
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
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObj = (JSONObject) jsonArray.get(i);
			if (jsonObj != null && strUsername.equals(jsonObj.get("Username"))) {
				strPassword = (String) jsonObj.get("Password");
				break;
			}
		}
		return new EncodeDecodeService().decodePassword(strPassword);
	}

	public void appendDataToFile(String strName, String strUserName, String strPassword) {
		try {
			jsonArray = (JSONArray) new JSONParser().parse(new FileReader(jsonFilePath));
			User user = new User();
			user.setName(strName);
			user.setUsername(strUserName);
			user.setPassword(new EncodeDecodeService().encodePassword(strPassword));
			ObjectWriter writer = ObjectMapper.writer(new DefaultPrettyPrinter());
			FileWriter file = new FileWriter(jsonFilePath);
			writer.writeValue(file, user);
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