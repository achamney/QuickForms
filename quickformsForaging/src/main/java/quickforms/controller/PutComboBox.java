/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author achamney
 */
@WebServlet(name = "PutComboBox", urlPatterns = {"/putComboBox"})
public class PutComboBox extends HttpServlet {

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
       Map<String,String[]> params = request.getParameterMap();
       System.out.println("Saving ComboBox");
       response.setContentType("text/html;charset=UTF-8");
       String application = request.getParameter("app");
       String lookupTable = request.getParameter("lookupTable");
       String value = request.getParameter("value");
       Database db = null;
       String json = null;
       PrintWriter out = response.getWriter();
        try{
            InitialContext cxt = new InitialContext(); 
            DataSource  ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/"+application);
            db = new Database(ds);
            
            db.putComboBox(params,application,lookupTable,value); 
            
            out.println(json);
        }
        catch(Exception e)
        {
            Logger.log(application, e);
            out.append(e.toString());
            db.disconnect();
        }
       
       out.close();
    }

}
