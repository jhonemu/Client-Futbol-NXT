package api.futbol.GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import lejos.nxt.Motor;
import lejos.nxt.remote.NXTCommand;
import lejos.pc.comm.NXTCommandConnector;

public class OyenteButton implements ActionListener{

	int cont=1;

	@Override
	public void actionPerformed(ActionEvent clic) {
		String s  = clic.getActionCommand();
		if(s.equals("Trote")){
			cont++;
			try {
			
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);
			int q =Integer.valueOf((String) respuesta.get("Trotar").toString());
			System.out.println(q);
			NXTCommandConnector.setNXTCommand(new NXTCommand(Main.conn.getNXTComm()));
			System.out.println(cont);
			if(cont%2==0){
			Motor.A.setSpeed(q);
			Motor.C.setSpeed(q);
			Motor.A.forward();
			Motor.C.forward();
			}
			else{
				Thread.sleep(1000);
				Motor.A.stop();
				Motor.C.stop();
			}
			} catch (JSONException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (s.equals("Correr")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Atras")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Correr Atras")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Izquierda")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Derecha")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Chute")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Patear")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
	}
}