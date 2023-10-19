
public class GameLoop {
	
	public boolean rodando = true;
	
	public GameLoop()
	{
		Start();
		Loop();
	}
	
	
	public void Loop() 
	{
		
		float gameTime = 0;
		while(rodando) {
			
			long inicio = System.currentTimeMillis();
			
			Update(gameTime);
			
			long fim = System.currentTimeMillis();
			gameTime = (float)(fim - inicio) * 0.001f;
			
			Draw();
			
		}
		
	}
	
	
	public void Start() 
	{
		
	}
	public void Update(float gameTime) 
	{
		
	}
	public void Draw() 
	{
	
	}
}
