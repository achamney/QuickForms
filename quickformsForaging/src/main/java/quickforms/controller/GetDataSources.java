/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quickforms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author achamney
 */
@WebServlet(name = "GetDataSources", urlPatterns = {"/getDataSources"})
public class GetDataSources extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Database db = null;
        PrintWriter out = response.getWriter();
        try{
            InitialContext cxt = new InitialContext(); 
            NamingEnumeration<NameClassPair> ne = cxt.list("java:/comp/env/jdbc");
                        
            while (ne.hasMore()) {
                NameClassPair nc = (NameClassPair)ne.next();
                System.out.println(nc.getName());
                
                out.write(". <div class=\"border\">");
                out.write(nc +"<br />");
                out.write("<h1>"+nc.getName() +"</h1>");
                DataSource  ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/"+nc.getName());
                db = new Database(ds);
                try{
                    db.testConnection(nc.getName());
                    out.write("<div class='success'>Connection Succeeded!</div><br />");
                }
                catch(Exception e){
                    out.write("<div class='error'>Connection Failed!</div><br />"+e);
                }
                out.write("</div>");
            }
        }
        catch(Exception e)
        {
            Logger.log("quickforms", e);
            e.printStackTrace();
            db.disconnect();
        }
        out.close();
    }

}
