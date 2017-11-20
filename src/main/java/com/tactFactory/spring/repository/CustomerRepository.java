/**
 * 
 */
package com.tactFactory.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.tactFactory.spring.entity.Customer;

/**
 * @author ALEPAG1
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
