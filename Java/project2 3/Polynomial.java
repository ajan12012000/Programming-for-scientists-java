/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
        // You need to fill in this function.
    	int deg = coeff.length;
    	if ((coeff[deg - 1].abs() == 0) && (deg > 1))
    		deg-=1;
    	this.coeff = new Complex[deg];
    	for (int i = 0 ; i < deg ; i++ )
    		this.coeff[i] = coeff[i];
    }
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        // You need to fill in this function.
    	this.coeff = new Complex[1];
		this.coeff[0] = new Complex();
    }

    public Polynomial(Object coeff2) {
		// TODO Auto-generated constructor stub
	}

	// ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
        // You need to fill in this function.
    	String t = "(" + coeff[0].toString() + ")";
    	if (degree() >= 1)
    		t += "+(" + coeff[1].toString() + ")X";
        for (int i=2 ;i <= degree(); i++){
            t += "+(" + coeff[i].toString() + ")X^" + i;
        }
    	return t;
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        // You need to fill in this function.
    	return coeff.length - 1;
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        // You need to fill in this function.
    	Complex p = new Complex();
    	for (int i = degree() ; i >= 0 ; i--)
    		p = coeff[i].add((z.multiply(p)));
    	return p;
    }
    
    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */
    public Polynomial derivative() {
        // You need to fill in this function.
//    	Polynomial deri = new Polynomial();
    	if (degree() == 0) {
    		return new Polynomial();
    	
    	} else { 
    		Complex [] derivative = new Complex[degree()];
        	for (int i = 0 ; i <= degree() - 1 ; i++)
        		derivative [i] = this.coeff[i + 1].multiply(i + 1);
        	Polynomial der = new Polynomial(derivative);
    		return der;
    	}
    	
    	
    }
    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // You need to fill in this function.
    	Complex A = new Complex(1.0, 1.0);
        Complex B = new Complex(1.0);
        Complex C = new Complex(3.6, 5.0);
        Complex [] z = new Complex[1];
        z[0] = C;
    	Polynomial p3 = new Polynomial(z);
    	Complex [] p = new Complex[4];
    	p[0] = C;
    	p[1] = B;
    	p[2] = A;
    	p[3] = C;
//        Polynomial p1   = new Polynomial(A,B,C);
        Polynomial p2   = new Polynomial(p);
        Polynomial zero   = new Polynomial();
        
        System.out.println("zero(x)     = " + zero);
        System.out.println(p2);
        System.out.println(p2.derivative().derivative());
        System.out.println(p3);
    }
}