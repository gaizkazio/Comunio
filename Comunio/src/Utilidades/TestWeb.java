package Utilidades;


// Imports relacionados con el proceso
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

// Imports de librer�a externa  -  https://sourceforge.net/projects/htmlparser/
import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;


/** 
 * Clase de test de una direcci�n web para ver su contenido
 * Programaci�n II - IEIAII
 * 
 * Utiliza la librer�a externa htmlparser 1.6
 * Descargada en feb de 2016 de  https://sourceforge.net/projects/htmlparser/files/htmlparser/1.6/htmlparser1_6_20060610.zip
 * Web del proyecto: https://sourceforge.net/projects/htmlparser/
 * Descomprimir, guardar y enlazar fichero htmlparser.jar
 */
public class TestWeb {
    private String[] equipos={"alaves","athletic","atletico","barcelona","betis","celta","deportivo","eibar","espanyol","granada","las palmas","leganes","malaga","osasuna","real madrid","real sociedad","sevilla","sporting","valencia","villareal"};

	public static void main(String[] args) {
//		 procesaWeb( "http://www.comuniazo.com/comunio/equipos/alaves" );
		String [] a=pruebaDatosJugadoresComuniazo( "http://www.comuniazo.com/comunio/equipos/alaves" );
		sacarPosiciones("Christian Santos",a);
		sacarPuntosTotales("Christian Santos",a);
		sacarPrecio("Christian Santos", a);
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
			while (n!=null) {  // Procesado en funci�n de esquema devuelto el 5/12/16 (ver abajo (*))
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
		for(int j=0;j<jugadores.length;j++){
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
		String posicion="";
		int posNom=0;
		int posPos=0;
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
						if(cont<14)
						cont++;
					}
					if(cont==14){
						System.out.print("La posicion de "+ nombre +" es: ");
						posPos=l+2;
						posicion=equipo[i].substring(posPos, posPos+2);
						System.out.println(posicion);
						l=equipo[i].length();
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
		int posPos=0;
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
						if(cont<1)
						cont++;
					}
					if(cont==1){
						System.out.print("La puntuacion de "+ nombre +" es: ");
						posNom=l;
						posicion=equipo[i].substring(posNom-2, posNom);
						System.out.println(posicion);
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
						System.out.print("El precio de "+ nombre +" es: ");
						posNom=l;
						posicion=equipo[i].substring(posPre+1, posNom);
						System.out.println(posicion);
						l=equipo[i].length();	
					}
				}
				i=equipo.length;	}
		
		}
		
		
		
		return posicion;
	
		
	}
	
	
}


