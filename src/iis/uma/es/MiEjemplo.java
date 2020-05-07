package iis.uma.es;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class MiEjemplo {

	static ConexionConBasedeDatos accesoBD;
	
	private static void createAndShowGUI(ArrayList<Equipo>le, ArrayList<Jugador> lj) {
		// Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(createJTabbedPane(le, lj));

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		accesoBD = InterfazJDBCBaseDatos.getInstance();
		ArrayList<Equipo> listEquipos = accesoBD.listaEquipos();
		ArrayList<Jugador> listJugadores = accesoBD.listaJugadores();
		for (Equipo eq : listEquipos) {
			System.out.print(eq.identificador);
			System.out.println(" " + eq.nombre);
			ArrayList<Jugador> plantillaEquipo = accesoBD.listaJugadoresDeUnEquipo(eq.identificador);
			for (Jugador jgd: plantillaEquipo) {
				System.out.print(jgd.identificador);
				System.out.print(" " + jgd.nombre);
				System.out.print(" " + jgd.edad);
				System.out.println(" " + jgd.idEquipo);
				eq.inscribirJugador(jgd);
			}
			
		}
		ListIterator<Jugador> it = listJugadores.listIterator();
		while(it.hasNext()) {
			if (it.next().getIdEquipo() != 0) {
				it.remove();
			}
		}
		for (Jugador jgd : listJugadores) {
			System.out.print(jgd.identificador);
			System.out.print(" " + jgd.nombre);
			System.out.print(" " + jgd.edad);
			System.out.println(" " + jgd.idEquipo);
		}

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(listEquipos, listJugadores);
			}
		});
	}

	public static JTabbedPane createJTabbedPane(ArrayList<Equipo> le, ArrayList<Jugador> lj) {
		JTabbedPane tabbedPane = new JTabbedPane();

		NuevoJugadorPanel panel1 = new NuevoJugadorPanel(lj);
		CntrlNuevoJugador controladorNuevoJugador = new CntrlNuevoJugador(panel1, lj);
		panel1.controlador(controladorNuevoJugador);
		tabbedPane.addTab("Añadir nuevo jugador", null, panel1, "Añadir nuevo jugador");

		IncluirJugadorPanel panel2 = new IncluirJugadorPanel(le, lj);
		CntrlIncluirJugador controladorIncluirJugador = new CntrlIncluirJugador(panel2, le, lj);
		panel2.controlador(controladorIncluirJugador);
		tabbedPane.addTab("Adscribir/Dar de baja jugadores", null, panel2, "Does nothing");
		panel2.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e ){
	            panel2.actualizarListaJugadoresSinEquipo();
	        }

	        public void componentHidden ( ComponentEvent e ) {}
	    } );

//		JComponent panel2 = createJSplitPane();
//		tabbedPane.addTab("Split pane", null, panel2, "Does nothing");

		return tabbedPane;
	}
	protected static JSplitPane createJSplitPane() {
		// Create a split pane with the two scroll panes in it.
		JPanel leftPanel = new JPanel();
		JLabel leftLabel = new JLabel("Panel izquierdo");
		leftPanel.add(leftLabel);
		leftPanel.setBorder(BorderFactory.createLineBorder(new Color(150, 172, 107), 5));

		JLabel etiquetaConImagen = new JLabel();

		ImageIcon spIcon = new ImageIcon("images/spiderman.jpg");

		etiquetaConImagen.setIcon(spIcon);
		if (spIcon != null) {
			etiquetaConImagen.setText(null);
		}
		JScrollPane scrollPane = new JScrollPane(etiquetaConImagen);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, scrollPane);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		leftPanel.setMinimumSize(minimumSize);
		scrollPane.setMinimumSize(minimumSize);

		return splitPane;
	}

}
