package api.futbol.GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class OyenteButton implements ActionListener{

	int cont=1;
	WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
	@Override
	public void actionPerformed(ActionEvent clic) {
		String s  = clic.getActionCommand();
		if(s.equals("Trote")){
			cont++;
		
			
			
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			
		}
		else if (s.equals("Correr")){
			
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Atras")){
		
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Correr Atras")){
		
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Izquierda")){
		
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Derecha")){
		
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Chute")){
			
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Patear")){
		
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta);

		}
	}
}