package homework;

import java.util.ArrayList;
import java.util.List;

class TicketWindow implements Runnable{
    private int windowNumber;
    private List<Integer> ticketList = new ArrayList<Integer>();

    public TicketWindow(int number,List<Integer> list){
        windowNumber = number;
        ticketList = list;
    }

    public void salesTickets(){
        synchronized(this){   
            if (ticketList.size() > 0){   
                System.out.println("Window "+Thread.currentThread().getName()+":Ticket "+ ticketList.get(0) +" is sold.");
                ticketList.remove(0);
            }
        }
    }

    public void run(){
        while(ticketList.size() > 0){    
            salesTickets();   
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class TicketWindowTest {
    public static void main(String[] args){
        List<Integer> ticketlist = new ArrayList<Integer>();
        for(int i = 1;i <= 20;i++){
            ticketlist.add(i);
        }
        TicketWindow window = new TicketWindow(1,ticketlist);
        Thread t1 = new Thread(window,"1");
        Thread t2 = new Thread(window,"2");
        Thread t3 = new Thread(window,"3");
        t1.start();
        t2.start();
        t3.start();
    }
}
