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
import java.util.List;
import java.util.Set;
import java.util.Date;

public class StoreManagerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				 processRequest(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
       
    }
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {		
			
			//to add product
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>ADD PRODUCT</title><center><h3>ADD NEW PRODUCT</h3></center></head>");
			
			out.println("<body style= 'background-color:#F2F2F2'>");
			out.println("<form method ='get' action='/WebTutorial/StoreMgrConfirmServlet'>");
			out.println("<fieldset>");
			out.println("<legend>Add New Product information:</legend>");
			out.println("<table BORDER=1 ALIGN=CENTER>");
			out.println("<tr BGCOLOR='#FFAD00'>");
			out.println("<th>PRODUCT TYPE:<th>PRODUCT ID:<th> PRODUCT NAME:<th> PRODUCT DESCRIPTION:<th> PRODUCT PRICE:</tr>");
			out.println("<tr><td> <select name='addProductType'><option value='microsoft'>MICROSOFT</option>");
			out.println("<option value='sony'>SONY</option>");			 
			out.println("<option value='nintendo'>NINTENDO</option>");
			out.println("<option value='activision'>ACTIVISION</option>");
			out.println("<option value='electronicarts'>ELECTRONIC ARTS</option>");
			out.println("<option value='taketwo'>TAKE-TWO</option>");
			out.println("<option value='accessories'>ACCESSORIES</option></select> </td>");
			out.println("<td> <input type='text' name='addProductID'> </td>");
			out.println("<td> <input type='text' name='addProductName'> </td>");
			out.println("<td> <input type='text' name='addProductDesc'> </td>");
			out.println("<td> <input type='text' name='addProductPrice'> </td></tr>");
			out.println("</table><br><center><input type='submit' name = 'action' value='ADD'><center><br></fieldset>");
			
			//to delete product
			out.println("<fieldset>");
			out.println("<legend>Delete Product</legend>");
			out.println("<table BORDER=1 ALIGN=CENTER>");
			out.println("<tr BGCOLOR='#FFAD00'>");
			out.println("<th>PRODUCT TYPE:<th>PRODUCT ID:</tr>");
			out.println("<tr><td> <select name='delProductType'><option value='microsoft'>MICROSOFT</option>");
			out.println("<option value='sony'>SONY</option>");			 
			out.println("<option value='nintendo'>NINTENDO</option>");	
			out.println("<option value='activision'>ACTIVISION</option>");
			out.println("<option value='electronicarts'>ELECTRONIC ARTS</option>");
			out.println("<option value='taketwo'>TAKE-TWO</option>");			
			out.println("<option value='accessories'>ACCESSORIES</option></select> </td>");
			out.println("<td> <input type='text' name='delProductID'> </td></tr>");
			out.println("</table ><br><center><input type='submit' name = 'action' value='DELETE'><center><br></fieldset>");
			
			//to update product
			out.println("<fieldset>");
			out.println("<legend>Update Product</legend>");
			out.println("<table BORDER=1 ALIGN=CENTER>");
			out.println("<tr BGCOLOR='#FFAD00'>");
			out.println("<th>PRODUCT TYPE:<th>PRODUCT ID:<TH>PRODUCT NAME:<TH>PRODUCT DESC:<th>PRODUCT PRICE</tr>");
			out.println("<tr><td> <select name='updateProductType'><option value='microsoft'>MICROSOFT</option>");
			out.println("<option value='sony'>SONY</option>");			 
			out.println("<option value='nintendo'>NINTENDO</option>");			 
			out.println("<option value='activision'>ACTIVISION</option>");
			out.println("<option value='electronicarts'>ELECTRONIC ARTS</option>");
			out.println("<option value='taketwo'>TAKE-TWO</option>");
			out.println("<option value='accessories'>ACCESSORIES</option></select> </td>");
			out.println("<td><input type='text' name='updateProductID'> </td>");
			out.println("<td><input type='text' name='updateName'> </td>");
			out.println("<td><input type='text' name='updateDesc'> </td>");
			out.println("<td><input type='text' name='updatePrice'> </td></tr>");
			out.println("</table ><br><center><input type='submit' name = 'action' value='UPDATE'><center><br></fieldset>");
			out.println("</form>");
			out.println("<center><h2>Return To Home Page</h2><a href='/WebTutorial/MainPage'>Home</a><center>");
			out.println("</body></html>");
			
	}
	
	
	protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Buy Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
		out.println("<a href='index.html'>Index</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
	
	
	public void destroy()	{
      // do nothing.
	}
	
}