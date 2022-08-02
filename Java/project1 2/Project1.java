import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The Results() method will perform the tasks
 *    laid out in the project formulation.
 */
public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names of the variables below! This is where you will
    // store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int    circleCounter; // Number of non-singular circles in the file.
    public int    posFirstLast;  // Indicates whether the first and last
                                 // circles overlap or not.
    public double maxArea;       // Area of the largest circle (by area).
    public double minArea;       // Area of the smallest circle (by area).
    public double averageArea;   // Average area of the circles.
    public double stdArea;       // Standard deviation of area of the circles.
    public double medArea;       // Median of the area.
    public int    stamp=472143;
    // -----------------------------------------------------------------------
    // You may implement - but *not* change the names or parameters of - the
    // functions below.
    // -----------------------------------------------------------------------

    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {
        // This method is complete.
    }
    double x1,y1,r1;
    Circle firstnonsingular,lastnonsingular;
    double maxR = Double.MIN_VALUE;
    double minR = Double.MAX_VALUE;
    /**
     * Results function. It should open the file called FileName (using
     * Scanner), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName){
        // You need to fill in this method.
    	try {
    	      Scanner data = new Scanner(new BufferedReader(new FileReader(fileName)));
      		  ArrayList <Circle> circles1 = new ArrayList<Circle>();
    	while(data.hasNext()) {
       		x1 = data.nextDouble();
    		y1 = data.nextDouble();
    		r1 = data.nextDouble();
    		Circle c1 = new Circle(x1, y1 , r1); 
    		circles1.add(c1);
    		if (r1 > Point.GEOMTOL)
    			circleCounter ++;
    		if (r1 < minR && r1 > Point.GEOMTOL)
    			minR = r1;
    		if (r1 > maxR && r1 > Point.GEOMTOL)
    			maxR = r1;
    	}
    	Circle [] circles = new Circle [circles1.size()];
    	circles = circles1.toArray(circles);
    	averageArea = averageArea(circles);
    	maxArea = Math.PI * maxR * maxR;
    	minArea = Math.PI * minR * minR;
    	medArea = medianArea(circles);
    	posFirstLast = overlapper(circles);
    	stdArea = areaStandardDeviation(circles);
    	} catch (Exception e) {
    	System.err.println("An error has occured. See below for details");
    	e.printStackTrace();
    	}
    	
    }
    
    /**
     * A function to calculate the avarage area of circles in the array provided. 
     *
     * @param circles  An array if Circles
     */
    public double averageArea(Circle[] circles) {
      // You need to fill in this method and remove the return 0.0 statement.
    	double [] areaCircles = new double[circles.length];
    	for (int j = 0 ; j < circles.length ; j++)
    		areaCircles [j] = circles[j].area();
    	double sum = 0;
    	for (int i = 0 ; i < areaCircles.length; i++)
    		sum += areaCircles[i];
      return (sum/circleCounter);
    }
    
    /**
     * A function to calculate the standart deviation of areas in the circles in the array provided. 
     *
     * @param circles  An array of Circles
     */
    public double areaStandardDeviation(Circle[] circles) {
    // You need to fill in this method and remove the return 0.0 statement.
    	double [] areaCircles = new double[circles.length];
    	for (int j = 0 ; j < circles.length ; j++)
    		areaCircles [j] = circles[j].area();
    	double sumstd = 0;
    	for (int i = 0 ; i < areaCircles.length; i++)
    		sumstd += areaCircles[i] * areaCircles[i];
      return (Math.sqrt((sumstd/circleCounter) - (averageArea * averageArea)));//do this
    }
    
    public double medianArea(Circle[] circles) {
    	double [] areaCircles = new double[circles.length];
    	for (int j = 0 ; j < circles.length ; j++)
    		if (circles[j].area() >= Point.GEOMTOL)
    			areaCircles [j] = circles[j].area();
    	Arrays.sort(areaCircles);
    	if (areaCircles.length % 2 == 0)
    	    return ((double) areaCircles[(int)(areaCircles.length/2.0)] + (double)areaCircles[(int)(areaCircles.length/2 - 1)])/2;
    	else
    	    return (double) areaCircles[(int) (areaCircles.length/2.0)];
    }
    
    public int overlapper(Circle[] circles) {
    	for (int i = 0 ; i < circles.length ; i++)
    		if (circles[i].getRadius() >= Point.GEOMTOL)
    			lastnonsingular = circles[i];
    	for (int i = circles.length-1 ; i >= 0 ; i--)
    		if (circles[i].getRadius() >= Point.GEOMTOL)
    			firstnonsingular = circles[i];
    	return (lastnonsingular.overlap(firstnonsingular));
    }
    	
  
    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]){
        // You need to fill in this method.
    	Project1 p1 = new Project1();
    	p1.results("Project1.data");
    	System.out.println(p1.circleCounter);
    	System.out.println(p1.posFirstLast);
    	System.out.println(p1.maxArea);
    	System.out.println(p1.minArea);
    	System.out.println(p1.averageArea);
    	System.out.println(p1.stdArea);
    	System.out.println(p1.medArea);
    }
}

