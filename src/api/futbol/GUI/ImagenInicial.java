package api.futbol.GUI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagenInicial extends JPanel {
	public ImagenInicial(){
		this.setMaximumSize(new Dimension(1,1));
	}
	public void paint(Graphics g) {
		ImageIcon img = new ImageIcon("src\\images\\ic_launcher.png");
		g.drawImage(img.getImage(),0 ,0 ,null );
		setOpaque(false);
		super.paintComponent(g);
	}
}
