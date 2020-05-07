package iis.uma.es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CntrlNuevoJugador implements ActionListener{
	
	NuevoJugadorPanel njPanel;
	ArrayList<Jugador> ljugadores;
	
	public CntrlNuevoJugador(NuevoJugadorPanel nj, ArrayList<Jugador> lj) {
		njPanel = nj;
		ljugadores = lj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// El 0 como identificador es provisional hasta que se inserte en la base de datos.
		Jugador j = new Jugador(0, njPanel.getNombreTf().getText(), 
				Integer.parseInt(njPanel.getEdadTf().getText()), 
				0);
		ljugadores.add(j);
		int jugadorId = InterfazJDBCBaseDatos.getInstance().inscribirNuevoJugador(j);
		j.setIdentificador(jugadorId);
		System.out.println("nuevo jugador añadido");
		
	}

}
