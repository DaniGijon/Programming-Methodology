/*
*
* Case Name: Backtracking Algorithm 
* Author name: Daniel Gijón Robas
* Release/Creation date: 23/06/2019
* Case version: 1.0
* Case description: I'm asked to:
*	1) Develop, and implement in Java, a backtracking algorithm solving the 'car trunk' problem
*	2) What is the complexity of the algorithm?
* 
* Tasks:
*	1) View the code below. 
*	   Firstly, I've sorted the bags by decreasingly importance, although in this case it's not a mandatory step.
*	   The Backtracking Algorithm explore the array of bags. For each level in the tree, the algorithm is going to evaluate a new bag, 
*	   and each leaf represents if that element has been elected or not.
*	   It evaluates all the possible combinations for placing the bags into the car trunk and also checks if the current solution is the
*	   best or not (It's the best if it reports more 'importance points' than the previous best solution stored).
*	   Once all combinations have been evaluated, we'll have obtained the best solution for packing the bags. 
*		
*	2) The algorithm has to evaluate all the possible combinations in order to compare them and get the best solution. 
*	   Each element in the tree can be included or not (2 options). So, the algorithm has a complexity O(2^n).
*
*/	

package case4;

import java.util.Arrays;

import case4.main.Bolso;

public class main {
	
	static class Bolso implements Comparable<Bolso>{

        public int largo, ancho, ID, importancia;
        // Constructor
        public Bolso(int largo, int ancho, int ID, int importancia) {
            this.largo = largo;
            this.ancho = ancho;
            this.ID = ID;
            this.importancia = importancia;
        }

        @Override
        public int compareTo(Bolso o) {
            if (importancia > o.importancia) {
                return -1;
            }
            if (importancia < o.importancia) {
                return 1;
            }
            return 0;
        }
    }
	
	static class Maletero{
		public int largo, ancho;
		// Constructor
		public Maletero(int largo, int ancho) {
			this.largo = largo;
			this.ancho = ancho;
		}
	}
	
	// Método para imprimir el array de Bolsos
    static void imprimeArrayBolsos(Bolso[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Largo: " + array[i].largo + " - Ancho: " + array[i].ancho + " - ID: " + array[i].ID + " - Importancia: " + array[i].importancia);
        }
    }
    
    //Método backtracking. 
    static void Backtracking( Bolso [] arrayBolsos, int maleteroLargo, int maleteroAncho, Bolso[] mejorSol, Bolso [] sol, int etapa){
    	//Si posible solución
    	if((etapa == arrayBolsos.length) || (maleteroLargo == 0 && maleteroAncho == 0 )) {
    		if(esMejor(arrayBolsos, sol, mejorSol))
    			System.arraycopy(sol, 0, mejorSol, 0, sol.length);
    	}
    	else {
    		//probamos a NO meter el bolso en la solucion
    		sol[etapa] = null;
    		Backtracking(arrayBolsos, maleteroLargo, maleteroAncho, mejorSol, sol, etapa+1);
    		
    		//probamos a SÍ meter el bolso en la solucion (en caso de que se pueda)
    		Bolso bolso = arrayBolsos[etapa];
    		int cabe = esValido(bolso, maleteroLargo, maleteroAncho);
    		if(cabe == 1) { //el bolso cabe tal cual
    			sol[etapa] = bolso;
    			Backtracking(arrayBolsos, maleteroLargo - bolso.largo, maleteroAncho - bolso.ancho, mejorSol, sol, etapa+1);
    		}
    		else if (cabe == 2) { //el bolso cabe rotado
    			sol[etapa] = bolso;
    			Backtracking(arrayBolsos, maleteroLargo - bolso.ancho, maleteroAncho - bolso.largo, mejorSol, sol, etapa+1);
    		}
    	}
    	
    } //FIN BACKTRACKING
    
    //Método que comprueba que las dimensiones del bolso no exceden a las dimensiones libres del maletero. Lo evalúa para entrar en su posición
    //natural, y si así no fuese posible, lo evalúa para entrar en posición rotada. En caso negativo, lo va a descartar.
    static int esValido (Bolso bolso, int maleteroLargo, int maleteroAncho) {
    	//comprueba si el bolso a evaluar cabe dentro del maletero
    	int valido;
	    	if((bolso.largo <= maleteroLargo) && (bolso.ancho <= maleteroAncho)) { //Entra tal cual
	    		
				valido = 1;
			}
	    	else if((bolso.largo <= maleteroAncho) && (bolso.ancho <= maleteroLargo)){ //Entra rotado
	    		
	    		valido = 2;
			}
	    	else {
	    		valido = 0;
	    	}
    	return valido;
    }
    
    //Método que comprueba si la solución actual supera en beneficio a la anterior mejor solución almacenada.
    static boolean esMejor(Bolso [] arrayBolsos, Bolso[]sol1, Bolso[]sol2) {
    	return beneficio(arrayBolsos,sol1) > beneficio(arrayBolsos,sol2);
    } //FIN esMejor
    
    //Método que cuantifica cuál es el beneficio que reporta la solución actual (suma de las importancias de cada bolso).
    static int beneficio(Bolso[] arrayBolsos, Bolso[]sol) {
    	int beneficio = 0;
    	for(int i= 0; i < sol.length; i++) {
    		if(sol[i] != null) {
    			Bolso bolso = arrayBolsos[i];
    			beneficio += bolso.importancia;
    		}
    	}
    	return beneficio;
    }
	
	public static void main(String[] args) {
		Bolso[] arrayBolsos = new Bolso[5];
        arrayBolsos[0] = new Bolso(10, 20, 1, 300);
        arrayBolsos[1] = new Bolso(34, 12, 2, 700);
        arrayBolsos[2] = new Bolso(1, 8, 3, 200);
        arrayBolsos[3] = new Bolso(67, 32, 4, 600);
        arrayBolsos[4] = new Bolso(30, 15, 5, 400);
        
        Bolso arraySolucion [] = new Bolso[arrayBolsos.length];
        Bolso arrayMejorSol [] = new Bolso[arraySolucion.length];
        
        Maletero maletero = new Maletero(50,50);
        System.out.println("Largo del maletero = " + maletero.largo);
        System.out.println("Ancho del maletero = " + maletero.ancho);
        

        System.out.println("Array de bolsos sin ordenar por importancia");
        imprimeArrayBolsos(arrayBolsos);
        
        // Ordeno el array de bolsos por importancia (de mayor a menor).
        Arrays.sort(arrayBolsos);
        System.out.println("Array de bolsos ordenado por importancia");
        imprimeArrayBolsos(arrayBolsos);
        
        //Vamos a aplicar backtracking para el array de bolsos candidatos.
        long inicio = System.nanoTime();
        Backtracking(arrayBolsos, maletero.largo, maletero.ancho, arrayMejorSol, arraySolucion, 0);
        long fin = System.nanoTime();
		long tiempo = fin - inicio;
		System.out.println("tiempo transcurrido: "+ tiempo);
        
        System.out.println("He colocado los siguientes bolsos:");
        for(int i=0; i<arrayMejorSol.length; i++) {
        	if(arrayMejorSol[i] != null) {
        		System.out.println("ID: " + arrayMejorSol[i].ID);
        	} 
        }
	}
}