package api.futbol.GUI;

import javax.swing.ImageIcon;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class Posicion {
	public final int xmax = 520;
	public final int ymax = 380;
	public int px =0;
	public int py =0;
	public static String activo;
	public void calcular(int x,int y){
		px = ((x*520)/183);
		py = ((y*380)/122);
		if(px < (xmax/2)){
			activo = IniciarPartido.jug1;
			WebResource webResource  = Main.client.resource(Main.URL+"jugador/jugadas");
			JSONObject respuesta = webResource.queryParam("nombre",IniciarPartido.jug1).get(JSONObject.class);
			try {
				JSONArray jarquero = (JSONArray) respuesta.get("jugadas");
				VentanaPrincipal.jugadascomplejas.removeAllItems();
				for (int i = 0 ; i<jarquero.length();i++){
					JSONObject aux = new JSONObject();
					aux = (JSONObject) jarquero.get(i);
					
					VentanaPrincipal.jugadascomplejas.addItem((String) aux.get("Jugada"));
					VentanaPrincipal.jugadascomplejas.updateUI();
				}
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			if(IniciarPartido.jug1.equals("Keylor navas")){
				Imagen.img = new ImageIcon("src\\images\\10.png");
			}
				else if(IniciarPartido.jug1.equals("Petr cech")){
					Imagen.img = new ImageIcon("src\\images\\9.png");
					
				}else if(IniciarPartido.jug1.equals("Thibaut coutrois")){
					Imagen.img= new ImageIcon("src\\images\\8.png");
					
				}else if(IniciarPartido.jug1.equals("David de gea")){
					Imagen.img = new ImageIcon("src\\images\\7.png");
					
				}
				else if(IniciarPartido.jug1.equals("Manuel neue")){
					Imagen.img = new ImageIcon("src\\images\\6.png");
					
				}
		}else if(px > (xmax/2)){
			activo = IniciarPartido.jug2;
			WebResource webResource  = Main.client.resource(Main.URL+"jugador/jugadas");
			JSONObject respuesta = webResource.queryParam("nombre",IniciarPartido.jug2).get(JSONObject.class);
			try {
				JSONArray jarquero = (JSONArray) respuesta.get("jugadas");
				VentanaPrincipal.jugadascomplejas.removeAllItems();
				for (int i = 0 ; i<jarquero.length();i++){
					JSONObject aux = new JSONObject();
					aux = (JSONObject) jarquero.get(i);
					
					VentanaPrincipal.jugadascomplejas.addItem((String) aux.get("Jugada"));
					VentanaPrincipal.jugadascomplejas.updateUI();
				}
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			if(IniciarPartido.jug2.equals("Messi")){
				Imagen.img = new ImageIcon("src\\images\\11.png");
			}
				else if(IniciarPartido.jug2.equals("Robert lewandowski")){
					Imagen.img = new ImageIcon("src\\images\\5.png");
					
				}else if(IniciarPartido.jug2.equals("Harry kane")){
					Imagen.img= new ImageIcon("src\\images\\4.png");
					
				}else if(IniciarPartido.jug2.equals("Luis Suarez")){
					Imagen.img = new ImageIcon("src\\images\\3.png");
					
				}
				else if(IniciarPartido.jug2.equals("Sergio agüero")){
					Imagen.img = new ImageIcon("src\\images\\2.png");
					
				}else if(IniciarPartido.jug2.equals("Karim Benzema")){
					Imagen.img = new ImageIcon("src\\images\\1.png");
					
				}
		}
	}
}
