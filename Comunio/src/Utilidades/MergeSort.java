package Utilidades;

import java.util.Random;

import Entidades.Usuario;

public class MergeSort {
	private static int profLlams=0;  // Para facilitar la vista de las llamadas

	/** Ordena recursivamente (mergesort) un array de enteros
	 * @param nums	Array de enteros a ordenar
	 */
	public static void mergeSort( Usuario[] nums ) {
		mergeSort( nums, 0, nums.length-1 );
	}
	
	/** Ordena recursivamente (mergesort) un array de enteros
	 * @param nums	Array de enteros a ordenar
	 * @param ini	Posición inicial de la ordenación
	 * @param fin	Posición final de la ordenación (inclusive)
	 */
	public static void mergeSort( Usuario[] nums, int ini, int fin ) {
		// for (int i=0;i<profLlams;i++) System.out.print( " "); System.out.println( "Entrando en merge: " + ini + "," + fin ); profLlams++; 
		if (ini>=fin) {
			// Caso base, nada que ordenar
		} else {
			int med = (ini+fin)/2;
			mergeSort(nums,ini,med);
			mergeSort(nums,med+1,fin);
			mezclaMergeSort(nums,ini,med,fin);
		}
		profLlams--;
	}
		// Algoritmo de mezcla (no recursivo)
		// Mezcla en nums las mitades ya ordenadas (ini1 a fin1) con (fin1+1 a fin2)
		private static void mezclaMergeSort( Usuario[] nums, int ini1, int fin1, int fin2 ) {
			int ref1 = ini1;   // Referencia inicial a primera mitad
			int ref2 = fin1+1; // Referencia inicial a segunda mitad
			// Mezclar las dos mitades. Primero llevarlas mezcladas a un array intermedio:
			Usuario[] destino = new Usuario[fin2-ini1+1];
			int posDest = 0;
			while (ref1<=fin1 || ref2<=fin2) {  // Van subiendo ref1 y ref2 hasta acabar (fin1 y fin2)
				// Hay que comparar ref1 con ref2
				boolean menorEs1 = true;  // Suponemos en principio que es <= ref1 
				if (ref1>fin1) // No hay ya elementos en la primera mitad
					menorEs1 = false; // En este caso no lo es
				else if (ref2<=fin2 && nums[ref1].getPuntos()>nums[ref2].getPuntos())
					menorEs1 = false;  // En este caso tampoco
				if (menorEs1) { // Si es menor el de la mitad 1 se lleva de 1
					destino[posDest] = nums[ref1];
					ref1++;
				} else {  // Si es menor el de la mitad 2 se lleva de 2
					destino[posDest] = nums[ref2];
					ref2++;
				}
				posDest++;
			}
			// Copiar el array intermedio a la listaOriginal
			posDest = 0;
			for( int i=ini1; i<=fin2; i++ ) {
				nums[i] = destino[posDest];
				posDest++;
			}
		}
	
	
}
