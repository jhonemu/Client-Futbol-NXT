package api.futbol.GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class OyenteButton implements ActionListener{

	Integer cont=1;
	WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
	@Override
	public void actionPerformed(ActionEvent clic) {
		String s  = clic.getActionCommand();
		if(s.equals("Trote")){
			cont++;
			//WebResource webResource2  = Main.client.resource(Main.URL+"pos/posi");
			
			System.out.println("contador trote "+cont.toString());
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta = webResource.queryParams(Params).get(String.class);
			
			System.out.println(respuesta);
			
		}
		else if (s.equals("Correr")){
			cont++;
			System.out.println("contador correr "+cont.toString());
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta = webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);
		
			
		}
		else if (s.equals("Atras")){
			cont++;
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Correr Atras")){
			cont++;
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);
			
		}
		else if (s.equals("Izquierda")){
			cont++;
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Derecha")){
			cont++;
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Chute")){
			
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont", cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);

		}
		else if (s.equals("Patear")){
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont", cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);

		}
	}
}