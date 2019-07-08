package case1;

/*
*
* Case Name: Complexity Study and Times 
* Author name: Daniel Gijón Robas
* Release/Creation date: 21/06/2019
* Case version: 1.0
* Case description: I'm asked to design 3 algorithms implemented
in Java that, using n as input parameter, provide the nth
hexagonal number. The first algorithm must use
equation and the other two equation in iterative
and recursive form. 
*
* Task 1) Determine theoretically the complexity of the algorithms
*		  (1) Equation: It's solved calling a function to calculate a given equation with 'n' as one of 
* 						 the operands.
*                        It has a O(1) CONSTANT COMPLEXITY, because it directly calculates the function:
*                        n * ((2*n) - 1); replacing 'n' for the given value as input parameter.
*                        In this case, the time complexity doesn't depend on the size of the input 'n'.
* 
* 		  (2) Iteration:  It calculates the equation for each value of the 'for-loop' from 0 to n-1
* 						  and then add the result to an accumulative variable. 
* 						  For each iteration, it exists only 1 for-loop that it simply calculates (4*i + 1)
* 						  being i = stage to evaluate. 
* 						  So, that equation is calculated N times.
* 						  Then, the complexity is LINEAL : O(n)
* 
* 		  (3) Recursive:  It calculates the equation for each value from 0 to n-1, calling itself recursively
* 						  evaluating the next stage each way. It also adds the result to an accumulator.
* 						  The time complexity of recursion can get to be EXPONENTIAL when there are 
* 						  a considerable number of recursive calls: O(2^n)
* 
* Task 2) Determine empirically the running time of the algorithms as a function of n
* 			(1) Equation:	
* 					N = 100: 2765 nanoseconds
* 					N = 200: 2766 nanoseconds
* 					N = 300: 2766 nanoseconds
* 					N = 400: 2766 nanoseconds
* 					N = 500: 2766 nanoseconds
* 
* 			(2) Iteration:
* 					N = 100:  4346 nanoseconds
* 					N = 200:  6321 nanoseconds
* 					N = 300:  8691 nanoseconds
* 					N = 400: 10271 nanoseconds
* 					N = 500: 12642 nanoseconds
* 
* 			(3) Recursive:
* 					N = 100:  7111 nanoseconds
* 					N = 200: 14223 nanoseconds
* 					N = 300: 51754 nanoseconds
* 					N = 400: 69531 nanoseconds
* 					N = 500: 92444 nanoseconds
* 
* Conclusion) The usage of recursion is advantageous in shorter the size of the code, but disadvantegeous in higher time complexity.
* 				
*
*/ 
public class main {
	public static void main(String[] args) {
		//Declaration of Variables
		int n = 500; //<- That's the size of N. In order to calculate the Nth hexagonal number
		long tstartEquation = 0;
		long tendEquation = 0;
		long deltaTEquation = 0;
		long tstartIteration = 0;
		long tendIteration = 0;
		long deltaTIteration = 0;
		long tstartRecursive = 0;
		long tendRecursive = 0;
		long deltaTRecursive = 0;
		
		//calling Equation method and computing its time
		tstartEquation = System.nanoTime();
		int equation = equation(n);
		tendEquation = System.nanoTime();
		deltaTEquation = tendEquation - tstartEquation;
		
		//calling Iteration method and computing its time
		tstartIteration = System.nanoTime();
		int iteration = iteration(n);
		tendIteration = System.nanoTime();
		deltaTIteration = tendIteration - tstartIteration;
		
		//calling Recursive method and computing its time
		tstartRecursive = System.nanoTime();
		int recursive = recursive(n,0,0);
		tendRecursive = System.nanoTime();
		deltaTRecursive = tendRecursive - tstartRecursive;
		
		//Showing obtained results
		System.out.println("Equation results: " + equation);
		System.out.println("Equation runs in " + deltaTEquation + " nanoseconds\n");
		System.out.println("Iteration results: " + iteration);
		System.out.println("Iteration runs in " + deltaTIteration + " nanoseconds\n");
		System.out.println("Recursive results: " + recursive);
		System.out.println("Recursive runs in " + deltaTRecursive + " nanoseconds\n");
	}
	
	//Método Equation. Ejecuta directamente una ecuación tomando como operando un valor de entrada que le paso
	static int equation(int n) {
		int result = n * ((2*n) - 1);
		return result;
	}
	
	/* Método Iteration. Recorre iterativamente de 0 hasta el n-1 a evaluar. Calcula la función en cada paso 
	 * y va acumulando una suma iterativa donde el resultado de la función en el paso evaluado se suma 
	 * al resultado acumulado que ha ido guardando en cada paso. */
	static int iteration(int n) { // i = stage
		int result = 0;
		for(int i=0; i<n; i++) { //from 0 to n-1
			result += (4*i + 1);
		}
		return result;
	}
	
	/* Método Recursive. Recorre recursivamente de 0 hasta n-1, llamándose a sí misma pasando el resultado 
	 * acumulado y evaluando la siguiente etapa con un resultado creciente hasta haber calculado 
	 * la función para cada valor requerido. */
	static int recursive(int n, int i, int result) { //i = stage
		result += (4*i + 1);
		if(i<n-1) {
			return recursive (n, i+1, result);
		}
		else{
			return result;
		}
	}

}
