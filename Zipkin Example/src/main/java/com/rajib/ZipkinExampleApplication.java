package com.rajib;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@RestController
//@EnableZipkinServer
public class ZipkinExampleApplication {
	
	private static final Logger LOG = Logger.getLogger(ZipkinExampleApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;


	public static void main(String[] args) {
		SpringApplication.run(ZipkinExampleApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	}

	@RequestMapping("/")
	public String home() {
	LOG.log(Level.INFO, "you called home");
	    return "Hello World";
	}

	@RequestMapping("/callhome")
	public String callHome() {
	LOG.log(Level.INFO, "calling home");
	    return restTemplate.getForObject("http://localhost:8082/api/getProfile", String.class);
	//return "calling home";
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
}
