package api.futbol.GUI;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.ws.rs.core.MultivaluedMap;


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
			
			
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta = webResource.queryParams(Params).get(String.class);
			
			System.out.println(respuesta);
			if(!respuesta.equals("y")){
			String[] pos = respuesta.split(",");
			System.out.println(Integer.valueOf(pos[0]));
			System.out.println(Integer.valueOf(pos[1]));
			Main.posicion.calcular(Integer.valueOf(pos[0]),Integer.valueOf(pos[1]));
			VentanaPrincipal.cancha.add(VentanaPrincipal.im);
			VentanaPrincipal.cancha.revalidate();
			VentanaPrincipal.cancha.repaint();
			}
			
		}
		else if (s.equals("Correr")){
			cont++;
			
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta = webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);
			if(!respuesta.equals("y")){
				String[] pos = respuesta.split(",");
				System.out.println(Integer.valueOf(pos[0]));
				System.out.println(Integer.valueOf(pos[1]));
				Main.posicion.calcular(Integer.valueOf(pos[0]),Integer.valueOf(pos[1]));
				VentanaPrincipal.cancha.add(VentanaPrincipal.im);
				VentanaPrincipal.cancha.revalidate();
				VentanaPrincipal.cancha.repaint();
				}
		
			
		}
		else if (s.equals("Atras")){
			cont++;
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);
			if(!respuesta.equals("y")){
				String[] pos = respuesta.split(",");
				System.out.println(Integer.valueOf(pos[0]));
				System.out.println(Integer.valueOf(pos[1]));
				Main.posicion.calcular(Integer.valueOf(pos[0]),Integer.valueOf(pos[1]));
				VentanaPrincipal.cancha.add(VentanaPrincipal.im);
				VentanaPrincipal.cancha.revalidate();
				VentanaPrincipal.cancha.repaint();
				}

		}
		else if (s.equals("Correr Atras")){
			cont++;
			MultivaluedMap<String, String> Params = new MultivaluedMapImpl();
			Params.add("jugada",s);
			Params.add("cont",cont.toString());
			String respuesta =webResource.queryParams(Params).get(String.class);
			System.out.println(respuesta);
			if(!respuesta.equals("y")){
				String[] pos = respuesta.split(",");
				System.out.println(Integer.valueOf(pos[0]));
				System.out.println(Integer.valueOf(pos[1]));
				Main.posicion.calcular(Integer.valueOf(pos[0]),Integer.valueOf(pos[1]));
				VentanaPrincipal.cancha.add(VentanaPrincipal.im);
				VentanaPrincipal.cancha.revalidate();
				VentanaPrincipal.cancha.repaint();
				}
			
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
			//String[] pos = respuesta.split(",");
		}
	}
}