/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diag;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upper;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lower;
    
    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param N  The dimension of the array.
     */
    public TriMatrix(int N) {
        // You need to fill in this method.
    	super(N,N);
    	diag = new double[N];
    	upper = new double[N-1];
    	lower = new double[N-1];
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        // You need to fill in this method.
    	double number;
		if (diag.length <= i|| diag.length <= j) {
			throw new MatrixException("It's not within the matrix"); // exception for it being out of bounds
		}
		if (i == j) {
			number = diag[i]; // if conditions to decide which array to choose from
		}
		else if (i - 1 == j) {
			number = lower[j];
		}
		else if (i + 1 == j) {
			number = upper[i];
		}
		else {
			number = 0.0;
		}
		return number;
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double val) {
        // You need to fill in this method.
    	if (diag.length <= i|| diag.length <= j) {
			throw new MatrixException("It's not within the matrix"); // exception for being out of bounds
		}
		if (i == j) {
			diag[i] = val;// if conditions to decide which array to store the value
		}		
		else if (i - 1 == j) {
			lower[i] = val;
		}
		else if (i + 1 == j) {
			upper[i] = val;
		}
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
    	TriMatrix decomp = decomp();
		double sum = 1.0;
		for (int i = 0; i < diag.length; i++) {
			sum *= decomp.diag[i]; // determinant is the product of diagonal entries
		}
		return sum;
    }
    
    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix decomp() {
        // You need to fill in this method.
    	TriMatrix decomposed = new TriMatrix(diag.length);
		decomposed.diag[0] = diag[0]; // first entry in the diagonal should stay the same
		for (int i = 0; i < upper.length; i++) {
			decomposed.upper[i] = upper[i]; // u*_i = u_i upper array is the same even after decomposed
		}
		for (int j = 0; j < lower.length; j++) {  // Iterative algorithm to decompose for the lower and diagonal array
			decomposed.lower[j] = lower[j] / decomposed.diag[j]; // lower
			decomposed.diag[j+1] = diag[j+1] - (decomposed.lower[j] * decomposed.upper[j]); // diagonal
		}	
		return decomposed;
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A){
        // You need to fill in this method.
    	if (m != A.m || n != A.n) { 
			throw new MatrixException("The matrices aren't the same dimensions");
		}
    	Matrix added = new GeneralMatrix(m ,n);
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n ; j++) {
    			added.setIJ(i , j , getIJ(i, j) + A.getIJ(i, j));
    		}
    	}
    	return added;
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
        // You need to fill in this method.
    	if (A.m != n) {
    		throw new MatrixException ("Not the correct dimensions for multiplication");
    	}
    	Matrix multiplied = new GeneralMatrix(m , A.n);
    	for (int i = 0; i < m; i++) {
			for (int j = 0; j < A.n; j++) {
				double l = 0;
				for (int k = 0; k < n; k++) {
					l += getIJ(i,k) * A.getIJ(k,j); // adding up the correct multiples for the value
				}
				multiplied.setIJ(i , j , l);
			}
		}
    	return multiplied;
    }
    
    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {
        // You need to fill in this method.
    	Matrix scalarmultiple = new GeneralMatrix(m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				scalarmultiple.setIJ(i, j, a * getIJ(i, j));
			}
		}		
		return scalarmultiple;
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        // You need to fill in this method.
    	for (int i = 0; i < diag.length; i++) {
			diag[i] = Math.random();
		}
		for (int j = 0; j < diag.length - 1; j++) {
			lower[j] = Math.random();
			upper[j] = Math.random();
		}
    }
    
    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
    	TriMatrix m = new TriMatrix(5);
		m.random();
		Matrix k = new GeneralMatrix(5, 5);
		k.random();
		Matrix y = k.multiply(3);
		Matrix hi = k.multiply(3);
		TriMatrix l = new TriMatrix(4);
		l.diag = new double[] {3, 3, 3, 3};
		l.upper = new double[] {-1, -1, -1};
		l.lower = new double[] {-1, -1, -1};
		TriMatrix f = l.decomp();
		System.out.println("Tri-Matrix A:\n"+m.toString());
		System.out.println("Matrix B:\n"+k.toString());
		System.out.println("Matrix B x 10:\n"+y.toString());
		System.out.println("Sum of the two:\n"+ m.add(k));
		System.out.println("Matrix B * 10:\n"+hi);
		System.out.println("BA:\n"+k.multiply(m));
		System.out.println("Matrix C:\n"+l);
		System.out.println("Matrix C Decomposed:\n"+f);
		System.out.println("Determinant of C:\n"+l.determinant());
		System.out.println("Tri-Matrix A:\n"+m.toString());
		System.out.println("Matrix A Decomposed:\n"+m.decomp());
		System.out.println("Determinant of A:\n"+m.determinant());
    }
}