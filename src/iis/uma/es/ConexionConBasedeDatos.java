package iis.uma.es;

import java.util.ArrayList;

public abstract class ConexionConBasedeDatos {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://database-iis.cobadwnzalab.eu-central-1.rds.amazonaws.com";
	static final String DB_SCHEMA = "pruebaLigadb";

	//  Database credentials
	static final String USER = "usuarioPrueba";
	static final String PASS = "pruebausuario";

	public abstract ArrayList<Equipo> listaEquipos();
	public abstract ArrayList<Jugador> listaJugadores();
	public abstract ArrayList<Jugador> listaJugadoresDeUnEquipo(int idEq);
	public abstract int inscribirNuevoJugador(Jugador j);
}
