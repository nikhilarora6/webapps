	

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Base class for pages showing catalog entries.
 *  Servlets that extend this class must specify
 *  the catalog entries that they are selling and the page
 *  title <I>before</I> the servlet is ever accessed. This
 *  is done by putting calls to setItems and setTitle
 *  in init.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class MicrosoftCatalogPageServlet extends HttpServlet {
 
  
  public void init() throws ServletException{
      	
	}
  
  /** First display title, then, for each catalog item,
   *  put its short description in a level-two (H2) heading
   *  with the price in parentheses and long description
   *  below. Below each entry, put an order button
   *  that submits info to the OrderPage servlet for
   *  the associated catalog entry.
   *  <P>
   *  To see the HTML that results from this method, do
   *  "View Source" on KidsBooksPage or TechBooksPage, two
   *  concrete classes that extend this abstract class.
   */
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
  
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					//+Fetch the details of product and show in html page
					//Reading form product file
					ServletContext sc = request.getServletContext();
					File inFile = new File(sc.getRealPath("Microsoftproduct.txt"));
					
				if (!inFile.isFile()) {
				out.println("PinFilearameter is not an existing file");
				return;
				}

					BufferedReader br = new BufferedReader(new FileReader(sc.getRealPath("Microsoftproduct.txt")));
					
					String line = null;
					String[] mytemp = new String[5];
					out.println("<html><head><title>Product List</title></head><body style= 'background-color:#CEECF5'>");
				while ((line = br.readLine()) != null) {
					mytemp=line.split(" ");
					String id = mytemp[0];
					String name = mytemp[1];
					String desc = mytemp[2];
					String price = mytemp[3];
	
					
					out.println("<form action ='/WebTutorial/ShoppingCartOperationsServlet' method ='get'>");
					out.println("<input type='hidden' name='id' value="+id+">");
					out.println("<input type='hidden' name='name' value="+name+">");
					out.println("<input type='hidden' name='price' value="+price+">");
					out.println("<input type='hidden' name='quantity' value=1>");
					out.println("<HR COLOR='BLACK'>\n");
					out.println("<h1>"+name+" "+desc+" "+price+"</h1>");
					out.println("<CENTER><input type = 'submit' name = 'action' value = 'AddToCart' ></CENTER>");
					out.println("</form>");
					out.println("<form method ='get' action = '/WebTutorial/ReviewServlet'>");
					out.println("<input type='hidden' name='manufacturerName' value='Microsoft'>");
					out.println("<input type='hidden' name='productName' value="+name+">");
					out.println("<input type='hidden' name='productCategory' value='GamingConsole'>");
					out.println("<input type='hidden' name='price' value="+price+">");
					out.println("<CENTER><input type = 'submit' value = 'Write Review' ></CENTER>");
					out.println("</form>");
					out.println("<form method ='get' action = '/WebTutorial/ViewReviews'>");
					out.println("<input type='hidden' name='productName' value="+name+">");	
					out.println("<CENTER><input type = 'submit' value = 'View Review'></CENTER>");
					out.println("</form>");
					out.println("<HR COLOR='BLACK'>\n");
					
				}
					out.println("</body></html>");
	
	    }
    
    
  
}