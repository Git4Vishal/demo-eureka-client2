package com.example;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Patient-Service")
public interface PatientFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/patients")
	List<Patient> findPatients();

	@RequestMapping(method = RequestMethod.GET, value = "/patients/{id}")
	Patient getPatient(@PathVariable("id") String id);
}
