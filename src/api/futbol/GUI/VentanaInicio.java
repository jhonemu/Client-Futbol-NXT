package api.futbol.GUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.ws.rs.core.MultivaluedMap;


import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@SuppressWarnings("serial")
public class VentanaInicio extends JFrame implements ActionListener{
	Container contenedor;// Contenedor principal
	JPanel panel1,panel2,panel3,panel4,panel5; //paneles contenedores
	JButton boton1,boton2,boton3; //botones de acciones
	JTextField campotxt1; 
	JPasswordField campotxt2;//campos de texto
	JScrollPane scroll1; //scroll
	JLabel label1,label2,label3;//etiquetas
	JTextArea areatxt1;
	ImageIcon icon;
	public VentanaInicio(){
		super("Futbol-NXT");
	}
	public void lanzar(){
		//inicializacion
		contenedor = this.getContentPane();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		boton1 = new JButton("Iniciar Sesión");
		boton2 = new JButton("Salir");
		boton3 = new JButton("Registrarse");
		campotxt1 = new JTextField();
		campotxt2 = new JPasswordField();

		areatxt1 = new JTextArea(8,30);
		areatxt1.setEditable(false);
		areatxt1.setLineWrap(true);
		areatxt1.setWrapStyleWord(true);
		areatxt1.setText("Este programa fue realizado por: Jhon Eider Murillo Usuga estudiande de ingenieria de sistemas semestre numero 4, como proyecto academico especial(PAE) para el profesor Jaime Guzman, el cual consiste en la implementacion de un webservice para el funcionamiento de un robot Lejos-NXT con el fin de aplicarlo en un partido de futbol,para esto se creo las interfaces graficas en java y las acciones realizadas en los botones y por el robot seran una consulta para un webservice restful, el cual retornara un mensaje correspondiente a la accion.");
		scroll1 = new JScrollPane(areatxt1);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		label1 = new JLabel("Bienvenido a Futbol-NXT", SwingConstants.CENTER);
		label2 =new JLabel("<html><body>Porfavor ingrese un nombre de usuario y contraseña validos <br> para iniciar el programa o dar clic en registrarse para crear usuario</body></html>" );
		//layouts
		contenedor.setLayout(new BoxLayout(contenedor,BoxLayout.Y_AXIS));
		contenedor.add(panel1);
		contenedor.add(panel2);
		contenedor.add(panel3);
		contenedor.add(panel4);
		contenedor.add(panel5);
		panel4.setLayout(new GridLayout(2,2));
		panel5.setLayout(new GridLayout(1,3,5,5));
		panel1.add(label1);
		panel2.add(scroll1);
		panel3.add(label2);
		panel4.add(new JLabel("Usuario"));
		panel4.add(campotxt1);
		panel4.add(new JLabel("Contraseña"));
		panel4.add(campotxt2);
		panel5.add(boton1);
		panel5.add(boton2);
		panel5.add(boton3);
		//iniciar
		boton1.addActionListener(this);
		boton2.addActionListener(this);
		boton3.addActionListener(this);
		icon = new ImageIcon("src\\images\\ic_launcher.png");
		
		setIconImage(icon.getImage());
		setSize(400,350);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo (null);


	}
	@Override
	public void actionPerformed(ActionEvent accion) {

		try{
			String s = (String)accion.getActionCommand();
			if(s.equals("Iniciar Sesión")){
				String Usuario = campotxt1.getText();
				@SuppressWarnings("deprecation")
				String clave = campotxt2.getText();
				if(Usuario.length() == 0){
					JOptionPane.showMessageDialog(null,"Porfavor ingrese un usuario y clave validos","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else if(clave.length()==0){
					JOptionPane.showMessageDialog(null,"Porfavor ingrese un usuario y clave validos","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else{
					
					WebResource webResource  = Main.client.resource(Main.URL+"LoginUs/confirmar");
					WebResource webResource2  = Main.client.resource(Main.URL+"carga/datos");
					MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
					Params.add("username", Usuario);
					Params.add("password", clave);
					String respuesta2 = webResource2.get(String.class);
					String respuesta = webResource.queryParams(Params).get(String.class);
					
					
					if(respuesta.equals("Usuario General")){
						JOptionPane.showMessageDialog(null,respuesta2,"ERROR",JOptionPane.INFORMATION_MESSAGE);
						Main.ventanainicio.dispose();
						Main.Usuario = "General";
						Main.ventanaPrincipal.lanzarAd();
					}
					else if(respuesta.equals("Usuario no valido")){
						JOptionPane.showMessageDialog(null,"Porfavor ingrese un usuario y clave validos","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null,respuesta2,"ERROR",JOptionPane.INFORMATION_MESSAGE);
						Main.ventanainicio.dispose();
						Main.Usuario = respuesta;
						Main.ventanaAd.lanzar();
					}
				}
			}
			else if(s.equals("Salir")){
				Object [] textOpcion = { "Si", "NO"};
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea salir del sistema?", "salir del sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,textOpcion,null);
				if(opcion == 0){
					System.exit(0);
				}
			}
			else if(s.equals("Registrarse")){
				new VentanaRegistro().lanzar();
			}

		}catch(ClientHandlerException e){
			JOptionPane.showMessageDialog(null,"Servidor desconectado","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
}



