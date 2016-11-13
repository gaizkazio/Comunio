package Ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Entidades.Usuario;

public class bd_statements {
	Connection conn =null;
	String url="C:\\Users\\gaizka\\Downloads\\Program\\Sqliteman-1.2.2\\ComunioBD";
	
	public void crearTablaUsuarios(){
		try { Class.forName("org.sqlite.JDBC"); }
		catch (ClassNotFoundException e) { 
			e.getStackTrace();
			}
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:"+url);
			Statement stmt=conn.createStatement();
			String sentencia="CREATE TABLE USUARIO("
					+ "USUARIO VARCHAR, "
					+ "CONTRASEÑA VARCHAR(15) NOT NULL PRIMARY KEY);";
			stmt.executeUpdate(sentencia);
			conn.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void insertarValoresUsuario(Usuario usuario){
		try { Class.forName("org.sqlite.JDBC"); }
		catch (ClassNotFoundException e) { 
			e.getStackTrace();
			}
		try{
		conn = DriverManager.getConnection("jdbc:sqlite:"+url);
		Statement stmt=conn.createStatement();
		String sentencia="INSERT INTO USUARIO VALUES('"+usuario.getNombre()+"','"+usuario.getContraseña()+"');";
		stmt.executeUpdate(sentencia);
		conn.close();
		stmt.close();
	}catch(Exception e){
		e.printStackTrace();
	}
		
		
	}
	public void seleccionarTodosLosValores(String nomTabla){
		try { Class.forName("org.sqlite.JDBC"); }
		catch (ClassNotFoundException e) { 
			e.getStackTrace();
			}
		 try{
				conn = DriverManager.getConnection("jdbc:sqlite:n.db");
				Statement stmt=conn.createStatement();
				ResultSet st =stmt.executeQuery("SELECT * FROM USUARIO;");
				System.out.print(st.getString("USUARIO"));
				System.out.print(",");
				System.out.print(st.getString("CONTRASEÑA"));
				
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}
		
		
		
	
	
	
}
