package com.docker.model.example.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

public class SimpleDateTimeTool {
	private Logger logger=LoggerFactory.getLogger(getClass());
	//information:tool
	@Tool(description = "Get the current date and time in users zone.")
	public String getCurrentDateTime()
	{
		this.logger.info("Tool calling");
		this.logger.info("Get the current date and time in users zone.");
		return LocalDateTime.now()
				.atZone(LocaleContextHolder.getTimeZone().toZoneId())
				.toString();
	}
	//action tool:so that it can take action
	@Tool(description="Set the alarm for given time.")
	void setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time)
	{
		var dateTime=LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
		this.logger.info("Set the alarm for given time.{}",dateTime);
	}
}
