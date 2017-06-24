package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

@Controller
@RequestMapping("/")
public class LogtailController {

	private static final Logger LOG = Logger.getLogger(LogtailController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome() {
		LOG.info("Main page loaded");

		Runnable r = new Runnable() {
			public void run() {
				LOG.info("Logs will be created repeatedly...");
				createLogs();
			}
		};

		new Thread(r).start();
		return "index";
	}

	@RequestMapping(value = "/getLogFileContent/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String getLogFileContent(@PathVariable("name") String fileName){
		LOG.info("getting log files."+fileName);
		try {
			LOG.info("Catalina home : "+System.getProperty("catalina.home"));
			String fileLocation = System.getProperty("catalina.home")+"/logs/"+fileName+".log";
			BufferedReader br = new BufferedReader(new FileReader(fileLocation));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} catch (Exception e) {
			LOG.error("Exception occurred while opening file : "+fileName, e);
		}
		return "file";
	}

	private void createLogs(){
		while(true) {
			try{
				Thread.sleep(5000);
				LOG.info("[createLogs] Logs will keep on generating here, time : " + new Date());
			}catch(InterruptedException e) {
				LOG.error("Exception occurred : ", e);
			}
		}
	}
}