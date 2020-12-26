package homework;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server{
    List<ClientThread> clients = new ArrayList<ClientThread>();
    public static void main(String[] args){
        new Server().start();
    }
    
    public void start(){
        try{
            boolean flag = false;
            ServerSocket ss = new ServerSocket(1720);
            flag = true;
            System.out.println("Server->java Server");
            System.out.println("Initializing Port...");
            System.out.println("Listening...");
            while(flag){
                Socket s = ss.accept();
                ClientThread currentClient = new ClientThread(s);
                System.out.println("Connect to client: " + s.getRemoteSocketAddress());
                clients.add(currentClient);//加入表中
                new Thread(currentClient).start();//处理新加入的client
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    class ClientThread implements Runnable{
        private Socket s;
        private DataInputStream dis;
        private DataOutputStream dos;
        private String str;
        private boolean flag = false;
        
        ClientThread(Socket s){
            this.s = s;
            flag = true;
        }
        
        public void run(){
            try{   
                while(flag){
                    dis = new DataInputStream(s.getInputStream());
                    str = dis.readUTF();  //接受来自client的信息
                    for(int i = 0; i<clients.size(); i++){
                        ClientThread c = clients.get(i);
                        if(str.charAt(str.length()-1)-'0' != i)
                            c.sendMsg(str);  //转发信息到其他的client
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        public void sendMsg(String str){
            try{
                dos = new DataOutputStream(this.s.getOutputStream());
                dos.writeUTF(str); //发送信息     
            }catch(IOException e){
                e.printStackTrace();
            } 
        }  
    } 
}