package api.futbol.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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
						System.out.println(Params);
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
			VentanaAdmin.panel2.add(new JLabel("Escoja tipo de jugada"));
			String[]tipo = {"Jugada Ofensiva","Jugada Defensiva","Jugada TiroLibre"};
			JComboBox<String> tip = new JComboBox<String>(tipo);
			VentanaAdmin.panel2.add(tip);
			VentanaAdmin.panel2.add(new JLabel("Escoja entre 2-10 jugadas"));
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
			VentanaAdmin.panel2.add(new JLabel("Ingrece Una Explicacion"));
			JTextArea exp = new JTextArea(5,5);
			exp.setLineWrap(true);
			exp.setWrapStyleWord(true);
			JScrollPane scroll = new JScrollPane(exp);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			VentanaAdmin.panel2.add(scroll);
			VentanaAdmin.panel2.add(crear);
			crear.addActionListener(new ActionListener(){


				@Override
				public void actionPerformed(ActionEvent arg) {
					if(nombre.getText().isEmpty() || fecha.getText().isEmpty() ||exp.getText().isEmpty()){
						JOptionPane.showMessageDialog(null,"Porfavor todos los campos de texto","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else{
						if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex()== 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if((lista.getSelectedIndex()!= 0 &&lista1.getSelectedIndex()== 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0)){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if((lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex()!= 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0)){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex()== 0&&lista2.getSelectedIndex()!= 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex() == 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()!= 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex() == 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()!= 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex() == 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()!= 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex()== 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()!= 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex()== 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()!= 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex()== 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()!= 0&&lista9.getSelectedIndex()== 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else if(lista.getSelectedIndex()== 0 &&lista1.getSelectedIndex()== 0&&lista2.getSelectedIndex()== 0&&lista3.getSelectedIndex()== 0&&lista4.getSelectedIndex()== 0&&lista5.getSelectedIndex()== 0&&
								lista6.getSelectedIndex()== 0&&lista7.getSelectedIndex()== 0&&lista8.getSelectedIndex()== 0&&lista9.getSelectedIndex()!= 0){
							JOptionPane.showMessageDialog(null,"Porfavor ingrese minimo 2 jugadas","ERROR",JOptionPane.ERROR_MESSAGE);
						}else{
							WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/crea");
							MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
							String tipo = (String) tip.getSelectedItem();
							String nombres = nombre.getText();
							String fc =fecha.getText();
							String autor = Main.Usuario;
							String ex =  exp.getText();
							String j = (String) lista.getSelectedItem();
							String j1 = (String) lista1.getSelectedItem();
							String j2 =(String) lista2.getSelectedItem();
							String j3 = (String) lista3.getSelectedItem();
							String j4 = (String) lista4.getSelectedItem();
							String j5 = (String) lista5.getSelectedItem();
							String j6 = (String) lista6.getSelectedItem();
							String j7 = (String) lista7.getSelectedItem();
							String j8 =(String) lista8.getSelectedItem();
							String j9 =(String) lista9.getSelectedItem();
							Params.add("tipo", tipo);
							Params.add("nombre", nombres);
							Params.add("fecha", fc);
							Params.add("autor",autor);
							Params.add("jugada", j);
							Params.add("jugada1", j1);
							Params.add("jugada2",j2 );
							Params.add("jugada3", j3);
							Params.add("jugada4", j4);
							Params.add("jugada5", j5);
							Params.add("jugada6", j6);
							Params.add("jugada7", j7);
							Params.add("jugada8", j8);
							Params.add("jugada9", j9);
							Params.add("explicacion",ex);
							String respuesta = webResource.queryParams(Params).post(String.class);
							JOptionPane.showMessageDialog(null,respuesta,"felicidades",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}

			});
		}else if(s.equals("Crear nuevo Jugador")){
			System.out.println("holi");
		}else if(s.equals("Remover una Jugada")){
			VentanaAdmin.panel2.removeAll();
			VentanaAdmin.panel2.updateUI();
			WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/lista");
			JSONObject respuesta = webResource.get(JSONObject.class);
			try {
				JSONArray jug = (JSONArray) respuesta.get("Jugadas");
				int a = jug.length();
				String []  com = new String [a];
				
				for(int i=0;i<jug.length();i++){
					JSONObject aux = (JSONObject) jug.get(i);
					com[i] = (String)aux.get("Nombre jugada");
				}
				VentanaAdmin.panel2.add(new JLabel("Escoja la jugada que desa borrar"));
				JComboBox<String> lista = new JComboBox<String>(com);
				lista.setMaximumSize(new Dimension(450,23));
				VentanaAdmin.panel2.add(lista);
				JButton borrar = new JButton("Borrar");
				VentanaAdmin.panel2.add(borrar);
				borrar.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						WebResource webResources  = Main.client.resource(Main.URL+"jcomplejas/eliminar");
						String respuesta = webResources.queryParam("nombre", (String) lista.getSelectedItem()).delete(String.class);
						JOptionPane.showMessageDialog(null,respuesta,"Listo",JOptionPane.INFORMATION_MESSAGE);
						
					}
					
				});
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
			
		}else if(s.equals("Remover un Jugador")){
			System.out.println("holi");
		}
	}

}
