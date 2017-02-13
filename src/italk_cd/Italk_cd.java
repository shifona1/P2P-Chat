/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package italk_cd;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author shifona
 */
public class Italk_cd extends Thread{
    /**
     * @param args the command line arguments
     */
    static private String host_name="172.16.27.22";
    static private int port=1234;
    static private BufferedReader br; 
    static private BufferedOutputStream bos;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        Socket socket=new Socket(host_name,port);
        Connection conn=new Connection(socket, "");
        LoginScreen.login(conn);
               
    }

     public void chatstart(String name,String host)
     {
        
   
//            String host=JOptionPane.showInputDialog("Please Enter the IP Address Of the Server");
//            int port=Integer.parseInt(JOptionPane.showInputDialog("Please Enter the Port Number"));
            
            Client client=new Client(host,Server.portno,name);
            client.connect();
            
            
        
       
    }
    String name;

    public Italk_cd(String name) {
        this.name = name;
    } 
    public Italk_cd() {
    }
    
    public void run()
    {
        try {
            (new Server(name)).startServer();
        } catch (IOException ex) {
            Logger.getLogger(Italk_cd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void chat(Socket socket,String name) {
         
        try {
            
            bos= new BufferedOutputStream(socket.getOutputStream());
            bos.write((name+" : ").getBytes());
        } catch (IOException ex) {
            Logger.getLogger(Italk_cd.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
//To change body of generated methods, choose Tools | Templates.
    
    }
    
    
    
    
    
}
