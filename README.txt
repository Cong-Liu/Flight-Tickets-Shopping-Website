1. According to my bitbucket commit, I have writen more than 2500 lines of code and till now, the project has more than 1500 lines of code.

2. I implement the following feature:
	1> Visiter can view and search my website
	2> Only registed users can buy ticket from my website
	3> Registed user can add/delete tickets from his own shoppig cart
	4> Registed user can view and cancel his order in 'MyOrder' page, however, only orders created less than one day can be canceled.
	5> Users and visitors can using left navigation bar 'Airlines Included' to narrow his search by airline companies.
	6> Using servlets for 'Home page', 'Login', 'Signup' and 'LogOut' pages or features.
	7> Using JSP and Java Beans for 'Home page', 'tickets list', 'Shopping cart', 'MyOrder' pages or features.
	8> Using MySQL to store user infomation, order infomation and ticket infomation
	9> Using Python 2.7 and ipython notebook to read raw ticket file, modify data and write to 'modifiedflight.json' file.
	10> Using Google Flight Search API to get flight infomation, due to the rate limits, I stored data in 'flight.json' after search.
	11> Using cookies and sessions for different variables.
	12> Modified CSS and HTML template for the website.
	
3. Tutoral of deployment
	0. Open Flights.java in file CSPProject\WEB-INF\classes\models, and change static final String TOMCAT_HOME to the root where the project located.
	1. copy additional jar libraries from file ...\jars to tomcat\lib
	2. open \database file, run bestflight.sql into in  MySQL data store location, the Username of MySQL is root, password of MySQL is 0000000.
	3. put \CSPProject rest of files into "...\apache-tomcat-7.0.34\webapps" to the root path of where you put the project files.
	4. Run throw 'FlightParser.ipynb' to get formated flight infomation
	5. Homepage: open url "http://localhost/CSPProject/Home.jsp" in your browser.
	6. Default user account: username: user1, password: user1
	7. Feel free to creat any user account from sign up page from login entry.
	8. For flight search, all the flights I saved are at only shows some of SFO-ORD, ORD-SFO, ORD-LAX, LAX-ORD on 2016/12/05.