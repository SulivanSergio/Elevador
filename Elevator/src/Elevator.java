

public class Elevator extends Thread{
	
	public boolean rodando = true;
	
	public Rect rectOpen;
	public Rect rectClosed;
	

	float speed = 800.0f;
	private static boolean door = false;
	
	Floor[] floor;
	int floorCurrent = 0;
	int floorDestiny = 0;
	boolean existPerson = false;
	int timer = 0;
	Person person;
	
	public Elevator(Floor[] floor)
	{
		rectOpen = new Rect(0,0,100,200);
		rectClosed = new Rect(0,0,100,200);
		
		this.floor = floor;
		
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
		
		if(door == true)
		{
			SetAnimation(0,-1000);
			
			
			
		}else 
		{
			SetAnimation(-1000,0);
			VistarAndar(gameTime);
		}
		
		
	}
	
	private void Draw() 
	{
		//Form.Paint(rectOpen);
		//Form.Paint(rectClosed);
	}
	
	
	
	private int Distance(int destiny, int current)
	{
		int distance = Math.abs(destiny - current);
		return distance;
	}
	
	private void SetAnimation(int open, int closed)
	{
		rectOpen.x = open;
		rectClosed.x = closed;
	}
	

	
	public void ChangeDestiny(Person person)
	{
		if(this.person == null)
		{
			this.person = person;
			System.out.println("Person: "+ person.id + "FloorDestiny: " + person.floorDestiny);
			floorDestiny = this.person.floor.numberFloor;
			FecharPorta();
		}else if(this.person != person)
		{
			this.person = person;
			System.out.println("Person: "+ person.id + "FloorDestiny: " + person.floorDestiny);
			floorDestiny = this.person.floor.numberFloor;
			FecharPorta();
		}else {
			System.out.println("REPETIDO" );
			this.person.semaphore.release();
		}
	}
	
	private void AbrirPorta()
	{
		
		door = true;
		floorCurrent = floorDestiny;
		
		if(person != null)
		{
		
			
			if(floorCurrent == person.floorDestiny)
			{
				
				existPerson = false;
				person.free = false;
				person.rect.x = 40.0f;
				person.rect.y = this.rectClosed.y;
				person.semaphore.release();
				
				System.out.println("Release: " + person.free + " Id: "+ person.id);
				
			}else {
				if(!existPerson)
				{
					floorDestiny = person.floorDestiny;
					existPerson = true;
					person.rect.x = -100;
					FecharPorta();
				}
			}
		
		}
			
	}
	private void FecharPorta()
	{
		door = false;
		
	}
	private void VistarAndar(float gameTime)
	{
		if( Distance(floor[floorDestiny].positionY , floor[floorCurrent].positionY) > 0 && Distance(floor[floorDestiny].positionY , (int)rectClosed.y) > 10 && person.free == true)
		{
			
				if(rectClosed.y < floor[floorDestiny].positionY) 
				{
					rectClosed.y += speed  * gameTime;
					rectOpen.y +=  speed  * gameTime;
					
				}else 
				{
					rectClosed.y -= speed  * gameTime;
					rectOpen.y -=  speed  * gameTime;
					
				}
		}else 
		{
			AbrirPorta();
		}
	}
	
	
}
