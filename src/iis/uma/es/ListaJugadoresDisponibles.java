package iis.uma.es;

import java.util.ArrayList;

public class ListaJugadoresDisponibles {
	ArrayList<Jugador> lJugadoresDisponibles;
	
	public ListaJugadoresDisponibles() {
		lJugadoresDisponibles = new ArrayList<>();
	}
	
	public void incluirJugadorDisponible(Jugador j) {
		lJugadoresDisponibles.add(j);
	}
	
	public void quitarJugadorDisponible(Jugador j) {
		lJugadoresDisponibles.remove(j);
	}
}
