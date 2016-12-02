package models;

import java.util.Date;

public class Flight {
	private int id;
	private String origin;
	private String destination;
	private Date date;
	private double price;
	private String arrivalTime;
	private String departureTime;
	private String flightNumber;
	private String carrier;
	
	public Flight(){ }
	


	public Flight(int id, String origin, String destination, Date date, double price, String arrivalTime,
			String departureTime, String flightNumber, String carrier) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.price = price;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.flightNumber = flightNumber;
		this.carrier = carrier;
	}


	public String getCarrier() {
		return carrier;
	}


	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

}
