package com.qa.qaspringtest.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.qaspringtest.entities.Customer;

@SpringBootTest
@AutoConfigureMockMvc										// allows us to send requests automated without postman
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) 
@ActiveProfiles("test")																				//Switches to H2 database before run test

public class CustomerControllerTest {
	@Autowired																						//tells spring, make me an object right now and store it in here
	private MockMvc mvc;																			//For sending mock requests without using PostMan, Model View Controller.
	
	@Autowired	
	private ObjectMapper mapper;																	//Deal with incoming and outgoing JSON data, converting to JSON data.
	
	@Test
	public void getAllTest() throws Exception {														//Thow exception for lines 40 and line 43to45; multiple exceptions.
		//data type received will be a list of customer, so expected output from testdata.sql.
		//This is the result we expect, the actual data tested against is found in testdata.sql.
		List<Customer> output = new ArrayList<>();
		Customer entry1 = new Customer(1L, "Anoush", "Test", "testemail@test.com");					//1L = 1 long, id being 1; 
		output.add(entry1);
		Customer entry2 = new Customer(2L, "Charles", "Smith", "charles@test.com");					//1L = 1 long, id being 1; 
		output.add(entry2);
				
		//Convert output to JSON
		String outputAsJSON = mapper.writeValueAsString(output); 									//converting result to JSON, and throwing exception done in line 31.
		
		//Sending request:
		mvc.perform(get("/customer/readAll")														//Get type request, and where to send the request; then 
				.contentType(MediaType.APPLICATION_JSON))											//telling it what data type
				.andExpect(content().json(outputAsJSON));											//expectation; chose result matches, should be first import.
	}
	
	
	@Test
	public void getByIDTest() throws Exception {
		//Data type received will be multiple Customer objects, as search result should determine correct result:
		//This is the result we expect, the actual data tested against is found in testdata.sql.
		Customer output = new Customer(1L, "Anoush", "Test", "testemail@test.com");					//Return type for this is a single customer, not a list like readAll
		
		
		//Convert output to JSON
		String outputAsJSON = mapper.writeValueAsString(output); 									//converting result to JSON
		
		//Sending request:
		mvc.perform(get("/customer/readById/1")														//Get type request, and where to send the request; then 
				.contentType(MediaType.APPLICATION_JSON))											//telling it what data type
				.andExpect(content().json(outputAsJSON));											//expectation; chose result matches, should be first import.
	}
	
	//content type + content
	//for create we have the object we want to post, in JSON, 
	
//	mvc.perform(get("/customer/readById/1")														//Get type request, and where to send the request; then 
//			.contentType(MediaType.APPLICATION_JSON))											//telling it what data type
//			.content(...)
//			.andExpect(content().json(outputAsJSON));											//expectation; chose result matches, should be first import.
//}
}
