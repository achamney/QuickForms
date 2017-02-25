/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quickforms.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
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
@WebServlet(name = "executeQuery", urlPatterns = {"/executeQuery"})
public class ExecuteQuery extends HttpServlet {

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
        
        String application = request.getParameter("app");
        String queryLabel = request.getParameter("queryLabel");
        String queryParameters = request.getParameter("params");
        StringTokenizer st = new StringTokenizer(queryParameters," ");
        HashMap<String,String> queryParametersMap = new HashMap<String,String>();
        while(st.hasMoreElements())
        {
            String[] next = st.nextToken().split("=");
            if(next.length==2){
                queryParametersMap.put(next[0],next[1]);
            }
        }
        PrintWriter out = response.getWriter();
        Database db = null;
        try{
            InitialContext cxt = new InitialContext(); 
            DataSource  ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/"+application);
            db = new Database(ds);
            String jsonString = db.executeQuery(application,queryLabel,queryParametersMap);
            out.println(jsonString);
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
