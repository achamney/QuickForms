/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quickforms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import quickforms.entity.LookupPair;

/**
 *
 * @author achamney
 */
@WebServlet(name = "GetFieldSelection", urlPatterns = {"/getFieldSelection"})
public class GetFieldSelection extends HttpServlet {


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
        String factTable = request.getParameter("factTable");
        String field = request.getParameter("field"); 
        String updateId = request.getParameter("updateid"); 
        Database db = null;
        try{
            InitialContext cxt = new InitialContext(); 
            DataSource  ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/"+application);
            db = new Database(ds);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
       
        PrintWriter out = response.getWriter();
        try{
            List<LookupPair> lookup = db.getLookupData(application,field);
            JSONObject updateRow = null;
            if(!updateId.equals("null") && !updateId.equals("") ){
                HashMap<String,String> params = new HashMap<String,String>();
                params.put("updateid",updateId);
                String json = db.getFactRow(application,factTable, updateId);
                JSONParser parser = new JSONParser();
                JSONArray wrapper = new JSONArray();
                try{
                    wrapper = (JSONArray) parser.parse(json);
                }
                catch(Exception e){}
                updateRow = (JSONObject) wrapper.get(0);
                Logger.log(application,"updateRow: "+updateRow.toString());
            }
            int i=0;
            String jsonField = "[";
            for(LookupPair id : lookup)
            {
                String selected = "";
                if(updateId.equals("null") || updateId.equals("") ){
                    if(i==0){
                        selected = "selected";
                    }
                }
                else
                {
                    if(updateRow.get(field).equals(id.left))
                    {
                        selected = "selected";
                    }
                }
                jsonField += "{";
                jsonField += "\"id\":\""+id.left+"\",";
                jsonField += "\"selected\":\""+selected+"\",";
                jsonField += "\"label\":\""+id.right+"\"";
                jsonField += "},";
                //out.println("<option  value= "+id+" "+selected+">"+lookup.get(id) +"</option>");
                i++;
            }
            jsonField = jsonField.substring(0, jsonField.length()-1)+"]";
            out.append(jsonField); 
        }
        catch(Exception e)
        {
            Logger.log(application,e);
            out.append(e.toString());
            db.disconnect();
        }
        out.close();
    }

}
