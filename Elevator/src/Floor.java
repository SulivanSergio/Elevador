
public class Floor {
	
	int id;
	int positionY;
	Image image;
	
	public Floor(int id, int positionY) {
		this.id = id;
		this.positionY = positionY;
		image = new Image(0,this.positionY,Form.screen.x,10, "Image/Floor.png");
	}
	
	public int GetFloor()
	{
		return id;
	}
}
