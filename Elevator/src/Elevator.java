

public class Elevator extends Thread{
	
	public boolean rodando = true;
	
	public Image imageOpen;
	public Image imageClosed;
	

	float speed = 500.0f;
	private static boolean door = false;
	
	Floor[] floor;
	int floorCurrent = 0;
	int floorDestiny = 0;
	boolean existPerson = false;
	int timer = 0;
	Person person;
	
	public Elevator(Floor[] floor)
	{
		imageOpen = new Image(0,0,100,200,"Image/ELevador_Aberto.png");
		imageClosed = new Image(0,0,100,200,"Image/Elevador_Fechado.png");
		
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
		imageOpen.UpdateImage();
		imageClosed.UpdateImage();
	}
	
	
	
	private int Distance(int destiny, int current)
	{
		int distance = Math.abs(destiny - current);
		return distance;
	}
	
	private void SetAnimation(int open, int closed)
	{
		imageOpen.positionX = open;
		imageClosed.positionX = closed;
	}
	

	
	public void ChangeDestiny(Person person)
	{
		
		
		this.person = person;
		System.out.println("Person: "+ person.id + "FloorDestiny: " + person.floorDestiny);
		floorDestiny = this.person.floor.numberFloor;
		FecharPorta();
		
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
				person.image.positionX = 40.0f;
				person.image.UpdateImage();
				person.semaphore.release();
				System.out.println("Release: " + person.free + " Id: "+ person.id);
				
			}else {
				if(!existPerson)
				{
					floorDestiny = person.floorDestiny;
					existPerson = true;
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
		if( Distance(floor[floorDestiny].positionY , floor[floorCurrent].positionY) > 0 && Distance(floor[floorDestiny].positionY , (int)imageClosed.positionY) > 10 && person.free == true)
		{
			
				if(imageClosed.positionY < floor[floorDestiny].positionY) 
				{
					imageClosed.positionY += speed  * gameTime;
					imageOpen.positionY +=  speed  * gameTime;
					
					
					if(this.person!= null )
					{
						if(this.person.floorDestiny == this.floorDestiny )
						{
							this.person.image.positionY = imageClosed.positionY;
							this.person.image.positionX = 40.0f;
							this.person.image.UpdateImage();
						}
					}
				
				}else 
				{
					imageClosed.positionY -= speed  * gameTime;
					imageOpen.positionY -=  speed  * gameTime;
					
					if(this.person!= null )
					{
						if(this.person.floorDestiny == this.floorDestiny)
						{
							this.person.image.positionY = imageClosed.positionY;
							this.person.image.positionX = 40.0f;
							this.person.image.UpdateImage();
						}
					}
				
					
				}
		}else 
		{
			AbrirPorta();
		}
	}
	
	
}
