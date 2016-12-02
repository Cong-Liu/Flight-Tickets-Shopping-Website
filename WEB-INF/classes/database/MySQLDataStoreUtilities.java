package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCollection;

import models.Flight;
import models.Order;
import models.User;

public class MySQLDataStoreUtilities {
	static Connection conn = null;

	public static void getConnection() throws Throwable {

		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestflightdb", "root", "0000000");

	}

	public static void insertUser(String username, String password) throws Throwable {

		getConnection();
		String insertUserQuery = "INSERT INTO users(username,password) " + "VALUES (?,?);";
		PreparedStatement pst = conn.prepareStatement(insertUserQuery);
		pst.setString(1, username);
		pst.setString(2, password);
		pst.execute();

		conn.close();
	}

	public static void deleteUser(String username) throws Throwable {

		getConnection();
		String deleteUserQuery = "DELETE FROM users WHERE username = ?;";
		PreparedStatement pst = conn.prepareStatement(deleteUserQuery);
		pst.setString(1, username);
		pst.execute();
		conn.close();
	}

	public static List<User> getUsers() throws Throwable {
		List<User> users = new ArrayList<User>();

		getConnection();
		String selectallUsersQuery = "select * from users;";
		ResultSet rs = null;
		Statement st = conn.createStatement();
		rs = st.executeQuery(selectallUsersQuery);
		while (rs.next()) {
			String name = rs.getString("username");
			String pswd = rs.getString("password");
			users.add(new User(name, pswd));
		}
		rs.close();
		st.close();
		conn.close();
		return users;
	}

	public static void deleteOrder(int orderNumber) throws Throwable {
		getConnection();
		String deleteOrderQuery = "DELETE FROM orders  WHERE orderNumber = ?;";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1, orderNumber);
		pst.execute();
		conn.close();
	}

	public static void insertOrder(Order o) throws Throwable {

		getConnection();
		String insertOrderQuery = "INSERT INTO orders(orderNumber,status,totalPrice,address,creditCard,username,date) "
				+ "VALUES (?,?,?,?,?,?,?);";
		PreparedStatement pst = conn.prepareStatement(insertOrderQuery);
		pst.setInt(1, o.getOrderNumber());
		pst.setInt(2, o.getStatus());
		pst.setDouble(3, o.getTotalPrice());
		pst.setString(4, o.getAddress());
		pst.setString(5, o.getCreditCard());
		pst.setString(6, o.getUsername());
		pst.setDate(7, parser2sqldate(o.getDate()));
		pst.execute();
		conn.close();
	}

	public static List<Order> getOrders() throws Throwable {
		List<Order> orderList = new ArrayList<Order>();
		getConnection();
		String selectallOrderQuery = "select * from orders;";
		ResultSet rs = null;
		Statement st = conn.createStatement();
		rs = st.executeQuery(selectallOrderQuery);

		while (rs.next()) {

			int orderNumber = rs.getInt("orderNumber");
			int status = rs.getInt("status");
			double price = rs.getDouble("totalPrice");
			String address = rs.getString("address");
			String creditCard = rs.getString("creditCard");
			String username = rs.getString("username");
			Date sqlDate = rs.getDate(7);
			orderList.add(new Order(orderNumber, status, price, address, creditCard, username, sqlDate));
		}
		rs.close();
		st.close();

		conn.close();
		return orderList;

	}

	public static void delectFlight(int fid) throws Throwable {
		getConnection();
		String deleteProductQuery = "DELETE FROM flights WHERE id = ?;";
		PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
		pst.setInt(1, fid);
		pst.execute();

		conn.close();
	}

	public static void insertFlight(Flight f) throws Throwable {

		getConnection();
		String insertProductQuery = "INSERT INTO flights(id,origin,destination,Date,price,arrivalTime,departureTime,flightNumber,carrier) "
				+ "VALUES (?,?,?,?,?,?,?,?,?);";
		PreparedStatement pst = conn.prepareStatement(insertProductQuery);
		pst.setInt(1, f.getId());
		pst.setString(2, f.getOrigin());
		pst.setString(3, f.getDestination());
		pst.setDate(4, parser2sqldate(f.getDate()));
		pst.setDouble(5, f.getPrice());
		pst.setString(6, f.getArrivalTime());
		pst.setString(7, f.getDepartureTime());
		pst.setString(8, f.getFlightNumber());
		pst.setString(9, f.getCarrier());
		pst.execute();
		conn.close();
	}

	public static List<Flight> getFlights() throws Throwable {
		List<Flight> flist = new ArrayList<Flight>();
		getConnection();
		String selectFlights = "select * from flights;";
		ResultSet rs = null;
		Statement st = conn.createStatement();
		rs = st.executeQuery(selectFlights);
		while (rs.next()) {
			int id = rs.getInt(1);
			String ori = rs.getString(2);
			String dest = rs.getString(3);
			Date date = rs.getDate(4);
			double price = rs.getDouble(5);
			String arrT = rs.getString(6);
			String depT = rs.getString(7);
			String fnum = rs.getString(8);
			String carrier = rs.getString(9);

			flist.add(new Flight(id, ori, dest, date, price, arrT, depT, fnum,carrier));
		}
		rs.close();
		st.close();
		conn.close();
		return flist;
	}
	
	public static List<Flight> getFlights(String or, String des, String d) throws Throwable {
		List<Flight> flist = new ArrayList<Flight>();
		getConnection();
		String selectFlights = "SELECT * FROM bestflightdb.flights where origin=\'"+or
				+"\' and destination = \'"+des+"\' and date = \'"+d+"\';";
		ResultSet rs = null;
		Statement st = conn.createStatement();
		rs = st.executeQuery(selectFlights);
		while (rs.next()) {
			int id = rs.getInt(1);
			String ori = rs.getString(2);
			String dest = rs.getString(3);
			Date date = rs.getDate(4);
			double price = rs.getDouble(5);
			String arrT = rs.getString(6);
			String depT = rs.getString(7);
			String fnum = rs.getString(8);
			String carrier = rs.getString(9);

			flist.add(new Flight(id, ori, dest, date, price, arrT, depT, fnum,carrier));
		}
		rs.close();
		st.close();
		conn.close();
		return flist;
	}

	private static Date parser2sqldate(java.util.Date d) {

		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		return sqlDate;
	}

}
