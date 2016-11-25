package main;

import java.sql.Connection;
import java.sql.Statement;

import Connection.Bd;
import Entidades.Usuario;
import Ventanas.bd_statements;

public class Comunio_main {

	public static void main(String[]args){
		Bd bd = new Bd();
		Connection con =Bd.initBD("ComunioBD");
		bd_statements bds= new bd_statements();
		Statement stmt=bd.usarBD(con);
		bds.metodoInsert(stmt, "usuario");
		bds.seleccionarTodosLosValores("", "usuario", con);
		
	}
}
