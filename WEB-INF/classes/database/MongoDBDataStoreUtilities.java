package database;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDataStoreUtilities {

	private MongoClient client;
	private DB database;
	private DBCollection collection;

	public MongoDBDataStoreUtilities() {
		this("CustomerReviews", "myReviews");
	}

	public MongoDBDataStoreUtilities(String db, String collection) {
		try {
			this.client = new MongoClient("localhost", 27017);
			this.database = client.getDB(db);
			this.collection = database.getCollection(collection);
			// System.out.print("MongoDB get connected");
		} catch (Exception ex) {
			System.err.println("Unable to connect mongoDB: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void close() {
		client.close();
	}
	public void insert(BasicDBObject obj) {
		this.collection.insert(obj);
	}

	public DBCursor find(DBObject query) {
		return this.collection.find(query);
	}

	public Iterable<DBObject> aggregate(BasicDBObject project, BasicDBObject match, BasicDBObject groupBy,
			BasicDBObject orderBy, int limit) {
		List<BasicDBObject> params = new ArrayList<BasicDBObject>();

		if (project != null)
			params.add(new BasicDBObject("$project", project));
		if (match != null)
			params.add(new BasicDBObject("$match", match));
		if (groupBy != null)
			params.add(new BasicDBObject("$group", groupBy));
		if (orderBy != null)
			params.add(new BasicDBObject("$sort", orderBy));
		if (limit > this.collection.count()) {
			limit = (int) this.collection.count();
			System.err.println("limit is out of boundary.");
		}
		if (limit > 0)
			params.add(new BasicDBObject("$limit", limit));
//		AggregationOutput output = collection.aggregate( params);
		return this.collection.aggregate(params).results();
	}
	
	public DBCursor sort(int limit, String feild){

		DBObject sort = new BasicDBObject();
		sort.put("reviewRating",-1);
		DBCursor dbCursor = this.collection.find().limit(limit).sort(sort);
		return dbCursor;
	}
	
	// groupField must match a key in mongodb
	public Iterable<DBObject> Count(String groupField, int order, int limit, String sortby) {

		// getting project
		BasicDBObject project = new BasicDBObject("_id", 0);
		project.put(groupField, "$" + groupField);
		project.put("_id", "$_id");
		project.put("count", "$count");
		project.put("average", "$average");

		// grouping based on groupfiled;
		BasicDBObject groupBy = new BasicDBObject("_id", 0);
		groupBy.put("_id", "$" + groupField);
		groupBy.put("count", new BasicDBObject("$sum", 1));
		groupBy.put("average", new BasicDBObject("$avg", "$reviewRate"));

		// sort by review count
		BasicDBObject orderBy = new BasicDBObject();
		orderBy.put(sortby, order);

		// limit
		Iterable<DBObject> count = aggregate(project, null, groupBy, orderBy, limit);
		return count;
	}
	
	// groupField must match a key in mongodb
	public Iterable<DBObject> Sort() {

		// getting count based on groupField
		BasicDBObject project = new BasicDBObject("_id", 0);
		project.put("productName", 1);
		project.put("reviewRate", 1);

		// grouping based on groupfiled;
		BasicDBObject groupBy = new BasicDBObject("_id", 0);
		groupBy.put("_id", "$productName");
		groupBy.put("average", new BasicDBObject("$avg", "$reviewRate"));

		// sort by review count
		BasicDBObject orderBy = new BasicDBObject();
		orderBy.put("average",-1);

		// limit
		Iterable<DBObject> count = aggregate(project, null, groupBy, orderBy, 5);
		return count;
	}
}
