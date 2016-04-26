package api.futbol.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class OyenteBotonAdmin implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent clic) {
		String s = (String)clic.getActionCommand();
		if(s.equals("Registrar Administrador")){
			JTextField nom = new JTextField();
			JPasswordField contra = new JPasswordField();
			JButton reg = new JButton("Registrar");
			VentanaAdmin.panel2.removeAll();
			VentanaAdmin.panel2.updateUI();
			VentanaAdmin.panel2.add(new JLabel("Nombre de usuario"));
			nom.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(nom);
			VentanaAdmin.panel2.add(new JLabel("Contraseña"));
			contra.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(contra);
			VentanaAdmin.panel2.add(reg);
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
		}else if(s.equals("Crear nueva Jugada")){
			VentanaAdmin.panel2.removeAll();
			VentanaAdmin.panel2.updateUI();
			VentanaAdmin.panel2.add(new JLabel("Nombre de la jugada"));
			JTextField nombre =new JTextField();
			nombre.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(nombre);
			VentanaAdmin.panel2.add(new JLabel("Fecha en formato dd-mm-aa"));
			JTextField fecha =new JTextField();
			fecha.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(fecha);
			VentanaAdmin.panel2.add(new JLabel("<html><boddy>Porfavor escoja minimo 2 jugadas primitivas</boddy></html>"));
			String[]aux={"","Trotar","Correr","Girar a la izquierda","Girar a la derecha","Chute","Patear","Ir atras","Correr atras"};

			JComboBox<String> lista = new JComboBox<String>(aux);
			JComboBox<String> lista1 = new JComboBox<String>(aux);
			JComboBox<String> lista2 = new JComboBox<String>(aux);
			JComboBox<String> lista3 = new JComboBox<String>(aux);
			JComboBox<String> lista4 = new JComboBox<String>(aux);
			JComboBox<String> lista5 = new JComboBox<String>(aux);
			JComboBox<String> lista6 = new JComboBox<String>(aux);
			JComboBox<String> lista7 = new JComboBox<String>(aux);
			JComboBox<String> lista8 = new JComboBox<String>(aux);
			JComboBox<String> lista9 = new JComboBox<String>(aux);
			
			VentanaAdmin.panel2.add(lista);
			VentanaAdmin.panel2.add(lista1);
			VentanaAdmin.panel2.add(lista2);
			VentanaAdmin.panel2.add(lista3);
			VentanaAdmin.panel2.add(lista4);
			VentanaAdmin.panel2.add(lista5);
			VentanaAdmin.panel2.add(lista6);
			VentanaAdmin.panel2.add(lista7);
			VentanaAdmin.panel2.add(lista8);
			VentanaAdmin.panel2.add(lista9);
			JButton crear = new JButton("Crear");
			VentanaAdmin.panel2.add(crear);
			crear.addActionListener(new ActionListener(){

				
				@Override
				public void actionPerformed(ActionEvent arg) {
					
				}
				
			});
		}else if(s.equals("Crear nuevo Jugador")){
			System.out.println("holi");
		}else if(s.equals("Remover una Jugada")){
			System.out.println("holi");
		}else if(s.equals("Remover un Jugador")){
			System.out.println("holi");
		}
	}

}
