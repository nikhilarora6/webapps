import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

public class Index extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public void init() throws ServletException{
      	
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	PrintWriter out = response.getWriter();
	out.println("<html><head> <title>Game Speed</title>");
	out.println("<center><table cell spacing = '2' border = '2' style='width:100%' >");
	out.println("<tr><td colspan ='50'><h1><u><center>GameSpeed</center> </u></h1>");	
	out.println("<div align = 'Right' >"); 
	out.println("</tr>");
	out.println("<br><tr>");
	out.println("<td  class='head' colspan='10'><center><a href='welcomepage.html' class ='anchorhead'>Weekly Deals</a></center></td>");
    out.println("<td class='head' colspan='10'><center><a href='contact.html' class ='anchorhead'>Contact Us</a></center></td>");
	out.println("</tr></table>");
	
	out.println(" <link rel='stylesheet' href='mystyles.css' type='text/css' /></head>");
	out.println("<body style= 'background-color:#F2F2F2'>");
	out.println("<div align = 'Left'>");
	out.println("<table style='width:1100'><h5>");
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='#'>Microsoft</a></center></td>");
	out.println("<td rowspan = '5' colspan = '5'><center><div style='width:1150px; height:600px; margin:0 auto; margin-left: auto; margin-right: auto' >");
	out.println("<hr color='Black'>");
	out.println("<font color='Black'><h1>Welcome to Game Speed</h1></font></p>");
	out.println("<font color='Black'><p><h3>Get Ready for your best online shopping experience!!! :)</h3></p></font>");
	out.println("<hr color='Black'>");
	out.println("<h3>Existing Users Please Login To Continue / Create New User To Continue </h3>");
	out.println("<form method='post' action='LoginServlet'>");
	out.println("<table BORDER=1 ALIGN=CENTER>");
	out.println("<tr><th BGCOLOR='#FFAD00'>User Name:<td><input style='margin-left:5px' type='TEXT' size='15' name='userid'></td></tr>");
	out.println("<tr><th BGCOLOR='#FFAD00'>Password:<td><input style='margin-left:5px' type='PASSWORD' size=15 name='password'/></td></tr>");
	out.println("<tr><th BGCOLOR='#FFAD00'>User Type:<td><input type='radio' name='userType' value='storemanager'>Store Manager<br>");
	out.println("<input type='radio' name='userType' value='customer' >Customer<br>");
	out.println("<input type='radio' name='userType' value='salesman' >Salesman</td>");
	out.println("</table><br><br>");
	out.println("<input  type='submit' name='eventAction' value='Login'/><input  type='submit' name='eventAction' value='NewUser'/>");
	out.println("</form></div></center></td></tr>");
	
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='#'>Sony</a></center></td></tr> ");
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='#'>Nintendo</a></center></td></tr>");
	out.println("<tr><td class ='table2'><center><a class ='anchorhead1' href='#'>Accessories</a></center></td></tr>");
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