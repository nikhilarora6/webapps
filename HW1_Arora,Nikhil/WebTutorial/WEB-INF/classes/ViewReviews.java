import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import java.util.List;
import java.util.Set;
import java.util.Date;

public class ViewReviews extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String pName=request.getParameter("productName");
				
			String manufacturerName = "";
			String productCategory = "";
			String productPrice = "";
			String retailerName = "";
			String retailerCity = "";
			String retailerState = "";
			String retailerZip = "";
			String productOnSale = "";
			String rebateAvailable = "";
			String productName = "";
			String userName = "";
			int userAge = 0;
			String userGender = "";
			String userOccupation = "";
			int reviewRating = 0;	
			String reviewDate = "";
			String reviewText = "";
			
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myReviews = db.getCollection("myReviews");
			
			PrintWriter out = response.getWriter();
			//out.println(cursor);
						
			out.println("<html>");
			out.println("<head> </head>");
			out.println("<body>");
			out.println("<h1> Reviews For:  "+pName+"</h1>");
			
			out.println("<table>");
			
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("productNameDB", pName);

			DBCursor cursor = myReviews.find(searchQuery);
			
			if(cursor.count() == 0){
				out.println("There are no reviews for this product.");
			}
			else{
				while (cursor.hasNext())
					{
					out.println("<table>");
					out.println("<br>");
					BasicDBObject obj = (BasicDBObject) cursor.next();
					out.println("<tr>");
						out.println("<td> Manufacturer Name: </td>");
						manufacturerName = obj.getString("manufacturerNameDB");
						out.println("<td>" +manufacturerName+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Product Name: </td>");
						productName = obj.getString("productNameDB");
						out.println("<td>" +productName+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Product Category:  </td>");
						productCategory = obj.getString("productCategoryDB");
						out.println("<td>" +productCategory+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Product Price: </td>");
						productPrice = obj.getString("productPriceDB");
						out.println("<td>" +productPrice+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Retailer Name: </td>");
						retailerName = obj.getString("retailerNameDB");
						out.println("<td>" +retailerName+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Retailer City: </td>");
						retailerCity = obj.getString("retailerCityDB");
						out.println("<td>" +retailerCity+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Retailer State: </td>");
						retailerState = obj.getString("retailerStateDB");
						out.println("<td>" +retailerState+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Retailer Zip: </td>");
						retailerZip = obj.getString("retailerZipDB");
						out.println("<td>" +retailerZip+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Product On Sale: </td>");
						productOnSale = obj.getString("productOnSaleDB");
						out.println("<td>" +productOnSale+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Rebate Available: </td>");
						rebateAvailable = obj.getString("rebateAvailableDB");
						out.println("<td>" +rebateAvailable+ "</td>");
						out.println("</tr>");
						out.println("<tr>");
						
						out.println("<tr>");
						out.println("<td> User Name: </td>");
						userName = obj.getString("userNameDB");
						out.println("<td>" +userName+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> User Age: </td>");
						userAge = Integer.parseInt(obj.getString("userAgeDB"));
						out.println("<td>" +userAge+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Gender: </td>");
						userGender = obj.getString("userGenderDB");
						out.println("<td>" +userGender+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> User Occupation: </td>");
						userOccupation = obj.getString("userOccupationDB");
						out.println("<td>" +userOccupation+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Review Rating: </td>");
						reviewRating = Integer.parseInt(obj.getString("reviewRatingDB"));
						out.println("<td>" +reviewRating+ "</td>");
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td> Review Date: </td>");
						reviewDate = obj.getString("reviewDateDB");
						out.println("<td>" +reviewDate+ "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Review Text: </td>");
						reviewText = obj.getString("reviewTextDB");
						out.println("<td>" +reviewText+ "</td>");
						out.println("</tr>");
						out.println("<br>");
						out.println("<hr>");
					}
					out.println("</table>");
				}
				//out.println("<hr>");
				out.println("</body>");
				out.println("</html>");
			
		} catch (MongoException e) {
				e.printStackTrace();
		}
	}

	
	public void destroy(){
      // do nothing.
	}
}