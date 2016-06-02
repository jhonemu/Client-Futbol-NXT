package api.futbol.GUI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Imagen extends JPanel {
	public static ImageIcon img;
	public Imagen(){
		this.setMaximumSize(new Dimension(1,1));
		img = new ImageIcon("src\\images\\ic_launcher.png");
	}
	
	public void paint(Graphics g) {
	 if(Main.img == 1){
		img = new ImageIcon("src\\images\\ic_launcher.png");
		g.drawImage(img.getImage(),0 ,0 ,null );
	 }else{
		 g.drawImage(img.getImage(), Main.posicion.px,Main.posicion.py ,null );
	 }
	
		
		setOpaque(false);
		super.paintComponent(g);
	}
	
}
