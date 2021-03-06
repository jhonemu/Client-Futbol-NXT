package api.futbol.GUI;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;



@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame implements ActionListener  {
	Container contenedor;
	
	JPanel panel1,panel2,panel3,panel4,panel6,panel7,panel8,panel9,panel10,panel11,panel12,panel13;
	JMenuBar barra;
	JMenu archivo,acciones,ayuda;
	public static JMenuItem help,inciarpar,conectar,salir,regAdmin,listjugadas,crearjug,finpartido,consultarEXjugada,cargar,consultarEXjugador,crearjugcompleja,listJugadores,Rgolafavor,Rgolencontra;
	ImageIcon icon;
	public static JPanel panel5;
	public static JTextArea areah;
	public static ArrayList<String> jugadasdelantero = new ArrayList<>();
	public static ArrayList<String> jugadasarquero = new ArrayList<>();
	public static JLabel historia,cancha,marcador;
	public static int gafavor =0;
	public static int gcontra =0;
	JScrollPane scroll;
	Image s;
	public static Imagen im= new Imagen();
	public static JButton adelante,atras,izquierda,derecha,patear,correr,chutar,runAtras , ejecutar, parar,consultar;
	public static int tip = 0;
	public static JComboBox<String> jugadascomplejas,options;
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
		panel12 = new JPanel();
		panel13 = new JPanel();
		marcador = new JLabel(gafavor+"-"+gcontra);
		jugadascomplejas = new JComboBox<String>();
		options = new JComboBox<String>();
		consultar = new JButton("Consultar");
		ejecutar = new JButton("Ejecutar");
		parar = new JButton("Parar");
		adelante = new JButton("Trote");
		correr = new JButton("Correr");
		atras =  new JButton("Atras");
		runAtras = new JButton("Correr Atras");
		izquierda = new JButton("Izquierda");
		derecha = new JButton("Derecha");
		patear =  new JButton("Chute");
		chutar = new JButton("Patear");
		
		historia = new JLabel("Historia");
		areah = new JTextArea(5,20);
		areah.setEditable(false);
		areah.setLineWrap(true);
		areah.setWrapStyleWord(true);
		areah.setText("Zona de informacion");
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
		help = new JMenuItem("Ayuda");
		Rgolafavor = new JMenuItem("Registrar gol a favor");
		Rgolencontra = new JMenuItem("Registrar gol en contra");
		inciarpar = new JMenuItem("Iniciar partido");
		conectar =new JMenuItem("Conectar a robot");
		
		finpartido = new JMenuItem("Finalizar partido");
		
		salir = new JMenuItem("Salir");
		
		//items
		//archivo
		archivo.add(consultarEXjugada);
		archivo.add(consultarEXjugador);
		
		archivo.add(salir);
		//archivo
		//acciones
		
		acciones.add(inciarpar);
		acciones.add(conectar);
		acciones.add(Rgolafavor);
		acciones.add(Rgolencontra);
		acciones.add(finpartido);
		ayuda.add(help);
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
		options.setMaximumSize(new Dimension(450,23));
		panel3.add(options);
		panel3.add(consultar);
		panel3.add(historia);
		panel3.add(scroll);
		panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
		panel4.add(panel5);
		panel4.add(panel6);
		cancha = new JLabel(new ImageIcon("src\\images\\can.png"));
		cancha.setLayout(new BorderLayout());
		panel5.add(cancha);
		cancha.add(im);
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
		panel8.setLayout(new BoxLayout(panel8,BoxLayout.Y_AXIS));
		panel8.add(panel12);
		panel8.add(panel13);
		panel13.setLayout(new GridLayout(1,2,10,10));
		
		panel13.add(ejecutar);
		
		ejecutar.setEnabled(false);
		Rgolafavor.setEnabled(false);
		inciarpar.setEnabled(false);
		Rgolencontra.setEnabled(false);
		finpartido.setEnabled(false);
		adelante.setEnabled(false);
		correr.setEnabled(false);
		atras.setEnabled(false);
		runAtras.setEnabled(false);
		izquierda.setEnabled(false);
		derecha.setEnabled(false);
		patear .setEnabled(false);
		chutar.setEnabled(false);
		panel12.setLayout(new GridLayout(3,1,10,10));
		panel12.add(new JLabel("Marcador"));
		panel12.add(marcador);
		panel12.add(jugadascomplejas);
		consultar.addActionListener(this);
		ejecutar.addActionListener(this);
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
		consultarEXjugada.addActionListener(new OyenteMenu());
		consultarEXjugador.addActionListener(new OyenteMenu());
		inciarpar.addActionListener(new OyenteMenu());
		help.addActionListener(new OyenteMenu());
		finpartido.addActionListener(new OyenteMenu());
		Rgolafavor.addActionListener(new OyenteMenu());
		Rgolencontra.addActionListener(new OyenteMenu());
		setIconImage(icon.getImage());
		setSize(900,650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo (null);
	}


	@Override
	public void actionPerformed(ActionEvent arg) {
		String s = (String)arg.getActionCommand();
	if(s.equals("Consultar")){
		if(tip == 1){
			WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/explicacion");
			JSONObject respuesta = webResource.queryParam("nombre", (String) options.getSelectedItem()).get(JSONObject.class);
			try {
				String nombre = respuesta.getString("nombre");
				String fecha = respuesta.getString("fecha");
				String autor = respuesta.getString("auto");
				String exp = respuesta.getString("expli");
				areah.setText("");
				areah.append("La jugada: "+ nombre+"\n"+ "Fue creada el: " + fecha+"\n"+ "Por: "+ autor+"\n"+"Y es una: "+exp);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else if(tip == 2){
			WebResource webResource  = Main.client.resource(Main.URL+"jugador/info");
			JSONObject respuesta = webResource.queryParam("nombre", (String) options.getSelectedItem()).get(JSONObject.class);
			
			try {
				
				areah.setText("");
				if(respuesta.get("posicion").equals("Arquero")){
				areah.append("Eljugador: "+respuesta.get("nombre") + "\n"+ "Juega de: " + respuesta.get("posicion")+ "\n"+ "Con el dorsal: " + respuesta.get("dorsal") +"\n"+ "Lleva "+ respuesta.get("tiempo sin gol") + " Sin gol" );
				}else if(respuesta.get("posicion").equals("Delantero")){
					areah.append("Eljugador: "+respuesta.get("nombre") + "\n"+ "Juega de: " + respuesta.get("posicion")+ "\n"+ "Con el dorsal: " + respuesta.get("dorsal") +"\n"+ "Lleva "+ respuesta.get("goles marcados") + " goles" );
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}else if(s.equals("Ejecutar")){
		WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/ejecutar");
		MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
		Params.add("nombre", (String)jugadascomplejas.getSelectedItem());
		Params.add("nombrejug", Posicion.activo);
		String respuesta = webResource.queryParams(Params).get(String.class);
		System.out.println(respuesta);
		String[] pos = respuesta.split(",");
		Main.posicion.calcular(Integer.valueOf(pos[0]),Integer.valueOf(pos[1]));
		VentanaPrincipal.cancha.add(VentanaPrincipal.im);
		VentanaPrincipal.cancha.revalidate();
		VentanaPrincipal.cancha.repaint();

	}
}
}
