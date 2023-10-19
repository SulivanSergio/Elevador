

public class Elevator extends Thread{
	
	public boolean rodando = true;
	
	public Image imageOpen;
	public Image imageClosed;
	
	int direction = 1;
	float speed = 300.0f;
	private static boolean door = true;
	
	Floor[] floor;
	int floorCurrent = 0;
	int floorDestiny = 1;
	
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
	
	public void Update(float gameTime) 
	{
		
		if(door == true)
		{
			if(( floor[floorDestiny].positionY - floor[floorCurrent].positionY) > 0 && floor[floorDestiny].positionY - imageOpen.positionY > 10)
			{
				imageOpen.positionY +=  speed * direction * gameTime;
				imageOpen.positionX = 0;
				imageClosed.positionX = -1000;
			}else {
				//imageOpen.positionY -=  speed * direction * gameTime;
				//imageOpen.positionX = 0;
				//imageClosed.positionX = -1000;
			}
			
		}else {
			imageClosed.positionY -= (floor[floorCurrent].positionY - floor[floorDestiny].positionY) - speed * direction * gameTime;
			imageOpen.positionX = -1000;
			imageClosed.positionX = 0;
		}
		
		
	}
	public void Draw() 
	{
		imageOpen.UpdateImage();
		imageClosed.UpdateImage();
	}
	
	public void SetOpenDoor(boolean open)
	{
		door = open;
	}
	public static boolean GetOpenDoor()
	{
		return door;
	}
	
	
	
	
}
