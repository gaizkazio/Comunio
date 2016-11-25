package Connection;

import java.sql.*;
import java.util.*; 
import java.util.logging.*;
;

public class Bd {


	
		private static Exception lastError = null;  // Informaci�n de �ltimo error SQL ocurrido
		
		/** Inicializa una BD SQLITE y devuelve una conexi�n con ella
		 * @param nombreBD	Nombre de fichero de la base de datos
		 * @return	Conexi�n con la base de datos indicada. Si hay alg�n error, se devuelve null
		 */
		public static Connection initBD( String nombreBD ) {
			String url="C:\\Users\\gaizka\\Downloads\\Program\\Sqliteman-1.2.2\\ComunioBD";
			try {
			    Class.forName("org.sqlite.JDBC");
			    Connection con = DriverManager.getConnection("jdbc:sqlite:" + url );
				log( Level.INFO, "Conectada base de datos " + nombreBD, null );
			    return con;
			} catch (ClassNotFoundException | SQLException e) {
				lastError = e;
				log( Level.SEVERE, "Error en conexi�n de base de datos " + url, e );
				e.printStackTrace();
				return null;
			}
		}
		
		/** Devuelve statement para usar la base de datos
		 * @param con	Conexi�n ya creada y abierta a la base de datos
		 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
		 */
		public static Statement usarBD( Connection con ) {
			try {
				Statement statement = con.createStatement();
				statement.setQueryTimeout(30);  // poner timeout 30 msg
				return statement;
			} catch (SQLException e) {
				lastError = e;
				log( Level.SEVERE, "Error en uso de base de datos", e );
				e.printStackTrace();
				return null;
			}
		}
		
		

		
		/** Reinicia en blanco las tablas de la base de datos. 
		 * UTILIZAR ESTE M�TODO CON PRECAUCI�N. Borra todos los datos que hubiera ya en las tablas
		 * @param con	Conexi�n ya creada y abierta a la base de datos
		 * @return	sentencia de trabajo si se borra correctamente, null si hay cualquier error
		 */
		public static Statement reiniciarBD( Connection con ) {
			try {
				Statement statement = con.createStatement();
				statement.setQueryTimeout(30);  // poner timeout 30 msg
				statement.executeUpdate("drop table if exists hotel");
				statement.executeUpdate("drop table if exists habitacion");
				statement.executeUpdate("drop table if exists reserva");
				log( Level.INFO, "Reiniciada base de datos", null );
				return null;
			} catch (SQLException e) {
				log( Level.SEVERE, "Error en reinicio de base de datos", e );
				lastError = e;
				e.printStackTrace();
				return null;
			}
		}
		
		/** Cierra la base de datos abierta
		 * @param con	Conexi�n abierta de la BD
		 * @param st	Sentencia abierta de la BD
		 */
		public static void cerrarBD( Connection con, Statement st ) {
			try {
				if (st!=null) st.close();
				if (con!=null) con.close();
				log( Level.INFO, "Cierre de base de datos", null );
			} catch (SQLException e) {
				lastError = e;
				log( Level.SEVERE, "Error en cierre de base de datos", e );
				e.printStackTrace();
			}
		}
		
		/** Devuelve la informaci�n de excepci�n del �ltimo error producido por cualquiera 
		 * de los m�todos de gesti�n de base de datos
		 */
		public static Exception getLastError() {
			return lastError;
		}
		
		
		/////////////////////////////////////////////////////////////////////
		//                      M�todos privados                           //
		/////////////////////////////////////////////////////////////////////

		// Devuelve el string "securizado" para volcarlo en SQL
		// (Implementaci�n 1) Sustituye ' por '' y quita saltos de l�nea
		// (Implementaci�n 2) Mantiene solo los caracteres seguros en espa�ol
		private static String secu( String string ) {
			// Implementaci�n (1)
			// return string.replaceAll( "'",  "''" ).replaceAll( "\\n", "" );
			// Implementaci�n (2)
			StringBuffer ret = new StringBuffer();
			for (char c : string.toCharArray()) {
				if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ��������������.,:;-_(){}[]-+*=<>'\"�?�!&%$@#/\\0123456789".indexOf(c)>=0) ret.append(c);
			}
			return ret.toString();
		}
		

		/////////////////////////////////////////////////////////////////////
		//                      Logging                                    //
		/////////////////////////////////////////////////////////////////////
		
		private static Logger logger = null;
		// M�todo p�blico para asignar un logger externo
		/** Asigna un logger ya creado para que se haga log de las operaciones de base de datos
		 * @param logger	Logger ya creado
		 */
		public static void setLogger( Logger logger ) {
			Bd.logger = logger;
		}
		// M�todo local para loggear (si no se asigna un logger externo, se asigna uno local)
		private static void log( Level level, String msg, Throwable excepcion ) {
			if (logger==null) {  // Logger por defecto local:
				logger = Logger.getLogger( Bd.class.getName() );  // Nombre del logger - el de la clase
				logger.setLevel( Level.ALL );  // Loguea todos los niveles
				try {
					// logger.addHandler( new FileHandler( "bd-" + System.currentTimeMillis() + ".log.xml" ) );  // Y saca el log a fichero xml
					logger.addHandler( new FileHandler( "bd.log.xml", true ) );  // Y saca el log a fichero xml
				} catch (Exception e) {
					logger.log( Level.SEVERE, "No se pudo crear fichero de log", e );
				}
			}
			if (excepcion==null)
				logger.log( level, msg );
			else
				logger.log( level, msg, excepcion );
		}
		
		
		
	}
