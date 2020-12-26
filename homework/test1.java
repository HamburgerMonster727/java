package homework;
import java.util.Scanner;
public class test1{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String array = input.nextLine();
        int i = 0;
        int[] num = new int[100];
        int p = 0;
        char[] str = new char[100];
        while(i < array.length()){
            int x = i;
            int n = 1;
            while(x <= array.length() - 2 && array.charAt(x) == array.charAt(x+1)){
                x = x + 1;
                n = n + 1;
            }
            num[p] = n;
            str[p] = array.charAt(x);
            i = x + 1;
            p++;
        }
        int sum = 0;
        for(int k = 0;k < p;k++){
            sum = sum + num[k];
        }
        System.out.print(sum+","+'"');
        for(int k = 0;k < p;k++){
            if(num[k] == 1){
                System.out.print(str[k]);
            }
            else{
                System.out.print(str[k]);
                System.out.print(num[k]);
            }
        }
        System.out.print('"');
    }
}