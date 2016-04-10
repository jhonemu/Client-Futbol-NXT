package api.futbol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.WebResource;

import lejos.pc.comm.NXTComm;



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
				
				WebResource webResource  = Main.client.resource(Main.URL+"conect/robot");
				
				JSONObject respuesta ;
				respuesta = webResource.get(JSONObject.class);
				JSONArray arr = respuesta.getJSONArray("Conexion");
				System.out.println(respuesta);
				System.out.println(arr);
				System.out.println(arr.optJSONObject(0).get("Nombre"));
			
				if(Main.conn.connectTo((String)arr.optJSONObject(0).get("Nombre"),NXTComm.LCP)){
					JOptionPane.showMessageDialog(null,"Coneccion exitosa","EXITO",JOptionPane.INFORMATION_MESSAGE);
					/*NXTCommandConnector.setNXTCommand(new NXTCommand(Main.conn.getNXTComm()));
					
					Motor.A.resetTachoCount();
					Motor.A.forward();
					Thread.sleep(1000);
					Motor.A.stop();
					System.out.println(Motor.A.getTachoCount());*/
					
				}
				
			}
			
		}catch(ClientHandlerException e){
			JOptionPane.showMessageDialog(null,"Servidor desconectado","ERROR",JOptionPane.ERROR_MESSAGE);
		} catch (JSONException e) {
			
			e.printStackTrace();
		} 
	}

}
