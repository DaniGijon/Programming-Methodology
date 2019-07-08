/*
*
* Case Name: Greedy Algorithm 
* Author name: Daniel Gijón Robas
* Release/Creation date: 23/06/2019
* Case version: 1.0
* Case description: I'm asked to:
*	1) Develop, and implement in Java, a greedy algorithm solving the 'car trunk' problem.
*	2) What is the complexity of the algorithm?
*	3) Is the algorithm optimal? Prove it or give a counterexample
* 
* Tasks:
*	1) View the code below. 
*	   Firstly, I need to sort the bags by decreasingly importance.
*	   When sorted, the bags array is ready to be processed in the Greedy Algorithm as input parameter.
*	   The Greedy Algorithm explore that array of bags sorted by importance. For each bag, it tries to place it into the car trunk.
*	   If fits, it's ok. If not, it tries to rotate the bag in order to fit. If it's still not possible, it discard the bag and continue
*	   exploring the bags array. 
*		
*	2) It has to evaluate each one of the 'n' elements of the bags array one time, to decide if the 'i' object fits or not. 
*	   So, the complexity is LINEAL O(n).
*
*	3) It's NOT an optimal solution. Imagine we have a bag of huge dimensions and it also has the bigger importance. It's going to be the first
*	element of the array to be explored and actually it fits into the trunk, but its dimensions are so big that there's no space left for packing others bag.
*	Maybe, if we had packed several of those smaller bags, we'd have as a result a greater importance sum. But, when those smaller bags are evaluated,
*	there's no space left in the trunk, so those smaller bags are not packed.
*	We should use 'Backtracking' in order to solve it in a more optimal way.
*/	

package case3;

import java.util.Arrays;

import case3.main.Bolso;

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
    
    // Método para algoritmo Voraz
    static int[] voraz( Bolso [] arrayBolsos, Maletero maletero){
    	int arraySolucion [] = new int[arrayBolsos.length];
    	
    	for(int i=0; i<arrayBolsos.length;i++) {
			if((arrayBolsos[i].largo <= maletero.largo) && (arrayBolsos[i].ancho <= maletero.ancho) ) { //Entra tal cual
				arraySolucion[i] = arrayBolsos[i].ID;
				maletero.largo = maletero.largo - arrayBolsos[i].largo;
				maletero.ancho = maletero.ancho - arrayBolsos[i].ancho;
			}
			else if((arrayBolsos[i].largo <= maletero.ancho) && (arrayBolsos[i].ancho <= maletero.largo)){ //Entra rotado
				arraySolucion[i] = arrayBolsos[i].ID;
				maletero.largo -= arrayBolsos[i].ancho;
				maletero.ancho -= arrayBolsos[i].largo;
			}
		}
    	
    	return arraySolucion;
    }
	
	
	public static void main(String[] args) {
		Bolso[] arrayBolsos = new Bolso[5];
        arrayBolsos[0] = new Bolso(10, 20, 1, 300);
        arrayBolsos[1] = new Bolso(34, 12, 2, 700);
        arrayBolsos[2] = new Bolso(1, 8, 3, 200);
        arrayBolsos[3] = new Bolso(67, 32, 4, 600);
        arrayBolsos[4] = new Bolso(30, 15, 5, 400);
        
        Maletero maletero = new Maletero(50,50);
        System.out.println("Largo del maletero = " + maletero.largo);
        System.out.println("Ancho del maletero = " + maletero.ancho);
        

        System.out.println("Array de bolsos sin ordenar por importancia");
        imprimeArrayBolsos(arrayBolsos);
        
        // Ordeno el array de bolsos por importancia (de mayor a menor).
        Arrays.sort(arrayBolsos);
        System.out.println("Array de bolsos ordenado por importancia");
        imprimeArrayBolsos(arrayBolsos);
        
        //Una vez que el arrayBolsos se encuentra ordenado por importancia, vamos a ir colocando los bolsos que sean posibles en orden de importancia
        int[] arraySolucion = new int[arrayBolsos.length];
        long inicio = System.nanoTime();
        arraySolucion = voraz(arrayBolsos, maletero);
        long fin = System.nanoTime();
		long tiempo = fin - inicio;
		System.out.println("tiempo transcurrido: "+ tiempo);
        
        System.out.println("He colocado los siguientes bolsos:");
        for(int i=0; i<arraySolucion.length; i++) {
        	if(arraySolucion[i] != 0) {
        		System.out.println("ID: " + arraySolucion[i]);
        	} 
        }
      
        
		
	}
}