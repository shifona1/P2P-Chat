/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package italk_cd;

import java.io.IOException;
import java.net.*;
import java.io.*;

/**
 *
 * @author shifona
 */
public class Server {
    
    public static int portno=8888;
    private BufferedReader br; 
    private BufferedOutputStream bos;
    String name="";
    Server(String n)
    {
        name=n;
    }
    
    
    
   void startServer() throws IOException
   {
       
       ServerSocket server=new ServerSocket(portno);
       Socket socket= server.accept();
       new Thread(){
           public void run()
           {
               Connection conn=new Connection(socket,name);
               chatWindow.chat(conn);
           }
//       chatWindow chatwindow=new chatWindow();
//       conn.receive(chatwindow);
       }.start();
      
      
       
   }

    
    
}
