/**
 * 
 */
package com.tactFactory.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.tactFactory.spring.entity.Product;

/**
 * @author ALEPAG1
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long>{

}
