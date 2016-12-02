package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.MySQLDataStoreUtilities;

public class Orders {
//	private final static String BIN_FILE = Template.TOMCAT_HOME + "Orders.bin";
	
	private static Orders instance = null;
	
	private List<Order> orderList = null;
	
//	private Orders() {
//		try {
//			File file = new File(BIN_FILE);
//			if (file.exists()) {
//				//initialize user list by reading bin file
//				FileInputStream fis = new FileInputStream(file);
//				ObjectInputStream ois = new ObjectInputStream(fis);
//				this.orderList = (List<Order>)ois.readObject();
//				ois.close();
//				fis.close();
//			}
//			else {
//				this.orderList = new ArrayList<Order>();
//			}
//		} catch (Exception ex) {
//			System.err.println("Unable to read Orders from BIN file. Initialize an empty order list.");
//			this.orderList = new ArrayList<Order>();
//		}
//	}
	
	private Orders(){
		try{
			this.orderList = MySQLDataStoreUtilities.getOrders();
		} catch (Throwable e) {
			System.err.println("Unable to get orders from MySQL. Initialize an empty order list.");
			this.orderList = new ArrayList<Order>();
		}
	}
	
//	//Save user list to disk as a serialized bin file
//	public void save() {
//		try {
//			File file = new File(BIN_FILE);
//			FileOutputStream fos = new FileOutputStream(file);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//			oos.writeObject(this.orderList);
//			oos.flush();
//			oos.close();
//			fos.close();
//
//		} catch (Exception e) {
//			System.err.println("Could NOT write orders to serialized file: " + e.getMessage());
//		}
//	}

	public Order findById(int orderNum) {
		if(this.orderList == null) return null;
		for(Order order : this.orderList) {
			if(order.getOrderNumber() == orderNum)
				return order;
		}
		
		return null;
	}
	
	//Add order to order list. Return order number
	public int add(Order order){
		int orderNumber;
		if(this.orderList.size() == 0) orderNumber = 0;
		else orderNumber = this.orderList.get(this.orderList.size() - 1).getOrderNumber() + 1;
		order.setOrderNumber(orderNumber);
		this.orderList.add(order);
		try{
			MySQLDataStoreUtilities.insertOrder(order);
		}catch(Throwable e){
			System.err.println("Could NOT add order into database: " + e.getMessage());
		}
		return orderNumber;
	}
	
	public boolean delete(Order o){
		boolean result =  this.orderList.remove(o);
		try{
			MySQLDataStoreUtilities.deleteOrder(o.getOrderNumber());
		}catch(Throwable e){
			System.err.println("Could NOT delete order from database: "+ e.getMessage());
		}
		return result;
	}

	public boolean delete(int id){
		boolean result =  this.orderList.remove(findById(id));
		try{
			MySQLDataStoreUtilities.deleteOrder(id);
			
		}catch(Throwable e){
			System.err.println("Could NOT delete order from database: "+ e.getMessage());
		}
		return result;
	}
//	//Add order to order list. Return order number
//	public int add(Order order) {
//		//get next order number
//		if(this.orderList == null) this.orderList = new ArrayList<Order>();
//		
//		int orderNumber;
//		if(this.orderList.size() == 0) orderNumber = 0;
//		else orderNumber = this.orderList.get(this.orderList.size() - 1).getOrderNumber() + 1;
//		
//		order.setOrderNumber(orderNumber);
//		this.orderList.add(order);
//		
//		this.save();
//		return orderNumber;
//	}
//	
//	//Remove an order from order list. Return whether the order was deleted or not
//	public boolean delete(Order order){
//		boolean result = this.orderList.remove(order);
//		
//		this.save();
//		return result;
//	}
	
	public List<Order> getList(String userName) {
		if(userName == null || userName.equals("")) return this.orderList;
		
		List<Order> list = new ArrayList<Order>();
		for(Order order : this.orderList) {
			if(userName.equalsIgnoreCase(order.getUsername()))
				list.add(order);
		}
		
		return list;
	}
	
	public static Orders GetInstance() {
		if(instance != null) return instance;
		else return instance = new Orders();
	}
}
