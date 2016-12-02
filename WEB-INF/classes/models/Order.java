package models;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Order implements java.io.Serializable {
	private int orderNumber;
	private int status = 0;	//0: in cart, 1: order placed, 2: order shipped, -1: order cancelled
	private double totalPrice;
	private String address;
	private String creditCard;
	private String username;
	private Date date;	//the date time when order was placed
	private List<Flight> items;

	//empty constructor, for serialization purpose
	public Order() { }

	public Order(String userName) {
		this.username = userName;
		this.status = 0;
		this.orderNumber = -1;
		this.items = new ArrayList<Flight>();
	}

	
	public Order(int orderNumber, int status, double totalPrice, String address, String creditCard, String username,
			Date date) {
		super();
		this.orderNumber = orderNumber;
		this.status = status;
		this.totalPrice = totalPrice;
		this.address = address;
		this.creditCard = creditCard;
		this.username = username;
		this.date = date;
		this.items = new ArrayList<Flight>();
	}

	public int addItem(Flight item) {
		if(this.items==null) this.items = new ArrayList<Flight>();
		this.items.add(item);
		this.totalPrice += item.getPrice();
		return this.items.size();
	}
	
	public int removeItem(int index) {
		if(this.items == null) return -1;
		if(index < 0 || index >= this.items.size()) return -1;
		
		Flight item = this.items.get(index);
		this.totalPrice = this.totalPrice - item.getPrice();
		this.items.remove(index);
		return this.items.size();
	}
	
	public List<Flight> getItems() {
		return this.items;
	}
	
	public void checkout(String address, String cardNumber) {
		this.address = address;
		this.creditCard = cardNumber;
		this.status = 1;
		this.date = new Date();
	}
	
	//try to cancel this order. Return true indicate this order has been successfully canceled
	public boolean cancel() {
		if (this.status != 1)
			return false;

		// user can cancel this order only within 5 days after order has been placed
		long diff = Math.abs(new Date().getTime() - this.date.getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);
		if (diffDays > 5)
			return false;
		else
			return true;
	}
	
	public String getAddress() {
		return address;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int number) {
		orderNumber = number;
	}

	public int getStatus() {
		return status;
	}
	
	public String getStatusString() {
		if(status == 0) return "In Cart";
		else if(status == 1) return "Placed";
		else if(status == 2) return "Delivered";
		else if(status == -1) return "Canceled";
		else return "Unknown";
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public Date getDeliveryDate() {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 14);
        return cal.getTime();
	}
	
	public String getDeliveryDateString() {
		Date date = this.getDeliveryDate();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return sdf.format(date);
	}
}
