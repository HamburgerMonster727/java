package homework;

import java.io.*;
import java.net.*;

public class Client{
    Socket s;
    DataOutputStream dos;
    DataInputStream dis;
    int clinetnumber;
    public static void main(String[] args){
        new Client(0).start();
    }

    public Client(int n){
        clinetnumber = n;
    }

    public void start(){
        try{
            s = new Socket("127.0.0.1",1720);
            System.out.println("Client->"+s.getLocalSocketAddress());
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            new Thread(new SendThread()).start();//接受来自server的转发过来的信息
            while(true){
                BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
                String msg = buffer.readLine();
                if(msg.length() > 0){
                    dos.writeUTF(s.getLocalSocketAddress()+" : "+ msg + clinetnumber);//发送信息到server
                }
                else{
                    System.out.println("You can not send message with none characters");
                }
            }
        }catch(UnknownHostException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }   
    }
    
    class SendThread implements Runnable{
        private boolean flag = false;

        public void run(){
            flag = true;
            getMsg();
        }

        public void getMsg(){
            try{
                while(flag){
                    String str = dis.readUTF();//接受来自server的信息
                    for(int i = 0;i < str.length()-1;i++){
                        System.out.print(str.charAt(i));
                    } 
                    System.out.println();
                }
            }catch(IOException e){
                e.printStackTrace();
            }    
        }
    }
}