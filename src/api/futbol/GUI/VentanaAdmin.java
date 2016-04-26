package api.futbol.GUI;


import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VentanaAdmin extends JFrame {
	Container contenedor;
	JPanel panel1;
	public static JPanel panel2;
	JLabel explic;
	JLabel bien;
	JButton registraradmin,crearjugador,crearjugada,remjugador,remjugada;
	
	
	
	public VentanaAdmin(){
		super("Administrador Futbol NXT");
	}
	public void lanzar(){
		contenedor = this.getContentPane();
		contenedor.setLayout(new GridLayout(1,2,10,10));
		panel1 = new JPanel();
		panel2 = new JPanel();
		registraradmin = new JButton("Registrar Administrador");
		crearjugador = new JButton("Crear nuevo Jugador");
		crearjugada = new JButton("Crear nueva Jugada");
		remjugador = new JButton("Remover un Jugador");
		remjugada = new JButton("Remover una Jugada");
		//panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		panel1.setLayout(new GridLayout(10,1,10,10));
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
		bien = new JLabel("Bienvenido Administrador");
		explic = new JLabel("<html><boddy>Porfavor de clic en la opcion que desea ejecutar y realice la accion deseada</boddy></html>");
		//panel1.setBackground(Color.BLACK);
		//panel2.setBackground(Color.BLUE);

		contenedor.add(panel1);
		contenedor.add(panel2);
		panel1.add(bien);
		panel1.add(registraradmin);
		panel1.add(crearjugada);
		panel1.add(remjugada);
		panel1.add(crearjugador);
		panel1.add(remjugador);
		panel2.add(explic);
		registraradmin.addActionListener( new OyenteBotonAdmin());
		crearjugada.addActionListener(new OyenteBotonAdmin());
		remjugada.addActionListener(new OyenteBotonAdmin());
		crearjugador.addActionListener(new OyenteBotonAdmin());
		remjugador.addActionListener(new OyenteBotonAdmin());
		setSize(500,515);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo (null);
	}
	
}
