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

public class SubmitReview extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 processRequest(request, response);
		
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
			DBCollection myReviews=null;
			String manufacturerName = request.getParameter("manufacturerName");
			String productCategory = request.getParameter("productCategory");
			String productPrice = request.getParameter("productPrice");
			String retailerName = request.getParameter("retailerName");
			String retailerCity = request.getParameter("retailerCity");
			String retailerState = request.getParameter("retailerState");
			String retailerZip = request.getParameter("retailerZip");
			String productOnSale = request.getParameter("productOnSale");
			String rebateAvailable = request.getParameter("rebateAvailable");
			String productName = request.getParameter("productName");
			String userName = request.getParameter("userName");
			int userAge = Integer.parseInt(request.getParameter("userAge"));
			String userGender = request.getParameter("gender");
			String userOccupation = request.getParameter("userOccupation");
			int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));	
			String reviewDate = request.getParameter("reviewDate");
			String reviewText = request.getParameter("reviewText");
			
		try{	
			
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			myReviews = db.getCollection("myReviews");
			System.out.println("Collection myReviews selected successfully");
				
			BasicDBObject doc = new BasicDBObject("title", "myReviews").
				append("manufacturerNameDB", manufacturerName).
				append("productCategoryDB", productCategory).
				append("productPriceDB", productPrice).
				append("retailerNameDB", retailerName).
				append("retailerCityDB", retailerCity).
				append("retailerStateDB", retailerState).
				append("retailerZipDB", retailerZip).
				append("productOnSaleDB", productOnSale).
				append("rebateAvailableDB", rebateAvailable).
				append("productNameDB", productName).
				append("userNameDB", userName).
				append("userAgeDB", userAge).
				append("userGenderDB", userGender).
				append("userOccupationDB", userOccupation).
				append("reviewRatingDB", reviewRating).
				append("reviewDateDB", reviewDate).
				append("reviewTextDB", reviewText);
									
			myReviews.insert(doc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		
		//Send the response back to the JSP
			PrintWriter out = response.getWriter();
						
			out.println("<html>");
			out.println("<head><title>success</title></head>");
			out.println("<body>");
			out.println("<h1> Review submitted for:"+ productName + "</h1>");
			out.println("</body>");
			out.println("</html>");
	}
	
	
	protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
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