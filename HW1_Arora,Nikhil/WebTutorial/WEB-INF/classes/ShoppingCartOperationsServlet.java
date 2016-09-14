

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
public class ShoppingCartOperationsServlet extends HttpServlet {
   
   
    public void init() {
		        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
						boolean avail=false;
						PrintWriter out = response.getWriter();
						String action = request.getParameter("action");
						String id = request.getParameter("id");
						String name = request.getParameter("name");
						String price = request.getParameter("price");
						String quantity = request.getParameter("quantity");
						int cartID = Integer.parseInt(id);
						int tPrice = Integer.parseInt(price);
						int cartQuantity = Integer.parseInt(quantity);	
						ArrayList<ShoppingCartServlet> retCart;
						
						if(action.equals("update")){
							//Get the prodID
									
									//Now check against what user entered
									if(cartQuantity<=0){
										
										retCart = CartOperations.fetchCart();
										for(int i=0 ; i<retCart.size() ; i++){
										String productName = retCart.get(i).getProductName();
										int productId=retCart.get(i).getProduct_ID();
											if(productName.equals(name)&&(productId==cartID) ){
												cartID = i;
											}
										}
										CartOperations.removeFromCart(cartID); //change it
										showPage(response,"Item has been removed");	
									}
									
									else{
											retCart = CartOperations.fetchCart();							
											for(int i=0 ; i<retCart.size() ; i++){
											String  productName = retCart.get(i).getProductName();
											int productID = retCart.get(i).getProduct_ID();
											
											if(productName.equals(name) && (productID == cartID)){
												cartID = i;
											}
										}
										CartOperations.updateCart(cartID, cartQuantity ); //change this position
										out.println("Item has been updated");
									}
						}else if(action.equals("AddToCart")){
							//Compare the item, wheter its already exist in the cart
							//if yes, then just update the quantity and price
							boolean present = false;
							retCart = CartOperations.fetchCart();
							for(int i=0 ; i<retCart.size() ; i++){
								String  proName = retCart.get(i).getProductName();
								int prodID = retCart.get(i).getProduct_ID();
								if(proName.equals(name) && (prodID == cartID)){
									cartID = i;
									present = true;
								}
							}
							//Now check the condition and insert/update card
							if(present == true){
								CartOperations.updateCart(cartID,  retCart.get(cartID).getQuantity()+1);
								out.println("Item updated in the cart");
							}
							else{
								ShoppingCartServlet c = new ShoppingCartServlet(cartID, name, tPrice, cartQuantity);
								CartOperations.addToCart(c);
								out.println("Item has been added");
							}
							
						}
								
			/*			
						switch(action)
						{
							case "update":
							{
							//updating tquantity in arraylist
									 retCart = CartOperations.fetchCart();
									for(int i=0 ; i<retCart.size() ; i++){
										String productName = retCart.get(i).getProductName();
										int productId=retCart.get(i).getProduct_ID();
										if(productName.equals(name)&&(productId==cartID) ){
											cartID = i;
										}
										
									}
									CartOperations.updateCart(cartID, cartQuantity );
							//Now check against what user entered
									if(cartQuantity<=0){
										
										retCart = CartOperations.fetchCart();
										for(int i=0 ; i<retCart.size() ; i++){
										String productName = retCart.get(i).getProductName();
										int productId=retCart.get(i).getProduct_ID();
											if(productName.equals(name)&&(productId==cartID) ){
												cartID = i;
											}
										}
										CartOperations.removeFromCart(cartID); //change it
										showPage(response,"Item has been removed");	
									}
							else		
							{
								retCart = CartOperations.fetchCart();
								for(int i=0 ; i<retCart.size() ; i++){
									String productName = retCart.get(i).getProductName();
									int productId=retCart.get(i).getProduct_ID();
											if(productName.equals(name)&&(productId==cartID) ){
												cartID = i;
											}
									
								}
							}
							
							CartOperations.updateCart(cartID, cartQuantity ); //change this position
							showPage(response, "Cart has been updated successfully");
							
							break;
							}
							case "AddToCart":
							{
								retCart = CartOperations.fetchCart();	
								for(int i=0 ; i<retCart.size() ; i++){
									String productName = retCart.get(i).getProductName();
									int productId=retCart.get(i).getProduct_ID();
											if(productName.equals(name)&&(productId==cartID) ){
												cartID = i;
												avail=true;
											}
									
								}
								if(avail==true){
								int presentQuantity=retCart.get(cartID).getQuantity()+1;
								CartOperations.updateCart(cartID, presentQuantity );
								}
									
								else
								{
								ShoppingCartServlet cart = new ShoppingCartServlet(cartID, name, tPrice, cartQuantity);
								CartOperations.addToCart(cart);
								showPage(response,"Item has been added");
								}
								
							
							break;
							}
							
						}
		*/	
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
