

public class Elevator extends Thread{
	
	public boolean rodando = true;
	
	public Image imageOpen;
	public Image imageClosed;
	
	int direction = 1;
	float speed = 500.0f;
	private static boolean door = true;
	
	Floor[] floor;
	int floorCurrent = 0;
	int floorDestiny = 1;
	
	Person person;
	
	public Elevator(Floor[] floor)
	{
		imageOpen = new Image(0,0,100,200,"Image/ELevador_Aberto.png");
		imageClosed = new Image(0,0,100,200,"Image/Elevador_Fechado.png");
		
		this.floor = floor;
		
	}
	
	public void run() 
	{
		
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			
			
			if(person != null)
			{
				if(person.floorDestiny == floorCurrent && person.image.positionY == this.imageOpen.positionY)
				{
					person.image.positionY = imageClosed.positionY;
					person.image.UpdateImage();
					person.free = false;
					person.semaphore.release();
					
				}else {
					if(this.person.free == true)
					{
						floorDestiny = person.floorDestiny;
						FecharPorta();
					}else {
						person.semaphore.release();
					}
				}
			}
		}else 
		{
			
			if( Distance(floor[floorDestiny].positionY , floor[floorCurrent].positionY) > 0 && Distance(floor[floorDestiny].positionY , (int)imageClosed.positionY) > 10)
			{
				if(person != null) {
					VisitarAndar(gameTime);
					
				}
					
				
			}else 
			{
				AbrirPorta();
			}
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
	
	private void AbrirPorta()
	{
		door = true;
		floorCurrent = floorDestiny;
		
	}
	private void FecharPorta()
	{
		door = false;
	}
	private void VisitarAndar(float gameTime)
	{
		if(imageClosed.positionY < floor[floorDestiny].positionY) 
		{
			imageClosed.positionY += speed * direction * gameTime;
			imageOpen.positionY +=  speed * direction * gameTime;
			
			
			if(person.floorDestiny == floorDestiny)
			{
				person.image.positionY = imageClosed.positionY;
				person.image.UpdateImage();
			}
			
		}else 
		{
			imageClosed.positionY -= speed * direction * gameTime;
			imageOpen.positionY -=  speed * direction * gameTime;
			
			if(person.floorDestiny == floorDestiny)
			{
				person.image.positionY = imageClosed.positionY;
				person.image.UpdateImage();
			}
			
		}
		SetAnimation(-1000,0);
	}
	
	public void ChangeDestiny(Person person)
	{
		if(door == true)
		{
			this.person = person;
			System.out.println("Person: "+ person.id + "FloorDestiny: " + person.floorDestiny);
			floorDestiny = this.person.floor.numberFloor;
			FecharPorta();
		}
	}
	
}
