/*
 * PROJECT I: Circle.java
 *
 * This file contains a template for the class Circle. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Point class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Circle {

    /*
     * Here, you should define private variables that represent the radius and
     * centre of this particular Circle. The radius should be of type double,
     * and the centre should be of type Point.
     */
	
	private double r;
	private Point A;
	
    // =========================
    // Constructors
    // =========================
    /**
     * Default constructor - performs no initialization.
     */
    public Circle() {
        // This method is complete.
    }
    
    /**
     * Alternative constructor, which sets the circle up with x and y
     * co-ordinates representing the centre, and a radius. Remember you should
     * not store these x and y co-ordinates explicitly, but instead create a
     * Point to hold them for you.
     *
     * @param xc   x-coordinate of the centre of the circle
     * @param yc   y-coordinate of the centre of the circle
     * @param rad  radius of the circle
     */
    public Circle(double xc, double yc, double rad) {
        // You need to fill in this method.
    	setCenter(xc , yc);
    	setRadius(rad);
    	
    }

    /**
     * Alternative constructor, which sets the circle up with a Point
     * representing the centre, and a radius.
     *
     * @param center  Point representing the centre
     * @param rad     Radius of the circle
     */
    
    public Circle(Point center, double rad) {
        // You need to fill in this method.
    	setCenter(center);
    	setRadius(rad);
    }

    // =========================
    // Setters and Getters
    // =========================

    /**
     * Setter - sets the co-ordinates of the centre.
     *
     * @param xc  new x-coordinate of the centre
     * @param yc  new y-coordinate of the centre
     */   
    public void setCenter(double xc, double yc) {
        // You need to fill in this method.
    	this.A = new Point(xc , yc);
    }

    /**
     * Setter - sets the centre of the circle to a new Point.
     *
     * @param p  A Point representing the new centre of the circle.
     */   
    public void setCenter(Point p) {
        // You need to fill in this method.
    	this.A = p;
    }
    
    /**
     * Setter - change the radius of this circle.
     *
     * @param rad  New radius for the circle.
     */   
    public void setRadius(double rad) {
        // You need to fill in this method.
    	this.r = rad;
    }
    
    /**
     * Getter - returns the centre of this circle.
     *
     * @return The centre of this circle (a Point).
     */   
    public Point getCenter(){
        // You need to fill in this method.
    	return A;
    }

    /**
     * Getter - extract the radius of this circle.
     *
     * @return The radius of this circle.
     */   
    public double getRadius(){
        // You need to fill in this method.
    	return r;
    }

    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Circle.
     *
     * @return A String of the form: "[Ax,Ay], r=Radius" where Ax and Ay are
     *         numerical values of the coordinates, and Radius is a numerical
     *         value of the radius.
     */
    public String toString() {
        // You need to fill in this method.
    	return "[" + getCenter().getX() + "," + getCenter().getY() + "], r=" + getRadius();
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    /**
     * Similar to the equals() function in Point. Returns true if two Circles
     * are equal. By this we mean:
     * 
     * - They have the same radius (up to tolerance).
     * - They have the same centre (up to tolerance).
     * 
     * Remember that the second test is already done in the Point class!
     *
     * @return true if the two circles are equal.
     */
    public boolean equals(Circle c) {
        // You need to fill in this method.
    	if(Math.abs(c.getRadius() - getRadius())<= Point.GEOMTOL && getCenter().equals(c.getCenter()))
    		return true;
    	else 
    		return false;
    }
    
    // -----------------------------------------------------------------------
    // Do not change the method below! It is essential for marking the
    // project, and you may lose marks if it is changed.
    // -----------------------------------------------------------------------

    /**
     * Compare this Circle with some Object, using the test above.
     *
     * @param obj  The object to compare with.
     * @return true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        // This method is complete.
        
        if (obj instanceof Circle) {
            boolean test = false;
            Circle C = (Circle)obj;
            
            test = this.equals(C);

            return test;
        } else {
            return false;
        }
    }

    // ======================================
    // Implementors
    // ======================================
    
    /**
     * Computes and returns the area of the circle.
     *
     * @return  Area of the circle
     */
    public double area() {
        // You need to fill in this method.
    	return getRadius() * getRadius() * Math.PI;
    }

    /**
     * Tests whether this circle overlaps/touches/is disjoint with another
     * Circle, as set out in the project formulation.
     *
     * @return An integer describing the overlap with C:
     *         0 - disjoint; 1 - overlaps; 2 - touches; 3 - identical.
     */
    public int overlap(Circle C) {
        // You need to fill in this method.
    	int t = 0;
    	if (equals(C))
    		t = 3;
    	if (Math.abs(getCenter().distance(C.getCenter()) - (C.getRadius() + getRadius())) <= Point.GEOMTOL)
    		t = 2;
    	if (getCenter().distance(C.getCenter()) < C.getRadius() + getRadius())
    		t = 1;
    	if (getCenter().distance(C.getCenter()) > C.getRadius() + getRadius())
    		t = 0;
		return t;
    }


    // =======================================================
    // Tester - test methods defined in this class
    // =======================================================
    
    public static void main(String args[]) {
        // You need to fill in this method.
    	Circle c1 = new Circle( 9.501293e-01,2.311385e-01,1.213685e+00);
    	Circle c2 = new Circle(9.218130e-01,7.382072e-01,3.525323e-01);
    	System.out.println(c1.overlap(c2));
    }
}
