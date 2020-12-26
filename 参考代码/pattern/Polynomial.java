package pattern;
public class Polynomial {
    double a,b,c;
    Polynomial(double a_,double b_,double c_){
        a = a_;
        b = b_;
        c = c_;
    }
    void setA(double n){
        a = n;
    }
    void setB(double n){
        b = n;
    }
    void setC(double n){
        c = n;
    }
    void showPolynomial(){
        System.out.println("F(x)="+a+"x^2+"+b+"x+"+c);
    }
    double getY(double x){
        double ans = a*x*x+b*x+c;
        return ans;
    }
    boolean hasSolution(double y){
        if(b*b-4*a*(c-y) >= 0)
            return true;
        else
            return false;
    }
    void showSolution(double y){
        System.out.print("solution:");
        double x1,x2;
        x1 = (-b+Math.sqrt(b*b-4*a*(c-y)))/(2*a);
        x2 = (-b-Math.sqrt(b*b-4*a*(c-y)))/(2*a);
        if(x1 == 0)
            System.out.println(x2);
        else
            System.out.println(x1+","+x2);
    }
    public static void main(String[] args){
        Polynomial f = new Polynomial(10,9,8);
        Polynomial f1 = new Polynomial(1, 0, 0);
        Polynomial f2 = new Polynomial(1, 0, 2);
        int[] tempArray = {0, 1, 3};
        // Set Method Test
        System.out.println("Set Method Test");
        f.showPolynomial();
        f.setA(-10);
        f.showPolynomial();
        f.setB(-1908);
        f.showPolynomial();
        f.setC(190908.87);
        f.showPolynomial();
        // F(x) Calculation Test
        System.out.println("\nCalculation Test");
        f1.showPolynomial();
        f2.showPolynomial();
        System.out.println("");
        for(int i = 0; i < tempArray.length;i++) {
            System.out.println("f1(" + String.valueOf(tempArray[i]) +
        ")=" + String.valueOf(f1.getY(tempArray[i])));
            System.out.println("f2(" + String.valueOf(tempArray[i]) +
        ")=" + String.valueOf(f2.getY(tempArray[i])));
        }
        // F(x)=0 Solution Test
        System.out.println("\nSolution Test");
        for(int i = 0; i < tempArray.length;i++){
            System.out.println("y = " + String.valueOf(tempArray[i]));
            boolean result1 = f1.hasSolution(tempArray[i]);
            boolean result2 = f2.hasSolution(tempArray[i]);
            System.out.print("f1:");
            System.out.println(result1);
            if(result1){
                f1.showSolution(tempArray[i]);
            }
            System.out.print("f2:");
            System.out.println(result2);
            if(result2){
                f2.showSolution(tempArray[i]);
            }
            System.out.println(""); 
        }
    }
} 
