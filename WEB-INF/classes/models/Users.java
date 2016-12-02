package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import database.MySQLDataStoreUtilities;


public class Users {
//	private final static String BIN_FILE = Template.TOMCAT_HOME + "User.bin";
//	private final static String XML_FILE = Template.TOMCAT_HOME + "User.xml";
	
	private static Users instance = null;
	
	private List<User> userList = null;
	
//	private Users() {
//		try {
//			File file = new File(BIN_FILE);
//			if (!file.exists()) {
//				// initialize user list by reading xml
//				SaxParserUsers su = new SaxParserUsers(XML_FILE);
//				this.userList = su.getUsers();
//			}
//			else {
//				//initialize user list by reading bin file
//				FileInputStream fis = new FileInputStream(file);
//				ObjectInputStream ois = new ObjectInputStream(fis);
//				this.userList = (List<User>)ois.readObject();
//				ois.close();
//				fis.close();
//			}
//		} catch (Exception ex) {
//			System.err.println("Unable to read user from XML or BIN file. Initialize an empty user list.");
//			this.userList = new ArrayList<User>();
//		}
//	}
	
	private Users(){
		try{
			this.userList = MySQLDataStoreUtilities.getUsers();
		} catch (Throwable e) {
			System.err.println("Unable to get user list from MySQL. Initialize an empty user list.");
			this.userList = new ArrayList<User>();
		}
	}
	
//	//Save user list to disk as a serialized bin file
//	public void save() {
//		try {
//			File userfile = new File(BIN_FILE);
//			FileOutputStream fos = new FileOutputStream(userfile);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//			oos.writeObject(this.userList);
//			oos.flush();
//			oos.close();
//			fos.close();
//
//		} catch (Exception e) {
//			System.err.println("Could NOT write users to userfile: " + e.getMessage());
//		}
//	}
//	
	//Find a particular user by user name
	public User findByName(String userName) {
		if(userName == null || userName.length() == 0) return null;
		
		for(User user : this.userList) {
			if(user.getName().equalsIgnoreCase(userName))
				return user;
		}
		
		return null;
	}
	
	/**
	 * Add a new user to user list
	 * @param user new user instance
	 * @return whether the user is successfully added to list. If user name is duplicated, return false.
	 */
	public boolean add(User user) {
		if(findByName(user.getName()) != null)
			return false;
//		this.save();
		try {
			MySQLDataStoreUtilities.insertUser(user.getName(), user.getPswd());
			this.userList.add(user);
		}catch (Throwable e) {
			System.err.println("Unable to add user list from MySQL: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public static Users GetInstance() {
		if(instance != null) return instance;
		else return instance = new Users();
	}
}
