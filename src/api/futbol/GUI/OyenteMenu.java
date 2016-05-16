package api.futbol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.WebResource;


public class OyenteMenu implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent clic) {
		try{
			String s = (String)clic.getActionCommand();
			if(s.equals("Salir")){
				Object [] textOpcion = { "Si", "NO"};
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea salir del sistema?", "salir del sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,textOpcion,null);
				if(opcion == 0){
					System.exit(0);
				}
			}
			else if(s.equals("Conectar a robot")){
				new Conexion().Lanzar();
				/*WebResource webResource  = Main.client.resource(Main.URL+"conect/robot");
				String respuesta ;
				respuesta = webResource.get(String.class);
				if(respuesta.equals("Conexion Exitosa")){
					JOptionPane.showMessageDialog(null,respuesta,"EXITO",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(respuesta.equals("Conexion fallida")){
					JOptionPane.showMessageDialog(null,respuesta,"ERROR",JOptionPane.ERROR_MESSAGE);
				}*/
			}
			else if(s.equals("Consultar Explicacion de una jugada")){
				VentanaPrincipal.options.removeAllItems();
				WebResource webResource  = Main.client.resource(Main.URL+"jcomplejas/lista");
				JSONObject respuesta = webResource.get(JSONObject.class);
				JSONArray jug = (JSONArray) respuesta.get("Jugadas");
				for(int i=0;i<jug.length();i++){
					JSONObject aux = (JSONObject) jug.get(i);
					VentanaPrincipal.options.addItem((String)aux.get("Nombre jugada"));
				}
				VentanaPrincipal.tip=1;
				
			}
			else if(s.equals("Consultar informacion de un jugador")){
				VentanaPrincipal.options.removeAllItems();
				VentanaPrincipal.tip=2;
				WebResource webResource  = Main.client.resource(Main.URL+"jugador/lista");
				JSONObject respuesta = webResource.get(JSONObject.class);
				JSONArray lista = (JSONArray) respuesta.get("jugadores");
				for (int i = 0;i<lista.length();i++){
					JSONObject aux = (JSONObject) lista.get(i);
					VentanaPrincipal.options.addItem((String) aux.get("nombre"));
				}
				
			}
		}catch(ClientHandlerException e){
			JOptionPane.showMessageDialog(null,"Servidor desconectado","ERROR",JOptionPane.ERROR_MESSAGE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
