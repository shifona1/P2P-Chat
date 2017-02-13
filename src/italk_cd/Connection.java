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
public class Connection {
    Socket socket;
    String name;
    chatWindow window;
    static private BufferedReader br; 
    static private BufferedOutputStream bos;

    Connection(Socket sckt,String n) {
        socket=sckt;
        name=n;
    }

    
    
    
    void send(String msg)
    {
        try {
            
            bos= new BufferedOutputStream(socket.getOutputStream());
            String output=(name+": " + msg+"\n");
            bos.write((output).getBytes());
            bos.flush();
            sendReceivedMsgToUI(output);
            
        } catch (IOException ex) {
            Logger.getLogger(Italk_cd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void sendReceivedMsgToUI(String msg){
        window.updatereceivedmsg(msg);
    }
    
    void receive(chatWindow chat)
    {
        window=chat;
        new Thread() {
            public void run(){
                String userIn="";
                String temp;
                try {
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             
                    while( (userIn=br.readLine())!=null)
                    {
                       
                        sendReceivedMsgToUI(userIn);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }.start();
    }
    
    void sendToserver(String msg)
    {
         try {
            
            bos= new BufferedOutputStream(socket.getOutputStream());
            String output=msg+"\n";
            bos.write((output).getBytes());
            bos.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Italk_cd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public String receivefromserver(String type)
   {
        try {
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String userin="",temp;
            System.out.println("waiting");
            if((temp=br.readLine())!=null)
                userin+=temp;
            System.out.println("userin = "+userin);
            if(type.equals("create"))
            {
                if(userin.equals("true"))
                return "true";
                else return "";
            }    
            else if(type.equals("login"))
            {
                if(!userin.equals("false"))
                return userin;
                else 
                    return "false";
            }
            else if(type.equals("users"))
                return userin;
            else if(type.equals("addfriend"))
                return userin;
            else if(type.equals("friends"))
                return userin;
            else if(type.equals("chat"))
                return userin;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
       
   }

    String addTolist(String newfriend) {
            sendToserver("4:"+ name+" : "+newfriend);
            return receivefromserver("addfriend");
    }

    String getFriendList() {
        sendToserver("5:"+ name); //To change body of generated methods, choose Tools | Templates.
        String friends=receivefromserver("friends");
        return friends;
    }

    String getIp(String chatfriend) {
        sendToserver("6:"+chatfriend); //To change body of generated methods, choose Tools | Templates.
        String ip=receivefromserver("chat");
        return ip;
    }
}  


