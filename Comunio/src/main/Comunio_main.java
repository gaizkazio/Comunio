package main;

import java.sql.Connection;
import java.sql.Statement;

import Connection.Bd;
import Entidades.Usuario;
import Ventanas.MenuRegistro;
import Ventanas.bd_statements;

public class Comunio_main {
private static Connection con =Bd.initBD("ComunioBD");
	public static void main(String[]args){
		bd_statements bds= new bd_statements();
		bds.seleccionarValores("*", "usuario", con);
		Bd bd=new Bd();
		Statement stmt = bd.usarBD(con);
		
		
	}
}
