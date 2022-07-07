package com.qa.qaspringtest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaspringtest.entities.Customer;
import com.qa.qaspringtest.services.CustomerService;

// Handle incoming HTTP requests and send responses
//Uses JSON data
@RestController					//Make an object (bean) of this class, of type rest controller
@RequestMapping("/customer")	//Adds a prefix to request URLs, which adds a /customer before mapping
public class CustomerController {

	private CustomerService service;								//now this controller is going to contain a customer service
	
	// We then tell it it is a dependency using constructor:
	//	This injects a bean into here
	public CustomerController(com.qa.qaspringtest.services.CustomerService service) {
		
		super();	
		this.service = service;										//This controller must be created with a service, which Spring will do.
	}
	
	//GET - ReadAll:
	@GetMapping("/readAll")
	public List<Customer> readAll() {								//Array list used to hold the multiple results.
		return this.service.readAll();								//this.service is our service object created line 25 - 30			
	}
	
	//GET - Read By ID:
	@GetMapping(".readById/{id}")									//here we can see how to the handler is picking up id
	public Customer readByID(@PathVariable int id) {				//PathVariable is used to get the ID from the path variable.
		return this.service.readByID(id);
	}
	
	//POST - CREATE
	@PostMapping("/create")
	public Customer create(@RequestBody Customer customer) {
		return this.service.create(customer);
	}
	
	//PUT - UPDATE
	@PutMapping("/update/{id}")
	public Customer update(@PathVariable int id, @RequestBody Customer customer) {
		return this.service.update(id, customer);
	}
	
	//POST - DELETE
	@DeleteMapping("/delete/id")
	public Customer delete(@PathVariable int id) {
		return this.service.delete(id);
	}
	
}
