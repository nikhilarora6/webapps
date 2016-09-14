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

public class StoreMgrConfirmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	public void init() throws ServletException{
      	
		}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				processRequest(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        
    }
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {		
			String action= request.getParameter("action");
			//variables for adding new item
			String pType=request.getParameter("addProductType");
			String pID= request.getParameter("addProductID");
			String pName= request.getParameter("addProductName");
			String pDesc = request.getParameter("addProductDesc");
			String pPrice= request.getParameter("addProductPrice");
			
			
			//variables for updating an item
			String updateProductType=request.getParameter("updateProductType");
			String updateProductID=request.getParameter("updateProductID");
			String updateProductName=request.getParameter("updateName");
			String updateProductDesc=request.getParameter("updateDesc");
			String updateProductPrice=request.getParameter("updatePrice");
			
			//variables for deleting an item
			String delProductType = request.getParameter("delProductType");
			String delProductID = request.getParameter("delProductID");
			
				
			if(action.equals("ADD"))
			{
				ServletContext sc = request.getSession().getServletContext();
						File fname=null;
						switch(pType){
							case "nintendo":
							{
								fname= new File(sc.getRealPath("Nintendoproduct.txt"));
								break;
							}
							case "microsoft":
							{
								fname= new File(sc.getRealPath("Microsoftproduct.txt"));
								break;
							}
							case "sony":
							{
								fname= new File(sc.getRealPath("Sonyproduct.txt"));
								break;
							}
							case "accessories":
							{
								fname= new File(sc.getRealPath("Accessories.txt"));
								break;
							}
							case "activision":
							{
								fname= new File(sc.getRealPath("Activisionproduct.txt"));
								break;
							}
							case "electronicarts":
							{
								fname= new File(sc.getRealPath("EAproducts.txt"));
								break;
							}
							case "taketwo":
							{
								fname= new File(sc.getRealPath("Taketwoproducts.txt"));
								break;
							}
							
						}
						//+Writing orders in file
						
							FileWriter fileWriter = new FileWriter(fname,true);
							BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
							bufferedWriter.write(pID+" "+pName+" "+pDesc+" "+pPrice+" ");
							bufferedWriter.write("\n");
							bufferedWriter.close();
						
						PrintWriter out = response.getWriter();
						out.println("<html>");
						out.println("<head><title>ADD PRODUCT</title><center><h3>NEW PRODUCT ADDED</h3></center></head>");
						
						out.println("<body style= 'background-color:#F2F2F2'>");
						
						out.println("<fieldset>");
						out.println("<legend>New Product information:</legend>");
						out.println("<table BORDER=1 ALIGN=CENTER>");
						out.println("<tr BGCOLOR='#FFAD00'>");
						out.println("<th>PRODUCT TYPE:<TH> PRODUCT ID:<th> PRODUCT NAME:<th> PRODUCT DESCRIPTION:<th> PRODUCT PRICE:</tr>");
						out.println("<tr><td><input type='text' name='productType' readonly value="+pType+ "></td>");
						out.println("<td> <input type='text' name='productID' readonly value="+pID+ "> </td>");
						out.println("<td> <input type='text' name='productName' readonly value="+pName+ "> </td>");
						out.println("<td> <input type='text' name='productDesc' readonly value="+pDesc+ "> </td>");
						out.println("<td> <input type='text' name='productPrice' readonly value="+pPrice+ "> </td></tr>");
						out.println("</table></fieldset>");
						out.println("<center><h2>Return To Home Page</h2><a href='/WebTutorial/MainPage'>Home<center>");
						out.println("</body></html>");
			}
			
			if(action.equals("UPDATE"))
			{
						ServletContext sc = request.getServletContext();
						File inFile=null;
						
						switch(updateProductType){
							case "nintendo":
							{
								inFile = new File(sc.getRealPath("Nintendoproduct.txt"));
								break;
							}
							case "microsoft":
							{
								inFile = new File(sc.getRealPath("Microsoftproduct.txt"));
								break;
							}
							case "sony":
							{
								inFile = new File(sc.getRealPath("Sonyproduct.txt"));
								break;
							}
							case "accessories":
							{
								inFile = new File(sc.getRealPath("Accessories.txt"));
								break;
							}
							case "activision":
							{
								inFile = new File(sc.getRealPath("Activisionproduct.txt"));
								break;
							}
							case "electronicarts":
							{
								inFile = new File(sc.getRealPath("EAproducts.txt"));
								break;
							}
							case "taketwo":
							{
								inFile = new File(sc.getRealPath("Taketwoproducts.txt"));
								break;
							}
						}
							if (!inFile.isFile()) {
								System.out.println("Parameter is not an existing file");
								return;
							}
							// Construct the new file that will later be renamed to the original
							// filename.
							File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

							BufferedReader br = new BufferedReader(new FileReader(inFile));
							PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

							String line = null;
									// Read from the original file and write to the new
							// unless content matches data to be removed.
							while ((line = br.readLine()) != null) {

								// Apply StringTokenizer and compare the tag
								StringTokenizer st2 = new StringTokenizer(line, " ");
								String prodId = null;
								while (st2.hasMoreElements()) {
									prodId = (String) st2.nextElement();
									break;
								}
								
								System.out.println("Prod id is :" + prodId);
								if(!prodId.trim().equals(updateProductID)) {
									pw.println(line);
									pw.flush();
								}
								else{
									pw.println(updateProductID+" "+updateProductName+" "+updateProductDesc+ " "+updateProductPrice+" ");
									pw.flush();
								}
							}
							
							pw.close();
							br.close();
							System.gc();
							inFile.deleteOnExit();
							
							if (!inFile.delete()) {
									System.out.println("Could not delete file");
									return;
								}

								// Rename the new file to the filename the original file had.
								if (!tempFile.renameTo(inFile)){
									System.out.println("Could not rename file");
									}
						
								showPage(response, "Item has been Updated successfully");
						
						PrintWriter out = response.getWriter();
						out.println("<html>");
						out.println("<head><title>ADD PRODUCT</title><center><h3>NEW PRODUCT ADDED</h3></center></head>");
						
						out.println("<body style= 'background-color:#F2F2F2'>");
						
						out.println("<fieldset>");
						out.println("<legend>New Product information:</legend>");
						out.println("<table BORDER=1 ALIGN=CENTER>");
						out.println("<tr BGCOLOR='#FFAD00'>");
						out.println("<th>PRODUCT TYPE:<TH> PRODUCT ID:<th> PRODUCT NAME:<th> PRODUCT DESCRIPTION:<th> PRODUCT PRICE:</tr>");
						out.println("<tr><td><input type='text' name='productType' readonly value="+pType+ "></td>");
						out.println("<td> <input type='text' name='productID' readonly value="+pID+ "> </td>");
						out.println("<td> <input type='text' name='productName' readonly value="+pName+ "> </td>");
						out.println("<td> <input type='text' name='productDesc' readonly value="+pDesc+ "> </td>");
						out.println("<td> <input type='text' name='productPrice' readonly value="+pPrice+ "> </td></tr>");
						out.println("</table></fieldset>");
						out.println("<center><h2>Return To Home Page</h2><a href='/WebTutorial/MainPage'>Home<center>");
						out.println("</body></html>");
				
			}
			if(action.equals("DELETE"))
			{
				ServletContext sc = request.getServletContext();
				File inFile=null;
				switch(delProductType){
					
							case "nintendo":
							{
								inFile = new File(sc.getRealPath("Nintendoproduct.txt"));
								break;
							}
							case "microsoft":
							{
								inFile = new File(sc.getRealPath("Microsoftproduct.txt"));
								break;
							}
							case "sony":
							{
								inFile = new File(sc.getRealPath("Sonyproduct.txt"));
								break;
							}
							case "accessories":
							{
								inFile = new File(sc.getRealPath("Accessories.txt"));
								break;
							}
							case "activision":
							{
								inFile = new File(sc.getRealPath("Activisionproduct.txt"));
								break;
							}
							case "electronicarts":
							{
								inFile = new File(sc.getRealPath("EAproducts.txt"));
								break;
							}
							case "taketwo":
							{
								inFile = new File(sc.getRealPath("Taketwoproducts.txt"));
								break;
							}
						}
				
						if (!inFile.isFile()) {
								System.out.println("Parameter is not an existing file");
								return;
							}
							// Construct the new file that will later be renamed to the original
							// filename.
							File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

							BufferedReader br = new BufferedReader(new FileReader(inFile));
							PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

							String line = null;
							// Read from the original file and write to the new
							// unless content matches data to be removed.
							while ((line = br.readLine()) != null) {

								// Apply StringTokenizer and compare the tag
								StringTokenizer st2 = new StringTokenizer(line, " ");
								String prodId = null;
								while (st2.hasMoreElements()) {
									prodId = (String) st2.nextElement();
									break;
								}
								
								System.out.println("Prod id is :" + prodId);
								if (!prodId.trim().equals(delProductID)) {

									pw.println(line);
									pw.flush();
								}
							}
							
							pw.close();
							br.close();
							System.gc();
							inFile.deleteOnExit();
							
							if (!inFile.delete()) {
									System.out.println("Could not delete file");
									return;
								}

								// Rename the new file to the filename the original file had.
								if (!tempFile.renameTo(inFile)){
									System.out.println("Could not rename file");
									}
						
								showPage(response, "Item has been deleted successfully");
			}		
	}
	
	
	protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
		out.println("<a href='/WebTutorial/MainPage'>Index</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
	
	
	public void destroy()	{
      // do nothing.
	}
	
}