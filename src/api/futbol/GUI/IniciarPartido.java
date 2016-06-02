package api.futbol.GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@SuppressWarnings("serial")
public class IniciarPartido extends JFrame implements ActionListener{
	Container contenedor;
	JComboBox<String> arqueros,delanteros;
	JPanel panel1;
	JButton jug;
	public static String jug1,jug2 = null;
	public IniciarPartido(){
		super("Iniciar Partido");
	}
	public void Lanzar(){
		contenedor = this.getContentPane();
		panel1 = new JPanel();
		arqueros = new JComboBox<String>();
		delanteros = new JComboBox<String>();
		jug = new JButton("Iniciar");
		contenedor.add(panel1);
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		panel1.add(new JLabel("Escoja un arquero"));
		WebResource webResource2  = Main.client.resource(Main.URL+"jugador/arqueros");
		JSONObject respuesta2 = webResource2.get(JSONObject.class);
		JSONArray lista;
		try {

			lista = (JSONArray) respuesta2.get("arqueros");
			for(int i = 0; i<lista.length();i++){
				JSONObject aux = new JSONObject();
				aux = (JSONObject) lista.get(i);
				arqueros.addItem((String) aux.get("nombre"));
			}
			
			arqueros.setMaximumSize(new Dimension(250,30));
			panel1.add(arqueros);
			panel1.add(new JLabel("Escoja un delantero"));
			WebResource webResource3  = Main.client.resource(Main.URL+"jugador/delanteros");
			JSONObject respuesta3 = webResource3.get(JSONObject.class);
			lista = (JSONArray) respuesta3.get("arqueros");
			for(int i = 0; i<lista.length();i++){
				JSONObject aux = new JSONObject();
				aux = (JSONObject) lista.get(i);
				delanteros.addItem((String) aux.get("nombre"));
			}
			delanteros.setMaximumSize(new Dimension(250,30));
			panel1.add(delanteros);
			panel1.add(jug);
			jug.addActionListener(this);
			setSize(200,210);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo (null);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		jug1 = (String) arqueros.getSelectedItem();
		jug2 = (String) delanteros.getSelectedItem();
		WebResource webResource  = Main.client.resource(Main.URL+"Partido/iniciar");
		MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
		Params.add("nombre1", jug1);
		Params.add("nombre2", jug2);
		JSONObject respuesta = webResource.queryParams(Params).get(JSONObject.class);
		try {
			JSONArray jarquero = (JSONArray) respuesta.get("Aerquero");
			
			for (int i = 0 ; i<jarquero.length();i++){
				JSONObject aux = new JSONObject();
				aux = (JSONObject) jarquero.get(i);
				
				VentanaPrincipal.jugadascomplejas.addItem((String) aux.get("jugada"));
				VentanaPrincipal.jugadascomplejas.updateUI();
			}
			
			this.dispose();
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		Main.img =2;
		VentanaPrincipal.cancha.add(VentanaPrincipal.im);
		VentanaPrincipal.cancha.revalidate();
		VentanaPrincipal.cancha.repaint();
	}

}
