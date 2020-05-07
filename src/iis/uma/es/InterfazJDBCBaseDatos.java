package iis.uma.es;

import java.sql.*;
import java.util.ArrayList;

public class InterfazJDBCBaseDatos extends ConexionConBasedeDatos {

	private Connection conn;

	private static InterfazJDBCBaseDatos instanciaInterfaz = null;

	private InterfazJDBCBaseDatos() {
		try {
			// create connection for database called DBB_SCHEMA in a server installed in
			// DB_URL, with a user USER with password PASS
			conn = DriverManager.getConnection(DB_URL + "/" + DB_SCHEMA, USER, PASS);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static InterfazJDBCBaseDatos getInstance() {
		if (instanciaInterfaz == null) {
			instanciaInterfaz = new InterfazJDBCBaseDatos();
		}
		return instanciaInterfaz;
	}

	public ArrayList<Equipo> listaEquipos() {
		ArrayList<Equipo> lEquipos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM Equipo";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					lEquipos.add(new Equipo(id, name));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEquipos;
	}

	public ArrayList<Jugador> listaJugadores() {
		ArrayList<Jugador> lEquipos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM Jugador";
		Statement querySt;
		try {
			querySt = conn.createStatement();
			ResultSet rs = querySt.executeQuery(selectQueryBody);
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int edad = rs.getInt(3);
					int idEquipo = rs.getInt(4);
					lEquipos.add(new Jugador(id, name, edad, idEquipo));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEquipos;
	}

	public ArrayList<Jugador> listaJugadoresDeUnEquipo(int idEq) {
		ArrayList<Jugador> lEquipos = new ArrayList<>();
		String selectQueryBody = "SELECT * FROM Jugador WHERE idEquipo=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(selectQueryBody);
			preparedStatement.setInt(1, idEq);
			ResultSet rs = preparedStatement.executeQuery();
			// position result to first
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int edad = rs.getInt(3);
					int idEquipo = rs.getInt(4);
					lEquipos.add(new Jugador(id, name, edad, idEquipo));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lEquipos;
	}

	public int inscribirNuevoJugador(Jugador j) {
		int jugadorId=0;
		String insertBody = "INSERT INTO Jugador (nombre, edad, idEquipo ) VALUES (?, ?, null)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(insertBody,
					PreparedStatement.RETURN_GENERATED_KEYS);
//			preparedStatement.setInt(1, j.getIdentificador());
//			preparedStatement.setString(2, j.getNombre());
//			preparedStatement.setInt(3, j.getEdad());
			preparedStatement.setString(1, j.getNombre());
			preparedStatement.setInt(2, j.getEdad());
			int res = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			while(rs.next()) {
				jugadorId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jugadorId;

	}

	// Equipo e = null para quitarlo de un equipo
	public void inscribirJugadorEnEquipo(Jugador j, Equipo e) {
		PreparedStatement preparedStatement = null;
		String updateBody = null;
		try {
			if (e == null) {
				updateBody = "UPDATE Jugador SET idEquipo = null WHERE (identificador = ?)";
				preparedStatement = conn.prepareStatement(updateBody);
				preparedStatement.setInt(1, j.getIdentificador());
			} else {
				updateBody = "UPDATE Jugador SET idEquipo = ? WHERE (identificador = ?)";
				preparedStatement = conn.prepareStatement(updateBody);
				preparedStatement.setInt(1, e.getIdentificador());
				preparedStatement.setInt(2, j.getIdentificador());
			}
			int res = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}

}
