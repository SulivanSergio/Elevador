import java.util.concurrent.Semaphore;

public class Person extends Thread{
	
	public boolean rodando = true;
	
	Image image;
	public Floor floor;
	Elevator elevator;
	public int floorDestiny;
	public boolean free = true;
	int id;
	
	public static Semaphore semaphore = new Semaphore(1);
	
	public Person(int id,int floorDestiny, Floor floor, Elevator elevator) 
	{
		this.id = id;
		this.floor = floor;
		this.floorDestiny = floorDestiny;
		this.elevator = elevator;
		
		image = new Image(40,this.floor.positionY,100,100,"Image/Floor.png");
		
	}
	
	
	
	public void run() 
	{
		float gameTime = 0;
		while(rodando) 
		{
			
			long inicio = System.currentTimeMillis();
			
			Update(gameTime);
			
			long fim = System.currentTimeMillis();
			gameTime = (float)(fim - inicio) * 0.001f;
			
			Draw();
			
		}
	}
	
	private void Update(float gameTime) 
	{
		
		
		if(free == false) {
			image.positionX += 200.0f * gameTime;
			image.UpdateImage();
		}else
		{
			try {
				semaphore.acquire();
				elevator.ChangeDestiny(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(image.positionX > 300)
		{
			System.out.println("Person XAAAUUU: "+ id);
			rodando = false;
		}
		
		
		
	}
	private void Draw() {
		image.UpdateImage();
	}
	
	public boolean GetFree()
	{
		return free;
	}
	
}
