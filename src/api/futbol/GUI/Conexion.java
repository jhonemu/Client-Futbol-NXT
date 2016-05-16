package api.futbol.GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;

@SuppressWarnings("serial")
public class Conexion extends JFrame implements ActionListener {
	
	Container contenedor;
	JComboBox<String> robots;
	JPanel panel1;
	JButton conectar;
	public Conexion(){
		super("Conexion");
	}
	
	public void Lanzar(){
		contenedor = this.getContentPane();

		
		panel1 = new JPanel();
		contenedor.add(panel1);
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		robots = new JComboBox<String>();
		conectar = new JButton("Conectar");
		panel1.add(new JLabel("Seleccione un robot"));
		WebResource webResource  = Main.client.resource(Main.URL+"conect/robot");
		JSONObject respuesta =  webResource.get(JSONObject.class);
		try {
			JSONArray lista = (JSONArray) respuesta.get("robots");
			for(int i = 0 ; i<lista.length();i++){
				JSONObject aux = new JSONObject();
				aux = (JSONObject) lista.get(i);
				robots.addItem((String) aux.get("nombre"));
			}
			robots.setMaximumSize(new Dimension(250,30));
			panel1.add(robots);
			panel1.add(conectar);
			conectar.addActionListener(this);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		setSize(200,110);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo (null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String nombre = (String) robots.getSelectedItem();
		WebResource webResource  = Main.client.resource(Main.URL+"conect/conexion");
		String respuesta = webResource.queryParam("nombre", nombre).get(String.class);
		if(respuesta.equals("Conexion Exitosa")){
			JOptionPane.showMessageDialog(null,respuesta,"EXITO",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(respuesta.equals("Conexion fallida")){
			JOptionPane.showMessageDialog(null,respuesta,"ERROR",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
