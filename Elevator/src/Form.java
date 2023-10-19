

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

public class Form extends GameLoop{
	
	public static JFrame window;
	public static Point screen;
	
	Elevator elevator;
	Floor[] floor;
	
	int totalFloor;
	
	
	public void Start() 
	{
		screen = new Point(700,700);
		
		totalFloor = 2;
		
		floor = new Floor[totalFloor];
		
		
		for(int i = 0; i < floor.length ; i++)
		{
			floor[i] = new Floor(i,(screen.y/totalFloor) * i);
			System.out.println(floor[i].id + " : "+ floor[i].positionY);
		}
		
		elevator = new Elevator(floor);
		elevator.start();
		
		
		
		CreateWindow();
		
	}
	public void Update(float gameTime) 
	{
		
	}
	public void Draw() 
	{
		
		for(int i = 0; i < floor.length ; i++)
		{
			floor[i].image.UpdateImage();
		}
		
	}
	private void CreateWindow() 
	{
		window = new JFrame();
		window.setSize(screen.x,screen.y);
		window.getContentPane().setBackground(new Color(100,100,100));
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		
		window.add(elevator.imageOpen.GetImage());
		window.add(elevator.imageClosed.GetImage());
		
		for(int i = 0; i < floor.length ; i++)
		{
			window.add(floor[i].image.GetImage());
		}
		
		window.setVisible(true);
	}
}
