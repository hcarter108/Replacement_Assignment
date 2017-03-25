package src_main;

public class Rectangle implements Comparable<Rectangle>{

	//Only need area for this assignment
	private double area;
	
	public Rectangle(double side1, double side2)
	{
		this.area= side1*side2;
	}
	
	public double getArea() 
	{
		return area;
	}

	@Override
	public int compareTo(Rectangle rec) {
		return (int) (this.getArea()-rec.getArea());
	}
}
