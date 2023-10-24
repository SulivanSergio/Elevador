

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JFrame;

public class Building extends GameLoop{
	
	public static JFrame window;
	public static Point screen = new Point(700,700);
	
	Elevator elevator;
	Floor[] floor;
	Person[] person;
	int totalFloor;
	float time;
	int timeMAX = 100;
	
	Random random = new Random();
	
	
	//predio utilizado para gerar elevador, andares e pesssoas
	//tambem foi utilizado com um GameLoop para desenhar os quadrados
	public Building() {
		
	}
	
	//Start do GameLoop
	public void Start() {
		
		screen = new Point(700,700);
		
		//criação da janela
		CreateWindow();
		
		//inicialização das variaveis
		timeMAX = 10;
		totalFloor = 3;
		random = new Random();
		floor = new Floor[totalFloor];
		
		
		//inicialização dos andares
		for(int i = 0; i < floor.length ; i++)
		{
			floor[i] = new Floor(i,30 +(screen.y/totalFloor) * i);
			System.out.println(floor[i].numberFloor + " : "+ floor[i].rect.y);
		}
		
		
		
		//inicialização do elevador
		elevator = new Elevator(floor);
		

		//inicialização das pessoas
		person = new Person[5];
		for(int i = 0; i < person.length ; i++)
		{
			person[i] = new Person(i,RandomNew(i),floor[i%totalFloor],elevator);
			person[i].start();
			System.out.println(person[i].floorDestiny);
		}
		
		
		
	}
	//metodo utilizado para chamar o destino da pessoa sem que seja o proprio andar
	public int RandomNew(int id) {
		
		int aux = random.nextInt(totalFloor);
		if(id == aux)
		{
			RandomNew(id);
		}else
		{
			return aux;
		}
		return aux;
	}
	
	//Update do GameLoop
	public void Update(float gameTime)
	{
		elevator.Update(gameTime);
	}
	
	//Metodo que desenha do GameLoop
	public void Draw() {
		//System.out.print("a");
		for(int i = 0; i < floor.length ; i++)
		{
			Paint(floor[i].rect,Color.black);
		}
		
		
		for(int i = 0; i < person.length ; i++)
		{
			Paint(person[i].rect,new Color(50 * i,10 * i,50 * i)); 
		}
		Paint(elevator.rectOpen, Color.green);
		Paint(elevator.rectClosed, Color.red);
	}
	
	//Criação da janela
	private void CreateWindow() 
	{
		window = new JFrame();
		window.setSize(screen.x,screen.y);
		window.getContentPane().setBackground(new Color(100,100,100));
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
	
	//Metodo que pinta e limpa a janela
	public void Paint(Rect rect, Color color)
	{
		time += 0.01f;
		Graphics g = window.getGraphics();
		
		if(time > timeMAX)
		{
			time =0;
			g.clearRect(0,0,screen.x,screen.y);
			g.setColor(Color.GRAY);
			g.fillRect(0,0,screen.x,screen.y);
		}
		
		g.setColor(color);
		g.fillRect((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height);
	
	}
	
}
