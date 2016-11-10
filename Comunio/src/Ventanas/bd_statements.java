package Ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class bd_statements {
	Connection conn =null;
	
	
	public void crearTablaUsuarios(String nombre,String contr ){
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:n.db");
			Statement stmt=conn.createStatement();
			String sentencia="CREATE TABLE USUARIO("
					+ "USUARIO VARCHAR, "
					+ "CONTRASEÑA VARCHAR NOT NULL PRIMARY KEY);";
					
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
