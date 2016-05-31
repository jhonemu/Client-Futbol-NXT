package api.futbol.GUI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class VentanaAyuda extends JFrame {
	Container contenedor;
	JPanel panel1;
	JTextArea at;
	JScrollPane scroll;
	public VentanaAyuda(){
		super("Ayuda");
	}
	public void Lanzar(){
		contenedor = this.getContentPane();
		contenedor.setLayout(new BorderLayout());
	
		panel1 = new JPanel();
		contenedor.add(panel1);
		panel1.setLayout(new BorderLayout());
		at = new JTextArea();
		at.setEditable(false);
		at.setLineWrap(true);
		at.setWrapStyleWord(true);
		at.setText("Para iniciar un partido porfavor primero conecte a un robot desde la opcion, Conectar robot, luego puede dar a la opcion Iniciar partido y manejar el robot con los controles basicos en la pantalla ");
		scroll = new JScrollPane(at);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel1.add(scroll);
		setSize(200,110);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo (null);
	}
	
}
