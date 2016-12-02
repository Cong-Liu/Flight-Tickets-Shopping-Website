import java.text.SimpleDateFormat;
import java.util.List;

import database.MySQLDataStoreUtilities;
import models.Flight;
import models.Flights;

public class TestDrive {

	public static void main(String[] args) {

		 List<Flight> fl = Flights.GetInstance().getlist();
//		String startDate = "2016-12-05";
//
//		try {
//			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//			java.util.Date date = sdf1.parse(startDate);
//			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//			List<Flight> fl = MySQLDataStoreUtilities.getFlights("ORD", "SFO", "2016-12-05");
//
//			for (Flight f : fl) {
//				System.out.println(f.getFlightNumber());
//			}
//		} catch (Throwable e) {
//
//			e.printStackTrace();
//		}
	}
}
