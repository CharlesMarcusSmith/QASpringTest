package com.qa.qaspringtest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.qaspringtest.entities.Customer;


@Service																//making bean of type service, 
public class CustomerService {
	//Temporary storage used for testing
		private List<Customer> customers = new ArrayList<>();
		
		//Get - READ
//		@GetMapping("/hello")	// localhost:8080/customer/hello
//		public String hello() {
//			return "Hello";
//		}
		
		//GET - ReadAll:		// localhost:8080/customer/readAll
		public List<Customer> readAll() {								//Array list used to hold the multiple results.
			return this.customers;										//returns the array created earlier.
		}
		
		//GET - Read By ID:		// localhost:8080/customer/readByID
		public Customer readByID(int id) {				//PathVariable is used to get the ID from the path variable.
			return this.customers.get(id);
		}
		
		//POST - CREATE			// localhost:8080/customer/create
		public Customer create(Customer customer) {
			this.customers.add(customer);								//Adds customer object to customers array list made above.
			return this.customers.get(this.customers.size() - 1);		//Returns the most recent entry added to the list.
		}
		
		//PUT - UPDATE			// localhost:8080/customer/update
		public Customer update(int id, Customer customer) {
			//Removing the old record.
			this.customers.remove(id);
			
			//Updating with the new record and the deleted records position.
			this.customers.add(id, customer);
			
			//Returning the customer @ id index to see if it has been updated.
			return this.customers.get(id);
		}
		
		//POST - DELETE
		public Customer delete(int id) {
			return this.customers.remove(id);
		}
}
