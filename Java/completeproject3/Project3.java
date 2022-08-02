/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class, as well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param m           The matrix object that will be filled with random
     *                    samples.
     * @param numSamples  The number of samples to generate when calculating 
     *                    the variance. 
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix m, int numSamples) {
        // You need to fill in this method.
    	double[] determinants = new double[numSamples]; // array for determinant
		for (int i = 0; i < numSamples; i++) {
			m.random(); // creating random matrices 
			determinants[i] = m.determinant(); // storing its determinant in the array
		}
		double sod = 0.0; // sod meaning sum of determinants
		double sods = 0.0; // sods meaning sum of determinats squared
		for (int j = 0; j < numSamples; j++) { // iterating to work out the sum of determinant and sum of determinant squared
			sod += determinants[j]; 
			sods += Math.pow(determinants[j],2);
		}
		double variance = (sods/numSamples) - Math.pow((sod/numSamples) ,2); // calculating variance
		return variance;    
    }
    
    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50. See the formulation for more detail.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
    	Project3 project3 = new Project3();		
		for (int n = 2; n < 51; n++) {
			GeneralMatrix generalmatrix = new GeneralMatrix(n, n); // Creating general matrix
			TriMatrix trimatrix = new TriMatrix(n); // Creating trimatrix
			double variance1 = project3.matVariance(generalmatrix , 15000); 
			double variance2 = project3.matVariance(trimatrix , 150000); 
			System.out.println(n+"\t"+variance1+"\t"+variance2); // Printing out in format n var1 var2 
		}
    }
}