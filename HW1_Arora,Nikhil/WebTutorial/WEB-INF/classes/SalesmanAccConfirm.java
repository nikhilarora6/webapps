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
import java.util.*;
import java.text.*;

public class SalesmanAccConfirm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	public void init() throws ServletException{
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		processRequest(request, response);
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db=null;
		
		String buttonType = request.getParameter("userOperation");
		HttpSession session = request.getSession();
		String userID =session.getAttribute("userIDDB").toString();
		String usertype =session.getAttribute("userTypeDB").toString();
		
		String userAddedBy = "Salesman";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");	
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		String userAdded = simpleDateFormat.format(c.getTime());
		
		String orderNumber=request.getParameter("orderNumber");
		String firstName= request.getParameter("newFirstName");
		String lastName= request.getParameter("newLastName");
		String address=request.getParameter("newAddress");
		String phone=request.getParameter("newPhone");
		
		switch(buttonType){
			
			case "CreateNewUser":
			{
						db = mongo.getDB("CSP595Tutorial");
						String userid = request.getParameter("newUserUsername");
						String password = request.getParameter("newUserPassword");	
						String userType = request.getParameter("newUserType");
						
						DBCollection myLogin = db.getCollection("myLogin");
						
						BasicDBObject doc = new BasicDBObject("title", "MongoDB")
						.append("userIDDB", userid)
						.append("userPasswordDB",password)
						.append("userTypeDB", userType)
						.append("userAddedDB",userAdded)
						.append("userAddedByDB",userAddedBy);
						
						myLogin.insert(doc);
				break;
			}
				
			case "Delete":
			{
						
						db = mongo.getDB("CSP595Tutorial");
						DBCollection myOrders = db.getCollection("myOrders");
						BasicDBObject obj1  = new BasicDBObject();
						obj1.put("orderNumberDB", orderNumber );
						myOrders.remove(obj1);
				break;
			}
			
			case "Update":
			{
											
						if(firstName.equals("")||lastName.equals("")||address.equals("")||phone.equals("")||orderNumber.equals("")){
							showPage(response, "invalid input provided");
						}
						
						BasicDBObject updateQuery = new BasicDBObject();
						updateQuery.append("$set", new BasicDBObject()
						.append("firstNameDB",firstName)
						.append("lastNameDB",lastName )
						.append("addressDB", address)
						.append("phoneNumberDB", phone));
					
						db = mongo.getDB("CSP595Tutorial");
						DBCollection myOrders = db.getCollection("myOrders");
						BasicDBObject searchQuery = new BasicDBObject().append("orderNumberDB", orderNumber);
						myOrders.update(searchQuery,updateQuery);
				break;
			}
		}
		
		if(buttonType.equals("CreateNewUser"))
		{
			showPage(response, "New User Successfully created");					
		}
		else if(buttonType.equals("Update"))
		{
			showPage(response, "Record Updated Successfully"+" "+firstName+" "+lastName+ " "+address+" "+phone);									
		}
		else if(buttonType.equals("Delete"))
		{
			showPage(response, "Order has been deleted" );			
		}	
	}
	
	
	protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Buy Servlet Result</title>");  
        out.println("</head>");
        out.println("<body style= 'background-color:#F2F2F2'>");
        out.println("<h2>" + message + "</h2>");
		out.println("<a href='/WebTutorial/MainPage'> return to salesman homepage </a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        
    }
	
	public void destroy()	{
      // do nothing.
	}
	
}