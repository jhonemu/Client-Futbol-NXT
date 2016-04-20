package api.futbol.GUI;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;


@SuppressWarnings("serial")
public class VentanaAdmin extends JFrame implements ActionListener {
	Container contenedor;
	JPanel panel1,panel2;
	JLabel explic;
	JLabel bien;
	JButton registraradmin,crearjugador,crearjugada,remjugador,remjugada;
	JTextField nom = new JTextField();
	JPasswordField contra = new JPasswordField();
	JButton reg = new JButton("Registrar");
	String[]aux={"prueba","prueba2"};

	JComboBox<String> lista = new JComboBox<String>(aux);
	public VentanaAdmin(){
		super("Administrador Futbol NXT");
	}
	public void lanzar(){
		contenedor = this.getContentPane();
		contenedor.setLayout(new GridLayout(1,2));
		panel1 = new JPanel();
		panel2 = new JPanel();
		registraradmin = new JButton("Registrar Administrador");
		crearjugador = new JButton("Crear nuevo Jugadro");
		crearjugada = new JButton("Crear nueva Jugada");
		remjugador = new JButton("Remover un Jugador");
		remjugada = new JButton("Remover una Jugada");
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
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
		registraradmin.addActionListener(this);
		setSize(500,515);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo (null);
	}
	@Override
	public void actionPerformed(ActionEvent clic) {
		String s = (String)clic.getActionCommand();
		if(s.equals("Registrar Administrador")){
			panel2.removeAll();
			panel2.updateUI();
			panel2.add(new JLabel("Nombre de usuario"));
			nom.setMaximumSize(new Dimension(450,23));
			panel2.add(nom);
			panel2.add(new JLabel("Contraseña"));
			contra.setMaximumSize(new Dimension(450,23));
			panel2.add(contra);
			panel2.add(reg);
			reg.addActionListener(new ActionListener(){
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(ActionEvent clic) {
					String Usuario =nom.getText();
					String contras =contra.getText();
					if(Usuario.length() == 0){
						JOptionPane.showMessageDialog(null,"Porfavor ingrese un usuario y clave validos","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else if(contras.length()==0){
						JOptionPane.showMessageDialog(null,"Porfavor ingrese un usuario y clave validos","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else{
						WebResource webResource  = Main.client.resource(Main.URL+"LoginUs/radmin");
						MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
						Params.add("username", Usuario);
						Params.add("password",contras);
						String respuesta = webResource.queryParams(Params).post(String.class);
						if(respuesta.equals("El nombre de usuario ya esta en uso")){
							JOptionPane.showMessageDialog(null,respuesta,"ERROR",JOptionPane.ERROR_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null,respuesta,"Bienvenido",JOptionPane.INFORMATION_MESSAGE);
							
						}
					}
				}
			});
		}
	}
}
