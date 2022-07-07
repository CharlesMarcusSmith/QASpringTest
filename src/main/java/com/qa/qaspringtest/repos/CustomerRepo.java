package com.qa.qaspringtest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.qaspringtest.entities.Customer;

@Repository
//JPA Repo requires <entity, data type of id column>, this must be the object version, so Long and Integer, instead of long and int.
//Is an interface because JPA Repo gives us abstract methods.
//Has all of the CRUD functionality pre-built, Save, Remove methods, etc..z but it means we dont need to write it.
public interface CustomerRepo extends JpaRepository<Customer, Long> {
//	List<Customer> findCustomerByName(String Name);
}
