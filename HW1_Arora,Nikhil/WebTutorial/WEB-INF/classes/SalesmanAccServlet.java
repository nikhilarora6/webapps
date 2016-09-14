import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.Date;

public class SalesmanAccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public void init() throws ServletException{
      	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request, response);
		
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		
			HttpSession session = request.getSession();
			String userName= session.getAttribute("userIDDB").toString();
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("CSP595Tutorial");
			DBCursor cursor = null;
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>Account Settings</title><h1>Account Settings</h1></head><body style= 'background-color:#F2F2F2'>");
			
						
			
			//create new user
			
			out.println("<form method='get' action='SalesmanAccConfirm'>");
			out.println("<fieldset><legend>Create New User:</legend>");
			
			DBCollection myLogin = db.getCollection("myLogin");
						cursor = myLogin.find();
						ArrayList userIDList= new ArrayList();
						ArrayList userDateAddedList= new ArrayList();
						ArrayList userTypeList= new ArrayList();
						ArrayList userAddedByList= new ArrayList();
						
						out.println("<center>");
						out.println("<table BORDER=1>");
						out.println("<tr BGCOLOR='#FFAD00'>");
						out.println("<TH>User ID<TH>User Type <TH>User Added On<TH>User Added By</tr>");
			
							if(cursor.count() == 0){
								
								out.println("There are No existing orders");
							}
							else{
									
									 while (cursor.hasNext()) {
										BasicDBObject obj = (BasicDBObject) cursor.next();
										
										userIDList.add(obj.get("userIDDB"));
										userDateAddedList.add(obj.get("userAddedDB"));
										userTypeList.add(obj.get("userTypeDB"));
										userAddedByList.add(obj.get("userAddedByDB"));
										
																			
									  }
									  for (int i =0;i<userIDList.size();i++) {  
												out.println("<tr><td>"+userIDList.get(i)+" </td>");  
												out.println("<td>"+userTypeList.get(i)+"</td>");
												out.println("<td>"+userDateAddedList.get(i)+"</td>");
												out.println("<td>"+userAddedByList.get(i)+"</td>");
												
											}	
								}
			out.println("<tr>");
			out.println("</table>");
			out.println("</center>");
			out.println("<br>");
			
			out.println("<table>");
			out.println("<tr><td> Provide UserName for New User: </td>");
			out.println("<td> <input type='text' name='newUserUsername'></input></td></tr>");
			out.println("<tr><td> Provide Password for New User: </td>");
			out.println("<td> <input type='password' name='newUserPassword'/></td></tr>");
			out.println("<tr><td> Type of User: </td>");
			out.println("<td> <input type='text' name='newUserType' readonly style='background-color:#E6E6E6' value ='customer'></input></td></tr>");
			
			out.println("<tr><td></td><td><br> <input type='submit' name = 'userOperation' value='CreateNewUser'/></td></tr>");
			out.println("</table></fieldset>");
			out.println("<br>");
			out.println("<fieldset><legend>Modify/Delete Order</legend>");
			
			//to display order table
			
						DBCollection myReviews = db.getCollection("myOrders");
						cursor = myReviews.find();
						
						ArrayList orderList= new ArrayList();
						ArrayList warranty = new ArrayList();
						ArrayList firstNameList= new ArrayList();
						ArrayList lastNameList= new ArrayList();
						ArrayList deliveryList= new ArrayList();
						ArrayList priceList= new ArrayList();
						ArrayList addressList= new ArrayList();
						ArrayList phoneList= new ArrayList();
						ArrayList orderByList= new ArrayList();
						out.println("<center>");
						out.println("<table BORDER=1>");
						out.println("<tr BGCOLOR='#FFAD00'>");
						out.println("<TH>Order Number<TH>Product Price<TH>1 Year Warranty<TH>First Name<TH>Last Name");
						out.println("<TH>Address<TH>Contact:<TH>Order Delivery Date<TH>Order Placed by:</tr>");
			
							if(cursor.count() == 0){
								
								out.println("There are No existing orders");
							}
							else{
									//BasicDBObject obj=null;
									
									 while (cursor.hasNext()) {
										BasicDBObject obj = (BasicDBObject) cursor.next();
										warranty.add(obj.get("warrantyDB"));
										priceList.add(obj.get("productPriceDB"));
										orderList.add(obj.get("orderNumberDB"));
										firstNameList.add(obj.get("firstNameDB"));
										lastNameList.add(obj.get("lastNameDB"));
										deliveryList.add(obj.get("deliveryDateDB"));
										orderByList.add(obj.get("orderByDB"));		
										phoneList.add(obj.get("phoneNumberDB"));
										addressList.add(obj.get("addressDB"));
										
									  }
									  for (int i =0;i<orderList.size()&&i<firstNameList.size()&&i<lastNameList.size()&&i<deliveryList.size();i++) {  
												out.println("<tr><td>"+orderList.get(i)+" </td>");
												out.println("<td>"+priceList.get(i)+"</td>");			
												out.println("<td>"+warranty.get(i)+"</td>");
												out.println("<td>"+firstNameList.get(i)+"</td>");
												out.println("<td>"+lastNameList.get(i)+"</td>");
												out.println("<td>"+addressList.get(i)+"</td>");
												out.println("<td>"+phoneList.get(i)+"</td>");
												out.println("<td>"+deliveryList.get(i)+"</td>");
												out.println("<td>"+orderByList.get(i)+"</td></tr>");	
											}	
								}
			out.println("<tr>");
			out.println("</table>");
			out.println("</center>");
			out.println("<br>");
			
			//to display utilities
			out.println("<table>");
			out.println("<tr><td> Please Provide Order No: </td>");
			out.println("<td> <input type='text' name='orderNumber'></input></td>");
			out.println("<td><input type='submit' name = 'userOperation' value='Delete'/></td>");
			out.println("<td> Only Order Number is required to delete the order. </td></tr>");
			
			out.println("<tr><td> Please Provide New First Name For This Order:</td>");
			out.println("<td> <input type='text' name='newFirstName'></input></td></tr>");
			
			out.println("<tr><td> Please Provide New Last Name For This Order:</td>");
			out.println("<td> <input type='text' name='newLastName'></input></td></tr>");
			
			out.println("<tr><td> Please Provide New Address For This Order:</td>");
			out.println("<td> <input type='text' name='newAddress'></input></td></tr>");
			
			out.println("<tr><td> Please Provide New Phone number For This Order:</td>");
			out.println("<td> <input type='text' name='newPhone'></input></td></tr>");
			
			out.println("<td></td><td><br> <input type='submit' name = 'userOperation' value='Update'/></td>");
			
			out.println("</table></fieldset>");
			out.println("</form></body>");
			out.println("</html>");
	}
	
	protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
		response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
       // processRequest(request, response);
    }
	
	public void destroy()	{
      // do nothing.
	}
}