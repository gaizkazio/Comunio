package Ventanas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import Connection.Bd;
import Entidades.Usuario;
import Connection.Conexion;
import Utilidades.Utilidades;;

public class bd_statements {
	Connection conn =null;
	String url="C:\\Users\\gaizka\\Downloads\\Program\\Sqliteman-1.2.2\\ComunioBD";
	
	
	public void insertarValoresUsuario(Usuario usuario,Connection con){
		
		Statement stmt=Bd.usarBD(con);
		metodoInsert(stmt,"Usuario");
		
	
		
		
	}
	public void seleccionarValores(	String valores,String nomTabla,Connection con){
		Statement stmt=Bd.usarBD(con);
				pediraBd(valores,"USUARIO",stmt,"USUARIO","CONTRASE�A");
				
			
		
	}
	// Metodo query FINALIZADO
		public void pediraBd(String valores,String nomTabla,Statement stmt,String...columnas){
			
			try{
				
				int i=0;
				ResultSet st =stmt.executeQuery("SELECT "+ valores+" FROM  "+nomTabla+" ") ;
				for(String columna:columnas){
				System.out.print(st.getString(columna));
				if(columnas.length>=i)System.out.print(" ,");
					i++;
					
				}
				
				
				
			}catch(Exception e) {
				e.printStackTrace();
			
		}
		
		
		}
	//Metodo insert FINALIZADO
	public void metodoInsert(Statement stmt,String nombreTabla){
		boolean respuesta=true;
		String[]columnas=new String[20];
		String[]valorCadena=new String[20];
		int i=0;
		while(respuesta){
			
		System.out.println("Como se llama la columna de la BD?");
		String resp=Utilidades.leerCadena();
		columnas[i]=resp;
		
		System.out.println("Que valor le desea meter ?");
		resp=Utilidades.leerCadena();
		valorCadena[i]=resp;
		
		i++;
		
		System.out.println("Desea meter una columna/valor mas(S/N)");
		resp=Utilidades.leerCadena();
		if(resp.equals("s")|| resp.equals("S")){
			respuesta=true;
		}else{
			respuesta=false;
		}
		}
		String envio="INSERT INTO "+nombreTabla+" ( ";
		for(int j=0;j<i;j++){
			envio=envio+columnas[j];
			if(j+1<i){
				envio=envio+" , ";
			}
		}
		envio=envio+" ) VALUES ( ";
		for(int j=0;j<i;j++){
			envio=envio+" '"+valorCadena[j]+"' ";
			if(j+1<i){
				envio=envio+" , ";
			}
		}
		envio=envio+" );";
		try{
			System.out.println(envio);
			stmt.executeUpdate(envio);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//Metodo alter FINALIZADO
	public void metodoAlter(Statement stmt,String nomTabla){
		System.out.println("A�adir(1) o modificar(2)?");
		int respuesta=Utilidades.leerEntero();
		String[]columnas=new String[20];
		String[]valores=new String[20];
		int i=0;
		boolean resp=true;
		while(resp){
			
			System.out.println("Como se llama la columna que desea alterar?");
			String columna=Utilidades.leerCadena();
			columnas[i]=columna;
			System.out.println("Introduce valor:");
			String valor=Utilidades.leerCadena();
			valores[i]=valor;
			i++;
			
			if(respuesta==1){
				for(int j=0;j<i;j++){
				String envio="ALTER TABLE "+ nomTabla + " ADD ( "+columnas[j]+" "+valores[j]+" );";
				try{
					System.out.println(envio);
					stmt.executeUpdate(envio);
				}catch(Exception e) {
						e.printStackTrace();
				}
				}
			}else{
				for(int j=0;j<i;j++){
					String envio=" ALTER TABLE "+ nomTabla+ " MODIFY ( "+columnas[j]+" "+valores[j]+");";
					try{
						stmt.executeUpdate(envio);
					}catch(Exception e) {
							e.printStackTrace();
					}
					}

			}
			
			System.out.println("Desea cambiar/meter alguna columna mas(S/N)");
			String respuesta1=Utilidades.leerCadena();
			if(respuesta1.equals("s")|| respuesta1.equals("S")){
				
				resp=true;
			}else{
				resp=false;
			}
			}
	
		
	}
	// Deberia estar finalizado
	public void metodoUpdate(Statement stmt,String nomTabla){
		boolean respuesta=true;
		String[]columnas=new String[20];
		String[]valorCadena=new String[20];
		String envio="UPDATE "+nomTabla+"SET ";
		
		while(respuesta){
			// Cada vez que preguntamos por una columna meterla directamente en el string
		System.out.println("Como se llama la columna?");
		String resp=Utilidades.leerCadena();
		envio=envio+resp+" = ";
		
		System.out.println("Que valor le desea meter ?");
		resp=Utilidades.leerCadena();
		envio=envio+" "+resp;
		
		
		System.out.println("Desea meter una columna/valor mas(S/N)");
		if(resp!="s"|| resp!="S"){
			envio=envio+" ;";
			respuesta=false;
		}else{
			respuesta=true;
		}
		}
		
}
}
