import java.util.concurrent.Semaphore;

public class Person extends Thread{
	
	public boolean rodando = true;
	
	public Rect rect;
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
		
		rect = new Rect(40 * id,this.floor.positionY,64,64);
		
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
			
			this.rect.x += 100.0f * gameTime;
			
			
			if(this.rect.x > 300.0f )
			{
				
				System.out.println("Person XAAAUUU: "+ id + " Position: "+ this.rect.x);
				rodando = false;
				
			}else {
				
				
			}
			
		}else {
			
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			elevator.ChangeDestiny(this);
			
			
		}
		
		
		
		
		
		
	}
	private void Draw() {
		//Form.Paint(rect);
	}
	
	public boolean GetFree()
	{
		return free;
	}
	
}
