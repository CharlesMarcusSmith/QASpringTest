package com.qa.qaspringtest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.qaspringtest.entities.Customer;
import com.qa.qaspringtest.repos.CustomerRepo;


@Service																//making bean of type service, 
public class CustomerService {
		private CustomerRepo repo;
		
		public CustomerService(CustomerRepo repo) {						
			this.repo = repo;
		}
	
	
		//Temporary storage used for testing
//		private List<Customer> customers = new ArrayList<>();
		
		//Get - READ
//		@GetMapping("/hello")	// localhost:8080/customer/hello
//		public String hello() {
//			return "Hello";
//		}
		
		//GET - ReadAll:		// localhost:8080/customer/readAll
		public List<Customer> readAll() {								//Array list used to hold the multiple results.
//			return this.customers;										//returns the array created earlier.
			return this.repo.findAll();										
		}
		
		//GET - Read By ID:		// localhost:8080/customer/readByID
		public Customer readByID(long id) {				//PathVariable is used to get the ID from the path variable, id has to be correct data type.
//			return this.customers.get(id);
			
			return this.repo.findById(id).get();						
		}
		
		//ReadByFirstName:
		public List<Customer> readByFirstName(String firstName) {
			return this.repo.findCustomerByFirstName(firstName);
		}
		
		//POST - CREATE			// localhost:8080/customer/create
		public Customer create(Customer customer) {
//			this.customers.add(customer);								//Adds customer object to customers array list made above.
//			return this.customers.get(this.customers.size() - 1);		//Returns the most recent entry added to the list.
			
			return this.repo.saveAndFlush(customer);					//save and flush guarantees it happens immediately, for efficiency purposes in large scale scenarios, as save stacks them in a queue.
		}
		
		//PUT - UPDATE			// localhost:8080/customer/update
		public Customer update(long id, Customer customer) {
//			//Removing the old record.
//			this.customers.remove(id);
//			
//			//Updating with the new record and the deleted records position.
//			this.customers.add(id, customer);
//			
//			//Returning the customer @ id index to see if it has been updated.
//			return this.customers.get(id);
			
			// SQL:
			
			//1 Get the existing entry
			Customer exsisting = this.repo.findById(id).get(); 	//storing the entry in a customer variable.
			
			//2 Change the existing entry, using our new customer object above.
			//Overwriting existing properties with the incoming values from the body of the update request: 
			exsisting.setFirstName(customer.getFirstName());			
			exsisting.setLastName(customer.getLastName());
			exsisting.setEmail(customer.getEmail());
			
			//3 Save the entry back into the database.
			return this.repo.saveAndFlush(exsisting);				//We are using the Customer as a return type so that the new entry can be confirmed
			
		}
		
		//POST - DELETE
		public boolean delete(long id) {							//boolean as we want confirm 
//			return this.customers.remove(id);
			
			this.repo.deleteById(id);								//nothing to return out of this method as stock
			return !this.repo.existsById(id);						//flips boolean, as we want to return a positive result for the deletion, if the exists by ID is False.
		}	
}
