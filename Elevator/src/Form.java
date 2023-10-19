

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;

public class Form extends GameLoop{
	
	public static JFrame window;
	public static Point screen;
	
	Elevator elevator;
	Floor[] floor;
	Person[] person;
	int totalFloor;
	
	Random random;
	
	public void Start() 
	{
		random = new Random();
		
		screen = new Point(700,700);
		
		totalFloor = 4;
		
		floor = new Floor[totalFloor];
		
		
		for(int i = 0; i < floor.length ; i++)
		{
			floor[i] = new Floor(i,(screen.y/totalFloor) * i);
			System.out.println(floor[i].numberFloor + " : "+ floor[i].positionY);
		}
		
		
		
		elevator = new Elevator(floor);
		elevator.start();
		

		person = new Person[totalFloor];
		
		for(int i = 0; i < person.length ; i++)
		{
			person[i] = new Person(i,random.nextInt(totalFloor),floor[i],elevator);
			person[i].start();
			System.out.println(person[i].floorDestiny);
		}
		
		CreateWindow();
		
		
		
		
		
	}
	public void Update(float gameTime) 
	{
		
	}
	public void Draw() 
	{
		
		
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
		
		for(int i = 0; i < person.length ; i++)
		{
			window.add(person[i].image.GetImage());
		}
		
		window.setVisible(true);
	}
}
