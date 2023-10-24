import java.util.concurrent.Semaphore;

public class Person extends Thread{
	
	public boolean rodando = true;
	
	public Rect rect;
	public Floor floor;
	Elevator elevator;
	public int floorDestiny;
	public boolean free = true;
	int id;
	
	
	
	public Person(int id,int floorDestiny, Floor floor, Elevator elevator) 
	{
		this.id = id;
		this.floor = floor;
		this.floorDestiny = floorDestiny;
		this.elevator = elevator;
		
		rect = new Rect(40 * id,this.floor.rect.y,64,64);
		
	}
	
	//GameLoop da pessoa
	public void run() 
	{
		float gameTime = 0;
		while(rodando) 
		{
			
			long inicio = System.currentTimeMillis();
			
			Update(gameTime);
			
			long fim = System.currentTimeMillis();
			gameTime = (float)(fim - inicio) * 0.001f;
			
			
		}
	}
	
	//Metodo que atualiza a posição e que tenta chamar o elevador
	private void Update(float gameTime) 
	{
		if(free == false) {
			this.rect.x += 50.0f * gameTime;
			//System.out.println("Andou: " + id + "Rcet X: "+ rect.x);
			if(this.rect.x > 300.0f )
			{
				System.out.println("Person XAAAUUU: "+ id + " Position: "+ this.rect.x);
				rodando = false;
			}
			if(this.rect.x < 0.0f)
			{
				rect.x = 1;
			}
		}else {
			//região critica
			elevator.ChangeDestiny(this);
			//fim da região critica
		}
	}
}
