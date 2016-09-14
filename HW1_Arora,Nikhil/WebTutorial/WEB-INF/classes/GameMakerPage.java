import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class GameMakerPage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public void init() throws ServletException{
      	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	HttpSession session = request.getSession();
	String userID = session.getAttribute("userIDDB").toString();
	String userType = session.getAttribute("userTypeDB").toString();
	PrintWriter out = response.getWriter();
	out.println("<html><head> <title>Game Speed</title>");
	out.println("<center><table cell spacing = '2' border = '2' style='width:100%' >");
	out.println("<tr><td colspan ='50'><h1><u><center>GameSpeed</center> </u></h1>");	
	out.println("<div align = 'Right' >"); 
	if(userType.equals("salesman"))
	out.println("Welcome "+userType+", "+userID);	
	else if(userType.equals("customer"))
	out.println("Welcome "+userType+", "+userID);
	else if(userType.equals("storemanager"))
	out.println("Welcome "+userType+", "+userID);
	out.println("<a href='/WebTutorial/ShoppingCartDisplay'><img src='/WebTutorial/images/shopping_cart.png'>Cart</a></div></td>");
	out.println("</tr>");
	out.println("<br><tr>");
	out.println("<td class='head' colspan='5' ><center><a href='/WebTutorial/MainPage' class ='anchorhead'> Console Manufacturers </a></center></td>");
    out.println("<td class='head' colspan='5' ><center><a href='/WebTutorial/GameMakerPage' class ='anchorhead'> Game Makers </a></center></td>");
    out.println("<td  class='head' colspan='5'><center><a href='welcomepage.html' class ='anchorhead'>Weekly Deals</a></center></td>");
    out.println("<td class='head' colspan='5'><center><a href='contact.html' class ='anchorhead'>Contact Us</a></center></td>");
	
	if(userType.equals("customer"))
	{
		out.println("<td class='head' colspan='5'><center><a href='/WebTutorial/CheckServlet' class ='anchorhead'>Orders</a></center></td>");
	}
	else if(userType.equals("salesman"))
	{
		out.println("<td class='head' colspan='5'><center><a href='/WebTutorial/CheckServlet' class ='anchorhead' >Account Settings</a></center></td>");
	}
	else if(userType.equals("storemanager"))
	{
		out.println("<td class='head' colspan='5'><center><a href='/WebTutorial/CheckServlet' class ='anchorhead'>Product Settings</a></center></td>");
	}
	out.println("<td class='head' colspan='5'><center><a href='/WebTutorial/LogoutServlet' class ='anchorhead'>LogOut</a></center></td>");
	out.println("</tr></table>");
	out.println(" <link rel='stylesheet' href='mystyles.css' type='text/css' /></head>");
	out.println("<body style= 'background-color:#F2F2F2'>");
	out.println("<div align = 'Left'>");
	out.println("<table style='width:1100'><h5>");
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='/WebTutorial/EACatalogPageServlet' target='iframe_a'>Electronic Arts</a></center></td>");
	out.println("<td rowspan = '5' colspan = '5'><center><iframe width='1150px' height='600px' margin:0 auto margin-left: auto margin-right: auto src='welcomepage.html' name='iframe_a'></iframe></center></td></tr>");
	
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='/WebTutorial/ActivisionCatalogPageServlet' target='iframe_a'>Activision</a></center></td></tr> ");
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='/WebTutorial/TakeTwoCatalogPageServlet'  target='iframe_a'>Take-Two Interactive</a></center></td></tr>");
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='/WebTutorial/AccessoriesCatalogPageServlet'  target='iframe_a'>Accessories</a></center></td></tr>");
	out.println("</h5></table></div><br>");
	
	out.println("<hr color ='black'>");
	out.println("<table style= 'background-color:#F2F2F2'><tr><td class ='table3'><a class = 'footer1' href='contact.html'>About Us</a></td> <td class ='table3'><a class = 'footer1' href='contact.html'>FeedBack</a></td><td class ='table3'><a class = 'footer1' href='contact.html'>Help</a></td></tr></table></body></html>");
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
	
	public void destroy(){
      // do nothing.
	}
}