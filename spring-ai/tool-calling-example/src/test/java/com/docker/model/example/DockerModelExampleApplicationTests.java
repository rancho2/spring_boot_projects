package com.docker.model.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.docker.model.example.tools.WeatherTool;

@SpringBootTest
class DockerModelExampleApplicationTests {
	@Autowired
	private WeatherTool weatherTool;
	@Test
	void getWeatherTest() {
		var response=weatherTool.getWeather("Delhi India");
		System.out.println(response);
	}

}
