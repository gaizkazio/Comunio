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
String url="C:\\Users\\gaizka\\Downloads\\Program\\ComunioBD";
	
	
	 public void registrarse(String usuario,String contraseña,JTextField textoUsuario,JTextField textoContraseña){
			usuario=textoUsuario.getText();
			contraseña=textoContraseña.getText();
			
			try{
				Statement stmt=connect.createStatement();
				String sentencia="INSERT INTO USUARIO VALUES('"+usuario+"','"+contraseña+"');";
				stmt.executeUpdate(sentencia);
			}catch(Exception e){
				e.printStackTrace();
			}	
			}
	 public void login (String usuario,String contraseña,JTextField textoUsuario,JTextField textoContraseña){
		 try{
				connect = DriverManager.getConnection("jdbc:sqlite:n.db");
				Statement stmt=connect.createStatement();
				ResultSet st =stmt.executeQuery("SELECT NOMBRE AND CONTRASEÑA FROM USUARIO;");
				usuario=st.getString("NOMBRE");
				contraseña=st.getString("CONTRASEÑA");
			}catch(Exception e){
				e.printStackTrace();
			}
	 }
	 
	
	 
	 
	 
	 
	 
	 
}
