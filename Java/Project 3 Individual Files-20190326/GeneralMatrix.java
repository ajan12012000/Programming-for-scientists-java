/*
 * PROJECT III: GeneralMatrix.java
 *
 * This file contains a template for the class GeneralMatrix. Not all methods
 * are implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

import java.util.Arrays;

public class GeneralMatrix extends Matrix {
    /**
     * This instance variable stores the elements of the matrix.
     */
    private double[][] data;

    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param m  The first dimension of the array.
     * @param n  The second dimension of the array.
     */
    public GeneralMatrix(int m, int n) throws MatrixException {
        // You need to fill in this method.
    	super(m,n);
    	data = new double [m][n];
    	
    }

    /**
     * Constructor function. This is a copy constructor; it should create a
     * copy of the matrix A.
     *
     * @param A  The matrix to create a copy of.
     */
    public GeneralMatrix(GeneralMatrix A) {
        // You need to fill in this method.
    	super(A.m,A.n);
    	data = new double [A.m][A.n];
    	for (int i = 0; i < A.m ; i++) {
    		for (int j = 0; j < A.n ; j++) {
    			data[i][j] = A.data[i][j];
    		}
    	}
    	
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
    	if (m <= i || n <= j) { 
			throw new MatrixException("It's not within the Matrix");
		}
    	double number = data[i][j];
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
    	if (m <= i || n <= j) { 
			throw new MatrixException("It's not within the Matrix");
		}
    	data[i][j] = val;
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
    	double[] d = new double [1];
		GeneralMatrix decomp = decomp(d); 
		double sum = 1.0;
		for (int i = 0; i < m; i++) {
			sum *= decomp.data[i][i]; // determinant is the product of diagonal entries
		}
		sum *= d[0]; // applying the sign 
		return sum;
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A) {
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
    	if (n != A.m) {
    		throw new MatrixException ("Not the correct dimensions for multiplication");
    	}
    	Matrix multiplied = new GeneralMatrix(m , A.n);
    	for (int i = 0; i < m; i++) {
			for (int j = 0; j < A.n; j++) {
				double l = 0;
				for (int k = 0; k < n; k++) {
					l += getIJ(i,k) * A.getIJ(k,j); //adding up the multiples for the value
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
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				setIJ(i, j, Math.random()); //setting random number between 0 and 1
			}
		}
    }

    /**
     * Returns the LU decomposition of this matrix; i.e. two matrices L and U
     * so that A = LU, where L is lower-diagonal and U is upper-diagonal.
     * 
     * On exit, decomp returns the two matrices in a single matrix by packing
     * both matrices as follows:
     *
     * [ u_11 u_12 u_13 u_14 ]
     * [ l_21 u_22 u_23 u_24 ]
     * [ l_31 l_32 u_33 u_34 ]
     * [ l_41 l_42 l_43 l_44 ]
     *
     * where u_ij are the elements of U and l_ij are the elements of l. When
     * calculating the determinant you will need to multiply by the value of
     * d[0] calculated by the function.
     * 
     * If the matrix is singular, then the routine throws a MatrixException.
     *
     * This method is an adaptation of the one found in the book "Numerical
     * Recipies in C" (see online for more details).
     * 
     * @param d  An array of length 1. On exit, the value contained in here
     *           will either be 1 or -1, which you can use to calculate the
     *           correct sign on the determinant.
     * @return   The LU decomposition of the matrix.
     */
    public GeneralMatrix decomp(double[] d) {
        // This method is complete. You should not even attempt to change it!!
        if (n != m)
            throw new MatrixException("Matrix is not square");
        if (d.length != 1)
            throw new MatrixException("d should be of length 1");
        
        int           i, imax = -10, j, k; 
        double        big, dum, sum, temp;
        double[]      vv   = new double[n];
        GeneralMatrix a    = new GeneralMatrix(this);
        
        d[0] = 1.0;
        
        for (i = 1; i <= n; i++) {
            big = 0.0;
            for (j = 1; j <= n; j++)
                if ((temp = Math.abs(a.data[i-1][j-1])) > big)
                    big = temp;
            if (big == 0.0)
                throw new MatrixException("Matrix is singular");
            vv[i-1] = 1.0/big;
        }
        
        for (j = 1; j <= n; j++) {
            for (i = 1; i < j; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < i; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
            }
            big = 0.0;
            for (i = j; i <= n; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < j; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
                if ((dum = vv[i-1]*Math.abs(sum)) >= big) {
                    big  = dum;
                    imax = i;
                }
            }
            if (j != imax) {
                for (k = 1; k <= n; k++) {
                    dum = a.data[imax-1][k-1];
                    a.data[imax-1][k-1] = a.data[j-1][k-1];
                    a.data[j-1][k-1] = dum;
                }
                d[0] = -d[0];
                vv[imax-1] = vv[j-1];
            }
            if (a.data[j-1][j-1] == 0.0)
                a.data[j-1][j-1] = 1.0e-20;
            if (j != n) {
                dum = 1.0/a.data[j-1][j-1];
                for (i = j+1; i <= n; i++)
                    a.data[i-1][j-1] *= dum;
            }
        }
        
        return a;
    }

    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
    	GeneralMatrix gm1 = new GeneralMatrix(4, 4);
		gm1.random();
		GeneralMatrix gm1c = new GeneralMatrix(gm1);
		gm1.setIJ(2, 2, 10);
		double setcheck = gm1.getIJ(2, 2);
		Matrix gm2 = gm1.add(gm1c);
		Matrix gm3 = gm1.multiply(2);
		Matrix gm4 = gm2.multiply(-2);
		
		System.out.println("position (2, 2):\n"+setcheck);
		System.out.println("matrix gm1: \n"+gm1);
		System.out.println("copy of gm1 --> gm1c \n"+gm1c);
		System.out.println("gm1 + gm1c \n"+gm2);
		System.out.println("2 * gm1 \n"+gm3);
		System.out.println("-2 * gm1 \n"+gm4);
		System.out.println("multipying gm1 and gm3 \n"+ gm1.multiply(gm3));
		System.out.println("determinant of gm1: \n"+ gm1.determinant());
    }
}