package case2;

/*
*
* Case Name: Divide and Conquer 
* Author name: Daniel Gijón Robas
* Release/Creation date: 22/06/2019
* Case version: 1.0
* Case description: I'm asked to design and implement in Java a divide and conquer 
* algorithm that applies the bisection method for finding roots of a given function, 'f'. 
* I must provide the 'ini' and 'fin' points defining the '[ini, fin]' interval and the
* desired precision of the root, 'precision'. 
* 
* Tasks:
*	1) Determine theoretically the complexity of the algorithm:
*	
*   For T(n): I recursively call the method for one of the halves of the array: bottom half or ceiling half,
*  			  depending on whether the zero is found between [ini,med] or it is placed into [med,fin].
*   		  In here, we have:
*			  T(n) = O(n)
*			  and we know that: T(n) > T(n/2) + T((n/2)/2) + ... + T((((n/2)/2)/.../2)).
*			  I mean, T(n) is always higher than the recursive sum from T(n/2) to T(n/2^i) for the i-th recursive step.
*			  
*			  We need that n/2^i = O(1), this is achieved when 2^i = c*n, for some constant c, 
*			  so we take the base 2 logarithm from both sides and get that i = c*logn. 
*
*			  So the last recursive step will be the c*logn-th step, and then the tree has c*logn height.

			  So, the total cost of the algorithm will be c*n for each of the c*logn recursive levels, which gives the O(n*logn) complexity.
*			  In conclusion: The algorithm has a n*log(n) complexity: O(nlogn).
*		
*	2) Find the function roots in the following cases:
*			  Being a precision = 0.1, I obtained the following roots for each case:
*				CASE A.1)  f(x)=x^2-2x-3 and [0, 4] 
*					Root: 3.0
*				CASE A.2)  f(x)=x^2-2x-3 and [2, 2.5] 
*					Root: 2.4375
*				CASE B)	   f(x)=2^x - x^2 -10 and [0, 6]
*					Root: 5.15625
*				CASE C)    f(x)= (Math.sin(med)) - (1/med) [2*Math.PI, 5*Math.PI/2]
*					Root: 6.4795348480289485
*
*/

public class main {
	
	static double encontrarRaiz(double ini, double fin, double precision) {
		double med = (ini+fin)/2;
		
		double resultado = calcularFuncionC(med);
		
		if((Math.abs(resultado) <= precision) || (Math.abs(med-ini) <= precision))
			return med;
		else {
			if(resultado < 0)
				return encontrarRaiz(med,fin,precision);
			else
				return encontrarRaiz(ini,med,precision);
		}
	}
	
	static double calcularFuncionA(double med) {
		return ((med*med)-(2*med)-3);
	}
	
	static double calcularFuncionB(double med) {
		return (Math.pow(2, med))-(med*med)-10;
	}
	
	static double calcularFuncionC(double med) {
		return (Math.sin(med)) - (1/med);
	}
	
	
	public static void main(String[] args) {
		long inicio = System.nanoTime();
		//double raiz = encontrarRaiz(0,6,0.1);
		double raiz = encontrarRaiz(2*Math.PI,(5*Math.PI)/2,0.1);
		System.out.println(raiz);
		long fin = System.nanoTime();
		long tiempo = fin - inicio;
		System.out.println("tiempo transcurrido: "+ tiempo);
	}
}
