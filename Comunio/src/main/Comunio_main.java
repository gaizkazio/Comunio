package main;

import java.sql.Connection;
import java.sql.Statement;
import Ventanas.MenuRegistro;
import Connection.Bd;
import Entidades.Usuario;
import Ventanas.MenuRegistro;
import Ventanas.bd_statements;

public class Comunio_main {
private static Connection con =Bd.initBD("ComunioBD");
	public static void main(String[]args){
		MenuRegistro menu=new MenuRegistro(con);
		menu.main(args,con);
		
		
	}
}
