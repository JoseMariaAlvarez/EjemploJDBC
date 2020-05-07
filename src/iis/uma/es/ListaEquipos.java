package iis.uma.es;

import java.util.ArrayList;

public class ListaEquipos {
	ArrayList<Equipo> lEquipos;
	
	public ListaEquipos() {
		lEquipos = new ArrayList<>();
	}
	
	public void agregarNuevoEquipo(Equipo e) {
		lEquipos.add(e);
	}
	
	public void quitarEquipo(Equipo e) {
		lEquipos.remove(e);
	}
}
