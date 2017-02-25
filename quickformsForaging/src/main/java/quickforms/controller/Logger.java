/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quickforms.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.GregorianCalendar;

/**
 *
 * @author achamney
 */
public class Logger 
{
    static void log(String app, Exception log)
    {
        File f = new File(app+"Log.log");
        BufferedWriter bw;
        try
        {
            bw = new BufferedWriter(new FileWriter(f,true));
            bw.write(new GregorianCalendar().getTime()+" "+log+"\n");
            for(StackTraceElement ste : log.getStackTrace())
            {
               bw.write("    "+ste+"\n"); 
            }
            System.out.println(log);
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    static void log(String app, String log)
    {
        File f = new File(app+"Log.log");
        BufferedWriter bw;
        try
        {
            bw = new BufferedWriter(new FileWriter(f,true));
            bw.write(new GregorianCalendar().getTime()+" "+log+"\n");
            System.out.println(log);
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
