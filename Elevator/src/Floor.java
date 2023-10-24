
public class Floor {
	
	public int numberFloor;
	
	public Rect rect;
	
	public Floor(int numberFloor, int positionY) {
		this.numberFloor = numberFloor;
		rect = new Rect(0,positionY,Building.screen.x,10);
	}
	
	public int GetFloor()
	{
		return numberFloor;
	}
}
