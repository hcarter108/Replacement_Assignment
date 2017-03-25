package src_main;

import java.util.Comparator;

public class myComparator implements Comparator<Rectangle>{

	//Order descending
	@Override
	public int compare(Rectangle rec1, Rectangle rec2) {
		if (rec1.getArea()>rec2.getArea())
			return -1;
		else if (rec1.getArea()<rec2.getArea())
			return 1;
		else 
			return 0;
	}
}
