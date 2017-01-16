package Utilidades;


// Imports relacionados con el proceso
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Imports de librería externa  -  https://sourceforge.net/projects/htmlparser/
import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;

import Connection.Bd;
import Entidades.Jugador;
import Ventanas.MenuRegistro;
import Ventanas.bd_statements;

/** 
 * Clase de test de una dirección web para ver su contenido
 * Programación II - IEIAII
 * 
 * Utiliza la librería externa htmlparser 1.6
 * Descargada en feb de 2016 de  https://sourceforge.net/projects/htmlparser/files/htmlparser/1.6/htmlparser1_6_20060610.zip
 * Web del proyecto: https://sourceforge.net/projects/htmlparser/
 * Descomprimir, guardar y enlazar fichero htmlparser.jar
 */
public class TestWeb {
    private static String[] equipos={"alaves","athletic","atletico","barcelona","betis","celta","deportivo","eibar","espanyol","granada","las-palmas","leganes","malaga","osasuna","real-madrid","real-sociedad","sevilla","sporting","valencia","villarreal"};
    private static String Elequipo="";
    private static bd_statements bds= new bd_statements();
    
	public static void meterAlineacion(Connection con){
		Statement st=Bd.usarBD(con);
		ResultSet rs=bds.seleccionarValores("*", "jugador", con);
		try {
			while(rs.next()){
				if(!(rs.getString("dueño").equals("computer"))){
					Jugador jugador=new Jugador(rs.getString(1),rs.getString(4),rs.getString(3),rs.getString(7),rs.getString(5),rs.getString(6));
					st.executeUpdate("INSERT INTO alineacion VALUES('"+jugador.getNombre()+"','"+jugador.getPosicion()+"','"+jugador.getPuntuacioTotal()+"','"+jugador.getDueño()+"');");
				}
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void darPuntos(Connection con,String nom,String puntos){
		Statement st=Bd.usarBD(con);
		try {
			st.executeUpdate("UPDATE usuario SET puntuacion='"+puntos+"' WHERE USUARIO='"+nom+"';");
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void convertirComputer(Connection con){
		Statement st=Bd.usarBD(con);
		try {
			st.executeUpdate("UPDATE jugador SET dueño='computer';");
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void jugadoresEnMercado(Connection con){
		Statement stmt=Bd.usarBD(con);
		for(int i=0;i<10;i++){
			System.out.println("metiendo jugadores");
			Jugador jugadoraBd=TestWeb.generarJugador(con);
			String jugadoraBD="INSERT INTO mercado(nombre,puntuacionTotal,precio) VALUES ( '"+jugadoraBd.getNombre()+"','"+jugadoraBd.getPuntuacioTotal()+"','"+jugadoraBd.getPrecio()+"');";	
		System.out.println("m");
			try {
			Thread.sleep(10);
			stmt.executeUpdate(jugadoraBD);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	public static void eliminarCosas(Connection con){
		
		Statement st=Bd.usarBD(con);
		try {
			st.executeUpdate("DELETE FROM alineacion;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void meterJugadoresEnBd(String equipos,Connection con){
		
		
		Statement st=Bd.usarBD(con);
		String[] jugadores= pruebaDatosJugadoresComuniazo("http://www.comuniazo.com/comunio/equipos/"+equipos);
		int comas=0;
		int cont=0;
		//contador de jugadores de ese equipo
		for(int i=0;i<jugadores.length;i++){
			if(jugadores[i]!=null)
			for(int j=0;j<jugadores[i].length();j++){
				if(jugadores[i].charAt(j)==",".charAt(0) && !(jugadores[i].endsWith("[]"))){
					comas++;
				}
			}
			if(comas>0)
			cont++;
			comas=0;
		}
		for(int i=0;i<cont;i++){
			
			String nombre=sacarNombre(i+1, jugadores);
			String jugador="INSERT INTO jugador(nombre,puntuacionAnterior,puntuacionTotal,precio,equipo,dueño) VALUES ( '"+nombre+"','"+sacarPuntosAnterior(nombre, jugadores)+"','"+sacarPuntosTotales(nombre, jugadores)+"','"+sacarPrecio(nombre, jugadores)+"','"+equipos+"','computer');";
			try {
				st.executeUpdate(jugador);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	/** Procesa una web y muestra en consola sus contenidos etiquetados
	 * @param dirWeb
	 */
	public static void procesaWeb( String dirWeb ) {
		URL url;
		try {
			url = new URL( dirWeb );
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/4.0");  // Hace pensar a la web que somos un navegador
			Lexer mLexer =  new Lexer (new Page (connection));
			Node n = mLexer.nextNode();
			while (n!=null) {
				if (n instanceof Tag) {
					Tag t = (Tag) n;
					System.out.println( "Tag " + t.getTagName() + " -> " + t.getText() );
				} else {
					System.out.println( "  Nodo html -> " + n.getText() );
				}
				n = mLexer.nextNode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/** Procesa datos de jugadores comuniazo
	 * @param dirWe
	 */
	public static String[] pruebaDatosJugadoresComuniazo( String dirWeb ) {
		URL url;
		String[]result=new String[50];
		int i=0;
		try {
			url = new URL( dirWeb );
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/4.0");  // Hace pensar a la web que somos un navegador
			Lexer mLexer =  new Lexer (new Page (connection));
			Node n = mLexer.nextNode();
			while (n!=null) {  // Procesado en función de esquema devuelto el 5/12/16 (ver abajo (*))
				if (n instanceof Tag) {
					Tag t = (Tag) n;
					if (t.getTagName().equals("A") && n.getText().startsWith( "a href=\"http://www.comuniazo.com/comunio/jugadores" )) {
						n = mLexer.nextNode();  // Salta STRONG
						String nombreJug = mLexer.nextNode().getText();
						n = mLexer.nextNode();  // Salta STRONG
						ArrayList<String> datosJugador = new ArrayList<>();
						n = mLexer.nextNode();
						while (!(n instanceof Tag) || "#A#TD#SPAN#TR#".contains("#"+((Tag)n).getTagName()+"#")) {
							if (n instanceof Tag && ((Tag)n).getTagName().equals("A") && !n.getText().equals("/a")) break;  // Ya es otro jugador nuevo
							if (!(n instanceof Tag))
								datosJugador.add( n.getText() );
							n = mLexer.nextNode();
						}
						
						System.out.println( "JUGADOR: " + nombreJug + "  " + datosJugador );
						result[i]="JUGADOR: " + nombreJug + "  " + datosJugador;
						i++;
					} else {
						// System.out.println( "[" + t.getTagName() + "] " + n.getText() );
						n = mLexer.nextNode();
					}
				} else {
					n = mLexer.nextNode();
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result[0]=null;
		
		
		
		
		return result;
}

// (*)
/* Ejemplo de volcado de jugadores comunio
Tag A -> a href="http://www.comuniazo.com/comunio/jugadores/gaizka-toquero"
Tag STRONG -> strong
  Nodo html -> Gaizka Toquero
Tag STRONG -> /strong
Tag A -> /a
Tag TD -> /td
Tag TD -> td class="font-m"
  Nodo html -> 23
Tag TD -> /td
Tag TD -> td class="font-m"
  Nodo html -> 3,8
Tag TD -> /td
Tag TD -> td class="aright font-s"
  Nodo html -> 820.000
Tag TD -> /td
Tag TD -> td class="aright font-m"
Tag SPAN -> span class="forma-jugadores bg-0"
  Nodo html -> -
Tag SPAN -> /span
  Nodo html ->  
Tag SPAN -> span class="forma-jugadores bg-3"
  Nodo html -> 6
Tag SPAN -> /span
  Nodo html ->  
Tag SPAN -> span class="forma-jugadores bg-0"
  Nodo html -> -
Tag SPAN -> /span
  Nodo html ->  
Tag SPAN -> span class="forma-jugadores bg-0"
  Nodo html -> -
Tag SPAN -> /span
  Nodo html ->  
Tag SPAN -> span class="forma-jugadores bg-2"
  Nodo html -> 2
Tag SPAN -> /span
  Nodo html ->  
Tag TD -> /td
Tag TR -> /tr
Tag TR -> tr
Tag TD -> td class="mini"
Tag SPAN -> span class="pos-4"
  Nodo html -> DL
Tag SPAN -> /span
Tag TD -> /td
Tag TD -> td class="aleft"
	 */
	public static String sacarNombre(int a,String[]jugadores){
		String nom="";
		int posNom=0;
		if(a==0)a=1;
		for(int j=0;j<jugadores[a].length();j++){
			if(jugadores[a].charAt(j)=="[".charAt(0)){
				posNom=j-2;
				j=jugadores[a].length();
			}
		}
		nom=jugadores[a].substring(9, posNom);
		return nom;
	}
	public static String sacarNombre(String jugadores){
		String nom="";
		int posNom=0;
		for(int j=0;j<jugadores.length();j++){
			if(jugadores.charAt(j)=="[".charAt(0)){
				posNom=j-2;
				j=jugadores.length();
			}
		}
		nom=jugadores.substring(9, posNom);
		return nom;
	}
	
	public static String sacarPosiciones(String nombre,String[]equipo){
		String nombreI;
		String posicion="a";
		int posNom=0;
		int posPos=0;
		int cont =0;
		for(int i=1;i<=equipo.length;i++){
			for(int j=0;j<equipo[i].length();j++){
				if(equipo[i].charAt(j)=="[".charAt(0)){
					posNom=j-2;
					j=equipo[i].length();
				}
			}
			nombreI=equipo[i].substring(9,posNom);
			if(nombreI.equals(nombre)){
				 cont =0;
				for(int l=0;l<equipo[i].length();l++){
					
					if(equipo[i].charAt(l)==",".charAt(0)){
						if(cont<13)
						cont++;
					}
					if(cont==13){
						posPos=l+1;
						posicion=equipo[i].substring(posPos, posPos+2);
						l=equipo[i].length();
					}
				}
				i=equipo.length;	}
		}
		return posicion;
	}
	public static Jugador generarJugador(Connection conn){
		int comas=0;
		int cont2=0;
		int j=0;
		String nomJugador=null;
		String[]jugadores = new String[50];
		int a = (int)(Math.random()*20);
		int cont=0;
		Elequipo=equipos[a];
		 ResultSet st = null;
		 ResultSet stt=null;
		 Statement stmt=Bd.usarBD(conn);
		 Statement stmtt=Bd.usarBD(conn);
			try{
				 st =stmt.executeQuery("SELECT COUNT(*) FROM jugador WHERE equipo='"+Elequipo+"';") ;
				cont=Integer.parseInt(st.getString(1));
				if(cont>35){
					cont/=2;
				}
				jugadores = new String[cont];
				stt=stmtt.executeQuery("SELECT * FROM jugador WHERE equipo='"+Elequipo+"' AND dueño='computer';");
				while(stt.next() && cont2<=cont){
					jugadores[cont2]="JUGADOR: "+stt.getString(1)+"  ["+stt.getString(3)+", 4,2, "+stt.getString(4)+", 2,  , 6,  , 2,  , 10,  , 2,"+stt.getString(7)+"]";
					cont2++;
				}
			}catch(Exception e) {
				e.printStackTrace();
				
		}
		int b=1+(int)(Math.random()*(cont-1));
			nomJugador=sacarNombre(jugadores[b]);
		Jugador jugador=new Jugador(nomJugador,sacarPrecio(nomJugador,jugadores),sacarPuntosTotales(nomJugador, jugadores),sacarPosiciones(nomJugador,jugadores),Elequipo,"computer");
		return jugador;
	}
	public static String sacarPuntosAnterior(String nombre,String[]equipo){
		String nombreI;
		int r=0;
		int s=0;
		int cont13=0;
		int cont12=0;
		int contador=0;
		int posicion2=0;
		String posicion="";
		String pospos="";
		int posNom=0;
		int p=0;
		int cont =0;
		for(int i=1;i<equipo.length;i++){
			for(int j=0;j<equipo[i].length();j++){
				if(equipo[i].charAt(j)=="[".charAt(0)){
					p=j;
					posNom=j-2;
					j=equipo[i].length();
				}
			}
			nombreI=equipo[i].substring(9,posNom);
			if(nombreI.equals(nombre)){
				 cont =0;
				for(int l=0;l<equipo[i].length();l++){
					if(equipo[i].charAt(l)==",".charAt(0)){
						cont++;
					}
					if(cont==1 && contador==0){
						contador++;
						posNom=l;
						posicion=equipo[i].substring(p+1, posNom);
						System.out.println(posicion);
							
					}
					if(cont==12 && cont12==0){
						cont12++;
						posicion2=l;
					}
					if(cont==13&& cont13==0){
						cont13++;
						posNom=l;
						pospos=equipo[i].substring(posicion2+2, posNom);				
						if(!posicion.equals("-")) r=Integer.parseInt(posicion);
						if(!pospos.equals("-")) s =Integer.parseInt(pospos);
						if(!posicion.equals("-") && !pospos.equals("-"))
						
							posicion=String.valueOf(r-s);
						    posicion=String.valueOf(posicion);
					}
				}
				i=equipo.length;	}
		}
		return posicion;
	}
	public static String sacarPuntosTotales(String nombre,String[]equipo){
		String nombreI;
		String posicion="";
		int posNom=0;
		int p=0;
		int cont =0;
		for(int i=1;i<equipo.length;i++){
			for(int j=0;j<equipo[i].length();j++){
				if(equipo[i].charAt(j)=="[".charAt(0)){
					p=j;
					posNom=j-2;
					j=equipo[i].length();
				}
			}
			
			nombreI=equipo[i].substring(9,posNom);
			if(nombreI.equals(nombre)){
				 cont =0;
				for(int l=0;l<equipo[i].length();l++){
					if(equipo[i].charAt(l)==",".charAt(0)){
						cont++;
					}
					if(cont==1){
						posNom=l;
						posicion=equipo[i].substring(p+1,posNom);
						l=equipo[i].length();	
					}
				}
				i=equipo.length;	}
		}
		return posicion;
	}
	
	public static String sacarPrecio(String nombre,String[]equipo){
		String nombreI;
		String posicion="";
		int cont2=0;
		int posNom=0;
		int posPre=0;
		int cont =0;
		for(int i=1;i<equipo.length;i++){
			for(int j=0;j<equipo[i].length();j++){
				if(equipo[i].charAt(j)=="[".charAt(0)){
					posNom=j-2;
					j=equipo[i].length();
				}
			}
			nombreI=equipo[i].substring(9,posNom);
			if(nombreI.equals(nombre)){
				 cont =0;
				for(int l=0;l<equipo[i].length();l++){
					if(equipo[i].charAt(l)==",".charAt(0)){
						if(cont<4)
						cont++;
					}
					else if(cont==3 &&cont2==0 ){
						posPre=l;
						cont2++;
					}
					 if(cont==4){
						posNom=l;
						posicion=equipo[i].substring(posPre+1, posNom);
						l=equipo[i].length();	
					}
				}
				i=equipo.length;	}
		}
		return posicion;
	}
}


