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

/**
 *
 * @author shifona
 */
public class Client {
    int port;
    String host;
    String name;
    private BufferedReader br; 
    private BufferedOutputStream bos;
    
    Client(String h,int p,String n)
    {
        host=h;
        port=p;
        name=n;
    }
    
    boolean connect() 
    {
        try {
            System.out.println("host: "+host +"port :"+port);
            Socket socket=new Socket(host,port);
            Connection conn=new Connection(socket,name);
            chatWindow.chat(conn);
//            chatWindow chatwindow=new chatWindow();
//            conn.receive(chatwindow);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
