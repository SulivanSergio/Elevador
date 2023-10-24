public class GameLoop {
	
	public static boolean rodando = true;
	int FPS_TOTAL = 30;
	public static int FPS =0;
	
	public GameLoop() {
		Start();
		Loop();
	}
	public void Loop() {
		
		float gameTime = 0;
		while(rodando) {
			
			long inicio = System.currentTimeMillis();
			
			Update(gameTime);
			Draw();
			
			long fim = System.currentTimeMillis();
			gameTime = (float)(fim - inicio)* 0.001f;
			
		}
	}
	
	public void Start(){
		
		
	}

	public void Update(float gameTime) {
		
	}
	
	public void Draw() {
		
	}
	
	
}