package com.legato.tomcat.logger.info.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	private static final Logger LOGGER = Logger.getLogger(LoggerService.class);
	
	public String readFile(String filePath){
		StringBuilder fileContent = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line = reader.readLine();
			while (line != null) {
				fileContent.append("<span style='color: "+(line.indexOf("ERROR") != -1 ? "red" : "green")+";'>"+line+"</span><br><br>");
				line = reader.readLine();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return fileContent.toString();
	}
}