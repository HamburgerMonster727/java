package homework;
public class test7 {
    public static void main(String[] args) {
        String s = "a'10101010101111111110010111001000101010bcd";
        int []start = {0,2,2,5,-1,5};
        int []end = {2,34,38,18,18,100};
        test7 test = new test7();
        for(int i = 0;i < 6;i++){
            int ans = test.binaryStringToInt(s,start[i],end[i]);
            if(ans != 0){
                System.out.println("result: " + ans);
            }
            System.out.println();
        }
    }
    public int binaryStringToInt(String s,int start,int end){
        System.out.println("s:"+s);
        System.out.println("start:" + start);
        System.out.println("end:" + end);
        try{
            if((start < 2 && start > 0) || s.charAt(end - 1) < '0' || s.charAt(end - 1) > '9'){
                throw new NumberFormatException();
            }
            else if(end - start > 32){
                throw new ArithmeticException();
            }
            else if(start < 0 || end < 0 || end < start || end > s.length()){
                throw new StringIndexOutOfBoundsException();
            }
            else{
                int ans = 0;
                int x = 0;
                for(int i = end - 1;i > start;i--){
                    if(s.charAt(i) < '0' || s.charAt(i) > '9'){
                        throw new NumberFormatException();
                    }
                    else{
                        ans = (int) (ans + (s.charAt(i) - '0') * Math.pow(2,x));
                        x++;
                    }
                }
                if(s.charAt(start) == '1'){
                    return 0 - ans;
                }
                else{
                    return ans;
                }
            }
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println("result: string index out of bounds."); 
            return 0;
        }
        catch(ArithmeticException e){
            System.out.println("result: out of bits size of int");
            return 0;
        }
        catch(NumberFormatException e){
            System.out.println("result: incorrect binary number format.");
            return 0;
        }
    }
}
