/*
 * LoginServlet.java
 *
 */
 

import java.util.*;
import java.io.*;
import java.text.*;
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
import java.io.IOException;
public class ShoppingCartDisplay extends HttpServlet {
   
   
    public void init() {
		
                
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
			PrintWriter out = response.getWriter();
			
						//Access cart
						ArrayList<ShoppingCartServlet> retCart = CartOperations.fetchCart();
												
						out.println("<html><head><title>cart</title></head><body style= 'background-color:#F2F2F2'>");
						out.println("<fieldset>");
						out.println("<legend>Shopping Cart Information:</legend><br>");
						out.println("<table BORDER=1 ALIGN=CENTER>");
						out.println("<tr BGCOLOR='#FFAD00'><th>Product ID<th>Product Name<th>Product Price<th>Product Quantity<th>Discount Price<th>Final Price<th>Product Action</tr>");
						
						Random r = new Random( System.currentTimeMillis() );
						
						long finalDiscount=0,finalPrice=0;
						int discountPrice=0;
						//Run a loop
						for(int i=0 ; i<retCart.size() ; i++){
							int randomNumber=r.nextInt(30);	
							discountPrice=randomNumber;
							finalPrice=finalPrice+retCart.get(i).getPrice();
							finalDiscount=finalDiscount+discountPrice;
							out.println("<form action ='/WebTutorial/ShoppingCartOperationsServlet' method = 'get'>");
							out.println("<tr><td><input type = 'text' readonly name='id' value="+retCart.get(i).product_ID+"></td>");
							out.println("<td><input type = 'text' readonly name='name' value="+retCart.get(i).productName+"></td>");
							out.println("<td><input type = 'text' readonly name='price' value="+retCart.get(i).getPrice()+"></td>");
							out.println("<td><input type = 'text' name='quantity' value="+retCart.get(i).getQuantity()+"></td>");
							out.println("<td><input type = 'text' readonly name='discount' value="+discountPrice+"></td>");
							long temp  = retCart.get(i).getPrice() - discountPrice;
							out.println("<td><input type = 'text' readonly name='discount' value="+temp+"></td>");
							out.println("<td><input type='submit' name='action' value='update'></td></tr>");
							//out.println("<input type='submit' value='delete' name='action'>");
							out.println("</form>");	
							
							
						}
						out.println("");
						out.println("</table>");
						
						out.println("</fieldset>");
						out.println("<center>");
						out.println("<h3>NOTE: In order to remove item from cart update quantity to ZERO</h3>");
						out.println("<br><br>");
						out.println("<center>Your Order Total is: <input type = 'text' readonly name='finalPrice' value="+(finalPrice-finalDiscount)+"><center><br>");
						out.println("<form action ='/WebTutorial/BuyServlet'>");
						out.println("<input type = 'hidden' readonly name='finalPrice' value="+(finalPrice-finalDiscount)+">");
						out.println("<input type='submit' value = 'CheckOut'><br>");
						out.println("</form>");
						
						out.println("<form action ='/WebTutorial/MainPage'>");
						out.println("<input type ='submit' value ='Continue Shopping'>");
						out.println("</center>");
						out.println("</form>");
						out.println("</body></html>");
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
		out.println("<a href='/WebTutorial/MainPage'> return to Homepage </a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
    }
}
