
public class Floor {
	
	public int numberFloor;
	protected int positionY;
	protected Image image;
	
	public Floor(int numberFloor, int positionY) {
		this.numberFloor = numberFloor;
		this.positionY = positionY;
		image = new Image(0,this.positionY,Form.screen.x,10, "Image/Floor.png");
	}
	
	public int GetFloor()
	{
		return numberFloor;
	}
}
