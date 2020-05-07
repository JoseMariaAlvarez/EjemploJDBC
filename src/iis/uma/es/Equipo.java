package iis.uma.es;

import java.util.ArrayList;

public class Equipo {
	int identificador;
	String nombre;
	ArrayList<Jugador> plantilla;
	
	public Equipo(int id, String n) {
		identificador = id;
		nombre = n;
		plantilla = new ArrayList<>();
	}
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Jugador> getPlantilla() {
		return plantilla;
	}
	
	public void inscribirJugador(Jugador j) {
		plantilla.add(j);
	}
	
	public void dardeBajaJugador(Jugador j) {
		plantilla.remove(j);
	}
	
	public String toString() {
		return nombre;
	}
	
}
