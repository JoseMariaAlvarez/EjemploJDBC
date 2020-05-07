package iis.uma.es;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CntrlIncluirJugador implements ActionListener {
	IncluirJugadorPanel ijPanel;
	ArrayList<Equipo> equipos;
	ArrayList<Jugador> jugadoresDisponibles;

	public CntrlIncluirJugador(IncluirJugadorPanel ijp, ArrayList<Equipo> le, ArrayList<Jugador> lj) {
		ijPanel = ijp;
		equipos = le;
		jugadoresDisponibles = lj;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Equipo equipoSeleccionado = (Equipo) (ijPanel.getCbSeleccEquipo().getSelectedItem());
		if (e.getActionCommand().equals(IncluirJugadorPanel.CB_SELECCION_EQUIPO_ACTION_COMMAND)) {
			System.out.println(equipoSeleccionado.toString());
			ijPanel.actualizarListaJugadoresEquipoSeleccionado(equipoSeleccionado.getPlantilla());
		} else if (e.getActionCommand().equals(IncluirJugadorPanel.BT_ADSCRIBIR_JUGADOR_ACTION_COMMAND)) {
			for (Jugador j : ijPanel.getJugadoresSeleccionadosSinEquipo()) {
				jugadoresDisponibles.remove(j);
				equipoSeleccionado.inscribirJugador(j);
				InterfazJDBCBaseDatos.getInstance().inscribirJugadorEnEquipo(j, equipoSeleccionado);
			}
			ijPanel.actualizarListaJugadoresEquipoSeleccionado(equipoSeleccionado.getPlantilla());
			ijPanel.actualizarListaJugadoresSinEquipo();
		} else if (e.getActionCommand().equals(IncluirJugadorPanel.BT_DARDEBAJA_JUGADOR_ACTION_COMMAND)) {
			for (Jugador j : ijPanel.getJugadoresSeleccionadosDelEquipoSeleccionado()) {
				equipoSeleccionado.dardeBajaJugador(j);
				jugadoresDisponibles.add(j);
				InterfazJDBCBaseDatos.getInstance().inscribirJugadorEnEquipo(j, null);
			}
			ijPanel.actualizarListaJugadoresEquipoSeleccionado(equipoSeleccionado.getPlantilla());
			ijPanel.actualizarListaJugadoresSinEquipo();
		}
	}

}
