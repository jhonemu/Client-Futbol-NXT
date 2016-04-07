package api.futbol.GUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@SuppressWarnings("serial")
public class VentanaRegistro extends JFrame implements ActionListener {

	Container contenedor; //contenedor principal
	JPanel panel1,panel2; //paneles contenedores secundarios
	JButton boton1;//boton de accion
	JTextField campotext;//campo de texto
	JPasswordField campopass;//campo contraseña
	
	public VentanaRegistro(){
		super("Registro");
	}
	
	
	public void lanzar() {
	
		campotext = new JTextField();
		campopass = new JPasswordField();
		contenedor = this.getContentPane();
		panel1 = new JPanel();
		panel2 = new JPanel();
		boton1 = new JButton("Aceptar");
		campotext = new JTextField();
		campopass = new JPasswordField();
		contenedor.setLayout(new BoxLayout(contenedor,BoxLayout.Y_AXIS));
		contenedor.add(panel1);
		contenedor.add(panel2);
		panel1.setLayout(new GridLayout(2,2));
		panel1.add(new JLabel("Nombre de usuario"));
		panel1.add(campotext);
		panel1.add(new JLabel("Conteaseña"));
		panel1.add(campopass);
		panel2.setLayout(new GridLayout(1,1,10,10));
		panel2.add(boton1);
		boton1.addActionListener(this);
		setSize(300,150);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo (null);
	}


	@Override
	public void actionPerformed(ActionEvent ac) {
		String user = campotext.getText();
		@SuppressWarnings("deprecation")
		String pass = campopass.getText();
		if(user.length() == 0 ){
			JOptionPane.showMessageDialog(null,"Porfavor llene los campos de texto","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		else if (pass.length() == 0){
			JOptionPane.showMessageDialog(null,"Porfavor llene los campos de texto","ERROR",JOptionPane.ERROR_MESSAGE);
		}
		else{
			WebResource webResource  = Main.client.resource(Main.URL+"LoginUs/registro");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("username", user);
			Params.add("password", pass);
			String respuesta = webResource.queryParams(Params).post(String.class);
			if(respuesta.equals("El nombre de usuario ya esta en uso")){
				JOptionPane.showMessageDialog(null,respuesta,"ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,respuesta,"Bienvenido",JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		}
	}
	
}
