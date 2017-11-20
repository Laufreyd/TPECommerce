package com.tactFactory.spring.entity;

//List of all import use in this class
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ALEPAG1
 * Instantiate the Order class, link to the table 'order'
 */
@Entity
@Table(name = "myorder")
public class Order {
	
	
	////	/!\ FIELDS /!\   ////

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private Date date;
	@Column
	private OrderState state;
	@Column
	private float totalPrice;
	
	
	////	/!\ GETTERS OF FIELDS /!\   ////
	
	
	/**
	 * Allows to receive the Id of an order
	 * @return The Id of the order
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Allows to receive the date of an order
	 * @return The date of the order
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Allows to receive the state of an order
	 * @return The state of the order
	 */
	public OrderState getState() {
		return state;
	}
	
	/**
	 * Allows to receive the total of an order
	 * @return The total of the order
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	
	
	////	/!\ SETTERS OF FIELDS /!\   ////
	
	
	//You can't change the date of an order, just initialize it
	/**
	 * Allows to change the date of an order
	 * @param date Amend the date of an order
	 * @return Return the order
	 */
	private Order setDate(Date date) {
		this.date = date;
		return this;
	}
	
	/**
	 * Allows to change the state of an order
	 * @param state Amend the state of an order
	 * @return Return the order
	 */
	public Order setState(OrderState state) {
		this.state = state;
		return this;
	}
	
	/**
	 * Allows to change the total of an order
	 * @param totalPrice Amend the total of an order
	 * @return Return the order
	 */
	private Order setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}
	
	
	////	/!\ CONSTRUCTORS /!\   ////
	
	/**
	 * Create a new Order
	 * @param customer Customer who have this order
	 */
	public Order(Customer customer) {
		this.setDate(new Date())
			.setState(OrderState.Brouillon)
			.setTotalPrice(0f)
			.setCustomer(customer);
	}
	
	
	////	/!\ METHODS /!\   ////
	
	
	/**
	 * @return Information of the order, under a string format
	 */
	@Override
	public String toString() {
		return String.format("Caddie [Date: %d, State: %s, Total: %f]", this.date, this.state, this.totalPrice);
	}
	
	
	////	/!\ LINKS TO OTHER TABLES : Getters, Setters and Methods /!\   ////
	
	
	//Link to 'customer', in ManyToOne option
	@ManyToOne
	private Customer customer;
	
	//Getter of "customer", customer who make/made the order
	/**
	 * Allows to receive the customer of the order
	 * @return Customer who make/made the order
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	//Setter of "customer", customer who make/made the order
	/**
	 * Allows to change the customer of the order
	 * @param customer Customer to link with the order
	 * @return the order with the new customer
	 */
	public Order setCustomer(Customer customer) {
		this.customer = customer;
		List<Order> orders = this.customer.getOrders();
		orders.add(this);
		this.customer.setOrders(orders);
		return this;
	}
	
	
	//Link to 'product', in ManyToMany option
	@ManyToMany(targetEntity = Product.class, cascade = CascadeType.MERGE)
	
	//Create the new table "Products_Order" with keys of order and product
	@JoinTable(
			name = "Products_Order",
			joinColumns = {@JoinColumn(name = "order_id")},
			inverseJoinColumns = {@JoinColumn(name = "product_id")})	
	private Set<Product> products = new HashSet<>();
	
	//Getter of "products", list of products of the order
	/**
	 * Allows to receive the list of products of the order
	 * @return the list of products of the order
	 */
	public Set<Product> getProducts(){
		return products;
	}
	
	//Setter of "products", list of products of the order
	/**
	 * Allows to change the list of products of the order
	 * @param products list of products to put in the order
	 * @return the order with the new list of products
	 */
	public Order setProducts(Set<Product> products) {
		this.products = products;
		float totalPriceOfOrder = 0f;
		for (Product product : this.products) {
			totalPriceOfOrder += product.getPrice();
		}
		this.setTotalPrice(totalPriceOfOrder);
		return this;
	}
	
	//Methods of "products"
	/**
	 * Allows to put a new product in the order
	 * @param product Product to add in the order
	 */
	public void ProductAdd(Product product) {
		this.products.add(product);
		Set<Order> orders = product.getOrders();
		orders.add(this);
		product.setOrders(orders);
		this.setTotalPrice(this.getTotalPrice() + product.getPrice());
	}
	
	/**
	 * Allows to delete a product in this order
	 * @param product Product to remove in this order
	 */
	public void ProductDelete(Product product) {
		this.products.remove(product);
		Set<Order> orders = product.getOrders();
		orders.remove(this);
		product.setOrders(orders);
		this.setTotalPrice(this.getTotalPrice() - product.getPrice());
	}
}
