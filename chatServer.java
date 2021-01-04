package homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class chatServer{
	List<Socket> clients=new ArrayList<Socket>();
	public static void main(String[] args){
		new chatServer();
	}
	public chatServer(){
	    ServerSocket server = null;
	    //BufferedReader in = null;
	    //PrintWriter out = null;
        try {
            BufferedWriter fout = new BufferedWriter(new FileWriter("Chatrecord.txt"));
            fout.close();
            server = new ServerSocket(9999);
            clients = new ArrayList<Socket>();
            System.out.println("聊天室已建立，等待成员加入...");
            while(true){
            	Socket accept = server.accept();
                clients.add(accept);
                Mythread mythread = new Mythread(accept);
                mythread.start();
            }
            
        }
        catch (IOException e) {}
    }
    class Mythread extends Thread {
        Socket accept;
        private BufferedReader in;
        private PrintWriter out;
        public String msg;
        public Mythread(Socket s) {
            accept = s;
        }
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                while (true) {
                	msg = in.readLine();
                	if(!msg.equals("")){
                		sendMsg();
                	}
                	else{
                    	out = new PrintWriter(accept.getOutputStream(), true);
                        out.flush();
                	}
                }
            } 
            catch (Exception e) {
            	//怎么处理此处的异常
            }
        }
        public void sendMsg() {
            try {
                System.out.println(msg);
                for (int i = clients.size() - 1; i >= 0; i--) {
                    out = new PrintWriter(clients.get(i).getOutputStream(), true);
                    out.println(msg);
                    out.flush();
                }
            }
            catch (Exception e) {}
        }
    }
}