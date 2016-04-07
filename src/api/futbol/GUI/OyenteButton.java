package api.futbol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class OyenteButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent clic) {
		String s  = clic.getActionCommand();
		if(s.equals("Trote")){
			WebResource webResource  = Main.client.resource(Main.URL+"jprimitivas/jugada");
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			JSONObject respuesta =webResource.queryParams(Params).get(JSONObject.class);
			System.out.println(respuesta.length());
		}
	}

}
