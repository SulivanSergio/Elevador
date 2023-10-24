
public class Floor {
	
	public int numberFloor;
	protected int positionY;
	public Rect rect;
	
	public Floor(int numberFloor, int positionY) {
		this.numberFloor = numberFloor;
		this.positionY = positionY;
		rect = new Rect(0,this.positionY,Form.screen.x,10);
		//Form.Paint(rect);
	}
	
	public int GetFloor()
	{
		return numberFloor;
	}
}
