package com.luv2code.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		// Get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// execute query and get result
	//	Query<Customer> theQuery=currentSession.createQuery("from Customer", Customer.class);
		// create query Sort by last name
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by firstName", Customer.class);
		
		//  return the result
		List<Customer>customers=theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// Get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();

		// Save the Customer
	//	currentSession.save(theCustomer);	 // Insert new customer
	//	currentSession.update(theCustomer);  // Update exiting customer
		currentSession.saveOrUpdate(theCustomer); // Insert + Update
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// Get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// now retrieve from database using the primary key
		Customer theCustomer=currentSession.get(Customer.class, theId);

		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// Get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// Delete obj with primary key or Id
		Query theQuery=currentSession.createQuery("delete from Customer Where id=:theCustomerId");
		theQuery.setParameter("theCustomerId", theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {

		// Get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		Query theQuery=null;
		// Only search by name if theSearchName is not Empty
		if (theSearchName != null && theSearchName.trim().length()>0) {
			//search for firstName or lastName : case insensitime
	        theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);	
	        theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else
		{
            // theSearchName is empty ... so just get all customers
			theQuery=currentSession.createQuery("from Customer", Customer.class);
		}
		
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
		
		return customers;
	} 

}
