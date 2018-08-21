package com.legato.tomcat.logger.info.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.legato.tomcat.logger.info.domain.LogFile;
import com.legato.tomcat.logger.info.domain.OrderBy;
import com.legato.tomcat.logger.info.domain.OrderType;
import com.legato.tomcat.logger.info.service.LoggerService;

@Controller
@RequestMapping("/logger")
public class LoggerController {
	@Autowired private LoggerService loggerService;
	private static final Logger LOGGER = Logger.getLogger(LoggerController.class);
	private String basePath = "";
	
	@RequestMapping(value = "/home")
	public ModelAndView logger(HttpServletRequest request, 
			HttpServletResponse response){
		basePath = this.getClass().getClassLoader().getResource("").getPath().split("/wtpwebapps")[0] + File.separator + "logs";
		return new ModelAndView("loglist").addObject("basePath", basePath)/*.addObject("logFiles", logFiles)*/;
	}
	
	@RequestMapping(value = "/orderedFileList")
	public @ResponseBody List<LogFile> orderedFileList(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam("orderBy") int orderBy, 
			@RequestParam("orderType") int orderType){
		basePath = this.getClass().getClassLoader().getResource("").getPath().split("/wtpwebapps")[0] + File.separator + "logs";
		File[] fileArray = new File(basePath).listFiles();
		List<LogFile> logFiles = new ArrayList<>();
		for (File file : fileArray) {
			LogFile logFile = new LogFile();
			logFile.setFileName(file.getName());
			logFile.setSize(((int)file.length() < 1024) ? 1 : ((int)file.length()/1024));
			logFile.setLastModifiedDate(new Date(file.lastModified()));
			logFiles.add(logFile);
		}
		if(orderBy == OrderBy.NAME.getId())
			Collections.sort(logFiles, LogFile.sortByName(orderType == OrderType.ASC.getId()));
		if(orderBy == OrderBy.DATE.getId())
			Collections.sort(logFiles, LogFile.sortByDate(orderType == OrderType.ASC.getId()));
		if(orderBy == OrderBy.SIZE.getId())
			Collections.sort(logFiles, LogFile.sortBySize(orderType == OrderType.ASC.getId()));
		return logFiles;
	}
	
	@RequestMapping(value = "/fileInfo", method = RequestMethod.POST)
	public ModelAndView fileInfo(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam("fileName") String fileName){
		basePath = this.getClass().getClassLoader().getResource("").getPath().split("/wtpwebapps")[0] + File.separator + "logs";
		String logContent = loggerService.readFile(basePath + File.separator + fileName);
		return new ModelAndView("loginfo").addObject("filePath", basePath + File.separator + fileName) .addObject("logContent", logContent);
	}
}