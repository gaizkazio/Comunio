package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;

public class Conexion {
Connection connect;
String url="C:\\Users\\gaizka\\Downloads\\Program\\Sqliteman-1.2.2\\ComunioBD";
	
	public void connect(){
	 try {
	     connect = DriverManager.getConnection("jdbc:sqlite:"+url);
	     if (connect!=null) {
	         System.out.println("Conectado");
	     }
	 }catch (SQLException ex) {
	     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
	 }
	}

	 public void close(){
	        try {
	            connect.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
	        }
	 }
	 public void registrarse(String usuario,String contrase�a,JTextField textoUsuario,JTextField textoContrase�a){
			usuario=textoUsuario.getText();
			contrase�a=textoContrase�a.getText();
			
			try{
				Statement stmt=connect.createStatement();
				String sentencia="INSERT INTO USUARIO VALUES('"+usuario+"','"+contrase�a+"');";
				stmt.executeUpdate(sentencia);
			}catch(Exception e){
				e.printStackTrace();
			}	
			}
	 public void login (String usuario,String contrase�a,JTextField textoUsuario,JTextField textoContrase�a){
		 try{
				connect = DriverManager.getConnection("jdbc:sqlite:n.db");
				Statement stmt=connect.createStatement();
				ResultSet st =stmt.executeQuery("SELECT NOMBRE AND CONTRASE�A FROM USUARIO;");
				usuario=st.getString("NOMBRE");
				contrase�a=st.getString("CONTRASE�A");
			}catch(Exception e){
				e.printStackTrace();
			}
	 }
	 
	 public static void main (String[]args){
		 Conexion con = new Conexion();
	        con.connect();
	    
	        con.close();
	 }
	 
	 
	 
	 
	 
	 
}
