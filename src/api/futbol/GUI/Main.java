package api.futbol.GUI;

import com.sun.jersey.api.client.Client;

public class Main {
	public static VentanaInicio ventanainicio = new VentanaInicio();
	public static VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
	public static VentanaAdmin ventanaAd = new VentanaAdmin();
	public static Client client = Client.create();
	public static String URL = "http://localhost:8080/LejosService/";
	public static String Usuario;
	public static int img =1;
	public static Posicion posicion = new Posicion(); 
	public static void main(String[] args) {
		ventanainicio.lanzar();
	}

}
