package com.alibaba.cloud.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yingzi
 * @date 2025/5/28 08:55
 */

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Tool(description = "Get the weather of a specified city.")
    public String  getCityTimeMethod(@ToolParam(description = "Time zone id, such as Asia/Shanghai") String city) {
        logger.info("The current weather is {}", city);
        return String.format("The current time zone is %s and the current time is " + "%s", city,
                "天气晴");
    }

    private String getTimeByZoneId(String zoneId) {

        // Get the time zone using ZoneId
        ZoneId zid = ZoneId.of(zoneId);

        // Get the current time in this time zone
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zid);

        // Defining a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        // Format ZonedDateTime as a string
        String formattedDateTime = zonedDateTime.format(formatter);

        return formattedDateTime;
    }
}
