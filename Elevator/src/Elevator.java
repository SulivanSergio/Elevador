import java.util.concurrent.Semaphore;

public class Elevator{
	
	public boolean rodando = true;
	
	public Rect rectOpen;
	public Rect rectClosed;
	private Floor[] floor;
	private Person person;
	
	float speed = 50.0f;
	
	private int floorCurrent = 0;
	private int floorDestiny = 0;
	private boolean existPerson = false;
	
	public static Semaphore semaphore = new Semaphore(1);
	
	//Construtor
	public Elevator(Floor[] floor)
	{
		rectOpen = new Rect(0,0,100,200);
		rectClosed = new Rect(0,0,100,200);
		this.floor = floor;
		
	}
	
	//Update do Elevador
	public void Update(float gameTime) 
	{
		VisitarAndar(gameTime);
	}
	
	//Região critica
	public void ChangeDestiny(Person person)
	{
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		if(existPerson == false )
		{
			
			if(person.free)
			{
				this.person = person;
				existPerson = true;
				floorDestiny = this.person.floor.numberFloor;
				System.out.println("Person: " + person.id + " PersonDestiny: " + person.floorDestiny);
			}
		}
		semaphore.release();
		
	}
	
	//Metodo que abre a porta e adiciona um destino para o elevador
	private void AbrirPorta()
	{
		
		floorCurrent = floorDestiny;
		
		if(person != null)
		{
			if(floorCurrent == person.floorDestiny)
			{
				person.rect.x = 40;
				person.free = false;
				person = null;
				existPerson = false;
				
			}else {
				if(floorCurrent == person.floor.numberFloor && existPerson)
				{
					
					floorDestiny = person.floorDestiny;
					
					
				}else {
					floorCurrent = person.floor.numberFloor;
				}
				
			}
		}
		
	}
	
	//Metodo utilizado para seguir o andar destinado
	private void VisitarAndar(float gameTime)
	{
		if( Distance(floor[floorDestiny].rect.y , floor[floorCurrent].rect.y) > 0 && Distance(floor[floorDestiny].rect.y , (int)rectClosed.y) > 10 )
		{
			SetAnimation(-1000,0);
				if(rectClosed.y < floor[floorDestiny].rect.y) 
				{
					rectClosed.y += speed  * gameTime;
					rectOpen.y +=  speed  * gameTime;
					
					if(this.person != null)
					{
						if(floorDestiny == this.person.floorDestiny)
						{
							this.person.rect.y = rectClosed.y;
							this.person.rect.x = 40.0f;
						}
					}
					
				}else 
				{
					rectClosed.y -= speed  * gameTime;
					rectOpen.y -=  speed  * gameTime;
					
					if(this.person != null)
					{
						if(floorDestiny == this.person.floorDestiny)
						{
							this.person.rect.y = rectClosed.y;
							this.person.rect.x = 40.0f;
						}
					}
					
					
				}
				
		}
		else 
		{
			//System.out.println("Saiuuu");
			SetAnimation(0,-1000);
			AbrirPorta();
			
		}
	}
	
	//Metodo utilizado para calcular a distancia entre um andar e outro
	private int Distance(float destiny, float current)
	{
		int distance = Math.abs((int)destiny - (int)current);
		return distance;
	}
	
	//Metodo para trocar a imagem de fechado e aberto do elevador
	private void SetAnimation(int open, int closed)
	{
		rectOpen.x = open;
		rectClosed.x = closed;
	}
	
}
