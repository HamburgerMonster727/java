package homework;
public class test4{
    public static void main(String args[]){
        Shape shape1 = new Circle(3.0);
        Shape shape2 = new Cylinder(3.0, 4.0);
        double sumAreaOfShape = shape1.getArea() + shape2.getArea();
        System.out.println("Sum area of shape is: " + sumAreaOfShape);
        System.out.println("The name of shape1 is: " + shape1.getName());
        System.out.println("The name of shape2 is: " + shape2.getName());
        System.out.println("");
        shape1.showDescription();;
        shape2.showDescription();;
    }
}         

abstract class Shape {
    // fields
    protected String name;
    // methods
    public Shape(){}
    public Shape(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // abstract method
    public abstract double getArea();
    public abstract void showDescription();
}

class Circle extends Shape {
    protected String name;
    protected double radius;
    public Circle(){
        name = "Circle";
    }
    public Circle(double _radius){
        name = "Circle";
        radius = _radius;
    }
    public double getRadius(){
        return radius;
    }
    public double getArea(){
        return radius*radius*Math.PI;
    }
    public double getPerimeter(){
        return 2*Math.PI*radius;
    }
    public void showDescription(){
        System.out.println("Shape: " + getName());
        System.out.printf("radius: %.4f\n",getRadius());
        System.out.printf("Area: %.4f\n",getArea());
        System.out.printf("Perimeter: %.4f\n",getPerimeter());
        System.out.println();
    }
    public String getName(){
        return name;
    }
}

class Cylinder extends Shape{
    protected String name;
    protected double radius;
    protected double height;
    public Cylinder(){
        name = "Cylinder";
    }
    public Cylinder(double _radius,double _height){
        name = "Cylinder";
        radius = _radius;
        height = _height;
    }
    public double getHeight(){
        return height;
    }
    public void setHeight(double _height){
        height = _height;
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double _radius){
        radius = _radius;
    }
    public double getArea(){
        return Math.PI*radius*radius*2+Math.PI*2*radius*height;
    }
    public double getVolume(){
        return Math.PI*radius*radius*height;
    }
    public void showDescription(){
        System.out.println("Shape: " + getName());
        System.out.printf("radius: %.4f\n",getRadius());
        System.out.printf("height: %.4f\n",getHeight());
        System.out.printf("Area: %.4f\n",getArea());
        System.out.printf("Volume: %.4f\n",getVolume());
        System.out.println();
    }
    public String getName(){
        return name;
    }
}







