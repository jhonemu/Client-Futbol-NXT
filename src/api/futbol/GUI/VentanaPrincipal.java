package api.futbol.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	Container contenedor;
	JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10,panel11;
	JMenuBar barra;
	JMenu archivo,acciones,ayuda;
	JMenuItem pausar,inciarpartido,conectar,salir,regAdmin,listjugadas,crearjug,finpartido,consultarEXjugada,cargar,consultarEXjugador,crearjugcompleja,listJugadores;
	ImageIcon icon;
	JTextArea areah;
	JLabel historia,cancha;
	JScrollPane scroll;
	Image s;
	JButton adelante,atras,izquierda,derecha,patear,correr,chutar,runAtras;
	public VentanaPrincipal(){
		super("Futbol-NXT");
	}
	
	public void lanzarAd(){
		contenedor = this.getContentPane();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		panel9 = new JPanel();
		panel10 = new JPanel();
		panel11 = new JPanel();
		adelante = new JButton("Trote");
		correr = new JButton("Correr");
		atras =  new JButton("Atras");
		runAtras = new JButton("Correr Atras");
		izquierda = new JButton("Izquierda");
		derecha = new JButton("Derecha");
		patear =  new JButton("Chute");
		chutar = new JButton("Patear");
		panel8.setBackground(Color.BLUE);
		historia = new JLabel("Historia");
		areah = new JTextArea(5,20);
		areah.setEditable(false);
		areah.setLineWrap(true);
		areah.setWrapStyleWord(true);
		scroll = new JScrollPane(areah);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//espacio menu
		barra = new JMenuBar();
		archivo = new JMenu("Archivo");
		acciones = new JMenu("Acciones");
		ayuda = new JMenu("Ayuda");
		//items
	
		consultarEXjugada = new JMenuItem("Consultar Explicacion de una jugada");
		consultarEXjugador = new JMenuItem("Consultar informacion de un jugador");
		
		listJugadores = new JMenuItem("Lista de jugadores");
		listjugadas = new JMenuItem("Lista de jugadas");
		inciarpartido = new JMenuItem("Iniciar partido");
		conectar =new JMenuItem("Conectar a robot");
		
		finpartido = new JMenuItem("Finalizar partido");
		cargar = new JMenuItem("Reanudar");
		salir = new JMenuItem("Salir");
		pausar = new JMenuItem("Pausar");
		//items
		//archivo
		archivo.add(consultarEXjugada);
		archivo.add(consultarEXjugador);
		archivo.add(listJugadores);
		archivo.add(listjugadas);
		archivo.add(salir);
		//archivo
		//acciones
		
		acciones.add(inciarpartido);
		acciones.add(conectar);
		
		acciones.add(cargar);
		acciones.add(pausar);
		acciones.add(finpartido);
		//acciones
		barra.add(archivo);
		barra.add(acciones);
		barra.add(ayuda);
		//espacio menu.....
		contenedor.setLayout(new BorderLayout());
		contenedor.add(panel1,BorderLayout.NORTH);
		contenedor.add(panel2,BorderLayout.CENTER);
		panel1.setLayout(new BorderLayout());
		panel1.add(barra,BorderLayout.NORTH);
		panel2.setLayout(new BorderLayout());
		panel2.add(panel3,BorderLayout.EAST);
		panel2.add(panel4, BorderLayout.CENTER);
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
		panel3.add(historia);
		panel3.add(scroll);
		panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
		panel4.add(panel5);
		panel4.add(panel6);
		cancha = new JLabel(new ImageIcon("src\\images\\can.png"));
		cancha.setLayout(new BorderLayout());
		panel5.add(cancha);
		panel6.setLayout(new GridLayout(1,2));
		panel6.add(panel7);
		panel7.setLayout(new BorderLayout());
		panel7.add(panel9, BorderLayout.NORTH);
		panel9.setLayout(new GridLayout(1,2));
		panel9.add(adelante);
		panel9.add(correr);
		panel7.add(panel10, BorderLayout.SOUTH);
		panel10.setLayout(new GridLayout(1,2));
		panel10.add(atras);
		panel10.add(runAtras);
		panel7.add(izquierda, BorderLayout.WEST);
		panel7.add(derecha, BorderLayout.EAST);
		panel7.add(panel11, BorderLayout.CENTER);
		panel11.setLayout(new GridLayout(1,2));
		panel11.add(patear);
		panel11.add(chutar);
		panel6.add(panel8);
		adelante.addActionListener(new OyenteButton());
		correr.addActionListener(new OyenteButton());
		atras.addActionListener(new OyenteButton());
		runAtras.addActionListener(new OyenteButton());
		izquierda.addActionListener(new OyenteButton());
		derecha.addActionListener(new OyenteButton());
		patear.addActionListener(new OyenteButton());
		chutar.addActionListener(new OyenteButton());
		icon = new ImageIcon("src\\images\\ic_launcher.png");
		salir.addActionListener(new OyenteMenu());
		conectar.addActionListener(new OyenteMenu());
		setIconImage(icon.getImage());
		setSize(900,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo (null);
	}
	
	
}
