import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Image {
	
	protected JLabel image;
	protected String path;
	
	protected float positionX;
	protected float positionY;
	
	protected float scaleX;
	protected float scaleY;
	
	public Image(int x, int y,int width, int height, String path) 
	{
		positionX = x;
		positionY = y;
		scaleX= width;
		scaleY = height;
		
		this.path = path;
		
		//"Image/ELevador_Aberto.png"
		image = new JLabel(new ImageIcon(Image.class.getResource(this.path)));
		image.setBounds((int)positionX,(int)positionY,(int)scaleX,(int)scaleY);
	}
	
	
	public JLabel GetImage()
	{
		return image;
	}
	
	public void UpdateImage() 
	{
		image.setBounds((int)positionX,(int)positionY,(int)scaleX,(int)scaleY);
	}
	
}
