package Utilidades;


// Imports relacionados con el proceso
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

// Imports de librería externa  -  https://sourceforge.net/projects/htmlparser/
import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;


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

	public static void main(String[] args) {
		// procesaWeb( "http://www.comuniazo.com/comunio/equipos/alaves" );
		pruebaDatosJugadoresComuniazo( "http://www.comuniazo.com/comunio/equipos/alaves" );
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
	public static void pruebaDatosJugadoresComuniazo( String dirWeb ) {
		URL url;
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

}
TestWeb.java
