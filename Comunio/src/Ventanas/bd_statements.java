package Ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import Entidades.Usuario;

public class bd_statements {
	Connection conn =null;
	
	
	public void crearTablaUsuarios(){
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:n.db");
			Statement stmt=conn.createStatement();
			String sentencia="CREATE TABLE USUARIO("
					+ "USUARIO VARCHAR, "
					+ "CONTRASEÑA VARCHAR NOT NULL PRIMARY KEY);";
			stmt.executeUpdate(sentencia);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void insertarValoresUsuario(Usuario usuario){
		try{
		conn = DriverManager.getConnection("jdbc:sqlite:n.db");
		Statement stmt=conn.createStatement();
		String sentencia="INSER INTO USUARIO VALUES('"+usuario.getNombre()+"','"+usuario.getContraseña()+"');";
		stmt.executeUpdate(sentencia);
	}catch(Exception e){
		e.printStackTrace();
	}
		
		
	}
		
		
		
	
	
	
}
