package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.dao.CustomerDAOImpl;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// Need to inject Customer dao
	// DI : Field level using(Class)
	@Autowired
	private CustomerDAOImpl customerDAOImpl;
	
	
	//DI : Field Level using (Interface)
//	@Autowired
//	private CustomerDAO customerDAO;
	
	// DI : Constructor level
//	private CustomerDAO customerDAO;
//	@Autowired
//	public CustomerServiceImpl(CustomerDAO customerDAO) {
//		this.customerDAO=customerDAO;
//	}
	
	// DI : setter level
//	private CustomerDAO customerDAO;
//	@Autowired
//	public void setCustomerDAO(CustomerDAO customerDAO) {
//		this.customerDAO = customerDAO;
//	}

	@Override
	@Transactional
	public List<Customer> getCustomers() {

		//return customerDAO.getCustomers();
		return customerDAOImpl.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		//customerDAO.saveCustomer(theCustomer);
		customerDAOImpl.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		//return customerDAO.getCustomer(theId);	
		return customerDAOImpl.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		//customerDAO.deleteCustomer(theId);
		customerDAOImpl.deleteCustomer(theId);

	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		
		//return customerDAO.searchCustomers(theSearchName);
		return customerDAOImpl.searchCustomers(theSearchName);
	}

}
