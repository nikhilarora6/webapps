import javax.servlet.*;
import javax.servlet.http.*;
public class LogoutServlet extends HttpServlet {
   
   // protected Map users = new HashMap();
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {
		
                //users.put("test", "TEST");
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		response.setContentType("text/html");
			java.io.PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
				try{
						if(session==null)
							{
								showPage(response, "No session found Please Login to continue");
							}
						
						else
							{
							
							String user= session.getAttribute("userIDDB").toString();
							session.invalidate();
							CartOperations.fetchCart().clear();
							out.println("<html>");
							out.println("<head>");
							out.println("<title>Servlet Result</title>");  
							out.println("</head>");
							out.println("<body>");
							out.println("<h2>"+user+"  have logged out successfully </h2>");
							out.println("<a href='/WebTutorial/Index'>Index</a>");
							out.println("</body>");
							out.println("</html>");
							out.close();
							}
					}
					catch(Exception e)
					{
						showPage(response, "No session found please login to continue");
					}
		
		
	}	
  
    /**
     * Actually shows the <code>HTML</code> result page
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
		out.println("<a href='/WebTutorial/Index'>Index</a>");
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
        processRequest(request, response);
    }
}
