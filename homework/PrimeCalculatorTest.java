package homework;
public class PrimeCalculatorTest {
    public static void main(String[] args){
        int result = 0;
        PrimeCalculator pc1 = new PrimeCalculator(1, 100000);
        PrimeCalculator pc2 = new PrimeCalculator(100001, 200000);
        PrimeCalculator pc3 = new PrimeCalculator(200001, 300000);
        PrimeCalculator pc4 = new PrimeCalculator(300001, 400000);
        Thread t1 = new Thread(pc1);
        Thread t2 = new Thread(pc2);
        Thread t3 = new Thread(pc3);
        Thread t4 = new Thread(pc4);
        result = pc1.getAmount()+pc2.getAmount()+pc3.getAmount()+pc4.getAmount();
        System.out.println("The amount of prime number from 1 to 400000 is : " + String.valueOf(result)); 
        t1.start(); 
        t2.start(); 
        t3.start();
        t4.start();
        try{ 
            t1.join(); 
            t2.join();
            t3.join(); 
            t4.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        result = pc1.getAmount()+pc2.getAmount()+pc3.getAmount()+pc4.getAmount();
        System.out.println("The amount of prime number from 1 to 400000 is : " + String.valueOf(result));
    } 
}

class PrimeCalculator implements Runnable{
    int lowerBound;
    int upperBound;
    int amount;
    boolean hasBeenCalcualated;

    public PrimeCalculator(int lower,int upper){
        lowerBound = lower;
        upperBound = upper;
        amount = -1;
        hasBeenCalcualated = false;
    }
    public void run(){
        amount = 0;
        for(int i = lowerBound;i <= upperBound;i++){
            boolean flag = true;
            if(i == 1){
                flag = false;
            }
            for(int x = 2;x < i;x++){
                if(i%x == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                amount++;
            }
        }
        hasBeenCalcualated = true;
        if(hasBeenCalcualated){
            Thread.yield();
        }    
    }
    public int getAmount(){
        return amount;
    }
}


