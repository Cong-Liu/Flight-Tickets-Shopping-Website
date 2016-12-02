package models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import database.MySQLDataStoreUtilities;

public class Flights {
	public static final String TOMCAT_HOME = "G:\\Course\\csp595\\595JavaWorkSpace\\CSPProject\\";
	private final static String JSON_FILE = TOMCAT_HOME + "formatedflight.json";
	private List<Flight> flightlists;
	private static Flights instance = null;

	public Flights() {

		try {
			if (MySQLDataStoreUtilities.getFlights().isEmpty()) {
				this.flightlists = readFile(JSON_FILE);

				for (Flight f : flightlists) {
					MySQLDataStoreUtilities.insertFlight(f);
				}
			} else
				this.flightlists = MySQLDataStoreUtilities.getFlights();
		} catch (Throwable ex) {
			this.flightlists = new ArrayList<Flight>();
			System.err.println(
					"Unable to read flight from json file. Initialize an empty flight list." + ex.getMessage());
		}

	}

	private List<Flight> readFile(String fn) throws Exception {
		List<Flight> flights = new ArrayList<Flight>();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(fn));
		JSONArray jsonObject = (JSONArray) obj;
		for (int i = 0; i < jsonObject.size(); i++) {
			JSONObject f = (JSONObject) jsonObject.get(i);
			String origin = (String) f.get("origin");
			String departureTime = (String) f.get("departureTime");
			double price = Double.parseDouble(String.valueOf(f.get("price")));
			String destination = (String) f.get("destination");
			String flightNumber = (String) f.get("flightNumber");
			String arrivalTime = (String) f.get("arrivalTime");
			String d = (String) f.get("date");
			Integer id = Integer.parseInt(String.valueOf(f.get("id")));
			Date date = string2date(d);
			String carrier = (String) f.get("carrier");
			Flight flight = new Flight(id, origin, destination, date, price, arrivalTime, departureTime, flightNumber,
					carrier);
			flights.add(flight);

		}
		return flights;
	}

	private Date string2date(String d) throws java.text.ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = (Date) format.parse(d);
		Date date = new Date(parsed.getTime());
		return date;
	}

	public List<Flight> getlist() {
		return this.flightlists;
	}

	public static Flights GetInstance() {
		if (instance != null)
			return instance;
		else
			return instance = new Flights();
	}

	public void addFlight(Flight f) {
		this.flightlists.add(f);
	}
	
	public Flight findById(int id){
		if(this.flightlists == null) return null;
		for(Flight f : this.flightlists) {
			if(f.getId() == id)
				return f;
		}
		return null;
	}
	

}
