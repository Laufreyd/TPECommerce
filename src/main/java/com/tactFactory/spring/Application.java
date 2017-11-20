package com.tactFactory.spring;

//List of all import use in this class
import javax.jws.soap.SOAPBinding.Style;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tactFactory.spring.entity.Customer;
import com.tactFactory.spring.entity.Order;
import com.tactFactory.spring.entity.Product;
import com.tactFactory.spring.repository.CustomerRepository;
import com.tactFactory.spring.repository.OrderRepository;
import com.tactFactory.spring.repository.ProductRepository;

/**
 * 
 * @author ALEPAG1
 *
 */
@SpringBootApplication
public class Application {

	final static Log logger = LogFactory.getLog(Style.class);
	
	/**
	 * Main application, start and end in this
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * 
	 * @param cRepository
	 * @param pRepository
	 * @param oRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(CustomerRepository cRepo, ProductRepository pRepo,OrderRepository oRepo) {
		return (args) -> {

			cRepo.deleteAll();
			pRepo.deleteAll();
			oRepo.deleteAll();
			
			//Create customers
			Customer c1 = new Customer("firstName", "lastName","0606060606", "address", 12, "city of stars");
			Customer c2 = new Customer("Gal", "Gadot","0606060606", "address", 1235, "Beautiful city");
			Customer c3 = new Customer("Theo", "Martin", "0606060606", "3 rue du pommier", 48000, "Montpellier");
			
			//Create orders
			Order o1 = new Order(c2);
			Order o2 = new Order(c3);
			Order o3 = new Order(c3);
			Order o4 = new Order(c2);
			
			//Create products
			Product p1 = new Product("name1", "description1", false, 0f);
			Product p2 = new Product("name2", "description2", false, 1f);
			Product p3 = new Product("name3", "description3", false, 2f);
			Product p4 = new Product("name4", "description4", false, 3f);
			Product p5 = new Product("name5", "description5", false, 4f);
			Product p6 = new Product("name6", "description6", false, 5f);
			Product p7 = new Product("name7", "description7", false, 6f);
			Product p8 = new Product("name8", "description8", false, 7f);
			
			//Add product into the order
			o1.ProductAdd(p1);
			o2.ProductAdd(p2);
			o3.ProductAdd(p3);
			o4.ProductAdd(p4);
			o1.ProductAdd(p5);
			o2.ProductAdd(p6);
			o3.ProductAdd(p7);
			o4.ProductAdd(p8);
			o1.ProductAdd(p7);
			o2.ProductAdd(p6);
			o3.ProductAdd(p5);
			o4.ProductAdd(p1);
			
			//Save into the database
			logger.info("Saving into database: ");
			cRepo.save(c1);
			cRepo.save(c2);
			cRepo.save(c3);
			logger.info("Saved customers: " + c1.toString() + "%n" + c2.toString() + "%n" + c3.toString() + "%n");
			
			oRepo.save(o1);
			oRepo.save(o2);
			oRepo.save(o3);
			oRepo.save(o4);
			logger.info("Saved orders: " + o1.toString() + "%n" + o2.toString() + "%n" + o3.toString() + "%n" + o4.toString() + "%n");
			
			pRepo.save(p1);
			pRepo.save(p2);
			pRepo.save(p3);
			pRepo.save(p4);
			pRepo.save(p5);
			pRepo.save(p6);
			pRepo.save(p7);
			pRepo.save(p8);
			logger.info("Saved products: " + p1.toString() + "%n" + p2.toString() + "%n" + p3.toString() + "%n");
		};
	}
	
	
}
