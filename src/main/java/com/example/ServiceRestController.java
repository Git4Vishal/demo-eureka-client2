package com.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
//@PropertySource("classpath:application.yml")
class ServiceRestController {
	
	private final RestTemplate restTemplate;
	private final PatientFeignClient patientsClient;

	@Autowired
	public ServiceRestController(RestTemplate restTemplate, PatientFeignClient patientsClient) {
		super();
		this.restTemplate = restTemplate;
		this.patientsClient = patientsClient;
	}

	
	public static final String PATIENTS_SERVICE_ID = "Patient-Service/patients";
	
//	@Value("${eureka.instance.instance-id}")
//	String instanceID;
	
	@RequestMapping(value = "/patients", 
					produces = { MediaType.APPLICATION_JSON_VALUE }, 
					method = { RequestMethod.GET })
	public List<Patient> findPatients() {
		Patient[] patients = null;
		String serviceUrl = String.format("http://%s", PATIENTS_SERVICE_ID);
		try { 
 			patients = this.restTemplate.getForObject(serviceUrl, Patient[].class); 
 		} catch (HttpClientErrorException e) { // 404 
 			// Nothing found 
 		} 
 
 
 		if (patients == null || patients.length == 0) 
 			return null; 
 		else 
 			return Arrays.asList(patients); 

	}
	
	@RequestMapping(value = "/patients/{id}", 
					method = RequestMethod.GET)
	public Patient findPatients(@PathVariable(value = "id") String id) {
		String url = String.format("http://%s/{id}", PATIENTS_SERVICE_ID);
		return this.restTemplate.getForObject(url, Patient.class, id);
	}
	
	// Using Feign
	@RequestMapping(value = "/feign-patients",
					produces = { MediaType.APPLICATION_JSON_VALUE },
					method = { RequestMethod.GET })
	public List<Patient> findAll() {
		return this.patientsClient.findPatients();
	}

	@RequestMapping(value = "/feign-patients/{id}", method = RequestMethod.GET)
	public Patient findbyID(@PathVariable(value = "id") String id) {
		return this.patientsClient.getPatient(id);
	}
	
}