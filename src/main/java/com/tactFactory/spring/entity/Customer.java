package com.tactFactory.spring.entity;

//List of all import use in this class
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author ALEPAG1
 * Instantiate the Customer class, link to the table 'customer'
 */
@Entity
@Table(name = "customer")
public class Customer {
	
	
	////	/!\ FIELDS /!\   ////
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String phone;
	@Column
	private String address;
	@Column
	private int cp;
	@Column
	private String city;
	

	////	/!\ GETTERS OF FIELDS /!\   ////
		
	
	/**
	 * Allows to receive the Id of a customer
	 * @return The Id of the customer
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Allows to receive the first name of a customer
	 * @return The first name of the customer
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Allows to receive the last name of a customer
	 * @return The last name of the customer
	 */
	public String getLastName() {
		return lastName;
	}
	

	/**
	 * Allows to receive the phone number of a customer
	 * @return The phone number of the customer
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Allows to receive the address of a customer
	 * @return The address of the customer
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Allows to receive the postal code of a customer
	 * @return The postal code of the customer
	 */
	public int getCp() {
		return cp;
	}

	/**
	 * Allows to receive the city of a customer
	 * @return The city of the customer
	 */
	public String getCity() {
		return city;
	}
	
	
	////	/!\ SETTERS OF FIELDS /!\   ////

	
	/**
	 * Allows to change the first name of a customer
	 * @param firstName Amend the first name of a customer
	 * @return Return the customer
	 */
	public Customer setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * Allows to change the last name of a customer
	 * @param lastName Amend the last name of a customer
	 * @return Return the customer
	 */
	public Customer setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * Allows to change the phone number of a customer
	 * @param phone Amend the phone number of a customer
	 * @return Return the customer
	 */
	public Customer setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	/**
	 * Allows to change the address of a customer
	 * @param address Amend the address of a customer
	 * @return Return the customer
	 */
	public Customer setAddress(String address) {
		this.address = address;
		return this;
	}

	/**
	 * Allows to change the postal code of a customer
	 * @param cp Amend the postal code of a customer
	 * @return Return the customer
	 */
	public Customer setCp(int cp) {
		this.cp = cp;
		return this;
	}

	/**
	 * Allows to change the city of a customer
	 * @param city Amend the city of a customer
	 * @return Return the customer
	 */
	public Customer setCity(String city) {
		this.city = city;
		return this;
	}

	
	////	/!\ CONSTRUCTORS /!\   ////
	
	
	//The empty constructor isn't available
	/**
	 * Create a new customer
	 */
	protected Customer() {}
	
	/**
	 * Create a new customer
	 * @param firstName Fill the first name of the new customer
	 * @param lastName Fill the last name of the new customer
	 * @param phone Fill the phone number of the new customer
	 * @param address Fill the address of the new customer
	 * @param cp Fill the postal code of the new customer
	 * @param city Fill the city of the new customer
	 */
	public Customer (String firstName, String lastName, String phone, String address, int cp, String city) {
		this.setFirstName(firstName)
			.setLastName(lastName)
			.setPhone(phone)
			.setAddress(address)
			.setCp(cp)
			.setCity(city);
	}
	
	
	////  /!\ METHODS /!\   ////
	
	
	/**
	 * @return Information of the customer, under a string format
	 */
	@Override
	public String toString() {
		return String.format("Customer [first name: %s, last name: %s, phone number: %s, address: %s, postal code: %d, city: %s", this.firstName, this.lastName, this.phone, this.address, this.cp, this.city);
	}
	
	
	////  /!\ LINKS TO OTHER TABLES : Getters, Setters and Methods /!\   ////
	
	
	//Link to 'myorder', in OneToMany option
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	//Getter of "orders", list of orders of the customer
	/**
	 * Allows to receive the list of orders of a customer
	 * @return List of orders of a customer
	 */
	public List<Order> getOrders(){
		return orders;
	}
	
	//Setter of "orders", list of orders of the customer
	/**
	 * Allows to put a list of orders to a customer
	 * @param orders List of orders
	 * @return the customer who receive the list of orders
	 */
	public Customer setOrders(List<Order> orders) {
		this.orders = orders;
		return this;
	}
	
	//Method of "orders"	
	/**
	 * Allows to delete an order to a customer
	 * @param order Order to delete in this customer
	 */
	public void OrderDelete(Order order) {
		this.orders.remove(order);
	}
	
}
