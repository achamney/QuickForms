/*  Copyright (c) 2014 Austin Chamney, achamney@gmail.com.
    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package quickforms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.naming.InitialContext; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import quickforms.controller.Database;
import quickforms.controller.Logger;
 
/**
 *
 * @author achamney
 */
@WebServlet(name = "PutFact", urlPatterns = {"/putFact"})
public class PutFact extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
	
	 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
	   Map<String,String[]> inParams = request.getParameterMap();
       Map<String,String[]> params = new HashMap<String,String[]>(inParams);//inParams is read only
	  
	   System.out.println("Saving Fact");
       response.setContentType("text/html;charset=UTF-8");
       String application = request.getParameter("app");
       String ipAddress = request.getHeader("X-FORWARDED-FOR");
       if (ipAddress == null) {
       	   ipAddress = request.getRemoteAddr();
       }
      
       Database db = null;
       String json = null;
       PrintWriter out = response.getWriter();
       
        try{
            InitialContext cxt = new InitialContext(); 
            DataSource  ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/"+application);
            db = new Database(ds);
            json = db.putFactProcess(params, db, ipAddress);
            out.print(json);
            
        }
        catch(Exception e)
        {
            Logger.log(application, e);
            out.append(e.toString());
            db.disconnect();
			e.printStackTrace();
        }
       
       out.close();
    }
    
   
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String debug = request.getParameter("debug");
        if(debug != null)
        {
            doPost(request,response);
        }
    }
}