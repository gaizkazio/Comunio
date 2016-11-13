package main;

import Entidades.Usuario;
import Ventanas.bd_statements;

public class Comunio_main {

	public static void main(String[]args){
		bd_statements bds= new bd_statements();
		bds.seleccionarTodosLosValores("USUARIO");
		
	}
}
