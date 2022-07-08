package com.qa.qaspringtest.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.qaspringtest.entities.Customer;

@Repository
//JPA Repo requires <entity, data type of id column>, this must be the object version, so Long and Integer, instead of long and int.
//Is an interface because JPA Repo gives us abstract methods.
//Has all of the CRUD functionality pre-built, Save, Remove methods, etc..z but it means we dont need to write it.
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
//	we dont need to do this: we can use derived
//	@Query(value = "SELECT * from customers WHERE firstName = ?1 and lastName = ?2", nativeQuery = true)
//	List<Customer> findCustomerByfirstNameAndlastName(String firstName, String lastName);
//	
//	@Query(value = "SELECT * from customers WHERE email = ?1", nativeQuery = true)
//	List<Customer> findCustomerByemail(String email);	
	
	//using derived query for findByFirstName
	List<Customer> findCustomerByFirstName(String firstName);						//Naming convention changed for firstName, to FirstName as it isn't the start, and spring is clever enough to separate the correct property firstName f
																					//SQL Syntax = Select * FROM customer WHERE first_name = firstName
	
	
}
