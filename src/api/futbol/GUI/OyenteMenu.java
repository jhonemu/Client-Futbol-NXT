package api.futbol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


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
				
				WebResource webResource  = Main.client.resource(Main.URL+"conect/robot");
				
				String respuesta ;
				respuesta = webResource.get(String.class);
				
				System.out.println(respuesta);
				
			
				if(respuesta.equals("Conexion Exitosa")){
					JOptionPane.showMessageDialog(null,respuesta,"EXITO",JOptionPane.INFORMATION_MESSAGE);
					//NXTCommandConnector.setNXTCommand(new NXTCommand(Main.conn.getNXTComm()));
					
				}
				else if(respuesta.equals("Conexion fallida")){
					JOptionPane.showMessageDialog(null,respuesta,"ERROR",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}catch(ClientHandlerException e){
			JOptionPane.showMessageDialog(null,"Servidor desconectado","ERROR",JOptionPane.ERROR_MESSAGE);
		} 
	}

}
