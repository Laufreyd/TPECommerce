package com.tactFactory.spring.entity;

//List of all import use in this class
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author ALEPAG1
 * Instantiate the Product class, link to the table 'product'
 */
@Entity
@Table(name = "product")
public class Product {

	
	////	/!\ FIELDS /!\   ////
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private Boolean dead;
	@Column
	private float price;
	
	
	////	/!\ GETTERS OF FIELDS /!\   ////
	
	
	/**
	 * Allows to receive the Id of a product
	 * @return The Id of the product
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Allows to receive the name of a product
	 * @return The name of the product
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Allows to receive the description of a product
	 * @return The description of the product
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Allows to receive the dead of a product
	 * @return The dead of the product
	 */
	public Boolean getDead() {
		return dead;
	}
	
	/**
	 * Allows to receive the price of a product
	 * @return The price of the product
	 */
	public float getPrice() {
		return price;
	}
	
	
	////	/!\ SETTERS OF FIELDS /!\   ////
	
	
	/**
	 * Allows to change the name of a product
	 * @param date Amend the name of a product
	 * @return Return the product
	 */
	public Product setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * Allows to change the description of a product
	 * @param description Amend the description of a product
	 * @return Return the product
	 */
	public Product setDescription(String description) {
		this.description = description;
		return this;
	}
	
	/**
	 * Allows to change the dead of a product
	 * @param dead Amend the dead of a product
	 * @return Return the product
	 */
	public Product setDead(Boolean dead) {
		this.dead = dead;
		return this;
	}

	/**
	 * Allows to change the price of a product
	 * @param price Amend the price of a product
	 * @return Return the product
	 */
	public Product setPrice(float price) {
		this.price = price;
		return this;
	}
	
	
	////	/!\ CONSTRUCTORS /!\   ////

	
	//The empty constructor isn't available
	/**
	 * Create a new product
	 */
	protected Product() {}
	
	/**
	 * Create a new product
	 * @param name Fill the name of the product
	 * @param description Fill the description of the product
	 * @param dead Fill the dead condition of the product
	 * @param price Fill the price of the product
	 */
	public Product (String name, String description, Boolean dead, float price) {
		this.setName(name)
			.setDescription(description)
			.setDead(dead)
			.setPrice(price);
	}
	
	
	////	/!\ METHODS /!\   ////
	
	
	/**
	 * @return Information of the product, under a string format
	 */
	@Override
	public String toString() {
		return String.format("Produit [name: %s, description: %s, dead: %b, price: %f]", this.name, this.description, this.dead, this.price);
	}
	
	
	////	/!\ LINKS TO OTHER TABLES : Getters, Setters and Methods /!\   ////
	
	
	//Link to "myorder", in ManyToMany option
	@ManyToMany(targetEntity = Order.class, mappedBy = "products")
	private Set<Order> orders = new HashSet<>();
	
	//Getter of "orders", list of orders who have a specific product
	/**
	 * Allows to receive the list of orders who have a specific product
	 * @return List of orders who have a specific product
	 */
	public Set<Order> getOrders(){
		return orders;
	}
	
	//Setter of "orders", list of orders who have a specific product
	/**
	 * Allows to change the list of order who have a specific product
	 * @param orders List of orders
	 * @return List of orders who have a specific product
	 */
	public Product setOrders(Set<Order> orders) {
		this.orders = orders;
		return this;
	}	

}
