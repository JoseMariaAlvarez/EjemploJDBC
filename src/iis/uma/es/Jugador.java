package iis.uma.es;

public class Jugador {
	int identificador;
	String nombre;
	int edad;
	int idEquipo;

	public Jugador(int id, String n, int e, int eq) {
		identificador = id;
		nombre = n;
		edad = e;
		idEquipo = eq;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	public String toString() {
		return nombre;
	}
}
