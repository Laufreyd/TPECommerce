/**
 * 
 */
package com.tactFactory.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.tactFactory.spring.entity.Order;

/**
 * @author ALEPAG1
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long>{

}
