package com.estafet.microservices.scrum.lib.commons.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestHelper {

	@SuppressWarnings("rawtypes")
	public static <T> List<T> listMapper(List objects, Class<T> clazz) {
		List<T> projects = new ArrayList<T>();
		ObjectMapper mapper = new ObjectMapper();
		for (Object object : objects) {
			T project = mapper.convertValue(object, new TypeReference<T>() {
			});
			projects.add(project);
		}
		return projects;
	}

	public static <T> List<T> getRestQuery(RestTemplate restTemplate, String uri, Class<T> clazz, Object... uriVariables) {
		return listMapper(restTemplate.getForObject(uri, List.class, uriVariables), clazz);
	}

	public static <T> List<T> getRestQuery(String uri, Class<T> clazz, Object... uriVariables) {
		return getRestQuery(new RestTemplate(), uri, clazz, uriVariables);
	}

}
