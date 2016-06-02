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
							nom.setText("");
							contra.setText("");
						}
					}
				}
			});
		}else if(s.equals("Editar un jugador")){
			VentanaAdmin.panel2.removeAll();
			VentanaAdmin.panel2.updateUI();
			VentanaAdmin.panel2.add(new JLabel("Seleccione el jugador"));
			JComboBox<String> jugadores = new JComboBox<String>();
			WebResource webResource  = Main.client.resource(Main.URL+"jugador/lista");
			JSONObject respuesta = webResource.get(JSONObject.class);
			
			JSONArray lista;
			try {
				lista = (JSONArray) respuesta.get("jugadores");
				for (int i = 0;i<lista.length();i++){
					JSONObject aux = (JSONObject) lista.get(i);
					jugadores.addItem((String) aux.get("nombre"));
				
				}
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			jugadores.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(jugadores);
			JButton agregar = new JButton("Agregar jugadas");
			JButton quitar = new JButton("Quitar jugadas");
			VentanaAdmin.panel2.add(agregar);
			VentanaAdmin.panel2.add(quitar);
			quitar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaAdmin.panel2.removeAll();
					VentanaAdmin.panel2.updateUI();
					String nombrejug =(String) jugadores.getSelectedItem();
					JComboBox<String> jug = new JComboBox<String>();
					WebResource webResource  = Main.client.resource(Main.URL+"jugador/jugadas");
					JSONObject respuesta = webResource.queryParam("nombre",nombrejug).get(JSONObject.class);
					try {
						JSONArray jarquero = (JSONArray) respuesta.get("jugadas");
						for (int i = 0 ; i<jarquero.length();i++){
							JSONObject aux = new JSONObject();
							aux = (JSONObject) jarquero.get(i);
							jug.addItem((String) aux.get("Jugada"));
						}
						
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					VentanaAdmin.panel2.add(jug);
					jug.setMaximumSize(new Dimension(450,23));
					JButton ag = new JButton("Quitar");
					VentanaAdmin.panel2.add(ag);
					ag.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							String jugada = (String) jug.getSelectedItem();
							WebResource webResource  = Main.client.resource(Main.URL+"jugador/quitar");
							MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
							Params.add("nombrejugador", nombrejug);
							Params.add("nombrejugada", jugada);
							String respuesta = webResource.queryParams(Params).get(String.class);
							JOptionPane.showMessageDialog(null,respuesta,"Listo",JOptionPane.INFORMATION_MESSAGE);
							jug.removeAllItems();
							WebResource webResource2  = Main.client.resource(Main.URL+"jugador/jugadas");
							JSONObject respuesta2 = webResource2.queryParam("nombre",nombrejug).get(JSONObject.class);
							try {
								JSONArray jarquero = (JSONArray) respuesta2.get("jugadas");
								for (int i = 0 ; i<jarquero.length();i++){
									JSONObject aux = new JSONObject();
									aux = (JSONObject) jarquero.get(i);
									jug.addItem((String) aux.get("Jugada"));
								}
								
							} catch (JSONException e1) {
								e1.printStackTrace();
							}
							jug.updateUI();
						}
						
					});
				}
				
			});
			agregar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg) {
					VentanaAdmin.panel2.removeAll();
					VentanaAdmin.panel2.updateUI();
					String nombrejug =(String) jugadores.getSelectedItem();
					JComboBox<String> jug = new JComboBox<String>();
					/*WebResource webResource  = Main.client.resource(Main.URL+"jugador/jugadas");
					JSONObject respuesta = webResource.queryParam("nombre",nombre).get(JSONObject.class);
					try {
						JSONArray jarquero = (JSONArray) respuesta.get("jugadas");
						for (int i = 0 ; i<jarquero.length();i++){
							JSONObject aux = new JSONObject();
							aux = (JSONObject) jarquero.get(i);
							jug.addItem((String) aux.get("Jugada"));
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}*/
					WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/lista");
					JSONObject respuesta = webResource.get(JSONObject.class);
					
						JSONArray jugA;
						try {
							jugA = (JSONArray) respuesta.get("Jugadas");
							for(int i=0;i<jugA.length();i++){
								JSONObject aux = (JSONObject) jugA.get(i);
								jug.addItem((String)aux.get("Nombre jugada"));
							}
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					
						
						
					VentanaAdmin.panel2.add(jug);
					jug.setMaximumSize(new Dimension(450,23));
					JButton ag = new JButton("Agregar");
					VentanaAdmin.panel2.add(ag);
					ag.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							String jugada = (String) jug.getSelectedItem();
							WebResource webResource  = Main.client.resource(Main.URL+"jugador/agregar");
							MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
							Params.add("nombrejugador", nombrejug);
							Params.add("nombrejugada", jugada);
							String respuesta = webResource.queryParams(Params).get(String.class);
							JOptionPane.showMessageDialog(null,respuesta,"Listo",JOptionPane.INFORMATION_MESSAGE);
						}
						
					});
				}
				
			});
			
		}else if(s.equals("Editar una jugada")){
			VentanaAdmin.panel2.removeAll();
			VentanaAdmin.panel2.updateUI();
			WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/lista");
			JSONObject respuesta = webResource.get(JSONObject.class);
			String []  com = null;
			try {
				JSONArray jug = (JSONArray) respuesta.get("Jugadas");
				int a = jug.length();
				 com = new String [a];
				
				for(int i=0;i<jug.length();i++){
					JSONObject aux = (JSONObject) jug.get(i);
					com[i] = (String)aux.get("Nombre jugada");
				}
				VentanaAdmin.panel2.add(new JLabel("Escoja la jugada a editar"));
				
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			JComboBox<String> lista = new JComboBox<String>(com);
			lista.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(lista);
			JButton agregar = new JButton("Agregar jugadas");
			JButton quitar = new JButton("Quitar jugadas");
			VentanaAdmin.panel2.add(agregar);
			VentanaAdmin.panel2.add(quitar);
			quitar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					VentanaAdmin.panel2.removeAll();
					VentanaAdmin.panel2.updateUI();
					String nombrejugada =(String) lista.getSelectedItem();
					JComboBox<String> primitivas = new JComboBox<String>();
					WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/jugadas");
					JSONObject respuesta = webResource.queryParam("nombre", nombrejugada).get(JSONObject.class);
					try {
						JSONArray lis = (JSONArray) respuesta.get("jugada");
						for(int i=0;i<lis.length();i++){
							JSONObject aux = (JSONObject) lis.get(i);
							primitivas.addItem((String) aux.get("jugada"));
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					VentanaAdmin.panel2.add(primitivas);
					primitivas.setMaximumSize(new Dimension(450,23));
					JButton qu = new JButton("Quitar");
					VentanaAdmin.panel2.add(qu);
					qu.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							String jugada = (String) primitivas.getSelectedItem();
							WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/quitar");
							MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
							Params.add("nombrejugada", nombrejugada);
							Params.add("nombrejug", jugada);
							String respuesta = webResource.queryParams(Params).get(String.class);
							JOptionPane.showMessageDialog(null,respuesta,"Listo",JOptionPane.INFORMATION_MESSAGE);
							primitivas.removeAllItems();
							WebResource webResource2  = Main.client.resource(Main.URL+"jcomplejas/jugadas");
							JSONObject respuesta2 = webResource2.queryParam("nombre", nombrejugada).get(JSONObject.class);
							try {
								JSONArray lis = (JSONArray) respuesta2.get("jugada");
								for(int i=0;i<lis.length();i++){
									JSONObject aux = (JSONObject) lis.get(i);
									primitivas.addItem((String) aux.get("jugada"));
								}
							} catch (JSONException e1) {
								e1.printStackTrace();
							}
							
						}
						
					});
				}
				
			});
			agregar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					VentanaAdmin.panel2.removeAll();
					VentanaAdmin.panel2.updateUI();
					String nombrejugada =(String) lista.getSelectedItem();
					String[]aux={"Trotar","Correr","Girar a la izquierda","Girar a la derecha","Chute","Patear","Ir atras","Correr atras"};
					JComboBox<String> primitivas = new JComboBox<String>(aux);
					JButton ag = new JButton("Agregar");
					primitivas.setMaximumSize(new Dimension(450,23));
					VentanaAdmin.panel2.add(primitivas);
					VentanaAdmin.panel2.add(ag);
					ag.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							String jugada = (String) primitivas.getSelectedItem();
							WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/agregar");
							MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
							Params.add("nombrejugada", nombrejugada);
							Params.add("nombrejug", jugada);
							String respuesta = webResource.queryParams(Params).get(String.class);
							JOptionPane.showMessageDialog(null,respuesta,"Listo",JOptionPane.INFORMATION_MESSAGE);
							
						}
						
					});
					
				}
				
			});
		}
		else if(s.equals("Crear nueva Jugada")){
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
							lista.removeAllItems();
							lista1.removeAllItems();
							lista2.removeAllItems();
							lista3.removeAllItems();
							lista4.removeAllItems();
							lista5.removeAllItems();
							lista6.removeAllItems();
							lista7.removeAllItems();
							lista8.removeAllItems();
							lista9.removeAllItems();
							for(int i = 0 ; i<aux.length;i++){
								lista.addItem(aux[i]);
								lista1.addItem(aux[i]);
								lista2.addItem(aux[i]);
								lista3.addItem(aux[i]);
								lista4.addItem(aux[i]);
								lista5.addItem(aux[i]);
								lista6.addItem(aux[i]);
								lista7.addItem(aux[i]);
								lista8.addItem(aux[i]);
								lista9.addItem(aux[i]);
							}
							lista.updateUI();
							lista2.updateUI();
							lista3.updateUI();
							lista4.updateUI();
							lista5.updateUI();
							lista6.updateUI();
							lista7.updateUI();
							lista8.updateUI();
							lista9.updateUI();
							nombre.setText("");
							fecha.setText("");
							exp.setText("");
						}
					}
				}

			});
		}else if(s.equals("Crear nuevo Jugador")){
			VentanaAdmin.panel2.removeAll();
			VentanaAdmin.panel2.updateUI();
			
			
			VentanaAdmin.panel2.add(new JLabel("Nombre del jugador"));
			JTextField nombre =new JTextField();
			nombre.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(nombre);
			VentanaAdmin.panel2.add(new JLabel("Posicion"));
			String aux[] = {"","Arquero","Delantero"};
			JComboBox<String> lista = new JComboBox<String>(aux);
			lista.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(lista);
			VentanaAdmin.panel2.add(new JLabel("Jugadas"));
			JComboBox<String> jugadas = new JComboBox<String>();
			JComboBox<String> jugadas1 = new JComboBox<String>();
			JComboBox<String> jugadas2 = new JComboBox<String>();
			jugadas.addItem("");
			jugadas1.addItem("");
			jugadas2.addItem("");
			jugadas.setMaximumSize(new Dimension(450,23));
			jugadas1.setMaximumSize(new Dimension(450,23));
			jugadas2.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(jugadas);
			VentanaAdmin.panel2.add(jugadas1);
			VentanaAdmin.panel2.add(jugadas2);
			//JButton cont = new JButton("Continuar");
			WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/lista");
			JSONObject respuesta = webResource.get(JSONObject.class);
			JSONArray jug;
			try {
				jug = (JSONArray) respuesta.get("Jugadas");
				for(int i=0;i<jug.length();i++){
					JSONObject au = (JSONObject) jug.get(i);
					jugadas.addItem((String)au.get("Nombre jugada"));
					jugadas1.addItem((String)au.get("Nombre jugada"));
					jugadas2.addItem((String)au.get("Nombre jugada"));
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			VentanaAdmin.panel2.add(new JLabel("Dorsal"));
			JTextField dorsal = new JTextField();
			dorsal.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(dorsal);
			JButton crear = new  JButton("Crear");
			VentanaAdmin.panel2.add(crear);
			crear.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg) {
					String name = nombre.getText();
					String dor = dorsal.getText();
					String pos = (String) lista.getSelectedItem();
					String jug1 = (String) jugadas.getSelectedItem();
					String jug2 = (String) jugadas1.getSelectedItem();
					String jug3 = (String) jugadas2.getSelectedItem();
					if(name.length()==0 && dor.length() ==0){
						JOptionPane.showMessageDialog(null,"porfavor llene los campos","ERROR",JOptionPane.ERROR_MESSAGE);
					}else{
					WebResource webResource2  = Main.client.resource(Main.URL+"jugador/crea");
					MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
					Params.add("nombre", name);
					Params.add("posicion",pos);
					Params.add("dorsal", dor);
					Params.add("jugada1", jug1);
					Params.add("jugada2", jug2);
					Params.add("jugada3", jug3);
					String respuesta = webResource2.queryParams(Params).post(String.class);
					JOptionPane.showMessageDialog(null,respuesta,"listo",JOptionPane.INFORMATION_MESSAGE);
					nombre.setText("");
					dorsal.setText("");
					jugadas.removeAllItems();
					jugadas1.removeAllItems();
				    jugadas2.removeAllItems();
				    jugadas.addItem("");
				    jugadas1.addItem("");
				    jugadas2.addItem("");
				    WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/lista");
					JSONObject respuesta2 = webResource.get(JSONObject.class);
					JSONArray jug;
					try {
						jug = (JSONArray) respuesta2.get("Jugadas");
						for(int i=0;i<jug.length();i++){
							JSONObject au = (JSONObject) jug.get(i);
							jugadas.addItem((String)au.get("Nombre jugada"));
							jugadas1.addItem((String)au.get("Nombre jugada"));
							jugadas2.addItem((String)au.get("Nombre jugada"));
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					jugadas.updateUI();
					jugadas1.updateUI();
					jugadas2.updateUI();
				}
				}
			});
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
						if(respuesta.equals("jugada eliminada")){
						JOptionPane.showMessageDialog(null,respuesta,"Listo",JOptionPane.INFORMATION_MESSAGE);
						WebResource webResource2  = Main.client.resource(Main.URL+"jcomplejas/lista");
						JSONObject respuesta2 = webResource2.get(JSONObject.class);
						lista.removeAllItems();
						try {
							JSONArray jug = (JSONArray) respuesta2.get("Jugadas");
							for(int i=0;i<jug.length();i++){
								JSONObject aux = (JSONObject) jug.get(i);
								lista.addItem((String)aux.get("Nombre jugada"));
							}
							lista.updateUI();
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
					
					}
						else if (respuesta.equals("La jugada se encuentra en uso y no puede ser eliminada")){
							JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			
			
		}else if(s.equals("Remover un Jugador")){
			VentanaAdmin.panel2.removeAll();
			VentanaAdmin.panel2.updateUI();
			VentanaAdmin.panel2.add(new JLabel("Seleccione el jugador"));
			JComboBox<String> jugadores = new JComboBox<String>();
			WebResource webResource  = Main.client.resource(Main.URL+"jugador/lista");
			JSONObject respuesta = webResource.get(JSONObject.class);
			
			JSONArray lista;
			try {
				lista = (JSONArray) respuesta.get("jugadores");
				for (int i = 0;i<lista.length();i++){
					JSONObject aux = (JSONObject) lista.get(i);
					jugadores.addItem((String) aux.get("nombre"));
				
				}
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			jugadores.setMaximumSize(new Dimension(450,23));
			VentanaAdmin.panel2.add(jugadores);
			JButton borrar = new JButton("Borrar");
			VentanaAdmin.panel2.add(borrar);
			borrar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					WebResource webResource  = Main.client.resource(Main.URL+"jugador/eliminar");
					String respuesta = webResource.queryParam("nombre", (String)jugadores.getSelectedItem()).delete(String.class);
					if(respuesta.equals("Jugador eliminado")){
					JOptionPane.showMessageDialog(null,respuesta,"Listo",JOptionPane.INFORMATION_MESSAGE);
					jugadores.removeAllItems();
					WebResource webResource2  = Main.client.resource(Main.URL+"jugador/lista");
					JSONObject respuesta2 = webResource2.get(JSONObject.class);
					JSONArray lista;
					try {
						lista = (JSONArray) respuesta2.get("jugadores");
						for (int i = 0;i<lista.length();i++){
							JSONObject aux = (JSONObject) lista.get(i);
							jugadores.addItem((String) aux.get("nombre"));
						
						}
						jugadores.updateUI();
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
					
				}else if(respuesta.equals("El jugador se encuentra en uso y no puede ser eliminado")){
					JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
				
			});
	}
	}

}
